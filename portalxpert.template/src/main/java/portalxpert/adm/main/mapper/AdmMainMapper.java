package portalxpert.adm.main.mapper;

import java.util.List;

import portalxpert.adm.main.vo.AdmMainVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("admMainMapper")
public interface AdmMainMapper {
	
	/**
	 * 선택안된 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getUnselectContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 필수 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getMandatoryContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기본 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getDefaultContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기존 설정 컨텐츠 삭제
	 * @param amVO
	 * @throws Exception
     * @author crossent
	 */
	public void deleteStandardContent(AdmMainVO amVO) throws Exception;
	
	/**
	 * 설정된 컨텐츠 저장
	 * @param amVO
	 * @throws Exception
     * @author crossent
	 */
	public void insertStandardContent(AdmMainVO amVO) throws Exception;
	
	/**
	 * 필수 게시판 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getMandatoryBoardList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기본 게시판 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getDefaultBoardList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기존 설정된 게시판 삭제
	 * @throws Exception
     * @author crossent
	 */
	public void deleteStandardBoard(AdmMainVO amVO) throws Exception;
	
	/**
	 * 설정된 게시판 저장
	 * @param amVO
	 * @throws Exception
     * @author crossent
	 */
	public void insertStandardBoard(AdmMainVO amVO) throws Exception;
	
	/**
	 * 업무데스크 카테고리 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getWorkDeskCategoryList() throws Exception;
	
	/**
	 * 업무데스크 카테고리별 항목 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public List getWorkDeskList() throws Exception;
	
	/**
	 * 업무데스크 설정 건수
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	public int getWorkDeskCnt() throws Exception;
	
	/**
	 * 기존 설정 리셋
	 * @throws Exception
     * @author crossent
	 */
	public void updateWorkDeskReset() throws Exception;
	
	/**
	 * 설정된 항목 저장
	 * @param amVO
	 * @throws Exception
     * @author crossent
	 */
	public void updateWorkDesk(AdmMainVO amVO) throws Exception;
	
}
