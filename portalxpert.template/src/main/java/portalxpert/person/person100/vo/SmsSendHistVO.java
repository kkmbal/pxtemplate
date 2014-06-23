package portalxpert.person.person100.vo;

import portalxpert.common.vo.SearchConditionVO;

public class SmsSendHistVO extends SearchConditionVO{
	
	private int sendSeq; //발송_일련번호
	private String objTelno; //대상_전화번호
	private String sendTelno; //발송_전화번호
	private String sendDttm;//발송일시
	private String sendDttmFrom; //발송_일시시작
	private String sendDttmTo; //발송_일시끝
	private String smsConts; //SMS_내용
	private String userId; //사용자_아이디
	private String userName; //사용자_명
	private String delYn; //삭제_여부
	private String regrId; //등록자_아이디
	private String regrName; //등록자_명
	private String regDttm; //등록_일시
	private String updrId; //수정자_아이디
	private String updrName; //수정자_명
	private String updDttm; //수정_일시
	private String rnum; //번호
	public String getRnum() {
		return rnum;
	}
	public void setRnum(String rnum) {
		this.rnum = rnum;
	}
	public String getSendDttmFrom() {
		return sendDttmFrom;
	}
	public void setSendDttmFrom(String sendDttmFrom) {
		this.sendDttmFrom = sendDttmFrom;
	}
	public String getSendDttmTo() {
		return sendDttmTo;
	}
	public void setSendDttmTo(String sendDttmTo) {
		this.sendDttmTo = sendDttmTo;
	}
	public int getSendSeq() {
		return sendSeq;
	}
	public void setSendSeq(int sendSeq) {
		this.sendSeq = sendSeq;
	}
	public String getObjTelno() {
		return objTelno;
	}
	public void setObjTelno(String objTelno) {
		this.objTelno = objTelno;
	}
	public String getSendTelno() {
		return sendTelno;
	}
	public void setSendTelno(String sendTelno) {
		this.sendTelno = sendTelno;
	}
	public String getSendDttm() {
		return sendDttm;
	}
	public void setSendDttm(String sendDttm) {
		this.sendDttm = sendDttm;
	}
	public String getSmsConts() {
		return smsConts;
	}
	public void setSmsConts(String smsConts) {
		this.smsConts = smsConts;
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
	
	

}
