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
					//성공후 실행 스크립트
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

<!--pop_wrap-->
<div class="pop_wrap" style="width:490px;">
	<div class="pop_header">링크분류 수정</div>
	<div class="pop_content ma_bot10">
	
		<!-- popup 본문 -->
		<form:form commandName="admGenLinkVO" name="frmMain" method="post">
		<input type="hidden" name="linkCatId" value="${sysLinkCtlg.linkCatId}">
		<input type="hidden" name="linkCatOrder" value="${sysLinkCtlg.linkCatOrder}">
				
	 	<div class="pop_post clearfix">
			<table class="tbl_form" summary="링크를 분류 및 수정 합니다.">
			<caption>링크분류명,링크분류순서</caption>
			<colgroup>
				<col style="width:25%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input02">링크분류명</label></th>
				<td><input type="text"  class="text" style="width:320px" id="linkCatNm" name="linkCatNm" value="${sysLinkCtlg.linkCatNm}" title="링크분류명" ></td>
			</tr>			
			<tr>
				<th scope="row"><label for="input02">링크 분류 순서</label></th>
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
		</div>
		
		</form:form>
		<!-- popup 본문 -->
	</div>
	
	<div class="pop_footer">
		<!-- 버튼영역 -->
		<div style="text-align:center;">
			<div class="rbox_btns">
				<a href="#" class="btn_set bt_style3" id="btnOK" onclick="fn_admGen_linkCtlg_uptBTN_click();"><span>수정</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose" onclick="fn_admGen_linkCtlg_iniBTN_click();"><span>초기화</span></a>
				<a href="#" class="btn_set bt_style2" id = "btnClose" onclick="fn_admGen_linkCtlg_cloBTN_click();"><span>닫기</span></a>
			</div>
		</div>
		<!-- //버튼영역 -->	     
	 </div> 	
</div>	
<!--//pop_wrap-->

</body>
</html>