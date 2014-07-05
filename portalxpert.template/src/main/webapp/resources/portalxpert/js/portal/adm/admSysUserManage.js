
	
	
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	for(var i=0;i<authCodeList.length;i++){
		$("#authCd").append("<option value='"+authCodeList[i].authCd+"'>"+authCodeList[i].authNm+"</option>");
	}

	for(var i=0;i<deptList.length;i++){
		$("#deptCode").append("<option value='"+deptList[i].deptCode+"'>"+deptList[i].deptName+"</option>");
	}
	
	$("#deptCode").val(deptCode);
	$("#authCd").val(authCd);
	if(userId != ''){
		$('#userId_v').attr('disabled', 'true');
	}
	
	
	$('#btnSave').click(function() {//등록
		if($("#userId_v").val() == ""){
			alert("아이디를 입력하세요.");
			return;
		}
		if($("#userId_v").val() != ""){
			$("#userId").val($("#userId_v").val());
		}
		if($("#userName").val() == ""){
			alert("이름을 입력하세요.");
			return;
		}
		if($("#userPassword").val() == ""){
			alert("비밀번호를 입력하세요.");
			return;
		}
		if($("#userPassword").val() != $("#userPassword2").val()){
			alert("비밀번호를 다시 입력하세요.");
			$("#userPassword2").focus();
			return;
		}
		if($("#deptCode").val() == ""){
			alert("부서를 선택하세요.");
			return;
		}
		if($("#authCd").val() == ""){
			alert("권한을 선택하세요.");
			return;
		}
		
		if (!confirm('등록 하시겠습니까?')) {
			return;
		}
		
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/sys/insertAdmUser.do?format=json",
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

