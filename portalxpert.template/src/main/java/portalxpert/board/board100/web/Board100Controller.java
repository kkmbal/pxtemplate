package portalxpert.board.board100.web;

import java.io.File;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsAddItemInfoVO;
import portalxpert.board.board100.vo.BbsBoardChartPopVO;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsBoardUserMapVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsTotalSearchVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.FileDownloadUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.SearchConditionVO;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.sc.OrganizationService;
import portalxpert.organization.vo.BbsVO;
import portalxpert.organization.vo.CategoryVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("board100")
public class Board100Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board100Controller.class); 
   
	/** boardService */
    @Resource(name = "board100Service")
    private Board100Service board100Service;
    
	/** boardService */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
    
    /**
     * 관리자 게시판생성 VIEW
     * @param modelMap
     * @return String
     * @auther crossent 
     */
    @RequestMapping(value="/createAdminBbsView")
    public String createAdminBbsView(
			ModelMap modelMap,
			@RequestParam(value="boardId",required = false) String boardId,
			HttpSession session
			)
            throws Exception {
 
    		
    		if (boardId == null)
    		{
    			boardId = "";
    		}
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		
    		if (boardId != null)
    		{
    			BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
    			bbsVO.setBoardId(boardId);
    			List list = board100Service.getAdminBbsBoardInfoList(bbsVO);
    			    			
    			BbsBoardUserMapVO userVO = new BbsBoardUserMapVO();
    			userVO.setBoardId(boardId);    			
    			List<BbsBoardUserMapVO> user_list = board100Service.getAdminBbsBoardUserMapList(userVO);
    			
    			logger.debug("user_list : "+JSONUtils.objectToJSON(user_list));
    			modelMap.put("boardId", boardId);
    			modelMap.put("bbsList", JSONUtils.objectToJSON(list));
    			modelMap.put("userMapList", JSONUtils.objectToJSON(user_list));
    			modelMap.put("userId", info.getId());
    			modelMap.put("userNm", info.getName());
    			modelMap.put("deptNm", info.getOu());
    			
    		}
  
    
        return ".self/board/createAdminBbsView";
    }
    
    /**
     * 공용게시판 중복 조회
     * @param bbsName
     * @param model
     * @param session
     * @return model
     * @throws Exception
     */
    @RequestMapping("/searchAdminBoardName")
    public ModelMap searchAdminBoardName(
            @RequestParam(value="bbsName" ,required = true) String bbsName,
            ModelMap model,
            HttpSession session
            )throws Exception {   
            
    	BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
    	bbsVO.setBoardName(bbsName);
    	
    	// 변수명은 CoC 에 따라 BbsBoardInfoVO
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(true);
        
        try{
        	model.put("isBoardName", JSONUtils.objectToJSON(board100Service.selectAdminIsBoardName(bbsVO)));
        }catch(Exception e){
        	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
        }
        
        model.put("jsonResult", jsonResult);
        return model;
    }
    
    /**
     * 추가항목 조회
     * @param model
     * @param session
     * @return ModelMap
     * @throws Exception
     */
    @RequestMapping("/searchAdminAddItem")
    public ModelMap searchAdminAddItem(            
            ModelMap model,
            HttpSession session
            )
            throws Exception {
    	BbsAddItemInfoVO itemVO = new BbsAddItemInfoVO();
    	    	
    	// 변수명은 CoC 에 따라 BbsBoardInfoVO
        JSONResult jsonResult = new JSONResult();
        jsonResult.setSuccess(true);
        
        try{
        	model.put("addItemList", JSONUtils.objectToJSON(board100Service.selectAdminAddItemList(itemVO)));
        }catch(Exception e){
        	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
        }	
        model.put("jsonResult", jsonResult);
        return model;
    }
    
    /**
     * 공용 게시판 생성
     * @param data
     * @param modelMap
     * @param session
     * @return modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/insertAdminBbsList", method = RequestMethod.POST)
    public ModelMap insertAdminBbsList(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			
    ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{	
			
			board100Service.createAdminBbsList(data, session);
			
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
	 * 게시판 리스트 팝업. (pageing)
	 * @param model
	 * @return "/board/bbsChartPop"
	 * @exception Exception
	 */
    @RequestMapping(value="/getBbsChartList")
	public String getBbsChartList(
			@ModelAttribute("searchVO") SearchConditionVO searchVO,
			@RequestParam(value="callback" ,required = true) String callback,
			ModelMap modelMap
			)
	        throws Exception {
	
		/** PropertyService.sample */
		searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		searchVO.setPageSize(propertiesService.getInt("pageSize"));
		
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(searchVO.getPageUnit());
		paginationInfo.setPageSize(searchVO.getPageSize());
		
		searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		//searchVO= null;
	    List<BbsBoardChartPopVO> bbsChartPopList = board100Service.selectAdminBbsChartPopList(searchVO);
	    modelMap.put("resultList", bbsChartPopList);
	    
	    int totCnt = board100Service.selectAdminBbsChartPopListTotCnt(searchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("searchVO", searchVO);
		modelMap.put("callback", callback);
	    return ".self/board/bbsChartPop";
    } 
    
    /**
	 * 나의 게시판 리스트 팝업. (pageing)
	 * @param model
	 * @return "/board/bbsChartPop"
	 * @exception Exception
	 */
    @RequestMapping(value="/getMyBbsChartList")
	public String getMyBbsChartList(
			@ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
			@RequestParam(value="callback" ,required = true) String callback,
			ModelMap modelMap,
			HttpSession session
			)
	        throws Exception {
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
		/** PropertyService.sample */
    	boardSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
		boardSearchVO.setPageSize(propertiesService.getInt("pageSize"));
		boardSearchVO.setLoginedUserId(info.getId());
		/** pageing setting */
		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(boardSearchVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(boardSearchVO.getPageUnit());
		paginationInfo.setPageSize(boardSearchVO.getPageSize());
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		boardSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
		boardSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		//searchVO= null;
	    List<BbsBoardChartPopVO> bbsChartPopList = board100Service.selectMyBbsChartPopList(boardSearchVO);
	    modelMap.put("resultList", bbsChartPopList);
	    
	    int totCnt = board100Service.selectMyBbsChartPopListTotCnt(boardSearchVO);
		paginationInfo.setTotalRecordCount(totCnt);
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("boardSearchVO", boardSearchVO);
		modelMap.put("callback", callback);
	    return ".self/board/myBbsChartPop";
    } 
   
    /**
    * 사용자 게시판 리스트
    * @param model
    * @return "/board/userBbsList"
    * @exception Exception
    */
    @RequestMapping(value="/getUserBbsList")
    public String getUserBbsList(
	   @ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
	   ModelMap modelMap, HttpSession session) throws Exception {
    
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
	   //** PropertyService.sample *//
       boardSearchVO.setPageUnit(propertiesService.getInt("pageUnit"));
       boardSearchVO.setPageSize(propertiesService.getInt("pageSize"));
	
	   //** pageing setting *//
	   PaginationInfo paginationInfo = new PaginationInfo();
	   paginationInfo.setCurrentPageNo(boardSearchVO.getPageIndex());
	   paginationInfo.setRecordCountPerPage(boardSearchVO.getPageUnit());
	   paginationInfo.setPageSize(boardSearchVO.getPageSize());
	
	   boardSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
	   boardSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
	   boardSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
	   boardSearchVO.setUserId(info.getId());
	   
	   List<PbsUserBoardInfoVO> userBoardList = board100Service.getUserBbsList(boardSearchVO);
	
	   int totCnt = board100Service.getUserBbsListTotCnt(boardSearchVO);
	   paginationInfo.setTotalRecordCount(totCnt);
	
	   modelMap.put("resultList", userBoardList);
	   modelMap.put("paginationInfo", paginationInfo);
	   modelMap.put("boardSearchVO", boardSearchVO);

	   return ".board/board/userBbsList";
    }
    
    /**
	* 사용자 게시판 수정 View
	* @param model
	* @return "board/modifyUserBbsView"
	* @exception Exception
	*/
    @RequestMapping(value="/getModifyUserBbsInfoView")
    public String getModifyUserBbsInfoView(
	    ModelMap modelMap,
	    @RequestParam(value="boardId",required = true) String boardId
	    ) throws Exception {
	
	 
	
	    PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();
	    pbsVO.setBoardId(boardId);
	    List board_list = board100Service.getPbsUserBoardInfoList(pbsVO);
	
	    PbsUserBoardPartInfoVO partVO = new PbsUserBoardPartInfoVO();
	    partVO.setBoardId(boardId);
	    List part_list = board100Service.getPbsUserBoardPartInfoList(partVO);
	
	    modelMap.put("boardId", boardId);
	    modelMap.put("boardList", JSONUtils.objectToJSON(board_list));
	    modelMap.put("partList", JSONUtils.objectToJSON(part_list));
	
 
	
	    return ".self/board/modifyUserBbsView";
    }
    
    /**
	 * 사용자 게시판 삭제
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 */
    @RequestMapping(value="/deleteUserBbs")
    public ModelMap deleteUserBbs(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

    	try{	
			board100Service.deleteUserBbs(data, session);
			
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
	* 개인 게시판생성 VIEW
	* @param modelMap
	* @return board/createUserBbsView.jsp
	* @throws Exception
	*/
    @RequestMapping(value="/createUserBbsView")
    public String createUserBbsView(
		    ModelMap modelMap,
		    @RequestParam(value="boardId",required = false) String boardId,
		    HttpSession session
		    )
		    throws Exception {
		    JSONResult jsonResult = new JSONResult();
	
	    try {
	
		    if (boardId == null)
		    {
		    	boardId = "";
		    }
		    UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		
		    if (!boardId.equals(""))
		    {
			    PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();
			    pbsVO.setBoardId(boardId);
			    List board_list = board100Service.getPbsUserBoardInfoList(pbsVO);
			
			    PbsUserBoardPartInfoVO partVO = new PbsUserBoardPartInfoVO();
			    partVO.setBoardId(boardId);
			    List part_list = board100Service.getPbsUserBoardPartInfoList(partVO);
			
			    if (board_list.size() > 0 ){
			    	modelMap.put("boardList", JSONUtils.objectToJSON(board_list));
			    }else{
			    	modelMap.put("boardList", "[]");
			    }
			    if (part_list.size() > 0 ){
			    	modelMap.put("partList", JSONUtils.objectToJSON(part_list));
			    }else{
			    	modelMap.put("partList", "[]");
			    }			    
		
		    }
		    else
		    {
		    	modelMap.put("boardList", "[]");
		    	modelMap.put("partList", "[]");
		    }
	    	modelMap.put("boardId", boardId);
	    	modelMap.put("userId", info.getId());
	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
	    	jsonResult.setMessage(messageSource.getMessage("common.error"));
	    	jsonResult.setErrMessage(e.getMessage());
	    }
	
	    return ".self/pbs/createUserBbsView";
   }
   
   /**
    * 사용자 게시판명 중복 조회
    * @param bbsName
    * @param model
    * @param session
    * @return
    * @throws Exception
    */
   @RequestMapping("/searchUserBoardName")
   public ModelMap searchUserBoardName(
           @RequestParam("bbsName") String bbsName,
           ModelMap model,
           HttpSession session
           )   
   
           throws Exception {
	   
	   UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	   PbsUserBoardInfoVO vo = new PbsUserBoardInfoVO();
       vo.setBoardName(bbsName);
       vo.setBoardOwnrId(info.getId());
   	
   	// 변수명은 CoC 에 따라 BbsBoardInfoVO
       JSONResult jsonResult = new JSONResult();
       jsonResult.setSuccess(true);
       
       try{
    	   model.put("isBoardName", JSONUtils.objectToJSON(board100Service.selectUserIsBoardName(vo)));
       }catch(Exception e){
    	   jsonResult.setSuccess(false);
		   jsonResult.setMessage(messageSource.getMessage("common.error")); 
		   jsonResult.setErrMessage(e.getMessage());
       }
       model.put("jsonResult", jsonResult);
       return model;
   }
   
   /**
    * 사용자 게시판 생성
    * @param data
    * @param modelMap
    * @param session
    * @return ModelMap
    * @throws Exception
    */
   @RequestMapping(value = "/insertUserPbsList", method = RequestMethod.POST)
   public ModelMap insertUserPbsList(
   		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
   			) throws Exception {
   	
   		JSONResult jsonResult = new JSONResult();

		try{	
			
			board100Service.createUserBbsList(data, session);
			
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
    * SNS형 게시판 View
    * @param modelMap
    * @return board/snsBbsListView.jsp
    * @throws Exception
    */
   @RequestMapping(value="/snsBbsListView")
   public String snsBbsListView(
			ModelMap modelMap,
			@RequestParam(value="boardId",required = true) String boardId,
			HttpSession session
			)
           throws Exception {
 
        String lastNotiId = "S99999999";
   		if (boardId == null)
   		{
   			boardId = "BBS000001";
   		}   			
   		BbsNotiInfoVO NotiVO = new BbsNotiInfoVO();
   		NotiVO.setBoardId(boardId);
   		NotiVO.setNotiId("S99999999");
   		
   		List noti_list = board100Service.getBbsNotiInfoList(NotiVO);
   		
   		
   		if (noti_list.size() > 0)
   		{
   			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
   			lastNotiId = vo.getNotiId();
   		}
   		
   		BbsNotiOpnVO opnVO = new BbsNotiOpnVO();
   		//opnVO.setBoardId(boardId);
   		opnVO.setNotiId("S99999999");
   		
		List opn_list = board100Service.getBbsNotiOpnList(opnVO);
		
		BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
		//apndVO.setBoardId(boardId);
		apndVO.setNotiId("S99999999");
		List apnd_list = board100Service.getBbsNotiApndFileList(apndVO);
		modelMap.put("boardId", boardId);
		modelMap.put("notiList", JSONUtils.objectToJSON(noti_list));
		modelMap.put("opnList", JSONUtils.objectToJSON(opn_list));   			
		modelMap.put("apndList", JSONUtils.objectToJSON(apnd_list));
		
		modelMap.put("lastNotiId", lastNotiId);
 
   
       return ".board/board/snsBbsListView";
   }
   
   /**
    * 게시물 저장
    * @param modelMap
    * @return board/insertNotiList.jsp
    * @throws Exception
    */
   @RequestMapping(value = "/insertBbsNotiInfo", method = RequestMethod.POST)
   public ModelMap insertBbsNotiInfo(
   		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session,
			HttpServletRequest request
			
   ) throws Exception {
   	
   		JSONResult jsonResult = new JSONResult();
   		BbsNotiInfoVO vo = new BbsNotiInfoVO(); 

		try{	
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
    * 의견 저장
    * @param modelMap
    * @return board/insertNotiList.jsp
    * @throws Exception
    */
   @RequestMapping(value = "/insertBbsOpnInfo", method = RequestMethod.POST)
   public ModelMap insertBbsOpnInfo(
   		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			
   ) throws Exception {
   	
   		JSONResult jsonResult = new JSONResult();
   		BbsNotiOpnVO vo = new BbsNotiOpnVO(); 

		try{	
			vo = board100Service.insertBbsNotiOpn(data, session);
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
    * 게시물 조회
    * @param modelMap
    * @return board/getBbsNotiInfo.jsp
    * @throws Exception
    */
   @RequestMapping("/getBbsNotiInfo")
   public ModelMap getBbsNotiInfo(
		   @RequestParam(value="data" ,required = true) String data,
           ModelMap model,
           HttpSession session
           )  
           throws Exception {
	    String lastNotiId = "S999999999";
	    BbsNotiInfoVO NotiVO = new BbsNotiInfoVO();	    

	    JSONObject jsonObject = JSONObject.fromObject(data);
	    
	    JSONResult jsonResult = new JSONResult();
	    jsonResult.setSuccess(true);
	    
	    try{
		    NotiVO.setBoardId((String)jsonObject.get("boardId"));
	  		NotiVO.setNotiId((String)jsonObject.get("notiId"));
	  		  		
	  		List noti_list = board100Service.getBbsNotiInfoList(NotiVO);
	  		
	  		if (noti_list.size() > 0)
	   		{
	   			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
	   			lastNotiId = vo.getNotiId();
	   		}
	  		
	  		BbsNotiOpnVO opnVO = new BbsNotiOpnVO();  		
	  		//opnVO.setBoardId((String)jsonObject.get("boardId"));
	  		opnVO.setNotiId((String)jsonObject.get("notiId"));  		
			List opn_list = board100Service.getBbsNotiOpnList(opnVO);
			
			BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
			//apndVO.setBoardId((String)jsonObject.get("boardId"));
			apndVO.setNotiId((String)jsonObject.get("notiId"));
			List apnd_list = board100Service.getBbsNotiApndFileList(apndVO);
			
		       
			model.put("notiList", JSONUtils.objectToJSON(noti_list));
			model.put("opnList", JSONUtils.objectToJSON(opn_list));
			model.put("apndList", JSONUtils.objectToJSON(apnd_list));
			model.put("lastNotiId", lastNotiId);
	    }catch(Exception e){
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
	    }
		model.put("jsonResult", jsonResult);
       return model;
   }
  
    /**
     * 유형별 게시판 프레임
     * @param modelMap
     * @return board/bbsFrame.jsp
     * @throws Exception
     */
    @RequestMapping(value="/boardFrame")
    public String boardFrame(
 			ModelMap modelMap,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="pageIndex",required = false) String pageIndex,
 			HttpSession session
 			)
            throws Exception {
    	
    	
    	String rtnPage = "blank";
    	BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
		bbsVO.setBoardId(boardId);
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		
		List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);
		BbsBoardInfoVO bbsInfo = null;
		String boardForm = null;
		if (list.size() > 0)
		{
			bbsInfo = list.get(0);
			boardForm = bbsInfo.getBoardForm();
		}
		
		
		if (boardId.equals("BBS999999"))  //임시게시판
		{
			rtnPage = "board/bbs240Frame";
		}
		else  //임시게시판이 아니면
		{
			if(bbsInfo !=null){
				
				if( ! bbsInfo.getBoardKind().equals(Constant.BOARD_KIND_020.getVal())){//일반, 경조사 
					if( bbsInfo.getBoardForm() != null){
						if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_010.getVal())){//일반형
							rtnPage = "board/bbs210Frame";
						}else if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_020.getVal())){//SNS형
							rtnPage = "board/bbs220Frame";
						}else if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_040.getVal())){//달력형
							rtnPage = "board/bbs210Frame";
						}else if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_030.getVal()) && bbsInfo.getBoardFormSpec().equals(Constant.BOARD_FORM_SPEC_010.getVal())){//컨텐츠 이미지형
							rtnPage = "board/bbs211Frame";
						}else if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_030.getVal()) && bbsInfo.getBoardFormSpec().equals(Constant.BOARD_FORM_SPEC_020.getVal())){//컨텐츠 동영상형
							rtnPage = "board/bbs212Frame";
						}else if(bbsInfo.getBoardForm().equals(Constant.BOARD_FORM_030.getVal()) && bbsInfo.getBoardFormSpec().equals(Constant.BOARD_FORM_SPEC_030.getVal())){//컨텐츠형 
							rtnPage = "board/bbs213Frame";
						}						
					}else{
						//logger.error("BoardForm 을 확인해주세요.");
						
						rtnPage = "board/bbs210Frame";
					}
				}else{//폐쇄게시판
					rtnPage = "board/bbs210Frame";
				}
				
			}else{
				logger.error("["+boardId+"]게시판이 존재하지 않습니다.");
			}
			
		}
		
		logger.debug("page : "+".board/" + rtnPage);
		
    	modelMap.put("boardId", boardId);
    	modelMap.put("boardForm", boardForm);
    	modelMap.put("pageIndex", pageIndex);
    	 
        return ".board/" + rtnPage;     
    }
    
    /**
     * 게시판 종합 검색 일반 검색 리스트
     * @param model
     * @return "/bbs/bbsTotalSearchList"
 	 * @exception Exception
     */
    @RequestMapping(value="/getBbsTotalSearchList")
    public String getBbsTotalSearchList(
 		   @ModelAttribute("searchVO") BbsTotalSearchVO searchVO,
 		   HttpSession session,
 		  @RequestParam(value="orderType",required = false) String orderType,
 		  @RequestParam(value="isDesc",required = false) boolean isDesc,
 		  @RequestParam(value="pageUnit",required = false) String pageUnit,
 		  @RequestParam(value="boardKind",required = false) String boardKind,
 		   ModelMap modelMap) throws Exception {
 	   
    	if (pageUnit == null)
    	{
    		pageUnit = "10";
    	}
    	
    	
 	   //** PropertyService.sample *//
 	   searchVO.setPageUnit(propertiesService.getInt("pageUnit"));
 	   searchVO.setPageSize(propertiesService.getInt("pageSize"));
 	   
 	   //** pageing setting *//
 	   PaginationInfo paginationInfo = new PaginationInfo();
 	   paginationInfo.setCurrentPageNo(searchVO.getPageIndex());
 	   paginationInfo.setRecordCountPerPage(Integer.parseInt(pageUnit));
 	   paginationInfo.setPageSize(searchVO.getPageSize());
 		
 	   searchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
 	   searchVO.setLastIndex(paginationInfo.getLastRecordIndex());
 	   searchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
 	   
 	   UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
 	   if (searchVO.getSearchCondition().equals("5"))
 	   {
 		   searchVO.setSearchKeyword(info.getId());
 	   }
 	   
 	   if (searchVO.getSearchKind() == null) searchVO.setSearchKind("1");
 	   
 	   if (orderType == null) orderType = "default";
 	   
 	   String auth = board100Service.getUserBbsMapList(info.getId());
	   searchVO.setUserMap(auth);
	   searchVO.setIsDesc(isDesc);
	   searchVO.setOrderType(orderType);
	  
 	   if (searchVO.getSearchKind().equals("1"))  //단순검색
 	   {
 		  List<BbsTotalSearchVO> normalSearchList = board100Service.getBbsTotalSearchList(searchVO);
 	 	 // List list = new ArrayList();		   
 	 	    
 	 	  /*for (int i = 0; i < normalSearchList.size(); i++) {
 	 		   BbsTotalSearchVO resultVO = normalSearchList.get(i);
 	 		   
 	 		   resultVO.setNotiTitle(resultVO.getNotiTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
 	 		   resultVO.setNotiTitle(resultVO.getNotiTitle().replaceAll("'", "`"));
 	 		   resultVO.setNotiTitle(CommUtil.cutString(resultVO.getNotiTitle(), 50, "..."));
 	 		   
 	 		   list.add(resultVO);
 	 	  }*/
 	 	   
 	 	  int totCnt = board100Service.getBbsTotalSearchListTotCnt(searchVO);
 	 	  paginationInfo.setTotalRecordCount(totCnt);		
 	 		   
 	 	  modelMap.put("resultList", normalSearchList);
 	 	  modelMap.put("paginationInfo", paginationInfo);
 	 	   
 	 	  if (searchVO.getSearchCondition().equals("5"))
 		  {
 			  searchVO.setSearchKeyword("");
 		  }
 	 	  
 	 	  modelMap.put("searchVO", searchVO);
 	 	  modelMap.put("totalCount", new DecimalFormat("#,###").format(totCnt));
 	 	  
 	   }
 	   else  //상세검색
 	   {
 		  List<BbsTotalSearchVO> detailSearchList = board100Service.getBbsTotalSearchList2(searchVO);
 	 	 // List list = new ArrayList();		   
 	 	    
 	 	  /*for (int i = 0; i < detailSearchList.size(); i++) {
 	 		   BbsTotalSearchVO resultVO = detailSearchList.get(i);
 	 		   
 	 		   resultVO.setNotiTitle(resultVO.getNotiTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
 	 		   resultVO.setNotiTitle(resultVO.getNotiTitle().replaceAll("'", "`"));
 	 		   resultVO.setNotiTitle(CommUtil.cutString(resultVO.getNotiTitle(), 50, "..."));
 	 		   
 	 		   list.add(resultVO);
 	 	  }*/
 	 	   
 	 	  int totCnt = board100Service.getBbsTotalSearchListTotCnt2(searchVO);
 	 	  paginationInfo.setTotalRecordCount(totCnt);		
 	 		   
 	 	  modelMap.put("resultList", detailSearchList);
 	 	  modelMap.put("paginationInfo", paginationInfo);
 	 	   	 	  
 	 	  String boardidList = searchVO.getBoardIdList();
 	 	  boardidList = boardidList.replaceAll("'", "");
 	 	  searchVO.setBoardIdList(boardidList);
 	 	  
 	 	  String boardNameList = searchVO.getBoardNameList();
 	 	  boardNameList = boardNameList.replaceAll("'", "");
	 	  searchVO.setBoardNameList(boardNameList);
 	 	  
 	 	  modelMap.put("searchVO", searchVO);
 	 	  modelMap.put("totalCount", new DecimalFormat("#,###").format(totCnt));
 	   } 	   
 	  
 	   modelMap.put("isDesc", !isDesc );
 	   modelMap.put("pageUnit", pageUnit);
 	   modelMap.put("boardKind", boardKind);
 	   
 	   
 	  if (searchVO.getSearchCondition().equals("5"))
	   {
		   searchVO.setSearchKeyword("");
	   }
 	   
 	   	   
 	   return ".self/board/bbsTotalSearchList";
    }
    
    
    /**
     * 첨부파일 다운로드
     * @param data, modelMap, request, response, session
     * @return void
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value = "/bbsFileDownload", method = RequestMethod.GET)
    public void bbsFileDownload(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpServletRequest request, 
 			HttpServletResponse response,
 			HttpSession session
 			
    ) throws Exception {

   	 	//data = URLDecoder.decode(new String(data.getBytes("ISO-8859-1")), "UTF-8");
    	data = URLDecoder.decode(data, "UTF-8");

   	 	JSONObject jsonObject = JSONObject.fromObject(data);
   	 	String notiId = (String)jsonObject.get("notiId");
		String apndFileOrgn = (String)jsonObject.get("apndFileOrgn");
		String apndFileName = (String)jsonObject.get("apndFileName");
		int apndFileSeq =  jsonObject.getInt("apndFileSeq");
		//String apndFilePath = (String)jsonObject.get("apndFilePath");
		logger.debug("apndFileOrgn : "+apndFileOrgn);
		logger.debug("apndFileName : "+apndFileName);
		logger.debug("apndFilePath : "+apndFileSeq);
    		
 		try{	
 			BbsNotiApndFileVO vo = new BbsNotiApndFileVO();
 			vo.setNotiId(notiId);
 			vo.setApndFileSeq(apndFileSeq);
 			BbsNotiApndFileVO bbsNotiApndFile = board210Service.getBbsNotiApndFile(vo);
 			String apndFilePath = PortalxpertConfigUtils.getString("upload.real.dir") + bbsNotiApndFile.getApndFilePath();
 			
 			File file = new File(apndFilePath+'/'+apndFileName.replace("..\\","").replace("../",""));
 			
	        if(!CommUtil.uploadExtensionsCheck(file.getName(), null)){
	        	throw new Exception("Invalid upload file");
	        }
 			
			FileDownloadUtil.download(request, response, file, apndFileOrgn);
			
 		}catch(Exception e){
 			logger.error(e.toString(), e);
 		}

 	}

    /**
     * 개인 게시판 MY 카테고리 관리
     * @param model
     * @return "/pbs/getPbsCategoryList"
 	 * @exception Exception
     */
    @RequestMapping(value="/getPbsCategoryList")
    public String getPbsCategoryList(
    		@ModelAttribute CategoryVO categoryVO,
			@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:관리자용, 2:사용자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)  		
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
	    
		JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		categoryVO.setId(info.getId());
    		categoryVO.setKind(kind);
    		categoryVO.setAdmin(admin);
    		List<CategoryVO> list = organizationService.getCategoryList(categoryVO,info.getId(), session);
    		
    		String conts = "", userId ="";
    		if(list.size() == 0){
    			
    			//conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"미지정\",\"icon\":\"../resources/images/img/img_category.png\"}]";
    			conts = "[]";
    			
    		}else{
    			
    			CategoryVO vo = (CategoryVO)list.get(0);
    			conts = vo.getConts();
    			userId = vo.getUserId();
    		}
    		
    		if (kind.equals("2")) //사용자용이면 나의 권한에 맞는 리스트를 생성한다. 
    		{
    			BoardSearchVO boardSearchVO = new BoardSearchVO();
    			
    			boardSearchVO.setUserId(info.getId());
    			List<BbsVO> board_list = organizationService.getUserBbsMapList(boardSearchVO);
    			
    			modelMap.put("categoryList", JSONUtils.objectToJSON(conts));
    			
    			String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    			if( superAdmin.equals(Constant.ROLE_SUPER.getVal()))
    	    	{
    				modelMap.put("myBoardList", "[]");
    	    	}
    			else
    			{
    				modelMap.put("myBoardList", JSONUtils.objectToJSON(board_list));
    			}
    		}
    		else
    		{
    			modelMap.put("categoryList", JSONUtils.objectToJSON(conts));
    			modelMap.put("myBoardList", "[]");
    		}
    		
    		//modelMap.put("categoryList", JSONUtils.objectToJSON(conts));
		} catch (Exception e) {
	 		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
		}
    	modelMap.put("jsonResult", jsonResult); 
    	
 	   return ".self/pbs/pbs300Category";
    }
    
    /**
     * 선택한 카테고리의 게시판 리스트 
     * @param modelMap
     * @return board/getCategoryBoardList.jsp
     * @throws Exception
     */
    @RequestMapping("/getCategoryBoardList")
    public ModelMap getCategoryBoardList(
 		   @RequestParam(value="data" ,required = true) String data,
 		   @RequestParam(value="boardKind" ,required = false) String boardKind,
            ModelMap model,
            HttpSession session
            )  
            throws Exception {
 	    
    	if (boardKind == null){
    		boardKind = "PBS";
    	}
    	
    	JSONResult jsonResult = new JSONResult();
 	    jsonResult.setSuccess(true);
 	    
    	try{
    		
	    	List boardList = new ArrayList();    	
	    	findCategoryList(data, boardList);    	
	
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	
	    	
	    	    	
	    	String boardIdList = "";
	    	for(int i=0; i < boardList.size(); i++)
	    	{
	    		boardIdList += "'"+boardList.get(i)+"'";
	    		
	    		if ( i < boardList.size() -1)
	    		{
	    			boardIdList += ",";
	    		}
	    	}    	
	    	if (boardIdList.equals("")) boardIdList = "'X'";
	    	
	    	
	    	if (boardKind.equals("PBS"))  //개인 게시판 이면
	    	{
		    	PbsUserBoardInfoVO pbsBoardVo = new PbsUserBoardInfoVO();
		    	pbsBoardVo.setBoardOwnrId(info.getId());
		    	pbsBoardVo.setBoardIdList(boardIdList);    	
		    	List board_list = board100Service.getPbsUserBoardInfoCateList(pbsBoardVo);
		    	
		    	
		    	CategoryVO cate = new CategoryVO();
		    	cate.setId(info.getId() );
		    	cate = (CategoryVO)board100Service.selectMyCategoryCont(cate);
		    	
		    	String cateInfo = cate.getConts();    	
		    	
		    	for (int i=0; i < board_list.size(); i++)
		    	{
		    		PbsUserBoardInfoVO vo = (PbsUserBoardInfoVO)board_list.get(i);
		    		vo.setCategory(findCategoryLevel(cateInfo, vo.getBoardId()));
		    	}
		    	

		    	model.put("boardList", JSONUtils.objectToJSON(board_list));
		    	model.put("jsonResult", jsonResult);
	    	}
	    	else  //공용이면  BBS
	    	{
	    		BbsBoardInfoVO bbsBoardVo = new BbsBoardInfoVO();
	    		bbsBoardVo.setUserId(info.getId());
	    		bbsBoardVo.setBoardId(boardIdList);
	    		List board_list = board100Service.getBbsBoardInfoCateList(bbsBoardVo);
	    		
	    		/*CategoryVO cate = new CategoryVO();
		    	cate.setId(Constant.ROLE_SUPER.getVal());
		    	cate = (CategoryVO)board100Service.selectMyCategoryCont(cate);	    	
		    	String cateInfo = cate.getConts();	    	
		    	
		    	for (int i=0; i < board_list.size(); i++)
		    	{
		    		BbsBoardInfoVO vo = (BbsBoardInfoVO)board_list.get(i);
		    		vo.setCategory(findCategoryLevel(cateInfo, vo.getBoardId()));
		    	}*/
		    	

		    	model.put("boardList", JSONUtils.objectToJSON(board_list));
		    	model.put("jsonResult", jsonResult);
	    		
	    	}
    	}catch(Exception e){
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
    	}
        return model;
    }
    
    /*
     * 카테고리 하위게시판 정보 검색
     * */
    public void findCategoryList(String data, List boardList)
    {
    	JSONObject jo = JSONObject.fromObject(data);
    	if (jo.has("children"))
    	{
    		JSONArray jsonArr = JSONArray.fromObject(jo.get("children"));
    		
    		for (int i=0; i < jsonArr.size(); i++)
    		{
    			JSONObject jo2 = (JSONObject)jsonArr.get(i);
    			if (!jo2.getString("boardId").equals("")){	
    				boardList.add(jo2.getString("boardId"));
    			}
    			if (jo2.has("children"))
    			{
    				findCategoryList(jo2.toString(), boardList);
    			}
    		}
    	}
    	else
    	{
    		if (!jo.getString("boardId").equals("")){
    			boardList.add(jo.getString("boardId"));
    		}
    		
    	}
    }
    
    //카테고리 계층 구조 리턴
    public String findCategoryLevel(String cateInfo, String boardId)
    {
    	JSONArray jsonArr =JSONArray.fromObject(cateInfo);
    	List levelList = new ArrayList();
    	int id = 0;
    	int pid = 0;
    	String level = "";
    	for(int i=0; i < jsonArr.size(); i++)
    	{
    		JSONObject jo = (JSONObject)jsonArr.get(i);
    		
    		if (jo.getString("boardId").equals(boardId))
    		{
    			levelList.add(jo.getString("name"));
    			id = jo.getInt("id");
    			pid = jo.getInt("pId");
    			
    			findParentboard(jsonArr, pid, levelList);
    			break;
    		}
    	}    	
    	
    	for(int i=levelList.size()-1; i > -1; i--)
    	{
    		level += levelList.get(i);
    		
    		if (i > 0) level += ">";
    		
    	}
    	return level;
    }
    
    public void findParentboard(JSONArray jsonArr, int pid, List levelList)
    {
    	for(int i=0; i < jsonArr.size(); i++)
    	{
    		JSONObject jo = (JSONObject)jsonArr.get(i);
    		
    		if (jo.getInt("id") == pid)
    		{
    			levelList.add(jo.getString("name"));
    			findParentboard(jsonArr, jo.getInt("pId"), levelList);
    		}
    	}
    	
    }
    
    
    
    /**
     * 카테고리 정보 저장
     * @param modelMap
     * @return 
     * @throws Exception
     */
    @RequestMapping(value = "/updatePbsUserCategoryInfo", method = RequestMethod.POST)
    public ModelMap updatePbsUserCategoryInfo(
    		@RequestParam(value="data" ,required = true) String data,
    		@RequestParam(value="admin" ,required = true) String admin,  //admin : 1 관리자 2:사용자
 			ModelMap 		modelMap,
 			HttpSession session,
 			HttpServletRequest request
 			
    ) throws Exception {
    	

    		JSONResult jsonResult = new JSONResult();

 		try{	
 			board100Service.updatePbsUserCategoryInfo(data, session, admin);
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
     * 공용 게시판   카테고리 관리
     * @param model
     * @return "/board/getBbsCategoryList"
 	 * @exception Exception
     */
    @RequestMapping(value="/getBbsCategoryList")
    public String getBbsCategoryList(
    		@ModelAttribute CategoryVO categoryVO,
			@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:관리자용, 2:사용자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)  		
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
	    
 
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		if (admin.equals("1"))
		{
			categoryVO.setId(Constant.ROLE_SUPER.getVal());
		}
		else
		{
			categoryVO.setId(info.getId());
		}
		
		categoryVO.setKind(kind);
		categoryVO.setAdmin(admin);
		List<CategoryVO> list = organizationService.getCategoryList(categoryVO,info.getId(), session);
		
		String conts = "", userId ="";
		if(list.size() == 0){
			
			conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"게시판\",\"icon\":\"../resources/images/img/img_category.png\"}]";
			
		}else{
			
			CategoryVO vo = (CategoryVO)list.get(0);
			conts = vo.getConts();
			userId = vo.getUserId();
		}
		
		modelMap.put("categoryList", JSONUtils.objectToJSON(conts));
 
    	
 	   return ".adm/board/categoryAdminBbsView";
 	   
 	   
    }
    
    /**
	 * 공용 게시판 삭제
	 * @param model
	 * @return ModelMap
	 * @exception Exception
	 */
    @RequestMapping(value="/deleteBbsBoardInfo")
    public ModelMap deleteBbsBoardInfo(
    		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session) throws Exception {    		
    	
    	JSONResult jsonResult = new JSONResult();

    	try{	
			board100Service.deleteBbsBoardInfo(data, session);
			
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
     * 게시글 인쇄
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    @RequestMapping(value="/bbsPrintPreview" , method = RequestMethod.GET)
    public String bbsPrintPreview(
			ModelMap modelMap,
			@RequestParam(value="boardId",required = false) String boardId,
			@RequestParam(value="notiId",required = false) String notiId,
			@RequestParam(value="boardKind",required = false) String boardKind,
			@RequestParam(value="pnum",required = false) String pnum,
			HttpSession session
			)
            throws Exception {

    	modelMap.put("notiId", notiId);
    	modelMap.put("boardId", boardId);
    	modelMap.put("boardKind", boardKind);
    	modelMap.put("pnum", pnum);
    	
        return ".self/board/bbsPrintPreview";
    }
        
    
    @RequestMapping(value="/getMailSendPop")
    public String getMailWritePop(
    		ModelMap modelMap,
    		@RequestParam(value="notiId", required=true) String notiId,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
    	
   	
    		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss", Locale.KOREA);
		Date currentTime = new Date();
		String dTime = formatter.format(currentTime);
		
 	   	String sSource =  info.getId()+"| |write|"+dTime;
 	   	String encKey = "";
 	   	String sKey = "0176061804154490";

//     	   	SecurityUtil sUtil = new SecurityUtil();
//     	   	encKey = sUtil.cryptEncrypt(sSource, sKey);
//     	   	
//     	   	String subject = "";
//     	   	String notiConts = "";
//     	   	
//			modelMap.put("content", notiConts);
//			modelMap.put("subject", subject);
//			modelMap.put("encKey", encKey);
//     	   	
   	
		return ".self/qmemo/mailWritePop";
    }
    

    
}

