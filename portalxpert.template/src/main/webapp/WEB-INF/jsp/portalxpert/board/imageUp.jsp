<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<style type="text/css"> 
<!-- 
/*popup*/
.pop_wrap {position:absolute; border:1px solid #666; position:relative; background-color:white;}
.pop_wrap .pop_header {height:21px; padding:7px 0 0 15px; color:#fff; font-weight:bold; background:#598aa7 url(../images/img/bg_phead.png) repeat-x 0 0; border-bottom:1px solid #598aa7;}
.pop_wrap .pop_content {position:relative; padding:15px 15px 0 15px; margin-bottom:54px;}
.pop_wrap .pop_content .p_left {width:49%; margin-right:6px; min-height:100%; float:left;}
.pop_wrap .pop_content .p_left.scroll, .pop_wrap .pop_content .p_right.scroll {width:86%; height:135px; padding:1px; border:1px solid #d9d9d9; overflow-y:scroll;}
.pop_wrap .pop_content .tree {height:341px; overflow-y:scroll;}
.pop_wrap .pop_content .p_right {width:49%; float:right;}
.pop_wrap .pop_content .search_area2, .gr_sch .search_area2 {padding-top:10px; padding-bottom:9px; margin-bottom:15px;}
.pop_wrap .pop_content .tbl_scroll {height:310px; border-bottom:1px solid #d9d9d9;}
.pop_wrap .pop_content .last_bg {padding-right:25px;}
.pop_wrap .pop_content .tbl_list2.grp thead th {text-align:center;}
.pop_wrap .pop_content .tbl_list2.grp thead th:first-child {padding-left:2px;}
.pop_wrap .pop_content .tbl_list2.grp tbody td {height:20px; padding:5px 0 0 10px; text-align:left;   }
.pop_wrap .pop_content .tbl_list2.grp tbody .te_center {padding-right:0; padding-left:0;}
.pop_wrap .pop_content .tbl_list2.grp tbody td:first-child {padding:7px 0 0 1px !important; height:18px;}
.pop_wrap .pop_footer {width:100%; position:absolute; bottom:0; left:0; margin-bottom:10px;}
.pop_post .fl_left h2, .pop_post .fl_right h2 {background:url(../images/img/bl_title.png) no-repeat 0 -495px; padding-left:12px; font-weight:bold; margin-bottom:5px;}
.pop_post .scroll li a, .pop_post .scroll li a {display:block; height:19px; padding-top:3px; color:#000; padding-left:10px;}
.pop_post .scroll li a:focus, .pop_post .scroll li a:focus {background-color:#f0f0f0; color:#de2436;}

.pop_wrap .ele_list {padding:0 10px; margin:7px 0 6px 0;}
.pop_wrap .ele_list li {border-bottom:1px solid #eee; padding:2px 0 ;}
.pop_wrap .ele_list li:first-child {border-top:1px solid #eee;}
.pop_wrap .grid_set dd {display:block;}



.file_input_textbox {float:left; width:200px; border:1px solid #cccccc; font-family:'dotum'; resize:none; line-height:14px;} 
.file_input_div {position:relative; width:100px; left:1px;  height:23px; overflow: hidden; float:left;} 
.file_input_button {width:100px; position:absolute;top:0px; background-color:#f8f8f8; color:#555555; border:1px solid #cccccc;text-align:center;font-weight:bold;padding:5px 10px 0 0;} 
.file_input_button2 {width:50px; background-color:#f8f8f8; color:#555555; border:1px solid #cccccc;text-align:center;font-weight:bold;padding:5px 10px 0 0;}  
.file_input_hidden {font-size:45px; position:absolute; right:0px; top:0px; opacity:0; filter:alpha(opacity=0); -ms-filter:"alpha(opacity=0)"; -khtml-opacity:0; -moz-opacity:0;} 
--> 
</style>
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

<div class="pop_wrap" style="width:455px;height:160px"><!-- 팝업사이즈 width:800px; height:500px; -->
	<div class="pop_header">이미지 업로드</div>
	<div class="pop_content">
	<!-- popup 본문 -->
	 	<div class="">
			<form  name="detailForm" action="<%=request.getContextPath()%>/portalxpert/board/innoUpload.do" method="post" enctype="multipart/form-data">
				<input type="text" id="fileName" class="file_input_textbox" readonly="readonly"> 
				<div class="file_input_div"> 
				<input type="button" value="찾아보기" class="file_input_button" /> 
				<input type="file" name="file" class="file_input_hidden" onchange="javascript: document.getElementById('fileName').value = this.value" />
				</div>
				<div style="margin-left:10px;float:left"> 
				
				</div> 
			</form>	 	
		</div>
		<!-- popup 본문 -->
	</div>
	<div class="pop_footer">
		<!--btn_area-->         
		<div class="btn_area ma_top10">
			<div class="fl_cen">
				<a href="#" class="btn_basic2">
<!-- 					<input type="button" value="추가" class="file_input_button2" width="100px;" onclick="detailForm.submit();"/> -->
					<span class="fo_bold btn_text" onclick="goAppend();">추가 </span>
				</a>
			</div>
			<div class="fl_cen">
				<a href="#" class="btn_basic2 bw70">
					<span class="fo_bold btn_text" onclick="window.close();">닫기 </span>
				</a>
			</div>
		 </div>
		 <!--//btn_area-->  
	</div>	
</div>

</body>
</html>