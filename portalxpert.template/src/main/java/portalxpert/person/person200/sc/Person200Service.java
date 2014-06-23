package portalxpert.person.person200.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person200.vo.MyProjectCommunityVO;


public interface Person200Service {
	
	/**
	 * 설정된 공지사항 컨텐츠 리스트
	 * @param 
	 * @return 설정된 공지사항 컨텐츠 리스트
	 * @exception 
	 */
	List<BbsNotiInfoVO> getNotiSetContentsList(String userId) throws Exception;
	
	
	/**
	 * 나의 프로젝트 동호회 리스트
	 * @param 
	 * @return 나의 프로젝트 동호회 리스트
	 * @exception 
	 */
	List<MyProjectCommunityVO> getMyPjtCouList(String userId,String mGubun) throws Exception;
	
	
	/**
	 * 조직도 나의 친구 조회
	 * @param 
	 * @return 조직도 나의 친구 조회
	 * @exception 
	 */
	List<OrganizationVO> getOrgFriendList(OrganizationVO vo) throws Exception;
	
	
	/**
	 * 나의관심 직원 저장
	 * * 
	 * @return 저장 성공 여부
	 * @author crossent
	 */
	String createMyMemberList(String json, HttpSession session) throws Exception;
	
	/**
	 * 나의 그룹조회  
	 * @param PsnUserGrpInfoVO -  조회할 정보가 담긴 VO
	 * @return 나의그룹리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserGrpInfoVO> getPsnUserGrpInfo(PsnUserGrpInfoVO psnUserGrpInfoVO) throws Exception;
	
	
	/**
	 * 나의관심 직원 저장
	 * * 
	 * @return 저장 성공 여부
	 * @author crossent
	 */
	int insertMyGroup(PsnUserGrpInfoVO vo) throws Exception;
	
	
	/**
	 * 나의 그룹별 멤버  조회
	 * @param PsnUserGrpFrdMapVO -  조회할 정보가 담긴 VO
	 * @return 나의그룹별 멤버 리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserGrpFrdMapVO> getPsnUserGrpFriendMap(PsnUserGrpFrdMapVO psnUserGrpFrdMapVO) throws Exception;
	
	/**
	 * 나의관심 GROUP FRIEND DELETE 
	 * * 
	 * @return 저장 성공 여부
	 * @author crossent
	 */
	int deletePsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception ;
	

	/**
	 * 나의관심 GROUP DELETE 
	 * * 
	 * @return 저장 성공 여부
	 * @author crossent
	 */
	int deletePsnUserGrpInfo(PsnUserGrpFrdMapVO vo) throws Exception ;
	
	
	/**
     * 나의 그룹  이름 수정
     * @param  PsnUserGrpFrdMapVO
     * @return 입력 성공 건수
     */
	int updatePsnUserGrpInfo(PsnUserGrpInfoVO vo) throws Exception ;
	
	
	String updatePsnUserFriendInfo(HttpSession session) throws Exception;
}
