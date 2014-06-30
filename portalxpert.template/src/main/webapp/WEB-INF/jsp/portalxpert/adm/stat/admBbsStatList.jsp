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
	
<div class="container">	
<div class="header">
	<h1>게시판현황</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>게시판현황</strong></span>
	</div>
</div>
 
<!-- 검색조건 2줄 이상일때 -->
<div class="rbox">
	<span class="rbox_top"></span>
	<div class="rboxInner">
		<ul>
			<li style="display:none;">
			</li>			
			<li class="half">
			    <label for="subtitle">검색조건</label>
				<!-- 셀렉트박스 -->
				<span class="selectN" style="width:70px">
					<span>
						<select id="search_gubun">
							<option value="">선택</option>
							<option value="BOARD_NAME" ${admStatSearchVO.searchCondition == 'BOARD_NAME' ? 'selected' : ''}>게시판명</option>
							<option value="REGR_NAME" ${admStatSearchVO.searchCondition == 'REGR_NAME' ? 'selected' : ''}>관리자</option>
						</select>  
					</span>
				</span>
				<!-- //셀렉트박스 -->
				<input type="text" value="${fn:replace(admStatSearchVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:120px" />
			</li>
			<li class="half">
				<label for="status">상태</label>
				<span class="selectN" style="width:133px">
					<span>
						<select id="stat" name="stat">
							<option value="">선택</option>
							<option value="ALL" ${admStatSearchVO.stat == 'ALL' ? 'selected' : ''}>전체</option>
							<option value="USE" ${admStatSearchVO.stat == 'USE' ? 'selected' : ''}>운영중</option>
							<option value="NOT_USE" ${admStatSearchVO.stat == 'NOT_USE' ? 'selected' : ''}>폐쇄</option>
						</select> 
					</span>
				</span>
			</li>
			<li>
			    <label for="period">기간</label>
				<span class="radiogroup1">
					<input type="radio" name="searchDttm" value="REG" />
					<label for="radio01">생성일</label>
					<input type="radio" name="searchDttm" value="END" />
					<label for="radio02" class="mgrn">폐쇄일</label>	
					<input type="radio" name="searchDttm" checked value="" />
					<label for="radio02" class="mgrn">선택안함</label>	
				</span>			
				<div class="sec_calender">
					<input type="text" class="text" id="sFromDt" name="sFromDt" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
				</div> ~ 
				<div class="sec_calender">
					<input type="text" class="text" id="sToDt" name="sToDt" title="시작날짜를 입력합니다. 예)YYYY.MM.DD">
				</div>
			</li>			
		</ul>
		<div class="rbox_btns">
			<a href="#" class="btn_set bt_style7" id="search"><span>검색</span></a> 
			<a href="#" class="btn_set bt_style6" id="reset"><span>초기화</span></a>
		</div>
	</div>
</div>
<!-- //검색조건 2줄 이상일때 -->

<br/>
<div class="btn_board_top">
	<div class="fl">
		<a href="#" id="createBbs" class="btn_write"><span>게시판생성</span></a> 
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
<col style="width:10%" />
<col style="width:*" />
<col style="width:8%" />
<col style="width:8%" />
<col style="width:11%" />
<col style="width:11%" />
<col style="width:11%" />
<col style="width:11%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span>게시판ID</span></th>
	<th scope="col"><span>게시판명</span></th>
	<th scope="col"><span>종류</span></th>
	<th scope="col"><span>관리자</span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('TOT_CNT')">게시물수</a></span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('READ_CNT')">방문자수</a></span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('REG_DTTM')">생성일</a></span></th>
	<th scope="col" class="e"><span><a href="javascript:fnSearchList('END_DTTM')">폐쇄일</a></span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			<tr>
				<td>${result.seq}</td>
				<td>${result.boardId}</td>
				<td class="tit" title="${result.boardName}"><a href="javascript:fnGetBoardView('${result.boardId}');" class="text_dot">${result.boardName}</a> 
				</td>
				<td>${result.boardKind}(${result.boardForm})</td>
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
			<td colspan="9">검색된 데이터가 없습니다.</td>
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

 --%>	 