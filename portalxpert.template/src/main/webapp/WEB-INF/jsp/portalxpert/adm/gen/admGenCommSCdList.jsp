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
	PortalCommon.popupWindowCenter('./getAdmGenCommonSCodeRegisterPop.do?cd=' + pCD,'타이틀',500,300);
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
	PortalCommon.popupWindowCenter('./getAdmGenCommonSCodeUpdatePop.do?cd=' + pCD + "&cdSpec=" +pCD_SPEC,'pop',495,300);
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
</head>

<body>

<div class="container">
	<div class="header">
		<div class="h1">공통코드관리</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">공통코드관리</strong>
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
					<div class="rbox_top"></div>
					<div class="rboxInner">
						<div class="halfWrap">
							<div class="half" style="width:70%">
							    <label class="lbl">상위코드</label>
								<span class="selectN" style="width:120px">
									<span>
										<select title="검색할 코드 선택" id="cd" name="cd">
											<c:forEach var="result" items="${admGenCodeTypeList}" varStatus="status">
												<option value="${result.cd}" <c:if test="${result.cd==pSearch.cd}"> selected="selected" </c:if> ><c:out value="${result.cd} - ${result.cdNm}"/></option>
											</c:forEach>
										</select> 
									</span>
								</span>
							</div>
						</div>
						<div class="half">
							<label class="lbl">세부코드명</label>
							<input type="text" id="searchKeyword" name="searchKeyword" title="코드 검색 입력" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}" class="text" style="width:554px">
						</div>
			
						<div class="rbox_btns">
							<button type="button" class="btn_style7_2" onclick="fn_adm_code_search();">검색</button> 
							<button type="button" class="btn_style6_3" id="btnReset" onclick="listForm.reset();">초기화</button>
						</div>
					</div>
				</div>
				
				<div class="btn_board_top">
					<div class="fl">
						<button class="btn_style4_2" type="button" onclick="fn_adm_code_add();">추가</button>
						<button class="btn_style4_2" type="button" onclick="fn_adm_code_update();">수정</button>
						<button class="btn_style3_2" type="button" onclick="fn_adm_code_delete();">삭제</button>
					</div>
					<div class="fr mt5">
						<c:out value="${pSearch.currentRecordCount}"/> 건
					</div>					
				</div>
				<table summary="이 표는 세부코드 목록입니다." class="tbl_list">
				<caption>세부코드 목록</caption>
				<colgroup>
				<col style="width:10%" />
				<col style="width:15" />
				<col style="width:*" />
				<col style="width:*" />
				</colgroup>
				<thead>
				<tr>
					<th scope="col" class="f"><div class="col">선택</div></th>
					<th scope="col"><div class="col">세부코드</div></th>
					<th scope="col"><div class="col">세부코드명</div></th>
					<th scope="col" class="e"><div class="col">세부코드설명</div></th>
				</tr>
				</thead>
				<tbody>
			<c:choose>
				<c:when test="${paginationInfo.totalRecordCount > 0}">
					<c:forEach var="result" items="${admGenCodeList}" varStatus="status">		
						<tr>
							<td><input type="radio" title="선택" name="choice" value="${result.cdSpec}"></td>
							<td>${result.cdSpec}</td>
							<td>${result.cdNm}</td>
							<td>${result.remark}</td>
						</tr>	
					</c:forEach>
				</c:when>
				<c:otherwise>
					<tr>
						<td colspan="4">검색된 데이터가 없습니다.</td>
					</tr>
				</c:otherwise>
			</c:choose>		
				
				</tbody>
				</table>
				<div class="paging">
				<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_adm_code_link_page" />
				</div>
			</form:form>

			</div>
			<!--tab02-->
	</div>
	<!--//tab-->	
	
</div>

</body>
</html>	

