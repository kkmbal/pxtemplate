package portalxpert.board.board220.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board220.mapper.Board220Mapper;
import portalxpert.board.board220.sc.Board220Service;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("board220Service")
public class Board220ServiceImpl extends AbstractServiceImpl implements Board220Service {

    /** board220Mapper */
    @Resource(name="board220Mapper")
    private Board220Mapper board220Mapper;
    
	/** board100Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;
	
	/**
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */  
	public List<BbsNotiInfoVO> getBbsNotiInfoListForTmln(BoardSearchVO vo) throws Exception {
		try{
			return board220Mapper.getBbsNotiInfoListForTmln(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

    /**
	 * BBS 게시물 의견
	 * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnListForTmln(BbsNotiOpnVO vo)throws Exception {
    	try{
    		return board220Mapper.getBbsNotiOpnListForTmln(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * BBS 첨부
     * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
     * @return BBS 게시물 첨부
     * @exception Exception
     * @auther crossent 
     */
    public List<BbsNotiApndFileVO> getBbsNotiApndListForTmln(BbsNotiApndFileVO vo)throws Exception {
    	try{
    		return board220Mapper.getBbsNotiApndListForTmln(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시물수정
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO updateBbsNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception{
    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			JSONObject bbsNotiObject = JSONObject.fromObject(json);
			vo.setBoardId((String)bbsNotiObject.get("boardId")) ;
			vo.setNotiId((String)bbsNotiObject.get("notiId")) ;
			vo.setNotiConts((String)bbsNotiObject.get("notiConts")) ;
			vo.setUpdrId(info.getId()) ;
			vo.setUpdrName(info.getName()) ;
			vo.setIsAdmin("");
			
			board100Mapper.updateBbsNotiInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    	
}
