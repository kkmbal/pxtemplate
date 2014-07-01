<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>

<script type="text/javascript" >
	var eamAdmnF = '${eamAdmnF}';
	var boardId = '${boardId}';
	var commonZNodes = null;
	var myBoardList = '';
	var commonBoardtreeObj = null;
	var myZNodes = null;
	var cmObjHeight = null;
	var myObjHeight = null;
	//스크롤 영역
	var scrollFlag = false;
	
	
	var fnDrawScrollBar = function(){
			
			$("#commonBoardListDiv").css({
				'height' : '310'
				, 'visibility' : 'true'
				, 'overflow-x': 'auto'
				, 'overflow-y': 'auto'
			});
	};

 
	
	var setting = {
		edit: {
				enable: false,
				showRemoveBtn: false,
				showRenameBtn: false
		},	
		view: {
			dblClickExpand: false,
			showLine : true,
			showTitle : false,
			selectedMulti:true
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
			onClick: zTreeOnClick /*,
			beforeDrop: zTreeBeforeDrop */
		}
		
	};
	
	
	var isTreeExpand = true;
	function zTreeOnClick(event, treeId, treeNode) {
		
		 if(treeId == 'commonBoardtreeObj'){
			   if(treeNode.boardId != ""){				   
				   doBoardList(treeId, treeNode.boardId, treeNode.boardKind, treeNode.boardForm, treeNode.boardFormSpec);
			   }
		    }
	};
	
	var doBoardList = function(treeId, bid, boardKind, boardForm, boardFormSpec){

		if(treeId == 'commonBoardtreeObj'){   //공용  
			if (bid == 'BBS999999'){  //임시게시판이면
				parent.document.getElementById("bbsFrame").src= '${WEB_HOME}/board240/getTmpBoardInfoList.do?boardId='+bid;
			}else{
				if (boardForm == '030' && boardFormSpec == '010'){  //이미지형
					parent.document.getElementById("bbsFrame").src='${WEB_HOME}/board211/getBbsImgBoardNotiList.do?boardId='+bid;
				}else if (boardForm == '030' && boardFormSpec == '020'){  //동영상형
					parent.document.getElementById("bbsFrame").src='${WEB_HOME}/board212/getBbsVideoBoardNotiList.do?boardId='+bid;
				}else if (boardForm == '020'){  //SNS형 게시판
					parent.document.getElementById("bbsFrame").src='${WEB_HOME}/board220/getBbsSnsBoardList.do?boardId='+bid;
				}else{
					parent.document.getElementById("bbsFrame").src='${WEB_HOME}/board210/getBoardInfoList.do?boardId='+bid;
				}
			}
		}
		
	};
	
	var doExpandNode = function(treeId, bExpand){
		   commonBoardtreeObj = $.fn.zTree.getZTreeObj("commonBoardtreeObj");
		   var nodes = commonBoardtreeObj.getSelectedNodes();
		   if (nodes.length>0) {
			   commonBoardtreeObj.expandNode(nodes[0], bExpand, true, true);
			   isTreeExpand = !isTreeExpand;
		   }
	};
	
	//tree 
	var treeReload = function(){
		//공통게시판
			boardType = "common";
			var isMatch = false;
			commonBoardtreeObj = $.fn.zTree.getZTreeObj("commonBoardtreeObj");
				/*
			    var renameNode = commonBoardtreeObj.getNodeByParam("name","미지정",null);
			    if(renameNode){
			    	renameNode.name = "게시판";
			    	commonBoardtreeObj.editName(renameNode);
			    }
			    */

 				for(var i =0 ; i < commonZNodes.length ;i++){
					if(commonZNodes[i].boardId == boardId){
 						var nodes = commonBoardtreeObj.getNodeByParam("boardId",boardId,null);
 						commonBoardtreeObj.selectNode(nodes);
//						commonBoardtreeObj.expandAll(true);
 						isMatch = true;
					}
 				}
				
				if(!isMatch){
					commonBoardtreeObj.expandAll(true);
					expandNodes(commonBoardtreeObj.getNodes());
				}
				
			
	};
	
	var readNodes=[];
	//게시판 권한 Setting
	var fnSetAuthList = function()
	{
		for (var i=0; i < commonZNodes.length; i++)
	 	{
			var json = commonZNodes[i];
			
			for (var j=0; j < myBoardList.length; j++)
			{
				if (json.boardId == myBoardList[j].boardid)
				{
					fnPushData(json);
					fnAddAuthList(json.pId);
				}
			}
		 }
		if (readNodes.length > 0)
		{
			commonZNodes  = readNodes;	
			readNodes = [];
		}
		 
	};

	var fnAddAuthList = function(pid)
	{
		for (var i=0; i < commonZNodes.length; i++)
	 	{
			var json = commonZNodes[i];
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
	
	var fnGetCommonBoardInfoListForZTree = function(){//공통게시판 
		$('#commonBoardtreeObj li').remove();
		PortalCommon.getJson({
			url: "${WEB_HOME}/organization/getCommonBoardListForZtree.do?format=json&type=1&kind=2&admin=1",
			success :function(data){
				if(data.jsonResult.success ===true){
					var list = JSON.stringify(data.categoryList);
					var categoryList = $.parseJSON(list);
					commonZNodes =  $.parseJSON(categoryList);
					if (eamAdmnF != 'E')
					{
						myBoardList = data.myBoardList;
						fnSetAuthList();
					}					
					
					cmObjHeight = commonZNodes.length * 18;
					fnDrawZTree();
					fnDrawScrollBar();
				}
			}
	 	});
	};
	
	
	var fnDrawZTree = function(){
		
			
			$.fn.zTree.init($("#commonBoardtreeObj"), setting, commonZNodes);
			treeReload();
			

		$('.tree').css({
			'margin-bottom':'0'
		   ,'height':'200px'
		});
	};
	
	var fnDoAllOpen = function(gubun){
		if(gubun == 'cm'){
			
			fnGetCommonBoardInfoListForZTree();
		}
	};
	
	
	
	
	
	var select_idx = 0;
	var search_ok = false;
	
	
	function expandNodes(nodes) {
		if (!nodes)
			return;
		curStatus = "expand";
		var zTree = null;
		
		zTree = $.fn.zTree.getZTreeObj("commonBoardtreeObj");
		
		for ( var i = 0, l = nodes.length; i < l; i++) {
			
			if(nodes[i].boardId == ""){
				zTree.expandNode(nodes[i], false, false, false);
			}
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}
	
	var fnGetZtreeHeight = function(mode, boardType){
		var rtnCnt = 1, height = 0;
		if(boardType == "common"){
			
			if(mode =="open"){
				height = cmObjHeight;
			}else{
				for(var i=0;i <  commonZNodes.length ;i++){
					
					if(commonZNodes[i].pId == '1'){
						rtnCnt++;
					}
				}
				height = rtnCnt * 18;
			}
			
			
		}
		return height;
	};
	
	function expandNodesOpen(nodes) {
		if (!nodes)
			return;
		curStatus = "expand";
		var zTree = null;
		zTree = $.fn.zTree.getZTreeObj("commonBoardtreeObj");
		for ( var i = 0, l = nodes.length; i < l; i++) {
			if(nodes[i].boardId == ""){
				zTree.expandNode(nodes[i], false, false, false);
			}
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			} else {
				goAsync = true;
			}
		}
	}
	
	
	var fnSetExtendsBtnToggle = function(btn, id){
		if(btn == "+"){
			$("#"+id).removeClass('ico_lnbcl');
			$("#"+id).addClass('ico_lnbop');
		}else if (btn == "-"){
			$("#"+id).removeClass('ico_lnbop');
			$("#"+id).addClass('ico_lnbcl');
		}
	};
	
	var fnGetExtendsBtnToggle = function(id){
		var isExtends;
		
		if($("#"+id).attr("class") == "ico_lnbop" ){
			isExtends = true;
		}else if($("#"+id).attr("class") == "ico_lnbcl" ){
			isExtends = false;
		}
		
		return isExtends;			
	};
	
	var fnGetExtendControl = function( tagDiv, tagUl, tagA , func, data){
		
		var objDiv = $("#"+tagDiv);
		var objUl_Li = $("#"+tagUl+" li");
		var objA = $("#"+tagA);
		var cssHeight = 0;
		if(objUl_Li.length == 0){
			objDiv.parent(".my_soc").css({
				'min-height': '20px'
				, 'height': '20px'
			});
			fnSetExtendsBtnToggle('-',tagA);
		}else{
			if(fnGetExtendsBtnToggle(tagA) == false ){

				if( objUl_Li.length == 1 ) cssHeight = 47;
				else if ( objUl_Li.length == 2 ) cssHeight = 66; 
				else cssHeight = objUl_Li.length * 27 ;
				
				objDiv.parent(".my_soc").css({
					'min-height': cssHeight+'px'
					, 'height': cssHeight+'px'
					, 'overflow-x': 'auto'
					,'overflow-y': 'hidden'
				});

				if( scrollFlag == true )
				{

					cssHeight+=30;
					objDiv.parent(".my_soc").css({
						'min-height': cssHeight+'px'
						, 'height': cssHeight+'px'
						, 'overflow-x': 'auto'
						,'overflow-y': 'hidden'
					});
					scrollFlag = false;
				}
				
				if(data.length > 3){
					fnSetExtendsBtnToggle('+',tagA);
					objA.attr("href", "javascript:"+func+"()");
				}else{
		
					fnSetExtendsBtnToggle('-',tagA);
					objA.attr("href", "#");
				}
		
			}else if(fnGetExtendsBtnToggle(tagA) == true ){
				
				objDiv.parent(".my_soc").css({
					
					 'overflow-x': 'hidden'
				});
			
				if(data.length > 10){
					
					objDiv.parent(".my_soc").css({
						'min-height': '220px'
						, 'height': '220px'
						, 'visibility' : 'true'
							, 'overflow-x': 'auto'
						, 'overflow-y': 'auto'
					});
					
				}else{
					
					if(data.length == 1 ) cssHeight = 47;
					else if ( objUl_Li.length == 2 ) cssHeight = 66; 
					else cssHeight = data.length * 23;
					
					objDiv.parent(".my_soc").css({
						'min-height': cssHeight+'px'
						, 'height': cssHeight+'px'
						, 'overflow-x': 'auto'
						, 'overflow-y': 'hidden'
					});
				}
		
				fnSetExtendsBtnToggle('-',tagA);
				objA.attr("href", "javascript:"+func+"()");
				
			}
			$("#"+tagUl+" li:last").addClass('last');
		}
		
	};
	
	
	//MY게시판
	var fnUserBbsList = function(){
		var userBbsCnt = 0;
		
		PortalCommon.getJson({
			url: "${WEB_HOME}/lnb/getUserBbsList.do?format=json",
			success :function(data){
				if (data.jsonResult.success ===true){
					var json = $.parseJSON(data.resultList);
					
					$("#userBbsList li").remove();

					for (var i = 0 ; i< json.length ;i++) {
						if (fnGetExtendsBtnToggle('userBbsListA') == false) {//처음엔 3개만 출력
							if (userBbsCnt < 3) {
								if (parseInt(json[i].nameLen) > 18) scrollFlag = true;
								$("#userBbsList").append('<li id="'+json[i].boardId+'""><a href="javascript:doBoardList(\'myBoardtreeObj\',\''+json[i].boardId+'\',\''+json[i].boardKind+'\',\''+json[i].boardForm+'\',\''+json[i].boardFormSpec+'\')">'+json[i].boardName+'</a></li>');
								userBbsCnt++;	
							}
						}else if (fnGetExtendsBtnToggle('userBbsListA') == true) {
							$("#userBbsList").append('<li id="'+json[i].boardId+'""><a href="javascript:doBoardList(\'myBoardtreeObj\',\''+json[i].boardId+'\',\''+json[i].boardKind+'\',\''+json[i].boardForm+'\',\''+json[i].boardFormSpec+'\')"  class="te_dot te93 userBbs" title="'+json[i].boardName+'">'+json[i].boardName+'</a></li>');
							userBbsCnt++;
						}
					}
					
					fnGetExtendControl('userBbsListDiv','userBbsList','userBbsListA','fnUserBbsList',json );
					
				}
			}
	 	});
		

	};	
	
	
	
	
	var boardType = "common";
	$(document).ready(function(){
		fnGetCommonBoardInfoListForZTree();
		
		$("#btn_all_open_cm").click(function() {//공통게시판 모두열림
			//location.reload();
			commonBoardtreeObj.expandAll(true);
		});
		$("#btn_all_close_cm").click(function() {//공통게시판 모두닫힘
			expandNodes(commonBoardtreeObj.getNodes());
		});
		
	});
	
</script>
<div class="lnb_bor">
	<div class="bbs_board">
		<div class="lnb_clop">
			<a href="#" class="ma_rig10" id="btn_all_close_cm"><span class="ico_allcl"></span>모두닫음</a>|
			<a href="#" class="ma_lef10" id="btn_all_open_cm"><span class="ico_allop"></span>모두펼침</a>
		</div>
		<div class="tree">
			<div class="content_wrap">
				<div id="commonBoardListDiv">
					<ul id="commonBoardtreeObj" class="ztree"></ul>
				</div>	
			</div>
		</div>
	</div>


<!-- 	<div class="my_soc ma_let5" style="min-height:20px">	 -->
<!-- 		<p><a href="#">참여게시판</a><a href="#" class="ico_lnbcl" id="partListA">닫기</a></p> -->
<!-- 		<div id="partDd"> -->
<!-- 			<div  id="partListDiv"> -->
<!-- 				<ul id="partList"> -->
<!-- 				</ul> -->
<!-- 			</div> -->
<!-- 		</div> -->
<!-- 	</div> -->

</div>
