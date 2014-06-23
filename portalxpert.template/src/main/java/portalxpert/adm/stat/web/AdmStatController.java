/**
 * 
 */
package portalxpert.adm.stat.web;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import portalxpert.adm.stat.sc.AdmStatService;
import portalxpert.adm.stat.vo.AdmStatBBSVO;
import portalxpert.adm.stat.vo.AdmStatSearchVO;
import portalxpert.adm.stat.vo.AdmStatSurveyVO;
import portalxpert.adm.stat.vo.AdmStatUseVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @author yoDJ
 *
 */
@Controller
@RequestMapping(value = "adm/stat")
public class AdmStatController {
	
	/** AdmStatService */
	@Resource(name = "admStatService")
	private AdmStatService admStatService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmStatController.class);
	
	/**
	 * 게시판 통계
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmBbsStatistics")
	public String getAdmBbsStatistics(@ModelAttribute("admStatSearchVO") AdmStatSearchVO searchForm ,ModelMap modelMap) throws Exception{
		
		String fromDt = (searchForm.getsFromDt().replaceAll("-", ""));
		String toDt = (searchForm.getsToDt().replaceAll("-", ""));
//		if(fromDt==null || fromDt.equals("")) fromDt = CitySchUtil.NowDayNPMDisPlay(CitySchUtil.getDate() ,30 ,"");
//		if(toDt==null || toDt.equals("")) toDt = CitySchUtil.getDate();		
		searchForm.setsFromDt(fromDt);
		searchForm.setsToDt(toDt);
		if(searchForm.getBrdId()==null || searchForm.getBrdId().equals("")) searchForm.setBrdId("ALL");
		//if(searchForm.getSearchCondition()==null || searchForm.getSearchCondition().equals("")) searchForm.setSearchCondition("VIEW_CNT");
		
		AdmStatBBSVO bbsInfo = new AdmStatBBSVO();
		List<AdmStatBBSVO> viewTopList = null;
		if(searchForm.getSearchCondition()==null || searchForm.getSearchCondition().equals("")){
			searchForm.setSearchCondition("VIEW_CNT");			
		}else{
			if(searchForm.getBrdId()!=null && !searchForm.getBrdId().equals("") && !searchForm.getBrdId().equals("ALL")){
				searchForm.setBrdIds(searchForm.getBrdId().split(";"));				
			}
			 
			bbsInfo = admStatService.getAdmBbsInfoStatistics(searchForm);
			viewTopList = admStatService.getAdmBbsTopList(searchForm);
		}
		
		modelMap.put("searchForm", searchForm);
		modelMap.put("bbsInfo", bbsInfo);
		modelMap.put("viewTopList", viewTopList);
		
		return ".adm/adm/stat/admBbsStatistics";
	}
	
	/**
	 * 개인게시판 통계
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmPBbsStatistics")
	public String getAdmPBbsStatistics(@ModelAttribute AdmStatSearchVO searchForm ,ModelMap modelMap) throws Exception{
		String fromDt = (searchForm.getsFromDt().replaceAll("-", ""));
		String toDt = (searchForm.getsToDt().replaceAll("-", ""));
//		if(fromDt==null || fromDt.equals("")) fromDt = CitySchUtil.NowDayNPMDisPlay(CitySchUtil.getDate() ,30 ,"");
//		if(toDt==null || toDt.equals("")) toDt = CitySchUtil.getDate();		
		searchForm.setsFromDt(fromDt);
		searchForm.setsToDt(toDt);
		if(searchForm.getBrdId()==null || searchForm.getBrdId().equals("")) searchForm.setBrdId("ALL");
		
		AdmStatBBSVO bbsInfo = new AdmStatBBSVO();
		List<AdmStatBBSVO> viewTopList = null;
		if(searchForm.getSearchCondition()==null || searchForm.getSearchCondition().equals("")){
			searchForm.setSearchCondition("VIEW_CNT");			
		}else{		
			//logger.info("%%%%%%%%%%%%%%%%%%%%%%searchForm.getsFromDt["+searchForm.getsFromDt()+"]searchForm.getsToDt()["+searchForm.getsToDt()+"]getOwnrId()["+searchForm.getOwnrId()+"]");
			bbsInfo = admStatService.getAdmPBbsInfoStatistics(searchForm);
			viewTopList = admStatService.getAdmPBbsTopList(searchForm);
		}
		
		modelMap.put("searchForm", searchForm);
		modelMap.put("bbsInfo", bbsInfo);
		modelMap.put("viewTopList", viewTopList);
		
		return ".adm/adm/stat/admPBbsStatistics";
	}
	
	/**
	 * 개인게시판 게시판 선택 팝업창
	 * @param searchForm
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmPBbsPopup")
	public String getAdmPBbsPopup(@ModelAttribute AdmStatSearchVO searchForm ,ModelMap modelMap) throws Exception{		
		//logger.info("%%%%%%%%%%%%%%%%%%%%%%searchForm.getsFromDt["+searchForm.getsFromDt()+"]searchForm.getsToDt()["+searchForm.getsToDt()+"]getOwnrId()["+searchForm.getOwnrId()+"]");
		/** pageing setting START */
		searchForm.setPageUnit(propertiesService.getInt("pageUnit"));
		searchForm.setPageSize(propertiesService.getInt("pageSize"));
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchForm.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchForm.getPageUnit());
		paginationInfo.setPageSize(searchForm.getPageSize());
		searchForm.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchForm.setLastIndex(paginationInfo.getLastRecordIndex());
		searchForm.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int pBbsListCnt = admStatService.getAdmPBbsPopupCnt(searchForm);

		paginationInfo.setTotalRecordCount(pBbsListCnt);
		modelMap.put("paginationInfo", paginationInfo);
		/** pageing setting END */
		
		List<AdmStatBBSVO> pBbsList = admStatService.getAdmPBbsPopup(searchForm);
		
		modelMap.put("searchForm", searchForm);
		modelMap.put("pBbsList", pBbsList);
		
		return ".self/adm/stat/pBbsList";
	}
	
	/**
	 * 이용현황 통계
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmUseStatistics")
	public String getAdmUseStatistics(@ModelAttribute AdmStatSearchVO searchForm ,ModelMap modelMap) throws Exception{
		String fromDt = (searchForm.getsFromDt().replaceAll("-", ""));
		String toDt = (searchForm.getsToDt().replaceAll("-", ""));
//		if(fromDt==null || fromDt.equals("")) fromDt = CitySchUtil.NowDayNPMDisPlay(CitySchUtil.getDate() ,30 ,"");
//		if(toDt==null || toDt.equals("")) toDt = CitySchUtil.getDate();	
		searchForm.setsFromDt(fromDt);
		searchForm.setsToDt(toDt);
		
//		if(searchForm.getSearchCondition()==null || searchForm.getSearchCondition().equals("")) searchForm.setSearchCondition("tag");
		if(searchForm.getInDelYn()==null || searchForm.getInDelYn().equals("")) searchForm.setInDelYn("Y");
		
		//logger.debug("%%%%%%%%%%%%%%%%%%%%%%searchForm.getsFromDt["+searchForm.getsFromDt()+"]searchForm.getsToDt()["+searchForm.getsToDt()+"]searchForm.getBrdId()["+searchForm.getBrdId()+"]");
		List<AdmStatUseVO> useStatList = admStatService.getAdmUseStatList(searchForm);
		
		modelMap.put("searchForm", searchForm);
		modelMap.put("useStatList", useStatList);
		
		return ".adm/adm/stat/admUseStatistics";
	}
	
	/**
	 * 메신저통계
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmMessengerStatistics")
	public String getAdmMessengerStatistics(@ModelAttribute AdmStatSearchVO searchForm ,ModelMap modelMap) throws Exception{
		String fromDt = (searchForm.getsFromDt().replaceAll("-", ""));
		String toDt = (searchForm.getsToDt().replaceAll("-", ""));
//		if(fromDt==null || fromDt.equals("")) fromDt = CitySchUtil.NowDayNPMDisPlay(CitySchUtil.getDate() ,30 ,"");
//		if(toDt==null || toDt.equals("")) toDt = CitySchUtil.getDate();
		searchForm.setsFromDt(fromDt);
		searchForm.setsToDt(toDt);
		if(searchForm.getSearchCondition()==null || searchForm.getSearchCondition().equals("")) searchForm.setSearchCondition("D");
		
		//logger.debug("%%%%%%%%%%%%%%%%%%%%%%searchForm.getsFromDt["+searchForm.getsFromDt()+"]searchForm.getsToDt()["+searchForm.getsToDt()+"]searchForm.getOrgCd()["+searchForm.getOrgCd()+"]");		
		List<AdmStatBBSVO> msnList = admStatService.getAdmMessengerStatistics(searchForm);
		
		modelMap.put("searchForm", searchForm);
		modelMap.put("msnList", msnList);
		return ".adm/adm/stat/admMessengerStatistics";
	}
	
	/**
	 * 접속자수 조회
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmConnectStatistics")
	public String getAdmConnectStatistics(ModelMap modelMap) throws Exception{
		return ".adm/adm/stat/admConnectStatistics";
	}
	
	/**
	 * 설문 통계
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmSurveyStatistics")
	public String getAdmSurveyStatistics(
			AdmStatSurveyVO admStatSurveyVO,
			ModelMap modelMap
			) throws Exception{
    	
    	/** pageing setting START */
		admStatSurveyVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admStatSurveyVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admStatSurveyVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admStatSurveyVO.getPageUnit());
		paginationInfo.setPageSize(admStatSurveyVO.getPageSize());
		
		admStatSurveyVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admStatSurveyVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admStatSurveyVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int surVeyListCnt = admStatService.getAdmSurVeyCnt(admStatSurveyVO);
		
		paginationInfo.setTotalRecordCount(surVeyListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmStatSurveyVO> surVeyList = admStatService.getAdmSurVeyList(admStatSurveyVO);
    	modelMap.put("surVeyList", surVeyList);
    	
    	AdmStatSurveyVO pSearch = new AdmStatSurveyVO();
    	pSearch.setSearchCondition(admStatSurveyVO.getSearchCondition());
    	pSearch.setSearchKeyword(admStatSurveyVO.getSearchKeyword());
    	pSearch.setCurrentRecordCount(surVeyList.size());
    	modelMap.put("pSearch", pSearch);
    	
		return ".adm/adm/stat/admSurveyStatistics";
	}
	
	
	/**
	 * 게시판 통계 조회
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmBbsStatList")
	public String getAdmBbsStatList(@ModelAttribute("admStatSearchVO") AdmStatSearchVO admStatSearchVO ,ModelMap modelMap) throws Exception{
		
		/** PropertyService.sample */
		admStatSearchVO.setPageUnit(admStatSearchVO.getPageUnit());
		admStatSearchVO.setPageSize(propertiesService.getInt("pageSize"));
		admStatSearchVO.setPageIndex(admStatSearchVO.getPageIndex());
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admStatSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admStatSearchVO.getPageUnit());
		paginationInfo.setPageSize(admStatSearchVO.getPageSize());
		
		admStatSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admStatSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admStatSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		admStatSearchVO.setsFromDt(admStatSearchVO.getsFromDt().replaceAll("[-,]", ""));
		admStatSearchVO.setsToDt(admStatSearchVO.getsToDt().replaceAll("[-,]", ""));

//		admStatSearchVO.setSearchCondition(admStatSearchVO.getSearchCondition());
//		admStatSearchVO.setSearchKeyword(admStatSearchVO.getSearchUseYn());
		
		List<AdmStatBBSVO> notiList = admStatService.getAdmBbsStatList(admStatSearchVO);
		int totCnt = admStatService.getAdmBbsStatListTotCnt(admStatSearchVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("admStatSearchVO", admStatSearchVO);
		modelMap.put("notiList", notiList);
		
		
		return ".self/adm/stat/admBbsStatList";
	}	

	
}
