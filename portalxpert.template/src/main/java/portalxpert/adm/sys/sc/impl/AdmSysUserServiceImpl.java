package portalxpert.adm.sys.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.adm.sys.mapper.AdmSysMapper;
import portalxpert.adm.sys.sc.AdmSysUserService;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.adm.sys.vo.AdmSysUserAuthVO;
import portalxpert.common.config.Constant;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admSysUserService")
public class AdmSysUserServiceImpl extends AbstractServiceImpl implements AdmSysUserService {
	
    @Resource(name="admSysMapper")
    private AdmSysMapper admSysMapper;
    
	
	private final static Logger logger = LoggerFactory.getLogger(AdmSysUserServiceImpl.class);
	 
    /**
     * 사용자목록
     * @param AdmSysPsnUserInfoVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public List<AdmSysPsnUserInfoVO> getAdmSysUserInfoList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserInfoList(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자목록 총수
     * @param AdmSysPsnUserInfoVO
     * @return int
     * @exception Exception
     */
    public int getAdmSysUserInfoListCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserInfoListCnt(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자정보
     * @param AdmSysPsnUserInfoVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public AdmSysPsnUserInfoVO getAdmSysUserInfo(AdmSysPsnUserInfoVO admSysPsnUserInfoVO)  throws Exception {
    	try{
    		return admSysMapper.getAdmSysUserInfo(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    

    
    /**
     * 사용자등록
     * @param AdmSysPsnUserInfoVO
     * @return void
     * @exception Exception
     */
    public void insertPsnUserInfo(AdmSysPsnUserInfoVO admSysPsnUserInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysPsnUserInfoVO.setRegrId(usrInfo.getId());
	    	admSysPsnUserInfoVO.setRegrName(usrInfo.getName());
	    	admSysPsnUserInfoVO.setUpdrId(usrInfo.getId());
	    	admSysPsnUserInfoVO.setUpdrName(usrInfo.getName());
	    	admSysPsnUserInfoVO.setDelYn("N");
	    	
			AdmSysPsnUserInfoVO admSysUserInfo = admSysMapper.getAdmSysUserInfo(admSysPsnUserInfoVO);
	    	
	    	if(admSysUserInfo == null){
	    		admSysMapper.insertPsnUserInfo(admSysPsnUserInfoVO);
	    	}else{
	    		admSysMapper.updatePsnUserInfo(admSysPsnUserInfoVO);
	    	}
	    	
	    	//권한
	    	AdmSysUserAuthVO admSysUserAuthVO = new AdmSysUserAuthVO();
	    	admSysUserAuthVO.setUserId(admSysPsnUserInfoVO.getUserId());
	    	admSysUserAuthVO.setRegrId(usrInfo.getId());
	    	admSysUserAuthVO.setRegrName(usrInfo.getName());
	    	admSysUserAuthVO.setUpdrId(usrInfo.getId());
	    	admSysUserAuthVO.setUpdrName(usrInfo.getName());
	    	admSysUserAuthVO.setAuthCd(admSysPsnUserInfoVO.getAuthCd());
	    	
	    	AdmSysUserAuthVO admSysUserAuthInfo = admSysMapper.getAdmSysUserAuthInfo(admSysUserAuthVO);
	    	if(admSysUserAuthInfo == null){
	    		admSysUserAuthVO.setDelYn("N");
	    		admSysMapper.insertUserAuth(admSysUserAuthVO);
	    	}else{
	    		admSysMapper.updateUserAuth(admSysUserAuthVO);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자수정
     * @param AdmSysPsnUserInfoVO
     * @return void
     * @exception Exception
     */
    public void updatePsnUserInfo(AdmSysPsnUserInfoVO admSysPsnUserInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysPsnUserInfoVO.setUpdrId((String)usrInfo.getId());
	    	admSysPsnUserInfoVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.updatePsnUserInfo(admSysPsnUserInfoVO);
	    	
	    	//권한
	    	AdmSysUserAuthVO admSysUserAuthVO = new AdmSysUserAuthVO();
	    	admSysUserAuthVO.setUserId(admSysPsnUserInfoVO.getUserId());
	    	admSysUserAuthVO.setRegrId(usrInfo.getId());
	    	admSysUserAuthVO.setRegrName(usrInfo.getName());
	    	admSysUserAuthVO.setUpdrId(usrInfo.getId());
	    	admSysUserAuthVO.setUpdrName(usrInfo.getName());
	    	admSysUserAuthVO.setAuthCd(admSysPsnUserInfoVO.getAuthCd());
	    	
	    	AdmSysUserAuthVO admSysUserAuthInfo = admSysMapper.getAdmSysUserAuthInfo(admSysUserAuthVO);
	    	if(admSysUserAuthInfo == null){
	    		admSysUserAuthVO.setDelYn("N");
	    		admSysMapper.insertUserAuth(admSysUserAuthVO);
	    	}else{
	    		admSysMapper.updateUserAuth(admSysUserAuthVO);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 부서목록
     * @param AdmSysPsnUserInfoVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public List<AdmSysPsnUserInfoVO> getUserDeptInfoList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO)  throws Exception {
    	try{
    		return admSysMapper.getUserDeptInfoList(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

}
