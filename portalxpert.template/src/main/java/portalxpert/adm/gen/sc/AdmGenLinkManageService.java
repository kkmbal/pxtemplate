package portalxpert.adm.gen.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.gen.vo.AdmGenLinkVO;

public interface AdmGenLinkManageService {
    
	/**
	 * 링크분류조회 목록
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCtlgList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류조회 목록 Count
	 * @param AdmGenLinkVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenSysLinkCtlgCnt(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류조회 단건
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    /**
	 * 링크분류 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    /**
	 * 링크분류 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    
    
    
    
    /**
	 * 링크조회 목록
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크조회 목록 Count
	 * @param AdmGenLinkVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenSysLinkListCnt(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류목록 Comb
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCatList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 단건 조회
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    /**
	 * 링크 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    /**
	 * 링크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    
    
    
    
    /**
	 * 메인화면에 표시되는 링크 설정을 위한 조회
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenMainLinkList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 메인화면에 표시되는 링크 설정 (저장)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenMainLink(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
    
    /**
	 * 메인화면에 표시되는 링크 설정 (초기화)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenMainLinkInit(AdmGenLinkVO admGenLinkVO, HttpSession session) throws Exception;
}
 
