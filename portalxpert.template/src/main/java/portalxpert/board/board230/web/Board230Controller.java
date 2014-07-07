package portalxpert.board.board230.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsBoardUserMapVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.BbsNotiUserMapVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.board.board230.sc.BBSMovieService;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.board.board230.vo.BBSMovieVO;
import portalxpert.board.board230.vo.BbsTmpNotiApndFileVO;
import portalxpert.board.board230.vo.BbsTmpNotiInfoVO;
import portalxpert.board.board230.vo.BbsTmpNotiUserMapVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.exception.PortalxpertException;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
@RequestMapping("board230")
public class Board230Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board230Controller.class); 
   
	/** board230Service */
    @Resource(name = "board230Service")
    private Board230Service board230Service;

    /** board210Service */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
    
    /** board100Service */
    @Resource(name = "board100Service")
    private Board100Service board100Service;
    
	/** BBSMovieService */
    @Resource(name = "bbsMovieService")
    private BBSMovieService bbsMovieService;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 게시글 작성
     * @param modelMap
     * @return board230/board230Write.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/board230Write")
	public String board230Write(ModelMap modelMap,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="boardId", required = true) String boardId,
			@RequestParam(value="notiId", required = false) String notiId,
			@RequestParam(value="upNotiId", required = false) String upNotiId,
			@RequestParam(value="tmpNotiSeq", required = false) String tmpNotiSeq,
			@RequestParam(value="pageIndex", required = false, defaultValue="1") String pageIndex,
			@RequestParam(value="pageUnit", required = false, defaultValue="10") String pageUnit,
			@RequestParam(value="kind", required = true) String kind  //게시물 종류(TMP, BBS, PSN)  //임시저장, 공용, 개인, 경조사, 폐쇄 
			) throws Exception {
    	
    	if (pageIndex == null) pageIndex = "1"; 
    	if (pageUnit == null) pageUnit = "10"; 
    	
    	String boardName = "";  //게시판 이름
    	String boardForm = Constant.BOARD_FORM_010.getVal(); 
    	String boardFormSpec = Constant.BOARD_FORM_SPEC_010.getVal(); 
    	String notiReadmanAsgnYn = "N";
    	String boardKind = Constant.BOARD_KIND_010.getVal();   //일반형/폐쇄/경조사
    	String basicCloseDttm = "";  //마감일자
    	String moblOpenYN = "N"; //모바일 공개 구분
    	String moblOpenDiv = "020"; //모바일 공개 구분
    	String editDiv= "010"; //입력툴 구분
    	String opnWrteDiv= "010"; //의견 사용 구분 
    	String nickUseYn= "Y"; //의견 사용 구분
    	String replyPrmsDiv= "Y"; //답글 사용 구분
    	String isAdmin = "N";
    	String notiWriteId = "";
    	String makrDispDiv = "";
    	String agrmOppUseYn = "";
    	String apndFileSz = "0"; 
    	String boardExplUseYn = ""; 
    	String boardExpl = ""; 
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
    	String SAVE_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
    	String WEB_MOVIE_DIR = PortalxpertConfigUtils.getString("upload.thumbnail.web");
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	if (boardId != null)
		{
    		
    		if (kind.equals("BBS"))  //공용게시물
    		{
    			isAdmin = getBoardUserMapYN(session, boardId);
    			
    			BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
    			bbsVO.setBoardId(boardId);
    			List board_list = board100Service.getAdminBbsBoardInfoList(bbsVO);
    			
    			if (board_list.size() > 0)
    			{
    				BbsBoardInfoVO bbs = (BbsBoardInfoVO)board_list.get(0);
    				boardForm = bbs.getBoardForm();
    				boardFormSpec = bbs.getBoardFormSpec();
    				notiReadmanAsgnYn = bbs.getNotiReadmanAsgnYn();
    				boardName = bbs.getBoardName();
    				boardKind = bbs.getBoardKind();
    				basicCloseDttm = bbs.getBasicCloseDttm();
    				moblOpenYN = bbs.getMoblLinkYn();
    				editDiv = bbs.getEditDiv();
    				opnWrteDiv = bbs.getOpnWrteDiv();
    				nickUseYn = bbs.getNickUseYn();
    				replyPrmsDiv = bbs.getReplyWrteDiv();
    				makrDispDiv = bbs.getMakrDispDiv();
    				agrmOppUseYn = bbs.getAgrmOppUseYn();
    				
    				apndFileSz = ""+bbs.getApndFileSz();
    				boardExplUseYn = bbs.getBoardExplUseYn();
    				boardExpl = bbs.getBoardExpl();
    				
    				
    			}
    			
    			BbsBoardUserMapVO userVO = new BbsBoardUserMapVO();
	    		userVO.setBoardId(bbsVO.getBoardId());
	    		List user_list = board100Service.getAdminBbsBoardUserMapList(userVO);
	    		
	    		boolean isALL = false;
	    		//전체공개 체크
	    		for(int i=0; i < user_list.size(); i++)
	    		{
	    			BbsBoardUserMapVO vo = (BbsBoardUserMapVO)user_list.get(i);
	    			if (vo.getUserDiv().equals("ALL"))
	    			{
	    				isALL = true;
	    				break;
	    			}
	    		}
	    		
	    		//전체공개만 리턴
	    		if (isALL)
	    		{
	    			for(int i=user_list.size()-1; i >= 0; i--)
	        		{
	        			BbsBoardUserMapVO vo = (BbsBoardUserMapVO)user_list.get(i);
	        			if (!vo.getUserDiv().equals("ALL"))
	        			{
	        				user_list.remove(i);
	        			}
	        		}
	    		}
	    		
	    		if (notiId != null)  //notiId가 있을 경우는 수정 인 경우
	    		{
	    			//게시물 기본정보
	    			BbsNotiInfoVO notiVO = new BbsNotiInfoVO();
	    			notiVO.setBoardId(boardId);
	    			notiVO.setNotiId(notiId);	    			
	    			List noti_list = board230Service.getBbsNotiInfoList(notiVO);
	    			//게시물 첨부 조회 
	    			BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
	    			apndVO.setNotiId(notiId);	    			
	    			List apnd_list = board230Service.getBbsNotiApndFileList(apndVO);
	    			
	    			
	    			
	    			
	    			for (int i = 0; i < apnd_list.size(); i++)
	    			{
	    				BbsNotiApndFileVO vo = (BbsNotiApndFileVO)apnd_list.get(i);
	    				if (vo.getApndFileName().indexOf(".") > 0)
	    				{
	    					String str =  vo.getApndFileName().substring(0, vo.getApndFileName().indexOf("."));
	    					vo.setSaveFileId(str);
	    				}
	    				//vo.setApndFilePath( WEB_DIR +"/" +vo.getApndFilePath());
	    			}
	    				    			
	    			//설문 정보 조회
	    			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
	    			surveyVO.setNotiId(notiId);	    			
	    			List survey_list = board230Service.getBbsNotiSurveyList(surveyVO);
	    			
	    			List surveyExmpl_list = null;
	    			
	    			if (survey_list.size() > 0)
	    			{
	    				// 설문 보기 정보 조회
	    				BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
		    			surveyExmplVO.setNotiId(notiId);
		    			surveyExmpl_list = board230Service.getBbsNotiSurveyExmplList(surveyExmplVO);
		    			modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
	    			}
	    			else
	    			{
	    				modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
	    			}
	    			
	    			//modelMap.put("notiList", JSONUtils.objectToJSON(noti_list));
	    			modelMap.put("notiList", CommUtil.scriptRemove(JSONUtils.objectToJSON(noti_list)));
	    			modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
	    			modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
	    			
	    			BbsNotiUserMapVO userMapVO = new BbsNotiUserMapVO();
	    			userMapVO.setNotiId(notiId);
		    		user_list = board100Service.getBbsNotiUserMapList(userMapVO);
		    		
		    		
	     			if (noti_list.size() > 0)
	     			{
	     				BbsNotiInfoVO noti = (BbsNotiInfoVO)noti_list.get(0);
	     				modelMap.put("userName", noti.getUserName());
	     				modelMap.put("deptName", noti.getDeptName());
	     				moblOpenDiv = noti.getMoblOpenDiv();
	     				notiWriteId = noti.getRegrId();
	     			}
	     			else
	     			{
	     				modelMap.put("userName", info.getName());
	     				modelMap.put("deptName", info.getOu());
	     			}
	    		}
	    		else
	    		{
	    			modelMap.put("userName", info.getName());
	    			modelMap.put("notiList", "[]");
	    			modelMap.put("apndList", "[]");
	    			modelMap.put("surveyList", "[]");
	    			modelMap.put("surveyExmplList", "[]");
	    			modelMap.put("userName", info.getName());
	    		}
	    		//게시판 및 게시물 권한 정보
	    		modelMap.put("userMapList", JSONUtils.objectToJSON(user_list));
	    		
	    		modelMap.put("tmpNotiList", "[]");
    			modelMap.put("tmpApndList", "[]");
    			modelMap.put("tmpUserList", "[]");
    			modelMap.put("tmpSurveyList", "[]");
    			modelMap.put("tmpSurveyExmplList", "[]");
	    		
    		} 
    		else if (kind.equals("TMP"))  //임시저장
    		{
    			BbsTmpNotiInfoVO bbsVO = new BbsTmpNotiInfoVO();
    			bbsVO.setTmpNotiSeq(Integer.parseInt(tmpNotiSeq));
    			
    			//임시저장 기본정보
    			List tmp_noti_list = board230Service.getBbsTmpNotiInfoList(bbsVO);
    			
    			if (tmp_noti_list.size() > 0)
    			{
    				boardName = ((BbsTmpNotiInfoVO)tmp_noti_list.get(0)).getBoardName();
    				boardKind = ((BbsTmpNotiInfoVO)tmp_noti_list.get(0)).getBoardKind();
    				isAdmin = getBoardUserMapYN(session, ((BbsTmpNotiInfoVO)tmp_noti_list.get(0)).getBoardId());
    				notiReadmanAsgnYn = ((BbsTmpNotiInfoVO)tmp_noti_list.get(0)).getNotiReadmanAsgnYn();
    			}
    			
    			BbsTmpNotiApndFileVO apndVO = new BbsTmpNotiApndFileVO();
    			apndVO.setTmpNotiSeq(bbsVO.getTmpNotiSeq());
    			
    			//임시저장 첨부정보
    			List tmp_apnd_list = board230Service.getBbsTmpNotiApndFileList(apndVO);
    			
    			for (int i = 0; i < tmp_apnd_list.size(); i++)
    			{
    				BbsTmpNotiApndFileVO vo = (BbsTmpNotiApndFileVO)tmp_apnd_list.get(i);
    				if (vo.getApndFileName().indexOf(".") > 0)
    				{
    					String str =  vo.getApndFileName().substring(0, vo.getApndFileName().indexOf("."));
    					vo.setSaveFileId(str);
    				}
    			}
    			
    			BbsTmpNotiUserMapVO userVO = new BbsTmpNotiUserMapVO();
    			userVO.setTmpNotiSeq(bbsVO.getTmpNotiSeq());
    			
    			//임시저장 조회자 지정 조회
    			List tmp_user_list = board230Service.getBbsTmpNotiUserMapList(userVO);
    			
    			//임시저장 설문 정보 조회
    			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
    			surveyVO.setTmpNotiSeq(bbsVO.getTmpNotiSeq());    			
    			List tmp_survey_list = board230Service.getBbsNotiSurveyList(surveyVO);
    			List tmp_surveyExmpl_list = null;
    			
    			if (tmp_survey_list.size() > 0)
    			{
    				//surveyVO = (BbsNotiSurveyVO)tmp_survey_list.get(0);
    				
    				//임시저장 설문 보기 정보 조회
    				BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
	    			surveyExmplVO.setTmpNotiSeq(bbsVO.getTmpNotiSeq());
	    			tmp_surveyExmpl_list = board230Service.getBbsNotiSurveyExmplList(surveyExmplVO);
	    			modelMap.put("tmpSurveyExmplList", JSONUtils.objectToJSON(tmp_surveyExmpl_list));
    			}
    			else
    			{
    				modelMap.put("tmpSurveyExmplList", JSONUtils.objectToJSON(tmp_surveyExmpl_list));
    			}
    			
    			//boardName = "임시 게시판";
    			
    			modelMap.put("tmpNotiList", JSONUtils.objectToJSON(tmp_noti_list));
    			modelMap.put("tmpApndList", JSONUtils.objectToJSON(tmp_apnd_list));
    			modelMap.put("tmpUserList", JSONUtils.objectToJSON(tmp_user_list));
    			modelMap.put("tmpSurveyList", JSONUtils.objectToJSON(tmp_survey_list));
    			
    			modelMap.put("notiList", "[]");
    			modelMap.put("apndList", "[]");
    			modelMap.put("userMapList", "[]");
    			modelMap.put("surveyList", "[]");
    			modelMap.put("surveyExmplList", "[]");
    		}
    		
     		
     		if (notiId == null) notiId = "";
     		
     		
     		modelMap.put("boardId", boardId);
     		modelMap.put("boardKind", boardKind);
     		modelMap.put("boardForm", boardForm);
     		modelMap.put("boardFormSpec", boardFormSpec);
     		modelMap.put("notiReadmanAsgnYn", notiReadmanAsgnYn);
     		modelMap.put("boardName", boardName);
     		modelMap.put("kind", kind);
     		modelMap.put("notiId", notiId);
     		modelMap.put("basicCloseDttm", basicCloseDttm);
     		
     		String IMG_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.img.max");
    		String IMG_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.img.size");
    		
    		String APND_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.apnd.max");
    		String APND_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.apnd.size");
    		
    		String SURVEY_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.max");
    		String SURVEY_VIEW_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.survey.view");
    		
    		
    		
    		if (IMG_UPLOAD_MAX == null) IMG_UPLOAD_MAX = "10";
    		if (IMG_UPLOAD_SIZE == null) IMG_UPLOAD_SIZE = "3";
    		
    		if (APND_UPLOAD_MAX == null) APND_UPLOAD_MAX = "10";
    		if (APND_UPLOAD_SIZE == null) APND_UPLOAD_SIZE = "3";
    		
    		if (SURVEY_UPLOAD_MAX == null) SURVEY_UPLOAD_MAX = "20";
    		if (SURVEY_VIEW_UPLOAD_MAX == null) SURVEY_VIEW_UPLOAD_MAX = "10";
    		
    		
    		if(!CommUtil.NumberChk(IMG_UPLOAD_MAX)) IMG_UPLOAD_MAX = "10";
    		if(!CommUtil.NumberChk(IMG_UPLOAD_SIZE)) IMG_UPLOAD_SIZE = "3";
    		if(!CommUtil.NumberChk(APND_UPLOAD_MAX)) APND_UPLOAD_MAX = "10";
    		if(!CommUtil.NumberChk(APND_UPLOAD_SIZE)) APND_UPLOAD_SIZE = "3";
    		if(!CommUtil.NumberChk(SURVEY_UPLOAD_MAX)) SURVEY_UPLOAD_MAX = "20";
    		if(!CommUtil.NumberChk(SURVEY_VIEW_UPLOAD_MAX)) SURVEY_VIEW_UPLOAD_MAX = "10";
    		
    		modelMap.put("imgUploadMax", IMG_UPLOAD_MAX);
    		modelMap.put("imgUploadSize", IMG_UPLOAD_SIZE);
    		modelMap.put("apndUploadMax", APND_UPLOAD_MAX);
    		modelMap.put("apndUploadSize", APND_UPLOAD_SIZE);
    		modelMap.put("surveyUploadMax", SURVEY_UPLOAD_MAX);
    		modelMap.put("surveyUploadView", SURVEY_VIEW_UPLOAD_MAX);
    		
    		if (upNotiId == null)
    		{
    			upNotiId = "";
    			modelMap.put("reply_list", "[]");
    		}
     		else  //답글인 경우 원본 내용을 조회     		
    		{
    			//게시물 기본정보
    			BbsNotiInfoVO notiVO = new BbsNotiInfoVO();
    			notiVO.setBoardId(boardId);
    			notiVO.setNotiId(upNotiId);	    			
    			List reply_list = board230Service.getBbsNotiInfoList(notiVO);
    			
    			modelMap.put("reply_list", CommUtil.scriptRemove(JSONUtils.objectToJSON(reply_list)));
    		}
    		
    		modelMap.put("upNotiId", upNotiId);
    		modelMap.put("tmpNotiSeq", tmpNotiSeq);
    		modelMap.put("moblOpenYN", moblOpenYN);
    		modelMap.put("moblOpenDiv", moblOpenDiv);
    		modelMap.put("agrmOppUseYn", agrmOppUseYn);
    		modelMap.put("apndFileSz", apndFileSz);
    		modelMap.put("editDiv", editDiv);
    		modelMap.put("opnWrteDiv", opnWrteDiv);
    		modelMap.put("replyPrmsDiv", replyPrmsDiv);
    		modelMap.put("nickUseYn", nickUseYn);
    		modelMap.put("pageIndex", pageIndex);
    		modelMap.put("isAdmin", isAdmin);
    		modelMap.put("userId", info.getId());
    		modelMap.put("notiWriteId", notiWriteId);
    		modelMap.put("makrDispDiv", makrDispDiv);
    		modelMap.put("WEB_DIR", CONTEXT_PATH + WEB_DIR);
    		modelMap.put("SAVE_DIR", SAVE_DIR);
    		modelMap.put("WEB_MOVIE_DIR", CONTEXT_PATH + WEB_MOVIE_DIR);
    		modelMap.put("boardExplUseYn", boardExplUseYn);
    		modelMap.put("boardExpl", boardExpl);
    		
		}
		return ".self/board/board230Write";
	}
    
    /**
     * 게시물 저장
     * @param modelMap
     * @return board/board230Write.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertBbsNotiInfo")
    @ResponseBody
    public ModelMap insertBbsNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
    		@RequestParam(value="contents" ,required = true) String contents,
 			ModelMap 		modelMap,
 			HttpSession session,
			HttpServletRequest request
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		BbsNotiInfoVO vo = new BbsNotiInfoVO(); 

 		try{	
 			//쓰기권한체크
			getBoardUserMapWriteYN(session, data, jsonResult);

			JSONObject jsonObject = JSONObject.fromObject(data);
			data = jsonObject.toString();

			vo = board100Service.insertBbsNotiInfo(data, session, request);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("");
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("notiList", vo);
 		
 		return modelMap;
 	}
    
    
    
    /**
     * 동영상 파일 업로드
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping("/movieFileUpload") 
    @ResponseBody 
    public void movieFileUpload(HttpServletRequest request, 
    		HttpServletResponse response,
    		ModelMap model,
    		HttpSession session) throws Exception{
    	
    	String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
		String WEB_DIR = PortalxpertConfigUtils.getString("upload.thumbnail.web");
		String BASIC_IMG = PortalxpertConfigUtils.getString("upload.thumbnail.file");
		String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
		int maxFileSize = PortalxpertConfigUtils.getInt("upload.file.size");
		   
		JSONArray jsonArr = FileUploadUtil.upload(request, SAVE_DIR, WEB_DIR, CONTEXT_PATH, maxFileSize);    	
    	
		if(jsonArr.size() > 0){
			JSONObject jsonObject = (JSONObject)jsonArr.get(0);
			
			int selectMovieKey = board230Service.selectMovieKey();
			
			String titleFilename = "/thumbnail/"+BASIC_IMG;
			String destDir = CommUtil.getDateString("yyyy/MM/dd");
			if (destDir.endsWith("/")){
				destDir = destDir.substring(0, destDir.length()-1);
			}
			
			BBSMovieVO bbsMovieVO = new BBSMovieVO();
			bbsMovieVO.setMvpKey(selectMovieKey+"");
			bbsMovieVO.setMvpFileNm(destDir + "/" + jsonObject.get("saveFileName"));
			bbsMovieVO.setTitleFileNm(titleFilename);
			bbsMovieService.insertBBSMovie(bbsMovieVO);
			jsonObject.put("nkey", selectMovieKey);
		}
    	HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response);
    	//wrapper.setContentType("text/plain");
    	//wrapper.setHeader("Content-length", String.valueOf(jsonArr.toString().length()));
    	response.getWriter().print(jsonArr.toString());
    	response.getWriter().flush();
    	response.getWriter().close();
    }    
    

    
    
    
    
    /**
     * 게시글 작성
     * @param modelMap
     * @return board230/board230Write.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/innoUpload")
	public String innoUpload(ModelMap modelMap,
			HttpSession session
			) throws Exception {

		return ".self/board/innoUpload";
	}
    
    /**
     * 게시물 임시저장
     * @param modelMap
     * @return board/board230Write.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertBbsTmpNotiInfo")
    @ResponseBody
    public ModelMap insertBbsTmpNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
    		@RequestParam(value="contents" ,required = true) String contents,
 			ModelMap 		modelMap,
 			HttpServletRequest request,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		BbsTmpNotiInfoVO vo = new BbsTmpNotiInfoVO(); 

 		try{	
 			
 			//쓰기권한체크
			getBoardUserMapWriteYN(session, data, jsonResult);

			JSONObject jsonObject = JSONObject.fromObject(data);
			data = jsonObject.toString();

			vo = board230Service.insertBbsTmpNotiInfo(data, request, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage("");
			
 		}catch(Exception e){
 			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
 		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		modelMap.put("notiList", vo);
 		
 		return modelMap;
 	}


    

    
    /**
     * 게시판 사용자 권한 체크
     * @param HttpSession, data
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    private String getBoardUserMapYN(HttpSession session, String boardId)throws Exception {
    	
    	String isAdmin = "N";
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	
    	
    	if( superAdmin.equals(Constant.ROLE_SUPER.getVal()))
    	{
    		return "Y";
    	}
    	
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String userMap = board100Service.getUserBbsMapList(info.getId());
		
		
		
		
		BbsBoardInfoVO vo = new BbsBoardInfoVO();
		vo.setBoardId(boardId);
		vo.setUserMap(userMap);
		List<BbsBoardInfoVO> list = board100Service.getBoardUserMapInfo(vo);
		BbsBoardInfoVO bbsInfo = list.get(0);
		
		logger.debug("getBoardName : "+bbsInfo.getBoardName());
		logger.debug("getBoardId : "+bbsInfo.getBoardId());
		logger.debug("superAdmin : "+superAdmin);
		logger.debug("getAdmYn : "+bbsInfo.getAdmYn());
		logger.debug("getWrtYn : "+bbsInfo.getWrtYn());
		
		if( superAdmin.equals(Constant.ROLE_SUPER.getVal())
    			|| bbsInfo.getAdmYn().equals("Y")){ 
    			//|| bbsInfo.getWrtYn().equals("Y")){
			isAdmin = "Y";
    	}
		
    	return isAdmin;
    }
    
    /**
     * 게시판 쓰기 권한 체크
     * @param HttpSession, data
     * @return String
     * @exception Exception
     */
    private String getBoardUserMapWriteYN(HttpSession session, String data, JSONResult jsonResult)throws Exception {
    	
    	JSONObject bbsNotiObject = JSONObject.fromObject(data);
    	String boardId = (String)bbsNotiObject.get("boardId");
    	
    	String isWrite = "N";
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	
    	
    	if( superAdmin.equals(Constant.ROLE_SUPER.getVal()))
    	{
    		return "Y";
    	}
    	
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String userMap = board100Service.getUserBbsMapList(info.getId());
		
		
		
		
		BbsBoardInfoVO vo = new BbsBoardInfoVO();
		vo.setBoardId(boardId);
		vo.setUserMap(userMap);
		List<BbsBoardInfoVO> list = board100Service.getBoardUserMapInfo(vo);
		BbsBoardInfoVO bbsInfo = list.get(0);
		
		logger.debug("getBoardName : "+bbsInfo.getBoardName());
		logger.debug("getBoardId : "+bbsInfo.getBoardId());
		logger.debug("superAdmin : "+superAdmin);
		logger.debug("getAdmYn : "+bbsInfo.getAdmYn());
		logger.debug("getWrtYn : "+bbsInfo.getWrtYn());
		
		if( superAdmin.equals(Constant.ROLE_SUPER.getVal())
    			|| bbsInfo.getAdmYn().equals("Y")
    			|| bbsInfo.getWrtYn().equals("Y")){
			isWrite = "Y";
    	}
		
		if(!"Y".equals(isWrite)){ //게시판권한
			throw new PortalxpertException("권한이 없습니다.");
		}else{
			JSONObject bbsObject = JSONObject.fromObject(data);
			String notiId = (String)bbsObject.get("notiId");
			if(!CommUtil.isEmpty(notiId)){ //수정
				List<BbsNotiInfoVO> notiInfo = board210Service.getBbsNotiInfoView(data);
				BbsNotiInfoVO notiVo = notiInfo.get(0);
				
				if( superAdmin.equals(Constant.ROLE_SUPER.getVal())
		    			|| bbsInfo.getAdmYn().equals("Y")
		    			|| notiVo.getRegrId().equals(info.getId())){
					isWrite = "Y";
		    	}else{
		    		isWrite = "N";
					throw new PortalxpertException("권한이 없습니다.");
		    	}
		    	
			}
		}
		
    	return isWrite;
    }    
    
    
    /**
     * 게시판 설명 팝업
     * @param modelMap
     * @return board/bbsBoardExplPopup.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/bbsBoardExplPopup")
	public String bbsBoardExplPopup(ModelMap modelMap,
			@RequestParam(value="boardId", required = true) String boardId,
			HttpSession session			
			) throws Exception {
    	String boardExpl = "";
    	BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		List board_list = board100Service.getAdminBbsBoardInfoList(bbsVO);
		if (board_list.size() > 0)
		{
			BbsBoardInfoVO bbs = (BbsBoardInfoVO)board_list.get(0);
			boardExpl = bbs.getBoardExpl();
			
			if(boardExpl != null) boardExpl = boardExpl.replaceAll("\r\n","<br>");
			else boardExpl = "";
		}
		modelMap.put("boardExpl", boardExpl);
    	return ".self/board/bbsBoardExplPopup";
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
    	//wrapper.setContentType("text/plain");
		response.getWriter().print(jsonArr.toString());
		response.getWriter().flush();
		response.getWriter().close();
 	}

   
    
}
