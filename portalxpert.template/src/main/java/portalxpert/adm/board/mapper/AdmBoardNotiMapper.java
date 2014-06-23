package portalxpert.adm.board.mapper;

import java.util.List;

import portalxpert.adm.board.vo.AdmBoardNotiDelInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiPopInfoVO;
import portalxpert.adm.board.vo.AdmBoardPbsNotiInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("admBoardNotiMapper")
public interface AdmBoardNotiMapper  {
	
    /**
	 * 게시물관리 목록
	 * @param AdmBoardNotiInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiList(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물관리 목록 Count
	 * @param AdmBoardNotiInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmBoardNotiCnt(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물관리 삭제시 삭제코드 리스트
	 * @param AdmBoardNotiInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiDelCodeList() throws Exception;
    
    /**
	 * 게시물 이동1 (BBS_NOTI_INFO Insert)
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmBoardNotiMove1(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물 이동2 (BBS_NOTI_DEL_INFO Insert)
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmBoardNotiMove2(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물 이동3 (BBS_NOTI_INFO Update)
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBoardNotiMove3(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물 삭제1 (BBS_NOTI_DEL_INFO Insert)
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    public void insertAdmBoardNotiDelete1(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물 삭제2 (BBS_NOTI_INFO Update)
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBoardNotiDelete2(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
	/**
	 * 개인게시판 목록 Count
	 * @param admBoardPbsNotiInfoVO
	 * @return
	 * @throws Exception
	 */
    public int getAdmPbsBoardCnt(AdmBoardPbsNotiInfoVO admBoardPbsNotiInfoVO) throws Exception;
    
	/**
	 * 개인게시판 목록
	 * @param admBoardPbsNotiInfoVO
	 * @return
	 * @throws Exception
	 */
    public List<AdmBoardPbsNotiInfoVO> getAdmPbsBoardList(AdmBoardPbsNotiInfoVO admBoardPbsNotiInfoVO) throws Exception;
    
    /**
	 * 개인 게시판 정보를 조회한다.
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 Map
	 * @return 개인 게시판 내용 
	 * @exception
	 */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) ;
    
    /**
	 * 개인 게시판 참여 정보를 조회한다.
	 * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 Map
	 * @return 개인 게시판 참여 내용 
	 * @exception
	 */
    public List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO vo) ;
    
	/**
	 * 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
    public List<AdmBoardNotiPopInfoVO> getBoardPopupList(AdmBoardNotiPopInfoVO vo) throws Exception;
    
	/**
	 * 공지사항 목록 Count
	 * @param admBoardNotiPopInfoVO
	 * @return
	 * @throws Exception
	 */
    public int getBoardPopupCnt(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception;
    
    /**
     * 공지사항 속성정보
     * @param vo
     * @return
     * @throws Exception
     */
    public AdmBoardNotiPopInfoVO selectAdmPopupNotiInfo(AdmBoardNotiPopInfoVO vo) throws Exception;
    
    /**
     * 공지사항 조회
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int selectAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception;
    
    /**
     * 공지사항 수정
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int updateAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception;

    /**
     * 공지사항 추가
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int insertAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception;
    
 	/**
 	 * 공지사항 팝업 전체취소
 	 * @param AdmBoardNotiPopInfoVO
 	 * @return ModelMap
 	 * @exception Exception
 	 */
    public int updateAdmAllPopupCancel(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception;
    
    /**
	 * 삭제이동게시물 목록
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiDelInfoVO> getAdmBoardNotiDelList(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception;
    
    /**
	 * 삭제이동게시물 목록 Count
	 * @param AdmBoardNotiDelInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmBoardNotiDelCnt(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception;
    
    /**
	 * 삭제이동게시물조회 단건
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
    public AdmBoardNotiDelInfoVO getAdmBoardNotiDel(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception;
    
    /**
	 * 삭제게시물 복원1 (BBS_NOTI_DEL_INFO 정보삭제)
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmBoardNotiDelRollBack1(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception;
    
    /**
	 * 삭제게시물 복원2 (BBS_NOTI_INFO 정보수정)
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBoardNotiDelRollBack2(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception;
    
    /**
	 * 삭제이동게시물조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
    public List getBbsNotiInfoView(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;

    /**
	 * 삭제이동게시판조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
	public AdmBoardNotiInfoVO getAdminBbsBoardInfo(AdmBoardNotiInfoVO admBoardNotiDelInfoVO);
    
}
