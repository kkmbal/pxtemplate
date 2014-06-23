package portalxpert.adm.banner.mapper;

import java.util.List;

import portalxpert.adm.banner.vo.AdmBannerVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;




@Mapper("admBannerMapper")
public interface AdmBannerMapper {
	
	
	/**
	 * Method Desciption : 홍보배너목록조회
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmBannerVO> getAdmBannerList(AdmBannerVO admBannerVO) throws Exception;
	  
	
	/**
	 * Method Desciption : 홍보배너 조회 건수
	 *  
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	 public int getAdmBannerListToCnt(AdmBannerVO admBannerVO) throws Exception;
		 
	 
	 /**
	 * Method Desciption : 홍보배너 상세조회 
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	 public AdmBannerVO getAdmBanner(AdmBannerVO admBannerVO) throws Exception;
		 
	 
	/**
	 * Method Desciption : 홍보 배너 이미지 조회 
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmBannerVO> getAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception;
		  
		
	/**
	 * Method Desciption : 홍보 배너등록
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public void insertAdmBanner(AdmBannerVO admBannerVO) throws Exception;
		  
	/**
	 * Method Desciption : 홍보 배너이미지등록
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public void insertAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmBanner(AdmBannerVO admBannerVO) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanner(AdmBannerVO admBannerVO) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너이미지삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImg(AdmBannerVO admBannerVO) throws Exception;

	/**
	 * Method Desciption : 홍보배너삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBanners(AdmBannerVO admBannerVO) throws Exception;
	
	/**
	 * Method Desciption : 홍보배너이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmBannerAppendImgs(AdmBannerVO admBannerVO) throws Exception;


	public void bannerInnoApUpload(AdmBannerVO bannerFileVO) throws Exception;


	public String getAdmBannerId() throws Exception;

}
