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
		if( contentsPx > 650 )
		parent.document.getElementById("bbsFrame").height = contentsPx;
		else parent.document.getElementById("bbsFrame").height = 650;
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
		document.listForm.action = WEB_HOME+"/board212/open/getBbsVideoBoardNotiList.do";
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
		document.listForm.action = WEB_HOME+"/board210/open/getBasicBoardView.do?notiId="+notiId+"&pageIndex="+curPage+"&spec=img";
		document.listForm.submit();
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
		
			

	});
	
	window.onload = fnLoadPage;

	