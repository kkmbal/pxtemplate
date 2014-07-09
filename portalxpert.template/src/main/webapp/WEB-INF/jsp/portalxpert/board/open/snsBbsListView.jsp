<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

if('${btnViewYn}' == "X"){
	alert('접근권한이 없습니다.');
	history.back();
}

var frameHeight = '${fh}'==''?'700':'${fh}';
var pageId = 'boardList';
var makrDispDiv = '${makrDispDiv}';
var agrmOppUseYn = '${agrmOppUseYn}';//찬성_반대_사용_여부
var likeUseYn = '${likeUseYn}';//좋아요_사용_여부
var superAdmin = '${superAdmin}';
var boardId = '${boardId}';

var pageIndex = '${pageIndex}';
var pageUnit = '${pageUnit}';
var boardName = '${boardName}';
var favoYn = '${favoYn}';
var boardKind = '${boardKind}';
var btnViewYn = '${btnViewYn}';
var host = '${host}';
var userId = '${userId}';
var eamAdminYn = '${eamAdminYn}';
var nickUseYn = '${nickUseYn}';
var boardForm = '${boardForm}';
var searchKeyword = decodeURIComponent("${fn:replace(searchKeyword,'"', '&quot;')}");
var searchCondition = '${searchCondition}';	
var regrId = null;


var notiList = ${notiList};
var opnList = ${opnList};
var apndList = ${apndList};			 

var moreData = "Y";
var searchData = "N";
var write_apnd_kind = '010';
var jsonAppendImgList = [];  //이미지 리스트
var jsonAppendFileList = [];  //첨부 리스트
//var jsonAppendFileList = [];//설문 보기 리스트



var lastSortSeq = "${lastSortSeq}";		
var userId = "${userId}";
var viewMode = "${viewMode}";  //ALL
var myImg = "${myImg}";
if (myImg == '') myImg = '${RES_HOME}/images/img/img_me.jpg';

var tmpNotiSeq;
var boardId = '${boardId}';
var userId = '${userId}';
var nojoYn = '${nojoYn}';
var notiWriteId = '${notiWriteId}';
var boardKind = '${boardKind}';
var moblOpenYN = '${moblOpenYN}';
var moblOpenDiv = '${moblOpenDiv}';
var editDiv = '${editDiv}';
var opnWrteDiv = '${opnWrteDiv}';
var replyPrmsDiv = '${replyPrmsDiv}';
var nickUseYn = '${nickUseYn}';
var makrDispDiv = '${makrDispDiv}';  //작성자 표기 구분
var agrmOppUseYn = '${agrmOppUseYn}';  //찬/반 구분


var isAdmin = '${isAdmin}';
var notiId = '';
var basicCloseDttm = '${basicCloseDttm}';
var boardForm;
var boardFormSpec;
var notiReadmanAsgnYn = '${notiReadmanAsgnYn}';
var kind = '${kind}';
var pageIndex = '${pageIndex}';
var WEB_DIR = '${WEB_DIR}';
var SAVE_DIR = '${SAVE_DIR}';
var userDiv ;
var userName;
var insertMode = "insert";

var imgUploadMax = ${imgUploadMax};
var imgUploadSize = ${imgUploadSize};
var apndUploadMax = ${apndUploadMax};
var apndUploadSize = ${apndUploadSize};

var upNotiId = '${upNotiId}';

var apndFileSz = '${apndFileSz}';  //파일 업로드 제한사이즈

if (boardId == 'BBS999999')
{
	apndFileSz = 50000;
}

var boardExplUseYn = '${boardExplUseYn}';
var boardExpl = '${boardExpl}';

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/open/snsBbsListView.js"></script>
</head>

<body>


	<div id="my_list" class="sns_list">
		<div class="header">
			<h1>${boardName}</h1>
			<div class="loc">
				<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
				<span><a href="#">커뮤니티</a></span>
				<span><strong>${boardName}</strong></span>
			</div>
		</div>
					<div class="tab_7">
						 
						<div class="tab_post">
						 
							 <div class="mysns_btn">
								
								<a id="pageRefresh" class="ico_ref" title="새로고침">새로고침</a>
							</div>

								<!--sns_area-->
								<div class="sns_area">
									
									<!--btn_area-->
									<div class="btn_area">
										<div class="fl_left ma_lef5">
										</div>
										<div class="fl_right">
										</div>
									</div>    
									<!--//btn_area-->
									<span class="sch_tl"><!--top,left--></span>
									<span class="sch_tr"><!--top,right--></span>
									<span class="sch_br"><!--bottom,right--></span>
									<span class="sch_bl"><!--bottom,left--></span>
								</div>
								
								<div id="div_sns_read">
								</div>
																		
									
			 
					</div>
					</div>
					<div class="title_add"  id="ajax_indicator" style="display:none;" >
						<span><img src="${RES_HOME}/images/img/loadinfo.gif"/>로딩중입니다. 잠시만 기다려주세요...</span>
					</div>
				</div>	
				
				<div id="show_dialog"></div>
				
				<iframe id="dummy" name="dummy" width=0 height=0></iframe>

</body>
</html>				
			 
			 
