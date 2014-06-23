package portalxpert.adm.banner.web;


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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.adm.banner.sc.AdmBannerService;
import portalxpert.adm.banner.vo.AdmBannerVO;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
@RequestMapping(value="adm/banner")
public class AdmBannerController 
{
	
	/** RmgMenuService */
	@Resource(name = "admBannerService")
	private AdmBannerService admBannerService;
 
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
	
//		String useYn = "Y";
//		if(admBannerVO.getUseYn()!=null && !admBannerVO.getUseYn().equals("") && !admBannerVO.getUseYn().equals("Y")) useYn = admBannerVO.getUseYn();
//		admBannerVO.setUseYn(useYn);
		
		int bannerListCnt = admBannerService.getAdmBannerListToCnt(admBannerVO);
		paginationInfo.setTotalRecordCount(bannerListCnt);
		modelMap.put("paginationInfo", paginationInfo);
		/** pageing setting END */
	
		List<AdmBannerVO> bannerList = admBannerService.getAdmBannerList(admBannerVO);	
		modelMap.put("bannerList", bannerList);
		modelMap.put("admBannerVO", admBannerVO);
		
		return ".adm/adm/banner/admBannerList";
 	
	}

	/**
	 * 홍보배너 등록
	 * @param AdmSysBannerVO
	 * @return adm/banner/admBannerInsert
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmBannerRegister")
	public String getAdmBannerRegister(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap)
								throws Exception {
	
		admBannerVO.setPageType("I");
		
		//VO 날짜 셋팅
		if(admBannerVO.getFromDts()=="" || admBannerVO.getFromDts() == null){
			admBannerVO.setFromDts(CommUtil.getDateString());
		}
		if(admBannerVO.getToDts()=="" || admBannerVO.getToDts() == null){
			admBannerVO.setToDts(CommUtil.getDateString());
		}
		modelMap.put("admBanner", admBannerVO);
		
		
		return ".adm/adm/banner/admBannerInsert";
	}
 
	/**
	 * 파일 서버 업로드
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "innoUpload")
	public String innoUpload(ModelMap modelMap,
			HttpSession session
			) throws Exception {

		return ".self/adm/banner/innoUpload";
	}
	
    /**
     * 배너 첨부이미지 정보 저장
     * @param FileUpload
     * @param request
     * @param response
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/bannerInnoApUpload")
	public ModelMap bannerInnoApUpload(
			@RequestParam(value="FileUpload" ,required = true) String FileUpload,
			@RequestParam(value="bnrId" ,required = true) String bnrId,
			ModelMap modelMap ,HttpSession session
			)
			throws Exception {
//		String[] file_name = null;
//	    String[] file_Name_re = null;
//	    String[] file_size = null;
//	    String[] web_path = null;
//	    String IMG_PATH = "/infoBanner/";
	    
	 	JSONResult jsonResult = new JSONResult();
		try {
			/*
			logger.debug("FileUpload : " + FileUpload);
		    String msg = "";
		    FileUpload = FileUpload.replaceAll("\\},\\{", "\\^");
		    String[] st= FileUpload.split("\\^");
//		    System.out.println("]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]]st["+st.length+"]::");
		    file_name = new String[st.length];
		    file_size = new String[st.length];
		    file_Name_re = new String[st.length];
		    web_path = new String[st.length];
		    
		    for(int i=0;i<st.length;i++) {
		    	msg = st[i];
		    	StringTokenizer st1 = new StringTokenizer(msg,",");
		    	
//		    	APPEND_SIZE	saveFileSize
//		    	APPEND_NM_ORG	original
//		    	APPEND_NM_RE	saveFileName
//		    	APPEND_PATH	webDir
		    	
		    	while(st1.hasMoreTokens()){
		    		String elementStr = st1.nextToken();
		    		String[] elements = elementStr.split(":",2);
		    		
		    		String data = elements[1];
		    		data = data.replaceAll("\"", "");
		    		data = data.replaceAll("\\}", "");
		    		data = data.replaceAll("\\]", "");
		    		
		    		if(elements[0].indexOf("saveFileSize")>0) file_size[i] = data;
		    		if(elements[0].indexOf("original")>0) file_name[i] = data;
		    		if(elements[0].indexOf("saveFileName")>0) file_Name_re[i] = data;
		    		if(elements[0].indexOf("webDir")>0) web_path[i] = data;
		    	}
		    }
		    
		    for(int i=0;i<file_name.length;i++){
//		    	System.out.println("file_size["+i+"]::"+file_size[i]);
//		    	System.out.println("file_name["+i+"]::"+file_name[i]);
//		    	System.out.println("file_Name_re["+i+"]::"+file_Name_re[i]);
//		    	System.out.println("web_path["+i+"]::"+web_path[i]);
		    	
		    	AdmBannerVO bannerFileVO = new AdmBannerVO();
		    	bannerFileVO.setBnrId(bnrId);
		    	bannerFileVO.setImgSize(Integer.parseInt(file_size[i]));
		    	bannerFileVO.setImgNameOrg(file_name[i]);
		    	bannerFileVO.setImgNameReal(file_Name_re[i]);
		    	bannerFileVO.setImgPath(IMG_PATH);
		    	
		    	admBannerService.bannerInnoApUpload(bannerFileVO);
		    }
		    */
			
			AdmBannerVO delBannerFileVO = new AdmBannerVO();
			delBannerFileVO.setBnrId(bnrId);
			admBannerService.deleteAdmBannerAppendImg(delBannerFileVO, session);
			
			
			JSONObject bbsNotiObject = JSONObject.fromObject(FileUpload);
			JSONArray jsonArr = (JSONArray)bbsNotiObject.get("jsonAppendFileList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				AdmBannerVO bannerFileVO = new AdmBannerVO();
				bannerFileVO.setBnrId(bnrId);
				bannerFileVO.setImgSize(obj.getInt("apndFileSz"));
				bannerFileVO.setImgNameOrg((String)obj.get("apndFileOrgn"));
				bannerFileVO.setImgNameReal((String)obj.get("apndFileName"));
				bannerFileVO.setImgPath((String)obj.get("apndFilePath"));
		    	
		    	admBannerService.bannerInnoApUpload(bannerFileVO);
				
			}			
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
 			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		} 		
 		modelMap.put("jsonResult", jsonResult);		
		return modelMap;
    }
	
    @RequestMapping(value="/getAdmBannerId")
	public ModelMap getAdmBannerId(ModelMap modelMap) throws Exception {
    	String bnrId = "";
    	JSONResult jsonResult = new JSONResult();
 		try{
 			bnrId = admBannerService.getAdmBannerId();
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);	
 		modelMap.put("maxBnrId", bnrId);
 		return modelMap;
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
			List<AdmBannerVO> appendImgVO = admBannerService.getAdmBannerAppendImg(admBannerVO);
			admBanner.setPageType("V");
			
			modelMap.put("admBanner", admBanner);
			modelMap.put("appendImg", appendImgVO);
		}
		return ".adm/adm/banner/admBannerView";
	}

	/**
	 * 홍보배너 이미지조회
	 * @param AdmSysBannerVO
	 * @return adm/banner/admBannerImgView
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmBannerImgView")
	public String getAdmBannerImgView(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap)
								throws Exception {

		if(admBannerVO.getBnrId() != null){
			List<AdmBannerVO> appendImgVO = admBannerService.getAdmBannerAppendImg(admBannerVO);
			modelMap.put("appendImg", appendImgVO);
			
			String WEB_DIR = PortalxpertConfigUtils.getString("webserver.image.path");
			modelMap.put("WEB_DIR", WEB_DIR);
		}
		return ".self/adm/banner/admBannerImgView";
	}


	/**
	 * 홍보배너 수정
	 * @param AdmSysBannerVO
	 * @return adm/banner/admBannerUpdate
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmBannerUpdate")
	public String getAdmBannerUpdate(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap)
								throws Exception {

		if(admBannerVO.getBnrId() != null){
			AdmBannerVO admBanner = admBannerService.getAdmBanner(admBannerVO);
			List<AdmBannerVO> appendImg = admBannerService.getAdmBannerAppendImg(admBannerVO);
			admBanner.setPageType("U");
			modelMap.put("admBanner", admBanner);
			modelMap.put("appendImg", appendImg);
			
			//파일 첨부 관련
			modelMap.put("innoApEnc" ,PortalxpertConfigUtils.getString("person.upload.innoap.enc"));
			modelMap.put("innoApkey1" ,PortalxpertConfigUtils.getString("person.upload.innoap.key1"));
			modelMap.put("innoApkey2" ,PortalxpertConfigUtils.getString("person.upload.innoap.key2"));
		}
		
		return ".adm/adm/banner/admBannerUpdate";
	}


	/**
	 * 홍보배너 등록 
	 * @param AdmLinkUserInfoVO
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmBanner", method = RequestMethod.POST)
	public ModelMap insertAdmBanner(
								@ModelAttribute("admBannerVO") AdmBannerVO admBannerVO,
								ModelMap modelMap,
								HttpSession session ) throws Exception {
		
		JSONResult jsonResult = new JSONResult();

		try{
			admBannerService.insertAdmBanner(admBannerVO, session);
//			admBannerService.insertAdmBannerAppendImg(admBannerVO, session);
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

	
	/**
	 * 홍보배너 수정
	 * @param AdmBannerVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAdmBanner", method = RequestMethod.POST)
	public ModelMap updateAdmBanner(
									@ModelAttribute  
									AdmBannerVO admBannerVO,
									ModelMap modelMap,
									HttpSession session ) throws Exception {

		JSONResult jsonResult = new JSONResult();

		try{
//			admBannerService.updateAdmBannerAppendImg(admBannerVO, session);
			admBannerService.updateAdmBanner(admBannerVO, session);
			jsonResult.setSuccess(true);
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
	 * 홍보배너 삭제(단건)
	 * @param AdmBannerVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmBanner", method = RequestMethod.POST)
	public ModelMap deleteAdmBanner(
									@ModelAttribute  
										AdmBannerVO admBannerVO,
										ModelMap modelMap,
										HttpSession session ) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
				
		try{

			admBannerService.deleteAdmBannerAppendImg(admBannerVO, session);
			admBannerService.deleteAdmBanner(admBannerVO, session);
			jsonResult.setSuccess(true);
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
	 * 홍보배너 삭제(다건)
	 * @param AdmBannerVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmBanners", method = RequestMethod.POST)
	public ModelMap deleteAdmBanners(
									@ModelAttribute  
										AdmBannerVO admBannerVO,
										ModelMap modelMap,
										HttpSession session ) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
		
		try{
			admBannerService.deleteAdmBannerAppendImgs(admBannerVO, session);
			admBannerService.deleteAdmBanners(admBannerVO, session);
			jsonResult.setSuccess(true);
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
