<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
<script type="text/javascript" src="${RES_HOME}/js/portal/organization/categoryChartPop.js"></script>

 <style type="text/css">
	div#rMenu {position:absolute; visibility:hidden; top:0; background-color:#fff; text-align: left;color:#666; border:1px solid #7a7a7a; min-width:100px;height:auto;z-index:1000}
	div#rMenu ul li {list-style: none outside none;}
	div#rMenu ul li a {padding:3px 5px 0px 5px; display:block;}
	div#rMenu ul li a:hover {background-color:#eee;} 
</style>
</head>
 
<body style="overflow:hidden;">
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

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">게시물 이동</h1>
		</div>
		<div class="contents">
			<p class="ptxt">이동할 게시판을 선택해 주십시오.</p>
			<table class="tbl_form" summary="이 표는 게시물 이동 표입니다.">
			<caption>게시물 이동</caption>
			<colgroup>
				<col style="width:100%" />
			</colgroup>
			<tbody>
			<tr>
				<td>
					<div class="tree" style="border:1px solid #eee;overflow:auto;">
						<div class="content_wrap">
							<div class="zTreeDemoBackground left ma_top5">
								<ul id="treeObj" class="ztree"></ul>
							</div>	
						</div>		
					</div>				
				</td>
			</tr>
			</tbody>
			</table>
			
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" id="btnOK">이동</button>
				<button class="btn_style4_2" type="button" id="btnClose">취소</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>

</body>
</html>

