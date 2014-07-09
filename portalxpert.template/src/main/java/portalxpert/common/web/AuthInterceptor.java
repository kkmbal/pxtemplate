package portalxpert.common.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import portalxpert.common.config.Constant;
import portalxpert.common.vo.UserInfoVO;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	
	private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> exceptLogin;
	
	/**
     * 로그인 제외 페이지
     * @param exceptLogin
     */
    public void setExceptLogin(List<String> exceptLogin){
    	this.exceptLogin = exceptLogin;
    }
    
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

    	UserInfoVO user = null;
    	try{
    		user = (UserInfoVO)WebUtils.getSessionAttribute(request, "pxLoginInfo");
    	}catch(IllegalStateException e){ 
    		//coherence exception 
    	}
    	
        if (user == null) {
        	//체크제외대상 url
        	if(checkIgnoreUrlPattern(request.getServletPath())){
                return true;
            } else {
            	if("json".equals(request.getParameter("format"))){
            		//response.sendError(500, "SESSION_CLOSE");
            		response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            		//throw new Exception("SESSION_CLOSE");
            		return false;
            	}else{
            		response.sendRedirect(request.getContextPath() + "/index.html");
            	}
            	return false;
            }
        } else{

        	request.setAttribute("pxLoginInfo", user);
        	request.setAttribute("userId", user.getSid());
            return true;
        }
    }
    
	private boolean checkIgnoreUrlPattern(String requestUri) {
        for(String urlPattern: exceptLogin) {
            if(pathMatcher.match(urlPattern, requestUri) ){
                return true;
            }
        }
        return false;
	}
    

}
