<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title></title>
<%
	request.setAttribute("NO_JQUERY_UI", "Y");
%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>



<script type="text/javascript" >

var authCodeList = ${authCodeList};
var authCd = '${authCd}';
var menuList = ${menuList};
var nodeCount = 0;


</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/adm/admSysMenuManage.js"></script>
</head>

<body>
<div class="container">
	<div class="header">
		<div class="h1">메뉴관리</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<strong class="str">메뉴관리</strong>
		</div>
	</div>
	
	<div class="rbox">
		<span class="rbox_top"></span>
		<div class="rboxInner">
			<!-- 셀렉트박스 -->
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">권한</label>
					<select title="" id="authCd">
						<option value="${ROLE_SUPER}">선택</option>
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<button class="btn_style7_2" type="button" id="search">검색</button>
		</div>
	</div>
	
	<div class="btn_board_top">
		<div class="fl">
			<button class="btn_write5" type="button" id="btn_catageory_create">신규 등록</button>
			<button class="btn_style4_4" type="button" id="btn_all_close_ca">모두닫음</button>
			<button class="btn_style4_4" type="button" id="btn_all_open_ca">모두펼침</button>
			<div class="tree" id="boardCategoryListDiv" style="margin-top:10px;width:350px;height:550px !important ; border:1px solid #ddd">
				<ul id="categoryTreeObj" class="ztree"></ul>
			</div>			
		</div>
		<div class="fr mt5">
			<table class="tbl_form" style="margin-top:35px; width:330px !important;" summary="이 표는 메뉴ID, 메뉴명을 입력하는 메뉴정보 표입니다.">
			<caption>사용자</caption>
			<colgroup>
				<col style="width:30%" />
				<col style="*" />
			</colgroup>
			<tbody>
			<tr>
				<th scope="row"><label for="input01">메뉴ID</label></th>
				<td><input type="text" id="menuId" name="menuId" disabled="disabled" class="text" style="width:200px" title="메뉴ID를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">상위메뉴ID</label></th>
				<td><input type="text" id="menuPId" name="menuPId" disabled="disabled" class="text" style="width:200px" title="상위메뉴ID를 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">메뉴명</label></th>
				<td><input type="text" id="menuNm" name="menuNm"  class="text" style="width:200px" title="메뉴명을 입력합니다." /></td>
			</tr>
			<tr>
				<th scope="row"><label for="input01">URL</label></th>
				<td><textarea id="menuUrl" name="menuUrl" cols="28" rows="5" maxlength="100"  title="URL을 입력합니다."></textarea></td>
			</tr>
			</tbody>
			</table>
			<div class="btn_board_sec">
				<div class="fr">
					<button class="btn_style4_2" type="button" id="btn_board_update">수정</button>
					<button class="btn_style4_2" type="button" id="btn_catageory_delete">삭제</button>
					<button class="btn_style3_2" type="button" id="saveMenu">저장</button>
				</div>			
			</div>
		</div>		
	</div>
</div>

</body>
</html>

