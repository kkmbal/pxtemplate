package portalxpert.common.mapper;


import org.omg.CORBA.portable.ApplicationException;

import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.psl.dataaccess.mapper.Mapper;

@Mapper("userLoginMapper")
public interface UserLoginMapper 
{

	 /**
	 * Method Desciption : 로그인정보 조회
	 * 
	 * @param con
	 * @return
	 * @throws ApplicationException
	 */
	 public UserInfoVO getLoginInfo(String userId) throws Exception;
	 
	 /**
	 * Method Desciption : 인증서 로그인정보 조회
	 * 
	 * @param con
	 * @return
	 * @throws ApplicationException
	 */
	 public UserInfoVO getLoginInfoBySsnId(String ssnId) throws Exception;
	 

	 


}
