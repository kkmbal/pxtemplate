package portalxpert.board.board210.mapper;

import java.util.List;
import java.util.Map;

import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiDelInfoVO;
import portalxpert.board.board100.vo.BbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.ComCodeSpecVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @author crossent
 *
 */
@Mapper("board210Mapper")
public interface Board210Mapper  {
	
	/**
	 * 게시물 정보 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo)throws Exception ;
    
    /**
	 * 게시물 총 개수 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception Exception
	 * @auther crossent 
	 */
    public int getBbsNotiInfoListTotCnt(BoardSearchVO vo)throws Exception;
    
	/**
	 * 게시물 목록 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoList(BoardSearchVO vo)throws Exception ;
    
    /**
     * 나의 작성글 여부 확인 
     * @param Map<String, Object> map
     * @return 타인의 글 갯수
     * @exception Exception
     * @auther crossent 
     */
    public int getMyNotiCheckList(Map<String, Object> map)throws Exception;;
    
    /**
     * 게시판 즐겨찾기 여부     * @param BoardSearchVO - 조회할 정보가 담긴 VO
     * @return 즐겨찻기 된 갯수
     * @exception Exception
     * @auther crossent 
     */
    public int getMyBbsFavoriteYn(BoardSearchVO vo)throws Exception;;
    
    /**
	 * 즐겨찾기 추가
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception Exception
	 * @auther crossent
	 */
    public int insertBbsFavorite(BoardSearchVO vo)throws Exception;
    
    /**
	 * 즐겨찾기 삭제
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception Exception
	 * @auther crossent
	 */
    public int deleteBbsFavorite(BoardSearchVO vo)throws Exception;
    
    /**
     * 게시글 이동
     * @param Map
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int updateBbsNotiInfoForMove(Map<String, Object> map)throws Exception;
    
    /**
     * 게시글 이동
     * @param Map
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiInfoForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 링크 등록
     * @param BbsNotiInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiInfoForLink(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음처리
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int updateBbsNotiEvalInfoForRead(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
	 * 게시글 상세정보
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoView(BbsNotiInfoVO vo)throws Exception ;
    
    /**
	 * BBS_게시물_첨부_파일
	 * @param BbsNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_첨부_파일 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileListForView(BbsNotiApndFileVO vo)throws Exception ;
    
    /**
     * BBS_게시물_첨부_파일
     * @param BbsNotiApndFileVO - 조회할 정보가 담긴 VO
     * @return BBS_게시물_첨부_파일 정보 
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiApndFileVO getBbsNotiApndFile(BbsNotiApndFileVO vo)throws Exception ;
    
    /**
	 * BBS_게시물_태그 
	 * @param BbsNotiTagInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
//    public List<BbsNotiTagInfoVO> getBbsNotiTagInfoList(BbsNotiTagInfoVO vo)throws Exception ;
    
    /**
	 * BBS_게시물_평가_정보
	 * @param BbsNotiEvalInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiEvalInfoVO> getBbsNotiEvalInfoList(BbsNotiEvalInfoVO vo)throws Exception ;
    
    /**
	 * BBS 게시물 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList1ForView(BbsNotiOpnVO vo)throws Exception ;
    
    /**
	 * BBS 게시물 의견의 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList2ForView(BbsNotiOpnVO vo)throws Exception ;
    
    /**
	 * BBS 게시판 
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시판 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsBoardInfoVO> getBbsBoardInfoListForView(BbsBoardInfoVO vo)throws Exception ;
    
    /**
     * 의견 등록
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiOpnForView(BbsNotiOpnVO vo)throws Exception;
    
    /**
     * 의견 수정
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int updateBbsNotiOpnForView(BbsNotiOpnVO vo)throws Exception;
    
    /**
     * 의견 삭제
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int deleteBbsNotiOpnForView(BbsNotiOpnVO vo)throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록 건수
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int getBbsNotiEvalInfoCntForView(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiEvalInfoForView(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiEvalInfoForRead(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
	 * 게시글 상세보기 이전글 다음글 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsPrevNextNotiInfoForView(BbsNotiInfoVO vo)throws Exception ;
    
    /**
     * 게시글 MAX NOTI_ID 조회
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getMaxNotiId()throws Exception;
    
    /**
     * 게시글 설문조사 MAX SURVEYNO 조회
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int getMaxSurveyNo()throws Exception;
    
    /**
     * 게시글 추가항목 이동
     * @param BbsNotiInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiAddItemForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 첨부파일 이동
     * @param BbsNotiInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiApndFileForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 평가 이동
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiEvalInfoForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 의견 이동
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsOpnForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 사용자 매핍 이동 
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsNotiUserMapForMove(BbsNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 설문조사 이동 
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertBbsSurveyForMove(BbsNotiSurveyVO vo)throws Exception;
    
    /**
     * 게시글별 답글존재 여부
     * @param map
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getReNotiYn(Map<String, Object> map)throws Exception;
    
    /**
     * 게시글 읽음 건수
     * @param BbsNotiInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int getNotiReadCnt(BbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음 건수
     * @param BbsNotiInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int updateBbsNotiInfoForCntPlus(BbsNotiEvalInfoVO vo)throws Exception;
    
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
    public int insertBbsNotiDelInfo(BbsNotiDelInfoVO vo)throws Exception;
    
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
	 * 이미지,동영상 파일과 게시물 정보  
	 * @param notiId - 게시물ID
	 * @return 이미지,동영상 파일정보  
	 * @exception Exception
	 */
	public List<BbsNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(BoardSearchVO vo) throws Exception ;
	
    /**
     * 이미지,동영상 게시글 PNUM 가져오기
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     */
    public int getBbsImgMoviePnumInfo(BbsNotiInfoVO vo) throws Exception;
	
    
}

