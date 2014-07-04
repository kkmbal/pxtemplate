var left_menu_setting = {
		edit: {
				enable: false,
				showRemoveBtn: false,
				showRenameBtn: false
		},	
		view: {
			dblClickExpand: false,
			showLine : true,
			showTitle : false,
			selectedMulti:true
		},
		data: {
			simpleData: { 
				enable: true
			}
		},
		check: {
			enable: false
		},
		callback: {
			onClick: zTreeOnClick 
		}
		
	};
	
	function zTreeOnClick(event, treeId, treeNode) {
		if(treeId == 'menuTreeObj'){
			if(treeNode.page != ""){				   
				doPage(treeId, treeNode.page);
			}
		}
	};	
	
	var doPage = function(treeId, page){

		if(treeId == 'menuTreeObj'){
			if(page.match(/^\//g)) page = page.substring(1);
			parent.document.getElementById("bbsFrame").src = WEB_HOME+"/"+page;
		}
		
	};
	
	
$(function(){
	
	$("body").remove('menuTreeObj');
	$("body").append("<div id='menuTreeObj' style='display:none'></div>");
	
	PortalCommon.getJson({
		url : WEB_HOME+"/adm/sys/getAuthMenu.do?format=json",
		data : 'authCd='+authCd,
		success : function(data) {
			if (data.jsonResult.success === true) {
				var zNodes = $.parseJSON(data.menuList);
				var treeObj = $.fn.zTree.init($("#menuTreeObj"), left_menu_setting, PortalCommon.getChildZMenuById(zNodes, menuId));
				treeObj.expandAll(true);					
			};
		}
	});		
	
	
});