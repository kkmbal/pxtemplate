<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
			,'linkModify',500,380);
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
			<li class="on"><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(2)" class="tab_title" ><span>링크 관리</span></a></li>
			<li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(1)" class="tab_title" ><span>링크분류 관리</span></a></li>
		</ul>
		<!-- //제목부분 -->
		<!-- 내용부분 -->
			<div class="tab_post">	
				<form:form commandName="admGenLinkVO" name="frmMain" method="post">	
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
						<th scope="row"><label for="input01">링크분류</label></th>
						<td>
							<select id="linkCatId" name="linkCatId" title="링크분류">
								<c:forEach var="result" items="${sysLinkCatList}" varStatus="i">
									<option value="${result.linkCatId}" <c:if test="${result.linkCatId == pLINK_CAT_ID}">selected='selected'</c:if>>${result.linkCatNm}</option>
								</c:forEach>
							</select>						
						</td>
						<th scope="row"><label for="input01">링크명</label></th>
						<td><input type="text" id="linkNm" name="linkNm" class="text" style="width:222px" title="링크명을 입력합니다." /></td>
					</tr>
					<tr>
						<th scope="row"><label for="input01">순서</label></th>
						<td>
							<select id="linkOrder" name="linkOrder" title="순서">
							<c:forEach var="order" begin="1" end="${paginationInfo.totalRecordCount+1}" step="1">
								<option value="${order}" <c:if test="${order == paginationInfo.totalRecordCount+1}">selected="selected"</c:if>>${order}</option>
							</c:forEach>
							</select>
						</td>
						<th scope="row"><label for="input01">팝업사용</label></th>
						<td><input type="checkbox" id="popupUseGb" name="popupUseGb" value="Y" <c:if test="${sysLink.popupUseGb eq 'Y'}">checked='checked'</c:if> title="팝업사용을 입력합니다." /></td>
					</tr>
					<tr>
						<th scope="row"><label for="input01">링크주소</label></th>
						<td colspan="3"><input type="text" id="linkAddress" name="linkAddress" class="text" style="width:222px" title="링크주소를 입력합니다." /></td>
					</tr>
					<tr>
						<th scope="row"><label for="input01">약어명</label></th>
						<td colspan="3"><input type="text" id="linkSnm" name="linkSnm" class="text" style="width:222px" title="약어명을 입력합니다." /></td>
					</tr>
					
					</tbody>
					</table>
										
					
					<div class="pop_btn_sec">
							<button class="btn_style3_2" type="button" onclick="fn_admGen_link_insert();">등록</button>
					</div>					
					
				</div>	
				<br></br>
				<!-- 링크 등록 -->
	
	
				<!-- 링크 목록 -->
				<div class="rbox">
					<span class="rbox_top"></span>
					<div class="rboxInner">
						<label class="lbl">링크명</label>
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
					<col width="70px"/>
					<col width="60px"/>
					<col width="60px"/>
				</colgroup>
				<thead>
				<tr>
					<th scope="col" class="f"><div class="col">링크분류</div></th>
					<th scope="col"><div class="col">표시순서</div></th>
					<th scope="col"><div class="col">링크명</div></th>
					<th scope="col"><div class="col">약어명</div></th>
					<th scope="col"><div class="col">팝업여부</div></th>
					<th scope="col"><div class="col">수정</div></th>
					<th scope="col" class="e"><div class="col">삭제</div></th>
				</tr>
				</thead>
				<tbody>
			<c:choose>
				<c:when test="${paginationInfo.totalRecordCount > 0}">
					<c:forEach var="result" items="${sysLinkList}" varStatus="i">		
						<tr>
							<td>${result.linkCatNm}</td>
							<td>${result.linkOrder}</td>
							<td class="tit"><a href="javascript:fnAdwWin('${result.linkAddress}' ,'${result.popupUseGb}')">${result.linkNm}</a></td>
							<td>${result.linkSnm}</td>
							<td>${result.popupUseGb}</td>
							<td><a onclick="fn_admGen_link_modify('${result.linkCatId}','${result.linkCd}');" class="ico_modify" title="수정"></a></td>
							<td><a onclick="fn_admGen_link_delete('${result.linkCatId}','${result.linkCd}');" class="ico_delete" title="삭제"></a></td>
						</tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="7">검색된 데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>		
				
				</tbody>
				</table>
				<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_admGen_link_linkPage" />
				</div>
				<!-- 링크 목록 -->
			
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

			</div>
			<!--tab02-->
	</div>
	<!--//tab-->	
	
</div>

</body>
</html>	

