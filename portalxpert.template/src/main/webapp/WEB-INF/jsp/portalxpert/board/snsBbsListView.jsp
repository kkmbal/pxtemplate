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
<script type="text/javascript" src="${RES_HOME}/js/portal/board/snsBbsListView.js"></script>
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
									<textarea id="id_sns_write" class="sns_write" style="width:97%; ime-mode:active;" cols="3" rows="5">글을 작성해주세요</textarea>
									<div id="div_img_view" style="display:none">
										<form id="fileform" name="fileform" enctype="multipart/form-data" method="post">
										<div class="sns_form">
											<div class="sns_tbl_form clearfix">
												<ul id="id_add_image">
													<li class="sns_imgadd">
														<a href="#" class="fo_byte">이미지추가
															<input type="file" class="file2" size="1" title="이미지추가" id="apndFile" name="upFile">
														</a>
													</li>						
												</ul>					
											</div>
											<a class="ico_sredel" style="cursor:pointer;" onclick="javascript:fnAppendMenuRemove();" title="첨부없이 일반쓰기로 돌아갑니다."></a>
										</div>
										</form>
									</div>
									<div id="div_file_view"  style="display:none">
										<div class="sns_form">
											<span class="fileadd">파일 첨부</span>
											<div class="sns_fileadd">									
									    <form id="apndFileform" name="apndFileform" enctype="multipart/form-data" method="post">
										<ul>
										    <li class="ma_bot5">
										    <input type="text" class="text" style="width:476px" readonly>
										    <a href="#" class="btn_set bt_style1 mv_file_a">
										    <input type="file" size="1" id="apndFileAddw" name="upFile" class="mv_file"><span>파일</span></a>
										    <a style="cursor:pointer;" class="btn_set bt_style1" onclick="fnAddFileList();"><span>추가</span></a>
										    </li>	
										</ul>
										</form>
											</div>
											<a class="ico_sredel" style="cursor:pointer;" onclick="javascript:fnAppendMenuRemove();" title="첨부없이 일반쓰기로 돌아갑니다."><!--삭제--></a>
										</div>										
									</div>									
									
									
									
									<!--btn_area-->
									<div class="btn_area">
										<div class="fl_left ma_lef5">
											<!-- <span class="nick">닉네임</span> -->
											<a id="btn_img" href="#" class="btn_img" title="이미지"></a>
											<a id="btn_file" href="#" class="btn_file3" title="파일"></a>
										</div>
										<div class="fl_right">
												<span class="fl_left" style="vertical-align: middle;margin-top: 5px;margin-right: 5px;display:none">공개설정</span>
												<select name="notiOpenDiv" id="notiOpenDiv" class="fl_left"  style="display:none">
													<option value="010" selected>전체공개
												</select>
											<a href="#" class="btn_reg" id="btn_context_write"></a>
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
			 
			 
<%--

	<div id="my_list" class="my_list">
					<div class="tab_7">
						 
						 
						 
							 <div class="mysns_btn">
								
								<a id="pageRefresh" class="ico_ref" title="새로고침">새로고침</a>
							</div>

								<!--sns_area-->
								<div class="sns_area">
									<textarea id="id_sns_write" class="sns_write" style="width:97%; ime-mode:active;" cols="3" rows="5">글을 작성해주세요</textarea>
									<div id="div_img_view" style="display:none">
										<form id="fileform" name="fileform" enctype="multipart/form-data" method="post">
										<div class="sns_form">
											<div class="tbl_form clearfix">
												<ul id="id_add_image">
													<li class="sns_imgadd">
														<a href="#" class="fo_byte">이미지추가
															<input type="file" class="file2" size="1" title="이미지추가" id="apndFile" name="upFile">
														</a>
													</li>						
												</ul>					
											</div>
											<a class="ico_sredel" style="cursor:pointer;" onclick="javascript:fnAppendMenuRemove();" title="첨부없이 일반쓰기로 돌아갑니다.">삭제</a>
										</div>
										</form>
									</div>
									<div id="div_file_view" style="display:none">										
										<div class="sns_form">
											<span class="fileadd">파일 첨부</span>
											<div class="sns_fileadd"> 
												<ul>
													<form id="apndFileform" name="apndFileform" enctype="multipart/form-data" method="post">													
														<li class="ma_bot5">
															<span class="inp_file2" >  
								                            <input type="text" title="파일을 넣으세요" style="height:17px;">
								                            <a href="#" class="btn_file">								                            	
								                            	<input type="file" class="file2" size="1" title="찾기" id="apndFileAddw" name="upFile">								                            	
								                                <a style="cursor:pointer;" onclick="javascript:fnAddFileList()" class="btn_grid2"><span class="btn_text">추가</span></a>
								                            </a>
								                        	</span>
								                        	
								                    	</li>
          							                </form>								                    
												</ul>
											</div>
											<a class="ico_sredel" style="cursor:pointer;" onclick="javascript:fnAppendMenuRemove();" title="첨부없이 일반쓰기로 돌아갑니다."><!--삭제--></a>
										</div>
										 										 
									</div>
									
									
									<!--btn_area-->
									<div class="btn_area">
										<div class="fl_left ma_lef5">
											<!-- <span class="nick">닉네임</span> -->
											<a id="btn_img" href="#" class="btn_img" title="이미지">이미지</a>
											<a id="btn_file" href="#" class="btn_file3" title="파일">파일</a>
										</div>
										<div class="fl_right">
											공개설정
												<select name="notiOpenDiv" id="notiOpenDiv">
													<option value="010" selected>전체공개
													<option value="020">운영자만공개
													<option value="030">부서지정
												</select>
											<a href="#" class="btn_reg" id="btn_context_write">등록</a>
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
					<div class="title_add"  id="ajax_indicator" style="display:none;" >
						<span><img src="${RES_HOME}/images/img/loadinfo.gif"/>로딩중입니다. 잠시만 기다려주세요...</span>
					</div>
				</div>	
				
				<div id="show_dialog"></div>
				
				<iframe id="dummy" name="dummy" width=0 height=0></iframe>

 --%>			 