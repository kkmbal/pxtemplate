package portalxpert.adm.gen.mapper;

import java.util.List;

import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.adm.gen.vo.AdmGenContentManageVO;
import portalxpert.adm.gen.vo.AdmGenLinkVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("admGenManageMapper")
public interface AdmGenManageMapper {

	/**
	 * 상위코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonLCodeList(AdmGenCodeManageVO admGenCodeManageVO);
	
    /**
	 * 상위코드 목록 총건수조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	int getAdmGenCommonListTotCnt(AdmGenCodeManageVO admGenCodeManageVO);
    
	/**
	 * 상위코드 중복 여부 확인
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드중복여부확인(0/1)
	 * @exception
	 */
	int getAdmGenCheckCodeCdCnt(String checkCd);

	/**
	 * 상위코드 상세조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 상세정보
	 * @exception
	 */
	AdmGenCodeManageVO getAdmGenCommonLCodeOne(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 상위코드 등록
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 등록 성공여부
	 * @exception
	 */
	int insertAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO);
	
	/**
	 * 상위코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 수정 성공여부
	 * @exception
	 */
	int updateAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO);
	
	/**
	 * 상위코드 삭제-상위코드의 세부코드삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제 성공여부
	 * @exception
	 */
	int deleteAdmGenCommonSCodeAll(AdmGenCodeManageVO admGenCodeManageVO);
		
	/**
	 * 상위코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상위코드 삭제 성공여부
	 * @exception
	 */
	int deleteAdmGenCommonLCode(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonSCodeList(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 조회를 위한 상위코드 목록조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 목록
	 * @exception Exception
	 */
	List<AdmGenCodeManageVO> getAdmGenCommonLCodeType();
    
	/**
	 * 세부코드 목록 총건수조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 글 총 개수
	 * @exception
	 */
	int getAdmGenCommonSCodeListTotCnt(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 저장시, 세부코드 중복여부조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 코드중복여부확인
	 * @exception
	 */
	int getAdmGenCheckCodeCdSpecCnt(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 상세조회
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 선택한 상세코드 정보
	 * @exception
	 */
	AdmGenCodeManageVO getAdmGenCommonSCodeOne(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 등록
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 등록성공여부
	 * @exception
	 */
	int insertAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO);

	/**
	 * 세부코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 수정성공여부
	 * @exception
	 */
	int updateAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO);
	
	/**
	 * 세부코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 VO
	 * @return 상세코드 삭제성공여부
	 * @exception
	 */
	int deleteAdmGenCommonSCode(AdmGenCodeManageVO admGenCodeManageVO);

    /**
	 * 콘텐츠조회 목록
	 * @param AdmGenContentManageVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenContentManageVO> getAdmGenContentManageList(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠조회 목록 Count
	 * @param AdmGenContentManageVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenContentManageCnt(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠조회 단건
	 * @param AdmGenContentManageVO
	 * @return AdmGenContentManageVO
	 * @exception Exception
	 */
    public AdmGenContentManageVO getAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠 등록
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠 수정
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
    /**
	 * 콘텐츠 삭제
	 * @param AdmGenContentManageVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenContentManage(AdmGenContentManageVO admGenContentManageVO) throws Exception;
    
	/////////////////////////// 링크분류관리 START ///////////////////////////
    /**
	 * 링크 분류 조회
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCtlgList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 분류 조회 Count
	 * @param AdmGenLinkVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmGenSysLinkCtlgCnt(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 분류 조회 단건
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류등록시 분류순서 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlgIOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    
    
    
    
    /**
	 * 링크분류 수정(링크명)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류수정시 링크분류 순서 수정1
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlgUOrder1(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류수정시 링크분류 순서 수정2
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlgUOrder2(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류 수정(링크순서)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlgUOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    
    
    
    
    /**
	 * 링크분류삭제시 링크분류 순서 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkCtlgDOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLinkAll(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLinkCtlg(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 업무데스크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLinkCtlgPsnJobDesk(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /////////////////////////// 링크분류관리 END ///////////////////////////
    
    /////////////////////////// 링크관리 START ///////////////////////////
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
    public int getAdmGenSysLinkCnt(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크분류목록 Comb
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenSysLinkCatList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크조회 단건
	 * @param AdmGenLinkVO
	 * @return AdmGenLinkVO
	 * @exception Exception
	 */
    public AdmGenLinkVO getAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    
    
    
    
    /**
	 * 링크등록시 분류순서 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkIOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 등록
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    
    
    
    
    /**
	 * 링크 수정(링크명)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크수정시 링크 순서 수정1
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkUOrder1(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크수정시 링크 순서 수정2
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkUOrder2(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 수정(링크순서)
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkUOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    
    
    
    
    /**
	 * 링크삭제시 링크순서 수정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenSysLinkDOrder(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 링크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 *  업무데스크 삭제
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmGenSysLinkPsnJobDesk(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /////////////////////////// 링크관리 END ///////////////////////////
    
    /////////////////////////// 업무데스크관리 START ///////////////////////////
    /**
	 * 메인화면에 표시되는 링크 설정을 위한 조회
	 * @param AdmGenLinkVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmGenMainLinkList(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 메인화면에 표시되는 링크 설정 초기화
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenMainLinkAll(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /**
	 * 메인화면에 표시되는 링크 설정
	 * @param AdmGenLinkVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmGenMainLink(AdmGenLinkVO admGenLinkVO) throws Exception;
    
    /////////////////////////// 업무데스크관리 END ///////////////////////////
    
}
