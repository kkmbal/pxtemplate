<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">

</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardView.js"></script>
</head>

<body>

	<div id="notiConts">
	${notiConts}
	</div>

	<div>
		<dl id="notiFileDl">
			<dt><img src="${RES_HOME}/images/img/txt_file.png" alt="첨부파일"></dt>
		</dl>
	</div>
	
</body>
</html>			 