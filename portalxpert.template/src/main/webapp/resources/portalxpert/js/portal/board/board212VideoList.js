	var fnLoadPage = function()
	{
		fnFrameReload();
	};
	
	//rePaint
	var fnFrameReload = function()
	{
		$(parent.document).scrollTop(0);
		fnImgReSizing();
		contentsPx = contentsPx + ( 140 * parseInt(listSize));
		if(parent.document.getElementById("bbsFrame")){
			if( contentsPx > 650 )
				parent.document.getElementById("bbsFrame").height = contentsPx;
			else parent.document.getElementById("bbsFrame").height = 650;
		}
	};

	var fnRemoveNotiList = function()
	{
		if ($("[name='chk']:checked").length == 0) {
			alert('글을 선택해주세요.');
			return;
		}
		
		if($("[name='chk']:checked").length > 1){
			alert('게시글은 1건씩만 삭제 가능합니다.');
			return;
		}		
		
		var regrId = $("[name='chk']:checked").eq(0).attr("regrId");
		
		if(eamAdminYn != "Y"){
			if(userId != regrId){
				alert('타인이 작성한 글을 삭제할 수 없습니다.');return;
			}
		}		
		
		if (!confirm('삭제하시겠습니까?')) 
		{
			return;
		}
		
		var jsonNotiObject = {
				
			 'removeNotiInfo' : []
		};
		
		$obj = $('#imgBbsListUl li');
		
		var notiInx =0 ;
		
		for( var inx = 0 ; inx < $obj.length;inx++ )
		{
			var chkId = "chk" + inx;
			
			if($('input:checkbox[id="'+ chkId + '"]').is(':checked'))
			{

				var notiJsonObject = 
				{
						'notiId' : $obj.eq(inx).attr("id")
				};
			
				
				jsonNotiObject.removeNotiInfo[notiInx] = notiJsonObject;
				notiInx ++;
				
			}
		}
		jsonRootArray[0] = notiJsonObject;

		
		fnRemoveNotiSave(JSON.stringify(jsonNotiObject));
		
	};
	
	
	var fnRemoveNotiSave = function(data) {
		
		PortalCommon.getJson({
			url : WEB_HOME+"/board211/updateImgNotiInfo.do?format=json",
			data : {
				'data' : data
			},
			success : function(data) {
				if (data.jsonResult.success == true) {
					
					fnFormBbsImgBoardNotiList(curPage);
					
				}
			}
		});
	};
	
	//게시물 이동
	var fnDoNotiMove = function(notiId) {
		var jsonNotiObject = {
			//----게시판속성 설정------
			'boardId' : '', //게시판 ID
			'notiId' : []
		};

		jsonNotiObject.boardId = boardId;
		jsonNotiObject.notiId = notiId;

		PortalCommon.getJson({
			url : WEB_HOME+"/board210/getMyNotiCheckList.do?format=json",
			data : {
				'data' : JSON.stringify(jsonNotiObject)
			},
			success : function(data) {
				if (data.jsonResult.success === true) {
					if (data.cnt < 1) {//모두 나의 글일경우
						//PortalCommon.popupWindowCenter(WEB_HOME+'/organization/myCategoryChartPop.do?kind=1&type=2&admin=0&notiId='+JSON.stringify(notiId),'myBoardPop','400','450');
						PortalCommon.popupWindowCenter(
								WEB_HOME+'/organization/categoryChartPop.do?kind=1&type=2&admin=1&notiId='
										+ JSON.stringify(notiId), 'myBoardPop',
								'400', '450');
					} else {
						alert('타인의 게시물은 이동할수 없습니다.');
					}
				}
			}
		});
	};

	//Frame 
	var fnDoAutoIframeResize = function(){
		
	
		var iFrames = parent.document.getElementById("bbs212Frame");
	
		parent.autoResize(iFrames);
	
	};
	
	function dateFormat(consDate)
	{
		
		var sYear = consDate.substring(0, 4);
		var sMonth = consDate.substring(4, 6);
		var sDate = consDate.substring(6, 8);
		
		var formatDate = sYear + '-' + sMonth + '-' + sDate;
		return formatDate;
		
	}
	
	/* pagination 페이지 링크 function */
	function fnFormBbsImgBoardNotiList(pageNo)
	{


		curPage = pageNo;
		document.listForm.pageIndex.value = pageNo;
		document.listForm.boardId.value = boardId;
		document.listForm.action = WEB_HOME+"/board212/getBbsVideoBoardNotiList.do";
	   	document.listForm.submit();
	};
	
	
	var fnReload = function()
	{
		fnFormBbsImgBoardNotiList(curPage);
		
	};
	
	
	
	var fnContsControl = function(listInx)
	{

		var curClass = $('#imgHref' + listInx).attr('class');
		
		if( curClass == 'ico_srcls on')
		{
			$('#imgHref' + listInx).removeClass('ico_srcls on');
			$('#imgHref' + listInx).addClass('ico_srcls');
			$('#notiConts' + listInx).css('display' , 'none');
			$('#notiConts' + listInx).parent().parent().css('margin-left' , '25px');
			$('#notiConts' + listInx).parent().css('display' , 'none');
			$('#imgDiv' + listInx).css('display' , 'none');

		}
		else
		{
			$('#imgHref' + listInx).removeClass('ico_srcls');
			$('#imgHref' + listInx).addClass('ico_srcls  on');
			$('#notiConts' + listInx).css('display' , 'block');
			$('#notiConts' + listInx).parent().parent().css('margin-left' , '165px');
			$('#notiConts' + listInx).parent().css('display' , 'block');
			$('#imgDiv' + listInx).css('display' , 'block');
			
		}
		
		
	};
	
	
	var allChk = function()
	{
		$obj = $('#imgBbsListUl li');
		
		for( var inx = 0 ; inx < $obj.length;inx++ )
		{
			var chkId = "chk" + inx;
			
			$('input:checkbox[id="'+ chkId + '"]').attr("checked", true);
		}
	};
	
	var fnNotiContsDetail = function(notiId)
	{
		document.listForm.boardId.value =  boardId;
		document.listForm.action = WEB_HOME+"/board210/getBasicBoardView.do?notiId="+notiId+"&pageIndex="+curPage+"&spec=img";
		document.listForm.submit();
	};
	
	
	var fnDoFavorite = function() {

		var jsonBoardInfoObject = {
			//----게시판속성 설정------
			'boardId' : '' //게시판 ID
		};

		jsonBoardInfoObject.boardId = boardId;

		if (favoYn == 'N') {//추가
			if (confirm('즐겨찾기에 추가 하시겠습니까?')) {

				PortalCommon
						.getJson({
							url : WEB_HOME+"/board210/insertBbsFavorite.do?format=json",
							data : {
								'data' : JSON.stringify(jsonBoardInfoObject)
							},
							success : function(data) {
								if (data.jsonResult.success === true) {
									location.reload();									
									parent.fnGetPersonLnbFavoList();
								}
							}
						});
			}

		} else if (favoYn == 'Y') {
			if (confirm('즐겨찾기에 삭제 하시겠습니까?')) {
				PortalCommon.getJson({
					url : WEB_HOME+"/board210/deleteBbsFavorite.do?format=json",
					data : {
						'data' : JSON.stringify(jsonBoardInfoObject)
					},
					success : function(data) {
						if (data.jsonResult.success === true) {
							location.reload();
							parent.fnGetPersonLnbFavoList();
						}
					}
				});
			}
		}
	};
	
	var getCheckNotiIdJsonData = function() {
		/*	
		if ($("[name='chk']:checked").length == 0) {
			alert('글을 선택해주세요.');
			return;
		}
		*/
		var jsonArray = [];
		var idx = 0;
		for ( var i = 0; i < $("#imgBbsListUl li").length; i++) {
			
			var chkId = "chk" + i;
			
			if($('input:checkbox[id="'+ chkId + '"]').is(':checked'))
			{
			
				var jsonObject = {
					'id' : $("#imgBbsListUl li").eq(i).attr("id")
				};
				
				alert($("#imgBbsListUl li").eq(i).attr("id"));
				jsonArray[idx] = jsonObject;
				idx++;
			}
		}
		
		if( idx == 0 )
		{
			alert('글을 선택해주세요.');
			return;
		}
		
		return jsonArray;
	};
	
	var fnImgReSizing = function()
	{
		$obj = $("#imgBbsListUl li");
		
		for(var inx = 0 ; inx < $obj.length ; inx++ )
		{
		
			var divWidth = $('#imgSrcDiv' + inx ).width();
			var imgWidth = $('#imgSrc' + inx).width();

			
			if (imgWidth >= divWidth )
			{
				
				$('#imgSrc' + inx).css( {
					 'width': '100%'
				});
				//width 100 % 설정 후 다시 계산 
				$('#imgSrc' + inx).css( {
					
			   		 'margin-left': "-" + $('#imgSrc' + inx).width() /2 +"px",
					 'margin-top': "-" + $('#imgSrc' + inx).height()/2 +"px"
				});
				
			}
			else if (imgWidth < divWidth)
			{
				$('#imgSrc' + inx).css({
					'width':'auto',
					'margin-left': "-" + imgWidth/2 +"px",
					'margin-top': "-" + $('#imgSrc' + inx).height() /2 +"px"		
					
				});						
			}	
			
		}
		
	};	
	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () { 

		$('.btn_write').click(function() {//글쓰기
			location.href = WEB_HOME+"/board230/board230Write.do?boardId="+ boardId+"&kind=BBS&pageIndex="+1+"&pageUnit="+10;
		});
		
		
		
		$("#searchKeyword").keypress(function(e){
			if (e.keyCode == '13') {
				fnFormBbsImgBoardNotiList('1');
  	    	}
		});
		
		$('#detail_search').click(function() {//상세
			if($('#detailSearch').attr("value") == "Y"){
				$('#detailSearch').attr("value","N");
				$('#detailSearch').hide();
			}else{
				$('#detailSearch').attr("value","Y");
				$('#detailSearch').show();	
			}
		});
		
		if(_regDttmFrom !="" || _regDttmTo !=""){
			$('#detailSearch').show();
		}
		
		
		$('#regDttm_from').datepicker({      
			showOn: "button",
			//showOn: "both",      
			buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
			buttonImageOnly: true,
			buttonText: "시작일자",
			showButtonPanel: true

		});
		
		$('.ui-datepicker').attr('style','display:none;');
		if(_regDttmFrom != ""){
			$( "#regDttm_from" ).datepicker( "setDate", _regDttmFrom );	
			
			$('.ui-datepicker').attr('style','display:none;');
			
		}
		$('#regDttm_to').datepicker({      
			showOn: "button",
			//showOn: "both",      
			buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
			buttonImageOnly: true,
			buttonText: "종료일자",
			showButtonPanel: true
					
		});
		//_regDttmTo = '20131101';
		if(_regDttmTo != ""){
			$( "#regDttm_to" ).datepicker( "setDate", _regDttmTo );	
			
			
		}
		
		if (favoYn == 'N') {
			$("#favoTxt").html("즐겨찾기 추가");
		} else {
			$("#favoTxt").html("즐겨찾기 삭제");
		}
		
		$('#noti_move').click(function() {//이동
			if (!confirm('이동하시겠습니까?')) {
				return;
			}
		
			fnDoNotiMove(getCheckNotiIdJsonData());
		});
		
		$('#alert_close').click(function() {
			$("#alert_copy").hide();
		});
		$('#board_copy').click(
				function(event) {
					$("#alert_copy").show();
					$("#alert_copy").fadeOut(1600, "linear", "");
					/* window.clipboardData.setData(
									'Text',
									 host
									+ WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='
									+ boardId+'&pageIndex=1&pageUnit=6&orderType=default'); */
					
					window.clipboardData.setData(
							'Text',
							"http://"+location.host
							+ WEB_HOME+'/board100/boardFrame.do?boardId='
							+ boardId
							+ '&pageIndex=1&pageUnit=10');
					
				});		

	});
	
	window.onload = fnLoadPage;

	