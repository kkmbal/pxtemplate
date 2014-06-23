<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var pageIndex = '${admStatSearchVO.pageIndex}';
	var pageUnit = '${admStatSearchVO.pageUnit}';
	var searchKeyword = decodeURIComponent("${fn:replace(admStatSearchVO.searchKeyword,'"', '&quot;')}");
	var searchCondition = '${admStatSearchVO.searchCondition}';	
	var orderType = '${admStatSearchVO.orderType}';
	var isDesc = '${!admStatSearchVO.isDesc}';
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admBbsStatList.js"></script>
</head>

<body>
<form:form commandName="admStatSearchVO" action="${WEB_HOME}/adm/stat/getAdmBbsStatList.do" name="listForm" method="post">
	<c:if test="${not empty admStatSearchVO }">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	<form:hidden path="orderType" />
	<form:hidden path="isDesc" />
	</c:if>
	
	<div>게시판현황</div>
	<div>
		<select id="search_gubun">
			<option value="">선택</option>
			<option value="BOARD_NAME" ${admStatSearchVO.searchCondition == 'BOARD_NAME' ? 'selected' : ''}>게시판명</option>
			<option value="REGR_NAME" ${admStatSearchVO.searchCondition == 'REGR_NAME' ? 'selected' : ''}>관리자</option>
		</select>  
		<input type="text" id="keyword" value="${fn:replace(admStatSearchVO.searchKeyword,'"', '&quot;')}"> 
		<input type="button" value="검색" id="search">
	</div>
	<div>
		<select id="stat" name="stat">
			<option value="">선택</option>
			<option value="ALL" ${admStatSearchVO.stat == 'ALL' ? 'selected' : ''}>전체</option>
			<option value="USE" ${admStatSearchVO.stat == 'USE' ? 'selected' : ''}>운영중</option>
			<option value="NOT_USE" ${admStatSearchVO.stat == 'NOT_USE' ? 'selected' : ''}>폐쇄</option>
		</select>  
	</div>	
	<div>
		<input type="radio" name="searchDttm" value="REG">생성일 <input type="radio" name="searchDttm" value="END">폐쇄일 <input type="radio" name="searchDttm" checked value="">선택안함
		<input id="sFromDt" name="sFromDt"  type="text">~ 
		<input id="sToDt" name="sToDt" type="text">		
	</div>	
	<div>
		<input type="button" id="createBbs" value="게시판생성">
		<select id="list_cnt">
			<option value="10">10개 보기</option>
			<option value="20">20개 보기</option>
			<option value="30">30개 보기</option>
		</select>
	</div>
	<div>
		<table>
		<tr>
			<td>번호</td>
			<td>게시판ID</td>
			<td>게시판명</td>
			<td>종류</td>
			<td>관리자</td>
			<td><a href="javascript:fnSearchList('TOT_CNT')">게시물수</a></td>
			<td><a href="javascript:fnSearchList('READ_CNT')">방문자수</a></td>
			<td><a href="javascript:fnSearchList('REG_DTTM')">생성일</a></td>
			<td><a href="javascript:fnSearchList('END_DTTM')">폐쇄일</a></td>
		</tr>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			
		<tr>
			<td>${result.seq}</td>
			<td>${result.boardId}</td>
			<td><a href="javascript:fnGetBoardView('${result.boardId}');">${result.boardName}</a></td>
			<td>${result.boardKind}</td>
			<td>${result.regrName}</td>
			<td>${result.totCnt}</td>
			<td>${result.readCnt}</td>
			<td>${result.regDttm}</td>
			<td>${result.endDttm}</td>
		</tr>
		
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="6">검색된 데이터가 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>		
		</table>
	</div>
	<div>
		<c:if test="${not empty paginationInfo }">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
		</c:if>
	</div>
	
</form:form>	
</body>
</html>			 