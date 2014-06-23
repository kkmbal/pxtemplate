package portalxpert.person.person200.mapper;

import java.util.List;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person200.vo.MyProjectCommunityVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("person200Mapper")
public interface Person200Mapper  {

	  /**
	 * 설정된 공지사항 컨텐츠 리스트
	 * @param 
	 * @return  설정된 공지사항 컨텐츠 리스트
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getNotiSetContentsList(String userId);
    
    
    /**
	 * 디폴트 공지사항 컨텐츠 리스트
	 * @param 
	 * @return  디폴트 공지사항 컨텐츠 리스트
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getNotiDefaultContentsList();
    
    
    
    /**
	 * 나의 프로젝트 동호회 리스트
	 * @param 
	 * @return 나의 프로젝트 동호회 리스트
	 * @exception Exception
	 */
    List<MyProjectCommunityVO> getMyPjtCouList(MyProjectCommunityVO vo);
    
    /**
	 * 조직도 나의 친구 조회
	 * @param 
	 * @return 조직도 나의 친구 조회
	 * @exception Exception
	 */
    List<OrganizationVO> getOrgFriendList(OrganizationVO vo);
    
    /**
     * 나의 그룹  등록
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int insertPsnUserGrpInfo(PsnUserGrpInfoVO vo) throws Exception  ;
    
    /**
     * 나의 그룹 삭제
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int deletePsnUserGrpInfo(String userId) throws Exception  ;
    
    
    /**
	 *  나의 그룹 조회 
	 * @param PsnUserGrpInfoVO - 조회할 정보가 담긴 VO
	 * @return  나의 그룹리스트 
	 * @exception Exception
	 */
    List<PsnUserGrpInfoVO> getPsnUserGrpInfo(PsnUserGrpInfoVO vo);
    
    /**
     * 나의 그룹  등록
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int insertMyGroup(PsnUserGrpInfoVO vo);
    
    /**
     * 나의 그룹 별 멤버 등록
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
    public int insertPsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception  ;
    
    /**
     * 나의 그룹  별 멤버 삭제
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
    public int deletePsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception  ;
    
    
    /**
     * 나의 그룹  삭제
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
    public int deletePsnUserGrpInfo(PsnUserGrpFrdMapVO vo) throws Exception  ;
    
    
    
    /**
     * 나의 그룹  이름 수정
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
    public int updatePsnUserGrpInfo(PsnUserGrpInfoVO vo) throws Exception;
    
    
    /**
	 *  나의 그룹별  멤버조회 
	 * @param PsnUserGrpFrdMapVO - 조회할 정보가 담긴 VO
	 * @return  나의 그룹리스트 
	 * @exception Exception
	 */
    List<PsnUserGrpFrdMapVO> getPsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo);
    
    public int deletePsnUserFriendInfo(String userId) throws Exception;
    
    public int insertPsnUserFriendInfo(PsnUserGrpFrdMapVO vo) throws Exception;
    
    
}
