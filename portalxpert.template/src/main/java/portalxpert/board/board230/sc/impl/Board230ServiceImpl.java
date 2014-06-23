package portalxpert.board.board230.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.vo.BbsNotiApndFileVO;
import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.board.board230.mapper.Board230Mapper;
import portalxpert.board.board230.sc.Board230Service;
import portalxpert.board.board230.vo.BbsTmpNotiApndFileVO;
import portalxpert.board.board230.vo.BbsTmpNotiInfoVO;
import portalxpert.board.board230.vo.BbsTmpNotiUserMapVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



@Service("board230Service")
public class Board230ServiceImpl extends AbstractServiceImpl implements  Board230Service {
	
	/** board230Mapper */
    @Resource(name="board230Mapper")
    private Board230Mapper board230Mapper;
	
	private final static Logger logger = LoggerFactory.getLogger(Board230ServiceImpl.class); 

	public int selectMovieKey() throws Exception{
		try{
			return board230Mapper.getMovieKey();
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
	}
	
    
	
	/*
     * 게시물 임시 입력
     * */
    public BbsTmpNotiInfoVO insertBbsTmpNotiInfo(String json, HttpServletRequest request, HttpSession session) throws Exception {
    	
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
        String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
        String REAL_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
        String WEB_TMP = PortalxpertConfigUtils.getString("upload.temp.web");
        
        BbsTmpNotiInfoVO vo = new BbsTmpNotiInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			JSONObject bbsNotiObject = JSONObject.fromObject(json);
			//게시물 기본 테이블			
			vo.setTmpNotiSeq(bbsNotiObject.getInt("tmpNotiSeq")) ;
			vo.setUpNotiId((String)bbsNotiObject.get("upNotiId")) ;
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
			vo.setUserNick(info.getNickName()) ;
			vo.setUserName(info.getName()) ;
			vo.setUserId(info.getId());
			vo.setEditDiv((String)bbsNotiObject.get("editDiv")) ;
			vo.setRsrvYn((String)bbsNotiObject.get("rsrvYn")) ;
			vo.setNotiBgnDttm((String)bbsNotiObject.get("notiBgnDttm")) ;
			
			if (!vo.getRsrvYn().equals("Y"))
			{
				vo.setNotiBgnDttm(CommUtil.getDateString("yyyyMMdd"));
			}			
			
			vo.setNotiEndDttm((String)bbsNotiObject.get("notiEndDttm")) ;
			vo.setNotiOpenDiv((String)bbsNotiObject.get("notiOpenDiv")) ;
			vo.setNotiOpenDivSpec((String)bbsNotiObject.get("notiOpenDivSpec")) ;
			vo.setPublAsgnDiv((String)bbsNotiObject.get("publAsgnDiv")) ;
			vo.setPublAsgnDivSpec((String)bbsNotiObject.get("publAsgnDivSpec")) ;
			vo.setReplyPrmsYn((String)bbsNotiObject.get("replyPrmsYn")) ;
			vo.setReplyMakrRealnameYn((String)bbsNotiObject.get("replyMakrRealnameYn")) ;
			vo.setOpnPrmsYn((String)bbsNotiObject.get("opnPrmsYn")) ;
			vo.setOpnMakrRealnameYn((String)bbsNotiObject.get("opnMakrRealnameYn")) ;			
			vo.setStatCode((String)bbsNotiObject.get("statCode")) ;
			vo.setDeptCode((String)bbsNotiObject.get("deptCode")) ;
			vo.setDeptName((String)bbsNotiObject.get("deptName")) ;
			vo.setDeptFname((String)bbsNotiObject.get("deptFname")) ;
			vo.setMakrIp((String)bbsNotiObject.get("makrIp")) ;
			vo.setBrghCode((String)bbsNotiObject.get("brghCode")) ;
			vo.setCdlnDeptCode((String)bbsNotiObject.get("cdlnDeptCode")) ;
			vo.setCdlnDeptName((String)bbsNotiObject.get("cdlnDeptName")) ;
			vo.setCdlnDeptFname((String)bbsNotiObject.get("cdlnDeptFname")) ;
			vo.setCdlnObjr("");
			vo.setCdlnEvntCode((String)bbsNotiObject.get("cdlnEvntCode")) ;
			vo.setDelYn((String)bbsNotiObject.get("delYn")) ;
			vo.setRegrId(info.getId()) ;
			vo.setRegrName(info.getName()) ;
			vo.setRegDttm((String)bbsNotiObject.get("regDttm")) ;
			vo.setUpdrId(info.getId()) ;
			vo.setUpdrName(info.getName()) ;
			vo.setUpdDttm((String)bbsNotiObject.get("updDttm")) ;			
			vo.setNotiTagLst((String)bbsNotiObject.get("notiTagLst")) ;
			vo.setAgrmOppYn((String)bbsNotiObject.get("agrmOppYn")) ;
			
			if (vo.getTmpNotiSeq() > 0)  //수정
			{
				board230Mapper.updateBbsTmpNotiInfo(vo);
				board230Mapper.deleteBbsTmpNotiApndFile(vo);
				board230Mapper.deleteBbsTmpNotiSurveyExmpl(vo);
				board230Mapper.deleteBbsTmpNotiSurvey(vo);
				board230Mapper.deleteBbsTmpNotiUserMap(vo);
			}
			else
			{
				board230Mapper.insertBbsTmpNotiInfo(vo);
			}
			
			
			JSONArray jsonArr = (JSONArray)bbsNotiObject.get("NotiOpenDivDeptList");  //부서지정			
			for(int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsTmpNotiUserMapVO userVO = new BbsTmpNotiUserMapVO();				
				userVO.setTmpNotiSeq(vo.getTmpNotiSeq());
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
				
				board230Mapper.insertBbsTmpNotiUserMap(userVO);
			}
			jsonArr = (JSONArray)bbsNotiObject.get("NotiOpenDivEmpList");  //개인지정			
			for(int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsTmpNotiUserMapVO userVO = new BbsTmpNotiUserMapVO();				
				userVO.setTmpNotiSeq(vo.getTmpNotiSeq());
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
				
				board230Mapper.insertBbsTmpNotiUserMap(userVO);
				
			}
			
		
			if (vo.getNotiKind().equals(Constant.NOTI_KIND_020.getVal()))  //이미지
			{
				JSONArray jsonArr2 = (JSONArray)bbsNotiObject.get("AppendList");
				
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsTmpNotiApndFileVO apndVO = new BbsTmpNotiApndFileVO();
					apndVO.setTmpNotiSeq( vo.getTmpNotiSeq()) ;
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
					//String realPath = apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					//apndVO.setApndFilePath(realPath);
					
					
					apndVO.setMvpKey((String)obj.get("mvpKey"));
					
					board230Mapper.insertBbsTmpNotiApndFile(apndVO);
				}
			}
			else if (vo.getNotiKind().equals(Constant.NOTI_KIND_030.getVal()))  //동영상
			{
				JSONArray jsonArr2 = (JSONArray)bbsNotiObject.get("AppendList");
				for (int i=0; i < jsonArr2.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr2.get(i);
					BbsTmpNotiApndFileVO apndVO = new BbsTmpNotiApndFileVO();
					apndVO.setTmpNotiSeq(vo.getTmpNotiSeq()) ;
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
					apndVO.setMvpKey((String)obj.get("mvpKey"));
					//String realPath = apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					//apndVO.setApndFilePath(realPath);
					board230Mapper.insertBbsTmpNotiApndFile(apndVO);					
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
					surveyVO.setNotiId((String)obj.get("notiId")) ;
					surveyVO.setTmpNotiSeq( vo.getTmpNotiSeq()) ;
					surveyVO.setTmlnSeq( obj.getInt("tmlnSeq")) ;
					surveyVO.setUserNotiSeq( obj.getInt("userNotiSeq")) ;
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
					
					board230Mapper.insertBbsTmpNotiSurvey(surveyVO);
					
					JSONArray jsonArr3 = (JSONArray)obj.get("apndExmpList");
										
					for(int j=0; j < jsonArr3.size(); j++ )
					{
						JSONObject exmplObj = (JSONObject)jsonArr3.get(j);
						
						BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
						surveyExmplVO.setSurveyNo( surveyVO.getSurveyNo()) ;
						surveyExmplVO.setExmplNo( exmplObj.getInt("exmplNo")) ;
						surveyExmplVO.setExmplConts((String)exmplObj.get("exmplConts")) ;					
						
						
						surveyExmplVO.setImgPath( WEB_TMP+'/'+CommUtil.getDateString("yyyyMMdd")) ;
						surveyExmplVO.setImgName( (String)exmplObj.get("imgName")) ;
						
						surveyExmplVO.setPrvwPath((String)exmplObj.get("prvwPath")) ;
						surveyExmplVO.setPrvwName((String)exmplObj.get("prvwName")) ;
						
						
						//String realPath = apndFileCopy(surveyExmplVO.getImgPath(), surveyExmplVO.getImgName());
						//surveyExmplVO.setImgPath(WEB_DIR+'/'+realPath);						
						
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
						
						board230Mapper.insertBbsTmpNotiSurveyExmpl(surveyExmplVO);
					}

				}
			}			
			
			//첨부파일 처리
			JSONArray jsonArr3 = (JSONArray)bbsNotiObject.get("AppendFileList");
			for (int i=0; i < jsonArr3.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr3.get(i);
				BbsTmpNotiApndFileVO apndVO = new BbsTmpNotiApndFileVO();
				apndVO.setTmpNotiSeq( vo.getTmpNotiSeq()) ;
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
				//apndVO.setReadCnt(obj.getInt("readCnt"));
				apndVO.setApndFilePath(realPath);
				apndVO.setApndFilePrvwPath(realPath) ;  //첨부파일 수정시 처리 하기 위하여 변경 20130705
				apndVO.setMvpKey((String)obj.get("mvpKey"));
				
				//String realPath = apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
				//apndVO.setApndFilePath(realPath);
				//apndVO.setMvpKey((String)obj.get("mvpKey"));

				board230Mapper.insertBbsTmpNotiApndFile(apndVO);
				
			}
			
			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    
    
    /**
	 * 임시저장 정보 조회 
	 * @param PsnTmlnOpnVO - 조회할 정보가 담긴 VO
	 * @return 임시저장 정보 조회 
	 * @exception Exception
	 */    
    public List<BbsTmpNotiInfoVO> getBbsTmpNotiInfoList(BbsTmpNotiInfoVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsTmpNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 임시저장 첨부 정보 조회 
	 * @param PsnTmlnOpnVO - 조회할 정보가 담긴 VO
	 * @return 임시저장 정보 조회 
	 * @exception Exception
	 */    
    public List<BbsTmpNotiApndFileVO> getBbsTmpNotiApndFileList(BbsTmpNotiApndFileVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsTmpNotiApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 임시저장 조회자 지정 정보 조회 
	 * @param BbsTmpNotiUserMapVO - 조회할 정보가 담긴 VO
	 * @return 임시저장 조회자 지정 정보 조회 
	 * @exception Exception
	 */    
    public List<BbsTmpNotiUserMapVO> getBbsTmpNotiUserMapList(BbsTmpNotiUserMapVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsTmpNotiUserMapList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 임시저장 설문 정보 조회 
	 * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO
	 * @return 임시저장 설문 정보 조회 
	 * @exception Exception
	 */
    public List<BbsNotiSurveyVO> getBbsNotiSurveyList(BbsNotiSurveyVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsNotiSurveyList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 임시저장 설문 보기 조회 
	 * @param BbsNotiSurveyExmplVO - 조회할 정보가 담긴 VO
	 * @return 임시저장 설문 보기 조회 
	 * @exception Exception
	 */
    public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplList(BbsNotiSurveyExmplVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsNotiSurveyExmplList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 조회 
	 * @exception Exception
	 */
    public List<BbsNotiInfoVO> getBbsNotiInfoList(BbsNotiInfoVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsNotiInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 첨부 조회 
	 * @param BbsNotiApndFileVO - 조회할 정보가 담긴 VO
	 * @return 게시물 첨부 조회 
	 * @exception Exception
	 */
    public List<BbsNotiApndFileVO> getBbsNotiApndFileList(BbsNotiApndFileVO vo) throws Exception {
    	try{
    		return board230Mapper.getBbsNotiApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    
    
}
