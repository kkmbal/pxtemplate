package portalxpert.common.config;

public enum Constant {

	BOARD_KIND_010("010"), //일반 
	BOARD_KIND_020("020"), //폐쇄 
	BOARD_KIND_030("030"), //경조사
	BOARD_KIND_110("110"), //설문조사
	BOARD_KIND_120("120"), //제도안내
	//BOARD_KIND_130("130"), //QNA
	//BOARD_KIND_140("140"), //FAQ
	//BOARD_KIND_150("150"), //자료실

	BOARD_FORM_010("010"), //리스트
	BOARD_FORM_020("020"), //SNS
	BOARD_FORM_030("030"), //콘텐츠
	BOARD_FORM_040("040"), //달력

	BOARD_FORM_SPEC_010("010"), //이미지
	BOARD_FORM_SPEC_020("020"), //동영상
	BOARD_FORM_SPEC_030("030"), //콘텐츠
	
	NOTI_KIND_010("010"), //일반
	NOTI_KIND_020("020"), //이미지
	NOTI_KIND_030("030"), //동영상
	NOTI_KIND_040("040"), //설문
	NOTI_KIND_050("050"), //첨부
	NOTI_KIND_060("060"), //링크
	
	E000001("portalxpert.service.error"),   //기본에러코드
	
	BOARD_ROLE_USER("public"), //공개게시판 사용자ID
	BOARD_OPEN_PATH("open"), // 공개게시판 URL Path
	
	ROLE_SUPER("SYSTEM") //슈퍼유저권한
	;

	
	private String val;
	private Constant(String val){
		this.val = val;
	}
	public String getVal(){
		return this.val;
	}
}
