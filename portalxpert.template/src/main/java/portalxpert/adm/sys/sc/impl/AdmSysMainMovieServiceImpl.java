package portalxpert.adm.sys.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.sys.mapper.AdmSysMapper;
import portalxpert.adm.sys.sc.AdmSysMainMovieService;
import portalxpert.adm.sys.vo.AdmSysBbsNotiApndFileVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;


@Service("admSysMainMovieService")
public class AdmSysMainMovieServiceImpl  extends EgovAbstractServiceImpl implements AdmSysMainMovieService
{
	
	/** AdmSysMainMovieMapper */
    @Resource(name="admSysMapper")
    private AdmSysMapper admSysMapper;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    private final static Logger logger = LoggerFactory.getLogger(AdmSysMainMovieServiceImpl.class);
    
    /**
	 * 동영상관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysBbsNotiApndFileVO> getAdmSysMainMovieList(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysMainMovieList(admSysBbsNotiApndFileVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 동영상관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysMainMovieCnt(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception {
    	try{
    		return admSysMapper.getAdmSysMainMovieCnt(admSysBbsNotiApndFileVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 동영상관리 수정(동영상 추천/추천해지)
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysMainMovie(HttpServletRequest request, HttpSession session) throws Exception {
    	try{
	    	AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO = null;
	    	
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	
	    	String adminRcmdYnVal = request.getParameter("adminRcmdYn"); 
	    	String[] apndFileSeqAry = request.getParameterValues("apndFileSeq");
	    	String[] notiIdAry = request.getParameterValues("notiId");
	    	
			for(int i=1 ; i < apndFileSeqAry.length ; i++){
		    	
	    		admSysBbsNotiApndFileVO = new AdmSysBbsNotiApndFileVO();
	    		
	    		admSysBbsNotiApndFileVO.setUpdrId((String)usrInfo.getId());
	    		admSysBbsNotiApndFileVO.setUpdrName((String)usrInfo.getName());
	    		admSysBbsNotiApndFileVO.setAdminRcmdYn(adminRcmdYnVal);
	    		admSysBbsNotiApndFileVO.setApndFileSeq(new Long(apndFileSeqAry[i]));
	    		admSysBbsNotiApndFileVO.setNotiId(notiIdAry[i]);
	    		
	    		admSysMapper.updateAdmSysMainMovie(admSysBbsNotiApndFileVO);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
}
