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

<body>

<div class="pop_wrap">
	<div class="pop_type1">
		<div class="header">
			<h1  style="font-size:25px;font-weight:bold;">이미지 업로드</h1>
		</div>
		<div class="contents">
		    <form id="detailForm" name="detailForm" action="<%=request.getContextPath()%>/board/innoUpload.do" enctype="multipart/form-data" method="post">
			    <input type="text" class="text" id="fileName" style="width:300px" readonly>
				<span class="file_wrap">
					<button class="btn_style1_2" type="button">파일</button>
					<input type="file" name="file" class="file_hidden" onchange="javascript: document.getElementById('fileName').value = this.value" />
				</span>	
			</form>							
			<div class="pop_btn_sec">
				<button class="btn_style3_2" type="button" onclick="goAppend();">저장</button>
				<button class="btn_style4_2" type="button" onclick="window.close();">취소</button>
			</div>
		</div>
		<a href="#" onclick="window.close();return false" class="pop_close"><img src="${RES_HOME}/images/btn_pop_close.png" width="16" height="15" alt="닫기" /></a>
	</div>
</div>

</body>
</html>

