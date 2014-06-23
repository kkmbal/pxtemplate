package portalxpert.board.board311.web;


import java.net.InetAddress;
import java.net.URLDecoder;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.board.board300.sc.Board300Service;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board310.sc.Board310Service;
import portalxpert.board.board311.sc.Board311Service;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value = "board311")
public class Board311Controller {
	
	/** boardService */
    @Resource(name = "board300Service")
    private Board300Service board300Service;
	/** person100Service */
	@Resource(name = "board311Service")
	private Board311Service board311Service;
	
	/** boardService */
    @Resource(name = "board310Service")
    private Board310Service board310Service;
	
	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
	
	
	private final static Logger logger = LoggerFactory.getLogger(Board311Controller.class);
	
	
	@RequestMapping(value="pageFowarding")
	public String pageFowarding(
								@RequestParam(value="urlKind" ,required = true) String urlKind,
								@RequestParam(value="boardId" ,required = true) String boardId,
								
								HttpSession session,
								ModelMap modelMap
								)
								throws Exception {
   	
	   	String fowardUrl = "";
	   	
	   	if(urlKind.equals("1"))
	   	{	
	   		
	   		modelMap.put("boardId", boardId);
	   		
	   		fowardUrl = ".board/board/bbs311Frame";					 
	   			
	   	}					
	   	
	   
   	
  	   return fowardUrl;
   }
	
	
	/**
	 * 개인 이미지 게시판 리스트 
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author 
	 */
	@RequestMapping(value="/getPbsImgBoardNotiList")                            
	 public String getPbsImgBoardNotiList(
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
	 			HttpServletRequest request,
	 			HttpSession session
	 			)
	            throws Exception {
	    	
 
	    		
	    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				String auth = board300Service.getUserBbsMapList(info.getId());
				logger.debug("auth : "+auth);
				
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
				
				List noti_list = board311Service.getPbsNotiUserInfoListForPaging(boardSearchVO);//게시글 조회
				int totCnt = board311Service.getPbsUserNotiInfoListTotCnt(boardSearchVO);
				
				paginationInfo.setTotalRecordCount(totCnt);
				
				
				String imgSvrUrl = PortalxpertConfigUtils.getString("upload.real.web");				
				if (!imgSvrUrl.endsWith("/")) imgSvrUrl = imgSvrUrl+"/";
				
				searchKeyword = searchKeyword == null?"":searchKeyword;
				searchKeyword = URLDecoder.decode(searchKeyword,"UTF-8");
				modelMap.put("host", PortalxpertConfigUtils.getString("image.web.contextpath"));
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
				modelMap.put("userId", info.getId());
				modelMap.put("imgUrl", PortalxpertConfigUtils.getString("image.web.contextpath") + imgSvrUrl);
				modelMap.put("listSize", noti_list.size());
				modelMap.put("host", "http://"+InetAddress.getLocalHost().getHostAddress()+":"+request.getServerPort());
				
 
	    
	        return ".self/pbs/pbs311Imagelist";
 	   		   
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
			
			
			board311Service.updateImgNotiInfo(data, session);
			
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
	/*@RequestMapping(value="/getBbsNotiList")  
	public ModelMap getBbsNotiList(
			@RequestParam(value="boardId" ,required = true) String boardId,
			ModelMap modelMap,HttpSession session) throws Exception {
			
			
		JSONResult jsonResult = new JSONResult();
	 	try {
	 		

	 		List<BbsImgBoardNotiInfoVO> bbsNotiList = board311Service.getBbsNotiList(boardId);
	 		
	 		modelMap.put("notiListSize", bbsNotiList.size());
	 		modelMap.put("bbsNotiList", JSONUtils.objectToJSON(bbsNotiList));
			
	 	 } catch (Exception e) {
		 		jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error"));
		};
		modelMap.put("jsonResult", jsonResult);
			
		return modelMap;
	}*/
	
    
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
    
	
	
}
