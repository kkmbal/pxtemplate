package portalxpert.person.person300.sc.impl;


import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyAnswVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.common.config.Constant;
import portalxpert.common.config.PortalxpertConfigUtils;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.person.person300.mapper.Person300Mapper;
import portalxpert.person.person300.sc.Person300Service;
import portalxpert.person.person300.vo.PsnSurveyResultVO;
import portalxpert.person.person300.vo.PsnTmlnApndFileVO;
import portalxpert.person.person300.vo.PsnTmlnEvalInfoVO;
import portalxpert.person.person300.vo.PsnTmlnInfoVO;
import portalxpert.person.person300.vo.PsnTmlnOpnVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;



@Service("person300Service")
public class Person300ServiceImpl extends AbstractServiceImpl implements Person300Service {
	
	/** person300Mapper */
    @Resource(name="person300Mapper")
    private Person300Mapper person300Mapper;

    private final static Logger logger = LoggerFactory.getLogger(Person300ServiceImpl.class);
    
    
    /*
     * 소통글 입력
     * */
    public PsnTmlnInfoVO insertPsnUserTmlnInfo(String json, HttpSession session) throws Exception {
    	
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
        String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
        String REAL_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
    	PsnTmlnInfoVO vo = new PsnTmlnInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject psnTmlnObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			vo.setUpTmlnSeq( psnTmlnObject.getInt("upTmlnSeq")) ;
			vo.setObjrDiv( (String)psnTmlnObject.get("objrDiv")) ;
			vo.setObjrId( (String)psnTmlnObject.get("objrId"));
			vo.setUserId( info.getId()) ;
			vo.setUserNick( (String)psnTmlnObject.get("userNick")) ;
			vo.setUserName( info.getName()) ;
			vo.setTmlnDiv( (String)psnTmlnObject.get("tmlnDiv")) ;
			vo.setTmlnKind( (String)psnTmlnObject.get("tmlnKind")) ;
			vo.setTmlnTitle( (String)psnTmlnObject.get("tmlnTitle")) ;
			vo.setTmlnConts( (String)psnTmlnObject.get("tmlnConts")) ;
			vo.setTmlnSrc( (String)psnTmlnObject.get("tmlnSrc")) ;
			vo.setRelaUrl( (String)psnTmlnObject.get("relaUrl")) ;
			vo.setOpnCnt( psnTmlnObject.getInt("opnCnt")) ;
			vo.setAgrmCnt( psnTmlnObject.getInt("agrmCnt")) ;
			vo.setOppCnt( psnTmlnObject.getInt("oppCnt")) ;
			vo.setLikeCnt( psnTmlnObject.getInt("likeCnt")) ;
			vo.setDelYn( (String)psnTmlnObject.get("delYn")) ;
			vo.setRegrId( info.getId()) ;
			vo.setRegrName( info.getName()) ;
			vo.setRegDttm( (String)psnTmlnObject.get("regDttm")) ;
			vo.setUpdrId( (String)psnTmlnObject.get("updrId")) ;
			vo.setUpdrName( (String)psnTmlnObject.get("updrName")) ;
			vo.setUpdDttm( (String)psnTmlnObject.get("updDttm")) ;

			person300Mapper.insertPsnTmlnInfo(vo);
			
			//읽음 처리 Y
			person300Mapper.updatePsnTmlnReadYn(info);
			
			if (vo.getTmlnKind().equals("020"))  //이미지첨부
			{
				JSONArray jsonArr = (JSONArray)psnTmlnObject.get("AppendList");
				for (int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					
					PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
					apndVO.setTmlnSeq( vo.getTmlnSeq()) ;
					apndVO.setFileSeq(obj.getInt("fileSeq"));				
					apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
					apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
					apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
					apndVO.setApndFileName( (String)obj.get("apndFileName")) ;				
					apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;				
					apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
					apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;				
					apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
					apndVO.setDelYn( (String)obj.get("delYn")) ;
					apndVO.setRegrId( info.getId()) ;
					apndVO.setRegrName( info.getName()) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( (String)obj.get("updrId")) ;
					apndVO.setUpdrName( (String)obj.get("updrName")) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;				
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					//apndVO.setApndFilePath(WEB_DIR+'/'+realPath);
					apndVO.setApndFilePath(realPath);
					
					person300Mapper.insertPsnTmlnApndFile(apndVO);
				}
			}
			else if (vo.getTmlnKind().equals("030"))  //동영상첨부
			{
				JSONArray jsonArr = (JSONArray)psnTmlnObject.get("AppendList");
				for (int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					
					PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
					apndVO.setTmlnSeq( vo.getTmlnSeq()) ;
					apndVO.setFileSeq(obj.getInt("fileSeq"));				
					apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
					apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
					apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
					apndVO.setApndFileName( (String)obj.get("apndFileName")) ;				
					apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;				
					apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
					apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;				
					apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
					apndVO.setDelYn( (String)obj.get("delYn")) ;
					apndVO.setRegrId( info.getId()) ;
					apndVO.setRegrName( info.getName()) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( (String)obj.get("updrId")) ;
					apndVO.setUpdrName( (String)obj.get("updrName")) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;
					apndVO.setMvpKey((String)obj.get("mvpKey"));
					/*String realPath = apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					apndVO.setApndFilePath(WEB_DIR+'/'+realPath);*/
					
					person300Mapper.insertPsnTmlnApndFile(apndVO);
				}
			}
			else if (vo.getTmlnKind().equals("040"))  //설문첨부
			{
				JSONArray jsonArr = (JSONArray)psnTmlnObject.get("AppendList");				
				for (int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();
					surveyVO.setSurveyNo( obj.getInt("surveyNo")) ;
					surveyVO.setNotiId( (String)obj.get("notiId")) ;
					surveyVO.setTmpNotiSeq( obj.getInt("tmpNotiSeq")) ;
					surveyVO.setTmlnSeq( vo.getTmlnSeq()) ;
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
					
					person300Mapper.insertBbsNotiSurvey(surveyVO);
					
					JSONArray jsonArr2 = (JSONArray)obj.get("apndExmpList");
										
					for(int j=0; j < jsonArr2.size(); j++ )
					{
						JSONObject exmplObj = (JSONObject)jsonArr2.get(j);
						
						BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
						surveyExmplVO.setSurveyNo( surveyVO.getSurveyNo()) ;
						surveyExmplVO.setExmplNo( exmplObj.getInt("exmplNo")) ;
						surveyExmplVO.setExmplConts((String)exmplObj.get("exmplConts")) ;
						
						
						
						//surveyExmplVO.setImgPath( (String)exmplObj.get("imgPath")) ;						
						surveyExmplVO.setImgPath(SAVE_DIR+'/'+CommUtil.getDateString("yyyyMMdd")) ;
						surveyExmplVO.setImgName( (String)exmplObj.get("imgName")) ;
						
						String realPath = CommUtil.apndFileCopy(surveyExmplVO.getImgPath(), surveyExmplVO.getImgName());
						//surveyExmplVO.setImgPath(WEB_DIR+'/'+realPath);
						surveyExmplVO.setImgPath(realPath);
						
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
						
						person300Mapper.insertBbsNotiSurveyExmpl(surveyExmplVO);
					}

				}
			}
			else if (vo.getTmlnKind().equals("050"))  //첨부파일
			{
				JSONArray jsonArr = (JSONArray)psnTmlnObject.get("AppendList");
				for (int i=0; i < jsonArr.size(); i++)
				{
					JSONObject obj = (JSONObject)jsonArr.get(i);
					PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
					apndVO.setTmlnSeq( vo.getTmlnSeq()) ;
					apndVO.setFileSeq(obj.getInt("fileSeq"));				
					apndVO.setApndFileTp( (String)obj.get("apndFileTp")) ;
					apndVO.setApndFileSz( obj.getInt("apndFileSz")) ;
					apndVO.setApndFileOrgn( (String)obj.get("apndFileOrgn")) ;
					apndVO.setApndFileName( (String)obj.get("apndFileName")) ;				
					apndVO.setApndFilePath( (String)obj.get("apndFilePath")) ;				
					apndVO.setApndFilePrvwName( (String)obj.get("apndFilePrvwName")) ;
					apndVO.setApndFilePrvwPath( (String)obj.get("apndFilePrvwPath")) ;				
					apndVO.setSourceCodeInput( (String)obj.get("sourceCodeInput")) ;
					apndVO.setDelYn( (String)obj.get("delYn")) ;
					apndVO.setRegrId( info.getId()) ;
					apndVO.setRegrName( info.getName()) ;
					apndVO.setRegDttm( (String)obj.get("regDttm")) ;
					apndVO.setUpdrId( (String)obj.get("updrId")) ;
					apndVO.setUpdrName( (String)obj.get("updrName")) ;
					apndVO.setUpdDttm( (String)obj.get("updDttm")) ;				
					String realPath = CommUtil.apndFileCopy(apndVO.getApndFilePath(), apndVO.getApndFileName());
					apndVO.setApndFilePath(REAL_DIR+'/'+realPath);
					
					person300Mapper.insertPsnTmlnApndFile(apndVO);
				}
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /**
	 * 소통글 정보 조회 
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 VO
	 * @return 소통글 정보 
	 * @exception Exception
	 */    
    public List<PsnTmlnInfoVO> getPsnTmlnInfoList(PsnTmlnInfoVO vo) throws Exception {
    	try{
    		return person300Mapper.getPsnTmlnInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 소통글에 대한 의견 정보 조회 
	 * @param PsnTmlnOpnVO - 조회할 정보가 담긴 VO
	 * @return 소통글에 대한 의견 정보 
	 * @exception Exception
	 */    
    public List<PsnTmlnOpnVO> getPsnTmlnOpnList(PsnTmlnOpnVO vo) throws Exception {
    	try{
    		return person300Mapper.getPsnTmlnOpnList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
	
    /*
     * 소통글에 대한 의견 입력
     * */
    public PsnTmlnOpnVO insertPsnTmlnOpn(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnOpnVO vo = new PsnTmlnOpnVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
			JSONObject psnOpnObject = JSONObject.fromObject(json);
			
			vo.setUserId( info.getId());
			vo.setUserName( info.getName());
			vo.setUserNick( info.getNickName());
			vo.setTmlnSeq( psnOpnObject.getInt("tmlnSeq")) ;
			vo.setTmlnOpnSeq( psnOpnObject.getInt("tmlnOpnSeq")) ;
			vo.setUpOpnSeq( psnOpnObject.getInt("upOpnSeq")) ;
			vo.setOpnConts( (String)psnOpnObject.get("opnConts")) ;
			vo.setDelYn( (String)psnOpnObject.get("delYn")) ;
			vo.setRegrId( info.getId()) ;
			vo.setRegrName( info.getName()) ;
			vo.setRegDttm( (String)psnOpnObject.get("regDttm")) ;
			vo.setUpdrId( (String)psnOpnObject.get("updrId")) ;
			vo.setUpdrName( (String)psnOpnObject.get("updrName")) ;
			vo.setUpdDttm(CommUtil.getDateString("yyyy-MM-dd HH:mm:ss")) ;
			vo.setFaceImg(info.getUserRepImg());

			if (vo.getFaceImg() == null){
				vo.setFaceImg("");
			}
			vo.setReadYn("Y");

			person300Mapper.insertPsnTmlnOpn(vo);
			
			// 알리미
			/*
			PsnTmlnInfoVO pvo = new PsnTmlnInfoVO();
			pvo.setTmlnSeq(psnOpnObject.getInt("tmlnSeq"));
			PsnTmlnInfoVO psnTmlnUser = person300Mapper.getPsnTmlnUser(pvo);
			if(psnTmlnUser != null){
				CommUtil.sendAlert(psnTmlnUser.getUserId(), info.getName(), "포털 개인홈 소통글 신규 의견 등록 알림");
			}
			*/
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    

    
    /**
	 * 소통글에 대한 첨부(이미지) 정보 조회 
	 * @param PsnTmlnApndFileVO - 조회할 정보가 담긴 VO
	 * @return 소통글에 대한 첨부 정보 
	 * @exception Exception
	 */    
    public List<PsnTmlnApndFileVO> getPsnTmlnApndFileList(PsnTmlnApndFileVO vo) throws Exception {
    	try{
    		return person300Mapper.getPsnTmlnApndFileList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 소통글에 대한 첨부(설문) 정보 조회 
	 * @param PsnTmlnApndFileVO - 조회할 정보가 담긴 VO
	 * @return 소통글에 대한 첨부 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiSurveyVO> getBbsNotiSurveyList(BbsNotiSurveyVO vo) throws Exception {
    	try{
    		return person300Mapper.getBbsNotiSurveyList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    

    /**
	 * 소통글에 대한 첨부(설문 보기) 정보 조회 
	 * @param PsnTmlnApndFileVO - 조회할 정보가 담긴 VO
	 * @return 소통글에 대한 첨부 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplList(BbsNotiSurveyExmplVO vo) throws Exception {
    	try{
    		return person300Mapper.getBbsNotiSurveyExmplList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 소통글에 대한 설문 결과 등록 
	 * @param BbsNotiSurveyAnswVO - 조회할 정보가 담긴 VO
	 * @return 소통글에 대한 설문 결과 정보 
	 * @exception Exception
	 */    
    public BbsNotiSurveyAnswVO insertBbsNotiSurveyAnsw(String json, HttpSession session) throws Exception {
    	
    	BbsNotiSurveyAnswVO vo = new BbsNotiSurveyAnswVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
			JSONArray jsonArr = JSONArray.fromObject(json);
			for (int i=0; i < jsonArr.size(); i++)
			{
				JSONObject jsonObject = (JSONObject)jsonArr.get(i);
				BbsNotiSurveyAnswVO answVO = new BbsNotiSurveyAnswVO();
				answVO.setSurveyNo( jsonObject.getInt("surveyNo")) ;
				answVO.setAnswmanId( info.getId()) ;
				answVO.setAnswmanName( info.getName()) ;
				answVO.setAnswExmplNo( jsonObject.getInt("answExmplNo")) ;
				answVO.setDelYn( (String)jsonObject.get("delYn")) ;
				answVO.setRegrId( info.getId()) ;
				answVO.setRegrName( info.getName()) ;
				answVO.setRegDttm( (String)jsonObject.get("delYn")) ;
				answVO.setUpdrId( (String)jsonObject.get("updrId")) ;
				answVO.setUpdrName( (String)jsonObject.get("updrName")) ;
				answVO.setUpdDttm( (String)jsonObject.get("updDttm")) ;
				person300Mapper.insertBbsNotiSurveyAnsw(answVO);
			
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    
    /*
     * 소통글 수정
     * */
    public PsnTmlnInfoVO updatePsnUserTmlnInfo(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnInfoVO vo = new PsnTmlnInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject psnTmlnObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			vo.setTmlnConts( (String)psnTmlnObject.get("tmlnConts")) ;
			vo.setUpdrId( info.getId()) ;
			vo.setUpdrName( info.getName()) ;

			person300Mapper.updatePsnTmlnInfo(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /*
     * 소통글 삭제
     * */
    public PsnTmlnInfoVO deletePsnUserTmlnInfo(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnInfoVO vo = new PsnTmlnInfoVO();
    	PsnTmlnApndFileVO apndVO = new PsnTmlnApndFileVO();
    	PsnTmlnOpnVO opnVO = new PsnTmlnOpnVO();
    	
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject psnTmlnObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			person300Mapper.deletePsnTmlnInfo(vo);
			
			apndVO.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			person300Mapper.deletePsnTmlnApndFile(apndVO);
			
			opnVO.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			person300Mapper.deletePsnTmlnOpn(opnVO);
			
			//설문삭제

		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /*
     * 소통글 의견 수정
     * */
    public PsnTmlnOpnVO updatePsnTmlnOpn(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnOpnVO vo = new PsnTmlnOpnVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject psnTmlnOpnObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( psnTmlnOpnObject.getInt("tmlnSeq")) ;
			vo.setTmlnOpnSeq( psnTmlnOpnObject.getInt("tmlnOpnSeq"));
			vo.setOpnConts((String)psnTmlnOpnObject.get("opnConts"));
			vo.setUpdrId( info.getId()) ;
			vo.setUpdrName( info.getName()) ;

			person300Mapper.updatePsnTmlnOpn(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /*
     * 소통글  의견 삭제
     * */
    public PsnTmlnOpnVO deleteTmlnOpn(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnOpnVO opnVO = new PsnTmlnOpnVO();
    	
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			JSONObject jsonObject = JSONObject.fromObject(json);
			opnVO.setTmlnOpnSeq(jsonObject.getInt("tmlnOpnSeq")) ;
			person300Mapper.deleteTmlnOpn(opnVO);

		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return opnVO;
    }
    
    
    /**
	 * 소통글에 대한 좋아요  등록 
	 * @param PsnTmlnEvalInfoVO
	 * @return 입력 결과 정보 
	 * @exception Exception
	 */    
    public PsnTmlnEvalInfoVO insertPsnTmlnEvalInfo(String json, HttpSession session) throws Exception {
    	
    	PsnTmlnEvalInfoVO vo = new PsnTmlnEvalInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
			JSONObject jsonObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( jsonObject.getInt("tmlnSeq")) ;
			vo.setTmlnEvalDiv( (String)jsonObject.get("tmlnEvalDiv")) ;
			vo.setUserId( info.getId()) ;
			vo.setEvalSeq( jsonObject.getInt("evalSeq")) ;
			vo.setUserName( info.getName()) ;
			vo.setEvalDttm( (String)jsonObject.get("evalDttm")) ;
			vo.setDelYn( (String)jsonObject.get("delYn")) ;
			vo.setRegrId( info.getId()) ;
			vo.setRegrName( info.getName()) ;
			vo.setRegDttm( (String)jsonObject.get("regDttm")) ;
			vo.setUpdrId( (String)jsonObject.get("updrId")) ;
			vo.setUpdrName( (String)jsonObject.get("updrName")) ;
			vo.setUpdDttm( (String)jsonObject.get("updDttm")) ;
			vo.setUserNick( info.getNickName()) ;
			
			person300Mapper.insertPsnTmlnEvalInfo(vo);
			
			// 알리미
			/*
			PsnTmlnInfoVO pvo = new PsnTmlnInfoVO();
			pvo.setTmlnSeq(jsonObject.getInt("tmlnSeq"));
			PsnTmlnInfoVO psnTmlnUser = person300Mapper.getPsnTmlnUser(pvo);
			if(psnTmlnUser != null){
				CommUtil.sendAlert(psnTmlnUser.getUserId(), info.getName(), "포털 개인홈 소통글 좋아요 등록 알림");
			}
			*/
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /**
	 * 좋아요 정보 조회 
	 * @param PsnTmlnEvalInfoVO - 조회할 정보가 담긴 VO
	 * @return 좋아요 건수 
	 * @exception Exception
	 */    
    public int getPsnTmlnEvalInfoList(String json, HttpSession session, String kind) throws Exception {
    	PsnTmlnEvalInfoVO vo = new PsnTmlnEvalInfoVO();
    	int iResult = 0;
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
			JSONObject jsonObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( jsonObject.getInt("tmlnSeq")) ;
			
			if (kind.equals("USER"))
			{
				vo.setTmlnEvalDiv( (String)jsonObject.get("tmlnEvalDiv")) ;
				vo.setUserId( info.getId()) ;
			}
			else
			{
				vo.setUserId("") ;
			}
			
			vo.setEvalSeq( jsonObject.getInt("evalSeq")) ;
			/*vo.setUserName( info.getName()) ;
			vo.setDelYn( (String)jsonObject.get("delYn")) ;
			vo.setRegrId( info.getId()) ;
			vo.setRegrName( info.getName()) ;			
			vo.setUserNick( info.getNickName()) ;*/
			
			iResult = person300Mapper.getPsnTmlnEvalInfoList(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}    	
    	
		return iResult;
	}
    
    
    /**
	 * 소통글에 대한 좋아요 사용자 조회 
	 * @param 
	 * @return 소통글에 대한 좋아요 사용자 조회 
	 * @exception Exception
	 */    
    public  List<PsnTmlnEvalInfoVO> getPsnTmlnEvalUserList(PsnTmlnEvalInfoVO vo)  throws Exception {
    	try{
    		return person300Mapper.getPsnTmlnEvalUserList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 소통글 읽음 처리 
	 * @param 
	 * @return 소통글 읽음 처리 결과 
	 * @exception Exception
	 */
    public UserInfoVO updatePsnTmlnReadYn(UserInfoVO vo ) throws Exception {    
    	try {			
			person300Mapper.updatePsnTmlnReadYn(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
      return vo;
    }
    
    /**
	 * 설문결과 타이틀 조회 
	 * @param 
	 * @return 설문결과 타이틀 정보 조회 
	 * @exception Exception
	 */    
    public List<PsnSurveyResultVO> getSurveyResultTitle(PsnSurveyResultVO vo)  throws Exception {
    	try{
    		return person300Mapper.getSurveyResultTitle(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 설문결과 질문정보 조회 
	 * @param 
	 * @return 설문결과 질문정보 조회 
	 * @exception Exception
	 */    
    public List<BbsNotiSurveyVO> getBbsNotiSurveyResultList(BbsNotiSurveyVO vo)  throws Exception {
    	try{
    		return person300Mapper.getBbsNotiSurveyResultList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 설문수정 보기정보 조회 
	 * @param 
	 * @return 설문수정 보기정보 조회 
	 * @exception Exception
	 */    
    public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplUpdateList(BbsNotiSurveyExmplVO vo)  throws Exception {
    	try{
    		return person300Mapper.getBbsNotiSurveyExmplUpdateList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 설문결과 보기정보 조회 
	 * @param 
	 * @return 설문결과 보기 정보 조회 
	 * @exception Exception
	 */    
    public List<BbsNotiSurveyExmplVO> getSurveyResultExmpl(BbsNotiSurveyExmplVO vo)  throws Exception {
    	try{
    		return person300Mapper.getSurveyResultExmpl(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public int getBbsNotiSurveyResultYN(BbsNotiSurveyAnswVO vo) throws Exception{
    	try{
    		return person300Mapper.getBbsNotiSurveyResultYN(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /*
     * 설문정보 수정
     * */
    public PsnTmlnInfoVO updateTmlnSurveyInfo(String json, HttpSession session) throws Exception {
    	PsnTmlnInfoVO vo = new PsnTmlnInfoVO();
    	String WEB_DIR = PortalxpertConfigUtils.getString("upload.real.web");
        String SAVE_DIR = PortalxpertConfigUtils.getString("upload.temp.dir");
        String REAL_DIR = PortalxpertConfigUtils.getString("upload.real.dir");
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONArray jsonArr = JSONArray.fromObject(json);
			
			for (int i=0; i < jsonArr.size(); i++)
			{
				
				
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();				
				surveyVO.setSurveyNo( obj.getInt("surveyNo")) ;
				surveyVO.setNotiId( (String)obj.get("notiId")) ;
				surveyVO.setTmpNotiSeq( obj.getInt("tmpNotiSeq")) ;
				surveyVO.setTmlnSeq( obj.getInt("tmlnSeq")) ;
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
				
				if (i == 0)  //기존 데이타 삭제
				{
					person300Mapper.deleteBbsNotiSurvey(surveyVO);
					person300Mapper.deleteBbsNotiSurveyExmpl(surveyVO);
				}
				
				
				person300Mapper.insertBbsNotiSurvey(surveyVO);
				
				JSONArray jsonArr2 = (JSONArray)obj.get("apndExmpList");
									
				for(int j=0; j < jsonArr2.size(); j++ )
				{
					JSONObject exmplObj = (JSONObject)jsonArr2.get(j);
					
					BbsNotiSurveyExmplVO surveyExmplVO = new BbsNotiSurveyExmplVO();
					surveyExmplVO.setSurveyNo( surveyVO.getSurveyNo()) ;
					surveyExmplVO.setExmplNo( exmplObj.getInt("exmplNo")) ;
					surveyExmplVO.setExmplConts((String)exmplObj.get("exmplConts")) ;
					
					
					String tmp = (String)exmplObj.get("imgPath");
					boolean isTemp = false;
					if (tmp.indexOf("temp") < 0)
					{
						if (tmp.length() > 10)
						{
							tmp = tmp.substring(tmp.length() -10 , tmp.length());
						}
						else
						{
							tmp = CommUtil.getDateString("yyyyMMdd");
						}
					}
					else
					{
						tmp = CommUtil.getDateString("yyyyMMdd");
						isTemp = true;
					}
					
					//surveyExmplVO.setImgPath( (String)exmplObj.get("imgPath")) ;						
					//surveyExmplVO.setImgPath(SAVE_DIR+'/'+CommUtil.getDateString("yyyyMMdd")) ;
					if (isTemp) {
						surveyExmplVO.setImgPath(SAVE_DIR+'/'+tmp) ;
					}else{
						surveyExmplVO.setImgPath(REAL_DIR+'/'+tmp) ;	
					}
						
					
					surveyExmplVO.setImgName( (String)exmplObj.get("imgName")) ;
					
					String realPath = CommUtil.apndFileCopy(surveyExmplVO.getImgPath(), surveyExmplVO.getImgName());
					//surveyExmplVO.setImgPath(WEB_DIR+'/'+realPath);
					surveyExmplVO.setImgPath(realPath);
					
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
					
					person300Mapper.insertBbsNotiSurveyExmpl(surveyExmplVO);
				}

			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    
    /*
     * 설문정보 수정2(이미 설문제출이 이루어 진 경우 마감일자만 수정 가능 하게)
     * */
    public String updateTmlnSurveyInfo2(String json, HttpSession session) throws Exception {
    	
    	try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONArray jsonArr = JSONArray.fromObject(json);
			
			if (jsonArr.size() > 0)
			{
				JSONObject obj = (JSONObject)jsonArr.get(0);
				BbsNotiSurveyVO surveyVO = new BbsNotiSurveyVO();				
				surveyVO.setSurveyNo( obj.getInt("surveyNo")) ;
				surveyVO.setNotiId( (String)obj.get("notiId")) ;
				surveyVO.setTmpNotiSeq( obj.getInt("tmpNotiSeq")) ;
				surveyVO.setTmlnSeq( obj.getInt("tmlnSeq")) ;
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
				
				person300Mapper.updateBbsNotiSurvey(surveyVO);
			}
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return "";
    }
    
    public int getBbsNotiSurveyResultCnt(BbsNotiSurveyVO vo) throws Exception{
    	try{
    		return person300Mapper.getBbsNotiSurveyResultCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 게시물 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getNotilnInfoList(BbsNotiInfoVO vo) throws Exception {
    	try{
    		return person300Mapper.getNotilnInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
     * 의견 조회 
     * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
     * @return 게시물 정보 
     * @exception Exception
     */    
    public List<BbsNotiOpnVO> getNotiOpnInfoList(BbsNotiOpnVO vo) throws Exception{
    	try{
    		return person300Mapper.getNotiOpnInfoList(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 소통글 정보 조회 
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 VO
	 * @return 소통글 정보 
	 * @exception Exception
	 */    
    public PsnTmlnInfoVO getPsnTmlnInfoForUpdate(String json,HttpSession session) throws Exception {
    	
    	PsnTmlnInfoVO vo = new PsnTmlnInfoVO();
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			JSONObject psnTmlnObject = JSONObject.fromObject(json);
			
			vo.setTmlnSeq( psnTmlnObject.getInt("tmlnSeq")) ;
			vo.setUpdrId( info.getId()) ;
			vo.setUpdrName( info.getName()) ;

			vo = person300Mapper.getPsnTmlnInfoForUpdate(vo);
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return vo;
    }
    
    /**
     * 소통글 의견 조회 
     * @param PsnTmlnOpnVO - 조회할 정보가 담긴 VO
     * @return 소통글 정보 
     * @exception Exception
     */    
    public PsnTmlnOpnVO getPsnTmlnOpnForUpdate(String json,HttpSession session) throws Exception {
    	
    	PsnTmlnOpnVO vo = new PsnTmlnOpnVO();
    	try {
    		
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		
    		JSONObject psnTmlnOpnObject = JSONObject.fromObject(json);
    		
    		vo.setTmlnSeq( psnTmlnOpnObject.getInt("tmlnSeq")) ;
    		vo.setTmlnOpnSeq( psnTmlnOpnObject.getInt("tmlnOpnSeq"));
    		vo.setUpdrId( info.getId()) ;
    		vo.setUpdrName( info.getName()) ;
    		
    		vo = person300Mapper.getPsnTmlnOpnForUpdate(vo);
    		
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    	return vo;
    }
}
