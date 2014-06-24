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
 

	<div class="container">
	<br/>
	<div class="header">
		<h1>${boardName}</h1>
		<div class="loc">
			<span><a href="#"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a></span>
			<span><a href="#">커뮤니티</a></span>
			<span><strong>${boardName}</strong></span>
		</div>
	</div>
	<!-- 검색 -->
	<div class="rbox">
		<span class="rbox_top"></span>
		<div class="rboxInner">
			<!-- 셀렉트박스 -->
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden"></label>
					<select title="" id="searchCondition" name="searchCondition">
						<option value="0" ${searchCondition == 0 ? 'selected' : ''}>제목
						<option value="1" ${searchCondition == 1 ? 'selected' : ''}>게시자
						<option value="2" ${searchCondition == 2 ? 'selected' : ''}>본문
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" name="searchKeyword" id="searchKeyword" class="text ml5mr10" style="width:450px" /> 
			<a href="#" class="btn_set bt_style7" onclick="fnFormBbsImgBoardNotiList('1')"><span>검색</span></a>
		</div>
	</div>
	<br/>
	<!-- 테이블상단 버튼영역 -->
	<div class="btn_board_top">
		<div class="fl">
			<c:if test="${btnViewYn == 'Y'}">
			<a href="#" class="btn_write"><span>글쓰기</span></a> 
			</c:if>
		</div>
	</div>
	<!-- //테이블상단 버튼영역 -->

	<!-- 게시판은 테이블 -->
	<c:import url="./boardNotiList.jsp" />
	<br>

	<!-- 테이블상단 버튼영역 -->
	<div class="btn_board_top">
		<div class="fr">
			<span class="selectN" style="width:80px">
				<span>
					<select title="" name="pageUnit" id="pageUnit" OnChange="fnFormBbsImgBoardNotiList('1')" title="게시물수 보기">
						<option value="6" ${pageUnit == 6 ? 'selected' : ''}>6개보기</option>
						<option value="12" ${pageUnit == 12 ? 'selected' : ''}>12개보기</option>
						<option value="24" ${pageUnit == 24 ? 'selected' : ''}>24개보기</option>
					</select>
				</span>
			</span>
		</div>
	</div>
	<!-- //테이블상단 버튼영역 -->



	<div class="bigbox">
		<c:choose>
			<c:when test="${paginationInfo.totalRecordCount > 0}">
				<c:forEach var="result" items="${notiList}" varStatus="i">
				
					<div class="smallbox">
			
						<div class="imgbox">
						<img src="${imgUrl}${result.apndFileName}" width="200" height="120" />
						</div>
						<a href="javascript:fnNotiContsDetail('${result.notiId}')" title="${result.notiTitle}">${result.notiTitle}</a>
<!-- 						<textarea rows="2" class="texttitle"> -->
<!-- 			게시물의 제목을 표기합니다. -->
<!-- 			제목은 볼드체로2줄로 표기... -->
<!-- 						</textarea> -->
						<div class="textdetail">
							<div class="fl">${result.updrName}</div>
							<div class="fr" style="color:#1688d9;font-weight:normal">[의견 ${result.opnCnt}]</div>
						</div>
						<div class="textdetail">
							<div class="fl">${result.updDttm}</div>
							<div class="fr">${result.notiReadCnt}</div>
						</div>
			
					</div><!-- end of smallbox	-->

				</c:forEach>
				</c:when>
				<c:otherwise>
					 검색된 데이터가 없습니다. 
				</c:otherwise>
			</c:choose>


	</div> <!-- end of bigbox -->


<div class="paging">
<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnFormBbsImgBoardNotiList" />
</div>


</div> <!-- end of container -->




</form:form>

</body>
</html>


<%--

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

--%>