<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

</head>

<script type="text/javascript" >

var authCd = '${admSysAuthVO.authCd}';

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysAuthManage.js"></script>
<body>
<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">권한</h1>
		</div>
		<div class="contents">
			<form:form commandName="admSysAuthVO" action="${WEB_HOME}/adm/sys/getAdmSysAuthManage.do" name="listForm" method="post">
			<input type="hidden" name="authCd" id="authCd" value="${admSysAuthVO.authCd}">		
			<table class="tbl_form" summary="이 표는 권한코드, 권한명, 권한패턴을 입력하는 권한정보 표입니다.">
			<caption>사용자</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:35%" />
				<col style="width:15%" />
				<col style="width:35%" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">권한코드</label></th>
				<td><input type="text" id="authCd_v" name="authCd_v" value="${admSysAuthVO.authCd}" class="text" style="width:222px" title="권한코드를 입력합니다." /></td>
				<th scope="row"><label for="input01">권한명</label></th>
				<td><input type="text" id="authNm" name="authNm" value="${admSysAuthVO.authNm}" class="text" style="width:222px" title="권한명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">권한설명</label></th>
				<td colspan="3"><input type="text" id="authDesc" name="authDesc" value="${admSysAuthVO.authDesc}" class="text" style="width:222px" title="권한설명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">URL 패턴</label></th>
				<td colspan="3"><textarea id="urlPtn" name="urlPtn" cols="32" rows="5" maxlength="100"  title="URL" style="width:659px;">${admSysAuthVO.urlPtn}</textarea></td>
			</tr>
			</tbody>
			</table>
			</form:form>
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnSave">저장</button>
				<button class="btn_style4_2" type="button" id="btnClose">취소</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>

</body>
</html>

<%--

<!--pop_wrap-->
<div class="pop_wrap" style="width:898px;">

	<div class="pop_header">권한</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="pop_post clearfix">
			<form:form commandName="admSysAuthVO" action="${WEB_HOME}/adm/sys/getAdmSysAuthManage.do" name="listForm" method="post">
			<input type="hidden" name="authCd" id="authCd" value="${admSysAuthVO.authCd}">
			<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
			<caption>제목</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:35%" />
				<col style="width:15%" />
				<col style="width:35%" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">권한코드</label></th>
				<td><input type="text" class="text" style="width:222px" title="권한코드" id="authCd_v" name="authCd_v" value="${admSysAuthVO.authCd}" /></td>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input03">권한명</label></th>
				<td><input type="text"  class="text" style="width:222px" title="권한명" id="authNm" name="authNm" value="${admSysAuthVO.authNm}" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">권한설명</label></th>
				<td colspan="3"><input type="text"  class="text" style="width:657px" title="권한설명" id="authDesc" name="authDesc" value="${admSysAuthVO.authDesc}" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">URL 패턴</label></th>
				<td  colspan="3"><textarea id="urlPtn" name="urlPtn" cols="32" rows="5" maxlength="100"  title="URL" style="width:659px;">${admSysAuthVO.urlPtn}</textarea></td>
			</tr>			
			</tbody>
			</table>
			</form:form>
			
		</div>
	</div>
	
	<!-- popup 본문 -->
	<div class="pop_footer">
	     
		<!-- 버튼영역 -->
		<div style="text-align:center;">
			<div class="rbox_btns">
				<a href="#" class="btn_set bt_style3" id="btnSave" ><span>저장</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose"><span>닫기</span></a>
			</div>
		</div>
		<!-- //버튼영역 -->	     
	     
	      
	 </div>  
</div>	
<!--//pop_wrap-->

--%>