<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<title>설문조사 | 맞춤형복지</title>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<script type="text/javascript">
	if('${btnViewYn}' == "X"){
		alert('접근권한이 없습니다.');
		history.back();
	}

	var pageId = 'boardList';
	var makrDispDiv = '${makrDispDiv}';
	var agrmOppUseYn = '${agrmOppUseYn}';//찬성_반대_사용_여부
	var likeUseYn = '${likeUseYn}';//좋아요_사용_여부
	var superAdmin = '${superAdmin}';
	var boardId = '${boardId}';
	var notiList = '${notiList}';
	var pageIndex = '${pageIndex}';
	var pageUnit = '${pageUnit}';
	var boardName = '${boardName}';
	var favoYn = '${favoYn}';
	var isDesc = '${isDesc}';
	var sid = '${sid}';
	var boardKind = '${boardKind}';
	var btnViewYn = '${btnViewYn}';
	var host = '${host}';
	var userId = '${userId}';
	var eamAdminYn = '${eamAdminYn}';
	var nickUseYn = '${nickUseYn}';
	var orderType = '${orderType}';
	var listYn = '${listYn}';
	var boardForm = '${boardForm}';
	var searchKeyword = decodeURIComponent("${fn:replace(searchKeyword,'"', '&quot;')}");
	var searchCondition = '${searchCondition}';	
	var chkSurvey = '${chkSurvey}';
	var isNotiSurvey = false;
	var regrId = null;
	
	var calList = ${calList};
</script>
<script type="text/javascript">
var fnLoadPage = function()
{
	fnFrameReload();
};

//rePaint
var fnFrameReload = function()
{
	parent.document.getElementById("bbsFrame").height = "700px";
	parent.document.getElementById("bbsFrame").height = $(document).height()+"px"; //document.body.scrollHeight+400+"px";
};

function fn_link_page(pageNo) {
	document.listForm.pageIndex.value = pageNo;
	document.listForm.pageUnit.value = pageUnit;
	document.listForm.action = WEB_HOME+"/board210/getBoardInfoList.do?boardId="+boardId;
	document.listForm.submit();
}

var doUserInfoPop = function(_userId){
	var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+_userId;
	PortalCommon.userInfoPop(url);
};

var doDeptInfoPop = function(_deptCode){
	var url = WEB_HOME+"/person100/personDeptInfoView.do?oucode="+_deptCode;
	PortalCommon.userDeptInfoPop(url);
};

var fnSearchList = function(orderType) {
	if(orderType == "") return;
	
	pageUnit = $('#list_cnt').val();
	//pageIndex = '${pageIndex}';
	document.listForm.pageIndex.value = '1';
	document.listForm.searchCondition.value = $("#search_gubun").val();
	document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
	document.listForm.orderType.value = orderType;
	document.listForm.isDesc.value = isDesc;
	document.listForm.chkSurvey.value = $(':radio[name="chk_survey"]:checked').val();

	document.listForm.pageUnit.value = pageUnit;
	document.listForm.action = WEB_HOME+"/board210/getBoardInfoList.do?boardId="+boardId;
	document.listForm.submit();

};

//링크등록 
var linkInsert = function() {
	if($("#link_linkUrl").val() == ''){alert('url을 입력해주세요.');return;}
	if($("#link_notiTitle").val() == ''){alert('제목을 입력해주세요.');return;}
	
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '', //게시판 ID
		'linkUrl' : '',
		'notiTitle' : ''
	};

	jsonNotiObject.boardId = boardId;
	jsonNotiObject.linkUrl = $("#link_linkUrl").val();
	jsonNotiObject.notiTitle = $("#link_notiTitle").val();

	PortalCommon.getJson({
		url : WEB_HOME+"/board210/insertBbsNotiInfoForLink.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {

				window.returnValue = 'ok';
				fnSearchList('default');
			}
		}
	});
};

//게시글 삭제
var notiDelete = function(notiId) {
	if(eamAdminYn != "Y"){
		if(userId != regrId){
			alert('타인이 작성한 글을 삭제할 수 없습니다.');return;
		}
	}
	if (!confirm('삭제하시겠습니까?')) {
		return;
	}
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '', //게시판 ID
		'notiId' : []
	};

	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;
	
	/**
	 *	1. 접속유저 권한 (포털관리자 게시판 관리자)
	 * 	2. 작성자 일치여부
	 */
	PortalCommon.getJson({
		url : WEB_HOME+"/board210/deleteNotiInfo.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
				fnSearchList('default');
			}else{
				alert(data.jsonResult.message);	
			}
		}
	});
};

//게시글 읽음
var notiRead = function(notiId) {
	
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '', //게시판 ID
		'notiId' : []
	};

	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;

	PortalCommon.getJson({
		url : WEB_HOME+"/board210/updateBbsNotiEvalInfoForRead.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
				window.returnValue = 'ok';
				fnSearchList('default');
			}
		}
	});
};

//게시물 이동
var fnDoNotiMove = function(notiId) {
	
	if(isNotiSurvey){
		alert('설문형 게시글은 이동할 수 없습니다.');
		return;
	}
	
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '' 
		,'notiId' : []
	};

	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;
	
	/**
	 *	1. 접속유저 권한 (포털관리자 게시판 관리자)
	 * 	2. 작성자 일치여부
	 *	3. 게시판의 쓰기 권한
	 */
	
	PortalCommon.getJson({
		url : WEB_HOME+"/board210/getMyNotiCheckList.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
					
				if (!confirm('이동하시겠습니까?')) {
					return;
				}
				PortalCommon.popupWindowCenter(
						WEB_HOME+'/organization/categoryChartPop.do?boardForm='+boardForm+'&mode=cm_move&kind=2&type=1&admin=1&notiId='
									+ JSON.stringify(notiId), 'myBoardPop',
							'400', '450');
				
			}else{
				alert(data.jsonResult.message);
			}
		}
	});
};


//게시물 이동 관리지용 
var fnDoNotiMoveDelForAdm = function(notiId, delDiv) {
	
	if(isNotiSurvey){
		alert('설문형 게시글은 이동할 수 없습니다.');
		return;
	}	
	PortalCommon.popupWindowCenter(
			WEB_HOME+'/organization/categoryChartPop.do?boardForm='+boardForm+'&mode='+delDiv+'&kind=2&type=1&admin=1&notiId='
					+ JSON.stringify(notiId), 'myBoardPop','400', '450');
			
};


var getCheckNotiIdJsonData = function() {
	
	var jsonArray = [];
	var idx = 0;
	for ( var i = 0; i < $("[name='chk']:checked").length; i++) {
		var notiKind = $("[name='chk']:checked").eq(i).attr("notiKind");
		regrId = $("[name='chk']:checked").eq(i).attr("regrId");
		if(notiKind == '040'){//설문확인
			isNotiSurvey = true;
		}else{
			isNotiSurvey = false;
		}
		var jsonObject = {
			'id' : $("[name='chk']:checked").eq(i).val()
		};
		jsonArray[idx] = jsonObject;
		idx++;

	}
	return jsonArray;
};

var getNotiId = function(){
	var rtnId = null;
	for ( var i = 0; i < $("[name='chk']:checked").length; i++) {
		rtnId = $("[name='chk']:checked").eq(i).val();
	}
	return rtnId;
};

//링크
var fnOpenLinkUrl = function(linkUrl, _notiId){
	
	var jsonObj = {
			'notiId' : '',
			'notiEvalDiv' : ''	
		};
	
	jsonObj.notiId = _notiId;
	jsonObj.notiEvalDiv = '040';//읽음
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/insertBbsNotiEvalInfoForLink.do?format=json",
		data: {  'data' : JSON.stringify(jsonObj) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnSearchList('default');
			}
		}
 	});
	
		if(linkUrl.indexOf("http://") == -1){
			linkUrl = "http://"+linkUrl;
		}
	window.open(linkUrl);
	
};

//즐겨찾기
var fnDoFavorite = function() {
	var jsonBoardInfoObject = {
		//----게시판속성 설정------
		'boardId' : '' //게시판 ID
	};
	jsonBoardInfoObject.boardId = boardId;
	if (favoYn == 'N') {//추가
		if (confirm('즐겨찾기에 추가 하시겠습니까?')) {
			PortalCommon.getJson({
				url : WEB_HOME+"/board210/insertBbsFavorite.do?format=json",
				data : {
					'data' : JSON.stringify(jsonBoardInfoObject)
				},
				success : function(data) {
					if (data.jsonResult.success === true) {
						parent.location.reload();
					}
				}
			});
		}
	} else if (favoYn == 'Y') {
		if (confirm('즐겨찾기에 삭제 하시겠습니까?')) {
			PortalCommon.getJson({
				url : WEB_HOME+"/board210/deleteBbsFavorite.do?format=json",
				data : {
					'data' : JSON.stringify(jsonBoardInfoObject)
				},
				success : function(data) {
					if (data.jsonResult.success === true) {
						parent.location.reload();
					}
				}
			});
		}
	}
};

//상세보기
var fnGetBoardView = function(id,  pnum){
	
	document.listForm.pageUnit.value = $("#list_cnt").val();
	document.listForm.searchCondition.value = $("#search_gubun").val();
	//document.listForm.searchKeyword.value = $("#keyword").val();
	document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
	document.listForm.pageIndex.value = pageIndex;
	document.listForm.action = WEB_HOME+"/board210/getBasicBoardView.do?notiId="+id+"&boardId="+boardId+"&pnum="+pnum;
	document.listForm.submit();
};


//게시글 선택체크
var fnDoNotiSelectCheck = function(){
	if ($("[name='chk']:checked").length == 0) {
		alert('글을 선택해주세요.');
		return 0;
	}else{
		return 1;
	}
};

//게시글 삭제 이력
var fnBbsDelInfoPop = function(_notiId , delDiv){
	PortalCommon.windowPopup(WEB_HOME+'/board210/bbsDelInfoPop.do?notiId='+JSON.stringify(_notiId)+'&delDiv='+delDiv,'삭제정보','528','560');
};

function convertDate(d){
	var year = d.getFullYear().toString();
	var tempDate = d.getDate();
	var date = tempDate < 10 ? "0".concat(tempDate.toString()) : tempDate.toString();
	var tempMonth = d.getMonth() + 1;
	var month = tempMonth < 10 ? "0".concat(tempMonth.toString()) : tempMonth.toString();
	return year.concat(month).concat(date);
}

function setCalList(start, end, callback){
	alert(boardId)
	var param = {};
	param.calYmFrom= convertDate(start._d);
	param.calYmTo = convertDate(end._d);
	param.boardId = boardId;
    $.ajax({
        url: WEB_HOME+'/board210/getBoardInfoList.do?format=json',
        type: "POST",
		dataType: "json",
        data: JSON.stringify(param),
        success: function(doc) {
        	console.log(doc)
            var events = [];
            events = doc;
            callback(events);
        }
    });    	
}


////////////////////////////////onload/////////////////////////////////////////////////////////////////////	

$(document).ready(function () {//이벤트 모음 
	/*	
	if(pageUnit == "10"){
		parent.document.getElementById("bbsFrame").height = frameHeight +"px";
	}else if(pageUnit == "20"){
		parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 200 +"px";
	}else if(pageUnit == "30"){
		parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 550 +"px";
	}else if(pageUnit == "50"){
		parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 1150 +"px";
	}
	*/

	parent.document.getElementById("bbsFrame").height = "700px";
	parent.document.getElementById("bbsFrame").height = $(document).height()+"px";

	$(parent.document).scrollTop(0);

	if(listYn =="Y"){
		fnSearchList(orderType);
	}

	$('#search').click(function() {//검색
		fnSearchList('default');
	});
	$('#keyword').keypress(function(event) {
		if ( event.which == 13 ) {     
			fnSearchList('default');   
		}
	});
	
	$('#btn_write').click(function() {//글쓰기
		location.href = WEB_HOME+"/board230/board230Write.do?boardId="+boardId+"&kind=BBS&pageIndex="+pageIndex+"&pageUnit="+pageUnit;
	});
	$('#btn_link').click(function() {//링크
		$("#link_insert_pop").show();
	});
	$('#noti_read').click(function() {//읽음
		if(fnDoNotiSelectCheck() > 0){
			for ( var i = 0; i < $("[name='chk']:checked").length; i++) {
				if(boardKind == '020'){
					var readReg = $("[name='chk']:checked").eq(i).attr("readReg");
					if(readReg == 'N'){
						alert("읽음 처리할 수 없습니다.");
						return;
					}
				}
			}
			notiRead(getCheckNotiIdJsonData());	
		}
	});
	$('#noti_move').click(function() {//이동
		if($("[name='chk']:checked").length > 1){
			alert('게시글은 1건씩만 이동이 가능합니다.');
			return;
		}
		if(fnDoNotiSelectCheck() > 0){
			regrId = $("[name='chk']:checked").eq(0).attr("regrId");
			if(eamAdminYn == "Y"){
				if(regrId == userId){
					fnDoNotiMove(getCheckNotiIdJsonData());
				}else{
					fnDoNotiMoveDelForAdm(getCheckNotiIdJsonData(),'cm_move_adm');	
				}
			}else{
				fnDoNotiMove(getCheckNotiIdJsonData());
			}
		}
	});
	$('#noti_del').click(function() {//삭제
		if($("[name='chk']:checked").length > 1){
			alert('게시글은 1건씩만 삭제 가능합니다.');
			return;
		}
		if(fnDoNotiSelectCheck() > 0){
			if(eamAdminYn == "Y"){
				regrId = $("[name='chk']:checked").eq(0).attr("regrId");
				if(regrId == userId){	
					notiDelete(getCheckNotiIdJsonData());	
				}else{
					fnBbsDelInfoPop(getCheckNotiIdJsonData(),'DEL');	
				}
				
			}else{
				notiDelete(getCheckNotiIdJsonData());	
			}
		}
	});
	$('#refresh').click(function() {//새로고침
		fnSearchList('default');
	});
	$('#list_cnt').change(function() {//조회갯수
		fnSearchList('default');
		//alert($('#list_cnt').val());
	});	
	$(':radio[name="chk_survey"]').click(function() {//조회갯수
		fnSearchList('default');
		//alert($(':radio[name="chk_survey"]:checked').val());
	});
	$("#chkAll").click(
			function() {
				$(".te_center").find('input[type=checkbox]').prop('checked', this.checked);
			});
	$('#alert_close').click(function() {
		$("#alert_copy").hide();
	});
	$('#board_copy').click(
					function(event) {
						$("#alert_copy").show();
						$("#alert_copy").fadeOut(1600, "linear", "");
						
						window.clipboardData.setData(
										'Text',
										"http://"+location.host
										+ WEB_HOME+'/board100/boardFrame.do?boardId='
										+ boardId
										+ '&pageIndex=1&pageUnit=10');

					});
	$('#link_insert_close, #link_insert_cancle').click(function() {
		$("#link_insert_pop").hide();
	});
	if (favoYn == 'N') {
		$("#favoTxt").html("즐겨찾기 추가");
	} else {
		$("#favoTxt").html("즐겨찾기 삭제");
	}


	
	$('#list_cnt').val(pageUnit);
	
	if(boardForm == '040'){
		
		//달력세팅
	    $('#calendar').fullCalendar({
			header: {
				left: '',
				center: 'prev, title, next',
				right: 'today'
			},
	    	editable: false,
	    	height : 500,
	    	//events : calList,
	    	events : function(start, end, timezone, callback) {
	    		var param = {};
	    		param.calYmFrom= convertDate(start._d);
	    		param.calYmTo = convertDate(end._d);
	    		param.boardId = boardId;
	            $.ajax({
	                url: WEB_HOME+'/board210/getBoardInfoList.do?format=json',
	                type: "POST",
	        		dataType: "json",
	                data: param,
	                success: function(doc) {
	                	//console.log(doc.calList)
	                    var events = [];
	                    events = $.parseJSON(doc.calList);
	                    calList = events;
	                    callback(events);
	                }
	            });
	        },
	    	dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
	    	titleFormat : {month : 'YYYY.MM'},
	    	//lang : 'ko',
	    	loading: function(bool) {
				if (!bool){
					$(".tbl_list tbody").empty();
					for(var i=0;i<calList.length;i++){
						var tr = "<tr>"
							+ "<td>"+calList[i].oldNoticeSeq+"</td>"
							+ "<td class='tit' title='"+calList[i].notiTitleOrgn+"'><a href=\"javascript:fnGetBoardView('"+calList[i].notiId+"','"+calList[i].pnum+"');\" class='text_dot'>"+calList[i].notiTitle+"</a></td>"
							+ "<td>"+(calList[i].apndFileCnt > 0?'<a href="#"><span class="ico_fileAttch"><span class="hidden">파일첨부</span></span></a>':'')+"</td>"
							+ "<td>"+calList[i].userName+"</td>"
							+ "<td>"+calList[i].notiReadCnt+"</td>"
							+ "<td>"+calList[i].regDttm+"</td>";
						$(".tbl_list tbody").append(tr);
					}
					
					if(calList.length == 0) $(".tbl_list tbody").append('<tr><td colspan="6">검색된 데이터가 없습니다.</td></tr>');
					parent.document.getElementById("bbsFrame").height = "700px";
					parent.document.getElementById("bbsFrame").height = ($(document).height()+700)+"px";
				}
			},
			selectable: true,
			select : function(start, end, allDay) {
			},
		    eventClick: function(calEvent, jsEvent, view) {
		    	fnGetBoardView(calEvent.notiId, calEvent.pnum);
		        $(this).css('border-color', 'red');
		        //var tskYmd = convertDate(calEvent.start);
		    },
		    dayClick: function(date, allDay, jsEvent, view ) {
		    	//var tskYmd = convertDate(date);
		    },
		    viewRender : function(){
		    	parent.document.getElementById("bbsFrame").height = "700px";
				parent.document.getElementById("bbsFrame").height = ($(document).height()+700)+"px";
		    },
			eventColor: '#99ccff'
	    });
	    
	}

	
});

window.onload = fnLoadPage;	
</script>

<!-- <script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardList.js"></script> -->
</head>

<body>
<form:form commandName="boardSearchVO" action="${WEB_HOME}/board210/getBoardInfoList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	<form:hidden path="notiSeqNo" />
	<form:hidden path="orderType" />
	<form:hidden path="isDesc" />
	<form:hidden path="chkSurvey"/>
	
<div class="container">	
	<div class="header">
		<div class="h1">${boardName}</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">${boardName}</strong>
		</div>
	</div>	 
	

	<div class="rbox">
		<div class="rbox_top"></div>
		<div class="rboxInner">
			<!-- 셀렉트박스 -->
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">검색구분</label>
					<select title="" id="search_gubun">
						<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
						<option value="REGR_NAME" ${searchCondition == 'REGR_NAME' ? 'selected' : ''}>작성자</option>
						<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
					</select>
				</span>
			</span>
			<!-- //셀렉트박스 -->
			<input type="text" value="${fn:replace(searchKeyword,'"', '&quot;')}" id="keyword" class="text ml5mr10" style="width:450px" title="검색어를 입력합니다." /> 
			<button class="btn_style7_2" id="search">검색</button>
		</div>
	</div>

	<div class="btn_board_top">
		<div class="fl">
		<c:if test="${btnViewYn == 'Y'}">
		    <button class="btn_write5" type="button" id="btn_write">설문 생성</button>
		</c:if>
		</div>
		<div class="fr mt5">
			<!-- 
			<span class="selectN" style="width:100px">
				<span>
					<select id="select13" title="게시물 정렬방법" onchange="fnSearchList(this.value)">
						<option value="">선택</option>
						<option value="notiTitle" ${orderType == 'notiTitle' ? 'selected' : ''}>제목</option>
						<option value="notiReadCnt" ${orderType == 'notiReadCnt' ? 'selected' : ''}>조회수 순</option>
						<option value="regDttm" ${orderType == 'regDttm' ? 'selected' : ''}>등록일 순</option>
					</select>
				</span>
			</span>
			 -->
			<span class="radiogroup">
				<input type="radio" class="mt5" id="radio01" name="chk_survey" title="전체을 선택합니다." value="all" ${chkSurvey == 'all' ? 'checked' : ''}/>
				<label for="radio01" class="mt5">전체</label>
				<input type="radio" class="mt5" id="radio02" name="chk_survey" title="마감된 설문을 선택합니다." value="finish" ${chkSurvey == 'finish' ? 'checked' : ''}/>
				<label for="radio02" class="mt5">마감된 설문</label>	
				<input type="radio" class="mt5" id="radio03" name="chk_survey" title="진행중인 설문을 선택합니다." value="ing" ${chkSurvey == 'ing' ? 'checked' : ''}/>
				<label for="radio03" class="mt5">진행중인 설문</label>	
			</span>
			<span class="selectN" style="width:100px">
				<span>
					<label for="selectN_id1" class="hidden">게시물수</label>
					<select title="게시물수 보기" id="list_cnt">
						<option value="10">10개보기</option>
						<option value="20">20개보기</option>
						<option value="30">30개보기</option>
					</select>
				</span>
			</span>
		</div>
	</div>
	<!-- 테이블 글보기스타일 -->
	<table summary="이 표는 번호, 제목, 파일, 작성자, 조회, 등록일, 설문종료일로 구성된 설문조사 목록입니다." class="tbl_list">
	<caption>설문조사</caption>
	<colgroup>
	<col style="width:7%" />
	<col style="width:*" />
	<col style="width:8%" />
	<col style="width:10%" />
	<col style="width:10%" />
	<col style="width:11%" />
	<col style="width:13%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="col" class="f"><div class="col">번호</div></th>
		<th scope="col"><div class="col" onclick="javascript:fnSearchList('notiTitle')">제목</div></th>
		<th scope="col"><div class="col">파일</div></th>
		<th scope="col"><div class="col" onclick="javascript:fnSearchList('regrName')">작성자</div></th>
		<th scope="col"><div class="col" onclick="javascript:fnSearchList('notiReadCnt')">조회</div></th>
		<th scope="col"><div class="col" onclick="javascript:fnSearchList('regDttm')">등록일</div></th>
		<th scope="col" class="e"><div class="col" onclick="javascript:fnSearchList('notiEndDttm')">설문종료일</div></th>
	</tr>
	</thead>
	
	<tbody>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			<tr <c:if test="${result.anmtYn == 'Y'}"> class="notice"</c:if>>
			    <c:if test="${result.anmtYn == 'Y'}">
				<td><div class="ico_notice">공지</div></td>
				</c:if>
				<c:if test="${result.anmtYn != 'Y'}">
				<td>${result.oldNoticeSeq}</td>
				</c:if>
				<td class="tit" title="${result.notiTitleOrgn}"><a href="javascript:fnGetBoardView('${result.notiId}','${result.pnum}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;')} 
					<c:if test="${result.opnPrmsYn == 'Y' && result.opnCnt > 0}">
					<span class="em">[의견${result.opnCnt}]</span>
					</c:if>
					</a>
				</td>
				<td>
					<c:if test="${result.apndFileCnt > 0}">
					<a href="#"><img src="${RES_HOME}/images/ico_fileAttch.png" alt="파일첨부" /></a>
					</c:if>
				</td>
				<td>${result.userName}</td>
				<td>${result.notiReadCnt}</td>
				<td>${result.regDttm}</td>
				<td>${result.notiEndDttm}</td>
			</tr>
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="7">검색된 데이터가 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>		
	</tbody>
	</table>
	<!-- 테이블 글보기스타일 -->
	<div class="paging">
	<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
	</div>
	</div>	 
	 
</form:form>	
</body>
</html>		


<%--

<body>
<form:form commandName="boardSearchVO" action="${WEB_HOME}/board210/getBoardInfoList.do" name="listForm" method="post">
	<form:hidden path="pageUnit" value="${pageUnit}"/>
	<form:hidden path="pageIndex" />
	<form:hidden path="searchCondition" />
	<form:hidden path="searchKeyword" value=""/>
	<form:hidden path="notiSeqNo" />
	<form:hidden path="orderType" />
	<form:hidden path="isDesc" />
	
	<div>${boardName}</div>
	
	<c:if test="${boardForm == '040'}">
		<div id='calendar'></div>
	</c:if>
	
	<div>
		<select title="선택" id="search_gubun">
			<option value="NOTI_TITLE_ORGN" ${searchCondition == 'NOTI_TITLE_ORGN' ? 'selected' : ''}>제목</option>
			<option value="USER_NICK" ${searchCondition == 'USER_NICK' ? 'selected' : ''}>작성자</option>
			<option value="NOTI_CONTS" ${searchCondition == 'NOTI_CONTS' ? 'selected' : ''}>내용</option>
		</select>  
		<input type="text" id="keyword" value="${fn:replace(searchKeyword,'"', '&quot;')}"> 
		<input type="button" value="검색" id="search">
	</div>
	<div>
	    <c:if test="${btnViewYn == 'Y'}">
		<input type="button" id="btn_write" value="글쓰기">
		</c:if> 
		<select id="list_cnt">
			<option value="10">10개 보기</option>
			<option value="15">15개 보기</option>
			<option value="30">30개 보기</option>
			<option value="50">50개 보기</option>
		</select>
	</div>
	<div>
		<table>
		<tr>
			<td>번호</td>
			<td><a href="javascript:fnSearchList('notiTitle')">제목</a></td>
			<td>파일</td>
			<td><a href="javascript:fnSearchList('regrName')">작성자</a></td>
			<td><a href="javascript:fnSearchList('notiReadCnt')">조회</a></td>
			<td><a href="javascript:fnSearchList('regDttm')">등록일</a></td>
		</tr>
<c:choose>
	<c:when test="${paginationInfo.totalRecordCount > 0}">
		<c:forEach var="result" items="${notiList}" varStatus="status">	
			
		<tr>
			<td>${result.oldNoticeSeq}</td>
			<td><a href="javascript:fnGetBoardView('${result.notiId}','${result.pnum}');">${fn:replace(result.notiTitle,'@!', '&nbsp;&nbsp;')}</a></td>
			<td>
				<c:if test="${result.apndFileCnt > 0}">
				파일
				</c:if>
			</td>
			<td>${result.userName}</td>
			<td>${result.notiReadCnt}</td>
			<td>${result.regDttm}</td>
		</tr>
		
		</c:forEach>
	</c:when>
	<c:otherwise>
		<tr>
			<td colspan="6">검색된 데이터가 없습니다.</td>
		</tr>
	</c:otherwise>
</c:choose>		
		</table>
	</div>
	<div>
		<ui:pagination paginationInfo="${paginationInfo}" type="image" jsFunction="fn_link_page" />
	</div>
	
</form:form>	
</body>

 --%>	 