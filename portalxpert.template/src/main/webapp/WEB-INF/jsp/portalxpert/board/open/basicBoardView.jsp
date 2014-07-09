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
	var thumbnailFile = '${thumbnailFile}';
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
<script type="text/javascript" src="${RES_HOME}/js/portal/board/open/basicBoardView.js"></script>
</head>

<body>

<div class="container">

	<div class="header">
		<h1>${boardName}</h1>
		<div class="loc">
			<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
			<span><a href="#">커뮤니티</a></span>
			<span><strong>${boardName}</strong></span>
		</div>
	</div><!-- end of header -->




	<!-- 테이블상단 버튼영역 -->
	<div class="btn_board_top">
		<div class="fl">
		</div>
		<div class="fr">
			<a href="#" class="btn_set bt_style7 btn_list"><span>목록</span></a>
		</div>
	</div>
	<!-- //테이블상단 버튼영역 -->

	<div>
		<div class="toptable" >
			<div class="fl">
				<div class="innerbox tit">번호</div>
				<div class="innerbox" id="notiNum"></div>
				<div class="innerbox"> &nbsp;</div>
				<div class="innerbox tit">조회수</div>
				<div class="innerbox" id="notiReadCnt"></div>
				<div class="innerbox"> &nbsp;</div>
				<div class="innerbox tit">의견</div>
				<div class="innerbox" id="opnCnt"></div>		
			</div>
			<div class="fr">
<!-- 				<div class="innerbox">전체공개</div> -->
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
		<div class="reply_post"  style="display:none;">
		</div>
	</div>
	
	


	<!-- 게시판이전글다음글 -->
	<div id="boardPage">
		<table width="100%" class="tbl_form" summary="이전 다음글 2개만.">
		<colgroup>
			<col width="15%" />
			<col width="85%" />
		</colgroup>
		</table>	
	</div>	





<!-- //입력테이블2 -->
<!-- 버튼영역 -->
<div style="padding:10px 0px 0px 0px" >
	<div class="fl">
	</div>
	<div class="fr">
		<a href="#" class="btn_set bt_style7 btn_list"><span>목록</span></a>
	</div>
</div>
<!-- //버튼영역 -->





</div><!-- end of container -->
<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>
	
</body>
</html>	


