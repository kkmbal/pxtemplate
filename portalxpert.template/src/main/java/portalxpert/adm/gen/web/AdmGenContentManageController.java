package portalxpert.adm.gen.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import portalxpert.adm.gen.sc.AdmGenContentManageService;
import portalxpert.adm.gen.vo.AdmGenContentManageVO;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="adm/gen")
public class AdmGenContentManageController {
	
	/** AdmGenContentManageService */
    @Resource(name = "admGenContentManageService")
    private AdmGenContentManageService admGenContentManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    private final static Logger logger = LoggerFactory.getLogger(AdmGenContentManageController.class);
    
    /**
	 * 콘텐츠조회 목록
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenContentManageList")
	public String getAdmGenContentManageList(
								AdmGenContentManageVO admGenContentManageVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	/** pageing setting START */
    	admGenContentManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admGenContentManageVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admGenContentManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admGenContentManageVO.getPageUnit());
		paginationInfo.setPageSize(admGenContentManageVO.getPageSize());
		admGenContentManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admGenContentManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admGenContentManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int genContentListCnt = admGenContentManageService.getAdmGenContentManageCnt(admGenContentManageVO);
		
		paginationInfo.setTotalRecordCount(genContentListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmGenContentManageVO> genContentList = admGenContentManageService.getAdmGenContentManageList(admGenContentManageVO);
    	modelMap.put("genContentList", genContentList);
    	
   		return ".adm/adm/gen/admGenContentManageList";
	}
    
    /**
	 * 콘텐츠조회 단건
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenContentManage")
	public String getAdmGenContentManage(
									AdmGenContentManageVO admGenContentManageVO,
									ModelMap modelMap
									)
									throws Exception {
    	
    	AdmGenContentManageVO genContent = null;
    	
    	if(admGenContentManageVO.getPageType().equals("I")){
    		genContent = new AdmGenContentManageVO();
    		
    	}else if(admGenContentManageVO.getPageType().equals("U")){
	    	genContent = admGenContentManageService.getAdmGenContentManage(admGenContentManageVO);
	    	modelMap.put("genContent", genContent);
	    }
	    
	    genContent.setPageType(admGenContentManageVO.getPageType());
	    modelMap.put("genContent", genContent);
	    
   		return ".self/adm/gen/admGenContentManageEditPopup";
	}
    
    /**
     * 콘텐츠 등록
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/insertAdmGenContentManage", method = RequestMethod.POST)
    public ModelMap insertAdmGenContentManage(
    								AdmGenContentManageVO admGenContentManageVO,
    								ModelMap modelMap,
    								HttpSession session
    								) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenContentManageService.insertAdmGenContentManage(admGenContentManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("save.ok"));
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
 			jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
 	
 	/**
     * 콘텐츠 수정
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmGenContentManage", method = RequestMethod.POST)
    public ModelMap updateAdmGenContentManage(
    										AdmGenContentManageVO admGenContentManageVO,
    										ModelMap modelMap,
    										HttpSession session
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenContentManageService.updateAdmGenContentManage(admGenContentManageVO, session);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
    
 	/**
     * 콘텐츠 삭제
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/deleteAdmGenContentManage", method = RequestMethod.POST)
    public ModelMap deleteAdmGenContentManage(
    										AdmGenContentManageVO admGenContentManageVO,
    										ModelMap modelMap
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenContentManageService.deleteAdmGenContentManage(admGenContentManageVO);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
    
}
