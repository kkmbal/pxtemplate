package portalxpert.adm.sys.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmSysMenuAuthVO  extends SearchConditionVO {
	private String authCd;
	private String menuConts;
	private String delYn;
	private String regrId;
	private String regrName;
	private String updrId;
	private String updrName;
	
	
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getRegrId() {
		return regrId;
	}
	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}
	public String getRegrName() {
		return regrName;
	}
	public void setRegrName(String regrName) {
		this.regrName = regrName;
	}
	public String getUpdrId() {
		return updrId;
	}
	public void setUpdrId(String updrId) {
		this.updrId = updrId;
	}
	public String getUpdrName() {
		return updrName;
	}
	public void setUpdrName(String updrName) {
		this.updrName = updrName;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getMenuConts() {
		return menuConts;
	}
	public void setMenuConts(String menuConts) {
		this.menuConts = menuConts;
	}
	
	
	
}
