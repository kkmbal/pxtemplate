package portalxpert.board.board310.mapper;

import java.util.List;
import java.util.Map;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.PbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiEvalInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiOpnVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board310Mapper")
public interface Board310Mapper  {

	/**
	 * 게시물 정보 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo)throws Exception ;
    
    /**
	 * 게시물 총 개수 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception Exception
	 * @auther crossent 
	 */
    public int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo)throws Exception;
    
    /**
	 * 게시물 상세보기 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(PbsUserNotiInfoVO vo)throws Exception ;
    
    /**
	 * PBS_게시물_첨부_파일 조회 
	 * @param PbsUserNotiApndFileVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiApndFileVO> getBbsNotiApndFileList(PbsUserNotiApndFileVO vo)throws Exception ;
    
    /**
	 * PBS_게시물_이전글 다음글 조회
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsPrevNextNotiInfo(PbsUserNotiInfoVO vo)throws Exception ;
    
    /**
	 * PBS 게시물 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList1(PbsUserNotiOpnVO vo)throws Exception ;
    
    /**
	 * PBS 게시물 의견의 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList2(PbsUserNotiOpnVO vo)throws Exception ;
    
    /**
	 * PBS 의견등록
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public int insertPbsUserNotiOpn(PbsUserNotiOpnVO vo)throws Exception ;
    
    /**
	 * PBS 의견수정
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public int updatePbsUserNotiOpn(PbsUserNotiOpnVO vo)throws Exception ;
    
    /**
	 * PBS 의견삭제
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public int deletePbsUserNotiOpn(PbsUserNotiOpnVO vo)throws Exception ;
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param PbsUserNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiEvalInfo(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음처리
     * @param PbsUserNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int updatePbsUserNotiEvalInfoForRead(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 이동
     * @param PbsUserNotiInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiInfoForMove(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 나의 작성글 여부 확인 
     * @param Map<String, Object> map
     * @return 타인의 글 갯수
     * @exception Exception
     * @auther crossent 
     */
    public int getMyPbsNotiCheckList(Map<String, Object> map)throws Exception;;
    
    /**
     * 게시글 첨부파일    
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiApndFileForMove(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 평가     
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiEvalForMove(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 의견      
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiOpnForMove(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 삭제      
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int deletePbsUserNotiInfoForBoard(Map<String, Object> map)throws Exception;
    
    /**
     * 게시글 첨부파일 삭제      
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int deletePbsUserNotiApndFileForBoard(Map<String, Object> map)throws Exception;
    
    /**
     * 게시글 평가 삭제      
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int deletePbsUserNotiEvalInfoForBoard(Map<String, Object> map)throws Exception;
    
    /**
     * 게시글 의견 삭제      
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int deletePbsUserNotiOpnForBoard(Map<String, Object> map)throws Exception;
    
    
    /**
     * 게시글 Max seq
     * @param 
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int getMaxUserNotiSeq()throws Exception;
    
    /**
     * 게시글 조회수 
     * @param 
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int getUserNotiReadCnt(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 스크랩 (공용->개인)    
     * @param PbsUserNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiInfoForScrapFromBbs(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 스크랩 (개인->개인)      
     * @param PbsUserNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsUserNotiInfoForScrapFromPbs(PbsUserNotiInfoVO vo)throws Exception;
    
    public int insertPbsUserNotiApndFileForScrapFromPbs(PbsUserNotiInfoVO vo)throws Exception;
    
    public int insertPbsUserNotiApndFileForScrapFromBbs(PbsUserNotiInfoVO vo)throws Exception;
    
    /**
     * 게시글 내용 조회
     * @param String
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO getPbsUserNotiInfoViewForNotiConts(int userNotiSeq)throws Exception;
    
    /**
     * 게시글 읽음 건수
     * @param BbsNotiInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int updatePbsUserNotiInfoForCntPlus(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음 건수
     * @param PbsUserNotiEvalInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int getNotiReadCnt(PbsUserNotiEvalInfoVO vo)throws Exception;
    
    /**
	 * 동영상 파일정보  
	 * @param TbsNotiDelInfoVO - 조회할 정보가 담긴 VO
	 * @return 동영상 파일정보  
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<TbsNotiDelInfoVO> getTnMspMvpFileList(int userNotiSeq)throws Exception ;
    
    /**
	 * 이미지,동영상 파일과 게시물 정보  
	 * @param notiId - 게시물ID
	 * @return 이미지,동영상 파일정보  
	 * @exception Exception
	 */
	public List<PbsUserNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(BoardSearchVO vo) throws Exception ;
	
    /**
     * 이미지,동영상 게시글 PNUM 가져오기
     * @param PbsUserNotiInfoVO
     * @return int 
     * @exception Exception
     */
    public int getPbsImgMoviePnumInfo(PbsUserNotiInfoVO vo) throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록 건수
     * @param BbsNotiOpnVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int getPbsNotiEvalInfoCntForView(PbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * 게시글 읽음 건수
     * @param BbsNotiInfoVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public int updatePbsNotiInfoForCntPlus(PbsNotiEvalInfoVO vo)throws Exception;
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public int insertPbsNotiEvalInfoForView(PbsNotiEvalInfoVO vo)throws Exception;
}

