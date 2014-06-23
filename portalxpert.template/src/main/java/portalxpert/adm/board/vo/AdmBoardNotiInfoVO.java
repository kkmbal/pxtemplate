package portalxpert.adm.board.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmBoardNotiInfoVO extends SearchConditionVO {
	
	private static final long serialVersionUID = 1L;
	
	private String notiId; /*게시물_아이디*/
	private String upNotiId; /*상위_게시물_아이디*/
	private Long sortSeq; /*정렬_일련번호*/
	private String boardId; /*게시판_아이디*/
	private String emgcYn; /*긴급_여부*/
	private String anmtYn; /*공지_여부*/
	private String popupYn; /*팝업_여부*/
	private String briefYn; /*핫브리핑_여부*/
	private String moblOpenDiv; /*모바일_공개_구분*/
	private String notiTitle; /*게시물_제목*/
	private String titleBoldYn; /*제목_강조_여부*/
	private String titleColorDiv; /*제목_컬러_구분*/
	private String notiConts; /*게시물_내용*/
	private String linkUrl; /*링크_URL*/
	private String notiTp; /*게시물_타입*/
	private String notiKind; /*게시물_종류*/
	private String nickUseYn; /*닉네임_사용_여부*/
	private String userId; /*사용자_아이디*/
	private String userNick; /*사용자_닉네임*/
	private String userName; /*사용자_명*/
	private String editDiv; /*입력툴_구분*/
	private String rsrvYn; /*예약_여부*/
	private String notiBgnDttm; /*게시물_시작_일시*/
	private String notiEndDttm; /*게시물_종료_일시*/
	private String notiOpenDiv; /*게시물_공개_구분*/
	private String notiOpenDivSpec; /*게시물_공개_구분_상세*/
	private String publAsgnDiv; /*게시자_지정_구분*/
	private String publAsgnDivSpec; /*게시자_지정_구분_상세*/
	private String replyPrmsYn; /*답글_허용_여부*/
	private String replyMakrRealnameYn; /*답글_작성자_실명_여부*/
	private String opnPrmsYn; /*의견_허용_여부*/
	private String opnMakrRealnameYn; /*의견_작성자_실명_여부*/
	private Long notiReadCnt; /*게시물_조회_건수*/
	private Long notiOpnCnt; /*게시물_의견_건수*/
	private Long notiAgrmCnt; /*게시물_찬성_건수*/
	private Long notiOppCnt; /*게시물_반대_건수*/
	private Long notiLikeCnt; /*게시물_좋아요_건수*/
	private Long moblSendCnt; /*모바일_발송_건수*/
	private String bfBoardId; /*이전_게시판_아이디*/
	private String bfNotiId; /*이전_게시물_아이디*/
	private String statCode; /*상태_코드*/
	private String deptCode; /*부서_코드*/
	private String deptName; /*부서_명*/
	private String deptFname; /*부서_전체명*/
	private String makrIp; /*작성자_아이피*/
	private String brghCode; /*자치구_코드*/
	private String cdlnDeptCode; /*경조사_부서_코드*/
	private String cdlnDeptName; /*경조사_부서_명*/
	private String cdlnDeptFname; /*경조사_부서_전체명*/
	private String cdlnObjrName; /*경조사_대상자_명*/
	private String cdlnEvntCode; /*경조사_이벤트_코드*/
	private String delYn; /*삭제_여부*/
	private String regrId; /*등록자_아이디*/
	private String regrName; /*등록자_명*/
	private String regDttm; /*등록_일시*/
	private String updrId; /*수정자_아이디*/
	private String updrName; /*수정자_명*/
	private String updDttm; /*수정_일시*/
	private String oldBrdId; /*OLD_BRD_ID*/
	private String oldHeaderId; /*OLD_HEADER_ID*/
	private String oldOrignalId; /*OLD_ORIGNAL_ID*/
	private String oldNoticeSeq; /*OLD_NOTICE_SEQ*/
	
	private String boardName; /*게시판명*/
	private String delBasis; /*삭제_근거*/
	private String delRsn; /*삭제_사유*/
	private String delRsnCode; /*삭제_사유_코드*/
	private String delRsnCodeSpec; /*삭제_사유_코드_상세*/
	private String rmrk; /*비고*/
	
	//공통코드관련
	private String cd; /*코드구룹*/
	private String cdSpec; /*상세코드*/
	private String cdNm; /*상세코드명*/
	private String remark; /*코드비고*/
	
	private String boardKind;
	private String boardForm;
	private String boardFormSpec;
	private String replyWrteDiv;
	private String likeUseYn;
	private String agrmOppUseYn;
	private String admYn;
	private String boardExplUseYn;
	private String boardExpl;
	private String notiEmailSendYn;
	private String notiTagLst;
	
	
	
	public String getNotiTagLst() {
		return notiTagLst;
	}
	public void setNotiTagLst(String notiTagLst) {
		this.notiTagLst = notiTagLst;
	}
	public String getLikeUseYn() {
		return likeUseYn;
	}
	public void setLikeUseYn(String likeUseYn) {
		this.likeUseYn = likeUseYn;
	}
	public String getAgrmOppUseYn() {
		return agrmOppUseYn;
	}
	public void setAgrmOppUseYn(String agrmOppUseYn) {
		this.agrmOppUseYn = agrmOppUseYn;
	}
	public String getAdmYn() {
		return admYn;
	}
	public void setAdmYn(String admYn) {
		this.admYn = admYn;
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
	public String getNotiEmailSendYn() {
		return notiEmailSendYn;
	}
	public void setNotiEmailSendYn(String notiEmailSendYn) {
		this.notiEmailSendYn = notiEmailSendYn;
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
	public String getReplyWrteDiv() {
		return replyWrteDiv;
	}
	public void setReplyWrteDiv(String replyWrteDiv) {
		this.replyWrteDiv = replyWrteDiv;
	}
	public String getCd() {
		return cd;
	}
	public void setCd(String cd) {
		this.cd = cd;
	}
	public String getCdSpec() {
		return cdSpec;
	}
	public void setCdSpec(String cdSpec) {
		this.cdSpec = cdSpec;
	}
	public String getCdNm() {
		return cdNm;
	}
	public void setCdNm(String cdNm) {
		this.cdNm = cdNm;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getRmrk() {
		return rmrk;
	}
	public void setRmrk(String rmrk) {
		this.rmrk = rmrk;
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
	public String getNotiId() {
		return notiId;
	}
	public void setNotiId(String notiId) {
		this.notiId = notiId;
	}
	public String getUpNotiId() {
		return upNotiId;
	}
	public void setUpNotiId(String upNotiId) {
		this.upNotiId = upNotiId;
	}
	public Long getSortSeq() {
		return sortSeq;
	}
	public void setSortSeq(Long sortSeq) {
		this.sortSeq = sortSeq;
	}
	public String getBoardId() {
		return boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = boardId;
	}
	public String getEmgcYn() {
		return emgcYn;
	}
	public void setEmgcYn(String emgcYn) {
		this.emgcYn = emgcYn;
	}
	public String getAnmtYn() {
		return anmtYn;
	}
	public void setAnmtYn(String anmtYn) {
		this.anmtYn = anmtYn;
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
	public String getMoblOpenDiv() {
		return moblOpenDiv;
	}
	public void setMoblOpenDiv(String moblOpenDiv) {
		this.moblOpenDiv = moblOpenDiv;
	}
	public String getNotiTitle() {
		return notiTitle;
	}
	public void setNotiTitle(String notiTitle) {
		this.notiTitle = notiTitle;
	}
	public String getTitleBoldYn() {
		return titleBoldYn;
	}
	public void setTitleBoldYn(String titleBoldYn) {
		this.titleBoldYn = titleBoldYn;
	}
	public String getTitleColorDiv() {
		return titleColorDiv;
	}
	public void setTitleColorDiv(String titleColorDiv) {
		this.titleColorDiv = titleColorDiv;
	}
	public String getNotiConts() {
		return notiConts;
	}
	public void setNotiConts(String notiConts) {
		this.notiConts = notiConts;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getNotiTp() {
		return notiTp;
	}
	public void setNotiTp(String notiTp) {
		this.notiTp = notiTp;
	}
	public String getNotiKind() {
		return notiKind;
	}
	public void setNotiKind(String notiKind) {
		this.notiKind = notiKind;
	}
	public String getNickUseYn() {
		return nickUseYn;
	}
	public void setNickUseYn(String nickUseYn) {
		this.nickUseYn = nickUseYn;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getEditDiv() {
		return editDiv;
	}
	public void setEditDiv(String editDiv) {
		this.editDiv = editDiv;
	}
	public String getRsrvYn() {
		return rsrvYn;
	}
	public void setRsrvYn(String rsrvYn) {
		this.rsrvYn = rsrvYn;
	}
	public String getNotiBgnDttm() {
		return notiBgnDttm;
	}
	public void setNotiBgnDttm(String notiBgnDttm) {
		this.notiBgnDttm = notiBgnDttm;
	}
	public String getNotiEndDttm() {
		return notiEndDttm;
	}
	public void setNotiEndDttm(String notiEndDttm) {
		this.notiEndDttm = notiEndDttm;
	}
	public String getNotiOpenDiv() {
		return notiOpenDiv;
	}
	public void setNotiOpenDiv(String notiOpenDiv) {
		this.notiOpenDiv = notiOpenDiv;
	}
	public String getNotiOpenDivSpec() {
		return notiOpenDivSpec;
	}
	public void setNotiOpenDivSpec(String notiOpenDivSpec) {
		this.notiOpenDivSpec = notiOpenDivSpec;
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
	public String getReplyPrmsYn() {
		return replyPrmsYn;
	}
	public void setReplyPrmsYn(String replyPrmsYn) {
		this.replyPrmsYn = replyPrmsYn;
	}
	public String getReplyMakrRealnameYn() {
		return replyMakrRealnameYn;
	}
	public void setReplyMakrRealnameYn(String replyMakrRealnameYn) {
		this.replyMakrRealnameYn = replyMakrRealnameYn;
	}
	public String getOpnPrmsYn() {
		return opnPrmsYn;
	}
	public void setOpnPrmsYn(String opnPrmsYn) {
		this.opnPrmsYn = opnPrmsYn;
	}
	public String getOpnMakrRealnameYn() {
		return opnMakrRealnameYn;
	}
	public void setOpnMakrRealnameYn(String opnMakrRealnameYn) {
		this.opnMakrRealnameYn = opnMakrRealnameYn;
	}
	public Long getNotiReadCnt() {
		return notiReadCnt;
	}
	public void setNotiReadCnt(Long notiReadCnt) {
		this.notiReadCnt = notiReadCnt;
	}
	public Long getNotiOpnCnt() {
		return notiOpnCnt;
	}
	public void setNotiOpnCnt(Long notiOpnCnt) {
		this.notiOpnCnt = notiOpnCnt;
	}
	public Long getNotiAgrmCnt() {
		return notiAgrmCnt;
	}
	public void setNotiAgrmCnt(Long notiAgrmCnt) {
		this.notiAgrmCnt = notiAgrmCnt;
	}
	public Long getNotiOppCnt() {
		return notiOppCnt;
	}
	public void setNotiOppCnt(Long notiOppCnt) {
		this.notiOppCnt = notiOppCnt;
	}
	public Long getNotiLikeCnt() {
		return notiLikeCnt;
	}
	public void setNotiLikeCnt(Long notiLikeCnt) {
		this.notiLikeCnt = notiLikeCnt;
	}
	public Long getMoblSendCnt() {
		return moblSendCnt;
	}
	public void setMoblSendCnt(Long moblSendCnt) {
		this.moblSendCnt = moblSendCnt;
	}
	public String getBfBoardId() {
		return bfBoardId;
	}
	public void setBfBoardId(String bfBoardId) {
		this.bfBoardId = bfBoardId;
	}
	public String getBfNotiId() {
		return bfNotiId;
	}
	public void setBfNotiId(String bfNotiId) {
		this.bfNotiId = bfNotiId;
	}
	public String getStatCode() {
		return statCode;
	}
	public void setStatCode(String statCode) {
		this.statCode = statCode;
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
	public String getDeptFname() {
		return deptFname;
	}
	public void setDeptFname(String deptFname) {
		this.deptFname = deptFname;
	}
	public String getMakrIp() {
		return makrIp;
	}
	public void setMakrIp(String makrIp) {
		this.makrIp = makrIp;
	}
	public String getBrghCode() {
		return brghCode;
	}
	public void setBrghCode(String brghCode) {
		this.brghCode = brghCode;
	}
	public String getCdlnDeptCode() {
		return cdlnDeptCode;
	}
	public void setCdlnDeptCode(String cdlnDeptCode) {
		this.cdlnDeptCode = cdlnDeptCode;
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
	public String getOldHeaderId() {
		return oldHeaderId;
	}
	public void setOldHeaderId(String oldHeaderId) {
		this.oldHeaderId = oldHeaderId;
	}
	public String getOldOrignalId() {
		return oldOrignalId;
	}
	public void setOldOrignalId(String oldOrignalId) {
		this.oldOrignalId = oldOrignalId;
	}
	public String getOldNoticeSeq() {
		return oldNoticeSeq;
	}
	public void setOldNoticeSeq(String oldNoticeSeq) {
		this.oldNoticeSeq = oldNoticeSeq;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
