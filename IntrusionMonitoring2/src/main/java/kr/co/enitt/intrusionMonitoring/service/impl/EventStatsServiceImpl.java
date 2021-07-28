package kr.co.enitt.intrusionMonitoring.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.CodeDAO;
import kr.co.enitt.intrusionMonitoring.dao.EventStatsDAO;
import kr.co.enitt.intrusionMonitoring.service.EventStatsService;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventStatsVO;

@Service("eventStatsService")
public class EventStatsServiceImpl implements EventStatsService{
	@Autowired
	private EventStatsDAO eventstatsDAO;


	/**
	  * @Method_Name : getEventStatsTimeList, getEventStatsDayList, getEventStatsMonthList, getEventStatsYearList
	  * @Method_Description : 이벤트 통계
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public HashMap<String, Object> getEventStatsTimeList(CommonVO vo, List<CodeVO> eventCodeList) throws Exception {
		List<EventStatsVO> list = eventstatsDAO.getEventStatsTimeList(vo);
		HashMap<String, Object> result = dateData2(list,eventCodeList);
		return result;
	}
	
	@Override
	public HashMap<String, Object> getEventStatsDayList(CommonVO vo) throws Exception {
		//해당 월의 첫째날 지정
		String searchDay = vo.getSearchDay();
		vo.setSearchDayFirst(searchDay+"-01");
		
		//해당 월의 마지막날 지정
		Calendar cal = Calendar.getInstance();
		String[] searchDayArr = searchDay.split("-");
		int year = ObjectUtil.toInt(searchDayArr[0],0);
		int month =ObjectUtil.toInt(searchDayArr[1],0)-1;
		cal.set(year,month,1);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		vo.setSearchDayLast(searchDay+"-"+lastDay);
		
		List<EventStatsVO> list = eventstatsDAO.getEventStatsDayList(vo);
		HashMap<String, Object> result = dateData(list); 
		return result;
	}

	@Override
	public HashMap<String, Object> getEventStatsMonthList(CommonVO vo) throws Exception {
		List<EventStatsVO> list = eventstatsDAO.getEventStatsMonthList(vo);

		HashMap<String, Object> result = dateData(list); 
		return result;
	}
	
	@Override
	public HashMap<String, Object> getEventStatsYearList(CommonVO vo) throws Exception {
		List<EventStatsVO> list = eventstatsDAO.getEventStatsYearList(vo);

		HashMap<String, Object> result = dateData(list); 
		return result;
	}
	
	public HashMap<String, Object> dateData(List<EventStatsVO> list) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
	
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		ArrayList<Integer> arrNull = new ArrayList<Integer>();
		int max = 10;
		
		List<String> categories = new ArrayList<String>();
		String categorie = "";
		List<String> eventNames = new ArrayList<String>();
		String eventName = "";
		int eventCount = 0;
		Integer eventCountNull;
		int dataYn = 0;
		
		
		for(int i = 0; i < list.size(); i++) {
			if(!categorie.equals(list.get(i).getEventDateGroup())) {
				categorie = list.get(i).getEventDateGroup();
				categories.add(categorie);
			}
			
			
			eventName = list.get(i).getEventCodeName();
			eventCount = list.get(i).getEventCount();
			eventCountNull = list.get(i).getEventCountNull();
			
			for(int j = 0; j < data.size(); j++) {
				map = new HashMap<String, Object>();
				map = data.get(j);
				if(map.get("name").equals(eventName)) {
					arr = new ArrayList<Integer>(); 
					arr = (ArrayList<Integer>) map.get("ListData");
					arr.add(eventCount);
					
					arrNull = new ArrayList<Integer>(); 
					arrNull = (ArrayList<Integer>) map.get("data");
					arrNull.add(eventCountNull);
					 
				    max += eventCount;
					 
					dataYn = 1;
					break;
				}
			}
			
			if(dataYn == 0) {
				arr = new ArrayList<Integer>();
			    arr.add(eventCount);
			    arrNull = new ArrayList<Integer>();
			    arrNull.add(eventCountNull);
			    map = new HashMap<String, Object>();
			    
			    max += eventCount;

				eventNames.add(eventName);
			    map.put("name",eventName);
			    map.put("data",arrNull);
			    map.put("ListData",arr);
				data.add(map);
				dataYn = 0;
			}	
		}

		resultMap.put("max",max);
		resultMap.put("categories", categories);
		resultMap.put("eventNames", eventNames);
		resultMap.put("data", data);
		
		return resultMap;
	}
	
	public HashMap<String, Object> dateData2(List<EventStatsVO> list, List<CodeVO> eventCodeList) {
		HashMap<String,Object> resultMap = new HashMap<String, Object>();
		List<HashMap<String, Object>> data = new ArrayList<HashMap<String,Object>>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		ArrayList<Integer> arr = new ArrayList<Integer>();
		int max = 10;
		
		
		List<String> categories = new ArrayList<String>();
		String eventName = "";
		String eventCodeId = "";
		String day = "";
		int cnt = 0;

		for(int j = 0; j < eventCodeList.size(); j++){
			eventName = eventCodeList.get(j).getCodeName();
			eventCodeId = eventCodeList.get(j).getCodeId();
			map = new HashMap<String, Object>();
			arr = new ArrayList<Integer>();
			map.put("name",eventName);
			map.put("code",eventCodeId);
			map.put("data",arr);
			data.add(map);
		}
		
		Calendar cal = Calendar.getInstance(); 
		SimpleDateFormat Date = new SimpleDateFormat("yyyy-MM-dd HH"); 
		SimpleDateFormat DateTime = new SimpleDateFormat("HH"); 
		cal.add(Calendar.HOUR , -23);
		for(int i = 24; i > 0 ; i--) {
			day = Date.format(cal.getTime());
			categories.add(DateTime.format(cal.getTime()));

			for(int j = 0; j < data.size(); j++) {
				map = new HashMap<String, Object>();
				map = data.get(j);
				arr = new ArrayList<Integer>();
				arr = (ArrayList<Integer>) map.get("data");
				eventCodeId = (String) map.get("code");
				
				for(int k = 0; k < list.size(); k++) {
					if(day.equals(list.get(k).getEventDate()) && eventCodeId.equals(list.get(k).getEventCode()) ) {
						arr.add(list.get(k).getEventCount());
						if(max < list.get(k).getEventCount()) {
							max = list.get(k).getEventCount();
						}
						cnt++;
						break;
					}
				}
				
				if(cnt == 0) {
					arr.add(null);
				}
				cnt = 0;
			}
				
				
			cal.add(Calendar.HOUR , +1);
		}
		
		resultMap.put("max", max);
		resultMap.put("categories", categories);
		resultMap.put("data", data);
		
		return resultMap;
	}
}
