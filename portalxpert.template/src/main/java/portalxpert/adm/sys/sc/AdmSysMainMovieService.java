package portalxpert.adm.sys.sc;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.transaction.annotation.Transactional;

import portalxpert.adm.sys.vo.AdmSysBbsNotiApndFileVO;


public interface AdmSysMainMovieService {
 
	/**
	 * 동영상관리 목록
	 * @param AdmSysBbsNotiApndFileVO
	 * @return List
	 * @exception Exception
	 */
    public List<AdmSysBbsNotiApndFileVO> getAdmSysMainMovieList(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception;
    
    /**
	 * 동영상관리 목록 Count
	 * @param AdmSysBbsNotiApndFileVO
	 * @return int
	 * @exception Exception
	 */
    public int getAdmSysMainMovieCnt(AdmSysBbsNotiApndFileVO admSysBbsNotiApndFileVO) throws Exception;
    
    /**
	 * 동영상관리 수정(동영상 추천/추천해지)
	 * @param AdmSysBbsNotiApndFileVO
	 * @return
	 * @exception Exception
	 */
    @Transactional
    public void updateAdmSysMainMovie(HttpServletRequest request, HttpSession session) throws Exception;
	
}
