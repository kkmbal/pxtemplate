<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 콘텐츠 미리보기 화면 */
/* var fn_admGen_contMag_preview = function(p_url, p_rowPos){
	
	var sWith = 0;
	var sHeight = 0;
	
	if(p_rowPos == 'W'){
		sWith = 490;
		sHeight = 260;
	}else{
		sWith = 230;
		sHeight = 160;
	}
	
	PortalCommon.popupWindowCenter('${WEB_HOME}'+p_url,'contentPreview',sWith,sHeight);
}; */

/* 콘텐츠등록화면 호출*/
var fn_admGen_contentManage_regist = function(){
	PortalCommon.popupWindowCenter('${WEB_HOME}/adm/gen/getAdmGenContentManage.do?pageType=I', 'contentRegister', 698, 368);
};

/* 콘텐츠수정화면 호출 */
var fn_admGen_contMag_modify = function(cntsId){
	PortalCommon.popupWindowCenter('${WEB_HOME}/adm/gen/getAdmGenContentManage.do?pageType=U&cntsId=' + cntsId, 'contentEditPopup', 698, 436);
};

/* 콘텐츠 삭제 */
var fn_admGen_contMag_delete = function(cntsId){
	
	$("#cntsId").val(cntsId);
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/deleteAdmGenContentManage.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					fn_admGen_contMag_seaBTN_click();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

/* pagination */
var fn_admGen_contMag_linkPage = function(pageNo){
	document.frmMain.pageIndex.value = pageNo;
	document.frmMain.action = "${WEB_HOME}/adm/gen/getAdmGenContentManageList.do";
   	document.frmMain.submit();
};

var fn_admGen_contMag_seaBTN_click = function(){
	$("form[name=frmMain]").attr('action','${WEB_HOME}/adm/gen/getAdmGenContentManageList.do').submit();
};

var fn_admGen_contMag_regBTN_click = function(){
	fn_admGen_contentManage_regist();
};
</script>

	<!-- web_navi -->
	<div class="web_navi"> 
		<ul>
			<li>Home</li>
			<li>관리자</li>
			<li>포털관리</li>
			<li>메인홈관리</li>
			<li>개인영역콘텐츠관리</li>
		</ul>
	</div>
	<!--//web_navi-->
	
	<!--button-->
	<div class="btn_area">
		<div class="fl_left">
			<a href="#" onclick="fn_admGen_contMag_regBTN_click();" class="btn_all">
				<span class="btn_text">등록</span>
			</a>
		</div>
	</div>
	<!--//button-->
	
	<form:form commandName="admGenContentManageVO" name="frmMain" method="post">
	<input type="hidden" id="cntsId" name="cntsId">
	
	<!--search-->
	<div class="search_area">
		<table summary="콘텐츠 검색">
			<caption>콘텐츠 검색</caption>
			<colgroup>
				<col width="12%">
				<col>
			</colgroup>
			<tbody>
				<tr>
					<th scope="row">콘텐츠 검색</th>
					<td><input type="text" id="searchKeyword" name="searchKeyword" title="검색 입력"></td>
				</tr>
			</tbody>
		</table>
		<a class="btn_search" href="#" onclick="fn_admGen_contMag_seaBTN_click();" title="검색"><!--검색버튼--></a>
		<span class="sch_tl"><!--top,left--></span>
		<span class="sch_tr"><!--top,right--></span>
		<span class="sch_br"><!--bottom,right--></span>
		<span class="sch_bl"><!--bottom,left--></span>
	</div>
	<!--//search-->
	
	<!-- page unit -->
	<div class="select te_right" >
		<c:out value="${paginationInfo.recordCountPerPage}"/> 건
	</div>
    <!-- //paga unit -->
    
	<!--list-->
	<div class="tbl_list te_center te90">
		<table summary="개인영역콘텐츠관리 목록" class="tbl_fixed">
			<caption>개인영역콘텐츠관리 목록</caption>
			<colgroup>
				<col width="8%">
				<col width="18%">
				<col>
				<col width="11%">
				<col width="10%" span="2">
				<col width="6%" span="2">
			</colgroup>
			<thead>
				<tr>
					<th scope="col">코드</th>
					<th scope="col">콘텐츠명</th>
					<th scope="col">콘텐츠 링크</th>
					<th scope="col">콘텐츠 <br>타입</th>
					<th scope="col">새로고침<br>(분)</th>
					<th scope="col">사용여부</th>
					<th scope="col">수정</th>
					<th scope="col">삭제</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="result" items="${genContentList}" varStatus="i">
				<tr>
					<td>${result.cntsId}</td>
					<td class="te_left"><%-- <a href="#" onclick="fn_admGen_contMag_preview('${result.cntsLink}','${result.cntsTp}');" class="fo_blue te_dot">${result.cntsName}</a> --%>
					<p class="fo_blue te_dot">${result.cntsName}</p></td>
					<td class="te_left">${result.cntsLink}</td>
					<td>
						<c:if test="${result.cntsTp eq 'M'}">해야할일</c:if>
						<c:if test="${result.cntsTp eq 'P'}">포틀릿</c:if>
						<c:if test="${result.cntsTp eq 'U'}">유용한툴</c:if>
						<c:if test="${result.cntsTp eq 'TAB'}">최근게시물</c:if>
						<c:if test="${result.cntsTp eq 'RLT'}">개인홈공지</c:if>
					</td>
					<td>${result.msgRefreshTm}</td>
					<td>
						<c:choose>
							<c:when test="${result.useYn eq 'Y'}">사용</c:when>
							<c:otherwise>미사용</c:otherwise>
						</c:choose>
					</td>
					<td><a href="#" onclick="fn_admGen_contMag_modify('${result.cntsId}');" title="수정" class="ico_modify"></a></td>
					<td><a href="#" onclick="fn_admGen_contMag_delete('${result.cntsId}');" title="삭제" class="ico_delete"></a></td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!--//list-->
	
	<!--paginate-->
	<div id="paging" class="paginate">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_admGen_contMag_linkPage" />
		<form:hidden path="pageIndex" />
	</div>
	<!--//paginate-->
	</form:form>
	
	<!--button-->
	<!--//button-->
