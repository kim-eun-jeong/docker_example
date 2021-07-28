package kr.co.enitt.intrusionMonitoring.controller;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.intrusionMonitoring.service.CodeService;
import kr.co.enitt.intrusionMonitoring.service.EventService;
import kr.co.enitt.intrusionMonitoring.service.EventStatsService;
import kr.co.enitt.intrusionMonitoring.util.DownloadUtil;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventStatsVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;
 
/**
* @Project : safetyControlSystem
* @FileName : EventStatsController.java
* @Author : KEJ 
* @Date : 2020. 9. 9. 
* @Description : 통계
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 9. 9.       KEJ      최초작성
*/
@RequestMapping("/eventStats")
@Controller
public class EventStatsController {
	
	@Resource(name = "eventStatsService")
	private EventStatsService eventStatsService;
	
	@Resource(name = "eventService")
	private EventService eventService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	
	
	/**
	  * @Method_Name : eventStats
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 3.
	  * @Author : KEJ
	  * @Method_Description : 이벤트 통계
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 3. KEJ : 생성
	  */
	@RequestMapping(value= {"","/"})
	public ModelAndView eventStats(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		// 이벤트명
		commonVO.setParentCode("EVENT");
		List<CodeVO> eventCodeList = codeService.getCodeList(commonVO);
		mav.addObject("eventCodeList", eventCodeList);
				
		// 검색조건 세팅
		setEventSetting(vo);
			
		// 일별
		HashMap<String, Object> dayData = eventStatsService.getEventStatsDayList(vo);
		mav.addObject("dayData", dayData);
		
		
		// 월별
		HashMap<String, Object> monthData = eventStatsService.getEventStatsMonthList(vo);
		mav.addObject("monthData", monthData);
		
		// 년별
		HashMap<String, Object> yearData = eventStatsService.getEventStatsYearList(vo);
		mav.addObject("yearData", yearData); 
	
		if(ObjectUtil.isEmpty(vo.getMode())){
			vo.setMode("day");
		}
		
		mav.addObject("commonVO", vo);
		mav.setViewName("eventStats/list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEventList
	  * @retuen :Object
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 차트
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/ajaxEventList") 
	public @ResponseBody Object ajaxEventList(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
				
		// 검색조건 세팅
		setEventSetting(vo);
		
		// 일별
		HashMap<String, Object> dayData = eventStatsService.getEventStatsDayList(vo);
		result.put("dayData", dayData);
		
		// 월별
		HashMap<String, Object> monthData = eventStatsService.getEventStatsMonthList(vo);
		result.put("monthData", monthData);
		
		// 년별
		HashMap<String, Object> yearData = eventStatsService.getEventStatsYearList(vo);
		result.put("yearData", yearData);
		
		return result;
	}
	
	
	/**
	  * @Method_Name : ajaxEventTime
	  * @retuen :Object
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 차트
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/ajaxEventTime") 
	public @ResponseBody Object ajaxEventTime(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		// 이벤트명
		commonVO.setParentCode("EVENT");
		List<CodeVO> eventCodeList = codeService.getCodeList(commonVO);
				
		// 시간별
		HashMap<String, Object> titmeData = eventStatsService.getEventStatsTimeList(vo,eventCodeList);
		result.put("titmeData", titmeData);
		
		return result;
	}
	
	/**
	  * @Method_Name : excelDay
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 일별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/day")  
	public void excelDay(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//일별 리스트 검색
		HashMap<String, Object> dayData = eventStatsService.getEventStatsDayList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","통계_일별("+vo.getSearchDay()+")");
		map.put("fileName","통계_일별("+vo.getSearchDay()+")");

		DownloadUtil.ecxelDownStatus(request, response, map, dayData);
	} 
	
	/**
	  * @Method_Name : excelMonth
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 월별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/month")  
	public void excelMonth(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//월별 리스트 검색
		HashMap<String, Object>  monthData = eventStatsService.getEventStatsMonthList(vo);
		
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName"," 통계_월별("+vo.getSearchMonth()+")");
		map.put("fileName","통계_월별("+vo.getSearchMonth()+")");
		
		DownloadUtil.ecxelDownStatus(request, response, map, monthData);
	} 
	
	/**
	  * @Method_Name : excelYear
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 년별 엑셀
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/year")  
	public void excelYear(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		// 검색조건 세팅
		setEventSetting(vo);
		
		//년별 리스트 검색
		HashMap<String, Object> yearData = eventStatsService.getEventStatsYearList(vo);
				
		//엑셀 정보 입력
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","통계_년별");
		map.put("fileName","통계_년별");
		
		DownloadUtil.ecxelDownStatus(request, response, map, yearData);
	} 
	/**
	  * @Method_Name : setEventSetting
	  * @retuen :void
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 통계 검색조건 세팅
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	public void setEventSetting(CommonVO vo) {
		
		/** 일별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchDay())){
			SimpleDateFormat  day = new SimpleDateFormat("yyyy-MM");
			vo.setSearchDay(day.format(new Date()));
		}
		
		/** 년별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchMonth())){
			SimpleDateFormat  Month = new SimpleDateFormat("yyyy");
			vo.setSearchMonth(Month.format(new Date()));
		}
		
		/** 년별 **/
		//검색 조건(날짜)
		if(ObjectUtil.isEmpty(vo.getSearchYear())){
			Calendar beforeYear = Calendar.getInstance();
			SimpleDateFormat  year = new SimpleDateFormat("yyyy");
			vo.setSearchYearEnd(year.format(beforeYear.getTime())); 
			
			beforeYear.add(Calendar.YEAR , -4);
			vo.setSearchYear(year.format(beforeYear.getTime())); 
			
		} 
	}
}
