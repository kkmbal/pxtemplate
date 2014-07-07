package portalxpert.board.board330.web;

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

import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.board.board300.sc.Board300Service;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.board.board310.sc.Board310Service;
import portalxpert.board.board330.sc.Board330Service;
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
@RequestMapping("board330")
public class Board330Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board330Controller.class); 
   
	/** board230Service */
    @Resource(name = "board230Service")
    private Board230Service board230Service;
    
    /** board330Service */
    @Resource(name = "board330Service")
    private Board330Service board330Service;
    
    /** board300Service */
    @Resource(name = "board300Service")
    private Board300Service board300Service;
    
    /** board300Service */
    @Resource(name = "board310Service")
    private Board310Service board310Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 개인 게시글 작성
     * @param modelMap
     * @return board330/board330Write.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/board330Write")
	public String board330Write(ModelMap modelMap,
			HttpServletRequest request,
			HttpSession session,
			@RequestParam(value="boardId", required = true) String boardId,
			@RequestParam(value="userNotiSeq", required = false) String userNotiSeq,
			@RequestParam(value="upUserNotiSeq", required = false) String upUserNotiSeq,
			@RequestParam(value="kind", required = true) String kind  //게시물 종류(TMP, BBS, PSN)  //임시저장, 공용, 개인, 경조사, 폐쇄 
			) throws Exception {
    	
    	String boardName = "";
    	String boardForm = Constant.BOARD_FORM_010.getVal();
    	String boardFormSpec = Constant.BOARD_FORM_SPEC_010.getVal(); 
    	String notiReadmanAsgnYn = "N";
    	String boardKind = Constant.BOARD_KIND_010.getVal();   //일반형/폐쇄/경조사
    	//String basicCloseDttm = "";
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
    	String SAVE_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
    	String WEB_MOVIE_DIR = PortalxpertConfigUtils.getString("upload.thumbnail.web");
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	String boardExplUseYn = "";
    	
    	if (userNotiSeq == null)
    	{
    		userNotiSeq = "0";
    	}
    	
    	if (boardId != null)
		{
    		
			PbsUserBoardInfoVO bbsVO = new PbsUserBoardInfoVO();
			bbsVO.setBoardId(boardId);
			List board_list = board330Service.getPbsUserBoardInfoList(bbsVO);
			
			if (board_list.size() > 0)
			{
				PbsUserBoardInfoVO bbs = (PbsUserBoardInfoVO)board_list.get(0);
				boardForm = bbs.getBoardForm();
				boardFormSpec = bbs.getBoardFormSpec();
				notiReadmanAsgnYn = bbs.getNotiReadmanAsgnYn();
				boardName = bbs.getBoardName();
				boardKind = bbs.getBoardKind();
				boardExplUseYn = bbs.getBoardExplUseYn();
				//basicCloseDttm = bbs.getBasicCloseDttm();
			}
			
			//권한정보 조회(현재는 안쓰임)
			/*PbsUserBoardPartInfoVO userVO = new PbsUserBoardPartInfoVO();
    		userVO.setBoardId(bbsVO.getBoardId());
    		List user_list = board330Service.getPbsUserBoardPartInfoList(userVO);*/
    		
    		if (Integer.parseInt(userNotiSeq) > 0)  //userNotiSeq가 있을 경우는 수정 인 경우
    		{
    			//게시물 기본정보
    			PbsUserNotiInfoVO notiVO = new PbsUserNotiInfoVO();
    			//notiVO.setBoardId(boardId);
    			notiVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));	    			
    			List noti_list = board330Service.getPbsUserNotiInfoList(notiVO);
    			
    			//게시물 첨부 조회 
    			PbsUserNotiApndFileVO apndVO = new PbsUserNotiApndFileVO();
    			apndVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));	    			
    			List apnd_list = board330Service.getPbsUserNotiApndFileList(apndVO);
    			
    			
    			
    			for (int i = 0; i < apnd_list.size(); i++)
    			{
    				PbsUserNotiApndFileVO vo = (PbsUserNotiApndFileVO)apnd_list.get(i);
    				if (vo.getApndFileName().indexOf(".") > 0)
    				{
    					String str =  vo.getApndFileName().substring(0, vo.getApndFileName().indexOf("."));
    					vo.setSaveFileId(str);
    				}
    				//vo.setApndFilePath( WEB_DIR +"/" +vo.getApndFilePath());
    			}
    				    			
    			//설문 정보 조회
    			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
    			surveyVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));	    			
    			List survey_list = board230Service.getBbsNotiSurveyList(surveyVO);
    			
    			List surveyExmpl_list = null;
    			
    			if (survey_list.size() > 0)
    			{
    				// 설문 보기 정보 조회
    				BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
	    			surveyExmplVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));
	    			surveyExmpl_list = board230Service.getBbsNotiSurveyExmplList(surveyExmplVO);
	    			modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
    			}
    			else
    			{
    				modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
    			}
    			
    			modelMap.put("notiList", CommUtil.scriptRemove(JSONUtils.objectToJSON(noti_list)));
    			modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
    			modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
    			
    		}
    		else
    		{
    			modelMap.put("notiList", "[]");
    			modelMap.put("apndList", "[]");
    			modelMap.put("surveyList", "[]");
    			modelMap.put("surveyExmplList", "[]");
    		}
    		
    		//modelMap.put("userMapList", JSONUtils.objectToJSON(user_list));
    		
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
        	String innoApEnc = PortalxpertConfigUtils.getString("person.upload.innoap.enc"); 
     		String innoApkey1 = PortalxpertConfigUtils.getString("person.upload.innoap.key1"); 
     		String innoApkey2 = PortalxpertConfigUtils.getString("person.upload.innoap.key2"); 
     		
     		if (userNotiSeq == null) userNotiSeq = "0";
     		
     		//modelMap.put("basicCloseDttm", basicCloseDttm);
     		
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
    		
    		if (upUserNotiSeq == null)
    		{
    			upUserNotiSeq = "0";
    			modelMap.put("reply_list", "[]");
    		}
     		else  //답글인 경우 원본 내용을 조회     		
    		{
    			//게시물 기본정보
     			PbsUserNotiInfoVO notiVO = new PbsUserNotiInfoVO();
    			notiVO.setBoardId(boardId);
    			notiVO.setUserNotiSeq(Integer.parseInt(upUserNotiSeq));
    			
    			List reply_list = board330Service.getPbsUserNotiInfoList(notiVO);
    			
    			modelMap.put("reply_list", CommUtil.scriptRemove(JSONUtils.objectToJSON(reply_list)));
    		}
    		
    		modelMap.put("innoApEnc", innoApEnc);
     		modelMap.put("innoApkey1", innoApkey1);
     		modelMap.put("innoApkey2", innoApkey2);
     		
    		modelMap.put("userName", info.getName());
     		modelMap.put("boardId", boardId);
     		modelMap.put("boardKind", boardKind);
     		modelMap.put("boardForm", boardForm);
     		modelMap.put("boardFormSpec", boardFormSpec);
     		modelMap.put("notiReadmanAsgnYn", notiReadmanAsgnYn);
     		modelMap.put("boardName", boardName);
     		modelMap.put("kind", kind);
     		modelMap.put("userNotiSeq", userNotiSeq);
     		modelMap.put("upUserNotiSeq", upUserNotiSeq);
     		
    		modelMap.put("imgUploadMax", IMG_UPLOAD_MAX);
    		modelMap.put("imgUploadSize", IMG_UPLOAD_SIZE);
    		modelMap.put("apndUploadMax", APND_UPLOAD_MAX);
    		modelMap.put("apndUploadSize", APND_UPLOAD_SIZE);
    		modelMap.put("surveyUploadMax", SURVEY_UPLOAD_MAX);
    		modelMap.put("surveyUploadView", SURVEY_VIEW_UPLOAD_MAX);    		
    		modelMap.put("WEB_DIR", CONTEXT_PATH + WEB_DIR);    		
    		modelMap.put("SAVE_DIR", SAVE_DIR); 
    		modelMap.put("WEB_MOVIE_DIR", CONTEXT_PATH + WEB_MOVIE_DIR); 
    		modelMap.put("userId", info.getId()); 
    		modelMap.put("ouCode", info.getOucode());
    		
    		String apndFileSz = PortalxpertConfigUtils.getString("pbs.upload.file.size");
    		if (apndFileSz == null) apndFileSz = "1000";
    		modelMap.put("apndFileSz", apndFileSz);
    		modelMap.put("apndUseYn", board330Service.getPbsUserNotiApndFileSize(info.getId()));
    		modelMap.put("nojoYn", "");
    		modelMap.put("boardExplUseYn", boardExplUseYn);
    		
		}
		return ".self/pbs/board330Write";
	}
    
    public boolean __NumberChk(String str){
        char c;

        if(str.equals("")) return false;
        
        for(int i = 0 ; i < str.length() ; i++){
            c = str.charAt(i);
            if(c < 48 || c > 57){
                return false;
            }
        }
        return true;
    }

    /**
     * 개인 게시물 저장
     * @param modelMap
     * @return board/board330Write.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/insertPbsNotiInfo")
    @ResponseBody
    public ModelMap insertPbsNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
    		//@ModelAttribute("tagFreeVO") TagFreeVO tagFreeVO,
    		@RequestParam(value="contents" ,required = true) String contents,
 			ModelMap 		modelMap,
 			HttpSession session,
			HttpServletRequest request
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		PbsUserNotiInfoVO vo = new PbsUserNotiInfoVO(); 

 		try{	
 			
 			//쓰기권한체크
 			getPbsBoardUserMapWriteYN(session, data, jsonResult);
 			
 			JSONObject jsonObject = JSONObject.fromObject(data);
 			
 			data = jsonObject.toString();
		
 			vo = board330Service.insertPbsUserNotiInfo(data, session, request);
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
     * 개인 게시판 이미지 파일 업로드
     * @param modelMap
     * @return board/board330write.jsp
     * @throws Exception
     */
    @RequestMapping("/pbsFileUpload") 
    @ResponseBody 
    public void pbsFileUpload(HttpServletRequest request, 
 			HttpServletResponse response,
 			ModelMap model,
 			HttpSession session) throws Exception{
 	  
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
    

   
    
    
    /**
     * 개인 게시판 설명 팝업
     * @param modelMap
     * @return pbs/pbsBoardExplPopup.jsp
     * @throws Exception
     */
	    
    @RequestMapping(value = "/pbsBoardExplPopup")
	public String pbsBoardExplPopup(ModelMap modelMap,
			@RequestParam(value="boardId", required = true) String boardId,
			HttpSession session			
			) throws Exception {
    	String boardExpl = "";
    	PbsUserBoardInfoVO bbsVO = new PbsUserBoardInfoVO();
		bbsVO.setBoardId(boardId);
		List board_list = board330Service.getPbsUserBoardInfoList(bbsVO);
		if (board_list.size() > 0)
		{
			PbsUserBoardInfoVO bbs = (PbsUserBoardInfoVO)board_list.get(0);
			boardExpl = bbs.getBoardExpl();
			
			if(boardExpl != null) boardExpl = boardExpl.replaceAll("\r\n","<br>");
			else boardExpl = "";
		}
		modelMap.put("boardExpl", boardExpl);
    	return ".self/pbs/pbsBoardExplPopup";
	}
    
    /**
     * 게시판 쓰기 권한 체크
     * @param HttpSession, data
     * @return String
     * @exception Exception
     */
    public String getPbsBoardUserMapWriteYN(HttpSession session, String data, JSONResult jsonResult)throws Exception {
    	
    	JSONObject bbsNotiObject = JSONObject.fromObject(data);
    	String boardId = (String)bbsNotiObject.get("boardId");
    	
    	String isWrite = "N";
    	
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String auth = board300Service.getUserBbsMapList(info.getId());
		
		PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();			
		pbsVO.setBoardId(boardId);
		pbsVO.setUserId(info.getId());
		pbsVO.setUserMap(auth);
		
		List<PbsUserBoardInfoVO> board_list = board300Service.getPbsUserBoardInfoList(pbsVO);//개인 게시판 조회
		
		if (board_list.size() > 0)
		{
			pbsVO = (PbsUserBoardInfoVO)board_list.get(0);
			isWrite = pbsVO.getPartYn();
		}
		
		if(!"Y".equals(isWrite)){ //게시판권한
			throw new PortalxpertException("권한이 없습니다.");
		}else{
			JSONObject bbsObject = JSONObject.fromObject(data);
			int seq = Integer.parseInt((String)bbsObject.get("userNotiSeq"));
			if(seq != 0){ //수정
				List<PbsUserNotiInfoVO> notiInfo = board310Service.getPbsUserNotiInfoList(data);
				PbsUserNotiInfoVO notiVo = notiInfo.get(0);
				
				if(!info.getId().equals(notiVo.getRegrId())){
					isWrite = "N";
					throw new PortalxpertException("권한이 없습니다.");
				}
			}
		}
		
    	return isWrite;
    }  
        
}
