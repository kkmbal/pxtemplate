package portalxpert.main.sc.impl;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.main.mapper.MainMapper;
import portalxpert.main.sc.MainService;
import portalxpert.main.vo.MainVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("mainService")
public class MainServiceImpl extends EgovAbstractServiceImpl implements MainService {
	
	/** MainMapper */
    @Resource(name="mainMapper")
    private MainMapper mainMapper;
    
	private final static Logger logger = LoggerFactory.getLogger(MainServiceImpl.class);
	
    /**
	 * 홍보배너 조회
	 * @author crossent	 
	 */
	public List getPromoteBannerList() throws Exception {
		try{
			return mainMapper.getPromoteBannerList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	/**
	 * 최근게시물 - 전체공지 조회
	 * @author crossent	 
	 */
	public List getTotalNoticeList(MainVO mainVO) throws Exception {
		try{
			return mainMapper.getTotalNoticeList(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	/**
	 * 게시물 조회
	 * @author crossent	 
	 */
	public List getBoardList(MainVO mainVO) throws Exception {
		try{
			return mainMapper.getBoardList(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
	/**
	 * 최근게시물 - 게시물 preview
	 * @author crossent	 
	 */
	public MainVO getNoticePreview(MainVO mainVO) throws Exception {
		MainVO resultVO = new MainVO();
		try{
			resultVO = mainMapper.getNoticePreview(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}        
        return resultVO;
	}
	
	/**
	 * 게시물 평가 정보 읽음 건수
	 * @author crossent
	 */
	public int getBbsNotiEvalInfoCnt(MainVO mainVO) throws Exception {
		try{
			return mainMapper.getBbsNotiEvalInfoCnt(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 게시물 평가 정보 설정
	 * @author crossent	 
	 */
	public String setBbsNotiInfoReadCnt(MainVO mainVO) throws Exception {
		try{
			mainMapper.insertBbsNotiEvalInfo(mainVO);
			mainMapper.updateBbsNotiInfoReadCnt(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		return "OK";
	}
	
	/**
	 * 최근게시물 설정- 설정 게시판 조회
	 * @author crossent	 
	 */
	public List getUserBoardList(UserInfoVO userVO) throws Exception {
		try{
			return mainMapper.getUserBoardList(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 최근게시물 설정- 사용자 설정 게시판 건수
	 * @author crossent	 
	 */
	public int getUserBoardSetCnt(UserInfoVO userVO) throws Exception {
		try{
			return mainMapper.getUserBoardSetCnt(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 최근게시물 설정  - 사용자 설정
	 * @author crossent	 
	 */
    public String setSelectedBoard(String json, UserInfoVO userVO) throws Exception {
    	
    	MainVO mainVO = new MainVO();
    	
    	try {
			
			JSONObject boardObject = JSONObject.fromObject(json);
			JSONArray jsonArr = (JSONArray)boardObject.get("selectedBoardList");			
			
			int setCnt = mainMapper.getUserBoardSetCnt(userVO);
			
			if (setCnt > 0) {
				mainMapper.deleteUserBoardSet(userVO);
			}
			
			for (int i=0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject)jsonArr.get(i);	
			
				mainVO.setUserId(userVO.getId());
				mainVO.setUserName(userVO.getName());
				mainVO.setStndBoardSeq(obj.getInt("boardSeq"));
				mainVO.setBoardId((String)obj.get("boardId"));
				mainVO.setBoardName((String)obj.get("boardName"));
				
				mainMapper.insertUserBoardSet(mainVO);			
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
    }
	
	/**
	 * 관심시스템 조회
	 * @author crossent	 
	 */
	public List getTagCloudList(MainVO mainVO) throws Exception {
		try{
			return mainMapper.getTagCloudList(mainVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 업무데스크 - 사용자 설정 조회
	 * @author crossent	 
	 */
	public List getUserWorkDeskList(UserInfoVO userVO) throws Exception {
		try{
			return mainMapper.getUserWorkDeskList(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 업무데스크 사용자 카운트
	 * @author crossent	 
	 */
	public int getUserWorkDeskCnt(UserInfoVO userVO) throws Exception {
		try{
			return mainMapper.getUserWorkDeskCnt(userVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
    
    /**
	 * 업무데스크 기본 카운트
	 * @author crossent	 
	 */
	public int getDefaultWorkDeskCnt() throws Exception {
		try{
			return mainMapper.getDefaultWorkDeskCnt();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 업무데스크 - 사용자 설정
	 * @author crossent
	 */
	public String setUserWorkDesk(String json, UserInfoVO userVO) throws Exception {

		MainVO mainVO = new MainVO();
    	
    	try {
			
			JSONObject workDeskObject = JSONObject.fromObject(json);
			JSONArray jsonArr = (JSONArray)workDeskObject.get("selectedWorkDesk");			
			
			int cnt = mainMapper.getUserWorkDeskCnt(userVO);
			
			if (cnt > 0) {
				mainMapper.deleteUserWorkDesk(userVO);
			}
			
			for (int i=0; i < jsonArr.size(); i++) {
				JSONObject obj = (JSONObject)jsonArr.get(i);	
				
				mainVO.setUserId(userVO.getId());
				mainVO.setLinkCatId((String)obj.get("linkCatId"));
				mainVO.setLinkCd((String)obj.get("linkCd"));
				
				mainMapper.insertUserWorkDesk(mainVO);			
			}
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "OK";
	}
	
	/**
	 * 최근 동영상/이미지
	 * @author crossent
	 */
	public List getRecentVodImageList() throws Exception {
		try{
			return mainMapper.getRecentVodImageList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 많이 본 동영상/이미지
	 * @author crossent
	 */
	public List getMostViewedVodImageList() throws Exception {
		try{
			return mainMapper.getMostViewedVodImageList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공지게시 팝업 조회
	 * @author crossent	
	 */
	public List getNotiPopup() throws Exception {
		try{
			return mainMapper.getNotiPopup();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 핫브리핑 팝업 조회
	 * @author crossent	
	 */
	public List getHotBriefingPopup() throws Exception {
		try{
			return mainMapper.getHotBriefingPopup();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 공지게시 팝업 카운트
	 * @author crossent	
	 */
	public int getNotiPopupCnt() throws Exception {
		try{
			return mainMapper.getNotiPopupCnt();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
	/**
	 * 핫브리핑 팝업 카운트
	 * @author crossent	
	 */
	public int getHotBriefingPopupCnt() throws Exception {
		try{
			return mainMapper.getHotBriefingPopupCnt();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}

}