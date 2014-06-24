<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	
	var loadingComplete = false;
	
	var write_apnd_kind = '010';
	var jsonAppendImgList = [];  //이미지 리스트
	var jsonAppendMovList = [];  //동영상 리스트
	var jsonAppendResearchList = [];//설문 보기 리스트
	var jsonAppendFileList = [];  //첨부 리스트
	
	var tmpNotiSeq = '${tmpNotiSeq}';
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
	var notiId = '${notiId}';
	var basicCloseDttm = '${basicCloseDttm}';
	var boardForm = '${boardForm}';
	var boardFormSpec = '${boardFormSpec}';
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
	var surveyUploadMax = ${surveyUploadMax};
	var surveyUploadView = ${surveyUploadView};
	
	var upNotiId = '${upNotiId}';
	
	var apndFileSz = '${apndFileSz}';  //파일 업로드 제한사이즈
	
	if (boardId == 'BBS999999')
	{
		apndFileSz = 50000;
	}
	
	var boardExplUseYn = '${boardExplUseYn}';
	var boardExpl = '${boardExpl}';
	var webMovieDir = "";
	var WEB_MOVIE_DIR = '${WEB_MOVIE_DIR}';
	
	//파일업로드목록
	var savedApndList = ${apndList};
	var userMapList = ${userMapList};
	
	//임시
	var tmpNotiList = ${tmpNotiList};
	var tmpApndList = ${tmpApndList};
	var tmpUserList = ${tmpUserList};
	var tmpSurveyList = ${tmpSurveyList};
	var tmpSurveyExmplList = ${tmpSurveyExmplList};
	
	//글수정
	var notiList = ${notiList};
	var surveyList = ${surveyList};
	var surveyExmplList = ${surveyExmplList};
	
	//답글
	var reply_list = ${reply_list};
	
	
	// -------------------------------------------------------------------------------
	
	$(function(){
		//이미지 전송		
		$("input[id^=apndImg]").change(function(e) {
			var form_id = $(this).parent().attr("id");
			
			if(!PortalCommon.imgUploadFileCheck($(this).val())){
				alert("추가할 수 없는 파일입니다.");
				return;
			}
		
			
	 		$("#"+form_id).ajaxSubmit({
				url : WEB_HOME+"/board230/bbsFileUpload.do",
				type : 'POST',
				data : $("#"+form_id).serialize(),
				action: $("#dummy"),
				success : function(data){			
					loadImageList2(form_id, $.parseJSON(data));
				},error : function(){
					alert("전송 실패 했습니다.");
				},
				clearForm: true,
				resetForm: true
			});	
		});
		
		//이미지 추가
		var loadImageList2 = function(form_id, obj)
		{
			var json = obj[0];
			
			$('<li id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'"  width="124" height="124"  alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($("#"+form_id));
// 			$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="/portalxpert.template/upload/test.jpg" width="124" height="124" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($("#"+form_id));

			var jsonObject = {
				 'notiId' : ''
				, 'apndFileSeq' : jsonAppendImgList.length+1
				, 'apndFileTp' : '020'
				, 'apndFileSz' : json.saveFileSize
				, 'apndFileOrgn' : json.original
				, 'apndFileName' : json.saveFileName
				, 'apndFilePath' : json.saveDir
				, 'apndFilePrvwPath' : json.webDir
				, 'apndFilePrvwName' : json.saveFileName
				, 'sourceCodeInput' : ''
				, 'adminRcmdYn' : ''
				, 'adminRcmdDttm' : ''
				, 'readCnt' : '0'
				, 'delYn' : 'N'
				, 'regrId' : ''
				, 'regrName' : ''
				, 'regDttm' : ''
				, 'updrId' : ''
				, 'updrName' : ''
				, 'updDttm' : ''
				, 'mvpKey' : ''
			};		

			
			jsonAppendImgList.push(jsonObject);
			
		};		
		
	});
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board230Write.js"></script>
<script type="text/javascript" src="${RES_HOME}/js/portal/editor.js"></script>
</head>

<body>

<div class="container">
<br/>
<div class="header">
	<h1>${boardName}</h1>
	<div class="loc">
		<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
		<span><a href="#">커뮤니티</a></span>
		<span><strong>${boardName}</strong></span>
	</div>
</div>
<ul style="font-size:13px; line-height:18px; color:#435454">
	<li>게시판 게시기준에 맞지 않는 부적절한 게시물은 작성자의 동의 없이 삭제됩니다.</li>
	
</ul>

<!-- 버튼영역 -->
<div class="btn_board_sec">
	<div class="fl">
		<a href="#" class="btn_set bt_style2"><span>미리보기</span></a>
		<a href="#" class="btn_set bt_style2" onclick="javascript:fnWriteTempInsert();"><span>임시저장</span></a>
		<a href="#" class="btn_set bt_style2" onclick="javascript:fnWriteCancel();"><span>취소</span></a>
	</div>
	<div class="fr">
		<a href="#" class="btn_set bt_style3" onclick="javascript:fnWriteInsert();"><span>등록</span></a>
		<a href="#" class="btn_set bt_style4" id="btn_item_list"><span>목록</span></a>
	</div>
</div>
<!-- //버튼영역 -->
<br/>

<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
<caption>제목</caption>
<colgroup>
	<col style="width:15%" />
	<col style="width:35%" />
	<col style="width:15%" />
	<col style="width:35%" />
</colgroup>
<tbody>
<tr>
	<th scope="row"><label for="input02">소속기관</label></th>
	<td>${deptName}</td>
	<th scope="row"><label for="input03">작성자</label></th>
	<td>${userName}</td>
</tr>
<tr>
	<th scope="row"><label for="input01">제목</label></th>
	<td colspan="3">
		<input type="text" id="txt_title" class="text" style="width:500px" title="제목을 입력합니다." />
		<div class ="fr">
			<input type="checkbox" id="rt4" title="선택합니다." />
			<label for="check01">공지</label>
		</div>
	</td>
</tr>
<tr>
	<th scope="row"><label for="input04">공개 대상</label></th>
	<td colspan="3">
		<select name="notiOpenDiv" id="notiOpenDiv">
			<option value="010" selected>전체공개
			<option value="020">운영자만공개
			<option value="030">부서지정
		</select>
<!-- 		<a href="#" class="btn_set bt_style1" onclick=""><span>조직도</span></a> -->
	</td>
</tr>
<tr>
	<th scope="row">
	<td colspan="3">
		<div>
			<ul id="OpenDeptCategories">
			</ul>
			<ul id="OpenEmpCategories" style="display:none;">
			</ul>
		</div>	
<!-- 		<textarea id="textbox01" cols="100" rows="5" maxlength="300" class="textbox" title="선택조직도">선택조직도.</textarea> -->
	</td>
</tr>
<tr>
<td colspan="4"> 
	<textarea class="editor ma_none" id="editor" style="height:350px;"></textarea>
</td>
</tr>
</tbody>
</table>

<!-- 버튼영역 -->
<div class="btn_board_sec">
	<div class="fl">
		<a href="#" class="btn_set bt_style2"><span>미리보기</span></a>
		<a href="#" class="btn_set bt_style2" onclick="javascript:fnWriteTempInsert();"><span>임시저장</span></a>
		<a href="#" class="btn_set bt_style2" onclick="javascript:fnWriteCancel();"><span>취소</span></a>
	</div>
	<div class="fr">
		<a href="#" class="btn_set bt_style3" onclick="javascript:fnWriteInsert();"><span>등록</span></a>
		<a href="#" class="btn_set bt_style4" id="btn_item_list"><span>목록</span></a>
	</div>
</div>
<!-- //버튼영역 -->
</div>

<div style="display:none;">
	<input type="radio" name="apndKind" value="010" checked>일반
	<input type="radio" name="apndKind" value="020">이미지
	<input type="radio" name="apndKind" value="030">동영상
</div>

</body>
</html>		

<%--


	<div>${boardName}</div>
	<div><input type="button" value="임시저장" onclick="javascript:fnWriteTempInsert();"><input type="button" value="목록" id="btn_item_list"><input type="button" value="완료" onclick="javascript:fnWriteInsert();"></div>
	<div>제목 <input type="text" id="txt_title"></div>
	<div>작성자 ${userName}</div>
	<div>소속기관</div>
	<div><input type="checkbox" id="rt4">공지</div>
	<div>공개설정
		<select name="notiOpenDiv" id="notiOpenDiv">
			<option value="010" selected>전체공개
			<option value="020">운영자만공개
			<option value="030">부서지정
		</select>
	</div>
	<div>
		부서
		<ul id="OpenDeptCategories">
		</ul>
		개인
		<ul id="OpenEmpCategories">
		</ul>
	</div>
	
	<div>
		<input type="radio" name="apndKind" value="010" checked>일반
		<input type="radio" name="apndKind" value="020">이미지
		<input type="radio" name="apndKind" value="030">동영상
	</div>
	
	<ul style="list-style-type:none;float:left;">
	    <li style="border:1px dashed #999999;width:124px; height:124px;list-style-type:none;float:left;">
	    <input type="radio" name="seq">
		<form id="bbsImgform1" name="bbsImgform1" enctype="multipart/form-data" method="post">
		<input type="file" size="1" title="이미지추가" id="apndImg1" name="bbsUpImg1" style="position:relative;left:0;top:0;width:124px; height:124px; opacity:0; filter:alpha(opacity:0);">
		</form>
		</li>

	    <li style="border:1px dashed #999999;width:124px; height:124px;list-style-type:none;float:left;">
	    <input type="radio" name="seq">
	    <form id="bbsImgform2" name="bbsImgform2" enctype="multipart/form-data" method="post">
		<input type="file" size="1" title="이미지추가" id="apndImg2" name="bbsUpImg2" style="position:relative;left:0;top:0;width:124px; height:124px; opacity:0; filter:alpha(opacity:0);">
		</form>
		</li>

	    <li style="border:1px dashed #999999;width:124px; height:124px;list-style-type:none;float:left;">
	    <input type="radio" name="seq">
	    <form id="bbsImgform3" name="bbsImgform3" enctype="multipart/form-data" method="post">
		<input type="file" size="1" title="이미지추가" id="apndImg3" name="bbsUpImg3" style="position:relative;left:0;top:0;width:124px; height:124px; opacity:0; filter:alpha(opacity:0);">
		</form>
		</li>

	    <li style="border:1px dashed #999999;width:124px; height:124px;list-style-type:none;float:left;">
	    <input type="radio" name="seq">
	    <form id="bbsImgform4" name="bbsImgform4" enctype="multipart/form-data" method="post">
		<input type="file" size="1" title="이미지추가" id="apndImg4" name="bbsUpImg4" style="position:relative;left:0;top:0;width:124px; height:124px; opacity:0; filter:alpha(opacity:0);">
		</form>
		</li>

	    <li style="border:1px dashed #999999;width:124px; height:124px;list-style-type:none;float:left;">
	    <input type="radio" name="seq">
	    <form id="bbsImgform5" name="bbsImgform5" enctype="multipart/form-data" method="post">
		<input type="file" size="1" title="이미지추가" id="apndImg5" name="bbsUpImg5" style="position:relative;left:0;top:0;width:124px; height:124px; opacity:0; filter:alpha(opacity:0);">
		</form>
		</li>
	</ul>


	
	<div style="clear:both">
	
	동영상파일올리기
	<div id="movieImgformDiv">
	<ul>
	    <li>
	    <form id="movieImgform" name="movieImgform" enctype="multipart/form-data" method="post">
	    <input type="text" style="width:100px">
		<input type="file" size="1" title="동영상추가" id="apndMovie" name="bbsUpMovie">
		<input type="button" onclick="fnAddMovieFileList()" value="추가">
		</form>
		</li>	
	</ul>
	</div>
	<div id="movieFileDiv" style="width:97%; height:130px; overflow:auto ">
		<dl></dl>
	</div>	
	
	
	<textarea class="editor ma_none" id="editor" style="height:100px;"></textarea>
	</div>
	<div>
		<form id="apndFileform" name="apndFileform" enctype="multipart/form-data" method="post">
		<ul>
		<li>
		<input type="text" style="width:100px">
		<div style="border:1px dashed #999999;width:42px; height:23px;">
	 	<input type="file" size="1" name="upFile-" style="position:relative;left:0;top:0;width:42px; height:23px; opacity:0; filter:alpha(opacity:0);">
	 	</div>
	 	<input type="button" onclick="fnAddFileList()" value="추가">
	 	</li>
	 	</ul>
	 	</form>
	</div>
	<div id="innoApDiv" style="width:97%; height:130px; overflow:auto ">
		<dl></dl>
	</div>	
	
	<div><input type="button" value="임시저장" onclick="javascript:fnWriteTempInsert();"><input type="button" value="목록" id="btn_item_list"><input type="button" value="완료" onclick="javascript:fnWriteInsert();"></div>

 --%>	 