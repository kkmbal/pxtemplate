package portalxpert.adm.sys.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;


public interface AdmSysPsnQtyService {
 
	/**
	 * 개인쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysPsnUserInfoVO> getAdmSysPsnQtyList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * 개인쿼터관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysPsnQtyCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * 개인쿼터관리 쿼터 수정
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysPsnQty(AdmSysPsnUserInfoVO admSysPsnUserInfoVO, HttpSession session) throws Exception;
	
}
