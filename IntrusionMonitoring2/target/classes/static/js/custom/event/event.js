$(document).ready(function(){
	$("#timeStart").datepicker(); 
	$("#timeEnd").datepicker(); 
});

//list
	//검색
	function goSearch(){
		var eventLocationStart = $("#searchForm [name=eventLocationStart]").val();
		var eventLocationEnd = $("#searchForm [name=eventLocationEnd]").val();
		if(eventLocationStart != null && eventLocationStart != '' && eventLocationEnd != null && eventLocationEnd != ''){
			if(Number(eventLocationStart) > Number(eventLocationEnd)){
				alert("시작 위치는 종료 위치보다 클 수 없습니다.");
				$("#searchForm [name=eventLocationStart]").val('');
				return;
			}
		}

		var eventTimeStart = $("#searchForm [name=eventTimeStart]").val();
		var eventTimeEnd = $("#searchForm [name=eventTimeEnd]").val();
		var regex = /^(19|20)\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$/;

		if(eventTimeStart != '' && eventTimeStart != null && !regex.test(eventTimeStart)){
			alert("날짜 형식이 틀렸습니다.");
			$("#searchForm [name=eventTimeStart]").val('');
			return;
		}
		if(eventTimeEnd != '' && eventTimeEnd != null && !regex.test(eventTimeEnd)){
			alert("날짜 형식이 틀렸습니다.");
			$("#searchForm [name=eventTimeEnd]").val('');
			return;
		}
		
		if(new Date(eventTimeStart) > new Date(eventTimeEnd)){
			alert("시간의 시작 시간은 종료 시간보다 클 수 없습니다.");
			$("#searchForm [name=eventTimeStart]").val('');
			return;
		}
		
		$('[name=search_form]').submit();
	}
	
	//페이지 이동
	function goPage(page) {
		$('[name=pageNo]').val(page);
		$('[name=list_form]').submit();
	}
	
	//엑셀 다운
	function excelDown(){
		$('[name=list_form]').attr('action','/eventHistory/excel/list');
		$('[name=list_form]').submit();
		$('[name=list_form]').attr('action','/eventHistory');
	}