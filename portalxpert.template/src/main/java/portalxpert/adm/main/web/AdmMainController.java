package portalxpert.adm.main.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.adm.main.sc.AdmMainService;
import portalxpert.adm.main.vo.AdmMainVO;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;

@Controller
@RequestMapping(value="adm/main")
public class AdmMainController {
	
	@Resource(name="admMainService")
	public AdmMainService admMainService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
   
    private final static Logger logger = LoggerFactory.getLogger(AdmMainController.class);
	
    /**
     * 관리자 메인홈관리 프레임
     * @return
     * @throws Exception
     * @author crossent
     */
	@RequestMapping(value="/admMainFrame")
	public String admMainFrame() throws Exception {
		return ".adm/adm/main/admMainFrame";
	}
	
	/**
	 * 해야할 일 관리 화면
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	@RequestMapping(value="/getAdmToDoManage")
    public String getAdmToDoManage() throws Exception {
    	return ".adm/adm/main/admToDoManage";
    }
	 
	/**
	 * 포틀릿 관리 화면
	 * @return
	 * @throws Exception
     * @author crossent
	 */
    @RequestMapping(value="/getAdmPortletManage")
    public String getAdmPortletManage() throws Exception {
    	return ".adm/adm/main/admPortletManage";
    }
    
    /**
     * 유용한 툴 관리 화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getAdmUsefulToolManage")
    public String getAdmUsefulToolManage() throws Exception {
    	return ".adm/adm/main/admUsefulToolManage";
    }
    
    /**
     * 최근 게시물 관리 화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getAdmRecentPostManage")
    public String getAdmRecentPostManage() throws Exception {
    	return ".adm/adm/main/admRecentPostManage";
    }
    
    /**
     * 업무데스크 관리 화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getAdmWorkDeskManage")
    public String getAdmWorkDeskManage() throws Exception {
    	return ".adm/adm/main/admWorkDeskManage";
    }

    /**
     * 컨텐츠 조회
     * @param modelMap
     * @param type
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getContentList")
    public ModelMap getContentList(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String type
			) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		
		AdmMainVO amVO = new AdmMainVO();
		
		amVO.setCntsTp(type);
		
		try {
			List unselectList = admMainService.getUnselectContentList(amVO);
			List mandatoryList = admMainService.getMandatoryContentList(amVO);
			List defaultList = admMainService.getDefaultContentList(amVO);
			
			if (unselectList.size() > 0) {
				modelMap.put("unselectList", JSONUtils.objectToJSON(unselectList));
			} else {
				modelMap.put("unselectList", "[]");
			}
			
			if (mandatoryList.size() > 0) {
				modelMap.put("mandatoryList", JSONUtils.objectToJSON(mandatoryList));
			} else {
				modelMap.put("mandatoryList", "[]");
			}
			
			if (defaultList.size() > 0) {
				modelMap.put("defaultList", JSONUtils.objectToJSON(defaultList));
			} else {
				modelMap.put("defaultList", "[]");
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
     * 컨텐츠 설정
     * @param modelMap
     * @param json
     * @param type
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/setSelectContent")
    public ModelMap setSelectContent(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String json,
    		@RequestParam(value="type", required=true) String type,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	AdmMainVO amVO = new AdmMainVO();
    	
    	amVO.setUserId(info.getId());
    	amVO.setUserName(info.getName());
    	
    	amVO.setCntsTp(type);
    	
    	try {
    		admMainService.setSelectContent(json, amVO);
    		
	    	jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		} catch (Exception e) {	 	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;  	
    }
    

    /**
     * 최근게시물, 공지게시판 설정 조회
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getAdmBoardList")
    public ModelMap getAdmBoardList(
    		ModelMap modelMap,
    		@RequestParam(value="type", required=true) String type
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	AdmMainVO amVO = new AdmMainVO();
		
		amVO.setBoardUseDiv(type);
    	
    	try {
    		List mandatoryList = admMainService.getMandatoryBoardList(amVO);    
    		List defaultList = admMainService.getDefaultBoardList(amVO); 
    		
    		if (mandatoryList.size() > 0) {
				modelMap.put("mandatoryList", JSONUtils.objectToJSON(mandatoryList));
			} else {
				modelMap.put("mandatoryList", "[]");
			}
			
			if (defaultList.size() > 0) {
				modelMap.put("defaultList", JSONUtils.objectToJSON(defaultList));
			} else {
				modelMap.put("defaultList", "[]");
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
     * 최근게시물, 공지게시판 설정 
     * @param modelMap
     * @param json
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/setSelectBoard")
    public ModelMap setSelectBoard(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String json,
    		@RequestParam(value="type", required=true) String type,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	AdmMainVO amVO = new AdmMainVO();
    	
    	amVO.setUserId(info.getId());
    	amVO.setUserName(info.getName());
    	amVO.setBoardUseDiv(type);
    	
    	try {
    		admMainService.setSelectBoard(json, amVO);
    		
	    	jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		} catch (Exception e) {	 	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;  	
    }
    
    /**
     * 업무데스크 조회
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getWorkDeskList")
    public ModelMap getWorkDeskList(
    		ModelMap modelMap
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try {
    		List categoryList = admMainService.getWorkDeskCategoryList();
    		List resultList = admMainService.getWorkDeskList();
    		int count = admMainService.getWorkDeskCnt();
			
			if (categoryList.size() > 0) {
				modelMap.put("categoryList", JSONUtils.objectToJSON(categoryList));
			} else {
				modelMap.put("categoryList", "[]");
			}
			
			if (resultList.size() > 0) {
				modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
			} else {
				modelMap.put("resultList", "[]");
			}
			
    		modelMap.put("count", count);
    	} catch (Exception e) {
	 		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}  
    	
    	modelMap.put("jsonResult", jsonResult);    	
    	return modelMap;
    }

    /**
     * 업무데스크 설정
     * @param modelMap
     * @param json
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/setSelectWorkDesk")
    public ModelMap setSelectWorkDesk(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String json,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	AdmMainVO amVO = new AdmMainVO();
    	
    	amVO.setUserId(info.getId());
    	amVO.setUserName(info.getName());
    	
    	try {
    		admMainService.setSelectWorkDesk(json, amVO);
    		
	    	jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		} catch (Exception e) {	 
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;
    }
}
