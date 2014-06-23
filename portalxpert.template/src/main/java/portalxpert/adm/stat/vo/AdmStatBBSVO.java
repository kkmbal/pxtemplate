/**
 * 
 */
package portalxpert.adm.stat.vo;

/**
 * @author yoDJ
 *
 */
public class AdmStatBBSVO {

	private static final long serialVersionUID = 1L;
	
	private int totContentCnt = 0;	/*	총게시물 건 수	*/
	private String insDts = "";		/*	게시판 생성일/게시일	*/
	private int viewCnt = 0;		/*	조회수	*/
	private int reCnt = 0;			/*	답글건수	*/
	private int admDelCnt = 0;		/*	삭제건수(관리자)	*/
	private int delCnt = 0;			/*	삭제건수(본인)	*/
	private int admMoveCnt = 0;		/*	이동건수(관리자)	*/
	private int moveCnt = 0;		/*	이동건수(본인)	*/
	/**	201308 추가	*/
	private int opinionCnt = 0;		/*	게시물_의견_건수	*/
	private int approvalCnt = 0;	/*	게시물_찬성_건수	*/
	private int oppositionCnt = 0;	/*	게시물_반대_건수	*/
	private int likeCnt = 0;		/*	게시물_좋아요_건수	*/		
	private int imageCnt = 0;		/*	이미지_건수	*/
	private int movieCnt = 0;		/*	동영상_건수	*/
	private int surveyCnt = 0;		/*	설문_건수	*/
		
	private int rank = 0;			/*	순위	*/
	private String title = "";		/*	제목	*/
	private String insName= "";		/*	작성자	*/
	/**	201308 추가	*/
	private String boardId = "";	/*	게시판 ID	*/
	private String boardTitle = "";	/*	게시판 제목	*/
	private String delYn = "";		/*	게시판 삭제여부	*/
	
	private String boardName;
	private String boardKind;
	private String boardForm;
	private String boardFormSpec;
	private String regrName;
	private String totCnt;
	private String readCnt;
	private String regDttm;
	private String endDttm;
	private String seq;
	
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardKind() {
		return boardKind;
	}
	public void setBoardKind(String boardKind) {
		this.boardKind = boardKind;
	}
	public String getBoardForm() {
		return boardForm;
	}
	public void setBoardForm(String boardForm) {
		this.boardForm = boardForm;
	}
	public String getBoardFormSpec() {
		return boardFormSpec;
	}
	public void setBoardFormSpec(String boardFormSpec) {
		this.boardFormSpec = boardFormSpec;
	}
	public String getRegrName() {
		return regrName;
	}
	public void setRegrName(String regrName) {
		this.regrName = regrName;
	}
	public String getTotCnt() {
		return totCnt;
	}
	public void setTotCnt(String totCnt) {
		this.totCnt = totCnt;
	}
	public String getReadCnt() {
		return readCnt;
	}
	public void setReadCnt(String readCnt) {
		this.readCnt = readCnt;
	}
	public String getRegDttm() {
		return regDttm;
	}
	public void setRegDttm(String regDttm) {
		this.regDttm = regDttm;
	}
	public String getEndDttm() {
		return endDttm;
	}
	public void setEndDttm(String endDttm) {
		this.endDttm = endDttm;
	}
	public int getTotContentCnt() {
		return totContentCnt;
	}
	public void setTotContentCnt(int totContentCnt) {
		this.totContentCnt = totContentCnt;
	}	
	public String getInsDts() {
		return insDts;
	}
	public void setInsDts(String insDts) {
		this.insDts = insDts;
	}
	public int getViewCnt() {
		return viewCnt;
	}
	public void setViewCnt(int viewCnt) {
		this.viewCnt = viewCnt;
	}
	public int getReCnt() {
		return reCnt;
	}
	public void setReCnt(int reCnt) {
		this.reCnt = reCnt;
	}
	public int getAdmDelCnt() {
		return admDelCnt;
	}
	public void setAdmDelCnt(int admDelCnt) {
		this.admDelCnt = admDelCnt;
	}
	public int getDelCnt() {
		return delCnt;
	}
	public void setDelCnt(int delCnt) {
		this.delCnt = delCnt;
	}
	public int getAdmMoveCnt() {
		return admMoveCnt;
	}
	public void setAdmMoveCnt(int admMoveCnt) {
		this.admMoveCnt = admMoveCnt;
	}
	public int getMoveCnt() {
		return moveCnt;
	}
	public void setMoveCnt(int moveCnt) {
		this.moveCnt = moveCnt;
	}
	public int getOpinionCnt() {
		return opinionCnt;
	}
	public void setOpinionCnt(int opinionCnt) {
		this.opinionCnt = opinionCnt;
	}
	public int getApprovalCnt() {
		return approvalCnt;
	}
	public void setApprovalCnt(int approvalCnt) {
		this.approvalCnt = approvalCnt;
	}
	public int getOppositionCnt() {
		return oppositionCnt;
	}
	public void setOppositionCnt(int oppositionCnt) {
		this.oppositionCnt = oppositionCnt;
	}
	public int getLikeCnt() {
		return likeCnt;
	}
	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}
	public int getImageCnt() {
		return imageCnt;
	}
	public void setImageCnt(int imageCnt) {
		this.imageCnt = imageCnt;
	}
	public int getMovieCnt() {
		return movieCnt;
	}
	public void setMovieCnt(int movieCnt) {
		this.movieCnt = movieCnt;
	}
	public int getSurveyCnt() {
		return surveyCnt;
	}
	public void setSurveyCnt(int surveyCnt) {
		this.surveyCnt = surveyCnt;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getInsName() {
		return insName;
	}
	public void setInsName(String insName) {
		this.insName = insName;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getDelYn() {
		return delYn;
	}
	public void setDelYn(String delYn) {
		this.delYn = delYn;
	}
}
