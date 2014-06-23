<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
    var nodeCount = 0;
    var type = '${type}';
	var kind = '${kind}';
	var notiId = '${notiId}';
	var userNotiSeq = '${userNotiSeq}';
	var mode = '${mode}';
	var boardForm = '${param.boardForm}';
	var selectBoardForm = '';
	var superAdmin = '${superAdmin}';
	var admin = '${admin}';
	var selectNodeId = '';
	var myBoardList = '';	
	var fnCategorySearch = function(){
		PortalCommon.getJson({
			url: "${WEB_HOME}/organization/getCategoryList.do?format=json&type="+type+"&kind="+kind+'&admin=1',
			success :function(data){
				if(data.jsonResult.success ===true){					
					var list = JSON.stringify(data.categoryList);
					zNodes = list;
					
				}
			}
		});
	};
	
	var fnGetBoardId = function(){
		var jsonBoardIdObject = {
				boardId : []
		}
		var y = 0;
		for (var i=0; i < zNodes.length; i++)
		{
			if(zNodes[i].boardId.length > 0 ){
				var json = zNodes[i];
				//jsonBoardIdObject. json.boardId
				
				var jsonObject = {
					id: json.boardId
				};
				jsonBoardIdObject.boardId[y] = jsonObject;	
				y++;
			}
		}
		
		return JSON.stringify(jsonBoardIdObject);
	}
	
	var getMoveJsonData = function(){
		
		var notiIdArray = null;
		if(mode == 'cm_move'){
			notiIdArray = $.parseJSON(notiId);
		}else{
			notiIdArray = $.parseJSON(userNotiSeq);
		}
		
		var jsonMoveArray = [];
		var jsonBoardInfoObject = null;
		
		for(var i=0 ; i < notiIdArray.length ; i++){
			var jsonObject = {
	    			'id' : notiIdArray[i].id
	    	};
			jsonMoveArray[i] = jsonObject;
		}
		
		if(mode == 'cm_move'){
			jsonBoardInfoObject = {
					'boardId'   : '', 
					'notiId'    :[]
				};	
			jsonBoardInfoObject.notiId = jsonMoveArray;
		}else if(mode == 'my_move'){
			jsonBoardInfoObject = {
					'boardId'   : '', 
					'userNotiSeq'    :[]
				};	
			jsonBoardInfoObject.userNotiSeq = jsonMoveArray;
		}
		jsonBoardInfoObject.boardId = selectNodeId;
		
		//넘겨받은 게시글이 있다면 해당 게시물을 선택한 게시판으로 이동시킨다.
		var moveData = "";
		if(notiIdArray.length > 0){
			moveData = JSON.stringify(jsonBoardInfoObject);
		}
		
		return moveData;
	};
	
	var fnBbsDelInfoPop = function(delDiv){

		cateForm.action = '${WEB_HOME}/board210/bbsDelInfoPop.do?notiId='+notiId+'&boardId='+selectNodeId+'&delDiv='+delDiv;
		cateForm.target = "_self";
		cateForm.submit();
		
	};
	
	var fnCategoryInsert = function(){
		if(mode == "cm_move"){//게시글 이동 
			if(selectNodeId == null || selectNodeId ==""){alert("게시판을 선택해 주세요.");return;}
			if(selectBoardForm != boardForm ){
				alert('같은형태의 게시판으로만 이동이 가능합니다.');
				return;
			}
			PortalCommon.getJson({
				url: "${WEB_HOME}/board210/insertBbsNotiInfoForMove.do?format=json&",
				data: {'moveData' : getMoveJsonData()},
				success :function(data){
					if(data.jsonResult.success ===true){
						
						if(opener.pageId == 'boardList'){
							opener.fnSearchList('default');
						}else if(opener.pageId == 'boardView'){
							opener.doPageReload();
						}
						window.close();
					}
				}
			});
			
		}else if(mode == "my_move"){
			if(selectNodeId == null || selectNodeId ==""){alert("게시판을 선택해 주세요.");return;}
			PortalCommon.getJson({
				url: "${WEB_HOME}/board310/insertPbsUserNotiInfoForMove.do?format=json&",
				data: {'moveData' : getMoveJsonData()},
				success :function(data){
					if(data.jsonResult.success ===true){
						
						if(opener.pageId == 'pBoardList'){
							opener.fnSearchList('default');
						}else if(opener.pageId == 'pBoardView'){
							opener.doPageReload();
						}
						window.close();
					}
				}
			});
		}else if(mode == 'cm_move_adm'){			
			fnBbsDelInfoPop('MOV');
		}else{//카테고리 등록
			PortalCommon.getJson({
				url: "${WEB_HOME}/organization/insertCategoryList.do?format=json&type="+type+"&kind="+kind,
				data: {'conts' : fnGetAllData()},
				success :function(data){
					if(data.jsonResult.success ===true){
						opener.location.reload();
						window.close();
					}
				}
			});
		}
	};
	
// 	 var zNodes = ${categoryList};
	 
// 	 for (var i=0; i < zNodes.length; i++)
// 	 {
// 		 var json = zNodes[i];
// 		 if (json.id >= nodeCount)
// 		 {
// 			 nodeCount = json.id;  
// 		 }
// 	 }
// 	 nodeCount++; 
	 
	 function callbackAddBbsList(data){
			
		var nodes = zTree.getSelectedNodes();
		if (nodes.length>0) 
		{
			var json = $.parseJSON(data);		
			for (var i=0; i < json.length; i++)
			{
				var jsonObject = {
						 id : nodeCount ++,
						 pId : nodes[0].id,
						 name : json[i].name,
						 boardId:json[i].id,
						 icon : "../resources/images/img/img_board.gif"
				 };			
				 zNodes.push(jsonObject);
			}
			treeReload();
		}
		else
		{
			alert('추가할 카테고리를 선택하세요');
			return;
		}
					
	}
	
	var setting = {
		edit: {
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
		},	
		view: {
			dblClickExpand: false,
			showLine:false
		},
		data: {
			simpleData: { 
				enable: true
			}
		},
		check: {
			enable: false
		},
		callback: {
			onClick: zTreeOnClick,
			onRightClick: OnRightClick,
			beforeDrop: zTreeBeforeDrop,
			onRename: zTreeOnRename
		}
	};
	
	if (type == '1')  //조회용
	{
		setting = {
				edit: {
						enable: false,
						showRemoveBtn: false,
						showRenameBtn: false
				},	
				view: {
					dblClickExpand: false,
					showLine:false
				},
				data: {
					simpleData: { 
						enable: true
					}
				},
				check: {
					enable: false
				},
				callback: {
					onClick: zTreeOnClick/* ,
					beforeDrop: zTreeBeforeDrop */
				}
			};
	}
	
	function zTreeOnClick(event, treeId, treeNode) {
	   if (treeNode.boardId != ''){
		   if(opener.pageId == 'AdminBoardNotiList'){//부모창에 boardId 값 넘기기 
			   $(opener.location).attr( "href" , "javascript:FN_SET_TREEBOARDID('"+treeNode.boardId+"');");
			   self.close();
		   }else if(  opener.pageId == 'boardList' 
				   || opener.pageId == 'boardView'
				   || opener.pageId == 'pBoardList'
				   || opener.pageId == 'pBoardView'){//공용, 게인 게시판 
		   	
			   selectNodeId = treeNode.boardId;
			   selectBoardForm = treeNode.boardForm;
		   }
	   	}
	};
	
	function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
		if(targetNode == null) 
		{
			return false;
		}
		if (targetNode.boardId != '')
		{
			alert('게시판 목록 하위로 이동할 수 없습니다. ');
			return false;
		}

		for (var i=0; i < zNodes.length; i++)
		{
			var json = zNodes[i];
			if (treeNodes[0].id == json.id)
			{
				json.pId = targetNode.id;
				break;
			}
		}
		treeReload(); 
	    return false;
		
	    //return !(targetNode == null || (moveType != "inner" && !targetNode.parentTId));
	};
	
	function zTreeOnRename(event, treeId, treeNode) {
		for (var i=0; i < zNodes.length; i++)
		{
			var json = zNodes[i];
			if (treeNode.id == json.id)
			{
				json.name = treeNode.name;
			}
		}
	}
	
	function OnRightClick(event, treeId, treeNode) {		
		if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			showRMenu("node", event.clientX, event.clientY);
		}
	};
	
	function showRMenu(type, x, y) {
		$("#rMenu ul").show();
		if (type=="root") {
			$("#m_del").hide();
		} else {
			$("#m_del").show();
		}
		rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
	
		$("body").bind("mousedown", onBodyMouseDown);
	}
	function hideRMenu() {
		if (rMenu) rMenu.css({"visibility": "hidden"});
		$("body").unbind("mousedown", onBodyMouseDown);
	}
	function onBodyMouseDown(event){
		if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
			rMenu.css({"visibility" : "hidden"});
		}
	}
	
	//새로고침
	var treeReload = function()
	{
		 var treeObj = $.fn.zTree.init($("#treeObj"), setting, zNodes);
		treeObj.expandAll(true);
		
		
		if (admin != '1')
		{
			$("#treeObj .ico_docu").css("background-position-x", "8px");
			$("#treeObj .ico_docu").css("background-position-y", "6px");
			$("#treeObj .noline_docu").css("display", "none");
		}
	}
	
	var addCount = 1;
	function addTreeNode() {
		hideRMenu();
		
		var nodes = zTree.getSelectedNodes();
		if (nodes.length>0) 
		{
			var jsonObject = {
					 id : nodeCount++,
					 pId : nodes[0].id,
					 name : "카테고리"+(addCount++),
					 boardId:"",
					 icon : "../resources/images/img/img_category.png"
			 };			
			zNodes.push(jsonObject);
			treeReload();
		}
		else
		{
			alert('추가할 카테고리를 선택 하세요');
			return;
		}
	}
	
	function removeId(id)
	{
		for (var i=zNodes.length-1 ; i >=0 ; i--)
		{
			var json = zNodes[i];
			if (id == json.id)
			{
				if (json.pId == '0')
				{
					alert('최상위 카테고리는 삭제 할 수 없습니다.');
					return;
				}
				zNodes.splice(i,1);
			}
			removePId(id);
		}
	}
	
	function removePId(id)
	{
		var rtnId = '';
		for (var i=zNodes.length-1; i >= 0 ; i--)
		{
			var json = zNodes[i];
			if (id == json.pId)
			{
				rtnId = json.id;
				zNodes.splice(i,1);
				break;
			}
		}
		
		if (rtnId != '')
		{
			removePId(rtnId);
		}
	}
	
	function removeTreeNode() {
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		
		removeId(nodes[0].id);
		treeReload();
	}
	function checkTreeNode(checked) {
		var nodes = zTree.getSelectedNodes();
		if (nodes && nodes.length>0) {
			zTree.checkNode(nodes[0], checked, true);
		}
		hideRMenu();
	}
	function resetTree() {
		hideRMenu();
		$.fn.zTree.init($("#treeObj"), setting, zNodes);
	}

	var fnBoardAdd = function()
	{
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		if (nodes[0].boardId != '')
		{
			alert('게시판에 하위 게시판을 추가 할 수 없습니다. 카테고리를 선택하세요');
			return;
		}
		//공통게시판
		//PortalCommon.popupWindowCenter('../board/getBbsChartList.do?callback=callbackAddBbsList', '게시판리스트',1200,800);
		//사용자게시판
		PortalCommon.popupWindowCenter('../board/getMyBbsChartList.do?callback=callbackAddBbsList', '게시판리스트',1200,800);
	};
	
	var renameTreeNode = function()
	{
		hideRMenu();
		var nodes = zTree.getSelectedNodes();
		zTree.editName(nodes[0]);
	};

	var fnGetAllData = function()
	{
		return JSON.stringify(zNodes);
	};
	
	var zTree, rMenu;
	var readNodes=[];
	$(document).ready(function(){
		
		//게시판 권한 Setting
		var fnSetAuthList = function()
		{
			for (var i=0; i < zNodes.length; i++)
		 	{
				var json = zNodes[i];
				
				for (var j=0; j < myBoardList.length; j++)
				{
					if (json.boardId == myBoardList[j].boardid)
					{
						fnPushData(json);
						fnAddAuthList(json.pId);
					}
				}
				
				/* if (json.boardId == '107'||json.boardId == '102'||json.boardId == '103')
				{
					fnPushData(json);
					fnAddAuthList(json.pId);
				} */
			 }
			if (readNodes.length > 0)
			{
				zNodes  = readNodes;	
				readNodes = [];
			}
			 
		};

		var fnAddAuthList = function(pid)
		{
			for (var i=0; i < zNodes.length; i++)
		 	{
				var json = zNodes[i];
				if (json.id == pid)
				{
					fnPushData(json);
					if (json.pId != 0)
					{
						fnAddAuthList(json.pId);
					}
				}
		 	}
		}

		var fnPushData = function(obj)
		{
			var contains = false;
			for (var i=0; i < readNodes.length; i++)
			{
				var json = readNodes[i];
				if (json == obj)
				{
					contains = true;
					break;
				}
			}
			if (contains == false)
			{
				readNodes.push(obj);
			}		
		};
		
		var data = ${categoryList};	
		zNodes = $.parseJSON(data);	
		
		if (superAdmin != 'E')
		{
			myBoardList = ${myBoardList};
			fnSetAuthList();
		}
		
		$.fn.zTree.init($("#treeObj"), setting, zNodes);
		zTree = $.fn.zTree.getZTreeObj("treeObj");
		rMenu = $("#rMenu");
		
		if(mode == "move"){
				
		}else if(mode == ""){
			if (type == '1')
			{
				$('.pop_footer').css('display','none');
				$('.pop_content').css({
					'margin-bottom':'0'
				});
				$('.pop_content .tree').css({
					'height':'395px'
				});
			}
		}
		
		
		
		treeReload();
		 /* 확인 ,닫기 EVENT */		
		 $("#btnOK, #btnClose").click(function(){
			var btnId = $(this).attr("id");			
			switch(btnId){
				case "btnOK": //확인
					//fnGetAllData();
					fnCategoryInsert();
					
					break;
				case "btnClose": //닫기
					/* fnSetAuthList();
					treeReload(); */
					window.close();				
					//alert(JSON.stringify(zNodes));				
					//fnGetAllData();
					break;
			};
		 });
		 
		 
	});

	
	
		
</script>
 <style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color:#fff; text-align: left;color:#666; border:1px solid #7a7a7a; min-width:100px;height:auto;z-index:1000}
	div#rMenu ul li {list-style: none outside none;}
	div#rMenu ul li a {padding:3px 5px 0px 5px; display:block;}
	div#rMenu ul li a:hover {background-color:#eee;} 
</style>
</head>
 
<body>
<form name="cateForm" method="post">

</form>	
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();"><a href="#">카테고리 추가</a></li>
		<li id="m_del" onclick="removeTreeNode();"><a href="#">카테고리 삭제</a></li>
		<!-- <li id="m_reset" onclick="resetTree();"><a href="#">초기화</a></li> -->
		<li id="m_rename" onclick="renameTreeNode()"><a href="#">이름바꾸기</a></li>
		<li id="m_rename" onclick="fnBoardAdd()"><a href="#">게시판 추가</a></li>
	</ul>	
</div>

<!--pop_wrap-->
<div class="pop_wrap" style="width:400px;"><!-- 팝업사이즈 width:400px; height:500px; -->
	<div class="pop_header">카테고리 관리</div>
	<div class="pop_content">
	<!-- popup 본문 -->
		<div class="tree" style="border:1px solid #eee;">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left ma_top5">
					<ul id="treeObj" class="ztree"></ul>
				</div>	
		</div>
		</div>
	<!-- popup 본문 -->	
	</div>
	<!-- popup 본문 -->
	<div class="pop_footer">
		<!--btn_area-->         
	    <div class="btn_area">
	        <div class="fl_cen">
	            <a id="btnOK" class="btn_basic2 bw70">
				    <span class="fo_bold btn_text">확인</span>
				</a>
				<a id="btnClose" class="btn_basic2 bw70" style="cursor: pointer;">
				    <span class="fo_bold btn_text" style="cursor: pointer;">닫기</span>
				</a>
	        </div>
	     </div>
	     <!--//btn_area-->  
	 </div>  
</div>	
<div id ="div_text">

</div>
<!--//pop_wrap-->
</body>
</html>
</html>