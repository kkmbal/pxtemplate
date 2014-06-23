package portalxpert.adm.gen.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.gen.vo.AdmGenContentManageVO;

public interface AdmGenContentManageService {
    
	/**
	 * 콘텐츠조회 목록
	 * @param AdmGenContentManageVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenContentManageVO> getAdmGenContentManageList(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠조회 목록 Count
	 * @param AdmGenContentManageVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenContentManageCnt(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠단건 조회
	 * @param AdmGenContentManageVO
	 * @return AdmGenContentManageVO
	 * @exception Exception
	 */
    public AdmGenContentManageVO getAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠 등록
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO, HttpSession session) throws Exception;
    
    /**
	 * 콘텐츠 수정
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO, HttpSession session) throws Exception;
    
    /**
	 * 콘텐츠 삭제
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
}
 
