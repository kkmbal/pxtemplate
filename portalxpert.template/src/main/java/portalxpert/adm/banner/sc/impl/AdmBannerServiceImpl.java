package portalxpert.adm.banner.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import portalxpert.adm.banner.mapper.AdmBannerMapper;
import portalxpert.adm.banner.sc.AdmBannerService;
import portalxpert.adm.banner.vo.AdmBannerApndFileVO;
import portalxpert.adm.banner.vo.AdmBannerVO;
import portalxpert.common.config.Constant;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("admBannerService")
public class AdmBannerServiceImpl extends EgovAbstractServiceImpl implements AdmBannerService {
	
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
    public List<AdmBannerVO> getAdmBannerAppendImg(AdmBannerApndFileVO admBannerVO) throws Exception {
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
    public void insertAdmBanner(String data, HttpSession session) throws Exception{
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	AdmBannerVO admBannerVO = new AdmBannerVO();
	    	admBannerVO.setRegrId(usrInfo.getId());
	    	admBannerVO.setUpdrId(usrInfo.getId());
	    	
	    	JSONObject json = JSONObject.fromObject(data);
	    	admBannerVO.setBnrId(json.getString("bnrId"));
	    	admBannerVO.setBnrTitle(json.getString("bnrTitle"));
	    	admBannerVO.setParRowPos(json.getString("parRowPos"));
	    	admBannerVO.setRowPos(json.getString("rowPos"));
	    	admBannerVO.setExpoBgnDttm(json.getString("expoBgnDttm"));
	    	admBannerVO.setExpoEndDttm(json.getString("expoEndDttm"));
	    	admBannerVO.setLinkUrl(json.getString("linkUrl"));
	    	admBannerVO.setSortSeq(json.getString("sortSeq"));
	    	admBannerVO.setDelYn("N");
	    	
	    	if("".equals(admBannerVO.getBnrId())){
	    		admBannerMapper.insertAdmBanner(admBannerVO); //신규
	    	}else{
	    		admBannerMapper.updateAdmBanner(admBannerVO); //수정
	    	}
	    	
			//첨부파일 처리
			JSONArray jsonArr = (JSONArray)json.get("appendFileList");
			if(jsonArr.size() > 0){
				admBannerMapper.deleteAdmBannerAppendImg(admBannerVO);
			}
			for (int i=0; i < jsonArr.size(); i++){
				JSONObject obj = (JSONObject)jsonArr.get(i);
				AdmBannerApndFileVO apndVO = new AdmBannerApndFileVO();
				apndVO.setBnrId( admBannerVO.getBnrId()) ;
				apndVO.setApndFileSeq(obj.getInt("apndFileSeq")) ;
				apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
				apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
				apndVO.setApndFileName( (String)obj.get("apndFileName")) ;				
				apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;				
				apndVO.setDelYn( (String)obj.get("delYn")) ;
				apndVO.setRegrId( usrInfo.getId()) ;
				apndVO.setUpdrId( usrInfo.getId()) ;
				
				String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
				apndVO.setApndFilePath(realPath);

				admBannerMapper.insertAdmBannerAppendImg(apndVO);
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
        
    }
}
