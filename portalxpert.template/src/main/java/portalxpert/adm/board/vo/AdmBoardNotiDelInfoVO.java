package portalxpert.adm.board.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmBoardNotiDelInfoVO extends SearchConditionVO {
	
	private static final long serialVersionUID = 1L;
	
	private String notiId; /*게시물_아이디*/
	private String delDiv; /*삭제_구분*/
	private String delBasis; /*삭제_근거*/
	private String delRsn; /*삭제_사유*/
	private String delRsnCode; /*삭제_사유_코드*/
	private String delRsnCodeSpec; /*삭제_사유_코드_상세*/
	private String badNotiFindDttm; /*불량_게시물_발견_일시*/
	private String userName; /*사용자_명*/
	private String rmrk; /*비고*/
	private String notiRegrId; /*게시물_등록자_아이디*/
	private String notiRegrName; /*게시물_등록자_명*/
	private String notiRegDttm; /*게시물_등록_일시*/
	private String notiUpdrId; /*게시물_수정자_아이디*/
	private String notiUpdrName; /*게시물_수정자_명*/
	private String notiUpdrDttm; /*게시물_수정자_일시*/
	private String delYn; /*삭제_여부*/
	private String regrId; /*등록자_아이디*/
	private String regrName; /*등록자_명*/
	private String regDttm; /*등록_일시*/
	private String updrId; /*수정자_아이디*/
	private String updrName; /*수정자_명*/
	private String updDttm; /*수정_일시*/
	private String oldNoticeSeq; /*OLD_NOTICE_SEQ*/
	private String oldDelyear; /*OLD_DELYEAR*/
	private String oldSeq; /*OLD_SEQ*/
	
	private String boardId; /*게시판ID*/
	private String boardName; /*게시판명*/
	private String notiTitle; /*게시물제목*/
	private String notiConts; /*게시물내용*/
	
	private String regDttmFrom; /*검색 시작날짜*/
	private String regDttmTo; /*검색 끝날짜*/
	
	private String ou; /*소속*/
	
	private String delRegDttm;
	private String apndFileCnt;
	
	
	public String getApndFileCnt() {
		return apndFileCnt;
	}
	public void setApndFileCnt(String apndFileCnt) {
		this.apndFileCnt = apndFileCnt;
	}
	public String getDelRegDttm() {
		return delRegDttm;
	}
	public void setDelRegDttm(String delRegDttm) {
		this.delRegDttm = delRegDttm;
	}
	public String getOu() {
		return ou;
	}
	public void setOu(String ou) {
		this.ou = ou;
	}
	public String getNotiConts() {
		return notiConts;
	}
	public void setNotiConts(String notiConts) {
		this.notiConts = notiConts;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getNotiTitle() {
		return notiTitle;
	}
	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
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
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public String getDelDiv() {
		return delDiv;
	}
	public void setDelDiv(String delDiv) {
		this.delDiv = delDiv;
	}
	public String getDelBasis() {
		return delBasis;
	}
	public void setDelBasis(String delBasis) {
		this.delBasis = delBasis;
	}
	public String getDelRsn() {
		return delRsn;
	}
	public void setDelRsn(String delRsn) {
		this.delRsn = delRsn;
	}
	public String getDelRsnCode() {
		return delRsnCode;
	}
	public void setDelRsnCode(String delRsnCode) {
		this.delRsnCode = delRsnCode;
	}
	public String getDelRsnCodeSpec() {
		return delRsnCodeSpec;
	}
	public void setDelRsnCodeSpec(String delRsnCodeSpec) {
		this.delRsnCodeSpec = delRsnCodeSpec;
	}
	public String getBadNotiFindDttm() {
		return badNotiFindDttm;
	}
	public void setBadNotiFindDttm(String badNotiFindDttm) {
		this.badNotiFindDttm = badNotiFindDttm;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
	}
	public String getNotiRegrId() {
		return notiRegrId;
	}
	public void setNotiRegrId(String notiRegrId) {
		this.notiRegrId = notiRegrId;
	}
	public String getNotiRegrName() {
		return notiRegrName;
	}
	public void setNotiRegrName(String notiRegrName) {
		this.notiRegrName = notiRegrName;
	}
	public String getNotiRegDttm() {
		return notiRegDttm;
	}
	public void setNotiRegDttm(String notiRegDttm) {
		this.notiRegDttm = notiRegDttm;
	}
	public String getNotiUpdrId() {
		return notiUpdrId;
	}
	public void setNotiUpdrId(String notiUpdrId) {
		this.notiUpdrId = notiUpdrId;
	}
	public String getNotiUpdrName() {
		return notiUpdrName;
	}
	public void setNotiUpdrName(String notiUpdrName) {
		this.notiUpdrName = notiUpdrName;
	}
	public String getNotiUpdrDttm() {
		return notiUpdrDttm;
	}
	public void setNotiUpdrDttm(String notiUpdrDttm) {
		this.notiUpdrDttm = notiUpdrDttm;
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
	public String getOldNoticeSeq() {
		return oldNoticeSeq;
	}
	public void setOldNoticeSeq(String oldNoticeSeq) {
		this.oldNoticeSeq = oldNoticeSeq;
	}
	public String getOldDelyear() {
		return oldDelyear;
	}
	public void setOldDelyear(String oldDelyear) {
		this.oldDelyear = oldDelyear;
	}
	public String getOldSeq() {
		return oldSeq;
	}
	public void setOldSeq(String oldSeq) {
		this.oldSeq = oldSeq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
