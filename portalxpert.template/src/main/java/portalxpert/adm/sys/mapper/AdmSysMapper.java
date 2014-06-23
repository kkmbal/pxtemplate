package portalxpert.adm.sys.mapper;

import java.util.List;

import portalxpert.adm.gen.vo.AdmGenLinkVO;
import portalxpert.adm.sys.vo.AdmSysBbsNotiApndFileVO;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.adm.sys.vo.AdmSysTagCloudInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("admSysMapper")
public interface AdmSysMapper {
	
	/**
	 * 동영상관리 목록
	 * @param AdmSysBbsNotiApndFileVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysBbsNotiApndFileVO> getAdmSysMainMovieList(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception;
    
    /**
	 * 동영상관리 목록 Count
	 * @param AdmSysBbsNotiApndFileVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysMainMovieCnt(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception;
    
    /**
	 * 동영상관리 수정
	 * @param AdmSysBbsNotiApndFileVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmSysMainMovie(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception;
    
	/**
	 * 개인쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysPsnUserInfoVO> getAdmSysPsnQtyList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * 개인쿼터관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysPsnQtyCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * 개인쿼터관리 쿼터 수정
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmSysPsnQty(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;    
    
	/**
	 * SMS쿼터관리 목록
	 * @param AdmSysPsnUserInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysPsnUserInfoVO> getAdmSysSmsQtyList(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * SMS쿼터관리 목록 Count
	 * @param AdmSysPsnUserInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysSmsQtyCnt(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
    /**
	 * SMS쿼터관리 쿼터 수정
	 * @param AdmSysPsnUserInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmSysSmsQty(AdmSysPsnUserInfoVO admSysPsnUserInfoVO) throws Exception;
    
	/**
	 * 태그클라우드관리 목록
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysTagCloudInfoVO> getAdmSysTagCloudList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 목록 Count
	 * @param AdmSysTagCloudInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysTagCloudCnt(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 단건
	 * @param AdmSysTagCloudInfoVO
	 * @return AdmSysTagCloudInfoVO
	 * @exception Exception
	 */
    public AdmSysTagCloudInfoVO getAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 링크카테고리리스트 combo
	 * @param 
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkCtlgList() throws Exception;
    
    /**
	 * 태그클라우드관리 링크리스트 combo
	 * @param AdmSysTagCloudInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmGenLinkVO> getAdmSysTagCloudLinkList(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 등록
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 수정
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;
    
    /**
	 * 태그클라우드관리 삭제
	 * @param AdmSysTagCloudInfoVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmSysTagCloud(AdmSysTagCloudInfoVO admSysTagCloudInfoVO) throws Exception;    
}
