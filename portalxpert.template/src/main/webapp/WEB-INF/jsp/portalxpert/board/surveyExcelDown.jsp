<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.UUID"%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>설문조사 | 맞춤형복지</title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>
<%
	//저장할 파일명 생성
	String sFileName = UUID.randomUUID().toString().toUpperCase()+".xls";
	sFileName = new String(sFileName.getBytes("KSC5601"), "8859_1");
	
	out.clear();
	out.pageContext.pushBody();
	response.reset(); //이 문장이 없으면 excel 등의 파일에서 한글이 깨지는 문제 발생
	
%>
</body>
</html>	
	 