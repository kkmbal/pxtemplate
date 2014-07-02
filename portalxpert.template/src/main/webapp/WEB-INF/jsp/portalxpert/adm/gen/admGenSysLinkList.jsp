<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 링크분류 등록 */
var fn_admGen_link_insert = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
	
	$("form[name=frmAction] :input[name=linkNm]").val($("#linkNm").val());
	$("form[name=frmAction] :input[name=linkAddress]").val($("#linkAddress").val());
	
	$("form[name=frmAction] :input[name=linkCatId]").val($("#linkCatId").val());
	$("form[name=frmAction] :input[name=linkOrder]").val($("#linkOrder").val());
	$("form[name=frmAction] :input[name=popupUseGb]").val($("#popupUseGb").val());
	
	$("form[name=frmAction] :input[name=linkSnm]").val($("#linkSnm").val());
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/insertAdmGenSysLink.do?format=json",
		    data: $("form[name=frmAction]").serialize(),
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
var fn_admGen_link_modify = function(linkCatId,linkCd){
	PortalCommon.popupWindowCenter('${WEB_HOME}/adm/gen/getAdmGenSysLink.do?linkCatId='+ linkCatId +'&linkCd=' + linkCd
			,'linkModify',500,310);
};

/* 링크분류 삭제 */
var fn_admGen_link_delete = function(linkCatId, linkCd){
	
	$("form[name=frmAction] :input").each(function(){
		$(this).val();
	});
	
	$("form[name=frmAction] :input[name=linkCatId]").val(linkCatId);
	$("form[name=frmAction] :input[name=linkCd]").val(linkCd);
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/deleteAdmGenSysLink.do?format=json",
		    data: $("form[name=frmAction]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					fn_admGen_linkCtlg_search();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

/* 페이지 링크 */
var fn_admGen_link_linkPage = function(pageNo){
	
	$("form[name=frmAction] :input").each(function(){
		$(this).val();
	});
	
	$("form[name=frmAction] :input[name=linkCatId]").val($("#linkCatId").val());
	$("form[name=frmAction] :input[name=pageIndex]").val(pageNo);
	$("form[name=frmAction]").attr('action','${WEB_HOME}/adm/gen/getAdmGenSysLinkList.do').submit();
};

$("document").ready(function(){
	
	$("form[name=frmMain]").validate({
		onfocusout: false
	    ,onkeyup: false
	    ,onclick: false
	    ,ignoreTitle:true
	    
		,rules : {
			linkCatId : {
				required : true
			}
			,linkNm : {
				required : true
				,maxlength : 15
			}
			,linkAddress : {
				required : true
				,maxlength : 128
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

/* search 버튼 클릭시 */
var fn_admGen_linkCtlg_search = function(){
	$("form[name=frmAction] :input[name=linkCatId]").val($("#linkCatId").val());
	$("form[name=frmAction] :input[name=searchKeyword]").val($("#searchKeyword").val());
	$("form[name=frmAction]").attr('action','${WEB_HOME}/adm/gen/getAdmGenSysLinkList.do').submit();
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

//링크 관리 연결
var fnAdwWin = function(url, popupYn) {
// 	var originMenu = "adw";
	
	if (url.indexOf("/adw/") > -1 || url.indexOf("/board100/") > -1 || url.indexOf("/adm/") > -1 || url.indexOf("/organization/") > -1) {
		url = "${WEB_HOME}"+url;
	}
	
// 	var sUrl = "${WEBLOG_RECEIVER}?originMenu="+originMenu+"&linkName="+linkName+"&actionURL="+encodeURIComponent(url);
	
	if (popupYn == "Y") {
		window.open(url);
	} else {
		location.href = url;
	}
};

</script>
			
	<!-- web_navi -->
	<div class="web_navi"> 
		<ul>
			<li>Home</li>
			<li>관리자</li>
			<li>포털관리</li>
			<li>연계관리</li>
			<li>링크관리</li>
		</ul>
	</div>
	<!--//web_navi-->
	
	<!--tab-->
	<div class="tab_1">
		
		<!--tab링크-->
		<ul class="clearfix">
			<li class="on"><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(2)" class="tab_title" ><span>링크 관리</span></a></li>
			<li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(1)" class="tab_title" ><span>링크분류 관리</span></a></li>
			<!-- li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(3)" class="tab_title" ><span>업무데스크 관리</span></a></li -->
		</ul>
		<!--//tab링크-->
		
		<div class="tab_post">
			<!--button-->
			<!--//button-->
			
			<form:form commandName="admGenLinkVO" name="frmMain" method="post">

			<!--search-->
			<!--title-->
			<p class="title_sub">링크 등록</p>
			<!--//title-->
			<!--data-->
			<div class="tbl_onedata">
				<table summary="링크 등록">
					<caption>링크 등록</caption>
					<colgroup>
						<col width="11%">
						<col width="19%">
						<col width="9%">
						<col>
						<col width="8%">
						<col width="9%">
						<col width="10%">
						<col width="8%">
					</colgroup>
					<tbody>
						<tr>
							<th scope="row"><span class="bl_red"><!--필수--></span>링크분류</th>
							<td>
								<select id="linkCatId" name="linkCatId" title="링크분류">
								<c:forEach var="result" items="${sysLinkCatList}" varStatus="i">
									<option value="${result.linkCatId}" <c:if test="${result.linkCatId == pLINK_CAT_ID}">selected='selected'</c:if>>${result.linkCatNm}</option>
								</c:forEach>
							</select>
							</td>
							<th scope="row"><span class="bl_red"><!--필수--></span>링크명</th>
							<td><input type="text" id="linkNm" name="linkNm" title="링크명" class="inp_all"></td>
							<th scope="row">순서</th>
							<td>
								<select id="linkOrder" name="linkOrder" title="순서">
								<c:forEach var="order" begin="1" end="${paginationInfo.totalRecordCount+1}" step="1">
									<option value="${order}" <c:if test="${order == paginationInfo.totalRecordCount+1}">selected="selected"</c:if>>${order}</option>
								</c:forEach>
								</select>
							</td>
							<th scope="row">팝업사용</th>
							<td><input type="checkbox" title="팝업사용" id="popupUseGb" name="popupUseGb" value="Y" <c:if test="${sysLink.popupUseGb eq 'Y'}">checked='checked'</c:if>></td>
						</tr>
						<tr>
							<th scope="row"><span class="bl_red"><!--필수--></span>링크 주소</th>
							<td colspan="7"><input type="text" id="linkAddress" name="linkAddress" title="링크주소" class="inp_all"></td>
						</tr>
						<tr>
							<th scope="row">약어명</th>
							<td colspan="7"><input type="text" id="linkSnm" name="linkSnm" title="약어명" ></td>
						</tr>
					</tbody>
				</table>
			</div>
			<!--data-->
			<!--button-->
			<div class="btn_area">
				<div class="fl_right">
					<a onclick="fn_admGen_link_insert();" class="btn_data">
						<span class="btnin_icon"><!--버튼아이콘--></span>
						<span class="btn_text">등록</span>
					</a>
				</div>
			</div>
			<!--//button-->
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
							<th scope="row">링크 검색</th>
							<td><input type="text" id="searchKeyword" name="searchKeyword" title="검색어 입력"></td>
						</tr>
					</tbody>
				</table>
				<a class="btn_search" onclick="fn_admGen_linkCtlg_search();" title="검색"><!--검색버튼--></a>
				<span class="sch_tl"><!--top,left--></span>
				<span class="sch_tr"><!--top,right--></span>
				<span class="sch_br"><!--bottom,right--></span>
				<span class="sch_bl"><!--bottom,left--></span>
			</div>
			<!--//search-->
			
			<!-- page unit -->
			<div class="select te_right" >
				<c:out value="${paginationInfo.totalRecordCount}"/> 건
			</div>
		    <!-- //paga unit -->
		    
			<!--list-->
			<div class="tbl_list te_center">
				<table summary="링크분류 목록">
					<caption>링크분류 목록</caption>
					<colgroup>
						<col width="150px">
						<col width="70px">
						<col>
						<col>
						<col width="70px">
						<col width="60px">
						<col width="60px">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">링크분류</th>
							<th scope="col">표시순서</th>
							<th scope="col">링크명</th>
							<th scope="col">약어명</th>
							<th scope="col">팝업여부</th>
							<th scope="col">수정</th>
							<th scope="col">삭제</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="result" items="${sysLinkList}" varStatus="i">
						<tr>
							<td class="te_left">${result.linkCatNm}</td>
							<td>${result.linkOrder}</td>
							<td><a onclick="fnAdwWin('${result.linkAddress}' ,'${result.popupUseGb}')">${result.linkNm}</a></td>
							<td>${result.linkSnm}</td>
							<td>${result.popupUseGb}</td>
							<td><a onclick="fn_admGen_link_modify('${result.linkCatId}','${result.linkCd}');" class="ico_modify" title="수정"></a></td>
							<td><a onclick="fn_admGen_link_delete('${result.linkCatId}','${result.linkCd}');" class="ico_delete" title="삭제"></a></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<!--//list-->
			
			<!--paginate-->
			<div id="paging" class="paginate">
				<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fn_admGen_link_linkPage" />
				<form:hidden path="pageIndex" />
			</div>
			<!--//paginate-->
			
			</form:form>
			
			<form:form commandName="admGenLinkVO" name="frmAction" method="post">
				<input type="hidden" name="linkCatId">
				<input type="hidden" name="linkCd">
				<input type="hidden" name="linkNm">
				<input type="hidden" name="popupUseGb">
				<input type="hidden" name="linkOrder">
				<input type="hidden" name="linkAddress">
				<input type="hidden" name="linkSnm">
				<input type="hidden" name="searchKeyword">
				<input type="hidden" name="pageIndex" value="1">
			</form:form>
			
			<!--button-->
			<!--//button-->
		</div>
		
	</div>
	<!--//tab-->