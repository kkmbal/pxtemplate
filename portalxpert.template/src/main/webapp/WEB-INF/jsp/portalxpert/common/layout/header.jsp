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
	
	$("#main").click(function() {
		$(this).attr("href", "${WEB_HOME}/main/mainFrame.do");		
	});	
	$("#board").click(function() {
		$(this).attr("href", "${WEB_HOME}/board100/boardFrame.do?boardId=BBS000002");		
	});	
	
	PortalCommon.getJson({
		url : "${pageContext.request.contextPath}/adm/sys/getAuthMenu.do?format=json",
		data : 'authCd=${sessionScope.pxLoginInfo.authCd}',
		success : function(data) {
			if (data.jsonResult.success === true) {
				var zNodes = $.parseJSON(data.menuList);
				var topMenus = PortalCommon.getSiblingZMenuByPid(zNodes, "0");
				for(var i=0;i<topMenus.length;i++){
					if(topMenus[i].name != '관리자'){
						var page = topMenus[i].page;
						if(page.match(/^\//g)) page = page.substring(1);
						$("#topMenu").append('<li><a href="${WEB_HOME}/'+page+'">'+topMenus[i].name+'</a></li>');
					}
				}
				
			};
		}
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
			<ul class="clearfix" id="topMenu">
        		<li><a id="main">HOME</a></li>
        		<li><a id="board">게시판</a></li>
            </ul>
		</div>
    </div>
    <!--//header-->
    