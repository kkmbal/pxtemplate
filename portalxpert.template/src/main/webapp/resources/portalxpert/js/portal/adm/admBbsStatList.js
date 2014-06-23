
	function fn_link_page(pageNo) {
		$("#pageUnit").val(pageUnit);
		$("#pageIndex").val(pageNo);
		$('#listForm').submit();
	}	

	var fnSearchList = function(orderType) {
		pageUnit = $('#list_cnt').val();
		document.listForm.pageIndex.value = '1';
		document.listForm.searchCondition.value = $("#search_gubun").val();
		document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
		document.listForm.orderType.value = orderType;
		document.listForm.isDesc.value = isDesc;

		document.listForm.pageUnit.value = pageUnit;
		document.listForm.submit();

	};	
	
	function fnGetBoardView(boardId){
		location.href = WEB_HOME+"/board100/createAdminBbsView.do?boardId="+boardId;
	}
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	$('#sFromDt').datepicker(
			{      
				showOn: "button",
				//showOn: "both",      
				buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
				buttonImageOnly: true,
				buttonText: "시작일자",
				showButtonPanel: true

			});	
	//$( "#sFromDt" ).datepicker( "setDate", new Date() );
	
	$('#sToDt').datepicker(
			{      
				showOn: "button",
				//showOn: "both",      
				buttonImage: RES_HOME+'/images/img/btn_cal2.png',      
				buttonImageOnly: true,
				buttonText: "종료일자",
				showButtonPanel: true
				
			});	
	//$( "#sToDt" ).datepicker( "setDate", new Date() );

	$('#list_cnt').val(pageUnit);
	
	$('#search').click(function() {//검색
		fnSearchList('');
	});
	$('#keyword').keypress(function(event) {
		if ( event.which == 13 ) {     
			fnSearchList('');   
		}
	});
	$('#list_cnt').change(function() {//조회갯수
		fnSearchList('');
	});
	
	$('#createBbs').click(function() {//글쓰기
		location.href = WEB_HOME+"/board100/createAdminBbsView.do";
	});	
	
});

