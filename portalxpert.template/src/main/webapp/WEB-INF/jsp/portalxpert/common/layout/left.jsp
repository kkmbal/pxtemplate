<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

 		<!--//left-->
				<div id="a">게시판1</div>
				<div id="b">게시판2</div>
				<div id="c">게시판3</div>
 		<!--//left-->
<script type="text/javascript">
$(function(){
	$("#a").click(function(){
		document.getElementById("bbsFrame").src = "${pageContext.request.contextPath}/board210/getBoardInfoList.do?boardId=BBS000002";
	});
	$("#b").click(function(){
		document.getElementById("bbsFrame").src = "${pageContext.request.contextPath}/board210/getBoardInfoList.do?boardId=BBS000003";
	});
	$("#c").click(function(){
		document.getElementById("bbsFrame").src = "${pageContext.request.contextPath}/board210/getBoardInfoList.do?boardId=BBS000004";
	});
});
</script>           
	           