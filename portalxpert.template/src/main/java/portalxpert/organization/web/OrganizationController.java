package portalxpert.organization.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.board.board210.sc.Board210Service;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.sc.UserLoginService;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.sc.OrganizationService;
import portalxpert.organization.vo.BbsVO;
import portalxpert.organization.vo.CategoryVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.organization.vo.UserVO;
import portalxpert.person.person100.sc.Person100Service;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping("organization")
public class OrganizationController {
    
	@Resource(name="organizationService")
	OrganizationService organizationService;
	
	@Resource(name="board210Service")
	Board210Service board210Service;
	
	@Resource(name="userLoginService")
	UserLoginService userLoginService;
	
	@Resource(name="person100Service")
	Person100Service person100Service;
	
	/** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
	
    private final static Logger logger = LoggerFactory.getLogger(OrganizationController.class); 
   
    
    /**********************************************************************************
     * 조직도 팝업 organization chart/ organogram
     * @param type 1:부서선텍,2:사용자선텍
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/organizationPop.jsp
     * @throws Exception
    ***********************************************************************************/

    @RequestMapping(value="/organizationChart2")                            
    public String getOrganizationChart2(
    		@RequestParam(value="type",required = true) String type,
    		@RequestParam(value="callback" ,required = true) String callback,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    
    	List<OrganizationVO> list = organizationService.getOrganizationList();
    	/*
    	ObjectMapper mapper = new ObjectMapper();
    	ObjectWriter writer =  mapper.writer().withDefaultPrettyPrinter();
    	logger.debug(writer.writeValueAsString(list));
    	*/
    	modelMap.put("organizationList", JSONUtils.objectToJSON(list).toString());
    	modelMap.put("type", type);
    	modelMap.put("callback", callback);
    	
    	return ".self/organization/organizationChartPop";
    }
    
    /**********************************************************************************
     * 조직도 팝업 organization chart/ organogram
     * @param orgfullname 부서명
     * @param modelMap
     * @return modelMap
     * @throws Exception
    ***********************************************************************************/
    @RequestMapping(value="/getOrganizationList" , produces="application/json")
    public ModelMap getOrganizationList(
    		@RequestParam("orgfullname") String orgfullname,
    		HttpSession session,
    		ModelMap modelMap)
    				throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try{
    		List<OrganizationVO> organizationList=null;
    		if(orgfullname!=null && orgfullname.trim().equals("")){
    			organizationList = organizationService.getOrganizationList();
    		}else{
    			organizationList = organizationService.getOrganizationListBySearch(orgfullname);
    		}
    		modelMap.put("organizationList", organizationList);
    	}catch (Exception e) {
    		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**********************************************************************************
     * 조직도 에서 사용자조회
     * @param oucode 부서코드
     * @param modelMap
     * @return modelMap
     * @throws Exception
    ***********************************************************************************/
    @RequestMapping(value = {"/getUserList"} , produces="application/json")
    public ModelMap getUserList(
    		@ModelAttribute OrganizationVO org,
    		ModelMap modelMap)	throws Exception 
    	{
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try {
    		List<UserVO> userList = organizationService.getUserList(org);
    		
    		modelMap.put("userList", userList);
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**********************************************************************************
     * 사용자 팝업  chart 
     * @param type 
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/personPop.jsp
     * @throws Exception
    ***********************************************************************************/
    @RequestMapping(value="/personChart")                            
    public String getPersonChart(
    		@RequestParam(value="type",required = true) String type,
    		@RequestParam(value="callback" ,required = true) String callback,
    		@RequestParam(value="search",required = true) String search,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {    	
    	modelMap.put("type", type);
    	modelMap.put("callback", callback);
    	modelMap.put("search", search);
    	
    	
        return ".self/organization/personChartPop";
    }
    
    /**********************************************************************************
     * 카테고리 팝업  chart 
     * @param type 
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/categoryChartPop.jsp
     * @throws Exception
    ***********************************************************************************/

    @RequestMapping(value="/categoryChartPop")                            
    public String getCategoryChart(
    		@ModelAttribute CategoryVO categoryVO,
    		@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:사용자용, 2:관리자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)
    		@RequestParam(value="notiId" ,required = false) String notiId,
    		@RequestParam(value="mode" ,required = false) String mode,//mode : move :게시물 이동
    		@RequestParam(value="userNotiSeq" ,required = false) String userNotiSeq,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
 
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		categoryVO.setId(info.getId());
		categoryVO.setKind(kind);
		categoryVO.setAdmin(admin);
		List<CategoryVO> list = organizationService.getCategoryList(categoryVO,info.getId(), session );
		String conts = "", userId ="";
		if(list.size() == 0){
			
			conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"게시판\",\"icon\":\"../resources/images/img/img_category.png\"}]";
			
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
			modelMap.put("myBoardList", JSONUtils.objectToJSON(board_list));
			
		}
		else
		{
			modelMap.put("categoryList", JSONUtils.objectToJSON(conts));
			modelMap.put("myBoardList", "[]");
		}
    		
 
		
		modelMap.put("userNotiSeq", userNotiSeq);
		modelMap.put("notiId", notiId);
    	modelMap.put("type", type);//카테고리 타입(1:조회용, 2:관리용)
    	modelMap.put("kind", kind);//카테고리 종류(1:사용자용, 2:관리자용)
    	modelMap.put("mode", mode);
    	modelMap.put("admin", admin);
    	
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	modelMap.put("superAdmin", superAdmin);
    	
        return ".self/organization/categoryChartPop";
    }
    
    /**********************************************************************************
     * 카테고리 팝업  chart 
     * @param type 
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/categoryChartPop.jsp
     * @throws Exception
    ***********************************************************************************/

    @RequestMapping(value="/myCategoryChartPop")                            
    public String getMyCategoryChart(
    		@ModelAttribute CategoryVO categoryVO,
    		@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:사용자용, 2:관리자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)
    		@RequestParam(value="notiId" ,required = false) String notiId,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
  
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		categoryVO.setId(info.getId());
		categoryVO.setKind(kind);
		categoryVO.setAdmin(admin);
		List<CategoryVO> list = organizationService.getCategoryList(categoryVO, info.getId(), session);
		
		String conts = "";
		if(list.size() == 0){
			conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"MY게시판\",\"icon\":\"../resources/images/img/img_category_off.gif\" ,\"iconOpen\":\"../resources/images/img/img_category_on.gif\" ,\"iconClose\":\"../resources/images/img/img_category_off.gif\"}]";
		}else{
			
			CategoryVO vo = (CategoryVO)list.get(0);
			conts = vo.getConts();
			
		}
		modelMap.put("categoryList", conts);
 
		modelMap.put("notiId", notiId);
    	modelMap.put("type", type);//카테고리 타입(1:조회용, 2:관리용)
    	modelMap.put("kind", kind);//카테고리 종류(1:사용자용, 2:관리자용)    	
    	
        return ".self/organization/myCategoryChartPop";
    }
    
    /**
     * 공통게시판 조회
     * @param categoryVO
     * @param kind
     * @param type
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/getCommonBoardListForZtree")                            
    public ModelMap getCommonBoardListForZtree(
    		@ModelAttribute CategoryVO categoryVO,
    		@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:관리자용, 2:사용자용) 2
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)  1
    		//@RequestParam(value="callback" ,required = true) String callback,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		if (admin.equals("1"))
    		{
    			categoryVO.setId(Constant.ROLE_SUPER.getVal());
    		}
    		else
    		{
    			categoryVO.setId(info.getId());
    		}
    		//categoryVO.setId(Constant.ROLE_SUPER.getVal());
    		categoryVO.setKind(kind);
    		categoryVO.setAdmin(admin);
    		
    		
    		List<CategoryVO> list = organizationService.getCategoryList(categoryVO, info.getId(), session);
    		
    		String conts = "";
    		if(list.size() == 0){
    			//conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"공통게시판\",\"icon\":\"../resources/images/img/img_category.png\"},{\"id\":99999,\"pId\":1,\"boardId\":\"SPT999999\",\"name\":\"임시저장\",\"icon\":\"../resources/images/img/img_category.png\"}]";
    			conts = "[" +
    					"  {\"id\":99997,\"pId\":0,\"boardId\":\"BBS999997\",\"name\":\"부서업무공지\",\"icon\":\"../resources/images/img/img_category_off.gif\" ,\"iconOpen\":\"../resources/images/img/img_category_on.gif\" ,\"iconClose\":\"../resources/images/img/img_category_off.gif\" }" +
    					", {\"id\":99998,\"pId\":0,\"boardId\":\"BBS999998\",\"name\":\"경조사\",\"icon\":\"../resources/images/img/img_category.png\" ,\"iconOpen\":\"../resources/images/img/img_category_on.gif\" ,\"iconClose\":\"../resources/images/img/img_category_off.gif\"}" +
    					", {\"id\":99999,\"pId\":0,\"boardId\":\"BBS999999\",\"name\":\"임시저장\",\"icon\":\"../resources/images/img/img_category.png\" ,\"iconOpen\":\"../resources/images/img/img_category_on.gif\" ,\"iconClose\":\"../resources/images/img/img_category_off.gif\"}" +
    					"]";
    		}else{
    			
    			CategoryVO vo = (CategoryVO)list.get(0);
    			conts = vo.getConts();
    			
    		}
    		
    		//폐쇄된것 제외
    		JSONArray updateJsonArr = new JSONArray();
    		if(!"".equals(conts)){
	    		JSONArray jsonArr = JSONArray.fromObject(conts);
	    		
	    		BbsVO bvo = new BbsVO();
	    		bvo.setBoardOperYn("N");
	    		List<BbsVO> bbsOperList = organizationService.getBbsOperInfo(bvo); //폐쇄된 게시판 리스트
				
	    		boolean isEqual = false;
				for (int i=0; i < jsonArr.size(); i++){
					for(BbsVO v : bbsOperList){
						if(v.getBoardid().equals(jsonArr.getJSONObject(i).getString("boardId"))){
							isEqual = true;
							break;
						}
					}
					if(!isEqual) updateJsonArr.add(jsonArr.getJSONObject(i));
					isEqual = false;
				}
    		}
    		
    		
    		
    		//logger.info("getCommonBoardListForZtree conts : "+conts);
    		
    		if (kind.equals("2")) //사용자용이면 나의 권한에 맞는 리스트를 생성한다. 
    		{
    			
    			BoardSearchVO boardSearchVO = new BoardSearchVO();
    			List<BbsVO> board_list = null;
    			
    			boardSearchVO.setUserId(info.getId());
    			board_list = organizationService.getUserBbsMapList(boardSearchVO);

    			//modelMap.put("categoryList", conts);    			
    			modelMap.put("categoryList", updateJsonArr.toString());    			
    			modelMap.put("myBoardList", board_list);
    		}
    		else
    		{
    			//modelMap.put("categoryList", conts);
    			modelMap.put("categoryList", updateJsonArr.toString());
    			modelMap.put("myBoardList", "[]"); 
    		}
    		
    		jsonResult.setMessage("");
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	modelMap.put("type", type);//카테고리 타입(1:조회용, 2:관리용)
    	modelMap.put("kind", kind);//카테고리 종류(1:사용자용, 2:관리자용)
    	modelMap.put("jsonResult", jsonResult);
    	
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	modelMap.put("superAdmin", superAdmin);
    	
        return modelMap;
    }
    
    /**
     * 공용게시판 카테고리 등록
     * @param categoryVO
     * @param conts
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertCategoryList", method = RequestMethod.POST)
    public ModelMap insertCategoryList(
    		CategoryVO 		categoryVO,
    		@RequestParam(value="conts" ,required = true) String conts,
    		HttpSession session,
			ModelMap 		modelMap ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			categoryVO.setId(Constant.ROLE_SUPER.getVal());
			organizationService.insertCategory(categoryVO);
			
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
     * 사용자게시판 카테고리 등록
     * @param categoryVO
     * @param conts
     * @param session
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/insertMyCategoryList", method = RequestMethod.POST)
    public ModelMap insertMyCategoryList(
    		CategoryVO 		categoryVO,
    		@RequestParam(value="conts" ,required = true) String conts,
    		HttpSession session,
			ModelMap modelMap ) throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();

		try{			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			categoryVO.setId(info.getId());
			organizationService.insertMyCategory(categoryVO, session);
			
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
    
    
    /**********************************************************************************
     * 조직도 에서 사용자조회
     * @param oucode 
     * @param modelMap
     * @return modelMap
     * @throws Exception
    ***********************************************************************************/
    @RequestMapping(value = {"/getCategoryList"} , produces="application/json")
    public ModelMap getCategoryList(
    		CategoryVO 		categoryVO,    		
    		HttpSession session,
    		ModelMap modelMap)	throws Exception 
    	{
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		categoryVO.setId(info.getId());    		
    		List<CategoryVO> categoryList = organizationService.getCategoryList(categoryVO, info.getId(), session);
    		
    		
    		modelMap.put("categoryList", categoryList);
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	modelMap.put("jsonResult", jsonResult);    	
    	
    	return modelMap;
    }
    
    /**
     * 조직 검색
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getDeptSearchList")
    public String getDeptSearchList(
    	@RequestParam(value="menu",required = true) String menu,
		HttpSession session,
		ModelMap modelMap)
        throws Exception {

		List<OrganizationVO> list = organizationService.getOrganizationList();
		/*
		ObjectMapper mapper = new ObjectMapper();
		ObjectWriter writer =  mapper.writer().withDefaultPrettyPrinter();
		logger.debug(writer.writeValueAsString(list));
		*/
		modelMap.put("organizationList", JSONUtils.objectToJSON(list).toString());
		modelMap.put("menu", menu);

		return ".self/organization/deptSearchList";
    }
    
    /**
     * 사용자 검색
     * @param org
     * @param modelMap
     * @return
     * @throws Exception
     */
    @RequestMapping(value = {"/getSearchUserList"} , produces="application/json")
    public ModelMap getSearchUserList(
    		@ModelAttribute OrganizationVO org,
    		ModelMap modelMap)	throws Exception 
    	{
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	try {
    		UserVO userVO = new UserVO();
    		
    		List<UserVO> userList = organizationService.getUserList(org);
    		
    		int totCnt = organizationService.getDeptNameMemberCnt(org);
    		
    		if (org.getOucode() == null) {
    			userVO.setOucode("9999");
    		} else {
    			userVO.setOucode(org.getOucode());
    		}
    		
    		UserVO resultVO = organizationService.getDeptName(userVO);
    		
    		modelMap.put("userList", userList);
    		modelMap.put("resultVO", resultVO);
    		modelMap.put("totCnt", totCnt);
    		
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
    	
    	modelMap.put("jsonResult", jsonResult);
    	return modelMap;
    }
    
    /**
     * 직원 검색
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/getUserSearchList")
    public String getUserSearchList(
    		@ModelAttribute("searchVO") UserVO userVO,
    		@RequestParam(value="menu",required = true) String menu,
    		HttpSession session,
    		ModelMap modelMap) throws Exception {
    	
    	UserInfoVO info = (UserInfoVO) session.getAttribute("pxLoginInfo");
    	
    	userVO.setOucode(info.getOucode());
    	
    	//** PropertyService.sample *//
    	userVO.setPageUnit(propertiesService.getInt("pageUnit"));
    	userVO.setPageSize(propertiesService.getInt("pageSize"));
  	   
  	   	//** pageing setting *//
  	   	PaginationInfo paginationInfo = new PaginationInfo();
  	   	paginationInfo.setCurrentPageNo(userVO.getPageIndex());
  	   	paginationInfo.setRecordCountPerPage(userVO.getPageUnit());
  	   	paginationInfo.setPageSize(userVO.getPageSize());
  		
  	   	userVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
  	   	userVO.setLastIndex(paginationInfo.getLastRecordIndex());
  	   	userVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
  	   	
  	   	List<UserVO> resultList = null;
  	    int totCnt = 0;
  	  
  	   	if (userVO.getUserName().equals("") && userVO.getDeptName().equals("") && userVO.getPhone().equals("") && userVO.geteMail().equals("")) {
  	   		resultList = organizationService.getDeptMemberList(userVO);
  	   		totCnt = organizationService.getDeptMemberListTotCnt(userVO);
  	   	} else {
  	   		resultList = organizationService.getUserSearchList(userVO);
  	   		totCnt = organizationService.getUserSearchListTotCnt(userVO);
  	   	}
  	   	
  	   	paginationInfo.setTotalRecordCount(totCnt);		
  	   	
  	   	modelMap.put("resultList", resultList);
  	   	modelMap.put("paginationInfo", paginationInfo);
  	   	modelMap.put("searchVO", userVO);
    	modelMap.put("menu", menu);
    	
    	return ".self/organization/userSearchList";
    }
    
    /**********************************************************************************
     * 조직도 팝업 organization chart/ organogram
     * @param type 1:부서선텍,2:사용자선텍
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/organizationPop.jsp
     * @throws Exception
    ***********************************************************************************/

    @RequestMapping(value="/organizationChart")                            
    public String getOrganizationChart(
    		@RequestParam(value="type",required = true) String type,
    		@RequestParam(value="callback" ,required = true) String callback,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    	List<OrganizationVO> list = organizationService.getOrganizationList();
    	modelMap.put("organizationList", JSONUtils.objectToJSON(list).toString());
    	modelMap.put("type", type);
    	modelMap.put("callback", callback);
    	modelMap.put("oucode", info.getOucode());
    	
    	return ".self/organization/organizationChartPop";
    }
    
    /**********************************************************************************
     * 게시판 종합검색에서 사용 되는 게시판 카테고리 
     * @param type 
     * @param callback javascript 함수
     * @param modelMap
     * @return organization/totCategoryChartPop.jsp
     * @throws Exception
    ***********************************************************************************/

    @RequestMapping(value="/totCategoryChartPop")                            
    public String getTotCategoryChart(
    		@ModelAttribute CategoryVO categoryVO,
    		@RequestParam(value="kind" ,required = true) String kind, //카테고리 종류(1:관리자용, 2:사용자용)
    		@RequestParam(value="type" ,required = true) String type, //카테고리 타입(1:조회용, 2:관리용)
    		@RequestParam(value="admin" ,required = true) String admin, //카테고리 게시판구분(1:공용게시판, 2:사용자게시판)
    		@RequestParam(value="select" ,required = false) String select, //선택 옵션
    		@RequestParam(value="portlet" ,required = false) String portlet, //포틀릿
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
    	
    	JSONResult jsonResult = new JSONResult();
    	
 
    		
		if (select == null)
		{
			select = "1";
		}
		
		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
		categoryVO.setId(info.getId());
		categoryVO.setKind(kind);
		categoryVO.setAdmin(admin);
		List<CategoryVO> list = organizationService.getCategoryList(categoryVO, info.getId(), session);
		
		String conts = "";
		if(list.size() == 0){
			conts = "[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"MY게시판\",\"icon\":\"../resources/images/img/img_category_off.gif\" ,\"iconOpen\":\"../resources/images/img/img_category_on.gif\" ,\"iconClose\":\"../resources/images/img/img_category_off.gif\"}]";
		}else{
			
			CategoryVO vo = (CategoryVO)list.get(0);
			conts = vo.getConts();
			
		}
		
		if (kind.equals("2")) //사용자용이면 나의 권한에 맞는 리스트를 생성한다. 
		{
			BoardSearchVO boardSearchVO = new BoardSearchVO();
			
			boardSearchVO.setUserId(info.getId());
			
			List<BbsVO> board_list = organizationService.getUserBbsMapList(boardSearchVO);
			
			modelMap.put("categoryList", conts);    			
			modelMap.put("myBoardList", JSONUtils.objectToJSON(board_list));
			
		}
		else
		{
			modelMap.put("categoryList", conts);
			modelMap.put("myBoardList", "[]");
		}
		
		
		//modelMap.put("categoryList", conts);
		jsonResult.setSuccess(true);
    		
 
		//modelMap.put("notiId", notiId);
    	modelMap.put("type", type);//카테고리 타입(1:조회용, 2:관리용)
    	modelMap.put("kind", kind);//카테고리 종류(1:사용자용, 2:관리자용)
    	modelMap.put("select", select);//선택 옵션(1:멀티, 2:싱글)
    	String title_name = "카테고리 관리";
    	if (select.equals("2")) title_name = "게시판 선택";
    	modelMap.put("title_name", title_name);//선택 옵션(1:멀티, 2:싱글)
    	String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
    	modelMap.put("superAdmin", superAdmin);
    	modelMap.put("portlet", portlet);
    	
        return ".self/organization/totCategoryChartPop";
    }
    
    /**
     * 조직도 화면 프레임
     * @return
     * @throws Exception
     * @author crossent
     */
    @RequestMapping(value="/organizationFrame")
    public String organizationFrame() throws Exception {
    	return ".organization/organization/organizationFrame";
    }
    
	/**
	 *  메신저 개인정보 조회
	 * @param session
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value="/getMsgUserInfo", method = RequestMethod.POST)
    public ModelMap getMsgUserInfo(
    		@RequestParam(value="data", required=true) String data,
    		HttpServletRequest request,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {

		JSONResult jsonResult = new JSONResult();

		try{
			String userId = data;
	
			String[] I_searchFactorArr = {"uid"};
			String[] I_searchValueArr  = {userId};
	
	//		try {
	//
	//			LDAPControlMain cMain = new LDAPControlMain();
	//			SiDoChargePrsnValue[] sido = cMain.searchPrsnMain(I_searchFactorArr, I_searchValueArr,"AND");
	//
	//			modelMap.put("displayName",sido[0].getDisplayName());
	//			modelMap.put("orgFullName",sido[0].getOrgFullName());
	//			modelMap.put("telephoneNumber",sido[0].getTelephoneNumber());
	//			modelMap.put("mobile",sido[0].getMobile());
	//			modelMap.put("birthday",sido[0].getBirthday());
	//		} catch (Exception e) {
	//			modelMap.put("displayName","");
	//			modelMap.put("orgFullName","");
	//			modelMap.put("telephoneNumber","");
	//			modelMap.put("mobile","");
	//			modelMap.put("birthday","");
	//		}
			
			
			/*사용자 이미지정보*/
			String sPathFileExt="";
	        List<ComUserPotoInfoVO> list = person100Service.getPsnUserPotoInfoListForRollEtc(userId, "1");
	        if(list.size() > 0){
				ComUserPotoInfoVO vo = (ComUserPotoInfoVO)list.get(0);
				sPathFileExt = PortalxpertConfigUtils.getString("image.web.contextpath") + vo.getFilePath() + vo.getFileName();
				modelMap.put("user_pic", JSONUtils.objectToJSON(sPathFileExt));
			}else{
				modelMap.put("user_pic", PortalxpertConfigUtils.getString("person.image.default"));
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
	 * 사용자 이미지 수정
	 * @param data
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMsgUserPotoInfo", method = RequestMethod.POST)
	public ModelMap updateMsgUserPotoInfo(
			@RequestParam(value = "data", required = true) String data,
			@RequestParam(value = "telephoneNumber", required = true) String telephoneNumber,
			@RequestParam(value = "mobile", required = true) String mobile,
			@RequestParam(value = "birthDay", required = true) String birthDay,			
			@RequestParam(value = "potoFlag", required = true) String potoFlag,			
			@RequestParam(value = "userId", required = true) String userId,			
			ModelMap modelMap,HttpSession session) throws Exception {
			
			
		JSONResult jsonResult = new JSONResult();
		
		try {
			
			if(potoFlag.equals("Y")){
				organizationService.deletePsnUserPoto(userId);
			}else{
				organizationService.updatePsnUserPoto(data, userId);
			}
			organizationService.updatePsnUserInfoMsg(telephoneNumber, mobile, birthDay, userId);
			
			UserInfoVO userInfoVO = new UserInfoVO();
			userInfoVO = userLoginService.getLoginInfoBySsnId(userId);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage(userInfoVO.getName()+" 님의 대한 사용자정보가 정상적으로 저장되었습니다.");
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}

		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
	
	/**
	 *  메신저 사용자 수정 - 사진삭제
	 * @param modelMap
	 * @param session
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUserPoto")
	public ModelMap deleteUserPoto(
			ModelMap modelMap,HttpSession session) throws Exception {
		
		JSONResult jsonResult = new JSONResult();
		try {
			modelMap.put("user_pic", PortalxpertConfigUtils.getString("person.image.default"));
			jsonResult.setSuccess(true);
		} catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		
		return modelMap;
	}
}
