package portalxpert.board.board230.vo;

import portalxpert.common.vo.SearchConditionVO;

public class BBSMovieVO extends SearchConditionVO {

	private String mvpKey;			//동영상 자료를 구분하는 키값. guid를 이용함
	private String mvpSkey;			//인코딩 설정 ID( Enjsoft내부에서 사용하는 키값
	private String formt;			//동영상 형식(format)
	private String seqNo;			//동영상 시퀀스 번호
	private String titleImgNo;		//타이틀 이미지 번호
	private String runTime;			//동영상 플레이 시간
	private String orginlFileNm;	//원본 파일 명
	private String thumbTime;		//썸네일 시간
	private String state;			//동영상 처리 상태 값 0 : 등록 1 : 인코딩 (-1 : 인코딩 실패) 2 : 업로드 (-2 : 업로드 실패) 3 : 완료
	private String mvpFileNm;		//동영상 파일명(일반화질)
	private String titleFileNm;		//대표이미지 파일명
	private String thumbFile1;		//첫번째 섬네일이 파일명 
	private String thumbFile2;		//두번째 섬네일이 파일명 
	private String thumbFile3;		//세번째 섬네일이 파일명 
	private String thumbFile4;		//네번째 섬네일이 파일명 
	private String thumbFile5;		//다섯번째 섬네일이 파일명 
	private String thumbFile6;		//여섯번째 섬네일이 파일명 
	private String rgsde;			//등록일
	private String mvpHighFileNm;	//인코딩된 동영상 파일(고화질) 
	private String[]	timeStringArr;
	private int[]		timeIntArry;
	
	public String getMvpKey() {
		return mvpKey;
	}
	public void setMvpKey(String mvpKey) {
		this.mvpKey = mvpKey;
	}
	public String getMvpSkey() {
		return mvpSkey;
	}
	public void setMvpSkey(String mvpSkey) {
		this.mvpSkey = mvpSkey;
	}
	public String getFormt() {
		return formt;
	}
	public void setFormt(String formt) {
		this.formt = formt;
	}
	public String getSeqNo() {
		return seqNo;
	}
	public void setSeqNo(String seqNo) {
		this.seqNo = seqNo;
	}
	public String getTitleImgNo() {
		return titleImgNo;
	}
	public void setTitleImgNo(String titleImgNo) {
		this.titleImgNo = titleImgNo;
	}
	public String getRunTime() {
		return runTime;
	}
	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}
	public String getOrginlFileNm() {
		return orginlFileNm;
	}
	public void setOrginlFileNm(String orginlFileNm) {
		this.orginlFileNm = orginlFileNm;
	}
	public String getThumbTime() {
		return thumbTime;
	}
	public void setThumbTime(String thumbTime) {
		this.thumbTime = thumbTime;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getMvpFileNm() {
		return mvpFileNm;
	}
	public void setMvpFileNm(String mvpFileNm) {
		this.mvpFileNm = mvpFileNm;
	}
	public String getTitleFileNm() {
		return titleFileNm;
	}
	public void setTitleFileNm(String titleFileNm) {
		this.titleFileNm = titleFileNm;
	}
	public String getThumbFile1() {
		return thumbFile1;
	}
	public void setThumbFile1(String thumbFile1) {
		this.thumbFile1 = thumbFile1;
	}
	public String getThumbFile2() {
		return thumbFile2;
	}
	public void setThumbFile2(String thumbFile2) {
		this.thumbFile2 = thumbFile2;
	}
	public String getThumbFile3() {
		return thumbFile3;
	}
	public void setThumbFile3(String thumbFile3) {
		this.thumbFile3 = thumbFile3;
	}
	public String getThumbFile4() {
		return thumbFile4;
	}
	public void setThumbFile4(String thumbFile4) {
		this.thumbFile4 = thumbFile4;
	}
	public String getThumbFile5() {
		return thumbFile5;
	}
	public void setThumbFile5(String thumbFile5) {
		this.thumbFile5 = thumbFile5;
	}
	public String getThumbFile6() {
		return thumbFile6;
	}
	public void setThumbFile6(String thumbFile6) {
		this.thumbFile6 = thumbFile6;
	}
	public String getRgsde() {
		return rgsde;
	}
	public void setRgsde(String rgsde) {
		this.rgsde = rgsde;
	}
	public String getMvpHighFileNm() {
		return mvpHighFileNm;
	}
	public void setMvpHighFileNm(String mvpHighFileNm) {
		this.mvpHighFileNm = mvpHighFileNm;
	}
	public final String[] getTimeStringArr() {
		return timeStringArr;
	}
	public final void setTimeStringArr(String[] timeStringArr) {
		this.timeStringArr = timeStringArr;
	}
	public final int[] getTimeIntArry() {
		return timeIntArry;
	}
	public final void setTimeIntArry(int[] timeIntArry) {
		this.timeIntArry = timeIntArry;
	}

}
