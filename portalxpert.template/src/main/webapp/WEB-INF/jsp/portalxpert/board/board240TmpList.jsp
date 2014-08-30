<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
	
<div class="container">
	<div class="header">
		<div class="h1">임시저장</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">임시저장</strong>
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
							<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
							<option value="USER_NICK" ${searchCondition == 'USER_NICK' ? 'selected' : ''}>작성자</option>
							<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
						</select>
					</span>
				</span>
				<!-- //셀렉트박스 -->
				<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" title="검색어를 입력합니다." /> 
				<button class="btn_style7_2" type="button" id="search">검색</button>
			</div>
		</div>
		
		<div class="btn_board_top">
			<div class="fr mt5">
				<span class="selectN" style="width:100px">
					<span>
						<label for="selectN_id1" class="hidden">게시물수</label>
						<select title="게시물수 보기" id="list_cnt">
							<option value="10">10개보기</option>
							<option value="20">20개보기</option>
							<option value="30">30개보기</option>
						</select>
					</span>
				</span>
			</div>
		</div>
		<!-- 테이블 글보기스타일 -->
		<table summary="게시판목록" class="tbl_list">
		<caption>게시판 A</caption>
		<colgroup>
		<col style="width:7%" />
		<col style="width:*" />
		<col style="width:11%" />
		<col style="width:20%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col" class="f"><div class="col">번호</div></th>
			<th scope="col"><div class="col">제목</div></th>
			<th scope="col"><div class="col">임시저장일</div></th>
			<th scope="col" class="e"><div class="col">종류</div></th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${paginationInfo.totalRecordCount > 0}">
				<c:forEach var="result" items="${notiList}" varStatus="status">		
					<tr>
						<td>${result.tmpNotiSeq}</td>
						<c:choose>
						<c:when test="${empty result.notiTitle}">
							<td class="tit"><a href="javascript:fnGetBoardView('${result.tmpNotiSeq}','${paginationInfo.totalRecordCount - status.count}');">제목없음</a></td>
						</c:when>
						<c:otherwise>
							<td class="tit"><a href="javascript:fnGetBoardView('${result.tmpNotiSeq}','${paginationInfo.totalRecordCount - status.count}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;&nbsp;&nbsp;')}</a></td>
						</c:otherwise>
						</c:choose>
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
		</tbody>
		</table>	
	
	<div class="paging">
		<c:if test="${boardForm != '040'}">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
		</c:if>
	</div>

</div>		 
	 
</form:form>
</body>
</html>		

<%--

<div class="container">	
<div class="header">
	<h1>임시저장</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">커뮤니티</a></span>
		<span><strong>임시저장</strong></span>
	</div>
</div>


<div class="rbox">
	<span class="rbox_top"></span>
	<div class="rboxInner">
		<!-- 셀렉트박스 -->
		<span class="selectN" style="width:100px">
			<span>
				<select title="" id="search_gubun">
					<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
					<option value="USER_NICK" ${searchCondition == 'USER_NICK' ? 'selected' : ''}>작성자</option>
					<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
				</select>
			</span>
		</span>
		<!-- //셀렉트박스 -->
		<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" /> 
		<a href="#" class="btn_set bt_style7" id="search"><span>검색</span></a>
	</div>
</div>
<br/>
<div class="btn_board_top">
	<div class="fl">
	<c:if test="${btnViewYn == 'Y'}">
		<a href="#" id="btn_write" class="btn_write"><span>글쓰기</span></a> 
	</c:if>
	</div>
	<div class="fr">
	    <!-- 
		<span class="selectN" style="width:100px">
			<span>
				<select id="select13" title="게시물 정렬방법" onchange="fnSearchList(this.value)">
					<option value="">선택</option>
					<option value="notiTitle" ${orderType == 'notiTitle' ? 'selected' : ''}>제목</option>
					<option value="notiReadCnt" ${orderType == 'notiReadCnt' ? 'selected' : ''}>조회수 순</option>
					<option value="regDttm" ${orderType == 'regDttm' ? 'selected' : ''}>등록일 순</option>
				</select>
			</span>
		</span>
		 -->
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
<col style="width:11%" />
<col style="width:11%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span>제목</span></th>
	<th scope="col"><span>임시저장일</span></th>
	<th scope="col" class="e"><span>종류</span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			<tr>
				<td>${result.tmpNotiSeq}</td>
				<td class="tit"><a href="javascript:fnGetBoardView('${result.tmpNotiSeq}','${paginationInfo.totalRecordCount - status.count}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;&nbsp;&nbsp;')}</a></td>
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
</tbody>
</table>

<div class="paging">
<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
</div>
</div>	

 --%>	 