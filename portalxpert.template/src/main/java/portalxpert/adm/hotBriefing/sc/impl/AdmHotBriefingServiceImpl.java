package portalxpert.adm.hotBriefing.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;

import portalxpert.adm.hotBriefing.mapper.AdmHotBriefingMapper;
import portalxpert.adm.hotBriefing.sc.AdmHotBriefingService;
import portalxpert.adm.hotBriefing.vo.AdmHotBriefingVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admHotBriefingService")
public class AdmHotBriefingServiceImpl extends AbstractServiceImpl implements AdmHotBriefingService {
	
	/** AdmHotBriefingMapper */
    @Resource(name="admHotBriefingMapper")
    private AdmHotBriefingMapper admHotBriefingMapper;

    /**
	 * 핫브리핑목록조회
	 * @param AdmSysHotBriefingVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmHotBriefingVO> getAdmHotBriefingList(AdmHotBriefingVO admHotBriefingVO) throws Exception {
    	try{
    		return admHotBriefingMapper.getAdmHotBriefingList(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /** 
	 * 핫브리핑조회건수
	 * @param AdmSysHotBriefingVO
	 * @return int 
	 * @exception Exception
	 */
    public int getAdmHotBriefingListToCnt(AdmHotBriefingVO admHotBriefingVO) throws Exception 
    {
    	try{
    		return admHotBriefingMapper.getAdmHotBriefingListToCnt(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /** 
	 * 핫브리핑상세조회
	 * @param AdmSysHotBriefingVO
	 * @return AdmHotBriefingVO 
	 * @exception Exception
	 */
    public AdmHotBriefingVO getAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception 
    {
    	try{
    		return admHotBriefingMapper.getAdmHotBriefing(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 핫브리핑이미지조회
	 * @param AdmSysHotBriefingVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmHotBriefingVO> getAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception {
    	try{
    		return admHotBriefingMapper.getAdmHotBriefingAppendImg(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /** 
	 * 핫브리핑등록
	 * @param AdmSysHotBriefingVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setInsId((String)usrInfo.getId());
	    	
	    	admHotBriefingMapper.insertAdmHotBriefing(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
        
    }
    
    /** 
	 * 핫브리핑이미지등록
	 * @param AdmSysHotBriefingVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setInsId((String)usrInfo.getId());
	    	
	    	admHotBriefingMapper.insertAdmHotBriefingAppendImg(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
        
    }
    
    /** 
	 * 핫브리핑수정
	 * @param AdmSysHotBriefingVO
	 * @return void 
	 * @exception Exception
	 */
    public void updateAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
	    	admHotBriefingMapper.updateAdmHotBriefing(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * Method Desciption : 핫브리핑이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
			admHotBriefingMapper.updateAdmHotBriefingAppendImg(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * Method Desciption : 핫브리핑삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
			return admHotBriefingMapper.deleteAdmHotBriefing(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * Method Desciption : 핫브리핑이미지삭제(단건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
			return admHotBriefingMapper.deleteAdmHotBriefingAppendImg(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * Method Desciption : 핫브리핑삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefings(AdmHotBriefingVO admHotBriefingVO, HttpSession session)
			throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
			return admHotBriefingMapper.deleteAdmHotBriefings(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * Method Desciption : 핫브리핑이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImgs(AdmHotBriefingVO admHotBriefingVO, HttpSession session)
			throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admHotBriefingVO.setUpdId((String)usrInfo.getId());
	    	
			return admHotBriefingMapper.deleteAdmHotBriefingAppendImgs(admHotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 유효 아이디를 얻어온다.
	 * @return
	 * @throws Exception
	 */
	public String getAdmHotBriefingId() throws Exception{
		try{
			return admHotBriefingMapper.getAdmHotBriefingId();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 첨부 이미지 파일 정보 저장
	 * @param hotBriefingVO
	 * @throws Exception
	 */
	public int hotbriefingInnoApUpload(AdmHotBriefingVO hotBriefingVO) throws Exception{ 
		try{
			return admHotBriefingMapper.hotbriefingInnoApUpload(hotBriefingVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
}
