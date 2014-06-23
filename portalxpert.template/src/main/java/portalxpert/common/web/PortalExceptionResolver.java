package portalxpert.common.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

public class PortalExceptionResolver extends SimpleMappingExceptionResolver {
	protected Log log = LogFactory.getLog("errorLog");
	
	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex){
		log.error(ex, ex);

		String viewName = super.determineViewName(ex, request);
		return super.getModelAndView(viewName, ex, request);
	}
}
