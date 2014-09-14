package portalxpert.board.board210.sc.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiDelInfoVO;
import portalxpert.board.board100.vo.BbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.ComCodeSpecVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.board.board210.mapper.Board210Mapper;
import portalxpert.board.board210.sc.Board210Service;
import portalxpert.common.config.Constant;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;



@Service("board210Service")
public class Board210ServiceImpl extends EgovAbstractServiceImpl implements  Board210Service {
	
	/** board210Mapper */
    @Resource(name="board210Mapper")
    private Board210Mapper board210Mapper;
    
    /** board100Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
	private final static Logger logger = LoggerFactory.getLogger(Board210ServiceImpl.class); 

	/**
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception {
    	try{
    		return board210Mapper.getBbsNotiInfoListForPaging(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * yblee
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getBbsNotiInfoListForPagingNew(BoardSearchVO vo) throws Exception {
    	try{
    		return board210Mapper.getBbsNotiInfoListForPagingNew(vo);
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
    		return board210Mapper.getBbsNotiInfoListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * yblee
	 * 사용자 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getBbsNotiInfoListTotCntNew(BoardSearchVO vo)throws Exception  {
    	try{
    		return board210Mapper.getBbsNotiInfoListTotCntNew(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
	/**
	 * 게시물 목록 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getBbsNotiInfoList(BoardSearchVO vo) throws Exception {
    	try{
    		return board210Mapper.getBbsNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }    
    
    /**
     * 공용 게시판 게시글 삭제
     * @param json
     * @param session
     * @return String
     * @throws Exception
     * @auther crossent 
     */
    public String deleteNotiInfo(String json, HttpSession session) throws Exception {
    	
		try {
			
				JSONObject bbsObject = JSONObject.fromObject(json);
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				JSONArray jsonArr = (JSONArray)bbsObject.get("notiId");		
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					logger.debug("deleteNotiInfo noti_id : "+(String)obj.get("id"));
					list.add((String)obj.get("id"));//notiId
				}
				map.put("list", list);
				map.put("updrId", info.getId());
				map.put("updrName", info.getName());
				
				board100Mapper.deleteNotiInfo(map);//게시글 삭제
				board100Mapper.deleteBbsNotiAddItemForBoard(map);
				board100Mapper.deleteBbsNotiApndFileForBoard(map);
				board100Mapper.deleteBbsNotiEvalInfoForBoard(map);
				board100Mapper.deleteBbsNotiOpnForBoard(map);
				board100Mapper.deleteBbsNotiUserMapForBoard(map);
				board100Mapper.deleteBbsNotiSurveyForBoard(map);
				board100Mapper.deleteBbsNotiSurveyAnswForBoard(map);
				board100Mapper.deleteBbsNotiSurveyExmplForBoard(map);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
     * 나의 작성글 여부 확인 
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 게시판 총 갯수
     * @auther crossent 
     */
    public int getMyNotiCheckList(String json, HttpSession session)throws Exception  {
    	try{
	    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
	    	JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
	    	
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
			JSONArray jsonArr = (JSONArray)bbsObject.get("notiId");		
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>(); 
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				logger.debug("getMyNotiCheckList noti_id : "+(String)obj.get("id"));
				list.add((String)obj.get("id"));
				
			}
			map.put("list", list);
			map.put("regrId", info.getId());
			
	    	return board210Mapper.getMyNotiCheckList(map); 
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시판 즐겨찾기 여부
     * @param BoardSearchVO - 조회할 정보가 담긴 VO
     * @return 즐겨찻기 된 갯수
     * @auther crossent 
     */
    public int getMyBbsFavoriteYn(BoardSearchVO vo)throws Exception  {
    	try{
    		return board210Mapper.getMyBbsFavoriteYn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * @throws Exception 
	 * 즐겨찾기 추가
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception
	 */
    public int insertBbsFavorite(String json, HttpSession session) throws Exception{
    	int ok = -1;
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			BoardSearchVO vo = new BoardSearchVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			vo.setBoardId((String )bbsObject.get("boardId"));
			vo.setUserId((String)info.getId());
			
			ok = board210Mapper.insertBbsFavorite(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return ok; 
    }
    
    /**
	 * 즐겨찾기 삭제
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 성공 여부
	 * @exception
	 */
    public int deleteBbsFavorite(String json, HttpSession session)throws Exception{
    	int ok = -1;
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			BoardSearchVO vo = new BoardSearchVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			vo.setBoardId((String )bbsObject.get("boardId"));
			vo.setUserId((String)info.getId());
			
			ok = board210Mapper.deleteBbsFavorite(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return ok;  
    }
    
    /**
     * 게시글 이동
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiInfoForMove(String json, HttpSession session) throws Exception{
    	
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));
			
			JSONArray jsonArr = (JSONArray)bbsObject.get("notiId");		
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>(); 
			logger.debug("jsonArr.size() : "+jsonArr.size());//선택된 게시글 만큼(현재는 단건처리)
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				String maxNotiId = board210Mapper.getMaxNotiId();
				int maxSurveyNo = board210Mapper.getMaxSurveyNo();
				BbsNotiInfoVO notiInfo = new BbsNotiInfoVO();
				logger.debug("target boardId : "+(String)bbsObject.get("boardId"));
				logger.debug("org notiId : "+(String)obj.get("id"));
				logger.debug("new notiId : "+maxNotiId);
				logger.debug("new maxSurveyNo : "+maxSurveyNo);
				notiInfo.setBoardId((String)bbsObject.get("boardId"));
				notiInfo.setNotiId((String)obj.get("id"));
				notiInfo.setMaxNotiId(maxNotiId);
				
				BbsNotiSurveyVO sur = new BbsNotiSurveyVO();
				sur.setSurveyNo(maxSurveyNo);
				sur.setMaxNotiId(maxNotiId);
				sur.setNotiId((String)obj.get("id"));
				
				board210Mapper.insertBbsNotiInfoForMove(notiInfo);//게시글  
				board210Mapper.insertBbsNotiAddItemForMove(notiInfo);//추가항목 
				board210Mapper.insertBbsNotiApndFileForMove(notiInfo);//첨부파일
				board210Mapper.insertBbsNotiEvalInfoForMove(notiInfo);//평가 
				board210Mapper.insertBbsOpnForMove(notiInfo);//의견
				board210Mapper.insertBbsNotiUserMapForMove(notiInfo);//매핍
				list.add((String)obj.get("id"));//notiId
			}
			map.put("list", list);
			map.put("updrId", info.getId());
			map.put("updrName", info.getName());
			
			board100Mapper.deleteNotiInfo(map);//게시글 삭제
			board100Mapper.deleteBbsNotiAddItemForBoard(map);
			board100Mapper.deleteBbsNotiApndFileForBoard(map);
			board100Mapper.deleteBbsNotiEvalInfoForBoard(map);
			board100Mapper.deleteBbsNotiOpnForBoard(map);
			board100Mapper.deleteBbsNotiUserMapForBoard(map);
			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "ok"; 
    }
    
    /**
     * 링크등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiInfoForLink(String json, HttpSession session ,HttpServletRequest request) throws Exception{
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			BbsNotiInfoVO vo = new BbsNotiInfoVO();
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			vo.setNotiId((String)bbsObject.get("notiId"));
			vo.setBoardId((String)bbsObject.get("boardId"));
			vo.setNotiTitle((String)bbsObject.get("notiTitle"));
			vo.setNotiTitleOrgn(vo.getNotiTitle());
			vo.setLinkUrl((String)bbsObject.get("linkUrl"));
			vo.setMakrIp(CommUtil.getClientIpAddr(request)) ;
			vo.setNotiTp("060");
			vo.setDeptCode(info.getOucode()) ;
			vo.setDeptName(info.getOu()) ;
			vo.setDeptFname(info.getOrgfullname()) ;
			vo.setUserId((String)info.getId());
			vo.setUserName((String)info.getName());
			vo.setRegrId((String)info.getId());
			vo.setRegrName((String)info.getName());
			vo.setUpdrId((String)info.getId());
			vo.setUpdrName((String)info.getName());
			
			board210Mapper.insertBbsNotiInfoForLink(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "ok"; 
    }
    
    /**
     * 공용 게시글 읽음처리 다건
     * @param json
     * @param session
     * @return
     * @throws Exception
     */
    public String updateBbsNotiEvalInfoForRead(String json, HttpSession session) throws Exception {
    	
		try {
				JSONObject bbsObject = JSONObject.fromObject(json);
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				String boardId = (String)bbsObject.get("boardId");		
				JSONArray jsonArr = (JSONArray)bbsObject.get("notiId");		
				
				for (int i=0; i < jsonArr.size(); i++)
				{
					
					JSONObject obj = (JSONObject)jsonArr.get(i);
					BbsNotiEvalInfoVO vo = new BbsNotiEvalInfoVO();
					logger.debug("boardId : "+boardId);
					logger.debug("notiId : "+(String )obj.get("id"));
					
					vo.setNotiId((String )obj.get("id"));
					vo.setNotiEvalDiv("040");
					vo.setUserId((String)info.getId());
					vo.setUserName((String)info.getName());
					vo.setRegrId((String)info.getId());
					vo.setRegrName((String)info.getName());
					vo.setUpdrId((String)info.getId());
					vo.setUpdrName((String)info.getName());
					int readCnt = board210Mapper.getNotiReadCnt(vo);
			    	logger.debug("updateBbsNotiEvalInfoForRead readCnt : "+readCnt);
			    	if(readCnt == 0){
			    		
			    		board210Mapper.updateBbsNotiInfoForCntPlus(vo);
			    		board210Mapper.insertBbsNotiEvalInfoForRead(vo);
			    	}
					//board210Mapper.updateBbsNotiEvalInfoForRead(vo);
				}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
     * 공용 게시판 게시글 읽음 처리 단건처리 
     * @param json
     * @param session
     * @return
     * @throws Exception
     */
    public String updateBbsNotiEvalInfoForReadForView(BbsNotiEvalInfoVO vo) throws Exception {
    	
		try {
			board210Mapper.updateBbsNotiEvalInfoForRead(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
	 * 게시글 상세정보
	 * @param 조회할 정보가 담긴 String
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoView(String json)throws Exception {
    	
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
			String notiId = (String)bbsObject.get("notiId");	
			logger.debug("getBbsNotiInfoView notiId : "+notiId);
			
			vo.setNotiId(notiId);

	    	return board210Mapper.getBbsNotiInfoView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * BBS_게시물_첨부_파일
	 * @param 조회할 정보가 담긴 String
	 * @return BBS_게시물_첨부_파일 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileListForView(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiApndFileVO vo = new BbsNotiApndFileVO();
			String notiId = (String)bbsObject.get("notiId");		
			vo.setNotiId(notiId);
			
	    	return board210Mapper.getBbsNotiApndFileListForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * BBS_게시물_첨부_파일
     * @param 조회할 정보가 담긴 String
     * @return BBS_게시물_첨부_파일 정보 
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiApndFileVO getBbsNotiApndFile(BbsNotiApndFileVO vo)throws Exception{
    	try{
    		return board210Mapper.getBbsNotiApndFile(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * BBS_게시물_태그 
	 * @param 조회할 정보가 담긴 String
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
//    public List<BbsNotiTagInfoVO> getBbsNotiTagInfoList(String json)throws Exception{
//    	
//    	JSONObject bbsObject = JSONObject.fromObject(json);
//    	BbsNotiTagInfoVO vo = new BbsNotiTagInfoVO();
//		String notiId = (String)bbsObject.get("notiId");		
//		vo.setNotiId(notiId);
//		
//    	return board210Mapper.getBbsNotiTagInfoList(vo);
//    }
    
    /**
	 * BBS_게시물_평가_정보
	 * @param 조회할 정보가 담긴 String
	 * @return BBS_게시물_태그_정보
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiEvalInfoVO> getBbsNotiEvalInfoList(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiEvalInfoVO vo = new BbsNotiEvalInfoVO();
	    	String notiEvalDiv = (String)bbsObject.get("notiEvalDiv");
			String notiId = (String)bbsObject.get("notiId");		
			vo.setNotiId(notiId);
			vo.setNotiEvalDiv("030");//좋아요
			vo.setRnum(bbsObject.getInt("rnum"));
			
	    	return board210Mapper.getBbsNotiEvalInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * BBS 게시물 의견
	 * @param 조회할 정보가 담긴 String
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList1ForView(String json)throws Exception {
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
			String notiId = (String)bbsObject.get("notiId");		
			vo.setNotiId(notiId);
	    	
	    	return board210Mapper.getBbsNotiOpnList1ForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * BBS 게시물 의견
	 * @param 조회할 정보가 담긴 String
	 * @return BBS 게시물 의견
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiOpnVO> getBbsNotiOpnList2ForView(String json)throws Exception {
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
			String notiId = (String)bbsObject.get("notiId");		
			vo.setNotiId(notiId);
	    	
	    	return board210Mapper.getBbsNotiOpnList2ForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * BBS 게시판 
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return BBS 게시판 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsBoardInfoVO> getBbsBoardInfoListForView(String data)throws Exception {
    	try{
	    	BbsBoardInfoVO vo = new BbsBoardInfoVO();
	    	return board210Mapper.getBbsBoardInfoListForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 의견 등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO insertBbsNotiOpnForView(HttpServletRequest request, String json, HttpSession session)throws Exception{
    	
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
    	try {

    		logger.debug("insertBbsNotiOpnForView IP : "+InetAddress.getLocalHost().getHostAddress());
    		JSONObject bbsObject = JSONObject.fromObject(json);
        	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		vo.setNotiId((String)bbsObject.get("notiId"));
    		vo.setUpOpnSeq(bbsObject.getInt("upOpnSeq"));
    		vo.setOpnConts((String)bbsObject.get("opnConts"));
    		vo.setUserId(info.getId());
    		vo.setUserName(info.getName());
    		vo.setUserNick((String)bbsObject.get("userNick"));
    		vo.setDeptCode(info.getOucode());
    		vo.setDeptName(info.getOu());
    		vo.setMakeIp(CommUtil.getClientIpAddr(request)) ;
    		vo.setRegrId(info.getId());
    		vo.setRegrName(info.getName());
    		vo.setUpdrId(info.getId());
    		vo.setUpdrName(info.getName());
    		vo.setUpdDttm(CommUtil.getDateString("yyyy-MM-dd HH:mm:ss")) ;
    		
    		board210Mapper.insertBbsNotiOpnForView(vo);
    		
			//폐쇄게시판 게시물 작성자에게 메일
			if(vo.getNotiId() != null){
				BbsNotiInfoVO bbsNotiVO = new BbsNotiInfoVO();
				bbsNotiVO.setNotiId(vo.getNotiId());
				List<BbsNotiInfoVO> bbsBasicNotiInfoList = board210Mapper.getBbsNotiInfoView(bbsNotiVO);
				if(bbsBasicNotiInfoList != null && bbsBasicNotiInfoList.size() > 0){
					bbsNotiVO = bbsBasicNotiInfoList.get(0);
					
					if(!info.getId().equals(bbsNotiVO.getUserId())){
						BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
						bbsVO.setBoardId(bbsNotiVO.getBoardId());
						List<BbsBoardInfoVO> list = board100Mapper.getAdminBbsBoardInfoList(bbsVO);
						BbsBoardInfoVO bbsInfo = list.get(0);
						if(Constant.BOARD_KIND_020.getVal().equals(bbsInfo.getBoardKind()) && "Y".equals(bbsInfo.getNotiEmailSendYn())){
							CommUtil.relayMailSend(bbsNotiVO.getMail(), "portaladmin@crossent.com", "포털 게시판 '"+bbsNotiVO.getBoardName()+"'에 작성하신 '"+bbsNotiVO.getNotiTitle()+"'에 의견이 작성되었습니다." , bbsNotiVO.getUserId(), bbsNotiVO.getUserName(), "포털관리자", "/board210/boardViewFrame.do?boardId="+bbsInfo.getBoardId()+"&notiId="+vo.getNotiId()+"&pageIndex=1");
						}
					}
				}
				
				
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
		
    	return vo;
    }
    
    /**
     * 의견 수정
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO updateBbsNotiOpnForView(HttpServletRequest request, String json, HttpSession session)throws Exception{
    	
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
    	try {

    		logger.debug("insertBbsNotiOpnForView IP : "+InetAddress.getLocalHost().getHostAddress());
    		JSONObject bbsObject = JSONObject.fromObject(json);
        	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		vo.setNotiId((String)bbsObject.get("notiId"));
    		vo.setNotiOpnSeq(bbsObject.getInt("notiOpnSeq"));
    		vo.setOpnConts((String)bbsObject.get("opnConts"));
    		vo.setUserId(info.getId());
    		vo.setUserName(info.getName());
    		vo.setUserNick(info.getNickName());
    		vo.setDeptCode(info.getOucode());
    		vo.setDeptName(info.getOu());
    		//vo.setMakeIp(InetAddress.getLocalHost().getHostAddress());
    		vo.setMakeIp(CommUtil.getClientIpAddr(request)) ;
    		vo.setRegrId(info.getId());
    		vo.setRegrName(info.getName());
    		vo.setUpdrId(info.getId());
    		vo.setUpdrName(info.getName());
    		
    		board210Mapper.updateBbsNotiOpnForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
		
    	return vo;
    }
    
    /**
     * 의견 삭제 
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiOpnVO deleteBbsNotiOpnForView(String json)throws Exception{
    	
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
    	try {

    		JSONObject bbsObject = JSONObject.fromObject(json);
    		vo.setNotiId((String)bbsObject.get("notiId"));
    		vo.setNotiOpnSeq(bbsObject.getInt("notiOpnSeq"));
    		
    		board210Mapper.deleteBbsNotiOpnForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
		
    	return vo;
    }
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiEvalInfoForView(String json, HttpSession session)throws Exception{
    	
    	String rtn = "ERR001";
    	try {
    		
    		logger.debug("insertBbsNotiOpnForView IP : "+InetAddress.getLocalHost().getHostAddress());
    		JSONObject bbsObject = JSONObject.fromObject(json);
        	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
        	String notiEvalDiv = (String)bbsObject.get("notiEvalDiv");
        	BbsNotiEvalInfoVO vo = new BbsNotiEvalInfoVO();
    		vo.setNotiId((String)bbsObject.get("notiId"));
    		vo.setNotiEvalDiv(notiEvalDiv);
    		vo.setUserId(info.getId());
    		vo.setUserName(info.getName());
    		vo.setRegrId(info.getId());
    		vo.setRegrName(info.getName());
    		vo.setUpdrId(info.getId());
    		vo.setUpdrName(info.getName());
    		
			int cnt = board210Mapper.getBbsNotiEvalInfoCntForView(vo);
			/**
			 * 조회수는 게시글당 한번
			 * 찬성반대는 두개중 한번 
			 */
    		logger.debug("insertBbsNotiEvalInfoForView : cnt : "+cnt);
    		if(cnt == 0){
    			BbsNotiEvalInfoVO bvo = new BbsNotiEvalInfoVO();
        		bvo.setNotiId(vo.getNotiId());
        		bvo.setNotiEvalDiv(notiEvalDiv);
        		board210Mapper.updateBbsNotiInfoForCntPlus(bvo);
    			board210Mapper.insertBbsNotiEvalInfoForView(vo);
    			rtn = "ok";
    		}
    		
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
		
    	return rtn;
    }
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param BbsNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiEvalInfoForRead(BbsNotiEvalInfoVO vo)throws Exception{
    	try{
	    	int readCnt = board210Mapper.getNotiReadCnt(vo);
	    	logger.debug("readCnt : "+readCnt);
	    	if(readCnt == 0){
	    		board210Mapper.updateBbsNotiInfoForCntPlus(vo);
	    		board210Mapper.insertBbsNotiEvalInfoForRead(vo);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "OK";
    }
    
    
    
    /**
	 * 게시글 상세보기 이전글 다음글 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<BbsNotiInfoVO> getBbsPrevNextNotiInfoForView(String json, String auth, int prev_pnum, int next_pnum, String notiReadmanAsgnYn, String userId)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
	    	String boardId = (String)bbsObject.get("boardId");
			logger.debug("getBbsNotiInfoView boardId : "+boardId);
	
			vo.setBoardId(boardId);
			vo.setUserMap(auth);
			vo.setPrevPnum(prev_pnum);
			vo.setNextPnum(next_pnum);
			vo.setNotiReadmanAsgnYn(notiReadmanAsgnYn);	
			vo.setUserId(userId);
	    	return board210Mapper.getBbsPrevNextNotiInfoForView(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    }
    
    /**
     * 게시글별 답글존재 여부 
     * @param json, session
     * @return 답글존재여부 
     * @auther crossent 
     */
    public String getReNotiYn(String json, HttpSession session)throws Exception  {
    	try{
	    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
	    	JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
	    	
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
			JSONArray jsonArr = (JSONArray)bbsObject.get("notiId");		
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>(); 
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				list.add((String)obj.get("id"));
				
			}
			map.put("list", list);
			map.put("regrId", info.getId());
			
	    	return board210Mapper.getReNotiYn(map); 
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시글 내용 조회
     * @param notiId
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO getBbsNotiInfoViewForNotiConts(String notiId)throws Exception{
    	try{
    		return board210Mapper.getBbsNotiInfoViewForNotiConts(notiId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시글 삭제정보 
     * @param BbsNotiDelInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertBbsNotiDelInfo(String json, HttpSession session)throws Exception{
    	
    	try {
    		JSONObject bbsObject = JSONObject.fromObject(json);
    		
    		
        	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
        	BbsNotiDelInfoVO vo = new BbsNotiDelInfoVO();
    		vo.setNotiId((String)bbsObject.get("notiId"));
    		vo.setDelDiv((String)bbsObject.get("delDiv"));
    		vo.setDelBasis((String)bbsObject.get("delBasis"));//근거
    		vo.setDelRsn((String)bbsObject.get("delRsn"));//삭제사유 
    		vo.setDelRsnCode((String)bbsObject.get("delRsnCode"));//AAA
    		vo.setDelRsnCodeSpec((String)bbsObject.get("delRsnCodeSpec"));//조치내용
    		vo.setBadNotiFindDttm((String)bbsObject.get("badNotiFindDttm"));//발견일시 
    		vo.setRmrk((String)bbsObject.get("rmrk"));
    		vo.setUserName(info.getName());
    		vo.setRegrId(info.getId());
    		vo.setRegrName(info.getName());
    		vo.setUpdrId(info.getId());
    		vo.setUpdrName(info.getName());
    		
    		board210Mapper.insertBbsNotiDelInfo(vo); 
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "ok";
    }
    
    /**
	 * 동영상 파일정보  
	 * @param TbsNotiDelInfoVO - 조회할 정보가 담긴 VO
	 * @return 동영상 파일정보  
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<TbsNotiDelInfoVO> getTnMspMvpFileList(String notiId)throws Exception{
    	try{
    		return board210Mapper.getTnMspMvpFileList(notiId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    		
    }
    
    /**
     * 코드 조회
     * @return List<ComCodeSpecVO>
     * @exception Exception
     * @auther crossent 
     */
    public List<ComCodeSpecVO> getComCodeSpecList()throws Exception {
    	try{
    		return board210Mapper.getComCodeSpecList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 이미지 파일과 게시물 정보  
	 * @param notiId - 게시물ID
	 * @return 이미지,동영상 파일정보  
	 * @exception Exception
	 */
    public List<BbsNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(String data, HttpSession session, String auth)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(data);
	    	
	    	BbsNotiInfoVO notiVO = new BbsNotiInfoVO();
			String notiId = bbsObject.getString("notiId");
			int direction = 0;
			try{ direction = bbsObject.getInt("direction"); }catch(Exception e){}
			notiVO.setNotiId(notiId);
			
			String superAdmin = (String)session.getAttribute("superAdmin")==null?"":(String)session.getAttribute("superAdmin");
			if (superAdmin.equals(Constant.ROLE_SUPER.getVal()))
			{
				notiVO.setNotiReadmanAsgnYn("A");
			}
	    	
	    	List notiInfoList = board210Mapper.getBbsNotiInfoView(notiVO);
	    	notiVO = (BbsNotiInfoVO) notiInfoList.get(0);
	    	
	    	BoardSearchVO searchVO = new BoardSearchVO();
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	if(info == null) info = new UserInfoVO();
	    	if(auth == null){
		    	searchVO.setUserId(info.getId());
		    	searchVO.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
				auth = board100Mapper.getUserBbsMapList(searchVO);
	    	}
	    	notiVO.setUserMap(auth);
	    	if (superAdmin.equals(Constant.ROLE_SUPER.getVal()))
			{
				notiVO.setNotiReadmanAsgnYn("A");
			}
	    	notiVO.setUserId(info.getId());
	    	
			int imgPnum = board210Mapper.getBbsImgMoviePnumInfo(notiVO);
			
			searchVO.setBoardId(notiVO.getBoardId());
			searchVO.setNotiReadmanAsgnYn(notiVO.getNotiReadmanAsgnYn());
			searchVO.setBoardFormSpec(notiVO.getBoardFormSpec());
			searchVO.setFirstIndex(imgPnum + direction);
			searchVO.setUserMap(auth);
			searchVO.setUserId(info.getId());
	    	
	    	return board210Mapper.getBbsMovieImagePrevNextNotiInfoForView(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 이미지,동영상 게시글 PNUM 가져오기
     * @param BbsNotiInfoVO
     * @return int 
     * @exception Exception
     */
    public int getBbsImgMoviePnumInfo(BbsNotiInfoVO vo) throws Exception{
    	try{
    		return board210Mapper.getBbsImgMoviePnumInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

}
