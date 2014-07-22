<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
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
<!--pop_wrap-->
<div class="pop_wrap" style="width:698px;">

	<div class="pop_header">배너정보</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="pop_post clearfix">
			<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
			<caption>제목</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:35%" />
				<col style="width:15%" />
				<col style="width:35%" />
			</colgroup>
			<tbody>
			<form:form commandName="admBannerVO" action="${WEB_HOME}/adm/banner/getAdmBannerView.do" name="listForm" method="post">
			<input type="hidden" name="bnrId" id="bnrId" value="${admBanner.bnrId}">
			<input type="hidden" name="expoBgnDttm" id="expoBgnDttm">
			<input type="hidden" name="expoEndDttm" id="expoEndDttm">
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">배너이름</label></th>
				<td colspan="3"><input type="text"  class="text" style="width:443px" title="배너이름" id="bnrTitle" name="bnrTitle" value="${admBanner.bnrTitle}" /></td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">게시기간</label></th>
				<td colspan="3">
					<div class="sec_calender">
						<input type="text" class="text" id="expoBgnDttm_v" name="expoBgnDttm_v" title="시작날짜를 입력합니다. 예)YYYY.MM.DD" value="${admBanner.expoBgnDttm}">
					</div> ~ 
					<div class="sec_calender">
						<input type="text" class="text" id="expoEndDttm_v" name="expoEndDttm_v" title="시작날짜를 입력합니다. 예)YYYY.MM.DD" value="${admBanner.expoEndDttm}">
					</div>			
				</td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">배너위치</label></th>
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
			</form:form>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">배너등록</label></th>
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