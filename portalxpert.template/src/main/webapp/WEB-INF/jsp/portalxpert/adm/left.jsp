<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 		<!--//left-->
 		<div id="left" class="lnb_area">
            <ul class="clearfix">
            	<li><a id="a" class="dep1_m1">게시판현황</a></li>
            	<li><a id="b" class="dep1_m1">삭제게시물</a></li>
            </ul>
		</div>
 		<!--//left-->
<script type="text/javascript">
$(function(){
	$("#a").click(function(){
		document.getElementById("admFrame").src = "${pageContext.request.contextPath}/adm/stat/getAdmBbsStatList.do";
	});
	$("#b").click(function(){
		document.getElementById("admFrame").src = "${pageContext.request.contextPath}/adm/board/getAdmBoardNotiDelList.do";
	});
});
</script>           
	           