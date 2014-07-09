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
		document.listForm.action = WEB_HOME+"/board210/open/getBoardInfoList.do?boardId="+boardId;
		document.listForm.submit();
	}
	
	var fnSearchList = function(orderType) {
		if(orderType == "") return;
		
		pageUnit = $('#list_cnt').val();
		//pageIndex = '${pageIndex}';
		document.listForm.pageIndex.value = '1';
		document.listForm.searchCondition.value = $("#search_gubun").val();
		document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
		document.listForm.orderType.value = orderType;
		document.listForm.isDesc.value = isDesc;

		document.listForm.pageUnit.value = pageUnit;
		document.listForm.action = WEB_HOME+"/board210/open/getBoardInfoList.do?boardId="+boardId;
		document.listForm.submit();

	};

	
	
	//상세보기
	var fnGetBoardView = function(id,  pnum){
		
		document.listForm.pageUnit.value = $("#list_cnt").val();
		document.listForm.searchCondition.value = $("#search_gubun").val();
		//document.listForm.searchKeyword.value = $("#keyword").val();
		document.listForm.searchKeyword.value = $("#keyword").val().replace(/&quot;/g,"\"");
		document.listForm.pageIndex.value = pageIndex;
		document.listForm.action = WEB_HOME+"/board210/open/getBasicBoardView.do?notiId="+id+"&boardId="+boardId+"&pnum="+pnum;
		document.listForm.submit();
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
            url: WEB_HOME+'/board210/open/getBoardInfoList.do?format=json',
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
		
		
		$('#refresh').click(function() {//새로고침
			fnSearchList('default');
		});
		$('#list_cnt').change(function() {//조회갯수
			fnSearchList('default');
			//alert($('#list_cnt').val());
		});	
		


		
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
		                url: WEB_HOME+'/board210/open/getBoardInfoList.do?format=json',
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