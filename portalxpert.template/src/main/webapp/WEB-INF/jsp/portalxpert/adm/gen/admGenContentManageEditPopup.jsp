<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

/* 콘텐츠 입력 */
var fn_admGen_contMag_insert = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
    
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/insertAdmGenContentManage.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					//성공후 실행 스크립트
					window.opener.location.reload();
					window.close();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

/* 링크분류 수정 */
var fn_admGen_contMag_update = function(){
	
	if(!$("form[name=frmMain]").valid()) return;
	    
	PortalCommon.getJson({
			url: "${WEB_HOME}/adm/gen/updateAdmGenContentManage.do?format=json",
		    data: $("form[name=frmMain]").serialize(),
			success :function(data){
				if(data.jsonResult.success === true){
					//성공후 실행 스크립트
					window.opener.location.reload();
					window.close();
				}else{
					//실패후 실행 스크립트
				}
			}
	 });
};

$("document").ready(function() {
	$("form[name=frmMain]").validate({
		onfocusout: false
	    ,onkeyup: false
	    ,onclick: false
	    ,ignoreTitle:true
	    
		,rules : {
			cntsName : {
				required : true
				,maxlength : 25
			}
			,cntsLink : {
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

var fn_admGen_contMag_iniBTN_click = function(){
	$("form[name=frmMain]").reset();
};

var fn_admGen_contMag_cloBTN_click = function(){
	window.close();
};

</script>

</head>
<body>

<!--pop_wrap-->
<div class="pop_wrap" style="width:700px;">
	<div class="pop_header">개인영역 콘텐츠 등록/수정</div>
	<div class="pop_content ma_bot10">
		
		<!-- popup 본문 -->
	 	<form:form commandName="admGenContentManageVO" name="frmMain" method="post">
		<input type="hidden" id="cntsId" name="cntsId" value="${genContent.cntsId}">
	 	
	 	<div class="pop_post clearfix">
			<!--tbl_blue-->                                    
			<div class="tbl_blue">
			    <table summary="개인영역콘텐츠를 등록/수정 합니다.">
			        <caption>개인영역콘텐츠</caption>
			        <colgroup>
			            <col width="170">
			            <col>
			         </colgroup> 
			        <tbody>
			            <tr>
			                <th scope="row"><span class="bl_red"><!--필수--></span>콘텐츠명</th>
			                <td><input type="text" id="cntsName" name="cntsName" value="${genContent.cntsName}" title="콘텐츠명" class="inp_form"></td>
			            </tr>
			            <tr>
			                <th scope="row"><span class="bl_red"><!--필수--></span>콘텐츠 링크 및 함수명</th>
			                <td><input type="text" id="cntsLink" name="cntsLink" value="${genContent.cntsLink}" title="콘텐츠 링크" class="inp_all"></td>
			            </tr>
			            <tr>
			                <th scope="row"><span class="bl_red"><!--필수--></span>콘텐츠 타입</th>
			                <td>
			                	<input type="radio" name="cntsTp" value="M" <c:if test="${'M' eq genContent.cntsTp}">checked</c:if> checked><label for="con_kind01">해야할일</label>
			                	<input type="radio" name="cntsTp" value="P" <c:if test="${'P' eq genContent.cntsTp}">checked</c:if>><label for="con_kind01">포틀릿</label>
			                	<input type="radio" name="cntsTp" value="U" <c:if test="${'U' eq genContent.cntsTp}">checked</c:if>><label for="con_kind01">유용한툴</label>
			                	<input type="radio" name="cntsTp" value="TAB" <c:if test="${'TAB' eq genContent.cntsTp}">checked</c:if>><label for="con_kind01">최근게시물</label>
			                	<input type="radio" name="cntsTp" value="RLT" <c:if test="${'RLT' eq genContent.cntsTp}">checked</c:if>><label for="con_kind01">개인홈공지</label>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">새로고침 시간(분)</th>
			                <td><input type="text" id="msgRefreshTm" name="msgRefreshTm" value="${genContent.msgRefreshTm}" title="새로고침 시간(분)" class="inp_w100">해야할일만 입력, 사용안함(null,0)</td>
			            </tr>
			            <tr>
			                <th scope="row">아이콘명</th>
			                <td><input type="text" id="iconName" name="iconName" value="${genContent.iconName}" title="아이콘명" class="inp_w100">해야할일, 유용한툴만 입력</td>
			            </tr>
			            <tr>
			                <th scope="row">연계키</th>
			                <td><input type="text" id="relaKey" name="relaKey" value="${genContent.relaKey}" title="관계키" class="inp_w100">해야할일만 입력(업무관리시스템 연결 시)</td>
			            </tr>
			            <tr>
			                <th scope="row">관리페이지</th>
			                <td><input type="text" id="adminLink" name="adminLink" value="${genContent.adminLink}" title="관리페이지" class="inp_all"></td>
			            </tr>
			            <tr>
			                <th scope="row">사용여부</th>
			                <td>
			                	<select name="useYn" title="사용여부">
								<option value="Y" <c:if test="${'Y' eq genContent.useYn}">selected='selected'</c:if>>사용</option>
								<option value="N" <c:if test="${'N' eq genContent.useYn}">selected='selected'</c:if>>미사용</option>
								</select>
			                </td>
			            </tr>
			            <c:if test="${genContent.pageType eq 'U'}">
			            <tr>
			                <th scope="row">삭제여부</th>
			                <td>
			                	<select name="delYn" title="사용여부">
								<option value="Y" <c:if test="${'Y' eq genContent.delYn}">selected='selected'</c:if>>삭제</option>
								<option value="N" <c:if test="${'N' eq genContent.delYn}">selected='selected'</c:if>>추가</option>
								</select>
			                </td>
			            </tr>
			            <tr>
			                <th scope="row">장애여부</th>
			                <td>
			                	<select name="sysErrYn" title="장애여부">
								<option value="Y" <c:if test="${'Y' eq genContent.sysErrYn}">selected='selected'</c:if>>사용</option>
								<option value="N" <c:if test="${'N' eq genContent.sysErrYn || '' eq genContent.sysErrYn}">selected='selected'</c:if>>미사용</option>
								</select> 외부시스템 연결 장애시 사용으로 선택
			                </td>
			            </tr>
			            </c:if>
			        </tbody>
			    </table>
			</div>
			<!--//tbl_blue-->
		<!--btn_area-->         
		<div class="btn_area ma_top15">
			<div class="fl_cen">
				<a href="#" onclick="fn_admGen_contMag_iniBTN_click();" class="btn_basic2">
					<span class="fo_bold btn_text">초기화</span>
				</a>
				
				<c:if test="${genContent.pageType eq 'I'}">
				<a href="#" onclick="fn_admGen_contMag_insert();" class="btn_basic2">
					<span class="fo_bold btn_text">등록</span>
				</a>
				</c:if>
				
				<c:if test="${genContent.pageType eq 'U'}">
				<a href="#" onclick="fn_admGen_contMag_update();" class="btn_basic2">
					<span class="fo_bold btn_text">수정</span>
				</a>
				</c:if>
				
				<a href="#" onclick="fn_admGen_contMag_cloBTN_click();" class="btn_basic2">
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
