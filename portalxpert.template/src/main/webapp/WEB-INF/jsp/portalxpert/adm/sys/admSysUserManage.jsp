<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

</head>

<script type="text/javascript" >

var userId = '${admSysPsnUserInfoVO.userId}';
var deptList = ${deptList};
var authCodeList = ${authCodeList};
var deptCode = '${admSysPsnUserInfoVO.deptCode}';
var authCd = '${admSysPsnUserInfoVO.authCd}';

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysUserManage.js"></script>
<body>

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">사용자</h1>
		</div>
		<div class="contents">
			<table class="tbl_form" summary="이 표는 사용자이름, 휴대폰, 이메일, 부서, 권한사항을 입력하는 사용자정보 표입니다.">
			<caption>사용자</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:35%" />
				<col style="width:15%" />
				<col style="width:35%" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">아이디</label></th>
				<td><input type="text" id="userId_v" name="userId_v" value="${admSysPsnUserInfoVO.userId}" class="text" style="width:222px" title="아이디를 입력합니다." /></td>
				<th scope="row"><label for="input01">이름</label></th>
				<td><input type="text" id="userName" name="userName" value="${admSysPsnUserInfoVO.userName}" class="text" style="width:222px" title="이름을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">비밀번호</label></th>
				<td><input type="password" id="userPassword" name="userPassword" value="${admSysPsnUserInfoVO.userPassword}" class="text" style="width:222px" title="비밀번호를 입력합니다." /></td>
				<th scope="row"><label for="input01">비밀번호확인</label></th>
				<td><input type="password" id="userPassword2" name="userPassword2" class="text" style="width:222px" title="비밀번호를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">휴대폰</label></th>
				<td><input type="text" id="mobile" name="mobile" value="${admSysPsnUserInfoVO.mobile}"  class="text" style="width:222px" title="휴대폰을 입력합니다." /></td>
				<th scope="row"><label for="input01">이메일</label></th>
				<td><input type="text" id="mail" name="mail" value="${admSysPsnUserInfoVO.mail}" class="text" style="width:222px" title="이메일을 입력합니다." /></td>
			</tr>
			
			<tr>
				<th scope="row"><label for="select02">부서</label></th>
				<td>
					<span class="selectN" style="width:150px">
						<span>
							<select title="" name="deptCode" id="deptCode">
								<option value="">선택</option>
							</select>
						</span>
					</span>
				</td>
				<th scope="row"><label for="select02">권한</label></th>
				<td>
					<span class="selectN" style="width:150px">
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

