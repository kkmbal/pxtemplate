
// 권한제거
var fnUserAuthListRemove = function(id)
{
   $("#userAuthList #"+id).remove();
};	
	
// 권한추가
var fnUserAuthListAdd = function(){
	
	if($("#authCd").val() == "") return;
	
	var json = {
			id : $("#authCd").val(),
			name : $("#authCd option:selected").text()
	};
	
	var contains = false;
	$obj = $('#userAuthList li');
	for( var j=0; j < $obj.length; j++)
	{
		if ($obj.eq(j).attr("id") == json.id)
		{
			contains = true;
			break;
		}
	}	    	
	if (!contains) $('#userAuthList').append('<li id="'+json.id+'"><a style="cursor:pointer;" onclick="javascript:fnUserAuthListRemove(\''+json.id+'\')" ></a>'+json.name+'</li>');
	//}
};
	
////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	for(var i=0;i<authCodeList.length;i++){
		$("#authCd").append("<option value='"+authCodeList[i].authCd+"'>"+authCodeList[i].authNm+"</option>");
	}

	for(var i=0;i<deptList.length;i++){
		$("#deptCode").append("<option value='"+deptList[i].deptCode+"'>"+deptList[i].deptName+"</option>");
	}
	
	$("#deptCode").val(deptCode);
	
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
		//if($("#authCd").val() == ""){
		//	alert("권한을 선택하세요.");
		//	return;
		//}
		
		if (!confirm('등록 하시겠습니까?')) {
			return;
		}
		
		var jsonObject = {
			'userId' : 	$("#userId").val(),
			'userName' : $("#userName").val(),
			'userPassword' : $("#userPassword").val(),
			'mobile' : $("#mobile").val(),
			'mail' : $("#mail").val(),
			'deptCode' : $("#deptCode").val(),
			'authList' : []
		};
		
		$obj = $('#userAuthList li');	
		for( var i=0; i < $obj.length; i++)
		{
			var json = {
	    			'authCd' : $obj.eq(i).attr("id"),
	    	};
			jsonObject.authList[i] = json;
		}		
		
		PortalCommon.getJson({
			url : WEB_HOME+"/adm/sys/insertAdmUser.do?format=json",
			//data: $("form[name=listForm]").serialize(),
			data: {'data' : JSON.stringify(jsonObject)},
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

