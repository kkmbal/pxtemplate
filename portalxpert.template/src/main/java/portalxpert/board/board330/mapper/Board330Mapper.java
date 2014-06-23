package portalxpert.board.board330.mapper;

import java.util.List;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board330Mapper")
public interface Board330Mapper  {

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
     * 게시물 권한 입력 
     * @param BoardSearchVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public String getUserBbsMapList(BoardSearchVO vo) throws Exception  ;
    
    
    /**
	 * 개인 게시판 생성 내용을 조회한다.
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시판 상세 내용
	 * @exception
	 */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO searchVO) ;
    
    /**
	 * 개인 게시판 권한 정보조회
	 * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시판 권한 정보 
	 * @exception
	 */
    public List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO searchVO) ;
    
    /**
	 * 개인 게시물 조회
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 개인 게시물 조회
	 * @exception
	 */
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(PbsUserNotiInfoVO vo) ;
    
    /**
	 * 개인 게시물 첨부 조회
	 * @param PbsUserNotiApndFileVO - 조회할 정보가 담긴 Map
	 * @return 개인 게시물 조회
	 * @exception
	 */
    public List<PbsUserNotiApndFileVO> getPbsUserNotiApndFileList(PbsUserNotiApndFileVO vo) ;
    
    /**
     * 개인 게시물 정보 입력
     * @param vo 개인 게시물 입력정보
     * @return 입력 성공 건수
     */
    public int insertPbsUserNotiInfo(PbsUserNotiInfoVO vo) throws Exception  ;
    
    /**
     * 개인 게시물 정보 수정
     * @param vo 개인 게시물 수정정보
     * @return 수정 성공 건수
     */
    public int updatePbsUserNotiInfo(PbsUserNotiInfoVO vo) throws Exception  ;
    
    /**
     * 개인 게시물 첨부 삭제
     * @param vo 개인 게시물 첨부 정보
     * @return 삭제 성공 건수
     */
    public int deletePbsNotiApndFile(PbsUserNotiInfoVO vo) throws Exception  ;
    
    
    public int deletePbsNotiSurveyExmpl(BbsNotiSurveyVO vo) throws Exception  ;
    public int deletePbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 개인 게시물 첨부정보 입력
     * @param vo 개인 게시물 첨부 입력정보
     * @return 입력 성공 건수
     */
    public int insertPbsUserNotiApndFile(PbsUserNotiApndFileVO vo) throws Exception  ;
    
    public String getPbsUserNotiApndFileSize(String boardId) throws Exception;
    
}

 