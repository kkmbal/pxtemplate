<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<style type="text/css">
.ztree li span.button.ico_close{margin-right:2px; background: url(${RES_HOME}/images/zTree/house_default_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.ico_open{margin-right:2px; background: url(${RES_HOME}/images/zTree/selected_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.ico_docu{margin-right:2px; background: url(${RES_HOME}/images/zTree/house_default_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.search_area2 {padding-right:0;}	
</style>
    
</head>

<script type="text/javascript" >

  var view_mode = "1";
  var type = '${type}';
  var callbackFunction ="${callback}";
  var oucode = '${oucode}';

	var setting = {
		edit: {
				enable: false
		},	
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: { 
				enable: true
			}
		},
		check: {
			enable: type == 1 ? true : false
		},
		callback: {
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck,
			beforeCheck: zTreeBeforeCheck
			/* onClick: zTreeOnClick,
			onRightClick: OnRightClick,
			beforeDrop: zTreeBeforeDrop,
			onRename: zTreeOnRename */
		}
	};
	
	
	var fnUserInfo = function(id)
	{
		var url = "${WEB_HOME}/person100/personMemberInfoView.do?userId="+id;
		PortalCommon.userInfoPop(url);
	};
	
	var setUserListData = function(userList)
	{
		var addData = "";		
		for (var i=0; i < userList.length; i++)
		{
			var json = userList[i];
			
			if (type == 1)
			{
				addData += '<tr><td class="te_center">'+json.displayname+'</td><td class="deptTD" style="display:none" ><span class="te_dot te93">'+json.ou+'</span></td><td>'+json.titlename+'</td><td>'+json.telephonenumber+'</td></tr>';
			}
			else
			{
				addData += '<tr><td class="te_center"><input type="checkbox" id="chkAll" title="선택" value="'+json.resinumber+','+json.displayname+','+json.titlename+','+json.telephonenumber+','+json.ou+','+json.mobile+'"></td>';
				addData += '<td class="te_center"><a onclick="javascript:fnUserInfo(\''+json.resinumber+'\')" class="te_dot te93">'+json.displayname+'</a></td><td class="deptTD" style="display:none" ><span class="te_dot te93">'+json.ou+'</span></td><td><span class="te_dot te93">'+json.titlename+'</span></td><td>'+json.telephonenumber+'</td></tr>';
			}
		}
		
		$("#userList").html(addData);
		
		if (view_mode == "1")
		{
			$("#deptTH").hide();			
		}
		else
		{
			$("#deptTH").show();
			$(".deptTD").css("display","block");
		}
		
	};
	
	var getUserList = function(param){
		$("#chkAll").prop('checked', false );
		
		PortalCommon.getJson({
			url: "${WEB_HOME}/organization/getUserList.do?format=json",
			data: param,
			success :function(data){
				if(data.jsonResult.success ===true){
					setUserListData(data.userList);
				};
			}
		});
	};

	var parentCnt = 0;
	function findParentNode(pid)
	{
		var zNodes = ${organizationList};		
		for (var i=0; i < zNodes.length; i++)
	 	{
			var json = zNodes[i];
			if (json.id == pid)
			{
				parentCnt++;
				findParentNode(json.pId);
			}
	 	}
		return parentCnt;
	}
	
	function zTreeBeforeCheck(treeId, treeNode) {
		parentCnt = 0;
		
		if (treeNode.id == '6110002' || treeNode.id == '6110003')
		{
			alert('하위 부서를 선택하세요');
			return false;
		}
		else
		{
			if (!treeNode.checked)
			{
				var rtnCnt = findParentNode(treeNode.pId);
				if (rtnCnt < 2)
				{
					alert('하위 부서를 선택하세요');				
					return false;
				}			
			}
		}
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
		
		
		
		
		
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		    view_mode = "1";
		    $("#userName").val('');
		    getUserList({'oucode' : treeNode.oucode });
	};
	
	var getDepData = function() {
		var treeObj = $.fn.zTree.getZTreeObj("treeObj");
		var nodes = treeObj.getAllParentChecked();

		var i = 0;
		var length = nodes.length;
		
		//return;
		
		if(length===0){
			alert("부서를 선택해주세요.");
			return;
		}
		var jsonList = [];
		for (i = 0; i < length; i++) {
			var node = nodes[i];
			var jsonObject = {
					id : node.id,
					name : node.name
			};
			jsonList.push(jsonObject);
		}
				
		//PortalCommon.callOpenerFunction(callbackFunction, jsonStr);
		opener.callbackOpenDept(JSON.stringify(jsonList));
		window.close();
	}
	
	var getUserData = function () {
		var data = $("#userList").find('input[type=checkbox]:checked');		
		var length = data.length;		
		if(length===0){
			alert("사용자를 선택해주세요.");
			return;
		}
		
		var jsonList = [];
		for (var i = 0; i < data.length; i++)
		{
			var user = data[i];		
			var temp = $(user).val();
			var strArr = temp.split(",");
			var jsonObject = {
					id : strArr[0],
					name : strArr[1],
					dept : strArr[2],
					titlename : strArr[3],
					ou : strArr[4],
					mobile : strArr[5]
			};
			jsonList.push(jsonObject);
		}

		opener.callbackOpenPerson(JSON.stringify(jsonList));
		
		window.close();
	};
	
	/* 부서 검색 */	
	var getOrganizationList = function(){
		PortalCommon.getJson({
			url: "${WEB_HOME}/organization/getOrganizationList.do?format=json",
		    data: {'orgfullname' : $("#orgfullname").val()},
			success :function(data){
				if(data.jsonResult.success ===true){
					$.fn.zTree.init($("#treeObj"), setting, data.organizationList);
				}
			}
		});
	};
	
	var findUserList = function(){
		var name = $("#userName").val(); 
		if($.trim(name)==="" || PortalCommon.getByte(name)<4){
			alert("사용자 이름을 입력해주세요");
			return;
		}
		view_mode = "2";
		getUserList( {'name' : $("#userName").val()});
		
	};
	
$(document).ready(function(){
	
	$.fn.zTree.init($("#treeObj"), setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("treeObj");
	var zNodes = ${organizationList};
	var treeObj = $.fn.zTree.init($("#treeObj"), setting, zNodes);
	treeObj.expandAll(false);
	
	var node = zTree.getNodeByParam('id', oucode);
	if (node != null)
	{
		zTree.selectNode(node);
		$("#userName").val('');		
		getUserList({'oucode' : node.oucode });
	}
	
	
	$("#btnOK, #btnClose").click(function(){
		var btnId = $(this).attr("id");			
		switch(btnId){
			case "btnOK": //확인
				if(type==1){
					getDepData(); //선택된 부서 리스트 Return
				}else{
					getUserData();// 서택된 사용자 리스트 Return
				}
				
				break;
			case "btnClose": //닫기
				window.close();				
				break;
		};
	 });
	
	/* 부서명 입력 Enter 키 이벤트 */
	$('#orgfullname').bind('keypress', function(e) {
		var code = e.keyCode || e.which;
		if(code === 13){
			getOrganizationList(); /* 부서 검색 */	
			e.preventDefault();
			return false;
		};
	});
	
	/* 사용자명 입력 Enter 키 이벤트 */
	$('#userName').bind('keypress', function(e) {
		var code = e.keyCode || e.which;
		if(code === 13){
			findUserList(); /* 사용자 검색 */
			e.preventDefault();
			return false;
		};
	});
	
	
	$("#btnUserSearch, #btnOrgSearch").click(function(){
		var btnId = $(this).attr("id");
		switch(btnId){
			/* 사용자 검색 */
			case "btnUserSearch":
				findUserList();
				break;
			/* 부서 검색 */	
			case "btnOrgSearch":
				getOrganizationList();
				break;
		};
	});
	
	
	/* 사용자 전체 체크 EVENT */
	$("#chkAll").click(function(){
		 $("#userList").find('input[type=checkbox]').prop('checked', this.checked);
	});

});

</script>
<body>
<!--pop_wrap-->
<div class="pop_wrap" style="width:898px;"><!-- 팝업사이즈 width:800px; height:500px; -->

	<div class="pop_header">조직도</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="pop_post clearfix">
			<div class="p_left" style="width:40%">
				<!--search-->
				<div class="search_area2">
					<table summary="조회영역" class="tbl_fixed">
			            <caption>조회영역</caption>
						<colgroup>
							<col>
						</colgroup>
						<tbody>
							<tr>
								<td>
									부서명
									<input id="orgfullname" type="text" name="orgfullname" title="검색어입력"  style="ime-mode:active;">
<!-- 									<a id="btnOrgSearch" class="btn_search2" style="cursor: pointer;">검색</a> -->
									<input type="button"  id="btnOrgSearch" value="검색">
								</td>
							</tr>
						</tbody>
					</table>
					<span class="sch_tl"><!--top,left--></span>
					<span class="sch_tr"><!--top,right--></span>
					<span class="sch_br"><!--bottom,right--></span>
					<span class="sch_bl"><!--bottom,left--></span>
				</div>
				<!--//search-->
				<div class="tree">
					<ul id="treeObj" class="ztree"></ul>
				</div>
			</div>
			<div class="p_right" style="width:58%">
				<!--search-->
				<div class="search_area2">
					<table summary="조회영역">
			            <caption>조회영역</caption>
						<colgroup>
							<col>
						</colgroup>
						<tbody>
							<tr>
								<td>
									사용자명
									<form id ="frmUser"><input id="userName" name="name" type="text" title="검색어입력"  style="ime-mode:active;"></form>
<!-- 									<a id="btnUserSearch" class="btn_search2" style="cursor: pointer;">검색</a>  -->
									<input type="button"  id="btnUserSearch" value="검색">
								</td>
							</tr>
						</tbody>
					</table>
					<span class="sch_tl"><!--top,left--></span>
					<span class="sch_tr"><!--top,right--></span>
					<span class="sch_br"><!--bottom,right--></span>
					<span class="sch_bl"><!--bottom,left--></span>
				</div>
				
				<!--//search-->
				<!--list-->
				<div class="title_board tbl_list2 ma_none">
					<table summary="게시판 리스트">
			        	<caption>게시판 리스트</caption>
						<colgroup>
						<c:if test="${type == 2}"><col width="20"></c:if>
							<col width="80">
							<col width="100">
							<col width="170">
							<col>
						</colgroup>
			            <thead>
							<tr>
							<c:if test="${type == 2}"><th scope="col"><span><input id="chkAll" type="checkbox" title="선택"></span></th></c:if>
								<th scope="col">성명</th>
								<th id="deptTH" scope="col" style="display:none">부서</th>
								<th scope="col">직위</th>
								<th scope="col" class="last_bg">전화번호</th>								
							</tr>
						</thead>
					</table>
				</div>
				<div class="tbl_list2 grp tbl_scroll">
					<table summary="게시판 리스트">	
					<caption>게시판 리스트</caption>
						<colgroup>
     					<c:if test="${type == 2}"><col width="20"></c:if>
							<col width="80">
							<col width="100">
							<col width="170" style="*width:160px">
							<col>
						</colgroup>
						<tbody id="userList">

						</tbody>
					</table>
				</div>
				<!--//list-->
			</div>
		</div>
	</div>
	
	<!-- popup 본문 -->
	<div class="pop_footer">
		<!--btn_area-->         
	    <div class="btn_area">
	        <div class="fl_cen">
	            <a id="btnOK" class="btn_basic2 bw70" style="cursor: pointer;">
				    <span class="fo_bold btn_text" >확인</span>
				</a>
				<a id = "btnClose" class="btn_basic2 bw70" style="cursor: pointer;">
				    <span class="fo_bold btn_text" >닫기</span>
				</a>
	        </div>
	     </div>
	     <!--//btn_area-->  
	 </div>  
</div>	
<!--//pop_wrap-->


<script type="text/javascript">

</script>
</body>
</html>