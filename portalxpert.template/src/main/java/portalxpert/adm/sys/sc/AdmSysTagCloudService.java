package portalxpert.adm.sys.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.adm.sys.vo.AdmSysTagCloudInfoVO;


public interface AdmSysTagCloudService {
 
	/**
	 * 태그클라우드관리 목록
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysTagCloudInfoVO> getAdmSysTagCloudList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 목록 Count
	 * @param AdmSysTagCloudInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysTagCloudCnt(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 단건
	 * @param AdmSysTagCloudInfoVO
	 * @return AdmSysTagCloudInfoVO
	 * @exception Exception
	 */
    public AdmSysTagCloudInfoVO getAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 링크카테고리리스트 combo
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkCtlgList() throws Exception;
    
    /**
	 * 태그클라우드관리 링크리스트 combo
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 등록
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void insertAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception;
    
    /**
	 * 태그클라우드관리 수정
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception;
    
    /**
	 * 태그클라우드관리 삭제
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void deleteAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO, HttpSession session) throws Exception;
	
}
