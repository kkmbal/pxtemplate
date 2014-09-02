<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	if('${btnViewYn}' == "X"){
		alert('접근권한이 없습니다.');
		history.back();
	}

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
	<input type="hidden" id="regDttmCondition" name="regDttmCondition">
	<input type="hidden" id="regDttmFrom" name="regDttmFrom">
	<input type="hidden" id="regDttmTo" name="regDttmTo">
	
<div class="container">
	<div class="header">
		<div class="h1">${boardName}</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">${boardName}</strong>
		</div>
	</div>
	
	
<c:choose>
	<%-- ********************** CALENDAR BOARD --%>
	<c:when test="${boardForm == '040'}">	
		<!-- 달력 -->
		<div class="schedule_sec">
			<div class="schedule_head">
				<div class="btn">
					<button class="btn_style8_3" type="button" id="btn_write">글쓰기</button>
				</div>
				<div class="date">
					<button class="btn_sch_prev" type="button" id="calPrevBtn"><span>이전달</span></button>
					<span id="calMonth"></span>
					<button class="btn_sch_next" type="button" id="calNextBtn"><span>다음달</span></button>
				</div>
				<button class="btn_sch_day" type="button" id="calTodayBtn">오늘</button>
				<div class="option">
					<button class="btn_refresh" type="button" id="calResetBtn"><span>새로고침</span></button>
				</div>
			</div>	
		</div>
		<div id='calendar' style="margin-top:-30px;margin-bottom:30px;"></div>
		<div style="display:none;">
			<input type="text" value="" id="keyword" class="text" title="검색제목" />		
			<select title="게시물수 보기" id="list_cnt">
				<option value="10">10개보기</option>
			</select>			
		</div>
		<!-- //달력 -->
		<div class="h2" id="calText"></div>
		<table summary="" class="tbl_list">
		<caption></caption>
		<colgroup>
		<col style="width:7%" />
		<col style="width:18%" />
		<col style="width:*" />
		<col style="width:22%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col" class="f"><div class="col">번호</div></th>
			<th scope="col"><div class="col">등록 기관</div></th>
			<th scope="col"><div class="col">제목</div></th>
			<th scope="col" class="e"><div class="col">교육일정</div></th>
		</tr>
		</thead>
		<tbody>		
		</tbody>
		</table>
	</c:when>
	<%-- ********************** CALENDAR BOARD --%>
	<%-- ********************** CMS BOARD --%>
	<c:when test="${boardKind == '120'}">	
		<div class="rbox">
			<div class="rbox_top"></div>
			<div class="rboxInner">
				<div class="halfWrap">
					<div class="half" style="width:100%">
						<span class="selectN" style="width:120px">
							<span>
								<label for="selectN_id1" class="hidden">구분</label>
								<select title="" id="search_gubun">
									<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
								</select> 
							</span>
						</span>
						<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" id="keyword" class="text" style="width:500px" title="검색제목" />
					</div>
				</div>
				<div class="halfWrap">
					<!-- 셀렉트박스 -->
					<span class="selectN" style="width:120px">
						<span>
							<label for="selectN_id1" class="hidden">검색구분</label>
							<select title="" id="search_gubun_dttm">
								<option value="">선택</option>
								<option value="REG_DTTM_FROM" ${regDttmCondition == 'REG_DTTM_FROM' ? 'selected' : ''}>등록일</option>
								<option value="REG_DTTM_TO" ${regDttmCondition == 'REG_DTTM_TO' ? 'selected' : ''}>종료일</option>
							</select>
						</span>
					</span>
					<!-- //셀렉트박스 -->
					<input type="text" class="text" id="regDttm" name="regDttm" value="${regDttm}" style="width:100px" title="날짜를 입력합니다. 예)YYYY.MM.DD">
				</div>
	
				<div class="rbox_btns">
					<button type="button" class="btn_style7_2" id="search">검색</button> 
					<button type="button" class="btn_style6_3" id="btnReset">초기화</button>
				</div>
			</div>
		</div>
		
		<div class="btn_board_top">
			<div class="fl">
				<c:if test="${btnViewYn == 'Y'}">
				<button class="btn_write5" type="button" id="btn_write">신규등록</button>
				</c:if>
			</div>
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
		<col style="width:20%" />
		<col style="width:*" />
		<col style="width:11%" />
		<col style="width:11%" />
		<col style="width:11%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col" class="f"><div class="col">번호</div></th>
			<th scope="col"><div class="col">기관</th>
			<th scope="col"><div class="col">제목</div></th>
			<th scope="col"><div class="col">등록일</th>
			<th scope="col"><div class="col">게시종료일</th>
			<th scope="col" class="e"><div class="col">재사용</div></th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${paginationInfo.totalRecordCount > 0}">
				<c:forEach var="result" items="${notiList}" varStatus="status">		
					<tr>
						<td>${result.oldNoticeSeq}</td>
						<td>${result.deptName}</td>
						<td class="tit"><a href="javascript:fnGetBoardWrite('${result.notiId}','');" title="${result.notiTitleOrgn}" class="text_dot"><span>${result.notiTitle}</span></a></td>
						<td>${result.regDttm}</td>
						<td>${result.notiEndDttm}</td>
						<td><c:if test="${btnViewYn == 'Y'}"><button type="button" class="btn_style1_2" onclick="fnGetBoardWrite('${result.notiId}','copy');">복사</button></c:if></td>
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
	</c:when>
	<%-- ********************** CMS BOARD --%>
	<%-- ********************** BASIC BOARD --%>
	<c:otherwise>
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
			<div class="fl">
				<c:if test="${btnViewYn == 'Y'}">
				<button class="btn_write" type="button" id="btn_write">글쓰기</button>
				</c:if>
			</div>
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
		<col style="width:8%" />
		<col style="width:11%" />
		<col style="width:11%" />
		<col style="width:11%" />
		</colgroup>
		<thead>
		<tr>
			<th scope="col" class="f"><div class="col">번호</div></th>
			<th scope="col"><div class="col"><a href="javascript:fnSearchList('notiTitle')">제목</a></div></th>
			<th scope="col"><div class="col">파일</div></th>
			<th scope="col"><div class="col"><a href="javascript:fnSearchList('regrName')">작성자</a></div></th>
			<th scope="col"><div class="col"><a href="javascript:fnSearchList('notiReadCnt')">조회</a></div></th>
			<th scope="col" class="e"><div class="col"><a href="javascript:fnSearchList('regDttm')">등록일</a></div></th>
		</tr>
		</thead>
		<tbody>
		<c:choose>
			<c:when test="${paginationInfo.totalRecordCount > 0}">
				<c:forEach var="result" items="${notiList}" varStatus="status">		
					<tr <c:if test="${result.anmtYn == 'Y'}"> class="notice"</c:if> <c:if test="${result.lvl > 0}"> class="re${result.lvl}"</c:if>>
						<c:if test="${result.anmtYn == 'Y'}">
						<td><div class="ico_notice">공지</div></td>
						</c:if>
						<c:if test="${result.anmtYn != 'Y'}">
						<td>${result.oldNoticeSeq}</td>
						</c:if>
						<c:choose>
						<c:when test="${result.openAdm > 0 && eamAdminYn == 'N' && userId != result.userId}">
							<td class="tit"><span class="adm">[비밀글] 관리자만 읽을 수 있습니다.</span></td>
						</c:when>
						<c:otherwise>
							<td class="tit"><a href="javascript:fnGetBoardView('${result.notiId}','${result.pnum}');" title="${result.notiTitleOrgn}" class="text_dot"><span<c:if test="${result.notiReadCnt == 0}"> class="nonread"</c:if>>${result.notiTitle}</span>
								<c:if test="${result.opnPrmsYn == 'Y' && result.opnCnt > 0}">
								<span class="em">[의견${result.opnCnt}]</span>
								</c:if>
								</a>				
							</td>
						</c:otherwise>
						</c:choose>
						<td><c:if test="${result.apndFileCnt > 0}"><a href="#"><img src="${RES_HOME}/images/ico_fileAttch.png" alt="파일첨부" /></a></c:if></td>
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
	</c:otherwise>
	<%-- ********************** BASIC BOARD --%>
</c:choose>		

	<div class="paging">
		<c:if test="${boardForm != '040'}">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
		</c:if>
	</div>

</div>

</form:form>
</body>
</html>		
