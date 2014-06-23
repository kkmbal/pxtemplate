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
		pageUnit = $('#list_cnt').val();
		//pageIndex = '${pageIndex}';
		document.listForm.pageIndex.value = '1';
		document.listForm.searchCondition.value = $("#search_gubun").val();
		document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
		document.listForm.orderType.value = orderType;
		document.listForm.isDesc.value = isDesc;

		document.listForm.pageUnit.value = pageUnit;
		document.listForm.action = WEB_HOME+"/board210/getBoardInfoList.do?boardId="+boardId+"&fh="+frameHeight;
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
	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	
	
	$(document).ready(function () {//이벤트 모음 
		
		if(pageUnit == "10"){
			parent.document.getElementById("bbsFrame").height = frameHeight +"px";
		}else if(pageUnit == "15"){
			parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 100 +"px";
		}else if(pageUnit == "30"){
			parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 550 +"px";
		}else if(pageUnit == "50"){
			parent.document.getElementById("bbsFrame").height = Number(frameHeight) + 1150 +"px";
		}
	
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
			location.href = WEB_HOME+"/board230/board230Write.do?boardId="+ boardId+"&kind=BBS&pageIndex="+pageIndex+"&pageUnit="+pageUnit;
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
		    	events : calList,
		    	/*
		    	events : function(start, end, callback) {
		    		var param = {};
		    		param.tskStYmd = convertDate(start);
		    		param.tskEdYmd = convertDate(end);

		            $.ajax({
		                url: WEB_HOME+'/board210/getCalBoardList.do?format=json',
		                type: "POST",
		        		dataType: "json",
		                data: "boardId="+boardId,
		                success: function(doc) {
		                    var events = [];
		                    events = doc;
		                    callback(events);
		                }
		            });
		        },
		    		 */
		    	dayNamesShort : ['일', '월', '화', '수', '목', '금', '토'],
		    	titleFormat : {month : 'YYYY.MM'},
		    	//lang : 'ko',
		    	loading: function(bool) {
					if (bool) $('#loading').show();
					else $('#loading').hide();
				},
				selectable: true,
				select : function(start, end, allDay) {
				},
			    eventClick: function(calEvent, jsEvent, view) {
			    	fnGetBoardView(calEvent.notiId, calEvent.pnum);
			        //$(this).css('border-color', 'red');
			        //var tskYmd = convertDate(calEvent.start);
			        //fnDhxPopup('${pageContext.request.contextPath}/itpm2/hr/effortDayInfoPop?id='+calEvent.id+'&tskYmd='+tskYmd, "일일업무등록", 554, 340);
			    },
			    dayClick: function(date, allDay, jsEvent, view ) {
			    	//var tskYmd = convertDate(date);
			    	//fnDhxPopup('${pageContext.request.contextPath}/itpm2/hr/effortDayInfoPop?id=&tskYmd='+tskYmd, "일일업무등록", 554, 340);
			    },
				eventColor: '#eaf2f6'
		    });			
		}


		
	});