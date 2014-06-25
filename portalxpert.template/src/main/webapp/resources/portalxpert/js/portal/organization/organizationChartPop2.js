	var setting = {
		edit: {
				enable: false
		},	
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: { 
				enable: true
			}
		},
		check: {
			enable: type == 1 ? true : false
		},
		callback: {
			onClick: zTreeOnClick,
			onCheck: zTreeOnCheck,
			beforeCheck: zTreeBeforeCheck
			/* onClick: zTreeOnClick,
			onRightClick: OnRightClick,
			beforeDrop: zTreeBeforeDrop,
			onRename: zTreeOnRename */
		}
	};
	
	
	var fnUserInfo = function(id)
	{
		var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+id;
		PortalCommon.userInfoPop(url);
	};
	
	var setUserListData = function(userList)
	{
		var addData = "";		
		for (var i=0; i < userList.length; i++)
		{
			var json = userList[i];
			
			if (type == 1)
			{
				addData += '<tr><td class="te_center">'+json.displayname+'</td><td class="deptTD" style="display:none" ><span class="te_dot te93">'+json.ou+'</span></td><td>'+json.titlename+'</td><td>'+json.telephonenumber+'</td></tr>';
			}
			else
			{
				addData += '<tr><td class="te_center"><input type="checkbox" id="chkAll" title="선택" value="'+json.resinumber+','+json.displayname+','+json.titlename+','+json.telephonenumber+','+json.ou+','+json.mobile+'"></td>';
				//addData += '<td class="te_center"><a onclick="javascript:fnUserInfo(\''+json.resinumber+'\')" class="te_dot te93">'+json.displayname+'</a></td><td class="deptTD" style="display:none" ><span class="te_dot te93">'+json.ou+'</span></td><td><span class="te_dot te93">'+json.titlename+'</span></td><td>'+json.telephonenumber+'</td></tr>';
				addData += '<td class="te_center">'+json.displayname+'</td><td class="deptTD" style="display:none" ><span class="te_dot te93">'+json.ou+'</span></td><td><span class="te_dot te93">'+json.titlename+'</span></td><td>'+json.telephonenumber+'</td></tr>';
			}
		}
		
		$("#userList").html(addData);
		
		if (view_mode == "1")
		{
			$("#deptTH").hide();			
		}
		else
		{
			$("#deptTH").show();
			$(".deptTD").css("display","block");
		}
		
	};
	
	var getUserList = function(param){
		$("#chkAll").prop('checked', false );
		
		PortalCommon.getJson({
			url: WEB_HOME+"/organization/getUserList.do?format=json",
			data: param,
			success :function(data){
				if(data.jsonResult.success ===true){
					setUserListData(data.userList);
				};
			}
		});
	};

	var parentCnt = 0;
	function findParentNode(pid)
	{
		var zNodes = organizationList;		
		for (var i=0; i < zNodes.length; i++)
	 	{
			var json = zNodes[i];
			if (json.id == pid)
			{
				parentCnt++;
				findParentNode(json.pId);
			}
	 	}
		return parentCnt;
	}
	
	function zTreeBeforeCheck(treeId, treeNode) {
		parentCnt = 0;
		
		if (treeNode.id == '6110002' || treeNode.id == '6110003')
		{
			alert('하위 부서를 선택하세요');
			return false;
		}
		else
		{
			if (!treeNode.checked)
			{
				var rtnCnt = findParentNode(treeNode.pId);
				if (rtnCnt < 2)
				{
					alert('하위 부서를 선택하세요');				
					return false;
				}			
			}
		}
	};
	
	function zTreeOnCheck(event, treeId, treeNode) {
		
		
		
		
		
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		    view_mode = "1";
		    $("#userName").val('');
		    getUserList({'oucode' : treeNode.oucode });
	};
	
	var getDepData = function() {
		var treeObj = $.fn.zTree.getZTreeObj("treeObj");
		var nodes = treeObj.getAllParentChecked();

		var i = 0;
		var length = nodes.length;
		
		//return;
		
		if(length===0){
			alert("부서를 선택해주세요.");
			return;
		}
		var jsonList = [];
		for (i = 0; i < length; i++) {
			var node = nodes[i];
			var jsonObject = {
					id : node.id,
					name : node.name
			};
			jsonList.push(jsonObject);
		}
				
		//PortalCommon.callOpenerFunction(callbackFunction, jsonStr);
		opener.callbackOpenDept(JSON.stringify(jsonList));
		window.close();
	}
	
	var getUserData = function () {
		var data = $("#userList").find('input[type=checkbox]:checked');		
		var length = data.length;		
		if(length===0){
			alert("사용자를 선택해주세요.");
			return;
		}
		
		var jsonList = [];
		for (var i = 0; i < data.length; i++)
		{
			var user = data[i];		
			var temp = $(user).val();
			var strArr = temp.split(",");
			var jsonObject = {
					id : strArr[0],
					name : strArr[1],
					dept : strArr[2],
					titlename : strArr[3],
					ou : strArr[4],
					mobile : strArr[5]
			};
			jsonList.push(jsonObject);
		}

		opener.callbackOpenPerson(JSON.stringify(jsonList));
		
		window.close();
	};
	
	/* 부서 검색 */	
	var getOrganizationList = function(){
		PortalCommon.getJson({
			url: WEB_HOME+"/organization/getOrganizationList.do?format=json",
		    data: {'orgfullname' : $("#orgfullname").val()},
			success :function(data){
				if(data.jsonResult.success ===true){
					$.fn.zTree.init($("#treeObj"), setting, data.organizationList);
				}
			}
		});
	};
	
	var findUserList = function(){
		var name = $("#userName").val(); 
		if($.trim(name)==="" || PortalCommon.getByte(name)<4){
			alert("사용자 이름을 입력해주세요");
			return;
		}
		view_mode = "2";
		getUserList( {'name' : $("#userName").val()});
		
	};

////////////////////////////// onload ///////////////////////////////////////////	
	
$(document).ready(function(){
	
	$.fn.zTree.init($("#treeObj"), setting, zNodes);
	zTree = $.fn.zTree.getZTreeObj("treeObj");
	var zNodes = organizationList;
	var treeObj = $.fn.zTree.init($("#treeObj"), setting, zNodes);
	console.log(zNodes)
	treeObj.expandAll(false);
	
	var node = zTree.getNodeByParam('id', oucode);
	if (node != null)
	{
		zTree.selectNode(node);
		$("#userName").val('');		
		getUserList({'oucode' : node.oucode });
	}
	
	
	$("#btnOK, #btnClose").click(function(){
		var btnId = $(this).attr("id");			
		switch(btnId){
			case "btnOK": //확인
				if(type==1){
					getDepData(); //선택된 부서 리스트 Return
				}else{
					getUserData();// 서택된 사용자 리스트 Return
				}
				
				break;
			case "btnClose": //닫기
				window.close();				
				break;
		};
	 });
	
	/* 부서명 입력 Enter 키 이벤트 */
	$('#orgfullname').bind('keypress', function(e) {
		var code = e.keyCode || e.which;
		if(code === 13){
			getOrganizationList(); /* 부서 검색 */	
			e.preventDefault();
			return false;
		};
	});
	
	/* 사용자명 입력 Enter 키 이벤트 */
	$('#userName').bind('keypress', function(e) {
		var code = e.keyCode || e.which;
		if(code === 13){
			findUserList(); /* 사용자 검색 */
			e.preventDefault();
			return false;
		};
	});
	
	
	$("#btnUserSearch, #btnOrgSearch").click(function(){
		var btnId = $(this).attr("id");
		switch(btnId){
			/* 사용자 검색 */
			case "btnUserSearch":
				findUserList();
				break;
			/* 부서 검색 */	
			case "btnOrgSearch":
				getOrganizationList();
				break;
		};
	});
	
	
	/* 사용자 전체 체크 EVENT */
	$("#chkAll").click(function(){
		 $("#userList").find('input[type=checkbox]').prop('checked', this.checked);
	});

});