<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>

<script type="text/javascript" >
	var eamAdmnF = '${eamAdmnF}';
	var boardId = '${boardId}';
	var commonZNodes = null;
	var myBoardList = '';
	var commonBoardtreeObj = null;
	var myZNodes = null;
	var cmObjHeight = null;
	var myObjHeight = null;
	//스크롤 영역
	var scrollFlag = false;
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/leftTree.js"></script>

<div class="lnb_bor">
	<div class="bbs_board">
		<div class="lnb_clop">
			<a href="#" class="ma_rig10" id="btn_all_close_cm"><span class="ico_allcl"></span>모두닫음</a>|
			<a href="#" class="ma_lef10" id="btn_all_open_cm"><span class="ico_allop"></span>모두펼침</a>
		</div>
		<div class="tree">
			<div class="content_wrap">
				<div id="commonBoardListDiv">
					<ul id="commonBoardtreeObj" class="ztree"></ul>
				</div>	
			</div>
		</div>
	</div>



</div>
