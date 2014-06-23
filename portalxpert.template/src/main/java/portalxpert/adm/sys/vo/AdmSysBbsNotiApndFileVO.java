package portalxpert.adm.sys.vo;

import portalxpert.adm.board.vo.AdmBoardNotiInfoVO;

public class AdmSysBbsNotiApndFileVO extends AdmBoardNotiInfoVO
{
	//BBS_NOTI_APND_FILE 테이블
	private Long apndFileSeq; /*첨부_파일_일련번호*/
	private String apndFileTp; /*첨부_파일_유형*/
	private Long apndFileSz; /*첨부_파일_사이즈*/
	private String apndFileOrgn; /*첨부_파일_원본*/
	private String apndFileName; /*첨부_파일_명*/
	private String apndFilePath; /*첨부_파일_경로*/
	private String apndFilePrvwPath; /*첨부_파일_미리보기_경로*/
	private String apndFilePrvwName; /*첨부_파일_미리보기_명*/
	private String sourceCodeInput; /*소스_코드_입력*/
	private String adminRcmdYn; /*관리자_추천_여부*/
	private String adminRcmdDttm; /*관리자_추천_일시*/
	private Long readCnt; /*조회_건수*/
	private String delYn; /*삭제_여부*/
	
	public Long getApndFileSeq() {
		return apndFileSeq;
	}
	public void setApndFileSeq(Long apndFileSeq) {
		this.apndFileSeq = apndFileSeq;
	}
	public String getApndFileTp() {
		return apndFileTp;
	}
	public void setApndFileTp(String apndFileTp) {
		this.apndFileTp = apndFileTp;
	}
	public Long getApndFileSz() {
		return apndFileSz;
	}
	public void setApndFileSz(Long apndFileSz) {
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
	public String getApndFilePrvwPath() {
		return apndFilePrvwPath;
	}
	public void setApndFilePrvwPath(String apndFilePrvwPath) {
		this.apndFilePrvwPath = apndFilePrvwPath;
	}
	public String getApndFilePrvwName() {
		return apndFilePrvwName;
	}
	public void setApndFilePrvwName(String apndFilePrvwName) {
		this.apndFilePrvwName = apndFilePrvwName;
	}
	public String getSourceCodeInput() {
		return sourceCodeInput;
	}
	public void setSourceCodeInput(String sourceCodeInput) {
		this.sourceCodeInput = sourceCodeInput;
	}
	public String getAdminRcmdYn() {
		return adminRcmdYn;
	}
	public void setAdminRcmdYn(String adminRcmdYn) {
		this.adminRcmdYn = adminRcmdYn;
	}
	public String getAdminRcmdDttm() {
		return adminRcmdDttm;
	}
	public void setAdminRcmdDttm(String adminRcmdDttm) {
		this.adminRcmdDttm = adminRcmdDttm;
	}
	public Long getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(Long readCnt) {
		this.readCnt = readCnt;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
	
}
