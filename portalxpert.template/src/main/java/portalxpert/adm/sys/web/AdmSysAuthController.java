/**
 * 
 */
package portalxpert.adm.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysAuthVO;
import portalxpert.adm.sys.vo.AdmSysDeptInfo;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.common.config.Constant;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


/**
 * @author crossent
 *
 */
@Controller
@RequestMapping(value = "adm/sys")
public class AdmSysAuthController {
	
	@Resource(name = "admSysAuthService")
	private AdmSysAuthService admSysAuthService;
	
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmSysAuthController.class);
	
	/**
	 * 권한목록 조회
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmSysAuthList")
	public String getAdmSysAuthList(@ModelAttribute("admSysAuthVO") AdmSysAuthVO admSysAuthVO ,ModelMap modelMap) throws Exception{
		
		/** PropertyService.sample */
		admSysAuthVO.setPageUnit(admSysAuthVO.getPageUnit());
		admSysAuthVO.setPageSize(propertiesService.getInt("pageSize"));
		admSysAuthVO.setPageIndex(admSysAuthVO.getPageIndex());
    	
    	/** pageing setting */
    	PaginationInfo paginationInfo = new PaginationInfo();
		paginationInfo.setCurrentPageNo(admSysAuthVO.getPageIndex());
		paginationInfo.setRecordCountPerPage(admSysAuthVO.getPageUnit());
		paginationInfo.setPageSize(admSysAuthVO.getPageSize());
		
		admSysAuthVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
		admSysAuthVO.setLastIndex(paginationInfo.getLastRecordIndex());
		admSysAuthVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
		
		List<AdmSysAuthVO> notiList = admSysAuthService.getAdmSysAuthList(admSysAuthVO);
		int totCnt = admSysAuthService.getAdmSysAuthListCnt(admSysAuthVO);
		
		paginationInfo.setTotalRecordCount(totCnt);
		
		modelMap.put("paginationInfo", paginationInfo);
		modelMap.put("admSysAuthVO", admSysAuthVO);
		modelMap.put("notiList", notiList);
		
		
		return ".self/adm/sys/admSysAuthList";
	}	
	

	/**
	 * 권한정보 조회
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmSysAuthManage")
	public String getAdmSysAuthManage(@RequestParam(value="authCd",required = true) String authCd ,ModelMap modelMap) throws Exception{
		
		AdmSysAuthVO admSysAuthVO = new AdmSysAuthVO();
		if(!CommUtil.isEmpty(authCd)){
			admSysAuthVO.setAuthCd(authCd);
			admSysAuthVO = admSysAuthService.getAdmSysAuthInfo(admSysAuthVO);
		}
		
		modelMap.put("admSysAuthVO", admSysAuthVO);
		
		return ".self/adm/sys/admSysAuthManage";
	}	
	
	   /**
	    * 권한등록
		* @param modelMap
		* @return
		* @throws Exception
		*/
	@RequestMapping(value = "/insertAdmAuth")
	public ModelMap insertAdmAuth(@ModelAttribute("admSysAuthVO") AdmSysAuthVO admSysAuthVO ,ModelMap modelMap, HttpSession session) throws Exception{
	
			JSONResult jsonResult = new JSONResult();
				
			try{
				admSysAuthService.insertAuth(admSysAuthVO, session);
				
		   	}catch (Exception e) {
		   		jsonResult.setSuccess(false);
		   		jsonResult.setMessage(messageSource.getMessage("common.error"));
		   		jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
	}	
	
	
	/**
	 * 메뉴 조회
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/getAdmSysMenuManage")
	public String getAdmBbsStatList(@RequestParam(value="authCd",required = false) String authCd ,ModelMap modelMap) throws Exception{
		
		AdmSysMenuAuthVO admSysMenuAuthVO = new AdmSysMenuAuthVO();
		if(CommUtil.isEmpty(authCd)){
			admSysMenuAuthVO.setAuthCd(Constant.ROLE_SUPER.getVal());
		}else{
			admSysMenuAuthVO.setAuthCd(authCd);
		}
		AdmSysMenuAuthVO admSysMenuAuthInfo = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
		
		String conts = "[]";
		if(admSysMenuAuthInfo != null){
			conts = admSysMenuAuthInfo.getMenuConts();
		}
		
		//권한코드
		AdmSysAuthVO admSysAuthVO = new AdmSysAuthVO();
		List<AdmSysAuthVO> listAdmSysAuthVO = admSysAuthService.getAuchCodeList(admSysAuthVO);
		
		modelMap.put("authCodeList", JSONUtils.objectToJSON(listAdmSysAuthVO));
		modelMap.put("menuList", JSONUtils.objectToJSON(conts));
		modelMap.put("authCd", admSysMenuAuthVO.getAuthCd());
		modelMap.put("ROLE_SUPER", Constant.ROLE_SUPER.getVal());
		
		
		return ".self/adm/sys/admSysMenuManage";
	}	

	   /**
	    * 권한별 메뉴목록 조회
		* @param modelMap
		* @return
		* @throws Exception
		*/
	@RequestMapping(value = "/getAuthMenu")
	public ModelMap getAuthMenu(@RequestParam(value="authCd",required = true) String authCd ,ModelMap modelMap) throws Exception{
	
			JSONResult jsonResult = new JSONResult();
				
			try{
				AdmSysMenuAuthVO admSysMenuAuthVO = new AdmSysMenuAuthVO();
				admSysMenuAuthVO.setAuthCd(authCd);
				AdmSysMenuAuthVO admSysMenuAuthInfo = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
				
				if(admSysMenuAuthInfo != null){
					modelMap.put("menuList", admSysMenuAuthInfo.getMenuConts());
				}else{
					modelMap.put("menuList", "[]");
				}
				
				modelMap.put("authCd", authCd);
				
		   	}catch (Exception e) {
		   		jsonResult.setSuccess(false);
		   		jsonResult.setMessage(messageSource.getMessage("common.error"));
		   		jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
	}
	
	/**
	 * 권한별 메뉴목록 저장
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/updateMenuAuth")
	public ModelMap updateMenuAuth(@RequestParam(value="data" ,required = true) String data ,
									@RequestParam(value="authCd" ,required = true) String authCd, ModelMap modelMap, HttpSession session) throws Exception{
		
		JSONResult jsonResult = new JSONResult();
		
		try{
			AdmSysMenuAuthVO admSysMenuAuthVO = new AdmSysMenuAuthVO();
			admSysMenuAuthVO.setAuthCd(authCd);
			AdmSysMenuAuthVO admSysMenuAuthInfo = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
			
			if(admSysMenuAuthInfo == null){
				admSysMenuAuthVO.setMenuConts(data);
				admSysMenuAuthVO.setDelYn("N");
				admSysAuthService.insertMenuAuth(admSysMenuAuthVO, session);
			}else{
				admSysMenuAuthVO.setMenuConts(data);
				admSysAuthService.updateMenuAuth(admSysMenuAuthVO, session);
			}
			
		}catch (Exception e) {
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
	
}
