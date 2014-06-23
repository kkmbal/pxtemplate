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

import portalxpert.adm.gen.sc.AdmGenLinkManageService;
import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="adm/gen")
public class AdmGenLinkManageController {
	
	/** AdmGenLinkManageService */
    @Resource(name = "admGenLinkManageService")
    private AdmGenLinkManageService admGenLinkManageService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    private final static Logger logger = LoggerFactory.getLogger(AdmGenLinkManageController.class);
    
    /**
	 * 링크분류조회 목록
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenSysLinkCtlgList")
	public String getAdmGenSysLinkCtlgList(
								AdmGenLinkVO admGenLinkVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	
    	/** pageing setting START */
    	admGenLinkVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admGenLinkVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admGenLinkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admGenLinkVO.getPageUnit());
		paginationInfo.setPageSize(admGenLinkVO.getPageSize());
		admGenLinkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admGenLinkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admGenLinkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int sysLinkCtlgListCnt = admGenLinkManageService.getAdmGenSysLinkCtlgCnt(admGenLinkVO);
		
		paginationInfo.setTotalRecordCount(sysLinkCtlgListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmGenLinkVO> sysLinkCtlgList = admGenLinkManageService.getAdmGenSysLinkCtlgList(admGenLinkVO);
    	modelMap.put("sysLinkCtlgList", sysLinkCtlgList);
    	
   		return ".adm/adm/gen/admGenSysLinkCtlgList";
	}
    
    /**
	 * 링크분류조회 단건
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenSysLinkCtlg")
	public String getAdmGenSysLinkCtlg(
									AdmGenLinkVO admGenLinkVO,
									ModelMap modelMap
									)
									throws Exception {
	   
    	AdmGenLinkVO sysLinkCtlg = admGenLinkManageService.getAdmGenSysLinkCtlg(admGenLinkVO);
    	modelMap.put("sysLinkCtlg", sysLinkCtlg);
    	
   		return ".self/adm/gen/admGenSysLinkCtlgPopup";
	}
    
    /**
     * 링크분류 등록
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/insertAdmGenSysLinkCtlg", method = RequestMethod.POST)
    public ModelMap insertAdmGenSysLinkCtlg(
    								AdmGenLinkVO admGenLinkVO,
    								ModelMap modelMap,
    								HttpSession session
    								) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenLinkManageService.insertAdmGenSysLinkCtlg(admGenLinkVO,session);
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
     * 링크분류 수정
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmGenSysLinkCtlg", method = RequestMethod.POST)
    public ModelMap updateAdmGenSysLinkCtlg(
    								AdmGenLinkVO admGenLinkVO,
    								ModelMap modelMap,
    								HttpSession session
    								) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenLinkManageService.updateAdmGenSysLinkCtlg(admGenLinkVO,session);
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
     * 링크분류 삭제
     * @param admGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/deleteAdmGenSysLinkCtlg", method = RequestMethod.POST)
    public ModelMap deleteAdmGenSysLinkCtlg(
    								AdmGenLinkVO admGenLinkVO,
    								ModelMap modelMap,
    								HttpSession session
    								) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admGenLinkManageService.deleteAdmGenSysLinkCtlg(admGenLinkVO,session);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
    
    
    
    
    
 	
    /**
	 * 링크조회 목록
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenSysLinkList")
	public String getAdmGenSysLinkList(
										AdmGenLinkVO admGenLinkVO,
										ModelMap modelMap
										) throws Exception {
    	
    	/** pageing setting START */
    	admGenLinkVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admGenLinkVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admGenLinkVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admGenLinkVO.getPageUnit());
		paginationInfo.setPageSize(admGenLinkVO.getPageSize());
		admGenLinkVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admGenLinkVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admGenLinkVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int sysLinkListCnt = admGenLinkManageService.getAdmGenSysLinkListCnt(admGenLinkVO);
		
		paginationInfo.setTotalRecordCount(sysLinkListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmGenLinkVO> sysLinkList = admGenLinkManageService.getAdmGenSysLinkList(admGenLinkVO);
    	List<AdmGenLinkVO> sysLinkCatList = admGenLinkManageService.getAdmGenSysLinkCatList(admGenLinkVO);
    	modelMap.put("sysLinkList", sysLinkList);
    	modelMap.put("sysLinkCatList", sysLinkCatList);
    	
    	if(admGenLinkVO.getLinkCatId() != null){
    		modelMap.put("pLINK_CAT_ID", admGenLinkVO.getLinkCatId());
    	}
    	
   		return ".adm/adm/gen/admGenSysLinkList";
	}
    
    /**
	 * 링크 단 건 조회
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenSysLink")
   	public String getAdmGenSysLink(
   								AdmGenLinkVO admGenLinkVO,
   								ModelMap modelMap
   								) throws Exception {
    	
    	AdmGenLinkVO sysLink = admGenLinkManageService.getAdmGenSysLink(admGenLinkVO);
    	modelMap.put("sysLink", sysLink);
    	
    	return ".self/adm/gen/admGenSysLinkPopup";
    }
    
    /**
	 * 링크 등록
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/insertAdmGenSysLink", method = RequestMethod.POST)
    public ModelMap insertAdmGenSysLink(
									AdmGenLinkVO admGenLinkVO,
									ModelMap modelMap,
									HttpSession session
									) throws Exception {
    	
		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenLinkManageService.insertAdmGenSysLink(admGenLinkVO,session);
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
	 * 링크 수정
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/updateAdmGenSysLink", method = RequestMethod.POST)
    public ModelMap updateAdmGenSysLink(
										AdmGenLinkVO admGenLinkVO,
										ModelMap modelMap,
										HttpSession session
										) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenLinkManageService.updateAdmGenSysLink(admGenLinkVO,session);
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
	 * 링크 삭제
	 * @param AdmGenLinkVO
	 * @param AdmGenLinkVO
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/deleteAdmGenSysLink", method = RequestMethod.POST)
    public ModelMap deleteAdmGenSysLink(
										AdmGenLinkVO admGenLinkVO,
										ModelMap modelMap,
										HttpSession session
										) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenLinkManageService.deleteAdmGenSysLink(admGenLinkVO,session);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
		
		}catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
    
    
    
    
    
    
    /**
	 * 메인화면에 표시되는 링크 설정을 위한 조회
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmGenMainLinkList")
	public String getAdmGenMainLinkList(
								AdmGenLinkVO admGenLinkVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	List<AdmGenLinkVO> mainLinkList = admGenLinkManageService.getAdmGenMainLinkList(admGenLinkVO);
    	modelMap.put("mainLinkList", mainLinkList);
    	
   		return ".adm/adm/gen/admGenMainLinkList";
	}
    
    /**
     * 메인화면에 표시되는 링크 설정 (저장)
     * @param AdmGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmGenMainLink", method = RequestMethod.POST)
    public ModelMap updateAdmGenMainLink(
    									AdmGenLinkVO admGenLinkVO,
    									ModelMap modelMap,
    									HttpSession session
    									) throws Exception {
 		
 		
 		JSONResult jsonResult = new JSONResult();
 		
		try{
			admGenLinkManageService.updateAdmGenMainLink(admGenLinkVO,session);
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
     * 메인화면에 표시되는 링크 설정 (초기화)
     * @param AdmGenLinkVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmGenMainLinkInit", method = RequestMethod.POST)
    public ModelMap updateAdmGenMainLinkInit(
    									AdmGenLinkVO admGenLinkVO,
    									ModelMap modelMap,
    									HttpSession session
    									) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 		
		try{
			admGenLinkManageService.updateAdmGenMainLinkInit(admGenLinkVO,session);
			jsonResult.setMessage("초기화되었습니다");
			
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
}
