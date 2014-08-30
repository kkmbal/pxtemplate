var fnLoadPage = function()
{
	fnFrameReload();
};



//
var fnUnLoadPage = function()
{
};

// rePaint
var fnFrameReload = function()
{
	parent.document.getElementById("bbsFrame").height = "700px";
	parent.document.getElementById("bbsFrame").height = $(document).height()+"px"; //document.body.scrollHeight+400+"px";
	
	
};

//선택한 이미지 삭제
var fnImgListRemove = function(id)
{

	for(var i=0; i < jsonAppendImgList.length; i++)
	{
		var json = jsonAppendImgList[i];
		var fileId = json.apndFileName;
		var arrFileId = fileId.split(".");
		if (arrFileId[0] == id)
		{
			jsonAppendImgList.splice(i,1);
			break;
		}
	}
	$("#"+id).remove();
	
	
	fnFrameReload();
};

//동영상 리스트 삭제
var fnMovieListRemove = function(id)
{
	for(var i=0; i < jsonAppendMovList.length; i++)
	{
		var json = jsonAppendMovList[i];
		var fileId = json.mvpKey;
		if(fileId == id)
		{
			jsonAppendMovList.splice(i,1);
			break;
		}
	}
	$("#del_movie_apnd-"+id).remove();   
	fnFrameReload();
}


//설문에서 선택한 이미지 삭제
var fnResearchListRemove = function(id)
{
	for(var i=0; i < jsonAppendResearchList.length; i++)
	{
		var json = jsonAppendResearchList[i];
		if (json.apndFileId == id)
		{
			jsonAppendResearchList.splice(i,1);
			break;
		}
	}
	//첨부파일 경로 삭제 후 이미지 객체 삭제
	$("#"+id).prev().children().children().val("");
	$("#"+id).remove();
}

//두자리 리턴
var fnTwoDigit = function(ddd)
{
	var Result = '00';
	if (ddd.length == 1){
		Result = '0'+ddd;
	}else{
		Result = ddd;
	}
	return Result;
};

var fnSetCalendar = function()
{
	$('#openCloseDate').datepicker(
	{
			showOn: "button",     
			buttonImage: RES_HOME+'/images/ico_calendar_off.png',      
			buttonImageOnly: true,
			buttonText: "게시종료일자",
			showButtonPanel: true,
			onSelect:function (dateText, inst){
				var nowdate = new Date();						 
				var date_str = nowdate.getFullYear() + "-" + fnTwoDigit(''+(nowdate.getMonth()+1)) + "-" + fnTwoDigit(''+nowdate.getDate());
			    if (dateText < date_str)
			    {
			    	alert('현재 일자 보다 작을 수 없습니다.');
			    	$( "#openCloseDate" ).datepicker( "setDate", new Date() );
			    }
			}
	});		
	$( "#openCloseDate" ).datepicker( "setDate", basicCloseDttm );
	
	/*
	$('img.ui-datepicker-trigger').attr('style','margin-left:-7px; vertical-align:middle; cursor:pointer;');
	
	 $('img.ui-datepicker-trigger').click(function(){
		$('.ui-datepicker').css('left', $(this).position().left+150);
	}); 
	*/	
	 
	 $('#openReserveDate').datepicker(
				{
					showOn: "button",     
					buttonImage: RES_HOME+'/images/ico_calendar_off.png',      
					buttonImageOnly: true,
					buttonText: "게시시작일자",
					showButtonPanel: true,
					onSelect:function (dateText, inst){
						var nowdate = new Date();						 
						var date_str = nowdate.getFullYear() + "-" + fnTwoDigit(''+(nowdate.getMonth()+1)) + "-" + fnTwoDigit(''+nowdate.getDate());
					    if (dateText < date_str)
					    {
					    	alert('현재 일자 보다 작을 수 없습니다.');
					    	$( "#openReserveDate" ).datepicker( "setDate", new Date() );
					    }
					}
				}
	);
	$( "#openReserveDate" ).datepicker( "setDate", new Date() );
	
	/*
	$('img.ui-datepicker-trigger').attr('style','margin-left:-7px; vertical-align:middle; cursor:pointer;');
		
	 $('img.ui-datepicker-trigger').click(function(){
			$('.ui-datepicker').css('margin-left', $(this).position().left-400+"px");
	});
	*/
	 
	 
	 $('#close_date').datepicker(
				{
					showOn: "button",     
					buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
					buttonImageOnly: true,
					buttonText: "설문마감일자",
					showButtonPanel: true,
					beforeShow: function (input, inst){
					  	
					},
					onSelect:function (dateText, inst){
						var nowdate = new Date();						 
						var date_str = nowdate.getFullYear() + "-" + fnTwoDigit(''+(nowdate.getMonth()+1)) + "-" + fnTwoDigit(''+nowdate.getDate());
					    if (dateText < date_str)
					    {
					    	alert('현재 일자 보다 작을 수 없습니다.');
					    	$( "#openReserveDate" ).datepicker( "setDate", new Date() );
					    }
					},
				     onChangeMonthYear: function (){
				    	 $('.ui-datepicker').append('<iframe  frameborder="1" ></iframe>');

					}
				}
	);
	 
	 $( "#close_date" ).datepicker( "setDate", new Date() );
		
	/*
	 $('img.ui-datepicker-trigger').attr('style','margin-left:-7px; vertical-align:middle; cursor:pointer;');
	
	 $('img.ui-datepicker-trigger').click(function(){
			
		    var id =$(this).parent().find("input").attr("id");
		    
		    $('.ui-datepicker').css('margin-left', '0px');
			$('.ui-datepicker').css('margin-top', '0px');
		    
		    if (id == 'close_date')
		    {
		    	$('.ui-datepicker').css('left', $(this).position().left+20);
				$('.ui-datepicker').css('top', $(this).position().top-100);
				

		    }
		    else if (id == 'openCloseDate')
		    {
		    	$('.ui-datepicker').css('left', $(this).position().left+20);
				$('.ui-datepicker').css('top', $(this).position().top-10);
		    }
		    else
		    {
		    	$('.ui-datepicker').css('left', $(this).position().left+20);
				$('.ui-datepicker').css('top', $(this).position().top-110);
		    }
		 				
			
			var ifm_h = $('.ui-datepicker').height()+2;
			var ifm_w = $('.ui-datepicker').width();
			$('.ui-datepicker iframe').remove();
			$('.ui-datepicker').append('<iframe  frameborder="0" ></iframe>');
			$('.ui-datepicker iframe').css({
				'height':ifm_h,
				'width':'200px',
				'position':'absolute',
				'top':'-1px',
				'left':'-1px',
				'z-index':'-1'
			});
	});
	*/
	 $('img.ui-datepicker-trigger').attr('style','margin-left:-7px; vertical-align:middle; cursor:pointer;');
};

//공개여부 부서지정 삭제
var fnOpenDeptListRemove = function(id)
{
	$("#OpenDeptCategories #"+id).remove();
};

//공개여부 개인지정 삭제
var fnOpenPersonListRemove = function(id)
{
   $("#OpenEmpCategories #"+id).remove();
};

//공개여부 조직도(부서)
var callbackOpenDept = function(data){
	
	var json = $.parseJSON(data);		
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
    	if (!contains) $('#OpenDeptCategories').append('<li id="'+json[i].id+'"><a style="cursor:pointer;" onclick="javascript:fnOpenDeptListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
	}
};

//공개여부 조직도(개인)
var callbackOpenPerson = function(data){

	var json = $.parseJSON(data);		
	for (var i=0; i < json.length; i++)
	{
		var contains = false;
		$obj = $('#OpenEmpCategories li');
    	for( var j=0; j < $obj.length; j++)
    	{
    		if ($obj.eq(j).attr("id") == json[i].id)
			{
    			contains = true;
    			break;
			};
    	};	
    	if (!contains) $('#OpenEmpCategories').append('<li id="'+json[i].id+'"><a style="cursor:pointer;" onclick="javascript:fnOpenPersonListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+' '+json[i].name+'</li>');
	};
};

//부서코드 콜백 함수
var callbackCdlnDeptFname = function(data)
{
	var json = $.parseJSON(data);
	
	//$('#cdlnDeptFname').val(json[0].name); 
}

//게시물 저장
var fnBoardNotiCreate = function()
{
	var notiTitle = $("#txt_title").val().replace(/</g,"&lt;").replace(/>/g,"&gt;");
	var notiTitleOrgn = $("#txt_title").val().replace(/</g,"&lt;").replace(/>/g,"&gt;"); 
	
	var titleBoldYn = "N";
	var titleColorDiv = " ";
	
	if ($("#rt1").is(":checked")){
		titleBoldYn = "Y";
		notiTitle = "<b>"+notiTitle+"</b>";
	}
	
	if ($("#rt3").is(":checked")){		
		titleColorDiv = "RED";
		notiTitle = "<font color='red'>"+notiTitle+"</font>";
	}
	else
	{
		if ($("#rt2").is(":checked")){		
			titleColorDiv = "BLU";
			notiTitle = "<font color='blue'>"+notiTitle+"</font>";
		}
	}
	
	var notiConts = tinymce.activeEditor.getContent();

	var linkUrl = "";
	var notiTP = "010";
	
	var moblOpenDiv = $(':radio[name="moblOpenDiv"]:checked').val();
	var notiKind = $(':radio[name="apndKind"]:checked').val();
	if(!notiKind){
		notiKind = "010";
	}
	var nickUseYn = "N";

	if ($("#nickUseYn").is(":checked")){
		nickUseYn = "Y";
	}
	
	var userNick = $("#txt_nickname").val();
	
	var editDiv = "010";
	var rsrvYN = 'N';
	
	var nowdate = new Date();	
	var openReserveDate = '';
	var openReserveHour = fnTwoDigit(''+nowdate.getHours());
	var openReserveMin = fnTwoDigit(''+nowdate.getMinutes())+":"+ fnTwoDigit(''+nowdate.getSeconds());
	
	if ($("#chkReserveDate").is(":checked")){
		rsrvYN = 'Y';
		if($('#openReserveHour').length > 0){
			openReserveHour = $('#openReserveHour option:selected').val();
		}
		if($('#openReserveMin').length > 0){
			openReserveMin = $('#openReserveMin option:selected').val()+':00';
		}
	}
	
	var openReserveDate = $('#openReserveDate').val();
	
	var notiBgnDttm = openReserveDate+' '+openReserveHour+":"+openReserveMin;//$('#openReserveDate').val();
	
	var notiEndDttm = null;
	if($('#openCloseDate').val()){
		notiEndDttm = $('#openCloseDate').val()+' 23:59:59';
	}else{
		notiEndDttm = "9999-12-31 23:59:59";
	}
	//var notiOpenDiv = $(':radio[name="notiOpenDiv"]:checked').val();
	var notiOpenDiv = $('#notiOpenDiv').val();
	
	var agrmOppYn = 'N';
	if ($("#agrmOppYN").is(":checked")){
		agrmOppYn = 'Y';
	}
    
	var anmtYn = 'N';
	
	if ($("#rt4").is(":checked")){
		anmtYn = 'Y';
	};
	
	var notiOpenDivSpec='';
	if (userDiv == 'ALL'){
		notiOpenDivSpec = '010';
	}else if (userDiv == 'PUB'){
		notiOpenDivSpec = '020';
	}else if (userDiv == 'SGU'){
		notiOpenDivSpec = '030';
	}
	
	var opnPrmsYN = 'N';	
	if ($("#opnPrmsYN").is(":checked")){
		opnPrmsYN = 'Y';
	};
	
	
	var replyPrmsYn = 'N';	
	if ($("#replyPrmsYn").is(":checked")){
		replyPrmsYn = 'Y';
	};
	
	
	var opnMakrRealnameYn = $(':radio[name="opnMarkRealNameYN"]:checked').val();
	
	var notiTagLst = $("#txt_tag").val();
	if(notiTagLst){
		if (notiTagLst == '태그는 10개 이하로 입력 가능하며 쉼표로 구분합니다.')
		{
			notiTagLst = '';
		}
		
		var notiTagLstArr = notiTagLst.split(",");
		
		if (notiTagLstArr.length > 9)
		{
			alert('태그는 10개 이하로 입력 가능합니다. ');
			return;
		}
	}
	

	if (upNotiId == '') upNotiId = '*';
	
	
	var cdlnEvntCode = '';
	var cdlnObjrName = '';
	var cdlnDeptFname = '';
	var cdlnDeptName = '';
	var cdlnDeptCode = '';
	
	if (boardKind == '030')
	{
		cdlnEvntCode =  $('#cdlnEvntCode option:selected').val();
		
		if (cdlnEvntCode == 'null')
		{
			alert('경조사 구분을 선택하세요');
			return;
		}
		
		cdlnObjrName =  $('#cdlnObjrName').val();	
		if (cdlnObjrName == "")
		{
			alert('대상자를 입력하세요');
			return;
		}
		
		cdlnDeptFname =  $('#cdlnDeptFname').val();
		if (cdlnDeptFname == "")
		{
			alert('소속기관을 입력하세요');
			return;
		}
		
		cdlnDeptName =  $('#cdlnDeptName').val();
		if (cdlnDeptName == "")
		{
			alert('소속부서를 입력하세요');
			return;
		}
		
	}
	
	var jsonWriteObject = {
		 'notiId' : notiId
		, 'upNotiId' : upNotiId
		, 'sortSeq' : '0'
		, 'boardId' : boardId
		, 'emgcYn' : 'N'
		, 'anmtYn' : anmtYn
		, 'popupYn' : 'N'  
		, 'briefYn' : 'N'
		, 'moblOpenDiv' : moblOpenDiv
		, 'notiTitle' : notiTitle
		, 'notiTitleOrgn' : notiTitleOrgn
		, 'titleBoldYn' : titleBoldYn
		, 'titleColorDiv' : titleColorDiv
		, 'notiConts' : notiConts
		//, 'notiConts' : ''
		, 'linkUrl' : linkUrl
		, 'notiTp' : notiTP
		, 'notiKind' : notiKind
		, 'nickUseYn' : nickUseYn
		, 'userId' : userId
		, 'userNick' : userNick
		, 'userName' : userName
		, 'editDiv' : editDiv
		, 'rsrvYn' : rsrvYN
		, 'notiBgnDttm' : notiBgnDttm
		, 'notiEndDttm' : notiEndDttm
		, 'notiOpenDiv' : notiOpenDiv
		, 'notiOpenDivSpec' : notiOpenDivSpec
		, 'publAsgnDiv' : ''
		, 'publAsgnDivSpec' : ''
		, 'replyPrmsYn' : replyPrmsYn
		, 'replyMakrRealnameYn' : 'N'  
		, 'opnPrmsYn' : opnPrmsYN   //작성자 실명여부
		, 'opnMakrRealnameYn' : opnMakrRealnameYn  //실명여부
		, 'notiTagLst' : notiTagLst  
		, 'notiReadCnt' : '0'
		, 'notiOpnCnt' : '0'
		, 'notiAgrmCnt' : '0'
		, 'notiOppCnt' : '0'
		, 'notiLikeCnt' : '0'
		, 'moblSendCnt' : '0'
		, 'bfBoardId' : ''
		, 'bfNotiId' : ''
		, 'statCode' : ''
		, 'deptCode' : ''
		, 'deptName' : ''
		, 'deptFname' : ''
		, 'makrIp' : ''
		, 'brghCode' : ''
		, 'cdlnDeptCode' : cdlnDeptCode
		, 'cdlnDeptName' : cdlnDeptName
		, 'cdlnDeptFname' : cdlnDeptFname
		, 'cdlnObjrName' : cdlnObjrName
		, 'cdlnEvntCode' : cdlnEvntCode
		, 'delYn' : 'N'
		, 'regrId' : notiWriteId
		, 'regrName' : ''
		, 'regDttm' : ''
		, 'updrId' : notiWriteId
		, 'updrName' : ''
		, 'updDttm' : ''
		, 'oldBrdId' : ''
		, 'oldHeaderId' : ''
		, 'oldOrignalId' : ''
		, 'oldNoticeSeq' : ''
		, 'agrmOppYn' : agrmOppYn
		, 'notiReadmanAsgnYn' : notiReadmanAsgnYn
		, 'isAdmin' : isAdmin
		, 'type' : type
		, 'tmpNotiSeq' : tmpNotiSeq
		, 'AppendList' : []			
		, 'AppendFileList' : []  //첨부 리스트
		, 'NotiOpenDivDeptList' : []  // 조회자 지정 부서
		, 'NotiOpenDivEmpList' : []   // 조회자 지정 개인
		
	};
	
	//게시자 지정 부서지정
	$obj = $('#OpenDeptCategories li');	
	for( var i=0; i < $obj.length; i++)
	{
		var jsonObject = {
    			'id' : $obj.eq(i).attr("id"),
    			'name' : $obj.eq(i).text(),
    			'div'  : 'DPT',
    			'auth' : 'RED'
    	};
		jsonWriteObject.NotiOpenDivDeptList[i] = jsonObject;
	}
	
	//게시자 지정 개인지정
	$obj = $('#OpenEmpCategories li');	
	for( var i=0; i < $obj.length; i++)
	{
		var jsonObject = {
    			'id' : $obj.eq(i).attr("id"),
    			'name' : $obj.eq(i).text(),
    			'div'  : 'EMP',
    			'auth' : 'RED'
    	};
		jsonWriteObject.NotiOpenDivEmpList[i] = jsonObject;
	}

	if (write_apnd_kind == '020')  //이미지
	{
	   if (jsonAppendImgList.length == 0)
	   {
		  alert('추가한 이미지가 없습니다. 이미지를 추가하세요');
		  return;
	   }
	   jsonWriteObject.AppendList=jsonAppendImgList;
	}
	else if (write_apnd_kind == '030')  //동영상
	{
		
		if (jsonAppendMovList.length == 0)
		{
			alert('추가한 동영상이 없습니다. 동영상을 추가하세요');
		    return;
		}
		jsonWriteObject.AppendList=jsonAppendMovList;
	}
	else if (write_apnd_kind == '040')  //설문
	{
		var jsonSurveyObjectList = [];		   
		   
		   var limit_day  = $("#close_date").val();  //마감 일자
		   var limit_hour = $('#inp_hour option:selected').val(); //시간
		   
		   if (limit_hour == 'null')
		   {
			   alert('설문 마감 시간을 선택하세요');
			   $('#limit_hour').focus();
			   return;
		   }			   
		   var limit_min  = $('#inp_min option:selected').val();   //분
		   if (limit_min == 'null')
		   {
			   alert('설문 마감 시간(분)을 선택하세요');
			   $('#inp_min').focus();
			   return;
		   }
		   
		   var nowdate = new Date();		   
		   var date_str = nowdate.getFullYear() + "-" + fnTwoDigit(''+(nowdate.getMonth()+1)) + "-" + fnTwoDigit(''+nowdate.getDate())+nowdate.getHours()+nowdate.getMinutes()+'00'
		   
		   if (date_str > limit_day+limit_hour+limit_min+'00')
		   {
			   alert('설문마감시간이 현재 시간 보다 작을 수는 없습니다.');
			   return;
		   }
		   
		   var rshOpenYn  = $(':radio[name="researchOpenYn"]:checked').val(); //공개여부
		   var resObj = $('[id^="research-"]');
		   
		   for (var i=0; i < resObj.length; i++)  //질문갯수
		   {
			   
			   //설문 기본정보
			   var surveyJsonObject = {
			    'surveyNo' : '0'
			   , 'relaNotiKind' : ''
			   , 'notiId' : ''
			   , 'tmpNotiSeq' : '0'
			   , 'tmlnSeq' : '0'
			   , 'userNotiSeq' : '0'
			   , 'surveyClosDttm' : ''
			   , 'surveyRsltOpenYn' : ''
			   , 'surveyConts' : ''
			   , 'surveyTp' : ''
			   , 'delYn' : 'N'
			   , 'regrId' : ''
			   , 'regrName' : ''
			   , 'regDttm' : ''
			   , 'updrId' : ''
			   , 'updrName' : ''
			   , 'updDttm' : ''
			   , 'apndExmpList' : []
			   };
			   
			   var surveyConts = resObj.eq(i).find('textarea').val();  //질문내용
			   var view_cnt = resObj.eq(i).find('input:eq(0)').val();  //보기갯수
			   var view_type = '020';  //보기유형
			   if (resObj.eq(i).find('input:eq(1)').is(':checked')) 
			   {
				   view_type = '010';
			   }
			   
			   surveyJsonObject.surveyClosDttm = limit_day+limit_hour+limit_min+'00';  //마감일시
			   surveyJsonObject.surveyRsltOpenYn = rshOpenYn; //공개여부
			   surveyJsonObject.surveyConts = surveyConts;  //설문내용
			   surveyJsonObject.surveyTp = view_type;    //설문유형
			   
			   
			   var olObj = resObj.eq(i).find('li ol');		
			   
			   if (olObj.length < 2)
			   {
			     alert('보기 갯수는 적어도 2개 이상 이어야 합니다.');
			     return;
			   }
			   
			   for (var j=0; j < olObj.length; j++)  //보기갯수
			   {
				   var jsonObject = {
					    'surveyNo' : '0'
					   , 'exmplNo' : '0'
					   , 'exmplConts' : ''
					   , 'imgPath' : ''
					   , 'imgName' : ''
					   , 'prvwPath' : ''
					   , 'prvwName' : ''
					   , 'totCnt' : '0'
					   , 'rsltCnt' : '0'
					   , 'rsltRto' : '0'
					   , 'delYn' : 'N'
					   , 'regrId' : ''
					   , 'regrName' : ''
					   , 'regDttm' : ''
					   , 'updrId' : ''
					   , 'updrName' : ''
					   , 'updDttm' : ''
				   };
				   
				   if (view_type == '010')  //텍스트
				   {
					   if (olObj.eq(j).find('input:eq(0)').val() == '')
					   {
						 alert('보기항목 [텍스트]을 입력하세요');
						 return;
					   }
					   jsonObject.exmplConts = olObj.eq(j).find('input:eq(0)').val();
				   }
				   else  //이미지
				   {
					   if (olObj.eq(j).find('img').attr("src") == null)
					   {
						   alert('보기항목 [이미지]을  입력하세요');
						   return;
					   }
					   
					   var tmpStr = olObj.eq(j).find('img').attr("src");
					   var filePath = tmpStr.substring(0, tmpStr.lastIndexOf('/'));
					   var fileName = tmpStr.substring(tmpStr.lastIndexOf('/')+1,  tmpStr.length);
					   jsonObject.imgPath = filePath;
					   jsonObject.imgName = fileName;
					   
					   tmpStr = olObj.eq(j).find('input:eq(0)').val(); 
					   filePath = tmpStr.substring(0, tmpStr.lastIndexOf("\\"));
					   fileName = tmpStr.substring(tmpStr.lastIndexOf("\\")+1,  tmpStr.length);
					   
					   jsonObject.prvwPath = filePath;
					   jsonObject.prvwName = fileName;
					   
				   }
				   
				   surveyJsonObject.apndExmpList.push(jsonObject);
				   
			   }
			   jsonSurveyObjectList.push(surveyJsonObject);
		   }
		   
		   jsonWriteObject.AppendList = jsonSurveyObjectList;
	}
	
	if (jsonAppendFileList.length > 0)
	{
		jsonWriteObject.AppendFileList = jsonAppendFileList; 
	}
	
	//전체 공개면
	if (notiOpenDiv == '010')
	{
		jsonWriteObject.NotiOpenDivDeptList = [];
		jsonWriteObject.NotiOpenDivEmpList = [];
		
		var jsonObject = {
				'id' : 'ALL',
				'name' : '',
				'div'  : 'PUB',
				'auth' : 'RED'
		};
		jsonWriteObject.NotiOpenDivDeptList[0] = jsonObject;
	}
	//보드 권한 지정이면 게시물 권한 지정 X
	if (notiReadmanAsgnYn == 'N')
	{
		jsonWriteObject.NotiOpenDivDeptList = [];
		jsonWriteObject.NotiOpenDivEmpList = [];
		
	}
	
	fnNotiInsert(JSON.stringify(jsonWriteObject));
};

//등록버튼
var fnWriteInsert = function()
{
	if (nojoYn == 'N')
	{
		alert('업무 시간 이외 에는  등록 할 수 없습니다.');
		return;
	}	
	
	if (notiId != "")
	{
		//권한이없으면  아니면 등록 막음
		if (isAdmin == 'N')
		{
			if (notiWriteId != userId)
			{
				alert('귀하는 수정 할 권한이 없습니다.');
				return;
			}
		}
		
		if(type == 'copy'){
			if (!confirm('복사하시겠습니까?')) {
				return;
			}
		}else{
			if (!confirm('수정하시겠습니까?')) {
				return;
			}
		}
	}
	else
	{
		if (!confirm('등록하시겠습니까?')) {
			return;
		}
	}
	
	insertMode = "insert";
	
	//제목 체크
	if ($("#txt_title").val() == '' )
	{
		alert('제목을 입력하세요');
		$("#txt_title").focus();
		return;
	}
	//본문 내용
	if(tinymce.activeEditor.getContent() == ''){
		alert('본문 내용을 입력하세요');
		return;
	}
	
	var nickUseYn = "N";

	if ($("#nickUseYn").is(":checked")){
		nickUseYn = "Y";
	}
	
	var userNick = $("#txt_nickname").val();
	if(userNick){
		if (nickUseYn == 'Y')
		{
			if (userNick == '')
			{
				alert('닉네임을 입력하세요');
				$("#txt_nickname").focus();
				return;
			}
		}
	
		if (userNick.length > 10)
		{
			alert('닉네임의 길이가 너무 큽니다. 10자이내로 연락하세요.');
			$("#txt_nickname").focus();
			return;
		}
	}
	var notiEndDttm = $('#openCloseDate').val();
	
	if (notiEndDttm == '')
	{
		alert('게시 종료일자를 입력하세요');
		$('#openCloseDate').focus();
		return;
	}
	var rsrvYN = 'N';
	
	if ($("#chkReserveDate").is(":checked")){
		rsrvYN = 'Y';
	}
	
	
	if (rsrvYN == 'Y')
	{
		var openReserveHour = $('#openReserveHour option:selected').val();
		
		if (openReserveHour == 'null')
		{
			alert('예약 시간을 선택하세요');
			return;
		}
		var openReserveMin = $('#openReserveMin option:selected').val()+'00';
		if (openReserveMin == 'null')
		{
			alert('예약 분을 선택하세요');
			return;
		}
	}
	
	if (boardKind == '030')
	{
		var cdlnEvntCode =  $('#cdlnEvntCode option:selected').val();
		
		if (cdlnEvntCode == 'null')
		{
			alert('경조사 구분을 선택하세요');
			return;
		}
		
		var cdlnObjrName =  $('#cdlnObjrName').val();	
		if (cdlnObjrName == "")
		{
			alert('대상자를 입력하세요');
			return;
		}
		
		var cdlnDeptFname =  $('#cdlnDeptFname').val();
		if (cdlnDeptFname == "")
		{
			alert('소속기관을 입력하세요');
			return;
		}
		
		 var cdlnDeptName =  $('#cdlnDeptName').val();
		if (cdlnDeptName == "")
		{
			alert('소속부서를 입력하세요');
			return;
		}
		
	};
	
	if (write_apnd_kind == '020')  //이미지
	{
	   if (jsonAppendImgList.length == 0)
	   {
		  alert('추가한 이미지가 없습니다. 이미지를 추가하세요');
		  return;
	   }
	}
	else if (write_apnd_kind == '030')  //동영상
	{
		if (jsonAppendMovList.length == 0)
		{
			alert('추가한 동영상이 없습니다. 동영상을 추가하세요');
		    return;
		}
	}
	else if (write_apnd_kind == '040')  //설문
	{
		   var limit_day  = $("#close_date").val();  //마감 일자
		   var limit_hour = $('#inp_hour option:selected').val(); //시간
		   
		   if (limit_hour == 'null')
		   {
			   alert('설문 마감 시간을 선택하세요');
			   $('#limit_hour').focus();
			   return;
		   }			   
		   var limit_min  = $('#inp_min option:selected').val();   //분
		   if (limit_min == 'null')
		   {
			   alert('설문 마감 시간(분)을 선택하세요');
			   $('#inp_min').focus();
			   return;
		   }
		   var resObj = $('[id^="research-"]');		   
		   for (var i=0; i < resObj.length; i++)  //질문갯수
		   {
			   var view_type = '020';  //보기유형
			   if (resObj.eq(i).find('input:eq(1)').is(':checked')) 
			   {
				   view_type = '010';
			   }
			   var olObj = resObj.eq(i).find('li ol');				   
			   for (var j=0; j < olObj.length; j++)  //보기갯수
			   {
				   if (view_type == '010')  //텍스트
				   {
					   if (olObj.eq(j).find('input:eq(0)').val() == '')
					   {
						 alert('보기항목 [텍스트]을 입력하세요');
						 return;
					   }
				   }
				   else  //이미지
				   {
					   if (olObj.eq(j).find('img').attr("src") == null)
					   {
						   alert('보기항목 [이미지]을  입력하세요');
						   return;
					   }
				   }
			   }
		   }
	};

	
	//portalexpet 추가
	//첨부 파일 upload
	var fileExist = false;
	$("#apndFileform input:file[name^=upFile]").each(function(){
		if($(this).val() !== ""){
			fileExist = true;
		}
		return;
	});
	if(fileExist){
		fnMultiFileUpload();
	}else{
		fnBoardNotiCreate();
	}
};


var fnWriteCancel = function()
{
	if (loadingComplete) history.back(-1);
};

// 임시저장 버튼
var fnNotiTempInsert = function(data){
	
	var editMethod = "html";
    var mimeValue = null;
    var temp_mimeValue = null;
    
	PortalCommon.getJson({
		url: WEB_HOME+"/board230/insertBbsTmpNotiInfo.do?format=json",
	    data: {'data' : data, 'contents' : mimeValue, 'htmlSrc' : null},
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    type : 'POST',
		success :function(data){
			if(data.jsonResult.success ===true){
				
				
				if (pageIndex == 0) pageIndex = 1;
				
				if (boardId = 'BBS999999')  //임시게시판이면
				{
					parent.document.location.href = WEB_HOME+'/board100/boardFrame.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
				}
				else
				{
					if (kind == 'BBS')
					{
						if (boardForm == '010')
						{
							location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex='+pageIndex+'&pageUnit=10&orderType=default';
						}
						else if (boardForm == '020')  //SNS형
						{
							alert('게시판 종류가 SNS형 게시판 입니다.');
							location.href = WEB_HOME+'/board220/getBbsSnsBoardList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
						}
						else if (boardForm == '030')  //컨텐츠
						{
							if (boardFormSpec =='010') //이미지형
							{
								location.href = WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
							}
							else if (boardFormSpec =='020') //동영상
							{
								location.href = WEB_HOME+'/board212/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
							}
							else if (boardFormSpec =='030') //컨텐츠리스트형
							{
								location.href = WEB_HOME+'/board213/getBbsContentsBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
							}
							
						}
						else
						{
							location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex='+pageIndex+'&pageUnit=10&orderType=default';
						}
					}
				}
			}
		}
	});
};


//게시물 저장
var fnNotiInsert = function(data){
	var editMethod = "html";
    var mimeValue = null;
    var temp_mimeValue = null;
    
    var jsonObject = {
			'mimeValue' : mimeValue
	};
    
	PortalCommon.getJson({
		url: WEB_HOME+"/board230/insertBbsNotiInfo.do?format=json",
	    data: {'data' : data, 'contents' : jsonObject.mimeValue},
	    contentType: "application/x-www-form-urlencoded; charset=utf-8",
	    type : 'POST',
		success :function(data){
			if(data.jsonResult.success ===true){
				if (boardForm == '010')
				{
					location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
				}
				else if (boardForm == '020')  //SNS형
				{
					alert('게시판 종류가 SNS형 게시판 입니다.');
					location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
				}
				else if (boardForm == '030')  //컨텐츠
				{
					if (boardFormSpec =='010') //이미지형
					{
						location.href = WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
					}
					else if (boardFormSpec =='020') //동영상
					{
						location.href = WEB_HOME+'/board212/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
					}
					else if (boardFormSpec =='030') //컨텐츠형
					{
						location.href = WEB_HOME+'/board213/getBbsContentsBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
					}
					
				}
				else
				{
					location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
				}
			}
		}
	});
};


//게시물 임시저장
var fnBoardTempNotiCreate = function()
{
	var notiTitle = $("#txt_title").val().replace(/</g,"&lt;").replace(/>/g,"&gt;");
	
	
	var notiTitleOrgn = $("#txt_title").val().replace(/</g,"&lt;").replace(/>/g,"&gt;"); 
	
	var titleBoldYn = "N";
	var titleColorDiv = " ";
	
	if ($("#rt1").is(":checked")){
		titleBoldYn = "Y";
		notiTitle = "<b>"+notiTitle+"</b>";
	}
	
	if ($("#rt3").is(":checked")){		
		titleColorDiv = "RED";
		notiTitle = "<font color='red'>"+notiTitle+"</font>";
	}
	else
	{
		if ($("#rt2").is(":checked")){		
			titleColorDiv = "BLU";
			notiTitle = "<font color='blue'>"+notiTitle+"</font>";
		}
	}
	
	var notiConts = tinymce.activeEditor.getContent();
	var linkUrl = "";
	var notiTP = "010";
	
	var moblOpenDiv = $(':radio[name="moblOpenDiv"]:checked').val();
	var notiKind = $(':radio[name="apndKind"]:checked').val();
	if(!notiKind){
		notiKind = "010";
	}
	
	var nickUseYn = "N";

	if ($("#nickUseYn").is(":checked")){
		nickUseYn = "Y";
	}
	
	var userNick = $("#txt_nickname").val();
	
	var editDiv = "010";
	var rsrvYN = 'N';
	
	if ($("#chkReserveDate").is(":checked")){
		rsrvYN = 'Y';
	}
	
	
	var notiBgnDttm = $('#openReserveDate').val();	
	var notiEndDttm = $('#openCloseDate').val();
	if(!notiEndDttm){
		notiEndDttm = "9999-12-31 23:59:59";
	}
	
	var notiOpenDiv = $(':radio[name="notiOpenDiv"]:checked').val();
    
	var notiOpenDivSpec='';
	if (userDiv == 'ALL'){
		notiOpenDivSpec = '010';
	}else if (userDiv == 'PUB'){
		notiOpenDivSpec = '020';
	}else if (userDiv == 'SGU'){
		notiOpenDivSpec = '030';
	}
	
	var opnPrmsYN = 'N';
	
	if ($("#opnPrmsYN").is(":checked")){
		opnPrmsYN = 'Y';
	};
	
	
	var agrmOppYn = 'N';
	if ($("#agrmOppYN").is(":checked")){
		agrmOppYn = 'Y';
	}
	
	var replyPrmsYn = 'N';	
	if ($("#replyPrmsYn").is(":checked")){
		replyPrmsYn = 'Y';
	};
	
	var opnMakrRealnameYn = $(':radio[name="opnMarkRealNameYN"]:checked').val();
	
	var notiTagLst = $("#txt_tag").val();	

	if (tmpNotiSeq == '' ) tmpNotiSeq = 0;
	
	
	var cdlnEvntCode = '';
	var cdlnObjrName = '';
	var cdlnDeptFname = '';
	var cdlnDeptName = '';
	var cdlnDeptCode = '';
	
	if (boardKind == '030')
	{
		cdlnEvntCode =  $('#cdlnEvntCode option:selected').val();
		cdlnObjrName =  $('#cdlnObjrName').val();	
		cdlnDeptFname =  $('#cdlnDeptFname').val();
		cdlnDeptName =  $('#cdlnDeptName').val();
	}
	
	var anmtYn = 'N';
	if ($("#rt4").is(":checked")){
		anmtYn = 'Y';
	};
	
	var jsonWriteObject = {
		 'tmpNotiSeq' : tmpNotiSeq
		, 'upNotiId' : ''
		, 'sortSeq' : '0'
		, 'boardId' : boardId
		, 'emgcYn' : 'N'
		, 'anmtYn' : anmtYn
		, 'popupYn' : 'N'
		, 'briefYn' : 'N'
		, 'moblOpenDiv' : moblOpenDiv
		, 'notiTitle' : notiTitle
		, 'notiTitleOrgn' : notiTitleOrgn
		, 'titleBoldYn' : titleBoldYn
		, 'titleColorDiv' : titleColorDiv
		, 'notiConts' : notiConts
		//, 'notiConts' : ''
		, 'linkUrl' : linkUrl
		, 'notiTp' : notiTP
		, 'notiKind' : notiKind
		, 'nickUseYn' : nickUseYn
		, 'userId' : userId
		, 'userNick' : userNick
		, 'userName' : userName
		, 'editDiv' : editDiv
		, 'rsrvYn' : rsrvYN
		, 'notiBgnDttm' : notiBgnDttm
		, 'notiEndDttm' : notiEndDttm
		, 'notiOpenDiv' : notiOpenDiv
		, 'notiOpenDivSpec' : notiOpenDivSpec
		, 'publAsgnDiv' : ''
		, 'publAsgnDivSpec' : ''
		, 'replyPrmsYn' : replyPrmsYn
		, 'replyMakrRealnameYn' : 'N'  
		, 'opnPrmsYn' : opnPrmsYN   //작성자 실명여부
		, 'opnMakrRealnameYn' : opnMakrRealnameYn  //실명여부
		, 'notiTagLst' : notiTagLst  
		, 'notiReadCnt' : '0'
		, 'notiOpnCnt' : '0'
		, 'notiAgrmCnt' : '0'
		, 'notiOppCnt' : '0'
		, 'notiLikeCnt' : '0'
		, 'moblSendCnt' : '0'
		, 'bfBoardId' : ''
		, 'bfNotiId' : ''
		, 'statCode' : ''
		, 'deptCode' : ''
		, 'deptName' : ''
		, 'deptFname' : ''
		, 'makrIp' : ''
		, 'brghCode' : ''
		, 'cdlnDeptCode' : cdlnDeptCode
		, 'cdlnDeptName' : cdlnDeptName
		, 'cdlnDeptFname' : cdlnDeptFname
		, 'cdlnObjrName' : cdlnObjrName
		, 'cdlnEvntCode' : cdlnEvntCode
		, 'delYn' : 'N'
		, 'regrId' : notiWriteId
		, 'regrName' : ''
		, 'regDttm' : ''
		, 'updrId' : notiWriteId
		, 'updrName' : ''
		, 'updDttm' : ''
		, 'oldBrdId' : ''
		, 'oldHeaderId' : ''
		, 'oldOrignalId' : ''
		, 'oldNoticeSeq' : ''
		, 'agrmOppYn' : agrmOppYn
		, 'isAdmin' : isAdmin
		, 'AppendList' : []			
		, 'AppendFileList' : []  //첨부 리스트
		, 'NotiOpenDivDeptList' : []  // 조회자 지정 부서
		, 'NotiOpenDivEmpList' : []   // 조회자 지정 개인
		
	};
	
	//게시자 지정 부서지정
	$obj = $('#OpenDeptCategories li');	
	for( var i=0; i < $obj.length; i++)
	{
		var jsonObject = {
    			'id' : $obj.eq(i).attr("id"),
    			'name' : $obj.eq(i).text(),
    			'div'  : 'DPT',
    			'auth' : 'RED'
    	};
		jsonWriteObject.NotiOpenDivDeptList[i] = jsonObject;
	}
	
	//게시자 지정 개인지정
	$obj = $('#OpenEmpCategories li');	
	for( var i=0; i < $obj.length; i++)
	{
		var jsonObject = {
    			'id' : $obj.eq(i).attr("id"),
    			'name' : $obj.eq(i).text(),
    			'div'  : 'EMP',
    			'auth' : 'RED'
    	};
		jsonWriteObject.NotiOpenDivEmpList[i] = jsonObject;
	}

	if (write_apnd_kind == '020')  //이미지
	{
	   if (jsonAppendImgList.length == 0)
	   {
		  alert('추가한 이미지가 없습니다. 이미지를 추가하세요');
		  return;
	   }
	   jsonWriteObject.AppendList=jsonAppendImgList;
	   
	}
	else if (write_apnd_kind == '030')  //동영상
	{
		
		if (jsonAppendMovList.length == 0)
		{
			alert('추가한 동영상이 없습니다. 동영상을 추가하세요');
		    return;
		}
		jsonWriteObject.AppendList=jsonAppendMovList;
	}
	else if (write_apnd_kind == '040')  //설문
	{
		var jsonSurveyObjectList = [];		   
		   
		   var limit_day  = $("#close_date").val();  //마감 일자
		   var limit_hour = $('#inp_hour option:selected').val(); //시간
		   
		   if (limit_hour == 'null')
		   {
			   alert('설문 마감 시간을 선택하세요');
			   $('#limit_hour').focus();
			   return;
		   }			   
		   var limit_min  = $('#inp_min option:selected').val();   //분
		   if (limit_min == 'null')
		   {
			   alert('설문 마감 시간(분)을 선택하세요');
			   $('#inp_min').focus();
			   return;
		   }
		   var rshOpenYn  = $(':radio[name="researchOpenYn"]:checked').val(); //공개여부
		   var resObj = $('[id^="research-"]');
		   
		   for (var i=0; i < resObj.length; i++)  //질문갯수
		   {
			   
			   //설문 기본정보
			   var surveyJsonObject = {
			    'surveyNo' : '0'
			   , 'relaNotiKind' : 'TMP'
			   , 'notiId' : ''
			   , 'tmpNotiSeq' : '0'
			   , 'tmlnSeq' : '0'
			   , 'userNotiSeq' : '0'
			   , 'surveyClosDttm' : ''
			   , 'surveyRsltOpenYn' : ''
			   , 'surveyConts' : ''
			   , 'surveyTp' : ''
			   , 'delYn' : 'N'
			   , 'regrId' : ''
			   , 'regrName' : ''
			   , 'regDttm' : ''
			   , 'updrId' : ''
			   , 'updrName' : ''
			   , 'updDttm' : ''
			   , 'apndExmpList' : []
			   };
			   
			   var surveyConts = resObj.eq(i).find('textarea').val();  //질문내용
			   var view_cnt = resObj.eq(i).find('input:eq(0)').val();  //보기갯수
			   var view_type = '020';  //보기유형
			   if (resObj.eq(i).find('input:eq(1)').is(':checked')) 
			   {
				   view_type = '010';
			   }
			   
			   surveyJsonObject.surveyClosDttm = limit_day+limit_hour+limit_min+'00';  //마감일시
			   surveyJsonObject.surveyRsltOpenYn = rshOpenYn; //공개여부
			   surveyJsonObject.surveyConts = surveyConts;  //설문내용
			   surveyJsonObject.surveyTp = view_type;    //설문유형
			   
			   
			   var olObj = resObj.eq(i).find('li ol');				   
			   for (var j=0; j < olObj.length; j++)  //보기갯수
			   {
				   var jsonObject = {
					    'surveyNo' : '0'
					   , 'exmplNo' : '0'
					   , 'exmplConts' : ''
					   , 'imgPath' : ''
					   , 'imgName' : ''
					   , 'prvwPath' : ''
					   , 'prvwName' : ''
					   , 'totCnt' : '0'
					   , 'rsltCnt' : '0'
					   , 'rsltRto' : '0'
					   , 'delYn' : 'N'
					   , 'regrId' : ''
					   , 'regrName' : ''
					   , 'regDttm' : ''
					   , 'updrId' : ''
					   , 'updrName' : ''
					   , 'updDttm' : ''
				   };
				   
				   if (view_type == '010')  //텍스트
				   {
					   if (olObj.eq(j).find('input:eq(0)').val() == '')
					   {
						 alert('보기항목 [텍스트]을 입력하세요');
						 return;
					   }
					   jsonObject.exmplConts = olObj.eq(j).find('input:eq(0)').val();
				   }
				   else  //이미지
				   {
					   if (olObj.eq(j).find('img').attr("src") == null)
					   {
						   alert('보기항목 [이미지]을  입력하세요');
						   return;
					   }
					   var tmpStr = olObj.eq(j).find('img').attr("src");
					   var filePath = tmpStr.substring(0, tmpStr.lastIndexOf('/'));
					   var fileName = tmpStr.substring(tmpStr.lastIndexOf('/')+1,  tmpStr.length);
					   jsonObject.imgPath = filePath;
					   jsonObject.imgName = fileName;
					   
					   tmpStr = olObj.eq(j).find('input:eq(0)').val(); 
					   filePath = tmpStr.substring(0, tmpStr.lastIndexOf("\\"));
					   fileName = tmpStr.substring(tmpStr.lastIndexOf("\\")+1,  tmpStr.length);
					   
					   jsonObject.prvwPath = filePath;
					   jsonObject.prvwName = fileName;
					   
					   
				   }
				   
				   surveyJsonObject.apndExmpList.push(jsonObject);
				   
			   }
			   jsonSurveyObjectList.push(surveyJsonObject);
		   }
		   
		   jsonWriteObject.AppendList = jsonSurveyObjectList;
	}
	
	if (jsonAppendFileList.length > 0)
	{
		jsonWriteObject.AppendFileList = jsonAppendFileList; 
	}
	
	fnNotiTempInsert(JSON.stringify(jsonWriteObject));
};


//임시저장
var fnWriteTempInsert = function()
{

	if (nojoYn == 'N')
	{
		alert('업무 시간 이외 에는  등록 할 수 없습니다.');
		return;
	}

	
	if (!confirm('임시 저장 하시겠습니까?')) {
		return;
	}	
	
	insertMode = "temp";
	
	fnBoardTempNotiCreate();
};


var fnMovieEffect = function(id)
{
	/*sns 이미지 등록 - sns 가로,세로 가로정렬*/
    //$obj = $('.sns_img');
	  $obj = $('#'+id);
	  for( var j=0; j < $obj.length; j++)
	  {
		    var li_list = $obj[j];
		    $(li_list).find('img').each(function(){
				if ($(this).width() >= $(li_list).width()){
					$(this).css( {
				    	 'width': '100%'
					});
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




	//게시판 선택 팝업
	var fnBoardListView = function()
	{
		PortalCommon.windowPopup(WEB_HOME+'/organization/totCategoryChartPop.do?kind=2&type=1&admin=1&select=2', '카테고리',400,460);  //select옵션이 2 면 한개만 선택
	};

	//게시판명 선택
	var callbackBoardList = function(data)
	{
		var jsonArr = $.parseJSON(data);
		
		boardId = jsonArr[0].id;  //1건 이므로
		$("#txtBoardName").val(jsonArr[0].name);
		
	};

//첨부파일 추가
var fnAddFileList = function()
{
	var id = Math.floor(Math.random() * (9999998))+2;
	$("#apndFileform ul").append(
			//'<li id="apnd-'+id+'" class="ma_bot5"> '
			//+'<input type="text" class="text" style="width:476px;height:30px;" title="파일을 넣으세요" readonly> ' 
			//+'<a href="#" class="btn_set bt_style1 mv_file_a"><input type="file" class="mv_file" size="1" title="찾기" id="file-'+id+'" name="upFile-'+id+'"> '
			//+'<button type="button" class="btn_style2_2">파일</button></a> '
			//+'<a style="cursor:pointer;" onclick="javascript:fnDelFileList(\'apnd-'+id+'\')" class="btn_set bt_style1"><button type="button" class="btn_style2_2">삭제</button></a> '
			//+'</li>'
			'<li id="apnd-'+id+'" class="ma_bot5"> '
			+'<input type="text" class="text" style="width:476px" readonly>'
			+'<span class="file_wrap">'
			+'	<button class="btn_style1_2" type="button">파일</button>'
			+'	<input type="file" id="file-'+id+'" name="upFile-'+id+'" class="file_hidden" />'
			+'</span>'					    
			+'<button type="button" class="btn_style1_2" onclick="fnDelFileList(\'apnd-'+id+'\')">삭제</button>'		
			+'</li>'
	);
	
	
	$("#file-"+id).bind("change",function(e) {
		$(this).parent().prev().val($(this).val());
	});

	parent.document.getElementById("bbsFrame").height = $(document).height()+"px";
	
	return false;
}	

var fnDelFileList = function(id)
{
	$("#"+id).remove();
}

//첨부파일 전송
var fnMultiFileUpload = function()
{  
	var chk = true;
	$("#apndFileform input:file[name^=upFile]").each(function(){
		if(!PortalCommon.execUploadFileCheck($(this).val())){
			chk = false;
			return false;
		}
	});
	if(!chk){
		alert("추가할 수 없는 파일입니다.");
		return;
	}

	$("#apndFileform").ajaxSubmit({
		url : WEB_HOME+"/board230/bbsFileUpload.do",
		// contentType: "application/x-www-form-urlencoded; charset=utf-8",

		type : 'POST',
		data :  $("#apndFileform").serialize(),
		action: $("#dummy"),
		dataType : "script",
		success : function(data){
			makeApndFileList($.parseJSON(data));
			fnBoardNotiCreate();
		},error : function(){
			alert("전송 실패 했습니다.");
		},
		clearForm: true,
		resetForm: true
	});
}

//동영상첨부파일 추가
var fnAddMovieFileList = function()
{
	var id = Math.floor(Math.random() * (9999998))+2;
	/*
	$("#movieImgform ul").append(
	  '<li id="movieApnd-'+id+'" class="fo_11px ma_bot5">'
      +'<span class="inp_file2">'
      +'<input type="text" title="파일을 넣으세요">' 
      +'<a href="#" class="btn_file"><input type="file" size="1" title="찾기" id="moviefile-'+id+'" name="upMovieFile-'+id+'"></a>'
      +'<a style="cursor:pointer;" onclick="javascript:fnDelFileList(\'movieApnd-'+id+'\')" class="btn_grid2"><span class="btn_text">삭제</span></a>'
      +'</span>'
      +'</li>'
	);
	*/
	
	$("#movieImgformDiv ul").append(
    '<li id="movieApnd-'+id+'">'
	+'<form id="movieImgform-'+id+'" name="movieImgform-'+id+'" enctype="multipart/form-data" method="post">'
	+'<input type="text" style="width:100px">'
	+'<input type="file" size="1" title="동영상추가" id="apndMovie-'+id+'" name="bbsUpMovie-'+id+'">'
	+'<input type="button" onclick="javascript:fnDelFileList(\'movieApnd-'+id+'\')" value="삭제">'
	+'</form>'
	+'</li>'
	);
	
	$("#moviefile-"+id).bind("change",function(e) {
		$(this).parent().prev().val($(this).val());
	});
	
}

//글수정시 파일업로드 목록 구함.
var makeAlreadyUploadFileList = function()
{
	var apndList = '[]';
	if (kind == 'TMP')
	{
		apndList = tmpApndList;
	}
	else
	{
		apndList = savedApndList;
	}	

	for (var j=0; j < apndList.length; j++)  //첨부파일
	{
		var obj = apndList[j];
		if (obj.apndFileTp == '050')
		{
				var jsonObject = {				
						  'notiId' : ''
						, 'apndFileSeq' : obj.apndFileSeq
						, 'apndFileTp' : '050'
						, 'apndFileId' : obj.apndFileId
						, 'apndFileSz' : obj.apndFileSz
						, 'apndFileOrgn' : obj.apndFileOrgn
						, 'apndFileName' : obj.apndFileName
						, 'apndFilePath' : SAVE_DIR+'/'+obj.apndFilePath
						, 'apndFilePrvwPath' : obj.apndFilePrvwPath
						, 'apndFilePrvwName' : obj.apndFilePrvwName
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
				jsonAppendFileList.push(jsonObject);
		}
	}	
}

//파일업로드 성공한 목록
var makeApndFileList = function(obj){
	for(var i=0; i < obj.length; i++)
	{
		var json = obj[i];
		var jsonObject = {
				  'notiId' : ''
				, 'apndFileSeq' : '1'
				, 'apndFileTp' : '050'
				, 'apndFileId' : json.saveFileId
				, 'apndFileSz' : json.saveFileSize
				, 'apndFileOrgn' : json.original
				, 'apndFileName' : json.saveFileName
				, 'apndFilePath' : json.saveDir
				//, 'apndFilePrvwPath' : json.orgfilepath
				, 'apndFilePrvwPath' : json.saveDir  //파일수정시 참조하기위하여 저장
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
		jsonAppendFileList.push(jsonObject);
	}
};

//첨부파일 삭제
var fnApndFileListRemove = function(id)
{
	for(var i=0; i < jsonAppendFileList.length; i++)
	{
		var json = jsonAppendFileList[i];
		if (json.apndFileSeq == id)
		{
			
			jsonAppendFileList.splice(i,1);
			break;
		}
	}
	$("#delapnd-"+id).remove();
};



////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	

	//데이타 로드
	var fnDataLoad  =  function()
	{
		var userList = "";
		//boardId = '${boardId}';
		//tmpNotiSeq = '${tmpNotiSeq}';
		//notiId = '${notiId}';
		//boardForm = '${boardForm}';
		//boardFormSpec = '${boardFormSpec}';
		
		if (kind == 'BBS')  //게시판 쓰기
		{
			//var userList = ${userMapList};
			var userList = userMapList;
			if (notiId == "")
			{
				
				/* userDiv = userList[0].userDiv ;
				if (userDiv == 'ALL'||userDiv == 'PUB'||userDiv =='SGU') //전체 공개
				{
					
				}
				else
				{
					for (var i=0; i < userList.length; i++)
					{
						if (userList[i].userDiv == 'DPT')
						{
							fnOpenDept(userList[i]);
						}
						else if (userList[i].userDiv == 'EMP')
						{
							fnOpenEmp(userList[i]);
						}
					}
				} */
			}
			else if (notiId != "")
			{
				for (var i=0; i < userList.length; i++)
				{
					if (userList[i].userDiv == 'DPT')
					{
						fnOpenDept(userList[i]);
					}
					else if (userList[i].userDiv == 'EMP')
					{
						fnOpenEmp(userList[i]);
					}
				}
			}
			
			
			
			if (boardFormSpec == '010')  //이미지형
			{
			   $("#mobile_item").hide();
			  
			   $('input:radio[name=apndKind]:input[value='+'020'+']').attr("checked", "true");
			   $("#item_kind").attr("disabled",true);
			   $('#div_image_view').show();
			   write_apnd_kind = "020";
			   
			}
			else if (boardFormSpec == '020')  //동영상형
			{
				  $("#mobile_item").hide();
				  $('input:radio[name=apndKind]:input[value='+'030'+']').attr("checked", "true");
				  $("#item_kind").attr("disabled",true);
				  $('#div_media_view').show();
				  write_apnd_kind = "030";
			}
			else if (boardFormSpec == '030')  //컨텐츠 리스트형
			{
				  $("#mobile_item").hide();
				  $("#item_research").attr("disabled",true);
				  $('#div_media_view').show();
				  write_apnd_kind = "010";
			}
		};			
		
		fnSetCompoValue();
		
	}
	
	//게시판에 따른 화면  설정
	var fnSetCompoValue = function()
	{
		if (boardKind == '030')
		{
			//$("#mobile_item").hide();
			//$("#cdlnEvntTH").show();
			$("#cdlnEvntTH").css("visibility","visible");		
			
			//$("#cdlnEvntTD").show();
			$("#cdlnEvntTD").css("visibility","visible");
			//$("#cdlnObjrTH").show();
			$("#cdlnObjrTH").css("visibility","visible");
			//$("#cdlnObjrTD").show();
			$("#cdlnObjrTD").css("visibility","visible");
			$("#cdlnDeptFnameTR").show();
			
			$("#cdlnDeptFnameTH").css("visibility","visible");
			$("#cdlnDeptFnameTD").css("visibility","visible");
			
			$("#cdlnDeptFname").val('크로센트');
			

		}
		
		$('input:radio[name=moblOpenDiv]:input[value='+moblOpenDiv+']').attr("checked", "true"); //모바일 공개
		//의견 사용 여부
		if (opnWrteDiv == '020')
		{
			$("#opnPrmsTR").hide();
			$("#opnPrmsYN").removeAttr("checked");
		}
		//닉네임 사용 여부
		if (nickUseYn == 'N')
		{
			$("#nickUseYnTR").hide();
		}
		if (replyPrmsDiv = '020')
		{
			$("#replyPrmsTR").hide();
			$("#replyPrmsYn").removeAttr("checked");
		}
		//관리자면 
		if (isAdmin == 'Y')
		{
			$("#chkRedFont").show();
			
			$("#chkPopupYnTH").css("visibility","visible");
			$("#chkPopupYnTD").css("visibility","visible");
			
		}
		
		if (moblOpenYN == 'N')
		{
			$("#mobile_item").hide();
		}
		//찬성 반대
		if (agrmOppUseYn == 'N')
		{
			$("#agrmOppYnTR").hide();
		}
		
		//수정모드면 임시저장 막음
		if (notiId != "")
		{
			$("#tmpButton").hide();
			$("#tmpButton2").hide();
			
			//수정모드면 찬/반, 의견 항목 막음
			$("#agrmOppYnTR").hide();
			$("#opnPrmsTR").hide();
			
		}
		
		//게시물 권한이면 전체공개 막음
		if (notiReadmanAsgnYn == 'Y')
		{
			$("#notiOpenDiv_ALL_LI").css("display","none");
		}
		
		
		if(boardExplUseYn == 'Y' && boardExpl !=""){
			$("#boardExpl").html(boardExpl);
		}
		
		var notiKind = $(':radio[name="apndKind"]:checked').val();
		if(notiKind == '020'){
			//이미지
			$("#div_img_view_text").show();
			$("#div_img_view").show();
		}else if(notiKind == '030'){
			//동영상
			$("#div_mv_view").show();
		}
		
	};
	
	//부서지정
	var fnOpenDept = function(json){	
		var contains = false;
		
		$obj = $('#OpenDeptCategories li');
    	for( var j=0; j < $obj.length; j++)
    	{
    		if ($obj.eq(j).attr("id") == json.userId)
   			{
    			contains = true;
    			break;
   			}
    	}
    	if (!contains) $('#OpenDeptCategories').append('<li id="'+json.userId+'"><a class="ico_del" style="cursor:pointer;" onclick="javascript:fnOpenPersonListRemove(\''+json.userId+'\')" ></a>'+json.displayName+'</li>');
	};
	
	//조회자 지정
	var fnOpenEmp = function(json){	
		var contains = false;
		$obj = $('#OpenEmpCategories li');
    	for( var j=0; j < $obj.length; j++)
    	{
    		if ($obj.eq(j).attr("id") == json.userId)
   			{
    			contains = true;
    			break;
   			}
    	}
    	if (!contains) $('#OpenEmpCategories').append('<li id="'+json.userId+'"><a class="ico_del" style="cursor:pointer;" onclick="javascript:fnOpenPersonListRemove(\''+json.userId+'\')" ></a>'+json.displayName+'</li>');
	};
	
	//추가 입력항목 클릭 처리	
	$("#id_apnd_item").click(function(eventObject) {
		
	});
	
	//게시물 종류 클릭 처리
	 $("#item_none, #item_img, #item_media, #item_research").click(function(){

    	var btnId = $(this).attr("id");
    	if (btnId == 'item_none' && write_apnd_kind == '010' )  //일반
		{
			return;
		}
    	else if (btnId == 'item_img' && write_apnd_kind == '020' )  //이미지
		{
			return;
		}else if (btnId == 'item_media' && write_apnd_kind == '030')  //동영상
		{
			return;
		}else if (btnId == 'item_research' && write_apnd_kind == '040')  //설문
		{
			return;
		}
		
		/* $('#div_image_view').hide();
		$('#div_media_view').hide();
		$('#div_research_view').hide(); */
		
		$('#div_image_view').css("display","none");
		//$('#div_image_view').children().css("display","none");
		$("#div_image_view #bbsImgform").hide();
		
		$('#div_media_view').css("display","none");
		//$('#div_media_view').children().css("display","none");
		$("#div_media_view #movieImgform").hide();
		$('#div_research_view').css("display","none");
		//$('#div_research_view').children().css("display","none");
		
		switch(btnId){
		case "item_none":
			write_apnd_kind = '010';
			break;
		/* 이미지 */
		case "item_img":
			write_apnd_kind = '020';
			//$('#div_image_view').show();
			$('#div_image_view').css("display","block");
			//$('#div_image_view').children().css("display","block");
			$("#div_image_view #bbsImgform").show();
			break;
		/* 동영상 */	
		 case "item_media":
			 write_apnd_kind = '030';
			//$("#div_media_view").show();
			$('#div_media_view').css("display","block");
			//$('#div_media_view').children().css("display","block");
			$("#div_media_view #movieImgform").show();
			break; 
		/* 설문*/	
		 case "item_research":
			 write_apnd_kind = '040';
			//$("#div_research_view").show();
			$('#div_research_view').css("display","block");
			//$('#div_research_view').children().css("display","block");
			break;	
		}
		
		fnFrameReload();
		
    });
	
	//이미지 전송	(동적)	
	$("#apndImg").change(function(e) {
		//jQuery.ajaxSetup({cache:false});
		if (jsonAppendImgList.length >= imgUploadMax)
		{
			alert('최대 이미지 추가 개수는 '+imgUploadMax+'개입니다.\n 더이상 추가 할 수 없습니다.' );
			return;
		}
		
		if(!PortalCommon.imgUploadFileCheck(bbsImgform.bbsUpImg.value)){
			alert("추가할 수 없는 파일입니다.");
			return;
		}
	
		
 		$("#bbsImgform").ajaxSubmit({
			url : WEB_HOME+"/board230/bbsFileUpload.do",
			type : 'POST',
			data : $("#bbsImgform").serialize(),
			action: $("#dummy"),
			success : function(data){			
				loadImageList($.parseJSON(data));
			},error : function(){
				alert("전송 실패 했습니다.");
			},
			clearForm: true,
			resetForm: true
		});	
	});
	
	
	//이미지 추가 (동적)
	var loadImageList = function(obj)
	{
		var json = obj[0];
		
		var size = json.saveFileSize/1024/1024;
		
		if (size > imgUploadSize)
		{
			alert('최대 이미지 추가 사이즈는 '+imgUploadSize+'M 입니다.' );
			return;
		}
		
		
		
		$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($('#div_image_view .sns_imgadd'));

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
		
		$("#img-"+json.saveFileId).load(function(){
			fnImgEffect(json.saveFileId);
		});
		
		fnFrameReload();
	};
	
	//이미지 전송	(정적)	
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
	
	//이미지 추가 (정적)
	var loadImageList2 = function(form_id, obj)
	{
		var json = obj[0];
		
		$('<li id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'"  width="128" height="83"  alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($("#"+form_id));
//			$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="/portalxpert.template/upload/test.jpg" width="124" height="124" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($("#"+form_id));

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
	
	//이미지 위치조정 
	var fnImgEffect = function(id)
	{
		/*sns 이미지 등록 - sns 가로,세로 가로정렬*/
	    //$obj = $('.sns_img');
		  $obj = $('#'+id);
		  for( var j=0; j < $obj.length; j++)
		  {
			    var li_list = $obj[j];
			    $(li_list).find('img').each(function(){
				//$('.sns_img img').each(function(){
					if ($(this).width() >= $(li_list).width()){
						//alert('1:'+$(this).width()+' '+$(this).parents('li').width());
						$(this).css( {
					    	 'width': '100%'
						});
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
	
	//설문의 질문입력(이전에 입력한 정보가 지워지지 않도록 수정  20130620)		
	$("#question_cnt").css('imeMode','disabled').keyup(function(e) {
		//숫자만 허용
		$(this).val( $(this).val().replace(/[^0-9]/g, '') );
		if (e.keyCode == '13') {
			
			var v_cnt =  $('[id^="research-"]').length;  //이전에 보여진 보기수
			var q_cnt = $('#question_cnt').val();   //현재 입력한 보기수
			
			if (q_cnt > surveyUploadMax)
			{
				alert('질문 개수는 '+surveyUploadMax+'개를 초과 할 수 없습니다.');
				return;
			}
			
			var differ_cnt = v_cnt - q_cnt;
			
			if (differ_cnt > 0 )  //이전에 입력한 보기가 많을경우
			{
				for (var i= v_cnt-1; i >= q_cnt; i--)
				{
					$("#research-"+i).remove();
				}
			}
			else
			{
				for (var i= v_cnt; i < q_cnt; i++  )
				{
					fnAddViewForm(i);
				}
			}
		}
		
		fnFrameReload();
	});

	//설문의 질문입력 포커스 처리 
	$("#question_cnt").focusout(function(e) {
		
		var e = jQuery.Event("keyup");
		e.which = 13;
		e.keyCode = 13
		$('#question_cnt').trigger(e);
	});
	
	//설문 보기 항목 추가
	var fnAddViewForm = function(cnt)
	{
		$("#research_body").append(
    			'<tr id="research-'+cnt+'">'
    			+'<th scope="row" class="ver_top">질문 입력</th>'
    			+'<td>'
    			+'	<ul class="h_list k2 clearfix">'
    			+'		<li><textarea cols="30" rows="3" style="width:97%;"></textarea></li>'
    			+'		<li class="fo_11px">보기개수 <input id="txt_view'+cnt+'" type="text" class="inp_mnum te_right ma_lef5" title="보기개수">개'
    			+'			<span class="ma_lef25">보기유형<input type="radio" value="010" id="radio_txt-'+cnt+'" name="radio_name-'+cnt+'" class="ma_lef10" checked><label for="radio_txt-'+cnt+'">텍스트</label><input type="radio" value="020" id="radio_img-'+cnt+'" name="radio_name-'+cnt+'"><label for="radio_img-'+cnt+'">이미지</label>'
    			+'			</span>'
    			+'		</li>'		    			
    			+'		<li id="li_view-'+cnt+'">'
    			+'		</li>'
    			+'</ul>' 
    			+'</td>'
    			+'</tr>'
    					
    		);
	    	
		
	    	$('#txt_view'+cnt).css('imeMode','disabled').bind("keyup", function(e)
			{
	    		$(this).val( $(this).val().replace(/[^0-9]/g, '') );
				if (e.keyCode == '13')
				{
					var b_cnt = $('#li_view-'+cnt+' ol').length;
					var c_cnt = $('#txt_view'+cnt).val();
					
					if (c_cnt > surveyUploadView)
					{
						alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
						return;
					}
					
					if (b_cnt - c_cnt > 0)  //이전에 입력한 보기가 많을경우
						{
						for(var i = b_cnt; i > c_cnt; i--)
							{
							$('#ol_view-'+cnt+'_'+i).remove();
							}

						}
					else
						{
						var view_kind = $(':radio[name="radio_name-'+cnt+'"]:checked').val();
						if (view_kind == '010')
    					{
    						for(var i = b_cnt+1; i <= c_cnt; i++)
   							{
    							$('#li_view-'+cnt).append(
            							'<ol id="ol_view-'+cnt+'_'+i+'">'	
            							+'<li class="fo_11px">보기'+i+'<input type="text" class="inp_half ma_lef25" title="항목 보기'+i+'"></li>'
            							+'</ol>'
            					);
   							}
    					}
						else
							{
							//
							for(var i = b_cnt+1; i <= c_cnt; i++)
   							{
								//
								$('#li_view-'+cnt).append(
		       							   '<ol id="ol_view-'+cnt+'_'+i+'">'		
		        						   +'<li class="fo_11px">보기'+i+''        						   
		        						   +'	<span class="inp_file2">'        			               
		        			               +'		<input type="text" title="파일을 넣으세요" readOnly >'
		        			               +'		<a href="#" class="btn_file">'
		        			               +'			<form id="view_form-'+cnt+'_'+i+'" enctype="multipart/form-data" method="post">'
		        			               +'				<input type="file" class="file2" size="1" title="찾기" id="viewFile-'+cnt+'_'+i+'" name="viewFile-'+cnt+'_'+i+'">'
		        			               +'			</form>'
		        			               +'		</a>'        			               
		        			               +'	</span>'        			               
		        			               +'</li>'
		        			               +'</ol>'
		        						);
		        						
		        						$("#viewFile-"+cnt+'_'+i+"").bind("change",function() {
		        							$(this).parent().parent().prev().val($(this).val());
		        							    var formfile = $(this).parent();
		        							    var idx = formfile.attr("id");
		        							    idx = idx.substring(idx.indexOf("-")+1, idx.length);
		        								$(formfile).ajaxSubmit({
		        								url : WEB_HOME+"/board230/bbsFileUpload.do",
		        								action: $("#dummy"),
		        								type : 'POST',
		        								data : $(formfile).serialize(),
		        								success : function(data){
		        									//var json = $.parseJSON(data);
		        									fnResearchAddImg($.parseJSON(data), idx);//$(formfile).attr("id"));
		        								},error : function(){
		        									alert("전송 실패 했습니다.");
		        								},
		        								clearForm: false,
		        								resetForm: false
		        							});
		        						}); 
   							}

							}
						}
					parent.document.getElementById("bbsFrame").height = $(document).height();
				}
	    				
	    	});

			//

			$("#radio_txt-"+cnt).bind("click",function() {
				
				var c_cnt = $('#txt_view'+cnt).val();
				
				if (c_cnt > surveyUploadView)
				{
					alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
					return;
				}
				
				$('#li_view-'+cnt+' ol').remove();					
				var e = jQuery.Event("keyup");
				e.which = 13;
				e.keyCode = 13
				$('#txt_view'+cnt).trigger(e);
			});
			
			
			$("#radio_img-"+cnt).bind("click",function() {
				
				var c_cnt = $('#txt_view'+cnt).val();
				
				if (c_cnt > surveyUploadView)
				{
					alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
					return;
				}
				
				$('#li_view-'+cnt+' ol').remove();					
				var e = jQuery.Event("keyup");
				e.which = 13;
				e.keyCode = 13
				$('#txt_view'+cnt).trigger(e);						
			});
			
			
			
			$('#txt_view'+cnt).focusout(function(e) {
				var e = jQuery.Event("keyup");
				e.which = 13;
				e.keyCode = 13
				$('#txt_view'+cnt).trigger(e);
				
			});
			
			
	};
	
	var fnResearchAddImg = function(obj, cnt)
	{
		var json = obj[0];
		
		//기존에 선택한 이미지가 있으면 삭제하고 추가한다.
		
		if ($('#ol_view-'+cnt+" li").length > 1) $('#ol_view-'+cnt+" li").last().remove();
		
		$('#ol_view-'+cnt).append(
			//'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.saveDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.saveDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'	
				'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.webDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'
		);
		
		$("#rshimg-"+json.saveFileId).bind("load",function(){
			fnImgEffect3();
			fnFrameReload();
		});
		
		fnFrameReload();
	};
	
	//이미지 조절 
	var fnImgEffect3 = function()
	{
		/*sns 이미지 등록 - sns 가로,세로 가로정렬*/
	  $obj = $('.sns_img');
		
	  for( var j=0; j < $obj.length+1; j++)
	  {
			$('.sns_img img').each(function(){
				if ($(this).width() >= $(this).parents('li').width()){
					//alert('1:'+$(this).width()+' '+$(this).parents('li').width());
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
	
	//닉네임 클릭 처리
	$("#nickUseYn").click(function() {
		if ($(this).is(":checked"))
		{
			$("#txt_nickname").removeAttr("disabled");
		}
		else
		{
			$("#txt_nickname").attr("disabled",true);
		}
		
	});
	
	
	$("#txt_tag").click(function() {
		if ($(this).val() == '태그는 10개 이하로 입력 가능하며 쉼표로 구분합니다.'){
				$("#txt_tag").val('');
		}
	});
	
	//전체공개 클릭 처리
	$("#notiOpenDiv_ALL").click(function() {
		$("#notiOpenKind").hide();
	});
	//부서/개인 지정 클릭 처리
	$("#notiOpenDiv_PART").click(function() {
		$("#notiOpenKind").show();
	});
	
	//의견 허용 여부 클릭처리	
	$("#opnPrmsYN").click(function() {
		if ($(this).is(":checked")){
			$("#opnMarkRealNameYN_Y").removeAttr("disabled");
			$("#opnMarkRealNameYN_N").removeAttr("disabled");
		}
		else
		{
			$("#opnMarkRealNameYN_Y").attr('disabled', 'true');
			$("#opnMarkRealNameYN_N").attr('disabled', 'true');
		}
	});
	//예약 게시여부 클릭 처리
	$("#chkReserveDate").click(function() {
		if ($(this).is(":checked")){
			$("#openReserveDate").removeAttr("disabled");
			$("#openReserveHour").removeAttr("disabled");
			$("#openReserveMin").removeAttr("disabled");
			$('img.ui-datepicker-trigger').removeAttr("disabled");
		}
		else
		{
			$("#openReserveDate").attr('disabled', 'true');
			$("#openReserveHour").attr('disabled', 'true');
			$("#openReserveMin").attr('disabled', 'true');
			$('img.ui-datepicker-trigger').attr('disabled', 'true');
		}
	
	});
	
	//동영상 클릭 처리
	$("#movie_upload_select").change(function(eventObject) {
		if ($(this).val() == '010')
		{
			$("#movie_upload_1").show();
			$("#movie_upload_2").hide();
		}
		else
		{
			$("#movie_upload_1").hide();
			$("#movie_upload_2").show();
		}
		fnFrameReload();
	});
	
//목록 클릭 처리
$("#btn_item_list, #btn_item_list2").click(function(){

	/* $("#innoApDiv").remove();	
	$("#editor").remove(); */
	
	if (boardId == 'BBS999999')  //임시게시판이면
	{
		parent.document.location.href = WEB_HOME+'/board100/boardFrame.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
	}
	else
	{
		if (kind == 'BBS')
		{
			if (boardForm == '010' || boardForm == '040')
			{
				location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
			}
			else if (boardForm == '020')  //SNS형
			{
				
			}
			else if (boardForm == '030')  //컨텐츠
			{
				if (boardFormSpec =='010') //이미지형
				{
					location.href = WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
				}
				else if (boardFormSpec =='020') //동영상
				{
					location.href = WEB_HOME+'/board212/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
				}
				
			}
		}
		else
		{
			if (boardForm == '010' || boardForm == '040')
			{
				location.href = WEB_HOME+'/board210/getBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
			}
			else if (boardForm == '020')  //SNS형
			{
				
			}
			else if (boardForm == '030')  //컨텐츠
			{
				if (boardFormSpec =='010') //이미지형
				{
					location.href = WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
				}
				else if (boardFormSpec =='020') //동영상
				{
					location.href = WEB_HOME+'/board212/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
				}
				
			}
			
		}
	}
	
	
});

//동영상 전송		
$("input[id^=apndMovie]").change(function(e) {
//$("input[id^=apndMovie]").bind("change",function() {	alert('1');return;
//$(document).on("change","input[id^=apndMovie]",function() {
	
	
	if (jsonAppendMovList.length >= 1)
	{
		alert('최대 동영상 추가 개수는 '+1+'개입니다.\n 더이상 추가 할 수 없습니다.' );
		return;
	}
	

	if(!PortalCommon.movieUploadFileCheck($(this).val())){
		alert("추가할 수 없는 파일입니다.");
		return;
	}

	
		$("#movieImgform").ajaxSubmit({
		url : WEB_HOME+"/board230/movieFileUpload.do",
		type : 'POST',
		data : $("#movieImgform").serialize(),
		action: $("#dummy"),
		success : function(data){			
			loadMovieList($.parseJSON(data));
		},error : function(){
			alert("전송 실패 했습니다.");
			return;
		},
		clearForm: true,
		resetForm: true
	});	
});


//동영상 추가
var loadMovieList = function(obj)
{
	var json = obj[0];


	var jsonObject = {
		 'notiId' : ''
		, 'apndFileSeq' : '1'
		, 'apndFileTp' : '030'
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
		, 'mvpKey' : json.nkey
	};		

	
	jsonAppendMovList.push(jsonObject);
	
	/*
	var thumbnailImg = WEB_MOVIE_DIR+"/movie_process.jpg";
	$("#div_movie_add").prepend('<li class="sns_img" id="'+json.nkey+'"><img id="img-movie-'+json.nkey+'" src="'+thumbnailImg+'" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnMovieListRemove(\''+json.nkey+'\')" ><!--삭제--></a></li>');
	$("#img-movie-"+json.nkey).load(function(){
		fnMovieEffect(json.nkey);
	});	
	*/
	$("#movieFileDiv dl").append('<dd id="del_movie_apnd-'+json.saveFileId+'" style="border: 0 !important;">'+json.original+' <a class="ico_del" style="cursor:pointer;" onclick="javascript:fnMovieListRemove(\''+json.saveFileId+'\')" ></a></dd>');	
	
};	

$("input[name^=upFile]").change(function(e) {
	$(this).parent().prev().val($(this).val());
});

	//이노AP 설정
	//innApInstall();
	//달력 Setting
	fnSetCalendar();		
	//데이타 로드
	fnDataLoad();
	//화면Setting
	fnSetCompoValue();
	
	//editor 설정
	fnMakeWebEditor(RES_HOME, '#editor');
	

	
    ////////////////////////////////////////////////////////////////////////////////////////////////
	
	//설문 보기 이미지 추가
	var fnResearchAddImg2 = function(obj, cnt)
	{
		var json = obj[0];
		
		//기존에 선택한 이미지가 있으면 삭제하고 추가한다.
		
		if ($('#ol_view-'+cnt+" li").length > 1) $('#ol_view-'+cnt+" li").last().remove();
		
		$('#ol_view-'+cnt).append(
			//'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.saveDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.saveDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'	
				'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.webDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'
		);
		
		$("#rshimg-"+json.saveFileId).bind("load",function(){
			fnImgTmpEffect();
			fnFrameReload();
		});
		
		//parent.document.getElementById("bbsFrame").height = $(document).height();
	};	
	
	//설문 보기항목 추가
	var fnAddTmpViewForm = function(cnt)
	{
		$("#research_body").append(
				'<tr id="research-'+cnt+'">'
				+'<th scope="row" class="ver_top">질문 입력</th>'
				+'<td>'
				+'	<ul class="h_list k2 clearfix">'
				+'		<li><textarea cols="30" rows="3" style="width:97%;"></textarea></li>'
				+'		<li class="fo_11px">보기개수 <input id="txt_view'+cnt+'" type="text" class="inp_mnum te_right ma_lef5" title="보기개수">개'
				+'			<span class="ma_lef25">보기유형<input type="radio" value="010" id="radio_txt-'+cnt+'" name="radio_name-'+cnt+'" class="ma_lef10" checked><label for="radio_txt-'+cnt+'">텍스트</label><input type="radio" value="020" id="radio_img-'+cnt+'" name="radio_name-'+cnt+'"><label for="radio_img-'+cnt+'">이미지</label>'
				+'			</span>'
				+'		</li>'		    			
				+'		<li id="li_view-'+cnt+'">'
				+'		</li>'
				+'</ul>' 
				+'</td>'
				+'</tr>'
						
			);
    	
    	$('#txt_view'+cnt).css('imeMode','disabled').bind("keyup", function(e)
		{
    		$(this).val( $(this).val().replace(/[^0-9]/g, '') );
			if (e.keyCode == '13')
			{
				var b_cnt = $('#li_view-'+cnt+' ol').length;
				var c_cnt = $('#txt_view'+cnt).val();
				
				if (c_cnt > surveyUploadView)
				{
					alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
					return;
				}
				
				if (b_cnt - c_cnt > 0)  //이전에 입력한 보기가 많을경우
					{
					for(var i = b_cnt; i > c_cnt; i--)
						{
						$('#ol_view-'+cnt+'_'+i).remove();
						}

					}
				else
					{
					var view_kind = $(':radio[name="radio_name-'+cnt+'"]:checked').val();
					if (view_kind == '010')
					{
						for(var i = b_cnt+1; i <= c_cnt; i++)
							{
							$('#li_view-'+cnt).append(
        							'<ol id="ol_view-'+cnt+'_'+i+'">'	
        							+'<li class="fo_11px">보기'+i+'<input type="text" class="inp_half ma_lef25" title="항목 보기'+i+'"></li>'
        							+'</ol>'
        					);
							}
					}
					else
						{
						//
						for(var i = b_cnt+1; i <= c_cnt; i++)
							{
							//
							$('#li_view-'+cnt).append(
	       							   '<ol id="ol_view-'+cnt+'_'+i+'">'		
	        						   +'<li class="fo_11px">보기'+i+''        						   
	        						   +'	<span class="inp_file2">'        			               
	        			               +'		<input type="text" title="파일을 넣으세요" readOnly >'
	        			               +'		<a href="#" class="btn_file">'
	        			               +'			<form id="view_form-'+cnt+'_'+i+'" enctype="multipart/form-data" method="post">'
	        			               +'				<input type="file" class="file2" size="1" title="찾기" id="viewFile-'+cnt+'_'+i+'" name="viewFile-'+cnt+'_'+i+'">'
	        			               +'			</form>'
	        			               +'		</a>'        			               
	        			               +'	</span>'        			               
	        			               +'</li>'
	        			               +'</ol>'
	        						);
	        						
	        						$("#viewFile-"+cnt+'_'+i+"").bind("change",function() {
	        							$(this).parent().parent().prev().val($(this).val());
	        							    var formfile = $(this).parent();
	        							    var idx = formfile.attr("id");
	        							    idx = idx.substring(idx.indexOf("-")+1, idx.length);
	        								$(formfile).ajaxSubmit({
	        								url : WEB_HOME+"/board230/bbsFileUpload.do",
	        								action: $("#dummy"),
	        								type : 'POST',
	        								data : $(formfile).serialize(),
	        								success : function(data){
	        									//var json = $.parseJSON(data);
	        									fnResearchAddImg2($.parseJSON(data), idx);//$(formfile).attr("id"));
	        								},error : function(){
	        									alert("전송 실패 했습니다.");
	        								},
	        								clearForm: false,
	        								resetForm: false
	        							});
	        						}); 
							}

						}
					}
				fnImgTmpEffect();
			}
    				
    	});
	
			//
	
			
		$("#radio_txt-"+cnt).bind("click",function() {
			
			var c_cnt = $('#txt_view'+cnt).val();
			
			if (c_cnt > surveyUploadView)
			{
				alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
				return;
			}
			
			$('#li_view-'+cnt+' ol').remove();					
			var e = jQuery.Event("keyup");
			e.which = 13;
			e.keyCode = 13
			$('#txt_view'+cnt).trigger(e);
		});
		
		
		$("#radio_img-"+cnt).bind("click",function() {
			
			var c_cnt = $('#txt_view'+cnt).val();
			
			if (c_cnt > surveyUploadView)
			{
				alert('보기의 최대개수는 '+surveyUploadView+'개 입니다.');
				return;
			}
			
			$('#li_view-'+cnt+' ol').remove();					
			var e = jQuery.Event("keyup");
			e.which = 13;
			e.keyCode = 13
			$('#txt_view'+cnt).trigger(e);						
		});
		
		
		
		$('#txt_view'+cnt).focusout(function(e) {
			var e = jQuery.Event("keyup");
			e.which = 13;
			e.keyCode = 13
			$('#txt_view'+cnt).trigger(e);
			
		});
	};	
		var fnImgUptEffect = function(id)
		{
			/*sns 이미지 등록 - sns 가로,세로 가로정렬*/
		  //$obj = $('.sns_img');
			
			$obj = $('#'+id);
		  for( var j=0; j < $obj.length; j++)
		  {
			    var li_list = $obj[j];
			    $(li_list).find('img').each(function(){
				//$('.sns_img img').each(function(){
					if ($(this).width() >= $(li_list).width()){
						//alert('1:'+$(this).width()+' '+$(this).parents('li').width());
						$(this).css( {
					    	 'width': '100%'
						});
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
		
		//동적
		var loadUpdImageList = function(obj)
		{
			var json = obj;		
		
			$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.apndFileOrgn+'><img id="img-'+json.saveFileId+'" src="'+WEB_DIR+'/'+json.apndFilePath+'/'+json.apndFileName+'" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($('#div_image_view .sns_imgadd'));
		
			var jsonObject = {
				 'notiId' : ''
				, 'apndFileSeq' : jsonAppendImgList.length+1
				, 'apndFileTp' : '020'
				, 'apndFileSz' : json.apndFileSz
				, 'apndFileOrgn' : json.apndFileOrgn
				, 'apndFileName' : json.apndFileName
				//, 'apndFilePath' : WEB_DIR+'/'+json.apndFilePath
				, 'apndFilePath' : SAVE_DIR+'/'+json.apndFilePath
				, 'apndFilePrvwPath' : json.apndFilePrvwPath
				, 'apndFilePrvwName' : json.apndFilePrvwName
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
			
			$("#img-"+json.saveFileId).load(function(){
				fnImgUptEffect(json.saveFileId);
			});
			
			fnFrameReload();
		};	
		
		//정적
		var loadUpdImageList2 = function(obj, idx)
		{
			var json = obj;		

			$('<li id="'+json.saveFileId+'" name= '+json.apndFileOrgn+'><img id="img-'+json.saveFileId+'" src="'+WEB_DIR+json.apndFilePath+'/'+json.apndFileName+'"  width="128" height="83"  alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($("#bbsImgform"+idx));
			
			var jsonObject = {
					'notiId' : ''
						, 'apndFileSeq' : jsonAppendImgList.length+1
						, 'apndFileTp' : '020'
							, 'apndFileSz' : json.apndFileSz
							, 'apndFileOrgn' : json.apndFileOrgn
							, 'apndFileName' : json.apndFileName
							//, 'apndFilePath' : WEB_DIR+'/'+json.apndFilePath
							, 'apndFilePath' : SAVE_DIR+'/'+json.apndFilePath
							, 'apndFilePrvwPath' : json.apndFilePrvwPath
							, 'apndFilePrvwName' : json.apndFilePrvwName
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
			
			//$("#img-"+json.saveFileId).load(function(){
			//	fnImgUptEffect(json.saveFileId);
			//});
			
			fnFrameReload();
		};		
		
		
		var loadTmpImageList = function(obj)
		{
			var json = obj;
			$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="'+json.apndFilePrvwPath+json.apndFilePrvwName+'" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($('#div_image_view .sns_imgadd'));
			
			var jsonObject = {
				 'notiId' : ''
				, 'apndFileSeq' : jsonAppendImgList.length+1
				, 'apndFileTp' : '020'
				, 'apndFileSz' : json.apndFileSz
				, 'apndFileOrgn' : json.apndFileOrgn
				, 'apndFileName' : json.apndFileName
				, 'apndFilePath' : json.apndFilePath
				, 'apndFilePrvwPath' : json.apndFilePrvwPath
				, 'apndFilePrvwName' : json.apndFilePrvwName
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
			
			$("#img-"+json.saveFileId).bind("load",function(){
				fnImgUptEffect(json.saveFileId);
			});
			
			fnFrameReload();
		};
		
		var fnImgTmpEffect = function()
		{
			/*sns 이미지 등록 - sns 가로,세로 가로정렬*/
		  $obj = $('.sns_img');
			
		  for( var j=0; j < $obj.length+1; j++)
		  {
				$('.sns_img img').each(function(){
					if ($(this).width() >= $(this).parents('li').width()){
						//alert('1:'+$(this).width()+' '+$(this).parents('li').width());
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
		
		//임시저장 설문보기 설정
		var fnResearchTmpAddImg = function(obj, cnt)
		{
			var json = obj;		
			var saveFileId = json.imgName;
			$("#txt_survey-"+cnt).val(json.prvwPath+"\\"+json.prvwName);		
			var strArr = saveFileId.split(".");
			 $('#ol_view-'+cnt).append(
					'<li class="sns_img" id="'+strArr[0]+'"><img id="rshimg-'+strArr[0]+'" src="'+json.imgPath+'/'+json.imgName+'" alt="이미지"><input type="hidden" name='+json.webDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+strArr[0]+'\')"></a></li>'
			);
			 
			 
			 
			 
			$("#rshimg-"+strArr[0]).bind("load",function(){
				fnImgTmpEffect();
			}); 
			
		};
		
		var fnResearchAddImgLoad = function(obj, cnt)
		{
			var json = obj;
			
			
			//기존에 선택한 이미지가 있으면 삭제하고 추가한다.		
			if ($('#ol_view-'+cnt+" li").length > 1) $('#ol_view-'+cnt+" li").last().remove();
			
			$('#ol_view-'+cnt).append(
				//'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.saveDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.saveDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'	
					'<li class="sns_img" id="'+json.saveFileId+'"><img id="rshimg-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'" alt="이미지"><input type="hidden" name='+json.webDir+json.saveFileName+'><a href="#" class="ico_clo" title="삭제" onclick="javascript:fnResearchListRemove(\''+json.saveFileId+'\')"></a></li>'
			);
 
			
			$("#rshimg-"+json.saveFileId).bind("load",function(){
				fnImgTmpEffect();
				//fnFrameReload();
			});
			
			fnImgTmpEffect();
		};	
		
		var loadUpdMovieList = function(obj)
		{ 
			//var WEB_MOVIE_DIR = WEB_MOVIE_DIR;
			var json = obj;	
			
		    var jsonObject = {
					 'notiId' : ''
					, 'apndFileSeq' : jsonAppendMovList.length+1
					, 'apndFileTp' : '030'
					, 'apndFileSz' : json.apndFileSz
					, 'apndFileOrgn' : json.apndFileOrgn
					, 'apndFileName' : json.apndFileName
					, 'apndFilePath' : json.apndFilePath
					, 'apndFilePrvwPath' : ''
					, 'apndFilePrvwName' : ''
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
					, 'mvpKey' : json.mvpKey == null ? '' : json.mvpKey
			};
			
			jsonAppendMovList.push(jsonObject);
			
			/*
			$("#div_movie_add").prepend('<li class="sns_img" id="'+json.mvpKey+'"><img id="img-movie-'+json.mvpKey+'" src="'+WEB_MOVIE_DIR+'/'+json.apndFilePath+'/'+json.apndFileName+'" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnMovieListRemove(\''+json.mvpKey+'\')" ><!--삭제--></a></li>');
			$("#img-movie-"+json.mvpKey).load(function(){
				fnMovieEffect(json.mvpKey);
			});	
			*/
			$("#movieFileDiv dl").append('<dd id="del_movie_apnd-'+json.mvpKey+'" style="border: 0 !important;">'+json.apndFileOrgn+' <a class="ico_del" style="cursor:pointer;" onclick="javascript:fnMovieListRemove(\''+json.mvpKey+'\')" ></a></dd>');	
			
			
		};		
	
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
		
		if (notiId != "")  //글수정
		{
			//console.log('qqq')
 			//var notiList = ${notiList};
			//var surveyList = ${surveyList};
			//var surveyExmplList = ${surveyExmplList};
			var apndList = savedApndList;
			
			var json = notiList[0];
			var notiTitle = json.notiTitle;
			var notiTitleOrgn = json.notiTitleOrgn;
			var moblOpenDiv = json.moblOpenDiv;
			var titleBoldYn = json.titleBoldYn;
			var titleColorDiv = json.titleColorDiv;
			var notiConts = json.notiConts;
			var linkUrl = json.linkUrl;
			var notiTp = json.notiTp;
			var notiKind = json.notiKind;
			var nickUseYn = json.nickUseYn;
			var userNick = json.userNick;
			var userName = json.userName;
			var editDiv = json.editDiv;
			var rsrvYn = json.rsrvYn;
			var notiBgnDttm = json.notiBgnDttm;  //게시 시작일
			var notiEndDttm = json.notiEndDttm;  //게시 종료일
			var opnPrmsYn = json.opnPrmsYn;  //의견 허용 여부
			var replyPrmsYn = json.replyPrmsYn;  //답글 허용 여부
			
			var opnMakrRealnameYn = json.opnMakrRealnameYn;  //실명표기 여부
			var notiTagLst = json.notiTagLst;  //태그 
			var notiOpenDiv = json.notiOpenDiv;
			var agrmOppYn = json.agrmOppYn;
			
			var anmtYn = json.anmtYn; //공지
			

			$("#txt_title").val(notiTitleOrgn);  //제목
			
			if (titleBoldYn == 'Y')  //볼드체
			{
				$("#rt1").attr("checked", "true");
			}
			if (titleColorDiv == 'BLU')  //파랑색
			{
				$("#rt2").attr("checked", "true");
			}
			if (titleColorDiv == 'RED')  //빨강색
			{
				$("#rt3").attr("checked", "true");
			}
			$('input:radio[name=moblOpenDiv]:input[value='+moblOpenDiv+']').attr("checked", "true"); //모바일 공개
			$('input:radio[name=apndKind]:input[value='+notiKind+']').attr("checked", "true"); //게시물 종류
			
			
			//##############################추가항목 설정##############################
			//닉네임 사용 여부		
			if (nickUseYn == 'Y')
			{
				$("#nickUseYn").attr("checked", "true");
				$("#txt_nickname").removeAttr("disabled");
				$("#txt_nickname").val(userNick);
				$("#userNameTR").hide();
			}
			else
			{
				$("#userNameTR").show();
			}
			
			//게시 종료일
			$('#openCloseDate').val(notiEndDttm);
			
			//태그
			$("#txt_tag").val(notiTagLst);
			
			if (json.opnPrmsYn == 'Y')
			{
				$("#opnPrmsYN").attr("checked", "true");
				$("#opnMarkRealNameYN_Y").removeAttr("disabled");
				$("#opnMarkRealNameYN_N").removeAttr("disabled");			

				$('input:radio[name=opnMarkRealNameYN]:input[value='+opnMakrRealnameYn+']').attr("checked", "true"); //작성자 실명 표기
			}
			else
			{
				$("#opnPrmsYN").removeAttr("checked");
				$("#opnMarkRealNameYN_Y").attr('disabled', 'true');
				$("#opnMarkRealNameYN_N").attr('disabled', 'true');
			}
			
			
			if (replyPrmsYn  == 'Y')
			{
				$("#replyPrmsYn").attr("checked", "true");
			}			
			
			//예약 게시
			if (rsrvYn == 'Y')
			{
				$("#chkReserveDate").attr("checked", "true");
				
				$("#openReserveDate").removeAttr("disabled");
				$("#openReserveHour").removeAttr("disabled");
				$("#openReserveMin").removeAttr("disabled");
				
				$("#openReserveDate").val(notiBgnDttm.split(':')[0]);
				$("#openReserveHour").val(notiBgnDttm.split(':')[1]);
				$("#openReserveMin").val(notiBgnDttm.split(':')[2]);
				
			}
			
			$('input:radio[name=notiOpenDiv]:input[value='+notiOpenDiv+']').attr("checked", "true"); //조회자 지정
			if (notiOpenDiv == '020')
			{
				$("#notiOpenKind").show();
			}
			
			if (agrmOppYn  == 'Y')
			{
				$("#agrmOppYN").attr("checked", "true");
			}			
			
			if (notiKind == '020')  //이미지
			{
				write_apnd_kind = '020';
				$('#div_image_view').show();
				for (var i=0; i < apndList.length; i++)
				{
					var obj = apndList[i];
					if (obj.apndFileTp == '020')
					{
						loadUpdImageList2(obj, i);
					}
				}			
			}
			else if (notiKind == '030')  //동영상 
			{
				write_apnd_kind = '030';
				//$('#div_movie_add').show();
				$('#div_media_view').show();
				for (var i=0; i < apndList.length; i++)
				{
					var obj = apndList[i];
					if (obj.apndFileTp == '030')
					{
						loadUpdMovieList(obj);
					}
				}
			}
			else if (notiKind == '040')  //설문
			{
				$("#question_cnt").val(surveyList.length);
				
				$('#div_research_view').show();
				
				
				
				var start_idx = 0;
				var end_idx = 0;
				for(var i=0; i < surveyList.length; i++)
				{
					
					var json = surveyList[i];
					
					if (i == 0)  //설문 시간 설정
					{
					  var surveyCloseDttm = json.surveyClosDttm;					  
					  var arrSurvey = surveyCloseDttm.split(" ");					  
					  var surveyCloseDay = arrSurvey[0];					  
					  var tmpTime = arrSurvey[1];
					  var arrTime = tmpTime.split(":");					  
					  var surveyCloseHour = arrTime[0]; 
					  var surveyCloseMinute = arrTime[1]; 
					  
					  $("#close_date").val(surveyCloseDay);
					  $("#inp_hour").val(surveyCloseHour);
					  $("#inp_min").val(surveyCloseMinute);
					  
					  
					}
					
					
					fnAddTmpViewForm(i);				
					$('#research-'+i).find('textarea').val(json.surveyConts);
					
					$('input:radio[name=radio_name-'+i+']:input[value='+json.surveyTp+']').attr("checked", "true"); 
					
					var exmpCnt = 0;
					for(var k=0; k < surveyExmplList.length; k++)
					{
					  var tmp =  surveyExmplList[k];
					  if (json.surveyNo == tmp.surveyNo) exmpCnt++; 
					}
				
					$('#txt_view'+i).val(exmpCnt);
					
					end_idx += exmpCnt; 
					if (json.surveyTp == '010')  //텍스트
					{
						var idx = 1;
						for (var j=start_idx; j < end_idx; j++)
						{
							var obj = surveyExmplList[j];
							$('#li_view-'+i).append(
									'<ol id="ol_view-'+i+'_'+idx+'">'	
									+'<li class="fo_11px">보기'+idx+'<input type="text" class="inp_half ma_lef25" title="항목 보기'+idx+'" value="'+obj.exmplConts+'"></li>'
									+'</ol>'
							);
						}
						idx++;
					}
					else  //이미지
					{
						var idx = 1;
						for (var j=start_idx; j < end_idx; j++)						
						{
							$('#li_view-'+i).append(
									   '<ol id="ol_view-'+i+'_'+idx+'">'		
								   +'<li class="fo_11px">보기'+idx+''        						   
								   +'	<span class="inp_file2">'        			               
					               +'		<input id="txt_survey-'+i+'_'+idx+'" type="text" title="파일을 넣으세요" readOnly >'
					               +'		<a href="#" class="btn_file">'
					               +'			<form id="view_form-'+i+'_'+idx+'" enctype="multipart/form-data" method="post">'
					               +'				<input type="file" class="file2" size="1" title="찾기" id="viewFile-'+i+'_'+idx+'" name="viewFile-'+i+'_'+idx+'">'
					               +'			</form>'
					               +'		</a>'        			               
					               +'	</span>'        			               
					               +'</li>'
					               +'</ol>'
							);
							
							
							$("#viewFile-"+i+'_'+idx).bind("change",function() {
								$(this).parent().parent().prev().val($(this).val());
								    var formfile = $(this).parent();
								    var idx_id = formfile.attr("id");
								    idx_id = idx_id.substring(idx_id.indexOf("-")+1, idx_id.length);
									$(formfile).ajaxSubmit({
									url : WEB_HOME+"/board230/bbsFileUpload.do",
									action: $("#dummy"),
									type : 'POST',
									data : $(formfile).serialize(),
									success : function(data){
										//var json = $.parseJSON(data);
										fnResearchAddImg2($.parseJSON(data), idx_id);//$(formfile).attr("id"));
									},error : function(){
										alert("전송 실패 했습니다.");
									},
									clearForm: false,
									resetForm: false
								});
							}); 
							
							fnResearchTmpAddImg(surveyExmplList[j], i+'_'+idx);
							//fnResearchAddImg(tmpSurveyExmplList[j], j);
							idx++;
						}

					}
					
					start_idx = end_idx;
				}
				
				write_apnd_kind = '040';
			}
			
			for (var i=0; i < apndList.length; i++)  //첨부파일
			{
				var obj = apndList[i];
				
				if (obj.apndFileTp == '050')
				{
					$("#innoApDiv dl").append('<dd id="delapnd-'+obj.apndFileSeq+'" style="border: 0 !important;">'+obj.apndFileOrgn+' <a class="ico_del" style="cursor:pointer;" onclick="javascript:fnApndFileListRemove(\''+obj.apndFileSeq+'\')" ></a></dd>');
				}
			}
			
			makeAlreadyUploadFileList();
			
			//첨부목록
			$("#innoApDiv").show();
			
			
			if (boardKind == '030')  //경조사
			{
				$("#cdlnEvntCode").val(json.cdlnEvntCode);
				$("#cdlnDeptName").val(json.cdlnDeptName);
				$("#cdlnDeptFname").val(json.cdlnDeptFname);
				$("#cdlnObjrName").val(json.cdlnObjrName);
				
			};
			
			//공지
			if (anmtYn  == 'Y')
			{
				$("#rt4").attr("checked", "true");
			}
			
			
			
			
			fnFrameReload();
			$("#editor").val(notiConts);
		};
		
		
		if (kind == 'TMP')  //임시저장 게시판		
		{

			
			var json = tmpNotiList[0];
			var notiTitle = json.notiTitle;
			var notiTitleOrgn = json.notiTitleOrgn;
			var moblOpenDiv = json.moblOpenDiv;
			var titleBoldYn = json.titleBoldYn;
			var titleColorDiv = json.titleColorDiv;
			var notiConts = json.notiConts;
			var linkUrl = json.linkUrl;
			var notiTp = json.notiTp;
			var notiKind = json.notiKind;
			var nickUseYn = json.nickUseYn;
			var userNick = json.userNick;
			var userName = json.userName;
			var editDiv = json.editDiv;
			var rsrvYn = json.rsrvYn;
			var notiBgnDttm = json.notiBgnDttm;  //게시 시작일
			var notiEndDttm = json.notiEndDttm;  //게시 종료일
			var opnPrmsYn = json.opnPrmsYn;  //의견 허용 여부
			var replyPrmsYn = json.replyPrmsYn;  //답글 허용 여부
			var opnMakrRealnameYn = json.opnMakrRealnameYn;  //실명표기 여부
			var notiTagLst = json.notiTagLst;  //태그 
			var notiOpenDiv = json.notiOpenDiv;
			var agrmOppYn = json.agrmOppYn;
			boardName = json.boardName;
			boardId = json.boardId;
			
			$("#txtBoardName").val(boardName);
			
			$('#tmpBoardName').css("display","block");
			$('#tmpBoardName').children().css("display","block");
			
			
			$("#txt_title").val(notiTitleOrgn);  //제목
			
			if (titleBoldYn == 'Y')  //볼드체
			{
				$("#rt1").attr("checked", "true");
			}
			if (titleColorDiv == 'BLU')  //파랑색
			{
				$("#rt2").attr("checked", "true");
			}
			if (titleColorDiv == 'RED')  //빨강색
			{
				$("#rt3").attr("checked", "true");
			}
			
			$('input:radio[name=moblOpenDiv]:input[value='+moblOpenDiv+']').attr("checked", "true"); //모바일 공개
			$('input:radio[name=apndKind]:input[value='+notiKind+']').attr("checked", "true"); //게시물 종류
			
			
			//##############################추가항목 설정##############################
			//닉네임 사용 여부		
			if (nickUseYn == 'Y')
			{
				$("#nickUseYn").attr("checked", "true");
				$("#txt_nickname").removeAttr("disabled");
				$("#txt_nickname").val(userNick);
			}
			
			//게시 종료일
			$('#openCloseDate').val(notiEndDttm);
			
			//태그
			$("#txt_tag").val(notiTagLst);
			
			if (opnPrmsYn == 'Y')
			{
				$("#opnPrmsYN").attr("checked", "true");
				$("#opnMarkRealNameYN_Y").removeAttr("disabled");
				$("#opnMarkRealNameYN_N").removeAttr("disabled");			

				$('input:radio[name=opnMarkRealNameYN]:input[value='+opnMakrRealnameYn+']').attr("checked", "true"); //작성자 실명 표기
			}
			
			if (replyPrmsYn == 'Y')
			{
				$("#replyPrmsYn").attr("checked", "true");
			}
			
			if (agrmOppYn == 'Y')
			{
				$("#agrmOppYN").attr("checked", "true");
			}
			
			//예약 게시
			if (rsrvYn == 'Y')
			{
				$("#chkReserveDate").attr("checked", "true");
				
				$("#openReserveDate").removeAttr("disabled");
				$("#openReserveHour").removeAttr("disabled");
				$("#openReserveMin").removeAttr("disabled");
				
				$("#openReserveDate").val(notiBgnDttm);
				
			}
			
			$('input:radio[name=notiOpenDiv]:input[value='+notiOpenDiv+']').attr("checked", "true"); //조회자 지정
			if (notiOpenDiv == '020')
			{
				$("#notiOpenKind").show();
			}
			
			//조회자 지정
			for (var i=0; i < tmpUserList.length; i++)
			{
				if (tmpUserList[i].userDiv == 'DPT')
				{
					fnOpenDept(tmpUserList[i]);
				}
				else if (tmpUserList[i].userDiv == 'EMP')
				{
					fnOpenEmp(tmpUserList[i]);
				}
			}
			if (notiKind == '020')  //이미지
			{
				$('#div_image_view').show();
				for (var i=0; i < tmpApndList.length; i++)
				{
					var obj = tmpApndList[i];				
					if (obj.apndFileTp == '020')
					{
						loadTmpImageList2(obj, (i+1));
					}
				}		
				write_apnd_kind = '020';
			}
			else if (notiKind == '030')  //동영상
			{
				write_apnd_kind = '030';
			}
			else if (notiKind == '040')  //설문
			{
				$("#question_cnt").val(tmpSurveyList.length);
				
				$('#div_research_view').show();
				
				
				var start_idx = 0;
				var end_idx = 0;
				for(var i=0; i < tmpSurveyList.length; i++)
				{
					
					var json = tmpSurveyList[i];
					fnAddTmpViewForm(i);				
					$('#research-'+i).find('textarea').val(json.surveyConts);
					
					$('input:radio[name=radio_name-'+i+']:input[value='+json.surveyTp+']').attr("checked", "true"); 
					
					var exmpCnt = 0;
					for(var k=0; k < tmpSurveyExmplList.length; k++)
					{
					  var tmp =  tmpSurveyExmplList[k];
					  if (json.surveyNo == tmp.surveyNo) exmpCnt++; 
					}
				
					$('#txt_view'+i).val(exmpCnt);
					
					end_idx += exmpCnt; 
					if (json.surveyTp == '010')  //텍스트
					{
						var idx = 1;
						for (var j=start_idx; j < end_idx; j++)
						{
							var obj = tmpSurveyExmplList[j];
							$('#li_view-'+i).append(
									'<ol id="ol_view-'+i+'_'+idx+'">'	
									+'<li class="fo_11px">보기'+idx+'<input type="text" class="inp_half ma_lef25" title="항목 보기'+idx+'" value="'+obj.exmplConts+'"></li>'
									+'</ol>'
							);
						}
						idx++;
					}
					else  //이미지
					{
						var idx = 1;
						for (var j=start_idx; j < end_idx; j++)						
						{
							$('#li_view-'+i).append(
									   '<ol id="ol_view-'+i+'_'+idx+'">'		
								   +'<li class="fo_11px">보기'+idx+''        						   
								   +'	<span class="inp_file2">'        			               
					               +'		<input id="txt_survey-'+i+'_'+idx+'" type="text" title="파일을 넣으세요" readOnly >'
					               +'		<a href="#" class="btn_file">'
					               +'			<form id="view_form-'+i+'_'+idx+'" enctype="multipart/form-data" method="post">'
					               +'				<input type="file" class="file2" size="1" title="찾기" id="viewFile-'+i+'_'+idx+'" name="viewFile-'+i+'_'+idx+'">'
					               +'			</form>'
					               +'		</a>'        			               
					               +'	</span>'        			               
					               +'</li>'
					               +'</ol>'
							);
							
							
							$("#viewFile-"+i+'_'+idx).bind("change",function() {
								$(this).parent().parent().prev().val($(this).val());
								    var formfile = $(this).parent();
								    var idx_id = formfile.attr("id");
								    idx_id = idx_id.substring(idx_id.indexOf("-")+1, idx_id.length);
									$(formfile).ajaxSubmit({
									url : WEB_HOME+"/board230/bbsFileUpload.do",
									action: $("#dummy"),
									type : 'POST',
									data : $(formfile).serialize(),
									success : function(data){
										//var json = $.parseJSON(data);
										fnResearchAddImg2($.parseJSON(data), idx_id);//$(formfile).attr("id"));
									},error : function(){
										alert("전송 실패 했습니다.");
									},
									clearForm: false,
									resetForm: false
								});
							}); 
							
							fnResearchTmpAddImg(tmpSurveyExmplList[j], i+'_'+idx);
							idx++;
						}

					}
					
					start_idx = end_idx;
				}
				
				write_apnd_kind = '040';
			}
			
			
			for (var i=0; i < tmpApndList.length; i++)  //첨부파일
			{
				var obj = tmpApndList[i];
				
				if (obj.apndFileTp == '050')
				{
					$("#innoApDiv dl").append('<dd style="border: 0 !important;">'+obj.apndFileOrgn+'<span class="fo_gray">('+obj.apndFileSz+')</span></dd>');
				}
			}
			makeAlreadyUploadFileList();
			
			//첨부목록
			$("#innoApDiv").show();
			
			//공지
			if (anmtYn  == 'Y')
			{
				$("#rt4").attr("checked", "true");
			}
			
			fnFrameReload();
			$("#editor").val(notiConts);
			
		}
		//답글
		if (upNotiId != '' )
		{
			//var reply_list = ${reply_list};
			var json = reply_list[0];
			$("#txt_title").val("[RE] "+json.notiTitleOrgn);  //제목
			$("#editor").val('<br><br>---- 원본메세지 ----<br>'+json.notiConts);
		}

		loadingComplete = true;
		if (notiReadmanAsgnYn == 'N')
		{
			$("#notiReadmanAsgnYn").hide();	
		}
	
				
		$("#txt_title").bind('keydown keyup', function(e){
			if(e.keyCode == 9)
			{
				$('#editor').tinymce().focus();
			}
			
		});
				
		if (nickUseYn == 'Y')
		{
			$("#userNameTR").hide();
		}
		else
		{
			$("#userNameTR").show();
		}
		
		//게시판 설명 보기		
		/* $("#moreBoardExpl").click(function() {
			
			if (boardExplUseYn == 'Y')
			{
				PortalCommon.windowPopup(WEB_HOME+'/board230/bbsBoardExplPopup.do?boardId='+boardId, '게시판설명',750,300);
			}
			
		}); */	

		
		
	
	$("#notiOpenDiv").change(function(){
		var div = $(this).val();
		if(div == '020'){
			//개인지정
			PortalCommon.popupWindowCenter(WEB_HOME+'/organization/organizationChart2.do?type=2&callback=callbackOpenPerson', '개인선택',900,520);
		}else if(div == '030'){
			//부서지정
			PortalCommon.popupWindowCenter(WEB_HOME+'/organization/organizationChart2.do?type=1&callback=callbackOpenDept', '부서선택',900,520);
		}
	});		
			
	//parent.document.getElementById("bbsFrame").height = "700px";
	//parent.document.getElementById("bbsFrame").height = $(document).height()+"px"; //document.body.scrollHeight+400+"px";
	
	
	
});

window.onload = fnLoadPage;