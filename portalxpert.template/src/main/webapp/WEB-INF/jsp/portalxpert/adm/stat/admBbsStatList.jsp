<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
		<div class="h1">게시판 현황</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">게시판관리</a>
			<strong class="str">게시판현황</strong>
		</div>
	</div>
	<div class="rbox">
		<div class="rbox_top"></div>
		<div class="rboxInner">
			<div class="halfWrap">
				<div class="half" style="width:70%">
					<span class="selectN" style="width:120px">
						<span>
							<label for="selectN_id1" class="hidden">구분</label>
							<select id="search_gubun">
								<option value="">선택</option>
								<option value="BOARD_NAME" ${admStatSearchVO.searchCondition == 'BOARD_NAME' ? 'selected' : ''}>게시판명</option>
								<option value="REGR_NAME" ${admStatSearchVO.searchCondition == 'REGR_NAME' ? 'selected' : ''}>관리자</option>
							</select> 
						</span>
					</span>
					<input type="text" value="${fn:replace(admStatSearchVO.searchKeyword,'"', '&quot;')}" id="keyword" class="text" style="width:300px" title="검색제목" />
				</div>
				<div class="half" style="width:30%">
					<label class="lbl">상태</label>
					<span class="selectN" style="width:100px">
						<span>
							<label for="selectN_id1" class="hidden">상태값 선택</label>
							<select id="stat" name="stat">
								<option value="">선택</option>
								<option value="ALL" ${admStatSearchVO.stat == 'ALL' ? 'selected' : ''}>전체</option>
								<option value="USE" ${admStatSearchVO.stat == 'USE' ? 'selected' : ''}>운영중</option>
								<option value="NOT_USE" ${admStatSearchVO.stat == 'NOT_USE' ? 'selected' : ''}>폐쇄</option>
							</select> 
						</span>
					</span>
				</div>
			</div>
			<div>
				<div class="radiogroup fl mt5">
					<input type="radio" name="searchDttm" value="REG" title="생성일을 선택합니다." />
					<label for="radio01">생성일</label>
					<input type="radio" name="searchDttm" value="END" title="폐쇄일을 선택합니다." />
					<label for="radio02">폐쇄일</label>
					<input type="radio" name="searchDttm" checked value="" title="선택안함을 선택합니다." />
					<label for="radio03" class="mgrn">선택안함</label>
				</div>
				&nbsp;
				<div class="sec_calender">
					<input type="text" class="text" id="sFromDt" name="sFromDt" title="시작날짜를 입력합니다. 예)YYYY.MM.DD"/>
				</div> ~ 
				<div class="sec_calender">
					<input type="text" class="text" id="sToDt" name="sToDt" title="시작날짜를 입력합니다. 예)YYYY.MM.DD"/>
				</div>
			</div>

			<div class="rbox_btns">
				<button type="button" class="btn_style7_2" id="search">검색</button> 
				<button type="button" class="btn_style6_3" id="btnReset">초기화</button>
			</div>
		</div>
	</div>

	<div class="btn_board_top">
		<div class="fl">
			<button type="button" class="btn_write6" id="createBbs">게시판 생성</button>
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
	<table summary="이 표는 번호, 게시판명, 종류, 관리자, 게시물수, 방문자수, 생성일, 폐쇄일로 구성된 게시판 현황목록입니다." class="tbl_list">
	<caption>게시판 현황</caption>
	<colgroup>
	<col style="width:7%" />
	<col style="width:*" />
	<col style="width:*" />
	<col style="width:11%" />
	<col style="width:11%" />
	<col style="width:11%" />
	<col style="width:11%" />
	<col style="width:11%" />
	<col style="width:11%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col" class="f"><div class="col">번호</div></th>
		<th scope="col"><div class="col">게시판ID</div></th>
		<th scope="col"><div class="col">게시판명</div></th>
		<th scope="col"><div class="col">종류</div></th>
		<th scope="col"><div class="col">관리자</div></th>
		<th scope="col"><div class="col"><a href="javascript:fnSearchList('TOT_CNT')">게시물수</a></div></th>
		<th scope="col"><div class="col"><a href="javascript:fnSearchList('READ_CNT')">방문자수</a></div></th>
		<th scope="col"><div class="col"><a href="javascript:fnSearchList('REG_DTTM')">생성일</a></div></th>
		<th scope="col" class="e"><div class="col"><a href="javascript:fnSearchList('END_DTTM')">폐쇄일</a></div></th>
	</tr>
	</thead>
	<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">		
			<tr>
				<td>${result.seq}</td>
				<td class="tit"><a href="javascript:fnGetBoardView('${result.boardId}');">${result.boardId}</a></td>
				<td class="tit"><a href="javascript:fnGetBoardView('${result.boardId}');" class="text_dot" title="${result.boardName}">${result.boardName}</a></td>
				<td>${result.boardKind}<br>(${result.boardForm})</td>
				<td>${result.regrName}</td>
				<td>${result.totCnt}</td>
				<td>${result.readCnt}</td>
				<td>${result.regDttm}</td>
				<td>
					<c:if test="${result.endDttm == '운영중'}">
						<span>운영중</span>
					</c:if>
					<c:if test="${result.endDttm != '운영중'}">
						<span class="txt_point">${result.endDttm}</span>
					</c:if>				
				</td>
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

