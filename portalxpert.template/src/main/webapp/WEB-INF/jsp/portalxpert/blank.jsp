<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
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
<iframe src="" frameborder="0"  id="bbsFrame" name="bbsFrame" width="100%" height="700" scrolling="no"></iframe>

