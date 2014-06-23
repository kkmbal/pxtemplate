package portalxpert.adm.gen.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmGenLinkCategoryVO extends SearchConditionVO {
	
	private static final long serialVersionUID = 1L;
	
	private String linkCatId; /*링크분류ID*/
	private String linkCatNm; /*링크분류명*/
	private Long linkCatOrder; /*링크분류 순서*/
	private String insDt; /*입력일시*/
	private String insMan; /*입력자*/
	private String updDt; /*수정일시*/
	private String updMan; /*수정자*/
	private String endDate; /**/
	
	private Long linkCatCount; /*링크분류 Count (수정시사용)*/
	private Long linkCatOrderNew; /*링크분류 순서 New (수정시사용)*/
	
	public Long getLinkCatCount() {
		return linkCatCount;
	}
	public void setLinkCatCount(Long linkCatCount) {
		this.linkCatCount = linkCatCount;
	}
	public Long getLinkCatOrderNew() {
		return linkCatOrderNew;
	}
	public void setLinkCatOrderNew(Long linkCatOrderNew) {
		this.linkCatOrderNew = linkCatOrderNew;
	}
	public String getLinkCatId() {
		return linkCatId;
	}
	public void setLinkCatId(String linkCatId) {
		this.linkCatId = linkCatId;
	}
	public String getLinkCatNm() {
		return linkCatNm;
	}
	public void setLinkCatNm(String linkCatNm) {
		this.linkCatNm = linkCatNm;
	}
	public Long getLinkCatOrder() {
		return linkCatOrder;
	}
	public void setLinkCatOrder(Long linkCatOrder) {
		this.linkCatOrder = linkCatOrder;
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
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
