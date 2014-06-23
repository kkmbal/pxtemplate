package portalxpert.board.board230.sc.impl;

import javax.annotation.Resource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;

import portalxpert.board.board230.mapper.BBSMovieMapper;
import portalxpert.board.board230.sc.BBSMovieService;
import portalxpert.board.board230.vo.BBSMovieVO;
import portalxpert.common.config.Constant;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("bbsMovieService")
public class BBSMovieServiceImpl extends AbstractServiceImpl implements BBSMovieService{

	/** BBSMovieMapper */
    @Resource(name="bbsMovieMapper")
    private BBSMovieMapper bbsMovieMapper;

    //save point
    @Resource(name = "txManager")
	private DataSourceTransactionManager txManager;
	private TransactionStatus transactionStatus;
	
	/**
     * 동영상  목록 화면
     @param searchVO - 조회할 정보가 담긴 SearchConditionVO
     * @return ArrayList
     * @exception Exception
     
	public ArrayList getBBSMovieList(SearchConditionVO searchVO) {
		log.debug("getBBSMovieList : "+searchVO.toString());
		ArrayList movieList = null;
		try {
			movieList = bbsMovieMapper.getBBSMovieList(searchVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return movieList;	
	}
	*/
	
	/**
     * 동영상  목록 Total Count
     @param searchVO - 조회할 정보가 담긴 SearchConditionVO
     * @return int
     * @exception Exception
    
	public int getBBSMovieListTotCnt(SearchConditionVO searchVO) {
		log.debug("getBBSMovieListTotCnt : "+searchVO.toString());
		int std = 0;
		try {
			std = bbsMovieMapper.getBBSMovieListTotCnt(searchVO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return std;	
	}
	 */
	
	/**
     * 동영상 상세화면 조회
     * @param bbsMovieVO - 등록할 정보가 담긴 vo
     * @return BBSMovieVO
     * @exception Exception
    
	public BBSMovieVO getBBSMovieSelect(BBSMovieVO bbsMovieVO) {
		log.debug("getBBSMovieSelect : "+bbsMovieVO.toString());
		BBSMovieVO resultVO = null;
		try {
			resultVO = bbsMovieMapper.getBBSMovieSelect(bbsMovieVO);
			if (resultVO == null) {
	            throw processException("info.nodata.msg");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return resultVO;
	}
	 */
	
	/**
	 * 동영상서버에서 보내는 메타 데이터를 DB에 저장한다
     * @param bbsMovieVO - 등록할 정보가 담긴 bbsMovieVO
	 * @return 등록 결과
	 * @exception Exception
	 */
    public int insertBBSMovie(BBSMovieVO bbsMovieVO) throws Exception {
    	try{
    		return bbsMovieMapper.insertBBSMovie(bbsMovieVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public int updateTnMspMvp(BBSMovieVO bbsMovieVO) throws Exception{
    	try{
    		return bbsMovieMapper.updateTnMspMvp(bbsMovieVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public int getTnMspMvpCnt(BBSMovieVO bbsMovieVO) throws Exception{
    	try{
    		return bbsMovieMapper.getTnMspMvpCnt(bbsMovieVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
	/**
	 * 동영상 플레이 정보
	 * @param bbsMovieVO
	 * @return bbsMovieVO
	 */
    public BBSMovieVO getMoviePlayInfo(BBSMovieVO bbsMovieVO) throws Exception{
    	try{
    		return bbsMovieMapper.getMoviePlayInfo(bbsMovieVO);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
}
