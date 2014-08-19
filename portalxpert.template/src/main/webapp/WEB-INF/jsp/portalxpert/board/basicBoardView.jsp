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
			</c:if>
			<button class="btn_style2_2 btn_printing" type="button" >출력</button>
			<button class="btn_style2_5 btn_boardMove" type="button" >게시물 이동</button>
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
<!-- 				<span class="viewer"> -->
<!-- 				</span> -->
				<ul id="imgNotiConts" class="sns_imgs" style="display:none"></ul><!-- 이미지형 게시판 -->		
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
<!-- 					<span class="ico_file"><strong>첨부파일</strong> <a href="#">document.hwp</a> (1.244kb)</span> -->
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
		<div id="replyUl" class="clearfix" style="display:none;">
		</div>
		<div style="display:none;">
			<textarea cols="" rows="" class="textbox" style="width:610px;height:40px" id="noti_reply" title="댓글 입력"></textarea>
			<span class="textbox_btns">
				<span class="btn_st01 w01"><button type="button" onclick="fnInsertBbsNotiOpnForView()">등록</button></span>
			</span>
		</div>
	</div>	
	
	<%--
	<div class="reply_sec"  id="opnPrmsDiv" style="display:none;">
		<ul>
			<li>
				<div class="re_tit">
					<span>작성자</span> <span>2014-06-01 17:33</span> <span><a href="#">의견</a></span> <span><a href="#">삭제</a></span>
				</div>
				<p>인생에는 두가지 고통이 있다. 하나는 훈련의 고통이고, 또 하나는 후회의 고통이다. 훈련의 고통은 가볍지만 후회의 고통은 무겁다. 기적은 훈련이 만든다.</p>
			</li>
			<li class="re">
				<div class="re_inner">
					<ul>
						<li>
							<div class="re_tit">
								<span>이선미</span> <span>2014-06-02 17:33</span> <span><a href="#">의견</a></span>
								<p>좋은 의견입니다. 많은 분들이 봐주셨으면 좋겠습니다. ~</p>
							</div>
						</li>
						<li>
							<div class="re_tit">
								<span>은하림</span> <span>2014-06-03 17:33</span> <span><a href="#">의견</a></span>
								<p>이선미&gt; 열심히 노력하셨던 결실을 이루는 날이 어서 왔으면 좋겠습니다. ^^</p>
							</div>
						</li>
						<li>
							<div class="re_tit">
								<span>이선미</span> <span>2014-06-03 20:40</span> <span><a href="#">의견</a></span>
								<p>은하림&gt; 감사합니다.</p>
							</div>
						</li>
					</ul>
					<div>
						<textarea cols="" rows="" class="textbox" style="width:560px;height:40px" title="댓글 입력"></textarea>
						<span class="textbox_btns">
							<span class="btn_st01 w01"><button>등록</button></span>
							<span class="btn_st02 w01"><button>취소</button></span>
						</span>
					</div>
				</div>
			</li>
			<li>
				<div class="re_tit">
					<span>강하나</span> <span>2014-06-01 17:33</span> <span><a href="#">의견</a></span>
				</div>
				<p>인생에는 두가지 고통이 있다. 하나는 훈련의 고통이고, 또 하나는 후회의 고통이다. 훈련의 고통은 가볍지만 후회의 고통은 무겁다. 기적은 훈련이 만든다.</p>
			</li>
			<li>
				<textarea cols="" rows="" class="textbox" style="width:610px;height:40px" title="댓글 입력"></textarea>
				<span class="textbox_btns">
					<span class="btn_st01 w01"><button>등록</button></span>
					<span class="btn_st02 w01"><button>취소</button></span>
				</span>
			</li>
		</ul>
	</div>
	 --%>

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
			</c:if>
			<button class="btn_style2_2 btn_printing" type="button" >출력</button>
			<button class="btn_style2_5 btn_boardMove" type="button" >게시물 이동</button>
		</div>
		<div class="fr">
			<button class="btn_style4_2 btn_list" type="button" >목록</button>
		</div>
	</div>
	
</div>

<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>
	
</body>
</html>	


<%--

<body>

<div class="container">

	<div class="header">
		<h1>${boardName}</h1>
		<div class="loc">
			<span><a href="#"><img src="${RES_HOME}/${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
			<span><a href="#">커뮤니티</a></span>
			<span><strong>${boardName}</strong></span>
		</div>
	</div><!-- end of header -->




	<!-- 테이블상단 버튼영역 -->
	<div class="btn_board_top">
		<div class="fl">
			<c:if test="${btnViewYn == 'Y'}">
			<a href="#" class="btn_write"><span>글쓰기</span></a>
			<c:if test="${boardForm != '030'}">
			<a href="#" class="btn_set bt_style5 btn_reply" style="display:none;"><span>답글쓰기</span></a>
			</c:if>
			<a href="#" class="btn_set bt_style2 btn_modify"><span>수정</span></a>
			<a href="#" class="btn_set bt_style2 btn_delete"><span>삭제</span></a>
			</c:if>
			<a href="#" class="btn_set bt_style2 btn_print"><span>출력</span></a>
			<a href="#" class="btn_set bt_style2 btn_boardMove"><span>게시물 이동</span></a>
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
			<textarea class="textbox" cols="5" rows="3" id="noti_reply" style="width:620px;margin-bottom:10px;"></textarea>
			<a href="javascript:fnInsertBbsNotiOpnForView()" title="의견등록" class="btn_set bt_style1"><span>의견등록</span></a>
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
		<c:if test="${btnViewYn == 'Y'}">
		<a href="#" class="btn_write"><span>글쓰기</span></a> 
		<c:if test="${boardForm != '030'}">
			<a href="#" class="btn_set bt_style5 btn_reply" style="display:none;"><span>답글쓰기</span></a>
		</c:if>
		<a href="#" class="btn_set bt_style2 btn_modify"><span>수정</span></a>
		<a href="#" class="btn_set bt_style2 btn_delete"><span>삭제</span></a>
		</c:if>
		<a href="#" class="btn_set bt_style2 btn_print"><span>출력</span></a>
		<a href="#" class="btn_set bt_style2 btn_boardMove"><span>게시물 이동</span></a>
	</div>
	<div class="fr">

		<a href="#" class="btn_set bt_style7 btn_list"><span>목록</span></a>
	</div>
</div>
<!-- //버튼영역 -->





</div><!-- end of container -->
<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>
	
</body>

 --%>		 