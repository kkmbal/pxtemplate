package portalxpert.board.board100.mapper;

import java.util.List;
import java.util.Map;

import portalxpert.board.board100.vo.BbsAddItemInfoVO;
import portalxpert.board.board100.vo.BbsBoardAddItemVO;
import portalxpert.board.board100.vo.BbsBoardChartPopVO;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsBoardUserMapVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.BbsNotiUserMapVO;
import portalxpert.board.board100.vo.BbsTotalSearchVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board100.vo.PsnTmlnInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.SearchConditionVO;
import portalxpert.organization.vo.CategoryVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board100Mapper")
public interface Board100Mapper  {

	
    public BbsBoardInfoVO getAdminBoardName(BbsBoardInfoVO vo) throws Exception ;
    
    /**
	 * 관리자 게시판 명을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 동일 게시판 갯수
	 * @exception
	 */
    
    public int getAdminIsBoardName(BbsBoardInfoVO vo) ;
    
    /**
	 * 추가항목 리스트를 조회한다.
	 * @param 
	 * @return 추가 항목 리스트
	 * @exception
	 */
    public List<BbsAddItemInfoVO> getAdminAddItemList(BbsAddItemInfoVO vo) throws Exception ;
    
    /**
	 * 공용 게시판 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 게시판 목록
	 * @exception Exception
	 */
    public List<BbsBoardChartPopVO> getAdminBbsChartPopList(SearchConditionVO searchVO) throws Exception ;
    
    /**
	 * 공용 게시판 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getAdminBbsChartPopListTotCnt(SearchConditionVO searchVO) ;
    
    /**
	 * 사용자 게시판 목록을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 게시판 목록
	 * @exception Exception
	 */
    public List<BbsBoardChartPopVO> getMyBbsChartPopList(BoardSearchVO boardSearchVO) throws Exception ;
    
    /**
	 * 사용자 게시판 총 갯수를 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getMyBbsChartPopListTotCnt(BoardSearchVO boardSearchVO) ;
    
    /**
     * 게시판 마스터 입력
     * @param vo 게시판 마스터 정보
     * @return 입력 성공 건수
     */
    public int insertAdminBbsBoardInfo(BbsBoardInfoVO vo) throws Exception  ;
    
    /**
     * 게시판 사용자 매핑 입력
     * @param vo 게시판 사용자 정보
     * @return 입력 성공 건수
     */
    public int insertAdminBbsBoardUserMap(BbsBoardUserMapVO vo) throws Exception  ;
    
    /**
     * 게시판 추가 항목 입력
     * @param vo 게시판 추가항목 정보
     * @return 입력 성공 건수
     */
    public int insertAdminBbsBoardAddItem(BbsBoardAddItemVO vo) throws Exception  ;
    
    
    /**
	 * 게시판 생성 내용을 조회한다.
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시판 상세 내용
	 * @exception
	 */
    public List<BbsBoardInfoVO> getAdminBbsBoardInfoList(BbsBoardInfoVO searchVO) ;
    
    /**
	 * 게시판 생성 사용자 매핑 정보 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 Map
	 * @return 게시판 상세 내용
	 * @exception
	 */
    public List<BbsBoardUserMapVO> getAdminBbsBoardUserMapList(BbsBoardUserMapVO vo) ;
    
    /**
	 * 게시판 생성 추가 항목정보를 조회한다.
	 * @param BbsBoardAddItemVO - 조회할 정보가 담긴 Map
	 * @return 게시판 상세 내용
	 * @exception
	 */
    public List<BbsBoardAddItemVO> getAdminBbsBoardAddItemList(BbsBoardAddItemVO vo) ;
    
    /**
     * 게시판 마스터 수정
     * @param vo 게시판 마스터 정보
     * @return 수정 성공 건수
     */
    public int updateAdminBbsBoardInfo(BbsBoardInfoVO vo) throws Exception  ;
    
    /**
     * 사용자 매핑 정보 삭제
     * @param vo 사용자 매핑 정보
     * @return 삭제 성공 건수
     */
    public int deleteAdminBbsBoardUserMap(BbsBoardUserMapVO vo) throws Exception  ;
    
    /**
     * 추가 항목정보 삭제
     * @param vo 추가 항목정보
     * @return 삭제 성공 건수
     */
    public int deleteAdminBbsBoardAddItem(BbsBoardAddItemVO vo) throws Exception  ;
    
    /**
	 * 사용자 게시판 명을 조회한다.
	 * @param searchMap - 조회할 정보가 담긴 Map
	 * @return 동일 게시판 갯수
	 * @exception
	 */
    public int getUserIsBoardName(PbsUserBoardInfoVO vo) ;
    
    /**
     * 개인 게시판 메인 입력
     * @param vo 개인 게시판 입력 정보 
     * @return 입력 성공 건수
     */
    public int insertPbsUserBoardInfo(PbsUserBoardInfoVO vo) throws Exception  ;
    
    /**
     * 개인 게시판 메인 수정
     * @param vo 개인 게시판 수정 정보 
     * @return 수정 성공 건수
     */
    public int updatePbsUserBoardInfo(PbsUserBoardInfoVO vo) throws Exception  ;
    
    /**
     * 개인 게시판 참여 정보 입력
     * @param vo 개인 게시판 참여 정보
     * @return 입력 성공 건수
     */
    public int insertPbsUserBoardPartInfo(PbsUserBoardPartInfoVO vo) throws Exception  ;
    
    /**
     * 타임라인 정보 등록 
     * @param vo 타임라인 정보
     * @return 입력 성공 건수
     */
    public int insertPsnTmlnInfo(PsnTmlnInfoVO vo) throws Exception;
    
    /**
     * 개인 게시판 참여 정보 삭제
     * @param vo 개인 게시판 참여 정보
     * @return 삭제 성공 건수
     */
    public int deletePbsUserBoardPartInfo(PbsUserBoardPartInfoVO vo) throws Exception  ;
    
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
     * 게시물 정보 입력
     * @param vo 게시물 입력정보
     * @return 입력 성공 건수
     */
    public int insertBbsNotiInfo(BbsNotiInfoVO vo) throws Exception  ;
    
    /**
	 * 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoList(BbsNotiInfoVO vo) ;
    
    /**
     * 의견 정보 입력
     * @param vo 의견 입력정보
     * @return 입력 성공 건수
     */
    public int insertBbsNotiOpn(BbsNotiOpnVO vo) throws Exception  ;
    
    /**
	 * 의견 정보 조회
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 의견 정보 
	 * @exception
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList(BbsNotiOpnVO vo) ;
    
    /**
     * 첨부파일 입력
     * @param vo 첨부파일 입력정보
     * @return 첨부파일 성공 건수
     */
    public int insertBbsNotiApndFile(BbsNotiApndFileVO vo) throws Exception  ;
    
    /**
	 * 첨부파일 조회
	 * @param BbsTmpNotiApndFileVO - 조회할 정보가 담긴 Map
	 * @return 첨부파일 정보 
	 * @exception
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileList(BbsNotiApndFileVO vo) ;
    
    /**
	 * 사용자 게시판 리스트 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    public List<PbsUserBoardInfoVO> getUserBbsList(BoardSearchVO vo) throws Exception;
    
    /**
	 * 사용자 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getUserBbsListTotCnt(BoardSearchVO vo);
    
    /**
	 * 사용자 게시판 삭제
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 삭제 성공
	 * @exception
	 */
    public int deleteUserBbs(PbsUserBoardInfoVO boardInfo);
    
    /**
	 * 게시판 종합 검색 리스트
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    public List<BbsTotalSearchVO> getBbsTotalSearchList(SearchConditionVO searchVO) throws Exception;
    
    /**
	 * 게시판 종합 검색 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getBbsTotalSearchListTotCnt(SearchConditionVO searchVO);
    
    /**
	 * 게시판 종합 검색 리스트
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    public List<BbsTotalSearchVO> getBbsTotalSearchList2(SearchConditionVO searchVO) throws Exception;
    
    /**
	 * 게시판 종합 검색 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getBbsTotalSearchListTotCnt2(SearchConditionVO searchVO);
    
    /**
     * 설문 정보 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    
    /**
     * 설문 보기 정보 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiSurveyExmpl(BbsNotiSurveyExmplVO vo) throws Exception  ;
    
    /**
     * 게시물 권한 입력 
     * @param BbsNotiUserMapVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiUserMap(BbsNotiUserMapVO vo) throws Exception  ;
    
    /**
     * 게시물 권한 입력 
     * @param BoardSearchVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public String getUserBbsMapList(BoardSearchVO vo) throws Exception  ;
    
    /**
     * 게시물 정보 수정
     * @param vo 게시물 수정 정보
     * @return 수정 성공 건수
     */
    public int updateBbsNotiInfo(BbsNotiInfoVO vo) throws Exception  ;
    
    /**
     * 설문 보기정보  삭제
     * @param vo 설문 보기 정보
     * @return 삭제 성공 건수
     */
    public int deleteBbsNotiSurveyExmpl(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 설문 정보  삭제
     * @param vo 설문  정보
     * @return 삭제 성공 건수
     */
    public int deleteBbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 첨부 정보  삭제
     * @param vo 첨부  정보
     * @return 삭제 성공 건수
     */
    public int deleteBbsNotiApndFile(BbsNotiInfoVO vo) throws Exception  ;
    
    /**
     * 게시물 권한 정보  삭제
     * @param vo 권한 정보
     * @return 삭제 성공 건수
     */
    public int deleteBbsNotiUserMap(BbsNotiInfoVO vo) throws Exception  ;
    
    /**
	 * 사용자 게시글 삭제
	 * @param Map<String, Object> map
	 * @return 삭제 성공
	 * @exception Exception
	 * @auther crossent 
	 */
    public int deleteNotiInfo(Map<String, Object> map)throws Exception;
    
    public int deleteBbsNotiAddItemForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiApndFileForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiEvalInfoForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiOpnForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiOpnApndFileForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiOpnEvalInfoForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiPopupInfoForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiSurveyForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiSurveyAnswForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiSurveyExmplForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiTagInfoForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiUserEvalCntForBoard (Map<String, Object> map) throws Exception ;
    public int deleteBbsNotiUserMapForBoard (Map<String, Object> map) throws Exception ;
    
    /**
     * 게시판 접근 권한가져오기
     * @param BbsBoardInfoVO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    public List<BbsBoardInfoVO> getBoardUserMapInfo(BbsBoardInfoVO vo)throws Exception;
    
    /**
     * 게시글 PNUM 가져오기
     * @param BbsBoardInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int getBbsMyPnumInfo(BbsNotiInfoVO vo) throws Exception;
    
    /**
     * 게시물 권한 정보 조회
     * @param vo 권한 정보
     * @return 게시물 권한 정보
     */    
    public List<BbsNotiUserMapVO> getBbsNotiUserMapList(BbsNotiUserMapVO vo) ;
    
    /**
     * 게시판 카테고리 정보조회
     * @param vo 카테고리 정보
     * @return 게시판 카테고리 정보
     */    
    public CategoryVO selectMyCategoryCont(CategoryVO vo) ;
    
    /**
     * 게시판 카테고리 건수조회
     * @param vo 카테고리 정보
     * @return 게시판 카테고리 정보 건수
     */    
    public int selectMyCategoryContCnt(CategoryVO vo) throws Exception  ;

    /**
     * 게시판 카테고리 정보 등록
     * @param vo 카테고리 정보
     * @return 게시판 카테고리 정보
	 * @exception Exception
     */
    public int insertPbsUserCategoryInfo(CategoryVO vo) throws Exception  ;

    /**
     * 게시판 카테고리 정보 수정
     * @param vo 카테고리 정보
     * @return 게시판 카테고리 정보
	 * @exception Exception
     */
    public int updatePbsUserCategoryInfo(CategoryVO vo) throws Exception  ;
    
    /**
     * 선택한 카테고리 하위 개인 게시판 정보 조회
     * @param PbsUserBoardInfoVO
     * @return List<PbsUserBoardInfoVO>
     * @exception Exception
     * @auther 
     */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoCateList(PbsUserBoardInfoVO vo)throws Exception;
    
    /**
     * 선택한 카테고리 하위 공용 게시판 정보 조회
     * @param BbsBoardInfoVO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther 
     */
    public List<BbsBoardInfoVO> getBbsBoardInfoCateList(BbsBoardInfoVO vo)throws Exception;
    
    /**
	 * 공용 게시판 삭제
	 * @param 
	 * @return 삭제 성공
	 * @exception
	 */
    public int deleteBbsBoardInfo(BbsBoardInfoVO boardInfo)throws Exception;
    
    /**
	 * 참여게시판 입력
	 * @param 
	 * @return 입력 성공
	 * @exception
	 */
    public int insertPbsUserBoardCateUse(PbsUserBoardInfoVO boardInfo)throws Exception;

    /**
     * 게시글 삭제여부
     * @param BoardSearchVO
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getNotiDelYn(BoardSearchVO vo)throws Exception; 
    
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
}
