package portalxpert.board.board311.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board211.vo.BbsImgBoardNotiInfoVO;
import portalxpert.board.board311.mapper.Board311Mapper;
import portalxpert.board.board311.sc.Board311Service;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;


@Service("board311Service")
public class Board311ServiceImpl extends EgovAbstractServiceImpl implements  Board311Service {
	
	/** person100Mapper */
    @Resource(name="board311Mapper")
    private Board311Mapper board311Mapper;
	
	private final static Logger logger = LoggerFactory.getLogger(Board311ServiceImpl.class); 
	
	
	/**
	 * 권한구분 조회
	 * @param 
	 * @return 권한구분 조회
	 * @exception Exception
	 * @author crossent
	 */
	public String getBbsImgBoardAuthFlag(String boardId) throws Exception
    {
		try{
			return board311Mapper.getBbsImgBoardAuthFlag(boardId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	
	
	
	 /**
	 * 이미지 게시판  (게시글 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO
	 * @return BbsImgBoardNotiInfoVO - 이미지 게시판 리스트
	 * @throws Exception
	 *  @author crossent
	 */   
    public List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthNotiList(BbsImgBoardNotiInfoVO vo) throws Exception
    {
    	try{
    		return board311Mapper.getBbsImgBoardAuthNotiList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
        
    }
	
    
    /**
	 * 이미지 게시판  (게시글 권한 포함 )리스트 건수
	 * @param BbsImgBoardNotiInfoVO
	 * @return  이미지 게시판 리스트 건수
	 * @throws Exception
	 * @author crossent
	 */   
    public int getBbsImgBoardAuthNotiListCnt(BbsImgBoardNotiInfoVO vo) throws Exception
    {
    	try{
    		return board311Mapper.getBbsImgBoardAuthNotiListCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
        
    }
    
    
	 /**
	 * 이미지 게시판  (게시판 권한 포함 )리스트 
	 * @param BbsImgBoardNotiInfoVO
	 * @return BbsImgBoardNotiInfoVO - 이미지 게시판 리스트
	 * @throws Exception
	 *  @author crossent
	 */   
    public List<BbsImgBoardNotiInfoVO> getBbsImgBoardAuthBoardList(BbsImgBoardNotiInfoVO vo) throws Exception
    {
    	try{
    		return board311Mapper.getBbsImgBoardAuthBoardList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
    
    /**
	 * 이미지 게시판  (게시판 권한 포함 ) 리스트 건수
	 * @param BbsImgBoardNotiInfoVO
	 * @return  이미지 게시판 리스트 건수
	 * @throws Exception
	 * @author crossent
	 */   
    public int getBbsImgBoardAuthBoardListCnt(BbsImgBoardNotiInfoVO vo) throws Exception
    {
    	try{
    		return board311Mapper.getBbsImgBoardAuthNotiListCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
 
    /**
	 * 이미지 게시판  게시물 삭제
	 * @param 
	 * @return  이미지 게시판  게시물 삭제
	 * @throws Exception
	 * @author crossent
	 */   
    public String updateImgNotiInfo(String json, HttpSession session) throws Exception {
    
    
    	try {
			
			JSONObject jsonBoard = JSONObject.fromObject(json);
		
			JSONArray jsonArr = (JSONArray)jsonBoard.get("removeNotiInfo");
			
			logger.debug("jsonArr.size() : " + jsonArr.size());
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				
				logger.debug("notiId : "  +  (String)obj.get("notiId"));
				board311Mapper.updateImgNotiInfo((String)obj.get("notiId"));
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    	
    	
    }
    
    
    
    
    
    /**
	 * 공지 리스트 
	 * @param BbsImgBoardNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return  게시판별 공지 리스트 
	 * @exception Exception
	 * @author crossent
	 */  
    public List<BbsImgBoardNotiInfoVO> getBbsNotiList(String boardId) throws Exception
    {
    	try{
    		return board311Mapper.getBbsNotiList(boardId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
        
    }
    
    /**
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception {
    	try{
    		return board311Mapper.getBbsNotiInfoListForPaging(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getBbsNotiInfoListTotCnt(BoardSearchVO vo)throws Exception  {
    	try{
    		return board311Mapper.getBbsNotiInfoListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 페이지별 게시물 정보 조회(이미지) 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo) throws Exception {
    	try{
    		return board311Mapper.getPbsNotiUserInfoListForPaging(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시물 리스트 총 갯수(이미지)
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시물 총 갯수
	 * @exception
	 */
    public int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo)throws Exception  {
    	try{
    		return board311Mapper.getPbsUserNotiInfoListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
}
