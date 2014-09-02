package portalxpert.common.sc;



import java.util.List;

import org.omg.CORBA.portable.ApplicationException;

import portalxpert.adm.sys.vo.AdmSysUserAuthVO;
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
	

    
}
