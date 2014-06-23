package portalxpert.adm.banner.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;

import portalxpert.adm.banner.mapper.AdmBannerMapper;
import portalxpert.adm.banner.sc.AdmBannerService;
import portalxpert.adm.banner.vo.AdmBannerVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admBannerService")
public class AdmBannerServiceImpl extends AbstractServiceImpl implements AdmBannerService {
	
	/** AdmBannerMapper */
    @Resource(name="admBannerMapper")
    private AdmBannerMapper admBannerMapper;

    /**
	 * 홍보배너목록조회
	 * @param AdmSysBannerVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmBannerVO> getAdmBannerList(AdmBannerVO admBannerVO) throws Exception {
    	try{
    		return admBannerMapper.getAdmBannerList(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    	
    	
    }
    
    /** 
	 * 홍보배너조회건수
	 * @param AdmSysBannerVO
	 * @return int 
	 * @exception Exception
	 */
    public int getAdmBannerListToCnt(AdmBannerVO admBannerVO) throws Exception 
    {
    	try{
    		return admBannerMapper.getAdmBannerListToCnt(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    /** 
	 * 홍보배너상세조회
	 * @param AdmSysBannerVO
	 * @return AdmBannerVO 
	 * @exception Exception
	 */
    public AdmBannerVO getAdmBanner(AdmBannerVO admBannerVO) throws Exception 
    {
    	try{
    		return admBannerMapper.getAdmBanner(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    
    /**
	 * 홍보배너이미지조회
	 * @param AdmSysBannerVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmBannerVO> getAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception {
    	try{
    		return admBannerMapper.getAdmBannerAppendImg(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    
    /** 
	 * 홍보배너등록
	 * @param AdmSysBannerVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setInsId((String)usrInfo.getId());
	    	
	    	admBannerMapper.insertAdmBanner(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
        
    }
    
    /** 
	 * 홍보배너이미지등록
	 * @param AdmSysBannerVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setInsId((String)usrInfo.getId());
	    	
	    	admBannerMapper.insertAdmBannerAppendImg(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
        
    }
    
	/**
	 *  배너 첨부이미지 정보 저장
	 * @param bannerFileVO
	 * @throws Exception
	 */
	public void bannerInnoApUpload(AdmBannerVO bannerFileVO) throws Exception{
		try{
			admBannerMapper.bannerInnoApUpload(bannerFileVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    /** 
	 * 홍보배너수정
	 * @param AdmSysBannerVO
	 * @return void 
	 * @exception Exception
	 */
    public void updateAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception 
    {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
	    	admBannerMapper.updateAdmBanner(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    /**
	 * Method Desciption : 홍보배너이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
			admBannerMapper.updateAdmBannerAppendImg(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
	}
	
	/**
	 * Method Desciption : 홍보배너삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
			return admBannerMapper.deleteAdmBanner(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
	}
	
	/**
	 * Method Desciption : 홍보배너이미지삭제(단건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception
	{
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
			return admBannerMapper.deleteAdmBannerAppendImg(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
	}

	/**
	 * Method Desciption : 홍보배너삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanners(AdmBannerVO admBannerVO, HttpSession session)
			throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
			return admBannerMapper.deleteAdmBanners(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
	}

	/**
	 * Method Desciption : 홍보배너이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImgs(AdmBannerVO admBannerVO, HttpSession session)
			throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBannerVO.setUpdId((String)usrInfo.getId());
	    	
			return admBannerMapper.deleteAdmBannerAppendImgs(admBannerVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
	}
	
	/**
	 * MAX bnrId get
	 * @return
	 * @throws Exception
	 */
	public String getAdmBannerId() throws Exception{
		try{
			return admBannerMapper.getAdmBannerId();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  		
	}
}
