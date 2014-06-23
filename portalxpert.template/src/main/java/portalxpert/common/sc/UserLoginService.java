package portalxpert.common.sc;



import portalxpert.common.vo.UserInfoVO;


public interface UserLoginService {

	 /**
	 * 로그인 정보 조회
	 * @param UserInfoVO
	 * @return
	 * @exception Exception
	 */
   public UserInfoVO getLoginInfo(String userId) throws Exception;

	 /**
	 * 인증서 로그인 정보 조회
	 * @param UserInfoVO
	 * @return
	 * @exception Exception
	 */
  public UserInfoVO getLoginInfoBySsnId(String ssnId) throws Exception;
	
    /**
	 * 소통글 읽음 처리
	 * @param UserInfoVO
	 * @return
	 * @exception Exception
	 */
    public int insertTmlnRead(UserInfoVO vo) throws Exception;
    
}
