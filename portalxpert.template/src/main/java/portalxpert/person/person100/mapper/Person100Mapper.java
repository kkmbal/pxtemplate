package portalxpert.person.person100.mapper;

import java.util.List;

import portalxpert.person.person100.vo.AdmTmlnEnvSetVO;
import portalxpert.person.person100.vo.ComCntsInfoVO;
import portalxpert.person.person100.vo.ComUserDeptInfoVO;
import portalxpert.person.person100.vo.ComUserInfoVO;
import portalxpert.person.person100.vo.ComUserPotoInfoVO;
import portalxpert.person.person100.vo.MesUserCountsVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.person.person100.vo.PbsUserBoardPartInfoVO;
import portalxpert.person.person100.vo.PsnUserBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrBoardSetVO;
import portalxpert.person.person100.vo.PsnUserCncrTagVO;
import portalxpert.person.person100.vo.PsnUserFrdInfoVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person100.vo.PsnUserTmlnAlertSetVO;
import portalxpert.person.person100.vo.SmsSendHistVO;
import portalxpert.person.person100.vo.TmlnEnvInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("person100Mapper")
public interface Person100Mapper  {

	/**
     * PBS 사용자 게시판 참여 등록 
     * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 VO
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPbsUserBoardPartInfo(PbsUserBoardPartInfoVO vo) throws Exception  ;
    
    /**
     * PBS 사용자 게시판 등록
     * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPbsList(PbsUserBoardInfoVO vo) throws Exception  ;
    
    /**
	 * 사용자 정보 수정 (업무분장, 닉네님, 선택이미지)
	 * @param ComUserInfoVO - 조회할 정보가 담긴 VO
	 * @return void
	 * @exception Exception
	 */
    public void updatePsnUserInfo(ComUserInfoVO vo) throws Exception  ;
    
    /**
     * 사용자 정보 수정 (대표이미지 삭제)
     * @param ComUserPotoInfoVO - 조회할 정보가 담긴 VO
     * @return 삭제 성공 건수
	 * @exception Exception
     */
    public int deleteComUserPotoInfo(ComUserPotoInfoVO vo) throws Exception  ;
    
    /**
     * 사용자 정보 수정 (대표이미지)
     * @param ComUserPotoInfoVO - 조회할 정보가 담긴 VO
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertComUserPotoInfo(ComUserPotoInfoVO vo) throws Exception  ;
    
    
	/**
	 * 사용자정보 
	 * @param comUserInfoVO - 조회할 정보가 담긴 VO
	 * @return 사용자정보
	 * @exception Exception
	 */
    public List getComUserInfoList(ComUserInfoVO vo);
	
	/**
	 * 사용자 이미지 정보 조회
	 * @param vo
	 * @return 사용자 이미지 정보
	 */
    public List getComUserPotoInfoList(ComUserPotoInfoVO vo);
	
	


	/**
	 * MY게시판 조회
	 * @param MesUserCountsVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회 
	 * @exception Exception
	 */
    public List getPbsUserBoardInfoList(PbsUserBoardInfoVO vo);
	
	/**
	 * MY게시판 조회 ZTREE
	 * @param MesUserCountsVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회 ZTREE
	 * @exception Exception
	 */
    public List getPbsUserBoardInfoListForZTree(PbsUserBoardInfoVO vo);

	/**
	 * MY게시판 조회 ZTREE
	 * @param MesUserCountsVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회 ZTREE
	 * @exception Exception
	 */
    public List getPersonLnbPartList(PbsUserBoardInfoVO vo);
    
    /**
	 * MY게시판 조회 ZTREE
	 * @param MesUserCountsVO - 조회할 정보가 담긴 VO
	 * @return MY게시판 조회 ZTREE
	 * @exception Exception
	 */
    public List getPersonLnbFavoList(PbsUserBoardInfoVO vo);
	
	/**
	 * 개인정보관리 SMS발송이력
	 * @param smsSendHistVO
	 * @return SmsSendHistVO - 개인정보관리 SMS발송이력
	 * @throws Exception
	 */
    public List getSmsSendHistList(SmsSendHistVO vo);
	
	/**
	 * 개인정보관리 SMS발송이력 총 개수
	 * @param SmsSendHistVO - 조회할 정보가 담긴 VO
	 * @return 총 개수
	 * @exception Exception
	 */
    public int getSmsSendHistListTotCnt(SmsSendHistVO vo);
	
    /**
     * SMS발송이력 삭제
     * @param  SmsSendHistVO
     * @return 삭제 성공
     */
    public int deleteSmsSendHist(SmsSendHistVO smsSendHistVO) throws Exception  ;
    
    /**
     * 관심태그 등록
     * @param PsnUserCncrTagVO - 조회할 정보가 담긴 VO
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPsnUserCncrTag(PsnUserCncrTagVO vo) throws Exception  ;
    
    /**
     * 관심태그 삭제
     * @param  PsnUserFrdInfoVO
     * @return 삭제 성공 건수
     */
    public int deletePsnUserCncrTagList(String userId) throws Exception  ;
    
    /**
	 * 관심태그 조회
	 * @param PsnUserCncrTagVO
	 * @return PsnUserCncrTagVO - 관심태그 정보
	 * @throws Exception
	 */
    public List getPsnUserCncrTagList(PsnUserCncrTagVO vo);
    
    
    /**
     * 나의 관심직원 등록
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int insertPsnUserFriendInfo(PsnUserFrdInfoVO vo) throws Exception  ;
    
    /**
     * 나의 관심직원 삭제
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int deletePsnUserFriendInfo(PsnUserFrdInfoVO vo) throws Exception  ;
    
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
     * 나의 그룹 별 멤버 등록
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int insertPsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo) throws Exception  ;
    
    /**
     * 나의 그룹  별 멤버 삭제
     * @param  PsnUserFrdInfoVO
     * @return 입력 성공 건수
     */
    public int deletePsnUserGrpFriendMap(String userId) throws Exception  ;
    
    
    /**
	 *  나의 관심직원 조회 
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return  나의 관심직원 정보 
	 * @exception Exception
	 */
    List<PsnUserFrdInfoVO> getPsnUserFriendInfo(PsnUserFrdInfoVO vo);
	
    
    /**
	 *  나의 관심직원 조회 건수
	 * @param userId - 로그인 아이디
	 * @return  나의 관심직원 정보 
	 * @exception Exception
	 */
    int getPsnUserFriendInfoCnt(String userId);
    
    
    /**
	 *  나의 My Staff 건수
	 * @param userId - 로그인 아이디
	 * @return  나의 관심직원 정보 
	 * @exception Exception
	 */
    int getPsnUserOVInfoCnt(String userId);
    
    
    /**
	 * 나를 추가한 직원 건수
	 * @param userId - 로그인 아이디
	 * @return  나의 관심직원 정보 
	 * @exception Exception
	 */
    int getStaffFriendInfoCnt(String userId);
    
    
    
    /**
	 *  나의 부서직원 
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return  나의 부서직원 정보 
	 * @exception Exception
	 */
    List<PsnUserFrdInfoVO> getPsnUserOVInfo(PsnUserFrdInfoVO vo);
    
    /**
	 * 내게 관심있는 직원 조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return  나의 부서직원 정보 
	 * @exception Exception
	 */
    List<PsnUserFrdInfoVO> getPsnInterestedUser(PsnUserFrdInfoVO vo);
    
    /**
	 * 나의 직원 상세  조회
	 * @param PsnUserFrdInfoVO - 조회할 정보가 담긴 VO
	 * @return 나의 직원 상세  정보 
	 * @exception Exception
	 */
    PsnUserFrdInfoVO getPsnUserDetailInfo(PsnUserFrdInfoVO vo);

    /**
	 *  나의 그룹 조회 
	 * @param PsnUserGrpInfoVO - 조회할 정보가 담긴 VO
	 * @return  나의 그룹리스트 
	 * @exception Exception
	 */
    List<PsnUserGrpInfoVO> getPsnUserGrpInfo(PsnUserGrpInfoVO vo);
    
    /**
	 *  나의 그룹별  멤버조회 
	 * @param PsnUserGrpFrdMapVO - 조회할 정보가 담긴 VO
	 * @return  나의 그룹리스트 
	 * @exception Exception
	 */
    List<PsnUserGrpFrdMapVO> getPsnUserGrpFriendMap(PsnUserGrpFrdMapVO vo);
    
    /**
	 *  전송할 수 있는 SMS 건수
	 * @param 
	 * @return   
	 * @exception Exception
	 */
	String getMySmsCnt(String userId);
    
    /**
	 *  전송할 수 있는 총 SMS 건수
	 * @param 
	 * @return   
	 * @exception Exception
	 */
	String getMySmsAllCnt(String userId);
    
    /**
     * SMS 수정 건수 
     * @param 유저아이디
     * @return 성공 건수
     * @throws Exception
     */
    public int updateMySmsCnt(String userId) ;
    
    /**
     * SMS 히스토리 저장
     * @param vo SMS정보
     * @return 입력 성공 건수
     */
    public int insertSmsSendHist(SmsSendHistVO smsSendHist) throws Exception  ;
    
	/**
	 * 설정된 공지사항 게시판 갯수
	 * @param 
	 * @return   
	 * @exception Exception
	 */
	int getPsnUserBoardCnt(String userId);
    
	/**
	 * 설정된 공지사항 게시판 리스트
	 * @param 
	 * @return   
	 * @exception Exception
	 */
	 List<PsnUserBoardSetVO> getPsnUserNotiList(String userId);
    
	 /**
	 * 공지사항 디폴트 게시판 리스트
	 * @param 
	 * @return   
	 * @exception Exception
	 */
	 List<PsnUserBoardSetVO> getComStandBoardList(String userId);
    
    /**
	 * 사용자가 지정한 게시판 목록
	 * @param 
	 * @return 사용자가 지정한 게시판 목록
	 * @exception 
	 */  
    List<PsnUserCncrBoardSetVO> getPsnUserCncrSetBoardList(String userId);
    
    
    /**
	 * 사용자가 지정한 알림 목록
	 * @param 
	 * @return 사용자가 지정한 알림 목록
	 * @exception 
	 */  
    List<PsnUserTmlnAlertSetVO> getPsnUserTmlnAlertList(String userId);
    
    /**
	 * 알림관리 컨텐츠 목록
	 * @param 
	 * @return  알림관리 컨텐츠 목록
	 * @exception 
	 */  
    List<ComCntsInfoVO> getComCntsInfoList();
    
    /**
	 *  지정가능한 공지 ,게시만 숫자
	 * @param 
	 * @return  지정가능한 공지 ,게시만판숫자
	 * @exception 
	 */   
    AdmTmlnEnvSetVO getAdmTmlnEnvSet();
    
    /**
     * 사용자 관심 게시판을 설정한다.
     * @param 관심 게시판 설정 정보
     * @return 입력 성공 건수
     */
    public int insertPsnUserCncrBoardSet(PsnUserCncrBoardSetVO vo) throws Exception  ;
    
    /**
     * 사용자 관심 게시판 설정 정보를 삭제한다..
     * @param 로그인 아이디
     * @return 삭제 성공 건수
     */
    
    public int deletePsnUserCncrBoardSet(String userId) throws Exception ;
    
    /**
     * 사용자 관심 알림을 설정한다.
     * @param 관심 게시판 설정 정보
     * @return 입력 성공 건수
     */
    public int insertPsnUserTmlnAlertSet(PsnUserTmlnAlertSetVO vo) throws Exception  ;
    
    /**
     * 사용자 관심 알림 설정 정보를 삭제한다..
     * @param 로그인 아이디
     * @return 삭제 성공 건수
     */
    public int deletePsnUserTmlnAlertSet(String userId) throws Exception ;
    
    /**
     * 사용자공지사항게시판설정
     * @param 관심 게시판 설정 정보
     * @return 입력 성공 건수
     */
    public int insertPsnUserNotiSet(PsnUserBoardSetVO vo) throws Exception  ;
    
    /**
     * 사용자공지사항게시판설정 정보를 삭제한다..
     * @param 로그인 아이디
     * @return 삭제 성공 건수
     */
    public int deletePsnUserNotiSet(String userId) throws Exception ;
    
    /**
     * 사용자 이미지 롤링
     * @param modelMap
     * @return modelMap
     * @throws Exception
     */
    public List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRoll(ComUserPotoInfoVO vo)throws Exception ;

    /**
     * 사용자부서정보 조회-DB
     * @param userId
     * @return ComUserDeptInfoVO
     * @throws Exception
     */
    public ComUserDeptInfoVO getComUserDeptInfoList(String userId) throws Exception ;
    
    /**
     * 타부서정보 조회-DB
     * @param oucode
     * @return ComUserDeptInfoVO
     * @throws Exception
     */
    public ComUserDeptInfoVO getComUserDeptDetailInfo(String oucode) throws Exception ;
    
    /**
     * 직원검색에서 관심직원 추가 중복 체크
     * @param pfVO
     * @return
     * @throws Exception
     * @author crossent
     */
    public int favoriteUserDuplicationCheck(PsnUserFrdInfoVO pfVO) throws Exception;
    
    /**
     * 부서홍보글 존재여부 조회
     * @param oucode
     * @return
     * @throws Exception
     */
    public int selectUserBuseoHongbo(String oucode) throws Exception;
    
    /**
     * 부서홍보글 추가
     * @param comUserDeptInfoVO
     * @return
     * @throws Exception
     */
    public int insertUserBuseoHongbo(ComUserDeptInfoVO comUserDeptInfoVO) throws Exception;
    
    /**
     * 부서홍보글 수정
     * @param comUserDeptInfoVO
     * @return
     * @throws Exception
     */
    public int updateUserBuseoHongbo(ComUserDeptInfoVO comUserDeptInfoVO) throws Exception;
    
	/**
	 * 타사용자 대표이미지조회
	 * @param userId
	 * @param potoSeq
	 * @return
	 * @throws Exception
	 */
    public List<ComUserPotoInfoVO> getPsnUserPotoInfoListForRollEtc(ComUserPotoInfoVO vo) throws Exception;

	/**
	 * 사용자 조회
	 * @param ComUserInfoVO
	 * @return
	 * @throws Exception
	 */
	public int getPsnUserInfo(ComUserInfoVO comUserInfoVO);

	/**
	 * 사용자 저장
	 * @param ComUserInfoVO
	 * @return
	 * @throws Exception
	 */
	public void insertPsnUserInfo(ComUserInfoVO comUserInfoVO);


	/**
	 * 타임라인 환경설정 화면
	 * @param TmlnEnvInfoVO
	 * @return TmlnEnvInfoVO
	 * @throws Exception
	 */
	 public TmlnEnvInfoVO getTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO) throws Exception;
    
    /**
	 * 타임라인 환경설정 수정
	 * @param TmlnEnvInfoVO
	 * @return 
	 * @exception Exception
	 */
    public void updateTmlnEnv(TmlnEnvInfoVO tmlnEnvInfoVO) throws Exception  ;
	
}
