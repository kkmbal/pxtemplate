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
			,'linkModify',500,330);
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
			
<body>
<div class="container">	
<div class="header">
	<h1>링크관리</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>링크관리</strong></span>
	</div>
</div>
	
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
			<h2>링크 등록</h2>
			<!--//title-->
			<!--data-->
			<div class="tbl_onedata">
			
			<table class="tbl_form" summary="링크 등록">
			<caption>링크 등록</caption>
			<colgroup>
				<col style="width:15%" />
				<col style="width:35%" />
				<col style="width:15%" />
				<col style="width:35%" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">링크분류</label></th>
				<td>
					<select id="linkCatId" name="linkCatId" title="링크분류">
						<c:forEach var="result" items="${sysLinkCatList}" varStatus="i">
							<option value="${result.linkCatId}" <c:if test="${result.linkCatId == pLINK_CAT_ID}">selected='selected'</c:if>>${result.linkCatNm}</option>
						</c:forEach>
					</select>
				</td>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input03">링크명</label></th>
				<td><input type="text"  class="text" style="width:210px" title="링크명" id="linkNm" name="linkNm" ></td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">순서</label></th>
				<td>
					<select id="linkOrder" name="linkOrder" title="순서">
					<c:forEach var="order" begin="1" end="${paginationInfo.totalRecordCount+1}" step="1">
						<option value="${order}" <c:if test="${order == paginationInfo.totalRecordCount+1}">selected="selected"</c:if>>${order}</option>
					</c:forEach>
					</select>
				</td>
				<th scope="row"><label for="input03">팝업사용</label></th>
				<td><input type="checkbox" title="팝업사용" id="popupUseGb" name="popupUseGb" value="Y" <c:if test="${sysLink.popupUseGb eq 'Y'}">checked='checked'</c:if>></td>
			</tr>
			<tr>
				<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">링크주소</label></th>
				<td colspan="3"><input type="text"  class="text" style="width:560px" title="링크주소" id="linkAddress" name="linkAddress" ></td>
			</tr>
			<tr>
				<th scope="row"><label for="input02">약어명</label></th>
				<td colspan="3"><input type="text"  class="text" style="width:560px" title="약어명" id="linkSnm" name="linkSnm" ></td>
			</tr>
			</tbody>
			</table>			
			</div>
			<!--data-->
			<br>
			<!--button-->
			<div class="btn_board_top">
				<div class="fr">
					<a href="#" class="btn_set bt_style3" onclick="fn_admGen_link_insert();"><span>등록</span></a>
				</div>
			</div>
			<!--//button-->
			<!--title-->
			<h2>링크 목록</h2>
			<!--//title-->
			<!--search-->
			<div class="rbox">
				<span class="rbox_top"></span>
				<div class="rboxInner">
					<!-- 셀렉트박스 -->
					<span style="font-weight:bold;width:100px">
						링크검색
					</span>
					<!-- //셀렉트박스 -->
					<input type="text" id="searchKeyword" name="searchKeyword" title="검색어 입력" class="text ml5mr10" style="width:450px" /> 
					<a href="#" class="btn_set bt_style7" id="search" onclick="fn_admGen_linkCtlg_search();"><span>검색</span></a>
				</div>
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
			<div id="paging" class="paging">
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
	
	</div>
	</body>
</html>	