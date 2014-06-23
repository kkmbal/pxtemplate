package portalxpert.adm.sys.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.sys.mapper.AdmSysMapper;
import portalxpert.adm.sys.sc.AdmSysPsnQtyService;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;


@Service("admSysPsnQtyService")
public class AdmSysPnsQtyServiceImpl  extends AbstractServiceImpl implements AdmSysPsnQtyService
{
	
	/** AdmSysPsnQtyMapper */
    @Resource(name="admSysMapper")
    private AdmSysMapper admSysMapper;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    private final static Logger logger = LoggerFactory.getLogger(AdmSysPnsQtyServiceImpl.class);
    
    /**
	 * 개인쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysPsnUserInfoVO> getAdmSysPsnQtyList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysPsnQtyList(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인쿼터관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysPsnQtyCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception {
        try{
        	return admSysMapper.getAdmSysPsnQtyCnt(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인쿼터관리 쿼터 수정
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysPsnQty(AdmSysPsnUserInfoVO admSysPsnUserInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysPsnUserInfoVO.setUpdrId((String)usrInfo.getId());
	    	admSysPsnUserInfoVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.updateAdmSysPsnQty(admSysPsnUserInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
}
