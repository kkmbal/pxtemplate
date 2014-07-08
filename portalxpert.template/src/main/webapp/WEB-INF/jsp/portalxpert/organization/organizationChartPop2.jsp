<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%
	request.setAttribute("tree", "tree");
%>
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
  var organizationList = ${organizationList};



</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/organization/organizationChartPop2.js"></script>
<body>
<!--pop_wrap-->
<div class="pop_wrap" style="width:898px;"><!-- 팝업사이즈 width:800px; height:500px; -->

	<div class="pop_header">조직도</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="pop_post clearfix">
			<div class="p_left" style="width:40%">
				<!--search-->
				<div class="attachbox" style="width:270px;margin-bottom:10px;">
					<span class="rbox_top"></span>
					<div class="rboxInner">
						부서명
						<input id="orgfullname" type="text" name="orgfullname" class="text ml5mr10" style="width:100px" style="ime-mode:active;" /> 
						<a href="#" class="btn_set bt_style7" id="btnOrgSearch"><span>검색</span></a>
					</div>
				</div>				
				<!--search-->
				
				
				<div class="tree">
					<ul id="treeObj" class="ztree"></ul>
				</div>
			</div>
			<div class="p_right" style="width:58%">
				<!--search-->
				<div class="attachbox" style="width:470px;margin-bottom:10px;">
					<span class="rbox_top"></span>
					<div class="rboxInner">
						사용자명
						<input type="text" id="userName" name="name" class="text ml5mr10" style="width:100px" style="ime-mode:active;" /> 
						<a href="#" class="btn_set bt_style7" id="btnUserSearch"><span>검색</span></a>
					</div>
				</div>				
				<!--search-->				
				
				
				<!--list-->
				<div class="title_board tbl_list2 ma_none">
					<table summary="게시판 리스트" class="tbl_list">
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
							<c:if test="${type == 2}">
							    <th scope="col" class="f"><span><input id="chkAll" type="checkbox" title="선택"></span></th></c:if>
								<th scope="col"><span>성명</span></th>
								<th id="deptTH" scope="col" style="display:none"><span>부서</span></th>
								<th scope="col"><span>직위</span></th>
								<th scope="col" class="e"><span>전화번호</span></th>								
							</tr>
						</thead>
					</table>
				</div>
				<div class="tbl_list2 grp tbl_scroll">
					<table summary="게시판 리스트" class="tbl_list">	
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
	     
		<!-- 버튼영역 -->
		<div style="text-align:center;">
			<div class="rbox_btns">
				<a href="#" class="btn_set bt_style2" id="btnOK" ><span>확인</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose"><span>닫기</span></a>
			</div>
		</div>
		<!-- //버튼영역 -->	     
	     
	      
	 </div>  
</div>	
<!--//pop_wrap-->


</body>
</html>