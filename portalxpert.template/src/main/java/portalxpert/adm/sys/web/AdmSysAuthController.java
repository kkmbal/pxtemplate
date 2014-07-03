/**
 * 
 */
package portalxpert.adm.sys.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.adm.gen.sc.AdmGenCodeManageService;
import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import egovframework.rte.fdl.property.EgovPropertyService;


/**
 * @author crossent
 *
 */
@Controller
@RequestMapping(value = "adm/sys")
public class AdmSysAuthController {
	
	@Resource(name = "admSysAuthService")
	private AdmSysAuthService admSysAuthService;
	
	@Resource(name = "admGenCodeManageService")
	private AdmGenCodeManageService admGenCodeManageService;
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
	
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(AdmSysAuthController.class);
	
	
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
			admSysMenuAuthVO.setAuthCd("SYSTEM");
		}else{
			admSysMenuAuthVO.setAuthCd(authCd);
		}
		AdmSysMenuAuthVO admSysMenuAuthInfo = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
		
		String conts = "[]";
		if(admSysMenuAuthInfo != null){
			conts = admSysMenuAuthInfo.getMenuConts();
		}
		
		//권한코드
		AdmGenCodeManageVO admGenCodeManageVO = new AdmGenCodeManageVO();
		admGenCodeManageVO.setCd("AUTH");
		List<AdmGenCodeManageVO> admGenCommonCodeSpecList = admGenCodeManageService.getAdmGenCommonCodeSpecList(admGenCodeManageVO);
		
		modelMap.put("authCodeList", JSONUtils.objectToJSON(admGenCommonCodeSpecList));
		modelMap.put("menuList", JSONUtils.objectToJSON(conts));
		modelMap.put("authCd", admSysMenuAuthVO.getAuthCd());
		
		
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
