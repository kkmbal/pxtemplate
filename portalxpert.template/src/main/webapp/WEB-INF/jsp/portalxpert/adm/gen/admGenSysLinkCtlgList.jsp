<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 링크화면 호출 */
var fn_admGen_linkCtlg_linkCall = function(linkCatId){
	document.frmMain.linkCatId.value = linkCatId;
	document.frmMain.action = "${WEB_HOME}/adm/gen/getAdmGenSysLinkList.do";
   	document.frmMain.submit();
};

/* 링크분류 등록 */
var fn_admGen_linkCtlg_insert = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/insertAdmGenSysLinkCtlg.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					fn_admGen_linkCtlg_search();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

/* 링크분류 수정화면 */
var fn_admGen_linkCtlg_modify = function(linkCatId){
	PortalCommon.popupWindowCenter('${WEB_HOME}/adm/gen/getAdmGenSysLinkCtlg.do?linkCatId=' + linkCatId
			,'linkCtlg_modify_pop',500,160);
};

/* 링크분류 삭제 */
var fn_admGen_linkCtlg_delete = function(linkCatId){
	
	$("#linkCatId").val(linkCatId);
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/deleteAdmGenSysLinkCtlg.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){ 
					fn_admGen_linkCtlg_search();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

/* pagination 페이지 링크 function */
var fn_admGen_linkCtlg_linkPage = function(pageNo){
	document.frmMain.pageIndex.value = pageNo;
	document.frmMain.action = "${WEB_HOME}/adm/gen/getAdmGenSysLinkCtlgList.do";
   	document.frmMain.submit();
};

/* search 버튼 클릭시 */
var fn_admGen_linkCtlg_search = function(pageNo){
	$("form[name=frmMain]").attr('action','${WEB_HOME}/adm/gen/getAdmGenSysLinkCtlgList.do').submit();
};

/* 탭이동 */
var fnMenuToggle = function(idx)
{
	if (idx == '1')
	{
		location.href = "${WEB_HOME}/adm/gen/getAdmGenSysLinkCtlgList.do";
	}
	else if (idx == '2')
	{
		location.href = "${WEB_HOME}/adm/gen/getAdmGenSysLinkList.do";
	}
	else if (idx == '3')
	{
		location.href = "${WEB_HOME}/adm/gen/getAdmGenMainLinkList.do";
	}
};

/*  pagination 페이지 navigation 이전/다음/처음/마지막 function */
var fn_adm_code_page_navi = function(idx){
	var pageNo = '${paginationInfo.currentPageNo}';
	if(idx == 'previous'){
		if(pageNo != 1)
			fn_adm_code_link_page(Number(pageNo)-1);
		
	}else if(idx == 'next'){
		if(pageNo != '${paginationInfo.lastPageNo}')
			fn_adm_code_link_page(Number(pageNo)+1);
			
		
	}else if(idx == 'first'){
		if(pageNo != 1)
			fn_adm_code_link_page('${paginationInfo.firstPageNo}');
		
	}else if(idx == 'last'){
		if(pageNo != '${paginationInfo.lastPageNo}')
			fn_adm_code_link_page('${paginationInfo.lastPageNo}');
		
	}
};

$("document").ready(function(){
	
	$("form[name=frmMain]").validate({
		
		onfocusout: false
	    ,onkeyup: false
	    ,onclick: false
	    ,ignoreTitle:true
	    ,onsubmit: false
	    
		,rules : {
			linkCatNm : {
				required : true
				,maxlength : 15
			}
		}
		,showErrors:function(errorMap, errorList){
			if(errorList.length == 0) return;
			var caption = $(errorList[0].element).attr('title') || $(errorList[0].element).attr('alt') || $(errorList[0].element).attr('name');
			alert(caption + " " + errorList[0].message);
			$(errorList[0].element).focus();
		}
		
	});
	
	//필수 초기화 메소드 호출
	initValidform("frmMain");
	
});
</script>

	<!-- web_navi -->
	<div class="web_navi"> 
		<ul>
			<li>Home</li>
			<li>관리자</li>
			<li>포털관리</li>
			<li>연계관리</li>
			<li>링크분류관리</li>
		</ul>
	</div>
	<!--//web_navi-->
	
	<!--tab-->
	<div class="tab_1">
		
		<!--tab링크-->
		<ul class="clearfix">
			<!--tab01-->
			<li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(2)" class="tab_title" ><span>링크 관리</span></a></li>
			<li class="on"><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(1)" class="tab_title" ><span>링크분류 관리</span></a></li>
			<!-- li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(3)" class="tab_title" ><span>업무데스크 관리</span></a></li -->
		</ul>
		<!--//tab링크-->
		
		<div class="tab_post">
			
			<!--button-->
			<!--//button-->
			
			<form:form commandName="admGenLinkVO" name="frmMain" method="post">
			<input type="hidden" id="linkCatId" name="linkCatId">
			
			<!--search-->
					<p class="title_sub">링크 등록</p>
					<div class="tbl_onedata">
						<table summary="링크분류 등록">
							<caption>링크분류 등록</caption>
							<colgroup>
								<col width="12%">
								<col width="40%">
								<col width="12%">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row"><span class="bl_red"><!--필수--></span>분류명</th>
									<td><input type="text" id="linkCatNm" name="linkCatNm" title="분류명" class="inp_all"></td>
									<th scope="row">순서</th>
									<td>
										<select name="linkCatOrder" title="순서 선택" style="width:50px;">
										<c:forEach var="order" begin="1" end="${paginationInfo.totalRecordCount+1}" step="1">
											<option value="${order}" <c:if test="${order == paginationInfo.totalRecordCount+1}">selected="selected"</c:if>>${order}</option>
										</c:forEach>
										</select>
									</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="btn_area">
						<div class="fl_right">
							<a href="#" onclick="javascript:fn_admGen_linkCtlg_insert();" class="btn_data">
								<span class="btnin_icon"><!--버튼아이콘--></span>
								<span class="btn_text">등록</span>
							</a>
						</div>
					</div>
					
					<!--title-->
					<p class="title_sub">링크 목록</p>
					<!--//title-->
					<!--search-->
					<div class="search_area">
						<table summary="링크분류 검색">
							<caption>링크분류 검색</caption>
							<colgroup>
								<col width="10%">
								<col>
							</colgroup>
							<tbody>
								<tr>
									<th scope="row">링크분류</th>
									<td><input type="text" id="searchKeyword" name="searchKeyword" title="검색어 입력"></td>
								</tr>
							</tbody>
						</table>
						<a class="btn_search" href="#" onclick="javascript:fn_admGen_linkCtlg_search();"><!--검색버튼--></a>
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
			<div class="tbl_list te_center">
				<table summary="링크분류 목록">
					<caption>링크분류 목록</caption>
					<colgroup>
						<col width="45%">
						<col>
						<col>
						<col>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th scope="col">링크분류</th>
							<th scope="col">표시순서</th>
							<th scope="col">수정</th>
							<th scope="col">삭제</th>
							<th scope="col">사용여부</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${sysLinkCtlgList}" varStatus="i">
						<tr>
							<td class="te_left"><a href="#" onclick="javascript:fn_admGen_linkCtlg_linkCall('${result.linkCatId}');">${result.linkCatNm}</a></td>
							<td>${result.linkCatOrder}</td>
							<td><a href="#" onclick="fn_admGen_linkCtlg_modify('${result.linkCatId}');" class="ico_modify" title="수정"></a></td>
							<td><a href="#" onclick="fn_admGen_linkCtlg_delete('${result.linkCatId}');" class="ico_delete" title="삭제"></a></td>
							<td>사용</td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--//list-->
			
			<!--paginate-->
			<div id="paging" class="paginate">
				<ui:pagination paginationInfo = "${paginationInfo}"
						   type="image"
						   jsFunction="fn_admGen_linkCtlg_linkPage"
						   />
				<form:hidden path="pageIndex" />
			</div>
			<!--//paginate-->
			
			</form:form>
			
			<!--button-->
			<!--//button-->
		</div>
		
	</div>
	<!--//tab-->