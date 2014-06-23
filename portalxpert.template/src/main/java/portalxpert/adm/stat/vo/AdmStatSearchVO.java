/**
 * 
 */
package portalxpert.adm.stat.vo;

import portalxpert.common.vo.SearchConditionVO;

/**
 * @author yoDJ
 *
 */
public class AdmStatSearchVO extends SearchConditionVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String brdId = "";		/**	게시판번호	*/
	private String[] brdIds = null;
	private String brdNm = "";		/**	게시판이름	*/
	private String sFromDt = "";	/**	검색시작일	*/
	private String sToDt = "";		/**	검색종료일	*/
	private String orgCd = "";		/**	조직번호	*/
	private String inDelYn = "";	/**	삭제불포함	*/
	private String ownrId = "";		/**	유저아이디	*/
	private String ownrName = "";	/**	유저명	*/
	private String deptCode = "";	/**	부서번호	*/
	
	private String stat;
	private String searchDttm;
	private String orderType;
	private boolean isDesc;
	
	
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public boolean getIsDesc() {
		return isDesc;
	}
	public void setIsDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
	public String getStat() {
		return stat;
	}
	public void setStat(String stat) {
		this.stat = stat;
	}
	public String getSearchDttm() {
		return searchDttm;
	}
	public void setSearchDttm(String searchDttm) {
		this.searchDttm = searchDttm;
	}
	public String getBrdId() {
		return brdId;
	}
	public void setBrdId(String brdId) {
		this.brdId = brdId;
	}	
	public String[] getBrdIds() {
		return brdIds;
	}
	public void setBrdIds(String[] brdIds) {
		this.brdIds = brdIds;
	}
	public String getBrdNm() {
		return brdNm;
	}
	public void setBrdNm(String brdNm) {
		this.brdNm = brdNm;
	}
	public String getsFromDt() {
		return sFromDt;
	}
	public void setsFromDt(String sFromDt) {
		this.sFromDt = sFromDt;
	}
	public String getsToDt() {
		return sToDt;
	}
	public void setsToDt(String sToDt) {
		this.sToDt = sToDt;
	}
	public String getOrgCd() {
		return orgCd;
	}
	public void setOrgCd(String orgCd) {
		this.orgCd = orgCd;
	}
	public String getInDelYn() {
		return inDelYn;
	}
	public void setInDelYn(String inDelYn) {
		this.inDelYn = inDelYn;
	}
	public String getOwnrId() {
		return ownrId;
	}
	public void setOwnrId(String ownrId) {
		this.ownrId = ownrId;
	}
	public String getOwnrName() {
		return ownrName;
	}
	public void setOwnrName(String ownrName) {
		this.ownrName = ownrName;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
}
