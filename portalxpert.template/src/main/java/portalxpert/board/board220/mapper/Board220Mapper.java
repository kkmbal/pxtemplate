package portalxpert.board.board220.mapper;

import java.util.List;

import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @author crossent
 *
 */
@Mapper("board220Mapper")
public interface Board220Mapper {

	/**
	 * 게시물 정보 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoListForTmln(BoardSearchVO vo)throws Exception ;
    
    /**
	 * BBS 게시물 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnListForTmln(BbsNotiOpnVO vo)throws Exception ;
    
    /**
     * BBS 첨부
     * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
     * @return BBS 게시물 첨부
     * @exception Exception
     * @auther crossent 
     */
    public List<BbsNotiApndFileVO> getBbsNotiApndListForTmln(BbsNotiApndFileVO vo)throws Exception ;
	
}
