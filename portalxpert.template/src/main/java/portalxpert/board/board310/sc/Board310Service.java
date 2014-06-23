package portalxpert.board.board310.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiEvalInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiOpnVO;
import portalxpert.common.vo.BoardSearchVO;



public interface Board310Service {
	
	/**
	 * 페이지별 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo) throws Exception;
    
    /**
	 * 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo) throws Exception;

    /**
	 * 게시물 상세보기 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(String json)throws Exception ;
    
    /**
	 * PBS_게시물_첨부_파일 조회 
	 * @param PbsUserNotiApndFileVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiApndFileVO> getBbsNotiApndFileList(String json)throws Exception ;
    
    /**
	 * PBS_게시물_이전글 다음글 조회
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsPrevNextNotiInfo(String json, String auth, int prev_pnum, int next_pnum, String userId)throws Exception ;
    
    /**
	 * PBS 게시물 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList1(String json)throws Exception ;
    
    /**
	 * PBS 게시물 의견의 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList2(String json)throws Exception ;
    
    /**
	 * PBS 의견등록
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String insertPbsUserNotiOpn(String json, HttpSession session)throws Exception ;
    
    /**
	 * PBS 의견수정
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String updatePbsUserNotiOpn(String json, HttpSession session)throws Exception ;
    
    /**
	 * PBS 의견삭제
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String deletePbsUserNotiOpn(String json)throws Exception ;
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param PbsUserNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiEvalInfo(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음처리
     * @param session, json
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String updatePbsUserNotiEvalInfoForRead(HttpSession session, String json)throws Exception;
    
    /**
     * 게시글 이동
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiInfoForMove(String json, HttpSession session) throws Exception;
    
    /**
     * 나의 작성글 여부 확인 
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 게시판 총 갯수
     * @auther crossent 
     */
    public int getMyPbsNotiCheckList(String data, HttpSession session)throws Exception ;
    
    /**
	 * 게시판 게시글 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 성공
	 * @exception
	 */
    public String deletePbsNotiInfo(String json,HttpSession session) throws Exception;
    
    /**
     * 게시글 스크랩 URL 복사
     * @param json, session
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiInfoForScrap(String json, HttpSession session) throws Exception;
    
    /**
     * 게시글 내용 조회
     * @param  Strng
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO getPbsUserNotiInfoViewForNotiConts(int userNotiSeq)throws Exception;
    
    /**
	 * 동영상 파일정보  
	 * @param TbsNotiDelInfoVO - 조회할 정보가 담긴 VO
	 * @return 동영상 파일정보  
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<TbsNotiDelInfoVO> getTnMspMvpFileList(int userNotiSeq)throws Exception ;
    
    /**
	 * 이미지동영상 파일과 게시물 정보  
	 * @param notiId - 게시물ID
	 * @return 이미지,동영상 파일정보  
	 * @exception Exception
	 */
    public List<PbsUserNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(String data, HttpSession session, String auth)throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsNotiEvalInfoForView(String json, HttpSession session)throws Exception;
}
