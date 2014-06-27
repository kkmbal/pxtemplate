<!DOCTYPE HTML>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<html lang="ko">
<head>
<title>포털</title> 
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>
	
<!-- 화면 사용 스크립트 -->
<script type="text/javascript" >

var fnSetSession = function()
{

    var userId = $('#loginId').val();
    var userPwd = $('#loginPwd').val();

    if(userId == "" || userId == null )
    {
	    alert("아이디를 입력하세요.");
	    return;
    }
    if(userPwd == "" || userPwd == null )
    {
	    alert("비밀번호를 입력하세요.");
	    return;
    }
    PortalCommon.getJson({
		url: "${pageContext.request.contextPath}/doLogin.do?format=json",
		data: "userId=" + userId + "&passwd=" + userPwd,
		success :function(data){
			if(data.jsonResult.success === true) {	
				top.location.href = "${pageContext.request.contextPath}/main/mainFrame.do";
			} else {
				//실패후 실행 스크립트
				alert("사용자가 존재하지 않습니다.");	
			}			
		}
	});		
	
};




//IE 브라우저 사용 확인
var fn_isIE = function() {
	var isIE = false;
	if(navigator.userAgent.indexOf("MSIE") > -1) {
		isIE = true;
	}
	
	return isIE;
};

//IE버전 확인
var fn_getIEVer = function() {
	var userAgent = navigator.userAgent;
	var re = new RegExp("MSIE ([0-9]{1,}[\.0-9]{0,})");
	var rv = 0;
	
	if (re.exec(userAgent) != null){
		rv = parseFloat(RegExp.$1);
	}
	
	return rv;
};

</script>


</head>

<body class="bg_log1">


<form name="LoginForm">
<div id="wrap">
    <!--container-->
    <div class="intro">
		<div id="content">
			<!-- 로그인 영역 -->
			<div class="log_area"> 
				<h2><!--로그인 타이틀--></h2>
				<ul>
					<li><span class="tx_id"><!-- 아이디 --></span><input id="loginId" type="password" title="아이디 입력" class="text"></li>
					<li><span class="tx_pw"><!-- 패스워드 --></span><input id="loginPwd" type="password" title="패스워드 입력" class="text" onkeyup="if(event.keyCode==13)fnSetSession();"></li>
				</ul>
				<a href="javascript:fnSetSession()" class="btn_login"><!-- 로그인 --></a>
			</div>
			<!--//로그인 영역 -->
			
		</div>
		<!--//content-->
  	</div>
</div>
</form>
</body>
</html>
