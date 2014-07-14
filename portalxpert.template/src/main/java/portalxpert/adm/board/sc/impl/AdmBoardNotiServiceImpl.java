package portalxpert.adm.board.sc.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.board.mapper.AdmBoardNotiMapper;
import portalxpert.adm.board.sc.AdmBoardNotiService;
import portalxpert.adm.board.vo.AdmBoardNotiDelInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;
import portalxpert.adm.board.vo.AdmBoardNotiPopInfoVO;
import portalxpert.adm.board.vo.AdmBoardPbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.PbsUserBoardInfoVO;
import portalxpert.board.board100.vo.PbsUserBoardPartInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;

@Service("admBoardNotiService")
public class AdmBoardNotiServiceImpl extends EgovAbstractServiceImpl implements AdmBoardNotiService {
	
	/** AdmBoardNotiMapper */
    @Resource(name="admBoardNotiMapper")
    private AdmBoardNotiMapper admBoardNotiMapper;
    
    @Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;
	
	private final static Logger logger = LoggerFactory.getLogger(AdmBoardNotiServiceImpl.class);
	 
    /**
	 * 게시물관리 목록
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiList(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception {
    	try{
    		return admBoardNotiMapper.getAdmBoardNotiList(admBoardNotiInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시물 목록 Count
	 * @param AdmBoardNotiDelInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmBoardNotiCnt(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception {
    	try{
    		return admBoardNotiMapper.getAdmBoardNotiCnt(admBoardNotiInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    /**
	 * 게시물관리 삭제시 삭제코드 리스트
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiInfoVO> getAdmBoardNotiDelCodeList() throws Exception {
    	try{
    		return admBoardNotiMapper.getAdmBoardNotiDelCodeList();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 이동
	 * @param AdmBoardNotiInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmBoardNotiMove(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception {
    	try{
	    	admBoardNotiInfoVO.setLoginedUserId("1abc46a2f3b5b2193294850cbdc989fa");//로그인된 User ID 세팅!
	    	
	    	logger.debug("::::::::::::::이동되는 Noti ID={}::::::::::::::::::::::: ",admBoardNotiInfoVO.getNotiId());
	    	
	    	admBoardNotiMapper.insertAdmBoardNotiMove1(admBoardNotiInfoVO);//BBS_NOTI_INFO Insert
	    	admBoardNotiMapper.insertAdmBoardNotiMove2(admBoardNotiInfoVO);//BBS_NOTI_DEL_INFO Insert
	    	admBoardNotiMapper.updateAdmBoardNotiMove3(admBoardNotiInfoVO);//BBS_NOTI_INFO Update
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 삭제
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmBoardNotiDelete(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception {
    	try{
	    	admBoardNotiInfoVO.setLoginedUserId("1abc46a2f3b5b2193294850cbdc989fa");//로그인된 User ID 세팅!
	    	
	    	logger.debug("::::::::::::::삭제되는 Noti ID={}::::::::::::::::::::::: ",admBoardNotiInfoVO.getNotiId());
	    	
	    	admBoardNotiMapper.insertAdmBoardNotiDelete1(admBoardNotiInfoVO);//BBS_NOTI_DEL_INFO Insert
	    	admBoardNotiMapper.updateAdmBoardNotiDelete2(admBoardNotiInfoVO);//BBS_NOTI_INFO Update
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
	/**
	 * 개인게시판 목록 Count
	 * @param admBoardPbsNotiInfoVO
	 * @return
	 * @throws Exception
	 */
    public int getAdmPbsBoardCnt(AdmBoardPbsNotiInfoVO admBoardPbsNotiInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.getAdmPbsBoardCnt(admBoardPbsNotiInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
	/**
	 * 개인게시판 목록
	 * @param admBoardPbsNotiInfoVO
	 * @return
	 * @throws Exception
	 */
    public List<AdmBoardPbsNotiInfoVO> getAdmPbsBoardList(AdmBoardPbsNotiInfoVO admBoardPbsNotiInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.getAdmPbsBoardList(admBoardPbsNotiInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시판 생성 정보 조회 
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 추가항목 정보
	 * @exception Exception
	 */    
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) throws Exception {
    	try{
    		return admBoardNotiMapper.getPbsUserBoardInfoList(vo);
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
    		return admBoardNotiMapper.getPbsUserBoardPartInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
	/**
	 * 공지사항 목록
	 * @param vo
	 * @return
	 * @throws Exception
	 */
    public List<AdmBoardNotiPopInfoVO> getBoardPopupList(AdmBoardNotiPopInfoVO vo) throws Exception{
    	try{
    		return admBoardNotiMapper.getBoardPopupList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    }
    
    /**
     * 공지사항 속성정보
     * @param vo
     * @return
     * @throws Exception
     */
    public AdmBoardNotiPopInfoVO selectAdmPopupNotiInfo(AdmBoardNotiPopInfoVO vo) throws Exception{
    	try{
    		return admBoardNotiMapper.selectAdmPopupNotiInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    }
    
	/**
	 * 공지사항 목록 Count
	 * @param admBoardNotiPopInfoVO
	 * @return
	 * @throws Exception
	 */
    public int getBoardPopupCnt(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.getBoardPopupCnt(admBoardNotiPopInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    }
    
    /**
     * 공지사항 조회
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int selectAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.selectAdmPopupNoti(admBoardNotiPopInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 공지사항 수정
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int updateAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO, HttpSession session) throws Exception{
    	try{
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBoardNotiPopInfoVO.setUpdrId(info.getId());
	    	admBoardNotiPopInfoVO.setUpdrName(info.getName());
	    	
	        return admBoardNotiMapper.updateAdmPopupNoti(admBoardNotiPopInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    /**
     * 공지사항 추가
     * @param admBoardNotiPopInfoVO
     * @return
     * @throws Exception
     */
    public int insertAdmPopupNoti(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO, HttpSession session) throws Exception{
    	try{
	    	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBoardNotiPopInfoVO.setRegrId(info.getId());
	    	admBoardNotiPopInfoVO.setRegrName(info.getName());
	    	admBoardNotiPopInfoVO.setUpdrId(info.getId());
	    	admBoardNotiPopInfoVO.setUpdrName(info.getName());
	    	
	        return admBoardNotiMapper.insertAdmPopupNoti(admBoardNotiPopInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
 	/**
 	 * 공지사항 팝업 전체취소
 	 * @param AdmBoardNotiPopInfoVO
 	 * @return ModelMap
 	 * @exception Exception
 	 */
    public int updateAdmAllPopupCancel(AdmBoardNotiPopInfoVO admBoardNotiPopInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.updateAdmAllPopupCancel(admBoardNotiPopInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시물 목록
	 * @param AdmBoardNotiDelInfoVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmBoardNotiDelInfoVO> getAdmBoardNotiDelList(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception {
    	try{
    		return admBoardNotiMapper.getAdmBoardNotiDelList(admBoardNotiDelInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시물 목록 Count
	 * @param AdmBoardNotiDelInfoVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmBoardNotiDelCnt(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception {
    	try{
    		return admBoardNotiMapper.getAdmBoardNotiDelCnt(admBoardNotiDelInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시물조회 단건
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
    public AdmBoardNotiDelInfoVO getAdmBoardNotiDel(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO) throws Exception {
        try{
        	return admBoardNotiMapper.getAdmBoardNotiDel(admBoardNotiDelInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제게시물 복원
	 * @param AdmBoardNotiDelInfoVO
	 * @return
	 * @exception Exception
	 */
    public void updateAdmBoardNotiDelRollBack(AdmBoardNotiDelInfoVO admBoardNotiDelInfoVO, HttpSession session) throws Exception {
    	try{
	    	//로그인된 User 정보 세팅
	    	UserInfoVO usrInfo = (UserInfoVO)session.getAttribute("pxLoginInfo");
	    	admBoardNotiDelInfoVO.setUpdrId((String)usrInfo.getId());
	    	admBoardNotiDelInfoVO.setUpdrName((String)usrInfo.getName());
	    	
			List list = new ArrayList();
			Map<String, Object> map = new HashMap<String, Object>();
			list.add(admBoardNotiDelInfoVO.getNotiId());//notiId
			
			map.put("list", list);
			map.put("updrId", usrInfo.getId());
			map.put("updrName", usrInfo.getName());
	    	
	    	admBoardNotiMapper.deleteAdmBoardNotiDelRollBack1(admBoardNotiDelInfoVO);
	    	admBoardNotiMapper.updateAdmBoardNotiDelRollBack2(admBoardNotiDelInfoVO);
	    	admBoardNotiMapper.deleteBbsNotiAddItemForBoard(map);
	    	admBoardNotiMapper.deleteBbsNotiApndFileForBoard(map);
	    	admBoardNotiMapper.deleteBbsNotiEvalInfoForBoard(map);
	    	admBoardNotiMapper.deleteBbsNotiOpnForBoard(map);
			admBoardNotiMapper.deleteBbsNotiUserMapForBoard(map);
			admBoardNotiMapper.deleteBbsNotiSurveyForBoard(map);
			admBoardNotiMapper.deleteBbsNotiSurveyAnswForBoard(map);
			admBoardNotiMapper.deleteBbsNotiSurveyExmplForBoard(map);	
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시물조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
    public List getBbsNotiInfoView(AdmBoardNotiInfoVO admBoardNotiInfoVO) throws Exception{
    	try{
    		return admBoardNotiMapper.getBbsNotiInfoView(admBoardNotiInfoVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 삭제이동게시판 조회
	 * @param AdmBoardNotiDelInfoVO
	 * @return AdmBoardNotiDelInfoVO
	 * @exception Exception
	 */
	public AdmBoardNotiInfoVO getAdminBbsBoardInfo(AdmBoardNotiInfoVO admBoardNotiDelInfoVO) throws Exception{
		try{
			return admBoardNotiMapper.getAdminBbsBoardInfo(admBoardNotiDelInfoVO);
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
			
	    	return admBoardNotiMapper.getBbsNotiApndFileListForView(vo);
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
	    	
	    	return admBoardNotiMapper.getBbsNotiOpnList1ForView(vo);
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
	    	
	    	return admBoardNotiMapper.getBbsNotiOpnList2ForView(vo);
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
    		return admBoardNotiMapper.getBbsNotiApndFile(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
}
