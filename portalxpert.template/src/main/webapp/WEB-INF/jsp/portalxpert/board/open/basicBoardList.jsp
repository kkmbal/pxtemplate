<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

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
<script type="text/javascript" src="${RES_HOME}/js/portal/board/open/basicBoardList.js"></script>
</head>

<body>
<form:form commandName="boardSearchVO" action="${WEB_HOME}/board210/open/getBoardInfoList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	<form:hidden path="notiSeqNo" />
	<form:hidden path="orderType" />
	<form:hidden path="isDesc" />
	
<div class="container">	
<div class="header">
	<h1>${boardName}</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">커뮤니티</a></span>
		<span><strong>${boardName}</strong></span>
	</div>
</div>

	 
	
	<c:if test="${boardForm == '040'}">
		<div id='calendar'></div>
		</br>
	</c:if>

<c:if test="${boardForm != '040'}">
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
</c:if>

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
<col style="width:10%" />
<col style="width:*" />
<col style="width:8%" />
<col style="width:11%" />
<col style="width:11%" />
<col style="width:11%" />
</colgroup>
<thead>
<tr>
	<th scope="col" class="f"><span>번호</span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('notiTitle')">제목</a></span></th>
	<th scope="col"><span>파일</span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('regrName')">작성자</a></span></th>
	<th scope="col"><span><a href="javascript:fnSearchList('notiReadCnt')">조회</a></span></th>
	<th scope="col" class="e"><span><a href="javascript:fnSearchList('regDttm')">등록일</a></span></th>
</tr>
</thead>
<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			<tr <c:if test="${result.anmtYn == 'Y'}"> class="notice"</c:if>>
			    <c:if test="${result.anmtYn == 'Y'}">
				<td><span class="btn_set ico_notice"><span>공지</span></span></td>
				</c:if>
				<c:if test="${result.anmtYn != 'Y'}">
				<td>${result.oldNoticeSeq}</td>
				</c:if>
				<td class="tit" title="${result.notiTitleOrgn}"><a href="javascript:fnGetBoardView('${result.notiId}','${result.pnum}');" class="text_dot">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;')}</a> 
					<c:if test="${result.opnPrmsYn == 'Y' && result.opnCnt > 0}">
					<em>[의견${result.opnCnt}]</em>
					</c:if>
				</td>
				<td>
					<c:if test="${result.apndFileCnt > 0}">
					<a href="#"><span class="ico_fileAttch"><span class="hidden">파일첨부</span></span></a>
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


	 