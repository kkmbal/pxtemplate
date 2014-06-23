package portalxpert.person.person100.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.common.vo.UserInfoVO;
import portalxpert.person.person100.vo.AdmTmlnEnvSetVO;
import portalxpert.person.person100.vo.ComCntsInfoVO;
import portalxpert.person.person100.vo.ComUserDeptInfoVO;
import portalxpert.person.person100.vo.ComUserInfoVO;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import portalxpert.person.person100.vo.MesUserCountsVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.person.person100.vo.PsnUserBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrTagVO;
import portalxpert.person.person100.vo.PsnUserFrdInfoVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person100.vo.PsnUserTmlnAlertSetVO;
import portalxpert.person.person100.vo.SmsSendHistVO;
import portalxpert.person.person100.vo.TmlnEnvInfoVO;




public interface Person100Service {
	
	/**
     * 사용자 정보수정 
     * @param 사용자 정보  
     * @return 수정 성공
     */
	String updatePsnUserInfo(String json,HttpSession session) throws Exception;
	
	/**
	 * 사용자정보 
	 * @param comUserInfoVO -  조회할 정보가 담긴 VO
	 * @return 사용자정보
	 * @exception Exception
	 */
	List getComUserInfoList(ComUserInfoVO comUserInfoVO)throws Exception;
	

	

	/**
	 * 개인정보관리 사용자 이미지 조회
	 * @param comUserInfoVO
	 * @return
	 */
	List getComUserPotoInfoList(ComUserPotoInfoVO comUserPotoInfoVO)throws Exception;
	
	/**
	 * 개인홈 MY게시판 조회 (ZTREE)
	 * @param comUserInfoVO
	 * @return
	 */
	List getPbsUserBoardInfoListForZTree(PbsUserBoardInfoVO pbsUserBoardInfoVO)throws Exception;
	
	/**
	 * 개인홈 즐겨찾기, 참여 리스트
	 * @param comUserInfoVO
	 * @return
	 */
	List getPersonLnbPartList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception;
	
	/**
	 * 개인홈 즐겨찾기, 참여 리스트
	 * @param comUserInfoVO
	 * @return
	 */
	List getPersonLnbFavoList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception;
	
	
	/**
	 * MY게시판 조회
	 * @param pbsUserBoardInfoVO
	 * @return
	 */
	List getPbsUserBoardInfoList(PbsUserBoardInfoVO pbsUserBoardInfoVO) throws Exception;
	
	/**
	 * 개인정보관리 SMS발송이력
	 * @param smsSendHistVO
	 * @return SmsSendHistVO - 개인정보관리 SMS발송이력
	 * @throws Exception
	 */
	List getSmsSendHistList(SmsSendHistVO smsSendHistVO)throws Exception;

    /**
	 * 개인정보관리 SMS발송이력 총 개수
	 * @param SmsSendHistVO - 조회할 정보가 담긴 VO
	 * @return 총 개수
	 * @exception Exception
	 */
    int getSmsSendHistListTotCnt(SmsSendHistVO smsSendHistVO) throws Exception;
    
    /**
     * SMS발송이력 삭제
     * @param json
     * @param session
     * @return 삭제 성공
     * @throws Exception
     */
    String deleteSmsSendHist(String json, HttpSession session)throws Exception;
    
    /**
	 * 관심태그 등록 
	 * * 
	 * @return 저장 성공 여부
	 */
	String insertPsnUserCncrTag(String json, HttpSession session) throws Exception;
	
	/**
	 * 관심태그 조회
	 * @param PsnUserCncrTagVO
	 * @return PsnUserCncrTagVO - 관심태그 정보
	 * @throws Exception
	 */
	List getPsnUserCncrTagList(PsnUserCncrTagVO psnUserCncrTagVO)throws Exception;
	
	/**
	 * 나의관심 직원 조회  
	 * @param mesUserCounts -  조회할 정보가 담긴 VO
	 * @return 직원리스트
	 * @exception Exception
	  * @author crossent
	 */
	List<PsnUserFrdInfoVO> getPsnUserFriendInfo(PsnUserFrdInfoVO psnUserFrdInfoVO) throws Exception;
	
	/**
	 * 나의 그룹조회  
	 * @param PsnUserGrpInfoVO -  조회할 정보가 담긴 VO
	 * @return 나의그룹리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserGrpInfoVO> getPsnUserGrpInfo(PsnUserGrpInfoVO psnUserGrpInfoVO) throws Exception;
	
	/**
	 * 나의 그룹별 멤버  조회
	 * @param PsnUserGrpFrdMapVO -  조회할 정보가 담긴 VO
	 * @return 나의그룹별 멤버 리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserGrpFrdMapVO> getPsnUserGrpFriendMap(PsnUserGrpFrdMapVO psnUserGrpFrdMapVO) throws Exception;
	
	
	
	/**
	 * 나의 부서직원 조회
	 * @param PsnUserFrdInfoVO -  조회할 정보가 담긴 VO
	 * @return  나의 부서직원 조회 리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserFrdInfoVO> getPsnUserOVInfo(PsnUserFrdInfoVO psnUserFrdInfoVO) throws Exception;
	
	
	/**
	 * 내게 관심있는 직원 조회
	 * @param PsnUserFrdInfoVO -  조회할 정보가 담긴 VO
	 * @return  나의 부서직원 조회 리스트
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserFrdInfoVO> getPsnInterestedUser(PsnUserFrdInfoVO psnUserFrdInfoVO) throws Exception;
	
	/**
	 * 내게 관심있는 직원 상세조회
	 * @param PsnUserFrdInfoVO -  조회할 정보가 담긴 VO
	 * @return  나의 부서직원 조회 리스트
	 * @exception Exception
	 * @author crossent
	 */
	PsnUserFrdInfoVO getPsnUserDetailInfo(PsnUserFrdInfoVO psnUserFrdInfoVO) throws Exception;
	
	
	/**
	 * 나의관심 직원 건수
	 * @param 유저 아이디
	 * @return 직원건수
	 * @exception Exception
	 * @author crossent
	 */
	 int getPsnUserFriendInfoCnt(String userId) throws Exception;
	
	 /**
	 * 나의 My Staff 건수
	 * @param 유저 아이디
	 * @return 직원건수
	 * @exception Exception
	 * @author crossent
	 */
	 int getPsnUserOVInfoCnt(String userId) throws Exception;
	
	 /**
	 * 나의 Added Me 건수
	 * @param 유저 아이디
	 * @return 직원건수
	 * @exception Exception
	 * @author crossent
	 */
	 int getStaffFriendInfoCnt(String userId) throws Exception;
			 
	 
	/**
	 * 나의관심 직원 저장
	 * * 
	 * @return 저장 성공 여부
	 * @author crossent
	 */
	String createMyMemberList(String json, HttpSession session) throws Exception;
	


	


	
	 /**
	 * 전송할 수 있는 SMS 건수
	 * @param 
	 * @return 
	 * @exception Exception
	 * @author crossent
	 */
	String getMySmsCnt(String userId) throws Exception;
	
	 /**
	 * 전송할 수 있는 총 SMS 건수
	 * @param 
	 * @return 
	 * @exception Exception
	 * @author crossent
	 */
	String getMySmsAllCnt(String userId) throws Exception;
	
	
	/**
     * SMS 건수 수정
     * @param 유저 아이디  
     * @return 수정 성공
     * @author crossent
     */
	int updateMySmsCnt(String userId)  throws Exception;
	
	
	/**
     * SMS HIST를 저장한다.
     * @param  SMS 정보 
     * @return 저장 성공
     * @author crossent
     */
	String insertSmsSendHist(String json, HttpSession session) throws Exception;
	
	
	/**
     * SMS HIST를 저장한다.
     * @param  SMS 정보 
     * @return 저장 성공
     * @author crossent
     */
	String insertSmsSendHist(SmsSendHistVO smsSendHist, HttpSession session) throws Exception;
	
	
	
	
	/**
	 * 사용자가 지정한 공지사항 목록
	 * @param 
	 * @return  사용자가 지정한 공지사항 목록
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserBoardSetVO> getPsnUserNotiList(String userId) throws Exception;
	
	
	
	/**
	 * 사용자가 지정한 게시판 목록
	 * @param 
	 * @return  사용자가 지정한 게시판 목록
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserCncrBoardSetVO> getPsnUserCncrSetBoardList(String userId) throws Exception;
	
	/**
	 * 사용자가 지정한 알림 목록
	 * @param 
	 * @return  사용자가 지정한 알림 목록
	 * @exception Exception
	 * @author crossent
	 */
	List<PsnUserTmlnAlertSetVO> getPsnUserTmlnAlertList(String userId) throws Exception;
	
	
	
	/**
	 * 알림관리 컨텐츠 목록
	 * @param 
	 * @return  알림관리 컨텐츠 목록
	 * @exception Exception
	 * @author crossent
	 */
	List<ComCntsInfoVO> getComCntsInfoList() throws Exception;
	
	
	/**
	 * 지정가능한 공지 ,게시판 숫자
	 * @param 
	 * @return  지정가능한 공지 ,게시판 숫자
	 * @exception Exception
	 * @author crossent
	 */
	AdmTmlnEnvSetVO getAdmTmlnEnvSet() throws Exception;
	
	
	/**
     * 사용자공지사항게시판설정
     * @param  관심게시판 정보
     * @return 저장 성공
     * @author crossent
     */
	String insertPsnUserNotiSet(String json, HttpSession session) throws Exception;
	
	
	
	/**
     * 사용자 관심 게시판을 설정한다.
     * @param  관심게시판 정보
     * @return 저장 성공
     * @author crossent
     */
	String insertPsnUserCncrBoardSet(String json, HttpSession session) throws Exception;
	
	
	/**
     * 사용자 관심 알림을 설정한다.
     * @param  관심알림 정보
     * @return 저장 성공
     * @author crossent
     */
	String insertPsnUserTmlnAlertSet(String json, HttpSession session) throws Exception;
	
	/**
     * 사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */  
    List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRoll(String potoSeq, HttpSession session) throws Exception; 
    
    /**
     * 타사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */  
    List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRollEtc(String userId, String potoSeq) throws Exception; 
    
	/**
	 * 사용자부서정보  - DB
	 * @param userId -  사용자ID
	 * @return ComUserDeptInfoVO - 사용자부서정보
	 * @exception Exception
	 */
    ComUserDeptInfoVO getComUserDeptInfoList(String userId) throws Exception;

    /**
     * 타부서정보  - DB
     * @param userId -  사용자ID
     * @return ComUserDeptInfoVO - 사용자부서정보
     * @exception Exception
     */
    ComUserDeptInfoVO getComUserDeptDetailInfo(String oucode) throws Exception;
    
    /**
     * 사용자부서정보 - ldap
     * @param oucode - 부서코드
     * @return ComUserDeptInfoVO - 사용자부서정보
     * @exception Exception
     */
    ComUserDeptInfoVO getComUserLdDeptInfoList(String oucode) throws Exception;
    
    /**
     * 직원검색에서 관심직원 추가 중복체크
     * @param pfVO
     * @return
     * @throws Exception
     * @author crossent
     */
    int favoriteUserDuplicationCheck(PsnUserFrdInfoVO pfVO) throws Exception;
    
    /**
     * 직원검색에서 관심직원 추가
     * @param json
     * @param userVO
     * @return
     * @throws Exception
     * @author crossent
     */
    String setFavoriteUserAdd(String json, UserInfoVO userVO) throws Exception;
    
    /**
     * 부서홍보글 존재여부 조회
     * @param oucode
     * @return int
     * @throws Exception
     */
    int selectUserBuseoHongbo(String oucode) throws Exception;
    
    /**
     * 부서홍보글 추가
     * @param oucode
     * @return int
     * @throws Exception
     */
    int insertUserBuseoHongbo(String oucode, String content, HttpSession session) throws Exception;
    
    /**
     * 부서홍보글 수정
     * @param oucode
     * @return int
     * @throws Exception
     */
    int updateUserBuseoHongbo(String oucode, String content, HttpSession session) throws Exception;
    
	/**
	 * 타임라인 환경설정 화면
	 * @param TmlnEnvInfoVO
	 * @return TmlnEnvInfoVO
	 * @exception Exception
	 */
	public TmlnEnvInfoVO getTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO) throws Exception;
	
	/**
	 * 타임라인 환경설정 수정
	 * @param TmlnEnvInfoVO
	 * @return TmlnEnvInfoVO
	 * @exception Exception
	 */
    public void updateTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO, HttpSession session) throws Exception;    
}
