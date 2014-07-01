<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 		<!--//left-->
 		<div id="left" class="lnb_area">
            
			<div class="bbs_board">
				<div class="lnb_clop">
					<a href="#" class="ma_rig10" id="btn_all_close_cm"><span class="ico_allcl"></span>모두닫음</a>|
					<a href="#" class="ma_lef10" id="btn_all_open_cm"><span class="ico_allop"></span>모두펼침</a>
				</div>
				<div class="tree">
					<div class="content_wrap">
						<div id="menuListDiv">
							<ul id="menuTreeObj" class="ztree"></ul>
						</div>	
					</div>
				</div>
			</div>            
            
            
		</div>
 		<!--//left-->
 		
<script type="text/javascript">
var setting = {
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
			parent.document.getElementById("admFrame").src = "${pageContext.request.contextPath}/"+page;
		}
		
	};	
	
$(function(){
	var zNodes = [];
	zNodes.push({id:1, pId:999, name:"사용자관리", page:""});
	zNodes.push({id:2, pId:999, name:"포털관리", page:""});
	zNodes.push({id:3, pId:2, name:"메인관리", page:""});
	zNodes.push({id:4, pId:2, name:"게시판관리", page:""});
	zNodes.push({id:5, pId:1, name:"회원관리", page:""});
	zNodes.push({id:6, pId:1, name:"권한관리", page:""});
	zNodes.push({id:7, pId:1, name:"메뉴관리", page:""});
	zNodes.push({id:8, pId:4, name:"게시판현황", page:"/adm/stat/getAdmBbsStatList.do"});
	zNodes.push({id:9, pId:4, name:"삭제게시물", page:"/adm/board/getAdmBoardNotiDelList.do"});
	zNodes.push({id:10, pId:3, name:"레이아웃관리", page:""});
	zNodes.push({id:11, pId:3, name:"팝업관리", page:""});
	zNodes.push({id:12, pId:3, name:"배너관리", page:""});
	
	var treeObj = $.fn.zTree.init($("#menuTreeObj"), setting, zNodes);
	treeObj.expandAll(true);
	
	$("#btn_all_open_cm").click(function() {//모두열림
		//location.reload();
		menuTreeObj.expandAll(true);
	});
	$("#btn_all_close_cm").click(function() {//모두닫힘
		expandNodes(menuTreeObj.getNodes());
	});
	
});
</script>           
	           