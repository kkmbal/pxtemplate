package portalxpert.adm.sys.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.adm.sys.mapper.AdmSysMapper;
import portalxpert.adm.sys.sc.AdmSysTagCloudService;
import portalxpert.adm.sys.vo.AdmSysTagCloudInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admSysTagCloudService")
public class AdmSysTagCloudServiceImpl extends AbstractServiceImpl implements AdmSysTagCloudService {
	
	/** AdmSysTagCloudMapper */
    @Resource(name="admSysMapper")
    private AdmSysMapper admSysMapper;
    
    @Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;
	
	private final static Logger logger = LoggerFactory.getLogger(AdmSysTagCloudServiceImpl.class);
	 
    /**
	 * 태그클라우드관리 목록
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysTagCloudInfoVO> getAdmSysTagCloudList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysTagCloudList(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 목록 Count
	 * @param AdmSysTagCloudInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysTagCloudCnt(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysTagCloudCnt(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 단건
	 * @param AdmSysTagCloudInfoVO
	 * @return AdmSysTagCloudInfoVO
	 * @exception Exception
	 */
    public AdmSysTagCloudInfoVO getAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysTagCloud(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 링크카테고리리스트 combo
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkCtlgList() throws Exception {
    	try{
    		return admSysMapper.getAdmSysTagCloudLinkCtlgList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 링크리스트 combo
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception {
        try{
        	return admSysMapper.getAdmSysTagCloudLinkList(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 등록
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void insertAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysTagCloudInfoVO.setRegrId((String)usrInfo.getId());
	    	admSysTagCloudInfoVO.setRegrName((String)usrInfo.getName());
	    	
	    	admSysMapper.insertAdmSysTagCloud(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 수정
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysTagCloudInfoVO.setUpdrId((String)usrInfo.getId());
	    	admSysTagCloudInfoVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.updateAdmSysTagCloud(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 태그클라우드관리 삭제
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void deleteAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admSysTagCloudInfoVO.setUpdrId((String)usrInfo.getId());
	    	admSysTagCloudInfoVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admSysMapper.deleteAdmSysTagCloud(admSysTagCloudInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
}
