package portalxpert.adm.sys.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;


public interface AdmSysSmsQtyService {
 
	/**
	 * SMS쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysPsnUserInfoVO> getAdmSysSmsQtyList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * SMS쿼터관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysSmsQtyCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * SMS쿼터관리 쿼터 수정
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysSmsQty(AdmSysPsnUserInfoVO admSysPsnUserInfoVO, HttpSession session) throws Exception;
	
}
