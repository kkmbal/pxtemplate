<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>

<html lang="ko">
<head>
<title><tiles:insertAttribute name="title" /></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/meta.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>
</head>

<body>
<div id="wrap">
	<tiles:insertAttribute name="header" />
	  <!--container-->
     <div id="container">
    	<div class="content_area clearfix">
    	  <!--content-->
			<div id="content_main">
				<tiles:insertAttribute name="left" />
				<tiles:insertAttribute name="body" />
			</div>    		
    	</div>
    </div>
	  <!--container-->
	<tiles:insertAttribute name="footer" />
</div>	
</body>
</html>