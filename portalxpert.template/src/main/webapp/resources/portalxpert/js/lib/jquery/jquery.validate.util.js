jQuery.validator.addMethod("invalidKor", function(value, element) {
	return !PortalCommon.isKor(value);
});
jQuery.validator.addMethod("invalidChars", function(value, element, param) {
	if(param != "") {
		return PortalCommon.isValidChar(value, param);
	}else {
		return true;
	}
});
jQuery.validator.addMethod("invalidCharsAll", function(value, element) {
	return PortalCommon.isValidChar(value);
});
jQuery.validator.addMethod("maxByte", function(value, element, param) {
	return PortalCommon.isValidMaxByte(value, param);
});
/*
jQuery.validator.addMethod("max", function(value, element) {
	return PortalCommon.isValidMax(value, element.getAttribute("max"));
});
jQuery.validator.addMethod("min", function(value, element) {
	return PortalCommon.isValidMin(value, element.getAttribute("min"));
});
*/
jQuery.extend(jQuery.validator.messages, {
    required: "항목은 필수입력 항목 입니다.",
    remote: "Please fix this field.",
    email: "Please enter a valid email address.",
    url: "Please enter a valid URL.",
    date: "Please enter a valid date.",
    dateISO: "Please enter a valid date (ISO).",
    number: "항목은 숫자로 입력해 주세요.",
    digits: "항목은 숫자로 입력해 주세요.",
    creditcard: "Please enter a valid credit card number.",
    equalTo: "Please enter the same value again.",
    accept: "Please enter a value with a valid extension.",
    maxlength: jQuery.validator.format("항목은 최대 {0} 자리 까지 입력이 가능 합니다."),
    minlength: jQuery.validator.format("항목은 최소 {0} 자리를 입력해 주세요."),
    rangelength: jQuery.validator.format("항목은 {0} 자리부터 {1} 자리까지만 입력이 가능 합니다."),
    range: jQuery.validator.format("항목의 범위는 {0} 부터 {1} 까지 입니다."),
    max: jQuery.validator.format("항목의 최대값은 {0} 입니다."),
    min: jQuery.validator.format("항목의 최소값은 {0} 입니다."),
    invalidKor: "항목은 한글 입력이 불가능 합니다.",
    invalidChars : "항목에 [ {0} ] 문자는 입력이 불가능 합니다.",
    invalidCharsAll : "항목에 특수 문자 입력이 불가능 합니다.",
    maxByte: "항목의 최대  길이는 {0} byte 입니다."
});

function initValidform(formList) {
	
	/*
	$.extend($.validator, {ignoreTitle:true});
	
	for(var i=0; i<arguments.length; i++) {
		var curForm = arguments[i];
		eval("$('#"+curForm+"').validate({ignoreTitle:true});");
	}
	*/
	
	/*
	var curForm = formList;
	eval("$('#"+curForm+"').validate();");
	
	//form의 element 가져오기
	var eForm = document.getElementById(curForm);
	
	eForm = document.forms(curForm);
	for(var i=0; i<eForm.elements.length; i++) {
		var ele = eForm.elements.item(i);
		
		if(ele.type.toLowerCase() != "button" && 
				ele.type.toLowerCase() != "hidden") {
			var evalString = "$('#"+ele.id+"').rules('add', {";

			if(ele.getAttribute("required") == "true") {
				evalString += "required: true,";
			}
			if(ele.getAttribute("number") == "true") {
				evalString += "number: true,";
			}
			if(ele.getAttribute("maxByte") != "") {
				evalString += "maxByte: " + ele.getAttribute("maxByte")+",";
			}
			if(ele.getAttribute("min") != "") {
				evalString += "min: " + ele.getAttribute("min")+",";
			}
			if(ele.getAttribute("max") != "") {
				evalString += "max: " + ele.getAttribute("max")+",";
			}
			if(ele.getAttribute("isKor") == "true") {
				evalString += "isKor: " + "true" +",";
			}
			if(ele.getAttribute("invalidChars") != "") {
				evalString += "invalidChars: " + "true" +",";
			}
			
			if(evalString.substring(evalString.length-1, evalString.length) == ",") {
				evalString = evalString.substring(0, evalString.length-1);
			}
			
			evalString += "});";
			
			//eval(evalString);
		}
	}
	*/
}
