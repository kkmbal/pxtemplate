package portalxpert.main.mapper;

import java.util.List;

import portalxpert.common.vo.UserInfoVO;
import portalxpert.main.vo.MainVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("mainMapper")
public interface MainMapper {

	
	/**
	 * 최근게시물 - 전체공지 조회
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public List getTotalNoticeList(MainVO mainVO) throws Exception;
	
	/**
	 * 게시물 조회
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public List getBoardList(MainVO mainVO) throws Exception;
	
	/**
	 * 최근게시물 - 게시물 preview
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public MainVO getNoticePreview(MainVO mainVO) throws Exception;
	
	/**
	 * 최근게시물 설정- 설정 게시판 조회
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public List getUserBoardList(UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근게시물 설정- 사용자 설정 게시판 건수
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public int getUserBoardSetCnt(UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근게시물 설정 - 사용자 설정 삭제
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public void deleteUserBoardSet(UserInfoVO userVO) throws Exception;
	
	/**
	 * 최근게시물 설정 - 사용자 설정 저장
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public void insertUserBoardSet(MainVO mainVO) throws Exception;
	
	/**
	 * 게시물 평가 정보 읽음 건수
	 * @param mainVO
	 * @return
	 * @throws Exception
	 */
	public int getBbsNotiEvalInfoCnt(MainVO mainVO) throws Exception;
	
	/**
	 * 게시물 평가 정보 읽음 저장
	 * @param mainVO
	 * @throws Exception
	 * @author crossent	 
	 */
	public void insertBbsNotiEvalInfo(MainVO mainVO) throws Exception;
	
	/**
	 * 게시물 읽음 카운트 증가
	 * @param mainVO
	 * @throws Exception
	 */
	public void updateBbsNotiInfoReadCnt(MainVO mainVO) throws Exception;
	
	/**
	 * 관심시스템 조회
	 * @return mainVO
	 * @exception Exception
	 * @author crossent
	 */
	public List getTagCloudList(MainVO mainVO) throws Exception;
	
	/**
	 * 업무데스크 - 사용자 설정 조회
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public List getUserWorkDeskList(UserInfoVO userVO) throws Exception;
	
	/**
	 * 업무데스크 사용자 카운트
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public int getUserWorkDeskCnt(UserInfoVO userVO) throws Exception;
	
	/**
	 * 업무데스크 기본 카운트
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public int getDefaultWorkDeskCnt() throws Exception;
	
	/**
	 * 업무데스크 - 사용자 설정 삭제
	 * @param userVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public void deleteUserWorkDesk(UserInfoVO userVO) throws Exception;
	
	/**
	 * 업무데스크 - 사용자 설정 저장
	 * @param mainVO
	 * @return
	 * @throws Exception
	 * @author crossent	 
	 */
	public void insertUserWorkDesk(MainVO mainVO) throws Exception;
	
	/**
	 * 최근 동영상/이미지
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public List getRecentVodImageList() throws Exception;
	
	/**
	 * 많이 본 동영상/이미지
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public List getMostViewedVodImageList() throws Exception;
	
	/**
	 * 공지게시 팝업 조회
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public List getNotiPopup() throws Exception;
	

	
	/**
	 * 공지게시 팝업 카운트
	 * @return
	 * @throws Exception
	 * @author crossent
	 */
	public int getNotiPopupCnt() throws Exception;
	
	
}