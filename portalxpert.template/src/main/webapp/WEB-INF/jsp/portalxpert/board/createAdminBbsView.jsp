<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<title></title>
<script type="text/javascript">
	var callbackKind = "O";  //O or W
	
	var boardId ="${boardId}";	
	var boardTitleYn = 'Y';
	var jsonBbsList = '${bbsList}';
	var userMapList = '${userMapList}';
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/createAdminBbsView.js"></script>
</head>

<body>

	<div>게시판생성</div>

<table>
<tr>
	<td>기관/관리자</td>
	<td>기관:<input type="text" id="id_requDeptName"> 관리자:<input type="text" id="id_requUserName"></td>
</tr>
<tr>
	<td>게시판명</td>
	<td><input type="text" id="id_boardName"><span id="boardNameInfo">사용 가능한 게시판명입니다.</span></td>
</tr>
<tr>
	<td>게시판종류</td>
	<td>
		<input type="radio" id="boardForm_010" value="010" name="boardForm" checked>일반 
	    <input type="radio" id="boardForm_030" value="030" name="boardForm">이미지/동영상 
	    <select id="boardFormSub" title="컨텐츠형 종류선택">
	    	<option value="010">이미지형</option>
			<option value="020">동영상형</option>
	    </select>
	    <input type="radio" id="boardKind_110" value="110" name="boardForm">설문조사
	    <input type="radio" id="boardKind_120" value="120" name="boardForm">제도안내
	    <input type="radio" id="boardKind_130" value="130" name="boardForm">QnA
	    <input type="radio" id="boardForm_040" value="040" name="boardForm">교육안내
	</td>
</tr>
<tr>
	<td>운영상태</td>
	<td><input type="radio" id="boardOperYn_Y" value="Y" name="boardOperYn" checked>운영 <input type="radio" id="boardOperYn_N" value="N" name="boardOperYn">폐쇄</td>
</tr>
<tr>
	<td>운영기간</td>
	<td>
		<input type="radio" name="boardOperDiv" id="boardOperDiv_010"  value="010" checked>영구 
		<input type="radio" name="boardOperDiv" id="boardOperDiv_020"  value="020">기간지정
		<input id="boardOperBgnDttm" name="boardOperBgnDttm" type="text" title="시작일" class="inp_date" style="margin-left:-20px;" size="10" maxlength="10">~ 
		<input id="boardOperEndDttm" name="boardOperEndDttm" type="text" title="종료일" class="inp_date" size="10" maxlength="10">
	</td>
</tr>
<tr>
	<td>의견허용</td>
	<td>
		<input name="opnWrteDiv" id="opnWrteDiv_010" value="010" type="radio" checked>사용
		<input name="opnWrteDiv" id="opnWrteDiv_020" value="020" type="radio" >사용안함
	</td>
</tr>
<tr>
	<td>답글허용</td>
	<td>
		<input name="replyWrteDiv" id="replyWrteDiv_010" value="010" type="radio"   checked>사용
		<input name="replyWrteDiv" id="replyWrteDiv_020" value="020" type="radio" >사용안함
	</td>
</tr>
<tr>
	<td>게시판설명</td>
	<td><textarea name="boardExpl"  id="boardExpl" cols="50" rows="3" style="width:97%"></textarea></td>
</tr>
</table> 

<input type="button" value="취소" id="btn_bbs_cancel2"><input type="button" value="완료" id="btn_bbs_create2">




<%-- default config --%>	
<div style="display:none;">
<input type="radio" value="010" name="boardKind" checked> <%-- 게시판종류 --%>
<input type="radio" id="moblLinkYn_N" name="moblLinkYn" value="N" checked> <%-- 모바일연동 --%>
<%-- 관리자 --%>
<ul id="managerCategories">
<li id="${userId}">${userNm}</li>
</ul>
<input name="adminAlertYn" id="adminAlertYn_N" value="N" type="radio" checked> <%-- 새로운게시물알림 --%>
<%-- 공개 선택 --%>
<input id ="boardOpenDiv_ALL" value="010" name="boardOpenDiv" type="radio" checked>
<select id="boardOpenDivSub">
	<option value="010">전체 공개</option>
</select>
<%-- 게시판기간 --%>
<select id="notiTermDivSelect">
	<option value="12">1년</option>
</select>
<%-- 게시자지정 --%>
<input name="publDiv" value="010" id ="publDiv_ALL" type="radio"checked>
<select title="전체공개 선택" id="publDivSub">
	<option value="010">전체 공개</option>
</select>
<input type="radio" value="Y" name="boardNotice" id="boardNotice_Y" checked> <%-- 공지사용 --%>
<input name="boardExplUseYn" id="boardExplUseYn_Y" value="Y" type="radio"  checked> <%-- 게시판설명사용 --%>
<input type="radio" id="replyEmailSendYN_N" name="replyEmailSendYN" value="N" checked> <%-- 의견메일받기 --%>
<input  name="makrDispDiv" id="makrDispDiv_EMP" value="010" type="radio" checked> <%-- 작성자이름표기 --%>
<input name="notiReadmanAsgnYn" id="notiReadmanAsgnYn_Y" value="Y" type="radio"  checked> <%-- 게시물조회자지정 --%>
<input name="agrmOppUseYn" id="agrmOppUseYn_N" value="N" type="radio" checked> <%-- 찬반사용 --%>
<input name="replyFileUploadYn" id="replyFileUploadYn_Y" value="Y" type="radio"  checked> <%-- 의견등록시파일업로드 --%>
<select name="fileUploadDiv" id="fileUploadDiv" title="파일개수선택">
<option value="999">제한없음</option>
</select>
<input name="opnReplyUseDiv" id="opnReplyUseDiv_010" value="010" type="radio" checked> <%-- 의견에대한답글허용 --%>
<input name="opnRealnameDiv" id="opnRealnameDiv_010" value="010" type="radio" checked> <%-- 의견실명사용 --%>
<input name="likeUseYn" id="likeUseYn_N" value="N" type="radio" checked> <%-- 좋아요 사용안함 --%>
<input name="nickUseYn" id="nickUseYn_N" value="N" type="radio" checked> <%-- 닉네임 사용안함 --%>
<input name="smsUseYn" id="smsUseYn_N" value="N" type="radio" checked > <%-- sms 사용안함 --%>
<select name="apndFileSzDiv" id="apndFileSzDiv" title="파일용량선택"">
	<option value="10">10MB</option>
</select>
<input type="radio" id="NotiMailSend_N" value="N" name="NotiMailSend" checked> <%-- 게시물 메일발송 사용안함 --%>
<select title="외부공개" id="oupOpenUseTypeSelect">
	<option value="N" selected>비허용</option>
</select>
</div>	


</body>
</html>			 