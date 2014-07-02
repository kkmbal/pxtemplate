package portalxpert.adm.sys.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.adm.sys.mapper.AdmSysMapper;
import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysUserAuthVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admSysAuthService")
public class AdmSysAuthServiceImpl extends AbstractServiceImpl implements AdmSysAuthService {
	
    @Resource(name="admSysMapper")
    private AdmSysMapper admSysMapper;
    
	
	private final static Logger logger = LoggerFactory.getLogger(AdmSysAuthServiceImpl.class);
	 
    /**
     * 사용자 권한목록
     * @param AdmSysUserAuthVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public List<AdmSysUserAuthVO> getAdmSysUserAuthList(AdmSysUserAuthVO admSysUserAuthVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserAuthList(admSysUserAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자 권한목록 총수
     * @param AdmSysUserAuthVO
     * @return int
     * @exception Exception
     */
    public int getAdmSysUserAuthListCnt(AdmSysUserAuthVO admSysUserAuthVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserAuthListCnt(admSysUserAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자 권한정보
     * @param AdmSysUserAuthVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public AdmSysUserAuthVO getAdmSysUserAuthInfo(AdmSysUserAuthVO admSysUserAuthVO)  throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserAuthInfo(admSysUserAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    

    
    /**
     * 사용자 권한등록
     * @param AdmSysUserAuthVO
     * @return void
     * @exception Exception
     */
    public void insertUserAuth(AdmSysUserAuthVO admSysUserAuthVO, HttpSession session)throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysUserAuthVO.setRegrId((String)usrInfo.getId());
	    	admSysUserAuthVO.setRegrName((String)usrInfo.getName());
	    	
	    	admSysMapper.insertUserAuth(admSysUserAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자 권한수정
     * @param AdmSysUserAuthVO
     * @return void
     * @exception Exception
     */
    public void updateUserAuth(AdmSysUserAuthVO admSysUserAuthVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysUserAuthVO.setUpdrId((String)usrInfo.getId());
	    	admSysUserAuthVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.updateUserAuth(admSysUserAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 메뉴 권한정보
     * @param AdmSysMenuAuthVO
     * @return AdmSysMenuAuthVO
     * @exception Exception
     */
    public AdmSysMenuAuthVO getAdmSysMenuAuthInfo(AdmSysMenuAuthVO admSysMenuAuthVO) throws Exception{
    	try{
    		return admSysMapper.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}   
    }
    
    /**
     * 메뉴 권한등록
     * @param AdmSysMenuAuthVO
     * @return void
     * @exception Exception
     */
    public void insertMenuAuth(AdmSysMenuAuthVO admSysMenuAuthVO, HttpSession session) throws Exception{
    	try{
    		//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysMenuAuthVO.setRegrId((String)usrInfo.getId());
	    	admSysMenuAuthVO.setRegrName((String)usrInfo.getName());
	    	
	    	admSysMapper.insertMenuAuth(admSysMenuAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}   
    }    
    
    /**
     * 메뉴 권한수정
     * @param AdmSysMenuAuthVO
     * @return void
     * @exception Exception
     */
    public void updateMenuAuth(AdmSysMenuAuthVO admSysMenuAuthVO, HttpSession session) throws Exception{
    	try{
    		//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysMenuAuthVO.setUpdrId((String)usrInfo.getId());
	    	admSysMenuAuthVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.updateMenuAuth(admSysMenuAuthVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}   
    } 
    
 	

}
