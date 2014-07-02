package portalxpert.adm.gen.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import portalxpert.adm.gen.mapper.AdmGenManageMapper;
import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;


@Service("admGenCodeManageService")
public class AdmGenCodeManageServiceImpl extends AbstractServiceImpl implements AdmGenCodeManageService{
    
	@Resource(name="admGenManageMapper")
    private AdmGenManageMapper admGenManageMapper;

	/**
	 * 상위코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<AdmGenCodeManageVO> getAdmGenCommonLCodeList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonLCodeList(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
    /**
	 * 글 총 개수를 조회한다.
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	public int getAdmGenCommonListTotCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonListTotCnt(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
	/**
	 * AJAX 입력 코드 등록 여부 확인
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드 등록 여부 확인
	 * @exception
	 */
	public int getAdmGenCheckCodeCdCnt(String checkCd) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCheckCodeCdCnt(checkCd);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공통코드-선택한 상위코드 정보
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 선택한 상위코드 정보
	 * @exception
	 */
	public AdmGenCodeManageVO getAdmGenCommonLCodeOne(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonLCodeOne(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
		
	/**
	 * 공통코드-상위코드 저장
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 저장
	 * @exception
	 */
	public int insertAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setInsMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.insertAdmGenCommonLCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * 공통코드-상위코드 수정
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 수정
	 * @exception
	 */
	public int updateAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setUpdMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.updateAdmGenCommonLCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공통코드-상위코드 삭제
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 삭제
	 * @exception
	 */
	public int deleteAdmGenCommonSCodeAll(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setUpdMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.deleteAdmGenCommonSCodeAll(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
		
	/**
	 * 공통코드-상세코드 삭제
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제
	 * @exception
	 */
	public int deleteAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setUpdMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.deleteAdmGenCommonLCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * 공통코드-상세코드 목록 화면 조회한다.
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<AdmGenCodeManageVO> getAdmGenCommonSCodeList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonSCodeList(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공통코드-상세코드 목록 화면 조회한다.
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<AdmGenCodeManageVO> getAdmGenCommonCodeSpecList(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * 공통코드-상위코드 목록 조회한다.
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	public List<AdmGenCodeManageVO> getAdmGenCommonLCodeType() throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonLCodeType();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
	/**
	 * 글 총 개수를 조회한다.
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	public int getAdmGenCommonSCodeListTotCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonSCodeListTotCnt(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * AJAX 입력 코드 등록 여부 확인
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드 등록 여부 확인
	 * @exception
	 */
	public int getAdmGenCheckCodeCdSpecCnt(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCheckCodeCdSpecCnt(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * 공통코드-선택한 상세코드 정보
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 선택한 상세코드 정보
	 * @exception
	 */
	public AdmGenCodeManageVO getAdmGenCommonSCodeOne(AdmGenCodeManageVO admGenCodeManageVO) throws Exception {
		try{
			return admGenManageMapper.getAdmGenCommonSCodeOne(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공통코드-상세코드 저장
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 저장
	 * @exception
	 */
	public int insertAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setInsMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.insertAdmGenCommonSCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

	/**
	 * 공통코드-상세코드 수정
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 수정
	 * @exception
	 */
	public int updateAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
		try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setUpdMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.updateAdmGenCommonSCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공통코드-상세코드 삭제
	 * @param admGenAdmGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제
	 * @exception
	 */
	public int deleteAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO, HttpSession session) throws Exception {
    	try{
			//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admGenCodeManageVO.setUpdMan((String)usrInfo.getId());
	    	
			return admGenManageMapper.deleteAdmGenCommonSCode(admGenCodeManageVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

}
