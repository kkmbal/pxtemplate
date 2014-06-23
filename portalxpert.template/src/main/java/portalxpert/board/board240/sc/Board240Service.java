package portalxpert.board.board240.sc;

import java.util.List;

import javax.servlet.http.HttpSession;

import portalxpert.board.board230.vo.BbsTmpNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;



public interface Board240Service {
	
	/**
	 * 게시물 정보 임시저장
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 게시물 임시저정 정보 입력
	 * @exception Exception
	 */
	List<BbsTmpNotiInfoVO> getBbsTmpNotiInfoListForPaging(BoardSearchVO vo) throws Exception;
	
	/**
	 * 임시게시물 총 갯수
	 * @param BbsNotiInfoVO - 조회할 정보가 담긴 VO
	 * @return 임시 게시물총 갯수
	 * @exception Exception
	 */
	 public int getBbsTmpNotiInfoListTotCnt(BoardSearchVO vo)throws Exception ;
	 
	 /**
		 * 임시 게시물 정보 삭제
		 * @param json - 삭제제 할 정보가 담긴 json
		 * @return 임시 게시물 성공건수
		 * @exception Exception
		 */
	 int deleteBbsTmpNotiInfo(String json, HttpSession session) throws Exception;

}
