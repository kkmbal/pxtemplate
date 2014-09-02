package portalxpert.common.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.WebUtils;

import portalxpert.adm.sys.sc.AdmSysAuthService;
import portalxpert.adm.sys.vo.AdmSysAuthVO;
import portalxpert.common.exception.PortalxpertException;
import portalxpert.common.utils.CommUtil;
import portalxpert.common.vo.UserInfoVO;

@Component
public class AuthUrlPatternInterceptor extends HandlerInterceptorAdapter {
	
	@Resource(name = "admSysAuthService")
	private AdmSysAuthService admSysAuthService;
	
	private PathMatcher pathMatcher = new AntPathMatcher();
    private List<String> exceptPattern;
	
	/**
     * 로그인 제외 페이지
     * @param exceptLogin
     */
    public void setExceptPattern(List<String> exceptPattern){
    	this.exceptPattern = exceptPattern;
    }
    
    public boolean preHandle(HttpServletRequest request,
            HttpServletResponse response, Object handler) throws Exception {

    	UserInfoVO user = null;
    	try{
    		user = (UserInfoVO)WebUtils.getSessionAttribute(request, "pxLoginInfo");
    	}catch(IllegalStateException e){ 
    		//coherence exception 
    	}
    	
        if (user != null) {
        	//체크제외대상 url
        	if(checkIgnoreUrlPattern(request.getServletPath())){
                return true;
            } 
        	if(user.getAuthCd() != null){
        		List<String> authCdList = user.getAuthCd();
        		AdmSysAuthVO admSysAuthVO = null;
        		for(String authCd : authCdList){
        			admSysAuthVO = new AdmSysAuthVO();
	    			admSysAuthVO.setAuthCd(authCd);
	    			admSysAuthVO = admSysAuthService.getAdmSysAuthInfo(admSysAuthVO);
	    			if(admSysAuthVO != null && !CommUtil.isEmpty(admSysAuthVO.getUrlPtn())){
	    				System.out.println(request.getServletPath());
	    				String[] locations = StringUtils.tokenizeToStringArray(admSysAuthVO.getUrlPtn(), ",; \t\n");
	    				boolean result = false;
	    				for (int i = 0; i < locations.length; ++i){
	    					System.out.println(locations[i]);
	    					if(pathMatcher.match(locations[i], request.getServletPath()) ){
	    						result = true;
	    						break;
	    					}
	    				}
	    				if(!result) throw new PortalxpertException("Insufficient auth");
	    	            	//return false;
	    			}
        		}
        	}
        }
        return true;
    }
    
	private boolean checkIgnoreUrlPattern(String requestUri) {
        for(String urlPattern: exceptPattern) {
            if(pathMatcher.match(urlPattern, requestUri) ){
                return true;
            }
        }
        return false;
	}
    
	public static void main(String[] args) throws Exception{
		PathMatcher pathMatcher = new AntPathMatcher();
		System.out.println(pathMatcher.match("/*.do", "/main/mainFrame.do"));
	}
}
