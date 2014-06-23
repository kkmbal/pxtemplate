package portalxpert.main.vo;

import portalxpert.common.vo.SearchConditionVO;

/**
 * @author crossent
 */
public class MainVO extends SearchConditionVO{

	private static final long serialVersionUID = -9110148193265925098L;
	
	private String bnrId           = "";
	private String title           = "";
    private String linkUrl         = "";                                                                
    private String altTxt          = "";
    private String setWidth        = "";                                                   
    private String setHeight       = "";                                                  
    private String imgNameOrg      = "";                                                               
    private String imgNameReal     = "";                                                               
    private String imgPath         = "";                                                                
                                      
    private String notiId          = "";
	private String boardId         = "";
	private String notiTitle       = "";
	private String notiTitleOrgn   = "";
	private String titleBoldYn     = "";
	private String titleColorDiv   = "";
	private String notiConts       = "";
	private String notiOpnCnt      = "";
	private String nickUseYn	   = "";
	private String userNick		   = "";
	private String notiKind		   = "";
	private String deptCode		   = "";
	private String deptName        = "";
	private String deptFname       = "";
	private String regrId		   = "";
	private String regrName        = "";
	private String regDttm         = "";
	private String updDttm         = "";
	private String makrDispDiv	   = "";
	private String notiReadYn	   = "";
	                         
	private String linkCateCode	   = "";
	private String linkCode		   = "";
    private String linkName        = "";
    private String tagFont         = "";
    private String boldYn          = "";
    private String fontSize        = "";
    private String fontColor       = "";
    private String bkgdColor       = "";
    private String tagDiv          = "";
                                   
    private String boardUseDiv     = "";
	private String boardName       = "";
	private String boardUrl        = "";
	private String mndtYn          = "";
	private String userId          = "";
	private String userName        = "";
	                               
	private String linkCatId       = "";
	private String linkCatNm       = "";
	private String linkCd          = "";
	private String linkOrder       = "";
	private String linkAddress     = "";
	private String linkNm          = "";
	private String popupUseGb      = "";
	private String mainDspGb       = "";
	private String psnCatId        = "";
	private String psnCd           = "";
	
	private int stndBoardSeq       = 0;
	
	private String contents        = "";
	private String imgWidth        = "";
	private String imgHeight       = "";
	private String imgSize         = "";
	private String insDts          = "";
	
	private String auth			   = "";
	
	private String apndFileTp	   = "";
	private String apndFileName	   = "";
	private String apndFileOrgn	   = "";
	private String apndFilePath	   = "";
	
	private String targeting      = "";
	private String imguseYn       = "";
	
	public String getBnrId() {
		return bnrId;
	}
	public void setBnrId(String bnrId) {
		this.bnrId = bnrId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getAltTxt() {
		return altTxt;
	}
	public void setAltTxt(String altTxt) {
		this.altTxt = altTxt;
	}
	public String getSetWidth() {
		return setWidth;
	}
	public void setSetWidth(String setWidth) {
		this.setWidth = setWidth;
	}
	public String getSetHeight() {
		return setHeight;
	}
	public void setSetHeight(String setHeight) {
		this.setHeight = setHeight;
	}
	public String getImgNameOrg() {
		return imgNameOrg;
	}
	public void setImgNameOrg(String imgNameOrg) {
		this.imgNameOrg = imgNameOrg;
	}
	public String getImgNameReal() {
		return imgNameReal;
	}
	public void setImgNameReal(String imgNameReal) {
		this.imgNameReal = imgNameReal;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
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
	public String getNotiOpnCnt() {
		return notiOpnCnt;
	}
	public void setNotiOpnCnt(String notiOpnCnt) {
		this.notiOpnCnt = notiOpnCnt;
	}
	public String getNickUseYn() {
		return nickUseYn;
	}
	public void setNickUseYn(String nickUseYn) {
		this.nickUseYn = nickUseYn;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getNotiKind() {
		return notiKind;
	}
	public void setNotiKind(String notiKind) {
		this.notiKind = notiKind;
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
	public String getUpdDttm() {
		return updDttm;
	}
	public void setUpdDttm(String updDttm) {
		this.updDttm = updDttm;
	}
	public String getMakrDispDiv() {
		return makrDispDiv;
	}
	public void setMakrDispDiv(String makrDispDiv) {
		this.makrDispDiv = makrDispDiv;
	}
	public String getNotiReadYn() {
		return notiReadYn;
	}
	public void setNotiReadYn(String notiReadYn) {
		this.notiReadYn = notiReadYn;
	}
	public String getLinkCateCode() {
		return linkCateCode;
	}
	public void setLinkCateCode(String linkCateCode) {
		this.linkCateCode = linkCateCode;
	}
	public String getLinkCode() {
		return linkCode;
	}
	public void setLinkCode(String linkCode) {
		this.linkCode = linkCode;
	}
	public String getLinkName() {
		return linkName;
	}
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	public String getTagFont() {
		return tagFont;
	}
	public void setTagFont(String tagFont) {
		this.tagFont = tagFont;
	}
	public String getBoldYn() {
		return boldYn;
	}
	public void setBoldYn(String boldYn) {
		this.boldYn = boldYn;
	}
	public String getFontSize() {
		return fontSize;
	}
	public void setFontSize(String fontSize) {
		this.fontSize = fontSize;
	}
	public String getFontColor() {
		return fontColor;
	}
	public void setFontColor(String fontColor) {
		this.fontColor = fontColor;
	}
	public String getBkgdColor() {
		return bkgdColor;
	}
	public void setBkgdColor(String bkgdColor) {
		this.bkgdColor = bkgdColor;
	}
	public String getTagDiv() {
		return tagDiv;
	}
	public void setTagDiv(String tagDiv) {
		this.tagDiv = tagDiv;
	}
	public String getBoardUseDiv() {
		return boardUseDiv;
	}
	public void setBoardUseDiv(String boardUseDiv) {
		this.boardUseDiv = boardUseDiv;
	}
	public String getBoardName() {
		return boardName;
	}
	public void setBoardName(String boardName) {
		this.boardName = boardName;
	}
	public String getBoardUrl() {
		return boardUrl;
	}
	public void setBoardUrl(String boardUrl) {
		this.boardUrl = boardUrl;
	}
	public String getMndtYn() {
		return mndtYn;
	}
	public void setMndtYn(String mndtYn) {
		this.mndtYn = mndtYn;
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
	public String getLinkCatId() {
		return linkCatId;
	}
	public void setLinkCatId(String linkCatId) {
		this.linkCatId = linkCatId;
	}
	public String getLinkCatNm() {
		return linkCatNm;
	}
	public void setLinkCatNm(String linkCatNm) {
		this.linkCatNm = linkCatNm;
	}
	public String getLinkCd() {
		return linkCd;
	}
	public void setLinkCd(String linkCd) {
		this.linkCd = linkCd;
	}
	public String getLinkOrder() {
		return linkOrder;
	}
	public void setLinkOrder(String linkOrder) {
		this.linkOrder = linkOrder;
	}
	public String getLinkAddress() {
		return linkAddress;
	}
	public void setLinkAddress(String linkAddress) {
		this.linkAddress = linkAddress;
	}
	public String getLinkNm() {
		return linkNm;
	}
	public void setLinkNm(String linkNm) {
		this.linkNm = linkNm;
	}
	public String getPopupUseGb() {
		return popupUseGb;
	}
	public void setPopupUseGb(String popupUseGb) {
		this.popupUseGb = popupUseGb;
	}
	public String getMainDspGb() {
		return mainDspGb;
	}
	public void setMainDspGb(String mainDspGb) {
		this.mainDspGb = mainDspGb;
	}
	public String getPsnCatId() {
		return psnCatId;
	}
	public void setPsnCatId(String psnCatId) {
		this.psnCatId = psnCatId;
	}
	public String getPsnCd() {
		return psnCd;
	}
	public void setPsnCd(String psnCd) {
		this.psnCd = psnCd;
	}
	public int getStndBoardSeq() {
		return stndBoardSeq;
	}
	public void setStndBoardSeq(int stndBoardSeq) {
		this.stndBoardSeq = stndBoardSeq;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}
	public String getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getImgSize() {
		return imgSize;
	}
	public void setImgSize(String imgSize) {
		this.imgSize = imgSize;
	}
	public String getInsDts() {
		return insDts;
	}
	public void setInsDts(String insDts) {
		this.insDts = insDts;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getAuth() {
		return auth;
	}
	public void setAuth(String auth) {
		this.auth = auth;
	}	
	public String getApndFileTp() {
		return apndFileTp;
	}
	public void setApndFileTp(String apndFileTp) {
		this.apndFileTp = apndFileTp;
	}
	public String getApndFileName() {
		return apndFileName;
	}
	public void setApndFileName(String apndFileName) {
		this.apndFileName = apndFileName;
	}
	public String getApndFileOrgn() {
		return apndFileOrgn;
	}
	public void setApndFileOrgn(String apndFileOrgn) {
		this.apndFileOrgn = apndFileOrgn;
	}
	public String getApndFilePath() {
		return apndFilePath;
	}
	public void setApndFilePath(String apndFilePath) {
		this.apndFilePath = apndFilePath;
	}
	public String getTargeting() {
		return targeting;
	}
	public void setTargeting(String targeting) {
		this.targeting = targeting;
	}
	public String getImguseYn() {
		return imguseYn;
	}
	public void setImguseYn(String imguseYn) {
		this.imguseYn = imguseYn;
	}
	
}