
	/* pagination 페이지 링크 function */
	function fn_link_page(pageNo) {
		
		//document.listForm.pageIndex.value = pageNo;
		document.listForm.pageUnit.value = pageUnit;
		document.listForm.action = WEB_HOME+"/board240/getTmpBoardInfoList.do?boardId="+boardId+"&pageIndex="+pageNo;
		document.listForm.submit();
	}

	var fnSearchList = function(orderType) {
		pageUnit = $('#list_cnt').val();
		document.listForm.searchCondition.value = $("#search_gubun").val();
		document.listForm.searchKeyword.value = $("#keyword").val();
		
		document.listForm.pageUnit.value = pageUnit;
		//document.listForm.action = WEB_HOME+"/board240/getTmpBoardInfoList.do?boardId="+boardId+"&fh="+$(window).height();
		document.listForm.action = WEB_HOME+"/board240/getTmpBoardInfoList.do?boardId="+boardId+"&fh="+$(window).height();
		document.listForm.submit();

	};
	
	var fnGetBoardView = function(id, no){
		document.listForm.notiSeqNo.value = no;
		document.listForm.action = WEB_HOME+"/board230/board230Write.do?boardId=BBS999999&kind=TMP&tmpNotiSeq="+id;
		document.listForm.submit();
		
	};
	
	var fnCheckListRemove = function()
	{
		var jsonObjectList = {
				deleteTmpNotiList : []
		};
		
		if (!confirm('삭제하시겠습니까?')) {
			return;
		}
		var obj = $(".te_center").find('input[type=checkbox]');
		for(var i=0; i <$(obj).length; i++ )
		{
			var tmp = obj[i];
			if($(tmp).is(":checked"))
			{
				if($(tmp).val() != 'on'){
					var json = {
							tmpNotiSeq : $(tmp).val()
					};				
					jsonObjectList.deleteTmpNotiList.push(json);
				}
			}
		}
		
		//alert(JSON.stringify(jsonObjectList));
		
		if (jsonObjectList.deleteTmpNotiList == 0)		{
			alert('삭제 할 자료를 선택하세요');
			return;
		}
		
		PortalCommon.getJson({
			url : WEB_HOME+"/board240/deleteTmpNotiInfo.do?format=json",
			data : {
				'data' : JSON.stringify(jsonObjectList)
			},
			success : function(data) {
				if (data.jsonResult.success === true) {

					//window.returnValue = 'ok';
					fnSearchList('default');

				}
			}
		});
		
		
	}	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////

$(document).ready(function () {
	
	var _height = $(window).height();
	
	if(fh == ""){fh = "700";}
	var _height = fh;
	
	if(parent.document.getElementById("bbsFrame")){
		if(pageUnit == "10"){
			parent.document.getElementById("bbsFrame").height = _height +"px";
		}else if(pageUnit == "15"){
			parent.document.getElementById("bbsFrame").height = _height + 100 +"px";
		}else if(pageUnit == "30"){
			parent.document.getElementById("bbsFrame").height = _height + 550 +"px";
		}else if(pageUnit == "50"){
			parent.document.getElementById("bbsFrame").height = _height + 750 +"px";
		} 		
	}
	 $('#list_cnt').change(function() {//조회갯수
			fnSearchList('default');
			//alert($('#list_cnt').val());
		});
	
	$('#search').click(function() {//검색
		fnSearchList('default');
	});
			

	$("#chkAll").click(
			function() {
				$(".te_center").find('input[type=checkbox]').prop('checked', this.checked);
			});		
	
	
	
	$('#list_cnt').val(pageUnit);
	$("#search_gubun").val(searchCondition==''?'NOTI_TITLE':searchCondition);
	$("#keyword").val(searchKeyword);

});

