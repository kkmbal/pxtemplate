<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

</head>

<script type="text/javascript" >

var bnrId = '${admBanner.bnrId}';
var parRowPosList = ${parRowPosList};
var rowPosList = ${rowPosList};
var parRowPos = '${admBanner.parRowPos}';
var rowPos = '${admBanner.rowPos}';
var jsonAppendFileList = [];  //첨부 리스트

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admBannerManage.js"></script>

<body>
<!--  팝업 520 * 305-->
<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1><img src="${RES_HOME}/images/pop_tit_bannerDetail.png" alt="배너정보" /></h1>
		</div>
		<div class="contents">
			<table class="tbl_form" summary="이 표는 배너이름, 게시기간, 배너위치, 배너등록 사항을 입력하는 배너정보 표입니다.">
			<caption>배너정보</caption>
			<colgroup>
				<col style="width:22%" />
				<col style="width:auto" />
			</colgroup>
			<tbody>
			<form:form commandName="admBannerVO" action="${WEB_HOME}/adm/banner/getAdmBannerView.do" name="listForm" method="post">
			<input type="hidden" name="bnrId" id="bnrId" value="${admBanner.bnrId}">
			<input type="hidden" name="expoBgnDttm" id="expoBgnDttm">
			<input type="hidden" name="expoEndDttm" id="expoEndDttm">			
			<tr>
				<th scope="row"><label for="input01">배너 이름</label></th>
				<td><input type="text" id="bnrTitle" name="bnrTitle" value="${admBanner.bnrTitle}" class="text" style="width:340px" title="배너 이름을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="date03">게시 기간</label></th>
				<td>
					<div class="sec_calender">
						<input type="text" class="text" id="expoBgnDttm_v" name="expoBgnDttm_v" value="${admBanner.expoBgnDttm}"  title="시작날짜를 입력합니다. 예)YYYY.MM.DD" />
					</div>
					~
					<div class="sec_calender">
						<input type="text" class="text" id="expoEndDttm_v" name="expoEndDttm_v" value="${admBanner.expoEndDttm}"  title="종료날짜를 입력합니다. 예)YYYY.MM.DD" />
					</div>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="select02">배너 위치</label></th>
				<td>
					<span class="selectN" style="width:150px">
						<span>
							<select title="상위목록을 선택합니다" name="parRowPos" id="parRowPos">
								<option value="">선택</option>
							</select>
						</span>
					</span>
					<span class="selectN" style="width:180px">
						<span>
							<select title="상세목록을 선택합니다" name="rowPos" id="rowPos">
								<option value="">선택</option>
							</select>
						</span>
					</span>
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">링크URL</label></th>
				<td><input type="text" id="linkUrl" name="linkUrl" value="${admBanner.linkUrl}" class="text" style="width:340px" title="링크URL을 입력합니다." /></td>
			</tr>			
			<tr>
				<th scope="row"><label for="input01">정렬순서</label></th>
				<td><input type="text" id="sortSeq" name="sortSeq" value="${admBanner.sortSeq}" class="text" style="width:50px" title="정렬순서를 입력합니다." /></td>
			</tr>
			</form:form>			
			<tr>
				<th scope="row"><label for="input13">배너 등록</label></th>
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
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnSave">저장</button>
				<button class="btn_style4_2" type="button" id="btnClose">취소</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>
<!--  //팝업 -->
</body>

</html>


