
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
 

		document.listForm.pageUnit.value = pageUnit;
		document.listForm.submit();

	};	
	
	function fnGetBoardView(boardId, notiId){
		location.href = WEB_HOME+"/adm/board/getAdmBoardNotiDelView.do?boardId="+boardId+"&notiId="+notiId;
	}
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	 

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
	
 	
	
});

