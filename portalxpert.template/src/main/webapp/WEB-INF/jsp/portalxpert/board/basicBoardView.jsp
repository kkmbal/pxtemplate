<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
	var opnWrteDiv = '${opnWrteDiv}';
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
	var upNotiIdCnt = 0;
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardView.js"></script>
</head>

<body>
<div class="container">
	<div class="header">
		<div class="h1">${boardName}</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">${boardName}</strong>
		</div>
	</div>
	
	<p class="txt_notice">게시판 게시기준에 맞지 않는 부적절한 게시물은 작성자의 동의 없이 삭제됩니다.</p>
	<div class="btn_board_sec mt13">
		<div class="fl">
			<c:if test="${btnViewYn == 'Y'}">
			<button class="btn_write" type="button" >글쓰기</button>
			<c:if test="${boardForm != '030'}">
			<button class="btn_style2_4 btn_reply" type="button" style="display:none;">답글쓰기</button>
			</c:if>
			<button class="btn_style2_2 btn_modify" type="button" >수정</button>
			<button class="btn_style2_2 btn_delete" type="button" >삭제</button>
			<button class="btn_style2_2 btn_printing" type="button" >출력</button>
			<button class="btn_style2_5 btn_boardMove" type="button" >게시물 이동</button>
			</c:if>
		</div>
		<div class="fr">
			<button class="btn_style4_2 btn_list" type="button" >목록</button>
		</div>
	</div>

	<table class="tbl_view mt10" summary="번호, 조회수, 공개여부에 관한 정보제공">
	<caption>게시판 입력목록</caption>
	<colgroup>
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:28%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="row">번호</th>
		<td><span id="notiNum"></span></td>
		<th scope="row">조회수</th>
		<td><span id="notiReadCnt"></span></td>
		<th scope="row">의견</th>
		<td><span id="opnCnt"></span></td>
		<td class="last"><a href="#" class="allopen">전체공개</a></td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td colspan="7">
			<div class="inner">
				<span class="title" id="notiTitle"></span>
				<ul>
					<li>
						<span class="tit">작성자</span>
						<span class="desc"><span id="userName"></span> <span><img src="${RES_HOME}/images/ico_room.png" alt="부서" /><label id="deptName"></label></span> <span><img src="${RES_HOME}/images/ico_email.png" alt="이메일" /><label id="mailTo"></label></span></span>
					</li>
					<li class="half2 other2">
						<span class="tit">등록일</span>
						<span class="desc" id="regDttm"></span>
					</li>
				</ul>
				<span class="notice" id="anmtDiv" style="display:none;"> <label for="notice">공지</label></span>
			</div>
			<!-- 글내용  -->
			<div class="intxt">
				<span class="viewer" id="imgViewer" style="display:none">
					<ul id="imgNotiConts"></ul><!-- 이미지형 게시판 -->		
				</span>
				<p class="te_center" id="movNotiConts" style="display:none"></p><!-- 동영상 게시판 -->
				<span id="notiConts">
				${notiConts}
				</span>
			</div>
			<!-- 글내용 끝 -->
		</td>
	</tr>
	<tr>
		<td colspan="7">
		<div class="rbox02 mt0">
			<span class="top"></span>
			<div class="mid">
				<div class="inquiry_top">
					<dl id="notiFileDl">
					</dl>
				</div>
			</div>
			<span class="btm"></span>
		</div>
		</td>
	</tr>
	</tbody>
	</table>
	
	
	<!--댓글-->
	<div class="reply_sec" id="opnPrmsDiv" style="display:none;">
		<ul id="replyUl">
		</ul>
	</div>	
	

	<div class="pageNavi" id="boardPage">
		<ul>
			<li>
				<span class="arr prev">이전 글</span>
				<span class="title"><a href="#">이전 글의 제목입니다.</a></span>
			</li>
			<li>
				<span class="arr next">다음 글</span>
				<span class="title"><a href="#">다음 글의 제목입니다.</a></span>
			</li>
		</ul>
	</div>
	
	
	<div class="btn_board_sec mt13">
		<div class="fl">
			<c:if test="${btnViewYn == 'Y'}">
			<button class="btn_write" type="button" >글쓰기</button>
			<c:if test="${boardForm != '030'}">
			<button class="btn_style2_4 btn_reply" type="button" style="display:none;">답글쓰기</button>
			</c:if>
			<button class="btn_style2_2 btn_modify" type="button" >수정</button>
			<button class="btn_style2_2 btn_delete" type="button" >삭제</button>
			<button class="btn_style2_2 btn_printing" type="button" >출력</button>
			<button class="btn_style2_5 btn_boardMove" type="button" >게시물 이동</button>
			</c:if>
		</div>
		<div class="fr">
			<button class="btn_style4_2 btn_list" type="button" >목록</button>
		</div>
	</div>
	
</div>

<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>
	
</body>
</html>	


	 