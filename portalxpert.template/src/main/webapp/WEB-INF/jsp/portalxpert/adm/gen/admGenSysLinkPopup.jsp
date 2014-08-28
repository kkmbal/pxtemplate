<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 링크분류 수정 */
var fn_admGen_link_update = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
	
	PortalCommon.getJson({
		url: "${WEB_HOME}/adm/gen/updateAdmGenSysLink.do?format=json",
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
			linkAddress : {
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



var fn_admGen_link_iniBTN_click = function(){
	$("form[name=frmMain]")[0].reset();
};

var fn_admGen_link_uptBTN_click = function(){
	fn_admGen_link_update();
};

var fn_admGen_link_cloBTN_click = function(){
	window.close();
};

</script>

</head>
<body>

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">링크수정</h1>
		</div>
		<div class="contents">
			<form:form commandName="admGenLinkVO" name="frmMain" method="post">
			<input type="hidden" name="linkCatId" value="${sysLink.linkCatId}">
			<input type="hidden" name="linkCd" value="${sysLink.linkCd}">
			<input type="hidden" name="linkOrder" value="${sysLink.linkOrder}">
			<table class="tbl_form" summary="이 표는 링크수정정보 표입니다.">
			<caption>링크수정</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">링크분류명</label></th>
				<td>${sysLink.linkCatNm}</td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">링크명</label></th>
				<td><input type="text" maxlength="50" id="linkNm" name="linkNm" value="${sysLink.linkNm}" class="text" style="width:240px" title="링크명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">링크주소</label></th>
				<td><input type="text" maxlength="2000" id="linkAddress" name="linkAddress" value="${sysLink.linkAddress}"  class="text" style="width:240px" title="링크주소를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">분류 순서</label></th>
				<td>
                	<select name="linkOrderNew" title="순서를 선택하세요" class="sel_w50">
					<c:forEach var="order" begin="1" end="${sysLink.linkCount+1}" step="1">
						<option value="${order}" <c:if test="${order == sysLink.linkOrder}">selected='selected'</c:if>>${order}</option>
					</c:forEach>
					</select>				
				</td>
			</tr>	
			<tr>
				<th scope="row"><label for="input01">팝업사용</label></th>
				<td><input type="checkbox" name="popupUseGb" value="Y" <c:if test="${sysLink.popupUseGb eq 'Y'}">checked='checked'</c:if> title="팝업사용을 입력하세요"></td>
			</tr>					
			<tr>
				<th scope="row"><label for="input01">약어명</label></th>
				<td><input type="text"  class="text" style="width:240px" id="linkSnm" name="linkSnm" value="${sysLink.linkSnm}" title="약어명" ></td>
			</tr>
			</tbody>
			</table>
			</form:form>
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnOK" onclick="fn_admGen_link_uptBTN_click();">등록</button>
				<button class="btn_style4_3" type="button" id = "btnClose" onclick="fn_admGen_link_iniBTN_click();">초기화</button>
				<button class="btn_style4_2" type="button" id = "btnClose" onclick="self.close();">닫기</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>
	
</body>
</html>

