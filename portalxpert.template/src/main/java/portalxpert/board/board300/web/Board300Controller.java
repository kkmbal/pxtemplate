package portalxpert.board.board300.web;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.board.board300.sc.Board300Service;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.FileUploadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
@RequestMapping("board300")
public class Board300Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board300Controller.class); 
   
	/** board230Service */
    @Resource(name = "board230Service")
    private Board230Service board230Service;
    
    /** board300Service */
    @Resource(name = "board300Service")
    private Board300Service board300Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 개인 게시판 프레임
     * @param modelMap
     * @return board/pbs100Frame.jsp
     * @throws Exception
     */
    @RequestMapping(value="/pbsFrame")
    public String pbs100Frame(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="lnbKind",required = true) String lnbKind,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			HttpSession session
 			)
            throws Exception {
    	
    	String rtnPage = "";
    	BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		/*List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);
		BbsBoardInfoVO bbsInfo = null;
		if (list.size() > 0)
		{
			bbsInfo = list.get(0);
		}*/
		
		if (lnbKind.equals("BBS"))
		{
			rtnPage = ".board/pbs/pbsFrame";
		}
		else if (lnbKind.equals("PSN"))
		{
			rtnPage = ".person/pbs/pbsFrame";
		}
		
		logger.debug("page : "+".board/" + rtnPage);
		
		modelMap.put("pbs", "Y");
    	modelMap.put("pBoardId", boardId);
    	modelMap.put("pageIndex", pageIndex);
    	 
        return rtnPage;     
    }
    
    
   
    
    /**
     * 개인 게시글 작성
     * @param modelMap
     * @return board300/board300Write.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/board300Write")
	public String board300Write(ModelMap modelMap,
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
    	if (boardId != null)
		{
    		if (kind.equals("BBS"))  //개인인게시물
    		{
    			PbsUserBoardInfoVO bbsVO = new PbsUserBoardInfoVO();
    			bbsVO.setBoardId(boardId);
    			List board_list = board300Service.getPbsUserBoardInfoList(bbsVO);
    			
    			if (board_list.size() > 0)
    			{
    				PbsUserBoardInfoVO bbs = (PbsUserBoardInfoVO)board_list.get(0);
    				boardForm = bbs.getBoardForm();
    				boardFormSpec = bbs.getBoardFormSpec();
    				notiReadmanAsgnYn = bbs.getNotiReadmanAsgnYn();
    				boardName = bbs.getBoardName();
    				boardKind = bbs.getBoardKind();
    				//basicCloseDttm = bbs.getBasicCloseDttm();
    			}
    			
    			PbsUserBoardPartInfoVO userVO = new PbsUserBoardPartInfoVO();
	    		userVO.setBoardId(bbsVO.getBoardId());
	    		List user_list = board300Service.getPbsUserBoardPartInfoList(userVO);
	    		
	    		if (userNotiSeq != null)  //userNotiSeq가 있을 경우는 수정 인 경우
	    		{
	    			//게시물 기본정보
	    			PbsUserNotiInfoVO notiVO = new PbsUserNotiInfoVO();
	    			//notiVO.setBoardId(boardId);
	    			notiVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));	    			
	    			List noti_list = board300Service.getPbsUserNotiInfoList(notiVO);
	    			
	    			//게시물 첨부 조회 
	    			PbsUserNotiApndFileVO apndVO = new PbsUserNotiApndFileVO();
	    			apndVO.setUserNotiSeq(Integer.parseInt(userNotiSeq));	    			
	    			List apnd_list = board300Service.getPbsUserNotiApndFileList(apndVO);
	    			
	    			String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
	    			String WEB_DIR = CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web");
	    			
	    			for (int i = 0; i < apnd_list.size(); i++)
	    			{
	    				PbsUserNotiApndFileVO vo = (PbsUserNotiApndFileVO)apnd_list.get(i);
	    				if (vo.getApndFileName().indexOf(".") > 0)
	    				{
	    					String str =  vo.getApndFileName().substring(0, vo.getApndFileName().indexOf("."));
	    					vo.setSaveFileId(str);
	    				}
	    				vo.setApndFilePath( WEB_DIR +"/" +vo.getApndFilePath());
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
	    			
	    			modelMap.put("notiList", JSONUtils.objectToJSON(noti_list));
	    			modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
	    			modelMap.put("userMapList", JSONUtils.objectToJSON(user_list));
	    			modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
	    			
	    		}
	    		else
	    		{
	    			modelMap.put("notiList", "[]");
	    			modelMap.put("apndList", "[]");
	    			modelMap.put("userList", "[]");
	    			modelMap.put("surveyList", "[]");
	    			modelMap.put("surveyExmplList", "[]");
	    		}
	    		
	    		modelMap.put("userMapList", JSONUtils.objectToJSON(user_list));
	    		modelMap.put("tmpNotiList", "[]");
    			modelMap.put("tmpApndList", "[]");
    			modelMap.put("tmpUserList", "[]");
    			modelMap.put("tmpSurveyList", "[]");
    			modelMap.put("tmpSurveyExmplList", "[]");
    		}    		
    		
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
    			
    			List reply_list = board300Service.getPbsUserNotiInfoList(notiVO);
    			
    			modelMap.put("reply_list", JSONUtils.objectToJSON(reply_list));
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
		}
		return ".self/board/board300Write";
	}
    

    /**
     * 개인 게시물 저장
     * @param modelMap
     * @return board/board300Write.jsp
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
 			String strMimeValue = contents;
 			
	 		vo = board300Service.insertPbsUserNotiInfo(data, session, request);
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
     * @return board/board300write.jsp
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
     * 게시글 인쇄
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/pbsPrintPreview" , method = RequestMethod.GET)
    public String bbsPrintPreview(
			ModelMap modelMap,
			@RequestParam(value="boardId",required = false) String boardId,
			@RequestParam(value="userNotiSeq",required = false) String userNotiSeq,
			@RequestParam(value="boardKind",required = false) String boardKind,
			@RequestParam(value="pnum",required = false) String pnum,
			HttpSession session
			)
            throws Exception {

    	modelMap.put("userNotiSeq", userNotiSeq);
    	modelMap.put("boardId", boardId);
    	modelMap.put("boardKind", boardKind);
    	modelMap.put("pnum", pnum);
    	
        return ".self/pbs/pbsPrintPreview";
    }
        
}
