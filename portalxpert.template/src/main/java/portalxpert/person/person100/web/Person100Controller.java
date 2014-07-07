package portalxpert.person.person100.web;



import java.util.List;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.sc.UserLoginService;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.sc.OrganizationService;
import portalxpert.organization.vo.CategoryVO;
import portalxpert.person.person100.sc.Person100Service;
import portalxpert.person.person100.vo.AdmTmlnEnvSetVO;
import portalxpert.person.person100.vo.ComCntsInfoVO;
import portalxpert.person.person100.vo.ComUserDeptInfoVO;
import portalxpert.person.person100.vo.ComUserInfoVO;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import portalxpert.person.person100.vo.MesUserCountsVO;
import portalxpert.person.person100.vo.PsnUserBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrTagVO;
import portalxpert.person.person100.vo.PsnUserFrdInfoVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person100.vo.PsnUserTmlnAlertSetVO;
import portalxpert.person.person100.vo.SmsSendHistVO;
import portalxpert.person.person100.vo.TmlnEnvInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value = "person100")
public class Person100Controller {

	/** board210Service */
	@Resource(name = "board210Service")
	private Board210Service board210Service;
	
	/** person100Service */
	@Resource(name = "person100Service")
	private Person100Service person100Service;
	
	@Resource(name = "userLoginService")
    private UserLoginService userLoginService;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory
			.getLogger(Person100Controller.class);
	
	/**
     * 개인 홈 마이게시판 조회 
     * @param modelMap
     * @return personBoardCreate
     * @throws Exception
     */
	@RequestMapping(value = "/personMyBoardList")
	public String personMyBoard(ModelMap modelMap) throws Exception {

		return ".person/person/person100MyBoardList";

	}
	
	/**
     * 개인 홈 마이게시판 등록 
     * @param modelMap
     * @return personBoardCreate
     * @throws Exception
     */
	@RequestMapping(value = "/personMyBoardWrite")
	public String personMyBoardWrite(ModelMap modelMap) throws Exception {

		return ".person/person/person100MyBoardWrite";

	}

	
	/**
     * 개인 홈 마이게시판 상세 
     * @param modelMap
     * @return personBoardCreate
     * @throws Exception
     */
	@RequestMapping(value = "/personMyBoardView")
	public String personMyBoardView(ModelMap modelMap) throws Exception {

		return ".person/person/person100MyBoardView";

	}
	
	/**
     * 개인 홈 마이게시판 수정 
     * @param modelMap
     * @return personBoardCreate
     * @throws Exception
     */
	@RequestMapping(value = "/personMyBoardModify")
	public String personMyBoardModify(ModelMap modelMap) throws Exception {

		return ".person/person/person100MyBoardModify";

	}
	
	/**
     * 개인 홈 마이최근게시판 
     * @param modelMap
     * @return personBoardCreate
     * @throws Exception
     */
	@RequestMapping(value = "/personMyLastBoard")
	public String personMyLastBoard(ModelMap modelMap) throws Exception {

		return ".person/person/person100MyLastBoard";

	}

	/**
     * 사용자 정보 수정 
     * @param modelMap
     * @return data
     * @throws Exception
     */
	@RequestMapping(value = "/updatePsnUserInfo", method = RequestMethod.POST)
	public ModelMap updatePsnUserInfo(
			@RequestParam(value = "data", required = true) String data,
			ModelMap modelMap,HttpSession session) throws Exception {
			
		JSONResult jsonResult = new JSONResult();
		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		try {
			person100Service.updatePsnUserInfo(data, session);
			
			UserInfoVO vo = new UserInfoVO();
			vo = userLoginService.getLoginInfo(info.getSid());	
	   		session.setAttribute("pxLoginInfo", vo);	
	   		
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
	
    
	
	/**
     * 개인정보관리 페이지
     * @param modelMap
     * @return personInfoMange
     * @throws Exception
     */
	@RequestMapping(value = "/personInfoMange")
	public String personInfoMange(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//return ".person/person/person100InfoMange";
		return ".self/person/person100InfoMange";

	}
	
	/**
     * 개인정보관리 - 개인정보 조회   
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getComUserInfoList",method = RequestMethod.POST)
	public ModelMap getComUserInfoList(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			ComUserInfoVO comUserInfoVO = new ComUserInfoVO();
			String userId = getUserId(session);//세션에서 가져오도록 수정
			//String userId = "66d7293b98f201ef8733e82eebd0e6ea";
			//String resiNumber = getUserIdDecrypt(session).substring(0,6)+" - *******"; 
			String resiNumber = getUserIdDecrypt(session); 
			
			comUserInfoVO.setUserId(userId);
			List<ComUserInfoVO> userDetailInfo = null;
			
			if( ! userId.equals("")){
			
				userDetailInfo = person100Service.getComUserInfoList(comUserInfoVO);
			}
			
	        model.put("resiNumber", resiNumber);
			model.put("userDetailInfo", JSONUtils.objectToJSON(userDetailInfo));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
        return model;

	}
	
	/**
     * 개인정보관리 - 사용자 이미지 조회    
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getComUserPotoInfoList",method = RequestMethod.POST)
	public ModelMap getComUserPotoInfoList(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			ComUserPotoInfoVO comUserPotoInfoVO = new ComUserPotoInfoVO();
			comUserPotoInfoVO.setUserId(getUserId(session));
			
			List<ComUserPotoInfoVO> comUserPotoInfoList = null;
			
			if( ! getUserId(session).equals("")){
			
				comUserPotoInfoList = person100Service.getComUserPotoInfoList(comUserPotoInfoVO);
				
			}
			
	        
			model.put("comUserPotoInfoList", JSONUtils.objectToJSON(comUserPotoInfoList));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
        return model;

	}
	
	/**
     * 패스워드 변경
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/doChangePwd", method = RequestMethod.POST)
	public ModelMap doChangePwd(
			@RequestParam(value = "data", required = true) String data,
			ModelMap modelMap) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
		
		String rtnMsg = "";
		boolean isSuccess = true;//성공여부 
		int pwdLength = 9;//패스워드 자릿수 
		int Eng = 0, Num = 0;
		JSONObject PbsUserPwdObject = JSONObject.fromObject(data);
		String strOldPswd =  (String) PbsUserPwdObject.get("strOldPswd");
		String strNewPswd1 = (String) PbsUserPwdObject.get("strNewPswd1");
		String strNewPswd2 = (String) PbsUserPwdObject.get("strNewPswd2");

		logger.debug("objectToJSON 1 : "+PbsUserPwdObject.get("strOldPswd"));
		logger.debug("objectToJSON 2 : "+PbsUserPwdObject.get("strNewPswd1"));
		logger.debug("objectToJSON 3 : "+PbsUserPwdObject.get("strNewPswd2"));
		
		// 특수 문자 체크 목록
        int vFilter1 = strNewPswd2.indexOf("@");
        int vFilter2 = strNewPswd2.indexOf("&");
        int vFilter3 = strNewPswd2.indexOf("^");
        int vFilter4 = strNewPswd2.indexOf("$");
        int vFilter5 = strNewPswd2.indexOf("*");
        
        if (vFilter1 >= 0 || vFilter2 >= 0 || vFilter3 >= 0 || vFilter4 >= 0 || vFilter5 >= 0) {
            String vFilterWord = "";
            if (vFilter1 >= 0) { vFilterWord += "@ "; }
            if (vFilter2 >= 0) { vFilterWord += "& "; }
            if (vFilter3 >= 0) { vFilterWord += "^ "; }
            if (vFilter4 >= 0) { vFilterWord += "$ "; }
            if (vFilter5 >= 0) { vFilterWord += "* "; }
            rtnMsg = strNewPswd2 + "에 제한하는 특수문자[" + vFilterWord + "]가 있습니다\n다른 문자를 입력하십시오.";
            isSuccess = false; 
        }
        
        if(isSuccess){
        	
    		if (pwdLength <= strNewPswd1.length()) {
    			rtnMsg = "사용자 보안정책에 의해 새비밀번호는" + pwdLength + " 자리 이상 유효합니다.";
    			isSuccess = false; 
    		}
        }
        
        Pattern pCombine = Pattern.compile("[\\w&&[^_]]+");
        Pattern pNumber  = Pattern.compile("[\\d]+");
        Pattern pString  = Pattern.compile("[a-zA-Z]+");
        
        if(isSuccess){
        	if ( ! (     pCombine.matcher(strNewPswd2).matches() 
        	    && ! pNumber.matcher(strNewPswd2).matches() 
        	    && ! pString.matcher(strNewPswd2).matches())) {//문자 숫자 조합이 아닌 경우
        		isSuccess = false; 
        		rtnMsg = "비밀번호는 숫자와 문자 등을 혼합하여 조합하여야 합니다.";
        	} 
        }
        
        try {
			if(isSuccess){
				String pwdOld = "";
				String pwdNew = "";
				
				String strResult = "";
				String id = "intom";
				int nRet = 0;
//				MGApiJni pmi = new MGApiJni();
				
//				if (pmi != null)
//				{
//					String MEAM_SERVER_IP		= "99.99.11.21";
//					String MPS_SITE_NAME = "";
//					int MEAM_SERVER_PORT		= 40010;
//					
//					int GPMI_SUCCESS			= 0;
//			
//					// SA 에서 세션정보 추출
//					pmi.init(MPS_SITE_NAME, MEAM_SERVER_IP, MEAM_SERVER_PORT);
//					nRet = pmi.updatePassword(id, pwdOld, pwdNew);
//					if (nRet == GPMI_SUCCESS)
//					{
//						strResult = "비밀번호가 ("+pwdNew+")으로 변경되었습니다.";
//					}
//					else
//					{
//						if (nRet == 512) {
//							strResult = "이전 암호가 틀립니다 다시 한번 확인 하세요";
//						} else {
//							strResult = "error code="+nRet;
//						}
//					}
//				}

				
				jsonResult.setSuccess(true);
				jsonResult.setMessage("수정하였습니다.");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setMessage(rtnMsg);
			}
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
	
	/**
	 * 개인홈 MY게시판 조회
	 * @param model
	 * @param request
	 * @param session
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPbsUserBoardInfoList",method = RequestMethod.POST)
	public ModelMap getPbsUserBoardInfoList(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			PbsUserBoardInfoVO pbsUserBoardInfoVO = new PbsUserBoardInfoVO();
			pbsUserBoardInfoVO.setBoardOwnrId(getUserId(session));
			//pbsUserBoardInfoVO.setBoardOwnrId("e749ddee5af73b7d5ed213d23e5c5b91");
			
			List<PbsUserBoardInfoVO> pbsUserBoardInfoList = null;
			
			if( ! getUserId(session).equals("")){
			
				pbsUserBoardInfoList = person100Service.getPbsUserBoardInfoList(pbsUserBoardInfoVO);
			}
			
	        
			model.put("pbsUserBoardInfoList", JSONUtils.objectToJSON(pbsUserBoardInfoList));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
        return model;

	}
	
	/**
	 * 개인홈 즐겨찾기 조회
	 * @param model
	 * @param request
	 * @param session
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonLnbFavoList",method = RequestMethod.POST)
	public ModelMap getPersonLnbFavoList(
			PbsUserBoardInfoVO pbsUserBoardInfoVO,    		
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		pbsUserBoardInfoVO.setBoardOwnrId(info.getId());  
    		List<PbsUserBoardInfoVO> favoList = person100Service.getPersonLnbFavoList(pbsUserBoardInfoVO);
    		
    		if(favoList.size() > 0){
    			modelMap.put("favoList", JSONUtils.objectToJSON(favoList));
    		}else{
    			modelMap.put("favoList", "[]");
    		}
    		jsonResult.setSuccess(true);
    		jsonResult.setMessage("");
    		
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	modelMap.put("jsonResult", jsonResult); 
		
        return modelMap;

	}
	
	/**
	 * 개인홈 참여게시판 조회
	 * @param model
	 * @param request
	 * @param session
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPersonLnbPartList",method = RequestMethod.POST)
	public ModelMap getPersonLnbPartList(
			PbsUserBoardInfoVO pbsUserBoardInfoVO,    		
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		pbsUserBoardInfoVO.setBoardOwnrId(info.getId());  
    		List<PbsUserBoardInfoVO> partList = person100Service.getPersonLnbPartList(pbsUserBoardInfoVO);
    		jsonResult.setSuccess(true);
    		modelMap.put("partList", JSONUtils.objectToJSON(partList));
    		jsonResult.setMessage("");
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	modelMap.put("jsonResult", jsonResult); 
		
        return modelMap;

	}
	
	/**
	 * 개인홈 MY게시판 조회
	 * @param model
	 * @param request
	 * @param session
	 * @return model
	 * @throws Exception
	 */
	@RequestMapping(value = "/getPbsUserBoardInfoListForZTree",method = RequestMethod.POST)
	public ModelMap getPersonLnbForZTree(CategoryVO categoryVO,
			@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:사용자용, 2:관리자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)  		
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		categoryVO.setId(info.getId());
    		categoryVO.setKind(kind);
    		categoryVO.setAdmin(admin);
    		List<CategoryVO> list = organizationService.getCategoryList(categoryVO,info.getId(), session);
    		
    		String conts = "", userId ="";
    		if(list.size() == 0){
    			
    			//conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"MY게시판\",\"icon\":\"../resources/images/img/img_category.png\"}]";
    			conts = "[]";
    			
    		}else{
    			
    			CategoryVO vo = (CategoryVO)list.get(0);
    			conts = vo.getConts();
    			userId = vo.getUserId();
    		}
    		
    		modelMap.put("boardList", JSONUtils.objectToJSON(conts));
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	modelMap.put("jsonResult", jsonResult); 
		
        return modelMap;

	}
	
	

	  @RequestMapping("/personFileUpload") 
	  @ResponseBody 
	  public void personFileUpload(HttpServletRequest request, 
				HttpServletResponse response,
				ModelMap model,
				HttpSession session) throws Exception{
		  
		String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
		String WEB_DIR = PortalxpertConfigUtils.getString("upload.temp.web");
		String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
		int maxFileSize = PortalxpertConfigUtils.getInt("upload.file.size");
		   
		JSONArray jsonArr = FileUploadUtil.upload(request, SAVE_DIR, WEB_DIR, CONTEXT_PATH, maxFileSize);
		
 		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
 		//wrapper.setContentType("text/plain");
 		response.getWriter().print(jsonArr.toString());
 		response.getWriter().flush();
 		response.getWriter().close();
		
	}
	  
	private String getUserId(HttpSession session){
		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		
		logger.debug("getUserId() : "+info.getId());//아이디
		
		return info.getId();
	}
	
	private String getUserIdDecrypt(HttpSession session){
		
		String userId = "";
		
//		EAMCrypt ec = new EAMCrypt();
		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		//userId = info.getId();
		//logger.debug("getUserId() : "+info.getId());//아이디
		userId = info.getResinumber();
//		try {
//			userId = ec.decrypt(info.getId());
//		} catch (InvalidKeyException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		return userId;
	}

   /**
    * 개인홈관리 페이지
    * @param modelMap
    * @return person100HomeMange
    * @throws Exception
    */
	@RequestMapping(value = "/personHomeMange")
	public String personHomeMange(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		//return ".person/person/person100HomeMange";
		return ".self/person/person100HomeMange";
	}
	
	/**
     * 현재 세션에서 사용자 프로필 조회
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getUserPicFromSesseion",method = RequestMethod.POST)
	public ModelMap getUserPicFromSesseion(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(true);
        
        logger.debug("getUserPic jsonResult : "+jsonResult);
        logger.debug("getUserPic user_pic : "+info.getUserRepImg());
        model.put("jsonResult", jsonResult);
        model.put("user_pic", info.getUserRepImg());
        
        return model;

	}
	
	/**
     * 사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getPsnUserPotoInfoListForRoll",method = RequestMethod.POST)
	public ModelMap getPsnUserPotoInfoListForRoll(ModelMap model,HttpServletRequest request,
			@RequestParam(value="data" ,required = true) String potoSeq,
            HttpSession session) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		String sPathFileExt="";
		jsonResult.setSuccess(true);
		try{
	        List<ComUserPotoInfoVO> list = person100Service.getPsnUserPotoInfoListForRoll(potoSeq, session);
	        logger.debug("getPsnUserPotoInfoListForRoll list : "+list.size());
	        
	        if(list.size() > 0){
	        	 ComUserPotoInfoVO vo = (ComUserPotoInfoVO)list.get(0);
	             sPathFileExt =  vo.getFilePath() + vo.getFileName();
	             
	             logger.debug("getFilePath::::::::::::::::::"+vo.getFilePath());
	             logger.debug("getFileName::::::::::::::::::"+vo.getFileName());
	             logger.debug("jsonResult : "+jsonResult);
	             logger.debug("user_pic : "+JSONUtils.objectToJSON(sPathFileExt));
	             logger.debug("user_poto_seq : "+JSONUtils.objectToJSON(vo.getPotoSeq()));
	             
	             model.put("user_pic", JSONUtils.objectToJSON(sPathFileExt));
	             model.put("user_poto_seq", JSONUtils.objectToJSON(vo.getPotoSeq()));
	             model.put("user_pic_count", vo.getPhotoCnt());  //이미지 리스트 건수
	             
	        }else{
	        	logger.debug("list is null");
	            model.put("user_pic", PortalxpertConfigUtils.getString("image.web.contextpath") + PortalxpertConfigUtils.getString("person.image.default"));
	            model.put("user_poto_seq", "0");
	        }
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
       
        
        return model;

	}
	
	/**
    * 개인정보관리 - SMS발송이력 Frame
    * @param modelMap
    * @return person100HomeMange
    * @throws Exception
    */
	@RequestMapping(value = "/personSmsManageList")
	public String personSmsManageList(
			ModelMap modelMap, HttpServletRequest request,
			HttpServletResponse response,HttpSession session,
			@ModelAttribute("smsSendHistVO") SmsSendHistVO smsSendHistVO,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			@RequestParam(value="pageUnit",required = false) String pageUnit,
 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
 			@RequestParam(value="sendDttmFrom",required = false) String sendDttmFrom,
 			@RequestParam(value="sendDttmTo",required = false) String sendDttmTo
			) throws Exception {
		
		/** PropertyService.sample */
		smsSendHistVO.setPageUnit(Integer.parseInt(pageUnit));
		smsSendHistVO.setPageSize(propertiesService.getInt("pageSize"));
		smsSendHistVO.setPageIndex(Integer.parseInt(pageIndex));
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(smsSendHistVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(smsSendHistVO.getPageUnit());
		paginationInfo.setPageSize(smsSendHistVO.getPageSize());
		
		smsSendHistVO.setUserId(getUserId(session));
		smsSendHistVO.setSendDttmFrom(sendDttmFrom);
		smsSendHistVO.setSendDttmTo(sendDttmTo);
		smsSendHistVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		smsSendHistVO.setLastIndex(paginationInfo.getLastRecordIndex());
		smsSendHistVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		smsSendHistVO.setSearchKeyword(searchKeyword);
   		
		List resultList = person100Service.getSmsSendHistList(smsSendHistVO);
		int totCnt = person100Service.getSmsSendHistListTotCnt(smsSendHistVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		modelMap.put("smsSendHistVO", smsSendHistVO);
		modelMap.put("pageUnit", pageUnit);
		modelMap.put("resultList", resultList);
		modelMap.put("paginationInfo", paginationInfo);
		
		return ".self/person/person100SmsManageList";

	}
	
	/**
	* 개인정보관리 - SMS발송이력 삭제
	* @param model
	* @return ModelMap
	* @exception Exception
	*/
    @RequestMapping(value="/deleteSmsSendHist")
    public ModelMap deleteSmsSendHist(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			person100Service.deleteSmsSendHist(data, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
	/**
     * 관심태그 등록
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/insertPsnUserCncrTag", method = RequestMethod.POST)
    public ModelMap insertPsnUserCncrTag(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();

 		try{	
 			
 			person100Service.insertPsnUserCncrTag(data, session);
 			
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
    
    /**
     * 개인정보관리 - 관심태그조회   
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getPsnUserCncrTagList",method = RequestMethod.POST)
	public ModelMap getPsnUserCncrTagList(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
	    
		PsnUserCncrTagVO psnUserCncrTagVO = new PsnUserCncrTagVO();
		psnUserCncrTagVO.setUserId(getUserId(session));
		
		List<MesUserCountsVO> psnUserCncrTagList = null;
		psnUserCncrTagList = person100Service.getPsnUserCncrTagList(psnUserCncrTagVO);
		
		JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(true);
        
        logger.debug("mesUserCounts : "+JSONUtils.objectToJSON(psnUserCncrTagList));
        
        model.put("jsonResult", jsonResult);
		model.put("psnUserCncrTagList", JSONUtils.objectToJSON(psnUserCncrTagList));
		
        return model;

	}
	
	  /**
	    * 나의 관심직원 등록
	    * @param modelMap
	    * @return modelMap
	    * @throws Exception
	    * @author crossent
	    */
	   @RequestMapping(value = "/insertMyMemberList", method = RequestMethod.POST)
	   public ModelMap insertMyMemberList(
	   		@RequestParam(value="data" ,required = true) String data,
				ModelMap 		modelMap,
				HttpSession session
				
	   ) throws Exception {
	   	
	   		JSONResult jsonResult = new JSONResult();

			try{	
				
				person100Service.createMyMemberList(data, session);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
	   
	   /**
	    * 나의 관심직원 조회
	    * @param modelMap
	    * @return modelMap
	    * @throws Exception
	    * @author crossent
	    */
	   @RequestMapping(value="/getPsnUserFriendInfo")                            
	    public ModelMap getPsnUserFriendInfo(
	    		HttpSession session,
				ModelMap modelMap)
	            throws Exception {
	    	
	    	JSONResult jsonResult = new JSONResult();
	    	
	    	
	    	try {
	    		
	    		PsnUserFrdInfoVO vo = new PsnUserFrdInfoVO();
	    		
	    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    		
	    		vo.setUserId(info.getId());
	    		
	    		List<PsnUserFrdInfoVO> psnUserFrdInfo = person100Service.getPsnUserFriendInfo(vo);
	    	
	    		modelMap.put("psnUserFrdInfo", psnUserFrdInfo);
	    	
		    } catch (Exception e) {
		    	jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			};
			modelMap.put("jsonResult", jsonResult);
		    
			return modelMap;
		    		
	}
	   
	   
	/**
	 * 나의 관심직원 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserGrpInfo")                            
	 public ModelMap getPsnUserGrpInfo(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	
	 	try {
	 		PsnUserGrpInfoVO vo = new PsnUserGrpInfoVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		vo.setUserId(info.getId());
	 		List<PsnUserGrpInfoVO> psnUserGrpInfo = person100Service.getPsnUserGrpInfo(vo);
	 		modelMap.put("psnUserGrpInfo", psnUserGrpInfo);
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
			    		
	}   
		   
	/**
	 * 나의 그룹별 멤버 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserGrpFriendMap")                            
	 public ModelMap getPsnUserGrpFriendMap(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		PsnUserGrpFrdMapVO vo = new PsnUserGrpFrdMapVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	
	 		vo.setUserId(info.getId());
	 		List<PsnUserGrpFrdMapVO> psnUserGrpFrdMap= person100Service.getPsnUserGrpFriendMap(vo);
	 	
	 		modelMap.put("psnUserGrpFrdMap", psnUserGrpFrdMap);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	/**
	 * 나의 멤버건수
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	
	@RequestMapping(value="/getPsnUserFriendCnt")                            
	 public ModelMap getPsnUserFriendCnt(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		PsnUserGrpFrdMapVO vo = new PsnUserGrpFrdMapVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	
	 		vo.setUserId(info.getId());
	 		int psnUserFriendInfoCnt= person100Service.getPsnUserFriendInfoCnt(info.getId());
	 		
	 		int psnUserOVInfoCnt= person100Service.getPsnUserOVInfoCnt(info.getId());
	 		
	 		int staffFriendInfoCnt = person100Service.getStaffFriendInfoCnt(info.getId());
	 		
	 		logger.debug("psnUserFriendInfoCnt : " + psnUserFriendInfoCnt);
	 		
	 		modelMap.put("psnUserFriendInfoCnt","" +  psnUserFriendInfoCnt);
	 		modelMap.put("psnUserOVInfoCnt","" +  psnUserOVInfoCnt);
	 		modelMap.put("staffFriendInfoCnt","" +  staffFriendInfoCnt);
	 		
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	/**
	 * 나의 부서 적원 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserOVInfo")                            
	 public ModelMap getPsnUserOVInfo(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		PsnUserFrdInfoVO vo = new PsnUserFrdInfoVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	
	 		vo.setUserId(info.getId());
	 		List<PsnUserFrdInfoVO> psnUserFrdInfoVO = person100Service.getPsnUserOVInfo(vo);
	 	
	 		modelMap.put("psnUserFrdInfoVO", psnUserFrdInfoVO);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
		
	 /**
	 * 내게 관심있는 직원 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnInterestedUser")                            
	 public ModelMap getPsnInterestedUser(
			 @RequestParam(value = "rnum", required = true) int rnum,
			 HttpSession session,	 		
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		PsnUserFrdInfoVO vo = new PsnUserFrdInfoVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	
	 		vo.setRnum(rnum);
	 		
	 		vo.setUserId(info.getId());
	 		List<PsnUserFrdInfoVO> interestedUser = person100Service.getPsnInterestedUser(vo);
	 	
	 		modelMap.put("interestedUser", interestedUser);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	/**
	 * 나의 직원 상세  조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserDetailInfo")                            
	 public ModelMap getPsnUserDetailInfo(
	 		@RequestParam(value="userId",required = false) String userId,
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		PsnUserFrdInfoVO vo = new PsnUserFrdInfoVO();
	 		logger.debug("getPsnUserDetailInfo : " + userId);
	 		
	 		vo.setUserId(userId);
	 		
	 		PsnUserFrdInfoVO userDetailInfo = person100Service.getPsnUserDetailInfo(vo);
	 	
	 		modelMap.put("userDetailInfo", userDetailInfo);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	/**
	 * 전송할 수 있는 SMS 건수
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	
	@RequestMapping(value="/getMySmsCnt")                            
	 public ModelMap getMySmsCnt(
	 		HttpSession session,
	 		//@RequestParam(value="userId",required = false) String userId,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	

	 		String mySmsCnt = person100Service.getMySmsCnt(info.getId());
	 		String mySmsAllCnt = person100Service.getMySmsAllCnt(info.getId());
	 		
	 		if( mySmsCnt == null ) mySmsCnt = "0";
	 		if( mySmsAllCnt == null ) mySmsAllCnt = "0";
	 		
	 		logger.info("mySmsCnt : " + mySmsCnt);
	 		logger.info("mySmsAllCnt : " + mySmsAllCnt);

	 		modelMap.put("mySmsCnt","" +  mySmsCnt);
	 		modelMap.put("mySmsAllCnt","" +  mySmsAllCnt);
	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	/**
	 * 로그인 사용자 정보 리턴
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getLoginUserInfo", method = RequestMethod.POST)
	public ModelMap getLoginUserInfo(
		ModelMap modelMap,
		HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try {
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			jsonResult.setSuccess(true);
			modelMap.put("jsonResult", jsonResult);
			modelMap.put("loginUserInfo", JSONUtils.objectToJSON(info));
		}catch(Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
			
	}
	
	/**
	 * SMS전송
	 * @param data
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/sendSms", method = RequestMethod.POST)
	public ModelMap sendSms(
			@RequestParam(value="data" ,required = true) String data,
			ModelMap modelMap, 
			HttpSession session) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		
		try {
			JSONObject jsonObject = JSONObject.fromObject(data);
			String sender = (String)jsonObject.get("sender");
			JSONArray receivers = jsonObject.getJSONArray("receivers");
			String msg = (String)jsonObject.get("msg");
			
			String resultMessage = "";
			
			logger.debug("받는 사람 인원:::::::::"+receivers.size());
			for(int i=0; i<receivers.size(); i++) {
				JSONObject ob= receivers.getJSONObject(i);

				logger.debug("보내는 번호::::::"+sender);
				logger.debug("받는 번호:::::::"+ob.getString("mobile"));
				
				//운영
//				SmsSend sms = new SmsSend(ob.getString("mobile"), msg);
//				long resultCode = sms.sendMessage(ob.getString("mobile"), msg, sender);
				
				//테스트용
//				String tmpMobile = "";
//				if(i == 0) tmpMobile = "01030777227";
//				else if (i==1) tmpMobile = "01023099765";
//				else tmpMobile = "01030777227";
//				
//				SmsSend sms = new SmsSend(tmpMobile, msg);
//				long resultCode = sms.sendMessage(tmpMobile, msg, sender);
				
				String resultMessageSub = "";
				
//				logger.debug("resultCode::::::"+resultCode);
//				if( resultCode > 0 ){
//					  //resultMessageSub = "성공";
//				}else if( resultCode == SmsSend.NEEDS_INIT ){
//					  //resultMessageSub = "초기상태";
//				}else if( resultCode == SmsSend.AUTH_ERROR ){
//					resultMessageSub = "인증실패";
//				}else if( resultCode == SmsSend.DATA_ERROR ){
//					resultMessageSub = "데이터오류";
//				}else if( resultCode == SmsSend.COMM_ERROR ){
//					resultMessageSub = "통신오류";
//				}else{
//					resultMessageSub = "알수없음["+resultCode+"]";
//				}
				
				//SMS 발송 실패
				if(!"".equals(resultMessageSub)) {
					resultMessage += ob.getString("mobile") + ":" + resultMessageSub + "\n";
				}
				//SMS 발송 성공 --> 히스토리 및 발송 가용 건수  업데이트(-1)
				else {
					UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
					//카운트 Update
					person100Service.updateMySmsCnt(info.getId());
					
					//History Insert
					SmsSendHistVO smsSendHist = new SmsSendHistVO();
					smsSendHist.setObjTelno(ob.getString("mobile"));
					smsSendHist.setSendTelno(sender);
					smsSendHist.setSmsConts(msg);
					smsSendHist.setDelYn("N");
					person100Service.insertSmsSendHist(smsSendHist, session);
				}
			}

			if(!"".equals(resultMessage)) {
				modelMap.put("resultMessage", resultMessage);
			}
			jsonResult.setSuccess(true);
			
		}catch(Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	
	
	/**
	 * 전송할 수 있는 SMS 건수 - 1
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/updateMySmsCnt", method = RequestMethod.POST)
		public ModelMap updateMySmsCnt(
			//	@RequestParam(value="userId",required = false) String userId,
				ModelMap modelMap,HttpSession session) throws Exception {
					
			JSONResult jsonResult = new JSONResult();
			
			try {
				
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				person100Service.updateMySmsCnt(info.getId());
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("update.ok"));
			} catch (Exception e) {
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
	
			modelMap.put("jsonResult", jsonResult);
			
			return modelMap;
		}
	
	
	
	/**
	 * 전송한 SMS 정보를 저장한다.
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/insertSmsSendHist", method = RequestMethod.POST)
	public ModelMap insertSmsSendHist(
				@RequestParam(value="data" ,required = true) String data,
				ModelMap 		modelMap,
				HttpSession session
				
	) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
	
		try{	
			
			person100Service.insertSmsSendHist(data, session);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	
	/**
	 * SMS 페이지를 오픈한다.
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="openSmsPage")
		public String openSmsPage(
									@RequestParam(value="smsStep" ,required = true) String smsStep,
									@RequestParam(value="notiId" ,required = false) String notiId,
									HttpSession session,
									ModelMap modelMap
									)
									throws Exception 
	{
		   
		
		   String fowardUrl = "";
		   
		   if(smsStep.equals("1") )
		   {
			   String notiConts = "";
			   if(notiId != null){
				   BbsNotiInfoVO bbsNotiInfoViewForNotiConts = board210Service.getBbsNotiInfoViewForNotiConts(notiId);
				   notiConts = (bbsNotiInfoViewForNotiConts == null)? "":bbsNotiInfoViewForNotiConts.getNotiConts();
			   }
			   modelMap.put("notiConts", CommUtil.stripHTMLTags(notiConts));
			   fowardUrl = ".self/person/sms/smsSend";
		   }
		   else if(smsStep.equals("2") )
		   {
			   fowardUrl = ".self/person/sms/smsSend2";
		   }
		   
		   return fowardUrl;
	}
	
	
		/**********************************************************************************
	  * 카테고리 팝업  chart 
	  * @param type 
	  * @param callback javascript 함수
	  * @param modelMap
	  * @return organization/categoryChartPop.jsp
	  * @throws Exception
	 ***********************************************************************************/
	
	 @RequestMapping(value="/getCategoryChart")                            
	 public String getCategoryChart(
	 		@ModelAttribute CategoryVO categoryVO,
	 		@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:사용자용, 2:관리자용)
	 		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
	 		//@RequestParam(value="callback" ,required = true) String callback,
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	
	 	try {
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		//CategoryVO categoryVO = new CategoryVO();
	 		categoryVO.setId(info.getId());
	 		categoryVO.setKind(kind);
	 		
	 		
	 		List<CategoryVO> list = organizationService.getCategoryList(categoryVO, info.getId(), session);
	 		CategoryVO vo = (CategoryVO)list.get(0);
	 		
	 		logger.debug("Conts : " + vo.getConts());
	 		
	 		modelMap.put("categoryList", vo.getConts());
		
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
	 	modelMap.put("type", type);//카테고리 타입(1:조회용, 2:관리용)
	 	modelMap.put("kind", kind);//카테고리 종류(1:사용자용, 2:관리자용)
	 	
	 	modelMap.put("jsonResult", jsonResult); 
	     return ".person/person/person100HomeMange";
	 }
		
	 /**
	  * 관심직원관리 페이지 오픈
	  * @param modelMap
	  * @return person100HomeMange
	  * @throws Exception
	  * @author crossent
	  */
		@RequestMapping(value = "/openMyMemberStaffPage")
		public String openMyMemberStaffPage(
				ModelMap modelMap, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			
			return ".self/person/person100AtMemberMag";
		
	
		}
		
	
	/**
	 * 알림관리 컨텐츠 목록
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getComCntsInfoList")                            
	 public ModelMap getComCntsInfoList(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		List<ComCntsInfoVO> cntsInfo = person100Service.getComCntsInfoList();
	 	
	 		modelMap.put("cntsInfo", cntsInfo);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	/**
	 * 사용자가 지정한 공지사항 목록
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserNotiList")                            
	 public ModelMap getPsnUserNotiList(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		
	 		List<PsnUserBoardSetVO> userNotiList = person100Service.getPsnUserNotiList(info.getId());
	 	
	 		modelMap.put("userNotiList", userNotiList);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	/**
	 * 사용자가 지정한 게시판 목록
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserCncrSetBoardList")                            
	 public ModelMap getPsnUserCncrSetBoardList(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		
	 		List<PsnUserCncrBoardSetVO> userCncrBoard = person100Service.getPsnUserCncrSetBoardList(info.getId());
	 	
	 		modelMap.put("userCncrBoard", userCncrBoard);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	
	/**
	 * 사용자가 지정한 알림 목록
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserTmlnAlertList")                            
	 public ModelMap getPsnUserTmlnAlertList(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		
	 		List<PsnUserTmlnAlertSetVO> userTmlnAlert = person100Service.getPsnUserTmlnAlertList(info.getId());
	 	
	 		modelMap.put("userTmlnAlert", userTmlnAlert);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	
	
	
	
	/**
	 * 지정가능한 공지 ,게시판 건수
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getAdmTmlnEnvSet")                            
	 public ModelMap getAdmTmlnEnvSet(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		
	 		AdmTmlnEnvSetVO tmlnEnv = person100Service.getAdmTmlnEnvSet();
	 	
	 		modelMap.put("tmlnEnv", tmlnEnv);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	/**
	 * 사용자공지사항게시판설정
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/insertPsnUserNotiSet", method = RequestMethod.POST)
	public ModelMap insertPsnUserNotiSet(
				@RequestParam(value="data" ,required = true) String data,
				ModelMap 		modelMap,
				HttpSession session
				
	) throws Exception {
		
			JSONResult jsonResult = new JSONResult();
	
			try{	
				
				person100Service.insertPsnUserNotiSet(data, session);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
	
	
	/**
	 * 사용자관심게시판설정
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/insertPsnUserCncrBoardSet", method = RequestMethod.POST)
	public ModelMap insertPsnUserCncrBoardSet(
				@RequestParam(value="data" ,required = true) String data,
				ModelMap 		modelMap,
				HttpSession session
				
	) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
	
			try{	
				
				person100Service.insertPsnUserCncrBoardSet(data, session);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
	
		   
	/**
	 * 사용자관심알림설정
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/insertPsnUserTmlnAlertSet", method = RequestMethod.POST)
	public ModelMap insertPsnUserTmlnAlertSet(
				@RequestParam(value="data" ,required = true) String data,
				ModelMap 		modelMap,
				HttpSession session
				
	) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
	
			try{	
				
				person100Service.insertPsnUserTmlnAlertSet(data, session);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
		
	@RequestMapping(value="pageFowarding")
	public String pageFowarding(
								@RequestParam(value="urlKind" ,required = true) String urlKind,
								HttpSession session,
								ModelMap modelMap
								)
								throws Exception {
   	
	   	String fowardUrl = "";
	   	
	   	if(urlKind.equals("1"))
	   	{
	   		fowardUrl = "portalxpert/person/person100AtMemberMag";
	   							 
	   	}					
	   	
	   	else if (urlKind.equals("2") )
	   	{
	   		fowardUrl = ".person/person/person300Home";
	   	}
	   	
	   	else if (urlKind.equals("3") )
	   	{
	   		
	   		fowardUrl = "portalxpert/person/login";
	   	}
   	
  	   return fowardUrl;
   }
	
	/**
     * 개인부서정보관리 - 개인부서정보 조회   
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/personDeptManage")
	public String personDeptManage(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
        return ".self/person/person100DeptManage";

	}
	
	/**
	 * 개인정보관리 - 비밀번로변경
	 * @param model
	 * @param request
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/personPwChangeManage")
	public String personPwChangeManage(ModelMap model,HttpServletRequest request,
			HttpSession session) throws Exception {
		return ".self/person/person100PwChangeManage";
		
	}
	
	/**
     * 개인부서정보관리 - 개인부서정보 조회   
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getComUserDeptInfoList",method = RequestMethod.POST)
	public ModelMap getComUserDeptInfoList(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			ComUserDeptInfoVO comUserDeptInfoVO = new ComUserDeptInfoVO();
			ComUserDeptInfoVO comUserLdDeptInfoVO = new ComUserDeptInfoVO();
	
			//부서정보 - DB : 부서명, 부서안내
			comUserDeptInfoVO = person100Service.getComUserDeptInfoList(info.getId());
			
			//부서정보 - LDAP : 부서명, 주소1, 주소2, 대표전화, 팩스번호, 부서업무
			comUserLdDeptInfoVO = person100Service.getComUserLdDeptInfoList(comUserDeptInfoVO.getOucode());
			
			model.put("userDeptDetailInfo", JSONUtils.objectToJSON(comUserDeptInfoVO));
			model.put("userLdDeptDetailInfo", JSONUtils.objectToJSON(comUserLdDeptInfoVO));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
        return model;

	}
	
	/**
	 * 타부서정보조회   
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/getComUserDeptInfoListEtc",method = RequestMethod.POST)
	public ModelMap getComUserDeptInfoListEtc(
			@RequestParam(value="data", required=true) String data,
			ModelMap model,
			HttpServletRequest request,
			HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			ComUserDeptInfoVO comUserDeptInfoVO = new ComUserDeptInfoVO();
			ComUserDeptInfoVO comUserLdDeptInfoVO = new ComUserDeptInfoVO();
			
			//부서정보 - DB : 부서명, 부서안내
			comUserDeptInfoVO = person100Service.getComUserDeptDetailInfo(data);
			
			//부서정보 - LDAP : 부서명, 주소1, 주소2, 대표전화, 팩스번호, 부서업무
			comUserLdDeptInfoVO = person100Service.getComUserLdDeptInfoList(comUserDeptInfoVO.getOucode());
			
			model.put("userDeptDetailInfo", JSONUtils.objectToJSON(comUserDeptInfoVO));
			model.put("userLdDeptDetailInfo", JSONUtils.objectToJSON(comUserLdDeptInfoVO));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
		return model;
		
	}
	
	/**
	 * 개인정보조회 팝업
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 */
	@RequestMapping(value = "/personMemberInfoView")
	public String personMemberInfoView(ModelMap model,HttpServletRequest request,
			HttpSession session) throws Exception {
		return ".self/person/person100MemberInfoView";
		
	}
	
	/**
     * 개인정보관리 - 개인정보 조회   
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/getComPersonMemberInfo",method = RequestMethod.POST)
	public ModelMap getComPersonMemberInfo(
			@RequestParam(value="data" ,required = true) String data,
			@RequestParam(value="potoSeq" ,required = true) String potoSeq,
			ModelMap model,
			HttpServletRequest request,
            HttpSession session) throws Exception {

		ComUserInfoVO comUserInfoVO = new ComUserInfoVO();
		
		String userId = data;

		comUserInfoVO.setUserId(userId);
		ComUserInfoVO userDetailInfo = new ComUserInfoVO();
		
		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		try{
			if( ! userId.equals("")){
			
				List<ComUserInfoVO> listDetailInfo = person100Service.getComUserInfoList(comUserInfoVO);
				if(listDetailInfo != null && listDetailInfo.size() > 0){
					userDetailInfo = listDetailInfo.get(0);
				}
				
				//개인정보를 ldap에서 가져오게 변경
	
	//			EAMCrypt ec = new EAMCrypt();
	//			String strID = userId;
	//
	//			String I_searchValue  			= "";
	//			I_searchValue = strID;		//SSO
	//			String[] I_searchFactorArr = {"uid"};
	//			String[] I_searchValueArr  = {I_searchValue};
	//
	//			LDAPControlMain cMain = new LDAPControlMain();
	//			
	//			//ldap연계
	//			SiDoChargePrsnValue[] sido = cMain.searchPrsnMain(I_searchFactorArr, I_searchValueArr,"AND");
	//			
	//		    String photoPath       = "";    // 담당업무
	//		    String displayName     = "";    // 성명
	//		    String eTitle          = "";    // 직위명
	//		    String orgFullName     = "";    // 소속 부서
	//		    String ouCode          = "";    // 소속 부서코드
	//		    String telephoneNumber = "";    // 전화 번호
	//		    String mobile          = "";    // 핸드폰 번호
	//		    String fax             = "";    // 팩스 번호
	//		    String homePageURL     = "";    // 홈페이지 URL
	//		    String mail            = "";    // 메일
	//		    String otherMail       = "";    //   전자우편
	//		    String jobTitle        = "";    // 담당업무
	//		    String deptCode        = "";    // 업무분장시 부서코드
	//		    
	//	        photoPath       = sido[0].getPhotoPath();
	//	        displayName     = sido[0].getDisplayName();
	//	        eTitle          = sido[0].getTitle();
	//	        orgFullName     = sido[0].getOrgFullName();
	//	        ouCode          = sido[0].getOuCode();
	//	        telephoneNumber = sido[0].getTelephoneNumber();
	//	        mobile          = sido[0].getMobile();
	//	        fax             = sido[0].getFax();
	//	        homePageURL     = sido[0].getHomePageURL();
	//	        mail            = Util.nvl(sido[0].getMail(),"");
	//	        otherMail       = Util.nvl(sido[0].getOtherMail(),"");
	//			jobTitle        = sido[0].getJobTitle();
	//
	//			userDetailInfo.setUserName(displayName);
	//			userDetailInfo.setTitleName(eTitle);
	//			userDetailInfo.setOrgFullname(orgFullName);
	//			userDetailInfo.setOu(ouCode);
	//			userDetailInfo.setTelePhonenumber(telephoneNumber);
	//			userDetailInfo.setMobile(mobile);
	//			userDetailInfo.setOuFax(fax);
	//			userDetailInfo.setMail(mail);
	//			userDetailInfo.setInterMail(otherMail);
	//			userDetailInfo.setUserJob(jobTitle);
			}
	
			model.put("userDetailInfo", JSONUtils.objectToJSON(userDetailInfo));
			
			/*사용자 이미지정보*/
			String sPathFileExt="";
			jsonResult.setSuccess(true);
	        List<ComUserPotoInfoVO> list = person100Service.getPsnUserPotoInfoListForRollEtc(data, potoSeq);
	        if(list.size() > 0){
		       	 ComUserPotoInfoVO vo = (ComUserPotoInfoVO)list.get(0);
		         sPathFileExt = vo.getFilePath() + vo.getFileName();
		            
		         model.put("user_pic", JSONUtils.objectToJSON(sPathFileExt));
	       }else{
	    	   model.put("user_pic", PortalxpertConfigUtils.getString("person.image.default"));
	       }
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
        return model;

	}
	
	/**
     * 부서정보조회 팝업
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
	@RequestMapping(value = "/personDeptInfoView")
	public String personDeptInfoView(ModelMap model,HttpServletRequest request,
            HttpSession session) throws Exception {
        return ".self/person/person100DeptInfoView";

	}
	
	/**
	 * 직원검색에서 관심직원 추가
	 * @param modelMap
	 * @param data
	 * @param session
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/setFavoriteUserAdd")
	public ModelMap setFavoriteUserAdd(
			ModelMap modelMap,
			@RequestParam(value="data", required=true) String data,
			HttpSession session
			) throws Exception {
		
		UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
		JSONResult jsonResult = new JSONResult();
		PsnUserFrdInfoVO pfVO = new PsnUserFrdInfoVO();
		String userArr = "";
		try {	
			
			JSONObject memObject = JSONObject.fromObject(data);
			
			JSONArray jsonArr = (JSONArray)memObject.get("selectedUserList");		

			for (int i=0; i < jsonArr.size(); i++) {
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
			
				userArr += ",'"+(String)obj.get("friendId")+"'";
			}
			
			pfVO.setUserId(info.getId());
			pfVO.setUserArr(userArr.substring(1));
			
			int chkCount = person100Service.favoriteUserDuplicationCheck(pfVO);
			
			if (chkCount == 0) {
				person100Service.setFavoriteUserAdd(data, info);
				jsonResult.setSuccess(true);
				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
			} else {
				jsonResult.setSuccess(true);
				jsonResult.setMessage("이미 등록된 사용자가 있습니다. \n 확인 후 다시 선택하여 주십시오.");
			}
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	
	/**
	 * 부서정보수정
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/insertComUserDeptInfo", method = RequestMethod.POST)
	public ModelMap insertComUserDeptInfo(
				@RequestParam(value="oucode" ,required = true) String oucode,
				@RequestParam(value="body" ,required = true) String body,
				@RequestParam(value="deptAddr1" ,required = true) String deptAddr1,
				@RequestParam(value="deptAddr2" ,required = true) String deptAddr2,
				@RequestParam(value="deptOuTelephoneNumber" ,required = true) String deptOuTelephoneNumber,
				@RequestParam(value="deptOuFax" ,required = true) String deptOuFax,
				@RequestParam(value="deptOuJobTitle" ,required = true) String deptOuJobTitle,
				ModelMap 		modelMap,
				HttpSession session
				
	) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		
		try{	
			UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
			
			/*
			String strSavePath = PortalxpertConfigUtils.getString("upload.real.dir") + "ouhongbo/"; // 실제 웹 서버에 저장되는 디렉터리를 지정한다.
			String strSaveUrl = PortalxpertConfigUtils.getString("upload.real.web") + "ouhongbo/"; // 실제 웹 서버에 저장되는 디렉터리의 웹 URL 경로를 지정한다.
			
			File file = new File(strSavePath);
			if(!file.exists()){
				file.mkdirs();
			}
			
			String sContent="";
			
//			MimeUtil util = new MimeUtil(); // com.tagfree.util.MimeUtil 생성
//			util.setMimeValue(body); // 작성된 본문 + 포함된 이진 파일의 MIME 값 지정
//			util.setSavePath(strSavePath); // 저장 디렉터리 지정
//			util.setSaveUrl(strSaveUrl); // URL 경로 지정
//			util.setInCharEncoding("iso8859-1");
//			util.setInCharEncoding("euc-kr");
//			util.setOutCharEncoding("euc-kr");
//			util.setOutCharEncoding("iso8859-1");
//			util.setRename(true); // 파일을 저장 시에 새로운 이름을 생성할 것인지를 설정
//			util.setSaveFilePattern("yyyyMMddHHmmss"); //SimpleDateFormat의 형식
//			util.setHtmlRange(Constant.HTML_INNER_BODY); // well-form 변환 시 완전한 html 문서를 만들어냄으로
//			// Inner Body나 Outer Body의 값을 가져오기 위해서는 이를 설정해야 한다.
//			// HTML_INNER_BODY : body 태그 제외한 값
//			// HTML_OUTER_BODY : body 태그까지 합친 값
//			// 기본 값 : <html> ~ </html>
//			util.processDecoding(); // MIME 값의 디코딩 -> 이 때 포함된 파일은 모두 웹 서버에 저장된다.
//			sContent = util.getDecodedHtml(false);
//			sContent = sContent.trim();	
//			sContent = Util.replace(sContent,"<P style=\"FONT-SIZE: 10pt\">&nbsp;</P>","");
//			sContent = Util.replace(sContent,"<P>&nbsp;</P>","");
//			
//			
//			 
//			//부서 V.O 정의
//			LDAPControlMain cMain = new LDAPControlMain();
//			GovOrgValue part = new GovOrgValue();
			String strValue = null;
			String check = "1";

			//V.O에 값세팅 -  수정시 반영되지 않게 하기 위해서 값이 없거나 전페이지에서 넘어오지 않은값들은 반드시 null로 세팅해야한다.

			strValue = null;
//			part.setOu(strValue);
//			part.setTopOUCode(strValue);
//			part.setParentOUCode(strValue);
//			part.setCn(strValue);
//			part.setOuDivision(strValue);
//			part.setOrgFullName(strValue);
//			part.setOuLevel(strValue);
//			part.setOuOrder(strValue);
//			part.setOuSendOutDocYN(strValue);
//			part.setOuReceiveDocYN(strValue);
//			part.setChiefTitle(strValue);
//			part.setDocSystemInfo(strValue);
//			part.setEngOU(strValue);
//			part.setOuPostalCode(strValue);
//			part.setOuHomePage(strValue);
//			part.setOuMail(strValue);
//			part.setIsSidoOnly(strValue);
//			part.setStatus(strValue);
//			part.setSidoAddUserId(strValue);
//			part.setSidoAddDate(strValue);
//			part.setSidoAddTime(strValue);
//			part.setSidoChangeUserId(strValue);
//			part.setSidoChangeDate(strValue);
//			part.setIsVirtual(strValue);
//			part.setVirParentOUCode(strValue);
//			
//			part.setOuCode(oucode);
//			
//			part.setTelephoneNumber(deptOuTelephoneNumber);
//			part.setOuAddress1(deptAddr1);
//			part.setOuAddress2(deptAddr2);
//			part.setOuFax(deptOuFax);
//			part.setOuJobTitle(deptOuJobTitle);
//
//
//			String strDateTime = DateUtil.getDate();
//
//			part.setSidoChangeUserId(info.getId());
//			part.setSidoChangeDate(strDateTime.substring(0,8));
//			part.setSidoChangeTime(strDateTime.substring(8,14));
//
//			String dn = cMain.searchDN(part.getOuCode(), "ORG");
//
//			int resultFlag = cMain.modifyOrg(dn, part);
			
			//부서홍보글 존재여부 조회
			int selectCnt = person100Service.selectUserBuseoHongbo(oucode);

			if(selectCnt == 0){
				person100Service.insertUserBuseoHongbo(oucode, sContent, session);
			}else{
				person100Service.updateUserBuseoHongbo(oucode, sContent, session);
			}
			*/
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	//끝
	
	
	/**
     * 유형별 게시판 프레임
     * @param modelMap
     * @return person/personFrame.jsp
     * @throws Exception
     */
    @RequestMapping(value="/personFrame")
    public String personFrame(
 			ModelMap modelMap, 			
 			HttpSession session
 			)
            throws Exception {
    	
        return ".person/person/personFrame";   
    }
	
    /**
	 * 공지게시판 관리 화면
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	@RequestMapping(value="/getAdmNoticeBoardManage")
    public String getAdmNoticeBoardManage() throws Exception {
    	return ".adm/adm/person/admNoticeBoardManage";
    }
	
	
	/**
	 * 사용자 정보 가져오기 LDAP
	 * @param 
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent 
	 */
	@RequestMapping(value = "/getPsnUserInfoListFromLdap",method = RequestMethod.POST)
	public ModelMap getPsnUserInfoListFromLdap(ModelMap modelMap,HttpServletRequest request,
            HttpSession session) throws Exception {

		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String userId = info.getId();
		JSONResult jsonResult = new JSONResult();
		String[] I_searchFactorArr = {"uid"};
		String[] I_searchValueArr  = {userId};
		
		try {
			
//			LDAPControlMain cMain = new LDAPControlMain();
//			SiDoChargePrsnValue[] sido = cMain.searchPrsnMain(I_searchFactorArr, I_searchValueArr,"AND");
//			modelMap.put("telephoneNumber",sido[0].getTelephoneNumber());//전화번호
//			modelMap.put("mobile",sido[0].getMobile());//휴대폰 
//			modelMap.put("fax",sido[0].getFax());//팩스 
//			modelMap.put("birthday",sido[0].getBirthday());//생년월일
//			modelMap.put("email",sido[0].getMail());//기타메일
//			modelMap.put("interMail",sido[0].getOtherMail());//전자우편
//			modelMap.put("jobTitle",sido[0].getJobTitle());//업무분장 
//			
		
		}catch (Exception e) {
			modelMap.put("telephoneNumber","");
			modelMap.put("mobile","");
			modelMap.put("fax","");
			modelMap.put("birthday","");
			modelMap.put("eMail","");
			modelMap.put("interMail","");
			modelMap.put("jobTitle","");
			
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
        jsonResult.setSuccess(true);
        modelMap.put("jsonResult", jsonResult);
        
        return modelMap;
	}
	
	/**
	 * 패스워드 체크
	 * @param 
	 * @return String
	 * @exception Exception
	 * @auther crossent 
	 */
	@RequestMapping(value="/getUserPwdChkPop")
    public String getUserPwdChkPop() throws Exception {
    	return ".self/person/userPwdChkPop";
    }
	
	/**
	 * 패스워드확인
	 * @param userPwd
	 * @param session
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
    @SuppressWarnings("finally")
	@RequestMapping("/getPwdCheckInfo")
    public ModelMap getPwdCheckInfo(
    		@RequestParam(value="userPwd", required=true) String userPwd,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {

    	JSONResult jsonResult = new JSONResult();
    	boolean checkVal = false;
    	
    	try {

//    		EAMCrypt ec = new EAMCrypt();
//    		String I_searchValue  			= "";
//
//    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
//    		
//    		I_searchValue = info.getId();		//SSO
//
//    		String[] I_searchFactorArr = {"uid"};
//    		String[] I_searchValueArr  = {I_searchValue};
//
//    		LDAPControlMain cMain = new LDAPControlMain();
//    		SiDoChargePrsnValue[] sido = cMain.searchPrsnMain(I_searchFactorArr, I_searchValueArr,"AND");
//
//    		if (!userPwd.equals("") && userPwd.equals(ec.decrypt(sido[0].getEamPassword()))) {
//    			checkVal = true;
//    		}else{
//    			jsonResult.setSuccess(false);
//    		}

			jsonResult.setSuccess(true);
			
		} catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage("사용자가 존재하지 않습니다.");
 			jsonResult.setErrMessage(e.getMessage());
		} finally {

	        modelMap.put("jsonResult", jsonResult);
			modelMap.put("checkVal", checkVal);
	    	return modelMap;
		}

    }
	
	/**
     * 사용자 정보 수정 LDAP 
	 * @param data, session
	 * @return String
	 * @throws Exception
     * @auther crossent 
     */
	@RequestMapping(value = "/updatePsnUserInfoToLdap", method = RequestMethod.POST)
    public ModelMap updatePsnUserInfoToLdap(
    		ModelMap modelMap,
    		String data, HttpSession session) throws Exception {
    	
		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		
    	try{
    		
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");

//        	LDAPControlMain cMain = new LDAPControlMain();
//        	//사용자 V.O 정의
//        	SiDoChargePrsnValue sido = new SiDoChargePrsnValue();
//        	
//    		//V.O에 값세팅 -  수정시 반영되지 않게 하기 위해서 값이 없거나 전페이지에서 넘어오지 않은값들은 반드시 null로 세팅해야한다.
//        	String strValue = null;
//        	sido.setCn(strValue);
//        	sido.setOu(strValue);
//        	sido.setOuCode(strValue);
//        	sido.setParentOUCode(strValue);
//        	sido.setTopOUCode(strValue);
//        	sido.setUid(strValue);
//        	sido.setDisplayName(strValue);
//        	sido.setDescription(strValue);
//        	sido.setSn(strValue);
//        	sido.setUserFullName(strValue);
//        	sido.setGivenname(strValue);
//        	sido.setPositionCode(strValue);
//        	sido.setPosition(strValue);
//        	sido.setTitleCode(strValue);
//        	sido.setTitle(strValue);
//        	sido.setTitleOrPosition(strValue);
//        	sido.setUserCertificate(strValue);
//        	sido.setMail(strValue);
//        	sido.setUserClass(strValue);
//        	sido.setUserType(strValue);
//    		sido.setEamPassword(null);
//        	//sido.setFax(strValue);
//        	sido.setCompanyName(strValue);
//        	sido.setOrgFullName(strValue);
//        	sido.setUserAuthority(strValue);
//        	sido.setOrder(strValue);
//        	sido.setHomePostalAddress1(strValue);
//        	sido.setHomePostalAddress2(strValue);
//        	sido.setHomeFAXPhoneNumber(strValue);
//        	sido.setHomePhone(strValue);
//        	sido.setPager(strValue);
//        	//sido.setJobTitle(strValue);
//        	sido.setNickName(strValue);
//        	sido.setEmpNumber(strValue);
//        	sido.setHomePageURL(strValue);
//        	sido.setHomePostalCode(strValue);
//        	sido.setIsOther(strValue);
//        	sido.setPhotoPath(strValue);
//        	sido.setGender(strValue);
//        	sido.setResiNumber(null);
//        	sido.setWeddingDate(strValue);
//        	sido.setCarNumber(strValue);
//        	sido.setOtherOUCode(strValue);
//        	sido.setOtherOU(strValue);
//        	sido.setStatus(strValue);
//        	//sido.setOtherMail(strValue);
//        	sido.setSidoUserLevel(strValue);
//        	sido.setEPosID(strValue);
//        	sido.setEtitleGrade(strValue);
//        	sido.setSvID(strValue);
//        	sido.setEUserType(null);	//부서계정.
//        	sido.setSid(strValue);											
//        	sido.setPreresiNumber(strValue);			
//        	sido.setMailID(strValue);
//        	sido.setOtherPeriod("");
//
//        	//아래는 sso에서 사용하는 필드
//
//        	//수정시 등록정보는 수정 안함
//        	sido.setSidoAddUserId(null);
//        	sido.setSidoAddDate(null);
//        	sido.setSidoAddTime(null);
//
//        	JSONObject json = JSONObject.fromObject(data);
//			//게시판 기본 테이블
//        	
//    		//전화번호
//    		sido.setTelephoneNumber((String)json.get("telePhonenumber"));
//    		//핸드폰
//    		sido.setMobile((String)json.get("mobile"));
//    		//팩스 
//    		sido.setFax(((String)json.get("ouFax")));
//    		//생년월일
//    		sido.setBirthday((String)json.get("birthday"));
//    		//전자우편
//    		sido.setOtherMail(null);
//    		//기타웹메일
//    		sido.setMail(((String)json.get("interMail")));
//    		//업무분장
//    		sido.setJobTitle(((String)json.get("userJob")));
//
//    		sido.setSidoChangeUserId(info.getId());
//    		String strDateTime = DateUtil.getDate();
//    		sido.setSidoChangeDate(strDateTime.substring(0,8));
//    		sido.setSidoChangeTime(strDateTime.substring(8,14));
//    		
//    		int res = cMain.modifyPrsn(info.getId(),sido);
//    		
// 			if(res == 0){
// 				//DB ****************************************************************************************
//	 			
//	 			DivChgVO divChgVO = new DivChgVO();
//	 			divChgVO.setTelephoneNumber((String)json.get("telePhonenumber"));
//	 			divChgVO.setJobTitle((String)json.get("userJob"));
//	 			divChgVO.setUserId(info.getId());
//	 			
//	 			divChgService.updateJobTitle(divChgVO);
// 			}else{
// 				jsonResult.setSuccess(false);
// 			}
		} catch (Exception e) {	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
        modelMap.put("jsonResult", jsonResult);
        
        return modelMap;
    }
	
	
	/**
     * 타임라인 환경설정 화면
     * @param TmlnEnvInfoVO
     * @param ModelMap
     * @return String
     * @throws Exception
     */
	@RequestMapping(value = "/getTmlnEnv")
	public String getTmlnEnv(
							TmlnEnvInfoVO tmlnEnvInfoVO,
							ModelMap modelMap
							) throws Exception {
		
		TmlnEnvInfoVO tmlnEnvInfo = person100Service.getTmlnEnv(tmlnEnvInfoVO);
		modelMap.put("tmlnEnvInfo", tmlnEnvInfo);

		return ".adm/person/person100TmlnEnv";

	}
	
	/**
     * 타임라인 환경설정 수정 
     * @param TmlnEnvInfoVO
     * @param ModelMap
     * @return ModelMap
     * @throws Exception
     */
	@RequestMapping(value = "/updateTmlnEnv", method = RequestMethod.POST)
	public ModelMap updateTmlnEnv(
								TmlnEnvInfoVO tmlnEnvInfoVO,
								ModelMap modelMap,
								HttpSession session
								) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
		
		try {
			person100Service.updateTmlnEnv(tmlnEnvInfoVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}   

	/**
	 * 타임라인 관리 리스트
	 * @param tmlnEnvInfoVO
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getTmlnEnvList")
	public String getTmlnEnvList(@ModelAttribute TmlnEnvInfoVO tmlnEnvInfoVO ,ModelMap modelMap ,HttpSession session) throws Exception {
		
		return ".adm/adm/person/admTmlnEnvList";
	}	
}
