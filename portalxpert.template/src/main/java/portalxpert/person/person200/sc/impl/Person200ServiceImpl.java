package portalxpert.person.person200.sc.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.mapper.OrganizationMapper;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person200.mapper.Person200Mapper;
import portalxpert.person.person200.sc.Person200Service;
import portalxpert.person.person200.vo.MyProjectCommunityVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;

@Service("person200Service")
public class Person200ServiceImpl extends EgovAbstractServiceImpl implements  Person200Service {
	
	/** person200Mapper */
    @Resource(name="person200Mapper")
    private Person200Mapper person200Mapper;
    

    @Resource(name="organizationMapper")
    private OrganizationMapper organizationMapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /*@Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;*/
	
	private final static Logger logger = LoggerFactory.getLogger(Person200ServiceImpl.class); 
	
	
	public List<BbsNotiInfoVO> getNotiSetContentsList(String userId) throws Exception
    {
		try{
			List<BbsNotiInfoVO> notiInfo = person200Mapper.getNotiSetContentsList(userId);
			
			
			if(notiInfo.size() == 0 )
			{
				notiInfo = 	person200Mapper.getNotiDefaultContentsList();
				
			}
			return notiInfo;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	public List<MyProjectCommunityVO> getMyPjtCouList(String userId,String mGubun) throws Exception
    {
		try{
			MyProjectCommunityVO vo = new MyProjectCommunityVO();
			
			vo.setUserId(userId);
			vo.setmGubun(mGubun);
			logger.debug("userId : "+userId);
			logger.debug("mGubun : "+mGubun);
			
	        return person200Mapper.getMyPjtCouList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	public List<OrganizationVO> getOrgFriendList(OrganizationVO vo) throws Exception
    {
		try{
			vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
			List<OrganizationVO> frdlist =person200Mapper.getOrgFriendList(vo);
			
			return frdlist;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	
	/*
     * 나의 관심직원 등록
     * @author crossent
     * */
   
    public String createMyMemberList(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject memObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");				
	    	JSONArray jsonArr = (JSONArray)memObject.get("PsnUserGrpFriendMap");	
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);				
				//delete 
				if( i == 0 )
				{
					PsnUserGrpFrdMapVO delInfo = new PsnUserGrpFrdMapVO();
			    	delInfo.setUserId(info.getId());
			    	delInfo.setGrpSeq(Integer.parseInt((String)obj.get("grpSeq")));
					int delCnt = person200Mapper.deletePsnUserGrpFriendMap(delInfo);					
				}
				PsnUserGrpFrdMapVO myGrpInfo = new PsnUserGrpFrdMapVO();				
				myGrpInfo.setUserId((String)info.getId());
				myGrpInfo.setGrpSeq(obj.getInt("grpSeq"));
				myGrpInfo.setFriendId((String)obj.get("friendId"));				
				myGrpInfo.setDelYn("N");
				myGrpInfo.setRegrId((String)info.getId());
				myGrpInfo.setRegrName(info.getName());
				myGrpInfo.setUpdrId((String)info.getId());
				myGrpInfo.setUpdrName(info.getName());
				
				logger.debug(myGrpInfo.getUserId());
				logger.debug(""+myGrpInfo.getGrpSeq());
				logger.debug(myGrpInfo.getFriendId());
				logger.debug(myGrpInfo.getRegrId());
				logger.debug(myGrpInfo.getRegrName());
				person200Mapper.insertPsnUserGrpFriendMap(myGrpInfo);
			}
			
			//관심직원 갱신
			person200Mapper.deletePsnUserFriendInfo(info.getId());
			
			
			PsnUserGrpFrdMapVO myGrpInfo = new PsnUserGrpFrdMapVO();				
			myGrpInfo.setUserId((String)info.getId());
			myGrpInfo.setGrpSeq(0);
			myGrpInfo.setFriendId("");				
			myGrpInfo.setDelYn("N");
			myGrpInfo.setRegrId((String)info.getId());
			myGrpInfo.setRegrName(info.getName());
			myGrpInfo.setUpdrId((String)info.getId());
			myGrpInfo.setUpdrName(info.getName());
			
			person200Mapper.insertPsnUserFriendInfo(myGrpInfo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    public String updatePsnUserFriendInfo(HttpSession session) throws Exception
    {
    	try {
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	//관심직원 갱신
			person200Mapper.deletePsnUserFriendInfo(info.getId());
			
			
			PsnUserGrpFrdMapVO myGrpInfo = new PsnUserGrpFrdMapVO();				
			myGrpInfo.setUserId((String)info.getId());
			myGrpInfo.setGrpSeq(0);
			myGrpInfo.setFriendId("");				
			myGrpInfo.setDelYn("N");
			myGrpInfo.setRegrId((String)info.getId());
			myGrpInfo.setRegrName(info.getName());
			myGrpInfo.setUpdrId((String)info.getId());
			myGrpInfo.setUpdrName(info.getName());
			
			person200Mapper.insertPsnUserFriendInfo(myGrpInfo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";	
			
    }
    
    
    public int deletePsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception 
    {
    	try{
    		return person200Mapper.deletePsnUserGrpFriendMap(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    public int deletePsnUserGrpInfo(PsnUserGrpFrdMapVO vo) throws Exception 
    {
    	try{
    		return person200Mapper.deletePsnUserGrpInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
    }
    
    
    
    
    /**
     * 나의 그룹  이름 수정
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
    public int updatePsnUserGrpInfo(PsnUserGrpInfoVO vo)throws Exception
    {
    	try{
    		return person200Mapper.updatePsnUserGrpInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 나의그룹 정보 조회
	 * @param PsnUserGrpInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserGrpInfoVO> getPsnUserGrpInfo(PsnUserGrpInfoVO vo) throws Exception
    {
    	try{
    		return person200Mapper.getPsnUserGrpInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    /**
	 * 나의그룹 정보 조회
	 * @param PsnUserGrpInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public int insertMyGroup(PsnUserGrpInfoVO vo) throws Exception
    {
    	try{
    		return person200Mapper.insertMyGroup(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    /**
	 * 나의그룹 별 멤버 조회 
	 * @param PsnUserGrpFrdMapVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserGrpFrdMapVO> getPsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception
    {
    	try{
    		return person200Mapper.getPsnUserGrpFriendMap(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    
}
