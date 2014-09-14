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

	var nickUseYn = null;
	var userMail = null;
	var agrmOppYn = null;
	var opnPrmsYn = null;
	var notiKind = null;
	var regrId = null;//게시글등록자
	var opnMakrRealnameYn = null;
	var opnWrteDiv = '${opnWrteDiv}';
	var admYn = '${admYn}';
	var eamAdminYn = '${eamAdminYn}';
	var superAdmin = '${superAdmin}';
	var pageId = 'boardView';
	var btnViewYn = '${btnViewYn}';
	var boardId = '${boardId}';
	var boardKind = '${boardKind}';
	var notiId = '${notiId}';
	var userId = '${userId}';//접속유저
	var pageIndex = '${pageIndex}';
	var pageUnit =  '${pageUnit}';
	var notiSeqNo = '${notiSeqNo}';//게시글 번호
	var agrmOppUseYn = '${agrmOppUseYn}';
	var boardNickUseYn = '${nickUseYn}';
	var pnum = '${pnum}';
	var prev_pnum = '${prev_pnum}';
	var next_pnum = '${next_pnum}';
	var imgRealDir = '${imgRealDir}';
	var movDir = '${movDir}';
	var thumbnailFile = '${thumbnailFile}';
	var imgSvrUrl = '${imgSvrUrl}';
	var replyWrteDiv = '${replyWrteDiv}';//답변쓰기 구분
	var boardExplUseYn = '${boardExplUseYn}';
	var boardExpl = '${boardExpl}';
	var boardForm = '${boardForm}';
	var boardFormSpec = '${boardFormSpec}';
	var searchCondition = '${searchCondition}';
	//var searchKeyword = '${searchKeyword}';
	var searchKeyword = decodeURIComponent("${fn:replace(searchKeyword,'"', '&quot;')}");
	var chkSurvey = '${chkSurvey}';
	var regDttmFrom = '${regDttmFrom}';
	var regDttmTo = '${regDttmTo}';
	var listYn = 'N';
	var orderType = '${orderType}';
	var isDesc = '${isDesc}';
	var notiEmailSendYn = '${notiEmailSendYn}';
	var notiInfo = null;
	var notiFile = null;
	var notiOpn1 = null;
	var notiOpn2 = null;
	var notiPrevNextInfo = null;
	var notiMov = null;
	var notiPrevNextImgMovInfo = null;	
	
	var goIdx = 0;
	var prev_last = false;
	var next_last = false;
	
	var maxNum = 0;
	
	var myZNodes = null;
	var myZTree = null;
	var myBoardtreeObj = null;
	var mObjHeight = null;
	

	var selectNodeId = null;
	var selectBoardForm = null;
	
	var isNotiSurvey = false;
	
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
	var cnt=0;
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
					+'		<p id="p_'+jsonList[i].surveyNo+'" name="'+jsonList[i].surveyConts+'">'+jsonList[i].surveyConts+'</p>'				
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
					+'		<p id="p_'+jsonList[i].surveyNo+'" name="'+jsonList[i].surveyConts+'">'+jsonList[i].surveyConts+'</p>'				
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

//설문에 대한 보기추가
var fnApndSurveyExmplList = function(jsonList, surveyJsonList, surveyRsltJsonList, totalMemberCnt)
{	
	var beforeSurveyNo;

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
			
			var eCnt = 0;
			
			for(var j = 0; j < surveyRsltJsonList.length; j++)
			{	
				if(surveyJson.surveyNo == surveyRsltJsonList[j].surveyNo && json.exmplNo == surveyRsltJsonList[j].answExmplNo)
				{
					eCnt = surveyRsltJsonList[j].eCnt;
				}
			}
			
			if(surveyJson.surveyNo == json.surveyNo)
			{
				inputAddNo++;
				no++;	
					
				if ($("#surveyExmpl-"+json.surveyNo).parent().attr('id') == 'surveyImg')//이미지
				{
					if(surveyJson.skipPermitYn=='N')//스킵사용 NO
					{
						$("#surveyExmpl-"+json.surveyNo).append(						
							'<div class="imgBox num_tot">'
							+'	<label class="quest3">'+no+'. </label><span class="sns_img"><a href="#" style="cursor:pointer;" onclick="javascript:fnSurveyImgPop(\''+tempWeb+json.imgName+'\');"><img id="exmplimg-'+json.exmplNo+'" src="'+tempWeb+json.imgName+'" style="width:100%;height:100%;" alt="올린이미지"/></a></span>'
							+'	<span class="studyNum">'
							+'		<span class="numWrap"><strong>'+eCnt+'</strong> / '+totalMemberCnt+'</span>'
							+'	</span>'
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
							'<div class="imgBox num_tot">'	
							+'	<label class="quest3">'+no+'. '
							+strMove
							+'	</label><span class="sns_img"><a href="#" style="cursor:pointer;" onclick="javascript:fnSurveyImgPop(\''+tempWeb+json.imgName+'\');"><img id="exmplimg-'+json.exmplNo+'" src="'+tempWeb+json.imgName+'" style="width:100%;height:100%;" alt="올린이미지"/></a></span>'
							+'	<span class="studyNum">'
							+'		<span class="numWrap"><strong>'+eCnt+'</strong> / '+totalMemberCnt+'</span>'
							+'	</span>'
							+'</div>'
						);
					}
				}
				else
				{	
					if(surveyJson.skipPermitYn=='N')//스킵사용 NO
					{
						$("#surveyExmpl-"+json.surveyNo).append(
							'<div class="num_tot">'	
							+no+'. '+json.exmplConts+'</label>'
							+'	<span class="studyNum">'
							+'		<span class="numWrap"><strong>'+eCnt+'</strong> / '+totalMemberCnt+'</span>'
							+'	</span>'
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
							'<div class="num_tot">'
							+no+'. '+json.exmplConts
							+strMove
							+'	<span class="studyNum">'
							+'		<span class="numWrap"><strong>'+eCnt+'</strong> / '+totalMemberCnt+'</span>'
							+'	</span>'
							+'</div>'
						);						
					}
				}
			}
		}

		if(surveyJson.inputAddYn=='Y') //직접입력추가
		{
			var iCnt = 0;
			
			for(var j = 0; j < surveyRsltJsonList.length; j++)
			{	
				if(surveyJson.surveyNo == surveyRsltJsonList[j].surveyNo && surveyJson.surveyNo == surveyRsltJsonList[j].answExmplNo)
				{
					iCnt = surveyRsltJsonList[j].iCnt;
				}
			}

			$("#surveyExmpl-"+surveyJson.surveyNo).append(
				'<div class="directBox num_tot">'
				+'	<label for="inputAdd-'+surveyJson.surveyNo+'" class="directInput">'+inputAddNo+'. 직접 입력 </label>'
				+'	<span class="studyNum">'
				+'		<span class="numWrap"><strong>'+iCnt+'</strong> / '+totalMemberCnt+'</span>'
				+'		<a href="#" id="inputAddButton" class="btn_style1_4" onclick="javascript:fnExcelDown(\'I\',\''+surveyJson.surveyNo+'\',\''+iCnt+'\',\''+inputAddNo+'\');">엑셀받기</a>'
				+'	</span>'
				+'</div>'
			);
			
		}
		if(surveyJson.exmplTp=='020')//주관식
		{			
			var sCnt = 0;
			
			for(var j = 0; j < surveyRsltJsonList.length; j++)
			{	
				if(surveyJson.surveyNo == surveyRsltJsonList[j].surveyNo && surveyJson.surveyNo == surveyRsltJsonList[j].answExmplNo)
				{
					sCnt = surveyRsltJsonList[j].sCnt;
				}
			}
			$("#surveyExmpl-"+surveyJson.surveyNo).append(
				'<div class="num_tot">주관식 설문 입력 : '
				+'	<span class="studyNum">'
				+'		<span class="numWrap"><strong>'+sCnt+'</strong> / '+totalMemberCnt+'</span>'
				+'		<a href="#" id="singleTxtButton" class="btn_style1_4" onclick="javascript:fnExcelDown(\'S\',\''+surveyJson.surveyNo+'\',\''+iCnt+'\',\'\');">엑셀받기</a>'
				+'	</span>'
				+'</div>'
			);			
		}
		
	}
	
};

var fnExcelDown = function(divn, surveyNo, cnt, exmplNo)
{
	//alert(divn+"/"+surveyNo+"/"+cnt);	
	var jsonExcelObject = {
			'divn' : '' , 
			'surveyNo' : 0,
			'cnt' : '',
			'exmplNo' : '',
			'surveyTitle' : '',
			'questionConts' : ''
		};
	jsonExcelObject.divn = divn;
	jsonExcelObject.surveyNo = surveyNo;
	jsonExcelObject.cnt = cnt;
	jsonExcelObject.exmplNo = exmplNo;
	jsonExcelObject.surveyTitle = $('#notiTitle').text();
	jsonExcelObject.questionConts = $('#question_'+surveyNo).attr("name")+'. '+$('#p_'+surveyNo).attr("name");

	var url =WEB_HOME+"/board210/surveyRsltExcelDown.do?data="+encodeURI(JSON.stringify(jsonExcelObject),"UTF-8");
	document.dummy.location.href = url;
};

//이미지 팝업
var fnSurveyImgPop = function(imgPath)
{
	var img1 = new Image();
	img1.src = imgPath;
	
	var width = 0;
	var height = 0;
	var option = "";
	var pop;
	if(img1.width!=0 && img1.height!=0)
	{
		width = img1.width;
		height = img1.height;
		option = "width="+width+",height="+height;
		pop = window.open(imgPath,"",option);
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
	if($(".thanks").attr("id")!="thanks")
	{
		$("#survey_main").append(
			'<div id="thanks" class="thanks">'
			+'	<p>설문에 참여해주셔서 고맙습니다.</p>'
			+'	<a id="surveySubject-'+json.surveyNo+'" onclick="javascript:fnsurveyResult(\''+json.notiId+'\')" class="btn_st02 posi_st02"><span>설문결과</span></a>'
			+'	<a d="surveyResult-'+json.surveyNo+'" onclick="javascript:fnsurveySubject(\''+json.notiId+'\')" class="btn_st01 posi_st01"><span>설문제출</span></a>'
			+'<span class="bottom"></span>'
			+'</div>'
		
			/*
		'	<p class="fo_byte"><span class="fo_bold">설문 마감일시</span>'+json.surveyClosDttm+'</p>'
		+'	<div class="btn_area">'
		+'		<div class="fl_left">'
		+'			<a id="surveySubject-'+json.surveyNo+'" style="cursor:pointer;" onclick="javascript:fnsurveySubject(\''+json.notiId+'\')" class="btn_basic ">'
		+'				<span class="btn_text fo_bold">설문제출</span>'
		+'			</a>'
		+'			<a id="surveyResult-'+json.surveyNo+'" style="cursor:pointer;" onclick="javascript:fnsurveyResult(\''+json.notiId+'\')" class="btn_basic ">'
		+'				<span class="btn_text">설문결과</span>'
		+'			</a>'
		// +'			<a href="#" class="btn_basic">'
		//+'				<span class="btn_text">출력</span>'
		//+'			</a>' 
		+'		</div>'
		+'	</div>'	
		*/	
		);
	}
		
};



//설문제출
var fnsurveySubject = function(id)
{
	if(pSurveyAnswJsonList.length>0)
	{
		alert("이미 처리된 자료 입니다.");
		return;
	}
	
	var surveyAnswList = [];
	
	var surveyExmpl = $("#board_survey div[name^=surveyExmpl-]");

	for(var i=0; i < surveyExmpl.length; i++)
	{
		var view = surveyExmpl.eq(i);			

		var obj;
		var radio = view.find('input[type=radio]');
		var checkbox = view.find('input[type=checkbox]');
		//var checkbox = view.find('input[type=checkbox]:checked').attr('id');
		var checkbox;
		var singleTxt;//주관식
		
		if(radio.length>0)//라디오 객관식
		{
			var radioChk = view.find('input[type=radio]:checked');
			var radioChkId = view.find('input[type=radio]:checked').attr('id');
			if(radioChkId == undefined && !radio.eq(0).is(":disabled"))
			{
				alert('설문에 대한 보기를 선택하세요');
				return;
			}
			
			if(radioChk.length>0 && radioChkId.split('-')[0]=="inputAdd")//직접입력을 선택했을 경우
			{
				var inputAddTxt = $('#inputAddTxt-'+radioChkId.replace("inputAdd-","")).val();
				if(inputAddTxt=='')
				{
					alert('직접 입력 내용을 입력하세요');
					$('#inputAddTxt-'+radioChkId.replace("inputAdd-","")).focus();
					return;
				}
				
				var jsonObject = {
						 'surveyNo' : view.attr('id').replace("surveyExmpl-","")
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
						, 'answConts' : ''
					};
				jsonObject.answExmplNo = radioChkId.replace("inputAdd-","");
				jsonObject.answConts = inputAddTxt;
				surveyAnswList.push(jsonObject);
			}
			else
			{
				var jsonObject = {
						 'surveyNo' : view.attr('id').replace("surveyExmpl-","")
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
				 
				if(radioChkId != undefined)
				{
					jsonObject.answExmplNo = radioChkId.replace("exmpl-","");
				}
				surveyAnswList.push(jsonObject);
			}
		}
		else if(checkbox.length>0)
		{			
			var checkboxChk = view.find('input[type=checkbox]:checked');
			if(checkboxChk.attr('id') == undefined)
			{
				if(!checkbox.eq(0).is(":disabled"))
				{
					alert('설문에 대한 보기를 선택하세요');
					return;
				}
			}

			//복수선택가능 checkbox 확인
			for(var j=0; j<checkboxChk.length; j++)
			{
				if(checkboxChk.eq(j).is(":checked")==true)
				{
					var jsonObject = {
							 'surveyNo' : view.attr('id').replace("surveyExmpl-","")
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
					if(checkboxChk.eq(j).attr('id')!= undefined)
					{
						jsonObject.answExmplNo = checkboxChk.eq(j).attr('id').replace("exmpl-","");
					}
					surveyAnswList.push(jsonObject);
				}
			}
			
		}
		else
		{
			singleTxt = view.find('input[type=text]');
			
			if(singleTxt.val()=='')
			{
				if(!singleTxt.is(":disabled"))
				{
					alert('주관식 내용을 입력하세요');
					singleTxt.focus();
					return;
				}
			}	
			
			var jsonObject = {
					 'surveyNo' : view.attr('id').replace("surveyExmpl-","")
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
					, 'answConts' : ''
				};
			if(!singleTxt.is(":disabled"))
			{
				jsonObject.answExmplNo = singleTxt.attr('id').replace("singleTxt-","");
				jsonObject.answConts = singleTxt.val();
			}			
			surveyAnswList.push(jsonObject);
		}
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
   $obj = $('.imgBox span');
		for( var j=0; j < $obj.length+1; j++){
		$('.imgBox span img').each(function(){
			if ($(this).width() >= $(this).parents('span').width()){
			    $(this).css( {
			   		 'width': '100%',
			   		 'margin-left': "-" + $(this).width()/2 +"px",
					 'margin-top': "-" + $(this).height()/2 +"px"
				});
			} else if($(this).width() < $(this).parents('div').width()){
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
		url: WEB_HOME+"/board210/getSurveyRsltInfoView.do?format=json",
		data: {  'data' : fnGetBoardNotiIdInfo(), 'pnum' : pnum }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				$("#noti_reply").val("");
				//$("#msgObj_MYID").val(data.regrIdEncrypt);//메선저연동
				notiInfo = $.parseJSON(data.notiInfo);//게식
				notiFile = $.parseJSON(data.notiFile);
				notiOpn1 = $.parseJSON(data.notiOpn1);
				notiOpn2 = $.parseJSON(data.notiOpn2);
				notiPrevNextInfo = $.parseJSON(data.notiPrevNextInfo);
				notiKind = notiInfo[0].notiKind;
				
				if(data.notiPrevNextImgMovInfo){
					notiPrevNextImgMovInfo = $.parseJSON(data.notiPrevNextImgMovInfo);
				}
				
				if(notiKind == '040'){//설문
					$("#surveyRsltMemberCnt").text(data.mCnt);
					isNotiSurvey = true;
					fnApndSurveyList($.parseJSON(data.surveyList));
					//fnApndSurveySubJectList($.parseJSON(data.surveyList));
					fnApndSurveyExmplList($.parseJSON(data.surveyExmplList), $.parseJSON(data.surveyList), $.parseJSON(data.surveyRsltAnswCntList), data.mCnt);
				}else if(notiKind == '030'){
					
					notiMov = $.parseJSON(data.movList);
				}
				
				if(notiKind == '040'){
					var cnt = notiOpn1.length;
					fnSetDataNotiInfo(notiInfo[0], cnt, $.parseJSON(data.surveyList));
				}
				else
				{
					fnSetDataNotiInfo(notiInfo[0], 0);
				}
			}
		}
 	});
};

var fnSetFrameHeight = function(addHeight){
	parent.document.getElementById("bbsFrame").height = "700px";
	parent.document.getElementById("bbsFrame").height = Number($(document).height()+ addHeight )+"px";
	//parent.document.getElementById("bbsFrame").height = (document.body.scrollHeight+ addHeight)+"px";
	
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
				
			}
		}
 	});
	
		if(linkUrl.indexOf("http://") == -1){
			linkUrl = "http://"+linkUrl;
		}
	window.open(linkUrl);
	
};

//일반 게시글 이전글 다음글 
var fnGetPrevNextNotiInfo = function(notiJson){
	$("#boardPage li").remove();
	if(notiJson !=null){

		if(notiJson.prevNotiId != null){
			if(notiJson.prevNotiKind =='060'){
				$("#boardPage").append('<li><span class="arr prev">이전 글</span><span class="title" id="prevNotiTitle"><a href="javascript:fnOpenLinkUrl(\''+notiJson.prevLinkUrl+'\' , \''+notiJson.prevNotiId+'\')" id="prevNotiId">'+notiJson.prevNotiTitle+'</a></span></li>');
			}else{	
				$("#boardPage").append('<li><span class="arr prev">이전 글</span><span class="title" id="prevNotiTitle"><a href="javascript:fnGetBoardView(\''+notiJson.prevNotiId+'\' , \''+prev_pnum+'\')" id="prevNotiId">'+notiJson.prevNotiTitle+'</a></span></li>');
			}
			
		}else{
			$("#boardPage").append('<li><span class="arr prev">이전 글</span><span class="title" id="prevNotiTitle">처음 게시물 입니다.</span></li>');
		}
		if(notiJson.nextNotiId != null){
			if(notiJson.nextNotiKind == '060'){				
				$("#boardPage").append('<li><span class="arr next">다음 글</span><span class="title" id="nextNotiTitle"><a href="javascript:fnOpenLinkUrl(\''+notiJson.nextLinkUrl+'\' , \''+notiJson.nextNotiId+'\')" id="nextNotiId">'+notiJson.nextNotiTitle+'</a></span></li>');
			}else{
				$("#boardPage").append('<li><span class="arr next">다음 글</span><span class="title" id="nextNotiTitle"><a href="javascript:fnGetBoardView(\''+notiJson.nextNotiId+'\' , \''+next_pnum+'\')" id="nextNotiId">'+notiJson.nextNotiTitle+'</a></span></li>');
			}
			
		}else{
			$("#boardPage").append('<li><span class="arr next">다음 글</span><span class="title" id="nextNotiTitle">마지막 게시물 입니다.</span></li>');
		}
	}
	
	fnSetFrameHeight(230);
};


//이미지형 게시글 이전글 다음글 
var fnGetImgNotiPrevNextInfo = function(param){
	
	if(notiPrevNextImgMovInfo){
		if(notiPrevNextImgMovInfo.length < 1) return ;

		$("#boardPage div").remove();
		$("#boardPage").append('<div class="page_vod"></div>');
		if(prev_last){
			$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(1)" class="btn_next" title="다음" id="btn_next_img"></a>');
		}else if(next_last){
			$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(-1)" class="btn_prev" title="이전" id="btn_prev_img"></a>');
		}else{
			$("#boardPage div").append('<a href="javascript:fnNotiPrevNextImgMovInfoView(-1)" class="btn_prev" title="이전" id="btn_prev_img"></a><a href="javascript:fnNotiPrevNextImgMovInfoView(1)" class="btn_next" title="다음" id="btn_next_img"></a>');
		}
		$("#boardPage div").append('<ul class="page_list clearfix" id="board_mov">');
		
		
		for(var i=0; i<notiPrevNextImgMovInfo.length; i++){
			if(boardFormSpec == '010'){
				$("#board_mov").append('<li class="ver_top">' +
						' 			<div class="box_vod">' +
						' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="'+imgSvrUrl+'/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판이미지"></a></p>' +
						' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><span class="te_dot"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')">'+notiPrevNextImgMovInfo[i].notiTitle+'</a></span><span class="fo_replyn">[의견:'+notiPrevNextImgMovInfo[i].opnCnt+']</span></p>' +
						' 				<p class="name">'+notiPrevNextImgMovInfo[i].updrName+'<span class="hits">'+notiPrevNextImgMovInfo[i].notiReadCnt+'</span></p>' +
						' 				<p class="fo_byte">'+notiPrevNextImgMovInfo[i].notiBgnDttm+'</p>' +
						' 			</div>' +
						' 		</li>');
			}else if(boardFormSpec == '020'){
				$("#board_mov").append('<li class="ver_top">' +
						' 			<div class="box_vod">' +
						' 				<p class="te_center"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')"><img src="'+movDir+'/'+notiPrevNextImgMovInfo[i].apndFileName+'" class="on" alt="게시판동영상"></a></p>' +
						' 				<p class="pho_tit"><span class="bl_pho"><!-- 이미지 --></span><span class="te_dot"><a href="javascript:fnGetBoardView(\''+notiPrevNextImgMovInfo[i].notiId+'\' , \''+notiPrevNextImgMovInfo[i].pnum+'\')">'+notiPrevNextImgMovInfo[i].notiTitle+'</a></span><span class="fo_replyn">[의견:'+notiPrevNextImgMovInfo[i].opnCnt+']</span></p>' +
						' 				<p class="name">'+notiPrevNextImgMovInfo[i].updrName+'<span class="hits">'+notiPrevNextImgMovInfo[i].notiReadCnt+'</span></p>' +
						' 				<p class="fo_byte">'+notiPrevNextImgMovInfo[i].notiBgnDttm+'</p>' +
						' 			</div>' +
						' 		</li>');
				
			}
		}
	}
	
	if(param == 'go'){
		$("#goBottom").trigger("click");
	}else{
		fnSetFrameHeight(230);//이미지형일경우
	}
};


var fnNotiPrevNextImgMovInfoView = function(go){

	var jsonNotiObject = {
			'boardId' : '' , 
			'notiId' : '',
			'direction' : 0
		};
	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;

	if(notiPrevNextImgMovInfo.length <= 1){
		if(goIdx < 0 && go < 0){ goIdx = goIdx + 0; $("#btn_prev_img").hide(); prev_last = true;}
		else if(goIdx < 0 && go > 0){ goIdx = goIdx + go; $("#btn_prev_img").show(); $("#btn_next_img").show(); prev_last = false; next_last=false;}
		else if(goIdx > 0 && go < 0){ goIdx = goIdx + go; $("#btn_prev_img").show(); $("#btn_next_img").show(); prev_last = false; next_last=false;}
		else if(goIdx > 0 && go > 0){ goIdx = goIdx - 0; $("#btn_next_img").hide(); next_last = true;}
	}else{
		goIdx = goIdx + go;
	}
	jsonNotiObject.direction = goIdx;
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/notiPrevNextImgMovInfo.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				if(data.notiPrevNextImgMovInfo){
					notiPrevNextImgMovInfo = $.parseJSON(data.notiPrevNextImgMovInfo);
				}
				
				fnGetImgNotiPrevNextInfo('go');	
			}
		}
 	});
};




//게시글 좋아요 사용자 조회
var fnGetBbsNotiEvalInfoList = function(rnum){
	
	$('#linkUserLayer').css({
//			'display': 'block',
//			'position': 'absolute',
//			'width': '180px'
	});
	var _rnum = 0;
	if(rnum > 0 ){
		_rnum = maxNum;
	}
	var jsonNotiObject = {
			'boardId' : '' , 
			'notiId' : '' ,
			'rnum' : ''
		};
	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;
	jsonNotiObject.rnum = _rnum;

	if($("#linkUserListDiv").attr("value") == "N"){
		
		$("#linkUserLayer").show();
		$("#linkUserListDiv").show();
		$("#linkUserListUl li").remove();
		PortalCommon.getJson({
			url: WEB_HOME+"/board210/getBbsNotiEvalInfoList.do?format=json",
			data: {  'data' : JSON.stringify(jsonNotiObject)}, 
			success :function(data){
				if(data.jsonResult.success ===true){
					var notiEval = $.parseJSON(data.notiEval);
					fnSetDataNotiEvalInfo(notiEval);
				}
				maxNum += notiEval.length;
			}
	 	});
		$("#linkUserListDiv").attr("value","Y");
	}else{
		$("#linkUserLayer").hide();
		$("#linkUserListDiv").hide();
		$("#linkUserListDiv").attr("value","N");
		maxNum = 0;
	}
}; 

var replaceAll = function(str,str1,str2){
	  return str.split(str1).join(str2);
};

var fnSetDataNotiInfo = function(notiJson, surveyReplyCnt, surveyListJson){
	//$("#boardPage ul").remove();
	$("#boardPage ul li").remove();
	regrId = notiJson.regrId;
	notiKind = notiJson.notiKind;
	nickUseYn = notiJson.nickUseYn;
	opnMakrRealnameYn = notiJson.opnMakrRealnameYn;//의견작성자 실명여부 
	userMail = notiJson.mail;
	opnPrmsYn = notiJson.opnPrmsYn;
	agrmOppYn = notiJson.agrmOppYn;

	$("#scrapNotiTitle").val("[스크랩] "+notiJson.notiTitleOrgn);
	$("#notiNum").html(notiJson.oldNoticeSeq);
	$("#notiReadCnt").html(notiJson.notiReadCnt);//조회수
	$("#opnCnt").html(notiJson.opnCnt);
	$("#notiLikeCnt").html(notiJson.evalLikeCnt);//좋아요
	$("#notiTitle").html(notiJson.notiTitle);
	$("#notiBgnDttm").html(notiJson.notiBgnDttm);
	$("#regDttm").html(notiJson.regDttm);
	if(notiJson.notiEndDttm == '9999-12-31'){
		$("#notiEndDttm").html('영구');
	}else{
		$("#notiEndDttm").html(notiJson.notiEndDttm);
	}
	$("#notiAgrmCnt").html(notiJson.evalAgrmCnt);//찬성
	$("#notiOppCnt").html(notiJson.evalOppCnt);//반대
	$("#notiTagList").html(notiJson.notiTagLst);
	$("#notiTagList").html(notiJson.notiTagLst);
	if(notiJson.moblOpenDiv == '010'){
		$("#moblOpenDiv").html("본문만 공개");	
	}else if(notiJson.moblOpenDiv == '020'){
		$("#moblOpenDiv").html("본문 + 첨부파일 공개");	
	}else if(notiJson.moblOpenDiv == '030'){
		$("#moblOpenDiv").html("공개하지 않음");	
	}
	if(nickUseYn == 'N'){//게시글의 닉네임 사용여부 
		
	}
	if(agrmOppYn == 'Y'){
		$("#agrmOppTd").show();
	}
	if(opnWrteDiv != '' && opnWrteDiv != '020' && opnPrmsYn == 'Y'){
		$(".reply_main_survey").show();			
	}
	if(eamAdminYn == 'Y' || userId == regrId ){
		$(".btn_modify_survey").show();
		$(".btn_delete_survey").show();
	}
	if(notiJson.nickUseYn == 'Y' && notiJson.userNick !=null){
		$("#makrIp").html(fnGetIpUtil(notiJson.makrIp));
		$("#userName").html(notiJson.userNick);	
		$("#deptName").html("");
		$("#deptName").removeClass("read_info");
		$("#msgSpan").removeClass("read_info");
		$("#mailTo").removeClass("read_info");
			$("#mailTo").html("");
		
	}else{
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
		$("#deptName").append('<img src="${RES_HOME}/images/ico_room.png" alt="부서"/>'+notiJson.deptName);
		if(notiJson.mail ==null ){
			$("#mailTo").append('<img src="${RES_HOME}/images/ico_email.png" alt="이메일" />');
		}
		else
		{
			$("#mailTo").append('<img src="${RES_HOME}/images/ico_email.png" alt="이메일" />'+notiJson.mail);
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
	}
	
	if(notiKind == '010'){//일반
		
	}else if(notiKind == '020'){//이미지
		
	}
	
	if(replyWrteDiv == '010' ){//답글쓰기 사용
		$(".btn_reply").show();	
	}else if(replyWrteDiv == '030' ){//게시자 지정
		if(notiJson.replyPrmsYn == 'Y'){
			$(".btn_reply").show();	
		}
	}
	
	//공지
	if(notiJson.anmtYn == "Y"){
		$("#anmtDiv").show();
	}

	fnSetDataNotiFileInfo(notiFile);
	
	
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



//좋아요 사용자 목록 
var fnSetDataNotiEvalInfo = function(notiEvalJson){
	$('#linkUserListUl li').remove();
	for (var i=0; i < notiEvalJson.length ; i++){
		$('#linkUserListUl').append('<li id="'+notiEvalJson[i].evalSeq+'">'
		+ '<a href="javascript:doLikeUserInfoPop(\''+ notiEvalJson[i].userId +'\')">'+ notiEvalJson[i].userName +'</a>'
		+ '<span class="te_dot">&nbsp;(' + notiEvalJson[i].ou +')</span>'
		+ '</li>');		
	}
	
	if (notiEvalJson.length > 48)  //50건씩 조회
	{
		$('<div id="inter-moreData" class="btn_area"><div class="fl_cen"><a onclick="fnGetBbsNotiEvalInfoList(50);" class="btn_basic2 pa_lef10"><span class="btn_text">더보기</span></a></div></div>').insertAfter($('#linkUserListUl'));
	}
	
	$("#linkUserListUl").parent("div").css({
		'min-height': '220px'
		, 'height': '220px'
		, 'visibility' : 'true'
		, 'overflow-x': 'hidden'
		, 'overflow-y': 'auto'
	});
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
	
	$('#ulNotiFile li').remove();

	var apFileCnt = 0;
	for (var i=0; i < notiFileJson.length ; i++){
		if(notiFileJson[i].apndFileTp == '050'){
			$('#ulNotiFile').append('<li><a href="javascript:fnDoFileDown(\''+ notiFileJson[i].apndFileSeq +'\',\''+ notiFileJson[i].apndFileName +'\',\''+ notiFileJson[i].apndFileOrgn +'\')">'+notiFileJson[i].apndFileOrgn+'</a>'
					+'('+getFileSzForKb(notiFileJson[i].apndFileSz)+"kb"+')</li>');	
			
			apFileCnt++;	
		 	    
		}
	}
	
	  
	if(apFileCnt > 0){
		$(".btn_apnd_save").show();
	}
	
	if(notiKind == "010"){//일반
		
	}else if(notiKind == "020"){//이미지
		
		$("#notiConts").addClass("te_center");
		$("#imgNotiConts li").remove();
		for (var i=0; i < notiFileJson.length ; i++){
			if(notiFileJson[i].apndFileTp == '020'){
				fnImgApndList(notiFileJson[i]);	
			}
		}
		
	}else if(notiKind == "030"){//동영상
		$("#movNotiConts p").remove();
		$("#movNotiConts img").remove();
		$("#movNotiConts object").remove();
		for (var i=0; i < notiFileJson.length ; i++){
			if(notiFileJson[i].apndFileTp == '030'){
				fnMovApndList(notiFileJson[i]);		
			}
		}
	}
	
	if(opnPrmsYn == 'Y'){//의견사용여부 
		fnSetDataNotiOpn1(notiOpn1);	
	}else{			
			if(boardForm == '010' ){
				fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
			}else if(   (boardForm == '030' && boardFormSpec == '010') 
					 || (boardForm == '030' && boardFormSpec == '020')
						){//이미지 , 동영상형 
				fnGetImgNotiPrevNextInfo();
			}
	}
	fnSetFrameHeight(250);
	
};

//파일다운로드
var fnFileDown = function(filename, fileorg)
{	
	alert(filename+"@@@@"+ fileorg);
	 var jsonObject = {
		'apndFileOrgn' : fileorg,
		'apndFileName' : filename,
		'notiId' : notiId
	 };
	 
	 var url =WEB_HOME+"/board100/bbsFileDownloadNew.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");
	 //alert(url);
	 document.dummy.location.href = url;
};

//이미지형 게시글 
var fnImgApndList = function(json)
{

	var rtSize = PortalCommon.fnImgPreviewResize(imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName, 430, 300);
	//$("#imgNotiConts li").remove();
	//$("#imgNotiConts div").remove();
	$("#imgNotiConts").append(
			'<li style="border: 0;"><a style="cursor:pointer;" onclick="javascript:fnImgPreview(\''+json.apndFileSeq+'\')" >'
			+'<img id="viewImg-'+json.apndFileSeq+'" onload="javascript:PortalCommon.fnImgPreviewOnloadResize(this, 430, 300)" src="'+imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName+'" alt="올린이미지"></a></li>'
			+'<div style="display:none;" align="center" id="dialog-'+json.apndFileSeq+'" title="'+json.apndFileOrgn+'">'
			+'<img src="'+imgSvrUrl+'/'+json.apndFilePath+'/'+json.apndFileName+'"></div>');
	
	$("#imgNotiConts").css('display','block');		
	$("#imgNotiConts").find('img').load(function(){
		fnImgEffect();
	});
	
};

//동영상형 게시글 (즉시실행)
var fnMovApndList2 = function(json)
{
	//$("#movNotiConts img").remove();
	//$("#movNotiConts object").remove();
	$("#movNotiConts").css('display','block');
	if(notiMov.length > 0 && notiMov[0].mvpKey){
		fnPlayMov();
	}else{
		var thumbnailImg = movDir+'/'+json.apndFilePath+'/'+json.apndFileName;
		$("#movNotiConts").append('<img src="'+thumbnailImg+'" alt="비디오파일" >');
	}
};
	

//동영상형 게시글 (클릭후실행)
var fnMovApndList = function(json)
{
	//$("#movNotiConts img").remove();
	//$("#movNotiConts object").remove();
	$("#movNotiConts").css('display','block');

	//var thumbnailImg = movDir+'/'+json.apndFilePath+'/'+json.apndFileName;
	var thumbnailImg = movDir+"/"+thumbnailFile;
	$("#movNotiConts").append('<a href="javascript:fnPlayMov();" id="mvp_'+json.mvpKey+'"><img src="'+thumbnailImg+'" alt="비디오파일" ></a>');
};

//동영상 (원래창)
var fnPlayMov2 = function(){
	if(notiMov.length > 0 && notiMov[0].mvpKey){
		$("#movNotiConts > #mvp_"+notiMov[0].mvpKey).remove();
	
		var thisMvpKey = RES_HOME+"/common/player/Player.swf?key="+notiMov[0].mvpKey;
		movPlayerObj = '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,115,0" width="406" height="358" id="main" align="middle"> '
					+ '	<param name="allowScriptAccess" value="always" />                                                                                                                                                            '
					+ '	<param name="allowFullScreen" value="true" />                                                                                                                                                                '
					+ '	<param name="movie" value="'+thisMvpKey+'" />                                                                                                                      '
					+ '	<param name="quality" value="high" />                                                                                                                                                                        '
					+ '	<param name="bgcolor" value="#ffffff" />                                                                                                                                                                     '
					+ '</object>                                                                                                                                                                                                     ';

		$("#movNotiConts").append(movPlayerObj);
	}
};

//동영상 (새창)
var fnPlayMov = function(){
	if(notiMov.length > 0 && notiMov[0].mvpKey){
		var w = 640;
		var h = 560;
		var winl = (screen.width-w)/2;
		var wint = (screen.height-h)/2;
		var settings  ='height='+h+',';
		settings +='width='+w+',';
		settings +='top='+wint+',';
		settings +='left='+winl+',';
		settings +='toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=no, scrollbars=no';
		var newWin = window.open ("about:blank", "mov", settings);
	
		var thisMvpKey = WEB_HOME+"/common/player/Player.swf?key="+notiMov[0].mvpKey;
		movPlayerObj = '<table  cellspacing="0" style="border:1px solid #DEDEDE;">'
		            + '<tr>'
		            + '<td style="padding:7px 21px;border-bottom:1px solid #DEDEDE;"><span style="font:bold 14px dotum;color:#01669A;">'+$("#notiTitle").text()+'</span></td>'
		            + '<tr>'
		            + '<td style="padding:7px 21px;border-bottom:1px solid #DEDEDE;">'
					+ '<object classid="clsid:d27cdb6e-ae6d-11cf-96b8-444553540000" codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,115,0" width="580" height="480" id="main" align="middle"> '
					+ '	<param name="allowScriptAccess" value="always" />                                                                                                                                                            '
					+ '	<param name="allowFullScreen" value="true" />                                                                                                                                                                '
					+ '	<param name="movie" value="'+thisMvpKey+'" />                                                                                                                      '
					+ '	<param name="quality" value="high" />                                                                                                                                                                        '
					+ '	<param name="bgcolor" value="#ffffff" />                                                                                                                                                                     '
					+ '</object>                                                                                                                                                                                                     '
					+ '</td></tr></table>';

		newWin.document.write(movPlayerObj);
	}
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
		    
		    var rtSize = PortalCommon.fnImgPreviewResize(img.attr('src'), 120, 120);
		    
		    img.css( {
		         'width': rtSize[0]+"px",
		         'height': rtSize[1]+"px",
		         'margin-left': "-" + rtSize[0]/2 +"px",
		      'margin-top': "-" + rtSize[1]/2 +"px"
		    });	
		    
			/*
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
			*/
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
	var img_path = $( "#dialog-"+id ).find("img").attr("src");
	
	var rtSize = PortalCommon.fnImgPreviewResize(img_path, 750, 640);
	
	parent.$('[id^="dialog-"]').remove();
	parent.$('#container').prepend(
			'<div id="dialog-'+id+'" align="center">'
			+'<img src="'+img_path+'" width="'+rtSize[0]+'" height="'+rtSize[1]+'" >'
			+'</div>'
	);
	
	
	parent.$( "#dialog-"+id ).dialog
	(
			{      
				height: rtSize[1]+70,      
				width: rtSize[0]+50,
				modal: true
			}
	);
};

//게시물 이동
var fnDoMoveNoti = function(boardId){
	
	if(regrId != userId){
		alert("게시자만 이동이 가능합니다.");
		return;
	}
	var jsonMoveArray = [];

	var jsonBoardInfoObject = {
			'boardId'   : '', //게시판 ID
			'notiId'    :[]
	};		
	
	var jsonObject = {
			'id' : notiId
	};
	jsonMoveArray[0] = jsonObject;
	jsonBoardInfoObject.boardId = boardId;//이동 대상 게시판아이디
	jsonBoardInfoObject.notiId = jsonMoveArray;
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/insertBbsNotiInfoForMove.do?format=json",
		data: {  'moveData' : JSON.stringify(jsonBoardInfoObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnDoList();
			}
		}
 	});
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
	
	if(boardForm == '010' ){
		fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
	}else if(   (boardForm == '030' && boardFormSpec == '010') 
			 || (boardForm == '030' && boardFormSpec == '020')
				){//이미지 , 동영상형 
		fnGetImgNotiPrevNextInfo();
	}
//		if(notiKind == '010' || notiKind == '040'){
//			fnGetPrevNextNotiInfo(notiPrevNextInfo[0]);	
//		}else if(notiKind == '020' || notiKind == '030'){//이미지 , 동영상형 
//			fnGetImgNotiPrevNextInfo();
//		}
	
};

//의견등록
var fnInsertBbsNotiOpnForView = function(upOpnSeq){
	
	var jsonNotiOpnObject = {
			'notiId' : '',
			'opnConts' : '', 
			'upOpnSeq' : '',
			'userNick' : ''
		};
	
	jsonNotiOpnObject.notiId = notiId;
	
	if(upOpnSeq != undefined ){//의견의 의견 
		if($("#re_reply_"+upOpnSeq).val() =="" ){
			alert("내용을 입력해주세요.");return;
		}
		jsonNotiOpnObject.opnConts = $("#re_reply_"+upOpnSeq).val().replaceAll("\n","<br/>");
		jsonNotiOpnObject.upOpnSeq = upOpnSeq;
		
	}else{//본문의 의견 
		if($("#noti_reply").val() =="" ){
			alert("내용을 입력해주세요.");return;
		}
		jsonNotiOpnObject.opnConts = $("#noti_reply").val().replaceAll("\n","<br/>");
		jsonNotiOpnObject.upOpnSeq = 0;

	}
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/insertBbsNotiOpnForView.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnGetNotiDetailInfoView();
			}
		}
 	});
	
}; 

//의견수정
var fnUpdateBbsNotiOpnForView = function(opnSeq){
	
	var jsonNotiOpnObject = {
			'notiId' : '',
			'opnConts' : '', 
			'notiOpnSeq' : ''	
		};
	if($("#opnTxt_"+opnSeq).val() == ""){
		alert("내용을 입력해주세요.");return;
	}
	
	jsonNotiOpnObject.notiId = notiId;
	jsonNotiOpnObject.opnConts = $("#opnTxt_"+opnSeq).val();
	jsonNotiOpnObject.notiOpnSeq = opnSeq;
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/updateBbsNotiOpnForView.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnGetNotiDetailInfoView();
			}
		}
 	});
	
}; 

//의견삭제
var fnDeleteBbsNotiOpnForView = function(opnSeq){
	if(!confirm('삭제하시겠습니까?')){return;}
	var jsonNotiOpnObject = {
			'notiId' : '',
			'notiOpnSeq' : ''	
		};
	
	jsonNotiOpnObject.notiId = notiId;
	jsonNotiOpnObject.notiOpnSeq = opnSeq;
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/deleteBbsNotiOpnForView.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnGetNotiDetailInfoView();
			}
		}
 	});
	
}; 

//게시글 평가 등록
var fnInsertBbsNotiEvalInfoForView = function(notiEvalDiv){
	
	var jsonNotiOpnObject = {
			'notiId' : '',
			'notiEvalDiv' : ''	
		};
	
	jsonNotiOpnObject.notiId = notiId;
	jsonNotiOpnObject.notiEvalDiv = notiEvalDiv;
	
	PortalCommon.getJson({
		url: WEB_HOME+"/board210/insertBbsNotiEvalInfoForView.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiOpnObject) }, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnGetNotiDetailInfoView();
			}
		}
 	});
};

//파일다운로드
var fnDoFileDown = function(fileseq, filename, fileorg)
{	
	
	 var jsonObject = {
		'apndFileOrgn' : fileorg,
		'apndFileName' : filename,
		'apndFileSeq' :  fileseq,
		'notiId' : notiId
	 };
	 
	 var url =WEB_HOME+"/board100/bbsFileDownload.do?data="+encodeURI(JSON.stringify(jsonObject),"UTF-8");
	 document.dummy.location.href = url;
};

var fnGetBoardView = function(id, temp){
	parent.document.getElementById("bbsFrame").height = "700px";
	location.href = WEB_HOME+"/board210/getBasicBoardView.do?notiId="+id+"&boardId="+boardId+"&pageIndex="+pageIndex+"&pageUnit=10&pnum="+temp;
};

//목록
var fnDoList = function(){
	if (boardId == 'BBS999999'){//임시게시판이면
		parent.document.getElementById("bbsFrame").src= WEB_HOME+'/board240/getTmpBoardInfoList.do?boardId='+boardId+'&pageIndex=1&pageUnit=10&orderType=default';
	}else{
		if (boardForm == '030' && boardFormSpec == '010'){  //이미지형
			parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board211/getBbsImgBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
		}else if (boardForm == '030' && boardFormSpec == '020'){  //동영상형
			parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board212/getBbsVideoBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
		}else if (boardForm == '030' && boardFormSpec == '030'){  //컨텐츠형
			parent.document.getElementById("bbsFrame").src=WEB_HOME+'/board213/getBbsContentsBoardNotiList.do?boardId='+boardId+'&pageIndex=1&pageUnit=6&orderType=default';
		}else{
			isDesc = isDesc==null?false:isDesc;
			isDesc = isDesc==""?false:isDesc;
			orderType = orderType==""?"default":orderType;
			
			if(searchKeyword !="" || regDttmFrom !="" || regDttmTo !="")
			{
				listYn = 'Y';
			}
			/*
			parent.document.getElementById("bbsFrame").src= 
				WEB_HOME+"/board210/getBoardInfoList.do?boardId="
						+boardId+"&pageIndex="+pageIndex+"&pageUnit="+pageUnit
						+"&searchCondition="+searchCondition
						+"&searchKeyword="+escape(encodeURIComponent(searchKeyword))
						+"&chkSurvey="+chkSurvey
						+"&regDttmFrom="+regDttmFrom
						+"&regDttmTo="+regDttmTo
						+"&orderType="+orderType
						+"&listYn="+listYn;
			*/
			fnListPage(orderType);
		}
	}
};

var fnListPage = function(orderType) {
	if(orderType == "") return;
	document.listForm.pageIndex.value = pageIndex;
	document.listForm.searchCondition.value = searchCondition;
	document.listForm.searchKeyword.value = escape(encodeURIComponent(searchKeyword));
	document.listForm.orderType.value = orderType;
	document.listForm.isDesc.value = isDesc;
	document.listForm.chkSurvey.value = chkSurvey;
	document.listForm.pageUnit.value = pageUnit;
	document.listForm.action = WEB_HOME+"/board210/getBoardInfoList.do?boardId="+boardId;
	document.listForm.submit();
};


//게시물 이동
var fnDoNotiMove = function(notiId) {
	if(notiKind == '040'){
		alert('설문형 게시글은 이동할 수 없습니다.');
		return;
	}
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '' 
		,'notiId' : []
	};
	//내가 작성한 게시물 인지 체크
	jsonNotiObject.boardId = boardId;
	jsonNotiObject.notiId = notiId;
	PortalCommon.getJson({
		url : WEB_HOME+"/board210/getMyNotiCheckList.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
				//if (!confirm('이동하시겠습니까?')) {
				//	return;
				//}
				PortalCommon.popupWindowCenter(
						WEB_HOME+'/organization/categoryChartPop.do?mode=cm_move&kind=2&type=1&admin=1&notiId='
								+ JSON.stringify(notiId), 'myBoardPop',
						'400', '450');
			
			}else{
				alert(data.jsonResult.message);
			}
		}
	});
};

var doPageReload = function(){
	fnDoList();//목록 
};

var getCheckNotiIdJsonData = function() {
	var jsonArray = [];
		var jsonObject = {
			'id' : notiId
		};
		jsonArray[0] = jsonObject;
	return jsonArray;
};

//게시글 삭제
var notiDelete = function(notiId) {
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
	
	PortalCommon.getJson({
		url : WEB_HOME+"/board210/deleteNotiInfo.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {

				window.returnValue = 'ok';
				fnDoList();

			}
		}
	});
};

//공용 게시글 삭제 이력
var fnInsertBbsNotiDelInfo = function(notiId){
	if (!confirm('삭제하시겠습니까?')) {
		return;
	}		
	var jsonNotiDelObject = {
			'notiId' : '',
			'boardId' : '',
			'delDiv' : '',
			'delBasis' : '',
			'delRsn' : '',
			'delRsnCode' : '',
			'delRsnCodeSpec' : '',
			'badNotiFindDttm' : '',
			'rmrk' : ''
		};
	jsonNotiDelObject.notiId = notiId;
	jsonNotiDelObject.boardId = boardId;
	jsonNotiDelObject.delDiv = 'DEL';//삭제DEL MOV
	//jsonNotiDelObject.delBasis = $("#delBasis").val();//근거	
	//jsonNotiDelObject.delRsn = $("#delRsn").val();//삭제 사유
	//jsonNotiDelObject.delRsnCode = 'AAA';	
	//jsonNotiDelObject.delRsnCodeSpec = $("#delRsnCodeSpec").val();//조치내용
	//jsonNotiDelObject.badNotiFindDttm = $("#badNotiFindDttm").val().replaceAll('-','');//발견일시  
	//jsonNotiDelObject.rmrk = $("#rmrk").val();	//비고 

	PortalCommon.getJson({
		url: WEB_HOME+"/board210/insertBbsNotiDelInfo.do?format=json",
		data: {  'data' : JSON.stringify(jsonNotiDelObject) , 'moveData' : getMoveJsonData()}, 
		success :function(data){
			if(data.jsonResult.success ===true){
				fnDoList();
			}
		}
 	});
};	

// bbsDelInfoPop.jsp에서 복사함.(단건처리로 수정)
var getMoveJsonData = function(){
	
	var jsonMoveArray = [];
	var jsonBoardInfoObject = null;
	
	var jsonObject = {
			'id' : notiId
	};
	jsonMoveArray[0] = jsonObject;
	
	jsonBoardInfoObject = {
			'boardId'   : '', 
			'notiId'    :[]
		};	
	jsonBoardInfoObject.notiId = jsonMoveArray;
	jsonBoardInfoObject.boardId = boardId;
	
	//넘겨받은 게시글이 있다면 해당 게시물을 선택한 게시판으로 이동시킨다.
	var moveData = "";
	moveData = JSON.stringify(jsonBoardInfoObject);
	
	
	return moveData;
};	

var getFileSzForKb = function(sz) {
	if(sz > 0){
		sz = sz / 1000;
	}
	return Number(sz);
};


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
			dblClickExpand: false,
			showLine:false
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

function zTreeOnClick(event, treeId, treeNode) {
	selectNodeId = treeNode.boardId;
	selectBoardForm = treeNode.boardForm;
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
	
	$("#myBoardtreeObj .ico_docu").css("background-position-x", "8px");
	$("#myBoardtreeObj .ico_docu").css("background-position-y", "6px");
	$("#myBoardtreeObj .noline_docu").css("display", "none");
};

var fnDrawScrollBar = function(){
	
	$("#myBoardListDiv").css({
		'height' : '200'
		, 'visibility' : 'true'
		, 'overflow-x': 'auto'
		, 'overflow-y': 'auto'
	});
	
};

//스크랩등록 
var fnInsertScrap = function() {
	
	if(selectNodeId == null || selectNodeId ==""){alert("게시판을 선택해 주세요.");return;}
	if(selectBoardForm != boardForm){
		alert('같은형태의 게시판으로만 이동이 가능합니다.');
		return;
	}
	if($("#scrapNotiTitle").val() == ''){alert('제목을 입력해주세요.');return;}
	
	var jsonNotiObject = {
		//----게시판속성 설정------
		'boardId' : '', //게시판 ID
		'linkUrl' : '',
		'notiTitle' : '',
		'notiId' : '',
		'notiKind' : '',
		'pbs' : 'N'
	};
	//공용게시판 -> 개인게시판으로 스크랩 
	jsonNotiObject.boardId = selectNodeId;
	jsonNotiObject.notiTitle = $("#scrapNotiTitle").val();
	jsonNotiObject.notiId = notiId;
	
	if($(':radio[name="scrapType"]:checked').attr("id") == "url"){//링크
		
		jsonNotiObject.linkUrl = "/board210/boardViewFrame.do?pageIndex=1&boardId="+boardId+"&notiId="+notiId;
		jsonNotiObject.notiKind = '060';
		
	}else if($(':radio[name="scrapType"]:checked').attr("id") == "all"){//본문
		/**
		* 본문스크랩은 의견, 첨부, 평가 등 기타 관련테이블은 스크랩되지 않는다. 
		*/
		jsonNotiObject.linkUrl = '';
		jsonNotiObject.notiKind = '';
	}	
	
	PortalCommon.getJson({
		url : WEB_HOME+"/board310/insertPbsUserNotiInfoForScrap.do?format=json",
		data : {
			'data' : JSON.stringify(jsonNotiObject)
		},
		success : function(data) {
			if (data.jsonResult.success === true) {
				window.returnValue = 'ok';
				$("#pop_scrap").hide();
			}
		}
	});
};

var doUserInfoPop = function(){
	if(nickUseYn == 'N'){//실명일경우만
		var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+regrId;
		PortalCommon.userInfoPop(url);	
	}
};

var doUserInfoPopForOpn = function(_regrId){
	if(nickUseYn == 'N'){//실명일경우만
		var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+_regrId;
		PortalCommon.userInfoPop(url);	
	}
};

var doLikeUserInfoPop = function(_userId){
	if(nickUseYn == 'N'){//실명일경우만
		var url = WEB_HOME+"/person100/personMemberInfoView.do?userId="+_userId;
		PortalCommon.userInfoPop(url);	
	}
};

//메일발송 
var fnEmailSubmit = function() {
		var subject = $("#notiTitle").text();
		var content = $("#notiConts").html();
	/* var subject = '1111'; 
	var content = '222';
	alert(content);  
	alert(content); */
	dummyForm.subject.value = subject;
	dummyForm.content.value = content;
	dummyForm.target = "goEmail";
	dummyForm.action = WEB_HOME+"/qmemo/getMailWritePop.do";
	//PortalCommon.windowPopup(WEB_HOME+"/qmemo/getMailWritePop.do?subject="+escape(encodeURIComponent(subject))+"&content="+escape(encodeURIComponent(content)), "goEmail", 1010, 780);
	PortalCommon.windowPopup("about:blank", "goEmail", 1010, 780);
	dummyForm.submit();
};

//게시글 삭제 이력
var fnBbsDelInfoPop = function(_notiId , delDiv){
	PortalCommon.windowPopup(WEB_HOME+'/board210/bbsDelInfoPop.do?notiId='+JSON.stringify(_notiId)+'&delDiv='+delDiv,'삭제정보','528','552');
};

//게시물 이동 관리지용 
var fnDoNotiMoveDelForAdm = function(notiId, delDiv) {
	
	if(isNotiSurvey){
		alert('설문형 게시글은 이동할 수 없습니다.');
		return;
	}	
	PortalCommon.popupWindowCenter(
			WEB_HOME+'/organization/categoryChartPop.do?mode='+delDiv+'&kind=2&type=1&admin=1&notiId='
					+ JSON.stringify(notiId), 'myBoardPop',
			'400', '450');
};


/*	
 $(window).load(function () {
		fnSetFrameHeight(230);
	});
*/	

////////////////////////////////onload/////////////////////////////////////////////////////////////////////	

$(document).ready(function () {//이벤트 모음 
	
	
	//parent.document.getElementById("bbsFrame").height = "600px";
	
	parent.document.getElementById("bbsFrame").height = "700px";
	
	if(boardExplUseYn){
		if(boardExplUseYn == 'Y' && boardExpl !=""){
			$("#boardExpl").html(boardExpl);
		} 
	}

	fnGetNotiDetailInfoView();

	fnSetFrameHeight(20);
	   
	$('#scrapSave').click(function(){
		fnInsertScrap();
	});
	$('#btnScrapClose').click(function(){
		$("#pop_scrap").hide();
	});
	$('#btnScrap').click(function(){
		fnGetPbsUserBoardInfoListForZTree();
		
		$("#pop_scrap").css({
				'position':'absolute',
				'display':'block',
				'z-index':'80',
				'top': $(this).offset().top- 10+"px",
				'left': $(this).offset().left - 410+"px"
			});
		$("#pop_scrap").show();			
	});
	//게시물이동
	$('.btn_boardMove_survey').click(function(){
		
		if(eamAdminYn == "Y"){
			if(regrId == userId){
				fnDoNotiMove(getCheckNotiIdJsonData());
			}else{
				fnDoNotiMoveDelForAdm(getCheckNotiIdJsonData(),'cm_move_adm');	
			}
		}else{
			fnDoNotiMove(getCheckNotiIdJsonData());
		}
		
	});
	//목록
	$(".btn_list_survey").click(function(){
		fnDoList();
	});
	$(".btn_write_survey").click(function(){
		location.href = WEB_HOME+"/board230/board230Write.do?boardId="+ boardId+"&kind=BBS&pageIndex="+pageIndex+"&pageUnit="+pageUnit;
	});
	$(".btn_modify_survey").click(function(){
		location.href = WEB_HOME+"/board230/board230Write.do?boardId="+ boardId+"&kind=BBS&notiId="+notiId+"&pageIndex="+pageIndex+"&pageUnit="+pageUnit;
	});
	$(".btn_reply_survey").click(function(){
		//alert('댓글 : '+notiId);
		location.href = WEB_HOME+"/board230/board230Write.do?boardId="+ boardId+"&kind=BBS&upNotiId="+notiId+"&pageIndex="+pageIndex+"&pageUnit="+pageUnit;
	});
	$(".btn_delete_survey").click(function(){
		fnInsertBbsNotiDelInfo(notiId);
		/*
		if(eamAdminYn == "Y"){//접속자와 등록자
			if(regrId == userId){
				notiDelete(getCheckNotiIdJsonData());		
			}else{
				fnBbsDelInfoPop(getCheckNotiIdJsonData(),'DEL');	
			}
		}else {
			notiDelete(getCheckNotiIdJsonData());		
		}
		*/
		
	});
	$(".btn_print_survey").click(function(){
		var top_po = (screen.availHeight/2) - (530/2);
	    var left_po = (screen.availWidth/2) - (820/2);
	    var f = document.pageForm;
	    f.pageHtml.value = $("#surveyRsltPage").html();
	    //var popup = window.open('', 'surveyPrintWin', 'top='+top_po+',left='+left_po+',width=820,height=530,resizable=yes,scrollbars=yes,toolbar=yes,menubar=yes');
	    var pop = window.open;
	    pop("","surveyRsltPrintWin", 'top='+top_po+',left='+left_po+',width=820,height=530,resizable=yes,scrollbars=yes,toolbar=yes,menubar=yes');
	    f.target = "surveyRsltPrintWin";
	    f.submit();
	});
	$(".btn_apnd_save").click(function(){
		//document.InnoFD.StartDownload();
	});

	$("#notiConts").find("IMG").load(function () {
		fnSetFrameHeight(100);
	});
	$("#mailToAddr").click(function(){//메일아이콘
		PortalCommon.windowPopup("http://98.33.11.25:9001/sso/Login/App/SSOAppConnect_2.jsp?SG=LNK&forwardAction=create&sReceive="+userMail,'',1024,600);
	});
	
	
	
	$(".bl_qus2").click(function(){
		fnSetFrameHeight(20);
	});
	
//		$(".btn_mail").click(function(){//메일발송
		
		
//			PortalCommon.windowPopup("http://98.33.11.25:9001/sso/Login/App/SSOAppConnect_2.jsp?SG=LNK&forwardAction=create&sReceive=",'',1024,600);
//		});
	$("#smsSend").click(function(){
		PortalCommon.popupWindowCenter(WEB_HOME+"/person100/openSmsPage.do?boardKind=030&smsStep=1&notiId="+notiId, "sms", 448, 444);				
	});
	
	/* //게시판 설명 보기		
	$("#moreBoardExpl").click(function() {
		
		if (boardExplUseYn == 'Y')
		{
			PortalCommon.windowPopup(WEB_HOME+'/board230/bbsBoardExplPopup.do?boardId='+boardId, '게시판설명',750,300);
		}
		
	}); */
	
	if (boardKind == '020')
	{
		$("#boardPage").css("display","none");
	}		
	
	$('.ico_smile').click(function(){
		$('.perlist').css({
			'position':'absolute',
			'top': $(this).offset().top+20+"px",
			'left': $(this).offset().left-67+"px"
		});
	});
	
	$(".ico_smile").hide();		
	
	// onload
	fnSetFrameHeight(230);
});
</script>
<!--  <script type="text/javascript" src="${RES_HOME}/js/portal/board/basicBoardView.js"></script> -->
</head>

<body>
<form action="${WEB_HOME}/board100/surveyRsltPrint.do" method="post" name="pageForm">
	<input type="hidden" name="pageHtml" value="">
</form>
<form action="" method="post" name="listForm">
	<input type="hidden" name="pageUnit" value=""/>
	<input type="hidden" name="pageIndex" />
	<input type="hidden" name="searchCondition" />
	<input type="hidden" name="searchKeyword" value=""/>
	<input type="hidden" name="notiSeqNo" />
	<input type="hidden" name="orderType" />
	<input type="hidden" name="isDesc" />
	<input type="hidden" name="chkSurvey"/>
</form>

<div class="container" id="surveyRsltPage">

	<div class="header">
		<div class="h1">${boardName}</div>
		<div class="loc">
			<a href="#" class="home"><img src="${RES_HOME}/images/ico_home.png" alt="홈" /></a>
			<a href="#">커뮤니티</a>
			<strong class="str">${boardName}</strong>
		</div>
	</div>
	
	<!-- 테이블상단 버튼영역 -->
	<div class="btn_board_sec">
		<div class="fl">
			<button class="btn_style2_2 btn_print_survey">출력</button>
		</div>
		<div class="fr">
			<button class="btn_style4_2 btn_list_survey">목록</button>
		</div>
	</div>
	<!-- //테이블상단 버튼영역 -->
	
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
					<li>
						<span class="tit">결과 공개여부</span>
						<span class="desc" id="surveyRsltOpenYn">공개</span>
					</li>
					<li>
						<span class="tit">참여 인원수</span>
						<span class="desc" id="surveyRsltMemberCnt"></span>
					</li>
				</ul>
			</div>
			<div class="intxt" id="notiConts">${notiConts}</div>
		</td>
	</tr>
	<tr id="apndFileTr">
		<td colspan="7">
		<div class="rbox02 mt0">
			<span class="top"></span>
			<div class="mid">
				<div class="inquiry_top">
					<div class="ico_file">
						<strong>첨부파일</strong>
						<ul id="ulNotiFile"></ul> 
					</div>
				</div>
			</div>
			<span class="btm"></span>
		</div>
		</td>
	</tr>
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

<!-- //입력테이블2 -->
<!-- 버튼영역 -->
<div class="btn_board_sec">
	<div class="fl">
		<button class="btn_style2_2 btn_print_survey">출력</button>
	</div>
	<div class="fr">
		<button class="btn_style4_2 btn_list_survey">목록</button>
	</div>
</div>
<!-- //버튼영역 -->

</div><!-- end of container -->
<iframe name="dummy" width=0 height=0 border=0 style="visibility:hidden"></iframe>
	
</body>
</html>	
	 