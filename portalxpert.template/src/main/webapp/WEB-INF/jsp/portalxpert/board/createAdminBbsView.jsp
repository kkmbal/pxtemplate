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

<div class="container">	
<div class="header">
	<h1>게시판 생성</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>게시판 생성</strong></span>
	</div>
</div>

<!-- 입력테이블1 -->
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
	<th scope="row"><label for="input01">기관/관리자</label></th>
	<td colspan="3">기관 <input type="text" id="id_requDeptName" class="text" style="width:220px" disabled="disabled" title="제목을 입력합니다." value="${deptNm}" /> 관리자 <input type="text" id="id_requUserName" class="text" style="width:220px" disabled="disabled" title="제목을 입력합니다." value="${userNm}" /></td>
</tr>
<tr>
	<th scope="row"><label for="input14">게시판명</label></th>
	<td colspan="3">
		<input type="text" class="text" id="id_boardName" value=""  style="width:220px" title="게시판명을 입력합니다." />
		<span class="txt_size">
			<span class="txt_result x" id="boardNameInfo"></span>
		</span>		
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">게시판 종류</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" id="boardKind_010" value="010" name="boardKind" checked title="라디오1을 선택합니다." />
			<label for="radio01">일반</label>
			<input type="radio" id="boardForm_030_010" value="030_010" name="boardKind" title="라디오2을 선택합니다." />
			<label for="radio02">이미지</label>
			<input type="radio" id="boardForm_030_020" value="030_020" name="boardKind" title="라디오2을 선택합니다." />
			<label for="radio02">동영상</label>
			<input type="radio" id="boardKind_110" value="110" name="boardKind" title="라디오1을 선택합니다." />
			<label for="radio01">설문조사</label>			
			<input type="radio" id="boardKind_120" value="120" name="boardKind" title="라디오1을 선택합니다." />
			<label for="radio01">CMS</label>			
		</div>
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">게시판 형태</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" id="boardForm_010" value="010" name="boardForm" checked title="라디오1을 선택합니다." />
			<label for="radio01">리스트형</label>
			<input type="radio" id="boardForm_020" value="020" name="boardForm" title="라디오1을 선택합니다." />
			<label for="radio01">SNS형</label>
			<input type="radio" id="boardForm_030" value="030" name="boardForm" title="라디오2을 선택합니다." style="display:none;"/>
			<label for="radio02" class="mgrn"  style="display:none;">콘텐츠형
		    <select id="boardFormSub" title="컨텐츠형 종류선택">
		    	<option value="010">이미지형</option>
				<option value="020">동영상형</option>
		    </select>			
			</label>	
			<input type="radio" id="boardForm_040" value="040" name="boardForm" title="라디오2을 선택합니다." />
			<label for="radio02" class="mgrn">달력형</label>	
		</div>
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">운영상태</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" id="boardOperYn_Y" value="Y" name="boardOperYn" checked title="라디오1을 선택합니다." />
			<label for="radio01">운영</label>
			<input type="radio" id="boardOperYn_N" value="N" name="boardOperYn" title="라디오2을 선택합니다." />
			<label for="radio02" class="mgrn">폐쇄</label>	
		</div>	
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">운영기간</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" name="boardOperDiv" id="boardOperDiv_010"  value="010" checked title="라디오1을 선택합니다." />
			<label for="radio01">영구</label>
			<input type="radio" name="boardOperDiv" id="boardOperDiv_020"  value="020" title="라디오2을 선택합니다." />
			<label for="radio02" class="mgrn">기간지정</label>	
		</div>	
		<div class="sec_calender">
			<input type="text" class="text" id="boardOperBgnDttm" name="boardOperBgnDttm" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
		</div> ~ 
		<div class="sec_calender">
			<input type="text" class="text" id="boardOperEndDttm" name="boardOperEndDttm" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
		</div>
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">의견 허용</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" name="opnWrteDiv" id="opnWrteDiv_010" value="010" checked title="라디오1을 선택합니다." />
			<label for="radio01">사용</label>
			<input type="radio" name="opnWrteDiv" id="opnWrteDiv_020" value="020" title="라디오2을 선택합니다." />
			<label for="radio02" class="mgrn">사용안함</label>	
		</div>	
	</td>
</tr>
<tr>
	<th scope="row"><label for="input01">답글 허용</label></th>
	<td colspan="3">
		<div class="radiogroup">
			<input type="radio" name="replyWrteDiv" id="replyWrteDiv_010" value="010" checked title="라디오1을 선택합니다." />
			<label for="radio01">사용</label>
			<input type="radio" name="replyWrteDiv" id="replyWrteDiv_020" value="020" title="라디오2을 선택합니다." />
			<label for="radio02" class="mgrn">사용안함</label>	
		</div>	
	</td>
</tr>
<tr>
	<th scope="row"><label for="textbox01">게시판설명</label></th>
	<td colspan="3">
		<textarea name="boardExpl"  id="boardExpl" cols="100" rows="5" maxlength="300" class="textbox" title="설명을 300자 이하로 작성하세요"></textarea>
	</td>
</tr>
</tbody>
</table>

<!-- 버튼영역 -->
<div class="btn_board_sec">
	<div class="fl">
		<a href="#" class="btn_set bt_style2" id="btn_bbs_cancel2"><span>취소</span></a>
	</div>
	<div class="fr">
		<a href="#" class="btn_set bt_style3" id="btn_bbs_create2"><span>완료</span></a>
	</div>
</div>
<!-- //버튼영역 -->

</div>

<%-- default config --%>	
<div style="display:none;">
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
	<option value="0">영구</option>
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



<%--

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

 --%>	 