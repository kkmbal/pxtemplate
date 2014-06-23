<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<script type="text/javascript" >

	var notiListSize = null;


	var fnLinkNotiItem = function(notiId)
	{
		//alert(notiId);
	};
		
	var fnGetNotiList = function() {
		
		
		PortalCommon.getJson({
			url : "${WEB_HOME}/board211/getBbsNotiList.do?format=json",	
			data : {
				'boardId' : boardId
			},
			success : function(data) {
				if (data.jsonResult.success == true) {
					
					
					var json = $.parseJSON(data.bbsNotiList);
						
					for (var i=0; i < json.length; i++)
					{
						var strNotiItem = '<tr><td><a href="javascript:fnLinkNotiItem(\''+ json[i].notiId +'\')"  class="fo_bold te_dot">'+ json[i].notiTitleOrgn +'</a></td>';
							strNotiItem += '<td class="te_right fo_num">' + json[i].notiReadCnt + '<span class="num_gap">|</span>'+ json[i].updDttm+'</td>';
						 $('#notiTableList tbody').append(strNotiItem);
	
					}
					
					notiListSize = data.notiListSize;
					
		
					contentsPx = contentsPx + ( 30 * parseInt(notiListSize));
					
					if( contentsPx > 650 )
						parent.parent.document.getElementById("bbsFrame").height = contentsPx;
					else parent.parent.document.getElementById("bbsFrame").height = 650;
					
				}
			}
		});
	};
	
	
	fnGetNotiList();


</script>
공지사항
		<table id="notiTableList" summary="공지사항 리스트">
			<colgroup>
				<col>
				<col width="20%">
			</colgroup>
			<tbody>

			</tbody>
		</table>
