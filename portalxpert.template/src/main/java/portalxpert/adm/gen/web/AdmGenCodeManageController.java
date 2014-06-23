package portalxpert.adm.gen.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;

@Controller
@RequestMapping(value = "adm/gen")
public class AdmGenCodeManageController {

	@Resource(name = "admGenCodeManageService")
	private AdmGenCodeManageService admGenCodeManageService;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;
    
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
    
    private final static Logger logger = LoggerFactory.getLogger(AdmGenCodeManageController.class);

    /**
	 * 공통코드 상위코드 목록 화면 (pageing)
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommLCdList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonLCodeList")
	public String getAdmGenCommonLCodeList(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model) throws Exception {
		
		admGenCodeManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admGenCodeManageVO.setPageSize(propertiesService.getInt("pageSize"));
		

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admGenCodeManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admGenCodeManageVO.getPageUnit());
		paginationInfo.setPageSize(admGenCodeManageVO.getPageSize());

		admGenCodeManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admGenCodeManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admGenCodeManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = admGenCodeManageService.getAdmGenCommonListTotCnt(admGenCodeManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.put("paginationInfo", paginationInfo);
		/** pageing setting end */
		
		List<AdmGenCodeManageVO> admGenCodeList = admGenCodeManageService.getAdmGenCommonLCodeList(admGenCodeManageVO);
		model.put("admGenCodeList", admGenCodeList);
		
		AdmGenCodeManageVO pSearchVO = new AdmGenCodeManageVO();
		pSearchVO.setSearchCondition(admGenCodeManageVO.getSearchCondition());
		pSearchVO.setSearchKeyword(admGenCodeManageVO.getSearchKeyword());
		pSearchVO.setCurrentRecordCount(admGenCodeList.size());
		model.put("pSearch", pSearchVO);

		return ".adm/adm/gen/admGenCommLCdList";
	}
	
    /**
	 * 공통코드 상위코드 신규팝업
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommLCdPop"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonLCodeRegisterPop")
	public String getAdmGenCommonLCodeRegisterPop(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model
			) throws Exception {

		admGenCodeManageVO.setPageType("I");
		model.put("admGenCodeManage", admGenCodeManageVO);
		
		return ".self/adm/gen/admGenCommLCdPop";
	}

    /**
	 * 공통코드 상위코드 팝업
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommLCdPop"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonLCodeUpdatePop")
	public String admGenCommonLCodePop(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		if (admGenCodeManageVO.getCd()!= null || admGenCodeManageVO.getCd()!= "") {
			admGenCodeManageVO = admGenCodeManageService.getAdmGenCommonLCodeOne(admGenCodeManageVO);
			admGenCodeManageVO.setPageType("U");
			model.put("admGenCodeManage", admGenCodeManageVO);
		}
		
		return ".self/adm/gen/admGenCommLCdPop";
	}
	
    /**
	 * 상위 공통코드 저장
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmGenCommonLCode", method = RequestMethod.POST)
	public ModelMap insertAdmGenCommonLCode(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model,
			HttpSession session) throws Exception {
		
		String checkCd = admGenCodeManageVO.getCd();
		int count = 0;
		
		JSONResult jsonResult = new JSONResult();
		try{
			// 중복확인
			count = admGenCodeManageService.getAdmGenCheckCodeCdCnt(checkCd);
			
			// 추가
			admGenCodeManageService.insertAdmGenCommonLCode(admGenCodeManageVO, session);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("save.ok"));				

		}catch (Exception e) {
			jsonResult.setSuccess(false);
			
			if(count > 0){	// 중복되는 cd 존재
				jsonResult.setMessage("이미 존재하는 ID입니다.");
			}else{
				
		    	jsonResult.setMessage(messageSource.getMessage("common.error"));	
			}
			jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
		return model;
	}

    /**
	 * 상위 공통코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAdmGenCommonLCode", method = RequestMethod.POST)
	public ModelMap updateAdmGenCommonLCode(
			@ModelAttribute("admGenCodeManageVO")
			AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model,
			HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenCodeManageService.updateAdmGenCommonLCode(admGenCodeManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
			
		}catch (Exception e) {
	    	jsonResult.setSuccess(false);
	    	jsonResult.setMessage(messageSource.getMessage("common.error"));	
	    	jsonResult.setErrMessage(e.getMessage());
		}
		
		model.put("jsonResult", jsonResult);
		
		return model;
	}

    /**
	 * 상위 공통코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmGenCommonLCode", method = RequestMethod.POST)
	public ModelMap deleteAdmGenCommonLCode(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model,
			HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			// 세부코드 삭제
			admGenCodeManageService.deleteAdmGenCommonSCodeAll(admGenCodeManageVO, session);
			// 상위코드 삭제
			admGenCodeManageService.deleteAdmGenCommonLCode(admGenCodeManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
			
		}catch (Exception e) {
	    	jsonResult.setSuccess(false);
	    	jsonResult.setMessage(messageSource.getMessage("common.error"));	
	    	jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
		return model;
	}

    /**
	 * 공통코드 세부코드 목록 화면 (pageing)
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommSCdList"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonSCodeList")
	public String getAdmGenCommonSCodeList(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model) throws Exception {
		
		/** pageing setting start */
		admGenCodeManageVO.setPageUnit(propertiesService.getInt("pageUnit"));
		admGenCodeManageVO.setPageSize(propertiesService.getInt("pageSize"));

		PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admGenCodeManageVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admGenCodeManageVO.getPageUnit());
		paginationInfo.setPageSize(admGenCodeManageVO.getPageSize());

		admGenCodeManageVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admGenCodeManageVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admGenCodeManageVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		int totCnt = admGenCodeManageService.getAdmGenCommonSCodeListTotCnt(admGenCodeManageVO);
		paginationInfo.setTotalRecordCount(totCnt);
		model.put("paginationInfo", paginationInfo);
		/** pageing setting end */
		
		// 상위코드목록조회
		List<AdmGenCodeManageVO> admGenCodeTypeList = admGenCodeManageService.getAdmGenCommonLCodeType();
		model.put("admGenCodeTypeList", admGenCodeTypeList);
		
		// 세부코드목록조회
		List<AdmGenCodeManageVO> admGenCodeList = admGenCodeManageService.getAdmGenCommonSCodeList(admGenCodeManageVO);
		model.put("admGenCodeList", admGenCodeList);
		
		
		
		AdmGenCodeManageVO pSearchVO = new AdmGenCodeManageVO();
		pSearchVO.setSearchCondition(admGenCodeManageVO.getSearchCondition());
		pSearchVO.setSearchKeyword(admGenCodeManageVO.getSearchKeyword());
		pSearchVO.setCd(admGenCodeManageVO.getCd());
		pSearchVO.setCurrentRecordCount(admGenCodeList.size());
		model.put("pSearch", pSearchVO);
		
		
		return ".adm/adm/gen/admGenCommSCdList";
	}
	
    /**
	 * 공통코드 세부코드 팝업
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommSCdPop"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonSCodeRegisterPop")
	public String getAdmGenCommonSCodeRegisterPop(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model) throws Exception {

		admGenCodeManageVO.setPageType("I");
		model.put("admGenCodeManage", admGenCodeManageVO);
		
		return ".self/adm/gen/admGenCommSCdPop";
	}
	
    /**
	 * 공통코드 세부코드 팝업
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param model
	 * @return "/adm/gen/admGenCommSCdPop"
	 * @exception Exception
	 */
	@RequestMapping(value = "/getAdmGenCommonSCodeUpdatePop")
	public String getAdmGenCommonSCodeUpdatePop(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model, HttpServletRequest request) throws Exception {
		
		if (!admGenCodeManageVO.getCdSpec().equals("")) {
			admGenCodeManageVO = admGenCodeManageService.getAdmGenCommonSCodeOne(admGenCodeManageVO);
			admGenCodeManageVO.setPageType("U");
			model.put("admGenCodeManage", admGenCodeManageVO);
		}
		
		return ".self/adm/gen/admGenCommSCdPop";
	}
	
    /**
	 * 세부 공통코드 저장
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/insertAdmGenCommonSCode", method = RequestMethod.POST)
	public ModelMap insertAdmGenCommonSCode(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model,
			HttpSession session) throws Exception {
		
		int count=0;
		
		JSONResult jsonResult = new JSONResult();
		try{
			count = admGenCodeManageService.getAdmGenCheckCodeCdSpecCnt(admGenCodeManageVO);

			admGenCodeManageService.insertAdmGenCommonSCode(admGenCodeManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("save.ok"));				
		}catch (Exception e) {
	    	jsonResult.setSuccess(false);
	    	if(count >0){
	    		jsonResult.setMessage("이미 존재하는 ID입니다.");
	    	}else{
	    		jsonResult.setMessage(messageSource.getMessage("common.error"));
	    	}
	    	jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
		return model;
	}

    /**
	 * 세부 공통코드 수정
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/updateAdmGenCommonSCode", method = RequestMethod.POST)
	public ModelMap updateAdmGenCommonSCode(
			@ModelAttribute("admGenCodeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenCodeManageService.updateAdmGenCommonSCode(admGenCodeManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("update.ok"));
			
		}catch (Exception e) {
			e.getMessage();
	    	jsonResult.setSuccess(false);
	    	jsonResult.setMessage(messageSource.getMessage("common.error"));
	    	jsonResult.setErrMessage(e.getMessage());
		}
		
		model.put("jsonResult", jsonResult);
		

		return model;
	}
    /**
	 * 세부 공통코드 삭제
	 * @param admGenCodeManageVO - 조회할 정보가 담긴 admGenCodeManageVO
	 * @param ModelMap
	 * @return model
	 * @exception Exception
	 */
	@RequestMapping(value = "/deleteAdmGenCommonSCode", method = RequestMethod.POST)
	public ModelMap deleteAdmGenCommonSCode(
			@ModelAttribute("codeManageVO") AdmGenCodeManageVO admGenCodeManageVO,
			ModelMap model, HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session) throws Exception {

		JSONResult jsonResult = new JSONResult();
		
		try{
			admGenCodeManageService.deleteAdmGenCommonSCode(admGenCodeManageVO, session);
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("delete.ok"));
			
		}catch (Exception e) {
	    	jsonResult.setSuccess(false);
	    	jsonResult.setMessage(messageSource.getMessage("common.error"));	
	    	jsonResult.setErrMessage(e.getMessage());
		}
		model.put("jsonResult", jsonResult);
		
		return model;
	}
}
