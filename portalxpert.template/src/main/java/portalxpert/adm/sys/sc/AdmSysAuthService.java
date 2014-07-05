package portalxpert.adm.sys.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.sys.vo.AdmSysAuthVO;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysUserAuthVO;


public interface AdmSysAuthService {
 
    
    /**
     *  권한목록
     * @param AdmSysUserAuthVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public List<AdmSysAuthVO> getAdmSysAuthList(AdmSysAuthVO admSysAuthVO) throws Exception;    
    
    /**
     *  권한목록 총수
     * @param AdmSysUserAuthVO
     * @return int
     * @exception Exception
     */
    public int getAdmSysAuthListCnt(AdmSysAuthVO admSysAuthVO) throws Exception;    
    
    /**
     *  권한정보
     * @param AdmSysUserAuthVO
     * @return AdmSysPsnUserInfoVO
     * @exception Exception
     */
    public AdmSysAuthVO getAdmSysAuthInfo(AdmSysAuthVO admSysAuthVO) throws Exception;    
    
    /**
     * 권한등록
     * @param AdmSysUserAuthVO
     * @return void
     * @exception Exception
     */
    public void insertAuth(AdmSysAuthVO admSysAuthVO, HttpSession session) throws Exception;    
       
    
    /**
     * 사용자 권한등록
     * @param AdmSysUserAuthVO
     * @return void
     * @exception Exception
     */
    public void insertUserAuth(AdmSysUserAuthVO admSysUserAuthVO, HttpSession session) throws Exception;    
    
    /**
     * 사용자 권한수정
     * @param AdmSysUserAuthVO
     * @return void
     * @exception Exception
     */
    public void updateUserAuth(AdmSysUserAuthVO admSysUserAuthVO, HttpSession session) throws Exception;    
    
    /**
     * 메뉴 권한정보
     * @param AdmSysMenuAuthVO
     * @return AdmSysMenuAuthVO
     * @exception Exception
     */
    public AdmSysMenuAuthVO getAdmSysMenuAuthInfo(AdmSysMenuAuthVO admSysMenuAuthVO) throws Exception;    
    
    /**
     * 메뉴 권한등록
     * @param AdmSysMenuAuthVO
     * @return void
     * @exception Exception
     */
    public void insertMenuAuth(AdmSysMenuAuthVO admSysMenuAuthVO, HttpSession session) throws Exception;    
    
    /**
     * 메뉴 권한수정
     * @param AdmSysMenuAuthVO
     * @return void
     * @exception Exception
     */
    public void updateMenuAuth(AdmSysMenuAuthVO admSysMenuAuthVO, HttpSession session) throws Exception;

    /**
     * 권한코드 전체 목록
     * @param AdmSysAuthVO
     * @return void
     * @exception Exception
     */
	public List<AdmSysAuthVO> getAuchCodeList(AdmSysAuthVO admSysAuthVO) throws Exception; 
    
 	

}
