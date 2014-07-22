package portalxpert.adm.pop.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmPopVO extends SearchConditionVO {

	private String popId; /*팝업 ID*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
	private String popTitle; /*제목*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                  
	private String expoBgnDttm; /*노출시작일*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
	private String expoEndDttm; /*노출종료일*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
	private String parRowPos; /*상위열위치*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	private String rowPos; /*열위치*/                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
	private String delYn; /*삭제여부*/
	private String useYn; /*운영여부*/
	private String expoYn; /*노출여부*/
	private String popKind; /*팝업종류*/
	private String linkUrl; /*링크URL*/
	private String linkKind; /*링크종류*/
	private String popConts; /*팝업내용*/
	
	private String regrId;
	private String regDttm;
	private String updrId;
	private String updDttm;
	
	private String seq; //순번



	public String getPopConts() {
		return popConts;
	}

	public void setPopConts(String popConts) {
		this.popConts = popConts;
	}

	public String getExpoYn() {
		return expoYn;
	}

	public void setExpoYn(String expoYn) {
		this.expoYn = expoYn;
	}

	public String getPopId() {
		return popId;
	}

	public void setPopId(String popId) {
		this.popId = popId;
	}

	public String getPopTitle() {
		return popTitle;
	}

	public void setPopTitle(String popTitle) {
		this.popTitle = popTitle;
	}

	public String getExpoBgnDttm() {
		return expoBgnDttm;
	}

	public void setExpoBgnDttm(String expoBgnDttm) {
		this.expoBgnDttm = expoBgnDttm;
	}

	public String getExpoEndDttm() {
		return expoEndDttm;
	}

	public void setExpoEndDttm(String expoEndDttm) {
		this.expoEndDttm = expoEndDttm;
	}

	public String getParRowPos() {
		return parRowPos;
	}

	public void setParRowPos(String parRowPos) {
		this.parRowPos = parRowPos;
	}

	public String getRowPos() {
		return rowPos;
	}

	public void setRowPos(String rowPos) {
		this.rowPos = rowPos;
	}

	public String getDelYn() {
		return delYn;
	}

	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}

	public String getUseYn() {
		return useYn;
	}

	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}

	public String getPopKind() {
		return popKind;
	}

	public void setPopKind(String popKind) {
		this.popKind = popKind;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLinkKind() {
		return linkKind;
	}

	public void setLinkKind(String linkKind) {
		this.linkKind = linkKind;
	}

	public String getRegrId() {
		return regrId;
	}

	public void setRegrId(String regrId) {
		this.regrId = regrId;
	}

	public String getRegDttm() {
		return regDttm;
	}

	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}

	public String getUpdrId() {
		return updrId;
	}

	public void setUpdrId(String updrId) {
		this.updrId = updrId;
	}

	public String getUpdDttm() {
		return updDttm;
	}

	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	
}
