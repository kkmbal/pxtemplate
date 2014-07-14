package portalxpert.board.board240.sc.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.board.board230.mapper.Board230Mapper;
import portalxpert.board.board230.vo.BbsTmpNotiInfoVO;
import portalxpert.board.board240.mapper.Board240Mapper;
import portalxpert.board.board240.sc.Board240Service;
import portalxpert.common.config.Constant;
import portalxpert.common.vo.BoardSearchVO;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;



@Service("board240Service")
public class Board240ServiceImpl extends EgovAbstractServiceImpl implements  Board240Service {
	
	/** board240Mapper */
    @Resource(name="board240Mapper")
    private Board240Mapper board240Mapper;
    
    /** board230Mapper */
    @Resource(name="board230Mapper")
    private Board230Mapper board230Mapper;
	
	private final static Logger logger = LoggerFactory.getLogger(Board240ServiceImpl.class); 

	/**
	 * 페이지별 게시물 정보 조회 
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 정보 
	 * @exception Exception
	 */    
    public List<BbsTmpNotiInfoVO> getBbsTmpNotiInfoListForPaging(BoardSearchVO vo) throws Exception {
    	try{
        	return board240Mapper.getBbsTmpNotiInfoListForPaging(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /**
	 * 사용자 게시판 리스트 총 갯수
	 * @param searchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception
	 */
    public int getBbsTmpNotiInfoListTotCnt(BoardSearchVO vo)throws Exception  {
    	try{
    		return board240Mapper.getBbsTmpNotiInfoListTotCnt(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /*
     * 임시 게시물 삭제
     * */
    public int deleteBbsTmpNotiInfo(String json, HttpSession session) throws Exception {
    	
    	
        
		try {
			
			UserInfoVO info = (UserInfoVO)session.getAttribute("pxLoginInfo");
			
			
			JSONObject jsonObject = JSONObject.fromObject(json);

			/*vo.setTmpNotiSeq(bbsNotiObject.getInt("tmpNotiSeq")) ;
			vo.setUserId(info.getId());*/
			
			JSONArray jsonArr = (JSONArray)jsonObject.get("deleteTmpNotiList");  			
			for(int i=0; i < jsonArr.size(); i++)
			{
				JSONObject obj = (JSONObject)jsonArr.get(i);
				BbsTmpNotiInfoVO vo = new BbsTmpNotiInfoVO();
				vo.setTmpNotiSeq(obj.getInt("tmpNotiSeq"));
				vo.setUserId(info.getId());

				board230Mapper.deleteBbsTmpNotiInfo(vo);
				board230Mapper.deleteBbsTmpNotiApndFile(vo);
				board230Mapper.deleteBbsTmpNotiSurveyExmpl(vo);
				board230Mapper.deleteBbsTmpNotiSurvey(vo);
				board230Mapper.deleteBbsTmpNotiUserMap(vo);

			}			
			
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
		
		return 0;
    }

    
}
