package portalxpert.adm.sys.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmSysTagCloudInfoVO extends SearchConditionVO 
{
	private String tagDiv; /*태그_구분*/
	private String regDiv; /*등록_구분*/
	private Long tagSeq; /*태그_일련번호*/
	private String linkCateCode; /*링크_카테고리_코드*/
	private String linkCode; /*링크_코드*/
	private String linkName; /*링크_명*/
	private Long visitCnt; /*방문_횟수*/
	private String linkCateName; /*링크_카테고리_명*/
	private String linkUrl; /*링크_URL*/
	private String tagFont; /*태그_폰트*/
	private String boldYn; /*강조_여부*/
	private Long fontSize; /*폰트_크기*/
	private String fontColor; /*폰트_컬러*/
	private String bkgdColor; /*배경_컬러*/
	private String delYn; /*삭제_여부*/
	private String regrId; /*등록자_아이디*/
	private String regrName; /*등록자_명*/
	private String regDttm; /*등록_일시*/
	private String updrId; /*수정자_아이디*/
	private String updrName; /*수정자_명*/
	private String updDttm; /*수정_일시*/
	
	private String linkCatId; /*링크카테고리아이디*/
	
	public String getLinkCatId() {
		return linkCatId;
	}
	public void setLinkCatId(String linkCatId) {
		this.linkCatId = linkCatId;
	}
	public String getTagDiv() {
		return tagDiv;
	}
	public void setTagDiv(String tagDiv) {
		this.tagDiv = tagDiv;
	}
	public String getRegDiv() {
		return regDiv;
	}
	public void setRegDiv(String regDiv) {
		this.regDiv = regDiv;
	}
	public Long getTagSeq() {
		return tagSeq;
	}
	public void setTagSeq(Long tagSeq) {
		this.tagSeq = tagSeq;
	}
	public String getLinkCateCode() {
		return linkCateCode;
	}
	public void setLinkCateCode(String linkCateCode) {
		this.linkCateCode = linkCateCode;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public Long getVisitCnt() {
		return visitCnt;
	}
	public void setVisitCnt(Long visitCnt) {
		this.visitCnt = visitCnt;
	}
	public String getLinkCateName() {
		return linkCateName;
	}
	public void setLinkCateName(String linkCateName) {
		this.linkCateName = linkCateName;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getTagFont() {
		return tagFont;
	}
	public void setTagFont(String tagFont) {
		this.tagFont = tagFont;
	}
	public String getBoldYn() {
		return boldYn;
	}
	public void setBoldYn(String boldYn) {
		this.boldYn = boldYn;
	}
	public Long getFontSize() {
		return fontSize;
	}
	public void setFontSize(Long fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getBkgdColor() {
		return bkgdColor;
	}
	public void setBkgdColor(String bkgdColor) {
		this.bkgdColor = bkgdColor;
	}
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
	public String getUpdrName() {
		return updrName;
	}
	public void setUpdrName(String updrName) {
		this.updrName = updrName;
	}
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	
}
