package portalxpert.common.sc.impl;


import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import portalxpert.common.config.Constant;
import portalxpert.common.mapper.UserLoginMapper;
import portalxpert.common.sc.UserLoginService;
import portalxpert.common.vo.UserInfoVO;
import egovframework.rte.fdl.cmmn.AbstractServiceImpl;

@Service("userLoginService")
public class UserLoginServiceImpl extends AbstractServiceImpl implements UserLoginService
{
	/** UserInfoMapper */
    @Resource(name="userLoginMapper")
    private UserLoginMapper userLoginMapper;
	
    private final static Logger logger = LoggerFactory.getLogger(UserLoginServiceImpl.class);
    

    /** 
	 * 로그인 정보 조회
	 * @param String : userId
	 * @return UserInfoVO 
	 * @exception Exception
	 */
    public UserInfoVO getLoginInfo(String userId) throws Exception 
    {
    	try{
    		return userLoginMapper.getLoginInfo(userId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    /** 
	 * 인증서 로그인 정보 조회
	 * @param String : userId
	 * @return UserInfoVO 
	 * @exception Exception
	 */
    public UserInfoVO getLoginInfoBySsnId(String ssnId) throws Exception 
    {
    	try{
    		return userLoginMapper.getLoginInfoBySsnId(ssnId);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    }
    
    public int insertTmlnRead(UserInfoVO vo) throws Exception
    {
    	try{
    		return userLoginMapper.insertTmlnRead(vo);
		}catch(Exception e){
			throw processException(Constant.E000001.getVal(), new String[]{e.toString(), this.getClass().getSimpleName()}, e);
		}
    	
    }
    
	
	

}
