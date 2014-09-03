$(function() {
	$("#btnLogOut").click(function() {
		parent.location.href = WEB_HOME+"/logout.do";
	});
	
	$("#btnAdmin").click(function() {
		//$(this).attr("href", WEB_HOME+"/adm/sys/admFrame.do?url=adm/stat/getAdmBbsStatList.do");
		parent.document.getElementById("menufrm").src = WEB_HOME+"/adm/leftTree.do";
		parent.document.getElementById("contentfrm").src = WEB_HOME+"/adm/stat/getAdmBbsStatList.do";
	});	
	
	$("#main").click(function() {
		$(this).attr("href", WEB_HOME+"/main/mainFrame.do");		
	});	
	$("#board").click(function() {
		//$(this).attr("href", WEB_HOME+"/board100/boardFrame.do?boardId=BBS000001");
		parent.document.getElementById("menufrm").src = WEB_HOME+"/board/leftTree.do";
		parent.document.getElementById("contentfrm").src = WEB_HOME+"/board100/boardFrame.do?boardId=BBS000001";
	});	
	
	var zNodes = $.parseJSON(menuConts);
	var topMenus = PortalCommon.getSiblingZMenuByPid(zNodes, "0"); //최상단 메뉴
	for(var i=0;i<topMenus.length;i++){
		if(i == 6) return; // 상단 메뉴최대 갯수
		if(topMenus[i].name != '관리자'){
			var page = topMenus[i].page;
			if(page.match(/^\//g)) page = page.substring(1);
			$("#topMenu").append('<li><a href="'+WEB_HOME+'/'+page+'?menuId='+topMenus[i].menuId+'">'+topMenus[i].name+'</a></li>');
		}
	}
	
	/*
	PortalCommon.getJson({
		url : WEB_HOME+"/adm/sys/getAuthMenu.do?format=json",
		data : 'authCd='+topMenuAuthCd,
		success : function(data) {
			if (data.jsonResult.success === true) {
				var zNodes = $.parseJSON(data.menuList);
				var topMenus = PortalCommon.getSiblingZMenuByPid(zNodes, "0"); //최상단 메뉴
				for(var i=0;i<topMenus.length;i++){
					if(i == 6) return; // 상단 메뉴최대 갯수
					if(topMenus[i].name != '관리자'){
						var page = topMenus[i].page;
						if(page.match(/^\//g)) page = page.substring(1);
						$("#topMenu").append('<li><a href="'+WEB_HOME+'/'+page+'?menuId='+topMenus[i].menuId+'">'+topMenus[i].name+'</a></li>');
					}
				}
				
			};
		}
	});
	*/
	
});