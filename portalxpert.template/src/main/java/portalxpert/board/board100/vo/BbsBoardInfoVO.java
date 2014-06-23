package portalxpert.board.board100.vo;

public class BbsBoardInfoVO {
	
	private String boardId; //게시판_아이디
	private String boardTp; //게시판_유형
	private String requDeptCode; //요청_부서_코드
	private String requDeptName; //요청_부서_명
	private String requUserId; //요청_사용자_아이디
	private String requUserName; //요청_사용자_명
	private String requDocNo; //요청_문서_번호
	private String boardName; //게시판_명
	private String boardKind; //게시판_종류
	private String boardForm; //게시판_형태
	private String boardFormSpec; //게시판_형태_상세
	private String moblLinkYn; //모바일_연동_여부
	private String boardOperYn; //게시판_운영_여부
	private String adminAlertYn; //관리자_알림_여부
	private String boardOpenDiv; //게시판_공개_구분
	private String boardOpenDivSpec; //게시판_공개_구분_상세
	private String boardOperDiv; //게시판_운영_구분
	private String boardOperBgnDttm; //게시판_운영_시작_일시
	private String boardOperEndDttm; //게시판_운영_종료_일시
	private String notiTermDiv; //게시물_기간_구분
	private String publAsgnDiv; //게시자_지정_구분
	private String publAsgnDivSpec; //게시자_지정_구분_상세
	private String boardAnmtUseYn; //게시판_공지_사용_여부
	private String boardExplUseYn; //게시판_설명_사용_여부
	private String boardExpl; //게시판_설명
	private String makrDispDiv; //작성자_표기_구분
	private String notiReadmanAsgnYn; //게시물_조회자_지정_여부
	private String agrmOppUseYn; //찬성_반대_사용_여부
	private String replyWrteDiv; //답글_쓰기_구분
	private String opnFileUploadYn; //의견_파일_업로드_여부
	private String opnFileUploadCnt; //의견_파일_업로드_갯수
	private String opnWrteDiv; //의견_쓰기_구분
	private String opnReplyUseDiv; //의견_답글_사용_구분
	private String opnRealnameDiv; //의견_실명_구분
	private String likeUseYn; //좋아요_사용_여부
	private String nickUseYn; //닉네임_사용_여부
	private String smsUseYn; //SMS_사용_여부
	private int apndFileSz; //첨부_파일_사이즈
	private String editDiv; //입력툴_구분
	private String notiEmailSendYn; //게시물_이메일_발송_여부
	private String outsideOpenDiv; //외부_공개_구분
	private String outsideOpenDivSpec; //외부_공개_구분_상세
	private String popupYn; //팝업_여부
	private String briefYn; //핫브리핑_여부
	private String fcode; //FCODE
	private String readDiv; //조회_구분
	private String delYn; //삭제_여부
	private String regrId; //등록자_아이디
	private String regrName; //등록자_명
	private String regDttm; //등록_일시
	private String updrId; //수정자_아이디
	private String updrName; //수정자_명
	private String updDttm; //수정_일시
	private String oldBrdId; //OLD_BRD_ID
	private String oldParId; //OLD_PAR_ID
	private int oldSeq; //OLD_SEQ
	
	private String userMap;
	private String userId;
	private String redYn;
	private String admYn;
	private String wrtYn;
	private String favoriteYn;
	
	private String basicCloseDttm;
	
	private String category;
	
	public String getRedYn() {
		return redYn;
	}
	public void setRedYn(String redYn) {
		this.redYn = redYn;
	}
	public String getAdmYn() {
		return admYn;
	}
	public void setAdmYn(String admYn) {
		this.admYn = admYn;
	}
	public String getWrtYn() {
		return wrtYn;
	}
	public void setWrtYn(String wrtYn) {
		this.wrtYn = wrtYn;
	}
	public String getFavoriteYn() {
		return favoriteYn;
	}
	public void setFavoriteYn(String favoriteYn) {
		this.favoriteYn = favoriteYn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserMap() {
		return userMap;
	}
	public void setUserMap(String userMap) {
		this.userMap = userMap;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getBoardTp() {
		return boardTp;
	}
	public void setBoardTp(String boardTp) {
		this.boardTp = boardTp;
	}
	public String getRequDeptCode() {
		return requDeptCode;
	}
	public void setRequDeptCode(String requDeptCode) {
		this.requDeptCode = requDeptCode;
	}
	public String getRequDeptName() {
		return requDeptName;
	}
	public void setRequDeptName(String requDeptName) {
		this.requDeptName = requDeptName;
	}
	public String getRequUserId() {
		return requUserId;
	}
	public void setRequUserId(String requUserId) {
		this.requUserId = requUserId;
	}
	public String getRequUserName() {
		return requUserName;
	}
	public void setRequUserName(String requUserName) {
		this.requUserName = requUserName;
	}
	public String getRequDocNo() {
		return requDocNo;
	}
	public void setRequDocNo(String requDocNo) {
		this.requDocNo = requDocNo;
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
	public String getMoblLinkYn() {
		return moblLinkYn;
	}
	public void setMoblLinkYn(String moblLinkYn) {
		this.moblLinkYn = moblLinkYn;
	}
	public String getBoardOperYn() {
		return boardOperYn;
	}
	public void setBoardOperYn(String boardOperYn) {
		this.boardOperYn = boardOperYn;
	}
	public String getAdminAlertYn() {
		return adminAlertYn;
	}
	public void setAdminAlertYn(String adminAlertYn) {
		this.adminAlertYn = adminAlertYn;
	}
	public String getBoardOpenDiv() {
		return boardOpenDiv;
	}
	public void setBoardOpenDiv(String boardOpenDiv) {
		this.boardOpenDiv = boardOpenDiv;
	}
	public String getBoardOpenDivSpec() {
		return boardOpenDivSpec;
	}
	public void setBoardOpenDivSpec(String boardOpenDivSpec) {
		this.boardOpenDivSpec = boardOpenDivSpec;
	}
	public String getBoardOperDiv() {
		return boardOperDiv;
	}
	public void setBoardOperDiv(String boardOperDiv) {
		this.boardOperDiv = boardOperDiv;
	}
	public String getBoardOperBgnDttm() {
		return boardOperBgnDttm;
	}
	public void setBoardOperBgnDttm(String boardOperBgnDttm) {
		this.boardOperBgnDttm = boardOperBgnDttm;
	}
	public String getBoardOperEndDttm() {
		return boardOperEndDttm;
	}
	public void setBoardOperEndDttm(String boardOperEndDttm) {
		this.boardOperEndDttm = boardOperEndDttm;
	}
	public String getNotiTermDiv() {
		return notiTermDiv;
	}
	public void setNotiTermDiv(String notiTermDiv) {
		this.notiTermDiv = notiTermDiv;
	}
	public String getPublAsgnDiv() {
		return publAsgnDiv;
	}
	public void setPublAsgnDiv(String publAsgnDiv) {
		this.publAsgnDiv = publAsgnDiv;
	}
	public String getPublAsgnDivSpec() {
		return publAsgnDivSpec;
	}
	public void setPublAsgnDivSpec(String publAsgnDivSpec) {
		this.publAsgnDivSpec = publAsgnDivSpec;
	}
	public String getBoardAnmtUseYn() {
		return boardAnmtUseYn;
	}
	public void setBoardAnmtUseYn(String boardAnmtUseYn) {
		this.boardAnmtUseYn = boardAnmtUseYn;
	}
	public String getBoardExplUseYn() {
		return boardExplUseYn;
	}
	public void setBoardExplUseYn(String boardExplUseYn) {
		this.boardExplUseYn = boardExplUseYn;
	}
	public String getBoardExpl() {
		return boardExpl;
	}
	public void setBoardExpl(String boardExpl) {
		this.boardExpl = boardExpl;
	}
	public String getMakrDispDiv() {
		return makrDispDiv;
	}
	public void setMakrDispDiv(String makrDispDiv) {
		this.makrDispDiv = makrDispDiv;
	}
	public String getNotiReadmanAsgnYn() {
		return notiReadmanAsgnYn;
	}
	public void setNotiReadmanAsgnYn(String notiReadmanAsgnYn) {
		this.notiReadmanAsgnYn = notiReadmanAsgnYn;
	}
	public String getAgrmOppUseYn() {
		return agrmOppUseYn;
	}
	public void setAgrmOppUseYn(String agrmOppUseYn) {
		this.agrmOppUseYn = agrmOppUseYn;
	}
	public String getReplyWrteDiv() {
		return replyWrteDiv;
	}
	public void setReplyWrteDiv(String replyWrteDiv) {
		this.replyWrteDiv = replyWrteDiv;
	}
	public String getOpnFileUploadYn() {
		return opnFileUploadYn;
	}
	public void setOpnFileUploadYn(String opnFileUploadYn) {
		this.opnFileUploadYn = opnFileUploadYn;
	}
	public String getOpnFileUploadCnt() {
		return opnFileUploadCnt;
	}
	public void setOpnFileUploadCnt(String opnFileUploadCnt) {
		this.opnFileUploadCnt = opnFileUploadCnt;
	}
	public String getOpnWrteDiv() {
		return opnWrteDiv;
	}
	public void setOpnWrteDiv(String opnWrteDiv) {
		this.opnWrteDiv = opnWrteDiv;
	}
	public String getOpnReplyUseDiv() {
		return opnReplyUseDiv;
	}
	public void setOpnReplyUseDiv(String opnReplyUseDiv) {
		this.opnReplyUseDiv = opnReplyUseDiv;
	}
	public String getOpnRealnameDiv() {
		return opnRealnameDiv;
	}
	public void setOpnRealnameDiv(String opnRealnameDiv) {
		this.opnRealnameDiv = opnRealnameDiv;
	}
	public String getLikeUseYn() {
		return likeUseYn;
	}
	public void setLikeUseYn(String likeUseYn) {
		this.likeUseYn = likeUseYn;
	}
	public String getNickUseYn() {
		return nickUseYn;
	}
	public void setNickUseYn(String nickUseYn) {
		this.nickUseYn = nickUseYn;
	}
	public String getSmsUseYn() {
		return smsUseYn;
	}
	public void setSmsUseYn(String smsUseYn) {
		this.smsUseYn = smsUseYn;
	}
	public int getApndFileSz() {
		return apndFileSz;
	}
	public void setApndFileSz(int apndFileSz) {
		this.apndFileSz = apndFileSz;
	}
	public String getEditDiv() {
		return editDiv;
	}
	public void setEditDiv(String editDiv) {
		this.editDiv = editDiv;
	}
	public String getNotiEmailSendYn() {
		return notiEmailSendYn;
	}
	public void setNotiEmailSendYn(String notiEmailSendYn) {
		this.notiEmailSendYn = notiEmailSendYn;
	}
	public String getOutsideOpenDiv() {
		return outsideOpenDiv;
	}
	public void setOutsideOpenDiv(String outsideOpenDiv) {
		this.outsideOpenDiv = outsideOpenDiv;
	}
	public String getOutsideOpenDivSpec() {
		return outsideOpenDivSpec;
	}
	public void setOutsideOpenDivSpec(String outsideOpenDivSpec) {
		this.outsideOpenDivSpec = outsideOpenDivSpec;
	}
	public String getPopupYn() {
		return popupYn;
	}
	public void setPopupYn(String popupYn) {
		this.popupYn = popupYn;
	}
	public String getBriefYn() {
		return briefYn;
	}
	public void setBriefYn(String briefYn) {
		this.briefYn = briefYn;
	}
	public String getFcode() {
		return fcode;
	}
	public void setFcode(String fcode) {
		this.fcode = fcode;
	}
	public String getReadDiv() {
		return readDiv;
	}
	public void setReadDiv(String readDiv) {
		this.readDiv = readDiv;
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
	public String getOldBrdId() {
		return oldBrdId;
	}
	public void setOldBrdId(String oldBrdId) {
		this.oldBrdId = oldBrdId;
	}
	public String getOldParId() {
		return oldParId;
	}
	public void setOldParId(String oldParId) {
		this.oldParId = oldParId;
	}
	public int getOldSeq() {
		return oldSeq;
	}
	public void setOldSeq(int oldSeq) {
		this.oldSeq = oldSeq;
	}
	public String getBasicCloseDttm() {
		return basicCloseDttm;
	}
	public void setBasicCloseDttm(String basicCloseDttm) {
		this.basicCloseDttm = basicCloseDttm;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	
}
