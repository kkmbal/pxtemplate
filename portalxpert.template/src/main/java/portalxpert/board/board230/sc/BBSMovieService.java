package portalxpert.board.board230.sc;

import portalxpert.board.board230.vo.BBSMovieVO;

public interface BBSMovieService {
	
	/**
     * 동영상  목록 화면
     @param searchVO - 조회할 정보가 담긴 SearchConditionVO
     * @return ArrayList
     * @exception Exception
     
	public ArrayList getBBSMovieList(SearchConditionVO searchVO);
	*/
	/**
     * 동영상  목록 Total Count
     @param searchVO - 조회할 정보가 담긴 SearchConditionVO
     * @return int
     * @exception Exception
     
	public int getBBSMovieListTotCnt(SearchConditionVO searchVO);
	*/
	
	/**
     * 동영상  상세화면 조회
     * @param bbsMovieVO - 등록할 정보가 담긴 vo
     * @return BBSMovieVO
     * @exception Exception
     
	public BBSMovieVO getBBSMovieSelect(BBSMovieVO bbsMovieVO);
	*/
	
	/**
     * 동영상서버에서 보내는 메타 데이터를 DB에 저장한다
     * @param bbsMovieVO - 등록할 정보가 담긴 vo
     * @return int
     * @exception Exception
     */
	public int insertBBSMovie(BBSMovieVO bbsMovieVO) throws Exception;
	
	public int updateTnMspMvp(BBSMovieVO bbsMovieVO) throws Exception;
	
	public int getTnMspMvpCnt(BBSMovieVO bbsMovieVO) throws Exception;
	
	/**
	 * 동상상 플레이 정보
	 * @param bbsMovieVO
	 * @return
	 */
	public BBSMovieVO getMoviePlayInfo(BBSMovieVO bbsMovieVO) throws Exception;

}
