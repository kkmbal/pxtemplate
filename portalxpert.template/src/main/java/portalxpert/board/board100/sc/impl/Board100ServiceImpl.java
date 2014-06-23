package portalxpert.board.board100.sc.impl;

import java.util.List;
import java.util.ListIterator;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.sc.Board100Service;
import portalxpert.board.board100.vo.BbsAddItemInfoVO;
import portalxpert.board.board100.vo.BbsBoardAddItemVO;
import portalxpert.board.board100.vo.BbsBoardChartPopVO;
import portalxpert.board.board100.vo.BbsBoardInfoVO;
import portalxpert.board.board100.vo.BbsBoardUserMapVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board100.vo.BbsNotiUserMapVO;
import portalxpert.board.board100.vo.BbsTotalSearchVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board100.vo.PsnTmlnInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.SearchConditionVO;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.vo.CategoryVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;



@Service("board100Service")
public class Board100ServiceImpl extends AbstractServiceImpl implements  Board100Service {
	
	/** board100Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;

    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    /*@Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;*/
	
	private final static Logger logger = LoggerFactory.getLogger(Board100ServiceImpl.class); 

    public BbsBoardInfoVO selectAdminBoardName(BbsBoardInfoVO vo) throws Exception {
    	try{
    		BbsBoardInfoVO resultVO = board100Mapper.getAdminBoardName(vo);

    		return resultVO;
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    public int selectAdminIsBoardName(BbsBoardInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getAdminIsBoardName(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    public List<BbsAddItemInfoVO> selectAdminAddItemList(BbsAddItemInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getAdminAddItemList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public void showList(JSONArray jsonArray){
    	
    	ListIterator itr = jsonArray.listIterator();
    	
    	while(itr.hasNext())
    	{
    		JSONObject obj = (JSONObject)itr.next();
    	}
    }
    
    /**
	 * 관리자 게시판 생성
	 * @param json, json
	 * @return 게시판 목록
	 * @exception Exception
	 */
    public String createAdminBbsList(String json, HttpSession session) throws Exception {
    	
		try {
			JSONObject bbsObject = JSONObject.fromObject(json);
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");		
			//게시판 기본 테이블
			
			logger.debug("boardOperBgnDttm : "+(String )bbsObject.get("boardOperBgnDttm"));
			logger.debug("boardOperEndDttm : "+(String )bbsObject.get("boardOperEndDttm"));
			BbsBoardInfoVO bbsBoardInfo = new BbsBoardInfoVO();
			bbsBoardInfo.setBoardId("");
			bbsBoardInfo.setBoardTp((String )bbsObject.get("boardTp"));
			bbsBoardInfo.setRequDeptCode((String )bbsObject.get("requDeptCode"));
			bbsBoardInfo.setRequDeptName((String )bbsObject.get("requDeptName"));
			bbsBoardInfo.setRequUserId((String )bbsObject.get("requUserId"));
			bbsBoardInfo.setRequUserName((String )bbsObject.get("requUserName"));
			bbsBoardInfo.setRequDocNo((String )bbsObject.get("requDocNo"));
			bbsBoardInfo.setBoardName((String )bbsObject.get("boardName"));
			bbsBoardInfo.setBoardKind((String )bbsObject.get("boardKind"));
			bbsBoardInfo.setBoardForm((String )bbsObject.get("boardForm"));
			bbsBoardInfo.setBoardFormSpec((String )bbsObject.get("boardFormSpec"));
			bbsBoardInfo.setMoblLinkYn((String )bbsObject.get("moblLinkYn"));
			bbsBoardInfo.setBoardOperYn((String )bbsObject.get("boardOperYn"));
			bbsBoardInfo.setAdminAlertYn((String )bbsObject.get("adminAlertYn"));
			bbsBoardInfo.setBoardOpenDiv((String )bbsObject.get("boardOpenDiv"));//게시판공개구분
			bbsBoardInfo.setBoardOpenDivSpec((String )bbsObject.get("boardOpenDivSpec"));//게시판공개구분상세
			bbsBoardInfo.setBoardOperDiv((String )bbsObject.get("boardOperDiv"));//게사판운영구분
			bbsBoardInfo.setBoardOperBgnDttm((String )bbsObject.get("boardOperBgnDttm"));
			bbsBoardInfo.setBoardOperEndDttm((String )bbsObject.get("boardOperEndDttm"));
			bbsBoardInfo.setNotiTermDiv((String )bbsObject.get("notiTermDiv"));
			bbsBoardInfo.setPublAsgnDiv((String )bbsObject.get("publAsgnDiv"));
			bbsBoardInfo.setPublAsgnDivSpec((String )bbsObject.get("publAsgnDivSpec"));
			bbsBoardInfo.setBoardAnmtUseYn((String )bbsObject.get("boardAnmtUseYn"));
			bbsBoardInfo.setBoardExplUseYn((String )bbsObject.get("boardExplUseYn"));
			bbsBoardInfo.setBoardExpl((String )bbsObject.get("boardExpl"));
			bbsBoardInfo.setMakrDispDiv((String )bbsObject.get("makrDispDiv"));
			bbsBoardInfo.setNotiReadmanAsgnYn((String )bbsObject.get("notiReadmanAsgnYn"));
			bbsBoardInfo.setAgrmOppUseYn((String )bbsObject.get("agrmOppUseYn"));
			bbsBoardInfo.setReplyWrteDiv((String )bbsObject.get("replyWrteDiv"));
			bbsBoardInfo.setOpnFileUploadYn((String )bbsObject.get("opnFileUploadYn"));
			
			String opnFileUploadCnt = bbsObject.get("opnFileUploadCnt")==null?"999":bbsObject.get("opnFileUploadCnt").toString();
			bbsBoardInfo.setOpnFileUploadCnt(opnFileUploadCnt);
			bbsBoardInfo.setOpnWrteDiv((String )bbsObject.get("opnWrteDiv"));
			bbsBoardInfo.setOpnReplyUseDiv((String )bbsObject.get("opnReplyUseDiv"));
			bbsBoardInfo.setOpnRealnameDiv((String )bbsObject.get("opnRealnameDiv"));
			bbsBoardInfo.setLikeUseYn((String )bbsObject.get("likeUseYn"));
			bbsBoardInfo.setNickUseYn((String )bbsObject.get("nickUseYn"));
			bbsBoardInfo.setSmsUseYn((String )bbsObject.get("smsUseYn"));
			
			String apndFileSz = bbsObject.get("apndFileSz")==null?"5":bbsObject.get("apndFileSz").toString();
			bbsBoardInfo.setApndFileSz(CommUtil.getStringToInt(apndFileSz));
			bbsBoardInfo.setEditDiv((String )bbsObject.get("editDiv"));
			bbsBoardInfo.setNotiEmailSendYn((String )bbsObject.get("notiEmailSendYn"));
			bbsBoardInfo.setOutsideOpenDiv((String )bbsObject.get("outsideOpenDiv"));
			bbsBoardInfo.setOutsideOpenDivSpec((String )bbsObject.get("outsideOpenDivSpec"));
			bbsBoardInfo.setPopupYn((String )bbsObject.get("popupYn"));
			bbsBoardInfo.setBriefYn((String )bbsObject.get("briefYn"));
			bbsBoardInfo.setFcode((String )bbsObject.get("fcode"));
			bbsBoardInfo.setReadDiv((String )bbsObject.get("readDiv"));
			bbsBoardInfo.setDelYn((String )bbsObject.get("delYn"));
			bbsBoardInfo.setRegrId(info.getId()+"");
			bbsBoardInfo.setRegrName(info.getName()+"");
			bbsBoardInfo.setUpdrId(info.getId()+"");
			bbsBoardInfo.setUpdrName(info.getName()+"");
			bbsBoardInfo.setOldBrdId((String )bbsObject.get("oldBrdId"));
			bbsBoardInfo.setOldParId((String )bbsObject.get("oldParId"));
			bbsBoardInfo.setOldSeq(0);
			
			String saveKind = (String)bbsObject.get("boardId");
			
			if (saveKind.equals(""))
			{
				board100Mapper.insertAdminBbsBoardInfo(bbsBoardInfo);
			}
			else  //수정
			{
				bbsBoardInfo.setBoardId((String)bbsObject.get("boardId"));
				board100Mapper.updateAdminBbsBoardInfo(bbsBoardInfo);				
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();	
				userMapVO.setBoardId((String)bbsObject.get("boardId"));				
				board100Mapper.deleteAdminBbsBoardUserMap(userMapVO);
			}
			
			String boardId = bbsBoardInfo.getBoardId();
			String openSpec = bbsBoardInfo.getBoardOpenDivSpec();
			String publSpec = bbsBoardInfo.getPublAsgnDivSpec();
			
			//관리자 지정
			JSONArray jsonArr = (JSONArray)bbsObject.get("BbsManagerList");			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				userMapVO.setUserDiv((String)obj.get("div"));
				userMapVO.setUserId((String)obj.get("id"));
				userMapVO.setMngAuth((String)obj.get("auth"));				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			//공개여부 부서지정
			jsonArr = (JSONArray)bbsObject.get("BbsPublicDeptList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				userMapVO.setUserDiv((String)obj.get("div"));
				userMapVO.setUserId((String)obj.get("id"));
				userMapVO.setMngAuth((String)obj.get("auth"));				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
				
			}
			
			//공개여부 개인지정
			jsonArr = (JSONArray)bbsObject.get("BbsPublicPsnList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();
				userMapVO.setBoardId(boardId);
				userMapVO.setUserDiv((String)obj.get("div"));
				userMapVO.setUserId((String)obj.get("id"));
				userMapVO.setMngAuth((String)obj.get("auth"));				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			//게시자 부서지정
			jsonArr = (JSONArray)bbsObject.get("BbsMakerDeptList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				userMapVO.setUserDiv((String)obj.get("div"));
				userMapVO.setUserId((String)obj.get("id"));
				userMapVO.setMngAuth((String)obj.get("auth"));				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			//게시자 개인지정
			jsonArr = (JSONArray)bbsObject.get("BbsMakerPsnList");
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				userMapVO.setUserDiv((String)obj.get("div"));
				userMapVO.setUserId((String)obj.get("id"));
				userMapVO.setMngAuth((String)obj.get("auth"));				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			if (!openSpec.equals(""))//게시판공개구분 
			{
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				if (openSpec.equals("010"))//전체공개
				{
					userMapVO.setUserDiv("ALL");
				}
				else
				{
					userMapVO.setUserDiv("PUB");
				}				
				userMapVO.setUserId(userMapVO.getUserDiv());
				userMapVO.setMngAuth("RED");				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			if (!publSpec.equals(""))//게시자지정구분
			{
				BbsBoardUserMapVO userMapVO = new BbsBoardUserMapVO();				
				userMapVO.setBoardId(boardId);
				if (publSpec.equals("010"))
				{
					userMapVO.setUserDiv("ALL");
				}
				else
				{
					userMapVO.setUserDiv("PUB");
				}				
				userMapVO.setUserId(userMapVO.getUserDiv()); 
				userMapVO.setMngAuth("WRT");				
				userMapVO.setDelYn("N");
				userMapVO.setRegrId("");
				userMapVO.setRegrName("");
				userMapVO.setRegDttm("");
				userMapVO.setUpdrId("");
				userMapVO.setUpdrName("");
				userMapVO.setUpdDttm("");				
				board100Mapper.insertAdminBbsBoardUserMap(userMapVO);
			}
			
			//관리자용 카테고리 관리
			if (saveKind.equals(""))  //입력
			{
				//my카테고리 업데이트
				CategoryVO categoryVo = new CategoryVO();
				categoryVo.setId("SYSTEM");
				
				int categoryCnt = board100Mapper.selectMyCategoryContCnt(categoryVo);
				
				String conts = "";
				
				if(categoryCnt == 0){
	
					conts = "[{\"id\":999,\"pId\":0,\"name\":\"미지정\",\"boardId\":\"\",\"icon\":\"../resources/images/img/img_category.gif\",\"iconOpen\":\"../resources/images/img/img_category_on.gif\", \"iconClose\":\"../resources/images/img/img_category_off.gif\"}]";
					
			    	JSONArray jsonConts = (JSONArray)JSONSerializer.toJSON( conts );
	
					String newId = "1";
			    	
			    	JSONObject newCont = new JSONObject();
			    	newCont.put("id", Integer.parseInt(newId));
			    	newCont.put("pId", Integer.parseInt("999"));
			    	newCont.put("name", (String)bbsObject.get("boardName"));
			    	newCont.put("boardId", boardId);
			    	newCont.put("boardKind", bbsBoardInfo.getBoardKind());
			    	newCont.put("boardForm", bbsBoardInfo.getBoardForm());
			    	newCont.put("boardFormSpec", bbsBoardInfo.getBoardFormSpec());
			    	newCont.put("icon", "../resources/images/img/img_board.gif");
			    	
			    	jsonConts.add(newCont);
			    	
			    	conts = jsonConts.toString();
			    	
					categoryVo = new CategoryVO();
					categoryVo.setId("SYSTEM");
					categoryVo.setConts(conts);
					categoryVo.setDelYn("N");
					categoryVo.setRegrId(info.getId());
					categoryVo.setRegrName(info.getName());
					categoryVo.setUpdrId(info.getId());
					categoryVo.setUpdrName(info.getName());
					
					board100Mapper.insertPbsUserCategoryInfo(categoryVo);
				}else{
					
					categoryVo = new CategoryVO();
					categoryVo.setId("SYSTEM");
					
					categoryVo = board100Mapper.selectMyCategoryCont(categoryVo);
					
					conts = categoryVo.getConts();
					
			    	JSONArray jsonConts = (JSONArray)JSONSerializer.toJSON( conts );
	
			    	
			    	String newId = ""+(jsonConts.size()+1);
			    	
			    	JSONObject newCont = new JSONObject();
			    	newCont.put("id", Integer.parseInt(newId));
			    	newCont.put("pId", Integer.parseInt("999"));
			    	newCont.put("name", (String)bbsObject.get("boardName"));
			    	newCont.put("boardId", boardId);
			    	newCont.put("boardKind", bbsBoardInfo.getBoardKind());
			    	newCont.put("boardForm", bbsBoardInfo.getBoardForm());
			    	newCont.put("boardFormSpec", bbsBoardInfo.getBoardFormSpec());
			    	newCont.put("icon", "../resources/images/img/img_board.gif");
			    	
			    	jsonConts.add(newCont);
			    	
					conts = jsonConts.toString();
					
					categoryVo = new CategoryVO();
					categoryVo.setId("SYSTEM");
					categoryVo.setConts(conts);
					
					board100Mapper.updatePbsUserCategoryInfo(categoryVo);
				}
			}
			else
			{
				CategoryVO categoryVo = new CategoryVO();
				categoryVo.setId("SYSTEM");
				
				categoryVo = board100Mapper.selectMyCategoryCont(categoryVo);
				//conts = categoryVo.getConts();
				
				JSONArray jsonArrCate =JSONArray.fromObject(categoryVo.getConts());
		    	for(int i=0; i < jsonArrCate.size(); i++)
		    	{
		    		JSONObject jo = (JSONObject)jsonArrCate.get(i);
		    		
		    		if (jo.getString("boardId").equals(boardId))
		    		{
		    			jo.element("boardName", bbsBoardInfo.getBoardName());
		    			jo.element("name", bbsBoardInfo.getBoardName());
		    			jo.element("boardKind", bbsBoardInfo.getBoardKind());
		    			break;
		    		}		    		
		    	}
		    	
		    	categoryVo = new CategoryVO();
				categoryVo.setId("SYSTEM");
				categoryVo.setConts(jsonArrCate.toString());
				
				board100Mapper.updatePbsUserCategoryInfo(categoryVo);

			}

		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
	 * 공용 게시판  목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 목록
	 * @exception Exception
	 */
    public List<BbsBoardChartPopVO> selectAdminBbsChartPopList(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getAdminBbsChartPopList(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    		
    }
    
    /**
	 * 공용 게시판 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int selectAdminBbsChartPopListTotCnt(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getAdminBbsChartPopListTotCnt(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    /**
	 * 사용자 게시판  목록을 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 목록
	 * @exception Exception
	 */
    public List<BbsBoardChartPopVO> selectMyBbsChartPopList(BoardSearchVO boardSearchVO) throws Exception {
    	try{
    		return board100Mapper.getMyBbsChartPopList(boardSearchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시판 총 갯수를 조회한다.
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int selectMyBbsChartPopListTotCnt(BoardSearchVO boardSearchVO) throws Exception {
    	try{
    		return board100Mapper.getMyBbsChartPopListTotCnt(boardSearchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    /**
	 * 게시판 생성 내용을 조회한다.
	 * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시판 생성 내용
	 * @exception Exception
	 */
    public List<BbsBoardInfoVO> getAdminBbsBoardInfoList(BbsBoardInfoVO bbsVO) throws Exception {
    	try{
    		return board100Mapper.getAdminBbsBoardInfoList(bbsVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시판 생성 사용자 매핑 정보를 조회한다.
	 * @param BbsBoardUserMapVO - 조회할 정보가 담긴 VO
	 * @return 사용자 매핑 정보
	 * @exception Exception
	 */
    public List<BbsBoardUserMapVO> getAdminBbsBoardUserMapList(BbsBoardUserMapVO vo) throws Exception {
    	try{
    		return board100Mapper.getAdminBbsBoardUserMapList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    /**
	 * 게시판 생성 추가 항목 정보를 조회한다.
	 * @param BbsBoardAddItemVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 */    
    public List<BbsBoardAddItemVO> getAdminBbsBoardAddItemList(BbsBoardAddItemVO vo) throws Exception {
    	try{
    		return board100Mapper.getAdminBbsBoardAddItemList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시판 이름 조회
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 사용자 게시판 이름 정보
	 * @exception Exception
	 */ 
    public int selectUserIsBoardName(PbsUserBoardInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getUserIsBoardName(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    /**
	* 개인 게시판생성 
	* @param json, session
	* @return 성공여부
	* @throws Exception
	*/
    public String createUserBbsList(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			PbsUserBoardInfoVO boardInfo = new PbsUserBoardInfoVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			/*
			logger.debug("info.getId : "+(String)info.getId());
			logger.debug("info.getName : "+(String)info.getName());
			logger.debug("boardName : "+(String)bbsObject.get("boardName"));
			logger.debug("boardForm : "+(String)bbsObject.get("boardForm"));
			logger.debug("boardTp : "+(String)bbsObject.get("boardTp"));
			logger.debug("boardFormSpec : "+(String)bbsObject.get("boardFormSpec"));
			logger.debug("boardOperYn : "+(String)bbsObject.get("boardOperYn"));
			*/
			/*
			boardInfo.setBoardTp((String)bbsObject.get("boardTp"));
			boardInfo.setBoardOwnrId((String)info.getId());
			boardInfo.setBoardOwnrName((String)info.getName());
			boardInfo.setBoardName((String)bbsObject.get("boardName"));
			boardInfo.setBoardForm((String)bbsObject.get("boardForm"));
			boardInfo.setBoardFormSpec((String)bbsObject.get("boardFormSpec"));
			boardInfo.setBoardOperYn((String)bbsObject.get("boardOperYn"));
			boardInfo.setDelYn("N");
			boardInfo.setRegrId((String)info.getId());
			boardInfo.setRegrName((String)info.getName());
			boardInfo.setUpdrId((String)info.getId());
			boardInfo.setUpdrName((String)info.getName());
			
			String saveKind = (String)bbsObject.get("boardId");
			*/
			boardInfo.setBoardId("");
			boardInfo.setBoardTp((String )bbsObject.get("boardTp"));			
			boardInfo.setBoardOwnrId(info.getId()+"");
			boardInfo.setBoardOwnrName(info.getName()+"");			
			boardInfo.setBoardName((String )bbsObject.get("boardName"));
			boardInfo.setBoardKind((String )bbsObject.get("boardKind"));
			boardInfo.setBoardForm((String )bbsObject.get("boardForm"));
			boardInfo.setBoardFormSpec((String )bbsObject.get("boardFormSpec"));
			boardInfo.setMoblLinkYn((String )bbsObject.get("moblLinkYn"));
			boardInfo.setBoardOperYn((String )bbsObject.get("boardOperYn"));
			boardInfo.setAdminAlertYn((String )bbsObject.get("adminAlertYn"));
			boardInfo.setBoardOpenDiv((String )bbsObject.get("boardOpenDiv"));//게시판공개구분
			boardInfo.setBoardOpenDivSpec((String )bbsObject.get("boardOpenDivSpec"));//게시판공개구분상세
			boardInfo.setBoardOperDiv((String )bbsObject.get("boardOperDiv"));//게사판운영구분
			boardInfo.setBoardOperBgnDttm((String )bbsObject.get("boardOperBgnDttm"));
			boardInfo.setBoardOperEndDttm((String )bbsObject.get("boardOperEndDttm"));
			boardInfo.setNotiTermDiv((String )bbsObject.get("notiTermDiv"));
			boardInfo.setPublAsgnDiv((String )bbsObject.get("publAsgnDiv"));
			boardInfo.setPublAsgnDivSpec((String )bbsObject.get("publAsgnDivSpec"));
			boardInfo.setBoardAnmtUseYn((String )bbsObject.get("boardAnmtUseYn"));
			boardInfo.setBoardExplUseYn((String )bbsObject.get("boardExplUseYn"));
			boardInfo.setBoardExpl((String )bbsObject.get("boardExpl"));
			boardInfo.setMakrDispDiv((String )bbsObject.get("makrDispDiv"));
			boardInfo.setNotiReadmanAsgnYn((String )bbsObject.get("notiReadmanAsgnYn"));
			boardInfo.setAgrmOppUseYn((String )bbsObject.get("agrmOppUseYn"));
			boardInfo.setReplyWrteDiv((String )bbsObject.get("replyWrteDiv"));
			boardInfo.setOpnFileUploadYn((String )bbsObject.get("opnFileUploadYn"));
			boardInfo.setOpnFileUploadCnt((String )bbsObject.get("opnFileUploadCnt"));
			boardInfo.setOpnWrteDiv((String )bbsObject.get("opnWrteDiv"));
			boardInfo.setOpnReplyUseDiv((String )bbsObject.get("opnReplyUseDiv"));
			boardInfo.setOpnRealnameDiv((String )bbsObject.get("opnRealnameDiv"));
			boardInfo.setLikeUseYn((String )bbsObject.get("likeUseYn"));
			boardInfo.setNickUseYn((String )bbsObject.get("nickUseYn"));
			boardInfo.setSmsUseYn((String )bbsObject.get("smsUseYn"));	
			boardInfo.setEditDiv((String )bbsObject.get("editDiv"));
			boardInfo.setNotiEmailSendYn((String )bbsObject.get("notiEmailSendYn"));
			boardInfo.setOutsideOpenDiv((String )bbsObject.get("outsideOpenDiv"));
			boardInfo.setOutsideOpenDivSpec((String )bbsObject.get("outsideOpenDivSpec"));
			boardInfo.setPopupYn((String )bbsObject.get("popupYn"));		
			boardInfo.setBriefYn((String )bbsObject.get("briefYn"));
			boardInfo.setFcode((String )bbsObject.get("fcode"));		
			boardInfo.setReadDiv((String )bbsObject.get("readDiv"));
			boardInfo.setDelYn((String )bbsObject.get("delYn"));
			
			
			
			
			boardInfo.setRegrId(info.getId()+"");
	
			boardInfo.setRegrName(info.getName()+"");
		
			boardInfo.setUpdrId(info.getId()+"");
	
			boardInfo.setUpdrName(info.getName()+"");
			
			String saveKind = (String)bbsObject.get("boardId");
			
			if (saveKind.equals(""))  //입력
			{
				board100Mapper.insertPbsUserBoardInfo(boardInfo);
			}
			else  //수정
			{
				boardInfo.setBoardId((String)bbsObject.get("boardId"));				
				board100Mapper.updatePbsUserBoardInfo(boardInfo);
				PbsUserBoardPartInfoVO partInfoVO = new PbsUserBoardPartInfoVO();				
				partInfoVO.setBoardId((String)bbsObject.get("boardId"));
				board100Mapper.deletePbsUserBoardPartInfo(partInfoVO);
			}			
			
			String boardId = boardInfo.getBoardId();
			
			//참여부서 지정
			JSONArray jsonArr = (JSONArray)bbsObject.get("PbsPartDeptList");			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PbsUserBoardPartInfoVO partInfoVO = new PbsUserBoardPartInfoVO();
				logger.debug("userDiv : "+(String)obj.get("userDiv"));
				partInfoVO.setUserDiv((String)obj.get("userDiv"));
				partInfoVO.setBoardId(boardId);
				partInfoVO.setUserId((String)obj.get("userId"));				
				partInfoVO.setCateUseYn((String)obj.get("cateUseYn"));
				partInfoVO.setDelYn((String)obj.get("delYn"));
				partInfoVO.setRegrId(info.getId());
				partInfoVO.setRegrName(info.getName());
				partInfoVO.setUpdrId(info.getId());
				partInfoVO.setUpdrName(info.getName());
				board100Mapper.insertPbsUserBoardPartInfo(partInfoVO);
			}
			
			//참여자 지정
			jsonArr = (JSONArray)bbsObject.get("PbsPartPsnList");			
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				PbsUserBoardPartInfoVO partInfoVO = new PbsUserBoardPartInfoVO();
				logger.debug("userDiv : "+(String)obj.get("userDiv"));
				partInfoVO.setUserDiv((String)obj.get("userDiv"));
				partInfoVO.setBoardId(boardId);
				partInfoVO.setUserId((String)obj.get("userId"));				
				partInfoVO.setCateUseYn((String)obj.get("cateUseYn"));
				partInfoVO.setDelYn((String)obj.get("delYn"));
				partInfoVO.setRegrId(info.getId());
				partInfoVO.setRegrName(info.getName());
				partInfoVO.setUpdrId(info.getId());
				partInfoVO.setUpdrName(info.getName());
				board100Mapper.insertPbsUserBoardPartInfo(partInfoVO);
				
				//게시판이 생성됨을 참여자 타임라인 등록한다.
				PsnTmlnInfoVO psnTmlnInfoVO = new PsnTmlnInfoVO();
				psnTmlnInfoVO.setUserId((String)obj.get("userId"));
				psnTmlnInfoVO.setUserName((String)obj.get("userName"));
				psnTmlnInfoVO.setRelaUrl(boardId);  //참여 게시판은 보드 ID만 넣는다. 20130717
				String tmlnConts =info.getName()  +" 님이 생성하신 ["+boardInfo.getBoardName()+"] 게시판에 참여자로 지정되었습니다";
				psnTmlnInfoVO.setTmlnConts(tmlnConts);
				board100Mapper.insertPsnTmlnInfo(psnTmlnInfoVO);
			}
			
			PbsUserBoardPartInfoVO partInfoVO = new PbsUserBoardPartInfoVO();
			partInfoVO.setUserDiv("EMP");
			partInfoVO.setBoardId(boardId);
			partInfoVO.setUserId(info.getId());				
			partInfoVO.setCateUseYn("N");
			partInfoVO.setDelYn("N");
			partInfoVO.setRegrId(info.getId());
			partInfoVO.setRegrName(info.getName());
			partInfoVO.setUpdrId(info.getId());
			partInfoVO.setUpdrName(info.getName());
			board100Mapper.insertPbsUserBoardPartInfo(partInfoVO);
			
			if (saveKind.equals(""))  //입력
			{
				//my카테고리 업데이트
				CategoryVO categoryVo = new CategoryVO();
				categoryVo.setId(info.getId());
				
				
				int categoryCnt = board100Mapper.selectMyCategoryContCnt(categoryVo);
				
				String conts = "";
				
				if(categoryCnt == 0){
	
					conts = "[]";
					
			    	JSONArray jsonConts = (JSONArray)JSONSerializer.toJSON( conts );
	
					String newId = "1";
			    	
			    	JSONObject newCont = new JSONObject();
			    	newCont.put("id", Integer.parseInt(newId));
			    	newCont.put("pId", Integer.parseInt("999"));
			    	newCont.put("name", (String)bbsObject.get("boardName"));
			    	newCont.put("boardId", boardId);
			    	newCont.put("boardKind", boardInfo.getBoardKind());
			    	newCont.put("boardForm", boardInfo.getBoardForm());
			    	newCont.put("boardFormSpec", boardInfo.getBoardFormSpec());
			    	newCont.put("icon", "../resources/images/img/bl_dot.png");
			    	
			    	jsonConts.add(newCont);
			    	
			    	conts = jsonConts.toString();
			    	
					categoryVo = new CategoryVO();
					categoryVo.setId(info.getId());
					categoryVo.setConts(conts);
					categoryVo.setDelYn("N");
					categoryVo.setRegrId(info.getId());
					categoryVo.setRegrName(info.getName());
					categoryVo.setUpdrId(info.getId());
					categoryVo.setUpdrName(info.getName());
					
					board100Mapper.insertPbsUserCategoryInfo(categoryVo);
				}else{
					
					categoryVo = new CategoryVO();
					categoryVo.setId(info.getId());
					
					categoryVo = board100Mapper.selectMyCategoryCont(categoryVo);
					conts = categoryVo.getConts();
			    	JSONArray jsonConts = (JSONArray)JSONSerializer.toJSON( conts );
	
					//String newId = "9000";
					
					String newId = ""+(jsonConts.size()+1);
					
			    	for(int i = 0; i < jsonConts.size(); i++){
			    		JSONObject obj = (JSONObject)jsonConts.get(i);
			    		//if(obj.get("pId").equals("999")){
			    			if(Integer.parseInt(newId) <= Integer.parseInt( obj.getString("id"))){
			    				newId = Integer.toString(Integer.parseInt(obj.getString("id"))+1);
			    			}
			    		//}
			    	}
			    	
			    	JSONObject newCont = new JSONObject();
			    	newCont.put("id", Integer.parseInt(newId));
			    	newCont.put("pId", Integer.parseInt("999"));
			    	newCont.put("name", (String)bbsObject.get("boardName"));
			    	newCont.put("boardId", boardId);
			    	newCont.put("boardKind", boardInfo.getBoardKind());
			    	newCont.put("boardForm", boardInfo.getBoardForm());
			    	newCont.put("boardFormSpec", boardInfo.getBoardFormSpec());
			    	newCont.put("icon", "../resources/images/img/bl_dot.png");
			    	
			    	jsonConts.add(newCont);
			    	
					conts = jsonConts.toString();
					
					categoryVo = new CategoryVO();
					categoryVo.setId(info.getId());
					categoryVo.setConts(conts);
					
					board100Mapper.updatePbsUserCategoryInfo(categoryVo);
				}
				
				boardInfo.setBoardOwnrId(info.getId());
				
				//참여게시판 등록				
				board100Mapper.insertPbsUserBoardCateUse(boardInfo);
			}
			else
			{
				CategoryVO categoryVo = new CategoryVO();
				categoryVo.setId(info.getId());
				
				categoryVo = board100Mapper.selectMyCategoryCont(categoryVo);
				//conts = categoryVo.getConts();
				
				JSONArray jsonArrCate =JSONArray.fromObject(categoryVo.getConts());
		    	for(int i=0; i < jsonArrCate.size(); i++)
		    	{
		    		JSONObject jo = (JSONObject)jsonArrCate.get(i);
		    		
		    		if (jo.getString("boardId").equals(boardId))
		    		{
		    			jo.element("boardName", boardInfo.getBoardName());
		    			jo.element("name", boardInfo.getBoardName());
		    			jo.element("boardForm", boardInfo.getBoardForm());
		    			jo.element("boardFormSpec", boardInfo.getBoardFormSpec());
		    			break;
		    		}		    		
		    	}
		    	
		    	
		    	categoryVo = new CategoryVO();
				categoryVo.setId(info.getId());
				categoryVo.setConts(jsonArrCate.toString());
				
				board100Mapper.updatePbsUserCategoryInfo(categoryVo);
				
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
	 * 개인 게시판 생성 정보 조회 
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 */    
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getPbsUserBoardInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시판 참여 정보 조회 
	 * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 */    
    public List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getPbsUserBoardPartInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    /*
     * 게시물 입력
     * */
    public BbsNotiInfoVO insertBbsNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception {
    	
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
        String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
        //String REAL_DIR = PortalxpertConfigUtils.getString("upload.real.dir");        
    	BbsNotiInfoVO vo = new BbsNotiInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			JSONObject bbsNotiObject = JSONObject.fromObject(json);
			//게시물 기본 테이블			
			vo.setNotiId((String)bbsNotiObject.get("notiId")) ;
			vo.setUpNotiId((String)bbsNotiObject.get("upNotiId")) ;
			vo.setSortSeq( bbsNotiObject.getInt("sortSeq")) ;
			vo.setBoardId((String)bbsNotiObject.get("boardId")) ;
			vo.setEmgcYn((String)bbsNotiObject.get("emgcYn")) ;
			vo.setAnmtYn((String)bbsNotiObject.get("anmtYn")) ;
			vo.setPopupYn((String)bbsNotiObject.get("popupYn")) ;
			vo.setBriefYn((String)bbsNotiObject.get("briefYn")) ;
			vo.setMoblOpenDiv((String)bbsNotiObject.get("moblOpenDiv")) ;
			vo.setNotiTitle((String)bbsNotiObject.get("notiTitle")) ;
			vo.setNotiTitleOrgn((String)bbsNotiObject.get("notiTitleOrgn")) ;
			vo.setTitleBoldYn((String)bbsNotiObject.get("titleBoldYn")) ;
			vo.setTitleColorDiv((String)bbsNotiObject.get("titleColorDiv")) ;
			vo.setNotiConts((String)bbsNotiObject.get("notiConts")) ;
			vo.setLinkUrl((String)bbsNotiObject.get("linkUrl")) ;
			vo.setNotiTp((String)bbsNotiObject.get("notiTp")) ;
			vo.setNotiKind((String)bbsNotiObject.get("notiKind")) ;
			vo.setNickUseYn((String)bbsNotiObject.get("nickUseYn")) ;			
			vo.setUserNick((String)bbsNotiObject.get("userNick")) ;			
			vo.setEditDiv((String)bbsNotiObject.get("editDiv")) ;
			vo.setRsrvYn((String)bbsNotiObject.get("rsrvYn")) ;
			vo.setNotiBgnDttm((String)bbsNotiObject.get("notiBgnDttm")) ;
			vo.setNotiEndDttm((String)bbsNotiObject.get("notiEndDttm")) ;
			vo.setNotiOpenDiv((String)bbsNotiObject.get("notiOpenDiv")) ;
			vo.setNotiOpenDivSpec((String)bbsNotiObject.get("notiOpenDivSpec")) ;
			vo.setPublAsgnDiv((String)bbsNotiObject.get("publAsgnDiv")) ;
			vo.setPublAsgnDivSpec((String)bbsNotiObject.get("publAsgnDivSpec")) ;
			vo.setReplyPrmsYn((String)bbsNotiObject.get("replyPrmsYn")) ;
			vo.setReplyMakrRealnameYn((String)bbsNotiObject.get("replyMakrRealnameYn")) ;
			vo.setOpnPrmsYn((String)bbsNotiObject.get("opnPrmsYn")) ;
			vo.setOpnMakrRealnameYn((String)bbsNotiObject.get("opnMakrRealnameYn")) ;
			vo.setNotiReadCnt( bbsNotiObject.getInt("notiReadCnt")) ;
			vo.setNotiOpnCnt( bbsNotiObject.getInt("notiOpnCnt")) ;
			vo.setNotiAgrmCnt( bbsNotiObject.getInt("notiAgrmCnt")) ;
			vo.setNotiOppCnt( bbsNotiObject.getInt("notiOppCnt")) ;
			vo.setNotiLikeCnt( bbsNotiObject.getInt("notiLikeCnt")) ;
			vo.setBfBoardId((String)bbsNotiObject.get("bfBoardId")) ;
			vo.setBfNotiId((String)bbsNotiObject.get("bfNotiId")) ;
			vo.setStatCode((String)bbsNotiObject.get("statCode")) ;			
			vo.setMakrIp(CommUtil.getClientIpAddr(request)) ;
			vo.setBrghCode((String)bbsNotiObject.get("brghCode")) ;
			vo.setCdlnDeptCode((String)bbsNotiObject.get("cdlnDeptCode")) ;
			vo.setCdlnDeptName((String)bbsNotiObject.get("cdlnDeptName")) ;
			vo.setCdlnDeptFname((String)bbsNotiObject.get("cdlnDeptFname")) ;
			vo.setCdlnObjrName((String)bbsNotiObject.get("cdlnObjrName")) ;
			vo.setCdlnEvntCode((String)bbsNotiObject.get("cdlnEvntCode")) ;
			vo.setDelYn((String)bbsNotiObject.get("delYn")) ;			
			vo.setRegDttm((String)bbsNotiObject.get("regDttm")) ;			
			vo.setUpdDttm((String)bbsNotiObject.get("updDttm")) ;			
			vo.setNotiTagLst((String)bbsNotiObject.get("notiTagLst")) ;
			vo.setAgrmOppYn((String)bbsNotiObject.get("agrmOppYn")) ;
			vo.setIsAdmin(bbsNotiObject.getString("isAdmin"));
			String notiReadmanAsgnYn = bbsNotiObject.getString("notiReadmanAsgnYn");
						
			if (vo.getNotiId().equals(""))  //입력
			{
				vo.setUserName(info.getName()) ;
				vo.setUpdrId(info.getId()) ;
				vo.setUpdrName(info.getName()) ;
				vo.setRegrId(info.getId()) ;
				vo.setRegrName(info.getName()) ;
				vo.setUserId(info.getId()) ;
				vo.setDeptCode(info.getOucode()) ;
				vo.setDeptName(info.getOu()) ;
				vo.setDeptFname(info.getOrgfullname()) ;
				board100Mapper.insertBbsNotiInfo(vo);
			}
			else  //수정
			{

				
				//관리자라도 자기 글이면 수정일자 업데이트 처리
				if (bbsNotiObject.getString("updrId").equals(info.getId()))
				{
					vo.setIsAdmin("N");
				}
				//vo.setUserId((String)bbsNotiObject.get("userId"));
				vo.setUpdrId(info.getId()) ;
				vo.setUpdrName(info.getName()) ;				
				board100Mapper.updateBbsNotiInfo(vo);	
				
				board100Mapper.deleteBbsNotiApndFile(vo);				
				BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
				surveyVO.setNotiId(vo.getNotiId());
				board100Mapper.deleteBbsNotiSurveyExmpl(surveyVO);
				board100Mapper.deleteBbsNotiSurvey(surveyVO);
				board100Mapper.deleteBbsNotiUserMap(vo);
				
			}
			//보드 권한이 게시물 지정일 경우만 입력 한다.
			if (notiReadmanAsgnYn.equals("Y"))
			{
				int totPsnCnt = 0;
				int totDeptCnt = 0;
				
				JSONArray jsonArr = (JSONArray)bbsNotiObject.get("NotiOpenDivDeptList");  //부서지정

				for(int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					BbsNotiUserMapVO userVO = new BbsNotiUserMapVO();				
					userVO.setNotiId(vo.getNotiId());
					userVO.setUserDiv((String)obj.get("div"));
					userVO.setUserId((String)obj.get("id"));
					userVO.setMngAuth((String)obj.get("auth"));
					userVO.setDelYn("N");
					userVO.setRegrId(info.getId());
					userVO.setRegrName(info.getName());
					userVO.setRegDttm("");
					userVO.setUpdrId(info.getId());
					userVO.setUpdrName(info.getName());
					userVO.setUpdDttm("");
					
					if (userVO.getUserId().equals(info.getOucode())||userVO.getUserDiv().equals("PUB") )
					{
						totDeptCnt++;
						if (userVO.getUserDiv().equals("PUB"))  //전체공개면 부서,개인정보 INSERT X
						{
							totPsnCnt++;
						}
					}
					
					board100Mapper.insertBbsNotiUserMap(userVO);
				}
				jsonArr = (JSONArray)bbsNotiObject.get("NotiOpenDivEmpList");  //개인지정				
				for(int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					BbsNotiUserMapVO userVO = new BbsNotiUserMapVO();				
					userVO.setNotiId(vo.getNotiId());
					userVO.setUserDiv((String)obj.get("div"));
					userVO.setUserId((String)obj.get("id"));
					userVO.setMngAuth((String)obj.get("auth"));
					userVO.setDelYn("N");
					userVO.setRegrId(info.getId());
					userVO.setRegrName(info.getName());
					userVO.setRegDttm("");
					userVO.setUpdrId(info.getId());
					userVO.setUpdrName(info.getName());
					userVO.setUpdDttm("");
					
					if (userVO.getUserId().equals(info.getId()))
					{
						totPsnCnt++;
					}
					board100Mapper.insertBbsNotiUserMap(userVO);
					
				}
				//입력한 권한 정보가 없으면 작성자 정보를 입력한다.
				if (totPsnCnt == 0)
				{
					
					BbsNotiUserMapVO userVO = new BbsNotiUserMapVO();				
					userVO.setNotiId(vo.getNotiId());
					userVO.setUserDiv("EMP");
					userVO.setUserId(info.getId());
					userVO.setMngAuth("RED");
					userVO.setDelYn("N");
					userVO.setRegrId(info.getId());
					userVO.setRegrName(info.getName());
					userVO.setRegDttm("");
					userVO.setUpdrId(info.getId());
					userVO.setUpdrName(info.getName());
					userVO.setUpdDttm("");
					
					board100Mapper.insertBbsNotiUserMap(userVO);
					
				}
				//내부서 정보가 들어가있지 않은경우 부서업무 공지면 권한을 할당한다.
				if (totDeptCnt == 0)
				{
					if  (vo.getBoardId().equals("BBS000001"))
					{
						BbsNotiUserMapVO userVO = new BbsNotiUserMapVO();				
						userVO.setNotiId(vo.getNotiId());
						userVO.setUserDiv("DPT");
						userVO.setUserId(info.getOucode());
						userVO.setMngAuth("RED");
						userVO.setDelYn("N");
						userVO.setRegrId(info.getId());
						userVO.setRegrName(info.getName());
						userVO.setRegDttm("");
						userVO.setUpdrId(info.getId());
						userVO.setUpdrName(info.getName());
						userVO.setUpdDttm("");
						board100Mapper.insertBbsNotiUserMap(userVO);
					}
				}
				
				
			}
			
			
		
			if (vo.getNotiKind().equals(Constant.BOARD_KIND_020.getVal()))  //이미지
			{
				JSONArray jsonArr2 = (JSONArray)bbsNotiObject.get("AppendList");
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
					apndVO.setNotiId( vo.getNotiId()) ;
					apndVO.setApndFileSeq(obj.getInt("apndFileSeq")) ;
					apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
					apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
					apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
					apndVO.setApndFileName( (String)obj.get("apndFileName")) ;					
					apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;					
					apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;
					apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
					apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
					apndVO.setAdminRcmdDttm((String)obj.get("adminRcmdDttm"));
					apndVO.setAdminRcmdYn((String)obj.get("adminRcmdYn"));
					apndVO.setDelYn( (String)obj.get("delYn")) ;
					apndVO.setRegrId( (String)obj.get("regrId")) ;
					apndVO.setRegrName( (String)obj.get("regrName")) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( (String)obj.get("updrId")) ;
					apndVO.setUpdrName( (String)obj.get("updrName")) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;
					apndVO.setReadCnt(obj.getInt("readCnt"));
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					apndVO.setApndFilePath(realPath);
					apndVO.setMvpKey((String)obj.get("mvpKey"));
					board100Mapper.insertBbsNotiApndFile(apndVO);
				}
			}
			else if (vo.getNotiKind().equals(Constant.NOTI_KIND_030.getVal()))  //동영상
			{
				JSONArray jsonArr2 = (JSONArray)bbsNotiObject.get("AppendList");
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
					apndVO.setNotiId( vo.getNotiId()) ;
					apndVO.setApndFileSeq(obj.getInt("apndFileSeq")) ;
					apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
					apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
					apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
					apndVO.setApndFileName( (String)obj.get("apndFileName")) ;					
					apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), PortalxpertConfigUtils.getString("upload.thumbnail.dir"), apndVO.getApndFileName()); //추가
					apndVO.setApndFilePath(realPath);
					apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;
					apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
					apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
					apndVO.setAdminRcmdYn((String)obj.get("adminRcmdYn"));
					apndVO.setAdminRcmdDttm((String)obj.get("adminRcmdDttm"));
					apndVO.setReadCnt(obj.getInt("readCnt"));					
					apndVO.setDelYn( (String)obj.get("delYn")) ;
					apndVO.setRegrId( info.getId()) ;
					apndVO.setRegrName( info.getName()) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( info.getId()) ;
					apndVO.setUpdrName( info.getName()) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;
					apndVO.setMvpKey(obj.getInt("mvpKey")+"");

					board100Mapper.insertBbsNotiApndFile(apndVO);					
				}
			}
			else if (vo.getNotiKind().equals(Constant.NOTI_KIND_040.getVal()))  //설문
			{
		        
				JSONArray jsonArr2 = (JSONArray)bbsNotiObject.get("AppendList");				
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
					surveyVO.setSurveyNo( obj.getInt("surveyNo")) ;
					surveyVO.setRelaNotiKind((String)obj.get("relaNotiKind"));
					surveyVO.setNotiId( vo.getNotiId() ) ;
					surveyVO.setTmpNotiSeq( obj.getInt("tmpNotiSeq")) ;
					surveyVO.setTmlnSeq( obj.getInt("tmpNotiSeq")) ;
					surveyVO.setSurveyClosDttm( (String)obj.get("surveyClosDttm")) ;
					surveyVO.setSurveyRsltOpenYn( (String)obj.get("surveyRsltOpenYn")) ;
					surveyVO.setSurveyConts( (String)obj.get("surveyConts")) ;
					surveyVO.setSurveyTp( (String)obj.get("surveyTp")) ;
					surveyVO.setDelYn( (String)obj.get("delYn")) ;
					surveyVO.setRegrId( info.getId()) ;
					surveyVO.setRegrName( info.getName()) ;
					surveyVO.setRegDttm( (String)obj.get("regDttm")) ;
					surveyVO.setUpdrId( info.getId()) ;
					surveyVO.setUpdrName( info.getName()) ;
					surveyVO.setUpdDttm( (String)obj.get("updDttm")) ;
					
					board100Mapper.insertBbsNotiSurvey(surveyVO);
					
					JSONArray jsonArr3 = (JSONArray)obj.get("apndExmpList");
										
					for(int j=0; j < jsonArr3.size(); j++ )
					{
						JSONObject exmplObj = (JSONObject)jsonArr3.get(j);
						
						BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
						surveyExmplVO.setSurveyNo( surveyVO.getSurveyNo()) ;
						surveyExmplVO.setExmplNo( exmplObj.getInt("exmplNo")) ;
						surveyExmplVO.setExmplConts((String)exmplObj.get("exmplConts")) ;
						
						//surveyExmplVO.setImgPath( (String)exmplObj.get("imgPath")) ;						
						surveyExmplVO.setImgPath(SAVE_DIR+'/'+CommUtil.getDateString("yyyyMMdd")+"/") ;
						surveyExmplVO.setImgName( (String)exmplObj.get("imgName")) ;
						
						String realPath = CommUtil.apndFileCopy(surveyExmplVO.getImgPath(), surveyExmplVO.getImgName());
						surveyExmplVO.setImgPath(WEB_DIR+'/'+realPath);
						
						surveyExmplVO.setPrvwPath( (String)exmplObj.get("prvwPath")) ;
						surveyExmplVO.setPrvwName( (String)exmplObj.get("prvwName")) ;
						surveyExmplVO.setTotCnt( exmplObj.getInt("totCnt")) ;
						surveyExmplVO.setRsltCnt( exmplObj.getInt("rsltCnt")) ;
						surveyExmplVO.setRsltRto( exmplObj.getInt("rsltRto")) ;
						surveyExmplVO.setDelYn( (String)exmplObj.get("delYn")) ;
						surveyExmplVO.setRegrId( info.getId()) ;
						surveyExmplVO.setRegrName( info.getName()) ;
						surveyExmplVO.setRegDttm( (String)exmplObj.get("regDttm")) ;
						surveyExmplVO.setUpdrId( info.getId()) ;
						surveyExmplVO.setUpdrName( info.getName()) ;
						surveyExmplVO.setUpdDttm( (String)exmplObj.get("updDttm")) ;
						
						board100Mapper.insertBbsNotiSurveyExmpl(surveyExmplVO);
					}

				}
			}			
			
			
			//첨부파일 처리
			JSONArray jsonArr3 = (JSONArray)bbsNotiObject.get("AppendFileList");
			
			
			for (int i=0; i < jsonArr3.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr3.get(i);
				BbsNotiApndFileVO apndVO = new BbsNotiApndFileVO();
				apndVO.setNotiId( vo.getNotiId()) ;
				apndVO.setApndFileSeq(obj.getInt("apndFileSeq")) ;
				apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
				apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
				apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
				apndVO.setApndFileName( (String)obj.get("apndFileName")) ;				
				apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;				
				apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;
				apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
				apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
				apndVO.setDelYn( (String)obj.get("delYn")) ;
				apndVO.setRegrId( info.getId()) ;
				apndVO.setRegrName( info.getName()) ;
				apndVO.setRegDttm( (String)obj.get("regDttm")) ;
				apndVO.setUpdrId( info.getId()) ;
				apndVO.setUpdrName( info.getName()) ;
				apndVO.setUpdDttm( (String)obj.get("updDttm")) ;
				
				String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
				apndVO.setReadCnt(obj.getInt("readCnt"));
				apndVO.setApndFilePath(realPath);
				apndVO.setApndFilePrvwPath(realPath) ;  //첨부파일 수정시 처리 하기 위하여 변경 20130705
				apndVO.setMvpKey((String)obj.get("mvpKey"));

				board100Mapper.insertBbsNotiApndFile(apndVO);
				
			}
			
			//폐쇄게시판 관리자에게 메일
			if(vo.getBoardId() != null){
				BbsBoardInfoVO bbsVO = new BbsBoardInfoVO();
				bbsVO.setBoardId(vo.getBoardId());
				List<BbsBoardInfoVO> list = board100Mapper.getAdminBbsBoardInfoList(bbsVO);
				BbsBoardInfoVO bbsInfo = list.get(0);
				if(Constant.BOARD_KIND_020.getVal().equals(bbsInfo.getBoardKind()) && "Y".equals(bbsInfo.getAdminAlertYn())){
					BbsBoardUserMapVO userVO = new BbsBoardUserMapVO();
	    			userVO.setBoardId(vo.getBoardId());    			
	    			List<BbsBoardUserMapVO> user_list = board100Mapper.getAdminBbsBoardUserMapList(userVO);
	    			
	    			for(BbsBoardUserMapVO mapVo : user_list){
	    				if("ADM".equals(mapVo.getMngAuth()) && !info.getId().equals(mapVo.getUserId())){
	    					CommUtil.relayMailSend(mapVo.getMail(), "portaladmin@crossent.com", "포털 게시판 '"+mapVo.getBoardName()+"'에 새로운 게시물이 작성되었습니다", mapVo.getUserId(), mapVo.getName(), "포털관리자", "/board210/boardViewFrame.do?boardId="+bbsInfo.getBoardId()+"&notiId="+vo.getNotiId()+"&pageIndex=1");
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
	 * 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getBbsNotiInfoList(BbsNotiInfoVO vo) throws Exception {
    	try{
    		return board100Mapper.getBbsNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /*
     * 의견 입력
     * */
    public BbsNotiOpnVO insertBbsNotiOpn(String json, HttpSession session) throws Exception {
    	
    	BbsNotiOpnVO vo = new BbsNotiOpnVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject bbsOpnObject = JSONObject.fromObject(json);
			//의견 테이블
			//vo.setBoardId((String)bbsOpnObject.get("boardId"));
			vo.setNotiId((String)bbsOpnObject.get("notiId"));
			//vo.setOpnSeq(Integer.parseInt((String)bbsOpnObject.get("opnSeq")));
			vo.setUpOpnSeq(Integer.parseInt((String)bbsOpnObject.get("upOpnSeq")));
			vo.setOpnConts((String)bbsOpnObject.get("opnConts"));
			vo.setDelYn((String)bbsOpnObject.get("delYn"));
			vo.setRegrId(info.getId());
			vo.setRegrName(info.getName());
			vo.setRegDttm((String)bbsOpnObject.get("regDttm"));
			vo.setUpdrId((String)bbsOpnObject.get("updrId"));
			vo.setUpdrName((String)bbsOpnObject.get("updrName"));
			vo.setUpdDttm((String)bbsOpnObject.get("updDttm"));
			board100Mapper.insertBbsNotiOpn(vo);			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /**
	 * 의견 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 의견 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiOpnVO> getBbsNotiOpnList(BbsNotiOpnVO vo) throws Exception {
    	try{
    		return board100Mapper.getBbsNotiOpnList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    

    
    /**
	 * 이미지 정보 조회 
	 * @param BbsTmpNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return 이미지 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiApndFileVO> getBbsNotiApndFileList(BbsNotiApndFileVO vo) throws Exception {
    	try{
    		return board100Mapper.getBbsNotiApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시판 리스트 
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 리스트
	 * @exception Exception
	 */
    public List<PbsUserBoardInfoVO> getUserBbsList(BoardSearchVO vo) throws Exception {
    	try{
    		return board100Mapper.getUserBbsList(vo);
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
    public int getUserBbsListTotCnt(BoardSearchVO vo) throws Exception {
    	try{
    		return board100Mapper.getUserBbsListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 사용자 게시판 삭제
     * @param json
     * @param session
     * @return
     * @throws Exception
     */
    public String deleteUserBbs(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			PbsUserBoardInfoVO boardInfo = new PbsUserBoardInfoVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			boardInfo.setBoardId((String )bbsObject.get("boardId"));
			boardInfo.setUpdrId((String)info.getId());
			boardInfo.setUpdrName((String)info.getName());
			
			board100Mapper.deleteUserBbs(boardInfo);
			
			//카테고리의 게시판 정보도 수정한다. 20130706
			CategoryVO cate = new CategoryVO();
	    	cate.setId(info.getId() );
	    	cate = (CategoryVO)board100Mapper.selectMyCategoryCont(cate);
	    	
	    	JSONArray jsonArr =JSONArray.fromObject(cate.getConts());
	    	for(int i=0; i < jsonArr.size(); i++)
	    	{
	    		JSONObject jo = (JSONObject)jsonArr.get(i);
	    		
	    		if (jo.getString("boardId").equals((String )bbsObject.get("boardId")))
	    		{
	    			jsonArr.remove(i);
	    			break;
	    		}
	    	}
	    	cate.setId(info.getId());
			cate.setConts(jsonArr.toString());			
			board100Mapper.updatePbsUserCategoryInfo(cate);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
   	 * 게시판 종합 검색 리스트
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 게시판 리스트
   	 * @exception Exception
   	 */
    public List<BbsTotalSearchVO> getBbsTotalSearchList(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getBbsTotalSearchList(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
   	 * 게시판 종합 검색 리스트 총 갯수
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 게시판 총 갯수
   	 * @exception
   	 */
    public int getBbsTotalSearchListTotCnt(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getBbsTotalSearchListTotCnt(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
   	 * 게시판 종합 검색 리스트
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 게시판 리스트
   	 * @exception Exception
   	 */
    public List<BbsTotalSearchVO> getBbsTotalSearchList2(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getBbsTotalSearchList2(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
   	 * 게시판 종합 검색 리스트 총 갯수
   	 * @param searchVO - 조회할 정보가 담긴 VO
   	 * @return 게시판 총 갯수
   	 * @exception
   	 */
    public int getBbsTotalSearchListTotCnt2(SearchConditionVO searchVO) throws Exception {
    	try{
    		return board100Mapper.getBbsTotalSearchListTotCnt2(searchVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 권한
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 사용자 권한
	 * @exception
	 */
    public String getUserBbsMapList(String userId)throws Exception{
    	try{
	    	BoardSearchVO vo = new BoardSearchVO();
	    	vo.setUserId(userId);
	    	vo.setDeptCode(propertiesService.getString("START_DEPT_CODE"));
	    	return board100Mapper.getUserBbsMapList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 권한 정보 조회
	 * @param BbsNotiUserMapVO - 조회할 정보가 담긴 VO
	 * @return 게시물 권한 정보
	 * @exception
	 */
    public List<BbsNotiUserMapVO> getBbsNotiUserMapList(BbsNotiUserMapVO vo) throws Exception {
    	try{
    		return board100Mapper.getBbsNotiUserMapList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시판 접근권한 조회
     * @param BbsBoardInfoVO - 조회할 정보가 담긴 VO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    public List<BbsBoardInfoVO> getBoardUserMapInfo(BbsBoardInfoVO vo) throws Exception{
    	try{
    		return board100Mapper.getBoardUserMapInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시글 PNUM 가져오기
     * @param BbsNotiInfoVO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    public int getBbsMyPnumInfo(BbsNotiInfoVO vo)throws Exception{
    	try{
    		return board100Mapper.getBbsMyPnumInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 선택한 카테고리의 개인 게시판 정보 조회
     * @param PbsUserBoardInfoVO
     * @return List<PbsUserBoardInfoVO>
     * @exception Exception
     * @auther  
     */    
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoCateList(PbsUserBoardInfoVO vo)throws Exception{
    	try{
    		return board100Mapper.getPbsUserBoardInfoCateList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 선택한 카테고리의 개인 게시판 정보 조회
     * @param PbsUserBoardInfoVO
     * @return List<PbsUserBoardInfoVO>
     * @exception Exception
     * @auther  
     */    
    public List<BbsBoardInfoVO> getBbsBoardInfoCateList(BbsBoardInfoVO vo)throws Exception{
    	try{
    		return board100Mapper.getBbsBoardInfoCateList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public CategoryVO selectMyCategoryCont(CategoryVO vo) throws Exception{
    	try{
    		return  board100Mapper.selectMyCategoryCont(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public int updatePbsUserCategoryInfo(String json, HttpSession session, String admin) throws Exception {
    	try{
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	CategoryVO categoryVo = new CategoryVO();
			if (admin.equals("1"))
			{
				categoryVo.setId("SYSTEM");
			}
			else
			{
				categoryVo.setId(info.getId());
			}
	    	
			
			categoryVo.setConts(json);
			
			return board100Mapper.updatePbsUserCategoryInfo(categoryVo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 공용 게시판 삭제
     * @param json
     * @param session
     * @return
     * @throws Exception
     */
    public String deleteBbsBoardInfo(String json, HttpSession session) throws Exception {
    	
		try {
			
			JSONObject bbsObject = JSONObject.fromObject(json);
			//게시판 기본 테이블
			BbsBoardInfoVO boardInfo = new BbsBoardInfoVO();
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			boardInfo.setBoardId((String )bbsObject.get("boardId"));
			boardInfo.setUpdrId((String)info.getId());
			boardInfo.setUpdrName((String)info.getName());
			
			board100Mapper.deleteBbsBoardInfo(boardInfo);
			
			//카테고리의 게시판 정보도 수정한다. 20130708
			CategoryVO cate = new CategoryVO();
	    	cate.setId("SYSTEM");
	    	cate = (CategoryVO)board100Mapper.selectMyCategoryCont(cate);
	    	
	    	JSONArray jsonArr =JSONArray.fromObject(cate.getConts());
	    	for(int i=0; i < jsonArr.size(); i++)
	    	{
	    		JSONObject jo = (JSONObject)jsonArr.get(i);
	    		
	    		if (jo.getString("boardId").equals((String )bbsObject.get("boardId")))
	    		{
	    			jsonArr.remove(i);
	    			break;
	    		}
	    	}
	    	cate.setId("SYSTEM");
			cate.setConts(jsonArr.toString());			
			board100Mapper.updatePbsUserCategoryInfo(cate);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
    
    /**
     * 게시글 삭제 여부
     * @param 
     * @return String
     * @exception Exception
     * @auther crossent 
     */
    public String getNotiDelYn(String data) throws Exception{
    	try{
	    	JSONObject bbsObject = JSONObject.fromObject(data);
	    	
	    	String notiId = (String)bbsObject.get("notiId")==null?"":(String)bbsObject.get("notiId");
	    	
			int userNotiSeq = bbsObject.getInt("userNotiSeq");
			logger.debug("notiId : "+notiId);
			logger.debug("userNotiSeq : "+userNotiSeq);
			BoardSearchVO vo = new BoardSearchVO();
			vo.setNotiId(notiId);
			vo.setUserNotiSeq(userNotiSeq);
			
			return board100Mapper.getNotiDelYn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 게시글 PNUM 가져오기
     * @param BbsNotiInfoVO
     * @return List<BbsBoardInfoVO>
     * @exception Exception
     * @auther crossent 
     */
    public int getNotiUserAuth(BbsNotiInfoVO vo)throws Exception{
    	try{
    		return board100Mapper.getNotiUserAuth(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    		
    }
}
