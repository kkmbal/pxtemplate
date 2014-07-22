<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
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
	<h1>팝업관리</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>팝업관리</strong></span>
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
					<option value="BNR_TITLE" ${admPopVO.searchCondition == 'BNR_TITLE' ? 'selected' : ''}>이름</option>
				</select>
			</span>
		</span>
		<!-- //셀렉트박스 -->
		<input type="text" value="${fn:replace(admPopVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" /> 
		<a href="#" class="btn_set bt_style7" id="search"><span>검색</span></a>
	</div>
</div>
<br/>
<div class="btn_board_top">
	<div class="fl">
		<a href="#" id="createAuth" class="btn_write"><span>등록</span></a> 
	</div>	
</div>

<table summary="" class="tbl_list">
<caption></caption>
<colgroup>
<col style="width:7%" />
<col style="width:25%" />
<col style="width:*" />
<col style="width:20%" />
<col style="width:15%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span>팝업이름</span></th>
	<th scope="col"><span>게시기간</span></th>
	<th scope="col"><span>위치</span></th>
	<th scope="col" class="e"><span>상태</span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${popList}" varStatus="status">	
			<tr>
				<td>${result.seq}</td>
				<td class="tit" title="${result.popTitle}"><a href="javascript:fnGetRegView('${result.popId}');" class="text_dot">${result.popTitle}</a> 
				</td>
				<td><p class="text_dot" title="${result.expoBgnDttm} ~ ${result.expoEndDttm}">${result.expoBgnDttm} ~ ${result.expoEndDttm}</p></td>
				<td ><p class="text_dot" title="${result.parRowPos}>${result.rowPos}">${result.parRowPos}>${result.rowPos}</p></td>
				<td>
				<c:if test="${result.useYn == 'Y'}">
					운영중
				</c:if>
				<c:if test="${result.useYn != 'Y'}">
					기간만료
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

