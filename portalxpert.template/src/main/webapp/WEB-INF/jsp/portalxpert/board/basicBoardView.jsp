<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<title></title>
<script type="text/javascript">
if('${btnViewYn}' == "X"){
	alert('접근권한이 없습니다.');
	history.back();
}

	var nickUseYn = null;
	var userMail = null;
	var agrmOppYn = null;
	var opnPrmsYn = null;
	var notiKind = null;
	var regrId = null;//게시글등록자
	var opnMakrRealnameYn = null;
	var admYn = '${admYn}';
	var eamAdminYn = '${eamAdminYn}';
	var superAdmin = '${superAdmin}';
	var pageId = 'boardView';
	var btnViewYn = '${btnViewYn}';
	var boardId = '${boardId}';
	var boardKind = '${boardKind}';
	var notiId = '${notiId}';
	var userId = '${userId}';//접속유저
	var pageIndex = '${pageIndex}';
	var pageUnit =  '${pageUnit}';
	var notiSeqNo = '${notiSeqNo}';//게시글 번호
	var agrmOppUseYn = '${agrmOppUseYn}';
	var boardNickUseYn = '${nickUseYn}';
	var pnum = '${pnum}';
	var prev_pnum = '${prev_pnum}';
	var next_pnum = '${next_pnum}';
	var imgRealDir = '${imgRealDir}';
	var movDir = '${movDir}';
	var imgSvrUrl = '${imgSvrUrl}';
	var replyWrteDiv = '${replyWrteDiv}';//답변쓰기 구분
	var boardExplUseYn = '${boardExplUseYn}';
	var boardExpl = '${boardExpl}';
	var boardForm = '${boardForm}';
	var boardFormSpec = '${boardFormSpec}';
	var searchCondition = '${searchCondition}';
	//var searchKeyword = '${searchKeyword}';
	var searchKeyword = decodeURIComponent("${fn:replace(searchKeyword,'"', '&quot;')}");
	var regDttmFrom = '${regDttmFrom}';
	var regDttmTo = '${regDttmTo}';
	var listYn = 'N';
	var orderType = '${orderType}';
	var isDesc = '${isDesc}';
	var notiEmailSendYn = '${notiEmailSendYn}';
	var notiInfo = null;
	var notiFile = null;
	var notiOpn1 = null;
	var notiOpn2 = null;
	var notiPrevNextInfo = null;
	var notiMov = null;
	var notiPrevNextImgMovInfo = null;	
	
	var goIdx = 0;
	var prev_last = false;
	var next_last = false;
	
	var maxNum = 0;
	
	var myZNodes = null;
	var myZTree = null;
	var myBoardtreeObj = null;
	var mObjHeight = null;
	

	var selectNodeId = null;
	var selectBoardForm = null;
	
	var isNotiSurvey = false;
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardView.js"></script>
</head>

<body>

	<div>${boardName}</div>
	<div>
		<input type="button" value="목록"  class="btn_list">
		<input type="button" value="출력" class="btn_print">
		<c:if test="${btnViewYn == 'Y'}">
		<input type="button" value="수정"  class="btn_modify">
		<input type="button" value="삭제" class="btn_delete">
		</c:if>
		<input type="button" value="게시물이동" id="btn_boardMove">
		<c:if test="${btnViewYn == 'Y'}">
		<input type="button" value="답글" class="btn_reply">
		<input type="button" value="글쓰기"  class="btn_write">
		</c:if>
	</div>
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
	${notiConts}
	</div>
	<div>
		<dl id="notiFileDl">
			<dt><img src="${RES_HOME}/images/img/txt_file.png" alt="첨부파일"></dt>
		</dl>
	</div>
	<div>
		<ul id="replyUl" style="display:none;">
		</ul>
		<div class="reply_post"  style="display:none;">
			<textarea cols="5" rows="3" id="noti_reply"></textarea>
			<a href="javascript:fnInsertBbsNotiOpnForView()" title="의견등록">의견등록</a>
		</div>
	</div>

	<!-- 게시판이전글다음글 -->
	<div id="boardPage">
	</div>


	<div>
		<input type="button" value="목록"  class="btn_list">
		<input type="button" value="출력" class="btn_print">
		<c:if test="${btnViewYn == 'Y'}">
		<input type="button" value="수정"  class="btn_modify">
		<input type="button" value="삭제" class="btn_delete">
		</c:if>
		<input type="button" value="게시물이동" id="btn_boardMove">
		<c:if test="${btnViewYn == 'Y'}">
		<input type="button" value="답글" class="btn_reply">
		<input type="button" value="글쓰기"  class="btn_write">
		</c:if>
	</div>
	
<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>

	
</body>
</html>			 