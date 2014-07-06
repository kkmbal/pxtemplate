<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 상위코드 추가 및 수정된 내용 저장 */
var fn_adm_code_save = function(){
	var url = "";
	if('${admGenCodeManage.pageType}' == 'U'){
		url = "./updateAdmGenCommonLCode.do?format=json";
	}else if('${admGenCodeManage.pageType}' == 'I'){
		url = "./insertAdmGenCommonLCode.do?format=json";
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
			cd : {
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

<body>
<!--pop_wrap-->
<div class="pop_wrap" style="width:490px;"><!-- 팝업사이즈 width:800px; height:500px; -->
	<div class="pop_header">코드 등록/수정</div>
	<div class="pop_content ma_bot10">
	<!-- popup 본문 -->
	<form:form commandName="admGenCodeManageVO" name="popForm" id="popForm" method="post">
	 	<div class="pop_post clearfix">
			<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
			<caption>제목</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input02">코드</label></th>
				<td><input type="text"  class="text" style="width:340px" title="코드" maxlength="10" id="cd" name="cd" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cd} readonly="readonly"</c:if> ></td>
			</tr>			
			<tr>
				<th scope="row"><label for="input02">코드명</label></th>
				<td><input type="text"  class="text" style="width:340px" title="코드명" maxlength="50" id="cdNm" name="cdNm" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cdNm} </c:if> ></td>
			</tr>			
			<tr>
				<th scope="row"><label for="input02">코드설명</label></th>
				<td><input type="text"  class="text" style="width:340px" title="코드설명" maxlength="2000" id="remark" name="remark" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.remark} </c:if> ></td>
			</tr>			
			</tbody>
			</table>	 	
		</div>
		<!-- popup 본문 -->
	</form:form>
	</div>
	
	<!-- popup 본문 -->
	<div class="pop_footer">
		<!-- 버튼영역 -->
		<div style="text-align:center;">
			<div class="rbox_btns">
				<a href="#" class="btn_set bt_style3" id="btnOK" onclick="fn_adm_code_save();"><span>등록</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose" onclick="popForm.reset();"><span>초기화</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose" onclick="self.close();"><span>닫기</span></a>
			</div>
		</div>
		<!-- //버튼영역 -->	     
	 </div>  
	 	
</div>	
<!--//pop_wrap-->
</body>
</html>