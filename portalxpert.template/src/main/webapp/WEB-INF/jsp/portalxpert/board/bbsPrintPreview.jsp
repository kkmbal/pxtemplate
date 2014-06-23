<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<title></title>
<script type="text/javascript">
	var boardId = '${boardId}';
	var boardKind = '${boardKind}';
	var notiId = '${notiId}';
	var userId = '${userId}';//접속유저
	var pageIndex = '${pageIndex}';
	var pageUnit =  '${pageUnit}';
	var spec = '${spec}';
	var regrId = '${regId}';//게시글유저
	var notiKind = '${notiKind}';
	var boardNickUseYn = '${nickUseYn}';
	var pnum = '${pnum}';
	var opnMakrRealnameYn = null;
	var notiInfo = null;
	var notiFile = null;
	var notiOpn1 = null;
	var notiOpn2 = null;
	var notiPrevNextInfo = null; 
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/bbsPrintPreview.js"></script>
</head>

<body>

	<div>${boardName}</div>

	작성자:<div id="userName"></div>
	소속기관:<div id="cdlnDeptFname"></div>
	<div>공개설정</div>
	<div><input type="checkbox" name="">공지</div>
	번호:<div id="notiNum"></div>
	조회수:<div id="notiReadCnt"></div>
	의견:<div id="opnCnt"></div>
	등록일:<div id="regDttm"></div>
	제목:<div id="notiTitle"></div>
	<div id="notiConts">
	</div>
	<div>
	첨부
	</div>
	<div class="reply">
		<ul id="replyUl">
		</ul>
	</div>

	<!-- 게시판이전글다음글 -->
	<div id="boardPage">
	</div>



	
<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>

	
</body>
</html>			 