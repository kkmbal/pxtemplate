package portalxpert.board.board100.sc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsAddItemInfoVO;
import portalxpert.board.board100.vo.BbsBoardAddItemVO;
import portalxpert.board.board100.vo.BbsBoardChartPopVO;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsBoardUserMapVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiUserMapVO;
import portalxpert.board.board100.vo.BbsTotalSearchVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.SearchConditionVO;
import portalxpert.organization.vo.CategoryVO;


public interface Board100Service {
	
	
	BbsBoardInfoVO selectAdminBoardName(BbsBoardInfoVO vo) throws Exception;
	
	int selectAdminIsBoardName(BbsBoardInfoVO vo) throws Exception;
	
	List selectAdminAddItemList(BbsAddItemInfoVO vo) throws Exception;
	
	/**
	 * 관리자 게시판 정보 저장	 
	 * * 
	 * @return 저장 성공 여부
	 */
	String createAdminBbsList(String json, HttpSession session) throws Exception;
    
	/**
	 * 공용 게시판 리스트 조회 팝업
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    List<BbsBoardChartPopVO> selectAdminBbsChartPopList(SearchConditionVO searchVO) throws Exception;
    
    /**
	 * 공용 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    int selectAdminBbsChartPopListTotCnt(SearchConditionVO searchVO) throws Exception;
    
    /**
	 * 사용자 게시판 리스트 총 갯수
	 * @param boardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    int selectMyBbsChartPopListTotCnt(BoardSearchVO boardSearchVO) throws Exception;
    
    /**
	 * 사용자 게시판 리스트 조회 팝업
	 * @param boardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    List<BbsBoardChartPopVO> selectMyBbsChartPopList(BoardSearchVO boardSearchVO) throws Exception;
    
    /**
	 * 게시판 생성 자료
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시판 생성 리스트
	 * @exception Exception
	 */
    List<BbsBoardInfoVO> getAdminBbsBoardInfoList(BbsBoardInfoVO bbsVO) throws Exception;
    
    /**
	 * 게시판 생성 사용자 매핑 정보를 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 VO
	 * @return 게시판 생성 사용자 매핑 정보
	 * @exception Exception
	 */
    List<BbsBoardUserMapVO> getAdminBbsBoardUserMapList(BbsBoardUserMapVO vo) throws Exception;
    
    /**
	 * 게시판 생성 사용자 매핑 정보를 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 VO
	 * @return 게시판 생성 사용자 매핑 정보
	 * @exception Exception
	 */
    List<BbsBoardAddItemVO> getAdminBbsBoardAddItemList(BbsBoardAddItemVO vo) throws Exception;
    
    
    /**
	 * 동일 사용자 게시판 이름을 조회한다.
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 동일 사용자 게시판 갯수
	 * @exception Exception
	 */
    int selectUserIsBoardName(PbsUserBoardInfoVO vo) throws Exception;
    
    /**
	 * 사용자 게시판 정보 저장	 
	 * * 
	 * @return 저장 성공 여부
	 */
	String createUserBbsList(String json, HttpSession session) throws Exception;
    
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
	 * 게시물 정보 입력
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 입력
	 * @exception Exception
	 */
    BbsNotiInfoVO insertBbsNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception;
    
    /**
	 * 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getBbsNotiInfoList(BbsNotiInfoVO vo) throws Exception;
    
    /**
	 * 게시물 정보 입력
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 입력
	 * @exception Exception
	 */
    BbsNotiOpnVO insertBbsNotiOpn(String json, HttpSession session) throws Exception;
    
    /**
	 * 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiOpnVO> getBbsNotiOpnList(BbsNotiOpnVO vo) throws Exception;
    
    /**
	 * 게시물 정보(이미지) 조회
	 * @param BbsTmpNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return 게시물 (이미지,동영상,첨부) 정보
	 * @exception Exception
	 */
    List<BbsNotiApndFileVO> getBbsNotiApndFileList(BbsNotiApndFileVO vo) throws Exception;

    /**
	 * 사용자 게시판 리스트 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    List<PbsUserBoardInfoVO> getUserBbsList(BoardSearchVO boardSearchVO) throws Exception;
    
    /**
	 * 사용자 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    int getUserBbsListTotCnt(BoardSearchVO boardSearchVO)throws Exception;
    
    /**
	 * 사용자 게시판 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 성공
	 * @exception
	 */
    String deleteUserBbs(String json,HttpSession session) throws Exception;
    
    /**
	 * 게시판 종합 검색 리스트 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    List<BbsTotalSearchVO> getBbsTotalSearchList(SearchConditionVO searchVO) throws Exception;

    /**
	 * 게시판 종합 검색 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    int getBbsTotalSearchListTotCnt(SearchConditionVO searchVO) throws Exception;

    /**
	 * 게시판 종합 상세검색 리스트 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    List<BbsTotalSearchVO> getBbsTotalSearchList2(SearchConditionVO searchVO) throws Exception;

    /**
	 * 게시판 종합 상세 검색 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    int getBbsTotalSearchListTotCnt2(SearchConditionVO searchVO) throws Exception;

    /**
	 * 사용자 권한
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 사용자 권한
	 * @exception
	 */
    String getUserBbsMapList(String userId)throws Exception;
    
    /**
	 * 게시물 권한 정보 조회 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시물 권한  리스트
	 * @exception Exception
	 */
    List<BbsNotiUserMapVO> getBbsNotiUserMapList(BbsNotiUserMapVO vo) throws Exception;
    

    /**
     * 게시판 접근권한 조회
     * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    List<BbsBoardInfoVO> getBoardUserMapInfo(BbsBoardInfoVO vo) throws Exception;
    
    /**
     * 게시글 PNUM 가져오기
     * @param BbsNotiInfoVO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    public int getBbsMyPnumInfo(BbsNotiInfoVO vo)throws Exception ;
    
    /**
	 * 선택한 카테고리의 개인 게시판 정보 조회 
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 선택한 카테고리의 개인 게시판 정보
	 * @exception Exception
	 */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoCateList(PbsUserBoardInfoVO vo)throws Exception;
    
    /**
	 * 선택한 카테고리의 공용 게시판 정보 조회 
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 선택한 카테고리의 공용 게시판 정보
	 * @exception Exception
	 */
    public List<BbsBoardInfoVO> getBbsBoardInfoCateList(BbsBoardInfoVO vo)throws Exception;
    
    
    /**
	 * 나의 카테고리 정보 
	 * @param CategoryVO - 조회할 정보가 담긴 VO
	 * @return 나의 카테고리 정보
	 * @exception Exception
	 */
    public CategoryVO selectMyCategoryCont(CategoryVO vo) throws Exception;
    
    /**
	 * 카테고리 정보 저장 
	 * @param CategoryVO - 조회할 정보가 담긴 VO
	 * @return 나의 카테고리 정보
	 * @exception Exception
	 */
    public int updatePbsUserCategoryInfo(String json, HttpSession session, String admin) throws Exception  ;
    
    
    
    /**
	 * 공용 게시판 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 성공
	 * @exception
	 */
    public String deleteBbsBoardInfo(String json,HttpSession session) throws Exception;
    

    /**
     * 게시글 삭제 여부
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getNotiDelYn(String data) throws Exception;
    
    /**
     * 게시글 권한 가져오기
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther  
     */
    public int getNotiUserAuth(BbsNotiInfoVO vo) throws Exception;
    
    /**
     * 게시판 사용현황 가져오기
     * @param BbsBoardInfoVO
     * @return int 
     * @exception Exception
     * @auther  
     */
    public BbsBoardInfoVO getAdmBbsStat(BbsBoardInfoVO vo) throws Exception ;
    
    /**
     * 게시판 공개여부 가져오기
     * @param BbsBoardInfoVO
     * @return int 
     * @exception Exception
     * @auther  
     */
    public BbsBoardInfoVO getAdminBoardOpen(BbsBoardInfoVO vo) throws Exception;
}
