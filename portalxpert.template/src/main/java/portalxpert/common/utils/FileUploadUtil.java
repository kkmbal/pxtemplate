package portalxpert.common.utils;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import portalxpert.common.config.PortalxpertConfigUtils;

/**
 * 웹상에서 업로드 작업을 수행하는 유틸리티
 * 
 * @author crossent
 * 
 */
public class FileUploadUtil {
 

  /** 문자 인코딩 */
  private static final String CHARSET = "utf-8";

  /**
   * 생성자 - 객체 생성 불가
   */
  private FileUploadUtil() {
    // do nothing;
  }

  /**
   * 지정된 파일을 업로드 한다.
   * 
   */
	public static JSONArray upload(HttpServletRequest request, String SAVE_DIR, String WEB_DIR, String CONTEXT_PATH, int maxFileSize)
			throws IOException, Exception {
		
		String filename = "";
 		String encType = CHARSET;//인코딩 타입
 		int maxSize = maxFileSize*1024*1024;//최대 업로드 될 파일크기 1Mb
 		String saveFileId = "";
 		String saveFileName = "";
 		String original = "";	
 		String size = "";
 		
 		MultipartRequest multi = new MultipartRequest(request, SAVE_DIR, maxSize, encType, new DefaultFileRenamePolicy());
 		Enumeration files = multi.getFileNames();
 		
 		JSONArray jsonArr = new JSONArray();
 		
 		while(files.hasMoreElements())
 		{
 			String name =(String)files.nextElement();
 			boolean isOk = true;
 			while(isOk)
 			{
 				saveFileName = CommUtil.makeRandomString();
 			    
 				filename = multi.getFilesystemName(name);
 				
 				if(filename!=null) {
 					int idx = filename.lastIndexOf('.');
 				    if (idx != -1) {
 				        String extension = filename.substring(idx).toLowerCase();
 				        
 				       if(!portalxpert.common.utils.CommUtil.uploadExtensionsCheck(extension, multi.getFile(name))){
				        	throw new Exception("Invalid upload file");
				        }
 				        
 				        saveFileName = saveFileName + extension;
 				    }
 				    
 				    File tmpFile = new File(SAVE_DIR + "/"+ saveFileName);
 				    if (tmpFile.exists()) //중복된 파일이 존재하면 다시 생성.
 				    {
 				    	tmpFile = null;
 				    	continue;
 				    }
 				    
 				    original = multi.getOriginalFileName(name);			    
 					File file = multi.getFile(name);
 					size = ""+file.length();
 			        if(file.exists()){
 				        File newFile = new File(SAVE_DIR + saveFileName);
 				        file.renameTo(newFile);
 				        newFile = null;
 			        }
 				}
 				isOk = false;
 				
 				JSONObject jsonObject = new JSONObject();
 				jsonObject.put("original", original);
 				jsonObject.put("saveFileName", saveFileName);
 				saveFileId = saveFileName.substring(0, saveFileName.lastIndexOf('.'));
 				jsonObject.put("saveFileId", saveFileId);
 				jsonObject.put("saveFileSize", size);
 				//jsonObject.put("saveDir", SAVE_DIR);
 				jsonObject.put("saveDir", "");
 				jsonObject.put("webDir", CONTEXT_PATH + WEB_DIR);
 				
 				jsonArr.add(jsonObject);
 			}
 		  }
		return jsonArr;
	} 
  
}
