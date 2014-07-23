<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

</head>

<script type="text/javascript" >

var popId = '${admPop.popId}';
var parRowPosList = ${parRowPosList};
var rowPosList = ${rowPosList};
var parRowPos = '${admPop.parRowPos}';
var rowPos = '${admPop.rowPos}';
var userMapList = '${userMapList}';
var jsonAppendFileList = [];  //첨부 리스트
var userMapList = ${userMapList};

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admPopManage.js"></script>
<script type="text/javascript" src="${RES_HOME}/js/portal/editor.js"></script>

<body>
<div class="container">	
<div class="header">
	<h1>팝업관리</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>팝업관리</strong></span>
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
			<form:form commandName="admPopVO" action="${WEB_HOME}/adm/pop/getAdmPopView.do" name="listForm" method="post">
			<input type="hidden" name="popId" id="popId" value="${admPop.popId}">
			<input type="hidden" name="expoBgnDttm" id="expoBgnDttm">
			<input type="hidden" name="expoEndDttm" id="expoEndDttm">		
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input01">팝업이름</label></th>
				<td colspan="3"><input type="text"  class="text" style="width:500px" title="팝업이름" id="popTitle" name="popTitle" value="${admPop.popTitle}" /></td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input01">게시기간</label></th>
				<td colspan="3">
					<div class="sec_calender">
						<input type="text" class="text" id="expoBgnDttm_v" name="expoBgnDttm_v" title="시작날짜를 입력합니다. 예)YYYY.MM.DD" value="${admPop.expoBgnDttm}">
					</div> ~ 
					<div class="sec_calender">
						<input type="text" class="text" id="expoEndDttm_v" name="expoEndDttm_v" title="시작날짜를 입력합니다. 예)YYYY.MM.DD" value="${admPop.expoEndDttm}">
					</div>			
				</td> 
			</tr>
			<tr>
				<th scope="row"><label for="input01">팝업위치</label></th>
				<td colspan="3">
					<span class="selectN" style="width:222px">
						<span>
							<select title="" name="parRowPos" id="parRowPos">
								<option value="">선택</option>
							</select>
						</span>
					</span>
					<span class="selectN" style="width:222px">
						<span>
							<select title="" name="rowPos" id="rowPos">
								<option value="">선택</option>
							</select>
						</span>
					</span>					
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">공개대상</label></th>
				<td colspan="3">
					<select name="notiOpenDiv" id="notiOpenDiv">
						<option value="010" selected>전체공개
						<option value="020">사용자지정
						<option value="030">부서지정
					</select>
			 
					<div class="mv_file_div" style="width:510px;">
						<ul id="OpenDeptCategories">
						</ul>
						<ul id="OpenEmpCategories">
						</ul>
					</div>								
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">노출기능</label></th>
				<td colspan="3">
					<div class="checkgroup">
						<input type="checkbox" id="expoYn" name="expoYn" title="체크1을 선택합니다." <c:if test="${admPop.expoYn=='Y'}">checked</c:if> />
						<label for="check01">[오늘은 그만보기] 사용</label>
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">링크URL</label></th>
				<td colspan="3">
					<input type="text"  class="text" style="width:500px" title="팝업이름" id="linkUrl" name="linkUrl" value="${admPop.linkUrl}" />		
				</td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input01">링크방식</label></th>
				<td colspan="3">
					<div class="radiogroup">
						<input type="radio" id="linkKind_Y" value="001" name="linkKind" <c:if test="${admPop.linkKind=='001'}">checked</c:if> title="라디오1을 선택합니다." />
						<label for="radio01">새창에서 제공</label>
						<input type="radio" id="linkKind_N" value="002" name="linkKind" <c:if test="${admPop.linkKind=='002'}">checked</c:if> title="라디오2을 선택합니다." />
						<label for="radio02" class="mgrn">현재화면에서 제공</label>	
					</div>							
				</td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input01">팝업종류</label></th>
				<td colspan="3">
					<div class="radiogroup">
						<input type="radio" id="popKind_Y" value="001" name="popKind" <c:if test="${admPop.popKind=='001'}">checked</c:if> title="라디오1을 선택합니다." />
						<label for="radio01">CMS에서 생성</label>
						<input type="radio" id="popKind_N" value="002" name="popKind" <c:if test="${admPop.popKind=='002'}">checked</c:if> title="라디오2을 선택합니다." />
						<label for="radio02" class="mgrn">팝업이미지 등록</label>	
					</div>							
				</td>
			</tr>
			<tr id="contsDiv" style="display:none;">
				<td colspan="4"> 
					<textarea class="editor ma_none" id="editor" name="popConts" style="height:360px;">${admPop.popConts}</textarea>
				</td>
			</tr>			
			</form:form>
			<tr id="apndFileDiv" style="display:none;">
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">팝업이미지</label></th>
				<td  colspan="3">
			    <form id="apndFileform" name="apndFileform" action="<%=request.getContextPath()%>/adm/contents/bbsFileUpload.do" enctype="multipart/form-data" method="post">
			    <input type="text" class="text" id="fileName" readonly style="width:400px">
			    <a href="#" class="btn_set bt_style1 mv_file_a">   
				<input type="file" size="1" name="upFile" class="mv_file" onchange="javascript: document.getElementById('fileName').value = this.value">
				<span>파일</span></a>
				</form>	
				<div id="apndImg" style="width:457px;height:30px;margin-top:10px;border: 1px solid #c2c2c2;padding-left:5px;">
					<a href="#" onclick="fnImgPreview()">${appendImg.apndFileOrgn}</a>
				</div>
				<div id="apndImg-dialog" style="display:none;">
					<c:if test="${not empty appendImg.apndFilePath}">
					<img src="${WEB_HOME}/upload/${appendImg.apndFilePath}/${appendImg.apndFileName}">
					</c:if>
				</div>
				</td>
			</tr>
		</tbody>
	</table>

<!-- 버튼영역 -->
<div class="btn_board_sec">
	<div class="fl">
		<a href="#" class="btn_set bt_style2" id="btnCancel"><span>목록</span></a>
	</div>
	<div class="fr">
<!-- 		<a href="#" class="btn_set bt_style2" id="btn_bbs_preview"><span>미리보기</span></a> -->
		<a href="#" class="btn_set bt_style3" id="btnSave"><span>등록</span></a>
	</div>
</div>
<!-- //버튼영역 -->

</div>
</body>
</html>
