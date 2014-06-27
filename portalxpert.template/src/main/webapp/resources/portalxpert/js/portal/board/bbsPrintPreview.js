	
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
	}
	
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
			url: WEB_HOME+"/board210/getNotiDetailInfoView.do?format=json",
			data: {  'data' : fnGetBoardNotiIdInfo(), 'pnum' : pnum }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					$("#noti_reply").val("");
					
					notiInfo = $.parseJSON(data.notiInfo);
					notiFile = $.parseJSON(data.notiFile);
					notiOpn1 = $.parseJSON(data.notiOpn1);
					notiOpn2 = $.parseJSON(data.notiOpn2);
					notiPrevNextInfo = $.parseJSON(data.notiPrevNextInfo);
					
					notiKind = notiInfo[0].notiKind; 
					
					if(notiKind == '040'){//설문
						fnApndSurveyList($.parseJSON(data.surveyList));
						fnApndSurveySubJectList($.parseJSON(data.surveyList));
						fnApndSurveyExmplList($.parseJSON(data.surveyExmplList));
					}
					
					
					if(notiInfo[0].notiConts != null){
						
						$("#notiConts").html(notiInfo[0].notiConts);
						//$("#notiConts").html(replaceAll(notiInfo[0].notiConts, "\r\n","<br>"));
					}
					if($("#notiConts").html()!=""){
						fnSetDataNotiInfo(notiInfo[0]);	
					}
				}

				window.print();
			}
	 	});
	};
	
	var fnSetFrameHeight = function(addHeight){
		parent.document.getElementById("bbsFrame").height = Number($(document).height()+ addHeight )+"px";
	};
	
	//이미지형 게시글 이전글 다음글 
	var fnGetImgNotiPrevNextInfo = function(){
		$("#boardPage div").remove();
		$("#boardPage").append(' <div class="page_vod">' +
				' 	<a href="#" class="btn_prev" title="이전"></a><a href="#" class="btn_next" title="다음"></a>' +
				' 	<ul class="page_list clearfix">' +
				' 		<li>' +
				' 			<div class="box_vod">' +
				' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" class="on" alt="게시판이미지"></p>' +
				' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
				' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
				' 				<p class="fo_byte">2013-03-03 17:33</p>' +
				' 			</div>' +
				' 		</li>' +
				' 		<li>' +
				' 			<div class="box_vod">' +
				' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" alt="게시판이미지"></p>' +
				' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
				' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
				' 				<p class="fo_byte">2013-03-03 17:33</p>' +
				' 			</div>' +
				' 		</li>' +
				' 		<li>' +
				' 			<div class="box_vod">' +
				' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" alt="게시판이미지"></p>' +
				' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
				' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
				' 				<p class="fo_byte">2013-03-03 17:33</p>' +
				' 			</div>	' +
				' 		</li>' +
				' 	</ul>' +
				' </div>');
	};
	
	//게시글 좋아요 사용자 조회
	var fnGetBbsNotiEvalInfoList = function(){
		
		if($("#linkUserListDiv").attr("value") == "N"){
			$("#linkUserListDiv").show();
			$("#linkUserListUl li").remove();
			PortalCommon.getJson({
				url: WEB_HOME+"/board210/getBbsNotiEvalInfoList.do?format=json",
				data: {  'data' : fnGetBoardNotiIdInfo() }, 
				success :function(data){
					if(data.jsonResult.success ===true){
						var notiEval = $.parseJSON(data.notiEval);
						fnSetDataNotiEvalInfo(notiEval);
					}
				}
		 	});
			$("#linkUserListDiv").attr("value","Y");
		}else{
			$("#linkUserListDiv").hide();
			$("#linkUserListDiv").attr("value","N");
		}
	}; 
	
	var replaceAll = function(str,str1,str2){
		  return str.split(str1).join(str2);
	};
	var nickUseYn = null;
	var userMail = null;
	var fnSetDataNotiInfo = function(notiJson){
		
		$("#boardPage ul").remove();
		
		regrId = notiJson.regrId;
		notiKind = notiJson.notiKind;
		ipOpenYn = notiJson.ipOpenYn;//의견등록시 닉네임, 아이피 공개여부 
		nickUseYn = notiJson.nickUseYn;
		opnMakrRealnameYn = notiJson.opnMakrRealnameYn;//의견작성자 실명여부 
		userMail = notiJson.mail;
		
		$("#scrapNotiTitle").val("[스크랩] "+notiJson.notiTitleOrgn);
		$("#notiNum").html(notiJson.oldNoticeSeq);
		$("#notiReadCnt").html(notiJson.notiReadCnt);//조회수
		$("#opnCnt").html(notiJson.opnCnt);
		$("#notiLikeCnt").html(notiJson.evalLikeCnt);//좋아요
		$("#notiTitle").html(notiJson.notiTitle);
		$("#notiBgnDttm").html(notiJson.notiBgnDttm);
		$("#notiEndDttm").html(notiJson.notiEndDttm);
		$("#notiAgrmCnt").html(notiJson.evalAgrmCnt);//찬성
		$("#notiOppCnt").html(notiJson.evalOppCnt);//반대
		$("#notiTagList").html(notiJson.notiTagLst);
		if(notiJson.moblOpenDiv == '010'){
			$("#moblOpenDiv").html("본문만 공개");	
		}else if(notiJson.moblOpenDiv == '020'){
			$("#moblOpenDiv").html("본문 + 첨부파일 공개");	
		}else if(notiJson.moblOpenDiv == '030'){
			$("#moblOpenDiv").html("공개하지 않음");	
		}
		
		if(notiJson.opnPrmsYn == 'Y'){
			$("#opnPrmsDiv").show();
			$("#replyUl").show();
		}
		
		if(notiJson.userNick != null && notiJson.nickUseYn == 'Y' ){
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
		}
		
		if(boardKind == '030'){//경조사 
			
			$("#cdlnObjrName").html(notiJson.cdlnObjrName);
			$("#cdlnDeptName").html(notiJson.cdlnDeptName);
			$("#cdlnDeptFname").html(notiJson.cdlnDeptFname);
			$("#cdlnEvntName").html(evtCdByName[notiJson.cdlnEvntCode]);
		}
		
		if(notiKind == '010'){//일반
			
		}else if(notiKind == '020'){//이미지
			$(".btn_reply").hide();	
		}
		
		fnSetDataNotiFileInfo(notiFile);
		
		//스크랩
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
	
	//의견평가
	var fnSetDataNotiEvalInfo = function(notiEvalJson){
		$('#linkUserListUl li').remove();
		for (var i=0; i < notiEvalJson.length ; i++){
		
			$('#linkUserListUl').append('<li id="'+notiEvalJson[i].evalSeq+'"><a href="javascript:fnGetUser(\''+ notiEvalJson[i].userId +'\')">'+ notiEvalJson[i].userName +'</a></li>');		
			
		}
		$('.scroll_2, .good').addClass('default-skin');
	    $('.scroll_2, .good').customScrollbar();
			
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
				$('#notiFileDl:last').append('<dd><a class="bl_file2 fo_blue" >'+notiFileJson[i].apndFileOrgn+'</a>'
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
			
			for (var i=0; i < notiFileJson.length ; i++){
				//$("#notiConts").append('<img src="${imgSvrUrl}/'+notiFileJson[i].apndFilePath+'/'+notiFileJson[i].apndFileName+'" alt="이미지파일"/>');
				if(notiFileJson[i].apndFileTp == '020'){
					fnImgApndList(notiFileJson[i]);	
				}
			}
			
		}else if(notiKind == "030"){//동영상
			
		}
		
		fnSetDataNotiOpn1(notiOpn1);
	};
	
	//이미지형 게시글 
	var fnImgApndList = function(json)
	{
		$("#imgNotiConts li").remove();
		$("#imgNotiConts").append(
				'<li><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.apndFileSeq+'\')" >'
				+'<img id="viewImg-'+json.apndFileSeq+'" src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'" alt="올린이미지"></a></li>'
				+'<div style="display:none;" align="center" id="dialog-'+json.apndFileSeq+'" title="'+json.apndFileOrgn+'">'
				+'<img src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'"></div>');
		
		$("#viewImg-"+json.apndFileSeq).load(function(){
			fnImgEffect();
		});
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
		$( "#dialog-"+id ).dialog
		(
			{      
				height: 500,      
				width: 600,
				modal: true
			}
		);
	};
	
	//의견1 조회 (본문의 의견)
	var fnSetDataNotiOpn1__ = function(notiOpn1){
		
		var pName = '', ip = '',appendBtn='';
		
		$("#replyUl li").remove();
		for (var i=0; i < notiOpn1.length ; i++){

			if(opnMakrRealnameYn == 'Y'){//실명공개 
				ip = '';
				pName = notiOpn1[i].userName;
			}else{//비실명 
				ip = fnGetIpUtil(notiOpn1[i].makeIp);
				pName = '의견'+Number(notiOpn1.length - i);
			}
// 			if(userId == notiOpn1[i].userId){//권한이 있는 사용자 혹은 작성자 
// 				appendBtn = '<a id="btnModify_'+notiOpn1[i].notiOpnSeq+'" modiMode="N" class="fo_green" href="#" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">수정</a>'
// 						   +'<a id="btnDel_'+notiOpn1[i].notiOpnSeq+'"    class="fo_green" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">삭제</a>';
// 			}else{
// 				appendBtn = '';
// 			}
			$("#replyUl").append(
					'<li>'
					+'<div class="clearfix" id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">'	
					+'	<dl>'
					+'		<dt><span class="fo_bold fo_12px" notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</span><span class="fo_byte">'+ip+'</span><span>'+notiOpn1[i].regDttm+'</span>'
					+'			'+appendBtn+'</dt>'
					+'		<dd id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</dd>'
					+'	</dl>'
				 	+'	<div id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="clearfix reply_post2 reply_mod">'
				 	+'  	<textarea id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="5" rows="3">'+notiOpn1[i].opnConts+'</textarea>'
				 	+'		<a class="btn_reup" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" title="의견등록"></a>'
				 	+'	</div>'
					+'</div>'
					+'</li>');
			
			
		}
		
		fnSetDataNotiOpn2(notiOpn2);
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

			
			$("#replyUl").append(
			'<div id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">' 
			+'<div>' 
			+'	<div class="innerbox tit"  notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</div>'
			+'	<div class="innerbox">'+notiOpn1[i].regDttm+'</div>'
			+'	<div class="innerbox link"><a class="link" href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'" id="btnOpn_'+notiOpn1[i].notiOpnSeq+'">의견</a></div>'
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
		}
		
		fnSetDataNotiOpn2(notiOpn2);
	};	
	
	
	
	
	//의견2 조회 (의견의 의견)
	var fnSetDataNotiOpn2__ = function(notiOpn2){
		var pName = '', ip = '',appendBtn='';
		for (var i=0; i < notiOpn2.length ; i++){
			
			if(opnMakrRealnameYn == 'Y'){//실명공개 
				ip = '';
				pName = notiOpn2[i].userName;
			}else{//비실명 
				ip = fnGetIpUtil(notiOpn2[i].makeIp);
				pName = $("#noti_opn_name_"+notiOpn2[i].upOpnSeq).html()+"-"+ notiOpn2[i].rank;
			}
			
// 			if(userId == notiOpn2[i].userId){
// 				appendBtn = '<a id="btnModify_'+notiOpn2[i].notiOpnSeq+'" modiMode="N" class="fo_green" href="#" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">수정</a>'
// 						   +'<a id="btnDel_'+notiOpn2[i].notiOpnSeq+'"    class="fo_green" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">삭제</a>';
// 			}else{
// 				appendBtn = '';
// 			}
			
			$("#noti_opn_"+notiOpn2[i].upOpnSeq).parent("li").children(":first").after(
			'<div class="clearfix" id="noti_opn_'+notiOpn2[i].notiOpnSeq+'">'	
			+'	<span class="bl_reply" title="답글"><!--답글--></span>'
			+'	<dl>'
			+'		<dt><span class="fo_bold fo_12px">'+pName+'</span><span class="fo_byte">'+ip+'</span><span>'+notiOpn2[i].regDttm+'</span>'+appendBtn+'</dt>'
			+'		<dd id="opnDd_'+notiOpn2[i].notiOpnSeq+'">'+notiOpn2[i].opnConts+'</dd>'
			+'	</dl>'
			+'	<div id="opnTxtSapn_'+notiOpn2[i].notiOpnSeq+'" style="display:none;" class="clearfix reply_post2 reply_mod">'
		 	+'  	<textarea id="opnTxt_'+notiOpn2[i].notiOpnSeq+'" cols="5" rows="3">'+notiOpn2[i].opnConts+'</textarea>'
		 	+'		<a class="btn_reup" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" title="의견등록"></a>'
		 	+'	</div>'
			+'</div>');
			
		}
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
			
			
		}

		
	};	
	
	var getFileSzForKb = function(sz) {
		if(sz > 0){
			sz = sz / 1000;
		}
		return Number(sz);
	};
	
	var myZNodes = null;
	var myZTree = null;
	var myBoardtreeObj = null;
	var mObjHeight = null;
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
				dblClickExpand: false
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
	var selectNodeId = null;
	function zTreeOnClick(event, treeId, treeNode) {
		selectNodeId = treeNode.boardId;
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
	};
	
	var fnDrawScrollBar = function(){
		
		$("#myBoardListDiv").css({
			'height' : '200'
			, 'visibility' : 'true'
			, 'overflow-x': 'auto'
			, 'overflow-y': 'auto'
		});
		
	};
	
	
	var doUserInfoPop = function(){
		PortalCommon.userInfoPop(userId);
	};
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {
		fnGetNotiDetailInfoView();
		
		$('.ico_smile').click(function(){
			$('.info_wrt').css({
				'position':'absolute',
				'top': $(this).offset().top+20+"px",
				'left': $(this).offset().left-0+"px"
			});
		})		
	});
	
