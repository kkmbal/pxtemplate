package portalxpert.adm.sys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import portalxpert.adm.sys.sc.AdmSysMainMovieService;
import portalxpert.adm.sys.vo.AdmSysBbsNotiApndFileVO;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value="adm/sys")
public class AdmSysMainMovieController {
	
	/** AdmSysMainMovieService */
    @Resource(name = "admSysMainMovieService")
    private AdmSysMainMovieService admSysMainMovieService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
   
    private final static Logger logger = LoggerFactory.getLogger(AdmSysMainMovieController.class);
   
    /**
	 * 동영상관리 목록
	 * @param AdmSysBbsNotiApndFileVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmSysMainMovieList")
	public String getAdmSysMainMovieList(
								AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	/** pageing setting START */
    	admSysBbsNotiApndFileVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admSysBbsNotiApndFileVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admSysBbsNotiApndFileVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admSysBbsNotiApndFileVO.getPageUnit());
		paginationInfo.setPageSize(admSysBbsNotiApndFileVO.getPageSize());
		admSysBbsNotiApndFileVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admSysBbsNotiApndFileVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admSysBbsNotiApndFileVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		if(admSysBbsNotiApndFileVO.getApndFileTp()==null || admSysBbsNotiApndFileVO.getApndFileTp().equals("")){
			admSysBbsNotiApndFileVO.setApndFileTp("030");
		}
		if(admSysBbsNotiApndFileVO.getRemark()==null || admSysBbsNotiApndFileVO.getRemark().equals("")){
			admSysBbsNotiApndFileVO.setRemark("NEWEST");
		}
		
		int mainMovieCnt = admSysMainMovieService.getAdmSysMainMovieCnt(admSysBbsNotiApndFileVO);
		
		paginationInfo.setTotalRecordCount(mainMovieCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmSysBbsNotiApndFileVO> mainMovieList = admSysMainMovieService.getAdmSysMainMovieList(admSysBbsNotiApndFileVO);
    	modelMap.put("mainMovieList", mainMovieList);
    	
    	AdmSysBbsNotiApndFileVO pSearch = new AdmSysBbsNotiApndFileVO();
    	pSearch.setSearchCondition(admSysBbsNotiApndFileVO.getSearchCondition());
    	pSearch.setSearchKeyword(admSysBbsNotiApndFileVO.getSearchKeyword());
    	pSearch.setCurrentRecordCount(mainMovieList.size());
    	pSearch.setApndFileTp(admSysBbsNotiApndFileVO.getApndFileTp());
    	modelMap.put("pSearch", pSearch);
    	
   		return ".adm/adm/sys/admSysMainMovieList";
	}
 	
 	/**
     * 동영상관리 수정(추천/추천해지)
     * @param AdmSysBbsNotiApndFileVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmSysMainMovie", method = RequestMethod.POST)
    public ModelMap updateAdmSysMainMovie(
    								AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO,
    								HttpServletRequest request,
    								ModelMap modelMap,
    								HttpSession session
    								) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admSysMainMovieService.updateAdmSysMainMovie(request, session);
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
