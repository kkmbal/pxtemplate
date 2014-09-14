<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>
<script type="text/javascript">
$(document).ready(function () {
	$("#apndFileTr").remove();
	$("#inputAddButton").remove();
	$("#singleTxtButton").remove();
	$(".header").remove();
	$(".btn_board_sec").remove();
	window.print();
});
</script>

<!-- <script type="text/javascript" src="${RES_HOME}/js/portal/board/surveyPrint.js"></script> -->

</head>

<body>

<div class="container">
	<div id="printPage">
		${pageHtml}
	</div>
</div>	
</body>
</html>     
