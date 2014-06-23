package portalxpert.board.board312.mapper;

import java.util.List;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board211.vo.BbsImgBoardNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board312Mapper")
public interface Board312Mapper {
	
	
	
	/**
	 * 권한구분 조회
	 * @param 
	 * @return 권한구분 조회
	 * @exception Exception
	 * @author crossent
	 */
	String getBbsImgBoardAuthFlag(String boardId);
	
	
	
	/**
	 *  이미지 게시판 (게시글 권한 포함 ) 리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 
	 * @exception Exception
	 */
    List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthNotiList(BbsImgBoardNotiInfoVO vo);
	
    /**
	 *  이미지 게시판 (게시글 권한 포함 )리스트 건수
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 건수
	 * @exception Exception
	 */
    int getBbsImgBoardAuthNotiListCnt(BbsImgBoardNotiInfoVO vo);
	
    
    
    /**
	 *  이미지 게시판 (게시판 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 
	 * @exception Exception
	 */
    List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthBoardList(BbsImgBoardNotiInfoVO vo);
	
    /**
	 *  이미지 게시판 (게시판 권한 포함 )리스트 건수
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 건수
	 * @exception Exception
	 */
    int getBbsImgBoardAuthBoardListCnt(BbsImgBoardNotiInfoVO vo);
    
    /**
	 * 이미지 게시판  게시물 삭제
	 * @param 
	 * @return  이미지 게시판  게시물 삭제
	 * @throws Exception
	 * @author crossent
	 */   
    public void updateImgNotiInfo(String notiId) throws Exception  ;
    
   
    
    
    /**
	 * 공지 리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  게시판별 공지 리스트 
	 * @exception Exception
	 */
    List<BbsImgBoardNotiInfoVO> getBbsNotiList(String boardId);
	
    
    /**
	 * 게시물 정보 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo)throws Exception ;
    
    /**
	 * 게시물 총 개수 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception Exception
	 * @auther crossent 
	 */
    public int getBbsNotiInfoListTotCnt(BoardSearchVO vo)throws Exception;
    
    /**
	 * 게시물 정보 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo)throws Exception ;
    
    /**
	 * 게시물 총 개수 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception Exception
	 * @auther crossent 
	 */
    public int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo)throws Exception;

    
    
}
