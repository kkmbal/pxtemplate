	




	
	var fnGetBoardNotiIdInfo = function(){
		var jsonNotiObject = {
				'boardId' : '' , 
				'notiId' : ''
			};
		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;

		return JSON.stringify(jsonNotiObject);
	};
	
	//설문질문
	var fnApndSurveyList = function(jsonList)
	{
		$("#board_survey").empty();
		for(var i=0;i< jsonList.length;i++){
 			var no = $("#board_survey").find('dl').length+1;
 			if (jsonList[i].surveyTp == '020')  //보기형식:이미지
 			{
 				$("#board_survey").append(
 						'<div class="sns_poll">'
 						+'	<div class="sns_pollre">'				
 						+'		<dl>'
 						+'			<dt><span>질문'+no+'.</span>'+jsonList[i].surveyConts+'</dt>'				
 						+'			<dd class="clearfix imgex">'
 						+'				<ul id="surveyExmpl-'+jsonList[i].surveyNo+'">'
 						+'				</ul>'
 						+'			</dd>'
 						+'		</dl>'
 						+'  </div>'												
 						+'</div>'
 					);
 			}
 			else  //텍스트
 			{
 				$("#board_survey").append(
 						'<div class="sns_poll">'
 						+'	<div class="sns_pollre">'				
 						+'		<dl>'
 						+'			<dt><span>질문'+no+'.</span>'+jsonList[i].surveyConts+'</dt>'				
 						+'			<dd class="clearfix">'
 						+'				<ul id="surveyExmpl-'+jsonList[i].surveyNo+'">'
 						+'				</ul>'
 						+'			</dd>'
 						+'		</dl>'
 						+'  </div>'										
 						+'</div>'
 					);
 			}
 		}
	};
	
	//설문제출정보
	var fnApndSurveySubJectList = function(jsonList)
	{
		var json = jsonList[0];
		if ($("#board_survey").find('p').length == 0)
		{
			$("#board_survey").append(
				'	<p class="fo_byte"><span class="fo_bold">설문 마감일시</span>'+json.surveyClosDttm+'</p>'
				+'	<div class="btn_area">'
				+'		<div class="fl_left">'
				+'			<a id="surveySubject-'+json.surveyNo+'" style="cursor:pointer;" onclick="javascript:fnsurveySubject(\''+json.notiId+'\')" class="btn_basic ">'
				+'				<span class="btn_text fo_bold">설문제출</span>'
				+'			</a>'
				+'			<a id="surveyResult-'+json.surveyNo+'" style="cursor:pointer;" onclick="javascript:fnsurveyResult(\''+json.notiId+'\')" class="btn_basic ">'
				+'				<span class="btn_text">설문결과</span>'
				+'			</a>'
				/* +'			<a href="#" class="btn_basic">'
				+'				<span class="btn_text">출력</span>'
				+'			</a>' */
				+'		</div>'
				+'	</div>'	
					
			);
		}			
	};
	
	//설문에 대한 보기추가
	var fnApndSurveyExmplList = function(jsonList)
	{	
		for(var i =0 ; i < jsonList.length; i++){
			var json = jsonList[i];
			if ($("#surveyExmpl-"+json.surveyNo).parent().attr('class') == 'clearfix imgex')
			{
				
				if (json.answExmplNo == json.exmplNo)
				{
					$("#surveyExmpl-"+json.surveyNo).prepend(
						'<li><input type="radio" title="" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" checked><label for="exmpl-'+json.exmplNo+'"><img id="exmplimg-'+json.exmplNo+'" src="'+json.imgPath+'/'+json.imgName+'" alt="올린이미지"></label></li>'
					);
				}
				else
				{
					$("#surveyExmpl-"+json.surveyNo).prepend(
							'<li><input type="radio" title="" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" ><label for="exmpl-'+json.exmplNo+'"><img id="exmplimg-'+json.exmplNo+'" src="'+json.imgPath+'/'+json.imgName+'" alt="올린이미지"></label></li>'
						);
				}
				
				$("#exmplimg-"+json.exmplNo).bind("load",function(){
					fnSurveyImgEffect();
				});
			}
			else
			{
				if (json.answExmplNo == json.exmplNo)
				{
					$("#surveyExmpl-"+json.surveyNo).prepend(
						'<li><input type="radio" title="'+json.exmplConts+'" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" checked><label for="exmpl-'+json.exmplNo+'">'+json.exmplConts+'</label></li>'
					);
				}
				else
				{
					$("#surveyExmpl-"+json.surveyNo).prepend(
							'<li><input type="radio" title="'+json.exmplConts+'" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" ><label for="exmpl-'+json.exmplNo+'">'+json.exmplConts+'</label></li>'
					);
				}
			}
		}
	};
	
	//설문제출
	var fnsurveySubject = function(id)
	{
		var surveyAnswList = [];
		
		var surveyExmpl = $("#board_survey").children().find('dd');
		
		for(var i=0; i < surveyExmpl.length; i++)
		{
			var view = surveyExmpl.eq(i);			
			var li_view = view.find('ul');
			
			var radio = li_view.find('input[type=radio]:checked').attr('id');
			if (radio == undefined)
			{
				alert('설문에 대한 보기를 선택하세요');
				return;
			}
			var jsonObject = {
					 'surveyNo' : li_view.attr('id').replace("surveyExmpl-","")
					, 'answmanId' : ''
					, 'answmanName' : ''
					, 'answExmplNo' : '0'
					, 'delYn' : 'N'
					, 'regrId' : ''
					, 'regrName' : ''
					, 'regDttm' : ''
					, 'updrId' : ''
					, 'updrName' : ''
					, 'updDttm' : ''
				};
			jsonObject.answExmplNo = radio.replace("exmpl-","");;
			surveyAnswList.push(jsonObject);
		}
		fnSurveyAnswInsert(JSON.stringify(surveyAnswList));
	};
	
	//설문 결과 저장
	var fnSurveyAnswInsert = function(data){
		PortalCommon.getJson({
			url: WEB_HOME+"/person300/insertBbsNotiSurveyAnsw.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					//alert('설문제출이 정상적으로 처리되었습니다.');
				}
			}
		});
	};
	
	var fnsurveyResult = function(notiId)
	{
		PortalCommon.popupWindowModal(WEB_HOME+'/person300/person300SurveyRst.do?tmlnSeq=0&notiId='+notiId,'surveyPop',450,550);
	};
	
	var fnSurveyImgEffect = function()
	{
	   $obj = $('.imgex label');
			for( var j=0; j < $obj.length+1; j++){
			$('.imgex label img').each(function(){
				if ($(this).width() >= $(this).parents('label').width()){
				    $(this).css( {
				   		 'width': '100%',
				   		 'margin-left': "-" + $(this).width()/2 +"px",
						 'margin-top': "-" + $(this).height()/2 +"px"
					});
				} else if($(this).width() < $(this).parents('li').width()){
					$(this).css({
						'width':'auto',
						'margin-left': "-" + $(this).width()/2 +"px",
						'margin-top': "-" + $(this).height()/2 +"px"		
					});
				};
			});
		};
	};
	
	//게시글 상세정보 조회
	var fnGetNotiDetailInfoView = function(){
		
		PortalCommon.getJson({
			url: WEB_HOME+"/adm/board/getNotiDetailInfoView.do?format=json",
			data: {  'data' : fnGetBoardNotiIdInfo(), 'pnum' : pnum }, 
			success :function(data){
				if(data.jsonResult.success ===true){

					$("#noti_reply").val("");
					//$("#msgObj_MYID").val(data.regrIdEncrypt);//메선저연동
					notiInfo = $.parseJSON(data.notiInfo);//게식
					notiFile = $.parseJSON(data.notiFile);
					notiOpn1 = $.parseJSON(data.notiOpn1);
					notiOpn2 = $.parseJSON(data.notiOpn2);
					notiPrevNextInfo = $.parseJSON(data.notiPrevNextInfo);
					notiKind = notiInfo[0].notiKind;
					
					if(data.notiPrevNextImgMovInfo){
						notiPrevNextImgMovInfo = $.parseJSON(data.notiPrevNextImgMovInfo);
					}
					
					if(notiKind == '040'){//설문
						isNotiSurvey = true;
						fnApndSurveyList($.parseJSON(data.surveyList));
						fnApndSurveySubJectList($.parseJSON(data.surveyList));
						fnApndSurveyExmplList($.parseJSON(data.surveyExmplList));
					}else if(notiKind == '030'){
						
						notiMov = $.parseJSON(data.movList);
					}
					
					fnSetDataNotiInfo(notiInfo[0]);	
				}
			}
	 	});
	};
	
	var fnSetFrameHeight = function(addHeight){
		parent.document.getElementById("admFrame").height = "700px";
		parent.document.getElementById("admFrame").height = Number($(document).height()+ addHeight )+"px";
		//parent.document.getElementById("bbsFrame").height = "700px";
		//parent.document.getElementById("bbsFrame").height = (document.body.scrollHeight+ addHeight)+"px";
		
	};
	
	//링크
	var fnOpenLinkUrl = function(linkUrl, _notiId){
		
		var jsonObj = {
				'notiId' : '',
				'notiEvalDiv' : ''	
			};
		
		jsonObj.notiId = _notiId;
		jsonObj.notiEvalDiv = '040';//읽음
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiEvalInfoForLink.do?format=json",
			data: {  'data' : JSON.stringify(jsonObj) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					
				}
			}
	 	});
		
 		if(linkUrl.indexOf("http://") == -1){
 			linkUrl = "http://"+linkUrl;
 		}
		window.open(linkUrl);
		
	};
	
	//일반 게시글 이전글 다음글 
	var fnGetPrevNextNotiInfo = function(notiJson){
		
		$("#boardPage").append('<ul class="post_slt" id="post_sltUl"></ul>');	
		$("#post_sltUl li").remove();
		if(notiJson !=null){
			
			if(notiJson.prevNotiId != null){
				if(notiJson.prevNotiKind =='060'){
					$("#post_sltUl").append('<li><a href="javascript:fnOpenLinkUrl(\''+notiJson.prevLinkUrl+'\' , \''+notiJson.prevNotiId+'\')" id="prevNotiId"><span class="bl_prev">이전글</span><span id="prevNotiTitle">'+notiJson.prevNotiTitle+'</span></a></li>');					
				}else{
					$("#post_sltUl").append('<li><a href="javascript:fnGetBoardView(\''+notiJson.prevNotiId+'\' , \''+prev_pnum+'\')" id="prevNotiId"><span class="bl_prev">이전글</span><span id="prevNotiTitle">'+notiJson.prevNotiTitle+'</span></a></li>');	
				}
				
			}else{
				$("#post_sltUl").append('<li><span class="bl_prev">이전글</span><span id="prevNotiTitle">처음 게시물 입니다.</span></li>');
			}
			if(notiJson.nextNotiId != null){
				
				if(notiJson.nextNotiKind == '060'){
					$("#post_sltUl").append('<li><a href="javascript:fnOpenLinkUrl(\''+notiJson.nextLinkUrl+'\' , \''+notiJson.nextNotiId+'\')" id="nextNotiId"><span class="bl_next">다음글</span><span id="nextNotiTitle">'+notiJson.nextNotiTitle+'</span></a></li>');											
				}else{
					$("#post_sltUl").append('<li><a href="javascript:fnGetBoardView(\''+notiJson.nextNotiId+'\' , \''+next_pnum+'\')" id="nextNotiId"><span class="bl_next">다음글</span><span id="nextNotiTitle">'+notiJson.nextNotiTitle+'</span></a></li>');
				}
				
			}else{
				$("#post_sltUl").append('<li><span class="bl_next">다음글</span><span id="nextNotiTitle">마지막 게시물 입니다.</span></li>');
			}
		}
		
		fnSetFrameHeight(230);
	};
	
	//이미지형 게시글 이전글 다음글 
	var fnGetImgNotiPrevNextInfo = function(param){
		
		if(notiPrevNextImgMovInfo){
			if(notiPrevNextImgMovInfo.length < 1) return ;

			$("#boardPage div").remove();
			$("#boardPage").append('<div class="page_vod"></div>');
			if(prev_last){
				$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(1)" class="btn_next" title="다음" id="btn_next_img"></a>');
			}else if(next_last){
				$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(-1)" class="btn_prev" title="이전" id="btn_prev_img"></a>');
			}else{
				$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(-1)" class="btn_prev" title="이전" id="btn_prev_img"></a><a href="javascript:fnNotiPrevNextImgMovInfoView(1)" class="btn_next" title="다음" id="btn_next_img"></a>');
			}
			$("#boardPage div").append('<ul class="page_list clearfix" id="board_mov">');
			
			
			for(var i=0; i<notiPrevNextImgMovInfo.length; i++){
				if(boardFormSpec == '010'){
					$("#board_mov").append('<li class="ver_top">' +
							' 			<div class="box_vod">' +
							' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="${imgSvrUrl}/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판이미지"></a></p>' +
							' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><span class="te_dot"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')">'+notiPrevNextImgMovInfo[i].notiTitle+'</a></span><span class="fo_replyn">[의견:'+notiPrevNextImgMovInfo[i].opnCnt+']</span></p>' +
							' 				<p class="name">'+notiPrevNextImgMovInfo[i].updrName+'<span class="hits">'+notiPrevNextImgMovInfo[i].notiReadCnt+'</span></p>' +
							' 				<p class="fo_byte">'+notiPrevNextImgMovInfo[i].notiBgnDttm+'</p>' +
							' 			</div>' +
							' 		</li>');
				}else if(boardFormSpec == '020'){
					$("#board_mov").append('<li class="ver_top">' +
							' 			<div class="box_vod">' +
							' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="${movDir}/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판동영상"></a></p>' +
							' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><span class="te_dot"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')">'+notiPrevNextImgMovInfo[i].notiTitle+'</a></span><span class="fo_replyn">[의견:'+notiPrevNextImgMovInfo[i].opnCnt+']</span></p>' +
							' 				<p class="name">'+notiPrevNextImgMovInfo[i].updrName+'<span class="hits">'+notiPrevNextImgMovInfo[i].notiReadCnt+'</span></p>' +
							' 				<p class="fo_byte">'+notiPrevNextImgMovInfo[i].notiBgnDttm+'</p>' +
							' 			</div>' +
							' 		</li>');
					
				}
			}
		}
		
		if(param == 'go'){
			$("#goBottom").trigger("click");
		}else{
			fnSetFrameHeight(230);//이미지형일경우
		}
	};
	

	var fnNotiPrevNextImgMovInfoView = function(go){

		var jsonNotiObject = {
				'boardId' : '' , 
				'notiId' : '',
				'direction' : 0
			};
		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;

		if(notiPrevNextImgMovInfo.length <= 1){
			if(goIdx < 0 && go < 0){ goIdx = goIdx + 0; $("#btn_prev_img").hide(); prev_last = true;}
			else if(goIdx < 0 && go > 0){ goIdx = goIdx + go; $("#btn_prev_img").show(); $("#btn_next_img").show(); prev_last = false; next_last=false;}
			else if(goIdx > 0 && go < 0){ goIdx = goIdx + go; $("#btn_prev_img").show(); $("#btn_next_img").show(); prev_last = false; next_last=false;}
			else if(goIdx > 0 && go > 0){ goIdx = goIdx - 0; $("#btn_next_img").hide(); next_last = true;}
		}else{
			goIdx = goIdx + go;
		}
		jsonNotiObject.direction = goIdx;
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/notiPrevNextImgMovInfo.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					if(data.notiPrevNextImgMovInfo){
						notiPrevNextImgMovInfo = $.parseJSON(data.notiPrevNextImgMovInfo);
					}
					
					fnGetImgNotiPrevNextInfo('go');	
				}
			}
	 	});
	};
	
	
	
	
	//게시글 좋아요 사용자 조회
	var fnGetBbsNotiEvalInfoList = function(rnum){
		
		$('#linkUserLayer').css({
// 			'display': 'block',
// 			'position': 'absolute',
// 			'width': '180px'
		});
		var _rnum = 0;
		if(rnum > 0 ){
			_rnum = maxNum;
		}
		var jsonNotiObject = {
				'boardId' : '' , 
				'notiId' : '' ,
				'rnum' : ''
			};
		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;
		jsonNotiObject.rnum = _rnum;

		if($("#linkUserListDiv").attr("value") == "N"){
			
			$("#linkUserLayer").show();
			$("#linkUserListDiv").show();
			$("#linkUserListUl li").remove();
			PortalCommon.getJson({
				url: WEB_HOME+"/board210/getBbsNotiEvalInfoList.do?format=json",
				data: {  'data' : JSON.stringify(jsonNotiObject)}, 
				success :function(data){
					if(data.jsonResult.success ===true){
						var notiEval = $.parseJSON(data.notiEval);
						fnSetDataNotiEvalInfo(notiEval);
					}
					maxNum += notiEval.length;
				}
		 	});
			$("#linkUserListDiv").attr("value","Y");
		}else{
			$("#linkUserLayer").hide();
			$("#linkUserListDiv").hide();
			$("#linkUserListDiv").attr("value","N");
			maxNum = 0;
		}
	}; 
	
	var replaceAll = function(str,str1,str2){
		  return str.split(str1).join(str2);
	};
	
	var fnSetDataNotiInfo = function(notiJson){
		$("#boardPage ul").remove();
		regrId = notiJson.regrId;
		notiKind = notiJson.notiKind;
		nickUseYn = notiJson.nickUseYn;
		opnMakrRealnameYn = notiJson.opnMakrRealnameYn;//의견작성자 실명여부 
		userMail = notiJson.mail;
		opnPrmsYn = notiJson.opnPrmsYn;
		agrmOppYn = notiJson.agrmOppYn;
		
		$("#scrapNotiTitle").val("[스크랩] "+notiJson.notiTitleOrgn);
		$("#notiNum").html(notiJson.oldNoticeSeq);
		$("#notiReadCnt").html(notiJson.notiReadCnt);//조회수
		$("#opnCnt").html(notiJson.opnCnt);
		$("#notiLikeCnt").html(notiJson.evalLikeCnt);//좋아요
		$("#notiTitle").html(notiJson.notiTitle);
		$("#notiBgnDttm").html(notiJson.notiBgnDttm);
		if(notiJson.notiEndDttm == '9999-12-31'){
			$("#notiEndDttm").html('영구');
		}else{
			$("#notiEndDttm").html(notiJson.notiEndDttm);
		}
		$("#notiAgrmCnt").html(notiJson.evalAgrmCnt);//찬성
		$("#notiOppCnt").html(notiJson.evalOppCnt);//반대
		$("#notiTagList").html(notiJson.notiTagLst);
		$("#notiTagList").html(notiJson.notiTagLst);
		if(notiJson.moblOpenDiv == '010'){
			$("#moblOpenDiv").html("본문만 공개");	
		}else if(notiJson.moblOpenDiv == '020'){
			$("#moblOpenDiv").html("본문 + 첨부파일 공개");	
		}else if(notiJson.moblOpenDiv == '030'){
			$("#moblOpenDiv").html("공개하지 않음");	
		}
		if(nickUseYn == 'N'){//게시글의 닉네임 사용여부 
			
		}
		if(agrmOppYn == 'Y'){
			$("#agrmOppTd").show();
		}
		if(opnPrmsYn == 'Y'){
			$("#replyUl").show();
			$(".reply_post").show();
		}
		if(eamAdminYn == 'Y' || userId == regrId ){
			$(".btn_modify").show();
			$(".btn_delete").show();
		}
		if(notiJson.nickUseYn == 'Y' && notiJson.userNick !=null){
			$("#makrIp").html(fnGetIpUtil(notiJson.makrIp));
			$("#userName").html(notiJson.userNick);	
			$("#deptName").html("");
			$("#deptName").removeClass("read_info");
			$("#msgSpan").removeClass("read_info");
			$("#mailTo").removeClass("read_info");
 			$("#mailTo").html("");
			
		}else{
			$("#userName").html(notiJson.userName);
			$("#deptName").html(notiJson.deptFname);
			if(notiJson.mail ==null ){
				$("#mailTo").removeClass("read_info");
	 			$("#mailTo").html("");
			}
		}
		
		if(boardKind == '030'){//경조사 
			
			$("#cdlnObjrName").html(notiJson.cdlnObjrName);
			$("#cdlnDeptName").html(notiJson.cdlnDeptName);
			$("#cdlnDeptFname").html(notiJson.cdlnDeptFname);
		}
		
		if(notiKind == '010'){//일반
			
		}else if(notiKind == '020'){//이미지
			
		}
		
		if(replyWrteDiv == '010' ){//답글쓰기 사용
			$(".btn_reply").show();	
		}else if(replyWrteDiv == '030' ){//게시자 지정
			if(notiJson.replyPrmsYn == 'Y'){
				$(".btn_reply").show();	
			}
		}
		
		fnSetDataNotiFileInfo(notiFile);
		
		
		//게시물 메일발송	
		
	};
	
	var fnGetIpUtil = function(ip){
		var rtn = '';
		if(ip !=null && ip !="" ){
			var ipArray = ip.split(".");
			for(var i =0 ; i < ipArray.length; i++){
				if(i==2){
					ipArray[i] = "***";
				}
				rtn = rtn + ipArray[i] + ".";
			}
			rtn = rtn.substring(0, rtn.length-1);
		}
		
		return rtn;
	};
	

	
	//좋아요 사용자 목록 
	var fnSetDataNotiEvalInfo = function(notiEvalJson){
		$('#linkUserListUl li').remove();
		for (var i=0; i < notiEvalJson.length ; i++){
			$('#linkUserListUl').append('<li id="'+notiEvalJson[i].evalSeq+'">'
			+ '<a href="javascript:doLikeUserInfoPop(\''+ notiEvalJson[i].userId +'\')">'+ notiEvalJson[i].userName +'</a>'
			+ '<span class="te_dot">&nbsp;(' + notiEvalJson[i].ou +')</span>'
			+ '</li>');		
		}
		
		if (notiEvalJson.length > 48)  //50건씩 조회
		{
			$('<div id="inter-moreData" class="btn_area"><div class="fl_cen"><a onclick="fnGetBbsNotiEvalInfoList(50);" class="btn_basic2 pa_lef10"><span class="btn_text">더보기</span></a></div></div>').insertAfter($('#linkUserListUl'));
		}
		
		$("#linkUserListUl").parent("div").css({
			'min-height': '220px'
			, 'height': '220px'
			, 'visibility' : 'true'
			, 'overflow-x': 'hidden'
			, 'overflow-y': 'auto'
		});
	};
	
	//첨부파일
	var fnSetDataNotiFileInfo = function(notiFileJson){
		
	// 게시물_종류
	// 010: 일반
	// 020 : 이미지
	// 030 : 동영상
	// 031 : 동영상(소스코드)
	// 040 : 설문
	// 050 : 첨부
	// 060 : 링크
		
		$('#notiFileDl dd').remove();
		var apFileCnt = 0;
		for (var i=0; i < notiFileJson.length ; i++){
			if(notiFileJson[i].apndFileTp == '050'){
				$('#notiFileDl:last').append('<dd><a class="bl_file2 fo_blue" href="javascript:fnDoFileDown(\''+ notiFileJson[i].apndFileSeq +'\',\''+ notiFileJson[i].apndFileName +'\',\''+ notiFileJson[i].apndFileOrgn +'\')">'+notiFileJson[i].apndFileOrgn+'</a>'
						+'<span class="fo_gray">('+getFileSzForKb(notiFileJson[i].apndFileSz)+"kb"+')</span></dd>');	
				
				apFileCnt++;	
			 	    
			}
		}
		  
		if(apFileCnt > 0){
			$(".btn_apnd_save").show();
		}
		
		if(notiKind == "010"){//일반
			
		}else if(notiKind == "020"){//이미지
			
			$("#notiConts").addClass("te_center");
			$("#imgNotiConts li").remove();
			for (var i=0; i < notiFileJson.length ; i++){
				if(notiFileJson[i].apndFileTp == '020'){
					fnImgApndList(notiFileJson[i]);	
				}
			}
			
		}else if(notiKind == "030"){//동영상
			$("#movNotiConts p").remove();
			$("#movNotiConts img").remove();
			$("#movNotiConts object").remove();
			for (var i=0; i < notiFileJson.length ; i++){
				if(notiFileJson[i].apndFileTp == '030'){
					fnMovApndList(notiFileJson[i]);		
				}
			}
		}
		
		if(opnPrmsYn == 'Y'){//의견사용여부 
			fnSetDataNotiOpn1(notiOpn1);	
		}else{			
 			if(boardForm == '010' ){
 				fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
 			}else if(   (boardForm == '030' && boardFormSpec == '010') 
 					 || (boardForm == '030' && boardFormSpec == '020')
 						){//이미지 , 동영상형 
 				fnGetImgNotiPrevNextInfo();
 			}
		}
		fnSetFrameHeight(250);
		
	};
	
	//이미지형 게시글 
	var fnImgApndList = function(json)
	{

		var rtSize = PortalCommon.fnImgPreviewResize('${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName, 430, 300);
		//$("#imgNotiConts li").remove();
		//$("#imgNotiConts div").remove();
		$("#imgNotiConts").append(
				'<li style="border: 0;"><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.apndFileSeq+'\')" >'
				+'<img id="viewImg-'+json.apndFileSeq+'" onload="javascript:PortalCommon.fnImgPreviewOnloadResize(this, 430, 300)" src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'" alt="올린이미지"></a></li>'
				+'<div style="display:none;" align="center" id="dialog-'+json.apndFileSeq+'" title="'+json.apndFileOrgn+'">'
				+'<img src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'"></div>');
		
		$("#imgNotiConts").css('display','block');		
		$("#imgNotiConts").find('img').load(function(){
			fnImgEffect();
		});
		
	};

	//동영상형 게시글 (즉시실행)
	var fnMovApndList2 = function(json)
	{
		//$("#movNotiConts img").remove();
		//$("#movNotiConts object").remove();
		$("#movNotiConts").css('display','block');
		if(notiMov.length > 0 && notiMov[0].mvpKey){
			fnPlayMov();
		}else{
			var thumbnailImg = movDir+'/'+json.apndFilePath+'/'+json.apndFileName;
			$("#movNotiConts").append('<img src="'+thumbnailImg+'" alt="비디오파일" >');
		}
	};
		
	
	//동영상형 게시글 (클릭후실행)
	var fnMovApndList = function(json)
	{
		//$("#movNotiConts img").remove();
		//$("#movNotiConts object").remove();
		$("#movNotiConts").css('display','block');

		//var thumbnailImg = movDir+'/'+json.apndFilePath+'/'+json.apndFileName;
		var thumbnailImg = movDir+"/movie_process.jpg";
		$("#movNotiConts").append('<a href="javascript:fnPlayMov();" id="mvp_'+json.mvpKey+'"><img src="'+thumbnailImg+'" alt="비디오파일" ></a>');
	};
	
	//동영상 (원래창)
	var fnPlayMov2 = function(){
		if(notiMov.length > 0 && notiMov[0].mvpKey){
			$("#movNotiConts > #mvp_"+notiMov[0].mvpKey).remove();
		
			var thisMvpKey = WEB_HOME+"/common/player/Player.swf?key="+notiMov[0].mvpKey;
			movPlayerObj = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,115,0" width="406" height="358" id="main" align="middle"> '
						+ '	<param name="allowScriptAccess" value="always" />                                                                                                                                                            '
						+ '	<param name="allowFullScreen" value="true" />                                                                                                                                                                '
						+ '	<param name="movie" value="'+thisMvpKey+'" />                                                                                                                      '
						+ '	<param name="quality" value="high" />                                                                                                                                                                        '
						+ '	<param name="bgcolor" value="#ffffff" />                                                                                                                                                                     '
						+ '</object>                                                                                                                                                                                                     ';
	
			$("#movNotiConts").append(movPlayerObj);
		}
	};
	
	//동영상 (새창)
	var fnPlayMov = function(){
		if(notiMov.length > 0 && notiMov[0].mvpKey){
			var w = 640;
			var h = 560;
			var winl = (screen.width-w)/2;
			var wint = (screen.height-h)/2;
			var settings  ='height='+h+',';
			settings +='width='+w+',';
			settings +='top='+wint+',';
			settings +='left='+winl+',';
			settings +='toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no';
			var newWin = window.open ("about:blank", "mov", settings);
		
			var thisMvpKey = WEB_HOME+"/common/player/Player.swf?key="+notiMov[0].mvpKey;
			movPlayerObj = '<table  cellspacing="0" style="border:1px solid #DEDEDE;">'
			            + '<tr>'
			            + '<td style="padding:7px 21px;border-bottom:1px solid #DEDEDE;"><span style="font:bold 14px dotum;color:#01669A;">'+$("#notiTitle").text()+'</span></td>'
			            + '<tr>'
			            + '<td style="padding:7px 21px;border-bottom:1px solid #DEDEDE;">'
						+ '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,115,0" width="580" height="480" id="main" align="middle"> '
						+ '	<param name="allowScriptAccess" value="always" />                                                                                                                                                            '
						+ '	<param name="allowFullScreen" value="true" />                                                                                                                                                                '
						+ '	<param name="movie" value="'+thisMvpKey+'" />                                                                                                                      '
						+ '	<param name="quality" value="high" />                                                                                                                                                                        '
						+ '	<param name="bgcolor" value="#ffffff" />                                                                                                                                                                     '
						+ '</object>                                                                                                                                                                                                     '
						+ '</td></tr></table>';
	
			newWin.document.write(movPlayerObj);
		}
	};
	
	var fnImgEffect = function()
	{
		$obj = $("#imgNotiConts li");		
		$obj.removeClass('one');
		$obj.removeClass('two');
		$obj.removeClass('three');
		
		var imglength = $obj.length;
		if (imglength == 1){
			$obj.addClass('one');
		} else if (imglength == 2){
			$obj.addClass('two');
		} else if (imglength >= 3){
			$obj.addClass('three');
		};
		
		if (imglength > 1)
		{
			for(var i=0; i < imglength; i++)
			{
				
			    var img = $obj.eq(i).find('img');
			    
			    var rtSize = PortalCommon.fnImgPreviewResize(img.attr('src'), 120, 120);
			    
			    img.css( {
			         'width': rtSize[0]+"px",
			         'height': rtSize[1]+"px",
			         'margin-left': "-" + rtSize[0]/2 +"px",
			      'margin-top': "-" + rtSize[1]/2 +"px"
			    });	
			    
				/*
				var img = $obj.eq(i).find('img');
				if (img.width() >= $obj.eq(i).width())
				{
					var t = Math.abs(img.height()-$obj.eq(i).height())/2+'px';
					if (img.height() > $obj.eq(i).height())
					{
						t = 0+'px';
					}
					img.css( {
				   		 'width': '100%'
					});
					
					img.css( {
						'margin-left': '0px',
						'margin-top': '0px',
				   		 'left': 0+"px",
						 'top': t
					});
					
				}
				if(img.width() < $obj.eq(i).width())
				{
					img.css({
						'width':'auto',
						'margin-left': "-" + img.width()/2 +"px",
						'margin-top': "-" + img.height()/2 +"px"		
					});	
				}
				*/
			}
		}
		
		if (imglength == 1)
		{
			var img = $obj.eq(0).find('img');
			if (img.width() >= $obj.eq(0).width())
			{
				img.css( {
			   		 'width': '100%',
			   		 'margin-left': "-" + $obj.eq(0).width()/2 +"px",
					 'margin-top': "-" + $obj.eq(0).height()/2 +"px"
				});
				
			}
			else if(img.width() < $obj.eq(0).width())
			{
				img.css({
					'width':'auto',
					'margin-left': "-" + img.width()/2 +"px",
					'margin-top': "-" + img.height()/2 +"px"		
				});	
			}
		}
	};
	
	//클릭한 이미지 미리보기
	var fnImgPreview = function(id)
	{
		var img_path = $( "#dialog-"+id ).find("img").attr("src");
		
		var rtSize = PortalCommon.fnImgPreviewResize(img_path, 750, 640);
		
		parent.$('[id^="dialog-"]').remove();
		parent.$('#container').prepend(
				'<div id="dialog-'+id+'" align="center">'
				+'<img src="'+img_path+'" width="'+rtSize[0]+'" height="'+rtSize[1]+'" >'
				+'</div>'
		);
		
		
		parent.$( "#dialog-"+id ).dialog
		(
				{      
					height: rtSize[1]+70,      
					width: rtSize[0]+50,
					modal: true
				}
		);
	};
	
	//게시물 이동
	var fnDoMoveNoti = function(boardId){
		
		if(regrId != userId){
			alert("게시자만 이동이 가능합니다.");
			return;
		}
		var jsonMoveArray = [];

		var jsonBoardInfoObject = {
				'boardId'   : '', //게시판 ID
				'notiId'    :[]
		};		
		
		var jsonObject = {
    			'id' : notiId
    	};
		jsonMoveArray[0] = jsonObject;
		jsonBoardInfoObject.boardId = boardId;//이동 대상 게시판아이디
		jsonBoardInfoObject.notiId = jsonMoveArray;
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiInfoForMove.do?format=json",
			data: {  'moveData' : JSON.stringify(jsonBoardInfoObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnDoList();
				}
			}
	 	});
	};
	
	//의견1 조회 (본문의 의견)
	var fnSetDataNotiOpn1 = function(notiOpn1){
		var pName = '', ip = '',appendBtn='';
		$("#replyUl div").remove();
		for (var i=0; i < notiOpn1.length ; i++){
			
			if(opnMakrRealnameYn == 'Y'){//실명공개 
				ip = '';
				pName = '<a href="javascript:doLikeUserInfoPop(\''+notiOpn1[i].userId+'\')" class="tit">'+notiOpn1[i].userName+'</a>';
			}else{//비실명 
				ip = fnGetIpUtil(notiOpn1[i].makeIp);
				pName = '의견'+Number(notiOpn1.length - i);
			}
			if(userId == notiOpn1[i].userId){//권한이 있는 사용자 혹은 작성자 
				appendBtn = '<div class="innerbox link"><a id="btnModify_'+notiOpn1[i].notiOpnSeq+'" modiMode="N" class="link" href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">수정</a></div>'
				+'<div class="innerbox link"><a id="btnDel_'+notiOpn1[i].notiOpnSeq+'"    class="link" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">삭제</a></div>';
			}else{
				appendBtn = '';
			}
			
			appendBtn = '';
			
			/*
			$("#replyUl").append(
					'<li>'
					+'<div class="clearfix" id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">'	
					+'	<dl>'
					+'		<dt><span class="fo_bold fo_12px" notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</span><span class="fo_byte">'+ip+'</span><span>'+notiOpn1[i].regDttm+'</span>'
					+'			<a class="fo_green" href="#" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'" id="btnOpn_'+notiOpn1[i].notiOpnSeq+'">의견</a>'+appendBtn+'</dt>'
					+'		<dd id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</dd>'
					+'	</dl>'
					+'	<div id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="clearfix reply_post2 reply_mod">'
					+'  	<textarea id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="5" rows="3">'+notiOpn1[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
					+'		<a class="btn_reup" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" title="의견등록">의견등록</a>'
					+'	</div>'
					+'</div>'
					+'</li>');
					*/
			
			$("#replyUl").append(
			'<div id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">' 
			+'<div>' 
			+'	<div class="innerbox tit"  notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</div>'
			+'	<div class="innerbox">'+notiOpn1[i].regDttm+'</div>'
			+'	<div class="innerbox link"><a class="link" href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'" id="btnOpn_'+notiOpn1[i].notiOpnSeq+'"></a></div>'
			+   appendBtn
			+'</div>' 
			+'<div>' 
			+'	<div class="answer fl" id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</div>' 
			+'</div>' 	
			+'	<div id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="reply_post2 reply_mod">'
			+'  	<textarea class="textbox" id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="5" rows="5" style="width:620px;margin-bottom:10px;">'+notiOpn1[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
			+'		<a class="btn_set bt_style1" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" title="의견등록"><span>의견등록</span></a>'
			+'	</div>'			
			+'</div>' );			
			
			
			
			
			$("#btnOpn_"+notiOpn1[i].notiOpnSeq).click(function(){//의견
				
				$(".reply_post2").remove();
				//$("#noti_opn_"+$(this).attr("notiOpnSeq")).parent("li").children(":last").after(
				$("#noti_opn_"+$(this).attr("notiOpnSeq")).append(
						'<div class="clearfix reply_post2">'
						+'	<span class="bl_reply" title="답글"><!--답글--></span>'
						+'	<textarea class="textbox" cols="5" rows="3" id="re_reply_'+$(this).attr("notiOpnSeq")+'" style="width:570px;margin-left:50px;margin-bottom:10px;"></textarea>'
						+'  <a class="btn_set bt_style1" href="javascript:fnInsertBbsNotiOpnForView('+$(this).attr("notiOpnSeq")+')" title="의견등록"><span>의견등록</span></a>'
						+'</div>');
			});
			
			$("#btnModify_"+notiOpn1[i].notiOpnSeq).click(function(){//수정
				
				if($(this).attr("modiMode") == "N"){
					$(this).html("수정취소");
					$("#btnOpn_"+$(this).attr("notiOpnSeq")).html("");
					$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).show();
					$("#opnDd_"+$(this).attr("notiOpnSeq")).hide();
					$(this).attr("modiMode","Y");
				}else{
					$(this).html("수정");
					$("#btnOpn_"+$(this).attr("notiOpnSeq")).html("의견");
					$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).hide();
					$("#opnDd_"+$(this).attr("notiOpnSeq")).show();
					$(this).attr("modiMode","N");
				}
			});
		}
		
		fnSetDataNotiOpn2(notiOpn2);
	};
	
	//의견2 조회 (의견의 의견)
	var fnSetDataNotiOpn2 = function(notiOpn2){
		var pName = '', ip = '',appendBtn='';
		for (var i=0; i < notiOpn2.length ; i++){
			
			if(opnMakrRealnameYn == 'Y'){//실명공개 
				ip = '';
				pName = '<a href="javascript:doLikeUserInfoPop(\''+notiOpn2[i].userId+'\')" class="tit">'+notiOpn2[i].userName+'</a>';
			}else{//비실명 
				ip = fnGetIpUtil(notiOpn2[i].makeIp);
				pName = $("#noti_opn_name_"+notiOpn2[i].upOpnSeq).html()+"-"+ notiOpn2[i].rank;
			}
			
			if(userId == notiOpn2[i].userId){
				appendBtn = '<div class="innerbox link"><a id="btnModify_'+notiOpn2[i].notiOpnSeq+'" modiMode="N" class="link" href="#" onclick="return false;" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">수정</a></div>'
						   +'<div class="innerbox link"><a id="btnDel_'+notiOpn2[i].notiOpnSeq+'"    class="link" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">삭제</a></div>';
			}else{
				appendBtn = '';
			}
			
			appendBtn = '';
			
			/*
			$("#noti_opn_"+notiOpn2[i].upOpnSeq).parent("li").children(":first").after(
			'<div class="clearfix" id="noti_opn_'+notiOpn2[i].notiOpnSeq+'">'	
			+'	<span class="bl_reply" title="답글"><!--답글--></span>'
			+'	<dl>'
			+'		<dt><span class="fo_bold fo_12px">'+pName+'</span><span class="fo_byte">'+ip+'</span><span>'+notiOpn2[i].regDttm+'</span>'+appendBtn+'</dt>'
			+'		<dd id="opnDd_'+notiOpn2[i].notiOpnSeq+'">'+notiOpn2[i].opnConts+'</dd>'
			+'	</dl>'
			+'	<div id="opnTxtSapn_'+notiOpn2[i].notiOpnSeq+'" style="display:none;" class="clearfix reply_post2 reply_mod">'
		 	+'  	<textarea class="textbox" id="opnTxt_'+notiOpn2[i].notiOpnSeq+'" cols="5" rows="3">'+notiOpn2[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
		 	+'		<a class="btn_reup" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" title="의견등록">의견등록</a>'
		 	+'	</div>'
			+'</div>');
			*/
			
			$("#noti_opn_"+notiOpn2[i].upOpnSeq).children(":last").after(
					'<div class="rereply" id="noti_opn_'+notiOpn2[i].notiOpnSeq+'">' 
					+'<div>' 
					+'	<div class="innerbox tit">'+pName+'</div>'
					+'	<div class="innerbox">'+notiOpn2[i].regDttm+'</div>'
					+   appendBtn
					+'</div>' 
					+'<div>' 
					+'	<div class="answer fl" id="opnDd_'+notiOpn2[i].notiOpnSeq+'">'+notiOpn2[i].opnConts+'</div>' 
					+'</div>' 	
					+'	<div id="opnTxtSapn_'+notiOpn2[i].notiOpnSeq+'" style="display:none;" class="reply_post2 reply_mod">'
					+'  	<textarea class="textbox" id="opnTxt_'+notiOpn2[i].notiOpnSeq+'" cols="5" rows="5" style="width:520px;margin-bottom:10px;">'+notiOpn2[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
					+'		<a class="btn_set bt_style1" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" title="의견등록"><span>의견등록</span></a>'
					+'	</div>'			
					+'</div>' );			
			
			$("#btnModify_"+notiOpn2[i].notiOpnSeq).click(function(){//수정
				
				if($(this).attr("modiMode") == "N"){
					$(this).html("수정취소");
					
					$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).show();
					$("#opnDd_"+$(this).attr("notiOpnSeq")).hide();
					$(this).attr("modiMode","Y");
				}else{
					$(this).html("수정");
					
					$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).hide();
					$("#opnDd_"+$(this).attr("notiOpnSeq")).show();
					$(this).attr("modiMode","N");
				}
			});
		}
		
		if(boardForm == '010' ){
			fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
		}else if(   (boardForm == '030' && boardFormSpec == '010') 
				 || (boardForm == '030' && boardFormSpec == '020')
					){//이미지 , 동영상형 
			fnGetImgNotiPrevNextInfo();
		}
// 		if(notiKind == '010' || notiKind == '040'){
// 			fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
// 		}else if(notiKind == '020' || notiKind == '030'){//이미지 , 동영상형 
// 			fnGetImgNotiPrevNextInfo();
// 		}
		
	};
	
	//의견등록
	var fnInsertBbsNotiOpnForView = function(upOpnSeq){
		
		var jsonNotiOpnObject = {
				'notiId' : '',
				'opnConts' : '', 
				'upOpnSeq' : '',
				'userNick' : ''
			};
		
		jsonNotiOpnObject.notiId = notiId;
		
		if(upOpnSeq != undefined ){//의견의 의견 
			if($("#re_reply_"+upOpnSeq).val() =="" ){
				alert("내용을 입력해주세요.");return;
			}
			jsonNotiOpnObject.opnConts = $("#re_reply_"+upOpnSeq).val().replaceAll("\n","<br/>");
			jsonNotiOpnObject.upOpnSeq = upOpnSeq;
			
		}else{//본문의 의견 
			if($("#noti_reply").val() =="" ){
				alert("내용을 입력해주세요.");return;
			}
			jsonNotiOpnObject.opnConts = $("#noti_reply").val().replaceAll("\n","<br/>");
			jsonNotiOpnObject.upOpnSeq = 0;

		}
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiOpnForView.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnGetNotiDetailInfoView();
				}
			}
	 	});
	}; 
	
	//의견수정
	var fnUpdateBbsNotiOpnForView = function(opnSeq){
		
		var jsonNotiOpnObject = {
				'notiId' : '',
				'opnConts' : '', 
				'notiOpnSeq' : ''	
			};
		if($("#opnTxt_"+opnSeq).val() == ""){
			alert("내용을 입력해주세요.");return;
		}
		
		jsonNotiOpnObject.notiId = notiId;
		jsonNotiOpnObject.opnConts = $("#opnTxt_"+opnSeq).val();
		jsonNotiOpnObject.notiOpnSeq = opnSeq;
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/updateBbsNotiOpnForView.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnGetNotiDetailInfoView();
				}
			}
	 	});
	}; 
	
	//의견삭제
	var fnDeleteBbsNotiOpnForView = function(opnSeq){
		if(!confirm('삭제하시겠습니까?')){return;}
		var jsonNotiOpnObject = {
				'notiId' : '',
				'notiOpnSeq' : ''	
			};
		
		jsonNotiOpnObject.notiId = notiId;
		jsonNotiOpnObject.notiOpnSeq = opnSeq;
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/deleteBbsNotiOpnForView.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnGetNotiDetailInfoView();
				}
			}
	 	});
	}; 
	
	//게시글 평가 등록
	var fnInsertBbsNotiEvalInfoForView = function(notiEvalDiv){
		
		var jsonNotiOpnObject = {
				'notiId' : '',
				'notiEvalDiv' : ''	
			};
		
		jsonNotiOpnObject.notiId = notiId;
		jsonNotiOpnObject.notiEvalDiv = notiEvalDiv;
		
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiEvalInfoForView.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnGetNotiDetailInfoView();
				}
			}
	 	});
	};
	
	//파일다운로드
	var fnDoFileDown = function(fileseq, filename, fileorg)
	{	
		
		 var jsonObject = {
					'apndFileOrgn' : fileorg,
					'apndFileName' : filename,
					'apndFileSeq' :  fileseq,
					'notiId' : notiId
		 };
		 
		 var url =WEB_HOME+"/adm/board/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");
		 document.dummy.location.href = url;
	};
	
	var fnGetBoardView = function(id, temp){
		parent.document.getElementById("admFrame").height = "700px";
		location.href = WEB_HOME+"/board210/getBasicBoardView.do?notiId="+id+"&boardId="+boardId+"&pageIndex="+pageIndex+"&pageUnit=10&pnum="+temp;
	};
	
	//목록
	var fnDoList = function(){
		location.href = WEB_HOME+"/adm/board/getAdmBoardNotiDelList.do?pageIndex="+pageIndex+"&pageUnit="+pageUnit
							+"&searchCondition="+searchCondition
							+"&searchKeyword="+escape(encodeURIComponent(searchKeyword));
		 
	};
	
	//게시물 이동
	var fnDoNotiMove = function(notiId) {
		if(notiKind == '040'){
			alert('설문형 게시글은 이동할 수 없습니다.');
			return;
		}
		var jsonNotiObject = {
			//----게시판속성 설정------
			'boardId' : '' 
			,'notiId' : []
		};
		//내가 작성한 게시물 인지 체크
		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;
		PortalCommon.getJson({
			url : WEB_HOME+"/board210/getMyNotiCheckList.do?format=json",
			data : {
				'data' : JSON.stringify(jsonNotiObject)
			},
			success : function(data) {
				if (data.jsonResult.success === true) {
					if (!confirm('이동하시겠습니까?')) {
						return;
					}
					PortalCommon.popupWindowCenter(
							WEB_HOME+'/organization/categoryChartPop.do?mode=cm_move&kind=2&type=1&admin=1&notiId='
									+ JSON.stringify(notiId), 'myBoardPop',
							'400', '450');
				
				}else{
					alert(data.jsonResult.message);
				}
			}
		});
	};
	
	var doPageReload = function(){
		fnDoList();//목록 
	};
	
	var getCheckNotiIdJsonData = function() {
		var jsonArray = [];
			var jsonObject = {
				'id' : notiId
			};
			jsonArray[0] = jsonObject;
		return jsonArray;
	};
	
	//게시글 삭제
	var notiDelete = function(notiId) {
		if (!confirm('삭제하시겠습니까?')) {
			return;
		}
		var jsonNotiObject = {
			//----게시판속성 설정------
			'boardId' : '', //게시판 ID
			'notiId' : []
		};

		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;
		
		PortalCommon.getJson({
			url : WEB_HOME+"/board210/deleteNotiInfo.do?format=json",
			data : {
				'data' : JSON.stringify(jsonNotiObject)
			},
			success : function(data) {
				if (data.jsonResult.success === true) {

					window.returnValue = 'ok';
					fnDoList();

				}
			}
		});
	};
	
	//공용 게시글 삭제 이력
	var fnInsertBbsNotiDelInfo = function(notiId){
		
		var jsonNotiDelObject = {
				'notiId' : '',
				'boardId' : '',
				'delDiv' : '',
				'delBasis' : '',
				'delRsn' : '',
				'delRsnCode' : '',
				'delRsnCodeSpec' : '',
				'badNotiFindDttm' : '',
				'rmrk' : ''
			};
		jsonNotiDelObject.notiId = notiId;
		jsonNotiDelObject.boardId = boardId;
		jsonNotiDelObject.delDiv = 'DEL';//삭제DEL MOV
		//jsonNotiDelObject.delBasis = $("#delBasis").val();//근거	
		//jsonNotiDelObject.delRsn = $("#delRsn").val();//삭제 사유
		//jsonNotiDelObject.delRsnCode = 'AAA';	
		//jsonNotiDelObject.delRsnCodeSpec = $("#delRsnCodeSpec").val();//조치내용
		//jsonNotiDelObject.badNotiFindDttm = $("#badNotiFindDttm").val().replaceAll('-','');//발견일시  
		//jsonNotiDelObject.rmrk = $("#rmrk").val();	//비고 

		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiDelInfo.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiDelObject) , 'moveData' : getMoveJsonData()}, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnDoList();
				}
			}
	 	});
	};	
	
	// bbsDelInfoPop.jsp에서 복사함.(단건처리로 수정)
	var getMoveJsonData = function(){
		
		var jsonMoveArray = [];
		var jsonBoardInfoObject = null;
		
		var jsonObject = {
    			'id' : notiId
    	};
		jsonMoveArray[0] = jsonObject;
		
		jsonBoardInfoObject = {
				'boardId'   : '', 
				'notiId'    :[]
			};	
		jsonBoardInfoObject.notiId = jsonMoveArray;
		jsonBoardInfoObject.boardId = boardId;
		
		//넘겨받은 게시글이 있다면 해당 게시물을 선택한 게시판으로 이동시킨다.
		var moveData = "";
		moveData = JSON.stringify(jsonBoardInfoObject);
		
		
		return moveData;
	};	
	
	var getFileSzForKb = function(sz) {
		if(sz > 0){
			sz = sz / 1000;
		}
		return Number(sz);
	};
	

	var fnGetPbsUserBoardInfoListForZTree = function(){
		
		$("#myBoardtreeObj li").remove();
		
		PortalCommon.getJson({
			url: WEB_HOME+"/person100/getPbsUserBoardInfoListForZTree.do?format=json&kind=1&type=1&admin=0",
			success :function(data){
				if(data.jsonResult.success ===true){
							
					var list = null;
					list = $.parseJSON(data.boardList);
					myZNodes = $.parseJSON(list);
					mObjHeight = myZNodes.length * 19;
					
					fnDrawZTree();
					fnDrawScrollBar();
					
				}
			}
	 	});
	};

	
	//마이게시판설정
	var setting = {
			edit: {
					enable: false,
					showRemoveBtn: false,
					showRenameBtn: false
			},	
			view: {
				dblClickExpand: false,
				showLine:false
			},
			data: {
				simpleData: { 
					enable: true
				}
			},
			check: {
				enable: false
			},
			callback: {
				onClick: zTreeOnClick 
				
			}
		};	
	
	function zTreeOnClick(event, treeId, treeNode) {
		selectNodeId = treeNode.boardId;
		selectBoardForm = treeNode.boardForm;
	}
	//ZTREE게시판 그리기 boardGubun:1 공용게시판 2: 마이게시판 
	var fnDrawZTree = function(){
		//alert('fnDrawZTree');
		$.fn.zTree.init($("#myBoardtreeObj"), setting, myZNodes);
		//myZTree = $.fn.zTree.getZTreeObj("myBoardtreeObj");
		treeReload();
	};
	
	//ZTREE 
	var treeReload = function(boardGubun){
		
		myBoardtreeObj = $.fn.zTree.getZTreeObj("myBoardtreeObj");
		myBoardtreeObj.expandAll(true);	
		//fnDoScrollbakMyboard();
		
		$("#myBoardtreeObj .ico_docu").css("background-position-x", "8px");
		$("#myBoardtreeObj .ico_docu").css("background-position-y", "6px");
		$("#myBoardtreeObj .noline_docu").css("display", "none");
	};
	
	var fnDrawScrollBar = function(){
		
		$("#myBoardListDiv").css({
			'height' : '200'
			, 'visibility' : 'true'
			, 'overflow-x': 'auto'
			, 'overflow-y': 'auto'
		});
		
	};
	
	//스크랩등록 
	var fnInsertScrap = function() {
		
		if(selectNodeId == null || selectNodeId ==""){alert("게시판을 선택해 주세요.");return;}
		if(selectBoardForm != boardForm){
			alert('같은형태의 게시판으로만 이동이 가능합니다.');
			return;
		}
		if($("#scrapNotiTitle").val() == ''){alert('제목을 입력해주세요.');return;}
		
		var jsonNotiObject = {
			//----게시판속성 설정------
			'boardId' : '', //게시판 ID
			'linkUrl' : '',
			'notiTitle' : '',
			'notiId' : '',
			'notiKind' : '',
			'pbs' : 'N'
		};
		//공용게시판 -> 개인게시판으로 스크랩 
		jsonNotiObject.boardId = selectNodeId;
		jsonNotiObject.notiTitle = $("#scrapNotiTitle").val();
		jsonNotiObject.notiId = notiId;
		
		if($(':radio[name="scrapType"]:checked').attr("id") == "url"){//링크
			
			jsonNotiObject.linkUrl = "/board210/boardViewFrame.do?pageIndex=1&boardId="+boardId+"&notiId="+notiId;
			jsonNotiObject.notiKind = '060';
			
		}else if($(':radio[name="scrapType"]:checked').attr("id") == "all"){//본문
			/**
			* 본문스크랩은 의견, 첨부, 평가 등 기타 관련테이블은 스크랩되지 않는다. 
			*/
			jsonNotiObject.linkUrl = '';
			jsonNotiObject.notiKind = '';
		}	
		
		PortalCommon.getJson({
			url : WEB_HOME+"/board310/insertPbsUserNotiInfoForScrap.do?format=json",
			data : {
				'data' : JSON.stringify(jsonNotiObject)
			},
			success : function(data) {
				if (data.jsonResult.success === true) {
					window.returnValue = 'ok';
					$("#pop_scrap").hide();
				}
			}
		});
	};
	
	var doUserInfoPop = function(){
		if(nickUseYn == 'N'){//실명일경우만
			var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+regrId;
			PortalCommon.userInfoPop(url);	
		}
	};
	
	var doUserInfoPopForOpn = function(_regrId){
		if(nickUseYn == 'N'){//실명일경우만
			var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+_regrId;
			PortalCommon.userInfoPop(url);	
		}
	};
	
	var doLikeUserInfoPop = function(_userId){
		if(nickUseYn == 'N'){//실명일경우만
			var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+_userId;
			PortalCommon.userInfoPop(url);	
		}
	};
	
	//메일발송 
	var fnEmailSubmit = function() {
 		var subject = $("#notiTitle").text();
 		var content = $("#notiConts").html();
		/* var subject = '1111'; 
		var content = '222';
		alert(content);  
		alert(content); */
		dummyForm.subject.value = subject;
		dummyForm.content.value = content;
		dummyForm.target = "goEmail";
		dummyForm.action = WEB_HOME+"/qmemo/getMailWritePop.do";
		//PortalCommon.windowPopup(WEB_HOME+"/qmemo/getMailWritePop.do?subject="+escape(encodeURIComponent(subject))+"&content="+escape(encodeURIComponent(content)), "goEmail", 1010, 780);
		PortalCommon.windowPopup("about:blank", "goEmail", 1010, 780);
		dummyForm.submit();
	};
	
	//게시글 삭제 이력
	var fnBbsDelInfoPop = function(_notiId , delDiv){
		PortalCommon.windowPopup(WEB_HOME+'/board210/bbsDelInfoPop.do?notiId='+JSON.stringify(_notiId)+'&delDiv='+delDiv,'삭제정보','528','552');
	};

	//게시물 이동 관리지용 
	var fnDoNotiMoveDelForAdm = function(notiId, delDiv) {
		
		if(isNotiSurvey){
			alert('설문형 게시글은 이동할 수 없습니다.');
			return;
		}	
		PortalCommon.popupWindowCenter(
				WEB_HOME+'/organization/categoryChartPop.do?mode='+delDiv+'&kind=2&type=1&admin=1&notiId='
						+ JSON.stringify(notiId), 'myBoardPop',
				'400', '450');
	};
	

/*	
	 $(window).load(function () {
			fnSetFrameHeight(230);
		});
*/	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		
		//parent.document.getElementById("bbsFrame").height = "600px";
		
		//parent.document.getElementById("bbsFrame").height = "700px";
		
		if(boardExplUseYn == 'Y' && boardExpl !=""){
			$("#boardExpl").html(boardExpl);
		}
		
		fnGetNotiDetailInfoView();
		
		fnSetFrameHeight(20);
		   
		$('#scrapSave').click(function(){
			fnInsertScrap();
		});
		$('#btnScrapClose').click(function(){
			$("#pop_scrap").hide();
		});
		$('#btnScrap').click(function(){
			fnGetPbsUserBoardInfoListForZTree();
			
			$("#pop_scrap").css({
					'position':'absolute',
					'display':'block',
					'z-index':'80',
					'top': $(this).offset().top- 10+"px",
					'left': $(this).offset().left - 410+"px"
				});
			$("#pop_scrap").show();			
		});
		//게시물이동
		$('#btn_boardMove').click(function(){
			
			if(eamAdminYn == "Y"){
				if(regrId == userId){
					fnDoNotiMove(getCheckNotiIdJsonData());
				}else{
					fnDoNotiMoveDelForAdm(getCheckNotiIdJsonData(),'cm_move_adm');	
				}
			}else{
				fnDoNotiMove(getCheckNotiIdJsonData());
			}
			
		});
		//목록
		$(".btn_list").click(function(){
			fnDoList();
		});
		$(".btn_print").click(function(){
			var top_po = (screen.availHeight/2) - (530/2);
		    var left_po = (screen.availWidth/2) - (820/2);
		    window.open(WEB_HOME+'/board100/bbsPrintPreview.do?notiId='+notiId+"&boardId="+boardId+"&boardKind="+boardKind+"&pnum="+pnum, 'boardPrintPreview', 'top='+top_po+',left='+left_po+',width=820,height=530,resizable=yes,scrollbars=yes,toolbar=yes,menubar=yes');
		});

		$("#notiConts").find("IMG").load(function () {
			fnSetFrameHeight(100);
		});
		
		$(".btn_back").click(function(){
			if (!confirm('복원하시겠습니까?')) {
				return;
			}			
			PortalCommon.getJson({
				url: WEB_HOME+"/adm/board/updateAdmBoardNotiDelRollBack.do?format=json",
			    data: $("form[name=frmMain]").serialize(),
				success :function(data){
					if(data.jsonResult.success === true){
						location.href = WEB_HOME+'/adm/board/getAdmBoardNotiDelList.do';
					}else{
						//실패후 실행 스크립트
					}
				}
		 });			
		});
		
		$(".bl_qus2").click(function(){
			fnSetFrameHeight(20);
		});
		
		
		/* //게시판 설명 보기		
		$("#moreBoardExpl").click(function() {
			
			if (boardExplUseYn == 'Y')
			{
				PortalCommon.windowPopup(WEB_HOME+'/board230/bbsBoardExplPopup.do?boardId='+boardId, '게시판설명',750,300);
			}
			
		}); */
		
		if (boardKind == '020')
		{
			$("#boardPage").css("display","none");
		}		
		
		$('.ico_smile').click(function(){
			$('.perlist').css({
				'position':'absolute',
				'top': $(this).offset().top+20+"px",
				'left': $(this).offset().left-67+"px"
			});
		});
		
		$(".ico_smile").hide();		
		
		// onload
		fnSetFrameHeight(230);
	});