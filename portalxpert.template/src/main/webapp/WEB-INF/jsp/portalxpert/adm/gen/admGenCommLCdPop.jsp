<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">s

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
				,maxByte	: 3
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
			<!--tbl_blue-->                                    
			<div class="tbl_blue">
			    <table summary="코드/등록 , 수정합니다.">
			        <caption>코드,코드명,코드설명</caption>
			        <colgroup>
			            <col width="20%">
			            <col>
			         </colgroup> 
			        <tbody>
			            <tr>
			                <th scope="row">코드</th>
<!-- 			                <td><input type="text" title="코드를 입력하세요"></td> -->
			                <td><input type="text" id="cd" name="cd" title="상위코드명" maxlength="3" size="3" value=<c:if test="${admGenCodeManage.pageType == 'U'}"> ${admGenCodeManage.cd} readonly="readonly"</c:if>></td>
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
		<!--btn_area-->         
		<div class="btn_area ma_top15">
			<div class="fl_cen">
				<a href="#" class="btn_basic2" onclick="fn_adm_code_save();">
					<span class="fo_bold btn_text">등록</span>
				</a>
				<a href="#" class="btn_basic2" onclick="popForm.reset();">
					<span class="fo_bold btn_text">초기화</span>
				</a>
				<a href="#" class="btn_basic2" onclick="self.close();">
					<span class="fo_bold btn_text">닫기</span>
				</a>
			</div>
		</div>
		<!--//btn_area-->  
		</div>
		<!-- popup 본문 -->
	</form:form>
	</div>
</div>	
<!--//pop_wrap-->
</body>
</html>