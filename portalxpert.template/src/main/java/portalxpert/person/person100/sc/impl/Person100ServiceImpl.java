package portalxpert.person.person100.sc.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.mapper.Board300Mapper;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.person.person100.mapper.Person100Mapper;
import portalxpert.person.person100.sc.Person100Service;
import portalxpert.person.person100.vo.AdmTmlnEnvSetVO;
import portalxpert.person.person100.vo.ComCntsInfoVO;
import portalxpert.person.person100.vo.ComUserDeptInfoVO;
import portalxpert.person.person100.vo.ComUserInfoVO;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import portalxpert.person.person100.vo.PsnUserBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrTagVO;
import portalxpert.person.person100.vo.PsnUserFrdInfoVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person100.vo.PsnUserTmlnAlertSetVO;
import portalxpert.person.person100.vo.SmsSendHistVO;
import portalxpert.person.person100.vo.TmlnEnvInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;



@Service("person100Service")
public class Person100ServiceImpl extends EgovAbstractServiceImpl implements  Person100Service {
	
	/** person100Mapper */
    @Resource(name="person100Mapper")
    private Person100Mapper person100Mapper;
    
    /** board300Mapper */
    @Resource(name="board300Mapper")
    private Board300Mapper board300Mapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	private final static Logger logger = LoggerFactory.getLogger(Person100ServiceImpl.class); 
	
	
	
	/**
	 * 사용자 정보
	 * @param comUserInfoVO - 조회할 정보가 담긴 VO
	 * @return 사용자 정보
	 * @exception Exception
	 */
	public List getComUserInfoList(ComUserInfoVO comUserInfoVO) throws Exception {
		try{
			return person100Mapper.getComUserInfoList(comUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 사용자 정보
	 * @param comUserInfoVO - 조회할 정보가 담긴 VO
	 * @return 사용자 정보
	 * @exception Exception
	 */
	public List getComUserPotoInfoList(ComUserPotoInfoVO comUserPotoInfoVO) throws Exception{
		try{
			return person100Mapper.getComUserPotoInfoList(comUserPotoInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	

	/**
	 * 사용자 정보 수정 닉네임, 선택이미지, 업무분장
	 * @param json, session
	 * @return 성공여부
	 * @exception Exception
	 * 
	 */
    public String updatePsnUserInfo(String json, HttpSession session) throws Exception {
    	
		try {
			
			
			String SAVE_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
			String TEMP_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
			String MID_DIR = PortalxpertConfigUtils.getString("upload.real.web");
			JSONObject ComUserInfoObject = JSONObject.fromObject(json);
			ComUserInfoVO comUserInfoVO = new ComUserInfoVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			
			comUserInfoVO.setUserId(info.getId());
			comUserInfoVO.setUpdrId(info.getId());
//			comUserInfoVO.setResiNumber(ec.decrypt(info.getId()));
			comUserInfoVO.setUpdrName(info.getName());
			comUserInfoVO.setUserNick((String)ComUserInfoObject.get("userNick"));
			comUserInfoVO.setSlctPotoSeq(ComUserInfoObject.getInt("slctPotoSeq"));
			comUserInfoVO.setUserJob((String)ComUserInfoObject.get("userJob"));
			
			int result = person100Mapper.getPsnUserInfo(comUserInfoVO);
			if(result == 0){
				person100Mapper.insertPsnUserInfo(comUserInfoVO);
			}else{
				person100Mapper.updatePsnUserInfo(comUserInfoVO);//닉네임, 선택이미지, 업무분장 수정
			}
			
			JSONArray jsonArr = (JSONArray)ComUserInfoObject.get("AppendList");	
			
			ComUserPotoInfoVO cpoto = new ComUserPotoInfoVO();
			
			
			String destDir = CommUtil.fullApndMakeDir(SAVE_DIR);
			cpoto.setUserId(info.getId());
			person100Mapper.deleteComUserPotoInfo(cpoto);
			String tempDir = CommUtil.makeTempDir(TEMP_DIR);
			
			if (TEMP_DIR.endsWith("/")){
				TEMP_DIR = TEMP_DIR.substring(0, TEMP_DIR.length()-1);
			}
			
			TEMP_DIR = TEMP_DIR +"/"+ tempDir; 
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				ComUserPotoInfoVO potoVO = new ComUserPotoInfoVO();
				String newAddImg = (String)obj.get("newAddImg");
				potoVO.setDelYn("N");
				int potoSeq = 1;
				try{
					potoSeq = Integer.parseInt((String)(obj.get("potoSeq")));
				}catch(NumberFormatException e){
				}
				potoVO.setPotoSeq(potoSeq);
				potoVO.setFileExts((String)obj.get("apndFileNameExt"));
				potoVO.setFileName((String)obj.get("apndFileName"));
				potoVO.setPrvwExts((String)obj.get("apndFileNameExt"));
				potoVO.setPrvwName((String)obj.get("apndFilePrvwName"));
				potoVO.setRegrId(info.getId());
				potoVO.setRegrName(info.getName());
				potoVO.setUpdrId(info.getId());
				potoVO.setUpdrName(info.getName());
				potoVO.setUserId(info.getId());
				potoVO.setFilePath(MID_DIR+'/'+destDir+'/');// ==> /person/2013/07/01	
				potoVO.setPrvwPath(MID_DIR+'/'+destDir+'/');
				
				
				
				
				logger.debug("updatePsnUserInfo destDir : "+destDir);
				logger.debug("updatePsnUserInfo potoSeq : "+(String)(obj.get("potoSeq")));
				logger.debug("updatePsnUserInfo apndFileName : "+(String)(obj.get("apndFileName")));
				logger.debug("updatePsnUserInfo newAddImg : "+newAddImg);
				logger.debug("updatePsnUserInfo setFilePath : "+MID_DIR+'/'+destDir+'/');
				
				if(newAddImg.equals("Y")){
					
					logger.debug("TEMP_DIR : "+TEMP_DIR);
					//임시디렉토리에 존재한 대표이미지 파일을 저장한다.
					CommUtil.apndFileCopy(TEMP_DIR, potoVO.getFileName());
				}else{///webref/images/photo
					//
					//기존이미지 webref/images/photo =>> person
					//c:/tomcat6/webapps/ROOT/ImageServer/img_root   /person/2013/07/01
					
					String path = obj.getString("apndFilePath");  //webref/images/upload/2013/07/25
					//String rtnPath = apndFileCopy(IMG_DIR+(String)obj.get("apndFilePath"),potoVO.getFileName());
					
					if (path.indexOf("photo") > 0)
					{
						path = PortalxpertConfigUtils.getString("upload.temp.dir2"); 
					}
					else
					{
						path = path.substring(path.length() -12, path.length());  // /2013/07/28/
					}
					
					
				    String rtnPath = CommUtil.apndFileCopy(SAVE_DIR+path,potoVO.getFileName());
					if (rtnPath.equals("")) //이미지 카피에 실패 했으면 신규 디렉토리에서 찾는다.
					{
						//if (!SAVE_DIR.endsWith("/")) SAVE_DIR = SAVE_DIR+"/";						
						CommUtil.apndFileCopy(path,potoVO.getFileName());
					}
					
				}
				person100Mapper.insertComUserPotoInfo(potoVO);
			}//end for
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    
	/**
	 * MY게시판 조회
	 * @param pbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회
	 * @exception Exception
	 */
	public List getPbsUserBoardInfoList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception {
		try{
			return person100Mapper.getPbsUserBoardInfoList(pbsUserBoardInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * MY게시판 조회 ZTREE
	 * @param pbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회
	 * @exception Exception
	 */
	public List<PbsUserBoardInfoVO> getPbsUserBoardInfoListForZTree(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception {
		try{
			List list = person100Mapper.getPbsUserBoardInfoListForZTree(pbsUserBoardInfoVO);
	
			return list;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * MY게시판 조회 ZTREE
	 * @param pbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회
	 * @exception Exception
	 */
	public List<PbsUserBoardInfoVO> getPersonLnbPartList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception{
		List list = null;
		try{
			BoardSearchVO vo = new BoardSearchVO();
	    	vo.setUserId(pbsUserBoardInfoVO.getBoardOwnrId());
	    	vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
	    	String auth = board300Mapper.getUserBbsMapList(vo);
	    	pbsUserBoardInfoVO.setUserMap(auth);
			list = person100Mapper.getPersonLnbPartList(pbsUserBoardInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}

		return list;
	}
	
	/**
	 * MY게시판 조회 ZTREE
	 * @param pbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회
	 * @exception Exception
	 */
	public List<PbsUserBoardInfoVO> getPersonLnbFavoList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception{
		try{
			List list = person100Mapper.getPersonLnbFavoList(pbsUserBoardInfoVO);
	
			return list;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    

    

    
    
    
   
    /**
	 * 개인정보관리 SMS발송이력
	 * @param smsSendHistVO
	 * @return SmsSendHistVO - 개인정보관리 SMS발송이력
	 * @throws Exception
	 */   
    public List<SmsSendHistVO> getSmsSendHistList(SmsSendHistVO vo) throws Exception
    {
    	try{
    		return person100Mapper.getSmsSendHistList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인정보관리 SMS발송이력 총 개수
	 * @param SmsSendHistVO - 조회할 정보가 담긴 VO
	 * @return 총 개수
	 * @exception Exception
	 */
    public int getSmsSendHistListTotCnt(SmsSendHistVO smsSendHistVO) throws Exception{
    	try{
    		return person100Mapper.getSmsSendHistListTotCnt(smsSendHistVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    /**
     * SMS발송이력 삭제
     * @param smsSendHistVO
     * @return 삭제 성공 
     * @throws Exception
     */
    public String deleteSmsSendHist(String json, HttpSession session) throws Exception {
    	try {
			
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
			JSONArray jsonArr = (JSONArray)bbsObject.get("sendSeqList");		
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				SmsSendHistVO smsSendHistVO = new SmsSendHistVO();
				smsSendHistVO.setSendSeq(obj.getInt("sendSeq"));
				smsSendHistVO.setUserId(info.getId());
				smsSendHistVO.setUpdrId(info.getId());
				smsSendHistVO.setUpdrName(info.getId());
				person100Mapper.deleteSmsSendHist(smsSendHistVO);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    
    /**
     * 관심태그 등록
     * @param json, session
     * @return 삭제 성공 
     * @throws Exception
     */
    public String insertPsnUserCncrTag(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject jobj = JSONObject.fromObject(json);
			//게시판 기본 테이블
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONArray jsonArr = (JSONArray)jobj.get("cncr_tag_list");		

			
			person100Mapper.deletePsnUserCncrTagList((String)info.getId());
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				PsnUserCncrTagVO psnUserCncrTagVO = new PsnUserCncrTagVO();
				
				psnUserCncrTagVO.setUserId((String)info.getId());
				psnUserCncrTagVO.setCncrTag(obj.getString("cncrTag"));
				psnUserCncrTagVO.setRegrId((String)info.getId());
				psnUserCncrTagVO.setRegrName(info.getName());
				psnUserCncrTagVO.setUpdrId((String)info.getId());
				psnUserCncrTagVO.setUpdrName(info.getName());
				
				
				person100Mapper.insertPsnUserCncrTag(psnUserCncrTagVO);
				
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
	/**
	 * 관심태그 조회
	 * @param PsnUserCncrTagVO - 조회할 정보가 담긴 VO
	 * @return 관심태그 정보
	 * @exception Exception
	 */
	public List getPsnUserCncrTagList(PsnUserCncrTagVO psnUserCncrTagVO) throws Exception{
		try{
			return person100Mapper.getPsnUserCncrTagList(psnUserCncrTagVO);
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
			
			JSONArray jsonArr = (JSONArray)memObject.get("PsnUserFriendInfo");		

			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);				
				PsnUserFrdInfoVO myMemberInfo = new PsnUserFrdInfoVO();				
				myMemberInfo.setUserId((String)info.getId());
				myMemberInfo.setFriendId((String)obj.get("friendId"));
				myMemberInfo.setDelYn("N");
				myMemberInfo.setRegrId((String)info.getId());
				myMemberInfo.setRegrName(info.getName());
				myMemberInfo.setUpdrId((String)info.getId());
				myMemberInfo.setUpdrName(info.getName());
				
				
				/*logger.debug(myMemberInfo.getUserId());
				logger.debug(myMemberInfo.getFriendId());
				logger.debug(myMemberInfo.getDelYn());
				logger.debug(myMemberInfo.getRegrId());
				logger.debug(myMemberInfo.getRegrName());*/
				
				person100Mapper.deletePsnUserFriendInfo(myMemberInfo);				
				person100Mapper.insertPsnUserFriendInfo(myMemberInfo);
			}
			
			
			jsonArr = (JSONArray)memObject.get("PsnUserGrpInfo");		

			person100Mapper.deletePsnUserGrpInfo((String)info.getId());
			
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				PsnUserGrpInfoVO myGrpInfo = new PsnUserGrpInfoVO();
				
				myGrpInfo.setUserId((String)info.getId());
				myGrpInfo.setGrpSeq(Integer.parseInt((String)obj.get("grpSeq")));
				myGrpInfo.setGrpName((String)obj.get("grpName"));
				
				myGrpInfo.setDelYn("N");
				myGrpInfo.setRegrId((String)info.getId());
				myGrpInfo.setRegrName(info.getName());
				myGrpInfo.setUpdrId((String)info.getId());
				myGrpInfo.setUpdrName(info.getName());
				
				
				logger.debug(myGrpInfo.getUserId());
				logger.debug(""+myGrpInfo.getGrpSeq());
				logger.debug(myGrpInfo.getGrpName());
				logger.debug(myGrpInfo.getRegrId());
				logger.debug(myGrpInfo.getRegrName());
				
				person100Mapper.insertPsnUserGrpInfo(myGrpInfo);
				
			}
			
			
			
			jsonArr = (JSONArray)memObject.get("PsnUserGrpFriendMap");		

			person100Mapper.deletePsnUserGrpFriendMap((String)info.getId());
			
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				PsnUserGrpFrdMapVO myGrpInfo = new PsnUserGrpFrdMapVO();
				
				myGrpInfo.setUserId((String)info.getId());
				myGrpInfo.setGrpSeq(Integer.parseInt((String)obj.get("grpSeq")));
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
				
				person100Mapper.insertPsnUserGrpFriendMap(myGrpInfo);
				
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
     
    
    /**
	 * 나의관심직원 정보 조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserFrdInfoVO> getPsnUserFriendInfo(PsnUserFrdInfoVO vo) throws Exception
    {
    	try{
	    	int frdCnt = person100Mapper.getPsnUserFriendInfoCnt(vo.getUserId());
	    	
	    	
	    	logger.debug("frdCnt : " + frdCnt);
	    	
	    	if( frdCnt == 0 ) return person100Mapper.getPsnUserOVInfo(vo);
	    	else return person100Mapper.getPsnUserFriendInfo(vo);
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
    		return person100Mapper.getPsnUserGrpInfo(vo);
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
    		return person100Mapper.getPsnUserGrpFriendMap(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 나의 부서 직원 조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserFrdInfoVO> getPsnUserOVInfo(PsnUserFrdInfoVO vo) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserOVInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    /**
	 * 내게 관심있는 직원 조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserFrdInfoVO> getPsnInterestedUser(PsnUserFrdInfoVO vo) throws Exception
    {
    	try{
    		return person100Mapper.getPsnInterestedUser(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 나의 직원 상세  조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public PsnUserFrdInfoVO getPsnUserDetailInfo(PsnUserFrdInfoVO vo) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserDetailInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    

    
    /**
	 * 나의관심직원 건수 조회
	 * @param 유저아이디
	 * @return 나의관심직원 건수
	 * @exception Exception
	 * @author crossent
	 */    
    public int getPsnUserFriendInfoCnt(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserFriendInfoCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 나의관심직원 건수 조회
	 * @param 유저아이디
	 * @return 나의관심직원 건수
	 * @exception Exception
	 * @author crossent
	 */    
    public int getPsnUserOVInfoCnt(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserOVInfoCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 나를 추가한 직원 건수
	 * @param 유저아이디
	 * @return 나의관심직원 건수
	 * @exception Exception
	 * @author crossent
	 */    
    public int getStaffFriendInfoCnt(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getStaffFriendInfoCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 전송할 수 있는 SMS 건수
	 * @param 유저아이디
	 * @return 나의관심직원 건수
	 * @exception Exception
	 * @author crossent
	 */    
    public String getMySmsCnt(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getMySmsCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    

	
	 /**
	 * 전송할 수 있는 총 SMS 건수
	 * @param 
	 * @return 
	 * @exception Exception
	 * @author crossent
	 */
	public String getMySmsAllCnt(String userId) throws Exception{
		try{
			return person100Mapper.getMySmsAllCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    
    /**
     * SMS 건수 수정
     * @param 유저 아이디  
     * @return 수정 성공
     * @author crossent
     */ 
    public int updateMySmsCnt(String userId) throws Exception
    {
    	try{
    		return person100Mapper.updateMySmsCnt(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 발송한 SMS 내용 저장
     * @param 발송한 sms내용  
     * @return 수정 성공
     * @author crossent
     */ 
    public String insertSmsSendHist(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject jsonSms = JSONObject.fromObject(json);
			SmsSendHistVO smsSendHist = new SmsSendHistVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			smsSendHist.setObjTelno((String)jsonSms.get("objTelno"));
			smsSendHist.setSendTelno((String)jsonSms.get("sendTelno"));
			smsSendHist.setSmsConts((String)jsonSms.get("smsConts"));
			smsSendHist.setDelYn((String)jsonSms.get("delYn"));
			smsSendHist.setUserId((String)info.getId());
			smsSendHist.setUserName((String)info.getName());
			smsSendHist.setRegrId((String)info.getId());
			smsSendHist.setRegrName((String)info.getName());
			smsSendHist.setUpdrId((String)info.getId());
			smsSendHist.setUpdrName((String)info.getName());	
			
			person100Mapper.insertSmsSendHist(smsSendHist);
			
			//따로 하는것으로 변경함
			//sms tran cnt -1 
			//person100Mapper.updateMySmsCnt((String)info.getId());
			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    public String insertSmsSendHist(SmsSendHistVO smsSendHist, HttpSession session) throws Exception {
    	
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			smsSendHist.setUserId((String)info.getId());
			smsSendHist.setUserName((String)info.getName());
			smsSendHist.setRegrId((String)info.getId());
			smsSendHist.setRegrName((String)info.getName());
			smsSendHist.setUpdrId((String)info.getId());
			smsSendHist.setUpdrName((String)info.getName());	
			
			person100Mapper.insertSmsSendHist(smsSendHist);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    
    
  
    
    /**
	 * 사용자공지사항게시판설정
	 * @param 유저아이디
	 * @return 추가항목 정보
	 * @exception Exception
	 * @author crossent
	 */    
    public List<PsnUserBoardSetVO> getPsnUserNotiList(String userId) throws Exception
    {
    	try{
	    	int NotiCnt = person100Mapper.getPsnUserBoardCnt(userId);
	    	
	    	
	    	logger.debug("NotiCnt : " + NotiCnt);
	    	
	    	if( NotiCnt == 0 ) return person100Mapper.getComStandBoardList(userId);
	    	else return person100Mapper.getPsnUserNotiList(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 사용자가 지정한 게시판 목록
	 * @param 
	 * @return  사용자가 지정한 게시판 목록
	 * @exception 
	 * @author crossent
	 */  
    public List<PsnUserCncrBoardSetVO> getPsnUserCncrSetBoardList(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserCncrSetBoardList(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    /**
	 * 사용자가 지정한 알림 목록
	 * @param 
	 * @return 사용자가 지정한 알림 목록
	 * @exception 
	 * @author crossent
	 */  
    public List<PsnUserTmlnAlertSetVO> getPsnUserTmlnAlertList(String userId) throws Exception
    {
    	try{
    		return person100Mapper.getPsnUserTmlnAlertList(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    
    /**
	 * 알림관리 컨텐츠 목록
	 * @param 
	 * @return  알림관리 컨텐츠 목록
	 * @exception 
	 * @author crossent
	 */  
    public List<ComCntsInfoVO> getComCntsInfoList() throws Exception
    {
    	try{
    		return person100Mapper.getComCntsInfoList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 *  지정가능한 공지 ,게시만 숫자
	 * @param 
	 * @return  지정가능한 공지 ,게시만판숫자
	 * @exception 
	 * @author crossent
	 */  
    public AdmTmlnEnvSetVO getAdmTmlnEnvSet() throws Exception
    {
    	try{
    		return person100Mapper.getAdmTmlnEnvSet();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
     * 사용자공지사항게시판설정
     * @param  공지사항게시판 정보
     * @return 저장 성공
     * @author crossent
     */
    public String insertPsnUserNotiSet(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject jsonBoard = JSONObject.fromObject(json);
		
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			
			JSONArray jsonArr = (JSONArray)jsonBoard.get("PsnUserNotiSet");
			
			int delret = person100Mapper.deletePsnUserNotiSet((String)info.getId());
			
			logger.debug("delret : " + delret);
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				PsnUserBoardSetVO notiSet = new PsnUserBoardSetVO();
			
				notiSet.setUserId((String)info.getId());
				
				notiSet.setBoardId((String)obj.get("boardId"));
				notiSet.setDelYn((String)obj.get("delYn"));
				notiSet.setUseYn((String)obj.get("useYn"));
				
				notiSet.setBoardUseDiv((String)obj.get("boardUseDiv"));
				notiSet.setBoardUrl("");
				
				notiSet.setRegrId((String)info.getId());
				notiSet.setRegrName((String)info.getName());
				
				notiSet.setUpdrId((String)info.getId());
				notiSet.setUpdrName((String)info.getName());
				 
				notiSet.setOldUse("");
				notiSet.setOldSeq("");
				
				logger.debug("boardId : " + (String)obj.get("boardId"));
				logger.debug("delYn : " + (String)obj.get("delYn"));
				logger.debug("boardUseDiv : " + (String)obj.get("boardUseDiv"));
				logger.debug("useYn : " + (String)obj.get("useYn"));
				
				logger.debug("getId : " + (String)info.getId());
				logger.debug("getName : " + (String)info.getName());
				
				
				person100Mapper.insertPsnUserNotiSet(notiSet);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    
    
    
    /**
     * 사용자 관심 게시판을 설정한다.
     * @param  관심게시판 정보
     * @return 저장 성공
     * @author crossent
     */
    public String insertPsnUserCncrBoardSet(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject jsonBoard = JSONObject.fromObject(json);
		
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			
			JSONArray jsonArr = (JSONArray)jsonBoard.get("PsnUserCncrBoard");
			
			int delret = person100Mapper.deletePsnUserCncrBoardSet((String)info.getId());
			
			logger.debug("delret : " + delret);
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PsnUserCncrBoardSetVO boardSet = new PsnUserCncrBoardSetVO();
			
				boardSet.setUserId((String)info.getId());
				
				boardSet.setBoardId((String)obj.get("boardId"));
				boardSet.setBoardDiv((String)obj.get("boardDiv"));
				boardSet.setDelYn((String)obj.get("delYn"));
				
				boardSet.setRegrId((String)info.getId());
				boardSet.setRegrName((String)info.getName());
				
				boardSet.setUpdrId((String)info.getId());
				boardSet.setUpdrName((String)info.getName());
			
				person100Mapper.insertPsnUserCncrBoardSet(boardSet);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    

    /**
     * 사용자 관심 게시판을 설정한다.
     * @param  관심게시판 정보
     * @return 저장 성공
     * @author crossent
     */
    public String insertPsnUserTmlnAlertSet(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject jsonBoard = JSONObject.fromObject(json);
		
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			
			JSONArray jsonArr = (JSONArray)jsonBoard.get("PsnUserTmlnAlert");
			
			int delret = person100Mapper.deletePsnUserTmlnAlertSet((String)info.getId());
			
			logger.debug("delret : " + delret);
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PsnUserTmlnAlertSetVO alertSet = new PsnUserTmlnAlertSetVO();
			
				alertSet.setUserId((String)info.getId());
				
				alertSet.setCntsId((String)obj.get("cntsId"));
				alertSet.setDelYn((String)obj.get("delYn"));
				
				alertSet.setRegrId((String)info.getId());
				alertSet.setRegrName((String)info.getName());
				
				alertSet.setUpdrId((String)info.getId());
				
				alertSet.setUpdrName((String)info.getName());
			
				person100Mapper.insertPsnUserTmlnAlertSet(alertSet);
			
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    
    /**
     * 사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */  
    public List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRoll(String potoSeq,
            HttpSession session) throws Exception {
    	
    	try{
	    	ComUserPotoInfoVO vo = new ComUserPotoInfoVO();
	    	logger.debug("getPsnUserPotoInfoListForRoll potoSeq : "+potoSeq);
	    	logger.debug("potoSeq : "+potoSeq);
	 
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	vo.setPotoSeq(Integer.parseInt(potoSeq));
	    	vo.setUserId(info.getId());
    	
	    	return person100Mapper.getPsnUserPotoInfoListForRoll(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 타사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */  
    public List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRollEtc(String userId, String potoSeq) throws Exception {
    	try{
	    	ComUserPotoInfoVO vo = new ComUserPotoInfoVO();
	    	logger.debug("getPsnUserPotoInfoListForRoll potoSeq : "+potoSeq);
	    	logger.debug("potoSeq : "+potoSeq);
	    	
	    	vo.setPotoSeq(Integer.parseInt(potoSeq));
	    	vo.setUserId(userId);
	    	
	    	return person100Mapper.getPsnUserPotoInfoListForRollEtc(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
     * 사용자부서정보 조회 - DB
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    public ComUserDeptInfoVO getComUserDeptInfoList(String userId) throws Exception{
    	try{
    		return person100Mapper.getComUserDeptInfoList(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 타부서정보 조회 - DB
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    public ComUserDeptInfoVO getComUserDeptDetailInfo(String oucode) throws Exception{
    	try{
    		return person100Mapper.getComUserDeptDetailInfo(oucode);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자부서정보 조회 - LDAP
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    public ComUserDeptInfoVO getComUserLdDeptInfoList(String oucode) throws Exception{
    	try{
	
	//		ComUserDeptInfoVO comUserLdDeptInfoVO = new ComUserDeptInfoVO();
	//    	String Dept_Modify_searchValue = new String();
	//	    LDAPControlMain pMain = new LDAPControlMain();
	//    	try {
	//    		
	//    		Dept_Modify_searchValue  = oucode;
	//
	//    		String [] Part_searchValue  = {Dept_Modify_searchValue};
	//    		String [] SearchFactor = {"ouCode"};
	//    		
	//    		String [] SearchFact1 = {"parentOUCode","virParentOuCode"};
	//    		String [] SearchVal1  = {Dept_Modify_searchValue,Dept_Modify_searchValue};
	//    		GovOrgValue[] part = pMain.searchOrgMainOne( SearchFactor, Part_searchValue, "AND", SearchFact1, SearchVal1, "OR");
	//
	//    		comUserLdDeptInfoVO.setOuAddress1(part[0].getOuAddress1());	//주소1
	//    		comUserLdDeptInfoVO.setOuAddress2(part[0].getOuAddress2());	//주소2
	//    		comUserLdDeptInfoVO.setTelephonenumber(part[0].getTelephoneNumber());	//대표전화
	//    		comUserLdDeptInfoVO.setOufax(part[0].getOuFax());	//팩스번호
	//    		comUserLdDeptInfoVO.setOuJobTitle(part[0].getOuJobTitle());	//부서업무
	//			
	//		} catch (Exception ex) {			
	//			throw processException("errors.test");
	//		}  	
			
	//    	return comUserLdDeptInfoVO;
	    	return null;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    	
    }
    
    /**
     * 직원검색에서 관심직원 추가 중복체크
     * @author crossent
     */
    public int favoriteUserDuplicationCheck(PsnUserFrdInfoVO pfVO) throws Exception {
    	try{
    		return person100Mapper.favoriteUserDuplicationCheck(pfVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
     * 직원검색에서 관심직원 추가
     * @author crossent
     */
    public String setFavoriteUserAdd(String json, UserInfoVO userVO) throws Exception {
    	
    	try {
			JSONObject memObject = JSONObject.fromObject(json);
			
			JSONArray jsonArr = (JSONArray)memObject.get("selectedUserList");		

			for (int i=0; i < jsonArr.size(); i++) {
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				PsnUserFrdInfoVO myMemberInfo = new PsnUserFrdInfoVO();
				
				myMemberInfo.setUserId(userVO.getId());
				myMemberInfo.setFriendId((String)obj.get("friendId"));
				myMemberInfo.setDelYn("N");
				myMemberInfo.setRegrId(userVO.getId());
				myMemberInfo.setRegrName(userVO.getName());
				myMemberInfo.setUpdrId(userVO.getId());
				myMemberInfo.setUpdrName(userVO.getName());

			
				person100Mapper.insertPsnUserFriendInfo(myMemberInfo);
				
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "OK";
    }
    
    /**
     * 부서홍보글 존재여부 조회
     * @author crossent
     */
    public int selectUserBuseoHongbo(String oucode) throws Exception{
    	try{
    		return person100Mapper.selectUserBuseoHongbo(oucode);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    } 
    /**
     * 부서홍보글 존재여부 조회
     * @author crossent
     */
    public int insertUserBuseoHongbo(String oucode, String content, HttpSession session) throws Exception{
    	try{
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	
	    	ComUserDeptInfoVO comUserDeptInfoVO = new ComUserDeptInfoVO();
	    	
	    	comUserDeptInfoVO.setOucode(oucode);
	    	comUserDeptInfoVO.setContent(content);
	    	comUserDeptInfoVO.setInsId(info.getId());
	    	comUserDeptInfoVO.setUpdId(info.getId());
	    	
	    	return person100Mapper.insertUserBuseoHongbo(comUserDeptInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }    
    /**
     * 부서홍보글 존재여부 조회
     * @author crossent
     */
    public int updateUserBuseoHongbo(String oucode, String content, HttpSession session) throws Exception{
    	try{
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	
	    	ComUserDeptInfoVO comUserDeptInfoVO = new ComUserDeptInfoVO();
	    	
	    	comUserDeptInfoVO.setOucode(oucode);
	    	comUserDeptInfoVO.setContent(content);
	    	comUserDeptInfoVO.setUpdId(info.getId());
	    	
	    	return person100Mapper.updateUserBuseoHongbo(comUserDeptInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

	/**
	 * 타임라인 환경설정 화면
	 * @param TmlnEnvInfoVO
	 * @return TmlnEnvInfoVO
	 * @exception Exception
	 */
	public TmlnEnvInfoVO getTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO) throws Exception {
		try{
			return person100Mapper.getTmlnEnv(tmlnEnvInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 타임라인 환경설정 수정
	 * @param TmlnEnvInfoVO
	 * @return TmlnEnvInfoVO
	 * @exception Exception
	 */
    public void updateTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	tmlnEnvInfoVO.setUpdrId((String)usrInfo.getId());
	    	tmlnEnvInfoVO.setUpdrName((String)usrInfo.getName());
	    	
	    	person100Mapper.updateTmlnEnv(tmlnEnvInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }    
    
}
