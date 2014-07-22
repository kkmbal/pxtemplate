package portalxpert.adm.banner.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.banner.vo.AdmBannerApndFileVO;
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
	public List<AdmBannerVO> getAdmBannerAppendImg(AdmBannerApndFileVO admBannerVO) throws Exception;
	
	 /**
	 * 홍보배너 등록
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmBanner(String data, HttpSession session) throws Exception;
    
	
	
}
