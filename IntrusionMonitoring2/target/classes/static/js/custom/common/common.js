$(document).ready(function(){
	//현재 시간 표시(주기 1초)
	getTimeSetting(); 
	setInterval(getTimeSetting, 1000); 
	
	//이벤트 발생 갯수 세팅(주기 1분) 3초
	getEventCntSetting(); 
	setInterval(getEventCntSetting, 3000);
	
	//데이터 피커 
	$.datepicker.setDefaults({
        dateFormat: 'yy-mm-dd' //Input Display Format 변경
        ,showOtherMonths: true //빈 공간에 현재월의 앞뒤월의 날짜를 표시
        ,showMonthAfterYear:true //년도 먼저 나오고, 뒤에 월 표시
        ,changeYear: true //콤보박스에서 년 선택 가능
        ,changeMonth: true //콤보박스에서 월 선택 가능                
        ,showOn: "both" //button:버튼을 표시하고,버튼을 눌러야만 달력 표시 ^ both:버튼을 표시하고,버튼을 누르거나 input을 클릭하면 달력 표시  
        ,buttonImage: "/resources/css/lib/images/icon_datepicker.png" //버튼 이미지 경로
        ,buttonImageOnly: true //기본 버튼의 회색 부분을 없애고, 이미지만 보이게 함
        ,buttonText: "선택" //버튼에 마우스 갖다 댔을 때 표시되는 텍스트                
        ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
        ,monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'] //달력의 월 부분 텍스트
        ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
        ,dayNamesMin: ['일','월','화','수','목','금','토'] //달력의 요일 부분 텍스트
        ,dayNames: ['일요일','월요일','화요일','수요일','목요일','금요일','토요일'] //달력의 요일 부분 Tooltip 텍스트          
    });
     
	//레프트 메뉴 하위 메뉴 슬라이더
	$("#gnb > ul > li").on('mouseover', function () {
		if(!$(this).hasClass("on")){
			if($(this).children("ul").css("display") == "none"){
				$(this).children("ul").slideDown(300);  
			}
		}
	});
	$("#gnb > ul > li").on('mouseleave', function () {
		if(!$(this).hasClass("on")){
			 $(this).children("ul").slideUp(300);  
		}
	});
	 
	// 상단 이벤트 발생 슬라이더
	$(".hearder > ul > .alarm").on('mouseover', function () {
		if($(this).children("ul").css("display") == "none"){
			$(this).children("ul").slideDown(100);  
		}
	});
	$(".hearder > ul > .alarm").on('mouseleave', function () {
			 $(this).children("ul").slideUp(200);  
	});

});
	function monthPicker(obj){
		$(obj).MonthPicker({ 
			 Button: '<img class="icon" src="/resources/css/lib/images/icon_datepicker.png" />'
		     ,MonthFormat: 'yy-mm'
		});
	}
	
	function yearPicker(obj,val){
		 $(obj).yearpicker({
		     year: Number(val),
		     startYear: 2012,
		     endYear: 2030
		 });
		 $(obj).after("<img class='icon' src='/resources/css/lib/images/icon_datepicker.png' >");
		 
	}

	//현재시간 세팅
	function getTimeSetting(){
		var clock = document.querySelector('.clock');
		let today = new Date(); 
		let year = today.getFullYear(); // 년도
		let month = today.getMonth() + 1;  // 월
		let date = today.getDate();  // 날짜
		const time = new Date();
		const hour = time.getHours(); // 시
		const minutes = time.getMinutes(); // 분
		const seconds = time.getSeconds(); // 초	
		
		clock.innerHTML = `${year}-${month<10 ? `0${month}`:month}-${date<10 ? `0${date}`:date} ${hour<10 ? `0${hour}`:hour}:${minutes<10 ? `0${minutes}`:minutes}:${seconds<10 ? `0${seconds}`:seconds}`;
	}
	
	//이벤트 발생 갯수 세팅
	function getEventCntSetting(){
		rAjaxCall.async("/main/ajaxEventCnt", 'POST', null, function(r) {
			var eventCntList = r.eventCntList;
			var eventCnt = 0;
			if(eventCntList.length > 0){
				$(".eventCnt").children("li").remove();
				for(var i = 0; i < eventCntList.length; i++){
					$(".eventCnt").append("<li><a href='/eventHistory?monitoring=Y&eventCode="+eventCntList[i].codeId+"'><span>"+eventCntList[i].codeName+"</span> :&nbsp;&nbsp;<b>"+eventCntList[i].eventCnt+"</b>개</a></li>");
					eventCnt = eventCnt+Number(eventCntList[i].eventCnt);
				}
			}
			
			$(".hearder > ul > li:eq(0) > a > b").html(eventCnt);
		});	
	}
	
	//레이어 팝업 열기
	function popOpen(popupId){
		$("#"+popupId).show();
	}
	
	//레이어 팝업 닫기
	function popClose(popupId){
		$("#"+popupId).hide();
	}
	
	//숫자만 입력되게 체크
	function onlyNumber(obj){
		var str = obj.value;
		obj.value = str.replace(/[^0-9.]/g,"");;
	}
	
	//해당 obj가 compVal값과 같은지 체크
	function getComparison(obj, compVal, nullChk){
		if(nullChk == null) {
			nullChk = true;
		}
		if(obj == null){
			obj = "";
		}
		if("" != obj) {
			if(obj == compVal) {
				return true;
			}else {
				return false;
			}
		}else { 
			if(nullChk) {
				return false;
			}else {
				return true;
			}
		}
	}
	
	//쿠키 넣기
	function setCookie(name, value, exp) {
		var date = new Date();
		date.setTime(date.getTime() + exp*24*60*60*1000);
		document.cookie = name + '=' + value + ';expires=' + date.toUTCString() + ';path=/';
	};
	
	//쿠키 가져오기
	var getCookie = function(name) {
		var value = document.cookie.match('(^|;) ?' + name + '=([^;]*)(;|$)');
		return value? value[2] : '';
	};
