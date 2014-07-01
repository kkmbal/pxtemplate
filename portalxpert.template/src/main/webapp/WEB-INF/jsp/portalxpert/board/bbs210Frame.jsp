<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script type="text/javascript">
	$(window).scroll(function(){
		var url = $("#bbsFrame").attr("src");
		if (url.indexOf("getBbsSnsBoardList") > 0) {
			if  ($(window).scrollTop() == $(document).height() - $(window).height()){
				bbsFrame.fnResizeWindow();
			}
		}
	});
</script>  
<iframe src="${pageContext.request.contextPath}/board210/getBoardInfoList.do?boardId=${boardId}" id="bbsFrame" name="bbsFrame" width="100%" height="700" scrolling="no" frameborder="0"></iframe>

