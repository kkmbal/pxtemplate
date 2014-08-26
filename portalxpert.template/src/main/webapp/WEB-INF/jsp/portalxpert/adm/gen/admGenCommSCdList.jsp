<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
/* 세부코드 추가*/
var fn_adm_code_add = function(){
	var pCD = $("#cd option:selected").val();
	PortalCommon.popupWindowCenter('./getAdmGenCommonSCodeRegisterPop.do?cd=' + pCD,'타이틀',500,250);
};

/* 세부코드 수정 */
var fn_adm_code_update = function(){
	var pCD = $("#cd option:selected").val();
	var pCD_SPEC = "";
	var checkCnt = 0;
    var choice   = document.getElementsByName("choice");
   

    // 선택 항목 수 계산
    for (var i = 0; i < choice.length; i++) {
        if(choice[i].checked) {
        	pCD_SPEC = choice[i].value;
            checkCnt += 1;
        }
    }
    if (checkCnt == 0) {
        alert("코드를 선택하십시오.");
        return;
    }
	PortalCommon.popupWindowCenter('./getAdmGenCommonSCodeUpdatePop.do?cd=' + pCD + "&cdSpec=" +pCD_SPEC,'pop',495,245);
};

/* 세부 코드 삭제 */
var fn_adm_code_delete = function(){
	var checkCnt = 0;
    var choice   = document.getElementsByName("choice");

    // 선택 항목 수 계산
    for (var i = 0; i < choice.length; i++) {
        if(choice[i].checked) {
        	$("#cdSpec").val(choice[i].value);
            checkCnt += 1;
        }
    }

    if (checkCnt == 0) {
        alert("코드를 선택하십시오.");
        return;
    }
	
    if(confirm("삭제하시겠습니까?")){
    	PortalCommon.getJson({
    		url: "./deleteAdmGenCommonSCode.do?format=json",
    		data: $("form[name=listForm]").serialize(),
    		success :function(data){
    			if(data.jsonResult.success === true){
    				//성공후 실행 스크립트
//     				location.reload();
    				fn_adm_code_search();
    			}else{
    				//실패후 실행 스크립트
    			}
    		}
    	});
    }
	
	
};

/* 세부코드 검색  */
var fn_adm_code_search = function(){
	if(!$("form[name=listForm]").valid()) return false;
	
	document.listForm.target = "_self";
	$('form[name=listForm]').attr('action','./getAdmGenCommonSCodeList.do').submit();
};

/* pagination 페이지 링크 function */
var fn_adm_code_link_page = function(pageNo){
	document.listForm.pageIndex.value = pageNo;
	document.listForm.action = "./getAdmGenCommonSCodeList.do";
   	document.listForm.submit(); 
};

/* 탭이동 */
var fnMenuToggle = function(idx)
{
	if (idx == '1')
	{
		location.href = "./getAdmGenCommonLCodeList.do";
	}
	else if (idx == '2')
	{
		location.href = "./getAdmGenCommonSCodeList.do";
	}
	
};

$("document").ready(function(){
	// validate
	$("form[name=listForm]").validate({
		ignoreTitle:true,
		
		rules : {
			//모든특수문자
			searchKeyword		 : {
				invalidChars : "\\/&!'"
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
	
	$("#cd").change(function(){
		fn_adm_code_search();
	});
	
});

</script>

<body>
<div class="container">	
<div class="header">
	<h1>공통코드관리</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>공통코드관리</strong></span>
	</div>
</div>
	
	
	<!--tab-->
	<div class="tab_1">
		<!-- 제목부분 -->
		<ul class="clearfix">
			<!--tab01-->
			<li><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(1)" class="tab_title" ><span>상위코드</span></a></li>
			<li class="on"><a style="cursor:pointer;" onclick="javascript:fnMenuToggle(2)" class="tab_title" ><span>세부코드</span></a></li>
		</ul>
		<!-- //제목부분 -->
		<!-- 내용부분 -->
			<div class="tab_post">
				<form:form commandName="admGenCodeManageVO" name="listForm" method="post">
				<input type="hidden" name="cdSpec" id="cdSpec">

				<div class="rbox">
					<span class="rbox_top"></span>
					<div class="rboxInner">
						<ul>
							<li>
								<label for="subtitle">상위코드</label>
								<select title="검색할 코드 선택" id="cd" name="cd">
									<c:forEach var="result" items="${admGenCodeTypeList}" varStatus="status">
										<option value="${result.cd}" <c:if test="${result.cd==pSearch.cd}"> selected="selected" </c:if> ><c:out value="${result.cd} - ${result.cdNm}"/></option>
									</c:forEach>
								</select>
							</li>				
							<li>
								<label for="subtitle">세부코드명</label>
								<input type="text" id="searchKeyword" name="searchKeyword" title="코드 검색 입력" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}" class="text" style="width:554px">
							</li>				
						</ul>
						<div class="rbox_btns">
							<a href="#" class="btn_set bt_style7" onclick="fn_adm_code_search();"><span>검색</span></a> 
<!-- 							<a href="#" class="btn_set bt_style6"><span>초기화</span></a> -->
						</div>		
					</div>
				</div>			
				<br/>	
			
				<!--  버튼영역 -->
				<div class="btn_board_top">
					<div class="fl">
						<a href="#" onclick="fn_adm_code_add();" class="btn_set bt_style2 btn_modify"><span>추가</span></a>
						<a href="#" onclick="fn_adm_code_update();" class="btn_set bt_style2 btn_delete"><span>수정</span></a>
						<a href="#" onclick="fn_adm_code_delete();" class="btn_set bt_style2 btn_print"><span>삭제</span></a>
					</div>
				</div>
				<!-- // 버튼영역 -->
							
				<!-- page unit -->
				<div class="select te_right" >
					<c:out value="${pSearch.currentRecordCount}"/> 건
			    </div>
			    <!-- //paga unit -->
				<!--list-->
				<div class="tbl_list te_center">
					<table summary="세부코드 목록">
						<caption>세부코드 목록</caption>
						<colgroup>
							<col width="10%">
							<col width="15%">
							<col>
							<col>
						</colgroup>
						<thead>
							<tr>
								<th scope="col">선택</th>
								<th scope="col">세부코드</th>
								<th scope="col">세부 코드명</th>
								<th scope="col">세부 코드 설명</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="result" items="${admGenCodeList}" varStatus="status">
								<tr>
									<td><input type="radio" title="선택" name="choice" value="${result.cdSpec}"></td>
									<td><c:out value="${result.cdSpec}"/></td>
									<td><c:out value="${result.cdNm}"/></td>
									<td class="te_left"><c:out value="${result.remark}"/></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				
				<!--//list-->
				<!--paginate-->
				<div id="paging" class="paging">
					<ui:pagination paginationInfo = "${paginationInfo}"
							   type="image"
							   jsFunction="fn_adm_code_link_page"
							   />
					<form:hidden path="pageIndex" />
				</div>
				<!--//paginate-->
				</form:form>
				
			</div>
			<!--tab02-->
	</div>
	<!--//tab-->
	
	</div>
	</body>
</html>	