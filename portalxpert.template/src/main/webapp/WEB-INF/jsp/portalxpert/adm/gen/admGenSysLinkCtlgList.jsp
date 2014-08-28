<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
			,'linkCtlg_modify_pop',500,230);
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
</head>

<body>

<div class="container">
	<div class="header">
		<div class="h1">링크관리</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">링크관리</strong>
		</div>
	</div>
	
	
	<!--tab-->
	<div class="tab_1">
		<!-- 제목부분 --> 
		<ul class="clearfix">
			<!--tab01-->
			<li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(2)" class="tab_title" ><span>링크 관리</span></a></li>
			<li class="on"><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(1)" class="tab_title" ><span>링크분류 관리</span></a></li>
		</ul>
		<!-- //제목부분 -->
		<!-- 내용부분 -->
			<div class="tab_post">	
				<form:form commandName="admGenLinkVO" name="frmMain" method="post">
				<input type="hidden" id="linkCatId" name="linkCatId">
				<!-- 링크 등록 -->
				<div class="contents">
					<table class="tbl_form" summary="이 표는 링크 등록정보 표입니다.">
					<caption>링크 등록</caption>
					<colgroup>
						<col style="width:15%" />
						<col style="width:35%" />
						<col style="width:15%" />
						<col style="width:35%" />
					</colgroup>
					<tbody>
					<tr>
						<th scope="row"><label for="input01">분류명</label></th>
						<td><input type="text" id="linkCatNm" name="linkCatNm" class="text" style="width:222px" title="분류명을 입력합니다." /></td>
						<th scope="row"><label for="input01">순서</label></th>
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
					
					<div class="pop_btn_sec">
							<button class="btn_style3_2" type="button" onclick="fn_admGen_linkCtlg_insert();">등록</button>
					</div>					
					
				</div>	
				<br></br>
				<!-- 링크 등록 -->
	
	
				<!-- 링크 목록 -->
				<div class="rbox">
					<span class="rbox_top"></span>
					<div class="rboxInner">
						<label class="lbl">링크분류명</label>
						<input type="text" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" /> 
						<button class="btn_style7_2" type="button" id="search" onclick="fn_admGen_linkCtlg_search();">검색</button>
					</div>
				</div>
				
				<div class="btn_board_top">
					<div class="fr mt5">
						<c:out value="${pSearch.currentRecordCount}"/> 건
					</div>					
				</div>
				<table summary="이 표는 링크분류 목록입니다." class="tbl_list">
				<caption>링크분류 목록</caption>
				<colgroup>
					<col width="150px"/>
					<col width="70px"/>
					<col/>
					<col/>
					<col/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col" class="f"><div class="col">링크분류</div></th>
					<th scope="col"><div class="col">표시순서</div></th>
					<th scope="col"><div class="col">수정</div></th>
					<th scope="col"><div class="col">삭제</div></th>
					<th scope="col" class="e"><div class="col">사용여부</div></th>
				</tr>
				</thead>
				<tbody>
			<c:choose>
				<c:when test="${paginationInfo.totalRecordCount > 0}">
					<c:forEach var="result" items="${sysLinkCtlgList}" varStatus="i">		
						<tr>
							<td class="tit"><a href="#" onclick="javascript:fn_admGen_linkCtlg_linkCall('${result.linkCatId}');">${result.linkCatNm}</a></td>
							<td>${result.linkCatOrder}</td>
							<td><a href="#" onclick="fn_admGen_linkCtlg_modify('${result.linkCatId}');" class="ico_modify" title="수정"></a></td>
							<td><a href="#" onclick="fn_admGen_linkCtlg_delete('${result.linkCatId}');" class="ico_delete" title="삭제"></a></td>
							<td>사용</td>
						</tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="5">검색된 데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>		
				
				</tbody>
				</table>
				<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_admGen_linkCtlg_linkPage" />
				</div>
				<!-- 링크 목록 -->
			
			</form:form>
			

			</div>
			<!--tab02-->
	</div>
	<!--//tab-->	
	
</div>

</body>
</html>

