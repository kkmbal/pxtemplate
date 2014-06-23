package portalxpert.organization.mapper;

import java.util.List;

import portalxpert.board.board100.vo.PbsUserBoardCateUseVO;
import portalxpert.organization.vo.BbsVO;
import portalxpert.organization.vo.CategoryVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.organization.vo.UserVO;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("organizationMapper")
public interface OrganizationMapper  {

	/**
	 * 조직 리스트
	 * @return 조직 리스트
	 * @exception Exception
	 */
    List<OrganizationVO> getOrganizationList(OrganizationVO vo);

    /**
     * 조직도 검색
     * @return 조직도 리스트
     * @exception Exception
     */
    List<OrganizationVO> getOrganizationListBySearch(OrganizationVO vo);

    /**
     * 조직도 에서 사용자조회
     * @param vo 부서코드
     * @return 사용자 리스트
     */
    List<UserVO> getUserList(OrganizationVO vo);
    
    /**
     * 카테고리 정보 저장
     * @param vo 카테고리 정보(json)
     * @return 카테고리  리스트
     */
    public int insertCategory(CategoryVO vo) throws Exception  ;
    
    /**
     * 카테고리 정보 저장
     * @param vo 카테고리 정보(json)
     * @return 카테고리  리스트
     */
    public int insertPbsUserBoardCateUse(PbsUserBoardCateUseVO pbsUserBoardCateUseVO) throws Exception  ;
    
    /**
     * 카테고리 정보 삭제
     * @param vo 카테고리 정보(json)
     * @return 삭제 성공 여부
     */
    public int deleteCategory(CategoryVO vo) throws Exception;
    
    /**
     * 게시판 사용 카테고리 삭제
     * @param vo 사용자 카테고리 
     * @return 삭제 성공 여부
     */
    public int deletePbsUserBoardCateUse(PbsUserBoardCateUseVO pbsUserBoardCateUseVO) throws Exception  ;
    
    /**
     * 카테고리 정보 조회
     * @param vo 사용자 아이디
     * @return 카테고리 리스트
     */
    List<CategoryVO> getCategoryList(CategoryVO vo);
    
    /**
     * 카테고리 정보 조회
     * @param vo 사용자 아이디
     * @return 카테고리 리스트
     */
    List<CategoryVO> getMyCategoryList(CategoryVO vo);
    
    /**
     * 게시판 정보 조회
     * @param vo 사용자 아이디
     * @return 게시판 리스트
     */
    List<CategoryVO> getBbsAuthList(BbsVO vo);

    /**
     * 사용자 직원 검색
     * @param userVO
     * @return
     * @author crossent
     */
    List<UserVO> getDeptMemberList(UserVO userVO) throws Exception;
    
    /**
     * 사용자 직원 검색 건수
     * @param userVO
     * @return
     * @author crossent
     */
    int getDeptMemberListTotCnt(UserVO userVO) throws Exception;
    
    /**
     * 직원 검색
     * @param userVO
     * @return
     * @author crossent
     */
    List<UserVO> getUserSearchList(UserVO userVO) throws Exception;
    
    /**
     * 직원 검색 건수
     * @param userVO
     * @return
     * @author crossent
     */
    int getUserSearchListTotCnt(UserVO userVO) throws Exception;
    
    /**
     * 부서명 가져오기
     * @param vo
     * @return
     * @throws Exception
     */
    UserVO getDeptName(UserVO userVO) throws Exception;
    
    /**
     * 부서 인원수 가져오기
     * @param userVO
     * @return
     * @throws Exception
     * @author crossent
     */
    int getDeptNameMemberCnt(OrganizationVO vo) throws Exception;
    
    /**
     * 게시판 정보 조회
     * @param vo 사용자 아이디
     * @return 게시판 리스트
     */
    List<BbsVO> getBbsAuthListNew(BbsVO vo);

	/**
	 * 사용자 이미지정보수정
	 * @param comUserPotoInfoVO
	 * @return
	 * @throws Exception
	 */
    int updateUserPotoInfo(ComUserPotoInfoVO comUserPotoInfoVO) throws Exception;

	/**
	 * 사용자 이미지정보 추가
	 * @param comUserPotoInfoVO
	 * @return
	 * @throws Exception
	 */
    int insertUserPotoInfo(ComUserPotoInfoVO comUserPotoInfoVO) throws Exception;

	/**
	 * 사용자 이미지정보 사제
	 * @param userId
	 * @return
	 * @throws Exception
	 */
    int deleteUserPotoInfo(String userId) throws Exception;
}
