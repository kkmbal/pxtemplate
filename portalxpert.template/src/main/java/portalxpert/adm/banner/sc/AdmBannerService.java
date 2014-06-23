package portalxpert.adm.banner.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.banner.vo.AdmBannerVO;


public interface AdmBannerService {
	
	
	/**
	 * 홍보배너목록조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmBannerVO> getAdmBannerList(AdmBannerVO admBannerVO) throws Exception;
	
	
	/**
	 * 홍보배너 조회 건수
	 * @param AdmLinkUserInfoVO
	 * @return int
	 * @exception Exception
	 */
	public int getAdmBannerListToCnt(AdmBannerVO admBannerVO) throws Exception;
	
	
	/**
	 * 홍보배너 상세 조회 
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public AdmBannerVO getAdmBanner(AdmBannerVO admBannerVO) throws Exception;
	
	
	
	/**
	 * 홍보배너 이미지 조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmBannerVO> getAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception;
	
	
	 /**
	 * 홍보배너 등록
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
    
    /** 
	 * 홍보배너이미지등록
	 * @param AdmSysBannerVO
	 * @return void 
	 * @exception Exception
	 */
    public void insertAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
    
	 /**
	 * 홍보배너수정
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
    
    /**
	 * Method Desciption : 홍보배너이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanner(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너이미지삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImg(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanners(AdmBannerVO admBannerVO, HttpSession session) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImgs(AdmBannerVO admBannerVO, HttpSession session) throws Exception;


	/**
	 *  배너 첨부이미지 정보 저장
	 * @param bannerFileVO
	 * @throws Exception
	 */
	public void bannerInnoApUpload(AdmBannerVO bannerFileVO) throws Exception;


	/**
	 * MAX bnrId get
	 * @return
	 * @throws Exception
	 */
	public String getAdmBannerId() throws Exception;
	
}
