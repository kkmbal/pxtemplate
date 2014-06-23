<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var pageIndex = '${pSearch.pageIndex}';
	var pageUnit = '${pSearch.pageUnit}';
	var searchKeyword = decodeURIComponent("${fn:replace(pSearch.searchKeyword,'"', '&quot;')}");
	var searchCondition = '${pSearch.searchCondition}';	
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admBoardNotiDelList.js"></script>
</head>

<body>
<form:form commandName="admBoardNotiDelInfoVO" action="${WEB_HOME}/adm/board/getAdmBoardNotiDelList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	
	<div>삭제게시물</div>
	<div>
		<select id="search_gubun">
			<option value="">선택</option>
			<option value="boardName" ${pSearch.searchCondition == 'boardName' ? 'selected' : ''}>게시판명</option>
			<option value="notiTitle" ${pSearch.searchCondition == 'notiTitle' ? 'selected' : ''}>제목</option>
		</select>  
		<input type="text" id="keyword" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}"> 
		<input type="button" value="검색" id="search">
	</div>
	<div>
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
			<td>게시판명</td>
			<td>제목</td>
			<td>파일</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>삭제일</td>
			<td>복원일</td>
		</tr>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiDelList}" varStatus="status">	
			
		<tr>
			<td>${paginationInfo.totalRecordCount - status.count}</td>
			<td><a href="javascript:fnGetBoardView('${result.boardId}','${result.notiId}');">${result.boardName}</a></td>
			<td>${result.notiTitle}</td>
			<td>${result.apndFileCnt}</td>
			<td>${result.notiRegrName}</td>
			<td>${result.regDttm}</td>
			<td>${result.delRegDttm}</td>
			<td></td>
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
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
	</div>
	
</form:form>	
</body>
</html>

