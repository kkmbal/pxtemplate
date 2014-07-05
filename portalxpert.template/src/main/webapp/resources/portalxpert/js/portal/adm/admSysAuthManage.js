
	
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	if(authCd != ''){
		$('#authCd_v').attr('disabled', 'true');
	}
	
	
	$('#btnSave').click(function() {//등록
		if($("#authCd_v").val() == ""){
			alert("권한코드를 입력하세요.");
			return;
		}
		if($("#authCd_v").val() != ""){
			$("#authCd").val($("#authCd_v").val());
		}
		if($("#authNm").val() == ""){
			alert("권한명을 입력하세요.");
			return;
		}
		
		if (!confirm('등록 하시겠습니까?')) {
			return;
		}
		
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/sys/insertAdmAuth.do?format=json",
			data: $("form[name=listForm]").serialize(),
			success : function(data) {
				if (data.jsonResult.success === true) {
					opener.location.reload();
					self.close(); 
				}
			}
		});
	});	
	
	$("#btnClose").click(function(){
		window.close();	
	 });	
	
});

