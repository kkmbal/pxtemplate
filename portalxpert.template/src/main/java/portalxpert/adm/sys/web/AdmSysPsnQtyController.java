package portalxpert.adm.sys.web;

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

import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.sys.sc.AdmSysPsnQtyService;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value="adm/sys")
public class AdmSysPsnQtyController {
	
	/** AdmSysPsnQtyService */
    @Resource(name = "admSysPsnQtyService")
    private AdmSysPsnQtyService admSysPsnQtyService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
   
    private final static Logger logger = LoggerFactory.getLogger(AdmSysPsnQtyController.class);
   
    /**
	 * 개인홈쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmSysPsnQtyList")
	public String getAdmSysPsnQtyList(
								AdmSysPsnUserInfoVO admSysPsnUserInfoVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	/** pageing setting START */
    	admSysPsnUserInfoVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admSysPsnUserInfoVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admSysPsnUserInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admSysPsnUserInfoVO.getPageUnit());
		paginationInfo.setPageSize(admSysPsnUserInfoVO.getPageSize());
		admSysPsnUserInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admSysPsnUserInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admSysPsnUserInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int psnQtyListCnt = admSysPsnQtyService.getAdmSysPsnQtyCnt(admSysPsnUserInfoVO);
		
		paginationInfo.setTotalRecordCount(psnQtyListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmSysPsnUserInfoVO> psnQtyList = admSysPsnQtyService.getAdmSysPsnQtyList(admSysPsnUserInfoVO);
    	modelMap.put("psnQtyList", psnQtyList);
    	
    	AdmBoardNotiInfoVO pSearch = new AdmBoardNotiInfoVO();
    	pSearch.setSearchCondition(admSysPsnUserInfoVO.getSearchCondition());
    	pSearch.setSearchKeyword(admSysPsnUserInfoVO.getSearchKeyword());
    	pSearch.setCurrentRecordCount(psnQtyList.size());
    	modelMap.put("pSearch", pSearch);
    	
   		return ".adm/adm/sys/admSysPsnQtyList";
	}
 	
 	/**
     * 개인홈쿼터관리 쿼터수정
     * @param AdmSysPsnUserInfoVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmSysPsnQty", method = RequestMethod.POST)
    public ModelMap updateAdmSysPsnQty(
    										AdmSysPsnUserInfoVO admSysPsnUserInfoVO,
    										ModelMap modelMap,
    										HttpSession session
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admSysPsnQtyService.updateAdmSysPsnQty(admSysPsnUserInfoVO, session);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
  	
}
