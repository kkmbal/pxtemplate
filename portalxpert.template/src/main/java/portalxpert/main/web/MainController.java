package portalxpert.main.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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

import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.main.sc.MainService;
import portalxpert.main.vo.MainVO;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value="main")
public class MainController {
	
	@Resource(name="mainService")
	public MainService mainService;
	
//	@Resource(name="adwService")
//	public AdwService adwService;
//	
//	@Resource(name="setupService")
//	public SetupService setupService;
	
	@Resource(name="board100Service")
	public Board100Service board100Service;
	
	@Resource(name="board210Service")
	public Board210Service board210Service;
		
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
   
    private final static Logger logger = LoggerFactory.getLogger(MainController.class); 
    
    /**
     * 메인홈 프레임
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/mainFrame")
    public String mainFrame() throws Exception {
    	return ".main/main/mainFrame";
    }
    
    /**
     * 메인화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/main")
    public String main(
    		ModelMap modelMap,
    		HttpSession session
    		) throws Exception {    
    	
        String adminYn = (String) session.getAttribute("adminYn");
        
        int notiPopCnt = 0;
        notiPopCnt = mainService.getNotiPopupCnt();
        
        if (notiPopCnt > 0) {
        	modelMap.put("noticePopYn", "Y");
        } else {
        	modelMap.put("noticePopYn", "N");
        }
        
        int hotBrifPopCnt = 0 ;
        hotBrifPopCnt = mainService.getHotBriefingPopupCnt();
        
        if (hotBrifPopCnt > 0) {
        	modelMap.put("hotBrifPopYn", "Y");
        } else {
        	modelMap.put("hotBrifPopYn", "N");
        }
        
        modelMap.put("adminYn", adminYn);
    	return "main/mainHome";
    }
    
    
    /**
     * 업무관리 Embedded 화면
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value="/getWorkManageView")
    public String getWorkManageView() throws Exception {
    	return ".embedded/main/workManageView";
    }*/
    
    /**
     * 메일 Embedded 화면
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value="/getMailView")
    public String getMailView() throws Exception {
    	return ".embedded/main/mailView";
    }*/
    
    /**
  	 * 시장의 생각 조회
  	 * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
  	 */
    @RequestMapping(value="/getMayorThinkList")
    public ModelMap getMayorThinkList( 
    		ModelMap modelMap,
    		HttpSession session    		
    		) throws Exception { 
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();
    	JSONObject jsonObj = new JSONObject();
    	MainVO mainVO = new MainVO();
    	
    	List resultList = null;
    	
    	mainVO.setUserId(info.getId());
    	mainVO.setBoardId("BBS000138"); //시장의 생각 게시판 ID
    	
    	try {
    		resultList = mainService.getBoardList(mainVO);
    		
    		JSONArray jsonArr = new JSONArray();
    		
    		for (int i=0; i < resultList.size(); i++) {
    			
    			MainVO resultVO = (MainVO) resultList.get(i);
    			
    			jsonObj.put("boardId", resultVO.getBoardId());
    			jsonObj.put("notiId", resultVO.getNotiId());
    			
    			jsonObj.put("notiTitle", resultVO.getNotiTitleOrgn());
    			
	    		resultVO.setNotiTitleOrgn(CommUtil.cutString(resultVO.getNotiTitleOrgn(), 22, "..."));
	    		jsonObj.put("notiTitleOrgn", resultVO.getNotiTitleOrgn());
	    		
	    		jsonObj.put("notiOpnCnt", resultVO.getNotiOpnCnt());
	    		jsonObj.put("titleBoldYn", resultVO.getTitleBoldYn());
			    jsonObj.put("titleColorDiv", resultVO.getTitleColorDiv());
			    jsonObj.put("updDttm", resultVO.getUpdDttm());
			    
			    jsonArr.add(jsonObj);
    		}
    		
    		modelMap.put("resultList", JSONUtils.objectToJSON(jsonArr));
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}    	
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
  	 * 홍보배너 조회
  	 * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
  	 */
    @RequestMapping(value="/getPromoteBannerList")
    public ModelMap getPromoteBannerList( 
    		ModelMap modelMap
    		) throws Exception { 
    	
    	JSONResult jsonResult = new JSONResult();
    	JSONObject jsonObj = new JSONObject();    
    	
    	List resultList = null;
    	String filePath = "/uploadimage"; 
    	
    	try {
    		resultList = mainService.getPromoteBannerList();
    		
    		JSONArray jsonArr = new JSONArray();
    		
    		for (int i=0; i < resultList.size(); i++) {
    			
    			MainVO resultVO = (MainVO) resultList.get(i);
    			
    			resultVO.setTitle(resultVO.getTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
	    		resultVO.setTitle(resultVO.getTitle().replaceAll("'", "`"));
	    		resultVO.setTitle(CommUtil.cutString(resultVO.getTitle(), 26, "..."));
	    		
    			jsonObj.put("title", resultVO.getTitle());
    			jsonObj.put("linkUrl", resultVO.getLinkUrl());
    			jsonObj.put("altTxt", resultVO.getAltTxt());
    			
    			if (resultVO.getSetWidth().equals("0")) {
    				resultVO.setSetWidth("1024");
    				jsonObj.put("setWidth", resultVO.getSetWidth());
    			}
    			
        		if (resultVO.getSetHeight().equals("0")) {
        			resultVO.setSetHeight("768");
        			jsonObj.put("setHeight", resultVO.getSetHeight());
        		}
        		
        		jsonObj.put("imgNameOrg", resultVO.getImgNameOrg());
    			jsonObj.put("imgNameReal", resultVO.getImgNameReal());
    			jsonObj.put("imgPath", resultVO.getImgPath());
    			
    			jsonArr.add(jsonObj);
    		}
			    		
    		modelMap.put("resultList", JSONUtils.objectToJSON(jsonArr));
    		modelMap.put("filePath", JSONUtils.objectToJSON(filePath));
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}    	
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
     * 최근게시물 설정 팝업화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getRecentPostSetupPop")
    public String getRecentPostSetupPop() throws Exception {
    	return ".self/main/recentPostSetupPop";
    }
    
    /**
     * 최근게시물 설정 - 게시판 조회
     * @param modelMap
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getNoticeBoard")
    public ModelMap getNoticeBoard(
    		ModelMap modelMap,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	List resultList= null;
    	
    	try {
    		resultList = mainService.getUserBoardList(info);
    		
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
    	}
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
     * 최근게시물 설정 - 사용자 설정
     * @param modelMap
     * @param data
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/setSelectedBoard")
    public ModelMap setSelectedBoard(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String data,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	try {
    		mainService.setSelectedBoard(data, info);
    		
	    	jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		} catch (Exception e) {	 	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;  	
    }
    
    /**
     * 최근게시물 - 전체공지 조회
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getTotalNoticeList")
    public ModelMap getTotalNoticeList( 
    		ModelMap modelMap,
    		HttpSession session
    		) throws Exception { 
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	MainVO mainVO = new MainVO();
    	
    	JSONResult jsonResult = new JSONResult();	
    	JSONObject jsonObj = new JSONObject();  
    	
    	List resultList = null;
    	
    	try {
    		String auth = board100Service.getUserBbsMapList(info.getId());
    		
    		mainVO.setUserId(info.getId());
    		mainVO.setAuth(auth);
    		
    		resultList = mainService.getTotalNoticeList(mainVO);
    		
    		JSONArray jsonArr = new JSONArray();
    		
    		for (int i=0; i < resultList.size(); i++) {
    			
    			MainVO resultVO = (MainVO) resultList.get(i);
    			
    			jsonObj.put("notiId", resultVO.getNotiId());
    			jsonObj.put("boardId", resultVO.getBoardId());
    			
    			resultVO.setNotiTitleOrgn(CommUtil.cutString(resultVO.getNotiTitleOrgn(), 42, "..."));
    			jsonObj.put("notiTitleOrgn", resultVO.getNotiTitleOrgn());
	    		
	    		if (resultVO.getNotiConts() == null) {
					resultVO.setNotiConts("");
	    		}
	    		
	    		if (resultVO.getNotiConts().length() != 0 && resultVO.getNotiConts() != null && resultVO.getNotiConts() != "") {
					resultVO.setNotiConts(CommUtil.htmlRemove(resultVO.getNotiConts()));							
					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("&nbsp;", "").trim());
				} else {
					resultVO.setNotiConts("");
				}
	    		
	    		jsonObj.put("notiConts", resultVO.getNotiConts());
	    		jsonObj.put("notiOpnCnt", resultVO.getNotiOpnCnt());
	    		jsonObj.put("titleBoldYn", resultVO.getTitleBoldYn());
			    jsonObj.put("titleColorDiv", resultVO.getTitleColorDiv());
			    jsonObj.put("makrDispDiv", resultVO.getMakrDispDiv());//작성자표기구분
			    jsonObj.put("linkUrl", resultVO.getLinkUrl());
			    jsonObj.put("nickUseYn", resultVO.getNickUseYn());
			    jsonObj.put("userNick", resultVO.getUserNick());
			    jsonObj.put("notiKind", resultVO.getNotiKind());			    
			    jsonObj.put("readYn", resultVO.getNotiReadYn());
			    jsonObj.put("regrId", resultVO.getRegrId());
			    jsonObj.put("regrName", resultVO.getRegrName());
			    jsonObj.put("deptCode", resultVO.getDeptCode());
			    jsonObj.put("deptName", resultVO.getDeptName());
			    jsonObj.put("deptFname", resultVO.getDeptFname());
			    
			    jsonArr.add(jsonObj);
    		}
    		
    		modelMap.put("resultList", JSONUtils.objectToJSON(jsonArr));
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}    	
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }

    /**
     * 최근게시물 - 게시물 조회
     * @param modelMap
     * @param boardId
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getNoticeList")
    public ModelMap getNoticeList( 
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String boardId,
    		HttpSession session
    		) throws Exception { 
    	
    	UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();
    	JSONObject jsonObj = new JSONObject();
    	
    	try {
    		
			String auth = board100Service.getUserBbsMapList(info.getId());
			
			BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
			bbsVO.setBoardId(boardId);
			bbsVO.setUserId(info.getId());
			bbsVO.setUserMap(auth);
			
			BoardSearchVO boardSearchVO = new BoardSearchVO();
			List<BbsBoardInfoVO> list = board100Service.getAdminBbsBoardInfoList(bbsVO);
			BbsBoardInfoVO bbsInfo = list.get(0);
			//** PropertyService.sample *//*
			boardSearchVO.setPageUnit(20);
			boardSearchVO.setPageSize(1);
			boardSearchVO.setPageIndex(1);
	    	
	    	//** pageing setting *//*
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(boardSearchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(boardSearchVO.getPageUnit());
			paginationInfo.setPageSize(boardSearchVO.getPageSize());
			
			boardSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			boardSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			boardSearchVO.setBoardId(boardId);
			boardSearchVO.setUserId(info.getId());
			boardSearchVO.setNotiReadmanAsgnYn(bbsInfo.getNotiReadmanAsgnYn());
			boardSearchVO.setUserMap(auth);
			boardSearchVO.setOrderType("default");
			
			List resultList = board210Service.getBbsNotiInfoListForPaging(boardSearchVO);
    		
			JSONArray jsonArr = new JSONArray();
    		
    		for (int i=0; i < resultList.size(); i++) {
    			
    			BbsNotiInfoVO resultVO = (BbsNotiInfoVO) resultList.get(i);
    			
    			jsonObj.put("boardId", resultVO.getBoardId());
    			jsonObj.put("notiId", resultVO.getNotiId());
    			
    			resultVO.setNotiTitleOrgn(resultVO.getNotiTitleOrgn().replaceAll("@!", ""));
    			resultVO.setNotiTitleOrgn(CommUtil.cutString(resultVO.getNotiTitleOrgn(), 42, "..."));
	    		jsonObj.put("notiTitleOrgn", resultVO.getNotiTitleOrgn());
	    		
	    		if (resultVO.getNotiConts() == null) {
					resultVO.setNotiConts("");
	    		}
	    		
	    		if (resultVO.getNotiConts().length() != 0 && resultVO.getNotiConts() != null && resultVO.getNotiConts() != "") {
					resultVO.setNotiConts(CommUtil.htmlRemove(resultVO.getNotiConts()));							
					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));				
					resultVO.setNotiConts(resultVO.getNotiConts().replaceAll("&nbsp;", "").trim());
				} else {
					resultVO.setNotiConts("");
				}
	    		
	    		jsonObj.put("notiConts", resultVO.getNotiConts());
	    		jsonObj.put("notiOpnCnt", resultVO.getOpnCnt());
	    		jsonObj.put("titleBoldYn", resultVO.getTitleBoldYn());
			    jsonObj.put("titleColorDiv", resultVO.getTitleColorDiv());
			    jsonObj.put("notiKind", resultVO.getNotiKind());
			    jsonObj.put("makrDispDiv", bbsInfo.getMakrDispDiv());//작성자표기구분
			    jsonObj.put("linkUrl", resultVO.getLinkUrl());
			    jsonObj.put("readYn", resultVO.getNotiReadYn());
			    jsonObj.put("userNick", resultVO.getUserNick());
			    jsonObj.put("regrId", resultVO.getRegrId());
			    jsonObj.put("regrName", resultVO.getRegrName());
			    jsonObj.put("deptCode", resultVO.getDeptCode());
			    jsonObj.put("deptName", resultVO.getDeptName());
			    jsonObj.put("deptFname", resultVO.getDeptFname());
			    
			    jsonArr.add(jsonObj);
    		}
			
    		modelMap.put("resultList", JSONUtils.objectToJSON(jsonArr));
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}    	
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }

    /**
     * 최근게시물 - preview 시 읽음처리
     * @param modelMap
     * @param notiId
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/setBbsNotiInfoReadCnt")
    public ModelMap setBbsNotiInfoReadCnt( 
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String notiId,
    		HttpSession session
    		) throws Exception { 
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	MainVO mainVO = new MainVO();
    	
    	mainVO.setNotiId(notiId);
    	mainVO.setUserId(info.getId());
    	mainVO.setUserName(info.getName());
    	
    	try {
    		int cnt = mainService.getBbsNotiEvalInfoCnt(mainVO);
    		
    		if (cnt == 0) {
    			mainService.setBbsNotiInfoReadCnt(mainVO);    			
    		} else {
    			jsonResult.setSuccess(true);
    			jsonResult.setMessage("");
    		}
    		
    		jsonResult.setSuccess(true);
    		jsonResult.setMessage("");
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}    	
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
     * 관심시스템 조회
     * @param modelMap
     * @param value
     * @param session
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getTagCloudList")
    public ModelMap getTagCloudList(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String value,
    		HttpSession session
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	MainVO mainVO = new MainVO();
    	mainVO.setTagDiv(value);
    	
    	String strUserID = (String) session.getAttribute("USERID");
    	byte[] userId = null;
    	String encSSN = "";
    	
    	if (strUserID != null && !strUserID.equals("")) {
	    	sun.misc.BASE64Encoder encoder = new sun.misc.BASE64Encoder();
		    userId = strUserID.getBytes();
		    encSSN = encoder.encode(userId);
    	}
    	
	    List resultList = null;
	    
    	try {
    		resultList = mainService.getTagCloudList(mainVO);
    		
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
    		
    		modelMap.put("tagGb", mainVO.getTagDiv());
    		modelMap.put("encSSN", encSSN);
    	} catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}  
    	
    	modelMap.put("jsonResult", jsonResult);    	
    	return modelMap;
    }
    
    /**
     * 업무데스크 화면
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getUserWorkDeskSetup")
    public String getUserWorkDeskSetup() throws Exception {
    	return "main/userWorkDeskSetup";
    }
    
//    /**
//     * 업무데스크 조회
//     * @param modelMap
//     * @param type
//     * @param session
//     * @return
//     * @throws Exception
//     * @author crossent
//     */
//    @RequestMapping(value="/getUserWorkDeskList")
//    public ModelMap getUserWorkDeskList(
//    		ModelMap modelMap,
//    		@RequestParam(value="type", required=true) String type,
//    		HttpSession session
//    		) throws Exception {
//    	
//    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
//    	
//    	JSONResult jsonResult = new JSONResult();
//    	List resultList = null;
//    	List categoryList = null;
//    	
//    	String flag = "";
//    	int count = 0;
//    	
//    	try {
//    		
//    		resultList = mainService.getUserWorkDeskList(info);
//    		categoryList = adwService.getAdwCategoryList();
//    		
//    		if (type.equals("1")) {
//	    		count = mainService.getUserWorkDeskCnt(info);
//	    		
//	    		if (count == 0) {
//	    			count = mainService.getDefaultWorkDeskCnt();
//	    			flag = "default";
//	    		}
//	    		
//	    		flag = "user";
//    		} else if (type.equals("2")) {
//	    		count = mainService.getDefaultWorkDeskCnt();
//	    		flag = "default";
//    		}
//
//    		modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
//    		modelMap.put("categoryList", JSONUtils.objectToJSON(categoryList));
//    		modelMap.put("count", count);
//    		modelMap.put("flag", flag);
//    	} catch (Exception e) {
//	 		jsonResult.setSuccess(false);
//    		jsonResult.setMessage(messageSource.getMessage("common.error"));
//		}  
//    	
//    	modelMap.put("jsonResult", jsonResult);    	
//    	return modelMap;
//    }
    
    /**
     * 업무데스크 - 사용자 설정
     * @param modelMap
     * @param data
     * @param session
     * @return
     * @throws Exception
	 * @author crossent	 
     */
    @RequestMapping(value="/setUserWorkDesk")
    public ModelMap setUserWorkDesk(
    		ModelMap modelMap,
    		@RequestParam(value="data", required=true) String data,
    		HttpSession session
    		) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	try {
    		mainService.setUserWorkDesk(data, info);
    		
	    	jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		} catch (Exception e) {	 
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;
    }
    
 
    
    /**
     * 최근 동영상/이미지 조회
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getRecentVodImageList")
    public ModelMap getRecentVodImageList(
    		HttpServletRequest request,
    		ModelMap modelMap
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();	
    	String imagePath = "/uploadimage"; 
    	String moviePath = PortalxpertConfigUtils.getString("upload.thumbnail.web");
    	
    	try {
    		List resultList = mainService.getRecentVodImageList();
    		
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
    		
    		modelMap.put("imagePath", JSONUtils.objectToJSON(imagePath));
    		modelMap.put("moviePath", PortalxpertConfigUtils.getString("image.web.contextpath") + JSONUtils.objectToJSON(moviePath));
		} catch (Exception e) {	 
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;
    }
    
    /**
     * 많이 본 동영상/이미지 조회
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getMostViewedVodImageList")
    public ModelMap getMostViewedVodImageList(
    		HttpServletRequest request,
    		ModelMap modelMap
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();	
    	String imagePath = "/uploadimage"; 
    	String moviePath = PortalxpertConfigUtils.getString("upload.thumbnail.web");
    	
    	try {
    		List resultList = mainService.getMostViewedVodImageList();
    		
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
    		
    		modelMap.put("imagePath", JSONUtils.objectToJSON(imagePath));
    		modelMap.put("moviePath", PortalxpertConfigUtils.getString("image.web.contextpath") + JSONUtils.objectToJSON(moviePath));
		} catch (Exception e) {	 
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);        
		return modelMap;
    }
    
    /**
     * 공지게시 팝업 
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getNotiPopup")
    public ModelMap getNotiPopup(
    		ModelMap modelMap
    		) throws Exception {

    	JSONResult jsonResult = new JSONResult();
    	
    	try {	
    		List resultList = mainService.getNotiPopup();
			
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
		} catch (Exception e) {	 	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
     * 핫브리핑 팝업 
     * @param modelMap
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getHotBriefingPopup")
    public ModelMap getHotBriefingPopup(
    		ModelMap modelMap
    		) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();	
    	
    	//String filePath = "/uploadimage";
    	
    	try {
    		List resultList = mainService.getHotBriefingPopup();
    		
    		if (resultList.size() > 0) {
    			modelMap.put("resultList", JSONUtils.objectToJSON(resultList));
    			//modelMap.put("filePath", JSONUtils.objectToJSON(filePath));
    		} else {
    			modelMap.put("resultList", "[]");
    		}
		} catch (Exception e) {	
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
}    