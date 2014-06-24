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
	
<div class="container">	
<div class="header">
	<h1>삭제게시물</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>삭제게시물</strong></span>
	</div>
</div>
 
<div class="rbox">
	<span class="rbox_top"></span>
	<div class="rboxInner">
		<!-- 셀렉트박스 -->
		<span class="selectN" style="width:100px">
			<span>
				<select title="" id="search_gubun">
					<option value="">선택</option>
					<option value="boardName" ${pSearch.searchCondition == 'boardName' ? 'selected' : ''}>게시판명</option>
					<option value="notiTitle" ${pSearch.searchCondition == 'notiTitle' ? 'selected' : ''}>제목</option>
				</select>
			</span>
		</span>
		<!-- //셀렉트박스 -->
		<input type="text" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" /> 
		<a href="#" class="btn_set bt_style7" id="search"><span>검색</span></a>
	</div>
</div>

<br/>
<div class="btn_board_top">
	<div class="fl">
	</div>
	<div class="fr">
		<span class="selectN" style="width:80px">
			<span>
				<select id="list_cnt" title="게시물수 보기">
					<option value="10">10개보기</option>
					<option value="20">20개보기</option>
					<option value="30">30개보기</option>
				</select>
			</span>
		</span>
	</div>
</div>

<table summary="" class="tbl_list">
<caption></caption>
<colgroup>
<col style="width:7%" />
<col style="width:*" />
<col style="width:*" />
<col style="width:8%" />
<col style="width:8%" />
<col style="width:11%" />
<col style="width:11%" />
<col style="width:11%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span>게시판명</span></th>
	<th scope="col"><span>제목</span></th>
	<th scope="col"><span>파일</span></th>
	<th scope="col"><span>작성자</span></th>
	<th scope="col"><span>등록일</span></th>
	<th scope="col"><span>삭제일</span></th>
	<th scope="col" class="e"><span>복원일</span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiDelList}" varStatus="status">	
			<tr>
				<td>${paginationInfo.totalRecordCount - status.count}</td>
				<td class="tit"><a href="javascript:fnGetBoardView('${result.boardId}','${result.notiId}');">${result.boardName}</a></td>
				<td class="tit">${result.notiTitle}</td>
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
			<td colspan="8">검색된 데이터가 없습니다.</td>
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


<%--

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

--%>