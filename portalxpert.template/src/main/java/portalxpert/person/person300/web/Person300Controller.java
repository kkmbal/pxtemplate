package portalxpert.person.person300.web;

import java.io.File;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyAnswVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.exception.PortalxpertException;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.FileDownloadUtil;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.person.person300.sc.Person300Service;
import portalxpert.person.person300.vo.PsnSurveyResultVO;
import portalxpert.person.person300.vo.PsnTmlnApndFileVO;
import portalxpert.person.person300.vo.PsnTmlnEvalInfoVO;
import portalxpert.person.person300.vo.PsnTmlnInfoVO;
import portalxpert.person.person300.vo.PsnTmlnOpnVO;
import egovframework.rte.fdl.property.EgovPropertyService;

//page import="netscape.ldap.*"

@Controller
@RequestMapping(value = "person300")
public class Person300Controller {

	/** person300Service */
	@Resource(name = "person300Service")
	private Person300Service person300Service;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
	
	/** boardService */
    @Resource(name = "board100Service")
    private Board100Service board100Service;

    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory
			.getLogger(Person300Controller.class);
	
	/**
     * 전체보기
     * @param modelMap
     * @return person/person300TotView.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/person300TotView")
	public String person300TotView(ModelMap modelMap,
			@RequestParam(value="viewMode" ,required = false) String viewMode,
			HttpServletRequest request,
			HttpSession session
			) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	int lastTmlnSeq = 999999999;
    	
    	if (viewMode == null) viewMode = "ALL";

    	PsnTmlnInfoVO TmlnVO = new PsnTmlnInfoVO();
    	TmlnVO.setUserId(info.getId());
    	TmlnVO.setTmlnSeq(lastTmlnSeq);
    	TmlnVO.setRowNum(10);
    	TmlnVO.setViewMode(viewMode);
    	String webDir = PortalxpertConfigUtils.getString("image.web.contextpath");
   		List tmln_list = person300Service.getPsnTmlnInfoList(TmlnVO);   		
   		if (tmln_list.size() > 0)
   		{
   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(tmln_list.size()-1);
   			lastTmlnSeq = vo.getTmlnSeq();
   		}   		
   		//링크걸기
   		for (int i = 0; i < tmln_list.size(); i++)
   		{
   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(i);
   			vo.setTmlnConts(setLinkTag(vo.getTmlnConts()));
   			vo.setFaceImg(webDir + vo.getFaceImg());
   		}
   		
   		
   		PsnTmlnOpnVO opnVO = new PsnTmlnOpnVO();
   		opnVO.setUserId(info.getId());
   		opnVO.setTmlnSeq(999999999);
   		opnVO.setRowNum(10);
   		opnVO.setViewMode(viewMode);
   		
   		List opn_list = person300Service.getPsnTmlnOpnList(opnVO);
   		
   	    //의견
   		for (int i = 0; i < opn_list.size(); i++)
   		{
   			PsnTmlnOpnVO vo = (PsnTmlnOpnVO)opn_list.get(i);
   			vo.setOpnConts(setLinkTag(vo.getOpnConts()));
   			vo.setFaceImg(webDir + vo.getFaceImg());
   		}
   		
   		PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
   		apndVO.setUserId(info.getId());
   		apndVO.setTmlnSeq(999999999);
   		apndVO.setRowNum(10);
   		apndVO.setViewMode(viewMode);
   		
		List apnd_list = person300Service.getPsnTmlnApndFileList(apndVO);
   		
		BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
		surveyVO.setUserId(info.getId());
		surveyVO.setTmlnSeq(999999999);
		surveyVO.setRowNum(10);
		surveyVO.setViewMode(viewMode);
		
		List survey_list = person300Service.getBbsNotiSurveyList(surveyVO);
		
		BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();
		exmplVO.setUserId(info.getId());
		exmplVO.setTmlnSeq(999999999);
		exmplVO.setRowNum(10);
		exmplVO.setViewMode(viewMode);
		
		List exmpl_list = person300Service.getBbsNotiSurveyExmplList(exmplVO);
		
		modelMap.put("tmlnList", JSONUtils.objectToJSON(tmln_list));		
    	modelMap.put("opnList", JSONUtils.objectToJSON(opn_list));
    	modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
    	modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
    	modelMap.put("exmplList", JSONUtils.objectToJSON(exmpl_list));
		modelMap.put("lastTmlnSeq", lastTmlnSeq);
		modelMap.put("userId", info.getId());
		modelMap.put("viewMode", viewMode);
		modelMap.put("myImg", webDir+info.getUserRepImg());
		
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String isAdmin = "N";
		if( superAdmin.equals("E")) isAdmin = "Y";		
		modelMap.put("isAdmin", isAdmin);
    	
		return ".self/person/person300TotView";

	}
	
	/**
     * 소통글 작성
     * @param modelMap
     * @return person/person300Write.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/person300Write")
	public String person300Write(ModelMap modelMap,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="viewMode" ,required = false) String viewMode
			) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	String webDir = PortalxpertConfigUtils.getString("image.web.contextpath");
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
    	String MOVIE_DIR = PortalxpertConfigUtils.getString("upload.thumbnail.web");
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	int lastTmlnSeq = 999999999;
    	
    	//if (viewMode == null) viewMode = "USER";
    	if (viewMode == null) viewMode = "ALL"; //default

    	PsnTmlnInfoVO TmlnVO = new PsnTmlnInfoVO();    	
    	TmlnVO.setUserId(info.getId());
    	TmlnVO.setTmlnSeq(lastTmlnSeq);
    	TmlnVO.setTmlnDiv("010");
    	TmlnVO.setRowNum(10);    	
    	TmlnVO.setViewMode(viewMode);
   		
   		List tmln_list = person300Service.getPsnTmlnInfoList(TmlnVO);   		
   		if (tmln_list.size() > 0)
   		{
   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(tmln_list.size()-1);
   			lastTmlnSeq = vo.getTmlnSeq();
   		}
   		
   	    //의견
   		for (int i = 0; i < tmln_list.size(); i++)
   		{
   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(i);
   			vo.setTmlnConts(setLinkTag(vo.getTmlnConts()));
   			vo.setFaceImg(webDir + vo.getFaceImg());
   		}
   		
   		
   		PsnTmlnOpnVO opnVO = new PsnTmlnOpnVO();
   		opnVO.setUserId(info.getId());
   		opnVO.setTmlnDiv("010");
   		opnVO.setRowNum(10);
   		opnVO.setTmlnSeq(999999999);   		
   		opnVO.setViewMode(viewMode);   		
   		
   		List opn_list = person300Service.getPsnTmlnOpnList(opnVO);
   	    //링크걸기
   		for (int i = 0; i < opn_list.size(); i++)
   		{
   			PsnTmlnOpnVO vo = (PsnTmlnOpnVO)opn_list.get(i);
   			vo.setOpnConts(setLinkTag(vo.getOpnConts()));
   			vo.setFaceImg(webDir + vo.getFaceImg());
   		}
   		
   		
   		PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
   		apndVO.setUserId(info.getId());
   		apndVO.setTmlnDiv("010");
   		apndVO.setRowNum(10);
   		apndVO.setTmlnSeq(999999999);   		
   		apndVO.setViewMode(viewMode);
   		
		List apnd_list = person300Service.getPsnTmlnApndFileList(apndVO);
   		
		BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
		surveyVO.setUserId(info.getId());
		surveyVO.setTmlnDiv("010");
		surveyVO.setRowNum(10);
		surveyVO.setTmlnSeq(999999999);
		surveyVO.setViewMode(viewMode);
		
		List survey_list = person300Service.getBbsNotiSurveyList(surveyVO);
		
		BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();
		exmplVO.setUserId(info.getId());
		exmplVO.setTmlnDiv("010");
		exmplVO.setRowNum(10);
		exmplVO.setTmlnSeq(999999999);
		exmplVO.setViewMode(viewMode);
		
		List exmpl_list = person300Service.getBbsNotiSurveyExmplList(exmplVO);
		
		
		//UserInfoVO userVO = person300Service.updatePsnTmlnReadYn(info);
		
		
		
		modelMap.put("tmlnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(tmln_list)));		
    	modelMap.put("opnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(opn_list)));
    	modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
    	modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
    	modelMap.put("exmplList", JSONUtils.objectToJSON(exmpl_list));
		modelMap.put("lastTmlnSeq", lastTmlnSeq);
		modelMap.put("userId", info.getId());
		modelMap.put("viewMode", viewMode);
		modelMap.put("myImg", webDir+info.getUserRepImg());		
		modelMap.put("WEB_DIR", CONTEXT_PATH + WEB_DIR);		
		modelMap.put("MOVIE_DIR", CONTEXT_PATH + MOVIE_DIR);		
		
		
		String IMG_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.img.max");
		String IMG_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.img.size");
		
		String APND_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.apnd.max");
		String APND_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.apnd.size");
		
		String SURVEY_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.max");
		String SURVEY_VIEW_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.view");
		
		if (IMG_UPLOAD_MAX.equals("")) IMG_UPLOAD_MAX = "10";
		if (IMG_UPLOAD_SIZE.equals("")) IMG_UPLOAD_SIZE = "3";
		
		if (APND_UPLOAD_MAX.equals("")) APND_UPLOAD_MAX = "10";
		if (APND_UPLOAD_SIZE.equals("")) APND_UPLOAD_SIZE = "3";
		
		if (SURVEY_UPLOAD_MAX.equals("")) SURVEY_UPLOAD_MAX = "20";
		if (SURVEY_VIEW_UPLOAD_MAX.equals("")) SURVEY_VIEW_UPLOAD_MAX = "10";
		
		modelMap.put("imgUploadMax", IMG_UPLOAD_MAX);
		modelMap.put("imgUploadSize", IMG_UPLOAD_SIZE);
		modelMap.put("apndUploadMax", APND_UPLOAD_MAX);
		modelMap.put("apndUploadSize", APND_UPLOAD_SIZE);
		modelMap.put("surveyUploadMax", SURVEY_UPLOAD_MAX);
		modelMap.put("surveyUploadView", SURVEY_VIEW_UPLOAD_MAX);
		
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String isAdmin = "N";
		if( superAdmin.equals("E")) isAdmin = "Y";		
		modelMap.put("isAdmin", isAdmin);
		
		return ".self/person/person300Write";

	}
    
    /**
     * 알림
     * @param modelMap
     * @return person/person300Alarm.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/person300Alarm")
	public String person300Alarm(ModelMap modelMap,
			HttpSession session
			) throws Exception {
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	int lastTmlnSeq = 999999999;

    	PsnTmlnInfoVO TmlnVO = new PsnTmlnInfoVO();
    	TmlnVO.setUserId(info.getId());
    	TmlnVO.setTmlnSeq(lastTmlnSeq);
    	TmlnVO.setTmlnDiv("020");
    	TmlnVO.setRowNum(30);
    	TmlnVO.setViewMode("USER");
   		
   		List tmln_list = person300Service.getPsnTmlnInfoList(TmlnVO);
   		
   		if (tmln_list.size() > 0)
   		{
   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(tmln_list.size()-1);
   			lastTmlnSeq = vo.getTmlnSeq();
   		}
   		
		modelMap.put("tmlnList", JSONUtils.objectToJSON(tmln_list));
		modelMap.put("lastTmlnSeq", lastTmlnSeq);
		modelMap.put("userId", info.getId());
    	
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String isAdmin = "N";
		if( superAdmin.equals("E")) isAdmin = "Y";		
		modelMap.put("isAdmin", isAdmin);
		
		//return ".person/person/person300Alarm";
		return ".self/person/person300Alarm";

	}

    
    /**
     * 소통글 입력
     * @param modelMap
     * @return person/insertTmlnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertTmlnList", method = RequestMethod.POST)
    public ModelMap insertTmlnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnInfoVO vo = new PsnTmlnInfoVO(); 

 		try{	
 			vo = person300Service.insertPsnUserTmlnInfo(data, session);
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("tmlnList", vo);
 		
 		return modelMap;
 	}
    
    /**
     * 소통글 수정
     * @param modelMap
     * @return person/updateTmlnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/updateTmlnList", method = RequestMethod.POST)
    public ModelMap updateTmlnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnInfoVO vo = new PsnTmlnInfoVO(); 

 		try{
 			//쓰기권한체크
 			getTmlnWriteYN(session, data, jsonResult);

 			vo = person300Service.updatePsnUserTmlnInfo(data, session);
 			vo.setTmlnConts(CommUtil.scriptRemove(vo.getTmlnConts()));
 			
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("tmlnList", vo);
 		
 		return modelMap;
 	}
    
    
    /**
     * 소통글 삭제
     * @param modelMap
     * @return person/deleteTmlnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTmlnList", method = RequestMethod.POST)
    public ModelMap deleteTmlnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnInfoVO vo = new PsnTmlnInfoVO(); 

 		try{	
 			//쓰기권한체크
 			getTmlnWriteYN(session, data, jsonResult);
 			
 			vo = person300Service.deletePsnUserTmlnInfo(data, session);
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("tmlnList", vo);
 		
 		return modelMap;
 	}
    
    
    /**
     * 소통글에 대한 의견 저장
     * @param modelMap
     * @return person/insertOpnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertOpnList", method = RequestMethod.POST)
    public ModelMap insertOpnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpServletRequest request,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnOpnVO vo = new PsnTmlnOpnVO(); 
    		String webDir = PortalxpertConfigUtils.getString("image.web.contextpath");
    		
 		try{
 			
 			
 			vo = person300Service.insertPsnTmlnOpn(data, session);
 			
 			vo.setOpnConts(setLinkTag(vo.getOpnConts())); 
 			vo.setOpnConts(CommUtil.scriptRemove(vo.getOpnConts()));
 			vo.setFaceImg(webDir+vo.getFaceImg());
 			
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("opnList", vo);
 		
 		return modelMap;
 	}
    
    /**
     * 소통글 조회
     * @param modelMap
     * @return person/getPsnTmlnInfo.jsp
     * @throws Exception
     */
    @RequestMapping("/getPsnTmlnInfo")
    public ModelMap getPsnTmlnInfo(
 		   @RequestParam(value="data" ,required = true) String data,
            ModelMap model,
            HttpServletRequest request,
            HttpSession session
            )  
            throws Exception {
       UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
 	   int lastTmlnSeq = 999999999;
 	    
 	   PsnTmlnInfoVO tmlnVO = new PsnTmlnInfoVO();	    
 	   String webDir = PortalxpertConfigUtils.getString("image.web.contextpath");

 	  JSONResult jsonResult = new JSONResult();
 	  jsonResult.setSuccess(true);
 	  
 	  try{
	 	   JSONObject jsonObject = JSONObject.fromObject(data);
	 	    
	 	   tmlnVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
	 	   tmlnVO.setUserId((String)jsonObject.get("userId"));
	 	   tmlnVO.setTmlnDiv((String)jsonObject.get("tmlnDiv"));
	 	   tmlnVO.setViewMode((String)jsonObject.get("viewMode"));
	 	   
	 	   if (tmlnVO.getTmlnDiv().equals("020")){
	 		  tmlnVO.setRowNum(30);
	 	   }else{
	 		  tmlnVO.setRowNum(10);
	 	   }
	 	   
	 		List tmln_list = person300Service.getPsnTmlnInfoList(tmlnVO);
	
	 		if (tmln_list.size() > 0)
	 		{
	 			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(tmln_list.size()-1);
	 			lastTmlnSeq = vo.getTmlnSeq();
	 		}
	 		
	 		//의견
	   		for (int i = 0; i < tmln_list.size(); i++)
	   		{
	   			PsnTmlnInfoVO vo = (PsnTmlnInfoVO)tmln_list.get(i);
	   			vo.setTmlnConts(setLinkTag(vo.getTmlnConts()));
	   			vo.setFaceImg(webDir + vo.getFaceImg());
	   		}
	
	 		PsnTmlnOpnVO opnVO = new PsnTmlnOpnVO();
	   		opnVO.setUserId((String)jsonObject.get("userId"));
	   		opnVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
	   		opnVO.setTmlnDiv((String)jsonObject.get("tmlnDiv"));
	   		opnVO.setViewMode((String)jsonObject.get("viewMode"));
	  	   
	  	    if (opnVO.getTmlnDiv().equals("020")){
	  	    	opnVO.setRowNum(30);
	  	    }else{
	  	    	opnVO.setRowNum(10);
	  	    }   		
	   		
	   		List opn_list = person300Service.getPsnTmlnOpnList(opnVO);
	   		
	   	    //링크걸기
	   		for (int i = 0; i < opn_list.size(); i++)
	   		{
	   			PsnTmlnOpnVO vo = (PsnTmlnOpnVO)opn_list.get(i);
	   			vo.setOpnConts(setLinkTag(vo.getOpnConts()));
	   			vo.setFaceImg(webDir + vo.getFaceImg());
	   		}
	   		
	   		
	   		PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
	   		 				
	   		apndVO.setUserId((String)jsonObject.get("userId"));
	   		apndVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
	   		apndVO.setTmlnDiv((String)jsonObject.get("tmlnDiv"));
	   		apndVO.setViewMode((String)jsonObject.get("viewMode"));
	   	   
	  	    if (apndVO.getTmlnDiv().equals("020")){
	  	    	apndVO.setRowNum(30);
	  	    }else{
	  	    	apndVO.setRowNum(10);
	  	    }
	   		
	   		
			List apnd_list = person300Service.getPsnTmlnApndFileList(apndVO);
	   		
			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
			surveyVO.setUserId(info.getId());
			surveyVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
			surveyVO.setTmlnDiv((String)jsonObject.get("tmlnDiv"));
			surveyVO.setViewMode((String)jsonObject.get("viewMode"));
		   	   
	  	    if (surveyVO.getTmlnDiv().equals("020")){
	  	    	surveyVO.setRowNum(30);
	  	    }else{
	  	    	surveyVO.setRowNum(10);
	  	    }
			
			List survey_list = person300Service.getBbsNotiSurveyList(surveyVO);
			
			BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();
			exmplVO.setUserId(info.getId());
			exmplVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
			exmplVO.setTmlnDiv((String)jsonObject.get("tmlnDiv"));
			exmplVO.setViewMode((String)jsonObject.get("viewMode"));
		   	   
	  	    if (exmplVO.getTmlnDiv().equals("020")){
	  	    	exmplVO.setRowNum(30);
	  	    }else{
	  	    	exmplVO.setRowNum(10);
	  	    }
			
			List exmpl_list = person300Service.getBbsNotiSurveyExmplList(exmplVO);
			
			
	 	    
	   		model.put("tmlnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(tmln_list)));		
	   		model.put("opnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(opn_list)));
	   		model.put("apndList", JSONUtils.objectToJSON(apnd_list));
	   		model.put("surveyList", JSONUtils.objectToJSON(survey_list));
	    	model.put("exmplList", JSONUtils.objectToJSON(exmpl_list));    	
	   		model.put("lastTmlnSeq", lastTmlnSeq);
	   		model.put("userId", info.getId());
 	  }catch(Exception e){
 		 jsonResult.setSuccess(false);
		 jsonResult.setMessage(messageSource.getMessage("common.error")); 
		 jsonResult.setErrMessage(e.getMessage());
 	  }
 		model.put("jsonResult", jsonResult);
        return model;
    }
    
    @RequestMapping(value = "/person400Write")
	public String person400Write(ModelMap modelMap,
			HttpSession session
			) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		return ".person/person/person400Write";

	}
    
    /**
     * 소통글에 대한 설문결과 저장
     * @param modelMap
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/insertBbsNotiSurveyAnsw", method = RequestMethod.POST)
    public ModelMap insertBbsNotiSurveyAnsw(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		BbsNotiSurveyAnswVO vo = new BbsNotiSurveyAnswVO(); 
    		
 		try{	
            
 			JSONArray jsonArr = JSONArray.fromObject(data);
			
			JSONObject jsonObject = (JSONObject)jsonArr.get(0);
			BbsNotiSurveyAnswVO answVO = new BbsNotiSurveyAnswVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			answVO.setSurveyNo( jsonObject.getInt("surveyNo")) ;
			answVO.setAnswmanId( info.getId()) ;
 			
 			int iResult = person300Service.getBbsNotiSurveyResultYN(answVO);
  			
  			if (iResult > 0)
  			{
  				jsonResult.setSuccess(false);
  				jsonResult.setMessage("이미 처리된 자료 입니다.");
  			}
  			else
  			{
  				vo = person300Service.insertBbsNotiSurveyAnsw(data, session);
  				jsonResult.setMessage(messageSource.getMessage("commom.ok"));
  			} 			
 			jsonResult.setSuccess(true); 			
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("tmlnList", vo);
 		
 		return modelMap;
 	}
    
    /**
     * 소통글 의견 수정
     * @param modelMap
     * @return person/updateOpnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/updateOpnList", method = RequestMethod.POST)
    public ModelMap updateOpnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnOpnVO vo = new PsnTmlnOpnVO(); 

 		try{	
 			//쓰기권한체크
 			getTmlnOpnWriteYN(session, data, jsonResult);
 			
 			vo = person300Service.updatePsnTmlnOpn(data, session);
 			
 			vo.setOpnConts(setLinkTag(vo.getOpnConts()));
 			vo.setOpnConts(CommUtil.scriptRemove(vo.getOpnConts()));
 			
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("opnList", vo);
 		
 		return modelMap;
 	}
    
    
    /**
     * 소통글 의견 삭제
     * @param modelMap
     * @return person/deleteTmlnOpnList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTmlnOpnList", method = RequestMethod.POST)
    public ModelMap deleteTmlnOpnList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnOpnVO vo = new PsnTmlnOpnVO(); 

 		try{	
 			//쓰기권한체크
 			getTmlnOpnWriteYN(session, data, jsonResult);
 			
 			vo = person300Service.deleteTmlnOpn(data, session);
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("opnList", vo);
 		
 		return modelMap;
 	}
    
    /**
     * 소통글 좋아요
     * @param modelMap
     * @return person/insertTmlnEvalList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertTmlnEvalList", method = RequestMethod.POST)
    public ModelMap insertTmlnEvalList(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    	    int iResult = 0;
    		JSONResult jsonResult = new JSONResult();
    		PsnTmlnEvalInfoVO vo = new PsnTmlnEvalInfoVO(); 

 		try{	
 			jsonResult.setSuccess(true);
 			jsonResult.setMessage("");
 			
 			iResult = person300Service.getPsnTmlnEvalInfoList(data, session, "USER");
 			
 			if (iResult > 0)
 			{
 				jsonResult.setSuccess(false);
 				jsonResult.setMessage("이미 처리된 자료 입니다.");
 			}
 			else
 			{
 				vo = person300Service.insertPsnTmlnEvalInfo(data, session);
 				iResult = person300Service.getPsnTmlnEvalInfoList(data, session, "ALL");
 			}
 			
 			
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		vo.setEvalCnt(iResult);
 		modelMap.put("evalList", vo);
 		
 		return modelMap;
 	}
    
    /**
     * 좋아요 사용자 정보
     * @param modelMap
     * @return person/getPsnTmlnEvalUserList.jsp
     * @throws Exception
     */
    @RequestMapping("/getPsnTmlnEvalUserList")
    public ModelMap getPsnTmlnEvalUserList(
 		   @RequestParam(value="data" ,required = true) String data,
            ModelMap model,
            HttpSession session
            )  
            throws Exception {
       UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
 	   int lastTmlnSeq = 999999999;
 	    
 	   JSONResult jsonResult = new JSONResult();
 	   jsonResult.setSuccess(true);
 	   PsnTmlnEvalInfoVO evalVO = new PsnTmlnEvalInfoVO();	    

 	   try{
	 	   JSONObject jsonObject = JSONObject.fromObject(data);
	 	    
	 	   evalVO.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
	 	   
	 	   List eval_user_list = person300Service.getPsnTmlnEvalUserList(evalVO);
			
	 	    
	   	   model.put("evalUserList", JSONUtils.objectToJSON(eval_user_list));	
 	   }catch(Exception e){
 		  jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
			jsonResult.setErrMessage(e.getMessage());
 	   }
 	   model.put("jsonResult", jsonResult);
       return model;
    }
    
    /**
     * 이미지 파일 업로드
     * @param modelMap
     * @return board/bbsFileUpload.jsp
     * @throws Exception
     */
    @RequestMapping("/bbsFileUpload") 
    @ResponseBody 
    public void bbsFileUpload(HttpServletRequest request, 
 			HttpServletResponse response,
 			ModelMap model,
 			HttpSession session) throws Exception{
 	  
		String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
		String WEB_DIR = PortalxpertConfigUtils.getString("upload.temp.web");
		String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
		int maxFileSize = PortalxpertConfigUtils.getInt("upload.file.size");

		JSONArray jsonArr = FileUploadUtil.upload(request, SAVE_DIR, WEB_DIR, CONTEXT_PATH, maxFileSize);

		HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
		// wrapper.setContentType("text/plain");
		// wrapper.setHeader("Content-length",  String.valueOf(jsonArr.toString().length()));
		response.getWriter().print(jsonArr.toString());
		response.getWriter().flush();
		response.getWriter().close();
 		
 		
 		//return jsonArr.toString();
 		//return new String(jsonArr.toString().getBytes("ISO-8859-1"),"UTF-8");
 		//new String(jsonArr.toString().getBytes("ISO-8859-1"),"UTF-8"); //new String(jsonArr.toString().getBytes("euc-kr"),"8859_1");
 	}
    





     
     /**
      * 첨부파일 다운로드
      * @param modelMap
      * @return board/bbsFileDownload.jsp
      * @throws Exception
      */    
     @RequestMapping(value = "/bbsFileDownload", method = RequestMethod.GET)
     public void bbsFileDownload(
     		@RequestParam(value="data" ,required = true) String data,
  			ModelMap 		modelMap,
  			HttpServletRequest request, 
  			HttpServletResponse response,
  			HttpSession session
  			
     ) throws Exception {

    	 	data = URLDecoder.decode(new String(data.getBytes("ISO-8859-1")), "UTF-8");
    	 
    	 	JSONObject jsonObject = JSONObject.fromObject(data);
     		String apndFileOrgn = (String)jsonObject.get("apndFileOrgn");
     		
     		String apndFileName = (String)jsonObject.get("apndFileName");
     		String apndFilePath = (String)jsonObject.get("apndFilePath");
     		
  		try{	
  			
  			File file = new File(apndFilePath+'/'+apndFileName.replace("..\\","").replace("../",""));
  			
  			if(!CommUtil.uploadExtensionsCheck(file.getName(), null)){
	        	throw new Exception("Invalid upload file");
	        }
  			
 			FileDownloadUtil.download(request, response, file, apndFileOrgn);
 			
  		}catch(Exception e){
  			logger.error(e.toString(), e);
  		}

  	}
     
     //링크 태그 달기
     private String setLinkTag(String conts)
     {
    	 String str = conts;
    	 if(str == null) return "";
    	 if (str.indexOf("http:") >= 0||str.indexOf("https:") >= 0)
    	 {
    		int start = -1;
    		
    		if (str.indexOf("http:") >= 0)
    		{
    		  start = str.indexOf("http:");
    		}
    		else
    		{
    			start = str.indexOf("https:");
    		}
    		
    		
    		str = str.substring(start);    		
    		int end = str.indexOf(" ");
    		int end2 = str.indexOf("<br>");
    		if (end > 0 && end2 > 0)
    		{
    			if (end > end2)
    			{
    				end = end2;
    			}
    		}
    		else if (end < 0 && end2 < 0)
    		{
    			end = str.length();
    		}
    		else if (end < 0 && end2 > 0)
    		{
    			end = end2;
    		}
    		
    		String link = str.substring(0, end);
    		str = conts.replace(link, "<a href="+link+" target='_blank'>"+link+"</a>");
    	 }
    	 return str;
     }
     
     /**
      * 설문결과 보기
      * @param modelMap
      * @return person/person300SurveyRst.jsp
      * @throws Exception
      */
 	    
     @RequestMapping(value = "/person300SurveyRst")
 	public String person300SurveyRst(ModelMap modelMap,
 			HttpSession session,
 			@RequestParam(value="tmlnSeq" ,required = false) int tmlnSeq, 
 			@RequestParam(value="notiId" ,required = false) String notiId
 			) throws Exception {
     	
    	PsnSurveyResultVO surveyTitleVO = new PsnSurveyResultVO();
    	BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
    	BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();		
    	logger.debug("person300SurveyRst tmlnSeq : "+tmlnSeq);
    	logger.debug("person300SurveyRst notiId : "+notiId);
    	if(tmlnSeq != 0){
    		surveyTitleVO.setTmlnSeq(tmlnSeq);
    		surveyVO.setTmlnSeq(tmlnSeq);	
    		exmplVO.setTmlnSeq(tmlnSeq);
    	}
    	if(notiId != null){
    		surveyTitleVO.setNotiId(notiId);
    		surveyVO.setNotiId(notiId);	
    		exmplVO.setNotiId(notiId);
    	}
 		
 		List survey_title_list = person300Service.getSurveyResultTitle(surveyTitleVO);
 		List survey_result_list = person300Service.getBbsNotiSurveyResultList(surveyVO);
		List survey_exmpl_list = person300Service.getSurveyResultExmpl(exmplVO);
 		
 		modelMap.put("survey_title_list", JSONUtils.objectToJSON(survey_title_list));
 		modelMap.put("survey_result_list", JSONUtils.objectToJSON(survey_result_list));
 		modelMap.put("survey_exmpl_list", JSONUtils.objectToJSON(survey_exmpl_list));
    	 
 		return "portalxpert/person/person300SurveyRst";

 	}
     
     /**
      * 설문결과 수정
      * @param modelMap
      * @return person/person300SurveyUpdate.jsp
      * @throws Exception
      */
 	    
     @RequestMapping(value = "/person300SurveyUpdatePopup")
 	public String person300SurveyUpdatePopup(ModelMap modelMap,
 			HttpServletRequest request,
 			HttpSession session,
 			@RequestParam(value="tmlnSeq" ,required = true) int tmlnSeq
 			) throws Exception {
     	
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
 		surveyVO.setTmlnSeq(tmlnSeq);
 		 		
 		List survey_list = person300Service.getBbsNotiSurveyResultList(surveyVO);
 		
 		BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();
 		exmplVO.setTmlnSeq(tmlnSeq);
 		
 		List exmpl_list = person300Service.getBbsNotiSurveyExmplUpdateList(exmplVO);
    	 
    	String SURVEY_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.max");
 		String SURVEY_VIEW_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.view");
 		
 		if (SURVEY_UPLOAD_MAX.equals("")) SURVEY_UPLOAD_MAX = "20";
 		if (SURVEY_VIEW_UPLOAD_MAX.equals("")) SURVEY_VIEW_UPLOAD_MAX = "10";
 		
 		int tmlnSubjectCnt = person300Service.getBbsNotiSurveyResultCnt(surveyVO);
 		String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
 		
 		modelMap.put("surveyUploadMax", SURVEY_UPLOAD_MAX);
 		modelMap.put("surveyUploadView", SURVEY_VIEW_UPLOAD_MAX);
 		modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
    	modelMap.put("exmplList", JSONUtils.objectToJSON(exmpl_list));
    	modelMap.put("tmlnSeq", tmlnSeq);
    	modelMap.put("tmlnSubjectCnt", tmlnSubjectCnt);
    	modelMap.put("WEB_DIR", CONTEXT_PATH + WEB_DIR);
    	
    	
    	 
 		return ".self/person/person300SurveyUpdatePopup";

 	}
     
     /**
      * 설문 내용 수정
      * @param modelMap
      * @return person/person300SurveyUpdatePopup.jsp
      * @throws Exception
      */
     @RequestMapping(value = "/updateSurveyList", method = RequestMethod.POST)
     public ModelMap updateSurveyList(
     		@RequestParam(value="data" ,required = true) String data,
  			ModelMap 		modelMap,
  			HttpSession session
  			
     ) throws Exception {
     	
     		JSONResult jsonResult = new JSONResult();
     		PsnTmlnInfoVO vo = new PsnTmlnInfoVO(); 

  		try{	
  			vo = person300Service.updateTmlnSurveyInfo(data, session);
  			jsonResult.setSuccess(true);
  			jsonResult.setMessage("");
  		}catch(Exception e){
  			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
  		}
  		
  		modelMap.put("jsonResult", jsonResult);
  		  		
  		return modelMap;
  	}
     
     /**
      * 설문 내용 수정2(마감 일자만 수정)
      * @param modelMap
      * @return person/person300SurveyUpdatePopup.jsp
      * @throws Exception
      */
     @RequestMapping(value = "/updateSurveyList2", method = RequestMethod.POST)
     public ModelMap updateSurveyList2(
     		@RequestParam(value="data" ,required = true) String data,
  			ModelMap 		modelMap,
  			HttpSession session
  			
     ) throws Exception {
     	
     		JSONResult jsonResult = new JSONResult();

  		try{	
  			 person300Service.updateTmlnSurveyInfo2(data, session);
  			jsonResult.setSuccess(true);
  			jsonResult.setMessage("");
  		}catch(Exception e){
  			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
  		}
  		
  		modelMap.put("jsonResult", jsonResult);
  		  		
  		return modelMap;
  	}
    
     
     /**
      * 설문 수정 정보 조회
      * @param modelMap
      * @return person/getTmlnSurveyInfo.jsp
      * @throws Exception
      */
     @RequestMapping("/getTmlnSurveyInfo")
     public ModelMap getTmlnSurveyInfo(
  		     @RequestParam(value="data" ,required = true) int tmlnSeq,
             ModelMap model,
             HttpSession session
             )  
             throws Exception {
        
    	BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
 		surveyVO.setTmlnSeq(tmlnSeq);
 		
 		JSONResult jsonResult = new JSONResult();
 		jsonResult.setSuccess(true);
 		try{
	 		List survey_list = person300Service.getBbsNotiSurveyResultList(surveyVO);
	 		
	 		BbsNotiSurveyExmplVO exmplVO = new BbsNotiSurveyExmplVO();
	 		exmplVO.setTmlnSeq(tmlnSeq);
	 		
	 		List exmpl_list = person300Service.getBbsNotiSurveyExmplUpdateList(exmplVO);
	 		
	  	    jsonResult.setMessage("");
	  	    
	    	model.put("surveyList", JSONUtils.objectToJSON(survey_list));
	     	model.put("exmplList", JSONUtils.objectToJSON(exmpl_list));   	
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
  		model.put("jsonResult", jsonResult);
        return model;
     }
     
     
     /**
      * 게시물
      * @param modelMap
      * @return person/person300Noti.jsp
      * @throws Exception
      */
 	    
     @RequestMapping(value = "/person300Noti")
 	public String person300Noti(ModelMap modelMap,
 			HttpSession session
 			) throws Exception {
     	
     	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
     	
     	int lastSortSeq = 999999999;
     	
     	BbsNotiInfoVO notiVO = new BbsNotiInfoVO();    	
     	notiVO.setUserId(info.getId());
     	notiVO.setSortSeq(lastSortSeq);
     	notiVO.setRnum("20");
     	String auth = board100Service.getUserBbsMapList(info.getId());
     	notiVO.setUserMap(auth);
    		
    	List noti_list = person300Service.getNotilnInfoList(notiVO);
    	
  		if (noti_list.size() > 0)
  		{
  			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
  			lastSortSeq = vo.getSortSeq();
  			
  			//게시글내용 html제거
  			for(int i = 0; i < noti_list.size(); i++){
  				BbsNotiInfoVO resultVO = (BbsNotiInfoVO)noti_list.get(i);
  				
  	    		if (resultVO.getNotiConts() == null) {
  					resultVO.setNotiConts("");
  	    		}
  	    		
  	    		if (resultVO.getNotiConts().length() != 0 && resultVO.getNotiConts() != null && resultVO.getNotiConts() != "") {
  					resultVO.setNotiConts(CommUtil.htmlRemove(resultVO.getNotiConts()));							
  					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
  					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("&nbsp;", "").trim());
  				} else {
  					resultVO.setNotiConts("");
  				}
  			}
  		}
    	
		//for (int i = 0; i < noti_list.size(); i++)
		//{
		//	BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(i);
		//	vo.setNotiConts(setLinkTag(vo.getNotiConts()));
		//}
    		
 		modelMap.put("notiList", JSONUtils.objectToJSON(noti_list));
 		modelMap.put("lastSortSeq", lastSortSeq);
 		modelMap.put("userId", info.getId());
 		
 		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String isAdmin = "N";
		if( superAdmin.equals("E")) isAdmin = "Y";		
		modelMap.put("isAdmin", isAdmin);
     	
 		//return ".person/person/person300Noti";
 		return ".self/person/person300Noti";

 	}
     
     
     
     /**
      * 게시물 조회
      * @param modelMap
      * @return person/getPsnNotiInfo.jsp
      * @throws Exception
      */
     @RequestMapping("/getPsnNotiInfo")
     public ModelMap getPsnNotiInfo(
  		   @RequestParam(value="data" ,required = true) String data,
             ModelMap model,
             HttpSession session
             )  
             throws Exception {
        UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
  	    int lastSortSeq = 999999999;
  	    
		BbsNotiInfoVO notiVO = new BbsNotiInfoVO();

		JSONResult jsonResult = new JSONResult();
		jsonResult.setSuccess(true);
		
		try{
			JSONObject jsonObject = JSONObject.fromObject(data);
	
			notiVO.setSortSeq(jsonObject.getInt("sortSeq"));
			notiVO.setUserId((String) jsonObject.get("userId"));
			String auth = board100Service.getUserBbsMapList(info.getId());
	     	notiVO.setUserMap(auth);
			notiVO.setRnum("20");
	
			List noti_list = person300Service.getNotilnInfoList(notiVO);
	
	  		if (noti_list.size() > 0)
	  		{
	  			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
	  			lastSortSeq = vo.getSortSeq();
	  			
	  			//게시글내용 html제거
	  			for(int i = 0; i < noti_list.size(); i++){
	  				BbsNotiInfoVO resultVO = (BbsNotiInfoVO)noti_list.get(i);
	  				
	  	    		if (resultVO.getNotiConts() == null) {
	  					resultVO.setNotiConts("");
	  	    		}
	  	    		
	  	    		if (resultVO.getNotiConts().length() != 0 && resultVO.getNotiConts() != null && resultVO.getNotiConts() != "") {
	  					resultVO.setNotiConts(CommUtil.htmlRemove(resultVO.getNotiConts()));							
	  					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
	  					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("&nbsp;", "").trim());
	  				} else {
	  					resultVO.setNotiConts("");
	  				}
	  			}
	  		}
	  		
	  		//링크걸기
	    		//for (int i = 0; i < noti_list.size(); i++)
	    		//{
	    		//	BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(i);
	    		//	vo.setNotiConts(setLinkTag(vo.getNotiConts()));
	    		//}
	
	 		
	 		
	  	    
			model.put("notiList", JSONUtils.objectToJSON(noti_list));
			model.put("lastSortSeq", lastSortSeq);
			model.put("userId", info.getId());
		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
		model.put("jsonResult", jsonResult);
        return model;
     }     
     

     /**
      * 의견
      * @param modelMap
      * @return person/person300Opinion.jsp
      * @throws Exception
      */
 	    
     @RequestMapping(value = "/person300Opinion")
 	public String person300Opinion(ModelMap modelMap,
 			HttpSession session
 			) throws Exception {
     	
     	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
     	
     	String lastSortSeq = "9999-12-31 00:00:00";
     	
     	BbsNotiOpnVO notiVO = new BbsNotiOpnVO();    	
     	notiVO.setUserId(info.getId());
     	notiVO.setUpdDttm(lastSortSeq);
     	notiVO.setRnum("30");
    		
    	List noti_list = person300Service.getNotiOpnInfoList(notiVO);
    	
  		if (noti_list.size() > 0)
  		{
  			BbsNotiOpnVO vo = (BbsNotiOpnVO)noti_list.get(noti_list.size()-1);
  			lastSortSeq = vo.getUpdDttm();
  			
  			for(int i = 0; i < noti_list.size(); i++){
  				
  				BbsNotiOpnVO resultVO = (BbsNotiOpnVO)noti_list.get(i);
  				
  	    		if (resultVO.getOpnConts() == null) {
  					resultVO.setOpnConts("");
  	    		}
  	    		
  	    		if (resultVO.getOpnConts().length() != 0 && resultVO.getOpnConts() != null && resultVO.getOpnConts() != "") {
  					resultVO.setOpnConts(CommUtil.htmlRemove(resultVO.getOpnConts()));							
  					resultVO.setOpnConts(resultVO.getOpnConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
  					resultVO.setOpnConts(resultVO.getOpnConts().replaceAll("&nbsp;", "").trim());
  				} else {
  					resultVO.setOpnConts("");
  				}
  			}
  			
  		}
    	
		//for (int i = 0; i < noti_list.size(); i++)
		//{
		//	BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(i);
		//	vo.setNotiConts(setLinkTag(vo.getNotiConts()));
		//}
    		
 		modelMap.put("notiList", JSONUtils.objectToJSON(noti_list));
 		modelMap.put("lastSortSeq", lastSortSeq);
 		modelMap.put("userId", info.getId());
     	
 		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String isAdmin = "N";
		if( superAdmin.equals("E")) isAdmin = "Y";		
		modelMap.put("isAdmin", isAdmin);
		
 		//return ".person/person/person300Opinion";
 		return ".self/person/person300Opinion";

 	}
     
     
     /**
      * 의견 조회
      * @param modelMap
      * @return person/getPsnNotiInfo.jsp
      * @throws Exception
      */
     @RequestMapping("/getPsnNotiOpnInfo")
     public ModelMap getPsnNotiOpnInfo(
  		   @RequestParam(value="data" ,required = true) String data,
             ModelMap model,
             HttpSession session
             )  
             throws Exception {
        UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");

        String lastSortSeq = "9999-12-31 00:00:00";
  	    
        BbsNotiOpnVO notiVO = new BbsNotiOpnVO();
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(true);

        try{
			JSONObject jsonObject = JSONObject.fromObject(data);
	
			notiVO.setUpdDttm((String)jsonObject.get("sortSeq"));
			notiVO.setUserId((String) jsonObject.get("userId"));
			notiVO.setRnum("30");
	
			List noti_list = person300Service.getNotiOpnInfoList(notiVO);
	
	  		if (noti_list.size() > 0)
	  		{
	  			BbsNotiOpnVO vo = (BbsNotiOpnVO)noti_list.get(noti_list.size()-1);
	  			lastSortSeq = vo.getUpdDttm();
	  			
	  			for(int i = 0; i < noti_list.size(); i++){
	  				
	  				BbsNotiOpnVO resultVO = (BbsNotiOpnVO)noti_list.get(i);
	  				
	  	    		if (resultVO.getOpnConts() == null) {
	  					resultVO.setOpnConts("");
	  	    		}
	  	    		
	  	    		if (resultVO.getOpnConts().length() != 0 && resultVO.getOpnConts() != null && resultVO.getOpnConts() != "") {
	  					resultVO.setOpnConts(CommUtil.htmlRemove(resultVO.getOpnConts()));							
	  					resultVO.setOpnConts(resultVO.getOpnConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
	  					resultVO.setOpnConts(resultVO.getOpnConts().replaceAll("&nbsp;", "").trim());
	  				} else {
	  					resultVO.setOpnConts("");
	  				}
	  			}
	  		}
	  		
	  		//링크걸기
	    		//for (int i = 0; i < noti_list.size(); i++)
	    		//{
	    		//	BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(i);
	    		//	vo.setNotiConts(setLinkTag(vo.getNotiConts()));
	    		//}
	
	 		
	 		
	  	    
			model.put("notiList", JSONUtils.objectToJSON(noti_list));
			model.put("lastSortSeq", lastSortSeq);
			model.put("userId", info.getId());
        }catch(Exception e){
        	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
        }
		model.put("jsonResult", jsonResult);
        return model;
     }      
     
     
     
     
     /**
      * 이미지 미리보기
      * @param modelMap
      * @return person/person300SurveyUpdate.jsp
      * @throws Exception
      */
 	    
     @RequestMapping(value = "/person300WriteImagePrevPopup")
 	public String person300WriteImagePrevPopup(ModelMap modelMap,
 			HttpSession session,
 			@RequestParam(value="imgPath" ,required = true) String imgPath
 			) throws Exception {    	
    	modelMap.put("imgPath", imgPath);
    	 
 		return ".self/person/person300WriteImagePrevPopup";

 	}
     
     /**
      * 소통글 쓰기 권한 체크
      * @param HttpSession, data
      * @return String
      * @exception Exception
      */
     private String getTmlnWriteYN(HttpSession session, String data, JSONResult jsonResult)throws Exception {
		String isWrite = "N";

		String superAdmin = (String) session.getAttribute("superAdmin") == null ? ""
				: (String) session.getAttribute("superAdmin");

		if (superAdmin.equals("E")) {
			return "Y";
		}

		PsnTmlnInfoVO pVO = person300Service.getPsnTmlnInfoForUpdate(data, session);
		if (pVO != null) {
			UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
			if (info.getId().equals(pVO.getUserId())) {
				isWrite = "Y";
			}
		}
		
		if(!"Y".equals(isWrite)){
			throw new PortalxpertException("권한이 없습니다.");
		}
		
		return isWrite;
     }
     
     /**
      * 소통글 의견 쓰기 권한 체크
      * @param HttpSession, data
      * @return String
      * @exception Exception
      */
     private String getTmlnOpnWriteYN(HttpSession session, String data, JSONResult jsonResult)throws Exception {
    	 String isWrite = "N";
    	 
    	 String superAdmin = (String) session.getAttribute("superAdmin") == null ? ""
    			 : (String) session.getAttribute("superAdmin");
    	 
    	 if (superAdmin.equals("E")) {
    		 return "Y";
    	 }
    	 
    	 PsnTmlnOpnVO pVO = person300Service.getPsnTmlnOpnForUpdate(data, session);
    	 if (pVO != null) {
    		 UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
    		 if (info.getId().equals(pVO.getUserId())) {
    			 isWrite = "Y";
    		 }
    	 }
    	 
    	 if(!"Y".equals(isWrite)){
    		 throw new PortalxpertException("권한이 없습니다.");
    	 }
    	 
    	 return isWrite;
     }
     
}
