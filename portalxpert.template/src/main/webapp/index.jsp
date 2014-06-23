<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html>
	<title>Home</title>

<body>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000003" target="_blank">1.  제도안내목록</a>		(O)<br>
<a href="${pageContext.request.contextPath}/">2.  제도안내등록</a>		(O)<br>
<a href="${pageContext.request.contextPath}/">3.  제도안내보기</a>		(O)<br>
----------<a href="${pageContext.request.contextPath}/">4.  배너관리</a>			(-)<br>
----------<a href="${pageContext.request.contextPath}/">5.  배너관리-신규등록</a>	(-)<br>
----------<a href="${pageContext.request.contextPath}/">6.  팝업관리</a>			(-)<br>
----------<a href="${pageContext.request.contextPath}/">7.  팝업관리-신규등록</a>	(-)<br>
<a href="${pageContext.request.contextPath}/adm/sys/admFrame.do?url=adm/stat/getAdmBbsStatList.do" target="_blank">8.  게시판현황</a>			(O)<br>
<a href="${pageContext.request.contextPath}/">9.  게시판현황보기</a>		(O)<br>
<a href="${pageContext.request.contextPath}/board100/createAdminBbsView.do" target="_blank">10. 게시판생성</a>			(O)<br>
<a href="${pageContext.request.contextPath}/adm/sys/admFrame.do?url=adm/board/getAdmBoardNotiDelList.do" target="_blank">11. 삭제게시물</a>			(O)<br>
<a href="${pageContext.request.contextPath}/">12. 삭제게시물보기</a>		(O)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000007" target="_blank">13. 교육안내목록</a>		(O)<br>
<a href="">14. 교육안내등록</a>		(O)<br>
<a href="">15. 교육안내보기</a>		(O)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000002" target="_blank">16. 게시판목록</a>			(O)<br>
<a href="${pageContext.request.contextPath}/">17. 게시판글등록</a>		(O) --> 조직팝업<br>
<a href="${pageContext.request.contextPath}/">18. 게시판보기</a>			(O)<br>
----------<a href="${pageContext.request.contextPath}/">19. 설문조사목록</a>		(-)<br>
----------<a href="${pageContext.request.contextPath}/">20. 설문조사등록</a>		(-)<br>
----------<a href="${pageContext.request.contextPath}/">21. 설문조사보기</a>		(-)<br>
<a href="${pageContext.request.contextPath}/">22. QnA목록</a>			(-)<br>
<a href="${pageContext.request.contextPath}/">23. QnA글등록</a>			(-)<br>
<a href="${pageContext.request.contextPath}/">24. QnA보기</a>			(-)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000005" target="_blank">25. 이미지게시판목록</a>	(O)<br>
<a href="${pageContext.request.contextPath}/">26. 이미지게시판글등록</a>	(O)<br>
<a href="${pageContext.request.contextPath}/">27. 이미지게시판보기</a>	(O)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000006" target="_blank">28. 동영상게시판목록</a>	(O)<br>
<a href="${pageContext.request.contextPath}/">29. 동영상게시판글등록</a>	(O)<br>
<a href="${pageContext.request.contextPath}/">30. 동영상게시판보기</a>	(O)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS999999" target="_blank">31. 임시저장목록</a>		(O)<br>
----------<a href="${pageContext.request.contextPath}/">32. 쪽지목록</a>			(-)<br>
----------<a href="${pageContext.request.contextPath}/">33. 쪽지보기</a>			(-)<br>
<a href="${pageContext.request.contextPath}/board100/boardFrame.do?boardId=BBS000004" target="_blank"">34. SNS게시판목록</a>	(O)<br>
</body>
</html>
<%
portalxpert.common.vo.UserInfoVO vo = new portalxpert.common.vo.UserInfoVO();
vo.setName("tester");
vo.setId("1");
session.setAttribute("pxLoginInfo", vo);
%>