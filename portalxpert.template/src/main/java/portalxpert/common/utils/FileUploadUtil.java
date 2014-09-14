package portalxpert.common.utils;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
 		
 		JSONArray jsonArr = new JSONArray();
 		
 		if(request instanceof MultipartRequest){ //cos.jar
	 		MultipartRequest multi = new MultipartRequest(request, SAVE_DIR, maxSize, encType, new DefaultFileRenamePolicy());
	 		Enumeration files = multi.getFileNames();
	 		
	 		
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
 		}else if(request instanceof MultipartHttpServletRequest){
 			MultipartHttpServletRequest multi = (MultipartHttpServletRequest) request;
 			Map<String, MultipartFile> files = multi.getFileMap();
 			
		 	Iterator<Entry<String, MultipartFile>> itr = files.entrySet().iterator();
		 	MultipartFile file;
		 	String filePath;
		 	 
		 	while (itr.hasNext()) {
		 		Entry<String, MultipartFile> entry = itr.next();
		 		System.out.println("[" + entry.getKey() + "]");
		 	 
		 		file = entry.getValue();
		 		if (!"".equals(file.getOriginalFilename())) {
		 			filename = file.getOriginalFilename();
		 			saveFileName = CommUtil.makeRandomString();
		 			
		 			int idx = filename.lastIndexOf('.');
 				    if (idx != -1) {
 				        String extension = filename.substring(idx).toLowerCase();
 				        
 				       if(!portalxpert.common.utils.CommUtil.uploadExtensionsCheck(extension, null)){
				        	throw new Exception("Invalid upload file");
				        }
 				        
 				        saveFileName = saveFileName + extension;
 				    }
		 			
 				   File tmpFile = new File(SAVE_DIR);
 				   
 				 	// 디렉토리 생성
 				 	if (!tmpFile.exists() || tmpFile.isFile()) {
 				 		tmpFile.mkdirs();
 				 	}
 				    
		 			filePath = SAVE_DIR + saveFileName;
		 			file.transferTo(new File(filePath));
		 	 
	 				JSONObject jsonObject = new JSONObject();
	 				jsonObject.put("original", filename);
	 				jsonObject.put("saveFileName", saveFileName);
	 				saveFileId = saveFileName.substring(0, saveFileName.lastIndexOf('.'));
	 				jsonObject.put("saveFileId", saveFileId);
	 				jsonObject.put("saveFileSize", file.getSize());
	 				jsonObject.put("saveDir", "");
	 				jsonObject.put("webDir", CONTEXT_PATH + WEB_DIR);
	 				
	 				jsonArr.add(jsonObject);
		 		}
		 	} 			
 			
 		}
		return jsonArr;
	} 
  
}
