package portalxpert.common.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysAuthVO;
import portalxpert.adm.sys.vo.AdmSysMenuAuthVO;
import portalxpert.adm.sys.vo.AdmSysUserAuthVO;
import portalxpert.common.config.Constant;
import portalxpert.common.sc.UserLoginService;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;

@Controller
public class LoginController {

	@Resource(name = "userLoginService")
	private UserLoginService userLoginService;
	
	@Resource(name = "admSysAuthService")
	private AdmSysAuthService admSysAuthService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
   
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/doLogin")
	public ModelMap login(HttpServletRequest request, ModelMap modelMap) {

		JSONResult jsonResult = new JSONResult();

		String userId = request.getParameter("userId");
		String passwd = (String) request.getParameter("passwd");
		String ssnId = (String) request.getParameter("ssnId");
		
		try{
			UserInfoVO vo = null;
			if(ssnId == null || "".equals(ssnId)) {
				vo = userLoginService.getLoginInfo(userId);
				
				//권한
				List<AdmSysAuthVO> authList = admSysAuthService.getAuthInfo(userId);
				vo.setAuthCd(getAuthList(authList));
				vo.setAuthCdStr(CommUtil.getListToStr(getAuthList(authList)));
				
				// 메뉴
				AdmSysMenuAuthVO admSysMenuAuthVO = new AdmSysMenuAuthVO();
				admSysMenuAuthVO.setAuthCd(vo.getAuthCdStr());
				admSysMenuAuthVO = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
				if(admSysMenuAuthVO != null){
					vo.setMenuConts(admSysMenuAuthVO.getMenuConts());
				}
			}else {
				vo = userLoginService.getLoginInfoBySsnId(ssnId);
				
				//권한
				List<AdmSysAuthVO> authList = admSysAuthService.getAuthInfo(vo.getId());
				vo.setAuthCd(getAuthList(authList));
				vo.setAuthCdStr(CommUtil.getListToStr(getAuthList(authList)));
				
				// 메뉴
				AdmSysMenuAuthVO admSysMenuAuthVO = new AdmSysMenuAuthVO();
				admSysMenuAuthVO.setAuthCd(vo.getAuthCdStr());
				admSysMenuAuthVO = admSysAuthService.getAdmSysMenuAuthInfo(admSysMenuAuthVO);
				if(admSysMenuAuthVO != null){
					vo.setMenuConts(admSysMenuAuthVO.getMenuConts());
				}
			}
			
			if(null == vo || StringUtils.isEmpty(vo.getSid()) || StringUtils.isEmpty(vo.getPasswd()) || !vo.getPasswd().equals(passwd)){
				logger.debug(">>>>>>>>>>login fail:"+userId);
				jsonResult.setSuccess(false);
			}else{
				logger.debug(">>>>>>>>>>login success:"+userId);
				request.getSession().setAttribute("pxLoginInfo", vo);
				request.getSession().setAttribute("userId", vo.getSid() );
				if(Constant.ROLE_SUPER.getVal().equals(vo.getAuthCd())){
					request.getSession().setAttribute("superAdmin", Constant.ROLE_SUPER.getVal());
				}
			}
		}catch(Exception e){
			logger.error(e.toString(), e);
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error"));
		}
		modelMap.put("jsonResult", jsonResult);
	   return  modelMap;
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request, ModelMap modelMap) throws Exception {
		try{
			request.getSession().removeAttribute("pxLoginInfo");
			request.getSession().removeAttribute("userId");
	    	request.getSession().invalidate();
		}catch(IllegalStateException e){
		}
    	return "redirect:/index.html";
	}
	
	private List<String> getAuthList(List<AdmSysAuthVO> list){
		if(list == null || list.size() == 0) return null;
		List<String> listStr = new ArrayList<String>();
		for(AdmSysAuthVO vo : list){
			listStr.add(vo.getAuthCd());
		}
		return listStr;
	}
}
