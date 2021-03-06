package portalxpert.board.board300.sc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;



public interface Board300Service {
	
//	/**
//	 * 페이지별 게시물 정보 조회
//	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
//	 * @return 게시물 정보
//	 * @exception Exception
//	 */
//    List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception;
//    
//    /**
//	 * 게시물 정보 조회
//	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
//	 * @return 게시물 정보
//	 * @exception Exception
//	 */
//    int getBbsNotiInfoListTotCnt(BoardSearchVO vo) throws Exception;
    
    /**
	 * 사용자 권한
	 * @param userId - 조회할 정보가 담긴 VO
	 * @return 사용자 권한
	 * @exception
	 */
    String getUserBbsMapList(String userId)throws Exception;
    
    /**
	 * 개인게시판  내용 조회
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 개인게시판 
	 * @exception
	 */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) throws Exception ;
    
    /**
	 * 개인게시판  권한 조회
	 * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 VO
	 * @return 개인게시판  권한 조회
	 * @exception
	 */
    public List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO vo) throws Exception ;
    
    /**
	 * 개인게시물 조회
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 개인게시물 정보
	 * @exception
	 */
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(PbsUserNotiInfoVO vo) throws Exception;

    /**
	 * 개인게시물 첨부 조회
	 * @param PbsUserNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return 개인게시물 첨부 정보
	 * @exception
	 */
    public List<PbsUserNotiApndFileVO> getPbsUserNotiApndFileList(PbsUserNotiApndFileVO vo) throws Exception;
    
    /**
	 * 개인게시물 입력
	 * @param PbsUserNotiInfoVO - 입력할 정보가 담긴 VO
	 * @return 개인게시물 입력 성공건수
	 * @exception
	 */
    PbsUserNotiInfoVO insertPbsUserNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception;

    /**
     * 게시글 PNUM 가져오기
     * @param PbsUserNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int getPbsMyPnumInfo(PbsUserNotiInfoVO vo) throws Exception;
}
