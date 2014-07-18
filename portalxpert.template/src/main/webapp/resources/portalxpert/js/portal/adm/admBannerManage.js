
	//클릭한 이미지 미리보기
	var fnImgPreview = function()
	{
		var img_path = $( "#apndImg-dialog" ).find("img").attr("src");

		var rtSize = PortalCommon.fnImgPreviewResize(img_path, 250, 150);
		
		$('[id="dialog"]').remove();
		$('.pop_wrap').prepend(
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
	
	if(bnrId == ''){
		$( "#expoBgnDttm" ).datepicker( "setDate", new Date() );
	 	$( "#expoEndDttm" ).datepicker( "setDate", new Date() );	
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
	
	$('#btnSave').click(function() {//등록
		if($("#bnrTitle").val() == ""){
			alert("배너이름을 입력하세요.");
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
		if($("#bnrId").val() == ""){
			if($("#fileName").val() == ""){
				alert("배너를 등록하세요.");
				return;
			}
		}
		
		if ($("#expoBgnDttm_v").val() > $("#expoEndDttm_v").val()) {
			alert("종료일자는 시작일자보다 작을 수 없습니다. \n날짜를 다시 선택하여 주십시오.");
			return false;
		}		
		
		$("#expoBgnDttm").val($("#expoBgnDttm_v").val().replace(/-/g,''));
		$("#expoEndDttm").val($("#expoEndDttm_v").val().replace(/-/g,''));
		
		if (!confirm('등록 하시겠습니까?')) {
			return;
		}
		
		var writeObject = {
			"bnrId" : bnrId,
			"bnrTitle" : $("#bnrTitle").val(),
			"expoBgnDttm" : $("#expoBgnDttm").val(),
			"expoEndDttm" : $("#expoEndDttm").val(),
			"parRowPos" : $("#parRowPos").val(),
			"rowPos" : $("#rowPos").val(),
			"delYn" : 'N',
			"appendFileList" : []
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
			insertAdmBanner(writeObject);
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
			url : WEB_HOME+"/adm/banner/bbsFileUpload.do",
			type : 'POST',
			data :  $("#apndFileform").serialize(),
			action: $("#dummy"),
			//dataType : "script",
			success : function(data){
				makeApndFileList($.parseJSON(data));
				//저장
				insertAdmBanner(writeObject);
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
					  'bnrId' : ''
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
	
	function insertAdmBanner(obj){
		obj.appendFileList = jsonAppendFileList;
		
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/banner/insertAdmBanner.do?format=json",
			data: { data : JSON.stringify(obj) },
			success : function(data) {
				if (data.jsonResult.success === true) {
					opener.location.reload();
					self.close(); 
				}
			}
		});
		
	}
	
	$("#btnClose").click(function(){
		window.close();	
	 });	
	
});

