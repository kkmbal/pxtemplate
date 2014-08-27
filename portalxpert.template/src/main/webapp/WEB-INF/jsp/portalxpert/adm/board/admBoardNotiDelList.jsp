<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
		<div class="h1">삭제게시물</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">삭제게시물</strong>
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
						<option value="boardName" ${pSearch.searchCondition == 'boardName' ? 'selected' : ''}>게시판명</option>
						<option value="notiTitle" ${pSearch.searchCondition == 'notiTitle' ? 'selected' : ''}>제목</option>
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(pSearch.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" title="검색어를 입력합니다." /> 
			<button class="btn_style7_2" type="button" id="search">검색</button>
		</div>
	</div>
	
	<div class="btn_board_top">
		<div class="fr mt5">
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">게시물수</label>
					<select id="list_cnt" title="게시물수 보기">
						<option value="10">10개보기</option>
						<option value="20">20개보기</option>
						<option value="30">30개보기</option>
					</select>
				</span>
			</span>
		</div>
	</div>
	<table summary="이 표는 번호, 게시판명, 제목, 파일, 작성자, 등록일, 삭제일로 구성된 게시물삭제관리 목록입니다." class="tbl_list">
	<caption>배너관리 목록</caption>
	<colgroup>
	<col style="width:7%" />
	<col style="width:*" />
	<col style="width:*" />
	<col style="width:8%" />
	<col style="width:8%" />
	<col style="width:11%" />
	<col style="width:11%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col" class="f"><div class="col">번호</div></th>
		<th scope="col"><div class="col">게시판명</div></th>
		<th scope="col"><div class="col">제목</div></th>
		<th scope="col"><div class="col">파일</div></th>
		<th scope="col"><div class="col">작성자</div></th>
		<th scope="col"><div class="col">등록일</div></th>
		<th scope="col" class="e"><div class="col">삭제일</div></th>
	</tr>
	</thead>
	<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiDelList}" varStatus="status">		
			<tr>
				<td>${result.seq}</td>
				<td class="tit">${result.boardName}</td>
				<td class="tit"><a href="javascript:fnGetBoardView('${result.boardId}','${result.notiId}');" class="text_dot">${result.notiTitle}</a></td>
				<td><c:if test="${result.apndFileCnt > 0}"><a href="#"><img src="${RES_HOME}/images/ico_fileAttch.png" alt="파일첨부" /></a></c:if></td>
				<td>${result.notiRegrName}</td>
				<td>${result.regDttm}</td>
				<td>${result.delRegDttm}</td>
			</tr>	
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="7">검색된 데이터가 없습니다.</td>
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


