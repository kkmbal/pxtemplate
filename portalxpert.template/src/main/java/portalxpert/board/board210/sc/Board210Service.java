package portalxpert.board.board210.sc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.ComCodeSpecVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.common.vo.BoardSearchVO;


public interface Board210Service {
	
	/**
	 * 페이지별 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception;
    
    /**
	 * 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    int getBbsNotiInfoListTotCnt(BoardSearchVO vo) throws Exception;
    
	/**
	 * 게시물 목록 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getBbsNotiInfoList(BoardSearchVO vo) throws Exception;
    
	/**
	 * 공용 게시판 게시글 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 성공
	 * @exception
	 */
    String deleteNotiInfo(String json,HttpSession session) throws Exception;
    
    /**
     * 나의 작성글 여부 확인 
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 게시판 총 갯수
     * @auther crossent 
     */
    int getMyNotiCheckList(String data, HttpSession session)throws Exception ;
    
    /**
     * 게시판 즐겨찾기 여부
     * @param BoardSearchVO - 조회할 정보가 담긴 VO
     * @return 즐겨찻기 된 갯수
     * @auther crossent 
     */
    int getMyBbsFavoriteYn(BoardSearchVO vo)throws Exception ;
    
    /**
	 * 즐겨찾기 추가
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception
	 */
    public int insertBbsFavorite(String json, HttpSession session)throws Exception;
    
    /**
	 * 즐겨찾기 삭제
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception
	 */
    public int deleteBbsFavorite(String json, HttpSession session)throws Exception;
    
    /**
     * 게시글 이동
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiInfoForMove(String json, HttpSession session)throws Exception;
    
    /**
     * 링크등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiInfoForLink(String json, HttpSession session, HttpServletRequest request)throws Exception;
    
    /**
     * 게시글 읽음처리
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String updateBbsNotiEvalInfoForRead(String json, HttpSession session)throws Exception;
    
    /**
	 * 게시글 상세정보
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoView(String data)throws Exception ;
    
    /**
	 * BBS_게시물_첨부_파일
	 * @param BbsNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_첨부_파일 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileListForView(String data)throws Exception ;
    
    /**
     * BBS_게시물_첨부_파일
     * @param 조회할 정보가 담긴 String
     * @return BBS_게시물_첨부_파일 정보 
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiApndFileVO getBbsNotiApndFile(BbsNotiApndFileVO vo)throws Exception;    
    
    /**
	 * BBS_게시물_태그 
	 * @param BbsNotiTagInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
    //public List<BbsNotiTagInfoVO> getBbsNotiTagInfoList(String data)throws Exception ;
    
    /**
	 * BBS_게시물_태그
	 * @param BbsNotiEvalInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiEvalInfoVO> getBbsNotiEvalInfoList(String data)throws Exception ;
    
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
	 * BBS 게시판 
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시판 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsBoardInfoVO> getBbsBoardInfoListForView(String data)throws Exception ;
    
    /**
     * 의견 등록
     * @param request, json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO insertBbsNotiOpnForView(HttpServletRequest request,String json, HttpSession session)throws Exception;
    
    /**
     * 의견 수정
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO updateBbsNotiOpnForView(HttpServletRequest request, String json, HttpSession session)throws Exception;
    
    /**
     * 의견 삭제
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO deleteBbsNotiOpnForView(String json)throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiEvalInfoForView(String json, HttpSession session)throws Exception;
    
    /**
     * 게시물 읽음 처리 단건
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String updateBbsNotiEvalInfoForReadForView(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiEvalInfoForRead(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
	 * 게시글 상세보기 이전글 다음글 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsPrevNextNotiInfoForView(String json, String auth, int prev_pnum, int next_pnum, String notiReadmanAsgnYn, String userId)throws Exception ;
    
    /**
     * 게시글별 답글존재 여부 
     * @param json, session
     * @return 답글존재여부 
     * @auther crossent 
     */
    public String getReNotiYn(String json, HttpSession session)throws Exception ;
    
    /**
     * 게시글 내용 조회
     * @param notiId
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO getBbsNotiInfoViewForNotiConts(String notiId)throws Exception;
    
    
    /**
     * 게시글 삭제정보 
     * @param BbsNotiDelInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiDelInfo(String data, HttpSession session)throws Exception;
    
    /**
	 * 동영상 파일정보  
	 * @param TbsNotiDelInfoVO - 조회할 정보가 담긴 VO
	 * @return 동영상 파일정보  
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<TbsNotiDelInfoVO> getTnMspMvpFileList(String notiId)throws Exception ;
    
    /**
     * 코드 조회
     * @return List<ComCodeSpecVO>
     * @exception Exception
     * @auther crossent 
     */
    public List<ComCodeSpecVO> getComCodeSpecList()throws Exception ;
    
    /**
	 * 이미지동영상 파일과 게시물 정보  
	 * @param notiId - 게시물ID
	 * @return 이미지,동영상 파일정보  
	 * @exception Exception
	 */
    public List<BbsNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(String data, HttpSession session, String auth)throws Exception;
    
}
