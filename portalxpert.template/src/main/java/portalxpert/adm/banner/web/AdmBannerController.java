package portalxpert.adm.banner.web;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

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

import portalxpert.adm.banner.sc.AdmBannerService;
import portalxpert.adm.banner.vo.AdmBannerApndFileVO;
import portalxpert.adm.banner.vo.AdmBannerVO;
import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
@RequestMapping(value="adm/banner")
public class AdmBannerController 
{
	
	@Resource(name = "admBannerService")
	private AdmBannerService admBannerService;
	
	@Resource(name = "admGenCodeManageService")
	private AdmGenCodeManageService admGenCodeManageService;
 
	/** EgovPropertyService */	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
 
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmBannerController.class);


	/**
	 * 홍보배너 목록 조회  (pageing)
	 * @param admBannerVO - 조회할 정보가 담긴 admBannerVO
	 * @param model
	 * @return ".adm/adm/banner/admBannerList"
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmBannerList")
	public String getAdmBannerList(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap)
								throws Exception {
	
		/** pageing setting START */
		admBannerVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admBannerVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admBannerVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admBannerVO.getPageUnit());
		paginationInfo.setPageSize(admBannerVO.getPageSize());
		admBannerVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admBannerVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admBannerVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		int bannerListCnt = admBannerService.getAdmBannerListToCnt(admBannerVO);
		paginationInfo.setTotalRecordCount(bannerListCnt);
		modelMap.put("paginationInfo", paginationInfo);
		/** pageing setting END */
	
		List<AdmBannerVO> bannerList = admBannerService.getAdmBannerList(admBannerVO);	
		modelMap.put("bannerList", bannerList);
		modelMap.put("admBannerVO", admBannerVO);
		
		return ".self/adm/banner/admBannerList";
 	
	}
	
	/**
	 * 홍보배너 상세조회 
	 * @param AdmSysBannerVO
	 * @return adm/banner/admBannerView
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmBannerView")
	public String getAdmBannerView(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap)
								throws Exception {

		if(admBannerVO.getBnrId() != null){
			AdmBannerVO admBanner = admBannerService.getAdmBanner(admBannerVO);
			AdmBannerApndFileVO admBannerApndFileVO = new AdmBannerApndFileVO();
			admBannerApndFileVO.setBnrId(admBannerVO.getBnrId());
			List<AdmBannerVO> appendImgVO = admBannerService.getAdmBannerAppendImg(admBannerApndFileVO);
			if(appendImgVO.size() > 0){
				modelMap.put("appendImg", appendImgVO.get(0));
			}
			
			modelMap.put("admBanner", admBanner);
		}
		
		//배너위치
		AdmGenCodeManageVO admGenCodeManageVO = new AdmGenCodeManageVO();
		admGenCodeManageVO.setCd("P_ROW_POS");
		List<AdmGenCodeManageVO> admGenCommonCodeSpecList = admGenCodeManageService.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		modelMap.put("parRowPosList", JSONUtils.objectToJSON(admGenCommonCodeSpecList));
		
		admGenCodeManageVO.setCd("ROW_POS");
		admGenCommonCodeSpecList = admGenCodeManageService.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		modelMap.put("rowPosList", JSONUtils.objectToJSON(admGenCommonCodeSpecList));
		
		return ".self/adm/banner/admBannerManage";
	}	
	
    /**
     * 이미지 파일 업로드
     * @param modelMap
     * @return board/bbsFileUpload.jsp
     * @throws Exception
     */
    @RequestMapping("/bbsFileUpload") 
    @ResponseBody 
    public void bbsFileUpload(HttpServletRequest request, HttpServletResponse response, ModelMap model, HttpSession session) throws Exception{
 	  
		String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
		String WEB_DIR = PortalxpertConfigUtils.getString("upload.temp.web");
		String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
		int maxFileSize = PortalxpertConfigUtils.getInt("upload.file.size");
		   
		JSONArray jsonArr = FileUploadUtil.upload(request, SAVE_DIR, WEB_DIR, CONTEXT_PATH, maxFileSize);
			
		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
		response.getWriter().print(jsonArr.toString());
		response.getWriter().flush();
		response.getWriter().close();
 	}

    
	/**
	 * 홍보배너 등록 
	 * @param AdmLinkUserInfoVO
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmBanner", method = RequestMethod.POST)
	public ModelMap insertAdmBanner(@RequestParam(value="data" ,required = true) String data,
								ModelMap modelMap,
								HttpSession session ) throws Exception {
		
		JSONResult jsonResult = new JSONResult();

		try{
			admBannerService.insertAdmBanner(data, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
	 		
		 }catch (Exception e) {
		 	jsonResult.setSuccess(false);
		 	jsonResult.setMessage(messageSource.getMessage("common.error"));
		 	jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
}
