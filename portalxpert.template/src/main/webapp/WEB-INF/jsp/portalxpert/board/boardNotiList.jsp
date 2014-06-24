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
						var strNotiItem = '<tr><td><span class="btn_set ico_notice"><span>공지</span></span></td>';
						    strNotiItem += '<td class="tit"><strong><a href="javascript:fnLinkNotiItem(\''+ json[i].notiId +'\')">'+ json[i].notiTitleOrgn +'</a></strong></td>';
							strNotiItem += '<td>' + json[i].notiReadCnt + '</td>';
							strNotiItem += '<td>' + json[i].updDttm+'</td></tr>';
						 $('#notiTableList tbody').prepend(strNotiItem);
	
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


	<table id="notiTableList" summary="" class="tbl_list">
	<caption></caption>
	<colgroup>
	<col style="width:7%" />
	<col style="width:*" />
	<col style="width:11%" />
	<col style="width:11%" />
	</colgroup>
	<tbody>
	
	</tbody>
	</table>



