package portalxpert.person.person300.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.board.board100.vo.BbsNotiOpnVO;
import portalxpert.board.board100.vo.BbsNotiSurveyAnswVO;
import portalxpert.board.board100.vo.BbsNotiSurveyExmplVO;
import portalxpert.board.board100.vo.BbsNotiSurveyVO;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.person.person300.vo.PsnSurveyResultVO;
import portalxpert.person.person300.vo.PsnTmlnApndFileVO;
import portalxpert.person.person300.vo.PsnTmlnEvalInfoVO;
import portalxpert.person.person300.vo.PsnTmlnInfoVO;
import portalxpert.person.person300.vo.PsnTmlnOpnVO;

public interface Person300Service {
	
	
	/**
     * 소통글 등록 
     * @param 소통글 정보  
     * @return 입력 성공 건수
     */
	PsnTmlnInfoVO insertPsnUserTmlnInfo(String json,HttpSession session) throws Exception;
	
	/**
     * 소통글 조회 
     * @param 소통글 정보  
     * @return 소통글 리스트
     */
	public List<PsnTmlnInfoVO> getPsnTmlnInfoList(PsnTmlnInfoVO vo) throws Exception;
	
	/**
     * 소통글에 대한 의견 조회 
     * @param 의견 정보  
     * @return 소통글에 대한 의견 리스트
     */
	public List<PsnTmlnOpnVO> getPsnTmlnOpnList(PsnTmlnOpnVO vo) throws Exception;
	
	/**
     * 소통글에 대한 의견 등록 
     * @param 소통글 의견 정보  
     * @return 입력 객체
     */
	PsnTmlnOpnVO insertPsnTmlnOpn(String json,HttpSession session) throws Exception;
	
	/**
     * 소통글에 대한 첨부(이미지) 조회 
     * @param 첨부 정보  
     * @return 소통글에 대한 첨부 리스트
     */
	public List<PsnTmlnApndFileVO> getPsnTmlnApndFileList(PsnTmlnApndFileVO vo) throws Exception;
	
	/**
     * 소통글에 대한 설문 조회 
     * @param 첨부 정보  
     * @return 소통글에 대한 설문 리스트
     */
	public List<BbsNotiSurveyVO> getBbsNotiSurveyList(BbsNotiSurveyVO vo) throws Exception;
	
	/**
     * 소통글에 대한 설문 보기 조회 
     * @param 첨부 정보  
     * @return 소통글에 대한 설문 보기 리스트
     */
	public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplList(BbsNotiSurveyExmplVO vo) throws Exception;
	
	/**
     * 소통글에 대한 설문 결과 등록 
     * @param 소통글에 대한 설문 결과정보  
     * @return 입력 객체
     */
	BbsNotiSurveyAnswVO insertBbsNotiSurveyAnsw(String json, HttpSession session) throws Exception;
	
	/**
     * 소통글 수정
     * @param 소통글 정보  
     * @return 수정 성공 건수
     */
	PsnTmlnInfoVO updatePsnUserTmlnInfo(String json,HttpSession session) throws Exception;
	
	/**
     * 소통글 삭제
     * @param 소통글 정보  
     * @return 삭제 성공 건수
     */
	PsnTmlnInfoVO deletePsnUserTmlnInfo(String json,HttpSession session) throws Exception;
	
	/**
     * 소통글 의견 수정
     * @param 소통글 의견 정보  
     * @return 수정 성공 건수
     */
	PsnTmlnOpnVO updatePsnTmlnOpn(String json,HttpSession session) throws Exception;	
	
	/**
     * 소통글 의견 삭제
     * @param 소통글 정보  
     * @return 삭제 성공 건수
     */
	PsnTmlnOpnVO deleteTmlnOpn(String json,HttpSession session) throws Exception;
	
	/**
     * 소통글에 대한 좋아요 등록 
     * @param 좋아요 정보  
     * @return 입력 객체
     */
	PsnTmlnEvalInfoVO insertPsnTmlnEvalInfo(String json, HttpSession session) throws Exception;
	
	/**
     * 소통글에 대한 좋아요 조회 
     * @param 좋아요 정보  
     * @return 좋아요 건수
     */
	public int getPsnTmlnEvalInfoList(String json, HttpSession session, String kind) throws Exception;
	
	/**
     * 좋아요 사용자 정보 
     * @param   
     * @return 좋아요 사용자 리스트
     */
	public List<PsnTmlnEvalInfoVO> getPsnTmlnEvalUserList(PsnTmlnEvalInfoVO vo) throws Exception;
	
	/**
     * 소통글 읽음 처리 
     * @param   
     * @return 소통글 읽음 처리 결과
     */
	public UserInfoVO updatePsnTmlnReadYn(UserInfoVO vo ) throws Exception;
	
	/**
     * 설문결과 타이틀 
     * @param   
     * @return 설문결과 타이틀
     */
	public List<PsnSurveyResultVO> getSurveyResultTitle(PsnSurveyResultVO vo) throws Exception;
	
	/**
     * 설문결과 질문정보 조회 
     * @param   
     * @return 설문결과 질문정보 
     */
	public List<BbsNotiSurveyVO> getBbsNotiSurveyResultList(BbsNotiSurveyVO vo)  throws Exception;
	
	/**
     * 설문결과 질문정보 조회 
     * @param   
     * @return 설문결과 질문정보 
     */
	public List<BbsNotiSurveyExmplVO> getSurveyResultExmpl(BbsNotiSurveyExmplVO vo)  throws Exception;
	
	/**
     * 설문제출 여부 
     * @param   
     * @return 설문 제출 여부 
     */
	public int getBbsNotiSurveyResultYN(BbsNotiSurveyAnswVO vo)  throws Exception;
	
	/**
     * 설문수정 보기정보 조회 
     * @param   
     * @return 설문수정 보기정보 조회 
     */
	public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplUpdateList(BbsNotiSurveyExmplVO vo)  throws Exception;
	
	
	/**
     * 설문정보 수정 
     * @param 설문정보 수정  
     * @return 수정 정보
     */
	PsnTmlnInfoVO updateTmlnSurveyInfo(String json,HttpSession session) throws Exception;
	
	/**
     * 설문제출 건수 
     * @param   
     * @return 설문 제출 건수 
     */
	public int getBbsNotiSurveyResultCnt(BbsNotiSurveyVO vo) throws Exception;
	
	/**
     * 설문정보 수정(마감일자) 
     * @param 설문정보 수정 (마감일자) 
     * @return 수정 정보
     */
	String updateTmlnSurveyInfo2(String json,HttpSession session) throws Exception;

    /**
	 * 게시물 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsNotiInfoVO> getNotilnInfoList(BbsNotiInfoVO vo) throws Exception;
    
    /**
     * 의견 조회 
     * @param BbsNotiOpnVO - 조회할 정보가 담긴 VO
     * @return 게시물 정보 
     * @exception Exception
     */    
    public List<BbsNotiOpnVO> getNotiOpnInfoList(BbsNotiOpnVO vo) throws Exception;
    
	/**
     * 소통글 조회 
     * @param 소통글 정보  
     * @return 소통글
     */
	public PsnTmlnInfoVO getPsnTmlnInfoForUpdate(String json,HttpSession session) throws Exception;
	
	/**
	 * 소통글 의견 조회 
	 * @param 소통글 의견 정보  
	 * @return 소통글 의견
	 */
	public PsnTmlnOpnVO getPsnTmlnOpnForUpdate(String json,HttpSession session) throws Exception;
}
