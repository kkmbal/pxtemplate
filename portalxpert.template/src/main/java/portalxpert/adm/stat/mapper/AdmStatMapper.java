/**
 * 
 */
package portalxpert.adm.stat.mapper;

import java.util.List;

import portalxpert.adm.stat.vo.AdmStatBBSVO;
import portalxpert.adm.stat.vo.AdmStatSearchVO;
import portalxpert.adm.stat.vo.AdmStatSurveyVO;
import portalxpert.adm.stat.vo.AdmStatUseVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

/**
 * @author yoDJ
 *
 */
@Mapper("admStatMapper")
public interface AdmStatMapper {

	public AdmStatBBSVO getAdmBbsInfoStatistics(AdmStatSearchVO searchForm);

	public List<AdmStatBBSVO> getAdmBbsTopList(AdmStatSearchVO searchForm);

	public List<AdmStatBBSVO> getAdmMessengerStatistics(AdmStatSearchVO searchForm);

	public AdmStatBBSVO getAdmPBbsInfoStatistics(AdmStatSearchVO searchForm);

	public List<AdmStatBBSVO> getAdmPBbsTopList(AdmStatSearchVO searchForm);

	public List<AdmStatUseVO> getAdmUseStatList(AdmStatSearchVO searchForm);

	public List<AdmStatBBSVO> getAdmPBbsPopup(AdmStatSearchVO searchForm);
	
	/**
	 * 설문리스트
	 * @return
	 * @throws Exception
	 */
	List<AdmStatSurveyVO> getAdmSurVeyList(AdmStatSurveyVO admStatSurveyVO);
	
	/**
	 * 설문리스트 갯수
	 * @param admStatSurveyVO
	 * @return
	 * @throws Exception
	 */
	int getAdmSurVeyCnt(AdmStatSurveyVO admStatSurveyVO);

	public int getAdmPBbsPopupCnt(AdmStatSearchVO searchForm);
	
	public List<AdmStatBBSVO> getAdmBbsStatList(AdmStatSearchVO searchForm);
	public int getAdmBbsStatListTotCnt(AdmStatSearchVO searchForm);

}
