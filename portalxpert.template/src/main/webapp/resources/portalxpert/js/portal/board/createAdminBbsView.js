
    

	
	//공개여부,게시자지정 조직도(개인)
	var callbackOpenPerson = function(data){
		var json = $.parseJSON(data);
		
		if (callbackKind == 'O'){
			for (var i=0; i < json.length; i++)
			{
				var contains = false;
				$obj = $('#OpenPersonCategories li');
		    	for( var j=0; j < $obj.length; j++)
		    	{
		    		if ($obj.eq(j).attr("id") == json[i].id)
	    			{
		    			contains = true;
		    			break;
	    			}
		    	}	
		    	if (!contains) $('#OpenPersonCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnOpenPersonListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+'.'+json[i].name+'</li>');
			}
		}else if(callbackKind == 'W'){
			for (var i=0; i < json.length; i++)
			{
				var contains = false;
				$obj = $('#WriterPersonCategories li');
		    	for( var j=0; j < $obj.length; j++)
		    	{
		    		if ($obj.eq(j).attr("id") == json[i].id)
	    			{
		    			contains = true;
		    			break;
	    			}
		    	}	 

		    	if (!contains) $('#WriterPersonCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnWriterPersonListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+'.'+json[i].name+'</li>');
			}
		}else if(callbackKind == 'A'){
			for (var i=0; i < json.length; i++)
			{
				var contains = false;
				$obj = $('#managerCategories li');
		    	for( var j=0; j < $obj.length; j++)
		    	{
		    		if ($obj.eq(j).attr("id") == json[i].id)
	    			{
		    			contains = true;
		    			break;
	    			}
		    	}			
		    	if (!contains) $('#managerCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnManagerListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+'.'+json[i].name+'</li>');
			}
			$('#txt_manager_search').val('');			
		}
	};
	
	
	
	//관리자 지정 삭제
	var fnManagerListRemove = function(id)
	{
		$("#managerCategories #"+id+"").remove();
	};
	
	//공개여부 부서지정 삭제
	var fnOpenDeptListRemove = function(id)
	{
		$("#OpenDeptCategories #"+id).remove();
	};
    
	//공개여부 개인지정 삭제
	var fnOpenPersonListRemove = function(id)
	{
		$("#OpenPersonCategories #"+id).remove();
	};
    
   //게시자여부 부서지정 삭제
	var fnWriterDeptListRemove = function(id)
	{
		$("#WriterDeptCategories #"+id+"").remove();
	};
    
   //게시자여부 개인지정 삭제
    var fnWriterPersonListRemove = function(id)
    {
    	$("#WriterPersonCategories #"+id+"").remove();
    };

    //콤포 초기화
	var fnMenuCompoInit = function(){
		$('#boardFormSub').attr('disabled', 'true');
		
		//달력 설정
		
		$('#boardOperBgnDttm').datepicker(
		{      
			showOn: "button",
			//showOn: "both",      
			buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
			buttonImageOnly: true,
			buttonText: "시작일자",
			showButtonPanel: true

		});
		$( "#boardOperBgnDttm" ).datepicker( "setDate", new Date() );
		
	 	$('#boardOperEndDttm').datepicker(
	 	{      
			showOn: "button",
			//showOn: "both",      
			buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
			buttonImageOnly: true,
			buttonText: "종료일자",
			showButtonPanel: true
					
		}); 	
	 	$( "#boardOperEndDttm" ).datepicker( "setDate", new Date() );
	 	
	 	$('img.ui-datepicker-trigger').attr('style','margin-left:-7px; vertical-align:middle; cursor:pointer;');
	 	$('.ui-datepicker').attr('style','display:none;');
	 	//게시판 운영기간 설정
	 	$('#boardOperBgnDttm').attr('disabled', 'true');
		$('#boardOperEndDttm').attr('disabled', 'true');
		
		//게시판 공개 여부
		$("#div_OpenFlag").hide();
		//게시자 지정 여부
		$("#div_WriterFlag").hide();
		//태그입력 
		$("#div_tag").hide();
		//관리자 지정에 로그인한 사용자 기본 지정
		/*
		if (boardId == '')			
		{
			$('#managerCategories').append('<li id="'+"e749ddee5af73b7d5ed213d23e5c5b91"+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnManagerListRemove(\''+"e749ddee5af73b7d5ed213d23e5c5b91"+'\')" ></a>'+"홍길동"+'</li>');
		}*/
	};
	
	//게시판명 조회(AJAX)
	var fnBbsSearchName = function(name){
		PortalCommon.getJson({
			url: WEB_HOME+"/board100/searchAdminBoardName.do?format=json",
		    data: {'bbsName' : name},
			success :function(data){
				if(data.jsonResult.success ===true){
					var list = data.isBoardName;
					
					if (list > 0)
					{
						$("#boardNameInfo").removeClass('bl_o');
						$("#boardNameInfo").addClass('bl_x');
						$("#boardNameInfo").html("이미 사용 중입니다. 다른 이름을 입력해주세요.");
						boardTitleYn = 'N';
					}
					else
					{						
						$("#boardNameInfo").addClass('bl_o');
						$("#boardNameInfo").removeClass('bl_x');
						$("#boardNameInfo").html("사용할수 있는 게시판명  입니다.");
						boardTitleYn = 'Y';
					}
				}
			}
	 	});
	};
	
	//게시판 항목 저장
	var fnCreateBbs = function(data){
		PortalCommon.getJson({
			url: WEB_HOME+"/board100/insertAdminBbsList.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					//location.href=WEB_HOME+"/board100/getBbsCategoryList.do?type=2&kind=1&admin=1";
					location.href=WEB_HOME+"/adm/stat/getAdmBbsStatList.do";
				}
			}
		});
	};
	
	//관리자 지정
	var callbackManager = function(data){
		var json = $.parseJSON(data);
		for (var i=0; i < json.length; i++)
		{
			var contains = false;
			$obj = $('#managerCategories li');
	    	for( var j=0; j < $obj.length; j++)
	    	{
	    		if ($obj.eq(j).attr("id") == json[i].id)
    			{
	    			contains = true;
	    			break;
    			}
	    	}			
	    	if (!contains) $('#managerCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnManagerListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
		}
		$('#txt_manager_search').val('');
		
	};
	
	//관리자 선택 팝업
	var fnSearchPopup = function(){		
// 		var url = '../organization/personChart.do?type=1&callback=callbackManager'+'&search='+encodeURIComponent( $("#txt_manager_search").val());
// 	    PortalCommon.popupWindowModal(url, this,400,505);
		callbackKind = 'A';
		PortalCommon.popupWindowCenter('../organization/organizationChart2.do?type=2&callback=callbackManager', '관리자지정',900,515);
	};
	
	//공개여부 개인지정
	var fnPersonList = function(kind)
	{
		callbackKind = kind;
		
		PortalCommon.popupWindowCenter('../organization/organizationChart2.do?type=2&callback=callbackOpenPerson', '개인지정',900,515);
	};
	
	//공개여부 부서지정
	var fnDeptList = function(kind)
	{
		callbackKind = kind;
		PortalCommon.popupWindowCenter('../organization/organizationChart2.do?type=1&callback=callbackOpenDept', '부서지정',900,515);
	};
	
	
	

	//공개여부,게시자지정  조직도(부서)
    var callbackOpenDept = function(data){
    	
		var json = $.parseJSON(data);		
		if (callbackKind == 'O'){
			for (var i=0; i < json.length; i++)
			{
				var contains = false;
				$obj = $('#OpenDeptCategories li');
		    	for( var j=0; j < $obj.length; j++)
		    	{
		    		if ($obj.eq(j).attr("id") == json[i].id)
	    			{
		    			contains = true;
		    			break;
	    			}
		    	}	    	
		    	if (!contains) $('#OpenDeptCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnOpenDeptListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
			}
		}else{
			for (var i=0; i < json.length; i++)
			{
				var contains = false;
				$obj = $('#WriterDeptCategories li');
		    	for( var j=0; j < $obj.length; j++)
		    	{
		    		if ($obj.eq(j).attr("id") == json[i].id)
	    			{
		    			contains = true;
		    			break;
	    			}
		    	}
		    	if (!contains) $('#WriterDeptCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnWriterDeptListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
			}
		}
	};
	
    //게시자 지정 조직도(개인)
    var callbackWriterPerson = function(data){
    	
		var json = $.parseJSON(data);		
		for (var i=0; i < json.length; i++)
		{
			var contains = false;
			$obj = $('#WriterPersonCategories li');
	    	for( var j=0; j < $obj.length; j++)
	    	{
	    		if ($obj.eq(j).attr("id") == json[i].id)
    			{
	    			contains = true;
	    			break;
    			}
	    	}	 

	    	if (!contains) $('#WriterPersonCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnWriterPersonListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+'.'+json[i].name+'</li>');
		}
	};
    
    //게시자 지정 조직도(부서)
    var callbackWriterDept = function(data){    	
		var json = $.parseJSON(data);		
		for (var i=0; i < json.length; i++)
		{
			var contains = false;
			$obj = $('#WriterDeptCategories li');
	    	for( var j=0; j < $obj.length; j++)
	    	{
	    		if ($obj.eq(j).attr("id") == json[i].id)
    			{
	    			contains = true;
	    			break;
    			}
	    	}
	    	if (!contains) $('#WriterDeptCategories').append('<li id="'+json[i].id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnWriterDeptListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
		}
	};
    
   //게시판 생성
   var fnBbsCreate = function()
   {
	   //showProgressMsg('처리중입니다.');
	   var jsonRootArray = [];
	   
	   var JsonBbsBoardInfoObj  = {
			   "boardId" : ''  //게시판_아이디
			  , "boardTp" : ''  //게시판_유형
			  , "requDeptCode" : ''  //요청_부서_코드
			  , "requDeptName" : ''  //요청_부서_명
			  , "requUserId" : ''  //요청_사용자_아이디
			  , "requUserName" : ''  //요청_사용자_명
			  , "requDocNo" : ''  //요청_문서_번호
			  , "boardName" : ''  //게시판_명
			  , "boardKind" : ''  //게시판_종류
			  , "boardForm" : ''  //게시판_형태
			  , "boardFormSpec" : ''  //게시판_형태_상세
			  , "moblLinkYn" : ''  //모바일_연동_여부
			  , "boardOperYn" : ''  //게시판_운영_여부
			  , "adminAlertYn" : ''  //관리자_알림_여부
			  , "boardOpenDiv" : ''  //게시판_공개_구분
			  , "boardOpenDivSpec" : ''  //게시판_공개_구분_상세
			  , "boardOperDiv" : ''  //게시판_운영_구분
			  , "boardOperBgnDttm" : ''  //게시판_운영_시작_일시
			  , "boardOperEndDttm" : ''  //게시판_운영_종료_일시
			  , "notiTermDiv" : ''  //게시물_기간_구분
			  , "publAsgnDiv" : ''  //게시자_지정_구분
			  , "publAsgnDivSpec" : ''  //게시자_지정_구분_상세
			  , "boardAnmtUseYn" : ''  //게시판_공지_사용_여부
			  , "boardExplUseYn" : ''  //게시판_설명_사용_여부
			  , "boardExpl" : ''  //게시판_설명
			  , "makrDispDiv" : ''  //작성자_표기_구분
			  , "notiReadmanAsgnYn" : ''  //게시물_조회자_지정_여부
			  , "agrmOppUseYn" : ''  //찬성_반대_사용_여부
			  , "replyWrteDiv" : ''  //답글_쓰기_구분
			  , "opnFileUploadYn" : ''  //의견_파일_업로드_여부
			  , "opnFileUploadCnt" : ''  //의견_파일_업로드_갯수
			  , "opnWrteDiv" : ''  //의견_쓰기_구분
			  , "opnReplyUseDiv" : ''  //의견_답글_사용_구분
			  , "opnRealnameDiv" : ''  //의견_실명_구분
			  , "likeUseYn" : ''  //좋아요_사용_여부
			  , "nickUseYn" : ''  //닉네임_사용_여부
			  , "smsUseYn" : ''  //SMS_사용_여부
			  , "apndFileSz" : ''  //첨부_파일_사이즈
			  , "editDiv" : ''  //입력툴_구분
			  , "notiEmailSendYn" : ''  //게시물_이메일_발송_여부
			  , "outsideOpenDiv" : ''  //외부_공개_여부
			  , "outsideOpenDivSpec" : ''  //외부_공개_상세
			  , "popupYn" : ''  //팝업_여부
			  , "briefYn" : ''  //핫브리핑_여부
			  , "fcode" : ''  //FCODE
			  , "readDiv" : ''  //조회_구분
			  , "delYn" : ''  //삭제_여부
			  , "regrId" : ''  //등록자_아이디
			  , "regrName" : ''  //등록자_명
			  , "updrId" : ''  //수정자_아이디
			  , "updrName" : ''  //수정자_명
			  , "oldBrdId" : ''  //OLD_BRD_ID
			  , "oldParId" : ''  //OLD_PAR_ID
			  , "oldSeq" : ''  //OLD_SEQ
		      , 'BbsManagerList' : []    //관리자지정 리스트
		      , 'BbsPublicDeptList' : []    //공개여부 카테고리 부서
		      , 'BbsPublicPsnList' : []     //공개여부 카테고리 개인
		      , 'BbsMakerDeptList' : []     //게시자 부서지정
		      , 'BbsMakerPsnList' : []      //게시자 개인지정
		      
		};
	   
		if (boardTitleYn == 'N'){
	    	alert('게시판명은 이미 사용 중입니다. 다른 이름을 입력해주세요.');
	    	$('#id_boardName').focus();
    		return;
    	}
	    
		/////////////////////////////////////////////////////////////////////////
		// 게시판 속성 설정
		/////////////////////////////////////////////////////////////////////////
    	var BbsRequDeptName = $('#id_requDeptName').val();//요청_부서_명
    	var BbsRequUserName = $('#id_requUserName').val();//요청_사용자_명
    	var BbsRequDocNo = $('#id_requDocNo').val();//요청_문서_번호
    	
    	
    	if (BbsRequDeptName == null || BbsRequDeptName == '')
    	{
    		alert('신청부서명을 입력하세요');
    		$('#id_requDeptName').focus();
    		return;
    	}
    	if (BbsRequUserName == null || BbsRequUserName == '')
    	{
    		alert('신청자를 입력하세요');
    		$('#id_requUserName').focus();
    		return;
    	}
    	
    	//게시판 명
    	var BbsName = $('#id_boardName').val(); 
    	if (BbsName == null || BbsName == '')
    	{
    		alert('게시판 명을 입력하세요');
    		$('#id_boardName').focus();
    		return;
    	}    	    	
    	//게시판 유형
    	var BbsTp = '010'; // 일반게시판:010, 사용자 게시판:020    	
    	//게시판 종류
    	var BbsKind = $(':radio[name="boardKind"]:checked').val(); 
    	
    	//상태(운영/비운영)
    	var BbsOpState = $(':radio[name="boardOperYn"]:checked').val();

    	if ($('#managerCategories li').length == 0)
    	{
    		alert('관리자를 지정 하세요');
    		$('#txt_manager_search').focus();
    		return;
    	}
    	
    	//관리자 지정 카테고리
    	$obj = $('#managerCategories li');    	
    	for( var i=0; i < $obj.length; i++)
    	{
    		var jsonObject = {
        			'id' : $obj.eq(i).attr("id"),
        			'name' : $obj.eq(i).text(),
        			'div' : 'EMP',
        			'auth' : 'ADM'
        	};
    		JsonBbsBoardInfoObj.BbsManagerList[i] = jsonObject;
    	}
    	
    	//새로운 게시물 알림
    	var newNotiAlarm = $(':radio[name="adminAlertYn"]:checked').val();
    	
    	//공개여부
    	var BbsOpen = $(':radio[name="boardOpenDiv"]:checked').val();    	
    	var BbsOpenSub = '';
    	if (BbsOpen == '010') // 전체공개
    	{
    		BbsOpenSub = $('#boardOpenDivSub option:selected').val();
    		if (BbsOpenSub == 'null')
    		{
    			alert('전체공개 여부를  선택하세요');
    			$('#boardOpenDivSub').focus();
    			return;
    		}
    	}
    	
    	//개인/부서 공개여부 
    	if (BbsOpen == '030')
    	{
	    	if ($('#OpenDeptCategories li').length+ $('#OpenPersonCategories li').length == 0)
	    	{
	    		alert('부서/개인 공개여부 둘 중 하나는 입력 하셔야 합니다.');
	    		$('#boardOpenDivSub').focus();
	    		return;
	    	}
    	}
    	
    	//공개여부 카테고리 부서지정
    	$obj = $('#OpenDeptCategories li');    	
    	for( var i=0; i < $obj.length; i++)
    	{
    		var jsonObject = {
        			'id' : $obj.eq(i).attr("id"),
        			'name' : $obj.eq(i).text(),
        			'div' : 'DPT',
        			'auth' : 'RED'
        	};
    		JsonBbsBoardInfoObj.BbsPublicDeptList[i] = jsonObject;
    	}
    	
    	//공개여부 카테고리 개인지정
    	$obj = $('#OpenPersonCategories li');    	
    	for( var i=0; i < $obj.length; i++)
    	{
    		var jsonObject = {
        			'id' : $obj.eq(i).attr("id"),
        			'name' : $obj.eq(i).text(),
        			'div' : 'EMP',
        			'auth' : 'RED'
        	};
    		JsonBbsBoardInfoObj.BbsPublicPsnList[i] = jsonObject;
    	}
    	
    	//게시판 운영기간
    	var BbsOperDate = $(':radio[name="boardOperDiv"]:checked').val();
    	
    	var BbsOperStartDate = '1900-01-01';  //게시 운영 시작 일자
    	var BbsOperEndDate = '9999-12-31';    //게시 운영 종료 일자    	
    	if (BbsOperDate == '020')
    	{
    		BbsOperStartDate = $('#boardOperBgnDttm').val();
    		BbsOperEndDate = $('#boardOperEndDttm').val();
    	}
    	
    	BbsOperStartDate = BbsOperStartDate.replace('-','').replace('-','');
    	BbsOperEndDate = BbsOperEndDate.replace('-','').replace('-','');
    	
    	//게시자 지정
    	var BbsMaker = $(':radio[name="publDiv"]:checked').val();
    	
    	var BbsMakerSub = '';
    	if (BbsMaker == '010') // 전체공개
    	{
    		BbsMakerSub = $('#publDivSub option:selected').val();
    		if (BbsMakerSub == 'null')
    		{
    			alert('게시자 지정  공개 여부를  선택하세요');
    			$('#publDivSub').focus();
    			return;
    		}
    	}
    	    	
    	 //게시자 지정 개인/부서 공개여부 
    	if (BbsMaker == '030')
    	{
	    	if ($('#WriterDeptCategories li').length+ $('#WriterPersonCategories li').length == 0)
	    	{
	    		alert('부서/개인 공개여부 둘 중 하나는 입력 하셔야 합니다.');
	    		$('#publDiv_EMP').focus();
	    		return;
	    	}
    	}    	 
    	
    	//게시자 지정 부서지정
    	$obj = $('#WriterDeptCategories li');    	
    	for( var i=0; i < $obj.length; i++)
    	{
    		var jsonObject = {
        			'id' : $obj.eq(i).attr("id"),
        			'name' : $obj.eq(i).text(),
        			'div' : 'DPT',
        			'auth' : 'WRT'
        	};
    		JsonBbsBoardInfoObj.BbsMakerDeptList[i] = jsonObject;
    	}
    	
    	//게시자 지정 개인지정
    	$obj = $('#WriterPersonCategories li');    	
    	for( var i=0; i < $obj.length; i++)
    	{
    		var jsonObject = {
        			'id' : $obj.eq(i).attr("id"),
        			'name' : $obj.eq(i).text(),
        			'div' : 'EMP',
        			'auth' : 'WRT'
        	};
    		JsonBbsBoardInfoObj.BbsMakerPsnList[i] = jsonObject;
    	}
    	
    	//공지사항
    	var BbsNotice = $(':radio[name="boardNotice"]:checked').val();
    	
    	//게시판 설명여부
    	var BbsExplUse = $(':radio[name="boardExplUseYn"]:checked').val();
    	
    	//게시판 설명 내용
    	var BbsExplTxt =  $('#boardExpl').val();
    	
    	/////////////////////////////////////////////////////////////////////////
		// 게시판 종류가 폐쇄가 아닐경우만 설정(기타설정, 게시판형태, 모바일연동, 게시물기간구분)
		/////////////////////////////////////////////////////////////////////////
    	var BbsMakerDisplay='', BbsNotiReaderAsgn='', BbsYesNo='', BbsReply='', BbsReplyFileUploadUse='';
    	var BbsReplyFileSize='', BbsOpnWrite='', BbsOpnReply='';
    	var BbsOpnRealName='', BbsLikeUse='', BbsNickName='', BbsSmsUse='', BbsApndFileSize='';
		var BbsNotiMailSend='', BbsOupOpenUseType='', BbsOupOpenUseTypeSelect='';
		var BbsType='', BbsTypeSub='', mobileLink='', BbsNotiTermDivSelect='';//게시판형태, 모바일연동, 게시물기간구분
		
		if(BbsKind != '020'){//폐쇄가 아닐경우
			
			//게시판 타입
	    	BbsType = $(':radio[name="boardForm"]:checked').val();    	
	    	BbsTypeSub = ''; //컨텐츠형 상세
	    	if (BbsType == '030') // 컨텐츠형
	    	{
	    		BbsTypeSub = $('#boardFormSub option:selected').val();
	    		if (BbsTypeSub == 'null')
	    		{
	    			alert('컨텐츠형 종류를 선택하세요');
	    			$('#boardFormSub').focus();
	    			return;
	    		}
	    	}
	    	//모바일 연동(Y,N)
	    	mobileLink = $(':radio[name="moblLinkYn"]:checked').val();
	    	
	    	//게시물 기간구분 
	    	BbsNotiTermDivSelect = $('#notiTermDivSelect option:selected').val();
	    	
	    	
	    	if (BbsNotiTermDivSelect == '' )
	    	{
	    		alert('게시기간을 선택하세요');
    			$('#notiTermDivSelect').focus();
    			return;
	    	}
	    	
			//작성자 표기
			BbsMakerDisplay = $(':radio[name="makrDispDiv"]:checked').val();
			
	    	//게시물 조회자 지정
	    	BbsNotiReaderAsgn = $(':radio[name="notiReadmanAsgnYn"]:checked').val();
	    	
			//찬/반
			BbsYesNo = $(':radio[name="agrmOppUseYn"]:checked').val();
			
			//답글쓰기
			BbsReply = $(':radio[name="replyWrteDiv"]:checked').val();
			
			//의견작성시 파일업로드
			BbsReplyFileUploadUse = $(':radio[name="replyFileUploadYn"]:checked').val();
			BbsReplyFileSize = '0';
			if (BbsReplyFileUploadUse == 'Y')
	    	{
				BbsReplyFileSize = $('#fileUploadDiv option:selected').val();
	    		if (BbsReplyFileSize == '')
	    		{
	    			alert('파일업로드 갯수를  선택하세요');
	    			$('#fileUploadDiv').focus();
	    			return;
	    		}
	    	}
			//의견쓰기
			BbsOpnWrite = $(':radio[name="opnWrteDiv"]:checked').val();	
			
			//의견에 대한 답글
			BbsOpnReply = $(':radio[name="opnReplyUseDiv"]:checked').val();
			
			//의견실명
			BbsOpnRealName = $(':radio[name="opnRealnameDiv"]:checked').val();
			
			//좋아요
			BbsLikeUse = $(':radio[name="likeUseYn"]:checked').val();
			
			//닉네임
			BbsNickName = $(':radio[name="nickUseYn"]:checked').val();
			
			//SMS(경조사 게시판용)
			BbsSmsUse = $(':radio[name="smsUseYn"]:checked').val();
			
			//파일첨부(일반문서)
	   		//alert($('#apndFileSzDiv' ).html());
			//alert($("select[name=apndFileSzDiv]").val());
	   		//BbsApndFileSize = $('#apndFileSzDiv option:selected').val();
	   		BbsApndFileSize = $("select[name=apndFileSzDiv]").val();
	   		if (BbsApndFileSize == null || BbsApndFileSize =='null')
	   		{
	   			alert('첨부파일(일반문서) 용량을  선택하세요');
	   			$('#apndFileSzDiv').focus();
	   			return;
	   		}
	   		
			//게시물 메일발송
			BbsNotiMailSend = $(':radio[name="NotiMailSend"]:checked').val();
			
			//외부공개구분
			BbsOupOpenUseTypeSelect = $('#oupOpenUseTypeSelect option:selected').val();
			     
	  		if (BbsOupOpenUseTypeSelect == '')
	  		{
	  			alert('외부공개 허용여부를 선택하세요');
	  			$('#oupOpenUseTypeSelect').focus();
	  			return;
	  		}
	  		//외부공개상세
	  		BbsOupOpenUseType = '';
	  		if(BbsOupOpenUseTypeSelect == 'Y'){
	  			BbsOupOpenUseType = $(':radio[name="oupOpenUseType"]:checked').val();
	  		}
		}else{//폐쇄일경우 
			//의견메일발송여부
			BbsNotiMailSend = $(':radio[name="replyEmailSendYN"]:checked').val();
			BbsType = '010';
			BbsTypeSub = '';
			mobileLink ='N';
			BbsNotiTermDivSelect = '0';
			BbsMakerDisplay ='010';
			BbsNotiReaderAsgn = 'N';
			BbsYesNo = 'N';
			BbsReply = '030';
			BbsReplyFileUploadUse = 'N';
			BbsReplyFileSize = 0;
			BbsOpnWrite = 'N';
			BbsOpnReply = '020';
			BbsOpnRealName = '020';
			BbsLikeUse = 'N';
			BbsNickName ='N';
			BbsSmsUse = 'N';
			BbsApndFileSize = 0;
			BbsOupOpenUseTypeSelect = '010';
		
		}
  		
  		//console.log("BbsOupOpenUseType : "+BbsOupOpenUseType);
		
		BbsKind = fnCustomBbsKind(BbsKind);
		BbsType = fnCustomBbsType(BbsType);
  		
		JsonBbsBoardInfoObj.boardId= boardId ;  //게시판_아이디
		JsonBbsBoardInfoObj.boardTp = BbsTp ;  //게시판_유형
		JsonBbsBoardInfoObj.requDeptCode= '' ;  //요청_부서_코드
		JsonBbsBoardInfoObj.requDeptName= BbsRequDeptName ;  //요청_부서_명
		JsonBbsBoardInfoObj.requUserId= '' ;  //요청_사용자_아이디
		JsonBbsBoardInfoObj.requUserName= BbsRequUserName ;  //요청_사용자_명
		JsonBbsBoardInfoObj.requDocNo= BbsRequDocNo ;  //요청_문서_번호
		JsonBbsBoardInfoObj.boardName= BbsName ;  //게시판_명
		JsonBbsBoardInfoObj.boardKind= BbsKind ;  //게시판_종류
		JsonBbsBoardInfoObj.boardForm= BbsType ;  //게시판_형태
		JsonBbsBoardInfoObj.boardFormSpec= BbsTypeSub ;  //게시판_형태_상세
		JsonBbsBoardInfoObj.moblLinkYn= mobileLink ;  //모바일_연동_여부
		JsonBbsBoardInfoObj.boardOperYn= BbsOpState ;  //게시판_운영_여부
		JsonBbsBoardInfoObj.adminAlertYn= newNotiAlarm ;  //관리자_알림_여부
		JsonBbsBoardInfoObj.boardOpenDiv= BbsOpen ;  //게시판_공개_구분
		JsonBbsBoardInfoObj.boardOpenDivSpec= BbsOpenSub ;  //게시판_공개_구분_상세
		JsonBbsBoardInfoObj.boardOperDiv= BbsOperDate ;  //게시판_운영_구분
		JsonBbsBoardInfoObj.boardOperBgnDttm= BbsOperStartDate ;  //게시판_운영_시작_일시
		JsonBbsBoardInfoObj.boardOperEndDttm= BbsOperEndDate ;  //게시판_운영_종료_일시
		JsonBbsBoardInfoObj.notiTermDiv= BbsNotiTermDivSelect ;  //게시물_기간_구분
		JsonBbsBoardInfoObj.publAsgnDiv= BbsMaker ;  //게시자_지정_구분
		JsonBbsBoardInfoObj.publAsgnDivSpec= BbsMakerSub ;  //게시자_지정_구분_상세
		JsonBbsBoardInfoObj.boardAnmtUseYn= BbsNotice ;  //게시판_공지_사용_여부
		JsonBbsBoardInfoObj.boardExplUseYn= BbsExplUse ;  //게시판_설명_사용_여부
		JsonBbsBoardInfoObj.boardExpl= BbsExplTxt ;  //게시판_설명
		JsonBbsBoardInfoObj.makrDispDiv= BbsMakerDisplay ;  //작성자_표기_구분
		JsonBbsBoardInfoObj.notiReadmanAsgnYn= BbsNotiReaderAsgn ;  //게시물_조회자_지정_여부
		JsonBbsBoardInfoObj.agrmOppUseYn= BbsYesNo ;  //찬성_반대_사용_여부
		JsonBbsBoardInfoObj.replyWrteDiv= BbsReply ;  //답글_쓰기_구분
		JsonBbsBoardInfoObj.opnFileUploadYn= BbsReplyFileUploadUse ;  //의견_파일_업로드_여부
		JsonBbsBoardInfoObj.opnFileUploadCnt= BbsReplyFileSize ;  //의견_파일_업로드_갯수
		JsonBbsBoardInfoObj.opnWrteDiv= BbsOpnWrite ;  //의견_쓰기_구분
		JsonBbsBoardInfoObj.opnReplyUseDiv= BbsOpnReply ;  //의견_답글_사용_구분
		JsonBbsBoardInfoObj.opnRealnameDiv= BbsOpnRealName ;  //의견_실명_구분
		JsonBbsBoardInfoObj.likeUseYn= BbsLikeUse ;  //좋아요_사용_여부
		JsonBbsBoardInfoObj.nickUseYn= BbsNickName ;  //닉네임_사용_여부
		JsonBbsBoardInfoObj.smsUseYn= BbsSmsUse ;  //SMS_사용_여부
		JsonBbsBoardInfoObj.apndFileSz= BbsApndFileSize ;  //첨부_파일_사이즈(용량)
		JsonBbsBoardInfoObj.editDiv= '010' ;  //입력툴_구분
		JsonBbsBoardInfoObj.notiEmailSendYn= BbsNotiMailSend ;  //게시물_이메일_발송_여부
		JsonBbsBoardInfoObj.outsideOpenDiv= BbsOupOpenUseTypeSelect;//외부공개여부
		JsonBbsBoardInfoObj.outsideOpenDivSpec= BbsOupOpenUseType;//외부공개구분
		JsonBbsBoardInfoObj.popupYn= 'N' ;  //팝업_여부
		JsonBbsBoardInfoObj.briefYn= 'N' ;  //핫브리핑_여부
		JsonBbsBoardInfoObj.fcode= '' ;  //FCODE
		JsonBbsBoardInfoObj.readDiv= '' ;  //조회_구분
		JsonBbsBoardInfoObj.delYn= 'N' ;  //삭제_여부
		JsonBbsBoardInfoObj.regrId= '' ;  //등록자_아이디
		JsonBbsBoardInfoObj.regrName= '' ;  //등록자_명
		JsonBbsBoardInfoObj.updrId= '' ;  //수정자_아이디
		JsonBbsBoardInfoObj.updrName= '' ;  //수정자_명
		JsonBbsBoardInfoObj.oldBrdId= '' ;  //OLD_BRD_ID
		JsonBbsBoardInfoObj.oldParId= '' ;  //OLD_PAR_ID
		JsonBbsBoardInfoObj.oldSeq= '' ;  //OLD_SEQ
		
    	jsonRootArray[0] = JsonBbsBoardInfoObj;    	
		//console.log("JsonBbsBoardInfoObj : "+JSON.stringify(JsonBbsBoardInfoObj));
    	fnCreateBbs(JSON.stringify(JsonBbsBoardInfoObj));
    	
   };
   
   var fnCustomBbsKind = function(BbsKind){
		if ($("#boardKind_110").is(":checked")){
			BbsKind = '110';
		}else if ($("#boardKind_120").is(":checked")){
			BbsKind = '120';
		}else if ($("#boardKind_130").is(":checked")){
			BbsKind = '130';
		}
		return BbsKind;
   };
   
   var fnCustomBbsType = function(BbsType){
	   if ($("#boardForm_040").is(":checked")){
		   BbsType = '040';
	   }
	   return BbsType;
   };
   
   //조회값 설정
   var fnSetMenuCompo = function(){
	   
		//var bbsList = $.parseJSON('${bbsList}');
		var bbsList = $.parseJSON(jsonBbsList);
	       
		//var userList = $.parseJSON('${userMapList}');
		var userList = $.parseJSON(userMapList);
		boardKindByView(bbsList[0].boardKind);
		$("#id_requDeptName").val(bbsList[0].requDeptName);  //신청부서
		$("#id_requUserName").val(bbsList[0].requUserName);  //신청자
		$("#id_requDocNo").val(bbsList[0].requDocNo);  //신청공문번호
		$("#id_boardName").val(bbsList[0].boardName);  //게시판 명
		$('input:radio[name=boardKind]:input[value='+bbsList[0].boardKind+']').attr("checked", "true"); //게시판 종류
		$('input:radio[name=boardForm]:input[value='+bbsList[0].boardForm+']').attr("checked", "true"); //게시판 형태
		if (bbsList[0].boardForm == '030'){
			$("#boardFormSub").val(bbsList[0].boardFormSpec);
			$('#boardFormSub').removeAttr('disabled');
		}	   
		$('input:radio[name=moblLinkYn]:input[value='+bbsList[0].moblLinkYn+']').attr("checked", "true"); //모바일 연동	   
		$('input:radio[name=boardOperYn]:input[value='+bbsList[0].boardOperYn+']').attr("checked", "true"); //상태
		for (var i=0; i < userList.length; i++){  
			if (userList[i].mngAuth == 'ADM'){
				callbackKind = 'A';
				callbackOpenPerson("["+JSON.stringify(userList[i])+"]");
			}
// 		   else if(userList[i].mngAuth == 'EMP'){
// 			   callbackOpenPerson("["+JSON.stringify(userList[i])+"]");
// 		   }else if(userList[i].mngAuth == 'DPT'){
// 			   callbackOpenDept("["+JSON.stringify(userList[i])+"]");
// 		   }
	   }
	   $('input:radio[name=adminAlertYn]:input[value='+bbsList[0].adminAlertYn+']').attr("checked", "true"); //새로운 게시물 알림	   
	   $('input:radio[name=boardOpenDiv]:input[value='+bbsList[0].boardOpenDiv+']').attr("checked", "true"); //공개 여부
	
	   if (bbsList[0].boardOpenDiv == '010'){
		   $("#boardOpenDivSub").val(bbsList[0].boardOpenDivSpec);
	   }
	   
	   if (bbsList[0].boardOpenDiv == '030'){
		   $('#boardOpenDivSub').attr('disabled', 'true');
		   $("#div_OpenFlag").show();
		   
		   callbackKind = 'O';
		   for (var i=0; i < userList.length; i++){
			   if (userList[i].userDiv == 'EMP' && userList[i].mngAuth == 'RED'){
// 				if (userList[i].mngAuth == 'EMP'){
				   callbackOpenPerson("["+JSON.stringify(userList[i])+"]");
			   }
			   if (userList[i].userDiv == 'DPT' && userList[i].mngAuth == 'RED'){
// 				if (userList[i].mngAuth == 'DPT'){
				   callbackOpenDept("["+JSON.stringify(userList[i])+"]");
			   }
		   }
	   }
	   
	   $('input:radio[name=boardOperDiv]:input[value='+bbsList[0].boardOperDiv+']').attr("checked", "true"); //운영 기간
	   
	   if (bbsList[0].boardOperDiv == '020'){
		   $('#boardOperBgnDttm').val(bbsList[0].boardOperBgnDttm);
		   $('#boardOperEndDttm').val(bbsList[0].boardOperEndDttm);
		   $('#boardOperBgnDttm').removeAttr('disabled');
		   $('#boardOperEndDttm').removeAttr('disabled');
	   }
	    
	   $("#notiTermDivSelect").val(bbsList[0].notiTermDiv);
	   
	   $('input:radio[name=publDiv]:input[value='+bbsList[0].publAsgnDiv+']').attr("checked", "true"); //게시자 지정
	   
	   if (bbsList[0].publAsgnDiv == '010'){
		   $("#publDivSub").val(bbsList[0].publAsgnDivSpec);		   
	   }
	   
	   if (bbsList[0].publAsgnDiv == '030')
	   {
		   $('#publDivSub').attr('disabled', 'true');
		   $("#div_WriterFlag").show();
		   
		   callbackKind = 'W';
		   for (var i=0; i < userList.length; i++){
			   if (userList[i].userDiv == 'EMP' && userList[i].mngAuth == 'WRT'){
				   
				   callbackWriterPerson("["+JSON.stringify(userList[i])+"]");
			   }
			   if (userList[i].userDiv == 'DPT' && userList[i].mngAuth == 'WRT'){
				   callbackWriterDept("["+JSON.stringify(userList[i])+"]");
			   }
		   }
	   }
	   
	   $('input:radio[name=boardNotice]:input[value='+bbsList[0].boardAnmtUseYn+']').attr("checked", "true"); //공지
	   $('input:radio[name=boardExplUseYn]:input[value='+bbsList[0].boardExplUseYn+']').attr("checked", "true"); //게시판 설명
	   if(bbsList[0].boardExplUseYn == 'Y'){
		   $('#boardExpl').val(bbsList[0].boardExpl);
	   }else{
		   $('#boardExpl').hide();
	   }
	   
	   $('input:radio[name=makrDispDiv]:input[value='+bbsList[0].makrDispDiv+']').attr("checked", "true"); //작성자 표기
	   $('input:radio[name=notiReadmanAsgnYn]:input[value='+bbsList[0].notiReadmanAsgnYn+']').attr("checked", "true"); //게시물 조회자 지정
	   $('input:radio[name=agrmOppUseYn]:input[value='+bbsList[0].agrmOppUseYn+']').attr("checked", "true"); //찬/반
	   $('input:radio[name=replyWrteDiv]:input[value='+bbsList[0].replyWrteDiv+']').attr("checked", "true"); //답글쓰기
	   $('input:radio[name=replyFileUploadYn]:input[value='+bbsList[0].opnFileUploadYn+']').attr("checked", "true"); //답글작성시 파일업로드
	   if (bbsList[0].opnFileUploadYn == 'Y'){
		   $('#fileUploadDiv').val(bbsList[0].opnFileUploadCnt);
		   
	   }else{
		   $('#fileUploadDiv').attr('disabled', 'true');
	   }
	   
	   $('input:radio[name=opnWrteDiv]:input[value='+bbsList[0].opnWrteDiv+']').attr("checked", "true"); //의견 쓰기
	   $('input:radio[name=opnReplyUseDiv]:input[value='+bbsList[0].opnReplyUseDiv+']').attr("checked", "true"); //의견에 대한 답글
	   $('input:radio[name=opnRealnameDiv]:input[value='+bbsList[0].opnRealnameDiv+']').attr("checked", "true"); //의견실명
	   $('input:radio[name=likeUseYn]:input[value='+bbsList[0].likeUseYn+']').attr("checked", "true"); //좋아요
	   $('input:radio[name=nickUseYn]:input[value='+bbsList[0].nickUseYn+']').attr("checked", "true"); //닉네임
	   $('input:radio[name=smsUseYn]:input[value='+bbsList[0].smsUseYn+']').attr("checked", "true"); //SMS(경조사 게시판용)
	   $('input:radio[name=alertUseYn]:input[value='+bbsList[0].adminAlertYn+']').attr("checked", "true"); //알림
	   $('#apndFileSzDiv').val(bbsList[0].apndFileSz);//파일첨부(일반문서)	
	   if(bbsList[0].boardKind != '020'){//게시판종류 일반, 경조사
		   $('input:radio[name=NotiMailSend]:input[value='+bbsList[0].notiEmailSendYn+']').attr("checked", "true"); //게시물 메일발송
	   }else{
		   $('input:radio[name=replyEmailSendYN]:input[value='+bbsList[0].notiEmailSendYn+']').attr("checked", "true"); //게시물 메일발송
	   }
	   
	   $("#oupOpenUseTypeSelect").val(bbsList[0].outsideOpenDiv);//외부공개 여부
	   if(bbsList[0].outsideOpenDiv == 'N'){//비허용일경우
			
			$('#oupOpenUseType_010').attr("disabled", "true");
			$('#oupOpenUseType_020').attr("disabled", "true");
		}
	   $('input:radio[name=oupOpenUseType]:input[value='+bbsList[0].outsideOpenDivSpec+']').attr("checked", "true"); //외부공개상세
	   
	   //추가
	   $("#id_boardName").attr("disabled", true);
	   $('input:radio[name=boardForm]').attr("disabled", "true");
	   $("#boardFormSub").attr("disabled", true);
	   $('input:radio[name=opnWrteDiv]').attr("disabled", "true");
	   $('input:radio[name=replyWrteDiv]').attr("disabled", "true");
	   
	   fnCustomSet(bbsList[0]);
   };
   
   var fnCustomSet = function(bbsList){
	   if(bbsList.boardKind == '110' || bbsList.boardKind == '120' || bbsList.boardKind == '130'){
		   $('input:radio[name=boardForm]:input[value='+bbsList.boardKind+']').attr("checked", "true"); //게시판 종류
	   }
	   if(bbsList.boardForm == '140'){
		   $('input:radio[name=boardForm]:input[value='+bbsList.boardKind+']').attr("checked", "true"); //게시판 형태
	   }
   }
	
   var boardKindByView = function(kind){
	   if(kind == '020'){//페쇄일경우
		   $("#etcSetup").hide();
			$("#boardFormArea").hide();
			$("#moblLinkYnArea").hide();
			$("#notiTermDivSelectArea").hide();
			$("#replyEmailSendYNArea").show();
	   }else{
		   $("#boardFormArea").show();//게시판 형태
			$("#moblLinkYnArea").show();//모바일 연동
			$("#notiTermDivSelectArea").show();//게시판 기간
			$("#etcSetup").show();//기타설정
			$("#replyEmailSendYNArea").hide();//의견메일발송여부
			$('#fileUploadDiv').removeAttr('disabled');
			
	   }
   };
 
		

////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	fnMenuCompoInit();
	
	if (boardId != '')
	{
		fnSetMenuCompo();
	}
	
	//게시판 종류 설정
	$("#boardKind_010, #boardKind_020, #boardKind_030, #boardKind_110, #boardKind_120, #boardKind_130").click(function(eventObject) {
		boardKindByView($(this).val());
	});
	
	//게시판 형태 설정
	$("#boardForm_010").click(function(eventObject) {
		$('#boardFormSub').attr('disabled', 'true');
	});
	
	$("#boardForm_020").click(function(eventObject) {
		$('#boardFormSub').attr('disabled', 'true');
	});
	
	$("#boardForm_030").click(function(eventObject) {			
		$('#boardFormSub').removeAttr('disabled');
	});		
	
	$("#boardKind_110, #boardKind_120, #boardKind_130, #boardForm_040").click(function(eventObject) { 
		$('#boardFormSub').attr('disabled', 'true');
	});	
	
	$("#id_boardName").keyup(function(e) {
		fnBbsSearchName($("#id_boardName").val());
	});
	
	//게시판 운영기간
	$("#boardOperDiv_010").click(function(eventObject) {	
		$('#boardOperBgnDttm').attr('disabled', 'true');
		$('#boardOperEndDttm').attr('disabled', 'true');
	});
	$("#boardOperDiv_020").click(function(eventObject) {
		$('#boardOperBgnDttm').removeAttr('disabled');
		$('#boardOperEndDttm').removeAttr('disabled');
		
	});
	
	//게시판 공개 여부 이벤트 처리
	$("#boardOpenDiv_ALL").click(function(eventObject) {
		$('#boardOpenDivSub').removeAttr('disabled');
		$("#div_OpenFlag").hide();
		$("#OpenDeptCategories").empty();
		$("#OpenPersonCategories").empty();
	});
	$("#boardOpenDiv_CLO").click(function(eventObject) {
		$('#boardOpenDivSub').attr('disabled', 'true');
		$("#div_OpenFlag").hide();	
	});
	$("#boardOpenDiv_EMP").click(function(eventObject) {	
		$('#boardOpenDivSub').attr('disabled', 'true');
		$("#div_OpenFlag").show();
	});
	
	//게시자 지정 여부 이벤트 처리
	$("#publDiv_ALL").click(function(eventObject) {
		$('#publDivSub').removeAttr('disabled');
		$("#div_WriterFlag").hide();
		$("#WriterDeptCategories").empty();
		$("#WriterPersonCategories").empty();
	});
	$("#publDiv_CLO").click(function(eventObject) {
		$('#publDivSub').attr('disabled', 'true');
		$("#div_WriterFlag").hide();	
	});
	$("#publDiv_EMP").click(function(eventObject) {	
		$('#publDivSub').attr('disabled', 'true');
		$("#div_WriterFlag").show();	
	});
	
	//선택항목 삭제
	$("#btn_item_del").click(function(eventObject) {
		$obj = $('#div_Etc').find('ul');			
		for(var i=0; i < $obj.length; i++)
		{
			$chk_obj = $obj.eq(i).find('li');								
			//var $checkboxes = $chk_obj.find(':checkbox');
			if($chk_obj.find(':checkbox').is(":checked"))
			{
				//alert($obj.eq(i).attr("id") +' '+ $checkboxes.attr("id"));
				$obj.eq(i).remove();
				//$('#txt_view'+i+'').unbind("keypress");
			}
		}
	});
	
	$("#oupOpenUseTypeSelect").change(function() {
		
		var BbsOupOpenUseTypeSelect = $('#oupOpenUseTypeSelect option:selected').val(); 
		
		if(BbsOupOpenUseTypeSelect == 'N'){//비허용일경우
			
			$('#oupOpenUseType_010').attr("disabled", "true");
			$('#oupOpenUseType_020').attr("disabled", "true");
		}else{
			$('#oupOpenUseType_010').removeAttr('disabled');
			$('#oupOpenUseType_020').removeAttr('disabled');
		}
		
	});
	
	$("#txt_manager_search").keyup(function(e) {
		if (e.keyCode == '13') {
			//$("#id_btn_text").trigger("click");
			fnSearchPopup();
		}
	});
	
	//게시판 설명
	$("#boardExplUseYn_Y").click(function(eventObject) {
		$('#boardExpl').show();
	});
	$("#boardExplUseYn_N").click(function(eventObject) {
		$('#boardExpl').hide();
	});
	
	//답글작성시 파일업로드
	$("#replyFileUploadYn_Y").click(function(eventObject) {
		$('#fileUploadDiv').removeAttr('disabled');
	});
	
	$("#replyFileUploadYn_N").click(function(eventObject) {
		$('#fileUploadDiv').attr('disabled', 'true');
	});
	//파일첨부(일반문서)
	$("#apndFileUseYn_Y").click(function(eventObject) {
		$('#apndFileCntDiv').removeAttr('disabled');
		$('#apndFileSzDiv').removeAttr('disabled');
	});
	
	$("#apndFileUseYn_N").click(function(eventObject) {
		$('#apndFileCntDiv').attr('disabled', 'true');
		$('#apndFileSzDiv').attr('disabled', 'true');
	});
	
	//게시판 생성 버튼
	$("#btn_bbs_create, #btn_bbs_create2").click(function(eventObject) 
	{
		if (!confirm('저장 하시겠습니까?')) {
			return;
		}
		fnBbsCreate();
	});
	
	//게시판 취소 버튼
	$("#btn_bbs_cancel, #btn_bbs_cancel2").click(function(eventObject) {
		
		if (!confirm('취소 하시겠습니까?')) {
			return;
		}
		
		history.back();
	});
	
	//공개관련 RESET
	$("#btnResetBoardOpen").click(function(eventObject) {
		$("#OpenDeptCategories").empty();
		$("#OpenPersonCategories").empty();
	});
	
	//게시자관련 RESET
	$("#btnResetPubl").click(function(eventObject) {
		$("#WriterDeptCategories").empty();
		$("#WriterPersonCategories").empty();
	});	

});

