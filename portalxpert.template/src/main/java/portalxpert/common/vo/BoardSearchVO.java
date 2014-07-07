package portalxpert.common.vo;


public class BoardSearchVO extends SearchConditionVO{
	
	private String notiId;//게시물_아이디
	private String boardId;//게시판_아이디
	private String userId;
	private String regDttmFrom; //등록_시작일시
	private String regDttmTo; //등록_끝일시
	private String prevNotiId;//이전글아이디
	private String prevNotiTitle;//이전글제목
	private String nextNotiId;//다음글아이디
	private String nextNotiTitle;//다음글제목
	private String notiSeqNo; //글 순번
	private String notiReadmanAsgnYn; //조회자지정여부
	private String userMap; //사용자권한
	private String orderType;
	private String myNotiTitle;
	private String boardKind;
	private String cdlnDeptName;
	private String cdlnDeptFname;
	private String cdlnObjrName;
	private String cdlnEvntCode;
	private String boardAnmtUseYn;
	private String orderSeq;
	private int userNotiSeq;
	private String boardFormSpec;
	private String eamAdminYn;
	private String deptCode;
	private String regDttm;
	private int rowNum;
	private String boardForm;
	private String calYmFrom;
	private String calYmTo;
	
	
	
	public String getBoardForm() {
		return boardForm;
	}
	public void setBoardForm(String boardForm) {
		this.boardForm = boardForm;
	}
	public String getCalYmFrom() {
		return calYmFrom;
	}
	public void setCalYmFrom(String calYmFrom) {
		this.calYmFrom = calYmFrom;
	}
	public String getCalYmTo() {
		return calYmTo;
	}
	public void setCalYmTo(String calYmTo) {
		this.calYmTo = calYmTo;
	}
	public int getRowNum() {
		return rowNum;
	}
	public void setRowNum(int rowNum) {
		this.rowNum = rowNum;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getDeptCode() {
		return deptCode;
	}
	public void setDeptCode(String deptCode) {
		this.deptCode = deptCode;
	}
	public String getEamAdminYn() {
		return eamAdminYn;
	}
	public void setEamAdminYn(String eamAdminYn) {
		this.eamAdminYn = eamAdminYn;
	}
	public String getBoardFormSpec() {
		return boardFormSpec;
	}
	public void setBoardFormSpec(String boardFormSpec) {
		this.boardFormSpec = boardFormSpec;
	}
	public int getUserNotiSeq() {
		return userNotiSeq;
	}
	public void setUserNotiSeq(int userNotiSeq) {
		this.userNotiSeq = userNotiSeq;
	}
	public String getBoardAnmtUseYn() {
		return boardAnmtUseYn;
	}
	public void setBoardAnmtUseYn(String boardAnmtUseYn) {
		this.boardAnmtUseYn = boardAnmtUseYn;
	}
	public String getCdlnDeptName() {
		return cdlnDeptName;
	}
	public void setCdlnDeptName(String cdlnDeptName) {
		this.cdlnDeptName = cdlnDeptName;
	}
	public String getCdlnDeptFname() {
		return cdlnDeptFname;
	}
	public void setCdlnDeptFname(String cdlnDeptFname) {
		this.cdlnDeptFname = cdlnDeptFname;
	}
	public String getCdlnObjrName() {
		return cdlnObjrName;
	}
	public void setCdlnObjrName(String cdlnObjrName) {
		this.cdlnObjrName = cdlnObjrName;
	}
	public String getCdlnEvntCode() {
		return cdlnEvntCode;
	}
	public void setCdlnEvntCode(String cdlnEvntCode) {
		this.cdlnEvntCode = cdlnEvntCode;
	}
	public String getBoardKind() {
		return boardKind;
	}
	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}
	public String getMyNotiTitle() {
		return myNotiTitle;
	}
	public void setMyNotiTitle(String myNotiTitle) {
		this.myNotiTitle = myNotiTitle;
	}
	public void setDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
	private boolean isDesc;
	
	public boolean getIsDesc() {
		return isDesc;
	}
	public void setIsDesc(boolean isDesc) {
		this.isDesc = isDesc;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getUserMap() {
		return userMap;
	}
	public void setUserMap(String userMap) {
		this.userMap = userMap;
	}
	public String getNotiReadmanAsgnYn() {
		return notiReadmanAsgnYn;
	}
	public void setNotiReadmanAsgnYn(String notiReadmanAsgnYn) {
		this.notiReadmanAsgnYn = notiReadmanAsgnYn;
	}
	public String getNotiSeqNo() {
		return notiSeqNo;
	}
	public void setNotiSeqNo(String notiSeqNo) {
		this.notiSeqNo = notiSeqNo;
	}
	public String getPrevNotiId() {
		return prevNotiId;
	}
	public void setPrevNotiId(String prevNotiId) {
		this.prevNotiId = prevNotiId;
	}
	public String getPrevNotiTitle() {
		return prevNotiTitle;
	}
	public void setPrevNotiTitle(String prevNotiTitle) {
		this.prevNotiTitle = prevNotiTitle;
	}
	public String getNextNotiId() {
		return nextNotiId;
	}
	public void setNextNotiId(String nextNotiId) {
		this.nextNotiId = nextNotiId;
	}
	public String getNextNotiTitle() {
		return nextNotiTitle;
	}
	public void setNextNotiTitle(String nextNotiTitle) {
		this.nextNotiTitle = nextNotiTitle;
	}
	public String getRegDttmFrom() {
		return regDttmFrom;
	}
	public void setRegDttmFrom(String regDttmFrom) {
		this.regDttmFrom = regDttmFrom;
	}
	public String getRegDttmTo() {
		return regDttmTo;
	}
	public void setRegDttmTo(String regDttmTo) {
		this.regDttmTo = regDttmTo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getOrderSeq() {
		return orderSeq;
	}
	public void setOrderSeq(String orderSeq) {
		this.orderSeq = orderSeq;
	}
	
	
}
