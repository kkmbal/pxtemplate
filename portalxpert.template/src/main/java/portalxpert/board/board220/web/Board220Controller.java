package portalxpert.board.board220.web;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.board.board220.sc.Board220Service;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("board220")
public class Board220Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board220Controller.class); 
   
	/** boardService */
	/** board210Service */
    @Resource(name = "board210Service")
    private Board210Service board210Service;
    
    /** board220Service */
    @Resource(name = "board220Service")
    private Board220Service board220Service;
    
	/** board100Service */
    @Resource(name = "board100Service")
    private Board100Service board100Service;    
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
	 * SNS형 게시판 리스트 
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author 
	 */
	@RequestMapping(value="/getBbsSnsBoardList")                            
	 public String getBbsSnsBoardList(
				ModelMap modelMap,
	 			@ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
	 			@RequestParam(value="boardId",required = true) String boardId,
	 			@RequestParam(value="pageIndex",required = false, defaultValue="1") String pageIndex,
	 			@RequestParam(value="pageUnit",required = false, defaultValue="10") String pageUnit,
	 			@RequestParam(value="searchCondition",required = false) String searchCondition,
	 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,	 			
				HttpSession session,
				HttpServletRequest request)  throws Exception {

   		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		String auth = board100Service.getUserBbsMapList(info.getId());
		
		String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
		String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
    	String CONTEXT_PATH = PortalxpertConfigUtils.getString("image.web.contextpath");
    	
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
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setBoardAnmtUseYn(bbsInfo.getBoardAnmtUseYn());//공지사용여부
		boardSearchVO.setBoardKind(bbsInfo.getBoardKind());
		boardSearchVO.setUserId(info.getId());
		boardSearchVO.setEamAdminYn(getEamAdmBoardAdmYNForList(session, bbsInfo));
		boardSearchVO.setUserNotiSeq(999999999);
		boardSearchVO.setRowNum(10);

		searchKeyword = searchKeyword == null?"":searchKeyword;
		searchKeyword = URLDecoder.decode(searchKeyword,"UTF-8");

		int sortSeq = 999999999;
		int lastSortSeq = 999999999;
		
		List<BbsNotiInfoVO> noti_list = board220Service.getBbsNotiInfoListForTmln(boardSearchVO);//게시글 조회
   		if (noti_list.size() > 0)
   		{
   			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
   			lastSortSeq = vo.getSortSeq();
   		}
		
		BbsNotiOpnVO opnVo = new BbsNotiOpnVO();
		opnVo.setUserId(info.getId());
		opnVo.setSortSeq(String.valueOf(sortSeq));
		opnVo.setRnum(String.valueOf(10));
		List opn_list = board220Service.getBbsNotiOpnListForTmln(opnVo);
		
		logger.debug("noti_list : "+noti_list.size());
		
		BbsNotiApndFileVO apnVo = new BbsNotiApndFileVO();
		apnVo.setUserId(info.getId());
		apnVo.setSortSeq(String.valueOf(sortSeq));
		apnVo.setRnum(String.valueOf(10));
		List apn_list = board220Service.getBbsNotiApndListForTmln(apnVo);
		
 		String IMG_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.img.max");
		String IMG_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.img.size");
		
		String APND_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.apnd.max");
		String APND_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.apnd.size");
		
		modelMap.put("imgUploadMax", IMG_UPLOAD_MAX);
		modelMap.put("imgUploadSize", IMG_UPLOAD_SIZE);
		modelMap.put("apndUploadMax", APND_UPLOAD_MAX);
		modelMap.put("apndUploadSize", APND_UPLOAD_SIZE);
		
		logger.debug("bbsInfo.getMakrDispDiv() : "+bbsInfo.getMakrDispDiv());
		modelMap.put("notiReadmanAsgnYn", bbsInfo.getNotiReadmanAsgnYn());
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
		modelMap.put("notiList", CommUtil.scriptRemove(JSONUtils.objectToJSON(noti_list)));
		modelMap.put("opnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(opn_list)));
		modelMap.put("apndList", JSONUtils.objectToJSON(apn_list));
		modelMap.put("boardName", bbsInfo.getBoardName());
		modelMap.put("boardForm", bbsInfo.getBoardForm());
		modelMap.put("searchCondition", searchCondition);
		modelMap.put("searchKeyword", searchKeyword);
		modelMap.put("lastSortSeq", lastSortSeq);
		if("80".equals(request.getServerPort())){
			modelMap.put("host", "http://"+request.getRemoteHost());
		}else{
			modelMap.put("host", "http://"+request.getRemoteHost()+":"+request.getServerPort());
			
		}
		logger.debug("getBoardInfoList pageIndex : "+pageIndex);
		modelMap.put("userId", info.getId());
		modelMap.put("eamAdminYn", getEamAdmBoardAdmYNForList(session, bbsInfo));
		modelMap.put("btnViewYn", boardBtnViewYn);
		modelMap.put("WEB_DIR", CONTEXT_PATH + WEB_DIR);
		
		
 	   return ".self/board/snsBbsListView";
 	   		   
	}
   
    /**
     * 글 조회
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping("/getNotiTmlnInfo")
    public ModelMap getNotiTmlnInfo(
 		   @RequestParam(value="data" ,required = true) String data,
            ModelMap model,
            HttpServletRequest request,
            HttpSession session
            )  
            throws Exception {

    	JSONResult jsonResult = new JSONResult();
    	jsonResult.setSuccess(true);
    	
    	try{
	    	JSONObject jsonObject = JSONObject.fromObject(data);
	    	String boardId = (String)jsonObject.get("boardId");
	    	String notiId = (String)jsonObject.get("notiId");
	    	String searchCondition = (String)jsonObject.get("searchCondition");
	    	String searchKeyword = (String)jsonObject.get("searchKeyword");
	    	int sortSeq = jsonObject.getInt("sortSeq");
	    	
	    	
	   		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			String auth = board100Service.getUserBbsMapList(info.getId());
			
	
			
			BoardSearchVO boardSearchVO = new BoardSearchVO();
	
	
			boardSearchVO.setBoardId(boardId);
			boardSearchVO.setSearchCondition(searchCondition);
			boardSearchVO.setSearchKeyword(searchKeyword);
			boardSearchVO.setUserId(info.getId());
			
	
			boardSearchVO.setUserMap(auth);
			boardSearchVO.setUserId(info.getId());
			boardSearchVO.setUserId(info.getId());
	
			boardSearchVO.setUserId(info.getId());
	
			boardSearchVO.setUserNotiSeq(sortSeq);
			boardSearchVO.setRowNum(10);
	
			searchKeyword = searchKeyword == null?"":searchKeyword;
			searchKeyword = URLDecoder.decode(searchKeyword,"UTF-8");
	
			
			List<BbsNotiInfoVO> noti_list = board220Service.getBbsNotiInfoListForTmln(boardSearchVO);//게시글 조회
			int lastSortSeq = 999999999;
	   		if (noti_list.size() > 0)
	   		{
	   			BbsNotiInfoVO vo = (BbsNotiInfoVO)noti_list.get(noti_list.size()-1);
	   			lastSortSeq = vo.getSortSeq();
	   		}
			
			BbsNotiOpnVO opnVo = new BbsNotiOpnVO();
			opnVo.setUserId(info.getId());
			opnVo.setSortSeq(String.valueOf(sortSeq));
			opnVo.setRnum(String.valueOf(10));
			List opn_list = board220Service.getBbsNotiOpnListForTmln(opnVo);
			
			BbsNotiApndFileVO apnVo = new BbsNotiApndFileVO();
			apnVo.setUserId(info.getId());
			apnVo.setSortSeq(String.valueOf(sortSeq));
			apnVo.setRnum(String.valueOf(10));
			List apn_list = board220Service.getBbsNotiApndListForTmln(apnVo);
			
	 	    
			String IMG_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.img.max");
			String IMG_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.img.size");
			
			String APND_UPLOAD_MAX = PortalxpertConfigUtils.getString("person.tmln.apnd.max");
			String APND_UPLOAD_SIZE = PortalxpertConfigUtils.getString("person.tmln.apnd.size");
			
			model.put("imgUploadMax", IMG_UPLOAD_MAX);
			model.put("imgUploadSize", IMG_UPLOAD_SIZE);
			model.put("apndUploadMax", APND_UPLOAD_MAX);
			model.put("apndUploadSize", APND_UPLOAD_SIZE);
			
	 	    model.put("notiList", CommUtil.scriptRemove(JSONUtils.objectToJSON(noti_list)));
	 	    model.put("opnList", CommUtil.scriptRemove(JSONUtils.objectToJSON(opn_list)));
	   		model.put("apndList", JSONUtils.objectToJSON(apn_list));
	   		model.put("userId", info.getId());
	   		model.put("lastSortSeq", lastSortSeq);
    	}catch(Exception e){
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
    	}
 		model.put("jsonResult", jsonResult);
        return model;
    }	
	
    /**
     * 게시물 수정
     * @param modelMap
     * @throws Exception
     */
    @RequestMapping(value = "/updateBbsNotiInfo")
    @ResponseBody
    public ModelMap updateBbsNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session,
			HttpServletRequest request
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();
    		BbsNotiInfoVO vo = new BbsNotiInfoVO(); 

 		try{	
 			//쓰기권한체크
			//getBoardUserMapWriteYN(session, data, jsonResult);

			JSONObject jsonObject = JSONObject.fromObject(data);
			data = jsonObject.toString();

			vo = board220Service.updateBbsNotiInfo(data, session, request);
			vo.setNotiConts(CommUtil.scriptRemove(vo.getNotiConts()));
			
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
}

