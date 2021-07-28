$(document).ready(function(){
	fncSetting();

	//장비 상태는 1분 마다 갱신
	//equipmentState();
	//setInterval(equipmentState, 60000);
	
	//이벤트는 발생되었을때 갱신
	fncEventCheck();
	setInterval(fncEventCheck, 3000);


	
});

	function fncSetting(){
		//지도 세팅 후 선 긋기
		fncMapSetting();
		//이벤트 목록  > map에 이벤트 아이콘 세팅
	    fncEventSearch();
		//이벤트 차트
		//fncEventChartSetting();
	}

	 

	//장비 상태
	function equipmentState(){
		var param = {};
		rAjaxCall.asyncH("/monitoring/content/equipmentState", 'POST', param, function(data) {
			$('.equipment').html("");
			$('.equipment').html(data);
		});	
	}

    var monitoring_eventId = "";
	var monitoring_eventCnt = 0;
	var eventCheck = true;
	//이벤트는 발생되었을때 갱신
	function fncEventCheck(){
		var param = {
			"monitoring":"Y"
		}
		
		rAjaxCall.async("/monitoring/ajaxEventCnt", 'POST', param, function(data) {
			if(data.newEventCnt > 0){
				fncEventSearch();
				eventCheck = true;
			}else{
				if(data.eventCnt == 0){
					$("#eventList > tbody > tr").remove();
					$("#eventList > tbody").append("<tr class='noData'><td colspan='3' class='TxtC'>데이터가 없습니다.</td></tr>");
					if(eventCheck){
						//이벤트 총계
						fncMonitoringSearch();
						//지도
						fncMapSetting();
						eventCheck = false;
					}
				}else{
					if(monitoring_eventCnt != data.eventCnt){
						fncEventSearch();
						eventCheck = true;
					}
				}
			}
			monitoring_eventCnt = data.eventCnt;
		});
	}
	
	
			
	//이벤트 아이콘
	function fncEventSearch(){
		var selectId = monitoring_eventId;
		var eventCode = $("#eventSearch").val();
		var param = {
				"monitoring":"Y"
				,"eventCode":eventCode
		};
		rAjaxCall.asyncH("/monitoring/content/eventList", 'POST', param, function(data) {
			$('#eventListDiv').html("");
			$('#eventListDiv').html(data);
			
			//지도에 이벤트 아이콘
			fncMapEvent(eventCode);
			
			//이벤트 총계
			fncMonitoringSearch();
			
			/* var dts_fnc = $(".eventIcon > ul").children(".selected").attr("onclick"); //DTS 이벤트
			if(typeof dts_fnc == "string"){
				eval(dts_fnc);
			}
			
			monitoring_eventId = selectId; */
		});	
	}

	//이벤트 카운드
	function fncMonitoringSearch(){
		var selectId = monitoring_eventId;
		var eventCode = $("#eventSearch").val();
		var param = {
				"monitoring":"Y"
		};
		rAjaxCall.asyncH("/monitoring/content/monitoring", 'POST', param, function(data) {
			$('#eventCntListDiv').html("");
			$('#eventCntListDiv').html(data);
			
		});	
	}
	
		
	//24시간 이벤트 통계 차트
	function fncEventChartSetting(){
		rAjaxCall.async("/eventStats/ajaxEventTime", 'POST', null, function(r) {
			var categories = r.titmeData.categories;
			var series = r.titmeData.data;
			var max = r.titmeData.max;
			
			Highcharts.chart("eventChart", {
			    chart: {
		            backgroundColor: '#293948'
		            ,borderWidth: 0
		            ,plotBackgroundColor: '#293948'
		            ,plotShadow: false
		            ,plotBorderWidth: 0
		            ,style: {
		                color: '#FFFFFF'
		            }
			    	,type: 'column'
		        }
		        ,title: {text: ''}
			    ,xAxis: {
			        categories: categories
			        ,labels: {
		                style: {
		                    color: '#FFFFFF'
		                }
		            }
			    }
			    ,yAxis: {
			        min: 0
			        ,max: max
			        ,title: {text: ''}
			        ,labels: {
			            style: {
			                color: '#FFFFFF'
			            }
			        }
			    }
			    ,tooltip: {
			        headerFormat: '<b>{point.x}시</b><br/>'
			        ,pointFormat: '{series.name}: {point.y}<br/>Total: {point.stackTotal}'
			    }
			    ,plotOptions: {
			        column: {
			            stacking: 'normal',
			            dataLabels: {
			                enabled: true
			            }
			        }
			    }
			    ,series: series
			    ,legend: {
			        layout: 'vertical'
			        ,align: 'right'
			        ,verticalAlign: 'top'
			        ,x: 0
			        ,y: 0
			        ,floating: true
			        ,borderWidth: 1
			        ,backgroundColor: Highcharts.defaultOptions.legend.backgroundColor || '#293948'
			        ,shadow: true
			        ,itemStyle: {
		                   color: '#FFFFFF'
		            }
				    ,itemHoverStyle: {
			        	color: '#FFFFFF'
			        }
			    }
			});
		});
	}
			
	var mapContainer;
	var map;
	var mapTypeControl;
	var zoomControl;
	var mapInfoList;
	var railroadInfoList;
	var cctvList;
	var eventList;
	var trainList;
	var cctvMarker = new Array();
	var eventMarker = new Array();
	var trainMarker = new Array();
	var eventOverlay = new Array();
	var trainOverlay = new Array();

	function fncMapSetting(){
		/* 지도 세팅 S */ 
			mapContainer = document.getElementById('map'),  // 지도를 표시할 div  
			mapOption = { 
			    center: new kakao.maps.LatLng(35.23122738046199,126.86080512997357 ), // 지도의 중심좌표
			    level: 1 // 지도의 확대 레벨
			};// 지도 option
			map = new kakao.maps.Map(mapContainer, mapOption); // 지도를 생성
			
			//스카이 뷰로 세팅
			setMapType('skyview');
			// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
//			var zoomControl = new kakao.maps.ZoomControl();
//			map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
			zoomIn();
		/* 지도 세팅 E */
		
		/* 전체 선로정보 array에 넣기 S */	
			var param = {};
			mapInfoList = rAjaxCall.sync("/monitoring/ajaxMapInfo", 'POST', param);
			var latArray = new Array(); 
			var lngArray = new Array();
			if(mapInfoList != null){
				for(var i = 0; i < mapInfoList.length; i++){ 
					latArray.push(parseFloat(mapInfoList[i].lattitude));
					lngArray.push(parseFloat(mapInfoList[i].longitude));
				}
			}
		/* 전체 선로정보 array에 넣기 E */
			
		/* 지도에 DTS 위치 세팅 S */	
			new kakao.maps.Marker({ // 마커를 생성
		        map: map, // 마커를 표시할 지도
		        position:  new kakao.maps.LatLng(latArray[0], lngArray[0]), // 마커를 표시할 위치
		        title : "DAS", // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        zIndex : 0,
		        image :  new kakao.maps.MarkerImage('/resources/css/lib/images/DAS.png',  new kakao.maps.Size(45, 35), new kakao.maps.Point(21,31)) // 마커 이미지 
		    });
		/* 지도에 DTS 위치 세팅 E */	
			
		/* 지도에 선 정보 세팅 S */
			var polyline;
			var color = "";
			var linePath = new Array();         
			
			var zoneType = "";;
			var lineStr;
			var lineEnd;
			if(mapInfoList != null){
				for(var i = 0; i < mapInfoList.length; i++){ 
					zoneType = mapInfoList[i].zoneType;
					if(zoneType == "ZONE_01"){ //펜스
						color = "#4BACC6";
					}else if(zoneType == "ZONE_02"){ // 매립
						color = "#9BBB59";
					}else{ 
						color = "#000000";
					}
					
					if(i < (mapInfoList.length-1)){
						linePath.push(new kakao.maps.LatLng(latArray[i], lngArray[i]));
						linePath.push(new kakao.maps.LatLng(latArray[i+1], lngArray[i+1]));
						
						polyline = new kakao.maps.Polyline({
						    path: linePath, // 선을 구성하는 좌표배열 입니다
						    strokeWeight: 4, // 선의 두께 입니다
						    strokeColor: color, // 선의 색깔입니다
						    strokeOpacity: 1, // 선의 불투명도 입니다 1에서 0 사이의 값이며 0에 가까울수록 투명합니다
						    strokeStyle: 'solid' // 선의 스타일입니다
						});
						polyline.setMap(map);
						linePath = new Array();
					}
					
				}
			}
		/* 지도에 선 정보 세팅 E */
		
	}
	
	var eventOverlay = new Array();
	var eventMarker = new Array();	
	function fncMapEvent(eventCode){
		
		//event reset
		if(eventMarker != null){
			for(var i = 0; i < eventMarker.length; i++){
				eventMarker[i].setMap(null);
			}
			eventMarker = [];
		}
		if(eventOverlay != null){
			for(var i = 0; i < eventOverlay.length; i++){
				eventOverlay[i].setMap(null);
			}
			eventOverlay = [];
		}
		
		var param = {
			"eventCode" : eventCode
		};
		eventList = rAjaxCall.sync("/monitoring/ajaxEventInfo", 'POST', param);
		var eventLocation; 
	    var eventLat; 
	    var eventLng;
		var eventImg = ""; 
		var eventImgSize = new kakao.maps.Size(39, 58); // 마커 사이즈 지정   
	    var eventMarerImage = new kakao.maps.MarkerImage(eventImg, eventImgSize); // 마커 이미지를 생성합니다    
	    var imageOption = {offset: new kakao.maps.Point(19, 50)};
	    var eventCodeStr =  "";
		for(var i = 0; i < eventList.length; i++){
			eventCodeStr =  eventList[i].eventCode;
			eventCodeStr = eventCodeStr.toUpperCase();
			eventLocation =  eventList[i].eventLocation;
		
			
			eventImg = "/resources/css/lib/images/"+eventCodeStr+"_map_icon.png"; 
			
			eventLat = parseFloat(mapInfoList[eventLocation].lattitude);
			eventLng = parseFloat(mapInfoList[eventLocation].longitude);
			eventMarerImage = new kakao.maps.MarkerImage(eventImg, eventImgSize,imageOption); 
			
			// 마커를 생성합니다
			eventMarker[i] = new kakao.maps.Marker({
		        map: map, // 마커를 표시할 지도
		        position:  new kakao.maps.LatLng(eventLat, eventLng), // 마커를 표시할 위치
		        title : eventList[i].eventName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
		        zIndex : 99,
		        image : eventMarerImage // 마커 이미지 
		    });
		    
			
		   // var content = '<div class="overlay_info">';
		    var content = '<div class="eventinfo_wrap">';
		    content += '		<div class="eventinfo">';
		    content += '    		<ul class="eventinfo_ul">';
		    content += '        		<li>'+eventList[i].eventTime+'</li>';
		    content += '        		<li>이벤트명 : '+eventList[i].eventCodeNm+'</li>';
		    content += '        		<li>구간 : '+eventList[i].eventZoneNm+'</li>';
		   /* content += '        		<li>위치: '+eventList[i].eventLocation+'m</li>';*/
		    content += '    		</ul>';
		    content += '		</div>';
		    content += '	</div>';
		    
		    eventOverlay[i] = new kakao.maps.CustomOverlay({
		        content: content,
		        map: map,
		        position: eventMarker[i].getPosition()     
		        ,zIndex :100
		    });
			
		    eventOverlay[i].setMap(null);   
		    
		    kakao.maps.event.addListener(eventMarker[i], 'mouseover', makeOverListener(map, eventOverlay[i]));
		    kakao.maps.event.addListener(eventMarker[i], 'mouseout', makeOutListener(eventOverlay[i]));
		    kakao.maps.event.addListener(eventMarker[i], 'click', clickListener(eventList[i].eventId));
		}
	}
	
	function rand(min, max) {
	  return Math.floor(Math.random() * (max - min + 1)) + min;
	}
	

	// 지도타입 컨트롤의 지도 또는 스카이뷰 버튼을 클릭하면 호출되어 지도타입을 바꾸는 함수입니다
	function setMapType(maptype) { 
	    var roadmapControl = document.getElementById('btnRoadmap');
	    var skyviewControl = document.getElementById('btnSkyview'); 
	    if (maptype === 'roadmap') {
	        map.setMapTypeId(kakao.maps.MapTypeId.ROADMAP);    
	        roadmapControl.className = 'selected_btn';
	        skyviewControl.className = 'btn';
	    } else {
	        map.setMapTypeId(kakao.maps.MapTypeId.HYBRID);    
	        skyviewControl.className = 'selected_btn';
	        roadmapControl.className = 'btn';
		}
		map.setDraggable(true);    
	}

	// 지도 확대, 축소 컨트롤에서 확대 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomIn() {
	    map.setLevel(map.getLevel() - 1);
	}
	
	// 지도 확대, 축소 컨트롤에서 축소 버튼을 누르면 호출되어 지도를 확대하는 함수입니다
	function zoomOut() {
	    map.setLevel(map.getLevel() + 1);
	}
	

	//인포윈도우를 표시하는 클로저를 만드는 함수입니다 
	function makeOverListener(map, overlay) {
	    return function() {
	    	overlay.setMap(map);
	    };
	}
	
	// 인포윈도우를 닫는 클로저를 만드는 함수입니다 
	function makeOutListener(overlay) {
	    return function() {
	    	overlay.setMap(null);
	    };
	}
	
	//인포윈도우를 클릭
	function clickListener(eventId) {
	    return function() {
	    	statePopup(eventId);
	    };
	}		

	function eventOver(num){
		eventOverlay[num].setMap(map);
	}
	function eventOut(num){
			eventOverlay[num].setMap(null);
	}