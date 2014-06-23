package portalxpert.adm.main.sc;

import java.util.List;

import portalxpert.adm.main.vo.AdmMainVO;

public interface AdmMainService {
	
	/**
	 * 선택안된 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getUnselectContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 필수 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getMandatoryContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기본 컨텐츠 조회
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getDefaultContentList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 선택된 컨텐츠 저장 처리
	 * @param json
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	String setSelectContent(String json, AdmMainVO amVO) throws Exception;
	
	/**
	 * 필수 게시판 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getMandatoryBoardList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 기본 게시판 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getDefaultBoardList(AdmMainVO amVO) throws Exception;
	
	/**
	 * 선택된 게시판 저장 처리
	 * @param json
	 * @param userVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	String setSelectBoard(String json, AdmMainVO amVO) throws Exception;
	
	/**
	 * 업무데스크 카테고리 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getWorkDeskCategoryList() throws Exception;
	
	/**
	 * 업무데스크 카테고리별 항목 조회
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	List getWorkDeskList() throws Exception;	
	
	/**
	 * 업무데스크 건수
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	int getWorkDeskCnt() throws Exception;
	
	/**
	 * 업무데스크 저장 처리
	 * @param json
	 * @param amVO
	 * @return
	 * @throws Exception
     * @author crossent
	 */
	String setSelectWorkDesk(String json, AdmMainVO amVO) throws Exception;
}
