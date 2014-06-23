package portalxpert.adm.gen.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmGenCodeManageVO extends SearchConditionVO {
	private static final long serialVersionUID = 1L;
	
	private String cd;               
	private String cdNm;          
	private String remark;
	private String cdSpec;
	private String insDt;
	private String insMan;
	private String updDt;
	private String updMan;
	
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCdSpec() {
		return cdSpec;
	}
	public void setCdSpec(String cdSpec) {
		this.cdSpec = cdSpec;
	}
	public String getInsDt() {
		return insDt;
	}
	public void setInsDt(String insDt) {
		this.insDt = insDt;
	}
	public String getInsMan() {
		return insMan;
	}
	public void setInsMan(String insMan) {
		this.insMan = insMan;
	}
	public String getUpdDt() {
		return updDt;
	}
	public void setUpdDt(String updDt) {
		this.updDt = updDt;
	}
	public String getUpdMan() {
		return updMan;
	}
	public void setUpdMan(String updMan) {
		this.updMan = updMan;
	}
	
	
}
