<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
    var nodeCount = 0;
    var type = '${type}';
	var kind = '${kind}';
	var notiId = '${notiId}';
	var userNotiSeq = '${userNotiSeq}';
	var mode = '${mode}';
	var boardForm = '${param.boardForm}';
	var selectBoardForm = '';
	var superAdmin = '${superAdmin}';
	var admin = '${admin}';
	var selectNodeId = '';
	var myBoardList = '';	
	var categoryList = ${categoryList};
	var jsonMyBoardList = ${myBoardList};
		
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/organization/organizationChartPop2.js"></script>

 <style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color:#fff; text-align: left;color:#666; border:1px solid #7a7a7a; min-width:100px;height:auto;z-index:1000}
	div#rMenu ul li {list-style: none outside none;}
	div#rMenu ul li a {padding:3px 5px 0px 5px; display:block;}
	div#rMenu ul li a:hover {background-color:#eee;} 
</style>
</head>
 
<body>
<form name="cateForm" method="post">

</form>	
<div id="rMenu">
	<ul>
		<li id="m_add" onclick="addTreeNode();"><a href="#">카테고리 추가</a></li>
		<li id="m_del" onclick="removeTreeNode();"><a href="#">카테고리 삭제</a></li>
		<!-- <li id="m_reset" onclick="resetTree();"><a href="#">초기화</a></li> -->
		<li id="m_rename" onclick="renameTreeNode()"><a href="#">이름바꾸기</a></li>
		<li id="m_rename" onclick="fnBoardAdd()"><a href="#">게시판 추가</a></li>
	</ul>	
</div>

<!--pop_wrap-->
<div class="pop_wrap" style="width:400px;"><!-- 팝업사이즈 width:400px; height:500px; -->
	<div class="pop_header">카테고리 관리</div>
	<div class="pop_content">
	<!-- popup 본문 -->
		<div class="tree" style="border:1px solid #eee;">
			<div class="content_wrap">
				<div class="zTreeDemoBackground left ma_top5">
					<ul id="treeObj" class="ztree"></ul>
				</div>	
		</div>
		</div>
	<!-- popup 본문 -->	
	</div>
	<!-- popup 본문 -->
	<div class="pop_footer">
		<!--btn_area-->         
	    <div class="btn_area">
	        <div class="fl_cen">
	            <a id="btnOK" class="btn_basic2 bw70">
				    <span class="fo_bold btn_text">확인</span>
				</a>
				<a id="btnClose" class="btn_basic2 bw70" style="cursor: pointer;">
				    <span class="fo_bold btn_text" style="cursor: pointer;">닫기</span>
				</a>
	        </div>
	     </div>
	     <!--//btn_area-->  
	 </div>  
</div>	
<div id ="div_text">

</div>
<!--//pop_wrap-->
</body>
</html>
</html>