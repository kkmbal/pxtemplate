package portalxpert.board.board240.web;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import portalxpert.board.board240.sc.Board240Service;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.JSONResult;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.property.EgovPropertyService;
import egovframework.rte.ptl.mvc.tags.ui.pagination.PaginationInfo;


@Controller
@RequestMapping("board240")
public class Board240Controller {
    
	private final static Logger logger = LoggerFactory.getLogger(Board240Controller.class); 
   
	/** board240Service */
    @Resource(name = "board240Service")
    private Board240Service board240Service;
    
    /** EgovPropertyService */
    @Resource(name = "propertiesService")
    protected EgovPropertyService propertiesService;
    
    @Resource(name="messageSourceAccessor")
    private MessageSourceAccessor messageSource;
    
    /**
     * 임시 게시판 
     * @param modelMap
     * @return board/tmpBoardList.jsp
     * @throws Exception
     * @auther crossent
     */
    @RequestMapping(value="/getTmpBoardInfoList")
    public String getBoardInfoList(
 			ModelMap modelMap,
			//@RequestParam(value="callback" ,required = true) String callback,
 			@ModelAttribute("boardSearchVO") BoardSearchVO boardSearchVO,
 			BindingResult result,
 			@RequestParam(value="boardId",required = true) String boardId,
 			@RequestParam(value="pageIndex",required = false, defaultValue="1") String pageIndex,
 			@RequestParam(value="pageUnit",required = false, defaultValue="10") String pageUnit,
 			@RequestParam(value="searchCondition",required = false) String searchCondition,
 			@RequestParam(value="searchKeyword",required = false) String searchKeyword,
 			@RequestParam(value="regDttmFrom",required = false) String regDttmFrom,
 			@RequestParam(value="regDttmTo",required = false) String regDttmTo,
 			@RequestParam(value="orderType",required = false, defaultValue="default") String orderType,
 			@RequestParam(value="isDesc",required = false) boolean isDesc,
 			@RequestParam(value="fh",required = false) String fh,
 			HttpSession session
 			)
            throws Exception {
    	
 
    		int errorCount = result.getErrorCount();
    		
    		if (pageUnit == null)
    		{
    			pageUnit = "10";
    		}
    		UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");			
    		/** PropertyService.sample */
			boardSearchVO.setPageUnit(Integer.parseInt(pageUnit));
			boardSearchVO.setPageSize(propertiesService.getInt("pageSize"));
			boardSearchVO.setPageIndex(Integer.parseInt(pageIndex));
	    	
	    	/** pageing setting */
	    	PaginationInfo paginationInfo = new PaginationInfo();
			paginationInfo.setCurrentPageNo(boardSearchVO.getPageIndex());
			paginationInfo.setRecordCountPerPage(boardSearchVO.getPageUnit());
			paginationInfo.setPageSize(boardSearchVO.getPageSize());
			
			boardSearchVO.setFirstIndex(paginationInfo.getFirstRecordIndex());
			boardSearchVO.setLastIndex(paginationInfo.getLastRecordIndex());
			boardSearchVO.setRecordCountPerPage(paginationInfo.getRecordCountPerPage());
			boardSearchVO.setUserId(info.getId());
			
			boardSearchVO.setBoardId(boardId);
			boardSearchVO.setSearchCondition(searchCondition);
			boardSearchVO.setSearchKeyword(searchKeyword);			
			boardSearchVO.setRegDttmFrom(regDttmFrom);
			boardSearchVO.setRegDttmTo(regDttmTo);			
			boardSearchVO.setOrderType(orderType);
			boardSearchVO.setIsDesc(isDesc);
			
			List noti_list = board240Service.getBbsTmpNotiInfoListForPaging(boardSearchVO);
			int totCnt = board240Service.getBbsTmpNotiInfoListTotCnt(boardSearchVO);
			paginationInfo.setTotalRecordCount(totCnt);
			
			modelMap.put("boardSearchVO", boardSearchVO);
			modelMap.put("pageIndex", pageIndex);
			modelMap.put("pageUnit", pageUnit);
			modelMap.put("boardId", boardId);
			
			modelMap.put("notiList", noti_list);
			modelMap.put("regDttmFrom", regDttmFrom);
			modelMap.put("regDttmTo", regDttmTo);			
			modelMap.put("paginationInfo", paginationInfo);
			modelMap.put("isDesc", !isDesc);
			modelMap.put("searchCondition", searchCondition);
			modelMap.put("searchKeyword", searchKeyword);
			//modelMap.put("regDttmFrom", regDttmFrom);
			//modelMap.put("regDttmTo", regDttmTo);
			
			modelMap.put("fh", fh);
			//modelMap.put("btnViewYn", boardBtnViewYn);
			
 
    
        return ".self/board/board240TmpList";
    }
    
    /**
     * 임시 게시물 삭제
     * @param modelMap
     * @return board/board240TmpList.jsp
     * @throws Exception
     */
    @RequestMapping(value = "/deleteTmpNotiInfo")
    @ResponseBody
    public ModelMap deleteTmpNotiInfo(
    		@RequestParam(value="data" ,required = true) String data,
 			ModelMap 		modelMap,
 			HttpSession session
 			
    ) throws Exception {
    	
    		JSONResult jsonResult = new JSONResult();

 		try{	
 			
 			JSONObject jsonObject = JSONObject.fromObject(data);
 			data = jsonObject.toString();
 			board240Service.deleteBbsTmpNotiInfo(data, session);
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

        
}
