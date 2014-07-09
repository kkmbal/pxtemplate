	




	
	var fnGetBoardNotiIdInfo = function(){
		var jsonNotiObject = {
				'boardId' : '' , 
				'notiId' : ''
			};
		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;

		return JSON.stringify(jsonNotiObject);
	};
	
	
	
	//게시글 상세정보 조회
	var fnGetNotiDetailInfoView = function(){
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/open/getNotiDetailInfoView.do?format=json",
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
		parent.document.getElementById("bbsFrame").height = "700px";
		parent.document.getElementById("bbsFrame").height = Number($(document).height()+ addHeight )+"px";
		//parent.document.getElementById("bbsFrame").height = (document.body.scrollHeight+ addHeight)+"px";
		
	};
	
	
	
	//일반 게시글 이전글 다음글 
	var fnGetPrevNextNotiInfo = function(notiJson){
		
		$("#boardPage > table").append('<tbody id="post_sltUl"></tbody>');	
		$("#post_sltUl tr").remove();
		if(notiJson !=null){
			
			if(notiJson.prevNotiId != null){
				if(notiJson.prevNotiKind =='060'){
					$("#post_sltUl").append('<tr><th scope="row">이전글</th><td id="prevNotiTitle"><a href="javascript:fnOpenLinkUrl(\''+notiJson.prevLinkUrl+'\' , \''+notiJson.prevNotiId+'\')" id="prevNotiId">'+notiJson.prevNotiTitle+'</a></td></tr>');					
				}else{
					$("#post_sltUl").append('<tr><th scope="row">이전글</th><td id="prevNotiTitle"><a href="javascript:fnGetBoardView(\''+notiJson.prevNotiId+'\' , \''+prev_pnum+'\')" id="prevNotiId">'+notiJson.prevNotiTitle+'</a></td></tr>');	
				}
				
			}else{
				$("#post_sltUl").append('<tr><th scope="row">이전글</th><td id="prevNotiTitle">처음 게시물 입니다.</td></tr>');
			}
			if(notiJson.nextNotiId != null){
				
				if(notiJson.nextNotiKind == '060'){
					$("#post_sltUl").append('<tr><th scope="row">다음글</th><td id="nextNotiTitle"><a href="javascript:fnOpenLinkUrl(\''+notiJson.nextLinkUrl+'\' , \''+notiJson.nextNotiId+'\')" id="nextNotiId">'+notiJson.nextNotiTitle+'</a></td></tr>');											
				}else{
					$("#post_sltUl").append('<tr><th scope="row">다음글</td><td id="nextNotiTitle"><a href="javascript:fnGetBoardView(\''+notiJson.nextNotiId+'\' , \''+next_pnum+'\')" id="nextNotiId"><span class="bl_next">'+notiJson.nextNotiTitle+'</a></td></tr>');
				}
				
			}else{
				$("#post_sltUl").append('<tr><th scope="row">다음글</th><td id="nextNotiTitle">마지막 게시물 입니다.</td></tr>');
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
							' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="'+imgSvrUrl+'/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판이미지"></a></p>' +
							' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><span class="te_dot"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')">'+notiPrevNextImgMovInfo[i].notiTitle+'</a></span><span class="fo_replyn">[의견:'+notiPrevNextImgMovInfo[i].opnCnt+']</span></p>' +
							' 				<p class="name">'+notiPrevNextImgMovInfo[i].updrName+'<span class="hits">'+notiPrevNextImgMovInfo[i].notiReadCnt+'</span></p>' +
							' 				<p class="fo_byte">'+notiPrevNextImgMovInfo[i].notiBgnDttm+'</p>' +
							' 			</div>' +
							' 		</li>');
				}else if(boardFormSpec == '020'){
					$("#board_mov").append('<li class="ver_top">' +
							' 			<div class="box_vod">' +
							' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="'+movDir+'/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판동영상"></a></p>' +
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
			url: WEB_HOME+"/board210/open/notiPrevNextImgMovInfo.do?format=json",
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
		$("#regDttm").html(notiJson.regDttm);
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
			$("#opnPrmsDiv").show();
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
		
		//공지
		if(notiJson.anmtYn == "Y"){
			$("#anmtDiv").show();
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

		var rtSize = PortalCommon.fnImgPreviewResize(imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName, 430, 300);
		//$("#imgNotiConts li").remove();
		//$("#imgNotiConts div").remove();
		$("#imgNotiConts").append(
				'<li style="border: 0;"><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.apndFileSeq+'\')" >'
				+'<img id="viewImg-'+json.apndFileSeq+'" onload="javascript:PortalCommon.fnImgPreviewOnloadResize(this, 430, 300)" src="'+imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName+'" alt="올린이미지"></a></li>'
				+'<div style="display:none;" align="center" id="dialog-'+json.apndFileSeq+'" title="'+json.apndFileOrgn+'">'
				+'<img src="'+imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName+'"></div>');
		
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
		var thumbnailImg = movDir+"/"+thumbnailFile;
		$("#movNotiConts").append('<a href="javascript:fnPlayMov();" id="mvp_'+json.mvpKey+'"><img src="'+thumbnailImg+'" alt="비디오파일" ></a>');
	};
	
	//동영상 (원래창)
	var fnPlayMov2 = function(){
		if(notiMov.length > 0 && notiMov[0].mvpKey){
			$("#movNotiConts > #mvp_"+notiMov[0].mvpKey).remove();
		
			var thisMvpKey = RES_HOME+"/common/player/Player.swf?key="+notiMov[0].mvpKey;
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
			//+'	<div class="innerbox link"><a class="link" href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'" id="btnOpn_'+notiOpn1[i].notiOpnSeq+'">의견</a></div>'
			//+   appendBtn
			+'</div>' 
			+'<div>' 
			+'	<div class="answer fl" id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</div>' 
			+'</div>' 	
			//+'	<div id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="reply_post2 reply_mod">'
			//+'  	<textarea class="textbox" id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="5" rows="5" style="width:620px;margin-bottom:10px;">'+notiOpn1[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
			//+'		<a class="btn_set bt_style1" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" title="의견등록"><span>의견등록</span></a>'
			//+'	</div>'			
			+'</div>' );			
			
			
			
			
			$("#btnOpn_"+notiOpn1[i].notiOpnSeq).click(function(){//의견
				
				$(".reply_post2").remove();
				//$("#noti_opn_"+$(this).attr("notiOpnSeq")).parent("li").children(":last").after(
				$("#noti_opn_"+$(this).attr("notiOpnSeq")).append(
						'<div class="clearfix reply_post2">'
						+'	<span class="bl_reply" title="답글"><!--답글--></span>'
						+'	<textarea class="textbox" cols="5" rows="3" id="re_reply_'+$(this).attr("notiOpnSeq")+'" style="width:570px;margin-left:50px;margin-bottom:10px;"></textarea>'
						//+'  <a class="btn_set bt_style1" href="javascript:fnInsertBbsNotiOpnForView('+$(this).attr("notiOpnSeq")+')" title="의견등록"><span>의견등록</span></a>'
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
					//+   appendBtn
					+'</div>' 
					+'<div>' 
					+'	<div class="answer fl" id="opnDd_'+notiOpn2[i].notiOpnSeq+'">'+notiOpn2[i].opnConts+'</div>' 
					+'</div>' 	
					//+'	<div id="opnTxtSapn_'+notiOpn2[i].notiOpnSeq+'" style="display:none;" class="reply_post2 reply_mod">'
					//+'  	<textarea class="textbox" id="opnTxt_'+notiOpn2[i].notiOpnSeq+'" cols="5" rows="5" style="width:520px;margin-bottom:10px;">'+notiOpn2[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
					//+'		<a class="btn_set bt_style1" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" title="의견등록"><span>의견등록</span></a>'
					//+'	</div>'			
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
	
	
	
	//파일다운로드
	var fnDoFileDown = function(fileseq, filename, fileorg)
	{	
		
		 var jsonObject = {
			'apndFileOrgn' : fileorg,
			'apndFileName' : filename,
			'apndFileSeq' :  fileseq,
			'notiId' : notiId,
			'boardId' : boardId
		 };
		 
		 var url =WEB_HOME+"/board100/open/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");
		 document.dummy.location.href = url;
	};
	
	var fnGetBoardView = function(id, temp){
		parent.document.getElementById("bbsFrame").height = "700px";
		location.href = WEB_HOME+"/board210/open/getBasicBoardView.do?notiId="+id+"&boardId="+boardId+"&pageIndex="+pageIndex+"&pageUnit=10&pnum="+temp;
	};
	
	//목록
	var fnDoList = function(){
		if (boardId == 'BBS999999'){//임시게시판이면
			parent.document.getElementById("bbsFrame").src= WEB_HOME+'/board240/getTmpBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
		}else{
			if (boardForm == '030' && boardFormSpec == '010'){  //이미지형
				parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board211/open/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
			}else if (boardForm == '030' && boardFormSpec == '020'){  //동영상형
				parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board212/open/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
			}else if (boardForm == '030' && boardFormSpec == '030'){  //컨텐츠형
				parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board213/open/getBbsContentsBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
			}else{
				isDesc = isDesc==null?false:isDesc;
				isDesc = isDesc==""?false:isDesc;
				orderType = orderType==""?"default":orderType;
				
				if(searchKeyword !="" || regDttmFrom !="" || regDttmTo !=""){
					listYn = 'Y';
				}
				
				parent.document.getElementById("bbsFrame").src= 
					WEB_HOME+"/board210/open/getBoardInfoList.do?boardId="
							+boardId+"&pageIndex="+pageIndex+"&pageUnit="+pageUnit
							+"&searchCondition="+searchCondition
							+"&searchKeyword="+escape(encodeURIComponent(searchKeyword))
							+"&regDttmFrom="+regDttmFrom
							+"&regDttmTo="+regDttmTo
							+"&orderType="+orderType
							+"&listYn="+listYn;
			}
		}
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
	

	
	
	var fnDrawScrollBar = function(){
		
		$("#myBoardListDiv").css({
			'height' : '200'
			, 'visibility' : 'true'
			, 'overflow-x': 'auto'
			, 'overflow-y': 'auto'
		});
		
	};
	
		

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		
		//parent.document.getElementById("bbsFrame").height = "600px";
		
		parent.document.getElementById("bbsFrame").height = "700px";
		
		if(boardExplUseYn){
			if(boardExplUseYn == 'Y' && boardExpl !=""){
				$("#boardExpl").html(boardExpl);
			} 
		}
		
		fnGetNotiDetailInfoView();
		
		fnSetFrameHeight(20);
		   
		
		//목록
		$(".btn_list").click(function(){
			fnDoList();
		});
		

		$("#notiConts").find("IMG").load(function () {
			fnSetFrameHeight(100);
		});
		
		
		
		$(".bl_qus2").click(function(){
			fnSetFrameHeight(20);
		});
		
		
		if (boardKind == '020')
		{
			$("#boardPage").css("display","none");
		}		
		
			
		
		// onload
		fnSetFrameHeight(230);
	});