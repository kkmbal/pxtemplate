package portalxpert.board.board310.sc.impl;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.PbsNotiEvalInfoVO;
import portalxpert.board.board100.vo.TbsNotiDelInfoVO;
import portalxpert.board.board300.mapper.Board300Mapper;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiEvalInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiOpnVO;
import portalxpert.board.board310.mapper.Board310Mapper;
import portalxpert.board.board310.sc.Board310Service;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;



@Service("board310Service")
public class Board310ServiceImpl extends AbstractServiceImpl implements  Board310Service {
	
	/** board310Mapper */
    @Resource(name="board310Mapper")
    private Board310Mapper board310Mapper;
    
    /** board100Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;
    
	/** board300Mapper */
    @Resource(name="board300Mapper")
    private Board300Mapper board300Mapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    private final static Logger logger = LoggerFactory.getLogger(Board310ServiceImpl.class); 

    /**
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getPbsNotiUserInfoListForPaging(BoardSearchVO vo) throws Exception {
    	try{
    		return board310Mapper.getPbsNotiUserInfoListForPaging(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시물 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시물 총 갯수
	 * @exception
	 */
    public int getPbsUserNotiInfoListTotCnt(BoardSearchVO vo)throws Exception  {
    	try{
    		return board310Mapper.getPbsUserNotiInfoListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 상세보기 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiInfoVO vo = new PbsUserNotiInfoVO();
			vo.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));
			vo.setBoardId((String)bbsObject.get("boardId"));
			
	    	return board310Mapper.getPbsUserNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * PBS_게시물_첨부_파일 조회 
	 * @param PbsUserNotiApndFileVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiApndFileVO> getBbsNotiApndFileList(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiApndFileVO vo = new PbsUserNotiApndFileVO();
			vo.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));
	    	
	    	return board310Mapper.getBbsNotiApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * PBS_게시물_이전글 다음글 조회
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiInfoVO> getPbsPrevNextNotiInfo(String json, String auth, int prev_pnum, int next_pnum, String userId)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiInfoVO vo = new PbsUserNotiInfoVO();
	    	vo.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));
			vo.setBoardId((String)bbsObject.get("boardId"));
			vo.setUserMap(auth);
			vo.setPrevPnum(prev_pnum);
			vo.setNextPnum(next_pnum);
			vo.setUserId(userId);
			
	    	return board310Mapper.getPbsPrevNextNotiInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * PBS 게시물 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList1(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiOpnVO vo = new PbsUserNotiOpnVO();
	    	vo.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));		
			
	    	return board310Mapper.getPbsUserNotiOpnList1(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * PBS 게시물 의견의 의견 정보
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<PbsUserNotiOpnVO> getPbsUserNotiOpnList2(String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiOpnVO vo = new PbsUserNotiOpnVO();
	    	vo.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));
	    	
	    	return board310Mapper.getPbsUserNotiOpnList2(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * PBS 의견등록
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String insertPbsUserNotiOpn(String json, HttpSession session)throws Exception {
    	try{
			JSONObject bbsObject = JSONObject.fromObject(json);
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	PbsUserNotiOpnVO vo = new PbsUserNotiOpnVO();
			vo.setUserNotiSeq(bbsObject.getInt("userNotiSeq"));
			vo.setUpOpnSeq(bbsObject.getInt("upOpnSeq"));
			vo.setOpnConts((String)bbsObject.get("opnConts"));
			vo.setUserId(info.getId());
			vo.setUserName(info.getName());
			vo.setUserNick((String)bbsObject.get("userNick"));
			vo.setDeptCode(info.getOucode());
			vo.setDeptName(info.getOu());
			vo.setMakeIp(InetAddress.getLocalHost().getHostAddress());
			vo.setRegrId(info.getId());
			vo.setRegrName(info.getName());
			vo.setUpdrId(info.getId());
			vo.setUpdrName(info.getName());
			
			board310Mapper.insertPbsUserNotiOpn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
    	return "ok";
    }
    
    /**
	 * PBS 의견수정
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String updatePbsUserNotiOpn(String json, HttpSession session)throws Exception{
    	
    	try{
			JSONObject bbsObject = JSONObject.fromObject(json);
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	PbsUserNotiOpnVO vo = new PbsUserNotiOpnVO();
			vo.setUserNotiSeq(bbsObject.getInt("userNotiSeq"));
			vo.setUserNotiOpnSeq(bbsObject.getInt("userNotiOpnSeq"));
			vo.setOpnConts((String)bbsObject.get("opnConts"));
			vo.setUserId(info.getId());
			vo.setUserName(info.getName());
			vo.setUserNick(info.getNickName());
			vo.setDeptCode(info.getOucode());
			vo.setDeptName(info.getOu());
			vo.setMakeIp(InetAddress.getLocalHost().getHostAddress());
			vo.setRegrId(info.getId());
			vo.setRegrName(info.getName());
			vo.setUpdrId(info.getId());
			vo.setUpdrName(info.getName());
			
			board310Mapper.updatePbsUserNotiOpn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
    	return "ok";
    }
    
    /**
	 * PBS 의견삭제
	 * @param PbsUserNotiOpnVO - 조회할 정보가 담긴 Map
	 * @return 게시물 정보 
	 * @exception Exception
	 * @auther crossent 
	 */
    public String deletePbsUserNotiOpn(String json)throws Exception {
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
	    	PbsUserNotiOpnVO vo = new PbsUserNotiOpnVO();
			vo.setUserNotiSeq(bbsObject.getInt("userNotiSeq"));
			vo.setUserNotiOpnSeq(bbsObject.getInt("userNotiOpnSeq"));
			
			board310Mapper.deletePbsUserNotiOpn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "ok";
    }
    
    /**
     * 게시글 상세보기시 조회수 올리기
     * @param PbsUserNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiEvalInfo(PbsUserNotiEvalInfoVO vo)throws Exception{
    	try{
	    	if(board310Mapper.getUserNotiReadCnt(vo) == 0){
	    		board310Mapper.updatePbsUserNotiInfoForCntPlus(vo);
	    		board310Mapper.insertPbsUserNotiEvalInfo(vo);
	    	}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "OK";
    }
    
    /**
     * 개인 게시글 읽음처리 다건
     * @param PbsUserNotiEvalInfoVO
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String updatePbsUserNotiEvalInfoForRead(HttpSession session, String json)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(json);
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			String boardId = (String)bbsObject.get("boardId");		
			JSONArray jsonArr = (JSONArray)bbsObject.get("userNotiSeq");		
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PbsUserNotiEvalInfoVO vo = new PbsUserNotiEvalInfoVO();
				vo.setUserNotiSeq(obj.getInt("id"));
				vo.setNotiEvalDiv("040");
				vo.setUserId((String)info.getId());
				vo.setUserName((String)info.getName());
				vo.setRegrId((String)info.getId());
				vo.setRegrName((String)info.getName());
				vo.setUpdrId((String)info.getId());
				vo.setUpdrName((String)info.getName());
				int readCnt = board310Mapper.getNotiReadCnt(vo);
		    	logger.debug("updateBbsNotiEvalInfoForRead readCnt : "+readCnt);
		    	if(readCnt == 0){
		    		
		    		board310Mapper.updatePbsUserNotiInfoForCntPlus(vo);
		    		board310Mapper.insertPbsUserNotiEvalInfo(vo);
		    	}
				//board310Mapper.updatePbsUserNotiEvalInfoForRead(notiInfo);
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
    }
    
    /**
     * 게시글 이동
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiInfoForMove(String json, HttpSession session) throws Exception{
    	
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));
			JSONArray jsonArr = (JSONArray)bbsObject.get("userNotiSeq");		
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>(); 
			logger.debug("jsonArr.size() : "+jsonArr.size());
			int maxUserNotiSeq = board310Mapper.getMaxUserNotiSeq();
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PbsUserNotiInfoVO notiInfo = new PbsUserNotiInfoVO();
				logger.debug("boardId : "+(String)bbsObject.get("boardId"));
				
				logger.debug("userNotiSeq : "+(String )obj.get("id"));
				
				notiInfo.setBoardId((String)bbsObject.get("boardId"));
				notiInfo.setMaxUserNotiSeq(maxUserNotiSeq);
				notiInfo.setUserNotiSeq(obj.getInt("id"));
				notiInfo.setUpdrId((String)info.getId());
				notiInfo.setUpdrName((String)info.getName());
				
				board310Mapper.insertPbsUserNotiInfoForMove(notiInfo);//해당 게시판 등록 
				board310Mapper.insertPbsUserNotiApndFileForMove(notiInfo);//해당 게시판 등록
				board310Mapper.insertPbsUserNotiEvalForMove(notiInfo);//해당 게시판 등록
				board310Mapper.insertPbsUserNotiOpnForMove(notiInfo);//해당 게시판 등록
				
				
				list.add((String)obj.get("id"));//noti_id
			}
			
			map.put("list", list);
			map.put("updrId", info.getId());
			map.put("updrName", info.getName());
			board310Mapper.deletePbsUserNotiInfoForBoard(map);//게시글 삭제 
			board310Mapper.deletePbsUserNotiApndFileForBoard(map);//게시글 삭제
			board310Mapper.deletePbsUserNotiEvalInfoForBoard(map);//게시글 삭제
			board310Mapper.deletePbsUserNotiOpnForBoard(map);//게시글 삭제
			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "ok"; 
    }
    
    /**
     * 나의 작성글 여부 확인 
     * @param searchVO - 조회할 정보가 담긴 VO
     * @return 게시판 총 갯수
     * @auther crossent 
     */
    public int getMyPbsNotiCheckList(String json, HttpSession session)throws Exception  {
    	try{
	    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
	    	JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
	    	
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
			JSONArray jsonArr = (JSONArray)bbsObject.get("userNotiSeq");		
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
			
	    	return board310Mapper.getMyPbsNotiCheckList(map); 
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 공용 게시판 게시글 삭제
     * @param json
     * @param session
     * @return
     * @throws Exception
     */
    public String deletePbsNotiInfo(String json, HttpSession session) throws Exception {
    	
    	String rtnStr = "OK";
    	
		try {
			
			//사용자가 선택한 글중 권한이 없는 글 갯수
			int myNotNotiCnt = getMyPbsNotiCheckList(json, session);
			
			logger.debug("myNotNotiCnt : "+myNotNotiCnt+"");
			if(myNotNotiCnt > 0){
				rtnStr = "ERR001";
			}else{
				JSONObject bbsObject = JSONObject.fromObject(json);
				
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				String boardId = (String)bbsObject.get("boardId");		
				JSONArray jsonArr = (JSONArray)bbsObject.get("userNotiSeq");		
				List list = new ArrayList();
				Map<String, Object> map = new HashMap<String, Object>();
				for (int i=0; i < jsonArr.size(); i++)
				{
					
					JSONObject obj = (JSONObject)jsonArr.get(i);
					logger.debug("deleteNotiInfo noti_id : "+(String)obj.get("id"));
					list.add((String)obj.get("id"));//userNotiSeq
				}
				map.put("list", list);
				map.put("updrId", info.getId());
				map.put("updrName", info.getName());
				
				board310Mapper.deletePbsUserNotiInfoForBoard(map);//게시글 삭제 
				board310Mapper.deletePbsUserNotiApndFileForBoard(map);//게시글 삭제
				board310Mapper.deletePbsUserNotiEvalInfoForBoard(map);//게시글 삭제
				board310Mapper.deletePbsUserNotiOpnForBoard(map);//게시글 삭제

			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return rtnStr;
    }
    
    /**
     * 게시글 스크랩
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsUserNotiInfoForScrap(String json, HttpSession session) throws Exception{
    	
    	try {
    		
			JSONObject bbsObject = JSONObject.fromObject(json);
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			logger.debug("boardId : "+(String)bbsObject.get("boardId"));//타겟게시판
			logger.debug("notiId : "+(String)bbsObject.get("notiId"));//스크랩할게시글
			logger.debug("linkUrl : "+(String)bbsObject.get("linkUrl"));//링크URL
			logger.debug("notiTitle : "+(String)bbsObject.get("notiTitle"));//게시글제목 
			String pbs = (String)bbsObject.get("pbs");
			PbsUserNotiInfoVO notiInfo = new PbsUserNotiInfoVO();
			notiInfo.setBoardId((String)bbsObject.get("boardId"));
			
			notiInfo.setLinkUrl((String)bbsObject.get("linkUrl"));
			notiInfo.setNotiTitle((String)bbsObject.get("notiTitle"));
			notiInfo.setNotiKind((String)bbsObject.get("notiKind"));
			notiInfo.setUserNick(info.getNickName());
			notiInfo.setRegrId(info.getId());
			notiInfo.setRegrName(info.getName());
			notiInfo.setUpdrId(info.getId());
			notiInfo.setUpdrName(info.getName());
			notiInfo.setDeptCode(info.getOucode()) ;
			notiInfo.setDeptName(info.getOu()) ;
			notiInfo.setDeptFname(info.getOrgfullname()) ;
			int maxUserNotiSeq = board310Mapper.getMaxUserNotiSeq();
			if(pbs.equals("N")){//공용게시판 -> 개인게시판으로 스크랩 
				
				notiInfo.setNotiId((String)bbsObject.get("notiId"));
				notiInfo.setMaxUserNotiSeq(maxUserNotiSeq);
				board310Mapper.insertPbsUserNotiInfoForScrapFromBbs(notiInfo);
				board310Mapper.insertPbsUserNotiApndFileForScrapFromBbs(notiInfo);
				
			}else if(pbs.equals("Y")){//개인게시판 -> 개인게시판으로 스크랩
				logger.debug("userNotiSeq : "+bbsObject.getInt("userNotiSeq"));
				notiInfo.setUserNotiSeq(bbsObject.getInt("userNotiSeq"));
				notiInfo.setMaxUserNotiSeq(maxUserNotiSeq);
				board310Mapper.insertPbsUserNotiInfoForScrapFromPbs(notiInfo);
				board310Mapper.insertPbsUserNotiApndFileForScrapFromPbs(notiInfo);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return "ok"; 
    }
    
    /**
     * 게시글 내용
     * @param String
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public BbsNotiInfoVO getPbsUserNotiInfoViewForNotiConts(int userNotiSeq)throws Exception{
    	try{
    		return board310Mapper.getPbsUserNotiInfoViewForNotiConts(userNotiSeq);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 동영상 파일정보  
	 * @param TbsNotiDelInfoVO - 조회할 정보가 담긴 VO
	 * @return 동영상 파일정보  
	 * @exception Exception
	 * @auther crossent 
	 */
    public List<TbsNotiDelInfoVO> getTnMspMvpFileList(int userNotiSeq)throws Exception{
    	try{
    		return board310Mapper.getTnMspMvpFileList(userNotiSeq);
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
    public List<PbsUserNotiInfoVO> getBbsMovieImagePrevNextNotiInfoForView(String data, HttpSession session, String auth)throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(data);
	    	PbsUserNotiInfoVO notiVO = new PbsUserNotiInfoVO();
	    	notiVO.setUserNotiSeq(Integer.parseInt((String)bbsObject.get("userNotiSeq")));
	    	notiVO.setBoardId((String)bbsObject.get("boardId"));
			int direction = 0;
			try{ direction = bbsObject.getInt("direction"); }catch(Exception e){}
	    	
	    	List notiInfoList = board310Mapper.getPbsUserNotiInfoList(notiVO);
	    	notiVO = (PbsUserNotiInfoVO) notiInfoList.get(0);
	    	
	    	BoardSearchVO searchVO = new BoardSearchVO();
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	if(auth == null){
		    	searchVO.setUserId(info.getId());
		    	searchVO.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
				auth = board300Mapper.getUserBbsMapList(searchVO);
	    	}
	    	notiVO.setUserMap(auth);
	    	notiVO.setUserId(info.getId());
	    	
			int imgPnum = board310Mapper.getPbsImgMoviePnumInfo(notiVO);
			
			searchVO.setBoardId(notiVO.getBoardId());
			searchVO.setNotiReadmanAsgnYn(notiVO.getNotiReadmanAsgnYn());
			searchVO.setBoardFormSpec(notiVO.getBoardFormSpec());
			searchVO.setFirstIndex(imgPnum + direction);
			searchVO.setUserMap(auth);
			searchVO.setUserId(info.getId());
	    	
	    	return board310Mapper.getBbsMovieImagePrevNextNotiInfoForView(searchVO);
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
    public int getBbsImgMoviePnumInfo(PbsUserNotiInfoVO vo) throws Exception{
    	try{
    		return board310Mapper.getPbsImgMoviePnumInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    } 
    
    /**
     * BBS_게시물_평가_정보 등록
     * @param json, session
     * @return int
     * @exception Exception
     * @auther crossent 
     */
    public String insertPbsNotiEvalInfoForView(String json, HttpSession session)throws Exception{
    	
    	String rtn = "ERR001";
    	try {
    		
    		logger.debug("insertBbsNotiOpnForView IP : "+InetAddress.getLocalHost().getHostAddress());
    		JSONObject bbsObject = JSONObject.fromObject(json);
        	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
        	String notiEvalDiv = (String)bbsObject.get("notiEvalDiv");
        	PbsNotiEvalInfoVO vo = new PbsNotiEvalInfoVO();
    		vo.setUserNotiSeq((String)bbsObject.get("userNotiSeq"));
    		vo.setNotiEvalDiv(notiEvalDiv);
    		vo.setUserId(info.getId());
    		vo.setUserName(info.getName());
    		vo.setRegrId(info.getId());
    		vo.setRegrName(info.getName());
    		vo.setUpdrId(info.getId());
    		vo.setUpdrName(info.getName());
    		
			int cnt = board310Mapper.getPbsNotiEvalInfoCntForView(vo);
			/**
			 * 조회수는 게시글당 한번
			 * 찬성반대는 두개중 한번 
			 */
    		logger.debug("insertPbsNotiEvalInfoForView : cnt : "+cnt);
    		if(cnt == 0){
    			PbsNotiEvalInfoVO bvo = new PbsNotiEvalInfoVO();
        		bvo.setUserNotiSeq(vo.getUserNotiSeq());
        		bvo.setNotiEvalDiv(notiEvalDiv);
        		board310Mapper.updatePbsNotiInfoForCntPlus(bvo);
    			board310Mapper.insertPbsNotiEvalInfoForView(vo);
    			rtn = "ok";
    		}
    		
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
		
    	return rtn;
    }
}