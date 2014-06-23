package portalxpert.person.person200.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import portalxpert.board.board100.vo.BbsNotiInfoVO;
import portalxpert.common.utils.JSONUtils;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import portalxpert.organization.vo.OrganizationVO;
import portalxpert.person.person100.vo.PsnUserGrpFrdMapVO;
import portalxpert.person.person100.vo.PsnUserGrpInfoVO;
import portalxpert.person.person200.sc.Person200Service;
import portalxpert.person.person200.vo.MyProjectCommunityVO;
import egovframework.rte.fdl.property.EgovPropertyService;



//page import="netscape.ldap.*"

@Controller
@RequestMapping(value = "person200")
public class Person200Controller {

	/** person200Service */
	@Resource(name = "person200Service")
	private Person200Service person200Service;

	/** EgovPropertyService */
	@Resource(name = "propertiesService")
	protected EgovPropertyService propertiesService;

	@Resource(name="messageSourceAccessor")
	private MessageSourceAccessor messageSource;

	private final static Logger logger = LoggerFactory.getLogger(Person200Controller.class);
	
	
	/**
    * 설정된 공지사항 컨텐츠 리스트
    * @param modelMap
    * @return modelMap
    * @throws Exception
    */
   @RequestMapping(value="/getNotiSetContentsList")                            
    public ModelMap getNotiSetContentsList(
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	
    	try {
    		
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");

    		List<BbsNotiInfoVO> notiContents = person200Service.getNotiSetContentsList((String)info.getId());
  
    		modelMap.put("notiContents", notiContents);
    	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		};
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
		    		
	}
   
   
   /**
    * 나의 프로젝트 동호회 리스트
    * @param modelMap
    * @return modelMap
    * @throws Exception
    */
   @RequestMapping(value="/getMyPjtCouList")                            
    public ModelMap getMyPjtCouList(
    		@RequestParam(value="mGubun",required = false) String mGubun,
    		HttpSession session,
			ModelMap modelMap)
            throws Exception {
    	
    	JSONResult jsonResult = new JSONResult();
    	
    	
    	try {
    		
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
    		
    		List<MyProjectCommunityVO> myPjtCouList = person200Service.getMyPjtCouList((String)info.getId(),mGubun);
    		
    		modelMap.put("myPjtCouList", JSONUtils.objectToJSON(myPjtCouList));
    	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
		    		
	}
   
   
   @RequestMapping(value="/getOrganizationFriend")                            
   public ModelMap getOrganizationFriend(
   		@RequestParam(value="type",required = true) String type,
   		@RequestParam(value="callback" ,required = true) String callback,
   		@RequestParam(value="userName" ,required = false) String userName,
   		HttpSession session,
		ModelMap modelMap)
        throws Exception {
	   
	   JSONResult jsonResult = new JSONResult();
   	
   		try {
   	
			   	UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			   	
			   	OrganizationVO vo = new OrganizationVO();
			   	
			   	vo.setId(info.getId());
			   	
			   	if(userName != null && !userName.equals("")) vo.setName(userName);
			   	
			   	
			   	List<OrganizationVO> list = person200Service.getOrgFriendList(vo);
			   	
			   	modelMap.put("orgFriendList", JSONUtils.objectToJSON(list).toString());
			   	
			   	modelMap.put("type", type);
			   	modelMap.put("callback", callback);
	   	 } catch (Exception e) {
	   		jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
				jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	   	
	   	
	    return modelMap;
   }
   
   

   
   @RequestMapping(value="/friendPageOpen")                            
   public String friendPageOpen(
   		HttpSession session,
		ModelMap modelMap)
         throws Exception {
   
       return ".self/person/person200CernStaffMag";
   }
   
   
   /**
    * 나의 관심직원 등록
    * @param modelMap
    * @return modelMap
    * @throws Exception
    * @author crossent
    */

   @RequestMapping(value = "/insertMyMemberList", method = RequestMethod.POST)
   public ModelMap insertMyMemberList(
   		@RequestParam(value="data" ,required = true) String data,
			ModelMap 		modelMap,
			HttpSession session
			
   ) throws Exception {
   	
   		JSONResult jsonResult = new JSONResult();

		try{	
			
			logger.debug("insertMyMemberList");
			
			person200Service.createMyMemberList(data, session);
			
			jsonResult.setSuccess(true);
			jsonResult.setMessage(messageSource.getMessage("commom.ok"));
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
   
   
   
   /**
    * 나의 그룹 등록
    * @param modelMap
    * @return modelMap
    * @throws Exception
    * @author crossent
    */

   @RequestMapping(value = "/insertMyGroup", method = RequestMethod.POST)
   public ModelMap insertMyGroup(
   			@RequestParam(value="grpName" ,required = true) String grpName,
			ModelMap 		modelMap,
			HttpSession session
			
   ) throws Exception {
   	
   		JSONResult jsonResult = new JSONResult();

		try{	
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			PsnUserGrpInfoVO vo = new PsnUserGrpInfoVO();
			
			vo.setUserId(info.getId());
			
			vo.setUpdrName(info.getName());
			
			vo.setRegrName(info.getName());
			
			vo.setGrpName(grpName);
			
			logger.debug("grpName : " + grpName);
			person200Service.insertMyGroup(vo);
			
		}catch(Exception e){
			jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		
		modelMap.put("jsonResult", jsonResult);
		return modelMap;
	}
   
   /**
	 * 나의 관심직원 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserGrpInfo")                            
	 public ModelMap getPsnUserGrpInfo(
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	
	 	
	 	try {
	 		
	 		PsnUserGrpInfoVO vo = new PsnUserGrpInfoVO();
	 		
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	 		
	 		vo.setUserId(info.getId());
	 		
	 		List<PsnUserGrpInfoVO> psnUserGrpInfo = person200Service.getPsnUserGrpInfo(vo);
	 	
	 		modelMap.put("psnUserGrpInfo", psnUserGrpInfo);
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
			    		
	}   
	
	
	/**
	 * 나의 그룹별 멤버 조회
	 * @param modelMap
	 * @return modelMap
	 * @throws Exception
	 * @author crossent
	 */
	@RequestMapping(value="/getPsnUserGrpFriendMap")                            
	 public ModelMap getPsnUserGrpFriendMap(
				@RequestParam(value="grpSeq" ,required = true) String grpSeq,
	 		HttpSession session,
				ModelMap modelMap)
	         throws Exception {
	 	
	 	JSONResult jsonResult = new JSONResult();
	 	try {
	 		
	 		PsnUserGrpFrdMapVO vo = new PsnUserGrpFrdMapVO();
	 		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
	
	 		vo.setUserId(info.getId());
	 		
	 		vo.setGrpSeq(Integer.parseInt(grpSeq));
	 		
	 		
	 		List<PsnUserGrpFrdMapVO> psnUserGrpFrdMap= person200Service.getPsnUserGrpFriendMap(vo);
	 										
	 		modelMap.put("psnUserGrpFrdMap", JSONUtils.objectToJSON(psnUserGrpFrdMap).toString());
	 	
	    } catch (Exception e) {
	    	jsonResult.setSuccess(false);
			jsonResult.setMessage(messageSource.getMessage("common.error")); 
 			jsonResult.setErrMessage(e.getMessage());
		}
		modelMap.put("jsonResult", jsonResult);
	    
		return modelMap;
	}
	
	
	
	/**
	    * 나의 그룹 등록
	    * @param modelMap
	    * @return modelMap
	    * @throws Exception
	    * @author crossent
	    */

	   @RequestMapping(value = "/deletePsnUserGrpFriendMap", method = RequestMethod.POST)
	   public ModelMap deletePsnUserGrpFriendMap(
	   			@RequestParam(value="grpSeq" ,required = true) String grpSeq,
				ModelMap 		modelMap,
				HttpSession session
				
	   ) throws Exception {
	   	
	   		JSONResult jsonResult = new JSONResult();

			try{	
				
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
				PsnUserGrpFrdMapVO vo = new PsnUserGrpFrdMapVO();
				
				vo.setUserId(info.getId());
				
		    	vo.setGrpSeq(Integer.parseInt(grpSeq));
				person200Service.deletePsnUserGrpFriendMap(vo);
				person200Service.deletePsnUserGrpInfo(vo);
				
				person200Service.updatePsnUserFriendInfo(session);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage("그룹이 삭제되었습니다.");
				
				
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
	   
	   
	   /**
	    * 나의 그룹 등록
	    * @param modelMap
	    * @return modelMap
	    * @throws Exception
	    * @author crossent
	    */

	   @RequestMapping(value = "/updatePsnUserGrpInfo", method = RequestMethod.POST)
	   public ModelMap updatePsnUserGrpInfo(
	   			@RequestParam(value="grpSeq" ,required = true) String grpSeq,
	   			@RequestParam(value="grpName" ,required = true) String grpName,
				ModelMap 		modelMap,
				HttpSession session
				
	   ) throws Exception {
	   	
	   		JSONResult jsonResult = new JSONResult();

			try{	
				
				UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
				
				PsnUserGrpInfoVO vo = new PsnUserGrpInfoVO();
				
				vo.setUserId(info.getId());
		    	vo.setGrpSeq(Integer.parseInt(grpSeq));
		    	vo.setGrpName(grpName);
	
				person200Service.updatePsnUserGrpInfo(vo);
				
				jsonResult.setSuccess(true);
				jsonResult.setMessage("그룹명이 수정되었습니다.");
				
				
			}catch(Exception e){
				jsonResult.setSuccess(false);
				jsonResult.setMessage(messageSource.getMessage("common.error")); 
	 			jsonResult.setErrMessage(e.getMessage());
			}
			
			modelMap.put("jsonResult", jsonResult);
			return modelMap;
		}
	
}
