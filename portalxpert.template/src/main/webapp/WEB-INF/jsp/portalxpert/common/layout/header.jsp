<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>

	<!--header-->
	<div id="header">
		<div class="m_setarea">
			<div class="m_set">
				<a id="btnLogOut" class="btn_logout">로그아웃</a>
				<a id="btnAdmin" class="btn_logout" title="관리자버튼">관리자</a>
			</div>
		</div>
		<div class="head_con">
			<h1><a>LOGO</a></h1>
		</div>
		<div class="gnb_area">
			<ul class="clearfix" id="topMenu">
        		<li><a id="main">HOME</a></li>
        		<li><a id="board">게시판</a></li>
            </ul>
		</div>
    </div>
    <!--//header-->
    
<script type="text/javascript">
var topMenuAuthCd = '${sessionScope.pxLoginInfo.authCd}';
</script>           
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/portalxpert/js/portal/common/header.js"></script>
 	    
    