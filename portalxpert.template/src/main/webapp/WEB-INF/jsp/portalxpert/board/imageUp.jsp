<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	function goAppend(){
		if($("#fileName").val() == ""){
			alert("이미지를 선택하세요.");
			return false;
		}
		if(!PortalCommon.imgUploadFileCheck($("#fileName").val())){
			alert("추가할 수 없는 파일입니다.");
			return false;
		}		
		detailForm.submit();
	}
</script>
</head>

<body style="overflow:hidden">

<div class="pop_wrap" style="width:455px;height:150px;"><!-- 팝업사이즈 width:800px; height:500px; -->
	<div class="pop_header">이미지 업로드</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="">
		    <form id="detailForm" name="detailForm" action="<%=request.getContextPath()%>/board/innoUpload.do" enctype="multipart/form-data" method="post">
		    <input type="text" class="text" id="fileName" readonly style="width:300px">
		    <a href="#" class="btn_set bt_style1 mv_file_a">   
			<input type="file" size="1" name="file" class="mv_file" onchange="javascript: document.getElementById('fileName').value = this.value">
			<span>파일</span></a>
			</form>	 	
		</div>
		<!-- popup 본문 -->
	</div>
	<div class="pop_footer">
		<!-- 버튼영역 -->
		<div style="text-align:center;">
			<div class="rbox_btns">
				<a href="#" class="btn_set bt_style3" onclick="goAppend();" ><span>등록</span></a>
				<a href="#" class="btn_set bt_style2" onclick="window.close();"><span>닫기</span></a>
			</div>
		</div>
		<!-- //버튼영역 -->	
	</div>	
</div>

</body>
</html>