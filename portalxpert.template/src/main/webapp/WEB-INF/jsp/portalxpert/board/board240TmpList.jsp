<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<title></title>
<script type="text/javascript">
	var superAdmin = '${superAdmin}';
	var boardId = '${boardId}';
	var notiList = '${notiList}';
	var pageIndex = '${pageIndex}';
	var pageUnit = '${pageUnit}';
	var favoYn = 'N';
	var fh = '${fh}';
	var searchCondition = '${searchCondition}';
	var searchKeyword = '${searchKeyword}';
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board240TmpList.js"></script>
</head>

<body>
<form:form commandName="boardSearchVO" action="${WEB_HOME}/board240/getTmpBoardInfoList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" />
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" />
	<form:hidden path="notiSeqNo" />
	
	<div>임시게시판</div>
	<div>
		<select title="선택" id="search_gubun">
			<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
			<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
		</select>  
		<input type="text" id="keyword" value="${fn:replace(searchKeyword,'"', '&quot;')}"> 
		<input type="button" value="검색" id="search">
	</div>
	<div>
		<select id="list_cnt">
			<option value="10">10개 보기</option>
			<option value="15">15개 보기</option>
			<option value="30">30개 보기</option>
			<option value="50">50개 보기</option>
		</select>
	</div>
	<div>
		<table>
		<tr>
			<td>번호</td>
			<td>제목</td>
			<td>임시저장일</td>
			<td>종류</td>
		</tr>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			
		<tr>
			<td>${result.tmpNotiSeq}</td>
			<td><a href="javascript:fnGetBoardView('${result.tmpNotiSeq}','${paginationInfo.totalRecordCount - status.count}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;&nbsp;&nbsp;')}</a></td>
 			<td>${result.regDttm}</td>
 			<td>${result.boardName}</td>
		</tr>
		
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="4">검색된 데이터가 없습니다.</td>
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