package portalxpert.board.board213.mapper;

import java.util.List;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board211.vo.BbsImgBoardNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board213Mapper")
public interface Board213Mapper {
	
	
	
	
    
    /**
	 *  Contents 게시판 (게시판 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 
	 * @exception Exception
	 */
    List<BbsImgBoardNotiInfoVO> getBbsContentsBoardAuthBoardList(BbsImgBoardNotiInfoVO vo);
	
    /**
	 *  Contents 게시판 (게시판 권한 포함 )리스트 건수
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 건수
	 * @exception Exception
	 */
    int getBbsContentsBoardAuthBoardListCnt(BbsImgBoardNotiInfoVO vo);
    
    
    
    /**
	 *  Contents 게시판 (게시글 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 
	 * @exception Exception
	 */
    List<BbsImgBoardNotiInfoVO> getBbsContentsBoardAuthNotiList(BbsImgBoardNotiInfoVO vo);
	
    /**
	 *  Contents 게시판 (게시글 권한 포함 )리스트 건수
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  이미지 게시판 리스트 건수
	 * @exception Exception
	 */
    int getBbsContentsBoardAuthNotiListCnt(BbsImgBoardNotiInfoVO vo);
    
    
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
    
	
    
    
    
    
}
