<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
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
<!--pop_wrap-->
<div class="pop_wrap" style="width:490px;"><!-- 팝업사이즈 width:800px; height:500px; -->
	<div class="pop_header">코드 등록/수정</div>
	<div class="pop_content ma_bot10">
	<!-- popup 본문 -->
	<form:form commandName="admGenCodeManageVO" name="popForm" method="post">
	 	<div class="pop_post clearfix">
			<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
			<caption>제목</caption>
			<colgroup>
				<col style="width:20%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input02">상위코드명</label></th>
				<td><input type="text"  class="text" style="width:340px" title="상위코드명" maxlength="10" id="cd" name="cd" value="${admGenCodeManage.cd}" readonly="readonly" ></td>
			</tr>			
			<tr>
				<th scope="row"><label for="input02">세부코드</label></th>
				<td><input type="text"  class="text" style="width:340px" title="세부코드" maxlength="10" id="cdSpec" name="cdSpec" value= <c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cdSpec} readonly="readonly"</c:if> ></td>
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
	 	
	 	<%--
			<!--tbl_blue-->                                    
			<div class="tbl_blue">
			    <table summary="코드/등록 , 수정합니다.">
			        <caption>상위코드명,세부코드,코드명,코드설명</caption>
			        <colgroup>
			            <col width="20%">
			            <col>
			         </colgroup> 
			        <tbody>
			            <tr>
			                <th scope="row">상위코드명</th>
<!-- 			                <td><input type="text" title="상위코드명를 입력하세요"></td> -->
								<td><input type="text" id="cd" name="cd" title="상위코드명" size="3" value="${admGenCodeManage.cd}" readonly="readonly"></td>
			            </tr>
			            <tr>
			                <th scope="row">세부코드</th>
<!-- 			                <td><input type="text" title="세부코드를 입력하세요"></td> -->
								<td><input type="text" id="cdSpec" name="cdSpec" title="세부코드" maxlength="3" size="3" value= <c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cdSpec} readonly="readonly"</c:if>></td>
			            </tr>
			            <tr>
			                <th scope="row">코드명</th>
<!-- 			                <td><input type="text" title="코드명를 입력하세요"></td> -->
								<td><input type="text" id="cdNm" name="cdNm" title="코드명" maxlength="50" size="30" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cdNm} </c:if>></td>
			            </tr>
			            <tr>
			                <th scope="row">코드설명</th>
<!-- 			                <td><input type="text" title="코드설명를 적으세요"></td> -->
								<td><input type="text" id="remark" name="remark" title="코드설명" maxlength="2000" size="30" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.remark} </c:if>></td>
			            </tr>
			        </tbody>
			    </table>
			</div>
			<!--//tbl_blue-->
  --%>
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