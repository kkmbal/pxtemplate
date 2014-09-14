<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="ko" lang="ko">
<head>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/taglibs.jsp"%>
<%@ include file="/WEB-INF/jsp/portalxpert/common/inc/jsLibs.jsp"%>

<title></title>
<script type="text/javascript">
	var boardId = '${boardId}';
	var boardKind = '${boardKind}';
	var notiId = '${notiId}';
	var userId = '${userId}';//접속유저
	var pageIndex = '${pageIndex}';
	var pageUnit =  '${pageUnit}';
	var spec = '${spec}';
	var regrId = '${regId}';//게시글유저
	var notiKind = '${notiKind}';
	var boardNickUseYn = '${nickUseYn}';
	var pnum = '${pnum}';
	var opnMakrRealnameYn = null;
	var notiInfo = null;
	var notiFile = null;
	var notiOpn1 = null;
	var notiOpn2 = null;
	var notiPrevNextInfo = null;
	
	var tempWeb = '${tempWeb}';
	var pJsonList;
	var pSurveyJsonList;
	var skipInfo = new Array(0);
	var skipInfoAll = new Array(0);
	var pSurveyAnswJsonList;
</script>

<script type="text/javascript">

var fnGetBoardNotiIdInfo = function(){
	var jsonNotiObject = {
			'boardId' : '' , 
			'notiId' : ''
		};
	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;

	return JSON.stringify(jsonNotiObject);
};

//설문질문
var fnApndSurveyList = function(jsonList)
{
	$("#board_survey").empty();
	var no = 1;
	var subNo = 1;
	var grpSurveyNo = 0;
	for(var i=0;i< jsonList.length;i++)
	{	
		var strDivId = "";
		if (jsonList[i].surveyTp == '020')  //보기형식:이미지
		{
			strDivId = 'surveyImg';
		}
		
		if(jsonList[i].surveyForm == "seperate")//개별질문
		{
			if(jsonList[i].skipPermitYn=='Y')
				skipInfo.push(no+'::');
			$("#board_survey").append(
					'<li>'
					+'	<span id="question_'+jsonList[i].surveyNo+'" name="'+no+'" class="quest">질문'+no+'.</span> '				
					+'	<div id="'+strDivId+'" class="answer">'
					+'		<p>'+jsonList[i].surveyConts+'</p>'				
					+'		<div id="surveyExmpl-'+jsonList[i].surveyNo+'" name="surveyExmpl-'+jsonList[i].surveyNo+'">'
					+'		</div>'
					+'  </div>'										
					+'</li>'
				);
			no++;
		}
		else
		{				
			//그룹질문
			if(jsonList[i].skipPermitYn=='Y')
				skipInfo.push(no+'-'+subNo+'::');
			$("#board_survey").append(
					'<li>'
					+'	<span id="question_'+jsonList[i].surveyNo+'" name="'+no+'-'+subNo+'" class="quest">질문'+no+'-'+subNo+'.</span> '				
					+'	<div id="'+strDivId+'" class="answer">'
					+'		<p>'+jsonList[i].surveyConts+'</p>'				
					+'		<div id="surveyExmpl-'+jsonList[i].surveyNo+'" name="surveyExmpl-'+jsonList[i].surveyNo+'">'
					+'		</div>'
					+'  </div>'										
					+'</li>'
				);
			
			grpSurveyNo = jsonList[i].grpSurveyNo;	
			if(i+1<jsonList.length && jsonList[i+1].grpSurveyNo!=grpSurveyNo)
			{
				no++;
				subNo = 1;
			}
			else
			{
				subNo++;
			}
		}		
	}

};

//스킵사용정보 저장
var fnSetMoveExmplNo = function(pNo, moveExmplNo, exmplNo)
{
	//jsonList = pJsonList;
	//surveyJsonList = pSurveyJsonList;
	var spPNo = pNo.split('-');

	for(var h=0; h<skipInfo.length; h++)
	{
		var strSkipInfo = skipInfo[h];
		var skipInfoPNo = strSkipInfo.split(':')[0];
		var spSkipInfoPNo = skipInfoPNo.split('-');
		var spMoveExmplNo = moveExmplNo.split('-');
			
		if(skipInfoPNo==pNo)
		{						
			var unitSkipInfo = pNo+':'+moveExmplNo+':'+exmplNo;
			skipInfo.splice(h,1,unitSkipInfo);			
		}
		
		if(spSkipInfoPNo.length==1 && spPNo.length==1 && skipInfoPNo>pNo)
		{
			if(spMoveExmplNo.length==1 && moveExmplNo>skipInfoPNo)
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]>skipInfoPNo)
				skipInfo.splice(h,1,skipInfoPNo+'::');			
		}
		else if(spSkipInfoPNo.length>1 && spPNo.length==1 && spSkipInfoPNo[0]>pNo)
		{
			if(spMoveExmplNo.length==1 && moveExmplNo>spSkipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]>spSkipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]==spSkipInfoPNo[0] && spMoveExmplNo[1]>spSkipInfoPNo[1])
				skipInfo.splice(h,1,skipInfoPNo+'::');
		}
		else if(spSkipInfoPNo.length==1 && spPNo.length>1 && skipInfoPNo>pNo[0])
		{
			if(spMoveExmplNo.length==1 && moveExmplNo>skipInfoPNo)
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]>skipInfoPNo)
				skipInfo.splice(h,1,skipInfoPNo+'::');
		}
		else if(spSkipInfoPNo.length>1 && spPNo.length>1 && spSkipInfoPNo[0]>spPNo[0])
		{
			if(spMoveExmplNo.length==1 && moveExmplNo>skipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]>skipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]==spSkipInfoPNo[0] && spMoveExmplNo[1]>spSkipInfoPNo[1])
				skipInfo.splice(h,1,skipInfoPNo+'::');
		}
		else if(spSkipInfoPNo.length>1 && spPNo.length>1 && spSkipInfoPNo[0]==spPNo[0] && spSkipInfoPNo[1]>spPNo[1])
		{
			if(spMoveExmplNo.length==1 && moveExmplNo>skipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]>skipInfoPNo[0])
				skipInfo.splice(h,1,skipInfoPNo+'::');
			else if(spMoveExmplNo.length>1 && spMoveExmplNo[0]==spSkipInfoPNo[0] && spMoveExmplNo[1]>spSkipInfoPNo[1])
				skipInfo.splice(h,1,skipInfoPNo+'::');
		}
	}
	
	fnSetMoveExmplNoDetail();
};

//스킵사용시 스킵번호까지 선택못하도록 disabled 시키는 함수
var fnSetMoveExmplNoDetail = function()
{
	jsonList = pJsonList;
	surveyJsonList = pSurveyJsonList;	
	
	//초기화-disabled true 되어있는 항목들 전부 false로 변경
	for(var h = 0; h < surveyJsonList.length; h++)
	{			
		var surveyJson = surveyJsonList[h];

		for(var i =0 ; i < jsonList.length; i++)
		{
			var json = jsonList[i];	
			if(surveyJson.surveyNo == json.surveyNo)
			{																
				$("#exmpl-"+json.exmplNo).attr("disabled",false);																										
			}
		}
		if(surveyJson.inputAddYn=='Y') //직접입력추가
		{
			$("#inputAdd-"+surveyJson.surveyNo).attr("disabled",false);		
			$("#inputAddTxt-"+surveyJson.surveyNo).attr("disabled",false);		
		}
		if(surveyJson.exmplTp=='020')//주관식
		{
			$("#singleTxt-"+surveyJson.surveyNo).attr("disabled",false);		
		}
	}
	
	//저장된 스킵정보를 통해 세팅
	for(var g=0; g<skipInfo.length; g++)
	{
		var strSkipInfo = skipInfo[g];
		var pNo = strSkipInfo.split(':')[0];
		var spPNo = pNo.split('-');
		var moveExmplNo = strSkipInfo.split(':')[1];
		var spMoveExmplNo = moveExmplNo.split('-');
		
		if(moveExmplNo==null || moveExmplNo=='null' || moveExmplNo=='')
			continue;
		
		for(var h = 0; h < surveyJsonList.length; h++)
		{			
			var surveyJson = surveyJsonList[h];
			var qNo = $("#question_"+surveyJson.surveyNo).attr("name");
			var spQNo = qNo.split('-');	
			var flag = false;
			var aFlag = 'N';
			
			if(qNo=='1'||qNo=='1-1')
			{
				aFlag = 'Y';
			}
			
			if(spPNo.length==1 && spQNo.length==1)
			{
				if(qNo>pNo)
					aFlag = 'Y';
			}
			else if(spPNo.length>1 && spQNo.length==1)
			{
				if(qNo>spPNo[0])
					aFlag = 'Y';
			}
			else if(spPNo.length==1 && spQNo.length>1)
			{
				if(spQNo[0]>pNo)
					aFlag = 'Y';
			}
			else if(spPNo.length>1 && spQNo.length>1)
			{
				if(spQNo[0]>spPNo[0])
					aFlag = 'Y';
				else if(spQNo[0]==spPNo[0] && spQNo[1]>spPNo[1])
					aFlag = 'Y';
			}

			if(aFlag=='Y')
			{
			
				if(spMoveExmplNo.length==1 && spPNo.length==1 && spQNo.length==1)
				{
					if(qNo<moveExmplNo && qNo>pNo)
					{					
						flag = true;
					}
				}
				else if(spMoveExmplNo.length==1 && spPNo.length==1 && spQNo.length>1)
				{
					if(spQNo[0]<moveExmplNo && spQNo[0]>pNo)
					{
						flag = true;
					}
				}
				else if(spMoveExmplNo.length==1 && spPNo.length>1 && spQNo.length==1)
				{
					if(qNo<moveExmplNo && qNo>spPNo[0])
					{
						flag = true;
					}
				}
				else if(spMoveExmplNo.length==1 && spPNo.length>1 && spQNo.length>1)
				{
					if(spQNo[0]<moveExmplNo)
					{
						if(spQNo[0]>spPNo[0])
						{
							flag = true;
						}
						else if(spQNo[0]==spPNo[0] && spQNo[1]>spPNo[1])
						{
							flag = true;
						}
					}
				}
				else if(spMoveExmplNo.length>1 && spPNo.length==1 && spQNo.length==1)
				{
					if(qNo<spMoveExmplNo[0] && qNo>pNo)
					{
						flag = true;
					}
				}
				else if(spMoveExmplNo.length>1 && spPNo.length==1 && spQNo.length>1)
				{
					if(spQNo[0]>pNo)
					{
						if(spQNo[0]<spMoveExmplNo[0])
						{
							flag = true;
						}
						else if(spQNo[0]==spMoveExmplNo[0] && spQNo[1]<spMoveExmplNo[1])
						{
							flag = true;
						}
					}
				}
				else if(spMoveExmplNo.length>1 && spPNo.length>1 && spQNo.length==1)
				{
					if(qNo<spMoveExmplNo[0] && qNo>spPNo[0])
					{
						flag = true;
					}
				}
				else if(spMoveExmplNo.length>1 && spPNo.length>1 && spQNo.length>1)
				{
					if(spQNo[0]<spMoveExmplNo[0])
					{
						if(spQNo[0]>spPNo[0])
						{
							flag = true;
						}
						else if(spQNo[0]==spPNo[0] && spQNo[1]>spPNo[1])
						{
							flag = true;
						}
					}
					else if(spQNo[0]==spMoveExmplNo[0] && spQNo[1]<spMoveExmplNo[1])
					{
						if(spQNo[0]>spPNo[0])
						{
							flag = true;
						}
						else if(spQNo[0]==spPNo[0] && spQNo[1]>spPNo[1])
						{
							flag = true;
						}
					}
				}
				
				for(var i =0 ; i < jsonList.length; i++)
				{
					var json = jsonList[i];	
					if(surveyJson.surveyNo == json.surveyNo)
					{				
						if(flag==true)
						{
							$("#exmpl-"+json.exmplNo).attr("checked",false);
						} 			
						$("#exmpl-"+json.exmplNo).attr("disabled",flag);
					}
				}
				if(surveyJson.inputAddYn=='Y') //직접입력추가
				{
					if(flag==true)
					{
						$("#inputAdd-"+surveyJson.surveyNo).attr("checked",false);		
						$("#inputAddTxt-"+surveyJson.surveyNo).val("");
					}
					$("#inputAdd-"+surveyJson.surveyNo).attr("disabled",flag);		
					$("#inputAddTxt-"+surveyJson.surveyNo).attr("disabled",flag);		
				}
				if(surveyJson.exmplTp=='020')//주관식
				{
					if(flag==true)
					{	
						$("#singleTxt-"+surveyJson.surveyNo).val("");
					}
					$("#singleTxt-"+surveyJson.surveyNo).attr("disabled",flag);		
				}
			
			}
			
		}//surveyJsonList
	}//skipInfo
		
};	

//설문제출정보
var fnApndSurveySubJectList = function(jsonList)
{
	var json = jsonList[0];

	$("#survey_main").append(
		'<div class="thanks">'
		+'	<p>설문에 참여해주셔서 고맙습니다.</p>'
		//+'	<a id="surveySubject-'+json.surveyNo+'" onclick="javascript:fnsurveyResult(\''+json.notiId+'\')" class="btn_st02 posi_st02"><span>설문결과</span></a>'
		//+'	<a d="surveyResult-'+json.surveyNo+'" onclick="javascript:fnsurveySubject(\''+json.notiId+'\')" class="btn_st01 posi_st01"><span>설문제출</span></a>'
		+'<span class="bottom"></span>'
		+'</div>'
	);
		
};

//설문에 대한 보기추가
var fnApndSurveyExmplList = function(jsonList, surveyJsonList, surveyAnswJsonList)
{	
	var beforeSurveyNo;
	pSurveyAnswJsonList = surveyAnswJsonList;

	for(var h = 0; h < surveyJsonList.length; h++)
	{
		var surveyJson = surveyJsonList[h];		
		var no;
		
		if(beforeSurveyNo!=surveyJson.surveyNo)
		{
			beforeSurveyNo = surveyJson.surveyNo;
			no = 0;
		}
		
		var inputAddNo = 1;
		for(var i =0 ; i < jsonList.length; i++)
		{
			var json = jsonList[i];			
						
			if(surveyJson.surveyNo == json.surveyNo)
			{
				inputAddNo++;
				no++;
				var strType = "checkbox";
				if(surveyJson.multiSelPermitYn=='N')//복수선택일때 checkbox 아닐때 radio
				{
					strType = "radio"
				}
				var strChecked = "";
				if(json.answExmplNo == json.exmplNo)//선택된 답일때 체크
				{
					strChecked = "checked";
				}
				var strSkip = "";
				if(surveyJson.skipPermitYn=='Y')//스킵허용
				{
					strSkip
				}	
					
				if ($("#surveyExmpl-"+json.surveyNo).parent().attr('id') == 'surveyImg')//이미지
				{
					if(surveyJson.skipPermitYn=='N')//스킵사용 NO
					{
						$("#surveyExmpl-"+json.surveyNo).append(						
							'<div class="imgBox">'
							+'<label class="quest3"><input type="'+strType+'" title="" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" class="vTop" '+strChecked+'/>'+no+'. </label><span class="sns_img"><img id="exmplimg-'+json.exmplNo+'" src="'+tempWeb+json.imgName+'" onclick="javascript:fnSurveyImgPop(\''+tempWeb+json.imgName+'\');" style="width:100%;height:100%" alt="올린이미지"/></span>'
							+'</div>'
						);
					}
					else
					{	
						var strMove = "";
						if(json.moveExmplNo != null)
						{
							strMove = '<span class="col01">→ '+json.moveExmplNo+'번(으)로 이동</span>';
						}
						$("#surveyExmpl-"+json.surveyNo).append(
							'<div class="imgBox">'	
							+'<label class="quest3"><input type="radio" title="" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" class="vTop" onclick="javascript:fnSetMoveExmplNo(\''+pNo+'\',\''+json.moveExmplNo+'\',\''+json.exmplNo+'\')" '+strChecked+'/>'+no+'. '
							+strMove
							+'</label><span class="sns_img"><img id="exmplimg-'+json.exmplNo+'" src="'+tempWeb+json.imgName+'" onclick="javascript:fnSurveyImgPop(\''+tempWeb+json.imgName+'\')" style="width:100%;height:100%" alt="올린이미지"/></span>'
							+'</div>'
						);
						
						if(strChecked=="checked")
						{
							skipInfoAll.push(pNo+':'+json.moveExmplNo+':'+json.exmplNo);							
						}
					}
					/*
					$("#exmplimg-"+json.exmplNo).bind("load",function(){
						fnSurveyImgEffect();
					});
					*/
				}
				else
				{	
					if(surveyJson.skipPermitYn=='N')//스킵사용 NO
					{
						$("#surveyExmpl-"+json.surveyNo).append(
							'<div>'	
							+'<input type="'+strType+'" title="'+json.exmplConts+'" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" '+strChecked+' /><label for="exmpl-'+json.exmplNo+'" >'+no+'. '+json.exmplConts+'</label>'
							+'</div>'
						);		
					}
					else
					{						
						var pNo = $("#question_"+json.surveyNo).attr("name"); //스킵을 사용하고있는 보기를 선택했을때 해당 문제 번호
						
						pJsonList = jsonList;
						pSurveyJsonList = surveyJsonList;
						var strMove = "";
						if(json.moveExmplNo != null)
						{
							strMove = '<span class="col01">→ '+json.moveExmplNo+'번(으)로 이동</span>';
						}
						$("#surveyExmpl-"+json.surveyNo).append(
							'<div>'
							+'<input type="radio" title="'+json.exmplConts+'" name="answer-'+json.surveyNo+'" id="exmpl-'+json.exmplNo+'" onclick="javascript:fnSetMoveExmplNo(\''+pNo+'\',\''+json.moveExmplNo+'\',\''+json.exmplNo+'\')" '+strChecked+' /><label for="exmpl-'+json.exmplNo+'">'+no+'. '+json.exmplConts
							+strMove
							+'</label></div>'
						);
						
						if(strChecked=="checked")
						{
							skipInfoAll.push(pNo+':'+json.moveExmplNo+':'+json.exmplNo);							
						}
					}
				}
			}
		}

		if(surveyJson.inputAddYn=='Y') //직접입력추가
		{
			if(surveyAnswJsonList.length>0)
			{
				for(var i=0; i < surveyAnswJsonList.length; i++)
				{
					var surveyAnswJson = surveyAnswJsonList[i];
										
					var strValue = "";
					var strChecked = "";
					if (surveyJson.surveyNo == surveyAnswJson.surveyNo)
					{
						if(surveyAnswJson.surveyNo == surveyAnswJson.answExmplNo)
						{
							strValue = surveyAnswJson.answConts;
							strChecked = "checked";
						}
						$("#surveyExmpl-"+surveyJson.surveyNo).append(
							'<div class="directBox">'
							+'	<input type="radio" name="answer-'+surveyJson.surveyNo+'" id="inputAdd-'+surveyJson.surveyNo+'" title="직접입력" '+strChecked+' /> <label for="inputAdd-'+surveyJson.surveyNo+'" class="directInput">'+inputAddNo+'. 직접 입력 </label>'
							+'	<input type="text" id="inputAddTxt-'+surveyJson.surveyNo+'" class="text" style="width:475px" value="'+strValue+'" title="직접입력합니다." />'
							+'</div>'
						);
					}
				}
			}
			else
			{
				$("#surveyExmpl-"+surveyJson.surveyNo).append(
					'<div class="directBox">'
					+'	<input type="radio" name="answer-'+surveyJson.surveyNo+'" id="inputAdd-'+surveyJson.surveyNo+'" title="직접입력" /> <label for="inputAdd-'+surveyJson.surveyNo+'" class="directInput">'+inputAddNo+'. 직접 입력 </label>'
					+'	<input type="text" id="inputAddTxt-'+surveyJson.surveyNo+'" class="text" style="width:475px" value="" title="직접입력합니다." />'
					+'</div>'
				);
			}
		}
		if(surveyJson.exmplTp=='020')//주관식
		{
			if(surveyAnswJsonList.length>0)
			{
				for(var i=0; i < surveyAnswJsonList.length; i++)
				{
					var surveyAnswJson = surveyAnswJsonList[i];
										
					var strValue = "";
					var strChecked = "";
					if (surveyJson.surveyNo == surveyAnswJson.surveyNo)
					{
						if(surveyAnswJson.surveyNo == surveyAnswJson.answExmplNo)
						{
							strValue = surveyAnswJson.answConts;
						}
						$("#surveyExmpl-"+surveyJson.surveyNo).append(
							'<input type="text" id="singleTxt-'+surveyJson.surveyNo+'" name="singleTxt-'+surveyJson.surveyNo+'" class="text" style="width:575px" value="'+strValue+'">'
						);
					}
				}
			}
			else
			{
				$("#surveyExmpl-"+surveyJson.surveyNo).append(
					'<input type="text" id="singleTxt-'+surveyJson.surveyNo+'" name="singleTxt-'+surveyJson.surveyNo+'" class="text" style="width:575px" value="">'
				);
			}
		}
		
	}
	
	if(skipInfoAll.length>0)
	{
		for(var i=0; i<skipInfoAll.length;i++)
		{
			fnSetMoveExmplNo(skipInfoAll[i].split(':')[0],skipInfoAll[i].split(':')[1],skipInfoAll[i].split(':')[2]);
		}
	}
	
};

//설문제출
var fnsurveySubject = function(id)
{
	var surveyAnswList = [];
	
	var surveyExmpl = $("#board_survey").children().find('dd');
	
	for(var i=0; i < surveyExmpl.length; i++)
	{
		var view = surveyExmpl.eq(i);			
		var li_view = view.find('ul');
		
		var radio = li_view.find('input[type=radio]:checked').attr('id');
		if (radio == undefined)
		{
			alert('설문에 대한 보기를 선택하세요');
			return;
		}
		var jsonObject = {
				 'surveyNo' : li_view.attr('id').replace("surveyExmpl-","")
				, 'answmanId' : ''
				, 'answmanName' : ''
				, 'answExmplNo' : '0'
				, 'delYn' : 'N'
				, 'regrId' : ''
				, 'regrName' : ''
				, 'regDttm' : ''
				, 'updrId' : ''
				, 'updrName' : ''
				, 'updDttm' : ''
			};
		jsonObject.answExmplNo = radio.replace("exmpl-","");;
		surveyAnswList.push(jsonObject);
	}
	fnSurveyAnswInsert(JSON.stringify(surveyAnswList));
};

//설문 결과 저장
var fnSurveyAnswInsert = function(data){
	PortalCommon.getJson({
		url: WEB_HOME+"/person300/insertBbsNotiSurveyAnsw.do?format=json",
	    data: {'data' : data},
		success :function(data){
			if(data.jsonResult.success ===true){
				//alert('설문제출이 정상적으로 처리되었습니다.');
			}
		}
	});
};

var fnsurveyResult = function(notiId)
{
	PortalCommon.popupWindowModal(WEB_HOME+'/person300/person300SurveyRst.do?tmlnSeq=0&notiId='+notiId,'surveyPop',450,550);
};

var fnSurveyImgEffect = function()
{
   $obj = $('.imgex label');
		for( var j=0; j < $obj.length+1; j++){
		$('.imgex label img').each(function(){
			if ($(this).width() >= $(this).parents('label').width()){
				    $(this).css( {
				   		 'width': '100%',
				   		 'margin-left': "-" + $(this).width()/2 +"px",
						 'margin-top': "-" + $(this).height()/2 +"px"
					});
				} else if($(this).width() < $(this).parents('li').width()){
					$(this).css({
						'width':'auto',
						'margin-left': "-" + $(this).width()/2 +"px",
						'margin-top': "-" + $(this).height()/2 +"px"		
					});
				};
		});
	};
};

//게시글 상세정보 조회
var fnGetNotiDetailInfoView = function(){
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/getNotiDetailInfoView.do?format=json",
		data: {  'data' : fnGetBoardNotiIdInfo(), 'pnum' : pnum }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				$("#noti_reply").val("");
				
				notiInfo = $.parseJSON(data.notiInfo);
				notiFile = $.parseJSON(data.notiFile);
				notiOpn1 = $.parseJSON(data.notiOpn1);
				notiOpn2 = $.parseJSON(data.notiOpn2);
				notiPrevNextInfo = $.parseJSON(data.notiPrevNextInfo);
				
				notiKind = notiInfo[0].notiKind; 
				
				if(notiKind == '040'){//설문
					fnApndSurveyList($.parseJSON(data.surveyList));
					fnApndSurveySubJectList($.parseJSON(data.surveyList));
					fnApndSurveyExmplList($.parseJSON(data.surveyExmplList), $.parseJSON(data.surveyList), $.parseJSON(data.surveyAnswList));
				}
				fnSetDataNotiInfo(notiInfo[0], $.parseJSON(data.surveyList));
				/*
				if(notiInfo[0].notiConts != null){
					alert('11');
					$("#notiConts").html(notiInfo[0].notiConts);
					//$("#notiConts").html(replaceAll(notiInfo[0].notiConts, "\r\n","<br>"));
				}
				if($("#notiConts").html()!=""){
					alert('22');
					fnSetDataNotiInfo(notiInfo[0], $.parseJSON(data.surveyList));	
				}
				*/
			}
			window.print();
		}
 	});
};

var fnSetFrameHeight = function(addHeight){
	parent.document.getElementById("bbsFrame").height = Number($(document).height()+ addHeight )+"px";
};

//이미지형 게시글 이전글 다음글 
var fnGetImgNotiPrevNextInfo = function(){
	$("#boardPage div").remove();
	$("#boardPage").append(' <div class="page_vod">' +
			' 	<a href="#" class="btn_prev" title="이전"></a><a href="#" class="btn_next" title="다음"></a>' +
			' 	<ul class="page_list clearfix">' +
			' 		<li>' +
			' 			<div class="box_vod">' +
			' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" class="on" alt="게시판이미지"></p>' +
			' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
			' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
			' 				<p class="fo_byte">2013-03-03 17:33</p>' +
			' 			</div>' +
			' 		</li>' +
			' 		<li>' +
			' 			<div class="box_vod">' +
			' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" alt="게시판이미지"></p>' +
			' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
			' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
			' 				<p class="fo_byte">2013-03-03 17:33</p>' +
			' 			</div>' +
			' 		</li>' +
			' 		<li>' +
			' 			<div class="box_vod">' +
			' 				<p class="te_center"><img src="${RES_HOME}/images/img/@tmp_pho.jpg" alt="게시판이미지"></p>' +
			' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><a href="#">제목 두줄로 표기하며 글자수제한 걸어주세요...</a><span class="fo_replyn">[의견:38]</span></p>' +
			' 				<p class="name">홍길동<span class="hits">24,880</span></p>' +
			' 				<p class="fo_byte">2013-03-03 17:33</p>' +
			' 			</div>	' +
			' 		</li>' +
			' 	</ul>' +
			' </div>');
};

//게시글 좋아요 사용자 조회
var fnGetBbsNotiEvalInfoList = function(){
	
	if($("#linkUserListDiv").attr("value") == "N"){
		$("#linkUserListDiv").show();
		$("#linkUserListUl li").remove();
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/getBbsNotiEvalInfoList.do?format=json",
			data: {  'data' : fnGetBoardNotiIdInfo() }, 
			success :function(data){
				if(data.jsonResult.success ===true){
					var notiEval = $.parseJSON(data.notiEval);
					fnSetDataNotiEvalInfo(notiEval);
				}
			}
	 	});
		$("#linkUserListDiv").attr("value","Y");
	}else{
		$("#linkUserListDiv").hide();
		$("#linkUserListDiv").attr("value","N");
	}
}; 

var replaceAll = function(str,str1,str2){
	  return str.split(str1).join(str2);
};

var nickUseYn = null;
var userMail = null;
var fnSetDataNotiInfo = function(notiJson, surveyListJson){

	$("#boardPage ul").remove();
	
	regrId = notiJson.regrId;
	notiKind = notiJson.notiKind;
	ipOpenYn = notiJson.ipOpenYn;//의견등록시 닉네임, 아이피 공개여부 
	nickUseYn = notiJson.nickUseYn;
	opnMakrRealnameYn = notiJson.opnMakrRealnameYn;//의견작성자 실명여부 
	userMail = notiJson.mail;
	
	$("#scrapNotiTitle").val("[스크랩] "+notiJson.notiTitleOrgn);
	$("#notiNum").html(notiJson.oldNoticeSeq);
	$("#notiReadCnt").html(notiJson.notiReadCnt);//조회수
	$("#opnCnt").html(notiJson.opnCnt);
	$("#notiLikeCnt").html(notiJson.evalLikeCnt);//좋아요
	$("#notiTitle").html(notiJson.notiTitle);
	$("#notiBgnDttm").html(notiJson.notiBgnDttm);
	$("#regDttm").html(notiJson.regDttm);
	$("#notiEndDttm").html(notiJson.notiEndDttm);
	$("#notiAgrmCnt").html(notiJson.evalAgrmCnt);//찬성
	$("#notiOppCnt").html(notiJson.evalOppCnt);//반대
	$("#notiTagList").html(notiJson.notiTagLst);
	if(notiJson.moblOpenDiv == '010'){
		$("#moblOpenDiv").html("본문만 공개");	
	}else if(notiJson.moblOpenDiv == '020'){
		$("#moblOpenDiv").html("본문 + 첨부파일 공개");	
	}else if(notiJson.moblOpenDiv == '030'){
		$("#moblOpenDiv").html("공개하지 않음");	
	}
	
	if(notiJson.opnPrmsYn == 'Y'){
		$("#opnPrmsDiv").show();
		$("#replyUl").show();
	}
	
	if(notiJson.userNick != null && notiJson.nickUseYn == 'Y' ){
		$("#makrIp").html(fnGetIpUtil(notiJson.makrIp));
		$("#userName").html(notiJson.userNick);	
		$("#deptName").html("");
		$("#deptName").removeClass("read_info");
		$("#msgSpan").removeClass("read_info");
		$("#mailTo").removeClass("read_info");
		$("#mailTo").html("");
		
	}else{
		// yblee
		var json = surveyListJson[0];
		var surveyDttm = json.surveyOpenDttm+' ~ '+json.surveyClosDttm;
		$("#surveyDttm").html(surveyDttm);
		if(json.surveyRsltOpenYn=='Y')
		{
			$("#surveyRsltOpenYn").html("공개");	
		}
		else
		{
			$("#surveyRsltOpenYn").html("비공개");
		}
		
		$("#userName").html(notiJson.userName);
		$("#deptName").append('<img src="${WEB_HOME}/images/ico_room.png" alt="부서"/>'+notiJson.deptName);
		if(notiJson.mail ==null ){
			$("#mailTo").append('<img src="${WEB_HOME}/images/ico_email.png" alt="이메일" />');
		}
		else
		{
			$("#mailTo").append('<img src="${WEB_HOME}/images/ico_email.png" alt="이메일" />'+notiJson.mail);
		}
		if(notiJson.notiOpenDiv=="010")
		{
			$("#notiOpenDiv").html("전체공개");
		}
		else if(notiJson.notiOpenDiv=="020")
		{
			$("#notiOpenDiv").html("사용자지정");
		}
		else
		{
			$("#notiOpenDiv").html("부서지정");
		}
	}
	
	if(boardKind == '030'){//경조사 
		
		$("#cdlnObjrName").html(notiJson.cdlnObjrName);
		$("#cdlnDeptName").html(notiJson.cdlnDeptName);
		$("#cdlnDeptFname").html(notiJson.cdlnDeptFname);
		$("#cdlnEvntName").html(evtCdByName[notiJson.cdlnEvntCode]);
	}
	
	if(notiKind == '010'){//일반
		
	}else if(notiKind == '020'){//이미지
		$(".btn_reply").hide();	
	}
	
	//fnSetDataNotiFileInfo(notiFile);
	
	//스크랩
	//게시물 메일발송
	
};

var fnGetIpUtil = function(ip){
	var rtn = '';
	if(ip !=null && ip !="" ){
		var ipArray = ip.split(".");
		for(var i =0 ; i < ipArray.length; i++){
			if(i==2){
				ipArray[i] = "***";
			}
			rtn = rtn + ipArray[i] + ".";
		}
		rtn = rtn.substring(0, rtn.length-1);
	}
	
	return rtn;
};

//의견평가
var fnSetDataNotiEvalInfo = function(notiEvalJson){
	$('#linkUserListUl li').remove();
	for (var i=0; i < notiEvalJson.length ; i++){
	
		$('#linkUserListUl').append('<li id="'+notiEvalJson[i].evalSeq+'"><a href="javascript:fnGetUser(\''+ notiEvalJson[i].userId +'\')">'+ notiEvalJson[i].userName +'</a></li>');		
		
	}
	$('.scroll_2, .good').addClass('default-skin');
    $('.scroll_2, .good').customScrollbar();
		
};

//첨부파일
var fnSetDataNotiFileInfo = function(notiFileJson){
	
// 게시물_종류
// 010: 일반
// 020 : 이미지
// 030 : 동영상
// 031 : 동영상(소스코드)
// 040 : 설문
// 050 : 첨부
// 060 : 링크
	
	$('#spanNotiFile a').remove();
	$('#spanNotiFile span').remove();
	var apFileCnt = 0;
	for (var i=0; i < notiFileJson.length ; i++){
		if(notiFileJson[i].apndFileTp == '050'){
			$('#spanNotiFile:last').append('<a href="javascript:fnFileDown(\''+ notiFileJson[i].saveFileName +'\',\''+ notiFileJson[i].original +'\')">'+notiFileJson[i].original+'</a>'
					+' <span class="fo_gray">('+getFileSzForKb(notiFileJson[i].saveFileSize)+"kb"+')</span>');	
			
			apFileCnt++;	
		 	    
		}
	}
	  
	if(apFileCnt > 0){
		$(".btn_apnd_save").show();
	}
	
	if(notiKind == "010"){//일반
		
	}else if(notiKind == "020"){//이미지
		
		$("#notiConts").addClass("te_center");
		
		for (var i=0; i < notiFileJson.length ; i++){
			//$("#notiConts").append('<img src="${imgSvrUrl}/'+notiFileJson[i].apndFilePath+'/'+notiFileJson[i].apndFileName+'" alt="이미지파일"/>');
			if(notiFileJson[i].apndFileTp == '020'){
				fnImgApndList(notiFileJson[i]);	
			}
		}
		
	}else if(notiKind == "030"){//동영상
		
	}
	
	fnSetDataNotiOpn1(notiOpn1);
};

//이미지형 게시글 
var fnImgApndList = function(json)
{
	$("#imgNotiConts li").remove();
	$("#imgNotiConts").append(
			'<li><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.apndFileSeq+'\')" >'
			+'<img id="viewImg-'+json.apndFileSeq+'" src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'" alt="올린이미지"></a></li>'
			+'<div style="display:none;" align="center" id="dialog-'+json.apndFileSeq+'" title="'+json.apndFileOrgn+'">'
			+'<img src="${imgSvrUrl}/'+json.apndFilePath+'/'+json.apndFileName+'"></div>');
	
	$("#viewImg-"+json.apndFileSeq).load(function(){
		fnImgEffect();
	});
};

var fnImgEffect = function()
{
	$obj = $("#imgNotiConts li");		
	$obj.removeClass('one');
	$obj.removeClass('two');
	$obj.removeClass('three');
	
	var imglength = $obj.length;
	if (imglength == 1){
		$obj.addClass('one');
	} else if (imglength == 2){
		$obj.addClass('two');
	} else if (imglength >= 3){
		$obj.addClass('three');
	};
	
	if (imglength > 1)
	{
		for(var i=0; i < imglength; i++)
		{
			var img = $obj.eq(i).find('img');
			if (img.width() >= $obj.eq(i).width())
			{
				var t = Math.abs(img.height()-$obj.eq(i).height())/2+'px';
				if (img.height() > $obj.eq(i).height())
				{
					t = 0+'px';
				}
				img.css( {
			   		 'width': '100%'
				});
				
				img.css( {
					'margin-left': '0px',
					'margin-top': '0px',
			   		 'left': 0+"px",
					 'top': t
				});
				
			}
			if(img.width() < $obj.eq(i).width())
			{
				img.css({
					'width':'auto',
					'margin-left': "-" + img.width()/2 +"px",
					'margin-top': "-" + img.height()/2 +"px"		
				});	
			}
		}
	}
	
	if (imglength == 1)
	{
		var img = $obj.eq(0).find('img');
		if (img.width() >= $obj.eq(0).width())
		{
			img.css( {
		   		 'width': '100%',
		   		 'margin-left': "-" + $obj.eq(0).width()/2 +"px",
				 'margin-top': "-" + $obj.eq(0).height()/2 +"px"
			});
			
		}
		else if(img.width() < $obj.eq(0).width())
		{
			img.css({
				'width':'auto',
				'margin-left': "-" + img.width()/2 +"px",
				'margin-top': "-" + img.height()/2 +"px"		
			});	
		}
	}
};

//클릭한 이미지 미리보기
var fnImgPreview = function(id)
{
	$( "#dialog-"+id ).dialog
	(
		{      
			height: 500,      
			width: 600,
			modal: true
		}
	);
};

//의견1 조회 (본문의 의견)
var fnSetDataNotiOpn1__ = function(notiOpn1){
	
	var pName = '', ip = '',appendBtn='';
	
	$("#replyUl li").remove();
	for (var i=0; i < notiOpn1.length ; i++){

		if(opnMakrRealnameYn == 'Y'){//실명공개 
			ip = '';
			pName = notiOpn1[i].userName;
		}else{//비실명 
			ip = fnGetIpUtil(notiOpn1[i].makeIp);
			pName = '의견'+Number(notiOpn1.length - i);
		}
//			if(userId == notiOpn1[i].userId){//권한이 있는 사용자 혹은 작성자 
//				appendBtn = '<a id="btnModify_'+notiOpn1[i].notiOpnSeq+'" modiMode="N" class="fo_green" href="#" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">수정</a>'
//						   +'<a id="btnDel_'+notiOpn1[i].notiOpnSeq+'"    class="fo_green" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">삭제</a>';
//			}else{
//				appendBtn = '';
//			}
		$("#replyUl").append(
				'<li>'
				+'<div class="clearfix" id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">'	
				+'	<dl>'
				+'		<dt><span class="fo_bold fo_12px" notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</span><span class="fo_byte">'+ip+'</span><span>'+notiOpn1[i].regDttm+'</span>'
				+'			'+appendBtn+'</dt>'
				+'		<dd id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</dd>'
				+'	</dl>'
			 	+'	<div id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="clearfix reply_post2 reply_mod">'
			 	+'  	<textarea id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="5" rows="3">'+notiOpn1[i].opnConts+'</textarea>'
			 	+'		<a class="btn_reup" href="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" title="의견등록"></a>'
			 	+'	</div>'
				+'</div>'
				+'</li>');
		
		
	}
	
	fnSetDataNotiOpn2(notiOpn2);
};


//의견1 조회 (본문의 의견)
var fnSetDataNotiOpn1 = function(notiOpn1){
	
	var pName = '', ip = '',appendBtn='';
	
	$("#replyUl li").remove();
	for (var i=0; i < notiOpn1.length ; i++){		
		if(opnMakrRealnameYn == 'Y'){//실명공개
			//alert('1');
			ip = '';
			//pName = '<a href="javascript:doLikeUserInfoPop(\''+notiOpn1[i].userId+'\')">'+notiOpn1[i].userName+'</a>';
			pName = notiOpn1[i].userName;
		}else{//비실명 
			//alert('2');
			ip = fnGetIpUtil(notiOpn1[i].makeIp);
			pName = '의견'+Number(notiOpn1.length - i);
		}
		if(userId == notiOpn1[i].userId){//권한이 있는 사용자 혹은 작성자
			//alert('3');
			appendBtn = '<span><a id="btnModify_'+notiOpn1[i].notiOpnSeq+'" modiMode="N"  href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">수정</a></span>'
			+'<span><a id="btnDel_'+notiOpn1[i].notiOpnSeq+'" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'">삭제</a></span>';
		}else{
			appendBtn = '';
		}
		
		$("#replyUl").append(
		'<li id="noti_opn_'+notiOpn1[i].notiOpnSeq+'">' 
		+'	<div class="re_tit">' 
		+'		<span notiOpnSeq = '+notiOpn1[i].notiOpnSeq+' chNotiOpnCnt ='+notiOpn1[i].chNotiOpnCnt+' id="noti_opn_name_'+notiOpn1[i].notiOpnSeq+'">'+pName+'</span>'
		+'		<span>'+notiOpn1[i].regDttm+'</span>'
		+'		<span><a href="#" onclick="return false;" notiOpnSeq="'+notiOpn1[i].notiOpnSeq+'" id="btnOpn_'+notiOpn1[i].notiOpnSeq+'">의견</a></span>'
		+   	appendBtn
		+'	</div>'  
		+'	<p id="opnDd_'+notiOpn1[i].notiOpnSeq+'">'+notiOpn1[i].opnConts+'</p>' 		
		+'</li>' );
		
		$("#replyUl").append(
		'<li id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;">'
		+' 	<textarea class="textbox" id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="" rows="" style="width:610px;height:40px" title="댓글입력합니다.">'+notiOpn1[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
		+'	<span class="textbox_btns">'	
		+'		<span class="btn_st01 w01"><button onclick="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')">등록</button></span>'
		+'		<span class="btn_st02 w01"><button>취소</button></span>'
		+'	</span>'
		+'</li>');	
		
		$("#replyUl").append(
		'<li id="li_opn_'+notiOpn1[i].notiOpnSeq+'" name="li_opn_'+notiOpn1[i].notiOpnSeq+'" class="re" style="display:none;">'
		+'	<div class="re_inner">'
		+'		<ul id="ul_opn_'+notiOpn1[i].notiOpnSeq+'"></ul>'
		+'	</div>'
		+'</li>');

		$("#btnOpn_"+notiOpn1[i].notiOpnSeq).click(function(){//의견
			$("#li_opn_"+$(this).attr("notiOpnSeq")).css("display","block");

			if($("#replyUl div[name^=reOpn_]").length>0)
			{
				var reOpnName = $("#replyUl div[name^=reOpn_]").eq(0).attr("name");
				var reOpnNo = reOpnName.split("_")[1];
				//alert(reOpnNo+"/"+$(this).attr("notiOpnSeq"));
				if(reOpnNo==$(this).attr("notiOpnSeq"))
				{
					$("#replyUl div[name^=reOpn_]").eq(0).remove();
					//alert($(this).attr("notiOpnSeq"));
					if(reOpnNo==$(this).attr("notiOpnSeq"))
					{
						$("#replyUl div[name^=reOpn_]").eq(0).remove();
						var cnt = 0;
						for(var i=0; i<notiOpn2.length; i++)
						{
							//alert(notiOpn2[i].upOpnSeq+"/"+$(this).attr("notiOpnSeq"));
							if(notiOpn2[i].upOpnSeq==$(this).attr("notiOpnSeq"))
							{
								cnt++;
							}
						}
						if(cnt==0)
						{
							$("#li_opn_"+$(this).attr("notiOpnSeq")).css("display","none");
						}
					}										
					return;
				}
				else
				{					
					$("#replyUl div[name^=reOpn_]").eq(0).remove();
					var cnt = 0;
					for(var i=0; i<notiOpn2.length; i++)
					{
						if(notiOpn2[i].upOpnSeq==reOpnNo)
						{
							cnt++;
						}
					}
					if(cnt==0)
					{
						$("#li_opn_"+reOpnNo).css("display","none");
					}
				}
			}
	
			$("#ul_opn_"+$(this).attr("notiOpnSeq")).append(
			'<div name="reOpn_'+$(this).attr("notiOpnSeq")+'">'
			+' 	<textarea class="textbox" id="re_reply_'+$(this).attr("notiOpnSeq")+'" name="re_reply_'+$(this).attr("notiOpnSeq")+'" cols="" rows="" style="width:560px;height:40px" title="댓글입력합니다."></textarea>'
			+'	<span class="textbox_btns">'	
			+'		<span class="btn_st01 w01"><button onclick="javascript:fnInsertBbsNotiOpnForView('+$(this).attr("notiOpnSeq")+')">등록</button></span>'
			+'		<span class="btn_st02 w01"><button id="btnOpnCancel">취소</button></span>'
			+'	</span>'
			+'</div>');
			
			$("#btnOpnCancel").click(function(){
				$("#replyUl textarea[name^=re_reply_]").eq(0).val("");
			});
		});
		
		$("#btnModify_"+notiOpn1[i].notiOpnSeq).click(function(){//수정
			
			if($(this).attr("modiMode") == "N"){
				
				if($("#replyUl div[name^=reOpn_]").length>0)
				{
					var reOpnName = $("#replyUl div[name^=reOpn_]").eq(0).attr("name");
					var reOpnNo = reOpnName.split("_")[1];					
					if(reOpnNo==$(this).attr("notiOpnSeq"))
					{
						$("#replyUl div[name^=reOpn_]").eq(0).remove();
						for(var i=0; i<notiOpn2.length; i++)
						{
							var cnt = 0;
							for(var i=0; i<notiOpn2.length; i++)
							{
								//alert(notiOpn2[i].upOpnSeq+"/"+$(this).attr("notiOpnSeq"));
								if(notiOpn2[i].upOpnSeq==$(this).attr("notiOpnSeq"))
								{
									cnt++;
								}
							}
							if(cnt==0)
							{
								$("#li_opn_"+$(this).attr("notiOpnSeq")).css("display","none");
							}
						}
					}
				}								
				$(this).html("수정취소");
				$("#btnOpn_"+$(this).attr("notiOpnSeq")).html("");
				$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).show();
				$("#opnDd_"+$(this).attr("notiOpnSeq")).hide();
				$(this).attr("modiMode","Y");
			}else{
				$(this).html("수정");
				$("#btnOpn_"+$(this).attr("notiOpnSeq")).html("의견");
				$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).hide();
				$("#opnDd_"+$(this).attr("notiOpnSeq")).show();
				$(this).attr("modiMode","N");
			}
		});
	}
	
	$("#replyUl").append(
	'<li>'
	+'<textarea cols="" rows="" id="noti_reply" class="textbox" style="width:610px;height:40px" title="댓글입력합니다."></textarea>'
	+'<span class="textbox_btns" style="padding-left:4px;">'
	+'	<span class="btn_st01 w01"><button onclick="javascript:fnInsertBbsNotiOpnForView()">등록</button></span>'
	+'	<span class="btn_st02 w01"><button id="btnCancel">취소</button></span>'
	+'</span>'
	+'</li>'
	);
	
	$("#btnCancel").click(function(){
		$("#noti_reply").val("");
	});
		
	fnSetDataNotiOpn2(notiOpn2);
	
};

//의견2 조회 (의견의 의견)
var fnSetDataNotiOpn2 = function(notiOpn2){
	var pName = '', ip = '',appendBtn='';
	
	for (var i=0; i < notiOpn2.length ; i++){
		$("#li_opn_"+notiOpn2[i].upOpnSeq).css("display","block");
		if(opnMakrRealnameYn == 'Y'){//실명공개 
			ip = '';
			//pName = '<a href="javascript:doLikeUserInfoPop(\''+notiOpn2[i].userId+'\')" class="tit">'+notiOpn2[i].userName+'</a>';
			pName = notiOpn2[i].userName;
		}else{//비실명 
			ip = fnGetIpUtil(notiOpn2[i].makeIp);
			pName = $("#noti_opn_name_"+notiOpn2[i].upOpnSeq).html()+"-"+ notiOpn2[i].rank;
		}
		
		if(userId == notiOpn2[i].userId){
			appendBtn = '<span><a id="btnModify_'+notiOpn2[i].notiOpnSeq+'" modiMode="N" href="#" onclick="return false;" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">수정</a></span>'
					   +'<span><a id="btnDel_'+notiOpn2[i].notiOpnSeq+'" href="javascript:fnDeleteBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')" notiOpnSeq="'+notiOpn2[i].notiOpnSeq+'">삭제</a></span>';					   
		}else{
			appendBtn = '';
		}
		/*
		$("#replyUl").append(
		'<li id="opnTxtSapn_'+notiOpn1[i].notiOpnSeq+'" style="display:none;" class="reply_post2">'
		+' 	<textarea class="textbox" id="opnTxt_'+notiOpn1[i].notiOpnSeq+'" cols="" rows="" style="width:610px;height:40px" title="댓글입력합니다.">'+notiOpn1[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
		+'	<span class="textbox_btns">'	
		+'		<span class="btn_st01 w01"><button onclick="javascript:fnUpdateBbsNotiOpnForView('+notiOpn1[i].notiOpnSeq+')">등록</button></span>'
		+'		<span class="btn_st02 w01"><button>취소</button></span>'
		+'	</span>'
		+'</li>');
		*/

		$("#ul_opn_"+notiOpn2[i].upOpnSeq).append(
		'<li id="noti_opn_'+notiOpn2[i].notiOpnSeq+'">' 
		+'	<div class="re_tit">' 
		+'		<span>'+pName+'</span>'
		+'		<span>'+notiOpn2[i].regDttm+'</span>'
		+   	appendBtn
		+'	</div>' 
		+'	<p id="opnDd_'+notiOpn2[i].notiOpnSeq+'">'+notiOpn2[i].opnConts+'</p>'
		+'</li>');

		$("#ul_opn_"+notiOpn2[i].upOpnSeq).append(
		'<li id="opnTxtSapn_'+notiOpn2[i].notiOpnSeq+'" style="display:none;">'
		+'  <textarea class="textbox" id="opnTxt_'+notiOpn2[i].notiOpnSeq+'" cols="5" rows="5" style="width:560px;height:40px">'+notiOpn2[i].opnConts.replaceAll('<br/>','\n')+'</textarea>'
		+'	<span class="textbox_btns">'
		+'		<span class="btn_st01 w01"><button onclick="javascript:fnUpdateBbsNotiOpnForView('+notiOpn2[i].notiOpnSeq+')">등록</button></span>'
		+'		<span class="btn_st02 w01"><button>취소</button></span>'
		+'	</span>'			
		+'</li>' );

		$("#btnModify_"+notiOpn2[i].notiOpnSeq).click(function(){//수정
			
			if($(this).attr("modiMode") == "N"){
				$(this).html("수정취소");
				
				$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).show();
				$("#opnDd_"+$(this).attr("notiOpnSeq")).hide();
				$(this).attr("modiMode","Y");
			}else{
				$(this).html("수정");
				
				$("#opnTxtSapn_"+$(this).attr("notiOpnSeq")).hide();
				$("#opnDd_"+$(this).attr("notiOpnSeq")).show();
				$(this).attr("modiMode","N");
			}
		});
	}
};

var getFileSzForKb = function(sz) {
	if(sz > 0){
		sz = sz / 1000;
	}
	return Number(sz);
};

var myZNodes = null;
var myZTree = null;
var myBoardtreeObj = null;
var mObjHeight = null;
var fnGetPbsUserBoardInfoListForZTree = function(){
	
	$("#myBoardtreeObj li").remove();
	
	PortalCommon.getJson({
		url: WEB_HOME+"/person100/getPbsUserBoardInfoListForZTree.do?format=json&kind=1&type=1&admin=0",
		success :function(data){
			if(data.jsonResult.success ===true){
						
				var list = null;
				list = $.parseJSON(data.boardList);
				myZNodes = $.parseJSON(list);
				mObjHeight = myZNodes.length * 19;
				
				fnDrawZTree();
				fnDrawScrollBar();
				
			}
		}
 	});
};
//마이게시판설정
var setting = {
		edit: {
				enable: false,
				showRemoveBtn: false,
				showRenameBtn: false
		},	
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: { 
				enable: true
			}
		},
		check: {
			enable: false
		},
		callback: {
			onClick: zTreeOnClick 
			
		}
	};
var selectNodeId = null;
function zTreeOnClick(event, treeId, treeNode) {
	selectNodeId = treeNode.boardId;
}
//ZTREE게시판 그리기 boardGubun:1 공용게시판 2: 마이게시판 
var fnDrawZTree = function(){
	//alert('fnDrawZTree');
	$.fn.zTree.init($("#myBoardtreeObj"), setting, myZNodes);
	//myZTree = $.fn.zTree.getZTreeObj("myBoardtreeObj");
	treeReload();
};

//ZTREE 
var treeReload = function(boardGubun){
	
	myBoardtreeObj = $.fn.zTree.getZTreeObj("myBoardtreeObj");
	myBoardtreeObj.expandAll(true);	
	//fnDoScrollbakMyboard();
};

var fnDrawScrollBar = function(){
	
	$("#myBoardListDiv").css({
		'height' : '200'
		, 'visibility' : 'true'
		, 'overflow-x': 'auto'
		, 'overflow-y': 'auto'
	});
	
};


var doUserInfoPop = function(){
	PortalCommon.userInfoPop(userId);
};

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	

$(document).ready(function () {
	fnGetNotiDetailInfoView();
	
	$('.ico_smile').click(function(){
		$('.info_wrt').css({
			'position':'absolute',
			'top': $(this).offset().top+20+"px",
			'left': $(this).offset().left-0+"px"
		});
	})		
});


</script>

<!-- <script type="text/javascript" src="${RES_HOME}/js/portal/board/surveyPrintPreview.js"></script>  -->
</head>

<body>

<div class="container">

	<table class="tbl_view mt10" summary="번호, 조회수, 공개여부에 관한 정보제공">
	<caption>설문입력 목록</caption>
	<colgroup>
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:7%" />
		<col style="width:17%" />
		<col style="width:28%" />
	</colgroup>
	<thead>
	<tr>
		<th scope="row">번호</th>
		<td id="notiNum"></td>
		<th scope="row">조회수</th>
		<td id="notiReadCnt"></td>
		<th scope="row">의견</th>
		<td id="opnCnt"></td>
		<td class="last"><span class="allopen" id="notiOpenDiv">전체공개</span></td>
	</tr>
	</thead>
	
	<tbody>
	<tr>
		<td colspan="7">
			<div class="inner">
				<span id="notiTitle" class="title"></span>
				<ul>
					<li>
						<span class="tit">작성자</span>
						<span class="desc"><span id="userName"></span> <span id="deptName"></span> <span id="mailTo"></span></span>
					</li>
					<li class="half2 other2">
						<span class="tit">등록일</span>
						<span class="desc" id="regDttm"></span>
					</li>
					<li class="">
						<span class="tit">설문기간</span>
						<span class="desc" id="surveyDttm"></span>
					</li>
					<li class="half other">
						<span class="tit">결과 공개여부</span>
						<span class="desc" id="surveyRsltOpenYn">공개</span>
					</li>
				</ul>
			</div>
			<div class="intxt" id="notiConts">${notiConts}</div>
		</td>
	</tr>
	<!-- 
	<tr>
		<td colspan="7">
		<div class="rbox02 mt0">
			<span class="top"></span>
			<div class="mid">
				<div class="inquiry_top">
					<span id="spanNotiFile" class="ico_file"><strong>첨부파일</strong></span>
				</div>
			</div>
			<span class="btm"></span>
		</div>
		</td>
	</tr>
	 -->
	</tbody>
	</table>

	<div class="rbox03">
		<span class="top"></span>
		<div class="mid" id="survey_main">
			<ul id="board_survey">
			</ul>
		</div>
		<span class="btm"></span>
	</div>

</div><!-- end of container -->
	
</body>
</html>	
	 