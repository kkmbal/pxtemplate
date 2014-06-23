
	var fnAutoSetHeight = function()
	{
		parent.document.getElementById("bbsFrame").height = ($("#my_list").height() )+"px";
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
			url: WEB_HOME+"/board220/getNotiTmlnInfo.do?format=json",
		    data: {'data' : JSON.stringify(jsonObject)},
			success :function(data){					
				if(data.jsonResult.success ===true){
					$("#ajax_indicator").fadeOut(1000);
					lastSortSeq = data.lastSortSeq;
					var notiList = $.parseJSON(data.notiList);
					if (notiList.length == 0)
					{
						moreData = 'N';
						return;
					}					
					
					for (var i=0; i < notiList.length; i++)
					{
						fnNotiList(notiList[i]);
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
	
	
	var fnReplyWrite = function(id)
	{
		if ($("#replay_post-"+id).val().trim() == '')
		{
			$("#replay_post-"+id).val('');
			return;
		}
		
		var notiId = $("#replay_post-"+id).parent().prev().attr('id').replace("replay-","");
		
		/* var jsonOpnObject = {
				'tmlnSeq' : '0',
				'tmlnOpnSeq' : '0',
				'upOpnSeq' : '0',
				'userId' : '',
				'userName' : '',
				'userNick' : '',
				'opnConts' : '',
				'delYn' : '',
				'regrId' : '',
				'regrName' : '',
				'regDttm' : '',
				'updrId' : '',
				'updrName' : '',
				'updDttm' : ''
				
		};					   
		jsonOpnObject.tmlnSeq = tmlnSeq;
		jsonOpnObject.tmlnOpnSeq = '0';					
		jsonOpnObject.upOpnSeq = '0',
		jsonOpnObject.userId = '',
		jsonOpnObject.userName = '',
		jsonOpnObject.userNick = '',					
		jsonOpnObject.opnConts = replaceAll($("#replay_post-"+id).val(),"\n","<br>");
		
		jsonOpnObject.delYn = 'N';
		jsonOpnObject.regrId = '';
		jsonOpnObject.regrName = '';					
		jsonOpnObject.regDttm = '';
		jsonOpnObject.updrId = '';
		jsonOpnObject.updrName = '';
		jsonOpnObject.updDttm = '';
		*/
		var jsonNotiOpnObject = {
				'notiId' : '',
				'opnConts' : '', 
				'upOpnSeq' : '0',
				'userNick' : ''
			};
		
		jsonNotiOpnObject.notiId = notiId;	
		jsonNotiOpnObject.opnConts = replaceAll($("#replay_post-"+id).val(),"\n","<br>");
		
	    fnOpnInsert(JSON.stringify(jsonNotiOpnObject));
		
		$("#replay_post-"+id).val('');
	}
	
	
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
					+'			<span class="img_me">'
					+'				<img width="48" height="48" src="'+faceImg+'" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'">'
					+'			</span>'
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
					+'     <a class="btn_reup" title="의견등록" onclick="javascript:fnReplyWrite(\''+json.notiId+'\')">의견등록</a> '
					+'		<span class="img_me"><img width="48" height="48" src="'+myImg+'" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'"></span>'
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
				'<a class="ico_sdel" style="cursor:pointer;" id="sdel-'+json.notiId+'" onclick="javascript:fnDelTmln(\''+json.notiId+'\')" >삭제</a>'
				+'<a class="ico_smod" style="cursor:pointer;" id="smod-'+json.notiId+'" onclick="javascript:fnModTmln(\''+json.notiId+'\')" >수정</a>'	
			);
		}
				
	};
	
	//의견 저장
	var fnOpnInsert = function(data){
		/*
		PortalCommon.getJson({
			url: WEB_HOME+"/person300/insertOpnList.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					fnOpnList(data.opnList);
				}
			}
		});
		*/

		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiOpnForView.do?format=json",
			data: {  'data' : data }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnOpnList(data.opnList);
				}
			}
	 	});		
		
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
					+'			<dd><span class="img_me"><img src="'+faceImg+'" width="48" height="48" alt="닉네임" onerror="javascript:this.src=\''+defaultImg+'\'"></span><div id="replay_conts-'+json.notiOpnSeq+'" >'+json.opnConts+'</div></dd>'
					+'		</dl>'
					//+'		<a style="cursor:pointer;" onclick="javascript:fnDelOpn(\''+json.notiOpnSeq+'\')" class="ico_sredel" title="댓글삭제"></a>'
					//+'		<a style="cursor:pointer;" onclick="javascript:fnOpnUpdate(\''+json.notiOpnSeq+'\')" class="ico_sremod" title="댓글수정"></a>'
					+'	</li>'				
					+'</ul>'	
			);

		
		if (userId == json.userId || isAdmin == 'Y')
		{
			$("#replay_li-"+json.notiOpnSeq).append(
			 	'<a style="cursor:pointer;" onclick="javascript:fnDelOpn(\''+json.notiOpnSeq+'\')" class="ico_sredel" title="의견삭제">의견삭제</a>'
			 	+'<a style="cursor:pointer;" onclick="javascript:fnOpnUpdate(\''+json.notiOpnSeq+'\')" class="ico_sremod" title="의견수정">의견수정</a>'
			 );
		}
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
			
			$("#sns_imgs-"+json.notiId).find('img').load(function(){
				fnImgEffect2(json.notiId);
			});
			
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
	
	//첨부파일 삭제
    var fnApndFileListRemove = function(id)
    {
    	for(var i=0; i < jsonAppendFileList.length; i++)
		{
			var json = jsonAppendFileList[i];
			if (json.apndFileId == id)
			{
				jsonAppendFileList.splice(i,1);
				break;
			}
		}
		$("#"+id).remove();
    };
  //첨부파일 전체 삭제
    var fnApndFileListRemoveAll = function()
    {
    	jsonAppendFileList = [];
    	$("#AppendFileList").empty();
    }
	
  
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
  
	//의견 수정
	var fnOpnUpdate = function(id)
	{
		if ($("#replay_textarea-"+id).text() != '')
		{
			return;
		}
		var conts = $("#replay_conts-"+id).html();
		if (conts.indexOf("<br>") > 0)
		{
			conts = replaceAll($("#replay_conts-"+id).html(),"<br>","\n");
		}
		
		var conts2 = $("#replay_conts-"+id).html().trim();
		
		//if ($("#replay_conts-"+id+" text").length > 0 ) return;
		$("#replay_conts-"+id).empty();
		$("#replay_conts-"+id).append('<textarea id="replay_textarea-'+id+'" cols="5" rows="3"></textarea><a style="cursor:pointer;" onclick="javascript:fnOpnUpdateCancel(\''+id+'\',\''+conts2+'\')" class="mccl">수정취소</a><a class="btn_reup" title="의견등록" onclick="javascript:fnOpnBtnClick(\''+id+'\')"></a>');
		$("#replay_conts-"+id).addClass('te_right text_mody');
		$("#replay_textarea-"+id).val(conts);
		
	};
	
	//의견수정 Ajax
	var fnTmlnOpnUpdate = function(data){
		/*
		PortalCommon.getJson({
			url: WEB_HOME+"/person300/updateOpnList.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					fnTlmnOpnUpdateView(data.opnList);
				}
			}
		});
		*/
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/updateBbsNotiOpnForView.do?format=json",
			data: {  'data' : data }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnTlmnOpnUpdateView(data.opnList);
				}
			}
	 	});
	};
	
	//의견 수정 결과 
	var fnTlmnOpnUpdateView = function(data)
	{
		$("#replay_conts-"+data.tmlnOpnSeq).removeClass('te_right');
		$("#replay_conts-"+data.tmlnOpnSeq).empty();
		$("#replay_conts-"+data.tmlnOpnSeq).append(data.opnConts);
	}
	
	//의견수정 취소
	var fnOpnUpdateCancel = function(id, conts)
	{
		
		var conts2 = replaceAll(conts,"\n","<br>");
		$("#replay_conts-"+id).removeClass('te_right');
		$("#replay_conts-"+id).empty();
		$("#replay_conts-"+id).append(conts2.replace(/>/g, "&gt;").replace(/</g, "&lt;"));
	}
	
	//의견 삭제
	var fnDelOpn = function(id)
	{
		var result = confirm('삭제하시겠습니까?');
		if (!result) return;
		
		/*
		var jsonTmlnOpnObject = {
				   'tmlnSeq' : '0',							
				   'tmlnOpnSeq' : id				   
		   };
		//alert(JSON.stringify(jsonTmlnObject));
		var tmlnSeq = $("#replay_conts-"+id).parents("div").attr("id").replace("replay-","");
		jsonTmlnOpnObject.tmlnSeq = tmlnSeq;
		fnOpnDelete(JSON.stringify(jsonTmlnOpnObject));
		*/
		
		var notiId = $("#replay_conts-"+id).parents("div").attr("id").replace("replay-","");
		var jsonNotiOpnObject = {
				'notiId' : '',
				'notiOpnSeq' : ''	
			};
		
		jsonNotiOpnObject.notiId = notiId;
		jsonNotiOpnObject.notiOpnSeq = id;	
		fnOpnDelete(JSON.stringify(jsonNotiOpnObject));
	}
	
	//의견 삭제 Ajax
	var fnOpnDelete = function(data){
		/*
		PortalCommon.getJson({
			url: WEB_HOME+"/person300/deleteTmlnOpnList.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					fnTmlnOpnDeleteView(data.opnList);
				}
			}
		});
		*/
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/deleteBbsNotiOpnForView.do?format=json",
			data: {  'data' : data }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnTmlnOpnDeleteView(data.opnList);
				}
			}
	 	});		
	};
	//의견 삭제 적용
	var fnTmlnOpnDeleteView = function(data)
	{
		$("#replay_ul-"+data.notiOpnSeq).remove();
	}

    
	//목록 수정
	var fnModTmln = function(id)
	{
		if ($("#sns_tmln_conts-"+id+" textarea").length > 0 ) return;
		var conts = replaceAll($("#sns_tmln_conts-"+id).html(),"<br>","\n");
		
		var conts_br= $("#sns_tmln_conts-"+id).html();
		$("#sns_tmln_conts-"+id).empty();
		$("#sns_tmln_conts-"+id).append(
				'<textarea title="본문수정" cols="5" rows="10" class="snstext">'+conts+'</textarea>'
				+'<a style="cursor:pointer;" onclick="javascript:fnTmlnUpdateInfo(\''+id+'\')" class="mccl">수정</a>'
				+'<a style="cursor:pointer;" onclick="javascript:fnTmlnUpdateCancel(\''+id+'\',\''+conts_br+'\')" class="mccl">수정취소</a>'
		);
		$("#sns_tmln_conts-"+id).addClass('te_right');
	}
	
	var fnTmlnUpdateInfo = function(id)
	{
		var result = confirm('수정하시겠습니까?');
		if (!result) return;
		
		var result = replaceAll($("#sns_tmln_conts-"+id+" textarea").val(),"\n","<br>");		
		var jsonTmlnObject = {
				   'boardId' : boardId,
				   'notiId' : id,
				   'notiConts' : result				   
		   };
		fnTmlnUpdate(JSON.stringify(jsonTmlnObject));

	}
	//목록 수정 Ajax
	var fnTmlnUpdate = function(data){
		PortalCommon.getJson({
			url: WEB_HOME+"/board220/updateBbsNotiInfo.do?format=json",
		    data: {'data' : data},
			success :function(data){
				if(data.jsonResult.success ===true){
					//location.reload();
					fnTlmnUpdateView(data.notiList);
				}
			}
		});
	};
	
	//수정글
	var fnTlmnUpdateView = function(data)
	{
		//alert(data.tmlnSeq);
		$("#sns_tmln_conts-"+data.notiId).removeClass('te_right');
		$("#sns_tmln_conts-"+data.notiId).empty();
		$("#sns_tmln_conts-"+data.notiId).append(data.notiConts);
	}
	//수정취소
	var fnTmlnUpdateCancel = function(id, conts)
	{
		$("#sns_tmln_conts-"+id).removeClass('te_right');
		$("#sns_tmln_conts-"+id).empty();		
		$("#sns_tmln_conts-"+id).append(conts.replace(/>/g, "&gt;").replace(/</g, "&lt;"));
		

	}
	//메뉴 접었다 펴기
	var fnOnOffTmln = function(id)
	{
		$("#sns_menu-"+id).toggle();			
		$("#srcls-"+id).toggleClass("ico_srcls");
		$("#srcls-"+id).toggleClass("ico_srcls on");
	};
	//삭제
	var fnDelTmln = function(notiId)
	{
		var result = confirm('삭제하시겠습니까?');
		if (!result) return;
		
		var jsonTmlnObject = {
				   'boardId': boardId,
				   'notiId' : notiId,
				   'delDiv' : 'DEL'
		   };
		//alert(JSON.stringify(jsonTmlnObject));
		fnTmlnDelete(JSON.stringify(jsonTmlnObject), notiId);

	}
	
	//삭제 Ajax
	var fnTmlnDelete = function(data, notiId){
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/insertBbsNotiDelInfo.do?format=json",
			data: {  'data' : data , 'moveData' : getMoveJsonData(notiId)}, 
			success :function(data){
				if(data.jsonResult.success ===true){
					fnTmlnDeleteView(data.notiList);
				}
			}
	 	});		
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
	
	
	
	
	//선택한 이미지 삭제
	var fnImgListRemove = function(id)
	{
		for(var i=0; i < jsonAppendImgList.length; i++)
		{
			var json = jsonAppendImgList[i];
			if (json.apndFileId == id)
			{
				jsonAppendImgList.splice(i,1);
				break;
			}
		}
		$("#"+id).remove();
	};
	//이미지 모두 삭제
	var fnImgListRemoveAll = function()
	{
		jsonAppendImgList =  [];		
		$(".sns_img").remove();
	}
	
	// 선택한 이미지 삭제
	var fnResearchListRemove = function(id)
	{
		for(var i=0; i < jsonAppendFileList.length; i++)
		{
			var json = jsonAppendFileList[i];
			if (json.apndFileId == id)
			{
				jsonAppendFileList.splice(i,1);
				break;
			}
		}
		//첨부파일 경로 삭제 후 이미지 객체 삭제
		$("#"+id).prev().children().children().val("");
		$("#"+id).remove();
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
	


	
	var fnDelFileList = function(id)
	{
		$("#"+id).remove();
	}
	

	
	//첨부파일 추가
	var fnAddFileList = function()
	{
		if ($("#apndFileform li").length >= apndUploadMax)
		{
			alert('최대 첨부 추가 개수는 '+apndUploadMax+'개입니다.\n 더이상 추가 할 수 없습니다.' );
			return;
		}
		
		var id = Math.floor(Math.random() * (9999998))+2;
		$("#apndFileform").append(
				'<li id="apnd-'+id+'" class="ma_bot5">' 
				+'<span class="inp_file2" >'  
				+'<input type="text" title="파일을 넣으세요" style="height:17px;">'
				+'<a href="#" class="btn_file">'
				+'<input type="file" class="file2" size="1" title="찾기" id="file-'+id+'" name="upFile-'+id+'">'								                            	
				+'<a style="cursor:pointer;" onclick="javascript:fnDelFileList(\'apnd-'+id+'\')" class="btn_grid2"><span class="btn_text">삭제</span></a>'
				+'</a>'
				+'</span>'
				+'</li>'
		);
		
		$("#file-"+id).bind("change",function(e) {
			$(this).parent().prev().val($(this).val());
		});
		
	}
	
	//다운로드
	var fnDownLoadList = function(notiId, orgname, filename, fileseq)
	{
		 var jsonObject = {
			'apndFileOrgn' : encodeURI(orgname,"UTF-8"),
			'apndFileName' : filename,
			'apndFileSeq' :  fileseq,
			'notiId' : notiId
		};
		 var url =WEB_HOME+"/board100/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");	
		 
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
	
	//글 입력
	var fnBbsTimeLineCreate = function()
	{
		var notiTitle = "";
		var notiTitleOrgn = ""; 
		
		var titleBoldYn = "N";
		var titleColorDiv = " ";
		
		
		var notiConts = fnChangeTextValue();

		var linkUrl = "";
		var notiTP = "010";
		
		var moblOpenDiv = "";
		var notiKind = write_apnd_kind;

		var nickUseYn = "N";

		var userNick = "";
		
		var editDiv = "010";
		var rsrvYN = 'N';
		
		var nowdate = new Date();	
		var openReserveDate = '';
		var openReserveHour = "";
		var openReserveMin = "";
		
		if ($("#chkReserveDate").is(":checked")){
			rsrvYN = 'Y';
			openReserveHour = $('#openReserveHour option:selected').val();
			openReserveMin = $('#openReserveMin option:selected').val()+':00';
		}
		
		var notiBgnDttm = "";
		
		var notiEndDttm = "9999-12-31 23:59:59";

		//var notiOpenDiv = $(':radio[name="notiOpenDiv"]:checked').val();
		var notiOpenDiv = $('#notiOpenDiv').val();
		
		var agrmOppYn = 'N';
	    
		var anmtYn = 'N';

		
		var notiOpenDivSpec='';
		if (userDiv == 'ALL'){
			notiOpenDivSpec = '010';
		}else if (userDiv == 'PUB'){
			notiOpenDivSpec = '020';
		}else if (userDiv == 'SGU'){
			notiOpenDivSpec = '030';
		}
		
		var opnPrmsYN = 'N';	

		
		
		var replyPrmsYn = 'N';	
		
		
		var opnMakrRealnameYn = "N";
		
		var notiTagLst = "";
		

		if (upNotiId == '') upNotiId = '*';
		
		
		var cdlnEvntCode = '';
		var cdlnObjrName = '';
		var cdlnDeptFname = '';
		var cdlnDeptName = '';
		var cdlnDeptCode = '';
		
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
		
		fnTmlnInsert(JSON.stringify(jsonWriteObject));
	};	
	
	
	
	
	var fnChangeTextValue = function()
	{
		$result = replaceAll($("#id_sns_write").val(),"\n","<br>");
		return $result;
	};	
	
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
				//makeApndFileList($.parseJSON(JSON.stringify(data)));
				makeApndFileList($.parseJSON(data));
				fnBbsTimeLineCreate();
			},error : function(){
				alert("전송 실패 했습니다.");
			},
			clearForm: true,
			resetForm: true
		});
	}
	

	
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
	
	
	//글 저장
	var fnTmlnInsert = function(data){
	    var mimeValue = null;
	    
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
					initComponent();
					
					lastSortSeq = 999999999;
					moreData = 'Y';
					
					fnAddTmlnSearch();
				}
			}
		});
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
	
	
	
	var loadImageList = function(obj)
	{
		var json = obj[0];
		
		var size = json.saveFileSize/1024/1024;
		
		if (size > imgUploadSize)
		{
			alert('최대 이미지 추가 사이즈는 '+imgUploadSize+'M 입니다.' );
			return;
		}
		
		$('<li class="sns_img" id="'+json.saveFileId+'" name= '+json.original+'><img id="img-'+json.saveFileId+'" src="'+json.webDir+json.saveFileName+'" width="100" height="100" alt="이미지"><a style="cursor:pointer;" class="ico_clo" title="삭제" onclick="javascript:fnImgListRemove(\''+json.saveFileId+'\')" ><!--삭제--></a></li>').insertBefore($('.sns_imgadd'));
		var jsonObject = {
				  'notiId' : ''
				, 'apndFileSeq' : '1'
				, 'apndFileTp' : '020'
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

		
		jsonAppendImgList.push(jsonObject);
		
		$("#img-"+json.saveFileId).load(function(){
			fnImgEffect(json.saveFileId);
		});
		
	};	
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		
		
		$(window).load(function () {
			parent.document.getElementById("bbsFrame").height = "700px";
			fnSetNotiList();

			
			//$(".ico_grp").hide();
		});
		
		//첨부파일
		$("#apndFileAdd").change(function(eventObject) {
			//alert($(this).val());
			var fileFullname = $(this).val();
			var fileName = fileFullname.substring(fileFullname.lastIndexOf("\\")+1, fileFullname.length);
			var filePath = fileFullname.substring(0, fileFullname.lastIndexOf("\\")); 
			
			for (var i=0; i < jsonAppendImgList.length; i++)
			{
				var contains = false;
				var json = jsonAppendImgList[i];
				if (json.apndFileName == fileFullname)
				{
					contains = true;
	    			break;
				}
			}
			
	    	var id = 'apnd-'+Math.floor(Math.random() * (9999998))+2;	    	
	    	if (!contains) $('#AppendFileList').append('<li id="'+id+'"><a class="ico_del2" style="cursor:pointer;" onclick="javascript:fnApndFileListRemove(\''+id+'\')" ></a>'+fileName+'</li>');
			
	    	$(this).parent().prev().val("");
	    	
	    	var jsonObject = {
					'apndFileId' : id
					, 'apndFileName' : fileFullname
					, 'apndFilePath' : filePath
			};
	    	
	    	jsonAppendFileList.push(jsonObject);
	    	
		});
		


		//이미지 전송		
		$("#apndFile").change(function(e) {
			if (jsonAppendImgList.length >= imgUploadMax)
			{
				alert('최대 이미지 추가 개수는 '+imgUploadMax+'개입니다.\n 더이상 추가 할 수 없습니다.' );
				return;
			}
			
			
			if(!PortalCommon.imgUploadFileCheck(fileform.upFile.value)){
				alert("추가할 수 없는 파일입니다.");
				return;
			}
			
			$("#fileform").ajaxSubmit({
				url : WEB_HOME+"/board230/bbsFileUpload.do",
				type : 'POST',
				data : $("#fileform").serialize(),
				action: $("#dummy"),
				success : function(data){			
					loadImageList($.parseJSON(data));
				},error : function(){
					alert("전송 실패 했습니다.");
					//PortalCommon.closeProcessMessage($("#show_dialog"));
				},
				clearForm: true,
				resetForm: true
			});			
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
		
		
		//등록
		$("#btn_context_write").click(function(eventObject) {

			if (($("#id_sns_write").val() == '글을 작성해주세요')||($("#id_sns_write").val() == ''))
			{
				alert('내용을 작성하세요');
				return;
			}

			//첨부파일이면 파일을 업로드후 호출한다.
			if (write_apnd_kind == '050')
			{
				fnMultiFileUpload();
			}
			else
			{
				fnBbsTimeLineCreate();
			}
			
						
		});
		
		

		
		
		
		
		$("#id_sns_write").click(function(eventObject) {
			if ($("#id_sns_write").val() == '글을 작성해주세요')
			{
				$("#id_sns_write").val(''); 
			}
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
	
