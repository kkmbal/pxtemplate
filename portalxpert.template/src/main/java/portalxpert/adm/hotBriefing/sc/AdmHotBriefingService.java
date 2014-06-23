package portalxpert.adm.hotBriefing.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.hotBriefing.vo.AdmHotBriefingVO;


public interface AdmHotBriefingService {
	
	
	/**
	 * 핫브리핑목록조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmHotBriefingVO> getAdmHotBriefingList(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	
	/**
	 * 핫브리핑 조회 건수
	 * @param AdmLinkUserInfoVO
	 * @return int
	 * @exception Exception
	 */
	public int getAdmHotBriefingListToCnt(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	
	/**
	 * 핫브리핑 상세 조회 
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public AdmHotBriefingVO getAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	
	
	/**
	 * 핫브리핑 이미지 조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmHotBriefingVO> getAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	
	 /**
	 * 핫브리핑 등록
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
    
    /** 
	 * 핫브리핑이미지등록
	 * @param AdmSysBannerVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
    
	 /**
	 * 핫브리핑수정
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
    
    /**
	 * Method Desciption : 핫브리핑이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑이미지삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefings(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImgs(AdmHotBriefingVO admHotBriefingVO, HttpSession session) throws Exception;


	/**
	 * 유효 아이디를 얻어온다.
	 * @return
	 * @throws Exception
	 */
	public String getAdmHotBriefingId() throws Exception;


	/**
	 * 첨부 이미지 파일 정보 저장
	 * @param hotBriefingVO
	 * @throws Exception
	 */
	public int hotbriefingInnoApUpload(AdmHotBriefingVO hotBriefingVO) throws Exception;
	
}
