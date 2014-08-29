	




	
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
			url: WEB_HOME+"/board210/getNotiDetailInfoView.do?format=json",
			data: {  'data' : fnGetBoardNotiIdInfo()}, 
			success :function(data){
				if(data.jsonResult.success ===true){

					notiInfo = $.parseJSON(data.notiInfo);//게식
					notiFile = $.parseJSON(data.notiFile);
					notiKind = notiInfo[0].notiKind;
					
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
	
	
	
	var fnSetDataNotiInfo = function(notiJson){
		$("#boardPage ul").remove();
		regrId = notiJson.regrId;
		notiKind = notiJson.notiKind;
		nickUseYn = notiJson.nickUseYn;
		opnMakrRealnameYn = notiJson.opnMakrRealnameYn;//의견작성자 실명여부 
		userMail = notiJson.mail;
		opnPrmsYn = notiJson.opnPrmsYn;
		agrmOppYn = notiJson.agrmOppYn;

		$("#notiNum").html(notiJson.oldNoticeSeq);
		$("#notiReadCnt").html(notiJson.notiReadCnt);//조회수
		$("#notiTitle").html(notiJson.notiTitle);
		$("#notiBgnDttm").html(notiJson.notiBgnDttm);
		$("#regDttm").html(notiJson.regDttm);
		$("#userName").html(notiJson.userName);
		$("#deptName").html(notiJson.deptFname);
		
		fnSetDataNotiFileInfo(notiFile);
		
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
		/*
		$('#notiFileDl dd').remove();
		var apFileCnt = 0;
		for (var i=0; i < notiFileJson.length ; i++){
			if(notiFileJson[i].apndFileTp == '050'){
				$('#notiFileDl:last').append('<dd><a class="bl_file2 fo_blue" href="javascript:fnDoFileDown(\''+ notiFileJson[i].apndFileSeq +'\',\''+ notiFileJson[i].apndFileName +'\',\''+ notiFileJson[i].apndFileOrgn +'\')">'+notiFileJson[i].apndFileOrgn+'</a>'
						+'<span class="fo_gray">('+getFileSzForKb(notiFileJson[i].apndFileSz)+"kb"+')</span></dd>');	
				
				apFileCnt++;	
			 	    
			}
		}
		*/
		
		$('#notiFileDl dd').remove();
		var apFileCnt = 0;
		for (var i=0; i < notiFileJson.length ; i++){
			if(notiFileJson[i].apndFileTp == '050'){
				$('#notiFileDl:last').append('<dd><span class="ico_file"><strong>첨부파일</strong> <a class="bl_file2 fo_blue" href="javascript:fnDoFileDown(\''+ notiFileJson[i].apndFileSeq +'\',\''+ notiFileJson[i].apndFileName +'\',\''+ notiFileJson[i].apndFileOrgn +'\')">'+notiFileJson[i].apndFileOrgn+'</a>'
						+' ('+getFileSzForKb(notiFileJson[i].apndFileSz)+"kb"+')</span></dd>');	
				
				apFileCnt++;	
			 	    
			}
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
		 
		 var url =WEB_HOME+"/board100/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");
		 document.dummy.location.href = url;
	};
	
		
	
	var getFileSzForKb = function(sz) {
		if(sz > 0){
			sz = sz / 1000;
		}
		return Number(sz);
	};
	

	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		
		
		parent.document.getElementById("bbsFrame").height = "700px";
		
		fnGetNotiDetailInfoView();
		
		fnSetFrameHeight(0);
	});