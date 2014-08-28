<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 링크분류 수정 */
var fn_admGen_linkCtlg_update = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
	
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/updateAdmGenSysLinkCtlg.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					$(opener.location).attr("href", "javascript:fn_admGen_linkCtlg_search();"); 
					self.close();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

$("document").ready(function(){
	
	$("form[name=frmMain]").validate({
		onfocusout: false
	    ,onkeyup: false
	    ,onclick: false
	    ,ignoreTitle:true
	    
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

var fn_admGen_linkCtlg_uptBTN_click = function(){
	fn_admGen_linkCtlg_update();
};

var fn_admGen_linkCtlg_iniBTN_click = function(){
	$("form[name=frmMain]").reset();
};

var fn_admGen_linkCtlg_cloBTN_click = function(){
	window.close();
};

</script>

</head>
<body>

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">링크분류 수정</h1>
		</div>
		<div class="contents">
			<form:form commandName="admGenLinkVO" name="frmMain" method="post">
			<input type="hidden" name="linkCatId" value="${sysLinkCtlg.linkCatId}">
			<input type="hidden" name="linkCatOrder" value="${sysLinkCtlg.linkCatOrder}">
			<table class="tbl_form" summary="이 표는 링크를 분류 및 수정하는 표입니다.">
			<caption>링크분류 수정</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">링크분류명</label></th>
				<td><input type="text" maxlength="50" id="linkCatNm" name="linkCatNm" value="${sysLinkCtlg.linkCatNm}" class="text" style="width:240px" title="링크분류명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">분류 순서</label></th>
				<td>
                	<select name="linkCatOrderNew" title="순서를 선택하세요">
					<c:forEach var="order" begin="1" end="${sysLinkCtlg.linkCatCount}" step="1">
						<option value="${order}" <c:if test="${order == sysLinkCtlg.linkCatOrder}">selected="selected"</c:if>>${order}</option>
					</c:forEach>
					</select>				
				</td>
			</tr>	
			</tbody>
			</table>
			</form:form>
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnOK" onclick="fn_admGen_linkCtlg_uptBTN_click();">등록</button>
				<button class="btn_style4_3" type="button" id = "btnClose" onclick="fn_admGen_linkCtlg_iniBTN_click();">초기화</button>
				<button class="btn_style4_2" type="button" id = "btnClose" onclick="self.close();">닫기</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>

</body>
</html>

