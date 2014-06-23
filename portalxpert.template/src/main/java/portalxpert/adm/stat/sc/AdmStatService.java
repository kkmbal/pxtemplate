/**
 * 
 */
package portalxpert.adm.stat.sc;

import java.util.List;

import portalxpert.adm.stat.vo.AdmStatBBSVO;
import portalxpert.adm.stat.vo.AdmStatSearchVO;
import portalxpert.adm.stat.vo.AdmStatSurveyVO;
import portalxpert.adm.stat.vo.AdmStatUseVO;

/**
 * @author yoDJ
 *
 */
public interface AdmStatService {

	/**
	 * 게시판 통계정보
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public AdmStatBBSVO getAdmBbsInfoStatistics(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 게시판 조회수(Top20)
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatBBSVO> getAdmBbsTopList(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 메신저통계
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatBBSVO> getAdmMessengerStatistics(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 개인게시판 통계기본정보
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public AdmStatBBSVO getAdmPBbsInfoStatistics(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 개인게시판 Top20
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatBBSVO> getAdmPBbsTopList(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 이용현황 통계
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatUseVO> getAdmUseStatList(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 개인게시판 게시판 선택 팝업창
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatBBSVO> getAdmPBbsPopup(AdmStatSearchVO searchForm) throws Exception;
	
	/**
	 * 개인게시판 게시판 선택 팝업창 CNT
	 * @param searchForm
	 * @return
	 */
	public int getAdmPBbsPopupCnt(AdmStatSearchVO searchForm) throws Exception;

	/**
	 * 설문리스트
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatSurveyVO> getAdmSurVeyList(AdmStatSurveyVO admStatSurveyVO) throws Exception;
	
	/**
	 * 설문리스트 갯수
	 * @param admStatSurveyVO
	 * @return
	 * @throws Exception
	 */
	public int getAdmSurVeyCnt(AdmStatSurveyVO admStatSurveyVO) throws Exception;
	
	/**
	 * 게시판 통계 조회
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public List<AdmStatBBSVO> getAdmBbsStatList(AdmStatSearchVO searchForm)	throws Exception;
	
	/**
	 * 게시판 통계 총갯수
	 * @param searchForm
	 * @return
	 * @throws Exception
	 */
	public int getAdmBbsStatListTotCnt(AdmStatSearchVO searchForm) throws Exception;
}
