package portalxpert.adm.pop.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.omg.CORBA.portable.ApplicationException;

import portalxpert.adm.pop.vo.AdmPopApndFileVO;
import portalxpert.adm.pop.vo.AdmPopUserMap;
import portalxpert.adm.pop.vo.AdmPopVO;

public interface AdmPopService {
	
	/**
	 * 팝업목록조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmPopVO> getAdmPopList(AdmPopVO admPopVO) throws Exception;
	
	
	/**
	 * 팝업 조회 건수
	 * @param AdmLinkUserInfoVO
	 * @return int
	 * @exception Exception
	 */
	public int getAdmPopListToCnt(AdmPopVO admPopVO) throws Exception;
	
	
	/**
	 * 팝업 상세 조회 
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public AdmPopVO getAdmPop(AdmPopVO admPopVO) throws Exception;
	
	/**
	 * 팝업 이미지 조회
	 * @param AdmLinkUserInfoVO
	 * @return List
	 * @exception Exception
	 */
	public List<AdmPopVO> getAdmPopAppendImg(AdmPopApndFileVO admPopVO) throws Exception;
	
	 /**
	 * 팝업 등록
	 * @param AdmRmgRestVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmPop(String data, HttpSession session) throws Exception;
    
	/**
	 * Method Desciption : 팝업공개대상조회
	 * 
	 * @param con
	 * @param box
	 * @return
	 * @throws ApplicationException
	 */
	public List<AdmPopUserMap> getAdmPopUserMapList(AdmPopVO admPopVO) throws Exception;
}
