package portalxpert.adm.board.web;

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

import portalxpert.adm.board.sc.AdmBoardNotiService;
import portalxpert.adm.board.vo.AdmBoardNotiDelInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiPopInfoVO;
import portalxpert.adm.board.vo.AdmBoardPbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="adm/board")
public class AdmBoardNotiController {
	
	/** AdmBoardNotiService */
    @Resource(name = "admBoardNotiService")
    private AdmBoardNotiService admBoardNotiService;
    
	/** board210Service */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
    
    /** board230Service */
    @Resource(name = "board230Service")
    private Board230Service board230Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;    
    
    private final static Logger logger = LoggerFactory.getLogger(AdmBoardNotiController.class);
    
    /**
	 * 게시물관리 목록
	 * @param AdmBoardNotiInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmBoardNotiList")
	public String getAdmBoardNotiList(
								AdmBoardNotiInfoVO admBoardNotiInfoVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	/** pageing setting START */
    	admBoardNotiInfoVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	admBoardNotiInfoVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admBoardNotiInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admBoardNotiInfoVO.getPageUnit());
		paginationInfo.setPageSize(admBoardNotiInfoVO.getPageSize());
		admBoardNotiInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admBoardNotiInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admBoardNotiInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int notiListCnt = admBoardNotiService.getAdmBoardNotiCnt(admBoardNotiInfoVO);
		
		paginationInfo.setTotalRecordCount(notiListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmBoardNotiInfoVO> notiDelList = admBoardNotiService.getAdmBoardNotiList(admBoardNotiInfoVO);
    	modelMap.put("notiList", notiDelList);
    	
    	AdmBoardNotiInfoVO pSearch = new AdmBoardNotiInfoVO();
    	pSearch.setSearchCondition(admBoardNotiInfoVO.getSearchCondition());
    	pSearch.setSearchKeyword(admBoardNotiInfoVO.getSearchKeyword());
    	pSearch.setCurrentRecordCount(notiDelList.size());
    	modelMap.put("pSearch", pSearch);
    	
   		return ".adm/adm/board/admBoardNotiList";
	}
    
    /**
	 * 게시물관리 삭제시 입력팝업화면
	 * @param AdmBoardNotiInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmBoardNotiPopup")
	public String getAdmBoardNotiPopup(
								AdmBoardNotiInfoVO admBoardNotiInfoVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	List<AdmBoardNotiInfoVO> notiDelCodeList = admBoardNotiService.getAdmBoardNotiDelCodeList();
    	
    	modelMap.put("notiDelCodeList", notiDelCodeList);
    	modelMap.put("pNotiId", admBoardNotiInfoVO.getNotiId());
    	
    	return ".self/adm/board/admBoardNotiPopup";
	}
 	
 	/**
     * 게시물 이동
     * @param AdmBoardNotiInfoVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmBoardNotiMove", method = RequestMethod.POST)
    public ModelMap updateAdmBoardNotiMove(
    										AdmBoardNotiInfoVO admBoardNotiInfoVO,
    										ModelMap modelMap 
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admBoardNotiService.updateAdmBoardNotiMove(admBoardNotiInfoVO);
			jsonResult.setMessage("해당 게시물이 이동 되었습니다");
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}
 	
 	/**
     * 게시물 삭제
     * @param AdmBoardNotiInfoVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmBoardNotiDelete", method = RequestMethod.POST)
    public ModelMap updateAdmBoardNotiDelete(
    										AdmBoardNotiInfoVO admBoardNotiInfoVO,
    										ModelMap modelMap 
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admBoardNotiService.updateAdmBoardNotiDelete(admBoardNotiInfoVO);
			jsonResult.setMessage("해당 게시물이 삭제 되었습니다");
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	}

	/**
	 * 개인게시판 목록
	 * @param admBoardPbsNotiInfoVO
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
 	@RequestMapping("/getAdmPBoardList")
 	public String getAdmPBoardList(
 			AdmBoardPbsNotiInfoVO admBoardPbsNotiInfoVO,
 			ModelMap modelMap
 		) throws Exception{

    	/** pageing setting START */
 		admBoardPbsNotiInfoVO.setPageUnit(propertiesService.getInt("pageUnit"));
 		admBoardPbsNotiInfoVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admBoardPbsNotiInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admBoardPbsNotiInfoVO.getPageUnit());
		paginationInfo.setPageSize(admBoardPbsNotiInfoVO.getPageSize());
		
		admBoardPbsNotiInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admBoardPbsNotiInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admBoardPbsNotiInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int surVeyListCnt = admBoardNotiService.getAdmPbsBoardCnt(admBoardPbsNotiInfoVO);
		
		paginationInfo.setTotalRecordCount(surVeyListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmBoardPbsNotiInfoVO> surVeyList = admBoardNotiService.getAdmPbsBoardList(admBoardPbsNotiInfoVO);
    	modelMap.put("pbsBoardList", surVeyList);
    	
    	AdmBoardPbsNotiInfoVO pSearch = new AdmBoardPbsNotiInfoVO();
    	pSearch.setSearchCondition(admBoardPbsNotiInfoVO.getSearchCondition());
    	pSearch.setSearchKeyword(admBoardPbsNotiInfoVO.getSearchKeyword());
    	pSearch.setCurrentRecordCount(surVeyList.size());
    	modelMap.put("pSearch", pSearch);
 		return ".adm/adm/board/admBoardPbsNotiList";
 	}
    
	/**
	* 개인 게시판생성 VIEW
	* @param modelMap
	* @return adm/board/createUserBbsView.jsp
	* @throws Exception
	*/
    @RequestMapping(value="/getAdmPBoardView")
    public String getAdmPBoardView(
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
		
		    if (!boardId.equals(""))
		    {
			    PbsUserBoardInfoVO pbsVO = new PbsUserBoardInfoVO();
			    pbsVO.setBoardId(boardId);
			    List board_list = admBoardNotiService.getPbsUserBoardInfoList(pbsVO);
//			    List board_list = board100Service.getPbsUserBoardInfoList(pbsVO);
			
			    PbsUserBoardPartInfoVO partVO = new PbsUserBoardPartInfoVO();
			    partVO.setBoardId(boardId);
			    List part_list = admBoardNotiService.getPbsUserBoardPartInfoList(partVO);
//			    List part_list = board100Service.getPbsUserBoardPartInfoList(partVO);
			
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
	
 
	
	    return ".adm/adm/board/admBoardPbsNotiView";
   }
    
	/**
	 * 공지사항 리스트
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmNotiPopList")
	public String getAdmNotiPopList(
			@ModelAttribute("admBoardNotiPopInfoVO") AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO,
			ModelMap modelMap
			) throws Exception{
    	
    	/** pageing setting START */
		admBoardNotiPopInfoVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admBoardNotiPopInfoVO.setPageSize(propertiesService.getInt("pageSize"));
    	
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admBoardNotiPopInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admBoardNotiPopInfoVO.getPageUnit());
		paginationInfo.setPageSize(admBoardNotiPopInfoVO.getPageSize());
		
		admBoardNotiPopInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admBoardNotiPopInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admBoardNotiPopInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int surVeyListCnt = admBoardNotiService.getBoardPopupCnt(admBoardNotiPopInfoVO);
		
		paginationInfo.setTotalRecordCount(surVeyListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmBoardNotiPopInfoVO> boardPopupList = admBoardNotiService.getBoardPopupList(admBoardNotiPopInfoVO);
		for(int i=0;i<boardPopupList.size();i++){
			AdmBoardNotiPopInfoVO bvo = (AdmBoardNotiPopInfoVO)boardPopupList.get(i);
			bvo.setNotiTitle(CommUtil.scriptRemove(bvo.getNotiTitle()));
			bvo.setNotiTitleOrgn(CommUtil.scriptRemove(bvo.getNotiTitleOrgn()));
		}
		if(admBoardNotiPopInfoVO.getSearchGbn()==null || admBoardNotiPopInfoVO.getSearchGbn().equals("")){
			admBoardNotiPopInfoVO.setSearchGbn("titile");
		}
		
    	modelMap.put("boardPopupList", boardPopupList);
    	modelMap.put("admBoardNotiPopInfoVO", admBoardNotiPopInfoVO);
    	
		return ".adm/adm/board/admBoardPopupNotiList";
	}
	
	/**
	 * 공지사항 팝업 속성 상세보기
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmNotiPopView")
	public String getAdmNotiPopView(
			AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO,
			ModelMap modelMap
			) throws Exception{
		AdmBoardNotiPopInfoVO resultVo = new AdmBoardNotiPopInfoVO();
		resultVo = admBoardNotiService.selectAdmPopupNotiInfo(admBoardNotiPopInfoVO);
    	modelMap.put("notiInfo", resultVo);
		return ".adm/adm/board/admBoardPopupNotiView";
	}
	
 	/**
	* 공지사항 팝업 속성 수정
	* @param AdmRmgRestVO
	* @return ModelMap
	* @exception Exception
	*/
 	@RequestMapping(value = "/updateAdmPopupNoti", method = RequestMethod.POST)
	public ModelMap updateAdmPopupNoti(
 								@ModelAttribute
 								AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO,
 								ModelMap modelMap,
 								HttpSession session ) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 	
 		try{
 			int cnt = admBoardNotiService.selectAdmPopupNoti(admBoardNotiPopInfoVO);
 			
 			if(cnt > 0){
 				admBoardNotiService.updateAdmPopupNoti(admBoardNotiPopInfoVO, session);
 			}else{
 				admBoardNotiService.insertAdmPopupNoti(admBoardNotiPopInfoVO, session);
 			}
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
 	 * 공지사항 팝업 전체취소
 	 * @param AdmRmgRestVO
 	 * @return ModelMap
 	 * @exception Exception
 	 */
 	@RequestMapping(value = "/updateAdmAllPopupCancel", method = RequestMethod.POST)
 	public ModelMap updateAdmAllPopupCancel(
 			@ModelAttribute
 			AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO,
 			ModelMap modelMap,
 			HttpSession session ) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 		
 		try{
			admBoardNotiService.updateAdmAllPopupCancel(admBoardNotiPopInfoVO);
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
	 * 삭제이동게시물 목록
	 * @param AdmBoardNotiDelInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmBoardNotiDelList")
	public String getAdmBoardNotiDelList(
								AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO,
								ModelMap modelMap
								)
								throws Exception {
    	
    	/** pageing setting START */
    	admBoardNotiDelInfoVO.setPageUnit(admBoardNotiDelInfoVO.getPageUnit());
    	admBoardNotiDelInfoVO.setPageSize(propertiesService.getInt("pageSize"));
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admBoardNotiDelInfoVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admBoardNotiDelInfoVO.getPageUnit());
		paginationInfo.setPageSize(admBoardNotiDelInfoVO.getPageSize());
		admBoardNotiDelInfoVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admBoardNotiDelInfoVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admBoardNotiDelInfoVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int notiDelListCnt = admBoardNotiService.getAdmBoardNotiDelCnt(admBoardNotiDelInfoVO);
		
		paginationInfo.setTotalRecordCount(notiDelListCnt);
        modelMap.put("paginationInfo", paginationInfo);
        /** pageing setting END */
	   
    	List<AdmBoardNotiDelInfoVO> notiDelList = admBoardNotiService.getAdmBoardNotiDelList(admBoardNotiDelInfoVO);
    	modelMap.put("notiDelList", notiDelList);
    	
    	modelMap.put("pSearch", admBoardNotiDelInfoVO);
    	
   		return ".self/adm/board/admBoardNotiDelList";
	}
    
    /**
	 * 삭제이동게시물조회 단건
	 * @param AdmBoardNotiDelInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmBoardNotiDel")
	public String getAdmBoardNotiDel(
									AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO,
									ModelMap modelMap
									)
									throws Exception {
    	
    	AdmBoardNotiDelInfoVO notiDelInfo = admBoardNotiService.getAdmBoardNotiDel(admBoardNotiDelInfoVO);
	    modelMap.put("notiDelInfo", notiDelInfo);
	    
	    AdmBoardNotiDelInfoVO pSearch = new AdmBoardNotiDelInfoVO();
    	pSearch.setSearchCondition(admBoardNotiDelInfoVO.getSearchCondition());
    	pSearch.setSearchKeyword(admBoardNotiDelInfoVO.getSearchKeyword());
    	pSearch.setDelDiv(admBoardNotiDelInfoVO.getDelDiv());
    	pSearch.setRegDttmFrom(admBoardNotiDelInfoVO.getRegDttmFrom());
    	pSearch.setRegDttmTo(admBoardNotiDelInfoVO.getRegDttmTo());
    	pSearch.setPageIndex(admBoardNotiDelInfoVO.getPageIndex());
    	modelMap.put("pSearch", pSearch);
	    
   		return ".adm/adm/board/admBoardNotiDelView";
	}
 	
 	/**
     * 삭제게시물 복원
     * @param AdmBoardNotiDelInfoVO
     * @return ModelMap
     * @exception Exception
     */
 	@RequestMapping(value = "/updateAdmBoardNotiDelRollBack", method = RequestMethod.POST)
    public ModelMap updateAdmBoardNotiDelRollBack(
    										AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO,
    										ModelMap modelMap,
    										HttpSession session
    										) throws Exception {
 		
 		JSONResult jsonResult = new JSONResult();
 			
		try{
			admBoardNotiService.updateAdmBoardNotiDelRollBack(admBoardNotiDelInfoVO, session);
			jsonResult.setMessage("해당 게시물이 복원 되었습니다");
    		
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
    		jsonResult.setMessage(messageSource.getMessage("common.error"));
    		jsonResult.setErrMessage(e.getMessage());
		}
 		
 		modelMap.put("jsonResult", jsonResult);
 		return modelMap;
 	} 	
 	
    /**
	 * 삭제이동게시물조회 내용 조회
	 * @param AdmBoardNotiDelInfoVO
	 * @param ModelMap
	 * @return String
	 * @throws Exception
	 */
    @RequestMapping(value="/getAdmBoardNotiDelView")
	public String getAdmBoardNotiDelView(
									AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO,
						 			@RequestParam(value="boardId" ,required = true) String boardId,
						 			@RequestParam(value="notiId" ,required = true) String notiId,									
									ModelMap modelMap,
									HttpServletRequest request,
									HttpSession session
									)
									throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
    	AdmBoardNotiInfoVO bbsVO = new AdmBoardNotiInfoVO();
		bbsVO.setBoardId(admBoardNotiDelInfoVO.getBoardId());
    	
		AdmBoardNotiInfoVO bbsInfo = admBoardNotiService.getAdminBbsBoardInfo(bbsVO);
    	
		BbsNotiInfoVO bbsNotiInfoViewForNotiConts = board210Service.getBbsNotiInfoViewForNotiConts(admBoardNotiDelInfoVO.getNotiId());//본문가져오기 
		String notiConts = bbsNotiInfoViewForNotiConts==null?"":bbsNotiInfoViewForNotiConts.getNotiConts();
		
    	modelMap.put("notiConts",notiConts );
    	modelMap.put("boardId", admBoardNotiDelInfoVO.getBoardId());
    	//modelMap.put("pnum", pnum);
    	//modelMap.put("prev_pnum", prev_pnum);
    	//modelMap.put("next_pnum", next_pnum);
    	modelMap.put("boardName", bbsInfo.getBoardName());
    	modelMap.put("boardKind", bbsInfo.getBoardKind());
    	modelMap.put("boardForm", bbsInfo.getBoardForm());
    	modelMap.put("boardFormSpec", bbsInfo.getBoardFormSpec());
    	modelMap.put("replyWrteDiv", bbsInfo.getReplyWrteDiv());//답변쓰기 구분
    	modelMap.put("notiId", admBoardNotiDelInfoVO.getNotiId());
    	modelMap.put("userId", info.getId());
    	modelMap.put("pageIndex", admBoardNotiDelInfoVO.getPageIndex());
    	modelMap.put("pageUnit", admBoardNotiDelInfoVO.getPageUnit());
    	modelMap.put("likeUseYn", bbsInfo.getLikeUseYn());//좋아요_사용_여부
    	modelMap.put("agrmOppUseYn", bbsInfo.getAgrmOppUseYn());//찬성_반대_사용_여부
    	modelMap.put("nickUseYn", bbsInfo.getNickUseYn());//닉네임_사용_여부
    	modelMap.put("imgSvrUrl", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web"));
    	modelMap.put("imgRealDir", PortalxpertConfigUtils.getString("upload.real.dir"));
    	modelMap.put("realWeb", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.real.web"));
    	modelMap.put("admYn", bbsInfo.getAdmYn());//게시판 관리자 여부
    	modelMap.put("movDir", CONTEXT_PATH + PortalxpertConfigUtils.getString("upload.thumbnail.web"));
    	modelMap.put("boardExplUseYn", bbsInfo.getBoardExplUseYn());
		modelMap.put("boardExpl", bbsInfo.getBoardExpl());
		modelMap.put("notiEmailSendYn", bbsInfo.getNotiEmailSendYn());
		modelMap.put("searchCondition", admBoardNotiDelInfoVO.getSearchCondition());
		modelMap.put("searchKeyword", admBoardNotiDelInfoVO.getSearchKeyword());
	    
   		return ".self/adm/board/admBoardNotiDelView";
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
			ModelMap 		modelMap,
			HttpSession session) throws Exception {  
                	
    	JSONResult jsonResult = new JSONResult();

		try{	
			
			JSONObject bbsObject = JSONObject.fromObject(data);
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));
			logger.debug("notiId : "+(String)bbsObject.get("notiId"));
			String notiId = (String)bbsObject.get("notiId");
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			AdmBoardNotiInfoVO vvo = new AdmBoardNotiInfoVO();
			vvo.setNotiId(notiId);
			List notiInfo = admBoardNotiService.getBbsNotiInfoView(vvo);
			if(notiInfo == null || notiInfo.size() == 0) {
				notiInfo = new ArrayList<BbsNotiInfoVO>();
				notiInfo.add(new AdmBoardNotiInfoVO());
			}
					
			List notiFile = board210Service.getBbsNotiApndFileListForView(data);
			
			String boardId = (String)bbsObject.get("boardId");
			
			//List<BbsNotiInfoVO> notiPrevNextInfo = board210Service.getBbsPrevNextNotiInfoForView(data, auth,prev_pnum,next_pnum,notiReadmanAsgnYn, info.getId() );

			List notiOpn1 = board210Service.getBbsNotiOpnList1ForView(data);
			List notiOpn2 = board210Service.getBbsNotiOpnList2ForView(data);
			
			
			AdmBoardNotiInfoVO vo = (AdmBoardNotiInfoVO) notiInfo.get(0);
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

			
			vo.setNotiTagLst(CommUtil.scriptRemove(vo.getNotiTagLst()));
			
			modelMap.put("notiInfo", JSONUtils.objectToJSON(notiInfo));
			modelMap.put("notiFile", JSONUtils.objectToJSON(notiFile));
			modelMap.put("notiPrevNextInfo", "[]");
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
    
}
