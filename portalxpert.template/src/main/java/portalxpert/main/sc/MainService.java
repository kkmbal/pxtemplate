package portalxpert.main.sc;

import java.util.List;

import portalxpert.common.vo.UserInfoVO;
import portalxpert.main.vo.MainVO;

public interface MainService {
	
	
	/**
	 * 최근게시물 - 전체공지 조회
	 * @return
	 * @exception Exception
	 * @author crossent 
	 */
	List getTotalNoticeList(MainVO mainVO) throws Exception;
	
	/**
	 * 게시물 조회
	 * @return
	 * @exception Exception
	 * @author crossent	 
	 */
	List getBoardList(MainVO mainVO) throws Exception;
	
	/**
	 * 최근게시물 - 게시물 preview
	 * @return
	 * @exception Exception
	 * @author crossent	 
	 */
	MainVO getNoticePreview(MainVO mainVO) throws Exception;
	
	/**
	 * 최근게시물 설정- 설정 게시판 조회
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	List getUserBoardList(UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근게시물 설정- 사용자 설정 게시판 건수
	 * @param userVO
	 * @return
	 * @throws Exception
	 */
	int getUserBoardSetCnt(UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근게시물 설정 - 사용자 설정
	 * @param json
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	String setSelectedBoard(String json, UserInfoVO userVO) throws Exception;
	
	/**
	 * 게시물 평가 정보 건수
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	int getBbsNotiEvalInfoCnt(MainVO mainVO) throws Exception;
	
	/**
	 * 최근게시물 프리뷰 시 읽음처리
	 * @param mainVO
	 * @throws Exception
	 * @author crossent	 
	 */
	String setBbsNotiInfoReadCnt(MainVO mainVO) throws Exception;
	
	/**
	 * 관심시스템 조회
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	List getTagCloudList(MainVO mainVO) throws Exception;
	
	/**
	 * 업무데스크 - 사용자 설정 조회
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	List getUserWorkDeskList(UserInfoVO userVO) throws Exception;
	
	/**
	 * 업무데스크 사용자 카운트
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	int getUserWorkDeskCnt(UserInfoVO userVO) throws Exception;
	
	/**
	 * 업무데스크 기본 카운트
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	int getDefaultWorkDeskCnt() throws Exception;
	
	/**
	 * 업무데스크 - 사용자 설정
	 * @param json
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	String setUserWorkDesk(String json, UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근 동영상/이미지
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	List getRecentVodImageList() throws Exception;
	
	/**
	 * 많이 본 동영상/이미지
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	List getMostViewedVodImageList() throws Exception;
	
	/**
	 * 공지게시 팝업 조회
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	List getNotiPopup() throws Exception;
	
	
	/**
	 * 공지게시 팝업 카운트
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	int getNotiPopupCnt() throws Exception;
	
	
}