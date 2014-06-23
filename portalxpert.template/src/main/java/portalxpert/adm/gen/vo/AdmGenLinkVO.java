package portalxpert.adm.gen.vo;

/**
 * @author yoDJ
 *
 */
public class AdmGenLinkVO extends AdmGenLinkCategoryVO {
	
	private static final long serialVersionUID = 1L;
	
	private String linkCd; /*링크 코드*/
	private Long linkOrder; /*링크 순서*/
	private String linkAddress; /*링크 주소*/
	private String linkNm; /*링크명*/
	private String popupUseGb; /*팝업사용구분*/
	private String insDt; /*입력일시*/
	private String insMan; /*입력자*/
	private String updDt; /*수정일시*/
	private String updMan; /*수정일자*/
	private String mainDspGb; /*메인화면표시여부*/
	private String endDate; /**/
	private String linkSnm;	/*약어명*/
	
	private Long linkCount; /*링크 Count (수정시사용)*/
	private Long linkOrderNew; /*링크 순서 NEW (수정시사용)*/
	
	public Long getLinkOrderNew() {
		return linkOrderNew;
	}
	public void setLinkOrderNew(Long linkOrderNew) {
		this.linkOrderNew = linkOrderNew;
	}
	public Long getLinkCount() {
		return linkCount;
	}
	public void setLinkCount(Long linkCount) {
		this.linkCount = linkCount;
	}
	public String getLinkCd() {
		return linkCd;
	}
	public void setLinkCd(String linkCd) {
		this.linkCd = linkCd;
	}
	public Long getLinkOrder() {
		return linkOrder;
	}
	public void setLinkOrder(Long linkOrder) {
		this.linkOrder = linkOrder;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getPopupUseGb() {
		return popupUseGb;
	}
	public void setPopupUseGb(String popupUseGb) {
		this.popupUseGb = popupUseGb;
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
	public String getMainDspGb() {
		return mainDspGb;
	}
	public void setMainDspGb(String mainDspGb) {
		this.mainDspGb = mainDspGb;
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
	public String getLinkSnm() {
		return linkSnm;
	}
	public void setLinkSnm(String linkSnm) {
		this.linkSnm = linkSnm;
	}
	
}
