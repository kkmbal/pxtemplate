<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

</head>

<script type="text/javascript" >

var userId = '${admSysPsnUserInfoVO.userId}';
var deptList = ${deptList};
var authCodeList = ${admGenCommonCodeSpecList};
var deptCd = '${admSysPsnUserInfoVO.deptCode}';
var authCd = '${admSysPsnUserInfoVO.authCd}';

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysUserManage.js"></script>
<body>
<!--pop_wrap-->
<div class="pop_wrap" style="width:898px;">

	<div class="pop_header">사용자</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="pop_post clearfix">
			<form:form commandName="admSysPsnUserInfoVO" action="${WEB_HOME}/adm/sys/getAdmSysUserManage.do" name="listForm" method="post">
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
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">아이디</label></th>
				<td><input type="text" class="text" style="width:222px" title="아이디" id="userId" name="userId" value="${admSysPsnUserInfoVO.userId}" /></td>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input03">이름</label></th>
				<td><input type="text"  class="text" style="width:222px" title="이름" id="userNm" name="userName" value="${admSysPsnUserInfoVO.userName}" /></td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력"  /> <label for="input02">비밀번호</label></th>
				<td><input type="password"  class="text" style="width:222px" title="비밀번호" id="userPassword" name="userPassword" value="${admSysPsnUserInfoVO.userPassword}" /></td>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input03">비밀번호확인</label></th>
				<td><input type="password"  class="text" style="width:222px" title="비밀번호확인" /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">휴대폰</label></th>
				<td><input type="text"  class="text" style="width:222px" title="휴대폰" id="mobile" name="mobile" value="${admSysPsnUserInfoVO.mobile}"  /></td>
				<th scope="row"><label for="input03">이메일</label></th>
				<td><input type="text"  class="text" style="width:222px" title="이메일" id="mail" name="mail" value="${admSysPsnUserInfoVO.mail}" /></td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">부서</label></th>
				<td>
					<span class="selectN" style="width:222px">
						<span>
							<select title="" name="deptCd" id="deptCd">
								<option value="">선택</option>
							</select>
						</span>
					</span>
				</td>
				<th scope="row"><label for="input03">권한</label></th>
				<td colspan="3">
					<span class="selectN" style="width:222px">
						<span>
							<select title="" name="authCd" id="authCd">
								<option value="">선택</option>
							</select>
						</span>
					</span>
				</td>
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

</body>
</html>