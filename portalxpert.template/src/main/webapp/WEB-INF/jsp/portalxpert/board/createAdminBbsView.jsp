<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
		<div class="h1">게시판생성</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">게시판관리</a>
			<strong class="str">게시판생성</strong>
		</div>
	</div>
	<table class="tbl_form" summary="이 표는 기관/관리자, 게시판명, 게시판종류, 운영상태, 운영기간, 의견/답글 허용, 게시판 설명으로 구성된 게시판현황 표입니다.">
	<caption>게시판 현황</caption>
	<colgroup>
		<col style="width:15%" />
		<col style="width:auto" />
	</colgroup>
	<tbody>
	<tr>
		<th scope="row">기관/관리자</th>
		<td>
			<strong><label for="input04">기관</label></strong>
			<input type="text" class="text disabled" id="id_requDeptName" value="${deptNm}"  style="width:180px" title="기관명을 입력합니다." disabled="disabled"/>
			&nbsp;&nbsp;&nbsp;
			<strong><label for="input05">관리자</label></strong>
			<input type="text" class="text disabled" id="id_requUserName" value="${userNm}"  style="width:80px" title="관리자명을 입력합니다." disabled="disabled" />
		</td>
	</tr>
	<tr>
		<th scope="row">게시판명</th>
		<td>
			<input type="text" class="text" id="id_boardName" value=""  style="width:210px" title="게시판명을 입력합니다." />
			<span class="txt_size">
				<span class="txt_result_x" id="boardNameInfo"></span>
			</span>		
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="radio05">게시판 종류</label></th>
		<td>
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
		<th scope="row"><label for="radio05">게시판 형태</label></th>
		<td>
			<div class="radiogroup">
				<input type="radio" id="boardForm_010" value="010" name="boardForm" checked title="일반을 선택합니다." />
				<label for="radio01">리스트형</label>
				<input type="radio" id="boardForm_020" value="020" name="boardForm" title="리스트형을 선택합니다." />
				<label for="radio01">SNS형</label>
				<input type="radio" id="boardForm_030" value="030" name="boardForm" title="SNS형을 선택합니다." style="display:none;"/>
				<label for="radio02" class="mgrn"  style="display:none;">콘텐츠형
			    <select id="boardFormSub" title="컨텐츠형 종류선택">
			    	<option value="010">이미지형</option>
					<option value="020">동영상형</option>
			    </select>			
				</label>	
				<input type="radio" id="boardForm_040" value="040" name="boardForm" title="달력형을 선택합니다." />
				<label for="radio02" class="mgrn">달력형</label>	
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="radio01">운영 상태</label></th>
		<td>
			<div class="radiogroup">
				<input type="radio" id="boardOperYn_Y" value="Y" name="boardOperYn" checked title="운영을 선택합니다." />
				<label for="radio01">운영</label>
				<input type="radio" id="boardOperYn_N" value="N" name="boardOperYn" title="폐쇄를 선택합니다." />
				<label for="radio02" class="mgrn">폐쇄</label>	
			</div>
		</td>
	</tr>
	<tr style="display:none;">
		<th scope="row"><label for="radio03">운영 기간</label></th>
		<td>
			<div class="radiogroup mt5 fl">
				<input type="radio" name="boardOperDiv" id="boardOperDiv_010"  value="010" checked title="라디오1을 선택합니다." />
				<label for="radio01">영구</label>
				<input type="radio" name="boardOperDiv" id="boardOperDiv_020"  value="020" title="라디오2을 선택합니다." />
				<label for="radio02" class="mgrn">기간지정</label>	
			</div>
			&nbsp;
			<div class="sec_calender">
				<input type="text" class="text" id="boardOperBgnDttm" name="boardOperBgnDttm" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
			</div> ~ 
			<div class="sec_calender">
				<input type="text" class="text" id="boardOperEndDttm" name="boardOperEndDttm" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">의견 허용</th>
		<td>
			<div class="radiogroup">
				<input type="radio" name="opnWrteDiv" id="opnWrteDiv_010" value="010" checked title="허용을 선택합니다." />
				<label for="radio01">허용</label>
				<input type="radio" name="opnWrteDiv" id="opnWrteDiv_020" value="020" title="허용안함을 선택합니다." />
				<label for="radio02" class="mgrn">허용안함</label>	
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">답글 허용</th>
		<td>
			<div class="radiogroup">
				<input type="radio" name="replyWrteDiv" id="replyWrteDiv_010" value="010" checked title="허용을 선택합니다." />
				<label for="radio01">허용</label>
				<input type="radio" name="replyWrteDiv" id="replyWrteDiv_020" value="020" title="허용안함을 선택합니다." />
				<label for="radio02" class="mgrn">허용안함</label>		
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row">외부공개</th>
		<td>
		    <select id="oupOpenUseTypeSelect" title="외부공개">
		    	<option value="N" selected>허용안함</option>
				<option value="Y">허용</option>
		    </select>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="input13">게시판 설명</label></th>
		<td><textarea name="boardExpl"  id="boardExpl" cols="100" rows="5" maxlength="300" class="textbox" title="게시판 설명을 입력합니다." onclick="this.value=''">300자 이하로 작성하세요.</textarea></td>
	</tr>
	<tr  id="boardUrlDiv" style="display:none;">
		<th scope="row">호출 URL</th>
		<td>
		내부 <input type="text" class="text" id="boardUrl" name="boardUrl" title="제공  URL" readonly style="width:468px"><br>
		<span id="openBoardUrlTitle" style="display:none;">공개</span> <input type="text" class="text" id="openBoardUrl" name="openBoardUrl" title="제공  URL" readonly style="width:468px;margin-top:5px;display:none;">
		</td>
	</tr>	
	</tbody>
	</table>
	
	<c:if test="${not empty bbsStat}">
	<table width="100%" class="data_form mt30" summary="이 표는 게시물수, 방문자수, 생성일, 폐쇄일로 구성된 게시판현황입니다.">
	<colgroup>
		<col width="25%" />
		<col width="25%" />
		<col width="25%" />
		<col width="25%" />
	</colgroup>
	<tbody>
	<tr>
		<td class="th">게시물수</td>
		<td class="th">방문자수</td>
		<td class="th">생성일</td>
		<td class="th">폐쇄일</td>
	</tr>
	<tr>
		<td>${bbsStat.notiCnt}</td>
		<td>${bbsStat.notiReadCnt}</td>
		<td>${bbsStat.regDttm}</td>
		<td>${bbsStat.boardOperEndDttm}</td>
	</tr>
	</tbody>
	</table>
	</c:if>	
	
	<div class="btn_board_sec">
		<div class="fl">
			<button type="button" class="btn_style2_2" id="btn_bbs_cancel2">취소</button>
		</div>
		<div class="fr">
			<button type="button" class="btn_style3_2" id="btn_bbs_create2">등록</button>
			<button type="button" class="btn_style4_2" id="btn_bbs_cancel">목록</button>
		</div>
	</div>
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
</div>	
<%-- default config --%>

</body>
</html>		



