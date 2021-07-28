$(document).ready(function(){

	makeDatePicker();
	selectBox();
	clFoucsFn();
	tabCtrlFn();
	popAlignFn();
	fileFindFn(".fileFind_wrap .fileFind input");

	//$("input, textarea").placeholder();

});

$(function () {

  "use strict";
  /*  
  $(".connectedSortable").sortable({
  placeholder: "sort-highlight",
  connectWith: ".connectedSortable",
  handle: ".box-header, .nav-tabs",
  forcePlaceholderSize: true,
  zIndex: 999999
  });
  $(".connectedSortable .box-header, .connectedSortable .nav-tabs-custom").css("cursor", "move");
  */
  !function(a){a.fn.datepicker.dates.kr={days:["일요일","월요일","화요일","수요일","목요일","금요일","토요일","일요일"],daysShort:["일","월","화","수","목","금","토","일"],daysMin:["일","월","화","수","목","금","토","일"],months:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"],monthsShort:["1월","2월","3월","4월","5월","6월","7월","8월","9월","10월","11월","12월"]}}(jQuery);
});

function makeDatePicker(id) {
	$('#'+id).datepicker({
		format: "yyyy-mm-dd",
		weekStart: 1,
		orientation: "auto",
		autoclose: true,
		todayHighlight: true,
		language: 'kr'
	});
}

var setSlider = {
	"mode" : "fade"
}



/* function
------------------------------------------------------------------------------------------------- */

/* selectbox */
function selectBox(){
	$(document).on("change", ".selectBox01 select", function(){
		$(this).prev().text($(this).find("option:selected").text());
	});
}

/* calendar */
function clFoucsFn(){
	$(document).on("click", ".clEntry .btnC", function(){
		$(this).parent().find("input").focus();
		return false;
	});
}

/* tab */
function tabCtrlFn(){
	$(".tabCtrl_wrap .tabCont").hide();
	$(".tabCtrl_wrap .tabCont").eq(0).show();

	$(document).on("click", ".tabCtrl_wrap .tab_wrap01 li a", function(){
		$(this).parent().parent().find("li").removeClass("on");
		$(this).parent().addClass("on");
		$(this).closest(".tabCtrl_wrap").find(".tabCont").hide();
		$(this).closest(".tabCtrl_wrap").find(".tabCont").eq($(this).parent().index()).show();
		return false;
	});
}

/* popup auto align */
function popAlignFn(){
	$(".popup_container").css({
		"margin-left" : function(){return parseInt(-($(this).width()/2)) + "px";},
		"margin-top" : function(){return parseInt(-($(this).height()/2)) + "px";}
	});
}

/* 파일찾기 */
function fileFindFn(trg){
	$(trg).change(function(){
		$(this).parent().prev(".fileName").text($(this).val());
		//$(this).parent().prev().find("input").val($(this).val());
	});
}
