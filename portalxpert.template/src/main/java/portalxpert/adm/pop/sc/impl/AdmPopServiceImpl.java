package portalxpert.adm.pop.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.omg.CORBA.portable.ApplicationException;
import org.springframework.stereotype.Service;

import portalxpert.adm.pop.mapper.AdmPopMapper;
import portalxpert.adm.pop.sc.AdmPopService;
import portalxpert.adm.pop.vo.AdmPopApndFileVO;
import portalxpert.adm.pop.vo.AdmPopUserMap;
import portalxpert.adm.pop.vo.AdmPopVO;
import portalxpert.common.config.Constant;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("admPopService")
public class AdmPopServiceImpl extends EgovAbstractServiceImpl implements AdmPopService  {
	
	/** AdmPopMapper */
    @Resource(name="admPopMapper")
    private AdmPopMapper admPopMapper;

    /**
	 * 팝업목록조회
	 * @param AdmSysPopVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmPopVO> getAdmPopList(AdmPopVO admPopVO) throws Exception {
    	try{
    		return admPopMapper.getAdmPopList(admPopVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    	
    	
    }
    
    /** 
	 * 팝업조회건수
	 * @param AdmSysPopVO
	 * @return int 
	 * @exception Exception
	 */
    public int getAdmPopListToCnt(AdmPopVO admPopVO) throws Exception 
    {
    	try{
    		return admPopMapper.getAdmPopListToCnt(admPopVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    /** 
	 * 팝업상세조회
	 * @param AdmSysPopVO
	 * @return AdmPopVO 
	 * @exception Exception
	 */
    public AdmPopVO getAdmPop(AdmPopVO admPopVO) throws Exception 
    {
    	try{
    		return admPopMapper.getAdmPop(admPopVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    
    /**
	 * 팝업이미지조회
	 * @param AdmSysPopVO
	 * @return List 
	 * @exception Exception
	 */
    public List<AdmPopVO> getAdmPopAppendImg(AdmPopApndFileVO admPopVO) throws Exception {
    	try{
    		return admPopMapper.getAdmPopAppendImg(admPopVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
    }
    
    /** 
	 * 팝업등록
	 * @param AdmSysPopVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmPop(String data, HttpSession session) throws Exception{
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	AdmPopVO admPopVO = new AdmPopVO();
	    	admPopVO.setRegrId(usrInfo.getId());
	    	admPopVO.setUpdrId(usrInfo.getId());
	    	
	    	JSONObject json = JSONObject.fromObject(data);
	    	admPopVO.setPopId(json.getString("popId"));
	    	admPopVO.setPopTitle(json.getString("popTitle"));
	    	admPopVO.setParRowPos(json.getString("parRowPos"));
	    	admPopVO.setRowPos(json.getString("rowPos"));
	    	admPopVO.setExpoBgnDttm(json.getString("expoBgnDttm"));
	    	admPopVO.setExpoEndDttm(json.getString("expoEndDttm"));
	    	admPopVO.setPopKind(json.getString("popKind"));
	    	admPopVO.setExpoYn(json.getString("expoYn"));
	    	admPopVO.setLinkUrl(json.getString("linkUrl"));
	    	admPopVO.setLinkKind(json.getString("linkKind"));
	    	admPopVO.setPopConts(json.getString("popConts"));
	    	admPopVO.setDelYn("N");
	    	
	    	if("".equals(admPopVO.getPopId())){
	    		admPopMapper.insertAdmPop(admPopVO); //신규
	    	}else{
	    		admPopMapper.updateAdmPop(admPopVO); //수정
	    	}
	    	
	    	admPopMapper.deleteAdmPopUserMap(admPopVO); //공개대상
	    	
			int totPsnCnt = 0;
			int totDeptCnt = 0;
			
			JSONArray jsonArr = (JSONArray)json.get("popOpenDivDeptList");  //부서지정

			for(int i=0; i < jsonArr.size(); i++){
				JSONObject obj = (JSONObject)jsonArr.get(i);
				AdmPopUserMap vo = new AdmPopUserMap();				
				vo.setPopId(admPopVO.getPopId());
				vo.setUserDiv((String)obj.get("div"));
				vo.setUserId((String)obj.get("id"));
				vo.setDelYn("N");
				vo.setRegrId(usrInfo.getId());
				vo.setUpdrId(usrInfo.getId());
				
				if (vo.getUserId().equals(usrInfo.getOucode())|| vo.getUserDiv().equals("PUB") ){
					totDeptCnt++;
					if (vo.getUserDiv().equals("PUB"))  //전체공개면 부서,개인정보 INSERT X
					{
						totPsnCnt++;
					}
				}
				
				admPopMapper.insertAdmPopUserMap(vo);
			}
			
			jsonArr = (JSONArray)json.get("popOpenDivEmpList");  //개인지정				
			for(int i=0; i < jsonArr.size(); i++){
				JSONObject obj = (JSONObject)jsonArr.get(i);
				AdmPopUserMap vo = new AdmPopUserMap();				
				vo.setPopId(admPopVO.getPopId());
				vo.setUserDiv((String)obj.get("div"));
				vo.setUserId((String)obj.get("id"));
				vo.setDelYn("N");
				vo.setRegrId(usrInfo.getId());
				vo.setUpdrId(usrInfo.getId());
				
				if (vo.getUserId().equals(usrInfo.getId())){
					totPsnCnt++;
				}
				admPopMapper.insertAdmPopUserMap(vo);
				
			}
			//입력한 권한 정보가 없으면 작성자 정보를 입력한다.
			if (totPsnCnt == 0){
				
				AdmPopUserMap vo = new AdmPopUserMap();	
				vo.setPopId(admPopVO.getPopId());
				vo.setUserDiv("EMP");
				vo.setUserId(usrInfo.getId());
				vo.setDelYn("N");
				vo.setRegrId(usrInfo.getId());
				vo.setUpdrId(usrInfo.getId());
				
				admPopMapper.insertAdmPopUserMap(vo);
				
			}	    	
	    	
			//첨부파일 처리
			jsonArr = (JSONArray)json.get("appendFileList");
			if(jsonArr.size() > 0){
				admPopMapper.deleteAdmPopAppendImg(admPopVO);
			}
			for (int i=0; i < jsonArr.size(); i++){
				JSONObject obj = (JSONObject)jsonArr.get(i);
				AdmPopApndFileVO apndVO = new AdmPopApndFileVO();
				apndVO.setPopId( admPopVO.getPopId()) ;
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

				admPopMapper.insertAdmPopAppendImg(apndVO);
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}  
        
    }
    
	/**
	 * Method Desciption : 팝업공개대상조회
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmPopUserMap> getAdmPopUserMapList(AdmPopVO admPopVO) throws Exception {
    	try{
    		return admPopMapper.getAdmPopUserMapList(admPopVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    	
    	
    }
	
}
