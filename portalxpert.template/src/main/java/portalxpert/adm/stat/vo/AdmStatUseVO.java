/**
 * 
 */
package portalxpert.adm.stat.vo;

/**
 * @author yoDJ
 *
 */
public class AdmStatUseVO {
	private static final long serialVersionUID = 1L;
	
	private String linkNm = "";			/*	링크(명)	*/
	private String linkAddress = "";	/*	링크주소	*/
	private int linkCnt = 0;			/*	조회수	*/
	
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public int getLinkCnt() {
		return linkCnt;
	}
	public void setLinkCnt(int linkCnt) {
		this.linkCnt = linkCnt;
	}	
}
