package portalxpert.person.person300.mapper;

import java.util.List;

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
import egovframework.rte.psl.dataaccess.mapper.Mapper;


@Mapper("person300Mapper")
public interface Person300Mapper  {
    
    /**
     * 소통글 등록 
     * @param PsnTmlnInfoVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPsnTmlnInfo(PsnTmlnInfoVO vo) throws Exception  ;
    
    /**
	 * 소통글 조회
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 Map
	 * @return 소통글 정보 
	 * @exception
	 */
    public List<PsnTmlnInfoVO> getPsnTmlnInfoList(PsnTmlnInfoVO vo) ;
    
    /**
	 * 소통글에 대한 의견 조회
	 * @param PsnTmlnOpnVO - 조회할 정보가 담긴 Map
	 * @return 소통글의 대한 의견 정보 
	 * @exception
	 */
    public List<PsnTmlnOpnVO> getPsnTmlnOpnList(PsnTmlnOpnVO vo) ;
	
    /**
     * 소통글에 대한 의견 등록 
     * @param PsnTmlnOpnVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPsnTmlnOpn(PsnTmlnOpnVO vo) throws Exception  ;
    
    /**
     * 소통글 첨부 정보 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPsnTmlnApndFile(PsnTmlnApndFileVO vo) throws Exception  ;
    
    /**
     * 소통글 첨부 정보 조회 
     * @param PsnTmlnApndFileVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 첨부  정보 
	 * @exception Exception
     */
    
    public List<PsnTmlnApndFileVO> getPsnTmlnApndFileList(PsnTmlnApndFileVO vo) ;
    
    /**
     * 설문 정보 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 설문 보기 정보 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiSurveyExmpl(BbsNotiSurveyExmplVO vo) throws Exception  ;
    
    /**
     * 소통글 설문정보 조회 
     * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 첨부  정보 
	 * @exception Exception
     */
    
    public List<BbsNotiSurveyVO> getBbsNotiSurveyList(BbsNotiSurveyVO vo) ;
    
    /**
     * 소통글 설문 보기 조회 
     * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 첨부  정보 
	 * @exception Exception
     */
    
    public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplList(BbsNotiSurveyExmplVO vo) ;
    
    /**
     * 설문 보기 결과 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertBbsNotiSurveyAnsw(BbsNotiSurveyAnswVO vo) throws Exception  ;
    
    /**
     * 소통글 수정 
     * @param PsnTmlnInfoVO - 수정할 정보가 담긴 VO 
     * @return 수정 성공 건수
	 * @exception Exception
     */
    public int updatePsnTmlnInfo(PsnTmlnInfoVO vo) throws Exception  ;
    
    /**
     * 소통글 삭제
     * @param PsnTmlnInfoVO - 삭제할 정보가 담긴 VO 
     * @return 삭제 성공 건수
	 * @exception Exception
     */
    public int deletePsnTmlnInfo(PsnTmlnInfoVO vo) throws Exception  ;
    
    /**
     * 소통글의 첨부정보 삭제
     * @param PsnTmlnInfoVO - 삭제할 정보가 담긴 VO 
     * @return 삭제 성공 건수
	 * @exception Exception
     */
    public int deletePsnTmlnApndFile(PsnTmlnApndFileVO vo) throws Exception  ;
    
    /**
     * 소통글 삭제
     * @param PsnTmlnInfoVO - 삭제할 정보가 담긴 VO 
     * @return 삭제 성공 건수
	 * @exception Exception
     */
    public int deletePsnTmlnOpn(PsnTmlnOpnVO vo) throws Exception  ;
    
    /**
     * 소통글 의견 수정 
     * @param PsnTmlnOpnVO - 수정할 정보가 담긴 VO 
     * @return 수정 성공 건수
	 * @exception Exception
     */
    public int updatePsnTmlnOpn(PsnTmlnOpnVO vo) throws Exception  ;
    
    
    /**
     * 소통글 의견 삭제
     * @param PsnTmlnOpnVO - 삭제할 정보가 담긴 VO 
     * @return 삭제 성공 건수
	 * @exception Exception
     */
    public int deleteTmlnOpn(PsnTmlnOpnVO vo) throws Exception  ;
    
    
    /**
     * 좋아요 등록 
     * @param PsnTmlnApndFileVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int insertPsnTmlnEvalInfo(PsnTmlnEvalInfoVO vo) throws Exception  ;
    
    /**
     * 좋아요 조회 
     * @param PsnTmlnEvalInfoVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 좋아요  정보 
	 * @exception Exception
     */
    public int getPsnTmlnEvalInfoList(PsnTmlnEvalInfoVO vo) ;
    
    
    /**
     * 좋아요 사용자 정보 조회 
     * @param PsnTmlnEvalInfoVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 좋아요 사용자 정보 
	 * @exception Exception
     */
    
    public List<PsnTmlnEvalInfoVO> getPsnTmlnEvalUserList(PsnTmlnEvalInfoVO vo) ;
    
    
    /**
     * 읽음 처리 
     * @param UserInfoVO - 입력할 정보가 담긴 VO 
     * @return 입력 성공 건수
	 * @exception Exception
     */
    public int updatePsnTmlnReadYn(UserInfoVO vo) throws Exception  ;
    
    /**
     * 설문결과 Title 정보 
     * @param PsnSurveyResultVO - 조회할 정보가 담긴 VO 
     * @return 소통글의 대한 좋아요 사용자 정보 
	 * @exception Exception
     */    
    public List<PsnSurveyResultVO> getSurveyResultTitle(PsnSurveyResultVO vo) ;
    
    /**
     * 설문결과 질문정보  
     * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO 
     * @return 설문결과 질문정보 
	 * @exception Exception
     */    
    public List<BbsNotiSurveyVO> getBbsNotiSurveyResultList(BbsNotiSurveyVO vo) ;
    
    
    /**
     * 설문수정 보기정보  
     * @param BbsNotiSurveyExmplVO - 조회할 정보가 담긴 VO 
     * @return 설문수정 보기정보 
	 * @exception Exception
     */    
    public List<BbsNotiSurveyExmplVO> getBbsNotiSurveyExmplUpdateList(BbsNotiSurveyExmplVO vo) ;
    
    
    /**
     * 설문결과 보기정보  
     * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO 
     * @return 설문결과 보기정보 
	 * @exception Exception
     */    
    public List<BbsNotiSurveyExmplVO> getSurveyResultExmpl(BbsNotiSurveyExmplVO vo) ;
    
    /**
     * 설문 제출여부  
     * @param BbsNotiSurveyAnswVO - 조회할 정보가 담긴 VO 
     * @return 설문 제출여부 
	 * @exception Exception
     */    
    public int getBbsNotiSurveyResultYN(BbsNotiSurveyAnswVO vo) throws Exception  ;
    
    
    /**
     * 설문 정보 삭제  
     * @param BbsNotiSurveyVO - 삭제할 정보가 담긴 VO 
     * @return 설문 정보 삭제 
	 * @exception Exception
     */    
    public int deleteBbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 설문 보기 정보 삭제  
     * @param BbsNotiSurveyExmplVO - 삭제할 정보가 담긴 VO 
     * @return 설문 보기 정보 삭제 
	 * @exception Exception
     */    
    public int deleteBbsNotiSurveyExmpl(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 설문 제출 건수  
     * @param BbsNotiSurveyVO - 조회할 정보가 담긴 VO 
     * @return 설문 제출 건수 
	 * @exception Exception
     */    
    public int getBbsNotiSurveyResultCnt(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
     * 설문 정보 마감일자 수정 
     * @param BbsNotiSurveyVO - 입력할 정보가 담긴 VO 
     * @return 수정 성공 건수
	 * @exception Exception
     */
    public int updateBbsNotiSurvey(BbsNotiSurveyVO vo) throws Exception  ;
    
    /**
	 * 게시물 조회 
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 VO
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
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 Map
	 * @return 소통글 정보 
	 * @exception
	 */
    public PsnTmlnInfoVO getPsnTmlnInfoForUpdate(PsnTmlnInfoVO vo) ;
    
    /**
     * 소통글 의견 조회
     * @param PsnTmlnInfoVO - 조회할 정보가 담긴 Map
     * @return 소통글 의견 
     * @exception
     */
    public PsnTmlnOpnVO getPsnTmlnOpnForUpdate(PsnTmlnOpnVO vo) ;
    
    
    /**
	 * 소통글 사용자 조회
	 * @param PsnTmlnInfoVO - 조회할 정보가 담긴 Map
	 * @return 소통글 정보 
	 * @exception
	 */
    public PsnTmlnInfoVO getPsnTmlnUser(PsnTmlnInfoVO vo) ;
}
