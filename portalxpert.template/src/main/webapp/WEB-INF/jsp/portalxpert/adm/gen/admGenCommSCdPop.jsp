<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

var fn_adm_code_save = function(){

	var url = "";
	if('${admGenCodeManage.pageType}' == 'U'){
		url = "./updateAdmGenCommonSCode.do?format=json";
	}else if('${admGenCodeManage.pageType}' == 'I'){
		url = "./insertAdmGenCommonSCode.do?format=json";
	}
	if(!$("form[name=popForm]").valid()) return;
	
	PortalCommon.getJson({
		url: url,
	    data: $("form[name=popForm]").serialize(),
		success :function(data){
			if(data.jsonResult.success === true){
				//성공후 실행 스크립트
				opener.location.reload();
				self.close(); 
			}else{
				//실패후 실행 스크립트
			}
		}
	});

};

$("document").ready(function(){
	//필수 초기화 메소드 호출
	initValidform("popForm");
	
	// validate
	$("form[name=popForm]").validate({
		ignoreTitle:true
		
		,rules : {
			cdSpec : {
				required : true
				,maxByte	: 10
				,invalidKor	: true
			}
			,cdNm : {
				required : true
				,maxByte  : 50
			}
			,remark : {
				maxByte  : 2000
			}
		}
		,showErrors:function(errorMap, errorList){
			if(errorList.length == 0) return;
			var caption = $(errorList[0].element).attr('title') || $(errorList[0].element).attr('alt') || $(errorList[0].element).attr('name');
			alert(caption + " " + errorList[0].message);
		}
		,onfocusout	: false
		,onkeyup	: false
		,onclick	: false
	});

});
</script>

</head>

<body>
<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">코드 등록/수정</h1>
		</div>
		<div class="contents">
			<form:form commandName="admGenCodeManageVO" name="popForm" id="popForm" method="post">
			<table class="tbl_form" summary="이 표는 코드 등록/수정정보 표입니다.">
			<caption>코드 등록/수정</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">상위코드명</label></th>
				<td><input type="text" maxlength="10" id="cd" name="cd" value="${admGenCodeManage.cd}" readonly="readonly" class="text" style="width:240px" title="상위코드명를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">세부코드</label></th>
				<td><input type="text" maxlength="10" id="cdSpec" name="cdSpec" <c:if test="${admGenCodeManage.pageType == 'U'}">value="${admGenCodeManage.cdSpec}" readonly="readonly"</c:if> class="text" style="width:240px" title="세부코드를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">코드명</label></th>
				<td><input type="text" maxlength="50" id="cdNm" name="cdNm" <c:if test="${admGenCodeManage.pageType == 'U'}">value="${admGenCodeManage.cdNm}" </c:if> class="text" style="width:240px" title="코드명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">코드설명</label></th>
				<td><input type="text" maxlength="2000" id="remark" name="remark" <c:if test="${admGenCodeManage.pageType == 'U'}">value="${admGenCodeManage.remark}"</c:if>  class="text" style="width:240px" title="코드설명을 입력합니다." /></td>
			</tr>
			</tbody>
			</table>
			</form:form>
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnOK" onclick="fn_adm_code_save();">등록</button>
				<button class="btn_style4_3" type="button" id = "btnClose" onclick="popForm.reset();">초기화</button>
				<button class="btn_style4_2" type="button" id = "btnClose" onclick="self.close();">닫기</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>
</body>
</html>

