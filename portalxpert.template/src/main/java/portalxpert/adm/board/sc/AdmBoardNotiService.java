package portalxpert.adm.board.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.board.vo.AdmBoardNotiDelInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiPopInfoVO;
import portalxpert.adm.board.vo.AdmBoardPbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;

public interface AdmBoardNotiService {
    
	/**
	 * 게시물관리 목록
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiList(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 삭제이동게시물 목록 Count
	 * @param AdmBoardNotiDelInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmBoardNotiCnt(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물관리 삭제시 삭제코드 리스트
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiDelCodeList() throws Exception;
    
    /**
	 * 게시물 이동
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmBoardNotiMove(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
    /**
	 * 게시물 삭제
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmBoardNotiDelete(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;
    
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
	 * 개인 게시판 생성 정보를 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 VO
	 * @return 개인 게시판 생성 정보
	 * @exception Exception
	 */
    List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) throws Exception;
    
    /**
	 * 개인 게시판 참여 정보를 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 VO
	 * @return 개인 게시판 참여 정보
	 * @exception Exception
	 */
    List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO vo) throws Exception;
    
	/**
	 * 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
    List<AdmBoardNotiPopInfoVO> getBoardPopupList(AdmBoardNotiPopInfoVO vo) throws Exception;
    
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
    public int updateAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO, HttpSession session) throws Exception;

    /**
     * 공지사항 추가
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int insertAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO, HttpSession session) throws Exception;
    
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
	 * 삭제게시물 복원
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBoardNotiDelRollBack(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO, HttpSession session) throws Exception;
    
    /**
	 * 삭제이동게시물조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
    public List getBbsNotiInfoView(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception;

    /**
	 * 삭제이동게시판 조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
	public AdmBoardNotiInfoVO getAdminBbsBoardInfo(AdmBoardNotiInfoVO admBoardNotiDelInfoVO) throws Exception;
	
    /**
	 * BBS_게시물_첨부_파일
	 * @param BbsNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_첨부_파일 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileListForView(String data)throws Exception ;
    
    /**
	 * BBS 게시물 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList1ForView(String data)throws Exception ;
    
    /**
	 * BBS 게시물 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList2ForView(String data)throws Exception ;    
    
    /**
     * BBS_게시물_첨부_파일
     * @param 조회할 정보가 담긴 String
     * @return BBS_게시물_첨부_파일 정보 
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiApndFileVO getBbsNotiApndFile(BbsNotiApndFileVO vo)throws Exception;    
    
    /**
	 * 삭제게시물 영구삭제
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    public void deleteAdmBoardNotiDel(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO, HttpSession session) throws Exception;
 
}
 
