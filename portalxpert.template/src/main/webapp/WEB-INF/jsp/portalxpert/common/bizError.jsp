<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" isErrorPage="true"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title> </title>
</head>
<body>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
  <tr>
    <td>오류발생:</td>
    <td width="100%" height="100%" align="center" valign="middle" style="padding-top:150px;"><table border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td ><span style="font-family:Tahoma; font-weight:bold; color:#000000; line-height:150%; width:440px; height:70px;"><%=request.getAttribute("exception") %></span></td>
	  </tr>
	</table></td>
  </tr>
</table>
</body>
</html>