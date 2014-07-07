package portalxpert.board.board310.web;


import java.net.InetAddress;
import java.net.URLDecoder;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.board.board300.sc.Board300Service;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiEvalInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.board.board310.sc.Board310Service;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("board310")
public class Board310Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board310Controller.class); 
   
	/** board100Service */
    @Resource(name = "board100Service")
    private Board100Service board100Service;
    
    /** board300Service */
    @Resource(name = "board300Service")
    private Board300Service board300Service;
    
    /** board230Service */
    @Resource(name = "board230Service")
    private Board230Service board230Service;
    
    /** board310Service */
    @Resource(name = "board310Service")
    private Board310Service board310Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 게시판의 생성유저 여부
     * @param HttpSession, BbsBoardInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getPbsBoardOwnrYN(HttpSession session, PbsUserBoardInfoVO pbs)throws Exception {
    	
    	String yn = "N";
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	if(info.getId().equals(pbs.getBoardOwnrId())){
    		yn = "Y";
    	}
    	
    	logger.debug("getBoardBtnViewYN : "+yn);
    	
    	return yn;
    }
    
    /**
     * 개인게시판 게시판 
     * @param modelMap
     * @return board/basicPbsBoardList.jsp
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getPbsBoardInfoList")
    public String getPbsBoardInfoList(
 			ModelMap modelMap,
			//@RequestParam(value="callback" ,required = true) String callback,
 			@ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			@RequestParam(value="pageUnit",required = false) String pageUnit, 			
 			@RequestParam(value="searchCondition",required = false) String searchCondition,
 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
 			@RequestParam(value="regDttmFrom",required = false) String regDttmFrom,
 			@RequestParam(value="regDttmTo",required = false) String regDttmTo,
 			@RequestParam(value="orderType",required = false) String orderType,
 			@RequestParam(value="isDesc",required = false) boolean isDesc,
 			@RequestParam(value="listYn",required = false) String listYn,
 			@RequestParam(value="fh",required = false) String fh,
 			HttpSession session,
 			HttpServletRequest request
 			)
            throws Exception {
    	
 
    		
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			String auth = board300Service.getUserBbsMapList(info.getId());
			logger.debug("auth : "+auth);
			auth = auth==null?"null":auth;
			
			PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();			
			pbsVO.setBoardId(boardId);
			pbsVO.setUserId(info.getId());
			pbsVO.setUserMap(auth);
			
			List<PbsUserBoardInfoVO> board_list = board300Service.getPbsUserBoardInfoList(pbsVO);//개인 게시판 조회
			
			if (board_list.size() > 0)
			{
				pbsVO = (PbsUserBoardInfoVO)board_list.get(0);
			}

			/** PropertyService.sample */
			boardSearchVO.setPageUnit(Integer.parseInt(pageUnit));
			boardSearchVO.setPageSize(propertiesService.getInt("pageSize"));
			boardSearchVO.setPageIndex(Integer.parseInt(pageIndex));
	    	
	    	/** pageing setting */
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(boardSearchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(boardSearchVO.getPageUnit());
			paginationInfo.setPageSize(boardSearchVO.getPageSize());
			
			boardSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			boardSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			boardSearchVO.setBoardId(boardId);
			boardSearchVO.setSearchCondition(searchCondition);
			boardSearchVO.setSearchKeyword(searchKeyword);
			boardSearchVO.setUserId(info.getId());
			boardSearchVO.setRegDttmFrom(regDttmFrom);
			boardSearchVO.setRegDttmTo(regDttmTo);
			boardSearchVO.setUserMap(auth);
			boardSearchVO.setOrderType(orderType);
			boardSearchVO.setIsDesc(isDesc);
			
			logger.debug("사용자권한 : "+auth);
			logger.debug("isDesc : "+isDesc);
			
			List noti_list = board310Service.getPbsNotiUserInfoListForPaging(boardSearchVO);//게시글 조회
			int totCnt = board310Service.getPbsUserNotiInfoListTotCnt(boardSearchVO);
			
			paginationInfo.setTotalRecordCount(totCnt);
			
			searchKeyword = searchKeyword == null?"":searchKeyword;
			searchKeyword = URLDecoder.decode(searchKeyword,"UTF-8");
			
			modelMap.put("host", "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort());
			modelMap.put("boardKind", pbsVO.getBoardKind());
			modelMap.put("makrDispDiv", pbsVO.getMakrDispDiv());
			modelMap.put("boardSearchVO", boardSearchVO);
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageUnit", pageUnit);
			modelMap.put("boardId", boardId);
			modelMap.put("notiList", noti_list);
			modelMap.put("regDttmFrom", regDttmFrom);
			modelMap.put("regDttmTo", regDttmTo);
			modelMap.put("boardName", pbsVO.getBoardName());
			modelMap.put("replyWrteDiv", pbsVO.getReplyWrteDiv());
			modelMap.put("paginationInfo", paginationInfo);
			modelMap.put("isDesc", !isDesc);
			modelMap.put("searchCondition", searchCondition);
			modelMap.put("searchKeyword", searchKeyword);
			modelMap.put("pbs", "Y");
			modelMap.put("listYn", listYn);
			modelMap.put("fh", fh);
			modelMap.put("replyWrteDiv", pbsVO.getReplyWrteDiv());//답글쓰기
			modelMap.put("wrtUserYn", pbsVO.getPartYn());//참여자는쓰기권한유저(작성자포함)
			modelMap.put("boardOwnrYn", getPbsBoardOwnrYN(session, pbsVO));//게시판주인
			modelMap.put("userId", info.getId());//접속유저
			
 
    
        return ".self/pbs/pbsBasicBoardList";
    }
    
    /**
     * 게시글 상세보기 View
     * @param modelMap
     * @return board/basicBoardView
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getPbsBasicBoardView")
    public String getBasicBoardView(
 			ModelMap modelMap,
 			@RequestParam(value="boardId" ,required = true) String boardId,
 			@RequestParam(value="userNotiSeq" ,required = true) int userNotiSeq,
 			@RequestParam(value="pageIndex" ,required = false) String pageIndex,
 			@RequestParam(value="pageUnit" ,required = false) String pageUnit,
 			@RequestParam(value="searchCondition" ,required = false) String searchCondition,
 			@RequestParam(value="searchKeyword" ,required = false) String searchKeyword,
 			@RequestParam(value="regDttmFrom" ,required = false) String regDttmFrom,
 			@RequestParam(value="regDttmTo" ,required = false) String regDttmTo,
 			@RequestParam(value="orderType" ,required = false) String orderType,
 			@RequestParam(value="isDesc" ,required = false) String isDesc,
 			HttpServletRequest request,
 			HttpSession session
 			)
            throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	logger.debug("getBasicBoardView boardId : "+boardId);
    	
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
		String auth = board300Service.getUserBbsMapList(info.getId());
		logger.debug("auth : "+auth);
		PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();			
		pbsVO.setBoardId(boardId);
		pbsVO.setUserId(info.getId());
		pbsVO.setUserMap(auth);
		
		List<PbsUserBoardInfoVO> board_list = board300Service.getPbsUserBoardInfoList(pbsVO);//개인 게시판 조회
    	PbsUserBoardInfoVO pbsInfo = board_list.get(0);
		PbsUserNotiInfoVO notiVo = new PbsUserNotiInfoVO();
		notiVo.setBoardId(boardId);
		notiVo.setUserNotiSeq(userNotiSeq);
		notiVo.setUserMap(auth);
		notiVo.setNotiKind(pbsInfo.getBoardForm());
		notiVo.setUserId(info.getId());
		
		int pnum = board300Service.getPbsMyPnumInfo(notiVo);
    	int prev_pnum = 0, next_pnum = 0;
    	if(pnum > 0){
    		prev_pnum = pnum -1;
    		next_pnum = pnum +1;
    	}
    	logger.debug("pnum : "+pnum);
    	logger.debug("prev_pnum : "+prev_pnum);
    	logger.debug("next_pnum : "+next_pnum);
    	
    	
    	
    	PbsUserNotiEvalInfoVO vo = new PbsUserNotiEvalInfoVO();
		vo.setUserNotiSeq(userNotiSeq);
		vo.setNotiEvalDiv("040");
		vo.setUserId(info.getId());
		vo.setUserName(info.getName());
		vo.setRegrId(info.getId());
		vo.setRegrName(info.getName());
		vo.setUpdrId(info.getId());
		vo.setUpdrName(info.getName());
		
		board310Service.insertPbsUserNotiEvalInfo(vo);//읽음처리 조회수++, 
		
		BbsNotiInfoVO bbsNotiInfoVO = board310Service.getPbsUserNotiInfoViewForNotiConts(userNotiSeq);
		String notiConts = bbsNotiInfoVO==null?"":bbsNotiInfoVO.getNotiConts();
		if(notiConts != null) notiConts = notiConts.replaceAll("\r\n","<br>");
		else notiConts = "";
		
		if( bbsNotiInfoVO != null){
//			EAMCrypt ec = new EAMCrypt();
//			modelMap.put("regrIdEncrypt", Encrypter.Encrypt(ec.decrypt(bbsNotiInfoVO.getRegrId())));
		}
		
		
		modelMap.put("notiConts", CommUtil.scriptRemove(notiConts));
		modelMap.put("boardId", boardId);
    	modelMap.put("pnum", pnum);
    	modelMap.put("prev_pnum", prev_pnum);
    	modelMap.put("next_pnum", next_pnum);
    	modelMap.put("boardName", pbsInfo.getBoardName());
    	modelMap.put("boardKind", pbsInfo.getBoardKind());
    	modelMap.put("boardForm", pbsInfo.getBoardForm());
    	modelMap.put("boardFormSpec", pbsInfo.getBoardFormSpec());
    	modelMap.put("replyWrteDiv", pbsInfo.getReplyWrteDiv());//답변쓰기 구분
    	modelMap.put("userNotiSeq", userNotiSeq);
    	modelMap.put("userId", info.getId());
    	modelMap.put("pageIndex", pageIndex);
    	modelMap.put("pageUnit", pageUnit);
    	modelMap.put("likeUseYn", pbsInfo.getLikeUseYn());//좋아요_사용_여부
    	modelMap.put("agrmOppUseYn", pbsInfo.getAgrmOppUseYn());//찬성_반대_사용_여부
    	modelMap.put("nickUseYn", pbsInfo.getNickUseYn());//닉네임_사용_여부
    	modelMap.put("imgSvrUrl", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web"));
    	modelMap.put("imgRealDir", PortalxpertConfigUtils.getString("upload.real.dir"));
    	modelMap.put("movDir", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.thumbnail.web"));
    	modelMap.put("replyWrteDiv", pbsInfo.getReplyWrteDiv());//답글쓰기
		modelMap.put("wrtUserYn", pbsInfo.getPartYn());//참여자는쓰기권한유저(작성자포함)
		modelMap.put("boardOwnrYn", getPbsBoardOwnrYN(session, pbsInfo));//게시판주인
		modelMap.put("searchCondition", searchCondition);
		modelMap.put("searchKeyword", searchKeyword);
		modelMap.put("regDttmFrom", regDttmFrom);
		modelMap.put("regDttmTo", regDttmTo);
		modelMap.put("orderType", orderType);
		modelMap.put("isDesc", isDesc);
		modelMap.put("realWeb", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web"));
		
		String innofdEnc = PortalxpertConfigUtils.getString("person.upload.innofd.enc"); 
 		String innofdkey1 = PortalxpertConfigUtils.getString("person.upload.innofd.key1"); 
 		String innofdkey2 = PortalxpertConfigUtils.getString("person.upload.innofd.key2"); 
 		
 		modelMap.put("innofdEnc", innofdEnc);
 		modelMap.put("innofdkey1", innofdkey1);
 		modelMap.put("innofdkey2", innofdkey2);
    	
        return ".self/pbs/pbsBasicBoardView";
    }
    
    /**
     * 게시글 상세보기 DATA 가져오기
     * @param modelMap
     * @return modelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getPbsNotiDetailInfoView")
    public ModelMap getNotiDetailInfoView(
 			@RequestParam(value="data" ,required = true) String data,
 			@RequestParam(value="pnum" ,required = false) int pnum,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			int prev_pnum = 0, next_pnum = 0;
	    	if(pnum > 0){
	    		prev_pnum = pnum -1;
	    		next_pnum = pnum +1;
	    	}
			
			JSONObject bbsObject = JSONObject.fromObject(data);
			
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));
			logger.debug("notiId : "+(String)bbsObject.get("notiId"));
			int userNotiSeq = Integer.parseInt((String)bbsObject.get("userNotiSeq"));
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			String auth = board300Service.getUserBbsMapList(info.getId());
			logger.debug("auth : "+auth);
			logger.debug("prev_pnum : "+prev_pnum);
			logger.debug("next_pnum : "+next_pnum);
			
			
			List notiInfo = board310Service.getPbsUserNotiInfoList(data);
			List notiFile = board310Service.getBbsNotiApndFileList(data);
			List<PbsUserNotiInfoVO> notiPrevNextInfo = board310Service.getPbsPrevNextNotiInfo(data, auth,prev_pnum,next_pnum, info.getId() );
			
			List notiOpn1 = board310Service.getPbsUserNotiOpnList1(data);
			List notiOpn2 = board310Service.getPbsUserNotiOpnList2(data);
			PbsUserNotiInfoVO vo = (PbsUserNotiInfoVO) notiInfo.get(0);

			List survey_list = null;
			if(vo.getNotiKind() !=null && vo.getNotiKind().equals(Constant.NOTI_KIND_040.getVal())){//설문
				//설문 정보 조회
    			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
    			surveyVO.setUserNotiSeq(userNotiSeq);	    			
    			survey_list = board230Service.getBbsNotiSurveyList(surveyVO);
    			
    			List surveyExmpl_list = null;
    			logger.debug("survey_list.size() : "+survey_list.size());
    			if (survey_list.size() > 0)
    			{
    				// 설문 보기 정보 조회
    				BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
    				surveyExmplVO.setUserNotiSeq(userNotiSeq);
    				surveyExmplVO.setUserId(info.getId());
	    			surveyExmpl_list = board230Service.getBbsNotiSurveyExmplList(surveyExmplVO);
	    			modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
	    			modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
	    			logger.debug("surveyExmpl_list : "+JSONUtils.objectToJSON(surveyExmpl_list));
    			}
			}else if(vo.getNotiKind() !=null && vo.getNotiKind().equals(Constant.NOTI_KIND_030.getVal())){//동영상
				List movList = board310Service.getTnMspMvpFileList(userNotiSeq);
				modelMap.put("movList", JSONUtils.objectToJSON(movList));
			}
			
			vo.setNotiTagLst(CommUtil.scriptRemove(vo.getNotiTagLst()));
////			
			modelMap.put("notiInfo", JSONUtils.objectToJSON(notiInfo));
			modelMap.put("notiFile", JSONUtils.objectToJSON(notiFile));
			modelMap.put("notiPrevNextInfo", JSONUtils.objectToJSON(notiPrevNextInfo));
			modelMap.put("notiOpn1", CommUtil.scriptRemove(JSONUtils.objectToJSON(notiOpn1)));
			modelMap.put("notiOpn2", CommUtil.scriptRemove(JSONUtils.objectToJSON(notiOpn2)));
			if( vo.getRegrId() != null){
//				modelMap.put("regrIdEncrypt", Encrypter.Encrypt(ec.decrypt(vo.getRegrId())));
			}
//			
			//이미지,동영상 이전,다음글 처리
			if(Constant.BOARD_FORM_SPEC_010.getVal().equals(vo.getBoardFormSpec()) || Constant.BOARD_FORM_SPEC_020.getVal().equals(vo.getBoardFormSpec())){//이미지, 동영상
				
				modelMap.put("notiPrevNextImgMovInfo", JSONUtils.objectToJSON(board310Service.getBbsMovieImagePrevNextNotiInfoForView( data, session, auth )));
			}			
			
			jsonResult.setSuccess(true);
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
    	
        return modelMap;
    }
    
    /**
     * 이미지,동영상 이전, 다음글 조회 
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    @RequestMapping(value="/notiPrevNextImgMovInfo")
    public ModelMap getNotiPrevNextImgMovInfo(
 			@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			modelMap.put("notiPrevNextImgMovInfo", JSONUtils.objectToJSON(board310Service.getBbsMovieImagePrevNextNotiInfoForView( data, session, null )));
			
			jsonResult.setSuccess(true);
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
    	
        return modelMap;
    }    
    
    /**
	 * 상세보기 의견등록
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertPbsUserNotiOpn")
    public ModelMap insertPbsUserNotiOpn(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpServletRequest			request,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			modelMap.put("ok", board310Service.insertPbsUserNotiOpn(data, session));
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
	 * 상세보기 의견수정
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/updatePbsUserNotiOpn")
    public ModelMap updatePbsUserNotiOpn(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpServletRequest			request,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	

		try{	
			modelMap.put("ok", board310Service.updatePbsUserNotiOpn(data, session));
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
	 * 상세보기 의견삭제
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/deletePbsUserNotiOpn")
    public ModelMap deletePbsUserNotiOpn(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			modelMap.put("ok", board310Service.deletePbsUserNotiOpn(data));
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
	 * 게시글 읽음처리
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent 
	 */
    @RequestMapping(value="/updatePbsUserNotiEvalInfoForRead")
    public ModelMap updatePbsUserNotiEvalInfoForRead(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			board310Service.updatePbsUserNotiEvalInfoForRead(session, data);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
     * 나의 게시글 여부 조회 
     * @param data, modelMap
     * @return ModelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getMyPbsNotiCheckList")
    public ModelMap getMyPbsNotiCheckList(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			modelMap.put("cnt", board310Service.getMyPbsNotiCheckList(data, session));
			jsonResult.setMessage("");
			jsonResult.setSuccess(true);
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
     * 개인게시판 게시글 이동
     * @param session, moveData
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/insertPbsUserNotiInfoForMove", method = RequestMethod.POST)
    public ModelMap insertPbsUserNotiInfoForMove(
    		HttpSession session,
    		@RequestParam(value="moveData" ,required = false) String moveData,
			ModelMap 		modelMap ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{			

			board310Service.insertPbsUserNotiInfoForMove(moveData, session);
			jsonResult.setMessage("이동 되었습니다");
			jsonResult.setSuccess(true);
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
    
    /**
	* 게시글 삭제
	* @param model
	* @return ModelMap
	* @exception Exception
	* @auther crossent
	*/
    @RequestMapping(value="/deletePbsNotiInfo")
    public ModelMap deleteNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	String rtnStr = "";
		try{	
			rtnStr = board310Service.deletePbsNotiInfo(data, session);
			jsonResult.setSuccess(true);
			if(rtnStr.equals("ERR001")){
				jsonResult.setMessage("타인의 게시물을 삭제할수 없습니다.");
			}else{
				jsonResult.setMessage(messageSource.getMessage("delete.ok"));
			}
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
     * 스크랩 URL 복사
     * @param session, moveData
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/insertPbsUserNotiInfoForScrap", method = RequestMethod.POST)
    public ModelMap insertPbsUserNotiInfoForScrap(
    		HttpSession session,
    		@RequestParam(value="data" ,required = false) String data,
			ModelMap 		modelMap ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{			

			board310Service.insertPbsUserNotiInfoForScrap(data, session);
			jsonResult.setMessage("스크랩 되었습니다.");
			jsonResult.setSuccess(true);
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
    
    /**
     * 게시물 상세화면 프레임 
     * @param modelMap
     * @param boardId
     * @param notiId
     * @param pageIndex
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/pbsViewFrame")
    public String boardViewFrame(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="userNotiSeq",required = true) String userNotiSeq,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			HttpSession session
 			)
            throws Exception {
    	modelMap.put("boardId", boardId);
    	modelMap.put("userNotiSeq", userNotiSeq);
    	modelMap.put("pageIndex", pageIndex);
    	return ".board/pbs/pbsViewFrame";
    }
    
    /**
	 * 링크 게시글 평가 등록
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertPbsUserNotiEvalInfoForLink")
    public ModelMap insertPbsUserNotiEvalInfoForLink(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	JSONObject bbsObject = JSONObject.fromObject(data);
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		
		try{
			String delYn = board100Service.getNotiDelYn(data);
			
			logger.debug("delYn : "+delYn);
			
			if(delYn.equals("N")){
				PbsUserNotiEvalInfoVO vo = new PbsUserNotiEvalInfoVO();
				vo.setUserNotiSeq(bbsObject.getInt("userNotiSeq"));
				vo.setNotiEvalDiv((String)bbsObject.get("notiEvalDiv"));
				vo.setUserId(info.getId());
				vo.setUserName(info.getName());
				vo.setRegrId(info.getId());
				vo.setRegrName(info.getName());
				vo.setUpdrId(info.getId());
				vo.setUpdrName(info.getName());
				board310Service.insertPbsUserNotiEvalInfo(vo);
				jsonResult.setSuccess(true);
				jsonResult.setMessage("");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setMessage("삭제된 게시글입니다.");
			}
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
	    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
        
    /**
	 * 상세보기 게시글 평가 등록
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertPbsNotiEvalInfoForView")
    public ModelMap insertPbsNotiEvalInfoForView(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			String result = board310Service.insertPbsNotiEvalInfoForView(data, session);
			jsonResult.setSuccess(true);
			if(result.equals("ok")){
				jsonResult.setMessage("반영 되었습니다.");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setMessage("이미 등록하셨습니다.");
			}
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
}
