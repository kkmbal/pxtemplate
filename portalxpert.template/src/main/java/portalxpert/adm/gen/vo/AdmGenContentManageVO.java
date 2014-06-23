package portalxpert.adm.gen.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmGenContentManageVO extends SearchConditionVO {
	
	private static final long serialVersionUID = -7510187406953061619L;
	
	private String userId		 = "";
	private String userName		 = "";
	private String cntsId		 = "";
	private String cntsName		 = "";
	private String cntsLink		 = "";
	private String adminLink     = "";
	private String cntsTp        = "";
	private String iconName      = "";
	private String relaKey       = "";
	private String msgRefreshTm  = "";
	private String useYn         = "";
	private String delYn         = "";
	private String sysErrYn      = "";
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCntsId() {
		return cntsId;
	}
	public void setCntsId(String cntsId) {
		this.cntsId = cntsId;
	}
	public String getCntsName() {
		return cntsName;
	}
	public void setCntsName(String cntsName) {
		this.cntsName = cntsName;
	}
	public String getCntsLink() {
		return cntsLink;
	}
	public void setCntsLink(String cntsLink) {
		this.cntsLink = cntsLink;
	}
	public String getAdminLink() {
		return adminLink;
	}
	public void setAdminLink(String adminLink) {
		this.adminLink = adminLink;
	}
	public String getCntsTp() {
		return cntsTp;
	}
	public void setCntsTp(String cntsTp) {
		this.cntsTp = cntsTp;
	}
	public String getIconName() {
		return iconName;
	}
	public void setIconName(String iconName) {
		this.iconName = iconName;
	}
	public String getRelaKey() {
		return relaKey;
	}
	public void setRelaKey(String relaKey) {
		this.relaKey = relaKey;
	}
	public String getMsgRefreshTm() {
		return msgRefreshTm;
	}
	public void setMsgRefreshTm(String msgRefreshTm) {
		this.msgRefreshTm = msgRefreshTm;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getSysErrYn() {
		return sysErrYn;
	}
	public void setSysErrYn(String sysErrYn) {
		this.sysErrYn = sysErrYn;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}	
	
}
