
this.PortalCommon = {};

(function(PortalCommon){
	
	/*var loading = $('<img alt="loading" src="${RES_HOME}/images/img/loading.gif" />').appendTo(document.body).hide();
    $(window).ajaxStart(loading.show);
    $(window).ajaxStop(loading.hide);*/
	
	var timeout = 30000;//30 초;
	PortalCommon.timeout =timeout;
	
	
	PortalCommon.callback = function(callbackFunction){
		var func = (typeof callbackFunction == 'function') ?
	    callbackFunction : new Function(callbackFunction);
	    func();   
	};
	
	PortalCommon.callOpenerFunction = function(callbackFunction,param){
		this.callback("window.opener.window." + callbackFunction + "('" + param + "')"); 
	};
	
	PortalCommon.getByte = function (str) {
		var i, j = 0;
		for (i = 0; i < str.length; i++) {
			val = escape(str.charAt(i)).length;
			if (val == 6)
				j++;
			j++;
		}
		return j;
	};
	
	PortalCommon.isValidMaxByte = function (str, maxByte) {
		var i, j = 0;
		for (i = 0; i < str.length; i++) {
			val = escape(str.charAt(i)).length;
			if (val == 6)
				j++;
			j++;
		}
		if(j > maxByte) {
			return false;
		}else {
			return true;
		}
	};
	
	PortalCommon.msieversion = function() {
		var ua = window.navigator.userAgent;
		var msie = ua.indexOf("MSIE ");

		if (msie > 0) // If Internet Explorer, return version number
			return parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
		else
			// If another browser, return 0
			return 0;
	};
	
	PortalCommon.handlerException = function(jqXHR , exception ,that){

		//exception=jqXHR.statusText
		if (jqXHR.status == 404) {
	        alert('Requested page not found. [404] \n'+(that!=null ? that.url : ""));
	    } else if (jqXHR.status == 500) {
	    	if(jqXHR.responseText != ''){
	    		alert('Internal Server Error [500].'+(that != null ? JSON.stringify(that) : "") + ":" + exception + ":" + JSON.stringify(jqXHR));
	    	}
	    } else if (jqXHR.status == 401) {
	    	//session close
	    	//PortalCommon.getHome();
	    } else if (exception === 'timeout') {
	        alert('Time out error.');
		} else if (exception === 'parsererror') {
	        //alert('Requested JSON parse failed.');
	    } else if (exception === 'abort') {
	        alert('Ajax request aborted.');
		} else if (jqXHR.status === 0) {
	        alert("Can't connect to server");
	    } else {
	        //alert('Uncaught Error.\n' + jqXHR.responseText + (that != null ? JSON.stringify(that) : ""));
	    }

	};
	
	PortalCommon.getJson = function (param){	
		//blocking
		//var target = PortalCommon.blocking();
		$.ajax({
			url: param.url,
			type:  "POST" ,
			data: param.data,
			dataType: 'JSON',
			timeout: param.timeout === null ?  timeout : param.timeout,
			error: function(jqXHR , exception ){
				PortalCommon.handlerException(jqXHR , exception ,param);
				//PortalCommon.unblocking(target); //non-blocking
			},
			success: function(data){
				
				if (data.jsonResult != null)
				{
					var msg = data.jsonResult.message;
					//컨트롤러 정상처리
					if(data.jsonResult.success) {
						if(msg!=null && $.trim(msg)!=""){
							alert(msg);
						}
					}
					//컨트롤러 에러 처리
					else {
						if(msg!=null && $.trim(msg)!=""){
							var exceptionMsg = data.jsonResult.errMessage;
							if(exceptionMsg){
								alert(msg+"\n---------------------\n상세메세지 : " + exceptionMsg);
							}else{
								alert(msg);
							}
						}
					}
					param.success(data);
				}
				//PortalCommon.unblocking(target); //non-blocking
			}			
		});
	};
	
	PortalCommon.popupWindowCenter= function(URL, title,w,h){
		var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);
		var newWin = window.open (URL, title, 'toolbar=no, location=no,directories=no, status=no, menubar=no, scrollbars=no, copyhistory=no, width='+w+', height='+h+', top='+top+', left='+left+'');
		
	};
	
	PortalCommon.windowPopup= function(URL, title, w, h){
		var winl = (screen.width-w)/2;
		var wint = (screen.height-h)/2;
		var settings  ='height='+h+',';
		settings +='width='+w+',';
		settings +='top='+wint+',';
		settings +='left='+winl+',';
		settings +='toolbar=no, location=no, directories=no, status=no, menubar=no, resizable=yes, scrollbars=yes';
		
		var newWin = window.open (URL, title, settings);
		newWin.window.focus();
	};
	
	//조직도 모달폼으로 변경
	PortalCommon.popupWindowModal= function(URL, win, w, h){
		/*var left = (screen.width/2)-(w/2);
		var top = (screen.height/2)-(h/2);*/
		window.showModalDialog(URL, win, 'dialogWidth:'+w+'px;dialogHeight:'+h+'px;status:no;help:no; scroll:no');		
	};
	//조직도 모달폼으로 변경
	PortalCommon.callModalFunction = function(callbackFunction, param){		
		this.callback("window.dialogArguments." + callbackFunction + "('" + param + "')"); 
	};
	
	//스크롤바
	/*PortalCommon.scrollRebuild = function (id) {
		$(id).mCustomScrollbar({
			scrollButtons:{
				enable:true
			}
		});
	};
	
	PortalCommon.scrollDestroy = function (id) {
		$(id).mCustomScrollbar("destroy"); 
	};*/
	
	/**
	 * readonly 필드 세팅(비활성화 컨트롤)<br/>
	 * @param id 		- 컨트롤 ID <br/>
	 * @param bReadonly - readonly 여부 <br/>
	 * @type {void}
	 * @exam var ret = setReadOnly("testInput", true);
	 */
	PortalCommon.setReadOnly = function(id, bReadonly) {
		var ctrl = document.getElementById(id);
		ctrl.readOnly = bReadonly;
		if(bReadonly) {
			ctrl.setAttribute("oldBackgroundColor", ctrl.style.backgroundColor);
			ctrl.style.backgroundColor = "#EFEFEF";
		}
		else {
			ctrl.style.backgroundColor = ctrl.getAttribute("oldBackgroundColor");
		}
	};
	
	/*********************************************************
	 * validation 체크 
	 ********************************************************/
	/**
	 * 한글포함 문자열 여부 체크<br/>
	 * @type {boolean}
	 * @exam isKor = PortalCommon.isKor("한글1234테스트");
	 */
	PortalCommon.isKor = function (str) {
		var ret = (/[ㄱ-ㅎㅏ-ㅣ가-힣]/g).test(str);
		return ret;
	};
	
	/**
	 * 특수문자 체크<br/>
	 * 특수문자가 있으면 false 없으면 true<br/>
	 * @param str 검사대상 문자열<br/>
	 * @param invalidchars - invalid한 문자 character (여러개의 인자로 구분)<br/>
	 * @type {boolean}
	 * @exam var ret = isValidChar("텍스트@", "@'/\\&%");		//특정 문자 검사
	 * @exam var ret = isValidChar("텍스트@");				//전체 특수문자 검사
	 */
	PortalCommon.isValidChar = function (str, invalidChars){
		if(invalidChars == "" || invalidChars == null) {
			_invalidChars = "^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣_";
		}
		else if(invalidChars.toLowerCase() == "all" ) {
			_invalidChars = "^a-zA-Z0-9ㄱ-ㅎㅏ-ㅣ가-힣_";
		}
		else {
			_invalidChars = invalidChars;
			if(_invalidChars.indexOf("\\") > -1) {
				_invalidChars = _invalidChars.replace(/\\/g, "\\\\");
			}
		}
		//특수문자 검사
		//var re = new RegExp("["+_invalidChars+"]", "g");
		var re = new RegExp("["+_invalidChars+"]", "g");
		var ret = re.test(str);

		return !ret;
	};
	
	/**
	 * 숫자 체크
	 * @type {boolean}
	 * @exam isNum = PortalCommon.isNum(str);
	 */
	PortalCommon.isNum = function(str){
		return (/\D/g).test(str);
	};
	
	/**
	 * 최대값
	 * @type {boolean}
	 * @exam isNum = PortalCommon.isNum(str);
	 */
	PortalCommon.isValidMax = function(str, max){
		return Number(str) <= Number(max);
	};
	
	/**
	 * 최소값
	 * @type {boolean}
	 * @exam isNum = PortalCommon.isNum(str);
	 */
	PortalCommon.isValidMin = function(str, min){
		if(min == "") return true;
		return Number(str) >= Number(min);
	};
	
	
	PortalCommon.showProcessMessage = function(obj){
		
		$(obj).empty();
		$(obj).append('<span><img src="../resources/images/img/loadinfo.gif"/><font color="red"> 로딩중입니다. 잠시만 기다려주세요...</font></span>');		
		
		$( obj ).dialog
		(
				{  
					dialogClass:'no-title',
					height: 45,      
					width: 300,
					modal: true,
					resizable : false,
					//disabled : true,
					draggable : false
				}
		);		
	    
	};
	
	PortalCommon.closeProcessMessage = function(obj){
		$(obj ).dialog( "close" );
	};
	
	PortalCommon.blocking = function(){
		var target = null;
		try{
			if(window.event){
				var event = window.event;
				target = (event.target) ? event.target : event.srcElement;
			}
			$(target).parent().block({
	            message: null,
	            overlayCSS: { backgroundColor:'#ffffff',opacity:0.1 }
	        });
		}catch(e){}
		return target;
	};
	
	PortalCommon.unblocking = function(target){
		try{ $(target).parent().unblock(); }catch(e){}
	};

	PortalCommon.getHome = function(){
		var h = location.href;
		var d = location.host;
		var c = h.replace('http://'+d, '');
		var href_context = c.substring(0, c.indexOf('/', 1));
		
    	setTimeout(function() { top.location.href = href_context + "/index.html"; }, 1000);
    	$("body").append("<div id='sess_dialog' style='top:100px;left:150px;position:absolute;background-color:transparent;background:#FFFFFF;z-index:999'>세션이 종료되었습니다.</div>");
		
	};
	
	/**
	 * 부서정보팝업
	 */
	PortalCommon.userDeptInfoPop = function(url){
		PortalCommon.windowPopup(url, "deptDetailInfo", 598, 485);
	};
	
	/**
	 * 사용자상세정보팝업
	 */
	PortalCommon.userInfoPop = function(url){
		PortalCommon.popupWindowCenter(url, "memberDetailInfo", 650, 420);
	};

	
	/**
	 * 이미지 resize
	 */
	PortalCommon.fnImgPreviewResize = function(img, bWidth, bHeight){

		// 원본 이미지 사이즈 저장
		var rtVal = new Array();
		var user_pic_imgObj = new Image();
		user_pic_imgObj.src = img;
		var orgWidth = user_pic_imgObj.width;
		var orgHheight = user_pic_imgObj.height;
		var resizeWidth = bWidth;
		var resizeHeight = bHeight;
		// 가로, 세로 최대 사이즈 설정
		var maxWidth = bWidth;   // 원하는대로 설정. 픽셀로 하려면 maxWidth = 100  이런 식으로 입력
		var maxHeight = bHeight;   // 원래 사이즈 * 0.5 = 50%
		
		// 가로나 세로의 길이가 최대 사이즈보다 크면 실행  
		if(orgWidth > maxWidth || orgHheight > maxHeight){
		
		   // 가로가 세로보다 크면 가로는 최대사이즈로, 세로는 비율 맞춰 리사이즈
		   if(orgWidth > orgHheight){
		      resizeWidth = maxWidth;
		      resizeHeight = Math.round((orgHheight * resizeWidth) / orgWidth);
		
		   // 세로가 가로보다 크면 세로는 최대사이즈로, 가로는 비율 맞춰 리사이즈
		   }else{
		      resizeHeight = maxHeight;
		      resizeWidth = Math.round((orgWidth * resizeHeight) / orgHheight);
		   }
		
		// 최대사이즈보다 작으면 원본 그대로
		}else{
		   resizeWidth = orgWidth;
		   resizeHeight = orgHheight;
		}
		
		rtVal[0] = resizeWidth;
		rtVal[1] = resizeHeight;
		
		return rtVal;
		
	};
	

	
	/**
	 * 이미지 onload resize
	 */
	PortalCommon.fnImgPreviewOnloadResize = function(img, bWidth, bHeight){

		// 원본 이미지 사이즈 저장
		var orgWidth = img.width;
		var orgHheight = img.height;
		
		var resizeWidth = bWidth;
		var resizeHeight = bHeight;
		// 가로, 세로 최대 사이즈 설정
		var maxWidth = bWidth;   // 원하는대로 설정. 픽셀로 하려면 maxWidth = 100  이런 식으로 입력
		var maxHeight = bHeight;   // 원래 사이즈 * 0.5 = 50%
		
		// 가로나 세로의 길이가 최대 사이즈보다 크면 실행  
		if(orgWidth > maxWidth || orgHheight > maxHeight){
		
		   // 가로가 세로보다 크면 가로는 최대사이즈로, 세로는 비율 맞춰 리사이즈
		   if(orgWidth > orgHheight){
		      resizeWidth = maxWidth;
		      resizeHeight = Math.round((orgHheight * resizeWidth) / orgWidth);
		
		   // 세로가 가로보다 크면 세로는 최대사이즈로, 가로는 비율 맞춰 리사이즈
		   }else{
		      resizeHeight = maxHeight;
		      resizeWidth = Math.round((orgWidth * resizeHeight) / orgHheight);
		   }
		
		// 최대사이즈보다 작으면 원본 그대로
		}else{
		   resizeWidth = orgWidth;
		   resizeHeight = orgHheight;
		}
		
		//리사이즈한 크기로 이미지 크기 다시지정
		img.width = resizeWidth;
		img.height = resizeHeight;
		
	};
	
	/**
	 * 이미지 UPLOAD FILE 확장자 체크
	 */	
	PortalCommon.imgUploadFileCheck = function(fileName){
		var enableImg = "\.(gif|jpg|png|bmp)$";
		//var selectImg = fileform.upFile.value;
		if (!(new RegExp(enableImg, "i")).test(fileName)) {
			return false;
		}
		return true;
	};
	
	/**
	 * 실행가능 UPLOAD FILE 확장자 체크
	 */	
	PortalCommon.execUploadFileCheck = function(fileName){
		var enableImg = "\.(java|class|jsp|php|php3|php5|asp|aspx|cfm|bat|exe|dll|cgi|js)$";
		if ((new RegExp(enableImg, "i")).test(fileName)) {
			return false;
		}
		return true;
	};
	
	/**
	 * 동영상 UPLOAD FILE 확장자 체크
	 */	
	PortalCommon.movieUploadFileCheck = function(fileName){
		var enableImg = "\.(avi|mp4|wmv|mpeg|asf|flv)$";
		//var selectImg = fileform.upFile.value;
		if (!(new RegExp(enableImg, "i")).test(fileName)) {
			return false;
		}
		return true;
	};	
	
	/**
	 * 현재 날자를 yyyy-mm-dd 형식으로 반환
	 */	
	PortalCommon.getNowDate = function() {
		var d = new Date();

		var s = 
	    leadingZeros(d.getFullYear(), 4) + '-' +
	    leadingZeros(d.getMonth() + 1, 2) + '-' +
	    leadingZeros(d.getDate(), 2); 
//	    + ' ' +
//
//	    leadingZeros(d.getHours(), 2) + ':' +
//	    leadingZeros(d.getMinutes(), 2) + ':' +
//	    leadingZeros(d.getSeconds(), 2);

		return s;
	};
	
	/**
	 * 현재 시분을 hh:mm 형식으로 반환
	 */	
	PortalCommon.getNowHourMin = function() {
		var d = new Date();

		var s = 
	    leadingZeros(d.getHours(), 2);+ ':' +
	    leadingZeros(d.getMinutes(), 2);
//		+ ':' +
//	    leadingZeros(d.getSeconds(), 2);

		return s;
	};
	

	function leadingZeros(n, digits) {
		var zero = '';
		n = n.toString();

		if (n.length < digits) {
			for (var i = 0; i < digits - n.length; i++)
				zero += '0';
		}
		return zero + n;
	}
	
})(this.PortalCommon);

/*********************************************************
 * String 확장 메소드 
 ********************************************************/
/**
 * 문자열의 특정문자열을 모두 replace한다.<br>
 * String.replace의 확장.<br>
 * @type {string}
 * @exam value = "가나다라".replaceAll("1234", "5678");
 */
String.prototype.replaceAll = function(as_rex, as_out) {
	return this.replace(new RegExp(as_rex, "g"), as_out);
};

/**
 * 문자열의 좌우공백을 제외한 문자열을 리턴한다.<br>
 * @return 좌우 공백을 제외한 문자열
 * @type {string}
 * @exam value = " 가나다라 ".trim();
 */
String.prototype.trim = function() {
	return this.replace(/(^\s*)|(\s*$)/g,"");
};

/**
 * 모든공백을 제외한 문자열을 리턴한다.<br>
 * @return 모든 공백을 제외한 문자열
 * @type {string}
 * @exam value = " 가나 다라 ".trimAll();
 */
String.prototype.trimAll = function() {
	return this.replace(/\s/g,"");
};

/**
 * 문자열을 iLength만큼 sText 문자열로 채워서 반환한다.<br>
 * @param sText 채울 문자(default : 공백)
 * @param iLength 채울 문자열 길이
 * @param sPosition 채울 위치 ("left" or "right", default : "left")
 * @type {string}
 * @exam value = "가나다라".fillText("*", 10);
 * @exam value = "가나다라".fillText("*", 10, "left");
 */
String.prototype.fillText = function(sText, iLength, sPosition) {
	var fillText = "";

	if(sText == null || sText == "") {
		sText = " ";
	}
	
	if(sPosition == null || SPosition == "") {
		sPosition = "left";
	}

	for(var i=this.length; i<iLength; i++) {
		fillText += sText;
	}

	if(sPosition == "left") {
		return fillText + this;
	}else {
		return this + fillText;
	}
};

/**
 * Date 형태로 반환한다.<br>
 * 단, 문자열로 된 날짜 정보는 8자로 되어있어야 한다. ex) "20081008"
 * @return 날짜객체
 * @type {date}
 * @exam d_date = "20081008".toDate();
 */
String.prototype.toDate = function() {
	var sYear = Number(this.substring(0, 4));
	var sMonth = Number(this.substring(4, 6))-1;
	var sDate = Number(this.substring(6, 8));

	var tDate = new Date(sYear, sMonth, sDate);
	return tDate;
};

Date.prototype.dateToString = function(){
	var sYear = this.getFullYear();
	var sMonth = this.getMonth() + 1;
	var sDate = this.getDate();
	
	return ''+sYear+(sMonth<10?'0'+sMonth:sMonth)+(sDate<10?'0'+sDate:sDate);
};

/**
 * Date객체형 날자에 일수를 더하여 문자로 반환
 * @param dateObj Date객체형 날자
 * @param dates 더할 날수
 * @returns {날짜객체}
 * @exam  add_DATEs("2012-12-31", 3) 이면 20130103을 리턴
 */
function add_dates(dateObj, dates) {
	var int_millisecond = 1;
	var int_second      = 1000 * int_millisecond;
	var int_minute      = 60 * int_second;
	var int_hour        = 60 * int_minute;
	var int_day         = 24 * int_hour;
	
//	var YY_form = CInt(Left(dateformat, 4));
//	var MM_form = CInt(Mid(dateformat, 5, 2))-1;
//	var DD_form = CInt(Right(dateformat, 2));
	
	var date = dateObj;
	
	var date_milliseconds = date.valueOf();
	var add_milliseconds  = dates * int_day;
	var ret_date = new Date(date_milliseconds + add_milliseconds);
	
	var year  = ret_date.getFullYear();
	var month = ret_date.getMonth() + 1;
	if ( month < 10 ) {
		month = "0" + month;
	}
	var day   = ret_date.getDate();
	if ( day < 10 ) {
		day = "0" + day;
	}
	
	return ( "" + year + month + day );
}

function add_months(dateStr, months) {
	var oriMonth = dateStr.substring(4 ,6);
	
	var addMonth = parseInt(oriMonth ,10)+months;
	var ret_date = (dateStr.substring(0 ,4)+(addMonth<10?'0'+addMonth:addMonth)+dateStr.substring(6)).toDate();	
	
	var year  = ret_date.getFullYear();
	var month = ret_date.getMonth() + 1;
	if ( month < 10 ) {
		month = "0" + month;
	}
	var day   = ret_date.getDate();
	if ( day < 10 ) {
		day = "0" + day;
	}
	
	return ( "" + year + month + day );
}

function getDateWeek(date){
	var dateObj = date.toDate();
	var week = new Array('일', '월', '화', '수', '목', '금', '토');

	return week[dateObj.getDay()];
}
