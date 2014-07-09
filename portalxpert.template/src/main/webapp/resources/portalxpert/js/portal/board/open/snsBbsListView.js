
	var fnAutoSetHeight = function()
	{
		parent.document.getElementById("bbsFrame").height = ($("#my_list").height() + 45 )+"px";
	};
	
	
	var fnResizeWindow = function()
	{ 
			$("#ajax_indicator").css({
		   		 'z-index' :'999',
		   		 'position':'absolute',
		   		 'top': $(".my_list").height()-$("#ajax_indicator").height()-25 +"px"
			});

			if (moreData == 'Y')
			{
				if (searchData =='N')
				{
					searchData = 'Y';
					$("#ajax_indicator").show();
					fnAddTmlnSearch();
					
					fnAutoSetHeight();
				}
				//$("#ajax_indicator").fadeOut(1000);//('display','none');				
			}
		   
		
	};
	
	
	//글 조회
	var fnAddTmlnSearch = function(){
		var jsonObject = {
				 'boardId' : boardId,
				 'notiId' : '',
				 'sortSeq' : lastSortSeq
		};

		PortalCommon.getJson({
			url: WEB_HOME+"/board220/open/getNotiTmlnInfo.do?format=json",
		    data: {'data' : JSON.stringify(jsonObject)},
			success :function(data){					
				if(data.jsonResult.success ===true){
					$("#ajax_indicator").fadeOut(1000);
					lastSortSeq = data.lastSortSeq;
					var notiList = $.parseJSON(data.notiList);
					if (notiList.length == 0)
					{
						moreData = 'N';
						//return;
					}else{					
					
						for (var i=0; i < notiList.length; i++)
						{
							fnNotiList(notiList[i]);
						}	
					}
					
					var opnList = $.parseJSON(data.opnList);						
					for (var i=0; i < opnList.length; i++)
					{
						fnOpnList(opnList[i]);
					}
					var apndList = $.parseJSON(data.apndList);						
					for (var i=0; i < apndList.length; i++)
					{
						fnApndList(apndList[i]);
					}
					
					searchData = "N";
				}
			}
			
	 });
		 
	};
	
	
	
	
	
	var fnNotiList = function(json)
	{
		var faceImg = json.faceImg;
		if (faceImg == '' || faceImg == null){
			faceImg = RES_HOME+'/images/img/img_me.jpg';
		}
	
		
		var defaultImg = RES_HOME+'/images/img/img_me.jpg';
		
		
 
			$("#div_sns_read").append(
					'<div class="sns_read">'
					+'<div class="sns_readp" id="sns_readp-'+json.notiId+'">'
					+'<div id="sns_menu-'+json.notiId+'" >'
					+'	<dl>'
					+'		<dt>'
					+'			<a href="#" class="fo_bold fo_12px">'+json.regrName+'</a><span>'+json.regDttm+'</span>'
					+'		</dt>'
					+'		<dd>'
					//+'			<span class="img_me">'
					//+'				<img width="48" height="48" src="'+faceImg+'" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'">'
					//+'			</span>'
					+'       <div id="sns_tmln_conts-'+json.notiId+'" >'+json.notiConts+'</div>'
					+'		</dd>'
					+'	</dl>'
					+'<ul id="sns_imgs-'+json.notiId+'" class="sns_imgs"></ul>' //이미지 영역
					+'<div id="board_etc-'+json.notiId+'" class="board_etc" style="display:none;">'
					+'	<dl id="sns_files-'+json.notiId+'">'
					+'		<dt style="display:none;" class="fo_bold">첨부파일</dt>'
					+'	</dl>'
					+'</div>'
					+'<div class="reply">'
					+'<div id="replay-'+json.notiId+'"></div>'				
					+'	<div class="reply_post">'
					+'		<textarea cols="5" rows="3" id="replay_post-'+json.notiId+'" style="ime-mode:active"></textarea>'
					+'     <a class="btn_reup" title="의견등록" onclick="javascript:fnReplyWrite(\''+json.notiId+'\')"></a> '
					//+'		<span class="img_me"><img width="48" height="48" src="'+myImg+'" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'"></span>'
					+'	</div>'
					+'</div>'
					+'</div>'					
				    +'<a class="ico_srcls on" style="cursor:pointer;" id="srcls-'+json.notiId+'" onclick="javascript:fnOnOffTmln(\''+json.notiId+'\')" ></a>'
					+'</div>'				
					+'</div>'
			);
 
		
		if (userId == json.userId || isAdmin == 'Y')
		{
			$("#sns_readp-"+json.notiId).append(
				'<a class="ico_sdel" title="삭제" style="cursor:pointer;" id="sdel-'+json.notiId+'" onclick="javascript:fnDelTmln(\''+json.notiId+'\')" ></a>'
				+'<a class="ico_smod" title="수정" style="cursor:pointer;" id="smod-'+json.notiId+'" onclick="javascript:fnModTmln(\''+json.notiId+'\')" ></a>'	
			);
		}
				
	};
	
	
	//의견 리스트
	var fnOpnList = function(json)
	{
		var faceImg = json.faceImg;
		if (faceImg == '' || faceImg == null){
			faceImg = RES_HOME+'/images/img/img_me.jpg';
		}
		
 
		
		var defaultImg = RES_HOME+'/images/img/img_me.jpg';
		

	    	$("#replay-"+json.notiId).append(
					'<ul id="replay_ul-'+json.notiOpnSeq+'">'
					+'	<li id="replay_li-'+json.notiOpnSeq+'" class="clearfix">'
					+'		<dl>'
					+'			<dt><a href="#" class="fo_bold fo_12px">'+json.userName+'</a><span>'+json.updDttm+'</span></dt>'
					//+'			<dd><span class="img_me"><img src="'+faceImg+'" width="48" height="48" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'"></span><div id="replay_conts-'+json.notiOpnSeq+'" >'+json.opnConts+'</div></dd>'
					+'			<dd><div id="replay_conts-'+json.notiOpnSeq+'" >'+json.opnConts+'</div></dd>'
					+'		</dl>'
					//+'		<a style="cursor:pointer;" onclick="javascript:fnDelOpn(\''+json.notiOpnSeq+'\')" class="ico_sredel" title="댓글삭제"></a>'
					//+'		<a style="cursor:pointer;" onclick="javascript:fnOpnUpdate(\''+json.notiOpnSeq+'\')" class="ico_sremod" title="댓글수정"></a>'
					+'	</li>'				
					+'</ul>'	
			);

		
		if (userId == json.userId || isAdmin == 'Y')
		{
			$("#replay_li-"+json.notiOpnSeq).append(
			 	'<a style="cursor:pointer;" onclick="javascript:fnDelOpn(\''+json.notiOpnSeq+'\')" class="ico_sredel" title="의견삭제"></a>'
			 	+'<a style="cursor:pointer;" onclick="javascript:fnOpnUpdate(\''+json.notiOpnSeq+'\')" class="ico_sremod" title="의견수정"></a>'
			 );
		}
		
		fnAutoSetHeight();
	};
	
	/*sns 등록된 이미지  - sns 가로,세로 가로정렬*/
	var fnImgEffect2 = function(id)
	{
		$obj = $("#sns_imgs-"+id+" li");		
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
			}
		}
		
		 if (imglength == 1)
		{
			var img = $obj.eq(0).find('img');
			
			var rtSize = PortalCommon.fnImgPreviewResize(img.attr('src'), 430, 300);
			
			img.css( {
		   		 'width': rtSize[0]+"px",
		   		 'height': rtSize[1]+"px",
		   		 'margin-left': "-" + rtSize[0]/2 +"px",
				 'margin-top': "-" + rtSize[1]/2 +"px"
			});
			
		}
		
		
	};
	
	var replaceAll = function(str,str1,str2){
		 
	  return str.split(str1).join(str2);
		 
	};
	
	//이미지, 첨부파일
	var fnApndList = function(json)
	{
		//alert(JSON.stringify(json));
		if (json.apndFileTp == '020')  //이미지
		{

			var apndFile = json.apndFilePath+'/'+json.apndFileName;
			if (apndFile.indexOf("http://") < 0){
				apndFile = WEB_DIR+'/'+apndFile;
			}
			
			$("#sns_imgs-"+json.notiId).append(
					'<li style="border: 0 !important;"><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.fileSeq+'\')" ><img id="apndimg-'+json.notiId+'" width="100" height="100" src="'+apndFile+'" alt="올린이미지"></a></li>'
					+'<div style="display:none;" align="center" id="dialog-'+json.fileSeq+'" title="'+json.apndFileOrgn+'"><img src="'+apndFile+'"></div>'
			);
			
			//$("#sns_imgs-"+json.notiId).find('img').load(function(){
			//	fnImgEffect2(json.notiId);
			//});
			
		}
		else if (json.apndFileTp == '050')  //첨부파일
		{
			$("#sns_files-"+json.notiId).append(
				'<dd id="apnd_files-'+json.notiId+'"><a style="cursor:pointer;" onclick="javascript:fnDownLoadList(\''+json.notiId+'\',\''+json.apndFileOrgn+'\',\''+json.apndFileName+'\',\''+json.apndFileSeq+'\')" class="bl_file2 fo_blue">'+json.apndFileOrgn+'<span class="fo_gray">('+json.apndFileSz+')</span></a></dd>'	
			);
			$("#board_etc-"+json.notiId).attr("style","display:block");
			$("#sns_files-"+json.notiId).children().attr("style","display:block");			
		}
	};
	
	
	
  
    var fnOpnBtnClick = function(id)
    {
    	var notiId = $("#replay_conts-"+id).parents("div").attr("id").replace("replay-","");
/*
		var jsonOpnObject = {
				'sortSeq' : '0',
				'notiOpnSeq' : '0',
				'opnConts' : ''
		};					   
		jsonOpnObject.sortSeq = tmlnSeq;
		jsonOpnObject.notiOpnSeq = id;
		//jsonOpnObject.opnConts = $("#replay_conts-"+id+" textarea").text();
		jsonOpnObject.opnConts = replaceAll($("#replay_conts-"+id).find('textarea').val(),"\n","<br>");
		fnTmlnOpnUpdate(JSON.stringify(jsonOpnObject));
*/
    	
		var jsonNotiOpnObject = {
				'notiId' : '',
				'opnConts' : '', 
				'notiOpnSeq' : ''	
			};
		
		jsonNotiOpnObject.notiId = notiId;
		jsonNotiOpnObject.opnConts = replaceAll($("#replay_conts-"+id).find('textarea').val(),"\n","<br>");
		jsonNotiOpnObject.notiOpnSeq = id;		
		
		fnTmlnOpnUpdate(JSON.stringify(jsonNotiOpnObject));
    };
  
	
	//메뉴 접었다 펴기
	var fnOnOffTmln = function(id)
	{
		$("#sns_menu-"+id).toggle();			
		$("#srcls-"+id).toggleClass("ico_srcls");
		$("#srcls-"+id).toggleClass("ico_srcls on");
	};
	
	
	var getMoveJsonData = function(notiId){
		
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
	
	var fnTmlnDeleteView = function(data)
	{
		$("#sns_readp-"+data.notiId).parent().remove();
	}
	
	
	
	
	
	


	
	var fnAppendMenuRemove = function()
	{
		write_apnd_kind = '010';
		$('#div_img_view').hide();
		$('#div_media_view').hide();
		$('#div_research_view').hide();
		$('#div_file_view').hide();
	}
	
	
	var fnImgEffect4 = function()
	{
		var sns_img = $('[id^="sns_imgs-"]');
		for (var i=0; i < sns_img.length; i++)
		{
			var obj_li = sns_img.eq(i).find('li');			
			var imglength = obj_li.length;
			for (var j=0; j < obj_li.length; j++)
			{
				if (imglength == 1){
					obj_li.eq(j).addClass('one');
				} else if (imglength == 2){
					obj_li.eq(j).addClass('two');
				} else if (imglength >= 3){
					obj_li.eq(j).addClass('three');
				};
				
				
				
				var img = obj_li.eq(j).find('img');
				if (img.width() >= obj_li.eq(j).width())
				{
					img.css( {
				   		 'width': '100%'
					});
					var t = Math.abs(img.height()-obj_li.eq(j).height())/2+'px';
					if (img.height() > obj_li.eq(j).height())
					{
						t = 0+'px';
					}					
					img.css( {
						'margin-left': '0px',
						'margin-top': '0px',
				   		 'left': 0+"px",
						 'top': t
					});
					
				}
				if(img.width() < obj_li.eq(j).width())
				{
					img.css({
						'width':'auto',
						'margin-left': "-" + img.width()/2 +"px",
						'margin-top': "-" + img.height()/2 +"px"		
					});	
				}
				
			}
		}
		
	}
	
	//클릭한 이미지 미리보기
	var fnImgPreview = function(id)
	{
		
		var img_path = $( "#dialog-"+id ).find("img").attr("src");
		
		/* var URL = WEB_HOME+'/person300/person300WriteImagePrevPopup.do?imgPath='+img_path;
		var w = 650;
		var h = 600;
		
		window.showModalDialog(URL, this, 'dialogWidth:'+w+'px;dialogHeight:'+h+'px;status:no;help:no; scroll:yes'); */
		
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
	


	
	//다운로드
	var fnDownLoadList = function(notiId, orgname, filename, fileseq)
	{
		 var jsonObject = {
			'apndFileOrgn' : encodeURI(orgname,"UTF-8"),
			'apndFileName' : filename,
			'apndFileSeq' :  fileseq,
			'notiId' : notiId,
			'boardId' : boardId
		};
		 var url =WEB_HOME+"/board100/open/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");	
		 
		 document.dummy.location.href = url; 
	};
	
	
	

	//글 목록 setting
	var fnSetNotiList = function()
	{

		 
		 //게시물
		 for (var i=0; i < notiList.length; i++)
		 { 
			 fnNotiList(notiList[i]);
		 }
		 //의견
		 for (var i=0; i < opnList.length; i++)
		 {
			 fnOpnList(opnList[i]);
		 }
		 //첨부
		 for (var i=0; i < apndList.length; i++)
		 {
			 fnApndList(apndList[i]);
		 }
		 
	};
	
	//저장 후 컴포넌트 초기화
	var initComponent = function()
	{
		fnAppendMenuRemove();
		fnImgListRemoveAll(); //첨부 리스트 이미지 삭제
		fnApndFileListRemoveAll(); //첨부 파일 삭제
		 
		$('[id^="apnd-"]').remove();			
		$("#div_sns_read").empty();
	
		
		$("#id_sns_write").val('글을 작성해주세요');
		
	};	
	
		
	
	
	
	
	var fnChangeTextValue = function()
	{
		$result = replaceAll($("#id_sns_write").val(),"\n","<br>");
		return $result;
	};	
	
	
	

	
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
	
	
	
		
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		
		
		$(window).load(function () {
			parent.document.getElementById("bbsFrame").height = "700px";
			fnSetNotiList();

			
			//$(".ico_grp").hide();
		});
		
		
		

		
		
		
		
		//이미지,동영상,설문,파일 클릭시
		$("#btn_img, #btn_file").click(function(){
			var btnId = $(this).attr("id");
			

			
			if (btnId == 'btn_img' && write_apnd_kind == '020')
			{
				return;
			}else if (btnId == 'btn_media' && write_apnd_kind == '030')
			{
				return;
			}else if (btnId == 'btn_research' && write_apnd_kind == '040')
			{
				return;
			}else if (btnId == 'btn_file' && write_apnd_kind == '050')
			{
				return;
			}
			
			$('#div_img_view').hide();
			$('#div_media_view').hide();
			$('#div_research_view').hide();
			$('#div_file_view').hide();
			
			$("#close_date").datepicker("destroy");
			$("#close_date").removeClass("calendarclass");
		    $("#close_date").removeClass("hasDatepicker");
 		    $("#close_date").unbind();
			
			switch(btnId){
			/* 이미지 */
			case "btn_img":
				var options = {}; 
				write_apnd_kind = '020';
				$('#div_img_view').show("blind",options,100,'');
				break;
			
			/* 파일*/	
			 case "btn_file":
				 write_apnd_kind = '050';
				$("#div_file_view").show("blind",options,100,'');
				break;	
			};			
		});
		
		
	
		
		
		$(document).resize(function(){
			fnAutoSetHeight();
		});
		
		//새로고침
		$('#pageRefresh').click(function(){
			//location.reload();
			$("#ajax_indicator").css({
			   		 'z-index' :'999',
			   		 'position':'absolute',
			   		 'top': 200 +"px"
				});
			$("#ajax_indicator").show();

			if (searchData == 'N')
			{
				searchData = "Y";
				
				initComponent();			
				lastSortSeq = 999999999;
				moreData = 'Y';			
				fnAddTmlnSearch();
				fnAutoSetHeight();
				
			}
		});
		
	});
	
