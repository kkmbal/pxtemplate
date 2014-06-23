package portalxpert.organization.vo;

import java.io.Serializable;

public class OrganizationVO implements Serializable {

	private static final long serialVersionUID = -2221884857188319789L;
	private String oucode;

	private String id;

	private String pId;

	private String name;
	
	private String deptCode;
	
	private String orgfullname;
	
	
	
	public String getOrgfullname() {
		return orgfullname;
	}

	public void setOrgfullname(String orgfullname) {
		this.orgfullname = orgfullname;
	}

	public String getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getOucode() {
		return oucode;
	}

	public String getpId() {
		return pId;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setOucode(String oucode) {
		this.oucode = oucode;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
