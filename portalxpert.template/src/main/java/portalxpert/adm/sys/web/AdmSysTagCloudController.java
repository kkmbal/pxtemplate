package portalxpert.adm.sys.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.adm.sys.sc.AdmSysTagCloudService;
import portalxpert.adm.sys.vo.AdmSysTagCloudInfoVO;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping(value="adm/sys")
public class AdmSysTagCloudController 
{

	/** AdmSysTagCloudService */
    @Resource(name = "admSysTagCloudService")
    private AdmSysTagCloudService admSysTagCloudService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
   
   private final static Logger logger = LoggerFactory.getLogger(AdmSysTagCloudController.class);
    
   /**
	 * 태그클라우드관리 목록
	 * @param AdmSysTagCloudInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
   @RequestMapping(value="getAdmSysTagCloudList")
	public String getAdmSysTagCloudList(
								AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
								ModelMap modelMap
								)
								throws Exception {
	   if(admSysTagCloudInfoVO.getTagDiv()==null || admSysTagCloudInfoVO.getTagDiv().equals("")) admSysTagCloudInfoVO.setTagDiv("3");
	   
	   
	    /** pageing setting START */
	    admSysTagCloudInfoVO.setPageUnit(propertiesService.getInt("pageUnit"));
	    admSysTagCloudInfoVO.setPageSize(propertiesService.getInt("pageSize"));
   		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admSysTagCloudInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admSysTagCloudInfoVO.getPageUnit());
		paginationInfo.setPageSize(admSysTagCloudInfoVO.getPageSize());
		admSysTagCloudInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admSysTagCloudInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admSysTagCloudInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int tagCloudCnt = admSysTagCloudService.getAdmSysTagCloudCnt(admSysTagCloudInfoVO);
		
		paginationInfo.setTotalRecordCount(tagCloudCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
        
        List<AdmSysTagCloudInfoVO> tagCloudList = admSysTagCloudService.getAdmSysTagCloudList(admSysTagCloudInfoVO);
        modelMap.put("tagCloudList", tagCloudList);
        
	   	AdmBoardNotiInfoVO pSearch = new AdmBoardNotiInfoVO();
	   	pSearch.setSearchCondition(admSysTagCloudInfoVO.getSearchCondition());
	   	pSearch.setSearchKeyword(admSysTagCloudInfoVO.getSearchKeyword());
	   	pSearch.setCurrentRecordCount(tagCloudList.size());
	   	pSearch.setCd(admSysTagCloudInfoVO.getTagDiv());
	   	modelMap.put("pSearch", pSearch);
	   	
  		return ".adm/adm/sys/admSysTagCloudList";
	}
   
   /**
	 * 태그클라우드관리 단건조회
	 * @param AdmGenLinkVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
   @RequestMapping(value="/getAdmSysTagCloud")
   public String getAdmSysTagCloud(
									AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
									ModelMap modelMap
									)
									throws Exception {
	   
	    AdmSysTagCloudInfoVO tagCloud = admSysTagCloudService.getAdmSysTagCloud(admSysTagCloudInfoVO);	    
//	    if(tagCloud.getFontColor()==null || tagCloud.getFontColor().equals("")) tagCloud.setFontColor("#0000ff");
//	    if(tagCloud.getBkgdColor()==null || tagCloud.getBkgdColor().equals("")) tagCloud.setBkgdColor("#ffffff");
   		modelMap.put("tagCloud", tagCloud);
   		
   		AdmGenLinkVO linkVo = new AdmGenLinkVO();
   		linkVo.setLinkCatId(admSysTagCloudInfoVO.getLinkCatId());
   		List<AdmGenLinkVO> comboCtlList = admSysTagCloudService.getAdmSysTagCloudLinkCtlgList();
   		modelMap.put("comboCtlList", comboCtlList);
   		
   		AdmSysTagCloudInfoVO pSearch = new AdmSysTagCloudInfoVO();
   		
   		if(tagCloud != null){
   			pSearch.setPageType("U");
   			pSearch.setLinkCatId(tagCloud.getLinkCatId());
   			
   		}else{
   			pSearch.setPageType("I");
   		}
   		
   		modelMap.put("pSearch", pSearch);
   	
  		return ".adm/adm/sys/admSysTagCloudEdit";
   }
   
   /**
    * 태그클라우드관리 링크리스트 combo
    * @param AdmSysTagCloudInfoVO
    * @return ModelMap
    * @exception Exception
    */
	@RequestMapping(value = "/getAdmSysTagCloudLinkList", method = RequestMethod.POST)
   public ModelMap getAdmSysTagCloudLinkList(
		   						AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
   								ModelMap modelMap
   								) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		JSONArray jsonArr = new JSONArray();
		JSONObject jsonObj = null;
			
		try{
			List<AdmGenLinkVO> comboLinkList = admSysTagCloudService.getAdmSysTagCloudLinkList(admSysTagCloudInfoVO);
			
			for (AdmGenLinkVO resultVo : comboLinkList) {
				
				jsonObj = new JSONObject();
				jsonObj.put("linkCatId", resultVo.getLinkCatId());
				jsonObj.put("linkCd", resultVo.getLinkCd());
			    jsonObj.put("linkAddress", resultVo.getLinkAddress());
			    jsonObj.put("linkOrder", resultVo.getLinkOrder());
			    jsonObj.put("linkNm", resultVo.getLinkNm());
			    jsonObj.put("linkSnm", resultVo.getLinkSnm());
			    jsonObj.put("popupUseGb", resultVo.getPopupUseGb());
				
				jsonArr.add(jsonObj);
			}
			
			modelMap.put("comboLinkList", JSONUtils.objectToJSON(jsonArr));
			
	   	}catch (Exception e) {
	   		jsonResult.setSuccess(false);
	   		jsonResult.setMessage(messageSource.getMessage("common.error"));
	   		jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
   
   /**
    * 태그클라우드관리 등록
    * @param admGenLinkVO
    * @return ModelMap
    * @exception Exception
    */
	@RequestMapping(value = "/insertAdmSysTagCloud", method = RequestMethod.POST)
   public ModelMap insertAdmSysTagCloud(
		   						AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
   								ModelMap modelMap,
   								HttpSession session
   								) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
			
		try{
			admSysTagCloudService.insertAdmSysTagCloud(admSysTagCloudInfoVO,session);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
   		
	   	}catch (Exception e) {
	   		jsonResult.setSuccess(false);
	   		jsonResult.setMessage(messageSource.getMessage("common.error"));
	   		jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	
	/**
    * 태그클라우드관리 수정
    * @param admGenLinkVO
    * @return ModelMap
    * @exception Exception
    */
	@RequestMapping(value = "/updateAdmSysTagCloud", method = RequestMethod.POST)
   public ModelMap updateAdmSysTagCloud(
		   						AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
   								ModelMap modelMap,
   								HttpSession session
   								) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
			
		try{
			admSysTagCloudService.updateAdmSysTagCloud(admSysTagCloudInfoVO,session);
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
    * 태그클라우드관리 삭제
    * @param admGenLinkVO
    * @return ModelMap
    * @exception Exception
    */
	@RequestMapping(value = "/deleteAdmSysTagCloud", method = RequestMethod.POST)
   public ModelMap deleteAdmSysTagCloud(
		   						AdmSysTagCloudInfoVO admSysTagCloudInfoVO,
   								ModelMap modelMap,
   								HttpSession session
   								) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
			
		try{
			admSysTagCloudService.deleteAdmSysTagCloud(admSysTagCloudInfoVO,session);
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
