<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
var boardId = '${boardId}';
var boardKind = '${boardKind}';
var notiId = '${notiId}';
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board120View.js"></script>
</head>

<body>
<div class="container">
	<div class="header">
		<div class="h1" id="notiTitle"></div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">제도안내</a>
		</div>
	</div>

	<table class="tbl_view mt10" summary="기관, 담당자, 연락처에 대한 정보제공">
	<caption>게시판 글보기</caption>
	<colgroup>
		<col style="width:11%" />
		<col style="width:22%" />
		<col style="width:11%" />
		<col style="width:22%" />
		<col style="width:11%" />
		<col style="width:23%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="row">기관</th>
		<td><span id="deptName"></span></td>
		<th scope="row">담당자</th>
		<td><span id="userName"></span></td>
		<th scope="row">연락처</th>
		<td><span id="mailTo"></span></td>
	</tr>
	</thead>
	<tbody>
	<tr>
		<td colspan="6">
			<div class="intxt">
				${notiConts}
			</div>
		</td>
	</tr>
	<tr>
		<td colspan="6">
		<div class="rbox02 mt0">
			<span class="top"></span>
			<div class="mid">
				<div class="inquiry_top">
					<dl id="notiFileDl">
					</dl>
				</div>
			</div>
			<span class="btm"></span>
		</div>
		</td>
	</tr>
	</tbody>
	</table>
	
</div>
	

<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>

</body>
</html>		

 