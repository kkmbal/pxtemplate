<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<script type="text/javascript">	
$(function() {
	$("#btnLogOut").click(function() {
		parent.location.href = "${pageContext.request.contextPath}/logout.do";
	});
	
	$("#btnAdmin").click(function() {
		$(this).attr("href", "${WEB_HOME}/adm/sys/admFrame.do?url=adm/stat/getAdmBbsStatList.do");
	});	
});
</script>


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
			<ul class="clearfix">
        		<li><a>복지포인트관리</a></li>
        		<li><a>보험관리</a></li>
        		<li><a>복지제도안내</a></li>
        		<li><a>제휴복지서비스</a></li>
        		<li><a>커뮤니티</a></li>
        		<li><a>생활정보</a></li>
            </ul>
		</div>
    </div>
    <!--//header-->
    