package portalxpert.board.board100.vo;

public class BbsNotiApndFileVO {
	private String notiId; //게시물_아이디
	private int apndFileSeq; //첨부_파일_일련번호
	private String apndFileTp; //첨부_파일_유형
	private String saveFileId;  //첨부파이 아이디(글쓰기에서 쓰임)
	private int apndFileSz; //첨부_파일_사이즈	
	private String apndFileOrgn; //첨부_파일_원본
	private String apndFileName; //첨부_파일_명
	private String apndFilePath; //첨부_파일_경로
	private String apndFilePrvwPath; //첨부_파일_미리보기_경로
	private String apndFilePrvwName; //첨부_파일_미리보기_명
	private String sourceCodeInput; //소스_코드_입력
	private String adminRcmdYn; //관리자_추천_여부
	private String adminRcmdDttm; //관리자_추천_일시
	private int readCnt; //조회_건수
	private String delYn; //삭제_여부
	private String regrId; //등록자_아이디
	private String regrName; //등록자_명
	private String regDttm; //등록_일시
	private String updrId; //수정자_아이디
	private String updrName; //수정자_명
	private String updDttm; //수정_일시
	private String mvpKey; //동영상 키
	private String userId;
	private String rnum;
	private String sortSeq;
	
	
	public String getSortSeq() {
		return sortSeq;
	}
	public void setSortSeq(String sortSeq) {
		this.sortSeq = sortSeq;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public int getApndFileSeq() {
		return apndFileSeq;
	}
	public void setApndFileSeq(int apndFileSeq) {
		this.apndFileSeq = apndFileSeq;
	}
	public String getApndFileTp() {
		return apndFileTp;
	}
	public void setApndFileTp(String apndFileTp) {
		this.apndFileTp = apndFileTp;
	}
	
	public String getSaveFileId() {
		return saveFileId;
	}
	public void setSaveFileId(String saveFileId) {
		this.saveFileId = saveFileId;
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
	public int getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(int readCnt) {
		this.readCnt = readCnt;
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
	public String getMvpKey() {
		return mvpKey;
	}
	public void setMvpKey(String mvpKey) {
		this.mvpKey = mvpKey;
	}

}
