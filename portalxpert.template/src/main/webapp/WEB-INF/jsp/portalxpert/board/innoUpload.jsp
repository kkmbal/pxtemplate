<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="portalxpert.common.config.PortalxpertConfigUtils" %>
<%@ page import="portalxpert.common.utils.FileUploadUtil" %>
<%@ page import="portalxpert.common.utils.CommUtil" %>
<%@ page import="net.sf.json.JSONObject" %>
<%@ page import="net.sf.json.JSONArray" %>
<%
	String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
	int maxFileSize = PortalxpertConfigUtils.getInt("upload.file.size");

	JSONArray jsonArr = FileUploadUtil.upload(request, SAVE_DIR, WEB_DIR, CONTEXT_PATH, maxFileSize);

	JSONObject obj = (JSONObject) jsonArr.get(0);
	if(obj != null){
		String saveFileName = (String) obj.get("saveFileName");
		String path = CommUtil.apndFileCopy(saveFileName); // temp -->real 이동
		String fName = request.getContextPath() + WEB_DIR + path + "/" + saveFileName;

		//System.out.println(fName);
%>
  <script>
	  window.opener.tinymce.execCommand('mceInsertContent', false, '<img src="<%=fName%>">');
	  window.close();
  </script>
<%
	}
%>
