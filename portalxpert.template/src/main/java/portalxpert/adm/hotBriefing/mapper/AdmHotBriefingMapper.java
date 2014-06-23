package portalxpert.adm.hotBriefing.mapper;

import java.util.List;

import portalxpert.adm.hotBriefing.vo.AdmHotBriefingVO;

import egovframework.rte.psl.dataaccess.mapper.Mapper;




@Mapper("admHotBriefingMapper")
public interface AdmHotBriefingMapper {
	
	
	/**
	 * Method Desciption : 핫브리핑목록조회
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmHotBriefingVO> getAdmHotBriefingList(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	  
	
	/**
	 * Method Desciption : 핫브리핑 조회 건수
	 *  
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	 public int getAdmHotBriefingListToCnt(AdmHotBriefingVO admHotBriefingVO) throws Exception;
		 
	 
	 /**
	 * Method Desciption : 핫브리핑 상세조회 
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	 public AdmHotBriefingVO getAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception;
		 
	 
	/**
	 * Method Desciption : 핫브리핑 이미지 조회 
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmHotBriefingVO> getAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception;
		  
		
	/**
	 * Method Desciption : 핫브리핑등록
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public void insertAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception;
		  
	/**
	 * Method Desciption : 핫브리핑이미지등록
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public void insertAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑이미지수정
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public void updateAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefing(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑이미지삭제
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImg(AdmHotBriefingVO admHotBriefingVO) throws Exception;

	/**
	 * Method Desciption : 핫브리핑삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefings(AdmHotBriefingVO admHotBriefingVO) throws Exception;
	
	/**
	 * Method Desciption : 핫브리핑이미지삭제(다건)
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */	
	public int deleteAdmHotBriefingAppendImgs(AdmHotBriefingVO admHotBriefingVO) throws Exception;

	public String getAdmHotBriefingId() throws Exception;


	public int hotbriefingInnoApUpload(AdmHotBriefingVO hotBriefingVO);
}
