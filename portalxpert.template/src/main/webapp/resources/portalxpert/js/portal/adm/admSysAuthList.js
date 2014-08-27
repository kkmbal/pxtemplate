
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
	
	function fnGetRegView(authCd){
		PortalCommon.popupWindowCenter(WEB_HOME+'/adm/sys/getAdmSysAuthManage.do?authCd='+authCd, '권한',900,310);
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
	
	$('#createAuth').click(function() {//등록
		PortalCommon.popupWindowCenter(WEB_HOME+'/adm/sys/getAdmSysAuthManage.do?authCd=', '사용자',900,310);
	});	
	
	parent.document.getElementById("admFrame").height = "700px";
	parent.document.getElementById("admFrame").height = $(document).height()+"px";
});

