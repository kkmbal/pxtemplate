<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var pageIndex = '${admPopVO.pageIndex}';
	var pageUnit = '${admPopVO.pageUnit}';
	var searchKeyword = decodeURIComponent("${fn:replace(admPopVO.searchKeyword,'"', '&quot;')}");
	var searchCondition = '${admPopVO.searchCondition}';	

	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admPopList.js"></script>
</head>

<body>
<form:form commandName="admPopVO" action="${WEB_HOME}/adm/pop/getAdmPopList.do" name="listForm" method="post">
	<c:if test="${not empty admPopVO }">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword"/>
	</c:if>
	
<div class="container">
	<div class="header">
		<div class="h1">팝업관리</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">팝업관리</strong>
		</div>
	</div>
	
	<div class="rbox">
		<span class="rbox_top"></span>
		<div class="rboxInner">
			<!-- 셀렉트박스 -->
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">검색구분</label>
					<select title="" id="search_gubun">
					    <option value="">선택</option>
						<option value="POP_TITLE" ${admPopVO.searchCondition == 'POP_TITLE' ? 'selected' : ''}>이름</option>
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(admPopVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" title="검색어를 입력합니다." /> 
			<button class="btn_style7_2" type="button" id="search">검색</button>
		</div>
	</div>
	
	<div class="btn_board_top">
		<span class="fl">
			<button class="btn_write5" type="button" id="createAuth">신규 등록</button>
		</span>
	</div>
	<table summary="이 표는 번호, 팝업이름, 게시기간, 위치, 상태로 구성된 배너관리 목록입니다." class="tbl_list">
	<caption>배너관리 목록</caption>
	<colgroup>
	<col style="width:7%" />
	<col style="width:*" />
	<col style="width:25%" />
	<col style="width:25%" />
	<col style="width:110" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col" class="f"><div class="col">번호</div></th>
		<th scope="col"><div class="col">배너이름</div></th>
		<th scope="col"><div class="col">게시기간</div></th>
		<th scope="col"><div class="col">위치</div></th>
		<th scope="col" class="e"><div class="col">상태</div></th>
	</tr>
	</thead>
	<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${popList}" varStatus="status">		
			<tr>
				<td>${result.seq}</td>
				<td class="tit"><a href="javascript:fnGetRegView('${result.popId}');"  class="text_dot">${result.popTitle}</a></td>
				<td>${result.expoBgnDttm} ~ ${result.expoEndDttm}</td>
				<td class="tit">${result.parRowPos} > ${result.rowPos}</td>
				<td>				
				<c:if test="${result.useYn == 'Y'}">
					<span>운영중</span>
				</c:if>
				<c:if test="${result.useYn != 'Y'}">
					<span class="txt_point">기간만료</span>
				</c:if>
				</td>
			</tr>	
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="5">검색된 데이터가 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>		
	
	</tbody>
	</table>
	<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
	</div>
</div>

</form:form>
</body>
</html>		

