<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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

<div class="container">

	<div>
		<div class="toptable" >
			<div class="fl">
				<div class="innerbox tit">번호</div>
				<div class="innerbox" id="notiNum">345,789</div>
				<div class="innerbox"> &nbsp;</div>
				<div class="innerbox tit">조회수</div>
				<div class="innerbox" id="notiReadCnt">346,000</div>
				<div class="innerbox"> &nbsp;</div>
				<div class="innerbox tit">의견</div>
				<div class="innerbox" id="opnCnt">35</div>		
			</div>
			<div class="fr">
			</div>	
		</div>
	</div>

	

	<div class="titlebox fl">
		<div class="fl">
			<h1 id="notiTitle" style="width:660px;"> </h1>
			<div class="innerbox tit">작성자</div>
			<div class="innerbox" id="userName"></div>
			<div class="innerbox" id="deptName"></div>
			<div class="innerbox" id="mailTo"></div>
		</div>
		<div class="fl">
			<div class="innerbox tit">등록일</div>
			<div class="innerbox" id="regDttm"></div>
		</div>
		<div class="fr" id="anmtDiv" style="display:none;">
			<strong>공지</strong>
		</div>
	</div>


	<!--  글내용 -->
	<div class="view_post">
		<ul id="imgNotiConts" class="sns_imgs" style="display:none"></ul><!-- 이미지형 게시판 -->		
		<p class="te_center" id="movNotiConts" style="display:none"></p><!-- 동영상 게시판 -->
		<span id="notiConts">
		${notiConts}
		</span>
	</div>
	<!-- 글내용 끝 -->

	<div class="attachbox">
		<span class="tit" style="vertical-align:top;">첨부파일</span>
		<span class="ico_fileAttch2">
			<dl id="notiFileDl">
			</dl>
		</span>
	</div>




<!--댓글-->
	<div class="replybox fl" id="opnPrmsDiv" style="display:none;">
		<div id="replyUl" class="clearfix" style="display:none;">
		</div>
	</div>
	
	
</div><!-- end of container -->
	
</body>
</html>	

<%--

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

 --%>		 