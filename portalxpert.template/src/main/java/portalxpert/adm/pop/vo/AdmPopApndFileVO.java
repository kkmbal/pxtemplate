package portalxpert.adm.pop.vo;

public class AdmPopApndFileVO {
	private String popId; //팝업_아이디
	private int apndFileSeq; //첨부_파일_일련번호
	private int apndFileSz; //첨부_파일_사이즈	
	private String apndFileOrgn; //첨부_파일_원본
	private String apndFileName; //첨부_파일_명
	private String apndFilePath; //첨부_파일_경로
	private String delYn; //삭제_여부
	private String regrId; //등록자_아이디
	private String regDttm; //등록_일시
	private String updrId; //수정자_아이디
	private String updDttm; //수정_일시
	
	public String getPopId() {
		return popId;
	}
	public void setPopId(String popId) {
		this.popId = popId;
	}
	public int getApndFileSeq() {
		return apndFileSeq;
	}
	public void setApndFileSeq(int apndFileSeq) {
		this.apndFileSeq = apndFileSeq;
	}
	public int getApndFileSz() {
		return apndFileSz;
	}
	public void setApndFileSz(int apndFileSz) {
		this.apndFileSz = apndFileSz;
	}
	public String getApndFileOrgn() {
		return apndFileOrgn;
	}
	public void setApndFileOrgn(String apndFileOrgn) {
		this.apndFileOrgn = apndFileOrgn;
	}
	public String getApndFileName() {
		return apndFileName;
	}
	public void setApndFileName(String apndFileName) {
		this.apndFileName = apndFileName;
	}
	public String getApndFilePath() {
		return apndFilePath;
	}
	public void setApndFilePath(String apndFilePath) {
		this.apndFilePath = apndFilePath;
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
	
	
}
