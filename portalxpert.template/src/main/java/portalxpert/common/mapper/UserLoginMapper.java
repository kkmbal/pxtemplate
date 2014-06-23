package portalxpert.common.mapper;


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
	 
	 /**
		 * Method Desciption : 소통글 읽음 처리
		 * 
		 * @param con
		 * @return
		 * @throws ApplicationException
		 */
	 public int insertTmlnRead(UserInfoVO vo) throws Exception;

}
