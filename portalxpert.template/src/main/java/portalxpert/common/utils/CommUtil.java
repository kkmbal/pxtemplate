package portalxpert.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.e;

import org.springframework.web.multipart.MultipartFile;

import portalxpert.common.config.PortalxpertConfigUtils;

public class CommUtil {
	// 파일 업로드 불가한 확장자.
	private static final String[] DENY_EXTENSIONS = {"do","java","class","jsp","php","php3","php5","asp","aspx","cfm","bat","exe","dll","cgi","js"};
	
	/**
	 * DB로부터 취득한 주민번호를 복호화 하여 뒤에 7자리는 아스키 코드로 표시
	 * ex) 123456-*******
	 * 
	 * @param jumin
	 * @return
	 * @throws Exception
	 */
//	public static String getChangeJuminNum(String jumin) throws Exception{
//		
//		String result = "";
//		
//		if(StringUtils.isNotEmpty(jumin)){
//			ImCommonSecurityUtil util = new ImCommonSecurityUtil();
//			try{
//				String decrypt_jumin = util.decrypt(jumin);
//				if( decrypt_jumin.length()==13){
//					result = decrypt_jumin.substring(0, 6)+"-*******";
//				}else{
//					return decrypt_jumin;
//				}
//			}catch(Exception ex){
//				ex.printStackTrace();
//				result = jumin;
//			}
//		}
//		
//		return result;
//	}
	
	/**
	 *	원하는 형태로 현재 날짜 가져오기
	 *	@param	String 날짜형식 format
	 *
	 *	@return	String format으로 정의된 현재 날짜
	 */
	public static String getDateString(String p_format)	{
		if ( NVL(p_format) == "" )
			p_format = "yyyy-MM-dd";

		return new java.text.SimpleDateFormat(p_format).format(new java.util.Date());
	}

	/**
	 *	오늘 날짜 가져오기
	 *	@param	String 날짜형식 format
	 *
	 *	@return	String format으로 정의된 현재 날짜
	 */
	public static String getDateString()	{
		return getDateString("yyyy-MM-dd");
	}
	
	/**
	 * 그달의 마지막 일자 구하기
	 * 
	 */
	public static String getDayOfMonth(String ym){
		String year = ym.substring(0, 4);
		String month = ym.substring(4);
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(year), Integer.parseInt(month)-1 , 1);
		return year + month + cal.getActualMaximum(Calendar.DAY_OF_MONTH);
	}	
	
	//입력으로 들어온 일자에 대해 입력한 개월수 만큼 이전 일자로 변환
    public static String getAddDate(String strDate, String format, int addDate)
    {
        SimpleDateFormat fmt = new SimpleDateFormat(format);// NOPMD : 범용성을 고려하여 default locale을 사용하므로 검출제외 by 홍태구
        Calendar calendar = Calendar.getInstance();
        try
        {
            calendar.setTime(fmt.parse(strDate));
        }
        catch (Exception e)
        {
            return null;
        }
        
        calendar.add(Calendar.DATE, addDate);

        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(calendar.getTime());
    }
	
	/**
	 *	문자열을 받아서 null이면 공백 문자열로 리턴
	 *	@param  String str
	 *	@return String rtnStr
	 */
	public static String NVL(String str)	{
		if ((str == null) || (str.trim().equals("")) || (str.trim().equals("null")))
			return "";
		else
			return str.trim();
	}
	
	public static boolean isEmpty(String str){
		if("".equals(NVL(str))){
			return true;
		}else{
			return false;
		}
	}
	
    public String writeFile(MultipartFile multipartFile) throws IOException {
        OutputStream out = null;

            out = new FileOutputStream("D:/" + multipartFile.getOriginalFilename());
            String filePath = "D:/" + multipartFile.getOriginalFilename();
            BufferedInputStream bis = new BufferedInputStream(multipartFile.getInputStream());
            byte[] buffer = new byte[8106];
            int read;
            while ((read = bis.read(buffer)) > 0) {
                out.write(buffer, 0, read);
            }

            return filePath;

    }
    
    /*
     * 첨부파일을 저장할 디렉토리를 생성한다.
     * */
    public static String fullApndMakeDir(String dir)
    {
    	String result = "";
  	  try
  	  {
  		  String today = getDateString("");
  		  String temp = dir;
  		  
  		  if (!temp.endsWith("/"))
  		  {
  			temp += '/';
  		  }
  		  

  		  String fullDirPath = temp + today.replaceAll("-", "/");
  		  
  		  File f = new File(fullDirPath);
  		  if (!f.exists())
  		  {
  			  f.mkdirs();
  		  }

  		  result = today.replaceAll("-", "/");
  	  }catch(Exception e)
  	  {
  		  e.printStackTrace();
  	  }
  	  return result;
    }
    
    /**
	 *	문자열 자르기
	 *	@param	변경할 문자열, 길이, 공백
	 *	@return	변경된 문자열
	 */    
    public static String cutString(String src, int str_length, String att_str) {
        int ret_str_length = 0;

        StringBuffer ret_str = new StringBuffer("");

        if (src == null) {
            return ret_str.toString();
        }

        // 현재 환경의 Character length를 구한다.
        String tempMulLanChar = new String("가");
        int lanCharLength = tempMulLanChar.length();

        // Character가 중간에 잘리지 않게 하기위해 넣은 변수
        int multiLanCharIndex = 0;
        int nonMultiLanCharIndex = 0;

        for (int i=0; i<src.length(); i++) {
            ret_str.append(src.charAt(i));

            if (src.charAt(i)>'~') {
                ret_str_length = ret_str_length + 2/lanCharLength;
                multiLanCharIndex++;
            } else {
                ret_str_length = ret_str_length + 1;
                nonMultiLanCharIndex++;
            }

            if(ret_str_length >= str_length && (multiLanCharIndex%lanCharLength) == 0) {
                if (src.length() != multiLanCharIndex + nonMultiLanCharIndex)
                    ret_str.append(NVL(att_str));
                break;
            }
        }

        return ret_str.toString();
    }
    
    /**
     * 문자형을 숫자형으로 변환
     * @param str
     * @return int
     */
    public static int getStringToInt(String str){
    	
    	int rtnNum = 0;
    	Pattern pNumber  = Pattern.compile("[\\d]+");
    	if(pNumber.matcher(str).matches()){//숫자라면
    		rtnNum = Integer.parseInt(str);
    	}else{
    		//System.out.println("숫자가 아닙니다.");
    	}
    	
    	return rtnNum;
    	
    }
    
    public static byte[] getBytesFromFile(File file) throws IOException {
	     InputStream is = new FileInputStream(file);
	      
	     long length = file.length();
        byte[] bytes = new byte[(int)length];
	
	     int offset = 0;
	     int numRead = 0;
	     while (offset < bytes.length
	            && (numRead=is.read(bytes, offset, bytes.length-offset)) >= 0) {
	         offset += numRead;
	     }
	     
	     if (offset < bytes.length) {
	         throw new IOException("Could not completely read file "+file.getName());
	     }
	     
	     is.close();
	      
	     return bytes;
    }
    
    
    public static byte[] generateImage( byte[] imageContent,String exts, int maxWidth, double xyRatio) throws IOException {

        BufferedImage originalImg = ImageIO.read( new ByteArrayInputStream(imageContent));

        //get the center point for crop

        int[] centerPoint = { originalImg.getWidth() /2, originalImg.getHeight() / 2 };

        //calculate crop area

        int cropWidth=originalImg.getWidth();
        int cropHeight=originalImg.getHeight();
        if( cropHeight > cropWidth * xyRatio ) {
            cropHeight = (int) (cropWidth * xyRatio);

        } else {
            cropWidth = (int) ( (float) cropHeight / xyRatio) ;

        }

        //set target image size
        int targetWidth = cropWidth;
        int targetHeight = cropHeight;
        
        if( targetWidth > maxWidth) {

            //too big image
            targetWidth = maxWidth;
            targetHeight = (int) (targetWidth * xyRatio);

        }
        
        BufferedImage targetImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);

        Graphics2D graphics2D = targetImage.createGraphics();

        graphics2D.setBackground(Color.WHITE);

        graphics2D.setPaint(Color.WHITE);

        graphics2D.fillRect(0, 0, targetWidth, targetHeight);

        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        graphics2D.drawImage(originalImg, 0, 0, targetWidth, targetHeight,   centerPoint[0] - (int)(cropWidth /2) , centerPoint[1] - (int)(cropHeight /2), centerPoint[0] + (int)(cropWidth /2), centerPoint[1] + (int)(cropHeight /2), null);
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        ImageIO.write(targetImage, exts, output);
 
        return output.toByteArray();

    }
    
    public static String makeTempDir(String path)
    {
    	String strResult = "";
    	String today = CommUtil.getDateString("yyyyMMdd");
    	File f = new File(path+today);

 		if (!f.exists())
 		{
 			f.mkdirs();
 		}
 		strResult = today+'/';
 		return strResult;
    }
    
    // 임의의 파일명 생성
    public static String makeRandomString(){
    	StringBuilder str = new StringBuilder();
	    for (int i=1; i < 21; i++) {
	        int rnd = 1 + (int)(Math.random() * 52);
	        rnd = (rnd > 26) ? rnd + 70 : rnd + 64;
	        str.append( (char)(rnd) );
	    }    	
	    return str.toString();
    }
 
    /**
     * Temp 파일을 Real 파일로 이동한다. 
     * 
     * @param fileName 파일명
     * @return
     */
    public static String apndFileCopy(String fileName){
    	return apndFileCopy(null, null, fileName);
    }
    
    /**
     * Temp 파일을 Real 파일로 이동한다. 
     * 
     * @param tempPath 임시경로
     * @param fileName 파일명
     * @return
     */
    public static String apndFileCopy(String tempPath, String fileName){
    	return apndFileCopy(tempPath, null, fileName);
    }
    
    /**
     * Temp 파일을 Real 파일로 이동한다. 
     * 
     * @param tempPath 임시경로
     * @param realPath 실제경로
     * @param fileName 파일명
     * @return
     */
    public static String apndFileCopy(String tempPath, String realPath, String fileName)
    {
    	String strResult = "";
    	try
    	{
    		String TEMP_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
    		String SAVE_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
    		
    		String sourceFile = "";
    		
    		if(CommUtil.isEmpty(tempPath)){
    			tempPath = TEMP_DIR;
    		}
    		if(!CommUtil.isEmpty(realPath)){
    			SAVE_DIR = realPath;
    		}
    		String destDir = CommUtil.fullApndMakeDir(SAVE_DIR);
    		
    		if (!tempPath.endsWith("/"))
    		{
    			sourceFile = tempPath+"/"+ fileName;
    		}
    		else
    		{
    			sourceFile = tempPath+fileName;
    		}
    		
    		
    		File f = new File(sourceFile);
    		if (!f.exists())
    		{
    			return destDir;
    		}
    		if (SAVE_DIR.endsWith("/")){
    			SAVE_DIR = SAVE_DIR.substring(0, SAVE_DIR.length()-1);
    		}
    		if (destDir.endsWith("/")){
    			destDir = destDir.substring(0, destDir.length()-1);
    		}
    		
    		
    		File realF = new File(SAVE_DIR+'/'+destDir+'/'+fileName);    		
    		f.renameTo(realF);
    		strResult = destDir;
    		
    	}
    	catch(Exception e)
    	{
    		
    	}
    	return strResult;
    }    
    
    public static byte[] getImageContent(URL url) throws IOException {

        ByteArrayOutputStream bais = new ByteArrayOutputStream();

        InputStream is = null;
          is = url.openStream ();

          byte[] byteChunk = new byte[4096]; // Or whatever size you want to read in at a time.

          int n;

          while ( (n = is.read(byteChunk)) > 0 ) {

            bais.write(byteChunk, 0, n);

          }

        is.close();

        return bais.toByteArray();

    }
    
    //HTML 태그 없애기
    public static String htmlRemove(String str) {
    	
    	StringBuffer t = new StringBuffer();
    	StringBuffer t2 = new StringBuffer();
    	  
    	char[] c = str.toCharArray();
    	char ch;
    	int d = 0;
    	boolean check = false;
    	boolean scriptChkeck = false;
    	boolean styleCheck = false;
    	
    	for (int i=0, len = c.length; i < len; i++) {
    		ch = c[i];
    		
    		if (ch == '<') {
    			check = true;
    		}
    	   
    		if (!check&!scriptChkeck&&!styleCheck) {
    			t.append(ch);
    		}
    	 
    	    d++;
    	    t2.append(ch);
    	    
    	    if (d > 9) {
    	    	t2.delete(0,1);
    	    }
    	    
    	    if (!scriptChkeck) {
    	    	if(t2.toString().toLowerCase().indexOf("<script")==0){
    	    		scriptChkeck = true; 
    	     	}
    	    }
    	    
    	    if (scriptChkeck) {
    	    	if(t2.toString().toLowerCase().indexOf("</script>")==0){
    	    		scriptChkeck = false; 
    	    	}
    	    }
    	    
    	    if (!styleCheck) {
    	    	if(t2.toString().toLowerCase().indexOf("<style")==0){
    	    		styleCheck = true; 
    	    	}
    	    }

    	    if (styleCheck) {
    	    	if(t2.toString().toLowerCase().indexOf("</style>")==0){
    	    		styleCheck = false; 
    	    	}
    	    }
    	    
    	    if (ch == '>') {
    	    	check = false;
    	    }
    	}
    	  
    	return  t.toString();  
    }
    
    //요청한 클라이언트의 IP 리턴
    public static String getClientIpAddr(HttpServletRequest request) {   
    	 
        String ip = request.getHeader("X-Forwarded-For"); 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) { 
            ip = request.getHeader("Proxy-Client-IP");   
        }   
 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            ip = request.getHeader("WL-Proxy-Client-IP");   
        }   
 
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("HTTP_CLIENT_IP");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");   
        }   
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
            ip = request.getRemoteAddr();   
        }   
        return ip;   
 
    }
    
    /**
     * 개발모드 여부 확인
     * @param request
     * @return
     */
    public static boolean isDevMode() {
    	boolean isDevMode = false;
    	//개발모드여부체크.
    	//portal.app.mode
    	try{
        	String appMode = PortalxpertConfigUtils.getString("portal.app.mode");
        	if("dev".equals(appMode)) isDevMode = true;
    	}catch(Exception e){}
    	
    	return isDevMode;
    }
    
    /**
     * app모드 확인(개발:dev, 통합테스트:prod, 시범운영:demo, 운영:real)
     * @param request
     * @return
     */
    public static String getAppMode() {
    	String appMode = "dev";
    	try{
        	appMode = PortalxpertConfigUtils.getString("portal.app.mode");
    	}catch(Exception e){}
    	
    	return appMode;
    }
    
    /**
     * 문자열 숫자인지 체크
     * @param s
     * @return
     * @author crossent
     */
    public static boolean isNumOnly(String s) {
        int i = s.length();

        for(int j = 0; j < i; j++) {

            char c = s.charAt(j);

            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }
    
    //script 태그 없애기
    public static String scriptRemove(Object str) {
    	if(str != null){
    		StringBuilder sb = new StringBuilder();
    		sb.append(str.toString().replaceAll("(?i)<script>", "&lt;script&gt;").replaceAll("(?i)<\\/script>", "&lt;/script&gt;"));
    		return sb.toString();
    	}else{
    		return null;
    	}
    	//return htmlRemove(str.toString());
    }
    
    //script 태그 없애기
    public static String scriptRemoveSearch(Object str) {
    	String req_value = str.toString();
        req_value = req_value.replaceAll("</?[a-zA-Z][0-9a-zA-Z가-\uD7A3ㄱ-ㅎ=/\"\'%;:,._()\\-# ]+>", "");
        req_value = req_value.replaceAll(">","");
        req_value = req_value.replaceAll(">","");
        return req_value;
    	//return htmlRemove(str.toString());
    }
    
    //업로드 파일 확장자 체크
    public static boolean uploadExtensionsCheck(String fileName, File file){
    	String fileNameExt = fileName.substring(fileName.lastIndexOf(".")+1);
    	if(Arrays.asList(CommUtil.DENY_EXTENSIONS).contains(fileNameExt.toLowerCase())){
    		if(file != null && file.exists()) file.delete();
    		return false;
    	}
    	return true;
    }
    
    public static String stripHTMLTags(String message) {
        StringBuffer returnMessage = new StringBuffer(message);
        int startPosition = message.indexOf("<"); // first opening brace
        int endPosition = message.indexOf(">"); // first closing braces
        while (startPosition != -1) {
            returnMessage.delete(startPosition, endPosition + 1); // remove the tag
            startPosition = (returnMessage.toString()).indexOf("<"); // look for the next opening brace
            endPosition = (returnMessage.toString()).indexOf(">"); // look for the next closing brace
        }
        return returnMessage.toString();
    }
    
	public static String htmlEscape(String strVal) {
		StringBuffer strResult = new StringBuffer();
		for (int i = 0; i < strVal.length(); i++) {
			switch (strVal.charAt(i)) {
			case '&':
				strResult.append("&amp;");
				break;
			case '<':
				strResult.append("&lt;");
				break;
			case '>':
				strResult.append("&gt;");
				break;
			default:
				strResult.append(strVal.charAt(i));
				break;
			}
		}
		return strResult.toString();
	}
	
    public static boolean NumberChk(String str){
        char c;

        if(str.equals("")) return false;
        
        for(int i = 0 ; i < str.length() ; i++){
            c = str.charAt(i);
            if(c < 48 || c > 57){
                return false;
            }
        }
        return true;
    }	
    
    public static String getListToStr(List<String> list){
    	if(list == null || list.size() == 0) return null;
    	StringBuilder sb = new StringBuilder();
    	for(String str : list){
    		sb.append(str).append(",");
    	}
    	return sb.substring(0, sb.length()-1).toString();
    }
    
    /**
     * 메일보내기
     * @param toMail TO_MAIL
     * @param fromMail FROM_MAIL
     * @param boardName 게시판명
     * @param notiTitle 게시물제목
     * @param id 받는사람 암호화주민번호
     * @param toName TO_NAME
     * @param fromName FROM_NAME
     * @return String
     */
    public static String relayMailSend(String toMail, String fromMail, String conts, String id, String toName, String fromName, String url) {
    	return null;
    }
    
    public static void sendAlert(String ownerId, String fromName, String conts){
    }
    
    public static void main(String[] args){
    	ArrayList a = new ArrayList();
    	a.add("A");
    	a.add("B");
    	a.add("C");
    	System.out.println(getListToStr(a));
    }
}
