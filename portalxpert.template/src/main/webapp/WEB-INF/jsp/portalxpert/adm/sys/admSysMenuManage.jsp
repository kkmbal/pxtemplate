<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%
	request.setAttribute("tree", "tree");
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
<div id="ttree"></div>
<div class="container">	
	<div class="header">
		<h1>메뉴관리</h1>
		<div class="loc">
			<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
			<span><a href="#">관리자</a></span>
			<span><strong>메뉴관리</strong></span>
		</div>
	</div>

<div class="rbox">
	<span class="rbox_top"></span>
	<div class="rboxInner">
		<ul>
			<li>
				<label for="pname">권한</label> 
				<span class="selectN" style="width:100px">
					<span>
						<select title="" id="authCd">
							<option value="${ROLE_SUPER}">[ 메뉴관리 ]</option>
						</select>
					</span>
				</span>
				<a href="#" class="btn_set bt_style7"><span id="search">조회</span></a>
			</li>
		</ul>
	</div>
</div>
<br/>

    <div class="tree_list">
    	<div class="p_left">
		    <div class="btn_area">
				<div class="fl_left">
					<a href="#" class="btn_set bt_style2">
						<span id="btn_catageory_create">생성</span>
					</a>
					<a href="#" class="btn_set bt_style2">
						<span id="btn_catageory_delete">삭제</span>
					</a>
				</div>
				<div class="fl_right">
					<a href="#" class="btn_set bt_style3">
						<span id="saveMenu">저장</span>
					</a>
				</div>
			</div>	
		    <div class="btn_area">
				<div class="fl_left">
					<div class="lnb_clop"><a href="#" id="btn_all_close_ca"><span class="ico_allcl" ></span>모두닫음</a> | <a href="#" id="btn_all_open_ca"><span class="ico_allop"></span>모두펼침</a></div>
				</div>
			</div>	
			<div class="tree" id="boardCategoryListDiv" style="height:550px !important ; border:1px solid #ddd">
				<ul id="categoryTreeObj" class="ztree"></ul>
			</div>
	    </div>
	    <div class="p_right">

			 <div class="te_center te90">
		      
					<table class="tbl_form" summary="제목에 대한 입력테이블입니다.">
					<caption>제목</caption>
					<colgroup>
						<col style="width:30%" />
						<col style="*" />
					</colgroup>
					<tbody>
					<tr>
						<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">메뉴ID</label></th>
						<td><input type="text" class="text" style="width:200px" title="아이디" id="menuId" name="menuId" disabled="disabled" /></td>
					</tr>
					<tr>
						<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">상위메뉴ID</label></th>
						<td><input type="text" class="text" style="width:200px" title="아이디" id="menuPId" name="menuPId" disabled="disabled" /></td>
					</tr>
					<tr>
						<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">메뉴명</label></th>
						<td><input type="text" class="text" style="width:200px" title="아이디" id="menuNm" name="menuNm" value="" /></td>
					</tr>
					<tr>
						<th scope="row"><img src="${RES_HOME}/images/ico_essential.png" alt="필수입력" /> <label for="input02">URL</label></th>
						<td><textarea id="menuUrl" name="menuUrl" cols="32" rows="5" maxlength="100"  title="URL"></textarea></td>
					</tr>
					</tbody>
					</table>		      
		      
	    	</div>
	    	<br>
	    	<div class="btn_area">
	    		<div class="fl_right">
	    			<a id="btn_board_update" class="btn_set bt_style2">
	    				<span>수정</span>
	    			</a>
	    		</div>
	    	</div>	    	
	</div>
</div>
</div>
</body>