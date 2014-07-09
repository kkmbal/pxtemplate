package portalxpert.board.board211.web;


import java.net.InetAddress;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.board.board211.sc.Board211Service;
import portalxpert.board.board211.vo.BbsImgBoardNotiInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.exception.PortalxpertException;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value = "board211")
public class Board211Controller {
	
	/** boardService */
    @Resource(name = "board100Service")
    private Board100Service board100Service;
	/** person100Service */
	@Resource(name = "board211Service")
	private Board211Service board211Service;
	
	/** boardService */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
	
	
	private final static Logger logger = LoggerFactory.getLogger(Board211Controller.class);
	
	
	
	
	
	/**
	 * 이미지 게시판 게시물 리스트 
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author 
	 */
	@RequestMapping(value="/getBbsImgBoardNotiList")                            
	 public String getBbsImgBoardNotiList(
			 @ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
			    @RequestParam(value="boardId" ,required = false) String boardId,
			    @RequestParam(value="pageIndex",required = false, defaultValue="1") String pageIndex,
	 			@RequestParam(value="pageUnit",required = false, defaultValue="6") String pageUnit,
				@RequestParam(value="searchCondition",required = false) String searchCondition,
	 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
	 			@RequestParam(value="regDttmFrom",required = false) String regDttmFrom,
	 			@RequestParam(value="regDttmTo",required = false) String regDttmTo,
	 			@RequestParam(value="orderType",required = false, defaultValue="default") String orderType,
	 			@RequestParam(value="isDesc",required = false) boolean isDesc,
	 			@RequestParam(value="fh",required = false) String fh,
	 			@RequestParam(value="open",required = false) String open,
				HttpSession session,
				HttpServletRequest request,
				ModelMap modelMap)
         throws Exception {


		String imgSvrUrl = PortalxpertConfigUtils.getString("upload.real.web");
		String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
		
		if (!imgSvrUrl.endsWith("/")) imgSvrUrl = imgSvrUrl+"/";
		
		
    	UserInfoVO info = null;
    	String openPath = "";
    	if(Constant.BOARD_OPEN_PATH.getVal().equals(open)){
    		info = new UserInfoVO();
    		info.setId(Constant.BOARD_ROLE_USER.getVal());
    		openPath = Constant.BOARD_OPEN_PATH.getVal() + "/"; // 공개게시판
    	}else{
    		info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	}
    	
		String auth = board100Service.getUserBbsMapList(info.getId());
		
		BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		bbsVO.setUserId(info.getId());
		bbsVO.setUserMap(auth);
		logger.debug("auth : "+bbsVO.getUserMap());
		logger.debug("pageIndex : "+pageIndex);
				
		List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);//게시판 조회
		BbsBoardInfoVO bbsInfo = list.get(0);
		String boardBtnViewYn = getBoardBtnViewYN(session,bbsInfo );
		
		//공개게시판 체크
		checkOpenBoard(open, bbsInfo);
		
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
		boardSearchVO.setNotiReadmanAsgnYn(bbsInfo.getNotiReadmanAsgnYn());
		boardSearchVO.setUserMap(auth);
		boardSearchVO.setOrderType(orderType);
		boardSearchVO.setIsDesc(isDesc);
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setBoardAnmtUseYn(bbsInfo.getBoardAnmtUseYn());//공지사용여부
		
		logger.debug("bbsInfo.getBoardAnmtUseYn() : "+bbsInfo.getBoardAnmtUseYn());
//		logger.debug("사용자권한 : "+auth);
//		logger.debug("조회자 지정여부 : "+boardSearchVO.getNotiReadmanAsgnYn());
//		logger.debug("isDesc : "+isDesc);
		
		//관리자면 권한 체크 SKIP
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		if (superAdmin.equals(Constant.ROLE_SUPER.getVal()))
		{
			boardSearchVO.setNotiReadmanAsgnYn("A");
		}
		
		
		
		List<BbsNotiInfoVO> noti_list = board211Service.getBbsNotiInfoListForPaging(boardSearchVO);//게시글 조회
		int totCnt = board211Service.getBbsNotiInfoListTotCnt(boardSearchVO);
		
		
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
		modelMap.put("regDttmFrom", regDttmFrom);
		modelMap.put("regDttmTo", regDttmTo);
		modelMap.put("boardName", bbsInfo.getBoardName());
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("isDesc", !isDesc);
		modelMap.put("searchCondition", searchCondition);
		modelMap.put("searchKeyword", searchKeyword);
		modelMap.put("regDttmFrom", regDttmFrom);
		modelMap.put("regDttmTo", regDttmTo);
		modelMap.put("host", CONTEXT_PATH);
		modelMap.put("fh", fh);
		modelMap.put("btnViewYn", boardBtnViewYn);
		modelMap.put("userId", info.getId());
		modelMap.put("eamAdminYn", getEamAdmBoardAdmYNForList(session, bbsInfo));
		modelMap.put("imgUrl", CONTEXT_PATH + imgSvrUrl);
		modelMap.put("listSize", noti_list.size());
		
		modelMap.put("host", "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort());
       
 	   return ".self/board/"+openPath+"board211Imagelist";
 	   		   
	}
	
	@RequestMapping(value="/{open}/getBbsImgBoardNotiList")                            
	 public String getOpenBbsImgBoardNotiList(
			 @ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
			    @RequestParam(value="boardId" ,required = false) String boardId,
			    @RequestParam(value="pageIndex",required = false, defaultValue="1") String pageIndex,
	 			@RequestParam(value="pageUnit",required = false, defaultValue="6") String pageUnit,
				@RequestParam(value="searchCondition",required = false) String searchCondition,
	 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
	 			@RequestParam(value="regDttmFrom",required = false) String regDttmFrom,
	 			@RequestParam(value="regDttmTo",required = false) String regDttmTo,
	 			@RequestParam(value="orderType",required = false, defaultValue="default") String orderType,
	 			@RequestParam(value="isDesc",required = false) boolean isDesc,
	 			@RequestParam(value="fh",required = false) String fh,
	 			@PathVariable String open,
				HttpSession session,
				HttpServletRequest request,
				ModelMap modelMap)
        throws Exception {
		
		return getBbsImgBoardNotiList(boardSearchVO, boardId, pageIndex, pageUnit, searchCondition, searchKeyword, regDttmFrom ,regDttmTo, orderType, isDesc, fh, open, session, request, modelMap);
	}
	
	public int getMyBbsFavoriteYn(String userId, String boardId) throws Exception 
	{
		
		BoardSearchVO boardSearchVO = new BoardSearchVO();
		
		boardSearchVO.setUserId(userId);
		boardSearchVO.setBoardId(boardId);
		
		return board210Service.getMyBbsFavoriteYn(boardSearchVO);
		
	}
	
	
	 /**
	 * 이미지 게시글 삭제
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value = "/updateImgNotiInfo", method = RequestMethod.POST)
	public ModelMap updateImgNotiInfo(
			@RequestParam(value = "data", required = true) String data,
			ModelMap modelMap,HttpSession session) throws Exception {
			
			
		JSONResult jsonResult = new JSONResult();
		
		logger.debug("objectToJSON : "+JSONUtils.objectToJSON(data));
		
		try {
			
			
			board211Service.updateImgNotiInfo(data, session);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
			
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
	
	
	
	
	 /**
	 * 공지 리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  게시판별 공지 리스트 
	 * @exception Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getBbsNotiList")  
	public ModelMap getBbsNotiList(
			@RequestParam(value="boardId" ,required = true) String boardId,
			ModelMap modelMap,HttpSession session) throws Exception {
			
			
		JSONResult jsonResult = new JSONResult();
	 	try {
	 		

	 		List<BbsImgBoardNotiInfoVO> bbsNotiList = board211Service.getBbsNotiList(boardId);
	 		
	 		modelMap.put("notiListSize", bbsNotiList.size());
	 		modelMap.put("bbsNotiList", JSONUtils.objectToJSON(bbsNotiList));
			
	 	 } catch (Exception e) {
	 		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		};
		modelMap.put("jsonResult", jsonResult);
			
		return modelMap;
	}
	
	/**
     * 게시판의 쓰기 속성의 버튼 출력 여부
     * @param HttpSession, BbsBoardInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getBoardBtnViewYN(HttpSession session, BbsBoardInfoVO bbsInfo)throws Exception {
    	
    	String yn = "N";
//    	ROLE000272 : E 전체권한롤
//
//    	ROLE000591 : 게시판관리
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
    	if( superAdmin.equals(Constant.ROLE_SUPER.getVal())
    			|| bbsInfo.getAdmYn().equals("Y") 
    			|| bbsInfo.getWrtYn().equals("Y")){
    		yn = "Y";
    	}
    	if(bbsInfo.getAdmYn().equals("N") && bbsInfo.getWrtYn().equals("N") && bbsInfo.getRedYn().equals("N")){
    		yn = "X";
    	}
    	logger.debug("getBoardBtnViewYN : "+yn);
    	
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
		logger.debug("==superAdmin : "+ superAdmin);
		logger.debug("==getAdmYn : "+ bbsInfo.getAdmYn());
		if( superAdmin.equals(Constant.ROLE_SUPER.getVal())
    			|| bbsInfo.getAdmYn().equals("Y")){ 
    		yn = "Y";
    		
    	}
		logger.debug("==yn : "+ yn);
		logger.debug("========================================");
		
    	return yn;
    }
	
    //공개게시판 여부 체크
	private void checkOpenBoard(String open, BbsBoardInfoVO bbsInfo) {
		if(Constant.BOARD_OPEN_PATH.getVal().equals(open)){
			if(!"Y".equals(bbsInfo.getOutsideOpenDiv())){
				throw new PortalxpertException(messageSource.getMessage("auth.error"));
			}
		}
	}
}
