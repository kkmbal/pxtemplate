
	//클릭한 이미지 미리보기
	var fnImgPreview = function()
	{
		var img_path = $( "#apndImg-dialog" ).find("img").attr("src");

		var rtSize = PortalCommon.fnImgPreviewResize(img_path, 250, 150);
		
		$('[id="dialog"]').remove();
		$('.container').prepend(
				'<div id="dialog" align="center">'
				+'<img src="'+img_path+'" width="'+rtSize[0]+'" height="'+rtSize[1]+'" >'
				+'</div>'
		);
		
		
		$( "#dialog" ).dialog
		(
				{      
					height: rtSize[1]+70,      
					width: rtSize[0]+50,
					modal: true
				}
		);
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
	    	if (!contains) $('#OpenDeptCategories').append('<li id="'+json[i].id+'"><a class="ico_del" style="cursor:pointer;" onclick="javascript:fnOpenDeptListRemove(\''+json[i].id+'\')" ></a>'+json[i].name+'</li>');
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
	    	if (!contains) $('#OpenEmpCategories').append('<li id="'+json[i].id+'"><a class="ico_del" style="cursor:pointer;" onclick="javascript:fnOpenPersonListRemove(\''+json[i].id+'\')" ></a>'+json[i].ou+' '+json[i].name+'</li>');
		};
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
    	if (!contains) $('#OpenDeptCategories').append('<li id="'+json.userId+'"><a class="ico_del" style="cursor:pointer;" onclick="javascript:fnOpenDeptListRemove(\''+json.userId+'\')" ></a>'+json.displayName+'</li>');
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
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	//달력 설정
	$('#expoBgnDttm_v').datepicker(
			{      
				showOn: "button",
				//showOn: "both",      
				buttonImage: RES_HOME+'/images/ico_calendar_off.png',      
				buttonImageOnly: true,
				buttonText: "시작일자",
				showButtonPanel: true
				
			});
	
	$('#expoEndDttm_v').datepicker(
			{      
				showOn: "button",
				//showOn: "both",      
				buttonImage: RES_HOME+'/images/ico_calendar_off.png',      
				buttonImageOnly: true,
				buttonText: "종료일자",
				showButtonPanel: true
				
			}); 
	
	if(popId == ''){
		$( "#expoBgnDttm_v" ).datepicker( "setDate", new Date() );
	 	$( "#expoEndDttm_v" ).datepicker( "setDate", new Date() );	
	}else{
		// 수정
		// 공개대상 조회
		var userList = userMapList;
		for (var i=0; i < userList.length; i++){
			if (userList[i].userDiv == 'DPT'){
				fnOpenDept(userList[i]);
			}else if (userList[i].userDiv == 'EMP'){
				fnOpenEmp(userList[i]);
			}
		}		
	}


	
	//배너위치 selectbox
	for(var i=0;i<parRowPosList.length;i++){
		$("#parRowPos").append("<option value='"+parRowPosList[i].cdSpec+"'>"+parRowPosList[i].cdNm+"</option>");
	}

	for(var i=0;i<rowPosList.length;i++){
		$("#rowPos").append("<option value='"+rowPosList[i].cdSpec+"'>"+rowPosList[i].cdNm+"</option>");
	}
	
	//배너위치
	$("#parRowPos").val(parRowPos);
	$("#rowPos").val(rowPos);
	
	//editor 설정
	fnMakeWebEditor(RES_HOME, '#editor');
	
	$('#btnSave').click(function() {//등록
		if($("#popTitle").val() == ""){
			alert("팝업이름을 입력하세요.");
			return;
		}
		if($("#expoBgnDttm_v").val() == ""){
			alert("게시기간을 입력하세요.")
			return;
		}
		if($("#expoEndDttm_v").val() == ""){
			alert("게시기간을 입력하세요.")
			return;
		}
		
		if ($("#expoBgnDttm_v").val() > $("#expoEndDttm_v").val()) {
			alert("종료일자는 시작일자보다 작을 수 없습니다. \n날짜를 다시 선택하여 주십시오.");
			return false;
		}		
		
		$("#expoBgnDttm").val($("#expoBgnDttm_v").val().replace(/-/g,''));
		$("#expoEndDttm").val($("#expoEndDttm_v").val().replace(/-/g,''));
		
		
		var expoYn = "N";
		if ($("#expoYn").is(":checked")){
			expoYn = "Y";
		}
		var popKind = $(':radio[name="popKind"]:checked').val();
		var linkKind = $(':radio[name="linkKind"]:checked').val();
		if(!linkKind){
			alert("링크방식 선택하세요.");
			return;
		}
		if(!popKind){
			alert("팝업종류 선택하세요.");
			return;
		}
		
		var popConts = tinymce.activeEditor.getContent();

		if(popKind == '001' && popConts == ""){
			alet("팝업 내용을 입력하세요.");
			return;
		}
		
		if($("#popId").val() == "" && popKind == '002'){
			if($("#fileName").val() == ""){
				alert("팝업이미지를 등록하세요.");
				return;
			}
		}
		
		if (!confirm('등록 하시겠습니까?')) {
			return;
		}		
		
		var writeObject = {
			"popId" : popId,
			"popTitle" : $("#popTitle").val(),
			"expoBgnDttm" : $("#expoBgnDttm").val(),
			"expoEndDttm" : $("#expoEndDttm").val(),
			"parRowPos" : $("#parRowPos").val(),
			"rowPos" : $("#rowPos").val(),
			"popKind" : popKind,
			"expoYn" : expoYn,
			"linkUrl" : $("#linkUrl").val(),
			"linkKind" : linkKind,
			"popConts" : popConts,
			"delYn" : 'N',
			"appendFileList" : [],
			"popOpenDivDeptList" : [], //부서
			"popOpenDivEmpList" : [] //개인
			
		};
		
		// 공개대상 지정 -----------------------------------
		var notiOpenDiv = $('#notiOpenDiv').val();
		
		// 부서지정
		var $obj = $('#OpenDeptCategories li');	
		for( var i=0; i < $obj.length; i++)
		{
			var jsonObject = {
	    			'id' : $obj.eq(i).attr("id"),
	    			'name' : $obj.eq(i).text(),
	    			'div'  : 'DPT'
	    	};
			writeObject.popOpenDivDeptList[i] = jsonObject;
		}
		
		// 개인지정
		$obj = $('#OpenEmpCategories li');	
		for( var i=0; i < $obj.length; i++)
		{
			var jsonObject = {
	    			'id' : $obj.eq(i).attr("id"),
	    			'name' : $obj.eq(i).text(),
	    			'div'  : 'EMP'
	    	};
			writeObject.popOpenDivEmpList[i] = jsonObject;
		}		
		
		//전체 공개면
		if (notiOpenDiv == '010'){
			writeObject.popOpenDivDeptList = [];
			writeObject.popOpenDivEmpList = [];
			
			var jsonObject = {
					'id' : 'ALL',
					'name' : '',
					'div'  : 'PUB'
			};
			writeObject.popOpenDivDeptList[0] = jsonObject;
		}		
		
		//첨부 파일 upload
		var fileExist = false;
		$("#apndFileform input:file[name^=upFile]").each(function(){
			if($(this).val() !== ""){
				fileExist = true;
			}
			return;
		});
		if(fileExist){
			fnMultiFileUpload(writeObject);
		}else{
			//저장
			insertAdmPop(writeObject);
		}

	});	
	
	//첨부파일 전송
	var fnMultiFileUpload = function(writeObject)
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
			url : WEB_HOME+"/adm/pop/bbsFileUpload.do",
			type : 'POST',
			data :  $("#apndFileform").serialize(),
			action: $("#dummy"),
			//dataType : "script",
			success : function(data){
				makeApndFileList($.parseJSON(data));
				//저장
				insertAdmPop(writeObject);
			},error : function(){
				alert("전송 실패 했습니다.");
			},
			clearForm: true,
			resetForm: true
		});
	}	
	
	//파일업로드 성공한 목록
	var makeApndFileList = function(obj){
		for(var i=0; i < obj.length; i++){
			var json = obj[i];
			var jsonObject = {
					  'popId' : ''
					, 'apndFileSeq' : '0'
					, 'apndFileTp' : '050'
					, 'apndFileSz' : json.saveFileSize
					, 'apndFileOrgn' : json.original
					, 'apndFileName' : json.saveFileName
					, 'apndFilePath' : json.saveDir
					, 'delYn' : 'N'
					, 'regrId' : ''
					, 'regDttm' : ''
					, 'updrId' : ''
					, 'updDttm' : ''
				};
			jsonAppendFileList.push(jsonObject);
		}
	}	
	
	function insertAdmPop(obj){
		obj.appendFileList = jsonAppendFileList;
		
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/pop/insertAdmPop.do?format=json",
			data: { data : JSON.stringify(obj) },
			success : function(data) {
				if (data.jsonResult.success === true) {
					location.href = WEB_HOME+"/adm/pop/getAdmPopList.do";
				}
			}
		});
		
	}
	
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
	
	if ($("#popKind_Y").is(":checked")){
		$('#contsDiv').show();
		$('#apndFileDiv').hide();
	}
	
	if ($("#popKind_N").is(":checked")){
		$('#contsDiv').hide();
		$('#apndFileDiv').show();
	}
	
	$("#popKind_Y").click(function(eventObject) {	
		$('#contsDiv').show();
		$('#apndFileDiv').hide();
	});
	
	$("#popKind_N").click(function(eventObject) {	
		$('#contsDiv').hide();
		$('#apndFileDiv').show();
	});
	
	$("#btnCancel").click(function(){
		location.href = WEB_HOME+"/adm/pop/getAdmPopList.do";	
	});	
	
	$("#btnClose").click(function(){
		window.close();	
	 });	
	
	parent.document.getElementById("admFrame").height = "900px";
});

