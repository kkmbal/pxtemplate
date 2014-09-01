<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%
	request.setAttribute("NO_JQUERY_UI", "Y");
%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<style type="text/css">
.ztree li span.button.ico_close{margin-right:2px; background: url(${RES_HOME}/images/zTree/house_default_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.ico_open{margin-right:2px; background: url(${RES_HOME}/images/zTree/selected_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.ztree li span.button.ico_docu{margin-right:2px; background: url(${RES_HOME}/images/zTree/house_default_folder.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
.search_area2 {padding-right:0;}	
</style>
    
<script type="text/javascript" >

  var view_mode = "1";
  var type = '${type}';
  var callbackFunction ="${callback}";
  var oucode = '${oucode}';
  var organizationList = ${organizationList};



</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/organization/organizationChartPop2.js"></script>
</head>

<body>

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">조직도</h1>
		</div>
		<div class="contents">
			<div class="fl" style="margin-bottom:10px;">
				<div>
					<span class="rbox_top"></span>
					<div class="rboxInner">
						부서명 <input type="text" value="" id="orgfullname" name="orgfullname" class="text ml5mr10" style="width:100px;ime-mode:active;" title="검색어를 입력합니다." /> 
						<button class="btn_style7_2" type="button" id="btnOrgSearch">검색</button>
					</div>
				</div>		
				<div class="tree" style="margin-top:10px;width:370px;height:330px !important ; border:1px solid #ddd">
					<ul id="treeObj" class="ztree"></ul>
				</div>			
			</div>
			<div class="fr">
				<div>
					<span class="rbox_top"></span>
					<div class="rboxInner">
						사용자명 <input type="text" value="" id="userName" name="name" class="text ml5mr10" style="width:100px;ime-mode:active;" title="검색어를 입력합니다." /> 
						<button class="btn_style7_2" type="button" id="btnUserSearch">검색</button>
					</div>
				</div>			
				<table class="tbl_form" style="width:400px !important;margin-top:10px;" summary="이 표는 조직도 표입니다.">
				<caption>조직도</caption>
				<colgroup>
				<c:if test="${type == 2}"><col width="20"></c:if>
					<col width="80">
					<col width="100">
					<col width="*">
					<col width="*">
					<col>
				</colgroup>
	            <thead>
					<tr>
					<c:if test="${type == 2}">
					    <th scope="col" class="f"><span><input id="chkAll" type="checkbox" title="선택"></span></th></c:if>
						<th scope="col"><span>성명</span></th>
						<th scope="col"><span>부서</span></th>
						<th scope="col"><span>직위</span></th>
						<th scope="col" class="e"><span>전화번호</span></th>								
					</tr>
				</thead>
				<tbody id="userList">

				</tbody>
				</table>
			</div>
			
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnOK">확인</button>
				<button class="btn_style4_2" type="button" id="btnClose">취소</button>
			</div>
		</div>

		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>

</body>
</html>

