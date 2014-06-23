package portalxpert.board.board300.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.mapper.Board100Mapper;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board300.mapper.Board300Mapper;
import portalxpert.board.board300.sc.Board300Service;
import portalxpert.board.board300.vo.PbsUserBoardInfoVO;
import portalxpert.board.board300.vo.PbsUserBoardPartInfoVO;
import portalxpert.board.board300.vo.PbsUserNotiApndFileVO;
import portalxpert.board.board300.vo.PbsUserNotiInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;
import egovframework.rte.fdl.property.EgovPropertyService;



@Service("board300Service")
public class Board300ServiceImpl extends AbstractServiceImpl implements  Board300Service {
	
	/** board300Mapper */
    @Resource(name="board300Mapper")
    private Board300Mapper board300Mapper;
    
    /** board100Mapper */
    @Resource(name="board100Mapper")
    private Board100Mapper board100Mapper;
    
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    private final static Logger logger = LoggerFactory.getLogger(Board300ServiceImpl.class); 

//    /**
//	 * 페이지별 게시물 정보 조회 
//	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
//	 * @return 게시물 정보 
//	 * @exception Exception
//	 */    
//    public List<BbsNotiInfoVO> getBbsNotiInfoListForPaging(BoardSearchVO vo) throws Exception {
//        return board300Mapper.getBbsNotiInfoListForPaging(vo);
//    }
//    
//    /**
//	 * 사용자 게시물 리스트 총 갯수
//	 * @param searchVO - 조회할 정보가 담긴 VO
//	 * @return 게시물 총 갯수
//	 * @exception
//	 */
//    public int getBbsNotiInfoListTotCnt(BoardSearchVO vo)throws Exception  {
//    	return board300Mapper.getBbsNotiInfoListTotCnt(vo);
//    }
    
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
	    	return board300Mapper.getUserBbsMapList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시판 생성 내용을 조회한다.
	 * @param PbsUserBoardInfoVO - 조회할 정보가 담긴 VO
	 * @return 개인 게시판 생성 내용
	 * @exception Exception
	 */
    public List<PbsUserBoardInfoVO> getPbsUserBoardInfoList(PbsUserBoardInfoVO vo) throws Exception {
    	try{
    		return board300Mapper.getPbsUserBoardInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시판 권한 정보 조회 
	 * @param PbsUserBoardPartInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 권한 정보 
	 * @exception Exception
	 */    
    public List<PbsUserBoardPartInfoVO> getPbsUserBoardPartInfoList(PbsUserBoardPartInfoVO vo) throws Exception {
    	try{
    		return board300Mapper.getPbsUserBoardPartInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시물 조회 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */   
    
    public List<PbsUserNotiInfoVO> getPbsUserNotiInfoList(PbsUserNotiInfoVO vo) throws Exception {
    	try{
    		return board300Mapper.getPbsUserNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 개인 게시물 첨부 조회 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 첨부 정보 
	 * @exception Exception
	 */   
    
    public List<PbsUserNotiApndFileVO> getPbsUserNotiApndFileList(PbsUserNotiApndFileVO vo) throws Exception {
    	try{
    		return board300Mapper.getPbsUserNotiApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }

    /**
	 * 개인 게시물 저장 
	 * @param PbsUserNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 첨부 정보 
	 * @exception Exception
	 */ 
    public PbsUserNotiInfoVO insertPbsUserNotiInfo(String json, HttpSession session, HttpServletRequest request) throws Exception {
    	
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
        String SAVE_DIR = PortalxpertConfigUtils.getString("pbs.upload.temp.dir");
        String REAL_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
        
        PbsUserNotiInfoVO vo = new PbsUserNotiInfoVO();
		try {
			
						
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			JSONObject pbsUserNotiObject = JSONObject.fromObject(json);
			//게시물 기본 테이블			
			vo.setUserNotiSeq(pbsUserNotiObject.getInt("userNotiSeq")) ;
			vo.setUpUserNotiSeq(pbsUserNotiObject.getInt("upUserNotiSeq")) ;
			vo.setBoardId( pbsUserNotiObject.getString("boardId")) ;
			vo.setEmgcYn( pbsUserNotiObject.getString("emgcYn")) ;
			vo.setAnmtYn( pbsUserNotiObject.getString("anmtYn")) ;
			vo.setPopupYn( pbsUserNotiObject.getString("popupYn")) ;
			vo.setBriefYn( pbsUserNotiObject.getString("briefYn")) ;
			vo.setMoblOpenDiv( pbsUserNotiObject.getString("moblOpenDiv")) ;
			vo.setNotiTitle( pbsUserNotiObject.getString("notiTitle")) ;
			vo.setTitleBoldYn( pbsUserNotiObject.getString("titleBoldYn")) ;
			vo.setTitleColorDiv( pbsUserNotiObject.getString("titleColorDiv")) ;
			vo.setNotiConts( pbsUserNotiObject.getString("notiConts")) ;
			vo.setLinkUrl( pbsUserNotiObject.getString("linkUrl")) ;
			vo.setNotiTp( pbsUserNotiObject.getString("notiTp")) ;
			vo.setNotiKind( pbsUserNotiObject.getString("notiKind")) ;
			vo.setNickUseYn( pbsUserNotiObject.getString("nickUseYn")) ;
			vo.setUserNick( info.getNickName()) ;
			vo.setUserName( info.getName()) ;
			vo.setEditDiv( pbsUserNotiObject.getString("editDiv")) ;
			vo.setRsrvYn( pbsUserNotiObject.getString("rsrvYn")) ;
			vo.setNotiBgnDttm( pbsUserNotiObject.getString("notiBgnDttm")) ;
			vo.setNotiEndDttm( pbsUserNotiObject.getString("notiEndDttm")) ;
			vo.setNotiOpenDiv( pbsUserNotiObject.getString("notiOpenDiv")) ;
			vo.setNotiOpenDivSpec( pbsUserNotiObject.getString("notiOpenDivSpec")) ;
			vo.setPublAsgnDiv( pbsUserNotiObject.getString("publAsgnDiv")) ;
			vo.setPublAsgnDivSpec( pbsUserNotiObject.getString("publAsgnDivSpec")) ;
			vo.setReplyPrmsYn( pbsUserNotiObject.getString("replyPrmsYn")) ;
			vo.setReplyMakrRealnameYn( pbsUserNotiObject.getString("replyMakrRealnameYn")) ;
			vo.setOpnPrmsYn( pbsUserNotiObject.getString("opnPrmsYn")) ;
			vo.setOpnMakrRealnameYn( pbsUserNotiObject.getString("opnMakrRealnameYn")) ;
			vo.setNotiReadCnt(pbsUserNotiObject.getInt("notiReadCnt")) ;
			vo.setNotiOpnCnt(pbsUserNotiObject.getInt("notiOpnCnt")) ;
			vo.setNotiAgrmCnt(pbsUserNotiObject.getInt("notiAgrmCnt")) ;
			vo.setNotiOppCnt(pbsUserNotiObject.getInt("notiOppCnt")) ;
			vo.setNotiLikeCnt(pbsUserNotiObject.getInt("notiLikeCnt")) ;
			vo.setMoblSendCnt(pbsUserNotiObject.getInt("moblSendCnt")) ;
			vo.setStatCode( pbsUserNotiObject.getString("statCode")) ;
			vo.setDeptCode(info.getOucode()) ;
			vo.setDeptName(info.getOu()) ;
			vo.setDeptFname(info.getOrgfullname()) ;
			vo.setMakrIp(CommUtil.getClientIpAddr(request)) ;
			vo.setDelYn( pbsUserNotiObject.getString("delYn")) ;
			//vo.setRegrId( info.getId()) ;
			//vo.setRegrName( info.getName()) ;
			vo.setRegDttm( pbsUserNotiObject.getString("regDttm")) ;
			//vo.setUpdrId( info.getId()) ;
			//vo.setUpdrName( info.getName()) ;
			vo.setUpdDttm( pbsUserNotiObject.getString("updDttm")) ;

			if (vo.getUserNotiSeq() == 0)  //입력
			{
				vo.setUpdrId(info.getId()) ;
				vo.setUpdrName(info.getName()) ;				
				vo.setRegrId(info.getId()) ;
				vo.setRegrName(info.getName()) ;
				vo.setUserId(info.getId()) ;
				board300Mapper.insertPbsUserNotiInfo(vo);
			}
			else  //수정
			{
				//vo.setUserId(pbsUserNotiObject.getString("userId"));
				board300Mapper.updatePbsUserNotiInfo(vo);				
				board300Mapper.deletePbsNotiApndFile(vo);
				BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
				surveyVO.setUserNotiSeq(vo.getUserNotiSeq());
				board300Mapper.deletePbsNotiSurveyExmpl(surveyVO);
				board300Mapper.deletePbsNotiSurvey(surveyVO);
			}

			if (vo.getNotiKind().equals(Constant.NOTI_KIND_020.getVal()))  //이미지
			{
				JSONArray jsonArr2 = (JSONArray)pbsUserNotiObject.get("AppendList");
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					PbsUserNotiApndFileVO apndVO = new PbsUserNotiApndFileVO();
					apndVO.setUserNotiSeq( vo.getUserNotiSeq()) ;
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
					apndVO.setRegDttm("") ;
					apndVO.setUpdrId( info.getId()) ;
					apndVO.setUpdrName( info.getName()) ;
					apndVO.setUpdDttm("") ;
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					apndVO.setApndFilePath(realPath);
					apndVO.setMvpKey((String)obj.get("mvpKey"));
					board300Mapper.insertPbsUserNotiApndFile(apndVO);
				}
			}
			else if (vo.getNotiKind().equals(Constant.NOTI_KIND_030.getVal()))  //동영상
			{
				JSONArray jsonArr2 = (JSONArray)pbsUserNotiObject.get("AppendList");
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					PbsUserNotiApndFileVO apndVO = new PbsUserNotiApndFileVO();
					apndVO.setUserNotiSeq( vo.getUserNotiSeq()) ;
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
					apndVO.setRegrId( (String)obj.get("regrId")) ;
					apndVO.setRegrName( (String)obj.get("regrName")) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( (String)obj.get("updrId")) ;
					apndVO.setUpdrName( (String)obj.get("updrName")) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					apndVO.setApndFilePath(realPath);
					//String realPath = apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					//apndVO.setApndFilePath(realPath);
					board300Mapper.insertPbsUserNotiApndFile(apndVO);					
				}
			}
			else if (vo.getNotiKind().equals(Constant.NOTI_KIND_040.getVal()))  //설문
			{
		        
				JSONArray jsonArr2 = (JSONArray)pbsUserNotiObject.get("AppendList");				
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
					surveyVO.setSurveyNo( obj.getInt("surveyNo")) ;
					surveyVO.setRelaNotiKind((String)obj.get("relaNotiKind"));
					surveyVO.setUserNotiSeq(vo.getUserNotiSeq() ) ;
					surveyVO.setNotiId(obj.getString("notiId"));
					surveyVO.setTmlnSeq(obj.getInt("tmlnSeq"));
					surveyVO.setSurveyClosDttm( (String)obj.get("surveyClosDttm")) ;
					surveyVO.setSurveyRsltOpenYn( (String)obj.get("surveyRsltOpenYn")) ;
					surveyVO.setSurveyConts( (String)obj.get("surveyConts")) ;
					surveyVO.setSurveyTp( (String)obj.get("surveyTp")) ;
					surveyVO.setDelYn( (String)obj.get("delYn")) ;
					surveyVO.setRegrId( info.getId()) ;
					surveyVO.setRegrName( info.getName()) ;
					surveyVO.setRegDttm( (String)obj.get("regDttm")) ;
					surveyVO.setUpdrId( (String)obj.get("updrId")) ;
					surveyVO.setUpdrName( (String)obj.get("updrName")) ;
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
						surveyExmplVO.setUpdrId( (String)exmplObj.get("updrId")) ;
						surveyExmplVO.setUpdrName( (String)exmplObj.get("updrName")) ;
						surveyExmplVO.setUpdDttm( (String)exmplObj.get("updDttm")) ;
						
						board100Mapper.insertBbsNotiSurveyExmpl(surveyExmplVO);
					}

				}
			}			
			//첨부파일 처리
			JSONArray jsonArr3 = (JSONArray)pbsUserNotiObject.get("AppendFileList");
			for (int i=0; i < jsonArr3.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr3.get(i);
				PbsUserNotiApndFileVO apndVO = new PbsUserNotiApndFileVO();
				apndVO.setUserNotiSeq( vo.getUserNotiSeq()) ;
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
				apndVO.setRegDttm("") ;
				apndVO.setUpdrId( info.getId()) ;
				apndVO.setUpdrName( info.getName()) ;
				apndVO.setUpdDttm("") ;
				String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
				apndVO.setApndFilePath(realPath);
				apndVO.setMvpKey((String)obj.get("mvpKey"));
				board300Mapper.insertPbsUserNotiApndFile(apndVO);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    
    /**
     * 게시글 PNUM 가져오기
     * @param PbsUserNotiInfoVO
     * @return int 
     * @exception Exception
     * @auther crossent 
     */
    public int getPbsMyPnumInfo(PbsUserNotiInfoVO vo) throws Exception{
    	try{
    		return board300Mapper.getPbsMyPnumInfo(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
}