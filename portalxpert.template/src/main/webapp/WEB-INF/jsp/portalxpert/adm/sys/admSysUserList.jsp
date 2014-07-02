<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var pageIndex = '${admSysPsnUserInfoVO.pageIndex}';
	var pageUnit = '${admSysPsnUserInfoVO.pageUnit}';
	var searchKeyword = decodeURIComponent("${fn:replace(admSysPsnUserInfoVO.searchKeyword,'"', '&quot;')}");
	var searchCondition = '${admSysPsnUserInfoVO.searchCondition}';	
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysUserList.js"></script>
</head>

<body>

<form:form commandName="admSysPsnUserInfoVO" action="${WEB_HOME}/adm/sys/getAdmSysUserList.do" name="listForm" method="post">
	<c:if test="${not empty admSysPsnUserInfoVO }">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword"/>
	</c:if>
	
<div class="container">	
<div class="header">
	<h1>사용자관리</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">관리자</a></span>
		<span><strong>사용자관리</strong></span>
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
					<option value="USER_NAME" ${admSysPsnUserInfoVO.searchCondition == 'USER_NAME' ? 'selected' : ''}>이름</option>
				</select>
			</span>
		</span>
		<!-- //셀렉트박스 -->
		<input type="text" value="${fn:replace(admSysPsnUserInfoVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" /> 
		<a href="#" class="btn_set bt_style7" id="search"><span>검색</span></a>
	</div>
</div>
<br/>
<div class="btn_board_top">
	<div class="fl">
		<a href="#" id="createUser" class="btn_write"><span>등록</span></a> 
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
<col style="width:11%" />
<col style="width:11%" />
<col style="width:11%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span>사용자ID</span></th>
	<th scope="col"><span>사용자명</span></th>
	<th scope="col"><span>부서</span></th>
	<th scope="col"><span>담당업무</span></th>
	<th scope="col" class="e"><span>권한</span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			<tr>
				<td>${result.seq}</td>
				<td>${result.userId}</td>
				<td class="tit" title="${result.userName}"><a href="javascript:fnGetRegView('${result.userId}');" class="text_dot">${result.userName}</a> 
				</td>
				<td>${result.deptName}</td>
				<td>${result.userJob}</td>
				<td>${result.authCd}</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="6">검색된 데이터가 없습니다.</td>
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

