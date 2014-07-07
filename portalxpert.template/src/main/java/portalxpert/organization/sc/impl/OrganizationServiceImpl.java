package portalxpert.organization.sc.impl;

import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.vo.PbsUserBoardCateUseVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.mapper.UserLoginMapper;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.mapper.OrganizationMapper;
import portalxpert.organization.sc.OrganizationService;
import portalxpert.organization.vo.BbsVO;
import portalxpert.organization.vo.CategoryVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.organization.vo.UserVO;
import portalxpert.organization.web.OrganizationController;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;


@Service("organizationService")
public class OrganizationServiceImpl extends AbstractServiceImpl implements  OrganizationService {
	
	private final static Logger logger = LoggerFactory.getLogger(OrganizationController.class);
	
    @Resource(name="organizationMapper")
    private OrganizationMapper organizationMapper;
    
    @Resource(name="userLoginMapper")
    private UserLoginMapper userLoginMapper;
    

    @Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	//private TransactionStatus transactionStatus;
	
	/** board210Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;

	public List<OrganizationVO> getOrganizationList() throws Exception{
		try{
			OrganizationVO vo = new OrganizationVO();
			vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
			return organizationMapper.getOrganizationList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	public List<OrganizationVO> getOrganizationListBySearch(String orgfullname) throws Exception {
		try{
			OrganizationVO vo = new OrganizationVO();
			vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
			vo.setOrgfullname(orgfullname);
			return organizationMapper.getOrganizationListBySearch(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	public List<UserVO> getUserList(OrganizationVO vo) throws Exception {
		try{
			return organizationMapper.getUserList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	public String insertCategory(CategoryVO vo) throws Exception {
    	    	
		String id ="";
		try {
			
			//Object savePoint = transactionStatus.createSavepoint();
			//vo.setId("test");
			organizationMapper.deleteCategory(vo);
			organizationMapper.insertCategory(vo);	

		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}	
		
		return id;
    }
	
	public String insertMyCategory(CategoryVO vo, HttpSession session) throws Exception {
    	
		String id ="";
		try {
			
			//Object savePoint = transactionStatus.createSavepoint();
			//vo.setId("test");
			organizationMapper.deleteCategory(vo);
			organizationMapper.insertCategory(vo);	
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			if(! vo.getBoardId().equals("")){
				JSONObject jo = JSONObject.fromObject(vo.getBoardId());
				 
				JSONArray list = jo.getJSONArray("boardId");
				
				PbsUserBoardCateUseVO pbsUserBoardCateUseVO = new PbsUserBoardCateUseVO();
				pbsUserBoardCateUseVO.setUserId(info.getId());
				organizationMapper.deletePbsUserBoardCateUse(pbsUserBoardCateUseVO); 
				
				for (Iterator i = list.iterator(); i.hasNext();) {
					JSONObject ob = (JSONObject) i.next();
					
					String sId = ob.getString("id");
					logger.debug("sId : "+sId);
					
					pbsUserBoardCateUseVO.setBoardId(sId);
					pbsUserBoardCateUseVO.setDelYn("N");
					pbsUserBoardCateUseVO.setRegrId(info.getId());
					pbsUserBoardCateUseVO.setRegrName(info.getName());
					pbsUserBoardCateUseVO.setUpdrId(info.getId());
					pbsUserBoardCateUseVO.setUpdrName(info.getName());
					organizationMapper.insertPbsUserBoardCateUse(pbsUserBoardCateUseVO);

				}
			}

		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}		
		
		return id;
    }
	
	public List<CategoryVO> getCategoryList(CategoryVO vo, String UserId, HttpSession session) throws Exception {
		try{
			List list = null;
			if(vo.getAdmin().equals("1")){//공용게시판 리스트
				vo.setId(Constant.ROLE_SUPER.getVal());
				String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
				if( superAdmin.equals(Constant.ROLE_SUPER.getVal()))
		    	{ 
					vo.setId(Constant.ROLE_SUPER.getVal());
		    	}
			}
			
			list = organizationMapper.getCategoryList(vo);//모든 게시판
			
			/*if (vo.getKind().equals("2")) //사용자용이면 나의 권한에 맞는 리스트를 생성한다. 
			{
				List categoryList = new ArrayList();
				
				BbsVO bbsVo = new BbsVO();
				bbsVo.setUserid(vo.getId());
				
				BoardSearchVO boardSearchVO = new BoardSearchVO();
				boardSearchVO.setUserId(UserId);
				
				String auth = board100Mapper.getUserBbsMapList(boardSearchVO);
				
				bbsVo.setUserMap(auth);
				logger.debug("auth : "+auth);
				logger.debug("getCategoryList : "+bbsVo.getUserMap());
				
				//List boardList = organizationMapper.getBbsAuthList(bbsVo);
				List boardList = organizationMapper.getBbsAuthListNew(bbsVo);//사용자에게 권한이 있는 게시판
				
				
				if (list.size() > 0)
				{
					CategoryVO category = (CategoryVO)list.get(0);//모든JSON 				
					JSONArray jsonArr = JSONArray.fromObject(category.getConts());		
					logger.debug("나의게시판 갯수 : "+boardList.size()+" !@! 모든게시판 갯수 : "+jsonArr.size());
					for (int i=0; i < boardList.size(); i++)
					{
						BbsVO bbs = (BbsVO)boardList.get(i);//사용자에게 권한이 있는 게시판
						for (int j=0; j < jsonArr.size(); j++)
						{
							JSONObject jsonObject = (JSONObject)jsonArr.get(j);
							if (bbs.getBoardId().equals(jsonObject.get("boardId")))
							{
								//logger.debug(bbs.getBoardId()+" 같다....!@##$@#$#@$@$##$@##$"+ jsonObject.get("boardId"));
								
								addPushList(categoryList, jsonObject);
								String pId = ""+jsonObject.get("pId");
								addCategoryList(category, categoryList, pId);
							}
						}
					}
					
					//정렬처리	
					List categoryOrderList = new ArrayList();
					for (int i=0; i < jsonArr.size(); i++)
					{
						JSONObject jsonObject = (JSONObject)jsonArr.get(i);
						
						for (int j=0; j< categoryList.size(); j++)
						{
							JSONObject json = (JSONObject)categoryList.get(j);
							
							if (jsonObject.equals(json))
							{
								categoryOrderList.add(json);
								break;
							}
						}
					}
					categoryList = null;
					bbsVo = null;
					
					list.clear();			
					String jsonStr = "[";
					for (int i=0; i< categoryOrderList.size(); i++)
					{
						JSONObject json = (JSONObject)categoryOrderList.get(i);
						jsonStr += json.toString()+",";
					}
					jsonStr = jsonStr.substring(0, jsonStr.length()-1)+"]";
					
					CategoryVO cateVo = new CategoryVO();
					cateVo.setId(vo.getId());
					cateVo.setKind(vo.getKind());
					cateVo.setConts(jsonStr);
					
					if (categoryOrderList.size() == 0)
					{
						cateVo.setConts("[{\"id\":1,\"pId\":0,\"boardId\":\"root\",\"name\":\"공통게시판\",\"icon\":\"../resources/images/img/img_category.png\"},{\"id\":99999,\"pId\":1,\"boardId\":\"99999999\",\"name\":\"임시저장\",\"icon\":\"../resources/images/img/img_category.png\"}]");
					}
					logger.debug("공통게시판 JSON : " + jsonStr);
					list.add(cateVo);
				}			
				
			}*/
			return list;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	public void addPushList(List categoryList, JSONObject obj) throws Exception{
		try{
			boolean contain = false;
			for (int i=0; i < categoryList.size(); i++)
			{
				JSONObject json = (JSONObject)categoryList.get(i);
				if (json.equals(obj))
				{
					contain = true;
					break;
				}
			}
			if (!contain) categoryList.add(obj);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	public void addCategoryList(CategoryVO category, List categoryList, String pid) throws Exception{
		try{
			JSONArray jsonArr = JSONArray.fromObject(category.getConts());
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject json = (JSONObject)jsonArr.get(i);
				String id = ""+json.get("id"); 
				if (id.equals(pid))
				{
					addPushList(categoryList, json);
					String pId = ""+json.get("pId");
					if(!pId.equals("0"))
					{
						addCategoryList(category, categoryList, ""+json.get("pId"));
					}
				}
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 사용자 직원 검색
	 * @author crossent
	 */
	public List<UserVO> getDeptMemberList(UserVO userVO) throws Exception {
		try{
			return organizationMapper.getDeptMemberList(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 사용자 직원 검색 건수
     * @author crossent
	 */
	public int getDeptMemberListTotCnt(UserVO userVO) throws Exception {
		try{
			return organizationMapper.getDeptMemberListTotCnt(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 직원 검색
	 * @author crossent
	 */
	public List<UserVO> getUserSearchList(UserVO userVO) throws Exception {
		try{
			return organizationMapper.getUserSearchList(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 직원 검색 건수
     * @author crossent
	 */
	public int getUserSearchListTotCnt(UserVO userVO) throws Exception {
		try{
			return organizationMapper.getUserSearchListTotCnt(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 부서명 가져오기
	 * @author crossent
	 */
	public UserVO getDeptName(UserVO userVO) throws Exception {
		try{
			return organizationMapper.getDeptName(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 부서 인원수 가져오기
	 * @author crossent
	 */
	public int getDeptNameMemberCnt(OrganizationVO vo) throws Exception {
		try{
			return organizationMapper.getDeptNameMemberCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 나의 권한에 맞는 게시판 리스트 
	 * @author 
	 */
	public List<BbsVO> getUserBbsMapList(BoardSearchVO vo) throws Exception {
		try{
			vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
			String auth = board100Mapper.getUserBbsMapList(vo);
			
			BbsVO bbsVo = new BbsVO();
			bbsVo.setUserMap(auth);
	
			return organizationMapper.getBbsAuthListNew(bbsVo);//사용자에게 권한이 있는 게시판
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 사용자 이미지 수정
	 * @param json, session
	 * @return 성공여부
	 * @exception Exception
	 * 
	 */
    public String updatePsnUserPoto(String json, String userId) throws Exception {
    	
		try {
			
			String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
			String SAVE_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
			String TEMP_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
			JSONObject ComUserInfoObject = JSONObject.fromObject(json);
			
			JSONArray jsonArr = (JSONArray)ComUserInfoObject.get("AppendList");	
			
			UserInfoVO userInfoVO = new UserInfoVO();
			String ssnId = userId;
			userInfoVO = userLoginMapper.getLoginInfoBySsnId(ssnId);
			
			String tempDir = CommUtil.makeTempDir(TEMP_DIR);
			TEMP_DIR = TEMP_DIR +"/"+ tempDir;
			for (int i=0; i < jsonArr.size(); i++)
			{
		
				JSONObject obj = (JSONObject)jsonArr.get(i);
				ComUserPotoInfoVO potoVO = new ComUserPotoInfoVO();
				potoVO.setDelYn("N");
				
				potoVO.setFileExts((String)obj.get("apndFileNameExt"));
				potoVO.setFileName((String)obj.get("apndFileName"));
				potoVO.setFilePath((String)obj.get("apndFilePath"));
				potoVO.setPrvwExts((String)obj.get("apndFileNameExt"));
				potoVO.setPrvwName((String)obj.get("apndFilePrvwName"));
				potoVO.setPrvwPath((String)obj.get("apndFilePath"));
				potoVO.setRegrId(userId);
				potoVO.setRegrName(userInfoVO.getName());
				potoVO.setUpdrId(userId);
				potoVO.setUpdrName(userInfoVO.getName());
				potoVO.setUserId(userId);
				
				String destDir = CommUtil.fullApndMakeDir(SAVE_DIR);
				
				
				if (WEB_DIR.endsWith("/")){
					WEB_DIR = WEB_DIR.substring(0, WEB_DIR.length()-1);
 				}
 				if (destDir.endsWith("/")){
 					destDir = destDir.substring(0, destDir.length()-1);
 				}
				
				
				potoVO.setFilePath(WEB_DIR+'/'+destDir+'/');	
				potoVO.setPrvwPath(WEB_DIR+'/'+destDir+'/');

				int upCnt = organizationMapper.updateUserPotoInfo(potoVO);
				if(!(upCnt > 0)){
					upCnt = organizationMapper.insertUserPotoInfo(potoVO);
				}
				
				String fullFileNm = potoVO.getFileName();
				//temp에서 real로 복사한다.
				if(upCnt > 0){
					CommUtil.apndFileCopy(TEMP_DIR,fullFileNm);
				}
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }

 

	/**
	 * 사용자 정보 수정 - 전화,핸드폰,생년월일
	 * @param telephoneNumber
	 * @param mobile
	 * @param birthDay
	 * @return
	 * @throws Exception
	 */
    public String updatePsnUserInfoMsg(String telephoneNumber, String mobile, String birthDay, String userId) throws Exception {
    	
    	try{

//        	LDAPControlMain cMain = new LDAPControlMain();
//        	//사용자 V.O 정의
//        	SiDoChargePrsnValue sido = new SiDoChargePrsnValue();
//        	
//    		//V.O에 값세팅 -  수정시 반영되지 않게 하기 위해서 값이 없거나 전페이지에서 넘어오지 않은값들은 반드시 null로 세팅해야한다.
//        	String strValue = null;
//        	sido.setCn(strValue);
//        	sido.setOu(strValue);
//        	sido.setOuCode(strValue);
//        	sido.setParentOUCode(strValue);
//        	sido.setTopOUCode(strValue);
//        	sido.setUid(strValue);
//        	sido.setDisplayName(strValue);
//        	sido.setDescription(strValue);
//        	sido.setSn(strValue);
//        	sido.setUserFullName(strValue);
//        	sido.setGivenname(strValue);
//        	sido.setPositionCode(strValue);
//        	sido.setPosition(strValue);
//        	sido.setTitleCode(strValue);
//        	sido.setTitle(strValue);
//        	sido.setTitleOrPosition(strValue);
//        	sido.setUserCertificate(strValue);
//        	sido.setMail(strValue);
//        	sido.setUserClass(strValue);
//        	sido.setUserType(strValue);
//    		sido.setEamPassword(null);
//        	sido.setFax(strValue);
//        	sido.setCompanyName(strValue);
//        	sido.setOrgFullName(strValue);
//        	sido.setUserAuthority(strValue);
//        	sido.setOrder(strValue);
//        	sido.setHomePostalAddress1(strValue);
//        	sido.setHomePostalAddress2(strValue);
//        	sido.setHomeFAXPhoneNumber(strValue);
//        	sido.setHomePhone(strValue);
//        	sido.setPager(strValue);
//        	sido.setJobTitle(strValue);
//        	sido.setNickName(strValue);
//        	sido.setEmpNumber(strValue);
//        	sido.setHomePageURL(strValue);
//        	sido.setHomePostalCode(strValue);
//        	sido.setIsOther(strValue);
//        	sido.setPhotoPath(strValue);
//        	sido.setGender(strValue);
//        	sido.setResiNumber(null);
//        	sido.setWeddingDate(strValue);
//        	sido.setCarNumber(strValue);
//        	sido.setOtherOUCode(strValue);
//        	sido.setOtherOU(strValue);
//        	sido.setStatus(strValue);
//        	sido.setOtherMail(strValue);
//        	sido.setSidoUserLevel(strValue);
//        	sido.setEPosID(strValue);
//        	sido.setEtitleGrade(strValue);
//        	sido.setSvID(strValue);
//        	sido.setEUserType(null);	//부서계정.
//        	sido.setSid(strValue);											
//        	sido.setPreresiNumber(strValue);			
//        	sido.setMailID(strValue);
//        	sido.setOtherPeriod("");
//
//        	//아래는 sso에서 사용하는 필드
//
//        	//수정시 등록정보는 수정 안함
//        	sido.setSidoAddUserId(null);
//        	sido.setSidoAddDate(null);
//        	sido.setSidoAddTime(null);
//
//    		//전화번호
//    		sido.setTelephoneNumber(telephoneNumber);
//    		//핸드폰
//    		sido.setMobile(mobile);
//    		//생년월일
//    		sido.setBirthday(birthDay);
//
//    		sido.setSidoChangeUserId(userId);
//    		
//    		String strDateTime = DateUtil.getDate();
//    		sido.setSidoChangeDate(strDateTime.substring(0,8));
//    		sido.setSidoChangeTime(strDateTime.substring(8,14));
//    		
//    		int res = cMain.modifyPrsn(userId,sido);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }

	/**
	 * 사용자 이미지 정보 삭제
	 */
    public String deletePsnUserPoto(String userId) throws Exception {
    	
    	try {

			int upCnt = organizationMapper.deleteUserPotoInfo(userId);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "OK";
    }
}
