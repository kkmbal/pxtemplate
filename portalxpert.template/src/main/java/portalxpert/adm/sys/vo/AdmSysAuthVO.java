package portalxpert.adm.sys.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmSysAuthVO extends SearchConditionVO {
	private String authCd;
	private String authNm;
	private String authDesc;
	private String delYn;
	private String seq;
	private String regrId;
	private String regrName;
	private String updrId;
	private String updrName;
	private String urlPtn;
	private String regDttm;
	private String updDttm;
	
	
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public String getUrlPtn() {
		return urlPtn;
	}
	public void setUrlPtn(String urlPtn) {
		this.urlPtn = urlPtn;
	}
	public String getAuthCd() {
		return authCd;
	}
	public void setAuthCd(String authCd) {
		this.authCd = authCd;
	}
	public String getAuthNm() {
		return authNm;
	}
	public void setAuthNm(String authNm) {
		this.authNm = authNm;
	}
	public String getAuthDesc() {
		return authDesc;
	}
	public void setAuthDesc(String authDesc) {
		this.authDesc = authDesc;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
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
	
	
}
