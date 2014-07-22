package portalxpert.adm.pop.web;

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

import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.adm.pop.sc.AdmPopService;
import portalxpert.adm.pop.vo.AdmPopApndFileVO;
import portalxpert.adm.pop.vo.AdmPopUserMap;
import portalxpert.adm.pop.vo.AdmPopVO;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="adm/pop")
public class AdmPopController {
	
	@Resource(name = "admPopService")
	private AdmPopService admPopService;
	
	@Resource(name = "admGenCodeManageService")
	private AdmGenCodeManageService admGenCodeManageService;
 
	/** EgovPropertyService */	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
 
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmPopController.class);


	/**
	 * 팝업 목록 조회  (pageing)
	 * @param admPopVO - 조회할 정보가 담긴 admPopVO
	 * @param model
	 * @return ".adm/adm/pop/admPopList"
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmPopList")
	public String getAdmPopList(
								@ModelAttribute("admPopVO") AdmPopVO admPopVO,
								ModelMap modelMap)
								throws Exception {
	
		/** pageing setting START */
		admPopVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admPopVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admPopVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admPopVO.getPageUnit());
		paginationInfo.setPageSize(admPopVO.getPageSize());
		admPopVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admPopVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admPopVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
		int popListCnt = admPopService.getAdmPopListToCnt(admPopVO);
		paginationInfo.setTotalRecordCount(popListCnt);
		modelMap.put("paginationInfo", paginationInfo);
		/** pageing setting END */
	
		List<AdmPopVO> popList = admPopService.getAdmPopList(admPopVO);	
		modelMap.put("popList", popList);
		modelMap.put("admPopVO", admPopVO);
		
		return ".self/adm/pop/admPopList";
 	
	}
	
	/**
	 * 팝업 상세조회 
	 * @param AdmSysPopVO
	 * @return adm/pop/admPopView
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmPopView")
	public String getAdmPopView(
								@ModelAttribute("admPopVO") AdmPopVO admPopVO,
								ModelMap modelMap)
								throws Exception {

		if(admPopVO.getPopId() != null){
			AdmPopVO admPop = admPopService.getAdmPop(admPopVO);
			AdmPopApndFileVO admPopApndFileVO = new AdmPopApndFileVO();
			admPopApndFileVO.setPopId(admPopVO.getPopId());
			List<AdmPopVO> appendImgVO = admPopService.getAdmPopAppendImg(admPopApndFileVO);
			if(appendImgVO.size() > 0){
				modelMap.put("appendImg", appendImgVO.get(0));
			}
			
			modelMap.put("admPop", admPop);

			//게시판 및 게시물 권한 정보
			List<AdmPopUserMap> admPopUserMapList = admPopService.getAdmPopUserMapList(admPopVO);
			modelMap.put("userMapList", JSONUtils.objectToJSON(admPopUserMapList));
		}else{
			modelMap.put("userMapList", "[]");
		}
		
		//배너위치
		AdmGenCodeManageVO admGenCodeManageVO = new AdmGenCodeManageVO();
		admGenCodeManageVO.setCd("P_ROW_POS");
		List<AdmGenCodeManageVO> admGenCommonCodeSpecList = admGenCodeManageService.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		modelMap.put("parRowPosList", JSONUtils.objectToJSON(admGenCommonCodeSpecList));
		
		admGenCodeManageVO.setCd("ROW_POS");
		admGenCommonCodeSpecList = admGenCodeManageService.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		modelMap.put("rowPosList", JSONUtils.objectToJSON(admGenCommonCodeSpecList));
		
		
		return ".self/adm/pop/admPopManage";
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
	 * 팝업 등록 
	 * @param AdmLinkUserInfoVO
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmPop", method = RequestMethod.POST)
	public ModelMap insertAdmPop(@RequestParam(value="data" ,required = true) String data,
								ModelMap modelMap,
								HttpSession session ) throws Exception {
		
		JSONResult jsonResult = new JSONResult();

		try{
			admPopService.insertAdmPop(data, session);
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
