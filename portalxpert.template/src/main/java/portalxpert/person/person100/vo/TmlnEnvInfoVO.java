package portalxpert.person.person100.vo;

import portalxpert.common.vo.SearchConditionVO;

public class TmlnEnvInfoVO extends SearchConditionVO{

	private Long boardSlctMax; /*게시판_선택_최대*/
	private Long anmtSlctMax; /*공지_선택_최대*/
	private Long tagMax; /*태그_최대*/
	private String delYn; /*삭제_여부*/
	private String regrId; /*등록자_아이디*/
	private String regrName; /*등록자_명*/
	private String regDttm; /*등록_일시*/
	private String updrId; /*수정자_아이디*/
	private String updrName; /*수정자_명*/
	private String updDttm; /*수정_일시*/
	
	public Long getBoardSlctMax() {
		return boardSlctMax;
	}
	public void setBoardSlctMax(Long boardSlctMax) {
		this.boardSlctMax = boardSlctMax;
	}
	public Long getAnmtSlctMax() {
		return anmtSlctMax;
	}
	public void setAnmtSlctMax(Long anmtSlctMax) {
		this.anmtSlctMax = anmtSlctMax;
	}
	public Long getTagMax() {
		return tagMax;
	}
	public void setTagMax(Long tagMax) {
		this.tagMax = tagMax;
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
