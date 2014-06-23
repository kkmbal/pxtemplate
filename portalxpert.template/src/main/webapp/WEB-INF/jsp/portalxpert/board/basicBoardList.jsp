<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	if('${btnViewYn}' == "X"){
		alert('접근권한이 없습니다.');
		history.back();
	}
	var frameHeight = '${fh}'==''?'700':'${fh}';
	var pageId = 'boardList';
	var makrDispDiv = '${makrDispDiv}';
	var agrmOppUseYn = '${agrmOppUseYn}';//찬성_반대_사용_여부
	var likeUseYn = '${likeUseYn}';//좋아요_사용_여부
	var superAdmin = '${superAdmin}';
	var boardId = '${boardId}';
	var notiList = '${notiList}';
	var pageIndex = '${pageIndex}';
	var pageUnit = '${pageUnit}';
	var boardName = '${boardName}';
	var favoYn = '${favoYn}';
	var isDesc = '${isDesc}';
	var sid = '${sid}';
	var boardKind = '${boardKind}';
	var btnViewYn = '${btnViewYn}';
	var host = '${host}';
	var userId = '${userId}';
	var eamAdminYn = '${eamAdminYn}';
	var nickUseYn = '${nickUseYn}';
	var orderType = '${orderType}';
	var listYn = '${listYn}';
	var boardForm = '${boardForm}';
	var searchKeyword = decodeURIComponent("${fn:replace(searchKeyword,'"', '&quot;')}");
	var searchCondition = '${searchCondition}';	
	var isNotiSurvey = false;
	var regrId = null;
	
	var calList = ${calList};
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardList.js"></script>
</head>

<body>
<form:form commandName="boardSearchVO" action="${WEB_HOME}/board210/getBoardInfoList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	<form:hidden path="notiSeqNo" />
	<form:hidden path="orderType" />
	<form:hidden path="isDesc" />
	
	<div>${boardName}</div>
	
	<c:if test="${boardForm == '040'}">
		<div id='calendar'></div>
	</c:if>
	
	<div>
		<select title="선택" id="search_gubun">
			<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
			<option value="USER_NICK" ${searchCondition == 'USER_NICK' ? 'selected' : ''}>작성자</option>
			<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
		</select>  
		<input type="text" id="keyword" value="${fn:replace(searchKeyword,'"', '&quot;')}"> 
		<input type="button" value="검색" id="search">
	</div>
	<div>
	    <c:if test="${btnViewYn == 'Y'}">
		<input type="button" id="btn_write" value="글쓰기">
		</c:if> 
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
			<td><a href="javascript:fnSearchList('notiTitle')">제목</a></td>
			<td>파일</td>
			<td><a href="javascript:fnSearchList('regrName')">작성자</a></td>
			<td><a href="javascript:fnSearchList('notiReadCnt')">조회</a></td>
			<td><a href="javascript:fnSearchList('regDttm')">등록일</a></td>
		</tr>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			
		<tr>
			<td>${result.oldNoticeSeq}</td>
			<td><a href="javascript:fnGetBoardView('${result.notiId}','${result.pnum}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;')}</a></td>
			<td>
				<c:if test="${result.apndFileCnt > 0}">
				파일
				</c:if>
			</td>
			<td>${result.userName}</td>
			<td>${result.notiReadCnt}</td>
			<td>${result.regDttm}</td>
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