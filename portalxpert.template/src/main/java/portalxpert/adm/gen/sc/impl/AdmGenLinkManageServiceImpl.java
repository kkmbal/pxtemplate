package portalxpert.adm.gen.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.gen.mapper.AdmGenManageMapper;
import portalxpert.adm.gen.sc.AdmGenLinkManageService;
import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("admGenLinkManageService")
public class AdmGenLinkManageServiceImpl extends AbstractServiceImpl implements AdmGenLinkManageService {
	
	/** AdmGenLinkManageMapper */
    @Resource(name="admGenManageMapper")
    private AdmGenManageMapper admGenManageMapper;
    
    @Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;
	
	private final static Logger logger = LoggerFactory.getLogger(AdmGenLinkManageServiceImpl.class);
	 
    /**
	 * 링크분류조회 목록
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCtlgList(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkCtlgList(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크분류조회 목록 Count
	 * @param AdmGenLinkVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenSysLinkCtlgCnt(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkCtlgCnt(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크분류조회 단건
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkCtlg(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크분류 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void insertAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setInsMan((String)usrInfo.getId());
	    	//admGenLinkVO.setUpdrName((String)usrInfo.getName());
	    	
	    	admGenManageMapper.updateAdmGenSysLinkCtlgIOrder(admGenLinkVO);
	    	admGenManageMapper.insertAdmGenSysLinkCtlg(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크분류 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenSysLinkCtlg(admGenLinkVO);
	    	
	    	long linkCatOrderNew = admGenLinkVO.getLinkCatOrderNew();
	    	long linkCatOrder = admGenLinkVO.getLinkCatOrder();
	    	
	    	if(linkCatOrderNew != linkCatOrder){
	    		if(linkCatOrderNew < linkCatOrder){
	    			admGenManageMapper.updateAdmGenSysLinkCtlgUOrder1(admGenLinkVO);
	    		}else{
	    			admGenManageMapper.updateAdmGenSysLinkCtlgUOrder2(admGenLinkVO);
	    		}
	    	}
	    	
	    	admGenManageMapper.updateAdmGenSysLinkCtlgUOrder(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크분류 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void deleteAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	//링크분류삭제시 링크분류 순서 수정
	    	admGenManageMapper.updateAdmGenSysLinkCtlgDOrder(admGenLinkVO);
	    	//링크 삭제
	    	admGenManageMapper.deleteAdmGenSysLinkAll(admGenLinkVO);
	    	//링크분류 삭제
	    	admGenManageMapper.deleteAdmGenSysLinkCtlg(admGenLinkVO);
	    	//업무데스크 삭제
	    	admGenManageMapper.deleteAdmGenSysLinkCtlgPsnJobDesk(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    
    /**
	 * 링크조회 목록
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkList(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkList(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크조회 목록 Count
	 * @param AdmGenLinkVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenSysLinkListCnt(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkCnt(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크목록 Comb
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCatList(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
    		return admGenManageMapper.getAdmGenSysLinkCatList(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크 단 건 조회
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception {
        try{
        	return admGenManageMapper.getAdmGenSysLink(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void insertAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setInsMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenSysLinkIOrder(admGenLinkVO);
	    	admGenManageMapper.insertAdmGenSysLink(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenSysLink(admGenLinkVO);
	    	
	    	long linkOrderNew = admGenLinkVO.getLinkOrderNew();
	    	long linkOrder = admGenLinkVO.getLinkOrder();
	    	
	    	if(linkOrderNew != linkOrder){
	    		if(linkOrderNew < linkOrder){
	    			admGenManageMapper.updateAdmGenSysLinkUOrder1(admGenLinkVO);
	    		}else{
	    			admGenManageMapper.updateAdmGenSysLinkUOrder2(admGenLinkVO);
	    		}
	    	}
	    	
	    	admGenManageMapper.updateAdmGenSysLinkUOrder(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 링크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void deleteAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenSysLinkDOrder(admGenLinkVO);
	    	admGenManageMapper.deleteAdmGenSysLink(admGenLinkVO);
	    	admGenManageMapper.deleteAdmGenSysLinkPsnJobDesk(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
    
    
    
    /**
	 * 메인화면에 표시되는 링크 설정을 위한 조회
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenMainLinkList(AdmGenLinkVO admGenLinkVO) throws Exception {
    	try{
        	return admGenManageMapper.getAdmGenMainLinkList(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 메인화면에 표시되는 링크 설정 (저장)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmGenMainLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenMainLinkAll(admGenLinkVO);
	    	
	    	String[] linkCdAryTemp = admGenLinkVO.getLinkCd().split(",");
	    	
	    	String linkCatId = "";
	    	String linkCd = "";
	    	AdmGenLinkVO admGenLinkVO_new = null;
	    	
	    	for(String linkCatCd : linkCdAryTemp){
	    		
	    		linkCatId = linkCatCd.substring(0, 3);
	    		linkCd = linkCatCd.substring(3, 6);
	    		
	    		admGenLinkVO_new = new AdmGenLinkVO();
	    		admGenLinkVO_new.setUpdMan(admGenLinkVO.getUpdMan());
	    		admGenLinkVO_new.setLinkCatId(linkCatId);
	    		admGenLinkVO_new.setLinkCd(linkCd);
	    		
	    		admGenManageMapper.updateAdmGenMainLink(admGenLinkVO_new);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 메인화면에 표시되는 링크 설정 (초기화)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenMainLinkInit(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenLinkVO.setUpdMan((String)usrInfo.getId());
	    	
	    	admGenManageMapper.updateAdmGenMainLinkAll(admGenLinkVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
}
