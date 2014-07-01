<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
var boardId = '${boardId}';
var boardKind = '${boardKind}';
var notiId = '${notiId}';
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board120View.js"></script>
</head>

<body>
<div class="container">

	<div class="header">
		<h1 id="notiTitle"></h1>
		<div class="loc">
			<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
			<span><a href="#">제도안내</a></span>
		</div>
	</div><!-- end of header -->
	
	<div id="notiConts">
	${notiConts}
	</div>

	<div class="attachbox">
		<span class="tit" style="vertical-align:top;">첨부파일</span>
		<span class="ico_fileAttch2">
			<dl id="notiFileDl">
			</dl>
		</span>
	</div>
	
	<div class="titlebox fl">
		<div class="fl"  style="width:660px;">
			<div class="innerbox tit">작성자</div>
			<div class="innerbox" id="userName"></div>
			<div class="innerbox" id="deptName"></div>
			<div class="innerbox" id="mailTo"></div>
		</div>
		<div class="fl">
			<div class="innerbox tit">등록일</div>
			<div class="innerbox" id="regDttm"></div>
		</div>
	</div>	
</div>	

<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>

</body>
</html>			 