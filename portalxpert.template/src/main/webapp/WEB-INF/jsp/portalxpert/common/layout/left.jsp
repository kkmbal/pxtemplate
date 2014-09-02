<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 		<!--//left-->
 		<div id="left" class="lnb_area">
            
			<div class="bbs_board">
				<div class="non_tree">
					<div class="content_wrap">
						<div id="menuListDiv">
							<ul id="menuTreeObj" class="ztree"></ul>
						</div>	
					</div>
				</div>
			</div>            
            
            
		</div>
 		<!--//left-->
 
<script type="text/javascript">
var menuId = '${param.menuId}';
var authCd = '${sessionScope.pxLoginInfo.authCdStr}';
var menuConts = '${sessionScope.pxLoginInfo.menuConts}';
</script>           
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/portalxpert/js/portal/common/left.js"></script>
 		

	           