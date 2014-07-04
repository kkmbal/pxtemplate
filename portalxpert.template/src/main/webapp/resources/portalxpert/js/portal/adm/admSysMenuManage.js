var settingMenu = {
		edit: {
			enable: true,
			showRemoveBtn: false,
			showRenameBtn: false 
		},	
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: { 
				enable: true,
				rootPId:0
			}
		},
		check: {
			enable: true,
			chkboxType : {"Y":"p", "N":"s"}
		},
		onCheck: zTreeOnCheck,
		callback: {
			onClick: zTreeOnClick,
 			beforeDrop: zTreeBeforeDrop,
 			onDrop: zTreeOnDrop,
 			onRename: zTreeOnRename
		} 
	};
	
var fnCategoryDrawScrollBar = function(){
	
	$("#boardCategoryListDiv").css({
		'height' : '550px'
		, 'visibility' : 'true'
		, 'overflow-x': 'auto'
		, 'overflow-y': 'auto'
		, 'padding': '5px 0'
	});
};

function zTreeOnCheck(event, treeId, treeNode) {
};

function zTreeOnClick(event, treeId, treeNode) {
	console.log(treeNode)
	$("#menuId").val(treeNode.id);
	$("#menuNm").val(treeNode.name);
	$("#menuUrl").val(treeNode.page);
	
};

//이름변경
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

function zTreeOnDrop(event, treeId, treeNodes, targetNode, moveType) {
	
	if (moveType == 'inner')
	{
		if (treeNodes[0].pId == null)
		{
			for (var i=0; i < zNodes.length; i++)
			{
				var json = zNodes[i];
				if (treeNodes[0].id == json.id)
				{
					json.pId = 0;
					break;
				}
			}
		}
		else
		{
			for (var i=0; i < zNodes.length; i++)
			{
				var json = zNodes[i];
				if (treeNodes[0].id == json.id){
					if (json.id != targetNode.id){
						json.pId = targetNode.id;
					}else{
						json.pId = 0;
					}
					
					break;
				}
			}
		}
	}else{
		var moveNodeIdx = 0;
		for ( var i = 0; i < zNodes.length; i++) {
			var json = zNodes[i];
			if (treeNodes[0].id == json.id) {
				moveNodeIdx = i;
				break;
			}
		}
		
		var moveNode = zNodes[moveNodeIdx];
		var zTreeNodes = zTree.getNodesByParam("pId" ,moveNode.pId ,zTree.getNodeByParam("id" ,moveNode.pId ,""));

		if(zTreeNodes!=null && zTreeNodes.length>0){
			var idMin = zTreeNodes[0].id;
			var idMax = idMin;
			for(var i=0;i<zTreeNodes.length;i++){
				var tree = zTreeNodes[i];
				if(idMin>tree.id){
					idMin = tree.id;
				}
				if(idMax<tree.id){
					idMax = tree.id;
				}
			}			
			
			for(var i=idMin;i<=idMax;i++){
				//zNodes[i-1].id = i;
				zNodes[i].id = i;
			}
		}
	}    
};

//드래그
function zTreeBeforeDrop(treeId, treeNodes, targetNode, moveType) {
	if(targetNode == null && treeNodes.length!=1){
		return false;
	}
			
	if (moveType != 'inner') {
		var pos_idx = 0;
		for ( var i = 0; i < zNodes.length; i++) {
			var json = zNodes[i];
			if (targetNode.id == json.id) {
				pos_idx = i;
				break;
			}
		}
		var moveNodeIdx = 0;
		for ( var i = 0; i < zNodes.length; i++) {
			var json = zNodes[i];
			if (treeNodes[0].id == json.id) {
				moveNodeIdx = i;
				break;
			}
		}
			
		var moveNode = zNodes[moveNodeIdx];
		if(moveNode.pId != targetNode.pId){
			alert('정렬 이동은 카테고리 밖으로 이동할 수 없습니다. ');
	 		return false;
		}else{
			var moveNodeObj = zNodes.splice(moveNodeIdx ,1);
			zNodes.splice(pos_idx ,0 ,moveNodeObj[0]); //cut&&paste
		}
	}

	if (treeNodes[0].name == '미지정') {
		alert('[미지정] 카테고리는 이동 할 수 없습니다.');
		return false;
	}

	/* if (moveType != 'inner')
	{
		if (targetNode.pId == null)
		{
			if (treeNodes[0].boardId != '')
			{
				alert('게시판은 카테고리 안으로만 이동이 가능 합니다.');
				return false;
			}
		}
	} */

	//	alert('게시판은 카테고리안으로만 이동이 가능합니다.');
	//	zTree.cancelSelectedNode();

	/* for (var i=0; i < zNodes.length; i++)
	{
		var json = zNodes[i];
		if (treeNodes[0].id == json.id)
		{
			json.pId = targetNode.id;
			break;
		}
	} */

	/* if (treeNodes[0].pId == null)
	{
		for (var i=0; i < zNodes.length; i++)
		{
			var json = zNodes[i];
			if (treeNodes[0].id == json.id)
			{
				json.pId = 0;
				break;
			}
		}
	} */

	//treeReload(); 
	return true;

};

var addCount = 1;
function addTreeNode() {

	var jsonObject = {
		id : nodeCount++,
		pId : 0,
		name : "메뉴" + (addCount++),
		page : ""
		//icon : RES_HOME+"/images/img/img_category.gif"
	};
	var idx = 0;
	for ( var i = 0; i < zNodes.length; i++) {
		var json = zNodes[i];
		if (json.id == 999) {
			idx = i;
			break;
		}
	}

	/*
	if (zNodes.length > 0) {
		zNodes.splice(idx, 0, jsonObject);
	} else {
		zNodes.push(jsonObject);
	}
	*/
	zNodes.push(jsonObject);

	var treeObj = $.fn.zTree.init($("#categoryTreeObj"), settingMenu, zNodes);
	checkState(treeObj);

};

//이름바꾸기
var renameTreeNode = function() {
	var nodes = zTree.getSelectedNodes();

	if (nodes[0].name == '미지정') {
		alert('미지정 카테고리는 수정할 수 없습니다.');
		return;
	}

	zTree.editName(nodes[0]);

};

var list = '';
function removeTreeNode() {

	var nodes = zTree.getSelectedNodes();

	/* if (nodes[0].boardId != '')
	{
		alert('게시판은 삭제 할 수 없습니다.');
		return;
	} 
	if (nodes[0].name == '미지정') {
		alert('미지정 카테고리는 삭제할 수 없습니다.');
		return;
	}
	*/
	list = '';
	fnSearchChildTree(nodes[0].id);
	if (list != '') {
		alert('하위 메뉴가 포함된 메뉴는 삭제할 수 없습니다.');
		return;
	}

	removeId(nodes[0].id);
	$.fn.zTree.init($("#categoryTreeObj"), settingMenu, zNodes);

};

function removeId(id) {
	for ( var i = zNodes.length - 1; i >= 0; i--) {
		var json = zNodes[i];
		if (id == json.id) {
			zNodes.splice(i, 1);
		}
		removePId(id);
	}
}

function removePId(id) {
	var rtnId = '';
	for ( var i = zNodes.length - 1; i >= 0; i--) {
		var json = zNodes[i];
		if (id == json.pId) {
			rtnId = json.id;
			zNodes.splice(i, 1);
			break;
		}
	}

	if (rtnId != '') {
		removePId(rtnId);
	}
}

//넘어온 아이디를 부모로 쓰이는 노드를 찾는다.
var fnSearchChildTree = function(id) {

	for ( var i = 0; i < zNodes.length; i++) {
		var json = zNodes[i];
		if (id == json.pId) {
			if (json.page != '') {
				list = json.page;
				break;
			} else {
				fnSearchChildTree(json.id);
			}
		}
	}

	return;
};

//게시판 삭제
function fnBoardDelete(id) {
	var jsonObject = {
		boardId : id
	};

	PortalCommon.getJson({
		url : WEB_HOME+"/board100/deleteBbsBoardInfo.do?format=json",
		data : {
			'data' : JSON.stringify(jsonObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
				location.reload();
			}
		}
	});
}

function expandNodes2(nodes) {
	if (!nodes)
		return;
	curStatus = "expand";
	var zTree = $.fn.zTree.getZTreeObj("categoryTreeObj");

	for ( var i = 0, l = nodes.length; i < l; i++) {

		if (nodes[i].boardId != "root" && nodes[i].boardId == "") {
			zTree.expandNode(nodes[i], false, false, false);
		}
		if (nodes[i].isParent && nodes[i].zAsync) {
			expandNodes2(nodes[i].children);
		} else {
			goAsync = true;
		}
	}
};

function checkState(treeObj){
	if(authCd == 'SYSTEM'){
		treeObj.checkAllNodes(true);
	}	
}

$(document).ready(function() {
	var data = menuList;
	zNodes = $.parseJSON(data);
	$.fn.zTree.init($("#categoryTreeObj"), settingMenu, zNodes);
	zTree = $.fn.zTree.getZTreeObj("categoryTreeObj");
	zTree.expandAll(true);
	checkState(zTree);
	fnCategoryDrawScrollBar();
	
	
	//권한코드
	for(var i=0;i<authCodeList.length;i++){
		$("#authCd").append("<option value='"+authCodeList[i].cdSpec+"'>"+authCodeList[i].cdNm+"</option>");
		$("#authCd2").append("<option value='"+authCodeList[i].cdSpec+"'>"+authCodeList[i].cdNm+"</option>");
	}

	for ( var i = 0; i < zNodes.length; i++) {
		var json = zNodes[i];
		if (json.id >= nodeCount) {
			nodeCount = json.id;
		}
	}

	nodeCount++;

	$("#btn_all_open_ca").click(function() {//공통게시판 모두열림
		var treeObj = $.fn.zTree.init($("#categoryTreeObj"), settingMenu,zNodes);
		treeObj.expandAll(true);
		checkState(treeObj);
	});

	$("#btn_all_close_ca").click(function() {//공통게시판 모두닫힘
		var treeObj = $.fn.zTree.init($("#categoryTreeObj"), settingMenu,zNodes);
		expandNodes2(treeObj.getNodes());
		checkState(treeObj);
	});

	//게시판 생성
	$("#btn_board_create").click(function() {
		location.href = WEB_HOME+"/board100/createAdminBbsView.do";
	});

	//게시판 수정
	$("#btn_board_update").click(function() {
		if ($("#menuId").val() == "") return;
		
		var treeObj = $.fn.zTree.getZTreeObj("categoryTreeObj");
		var nodes = treeObj.getSelectedNodes();
		//zTree.editName(nodes[0]);

		for (var i=0; i < zNodes.length; i++)
		{
			var json = zNodes[i];
			if ($("#menuId").val() == json.id)
			{
				json.name = $("#menuNm").val();
				json.page = $("#menuUrl").val();
			}
		}
		
		nodes[0].name = $("#menuNm").val();
		treeObj.updateNode(nodes[0]);
		
		
		$("#menuId").val("");
		$("#menuNm").val("");
		$("#menuUrl").val("");
		
	});

	//게시판 삭제
	$("#btn_board_delete").click(function() {

		var boardId = $(
				':radio[name="radio"]:checked')
				.val();
		if (boardId == undefined) {
			alert('삭제할 게시판을 선택하세요');
			return;
		}

		if (!confirm('삭제 하시겠습니까?')) {
			return;
		}
		fnBoardDelete(boardId);

	});

	$("#btn_catageory_create").click(function() {//카테고리 생성
		//
		addTreeNode();
	});

	$("#btn_catageory_rename").click(function() {//카테고리 이름변경
		var nodes = zTree.getSelectedNodes();

		if (nodes.length > 0) {
			if (nodes[0].boardId != '') {
				if (nodes[0].name == '부서업무공지') {
					alert('부서업무공지는 변경할 수 없습니다.');
					zTree.cancelSelectedNode();
					return;
				} else if (nodes[0].name == '경조사') {
					alert('경조사는 변경할 수 없습니다.');
					zTree.cancelSelectedNode();
					return;
				} else if (nodes[0].name == '임시저장') {
					alert('임시저장은 변경할 수 없습니다.');
					zTree.cancelSelectedNode();
					return;
				} else {

					alert('게시판명은 변경할 수 없습니다.');
					zTree.cancelSelectedNode();
					return;
				}
			}
		} else {
			alert("이름을 변경할 카테고리를 선택하세요");
			return;
		}

		renameTreeNode();
	});

	$("#btn_catageory_delete").click(function() {//삭제

		var nodes = zTree.getSelectedNodes();

		if (nodes.length > 0) {
			/*
			if (nodes[0].boardId != '') {
				if (nodes[0].name == '부서업무공지') {
					alert('부서업무공지는 변경할 수 없습니다.');
					return;
				} else if (nodes[0].name == '경조사') {
					alert('경조사는 변경할 수 없습니다.');
					return;
				} else if (nodes[0].name == '임시저장') {
					alert('임시저장은 변경할 수 없습니다.');
					return;
				} else {
					alert('게시판은 삭제 할 수 없습니다.');
					return;
				}

			}
			*/
			removeTreeNode();
		} else {
			alert("삭제할 메뉴를 선택하세요");
			return;
		}
	});

	//저장
	$("#saveMenu").click(	function() {

		if (!confirm('저장 하시겠습니까?')) {
			return;
		}

		var treeObj = $.fn.zTree.getZTreeObj("categoryTreeObj");
		var saveNodes = [];
		for (var i=0; i < zNodes.length; i++){
			var json = zNodes[i];
			var node = treeObj.getNodeByParam("id", json.id, null);

			if ( node.id == json.id && node.checked){
				saveNodes.push(json);
			}
		}

		console.log(JSON.stringify(saveNodes));
			
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/sys/updateMenuAuth.do?format=json",
			data : {
				'data' : unescape(JSON.stringify(saveNodes)),
				'authCd' : $("#authCd").val()
			},
			success : function(data) {
				if (data.jsonResult.success === true) {
					alert('정상적으로 처리되었습니다.');
					location.reload();
				};
			}
		});
	});
	
	$("#search").click(function(){ //조회
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/sys/getAuthMenu.do?format=json",
			data : 'authCd='+$("#authCd").val(),
			success : function(data) {
				if (data.jsonResult.success === true) {

					var zNewNodes = $.parseJSON(data.menuList);
					var treeObj = $.fn.zTree.getZTreeObj("categoryTreeObj");
					
					if(data.authCd == 'SYSTEM'){
						$(".fl_left").show();
						$("#btn_board_update").show();
					}else{
						$(".fl_left").hide();
						$("#btn_board_update").hide();
					}
					treeObj.checkAllNodes(false);
					
					for (var i=0; i < zNewNodes.length; i++)
					{
						var json = zNewNodes[i];
						var node = treeObj.getNodeByParam("id", json.id, null);

						if ( node.id == json.id){
							node.checked = true;
						}else{
							node.checked = false;
						}
						treeObj.updateNode(node, true);
					}					
					
					$("#menuId").val("");
					$("#menuNm").val("");
					$("#menuUrl").val("");
				};
			}
		});		
	});
	
	parent.document.getElementById("admFrame").height = "700px";
	parent.document.getElementById("admFrame").height = $(document).height()+"px";

});