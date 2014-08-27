<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var pageIndex = '${admSysAuthVO.pageIndex}';
	var pageUnit = '${admSysAuthVO.pageUnit}';
	var searchKeyword = decodeURIComponent("${fn:replace(admSysAuthVO.searchKeyword,'"', '&quot;')}");
	var searchCondition = '${admSysAuthVO.searchCondition}';	
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysAuthList.js"></script>
</head>

<body>

<form:form commandName="admSysAuthVO" action="${WEB_HOME}/adm/sys/getAdmSysAuthList.do" name="listForm" method="post">
	<c:if test="${not empty admSysAuthVO }">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword"/>
	</c:if>
	
<div class="container">
	<div class="header">
		<div class="h1">권한관리</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">권한관리</strong>
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
						<option value="AUTH_NM" ${admSysPsnUserInfoVO.searchCondition == 'AUTH_NM' ? 'selected' : ''}>이름</option>
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(admSysAuthVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" title="검색어를 입력합니다." /> 
			<button class="btn_style7_2" type="button" id="search">검색</button>
		</div>
	</div>
	
	<div class="btn_board_top">
		<div class="fl">
			<button class="btn_write5" type="button" id="createAuth">신규 등록</button>
		</div>
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
	<table summary="이 표는 번호, 권한코드, 권한명, 설명, URL패턴, 등록일자로 구성된 권한관리 목록입니다." class="tbl_list">
	<caption>사용자관리 목록</caption>
	<colgroup>
		<col style="width:7%" />
		<col style="width:15%" />
		<col style="width:*" />
		<col style="width:15%" />
		<col style="width:15%" />
		<col style="width:15%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col" class="f"><div class="col">번호</div></th>
		<th scope="col"><div class="col">권한코드</div></th>
		<th scope="col"><div class="col">권한명</div></th>
		<th scope="col"><div class="col">설명</div></th>
		<th scope="col"><div class="col">URL패턴</div></th>
		<th scope="col" class="e"><div class="col">등록일자</div></th>
	</tr>
	</thead>
	<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">		
			<tr>
				<td>${result.seq}</td>
				<td>${result.authCd}</td>
				<td class="tit" title="${result.authNm}"><a href="javascript:fnGetRegView('${result.authCd}');" class="text_dot">${result.authNm}</a></td>
				<td><p class="text_dot" title="${result.authDesc}">${result.authDesc}</p></td>
				<td><p class="text_dot" title="${result.urlPtn}">${result.urlPtn}</p></td>
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
	
	</tbody>
	</table>
	<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
	</div>
</div>

</form:form>

</body>
</html>		

