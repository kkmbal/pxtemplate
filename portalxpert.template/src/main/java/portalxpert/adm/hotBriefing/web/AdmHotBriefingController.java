package portalxpert.adm.hotBriefing.web;


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

import portalxpert.adm.hotBriefing.sc.AdmHotBriefingService;
import portalxpert.adm.hotBriefing.vo.AdmHotBriefingVO;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;



@Controller
@RequestMapping(value="adm/hotBriefing")
public class AdmHotBriefingController 
{
	
	/** RmgMenuService */
	@Resource(name = "admHotBriefingService")
	private AdmHotBriefingService admHotBriefingService;
 
	/** EgovPropertyService */	
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
 
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmHotBriefingController.class);


	/**
	 * 핫브리핑 목록 조회  (pageing)
	 * @param admHotBriefingVO - 조회할 정보가 담긴 admHotBriefingVO
	 * @param model
	 * @return ".adm/adm/hotBriefing/admHotBriefingList"
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmHotBriefingList")
	public String getAdmHotBriefingList(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap)
								throws Exception {
	
		/** pageing setting START */
		admHotBriefingVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admHotBriefingVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admHotBriefingVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admHotBriefingVO.getPageUnit());
		paginationInfo.setPageSize(admHotBriefingVO.getPageSize());
		admHotBriefingVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admHotBriefingVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admHotBriefingVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	
//		String useYn = "Y";
//		if(admHotBriefingVO.getUseYn()!=null && !admHotBriefingVO.getUseYn().equals("") && !admHotBriefingVO.getUseYn().equals("Y")) useYn = admHotBriefingVO.getUseYn();
//		admHotBriefingVO.setUseYn(useYn);
		
		int hotBriefingListCnt = admHotBriefingService.getAdmHotBriefingListToCnt(admHotBriefingVO);
		paginationInfo.setTotalRecordCount(hotBriefingListCnt);
		modelMap.put("paginationInfo", paginationInfo);
		/** pageing setting END */
	
		List<AdmHotBriefingVO> hotBriefingList = admHotBriefingService.getAdmHotBriefingList(admHotBriefingVO);	
		modelMap.put("hotBriefingList", hotBriefingList);
		modelMap.put("admHotBriefingVO", admHotBriefingVO);
		
		return ".adm/adm/hotBriefing/admHotBriefingList";
 	
	}

	//getAdmBannerId
	@RequestMapping(value="/getAdmHotBriefingId")
	public ModelMap getAdmHotBriefingId(ModelMap modelMap) throws Exception {
    	String bnrId = "";
    	JSONResult jsonResult = new JSONResult();
    	
 		try{
 			bnrId = admHotBriefingService.getAdmHotBriefingId();
 		}catch (Exception e) {
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("maxBnrId", bnrId);
 		return modelMap;
    }
	
	/**
	 * 핫브리핑 등록
	 * @param AdmSysHotBriefingVO
	 * @return adm/hotBriefing/admHotBriefingInsert
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmHotBriefingRegister")
	public String getAdmHotBriefingRegister(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap)
								throws Exception {
	
		admHotBriefingVO.setPageType("I");
		
		//VO 날짜 셋팅
		if(admHotBriefingVO.getFromDts()=="" || admHotBriefingVO.getFromDts() == null){
			admHotBriefingVO.setFromDts(CommUtil.getDateString());
		}
		if(admHotBriefingVO.getToDts()=="" || admHotBriefingVO.getToDts() == null){
			admHotBriefingVO.setToDts(CommUtil.getDateString());
		}
		modelMap.put("admHotBriefing", admHotBriefingVO);
		
		return ".adm/adm/hotBriefing/admHotBriefingInsert";
	}
 
	/**
	 * 핫브리핑 상세조회 
	 * @param AdmSysHotBriefingVO
	 * @return adm/hotBriefing/admHotBriefingView
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmHotBriefingView")
	public String getAdmHotBriefingView(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap)
								throws Exception {

		if(admHotBriefingVO.getBnrId() != null){
			AdmHotBriefingVO admHotBriefing = admHotBriefingService.getAdmHotBriefing(admHotBriefingVO);
			List<AdmHotBriefingVO> appendImgVO = admHotBriefingService.getAdmHotBriefingAppendImg(admHotBriefingVO);
			admHotBriefing.setPageType("V");
			
			if(admHotBriefing.getImgWidth() == null){
				admHotBriefing.setImgWidth(100L);
			}
			if(admHotBriefing.getImgHeight() == null){
				admHotBriefing.setImgHeight(100L);
			}
			
			modelMap.put("admHotBriefing", admHotBriefing);
			modelMap.put("appendImg", appendImgVO);
		}
		return ".adm/adm/hotBriefing/admHotBriefingView";
	}

	/**
	 * 핫브리핑 이미지조회
	 * @param AdmSysHotBriefingVO
	 * @return adm/hotBriefing/admHotBriefingImgView
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmHotBriefingImgView")
	public String getAdmHotBriefingImgView(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap)
								throws Exception {

		if(admHotBriefingVO.getBnrId() != null){
			List<AdmHotBriefingVO> appendImgVO = admHotBriefingService.getAdmHotBriefingAppendImg(admHotBriefingVO);
			modelMap.put("appendImg", appendImgVO);
		}
		return ".self/adm/hotBriefing/admHotBriefingImgView";
	}

    /**
     * 핫브리핑 첨부이미지 정보 저장
     * @param FileUpload
     * @param request
     * @param response
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/hotbriefingInnoApUpload")
	public ModelMap hotbriefingInnoApUpload(
			@RequestParam(value="FileUpload" ,required = true) String FileUpload,
			@RequestParam(value="bnrId" ,required = true) String bnrId,
			ModelMap modelMap ,HttpSession session
			)
			throws Exception {
//		String[] file_name = null;
//	    String[] file_Name_re = null;
//	    String[] file_size = null;
//	    String[] web_path = null;
//	    String IMG_PATH = "/hotbriefing/";
	    
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
		    	
		    	AdmHotBriefingVO hotBriefingVO = new AdmHotBriefingVO();
		    	hotBriefingVO.setBnrId(bnrId);
		    	hotBriefingVO.setImgSize(Long.parseLong(file_size[i]));
		    	hotBriefingVO.setImgNameOrg(file_name[i]);
		    	hotBriefingVO.setImgNameReal(file_Name_re[i]);
		    	hotBriefingVO.setImgPath(IMG_PATH);
		    	
		    	admHotBriefingService.deleteAdmHotBriefingAppendImg(hotBriefingVO, session);
		    	admHotBriefingService.hotbriefingInnoApUpload(hotBriefingVO);
		    }
		    */
			
			AdmHotBriefingVO delHotBriefingVO = new AdmHotBriefingVO();
			delHotBriefingVO.setBnrId(bnrId);
			admHotBriefingService.deleteAdmHotBriefingAppendImg(delHotBriefingVO, session);
			
			
			JSONObject bbsNotiObject = JSONObject.fromObject(FileUpload);
			JSONArray jsonArr = (JSONArray)bbsNotiObject.get("jsonAppendFileList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
		    	AdmHotBriefingVO hotBriefingVO = new AdmHotBriefingVO();
		    	hotBriefingVO.setBnrId(bnrId);
		    	hotBriefingVO.setImgSize(obj.getLong("apndFileSz"));
		    	hotBriefingVO.setImgNameOrg((String)obj.get("apndFileOrgn"));
		    	hotBriefingVO.setImgNameReal((String)obj.get("apndFileName"));
		    	hotBriefingVO.setImgPath((String)obj.get("apndFilePath"));
		    	
		    	admHotBriefingService.hotbriefingInnoApUpload(hotBriefingVO);
				
			}
		} catch (Exception e) {
			jsonResult.setSuccess(false);
 			jsonResult.setMessage(messageSource.getMessage("common.error")); 	
 			jsonResult.setErrMessage(e.getMessage());
		} finally {
			
		}		
 		modelMap.put("jsonResult", jsonResult);		
		return modelMap;
    }
	
	/**
	 * 핫브리핑 수정
	 * @param AdmSysHotBriefingVO
	 * @return adm/hotBriefing/admHotBriefingUpdate
	 * @exception Exception
	 */
	@RequestMapping(value="getAdmHotBriefingUpdate")
	public String getAdmHotBriefingUpdate(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap)
								throws Exception {

		if(admHotBriefingVO.getBnrId() != null){
			AdmHotBriefingVO admHotBriefing = admHotBriefingService.getAdmHotBriefing(admHotBriefingVO);
			List<AdmHotBriefingVO> appendImg = admHotBriefingService.getAdmHotBriefingAppendImg(admHotBriefingVO);
			admHotBriefing.setPageType("U");
			modelMap.put("admHotBriefing", admHotBriefing);
			modelMap.put("appendImg", appendImg);
		}
		
		
		return ".adm/adm/hotBriefing/admHotBriefingUpdate";
	}


	/**
	 * 핫브리핑 등록 
	 * @param AdmLinkUserInfoVO
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmHotBriefing", method = RequestMethod.POST)
	public ModelMap insertAdmHotBriefing(
								@ModelAttribute("admHotBriefingVO") AdmHotBriefingVO admHotBriefingVO,
								ModelMap modelMap,
								HttpSession session ) throws Exception {
		
		JSONResult jsonResult = new JSONResult();

		try{
			admHotBriefingService.insertAdmHotBriefing(admHotBriefingVO, session);
//			admHotBriefingService.insertAdmHotBriefingAppendImg(admHotBriefingVO, session);
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
	 * 핫브리핑 수정
	 * @param AdmHotBriefingVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAdmHotBriefing", method = RequestMethod.POST)
	public ModelMap updateAdmHotBriefing(
									@ModelAttribute  
									AdmHotBriefingVO admHotBriefingVO,
									ModelMap modelMap,
									HttpSession session ) throws Exception {

		JSONResult jsonResult = new JSONResult();

		try{
//			admHotBriefingService.updateAdmHotBriefingAppendImg(admHotBriefingVO, session);
			admHotBriefingService.updateAdmHotBriefing(admHotBriefingVO, session);
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
	 * 핫브리핑 삭제(단건)
	 * @param AdmHotBriefingVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmHotBriefing", method = RequestMethod.POST)
	public ModelMap deleteAdmHotBriefing(
									@ModelAttribute  
										AdmHotBriefingVO admHotBriefingVO,
										ModelMap modelMap,
										HttpSession session ) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
				
		try{

			admHotBriefingService.deleteAdmHotBriefingAppendImg(admHotBriefingVO, session);
			admHotBriefingService.deleteAdmHotBriefing(admHotBriefingVO, session);
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
	 * 핫브리핑 삭제(다건)
	 * @param AdmHotBriefingVO, HttpServletRequest
	 * @return ModelMap
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmHotBriefings", method = RequestMethod.POST)
	public ModelMap deleteAdmHotBriefings(
									@ModelAttribute  
										AdmHotBriefingVO admHotBriefingVO,
										ModelMap modelMap,
										HttpSession session ) throws Exception {
				
		JSONResult jsonResult = new JSONResult();
		
		try{
			admHotBriefingService.deleteAdmHotBriefingAppendImgs(admHotBriefingVO, session);
			admHotBriefingService.deleteAdmHotBriefings(admHotBriefingVO, session);
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
