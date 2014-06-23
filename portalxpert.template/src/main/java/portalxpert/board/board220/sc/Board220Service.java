package portalxpert.board.board220.sc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.common.vo.BoardSearchVO;

public interface Board220Service {

	/**
	 * 페이지별 게시물 정보 조회
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보
	 * @exception Exception
	 */
    List<BbsNotiInfoVO> getBbsNotiInfoListForTmln(BoardSearchVO vo) throws Exception;	
    
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
    
    /**
     * 게시물수정
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO updateBbsNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception;
    	
	
}
