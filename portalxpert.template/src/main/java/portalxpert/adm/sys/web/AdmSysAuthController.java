/**
 * 
 */
package portalxpert.adm.sys.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.adm.gen.vo.AdmGenCodeManageVO;
import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysDeptInfo;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysPsnUserInfoVO;
import portalxpert.adm.sys.vo.AdmSysUserAuthVO;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.utils.JSONUtils;
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
		
		String conts = "";
		if(admSysMenuAuthInfo != null){
			conts = admSysMenuAuthInfo.getMenuConts();
		}
		
		modelMap.put("menuList", JSONUtils.objectToJSON(conts));
		
		
		return ".self/adm/sys/admSysMenuManage";
	}	

	
}
