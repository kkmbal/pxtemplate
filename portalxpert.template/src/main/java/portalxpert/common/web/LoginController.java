package portalxpert.common.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import portalxpert.common.sc.UserLoginService;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;

@Controller
public class LoginController {

	@Resource(name = "userLoginService")
	private UserLoginService userLoginService;
	
	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;
   
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping("/doLogin")
	public ModelMap login(HttpServletRequest request, ModelMap modelMap) {

		JSONResult jsonResult = new JSONResult();

		String sid = request.getParameter("userId");
		String ssnId = (String) request.getParameter("ssnId");
		
		try{
			UserInfoVO vo = null;
			if(ssnId == null || "".equals(ssnId)) {
				vo = userLoginService.getLoginInfo(sid);
			}else {
				vo = userLoginService.getLoginInfoBySsnId(ssnId);
			}
			
			if(null == vo || StringUtils.isEmpty(vo.getSid())){
				logger.debug(">>>>>>>>>>login fail:"+sid);
				jsonResult.setSuccess(false);
			}else{
				logger.debug(">>>>>>>>>>login success:"+sid);
				request.getSession().setAttribute("pxLoginInfo", vo);
				request.getSession().setAttribute("userId", vo.getSid() );
			}
		}catch(Exception e){
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
	
}
