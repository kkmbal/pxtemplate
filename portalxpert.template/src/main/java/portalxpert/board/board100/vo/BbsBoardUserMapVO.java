package portalxpert.board.board100.vo;

public class BbsBoardUserMapVO {	
	private String boardId;
	private String userDiv;
	private String userId;
	private String mngAuth;
	private String delYn;
	private String regrId;
	private String regrName;
	private String regDttm;
	private String updrId;
	private String updrName;
	private String updDttm;
	private String id;   //조회용으로 쓰임(이름 준수)
	private String name; //조회용으로 쓰임(이름 준수)
	private String boardName;
	private String mail;
	private String intermail;
	
	//20130917 부서명-이윤심 주무관 요구사항으로 추가
	private String ou;
	
	
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getIntermail() {
		return intermail;
	}
	public void setIntermail(String intermail) {
		this.intermail = intermail;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getUserDiv() {
		return userDiv;
	}
	public void setUserDiv(String userDiv) {
		this.userDiv = userDiv;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMngAuth() {
		return mngAuth;
	}
	public void setMngAuth(String mngAuth) {
		this.mngAuth = mngAuth;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
}
