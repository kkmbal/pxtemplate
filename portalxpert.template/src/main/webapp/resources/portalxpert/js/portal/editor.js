


var tinymce_setting = {
        script_url : '/js/lib/tinymce/tinymce.js',
        theme : "modern",
		language : "ko_KR",
		height : "100",
		//width : "472", 
		statusbar: false,
        plugins: [
                  "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
                  "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
                  "table contextmenu directionality emoticons template textcolor paste fullpage textcolor"
          ],

          toolbar1: "newdocument | bold italic underline strikethrough | alignleft aligncenter alignright alignjustify | styleselect formatselect fontselect fontsizeselect | forecolor backcolor",
          toolbar2: "cut copy paste | searchreplace | bullist numlist | outdent indent | undo redo | link unlink image media code | table | hr | charmap emoticons | ltr rtl | mybutton | preview | print",

          menubar: false,
          toolbar_items_size: 'small',
          
          style_formats: [
                  {title: 'Bold text', inline: 'b'},
                  {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
                  {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
                  {title: 'Example 1', inline: 'span', classes: 'example1'},
                  {title: 'Example 2', inline: 'span', classes: 'example2'},
                  {title: 'Table styles'},
                  {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
          ],
          
          font_formats: "굴림=굴림;궁서=궁서;돋움=돋움;맑은 고딕=맑은 고딕;바탕=바탕;"+
          "Andale Mono=andale mono,times;"+
          "Arial=arial,helvetica,sans-serif;"+
          "Arial Black=arial black,avant garde;"+
          "Book Antiqua=book antiqua,palatino;"+
          "Comic Sans MS=comic sans ms,sans-serif;"+
          "Courier New=courier new,courier;"+
          "Georgia=georgia,palatino;"+
          "Helvetica=helvetica;"+
          "Impact=impact,chicago;"+
          "Symbol=symbol;"+
          "Tahoma=tahoma,arial,helvetica,sans-serif;"+
          "Terminal=terminal,monaco;"+
          "Times New Roman=times new roman,times;"+
          "Trebuchet MS=trebuchet ms,geneva;"+
          "Verdana=verdana,geneva;"+
          "Webdings=webdings;"+
          "Wingdings=wingdings,zapf dingbats",
          
          setup: function(editor) {
              editor.addButton('mybutton', {
                  //text: 'My button',
            	  tooltip : '이미지파일첨부',
                  icon: true,
                  image: RES_HOME+'/images/img/uploadimage.png',
                  onclick: function() {
                	  cmdImgUploadPop();
                  }
              });
          }	
		/*
		plugins: [
		          "advlist autolink link image lists charmap print preview hr anchor pagebreak spellchecker",
		          "searchreplace wordcount visualblocks visualchars code fullscreen insertdatetime media nonbreaking",
		          "save table contextmenu directionality emoticons template paste textcolor"
		    ],
	    toolbar: "insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | l      ink image | print preview media fullpage | forecolor backcolor emoticons", 
	    style_formats: [
	         {title: 'Bold text', inline: 'b'},
	         {title: 'Red text', inline: 'span', styles: {color: '#ff0000'}},
	         {title: 'Red header', block: 'h1', styles: {color: '#ff0000'}},
	         {title: 'Example 1', inline: 'span', classes: 'example1'},
	         {title: 'Example 2', inline: 'span', classes: 'example2'},
	         {title: 'Table styles'},
	         {title: 'Table row 1', selector: 'tr', classes: 'tablerow1'}
	     ]
	     */
};
/**
 * TinyMCE Web Editor rendering function
 * @param res_home
 * @param id
 * @param param
 */
function fnMakeWebEditor(res_home, id, param){
	tinymce_setting = $.extend(tinymce_setting, param||{});
    tinymce_setting.script_url = res_home + tinymce_setting.script_url;
    $(id).tinymce(
		tinymce_setting
	);	
}

//이미지 버튼 클릭시 팝업 실제 파일업로드를 구성.
function cmdImgUploadPop(){
 var win = window.open(
		 			WEB_HOME+"/portalxpert/board/imageUp.do",
					"editor_win",
					"toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=no,resizable=0,width=450,height=150,left=50,top=50");
	win.focus();  
}