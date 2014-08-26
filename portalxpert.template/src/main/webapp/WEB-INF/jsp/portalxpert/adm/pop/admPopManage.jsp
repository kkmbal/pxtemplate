<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
		<div class="h1">팝업등록</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">팝업관리</strong>
		</div>
	</div>
	<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
	<caption>제목</caption>
	<colgroup>
		<col style="width:15%" />
		<col style="width:auto" />
	</colgroup>
	<tbody>
	
	<form:form commandName="admPopVO" action="${WEB_HOME}/adm/pop/getAdmPopView.do" name="listForm" method="post">
	<input type="hidden" name="popId" id="popId" value="${admPop.popId}">
	<input type="hidden" name="expoBgnDttm" id="expoBgnDttm">
	<input type="hidden" name="expoEndDttm" id="expoEndDttm">	
	<tr>
		<th scope="row"><label for="input01">팝업 이름</label></th>
		<td><input type="text" id="popTitle" name="popTitle" value="${admPop.popTitle}" class="text" style="width:563px" title="팝업이름을 입력합니다." /></td>
	</tr>
	<tr>
		<th scope="row"><label for="date03">게시 기간</label></th>
		<td>
			<div class="sec_calender">
				<input type="text" class="text" id="expoBgnDttm_v" name="expoBgnDttm_v" value="${admPop.expoBgnDttm}"  title="시작날짜를 입력합니다. 예)YYYY.MM.DD" />
			</div>
			~
			<div class="sec_calender">
				<input type="text" class="text" id="expoEndDttm_v" name="expoEndDttm_v" value="${admPop.expoEndDttm}"  title="종료날짜를 입력합니다. 예)YYYY.MM.DD" />
			</div>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="select02">팝업 위치</label></th>
		<td>
			<span class="selectN" style="width:150px">
				<span>
					<select title="" name="parRowPos" id="parRowPos">
						<option value="">선택</option>
					</select>
				</span>
			</span>
			<span class="selectN" style="width:150px">
				<span>
					<select title="" name="rowPos" id="rowPos">
						<option value="">선택</option>
					</select>
				</span>
			</span>
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="input04">공개 대상</label></th>
		<td>
			<select name="notiOpenDiv" id="notiOpenDiv">
				<option value="010" selected>전체공개
				<option value="020">사용자지정
				<option value="030">부서지정
			</select>
			<div class="listbox">
				<ul id="OpenDeptCategories">
				</ul>
				<ul id="OpenEmpCategories">
				</ul>
			</div>			
		</td>
	</tr>
	<tr>
		<th scope="row"><label for="check01">노출 기능</label></th>
		<td>
			<div class="checkgroup">
				<input type="checkbox" id="expoYn" name="expoYn"  <c:if test="${admPop.expoYn=='Y'}">checked</c:if> title="[오늘은 그만 보기] 사용을 체크합니다." />
				<label for="check01">[오늘은 그만 보기] 사용</label>
			</div>
		</td>
	</tr>	
	<tr>
		<th scope="row"><label for="input02">링크 URL</label></th>
		<td><input type="text" id="linkUrl" name="linkUrl" value="${admPop.linkUrl}" class="text" style="width:563px" title="링크 URL을 입력합니다." /></td>
	</tr>	
	<tr>
		<th scope="row"><label for="radio03">링크 방식</label></th>
		<td>
			<div class="radiogroup">
				<input type="radio" id="linkKind_Y" value="001" name="linkKind" <c:if test="${admPop.linkKind=='001'}">checked</c:if> title="새창에서 제공을 선택합니다." />
				<label for="radio03">새창에서 제공</label>
				<input type="radio" id="linkKind_N" value="002" name="linkKind" <c:if test="${admPop.linkKind=='002'}">checked</c:if> title="현재화면에서 제공을 선택합니다." />
				<label for="radio04" class="mgrn">현재화면에서 제공</label>	
			</div>
		</td>
	</tr>	
	<tr>
		<th scope="row"><label for="radio01">팝업 종류</label></th>
		<td>
			<div class="radiogroup">
				<input type="radio" id="popKind_Y" value="001" name="popKind" <c:if test="${admPop.popKind=='001'}">checked</c:if> title="CMS에서 생성을 선택합니다." />
				<label for="radio01">CMS에서 생성</label>
				<input type="radio" id="popKind_N" value="002" name="popKind" <c:if test="${admPop.popKind=='002'}">checked</c:if> title="팝업 이미지 등록을 선택합니다." />
				<label for="radio02" class="mgrn">팝업 이미지 등록</label>	
			</div>
		</td>
	</tr>
	<tr id="contsDiv" style="display:none;">
		<td colspan="2"  class="editor"> 
			<textarea class="editor ma_none" id="editor" name="popConts" style="height:360px;">${admPop.popConts}</textarea>
		</td>
	</tr>			
	</form:form>	
	<tr id="apndFileDiv" style="display:none;">
		<th scope="row"><label for="input13">팝업 이미지</label></th>
		<td>
		    <form id="apndFileform" name="apndFileform" action="<%=request.getContextPath()%>/adm/contents/bbsFileUpload.do" enctype="multipart/form-data" method="post">
	        <input type="text" class="text" id="fileName"  style="width:200px" readonly="readonly" title="배너 등록파일을 등록합니다." />
			<span class="file_wrap">
				<button class="btn_style1_2" type="button">파일</button>
				<input type="file" name="upFile" class="file_hidden" onchange="javascript:document.getElementById('fileName').value = this.value" />
			</span>
			<span class="txt_size"><span class="presNum">0</span> / 100KB이하</span>
			</form>
			<div id="apndImg" class="listbox">
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
	<div class="btn_board_sec">
		<div class="fl">
			<button type="button" class="btn_style2_2" id="btnCancel">취소</button>
		</div>
		<div class="fr">
			<button type="button" class="btn_style3_2" id="btnSave">등록</button>
			<button type="button" class="btn_style4_2" id="btnList">목록</button>
		</div>
	</div>
</div>
</body>

</html>

