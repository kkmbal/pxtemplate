package portalxpert.board.board210.web;

import java.net.URLDecoder;
import java.util.ArrayList;
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
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiCalInfoVO;
import portalxpert.board.board100.vo.BbsNotiDelInfoVO;
import portalxpert.board.board100.vo.BbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.ComCodeSpecVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.board.board211.sc.Board211Service;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.vo.CategoryVO;
import portalxpert.person.person300.sc.Person300Service;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("board210")
public class Board210Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board210Controller.class); 
   
	/** board210Service */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
    
    /** board211Service */
	@Resource(name = "board211Service")
	private Board211Service board211Service;
	
	/** board211Service */
	@Resource(name = "board230Service")
	private Board230Service board230Service;
    
	/** board100Service */
    @Resource(name = "board100Service")
    private Board100Service board100Service;
    
    /** person300Service */
    @Resource(name = "person300Service")
    private Person300Service person300Service;
    
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 게시판의 쓰기 속성의 버튼 출력 여부
     * @param HttpSession, BbsBoardInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getBoardBtnViewYN(HttpSession session, BbsBoardInfoVO bbsInfo)throws Exception {
    	
    	String yn = "N";
    	logger.debug("boardName : "+bbsInfo.getBoardName());
    	logger.debug("boardId : "+bbsInfo.getBoardId());
    	logger.debug("superAdmin : "+(String)session.getAttribute("superAdmin"));
    	logger.debug("getAdmYn : "+bbsInfo.getAdmYn());
    	logger.debug("getWrtYn : "+bbsInfo.getWrtYn());
    	logger.debug("getRedYn : "+bbsInfo.getRedYn());
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	/**
    	 * portal 관리자, 게시판관리자, 게시판담당자, 쓰기권한이 있는 사용자(작성자포함) 여부 확인 
    	 */
    	if( superAdmin.equals("E")
    			|| bbsInfo.getAdmYn().equals("Y") 
    			|| bbsInfo.getWrtYn().equals("Y")){
    		yn = "Y";
    	}
    	if( (!superAdmin.equals("E")) && bbsInfo.getAdmYn().equals("N") && bbsInfo.getWrtYn().equals("N") && bbsInfo.getRedYn().equals("N")){   		
    		yn = "X";
    	}
    	logger.debug("getBoardBtnViewYN : "+yn);
    	return yn;
    }
    
    /**
     * 게시판 사용자 권한 체크
     * @param HttpSession, data
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getBoardUserMapYN(HttpSession session, String data)throws Exception {
    	
    	String yn = "N";
    	JSONObject bbsObject = JSONObject.fromObject(data);
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String userMap = board100Service.getUserBbsMapList(info.getId());
		BbsBoardInfoVO vo = new BbsBoardInfoVO();
		vo.setBoardId((String)bbsObject.get("boardId"));
		vo.setUserMap(userMap);
		List<BbsBoardInfoVO> list = board100Service.getBoardUserMapInfo(vo);
		BbsBoardInfoVO bbsInfo = list.get(0);
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		
		if( superAdmin.equals("E")
    			|| bbsInfo.getAdmYn().equals("Y") 
    			|| bbsInfo.getWrtYn().equals("Y")){
    		yn = "Y";
    	}
    	return yn;
    }
    
    /**
     * 게시판 사용자 권한 체크
     * @param HttpSession, data
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getEamAdmBoardAdmYN(HttpSession session, String data)throws Exception {
    	
    	String yn = "N";
    	JSONObject bbsObject = JSONObject.fromObject(data);
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String userMap = board100Service.getUserBbsMapList(info.getId());
		BbsBoardInfoVO vo = new BbsBoardInfoVO();
		vo.setBoardId((String)bbsObject.get("boardId"));
		vo.setUserMap(userMap);
		List<BbsBoardInfoVO> list = board100Service.getBoardUserMapInfo(vo);
		BbsBoardInfoVO bbsInfo = list.get(0);
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		

		logger.debug("========================================");
		//logger.debug("==getBoardId : "+ bbsInfo.getBoardId());
		//logger.debug("==getBoardName : "+ bbsInfo.getBoardName());
		logger.debug("==getId : "+ info.getId());
		logger.debug("==getDisplayname : "+ info.getDisplayname());
		logger.debug("==superAdmin : "+ superAdmin);
		//logger.debug("==getAdmYn : "+ bbsInfo.getAdmYn());
		bbsInfo = null;
		if(bbsInfo != null){
			if( superAdmin.equals("E")
	    			|| bbsInfo.getAdmYn().equals("Y")){ 
	    		yn = "Y";
	    		
	    	}
		}
		logger.debug("==yn : "+ yn);
		logger.debug("========================================");
    	return yn;
    }
    
    /**
     * 게시판 사용자 권한 체크
     * @param HttpSession, BbsBoardInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getEamAdmBoardAdmYNForList(HttpSession session, BbsBoardInfoVO bbsInfo)throws Exception {
    	
    	String yn = "N";
    	
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		

		logger.debug("========================================");
		logger.debug("==getBoardId : "+ bbsInfo.getBoardId());
		logger.debug("==getBoardName : "+ bbsInfo.getBoardName());
		logger.debug("==getId : "+ info.getId());
		logger.debug("==getDisplayname : "+ info.getDisplayname());
		logger.debug("==superAdmin : "+ superAdmin);
		logger.debug("==getAdmYn : "+ bbsInfo.getAdmYn());
		if( superAdmin.equals("E")
    			|| bbsInfo.getAdmYn().equals("Y")){ 
    		yn = "Y";
    		
    	}
		logger.debug("==yn : "+ yn);
		logger.debug("========================================");
    	return yn;
    }
    
    
    
    /**
     * 유형별 게시판 
     * @param modelMap
     * @return board/basicBoardList.jsp
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getBoardInfoList")
    public String getBoardInfoList(
 			ModelMap modelMap,
 			@ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="pageIndex",required = false, defaultValue="1") String pageIndex,
 			@RequestParam(value="pageUnit",required = false, defaultValue="10") String pageUnit,
 			@RequestParam(value="searchCondition",required = false) String searchCondition,
 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
 			@RequestParam(value="orderType",required = false, defaultValue="default") String orderType,
 			@RequestParam(value="isDesc",required = false) boolean isDesc,
 			@RequestParam(value="listYn",required = false) String listYn,
 			HttpSession session,
 			HttpServletRequest request
 			)
            throws Exception {
    	
 
    		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String auth = board100Service.getUserBbsMapList(info.getId());
		
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		
		BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		bbsVO.setUserId(info.getId());
		bbsVO.setUserMap(auth);
		
		/*superAdmin = "E";
		//관리자면 권한 체크 SKIP
		if (superAdmin.equals("E"))
		{
			bbsVO.setUserMap("");
		}*/
		
		List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);//게시판 조회
		BbsBoardInfoVO bbsInfo = list.get(0);
		String boardBtnViewYn = getBoardBtnViewYN(session,bbsInfo );
		
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
		
		//관리자면 권한 체크 SKIP
		if (superAdmin.equals("E"))
		{
			bbsInfo.setNotiReadmanAsgnYn("A");
		}
		
		boardSearchVO.setNotiReadmanAsgnYn(bbsInfo.getNotiReadmanAsgnYn());
		
		boardSearchVO.setUserMap(auth);
		boardSearchVO.setOrderType(orderType);
		boardSearchVO.setIsDesc(isDesc);
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setBoardAnmtUseYn(bbsInfo.getBoardAnmtUseYn());//공지사용여부
		boardSearchVO.setBoardKind(bbsInfo.getBoardKind());
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setEamAdminYn(getEamAdmBoardAdmYNForList(session, bbsInfo));

		searchKeyword = searchKeyword == null?"":searchKeyword;
		searchKeyword = URLDecoder.decode(searchKeyword,"UTF-8");
//			logger.debug("사용자권한 : "+auth);
//			logger.debug("조회자 지정여부 : "+boardSearchVO.getNotiReadmanAsgnYn());
//			logger.debug("isDesc : "+isDesc);
		
		List<BbsNotiInfoVO> noti_list = board210Service.getBbsNotiInfoListForPaging(boardSearchVO);//게시글 조회
		int totCnt = board210Service.getBbsNotiInfoListTotCnt(boardSearchVO);
		
		// 달력타입
		List calList = new ArrayList();
		if(Constant.BOARD_FORM_040.getVal().equals(bbsInfo.getBoardForm())){
			for(BbsNotiInfoVO bvo : noti_list){
				BbsNotiCalInfoVO cvo = new BbsNotiCalInfoVO();
				cvo.setBoardId(bvo.getBoardId());
				cvo.setNotiId(bvo.getNotiId());
				cvo.setPnum(bvo.getPnum());
				cvo.setTitle(bvo.getNotiTitle());
				cvo.setStart(bvo.getNotiBgnDttm());
				cvo.setEnd(bvo.getNotiEndDttm());
				calList.add(cvo);
			}
		}
		
		logger.debug("noti_list : "+noti_list.size());
		logger.debug("totCnt : "+totCnt);
		
		paginationInfo.setTotalRecordCount(totCnt);
		logger.debug("bbsInfo.getMakrDispDiv() : "+bbsInfo.getMakrDispDiv());
		modelMap.put("favoYn", bbsInfo.getFavoriteYn());
		modelMap.put("nickUseYn", bbsInfo.getNickUseYn());
		modelMap.put("boardKind", bbsInfo.getBoardKind());
		modelMap.put("makrDispDiv", bbsInfo.getMakrDispDiv());//작성자표기구분
		modelMap.put("agrmOppUseYn", bbsInfo.getAgrmOppUseYn());//찬성_반대_사용_여부
		modelMap.put("likeUseYn", bbsInfo.getLikeUseYn());//좋아요_사용_여부
		modelMap.put("boardSearchVO", boardSearchVO);
		modelMap.put("pageIndex", pageIndex);
		modelMap.put("pageUnit", pageUnit);
		modelMap.put("boardId", boardId);
		modelMap.put("notiList", noti_list);
		modelMap.put("boardName", bbsInfo.getBoardName());
		modelMap.put("boardForm", bbsInfo.getBoardForm());
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("isDesc", !isDesc);
		modelMap.put("searchCondition", searchCondition);
		modelMap.put("searchKeyword", searchKeyword);
		modelMap.put("orderType", orderType);
		modelMap.put("calList", JSONUtils.objectToJSON(calList));
		
		//modelMap.put("host", "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort());
		if("80".equals(request.getServerPort())){
			modelMap.put("host", "http://"+request.getRemoteHost());
		}else{
			modelMap.put("host", "http://"+request.getRemoteHost()+":"+request.getServerPort());
			
		}

//			logger.debug("http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort());
		logger.debug("getBoardInfoList pageIndex : "+pageIndex);
		//modelMap.put("btnViewYn", "Y");
		modelMap.put("userId", info.getId());
		modelMap.put("listYn", listYn);
		modelMap.put("eamAdminYn", getEamAdmBoardAdmYNForList(session, bbsInfo));
		modelMap.put("btnViewYn", boardBtnViewYn);
			
 
    
        return ".self/board/basicBoardList";
    }
    
    /**
	* 공용 게시글 삭제
	* @param model
	* @return ModelMap
	* @exception Exception
	* @auther crossent
	*/
    @RequestMapping(value="/deleteNotiInfo")
    public ModelMap deleteNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
		try{	
			
			if(getEamAdmBoardAdmYN(session, data).equals("Y")){//eam관리자, 게시판관리자 삭제 
				board210Service.deleteNotiInfo(data, session);
				jsonResult.setSuccess(true);
				jsonResult.setMessage("");
			}else{
				
				if(board210Service.getMyNotiCheckList(data, session) == 0){
					board210Service.deleteNotiInfo(data, session);
					jsonResult.setSuccess(true);
					jsonResult.setMessage("");
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setMessage("타인이 작성한 글을 삭제할 수 없습니다.");
					
				}
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
     * 나의 게시글 여부 조회 
     * @param data, modelMap
     * @return ModelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getMyNotiCheckList")
    public ModelMap getMyNotiCheckList(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			int otherUserNotiCnt = board210Service.getMyNotiCheckList(data, session);
			String reNotiYn = board210Service.getReNotiYn(data, session);
			logger.debug("otherUserNotiCnt : "+otherUserNotiCnt);
			logger.debug("reNotiYn : "+reNotiYn);
			if(getEamAdmBoardAdmYN(session, data).equals("Y")){//포털관리자 또는 게시판 관리자 
				jsonResult.setMessage("");
				jsonResult.setSuccess(true);
			}else{
				if(getBoardUserMapYN(session, data).equals("Y")){//이동하고자 하는 게시판에 대한 사용자의 쓰기권한을 체크한다.
					if(otherUserNotiCnt == 0){//작성자체크 
						if(reNotiYn.equals("N")){//답글존재여부
							jsonResult.setMessage("");
							jsonResult.setSuccess(true);
						}else{
							jsonResult.setSuccess(false);
							jsonResult.setMessage("답글 혹은 답글이 있는 게시글은 이동할 수 없습니다.");
							
						}
					}else{
						jsonResult.setSuccess(false);
						jsonResult.setMessage("타인이 작성한 게시글은 이동할 수 없습니다.");
						
					}
				}else{
					jsonResult.setSuccess(false);
					jsonResult.setMessage("쓰기 권한이 없습니다.");
					
				}
			}
			
			logger.debug("jsonResult.getMessage() : "+jsonResult.getMessage());
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
    
    /**
     * 게시글 등록
     * @param modelMap
     * @return board/bbsFrame.jsp
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getBasicBoardWrite")
    public String getBasicBoardWrite(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = true) String boardId,
 			HttpSession session
 			)
            throws Exception {
    	
    	modelMap.put("boardId", boardId);
    	
        return ".self/board/basicBoardWrite";
    }
    
    /**
	 * 즐겨찾기 추가
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertBbsFavorite")
    public ModelMap insertBbsFavorite(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			modelMap.put("ok", board210Service.insertBbsFavorite(data, session));
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
	 * 즐겨찾기 삭제
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/deleteBbsFavorite")
    public ModelMap deleteBbsFavorite(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			modelMap.put("ok", board210Service.deleteBbsFavorite(data, session));
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
     * 게시판 상세조회
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/boardDetailSearchPop")
    public String boardDetailSearchPop(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = false) String boardId,
 			HttpSession session
 			)
            throws Exception {
    	
    	modelMap.put("boardId", boardId);
    	
        return ".self/board/boardDetailSearchPop";
    }
    
    /**
	 * 링크등록
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertBbsNotiInfoForLink")
    public ModelMap insertBbsNotiInfoForLink(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,HttpServletRequest request,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			modelMap.put("ok", board210Service.insertBbsNotiInfoForLink(data, session, request));
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
	 * 공용 게시글 읽음처리
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent 
	 */
    @RequestMapping(value="/updateBbsNotiEvalInfoForRead")
    public ModelMap updateBbsNotiEvalInfoForRead(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			board210Service.updateBbsNotiEvalInfoForRead(data, session);
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
     * 공용게시판 게시글 이동
     * @param categoryVO
     * @param conts
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertBbsNotiInfoForMove", method = RequestMethod.POST)
    public ModelMap insertBbsNotiInfoForMove(
    		CategoryVO 		categoryVO,
    		HttpSession session,
    		@RequestParam(value="moveData" ,required = false) String moveData,
			ModelMap 		modelMap ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{			

			if(getBoardUserMapYN(session, moveData).equals("Y")){
				board210Service.insertBbsNotiInfoForMove(moveData, session);
				jsonResult.setMessage("이동 되었습니다");
			}else{
				jsonResult.setSuccess(false);
				jsonResult.setMessage("쓰기 권한이 없습니다.");
				
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
     * 게시글 상세보기 View
     * @param modelMap
     * @return board/basicBoardView
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getBasicBoardView")
    public String getBasicBoardView(
 			ModelMap modelMap,
 			@RequestParam(value="boardId" ,required = true) String boardId,
 			@RequestParam(value="notiId" ,required = true) String notiId,
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
    	String imgSvrUrl = PortalxpertConfigUtils.getString("upload.real.web"); 
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	logger.debug("getBasicBoardView boardId : "+boardId);
    	
    	String auth = board100Service.getUserBbsMapList(info.getId());
		logger.debug("auth : "+auth);
    	BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		bbsVO.setUserId(info.getId());
		bbsVO.setUserMap(auth);
    	
		List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);
		BbsBoardInfoVO bbsInfo = list.get(0);
		String boardBtnViewYn = getBoardBtnViewYN(session,bbsInfo );
    	logger.debug("boardBtnViewYn : "+boardBtnViewYn);
    	
    	BbsNotiInfoVO notiVo = new BbsNotiInfoVO();
		notiVo.setBoardId(boardId);
		notiVo.setNotiId(notiId);
		notiVo.setUserMap(auth);
		notiVo.setUserId(info.getId());
		notiVo.setAnmtYn(bbsInfo.getBoardAnmtUseYn());
		notiVo.setNotiKind(bbsInfo.getBoardForm());
		notiVo.setNotiReadmanAsgnYn(bbsInfo.getNotiReadmanAsgnYn());
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		if (superAdmin.equals("E"))
		{
			notiVo.setNotiReadmanAsgnYn("A");
		}
		
		//게시물권한 체크
		if("Y".equals(notiVo.getNotiReadmanAsgnYn())){
			int notiUserAuth = board100Service.getNotiUserAuth(notiVo);
			if(notiUserAuth == 0){
				boardBtnViewYn = "X";
			}
			//관리자추천 동영상,이미지 체크
			if(Constant.BOARD_FORM_SPEC_010.getVal().equals(bbsInfo.getBoardFormSpec()) || Constant.BOARD_FORM_SPEC_020.getVal().equals(bbsInfo.getBoardFormSpec())){//이미지, 동영상
				String data = "{\"notiId\":\""+notiId+"\"}";
				List<BbsNotiApndFileVO> notiFile = board210Service.getBbsNotiApndFileListForView(data);
				for(BbsNotiApndFileVO vo : notiFile){
					if("Y".equals(vo.getAdminRcmdYn())){
						boardBtnViewYn = "Y";
						break;
					}
				}
			}
		}
		
		int pnum = board100Service.getBbsMyPnumInfo(notiVo);
		
		
    	int prev_pnum = 0, next_pnum = 0;
    	if(pnum > 0){
    		prev_pnum = pnum -1;next_pnum = pnum +1;
    	}
    	
    	BbsNotiEvalInfoVO vo = new BbsNotiEvalInfoVO();
		vo.setNotiId(notiId);
		vo.setNotiEvalDiv("040");
		vo.setUserId(info.getId());
		vo.setUserName(info.getName());
		vo.setRegrId(info.getId());
		vo.setRegrName(info.getName());
		vo.setUpdrId(info.getId());
		vo.setUpdrName(info.getName());
		board210Service.insertBbsNotiEvalInfoForRead(vo);//읽음처리 조회수++, 
		BbsNotiInfoVO bbsNotiInfoViewForNotiConts = board210Service.getBbsNotiInfoViewForNotiConts(notiId);//본문가져오기 
		String notiConts = bbsNotiInfoViewForNotiConts==null?"":bbsNotiInfoViewForNotiConts.getNotiConts();
		if(notiConts != null){ 
			notiConts = notiConts.replaceAll("\r\n","<br>");
			notiConts = CommUtil.scriptRemove(notiConts);
		}else{ 
			notiConts = "";
		}
		
		if( bbsNotiInfoViewForNotiConts != null){
//			EAMCrypt ec = new EAMCrypt();
//			modelMap.put("regrIdEncrypt", Encrypter.Encrypt(ec.decrypt(bbsNotiInfoViewForNotiConts.getRegrId())));
		}
		
		modelMap.put("notiConts",notiConts );
		modelMap.put("noTagNotiConts", CommUtil.htmlEscape(notiConts) );
    	modelMap.put("boardId", boardId);
    	modelMap.put("pnum", pnum);
    	modelMap.put("prev_pnum", prev_pnum);
    	modelMap.put("next_pnum", next_pnum);
    	modelMap.put("boardName", bbsInfo.getBoardName());
    	modelMap.put("boardKind", bbsInfo.getBoardKind());
    	modelMap.put("boardForm", bbsInfo.getBoardForm());
    	modelMap.put("boardFormSpec", bbsInfo.getBoardFormSpec());
    	modelMap.put("replyWrteDiv", bbsInfo.getReplyWrteDiv());//답변쓰기 구분
    	modelMap.put("notiId", notiId);
    	modelMap.put("userId", info.getId());
    	modelMap.put("pageIndex", pageIndex);
    	modelMap.put("pageUnit", pageUnit);
    	modelMap.put("btnViewYn", boardBtnViewYn);
    	modelMap.put("likeUseYn", bbsInfo.getLikeUseYn());//좋아요_사용_여부
    	modelMap.put("agrmOppUseYn", bbsInfo.getAgrmOppUseYn());//찬성_반대_사용_여부
    	modelMap.put("nickUseYn", bbsInfo.getNickUseYn());//닉네임_사용_여부
    	modelMap.put("imgSvrUrl", CONTEXT_PATH + imgSvrUrl);
    	//modelMap.put("imgRealDir", PortalxpertConfigUtils.getString("upload.real.dir"));
    	modelMap.put("imgRealDir", "");
    	modelMap.put("realWeb", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web"));
    	modelMap.put("admYn", bbsInfo.getAdmYn());//게시판 관리자 여부
    	modelMap.put("eamAdminYn", getEamAdmBoardAdmYNForList(session, bbsInfo));
    	modelMap.put("movDir", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.thumbnail.web"));
    	modelMap.put("boardExplUseYn", bbsInfo.getBoardExplUseYn());
		modelMap.put("boardExpl", bbsInfo.getBoardExpl());
		modelMap.put("notiEmailSendYn", bbsInfo.getNotiEmailSendYn());
		modelMap.put("searchCondition", searchCondition);
		modelMap.put("searchKeyword", searchKeyword);
		modelMap.put("regDttmFrom", regDttmFrom);
		modelMap.put("regDttmTo", regDttmTo);
		modelMap.put("orderType", orderType);
		modelMap.put("isDesc", isDesc);
		logger.debug("getBasicBoardView searchCondition : "+searchCondition);
		
		String rtnPage = ".self/board/basicBoardView";
		if(Constant.BOARD_KIND_120.getVal().equals(bbsInfo.getBoardKind())){
			rtnPage = ".self/board/board120View";
		}
    	
        return rtnPage;
    }
    
    /**
     * 게시글 상세보기 DATA 가져오기
     * @param modelMap
     * @return modelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getNotiDetailInfoView")
    public ModelMap getNotiDetailInfoView(
 			@RequestParam(value="data" ,required = true) String data,
 			@RequestParam(value="pnum" ,required = false) int pnum,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();
//    	EAMCrypt ec = new EAMCrypt();
		try{	
			int prev_pnum = 0, next_pnum = 0;
	    	if(pnum > 0){
	    		prev_pnum = pnum -1;
	    		next_pnum = pnum +1;
	    	}
			
			JSONObject bbsObject = JSONObject.fromObject(data);
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));
			logger.debug("notiId : "+(String)bbsObject.get("notiId"));
			String notiId = (String)bbsObject.get("notiId");
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			String auth = board100Service.getUserBbsMapList(info.getId());
			
			List notiInfo = board210Service.getBbsNotiInfoView(data);
			
			if(notiInfo == null || notiInfo.size() == 0) {
				notiInfo = new ArrayList<BbsNotiInfoVO>();
				notiInfo.add(new BbsNotiInfoVO());
			}
			BbsNotiInfoVO vvo =  (BbsNotiInfoVO)notiInfo.get(0);
			
			List notiFile = board210Service.getBbsNotiApndFileListForView(data);
			
			String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
			String boardId = (String)bbsObject.get("boardId");
			BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
			bbsVO.setBoardId(boardId);
			bbsVO.setUserId(info.getId());
			bbsVO.setUserMap(auth);	    	
			List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);
			BbsBoardInfoVO bbsInfo = list.get(0);
			String notiReadmanAsgnYn = bbsInfo.getNotiReadmanAsgnYn();
			if (superAdmin.equals("E"))
			{
				notiReadmanAsgnYn = "A";
			}
			
			List<BbsNotiInfoVO> notiPrevNextInfo = board210Service.getBbsPrevNextNotiInfoForView(data, auth,prev_pnum,next_pnum,notiReadmanAsgnYn, info.getId() );

			List notiOpn1 = board210Service.getBbsNotiOpnList1ForView(data);
			List notiOpn2 = board210Service.getBbsNotiOpnList2ForView(data);
			
			
			BbsNotiInfoVO vo = (BbsNotiInfoVO) notiInfo.get(0);
			List survey_list = null;
			if(vo.getNotiKind() !=null && vo.getNotiKind().equals(Constant.NOTI_KIND_040.getVal())){//설문
				//설문 정보 조회
    			BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
    			surveyVO.setNotiId(notiId);	    			
    			survey_list = board230Service.getBbsNotiSurveyList(surveyVO);
    			
    			List surveyExmpl_list = null;
    			logger.debug("survey_list.size() : "+survey_list.size());
    			if (survey_list.size() > 0){
    				// 설문 보기 정보 조회
    				BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
    				surveyExmplVO.setNotiId(notiId);
    				surveyExmplVO.setUserId(info.getId());
	    			surveyExmpl_list = board230Service.getBbsNotiSurveyExmplList(surveyExmplVO);
	    			modelMap.put("surveyList", JSONUtils.objectToJSON(survey_list));
	    			modelMap.put("surveyExmplList", JSONUtils.objectToJSON(surveyExmpl_list));
    			}
			}else if(vo.getNotiKind() !=null && vo.getNotiKind().equals(Constant.NOTI_KIND_030.getVal())){//동영상
				List movList = board210Service.getTnMspMvpFileList(notiId);
				logger.debug("movList 2 : "+JSONUtils.objectToJSON(movList));
				modelMap.put("movList", JSONUtils.objectToJSON(movList));
			}
			if( vo.getRegrId() != null){
//				modelMap.put("regrIdEncrypt", Encrypter.Encrypt(ec.decrypt(vo.getRegrId())));
			}
			
			vo.setNotiTagLst(CommUtil.scriptRemove(vo.getNotiTagLst()));
			
			//이미지,동영상 이전,다음글 처리
			if(Constant.BOARD_FORM_SPEC_010.getVal().equals(vo.getBoardFormSpec()) || Constant.BOARD_FORM_SPEC_020.getVal().equals(vo.getBoardFormSpec())){//이미지, 동영상
				
				modelMap.put("notiPrevNextImgMovInfo", JSONUtils.objectToJSON(board210Service.getBbsMovieImagePrevNextNotiInfoForView( data, session, auth )));
			}
			
			modelMap.put("notiInfo", JSONUtils.objectToJSON(notiInfo));
			modelMap.put("notiFile", JSONUtils.objectToJSON(notiFile));
			modelMap.put("notiPrevNextInfo", JSONUtils.objectToJSON(notiPrevNextInfo));
			modelMap.put("notiOpn1", CommUtil.scriptRemove(JSONUtils.objectToJSON(notiOpn1)));
			modelMap.put("notiOpn2", CommUtil.scriptRemove(JSONUtils.objectToJSON(notiOpn2)));
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
			
			modelMap.put("notiPrevNextImgMovInfo", JSONUtils.objectToJSON(board210Service.getBbsMovieImagePrevNextNotiInfoForView( data, session, null )));
			
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
     * 게시글 상세보기 좋아요 사용자 조회 
     * @param modelMap
     * @return modelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getBbsNotiEvalInfoList")
    public ModelMap getBbsNotiEvalInfoList(
 			@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			List notiEval = board210Service.getBbsNotiEvalInfoList(data);
			
			modelMap.put("notiEval", JSONUtils.objectToJSON(notiEval));

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
     * 일반 게시판 조회
     * @param modelMap
     * @return modelMap
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getBbsBoardInfoListForView")
    public ModelMap getBbsBoardInfoListForView(
 			@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			
			List boardList = board210Service.getBbsBoardInfoListForView(data);
			
			modelMap.put("boardList", JSONUtils.objectToJSON(boardList));

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
    @RequestMapping(value="/insertBbsNotiOpnForView")
    public ModelMap insertBbsNotiOpnForView(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpServletRequest			request,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();

		try{	
			vo = board210Service.insertBbsNotiOpnForView(request, data, session);
			modelMap.put("ok", "ok");
			
    		vo.setOpnConts(CommUtil.scriptRemove(vo.getOpnConts()));
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage("");
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("opnList", vo);
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
    @RequestMapping(value="/updateBbsNotiOpnForView")
    public ModelMap updateBbsNotiOpnForView(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpServletRequest			request,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();

		try{	
			vo = board210Service.updateBbsNotiOpnForView(request, data, session);
			modelMap.put("ok", "ok");
			vo.setOpnConts(CommUtil.scriptRemove(vo.getOpnConts()));
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
		}catch(Exception e){
			//logger.error("SampleController/insertSample : Exception #", e);
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("opnList", vo);
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
    @RequestMapping(value="/deleteBbsNotiOpnForView")
    public ModelMap deleteBbsNotiOpnForView(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();

		try{	
			vo = board210Service.deleteBbsNotiOpnForView(data);
			modelMap.put("ok", "ok");
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
		modelMap.put("opnList", vo);
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
    @RequestMapping(value="/insertBbsNotiEvalInfoForView")
    public ModelMap insertBbsNotiEvalInfoForView(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			String result = board210Service.insertBbsNotiEvalInfoForView(data, session);
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
    
    /**
	 * 링크 게시글 평가 등록
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertBbsNotiEvalInfoForLink")
    public ModelMap insertBbsNotiEvalInfoForLink(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			String result = board210Service.insertBbsNotiEvalInfoForView(data, session);
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
     * 게시물 상세화면 프레임 (메인에서 사용)
     * @param modelMap
     * @param boardId
     * @param notiId
     * @param pageIndex
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/boardViewFrame")
    public String boardViewFrame(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="notiId",required = true) String notiId,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			@RequestParam(value="viewMode",required = false) String viewMode,
 			HttpSession session
 			)
            throws Exception {
    	modelMap.put("boardId", boardId);
    	modelMap.put("notiId", notiId);
    	modelMap.put("pageIndex", pageIndex);
    	
    	if (viewMode == null)
    	{
    		viewMode = "";
    	}
    	
    	String returnPage = ".board/board/boardViewFrame";
    	if (!viewMode.equals(""))
    	{
    		returnPage = ".self/board/boardViewFrame";
    	}
    	
    	return returnPage;
    }
    
    
    
    /**
     * 삭제이력 팝업
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/bbsDelInfoPop" )
    public String bbsDelInfoPop(
 			ModelMap modelMap,
 			@RequestParam(value="notiId",required = true) String notiId,
 			@RequestParam(value="delDiv",required = true) String delDiv,
 			@RequestParam(value="boardId",required = false) String boardId,
 			HttpSession session
 			)
            throws Exception {
    	
    	modelMap.put("delDiv", delDiv);
    	modelMap.put("notiId", notiId);
    	modelMap.put("boardId", boardId);
    	
    	return ".self/board/bbsDelInfoPop";
    }
    
    /**
     * 게시글 조회 
     * @param 
     * @return ModelMap
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/getBbsNotiInfoForPop" )
    public ModelMap getBbsNotiInfoForPop(
 			@RequestParam(value="data",required = true) String data,
 			ModelMap modelMap,
 			HttpSession session
 			)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			List<BbsNotiInfoVO> notiInfo = board210Service.getBbsNotiInfoView(data);
			logger.debug("notiInfo : "+notiInfo.size());
			modelMap.put("notiInfo", JSONUtils.objectToJSON(notiInfo));
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
	 * 게시글 삭제 정보 등록 
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 * @auther crossent
	 */
    @RequestMapping(value="/insertBbsNotiDelInfo")
    public ModelMap insertBbsNotiDelInfo(
    		@RequestParam(value="data" ,required = true) String data,
    		@RequestParam(value="moveData" ,required = false) String moveData,
			ModelMap 		modelMap,
			HttpSession session
			) throws Exception {
    	
    	JSONObject bbsObject = JSONObject.fromObject(data);
    	String delDiv = (String)bbsObject.get("delDiv");
    	JSONResult jsonResult = new JSONResult();
    	logger.debug("delDiv : "+delDiv);
    	
    	BbsNotiDelInfoVO vo = new BbsNotiDelInfoVO();
		vo.setNotiId((String)bbsObject.get("notiId"));
    	
		try{	
			String isOk = board210Service.insertBbsNotiDelInfo(data, session);
			if(isOk.equals("ok")){
				if(delDiv.equals("MOV")){
					board210Service.insertBbsNotiInfoForMove(moveData, session);
				}else{
					board210Service.deleteNotiInfo(moveData, session);
				}
				jsonResult.setSuccess(true);
				jsonResult.setMessage("");
			}
			
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
     * 공통 코드 조회 
     * @param 
     * @return ModelMap
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/getComCodeSpecList" )
    public ModelMap getComCodeSpecList(
 			ModelMap modelMap,
 			HttpSession session
 			)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
		try{	
			List<ComCodeSpecVO> codeList = board210Service.getComCodeSpecList();
			modelMap.put("codeList", JSONUtils.objectToJSON(codeList));
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
    
    
}

