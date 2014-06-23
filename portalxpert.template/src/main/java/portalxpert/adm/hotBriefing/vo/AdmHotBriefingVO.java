package portalxpert.adm.hotBriefing.vo;

import portalxpert.common.vo.SearchConditionVO;

public class AdmHotBriefingVO extends SearchConditionVO 
{
	
	private static final long serialVersionUID = 1L;
	
	// hot_briefing
	private String bnrId; /*핫브리핑 ID*/
	private String title; /*제목*/
	private String linkUrl; /*링크주소*/
	private String targeting; /*타겟(팝업 or 바닥)*/
	private String contents; /*100자요약내용*/
	private String imguseYn; /*이미지 또는 100자 사용여부*/
	private String altTxt; /*ALT_텍스트*/
	private Long sort; /*순번*/
	private String useYn; /*사용여부*/
	private Long imgWidth; /*가로*/
	private Long imgHeight; /*세로*/
	private String fromDts; /*시작일*/
	private String toDts; /*종료일*/
	private String insId; /*입력ID*/
	private String insDts; /*입력일*/
	private String updId; /*수정ID*/
	private String updDts; /*수정일*/

	// 목록에서 삭제대상 체크 목록
	private String bnrCheckbox; /*체크된 항목*/
	
	// hot_briefing_file
	private String fileId; /*파일ID*/
	private String imgNameOrg; /*원본이름*/
	private String imgNameReal; /*물리적이름*/
	private Long imgSize; /*사이즈*/
	private String imgPath; /*경로*/
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
	public String getTargeting() {
		return targeting;
	}
	public void setTargeting(String targeting) {
		this.targeting = targeting;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getImguseYn() {
		return imguseYn;
	}
	public void setImguseYn(String imguseYn) {
		this.imguseYn = imguseYn;
	}
	public String getAltTxt() {
		return altTxt;
	}
	public void setAltTxt(String altTxt) {
		this.altTxt = altTxt;
	}
	public Long getSort() {
		return sort;
	}
	public void setSort(Long sort) {
		this.sort = sort;
	}
	public String getUseYn() {
		return useYn;
	}
	public void setUseYn(String useYn) {
		this.useYn = useYn;
	}
	public Long getImgWidth() {
		return imgWidth;
	}
	public void setImgWidth(Long imgWidth) {
		this.imgWidth = imgWidth;
	}
	public Long getImgHeight() {
		return imgHeight;
	}
	public void setImgHeight(Long imgHeight) {
		this.imgHeight = imgHeight;
	}
	public String getFromDts() {
		return fromDts;
	}
	public void setFromDts(String fromDts) {
		this.fromDts = fromDts;
	}
	public String getToDts() {
		return toDts;
	}
	public void setToDts(String toDts) {
		this.toDts = toDts;
	}
	public String getInsId() {
		return insId;
	}
	public void setInsId(String insId) {
		this.insId = insId;
	}
	public String getInsDts() {
		return insDts;
	}
	public void setInsDts(String insDts) {
		this.insDts = insDts;
	}
	public String getUpdId() {
		return updId;
	}
	public void setUpdId(String updId) {
		this.updId = updId;
	}
	public String getUpdDts() {
		return updDts;
	}
	public void setUpdDts(String updDts) {
		this.updDts = updDts;
	}
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
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
	public Long getImgSize() {
		return imgSize;
	}
	public void setImgSize(Long imgSize) {
		this.imgSize = imgSize;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getBnrCheckbox() {
		return bnrCheckbox;
	}
	public void setBnrCheckbox(String bnrCheckbox) {
		this.bnrCheckbox = bnrCheckbox;
	}
	
}
