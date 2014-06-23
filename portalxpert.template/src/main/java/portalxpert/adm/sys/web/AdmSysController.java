package portalxpert.adm.sys.web;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.rte.fdl.property.EgovPropertyService;


@Controller
@RequestMapping(value="adm/sys")
public class AdmSysController {
	
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
   
    private final static Logger logger = LoggerFactory.getLogger(AdmSysController.class);
   
    /*
	 * 관리자 첫 빈페이지
     * @param ModelMap
     * @return String
     * @exception Exception
     */
    @RequestMapping(value="/admFrame")
	public String getAdmSysBlank(
								ModelMap modelMap,
								@RequestParam(value="url",required = false) String url
								)
								throws Exception {
	  
    	modelMap.put("url", url);	
    	
  	   return ".adm/adm/admFrame";
	}
  	
}
