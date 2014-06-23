package portalxpert.board.board300.vo;

public class PbsUserNotiOpnVO {
	
	private int userNotiSeq; //사용자_게시물_일련번호
	private int userNotiOpnSeq; //의견_일련번호
	private int upOpnSeq; //상위_의견_일련번호
	private String opnConts; //의견_내용
	private String userId; //사용자_아이디
	private String userName; //사용자_명
	private String userNick; //사용자_닉네임
	private String deptCode; //부서_코드
	private String deptName; //부서_명
	private String makeIp; //작성_아이피
	private String adminDel; //관리자_삭제
	private String delYn; //삭제_여부
	private String regrId; //등록자_아이디
	private String regrName; //등록자_명
	private String regDttm; //등록_일시
	private String updrId; //수정자_아이디
	private String updrName; //수정자_명
	private String updDttm; //수정_일시
	private String rank; //rank
	
	
	public int getUserNotiOpnSeq() {
		return userNotiOpnSeq;
	}
	public void setUserNotiOpnSeq(int userNotiOpnSeq) {
		this.userNotiOpnSeq = userNotiOpnSeq;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public int getUserNotiSeq() {
		return userNotiSeq;
	}
	public void setUserNotiSeq(int userNotiSeq) {
		this.userNotiSeq = userNotiSeq;
	}
	
	public int getUpOpnSeq() {
		return upOpnSeq;
	}
	public void setUpOpnSeq(int upOpnSeq) {
		this.upOpnSeq = upOpnSeq;
	}
	public String getOpnConts() {
		return opnConts;
	}
	public void setOpnConts(String opnConts) {
		this.opnConts = opnConts;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getMakeIp() {
		return makeIp;
	}
	public void setMakeIp(String makeIp) {
		this.makeIp = makeIp;
	}
	public String getAdminDel() {
		return adminDel;
	}
	public void setAdminDel(String adminDel) {
		this.adminDel = adminDel;
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
