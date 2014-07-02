package portalxpert.adm.gen.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.adm.gen.vo.AdmGenCodeManageVO;

public interface AdmGenCodeManageService {

	/**
	 * 상위코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonLCodeList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;
	
	/**
	 * 상위코드 목록 총건수조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	int getAdmGenCommonListTotCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;
    
	/**
	 * 상위코드 중복 여부 확인
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드중복여부확인(0/1)
	 * @exception
	 */
	int getAdmGenCheckCodeCdCnt(String checkCd) throws Exception;

	/**
	 * 상위코드 상세조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 상세정보
	 * @exception
	 */
	AdmGenCodeManageVO getAdmGenCommonLCodeOne(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;
	
	/**
	 * 상위코드 등록
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 등록 성공여부
	 * @exception
	 */
	int insertAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;

	/**
	 * 상위코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 수정 성공여부
	 * @exception
	 */
	int updateAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;
	
	/**
	 * 상위코드 삭제-상위코드의 세부코드삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제 성공여부
	 * @exception
	 */
	int deleteAdmGenCommonSCodeAll(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;
		
	/**
	 * 상위코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 삭제 성공여부
	 * @exception
	 */
	int deleteAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;

	/**
	 * 세부코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonSCodeList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;

	/**
	 * 세부코드 조회를 위한 상위코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonLCodeType() throws Exception;
	
	/**
	 * 세부코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonCodeSpecList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;
    
	/**
	 * 세부코드 목록 총건수조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	int getAdmGenCommonSCodeListTotCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;

	/**
	 * 세부코드 저장시, 세부코드 중복여부조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드중복여부확인
	 * @exception
	 */
	int getAdmGenCheckCodeCdSpecCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;

	/**
	 * 세부코드 상세조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 선택한 상세코드 정보
	 * @exception
	 */
	AdmGenCodeManageVO getAdmGenCommonSCodeOne(AdmGenCodeManageVO admGenCodeManageVO) throws Exception;
	
	/**
	 * 세부코드 등록
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 등록성공여부
	 * @exception
	 */
	int insertAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;

	/**
	 * 세부코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 수정성공여부
	 * @exception
	 */
	int updateAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;
	
	/**
	 * 세부코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제성공여부
	 * @exception
	 */
	int deleteAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception;

}
