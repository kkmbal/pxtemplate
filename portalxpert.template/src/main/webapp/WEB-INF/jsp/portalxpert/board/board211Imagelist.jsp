<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
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
	var searchCondition = '${searchCondition}';
	
	var eamAdminYn = '${eamAdminYn}';
	var userId = '${userId}';
	
	var jsonRootArray = [];
	
</script>
<script type="text/javascript" src="${RES_HOME}/js/portal/board/board211Imagelist.js"></script>
</head>

<body>
<form:form commandName="boardSearchVO" name="listForm" method="post">
<form:hidden path="pageIndex" />
<input type='hidden' name='boardId'> 

<div class="container">
	<div class="header">
		<div class="h1">${boardName}</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">${boardName}</strong>
		</div>
	</div>
	
	<div class="rbox">
		<div class="rbox_top"></div>
		<div class="rboxInner">
			<!-- 셀렉트박스 -->
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">제목</label>
					<select title="" id="searchCondition" name="searchCondition">
						<option value="0" ${searchCondition == 0 ? 'selected' : ''}>제목
						<option value="1" ${searchCondition == 1 ? 'selected' : ''}>게시자
						<option value="2" ${searchCondition == 2 ? 'selected' : ''}>본문
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" name="searchKeyword" id="searchKeyword" class="text ml5mr10" style="width:450px" title="제목입력"> 
			<button class="btn_style7_2" type="button" onclick="fnFormBbsImgBoardNotiList('1')">검색</button>
		</div>
	</div>

	<div class="imgGallery">
		<!-- 공지 -->
		<c:import url="./boardNotiList.jsp" />
		
		<table summary="" class="tbl_list">
		<caption></caption>
		<colgroup>
		<col style="width:7%" />
		<col style="width:71%" />
		<col style="width:11%" />
		<col style="width:11%" />
		</colgroup>
		<tr class="imgView">
			<td colspan="4">
			<div class="img_sec">
				<!-- selectbox -->
				<span class="selectN" style="width:100px">
					<span>
						<label for="selectN_id1" class="hidden">갤러리 개수선택</label>
						<select title="" name="pageUnit" id="pageUnit" OnChange="fnFormBbsImgBoardNotiList('1')" title="게시물수 보기">
							<option value="6" ${pageUnit == 6 ? 'selected' : ''}>6개보기</option>
							<option value="12" ${pageUnit == 12 ? 'selected' : ''}>12개보기</option>
							<option value="24" ${pageUnit == 24 ? 'selected' : ''}>24개보기</option>
						</select>						
					</span>
				</span>
				<!-- //selectbox -->
				<ul class="gal_list">
					<c:choose>
						<c:when test="${paginationInfo.totalRecordCount > 0}">
							<c:forEach var="result" items="${notiList}" varStatus="i">				
								<li>
									<div class="imgFrame02">
										<!-- 링크영역 -->
										<a href="javascript:fnNotiContsDetail('${result.notiId}')" title="${result.notiTitle}">
											<img src="${imgUrl}${result.apndFileName}" alt="" style="width:180px;height:120px" />
											<strong class="img_tit">${result.notiTitle}</strong>
										</a>
										<!-- //링크영역 -->
										<div class="img_desc">
											<span class="fl">
												<span class="col03">${result.updrName}</span><br />${result.updDttm}
											</span>
											<span class="fr">
												<span class="col02">[의견:${result.opnCnt}]</span><br />
												${result.notiReadCnt}
											</span>
										</div>
									</div>
								</li>
						</c:forEach>
						</c:when>
						<c:otherwise>
							 <div style="text-align:center">검색된 데이터가 없습니다.</div> 
						</c:otherwise>
					</c:choose>					
				</ul>
			</div>
			</td>
		</tr>
		</tbody>
		</table>
		
		<div class="paging">
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fnFormBbsImgBoardNotiList" />
		</div>
		
		<c:if test="${btnViewYn == 'Y'}">
		<button type="button" class="btn_write"><strong>글쓰기</strong></button>
		</c:if>
	</div>
	
	
	
	
</div>


</form:form>
</body>
</html>


