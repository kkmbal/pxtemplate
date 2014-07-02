
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
	
	function fnGetRegView(userId){
		//PortalCommon.popupWindowCenter(WEB_HOME+'/adm/sys/getAdmSysUserManage.do?userId='+userId, '사용자',400,460);
		PortalCommon.popupWindowCenter(WEB_HOME+'/organization/organizationChart2.do?type=2&callback=callbackOpenPerson', '개인선택',900,520);
	}
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	for(var i=0;i<authCodeList.length;i++){
		$("#authCd").append("<option value='"+authCodeList[i].cd+"'>"+authCodeList[i].cdNm+"</option>");
	}

	for(var i=0;i<deptList.length;i++){
		$("#deptCd").append("<option value='"+deptList[i].deptCode+"'>"+deptList[i].deptName+"</option>");
	}
	
	$("#deptCd").val(deptCd);
	$("#authCd").val(authCd);
	if(userId != ''){
		$('#userId').attr('disabled', 'true');
	}
	
	$('#search').click(function() {//검색
		fnSearchList('');
	});
	$('#keyword').keypress(function(event) {
		if ( event.which == 13 ) {     
			fnSearchList('');   
		}
	});
	
	$('#btnSave').click(function() {//등록
		location.href = WEB_HOME+"/board100/createAdminBbsView.do";
	});	
	
	$("#btnClose").click(function(){
		window.close();	
	 });	
	
});

