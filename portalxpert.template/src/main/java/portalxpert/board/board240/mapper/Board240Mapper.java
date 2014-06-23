package portalxpert.board.board240.mapper;

import java.util.List;

import portalxpert.board.board230.vo.BbsTmpNotiInfoVO;
import portalxpert.common.vo.BoardSearchVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("board240Mapper")
public interface Board240Mapper  {

	/**
	 * 임시저장 기본정보 조회
	 * @param BbsTmpNotiInfoVO - 조회할 정보가 담긴 Map
	 * @return 임시저장 정보 
	 * @exception
	 */
    public List<BbsTmpNotiInfoVO> getBbsTmpNotiInfoListForPaging(BoardSearchVO vo)throws Exception  ;

    /**
	 * 임시 게시물 총 개수 조회
	 * @param BoardSearchVO - 조회할 정보가 담긴 VO
	 * @return 게시판 총 갯수
	 * @exception Exception
	 * @auther crossent 
	 */
    public int getBbsTmpNotiInfoListTotCnt(BoardSearchVO vo)throws Exception;
}

