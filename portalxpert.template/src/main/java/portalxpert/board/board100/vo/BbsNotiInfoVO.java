package portalxpert.board.board100.vo;

public class BbsNotiInfoVO {
	private String notiId;
	private String upNotiId;
	private int sortSeq;
	private String boardId;
	private String boardName;
	private String boardDiv;
	private String emgcYn;
	private String anmtYn;
	private String popupYn;
	private String briefYn;
	private String moblOpenDiv;
	private String notiTitle;
	private String notiTitleOrgn;
	private String titleBoldYn;
	private String titleColorDiv;
	private String notiConts;
	private String linkUrl;
	private String notiTp;
	private String notiKind;
	private String nickUseYn;
	private String userId;
	private String userNick;
	private String userName;
	private String editDiv;
	private String rsrvYn;
	private String notiBgnDttm;
	private String notiEndDttm;
	private String notiOpenDiv;
	private String notiOpenDivSpec;
	private String publAsgnDiv;
	private String publAsgnDivSpec;
	private String replyPrmsYn;
	private String replyMakrRealnameYn;
	private String opnPrmsYn;
	private String opnMakrRealnameYn;
	private int notiReadCnt;
	private int notiOpnCnt;
	private int notiAgrmCnt;
	private int notiOppCnt;
	private int notiLikeCnt;
	private String bfBoardId;
	private String bfNotiId;
	private String statCode;
	private String deptCode;
	private String deptName;
	private String deptFname;
	private String makrIp;
	private String brghCode;
	private String cdlnDeptCode;
	private String cdlnDeptName;
	private String cdlnDeptFname;
	private String cdlnObjrName;
	private String cdlnEvntCode;
	private String delYn;
	private String regrId;
	private String regrName;
	private String regDttm;
	private String updrId;
	private String updrName;
	private String updDttm;
	private String oldBrdId;
	private String oldHeaderId;
	private String oldOrignalId;
	private String oldNoticeSeq;
	private String boardUrl;
	private String mail;
	private String evalReadYn;
	private String notiTagLst;
	private String agrmOppYn;
	private String apndFileName;
	private String isAdmin;
	private String boardFormSpec;
	private String notiReadmanAsgnYn;
	private String apndFileTp;
	private String title;
	private String start;
	private String end;
	private int lvl; // 답글레벨
	private int upNotiIdCnt; //답글갯수
	private String openAdm; //운영자만공개
	
	
	public String getOpenAdm() {
		return openAdm;
	}
	public void setOpenAdm(String openAdm) {
		this.openAdm = openAdm;
	}
	public int getUpNotiIdCnt() {
		return upNotiIdCnt;
	}
	public void setUpNotiIdCnt(int upNotiIdCnt) {
		this.upNotiIdCnt = upNotiIdCnt;
	}
	public int getLvl() {
		return lvl;
	}
	public void setLvl(int lvl) {
		this.lvl = lvl;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStart() {
		return start;
	}
	public void setStart(String start) {
		this.start = start;
	}
	public String getEnd() {
		return end;
	}
	public void setEnd(String end) {
		this.end = end;
	}
	public String getBoardFormSpec() {
		return boardFormSpec;
	}
	public void setBoardFormSpec(String boardFormSpec) {
		this.boardFormSpec = boardFormSpec;
	}
	public String getNotiReadmanAsgnYn() {
		return notiReadmanAsgnYn;
	}
	public void setNotiReadmanAsgnYn(String notiReadmanAsgnYn) {
		this.notiReadmanAsgnYn = notiReadmanAsgnYn;
	}
	public String getAgrmOppYn() {
		return agrmOppYn;
	}
	public void setAgrmOppYn(String agrmOppYn) {
		this.agrmOppYn = agrmOppYn;
	}
	public String getEvalReadYn() {
		return evalReadYn;
	}
	public void setEvalReadYn(String evalReadYn) {
		this.evalReadYn = evalReadYn;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getCdlnTitle() {
		return cdlnTitle;
	}
	public void setCdlnTitle(String cdlnTitle) {
		this.cdlnTitle = cdlnTitle;
	}
	public String getBoardUrl() {
		return boardUrl;
	}
	public void setBoardUrl(String boardUrl) {
		this.boardUrl = boardUrl;
	}
	public String getOpnCnt() {
		return opnCnt;
	}
	public void setOpnCnt(String opnCnt) {
		this.opnCnt = opnCnt;
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
	public String getIpOpenYn() {
		return ipOpenYn;
	}
	public void setIpOpenYn(String ipOpenYn) {
		this.ipOpenYn = ipOpenYn;
	}
	public String getEvalAgrmCnt() {
		return evalAgrmCnt;
	}
	public void setEvalAgrmCnt(String evalAgrmCnt) {
		this.evalAgrmCnt = evalAgrmCnt;
	}
	public String getEvalOppCnt() {
		return evalOppCnt;
	}
	public void setEvalOppCnt(String evalOppCnt) {
		this.evalOppCnt = evalOppCnt;
	}
	public String getEvalLikeCnt() {
		return evalLikeCnt;
	}
	public void setEvalLikeCnt(String evalLikeCnt) {
		this.evalLikeCnt = evalLikeCnt;
	}
	public String getEvalReadCnt() {
		return evalReadCnt;
	}
	public void setEvalReadCnt(String evalReadCnt) {
		this.evalReadCnt = evalReadCnt;
	}
	private String apndFileCnt; //첨부파일 갯수
	private String rnum; //번호
	private String notiIdStr;//게시글 번호 모음
	private String opnCnt;//의견갯수
	private String prevNotiId;//이전글아이디
	private String prevNotiTitle;//이전글제목
	private String nextNotiId;//다음글아이디
	private String nextNotiTitle;//다음글제목
	private String ipOpenYn;//아이피 공개여부
	private String evalAgrmCnt;//찬성건수
	private String evalOppCnt;//반대건수
	private String evalLikeCnt;//좋아요 건수
	private String evalReadCnt;//읽기건수
	private String updYn;//수정여부
	private String cdlnTitle;//경조사 제목
	private String userMap;//사용자 권한
	private int pnum;//글정렬 고유값
	private int prevPnum;
	private int nextPnum;
	private String maxNotiId;
	private String reNotiYn;
	private String notiReadYn;
	private String plusMode;
	private String nextNotiKind;
	private String prevNotiKind;
	private String prevLinkUrl;
	private String nextLinkUrl;
	
	public String getPrevLinkUrl() {
		return prevLinkUrl;
	}
	public void setPrevLinkUrl(String prevLinkUrl) {
		this.prevLinkUrl = prevLinkUrl;
	}
	public String getNextLinkUrl() {
		return nextLinkUrl;
	}
	public void setNextLinkUrl(String nextLinkUrl) {
		this.nextLinkUrl = nextLinkUrl;
	}
	public String getNextNotiKind() {
		return nextNotiKind;
	}
	public void setNextNotiKind(String nextNotiKind) {
		this.nextNotiKind = nextNotiKind;
	}
	public String getPrevNotiKind() {
		return prevNotiKind;
	}
	public void setPrevNotiKind(String prevNotiKind) {
		this.prevNotiKind = prevNotiKind;
	}
	public String getPlusMode() {
		return plusMode;
	}
	public void setPlusMode(String plusMode) {
		this.plusMode = plusMode;
	}
	public String getNotiReadYn() {
		return notiReadYn;
	}
	public void setNotiReadYn(String notiReadYn) {
		this.notiReadYn = notiReadYn;
	}
	public String getReNotiYn() {
		return reNotiYn;
	}
	public void setReNotiYn(String reNotiYn) {
		this.reNotiYn = reNotiYn;
	}
	public String getMaxNotiId() {
		return maxNotiId;
	}
	public void setMaxNotiId(String maxNotiId) {
		this.maxNotiId = maxNotiId;
	}
	public int getPnum() {
		return pnum;
	}
	public void setPnum(int pnum) {
		this.pnum = pnum;
	}
	public int getPrevPnum() {
		return prevPnum;
	}
	public void setPrevPnum(int prevPnum) {
		this.prevPnum = prevPnum;
	}
	public int getNextPnum() {
		return nextPnum;
	}
	public void setNextPnum(int nextPnum) {
		this.nextPnum = nextPnum;
	}
	public String getUserMap() {
		return userMap;
	}
	public void setUserMap(String userMap) {
		this.userMap = userMap;
	}
	public String getUpdYn() {
		return updYn;
	}
	public void setUpdYn(String updYn) {
		this.updYn = updYn;
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
	public int getSortSeq() {
		return sortSeq;
	}
	public void setSortSeq(int sortSeq) {
		this.sortSeq = sortSeq;
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
	
	public String getBoardDiv() {
		return boardDiv;
	}
	public void setBoardDiv(String boardDiv) {
		this.boardDiv = boardDiv;
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
	public String getNotiTitleOrgn() {
		return notiTitleOrgn;
	}
	public void setNotiTitleOrgn(String notiTitleOrgn) {
		this.notiTitleOrgn = notiTitleOrgn;
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
	public int getNotiReadCnt() {
		return notiReadCnt;
	}
	public void setNotiReadCnt(int notiReadCnt) {
		this.notiReadCnt = notiReadCnt;
	}
	public int getNotiOpnCnt() {
		return notiOpnCnt;
	}
	public void setNotiOpnCnt(int notiOpnCnt) {
		this.notiOpnCnt = notiOpnCnt;
	}
	public int getNotiAgrmCnt() {
		return notiAgrmCnt;
	}
	public void setNotiAgrmCnt(int notiAgrmCnt) {
		this.notiAgrmCnt = notiAgrmCnt;
	}
	public int getNotiOppCnt() {
		return notiOppCnt;
	}
	public void setNotiOppCnt(int notiOppCnt) {
		this.notiOppCnt = notiOppCnt;
	}
	public int getNotiLikeCnt() {
		return notiLikeCnt;
	}
	public void setNotiLikeCnt(int notiLikeCnt) {
		this.notiLikeCnt = notiLikeCnt;
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
	public String getNotiTagLst() {
		return notiTagLst;
	}
	public void setNotiTagLst(String notiTagLst) {
		this.notiTagLst = notiTagLst;
	}
	public String getApndFileCnt() {
		return apndFileCnt;
	}
	public void setApndFileCnt(String apndFileCnt) {
		this.apndFileCnt = apndFileCnt;
	}
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getNotiIdStr() {
		return notiIdStr;
	}
	public void setNotiIdStr(String notiIdStr) {
		this.notiIdStr = notiIdStr;
	}
	public String getApndFileName() {
		return apndFileName;
	}
	public void setApndFileName(String apndFileName) {
		this.apndFileName = apndFileName;
	}
	public String getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(String isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getApndFileTp() {
		return apndFileTp;
	}
	public void setApndFileTp(String apndFileTp) {
		this.apndFileTp = apndFileTp;
	}
		

}
