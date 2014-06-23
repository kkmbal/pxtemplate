package portalxpert.board.board312.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board211.vo.BbsImgBoardNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;


public interface Board312Service 
{
	

	/**
	 * 권한구분 조회
	 * @param 
	 * @return 권한구분 조회
	 * @exception Exception
	 * @author crossent
	 */
	String getBbsImgBoardAuthFlag(String boardId) throws Exception;
	
	
	/**
	 * 이미지 게시판  ( 권한 포함 ) 리스트 
	 * @param BbsImgBoardNotiInfoVO -  조회할 정보가 담긴 VO
	 * @return 이미지 게시판 리스트 
	 * @exception Exception
	 * @author crossent
	 */
	List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthNotiList(BbsImgBoardNotiInfoVO vo) throws Exception;
	
	
	/**
	 * 이미지 게시판 (게시글 권한 포함 ) 리스트 건수
	 * @param BbsImgBoardNotiInfoVO -  조회할 정보가 담긴 VO
	 * @return 이미지 게시판 리스트 건수
	 * @exception Exception
	 * @author crossent
	 */
	int getBbsImgBoardAuthNotiListCnt(BbsImgBoardNotiInfoVO vo) throws Exception;
	
	
	/**
	 * 이미지 게시판 (게시판 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO -  조회할 정보가 담긴 VO
	 * @return 이미지 게시판 리스트 
	 * @exception Exception
	 * @author crossent
	 */
	List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthBoardList(BbsImgBoardNotiInfoVO vo) throws Exception;
	
	
	/**
	 * 이미지 게시판 (게시판 권한 포함 ) 리스트 건수
	 * @param BbsImgBoardNotiInfoVO -  조회할 정보가 담긴 VO
	 * @return 이미지 게시판 리스트 건수
	 * @exception Exception
	 * @author crossent
	 */
	int getBbsImgBoardAuthBoardListCnt(BbsImgBoardNotiInfoVO vo) throws Exception;
	
	
	/**
	 * 이미지 리스트를 삭제한다.
	 * @param 
	 * @return 삭제 건수
	 * @exception Exception
	 * @author crossent
	 */
	String updateImgNotiInfo(String json,HttpSession session) throws Exception;
	
	/**
	 * 공지 리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  게시판별 공지 리스트 
	 * @exception Exception
	 * @author crossent
	 */
	List<BbsImgBoardNotiInfoVO> getBbsNotiList(String boardId) throws Exception;
	
	
	/**
	 * 페이지별 게시물 정보 조회(이미지형)
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception;
    
    /**
	 * 게시물 정보 조회 카운트(이미지)
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    int getBbsNotiInfoListTotCnt(BoardSearchVO vo) throws Exception;
    
    /**
	 * 페이지별 게시물 정보 조회(이미지)
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo) throws Exception;
    
    /**
	 * 게시물 정보 조회(이미지)
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo) throws Exception;

}
