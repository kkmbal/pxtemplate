<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html lang="ko">
<head>
<title></title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript" >
	
	var contentsPx = 300;
	
	var curPage = '${pageIndex}';
	var boardId = '${boardId}';
	var _regDttmFrom = '${regDttmFrom}';
	var _regDttmTo = '${regDttmTo}';
	var favoYn = '${favoYn}';
	var listSize = '${listSize}';
	var boardName = '${boardName}';
	var host = '${host}';
	
	var eamAdminYn = '${eamAdminYn}';
	var userId = '${userId}';
	
	
	var jsonRootArray = [];
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board212VideoList.js"></script>
</head>

<body>

<form:form commandName="boardSearchVO" name="listForm" method="post">
<form:hidden path="pageIndex" />
<input type='hidden' name='boardId'>  
 
  <div>
	<select id="searchCondition" name="searchCondition">
		<option value="0" ${searchCondition == 0 ? 'selected' : ''}>제목
		<option value="1" ${searchCondition == 1 ? 'selected' : ''}>게시자
		<option value="2" ${searchCondition == 2 ? 'selected' : ''}>본문
	</select>
	<input type="text" value="${searchKeyword}" name="searchKeyword" id="searchKeyword" >
	<input type="button" value="검색" onclick="fnFormBbsImgBoardNotiList('1')">
  </div>

	<input type="button" value="글쓰기" class="btn_write">

	<c:import url="./boardNotiList.jsp" />

  <div>
	<table>
	<tr>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	</table>
  </div>

  <div>
	<select name="pageUnit" id="pageUnit" OnChange="fnFormBbsImgBoardNotiList('1')" >
		<option value="6" ${pageUnit == 6 ? 'selected' : ''}>6
		<option value="12" ${pageUnit == 12 ? 'selected' : ''}>12
		<option value="24" ${pageUnit == 24 ? 'selected' : ''}>24
	</select>
  </div>



  <div>
	  <table>
		<c:choose>
			<c:when test="${paginationInfo.totalRecordCount > 0}">
		
				<c:forEach var="result" items="${notiList}" varStatus="i">
				  <tr>
					<td>
						<img src="${imgUrl}${result.apndFileName}" width="125" height="125">
						<a href="javascript:fnNotiContsDetail('${result.notiId}')" title="${result.notiTitle}">${result.notiTitle}</a> 
						[의견:${result.opnCnt}] 
						${result.updrName} 
						${result.notiReadCnt} 
						${result.updDttm}
					</td>
				  </tr>
				</c:forEach>
				</c:when>
				<c:otherwise>
					 검색된 데이터가 없습니다. 
				</c:otherwise>
			</c:choose>
		</table>
  </div>

	<div id="paging" class="paginate2">
		<ui:pagination paginationInfo = "${paginationInfo}" type="image" jsFunction="fnFormBbsImgBoardNotiList" />


	</div>
</form:form>

</body>
</html>