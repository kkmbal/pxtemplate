<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
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

<!--pop_wrap-->
<div class="pop_wrap" style="width:490px;">
	<div class="pop_header">링크수정</div>
	<div class="pop_content ma_bot10">
		<!-- popup 본문 -->
		<form:form commandName="admGenLinkVO" name="frmMain" method="post">
		<input type="hidden" name="linkCatId" value="${sysLink.linkCatId}">
		<input type="hidden" name="linkCd" value="${sysLink.linkCd}">
		<input type="hidden" name="linkOrder" value="${sysLink.linkOrder}">
		
	 	<div class="pop_post clearfix">
			<!--tbl_blue-->                                    
			<div class="tbl_blue">
			    <table summary="링크를 분류 및 수정 합니다.">
			        <caption>링크분류명,링크명,링크주소,링크순서,팝업사용여부</caption>
			        <colgroup>
			            <col width="30%">
			            <col>
			         </colgroup> 
			        <tbody>
			            <tr>
			                <th scope="row"><span class="bl_red"><!--필수--></span>링크분류명</th>
			                <td>${sysLink.linkCatNm}</td>
			            </tr>
			            <tr>
			                <th scope="row">링크명</th>
			                <td><input type="text" id="linkNm" name="linkNm" value="${sysLink.linkNm}" title="링크명" class="inp_all"></td>
			            </tr>
			            <tr>
			                <th scope="row"><span class="bl_red"><!--필수--></span>링크주소</th>
			                <td><input type="text" id="linkAddress" name="linkAddress" value="${sysLink.linkAddress}" title="링크주소" class="inp_all"></td>
			            </tr>
			            <tr>
			                <th scope="row">링크 순서</th>
			                <td>
			                	<select name="linkOrderNew" title="순서를 선택하세요" class="sel_w50">
								<c:forEach var="order" begin="1" end="${sysLink.linkCount+1}" step="1">
									<option value="${order}" <c:if test="${order == sysLink.linkOrder}">selected='selected'</c:if>>${order}</option>
								</c:forEach>
								</select>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">팝업사용</th>
			                <td><input type="checkbox" name="popupUseGb" value="Y" <c:if test="${sysLink.popupUseGb eq 'Y'}">checked='checked'</c:if> title="링크를 분류할 이름을 입력하세요"></td>
			            </tr>
			            <tr>
			                <th scope="row">약어명</th>
			                <td><input type="text" id="linkSnm" name="linkSnm" value="${sysLink.linkSnm}" title="약어명" class="inp_all"></td>
			            </tr>
			        </tbody>
			    </table>
			</div>
			<!--//tbl_blue-->
		<!--btn_area-->         
		<div class="btn_area ma_top15">
			<div class="fl_cen">
				<a href="#" onclick="fn_admGen_link_uptBTN_click();" class="btn_basic2">
					<span class="fo_bold btn_text">수정</span>
				</a>
				<a href="#" onclick="fn_admGen_link_iniBTN_click();" class="btn_basic2">
					<span class="fo_bold btn_text">초기화</span>
				</a>
				<a href="#" onclick="fn_admGen_link_cloBTN_click();" class="btn_basic2">
					<span class="fo_bold btn_text">닫기</span>
				</a>
			</div>
		</div>
		<!--//btn_area-->  
		</div>
		</form:form>
		<!-- popup 본문 -->
	</div>
</div>	
<!--//pop_wrap-->
	
</body>
</html>