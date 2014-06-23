package portalxpert.common.web;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.FileCopyUtils;

import portalxpert.common.config.PortalxpertConfigUtils;


/**
 * Image file view 처리 Servlet.
 *
 */
public class ImageFileDownloadServlet extends HttpServlet {
	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String dirPath = request.getParameter("dirPath");
		String realFile = request.getParameter("realFile");
		String attachment = request.getParameter("attachment");
		if("temp".equals(dirPath)){
			dirPath = PortalxpertConfigUtils.getString("upload.temp.dir");
		}else if("real".equals(dirPath)){
			dirPath = PortalxpertConfigUtils.getString("upload.real.dir");
		}
		
		if("true".equals(attachment)){
			attachment = "attachment";
		}else{
			attachment = "inline";
		}
		
		if(!StringUtils.isEmpty(realFile)){
		
			String fileName = java.net.URLEncoder.encode(realFile, "UTF-8");
	
			response.reset();
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Transfer-Encoding", "binary;");
			response.setHeader("Pragma", "no-cache;");
			response.setHeader("Expires", "-1;");
			response.setHeader("Content-Disposition",attachment+";filename="+ fileName + ";");
	
			OutputStream out = response.getOutputStream();
			
			try{
				FileCopyUtils.copy(new FileInputStream(dirPath + realFile), out);
			}catch(java.io.IOException e){
				
			}finally{
				if(out != null) try{ out.close(); }catch(Exception e){}
			}
			out.flush();
			
		}
	}
}
