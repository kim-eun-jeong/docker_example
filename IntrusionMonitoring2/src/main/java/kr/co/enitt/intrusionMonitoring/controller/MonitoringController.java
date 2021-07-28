package kr.co.enitt.intrusionMonitoring.controller;

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
import kr.co.enitt.intrusionMonitoring.service.MonitoringService;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;
import kr.co.enitt.intrusionMonitoring.vo.MonitoringVO;

/**
* @Project : smartManagementSystem
* @FileName : MonitoringController.java
* @Author : ENITT_KEJ
* @Date : 2020. 11. 20. 
* @Description : 모니터링
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 11. 20.       ENITT      최초작성
*/
@Controller 
public class MonitoringController {

	@Resource(name = "monitoringService")
	private MonitoringService monitoringService;
	
	@Resource(name = "eventService")
	private EventService eventService;

	@Resource(name = "codeService") 
	private CodeService codeService;
	
	/**
	  * @Method_Name : main
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 모니터링 화면 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring") 
	public ModelAndView main(HttpServletResponse response, HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		// 이벤트명
		commonVO.setParentCode("EVENT");
		List<CodeVO> eventCodeList = codeService.getCodeList(commonVO);
		mav.addObject("eventCodeList", eventCodeList);
		
		mav.setViewName("monitoring/main");    
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEventCnt
	  * @retuen :Object
	  * @Date : 2021. 1. 8.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 이벤트 발생 확인
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 8. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/ajaxEventCnt")  
	public @ResponseBody Object ajaxEventCnt(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HashMap<String,Object> result = new HashMap<String,Object>();
		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		String timeStr = ObjectUtil.getNow("SECOND", -4);
			  
		// 이벤트 갯수
		vo.setLastTime(timeStr);
		int newEventCnt = eventService.getEventListCnt(vo);
		result.put("newEventCnt", newEventCnt);
		
		vo.setLastTime("");
		int eventCnt =eventService.getEventListCnt(vo);
		result.put("eventCnt", eventCnt);

		return result;
	}
	
	/**
	  * @Method_Name : ajaxEvent
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 8.
	  * @Author : ENITT_KEJ
	  * @Method_Description :  이벤트 COUNT 리스트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 8. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/content/monitoring")
	public ModelAndView ajaxMonitoring(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");

		//24시간 적용
		vo.setMonitoring("Y");
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		
		//이벤트 갯수
		List<EventVO> list = eventService.getEventCntList(vo);
		mav.addObject("eventCntList", list);
		
		mav.setViewName("monitoring/content/monitoring");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEvent
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 8.
	  * @Author : ENITT_KEJ
	  * @Method_Description :  이벤트 발생 아이콘 리스트
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 8. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/content/event")
	public ModelAndView ajaxEvent(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
			
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<EventVO> eventList = eventService.getEventList(vo);
		mav.addObject("eventList", eventList);
		if(ObjectUtil.isNotEmpty(eventList)) {
			//선택된게 없으면 무조건 첫번째 이벤트 띄게하기
			if(vo.getMonitoring_eventId() == null || vo.getMonitoring_eventId().equals("")) {
				vo.setMonitoring_eventId(eventList.get(0).getEventId());
			}
		}
	
		mav.addObject("commonVO", vo);
		
		mav.setViewName("monitoring/content/event");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEquipmenState
	  * @retuen :ModelAndView
	  * @Date : 2021. 1. 5.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 장비 상태
	  * ---------------------
	  * @변경이력
	  * 2021. 1. 5. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/content/equipmentState")
	public ModelAndView ajaxEquipmenState(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
	
		//장비 상태
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<MonitoringVO> equipmentList = monitoringService.getEquipmentState(vo);
		mav.addObject("equipmentList", equipmentList);
		
		mav.setViewName("monitoring/content/equipment_popup");
		return mav;
	}
	
	/**
	  * @Method_Name : eventPopup
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description :  이벤트 팝업 호출
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/content/eventPopup")
	public ModelAndView eventPopup(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		EventVO eventVO = eventService.getEventDetail(vo);
		mav.addObject("eventVO", eventVO);
		
		mav.setViewName("monitoring/content/event_popup");
		return mav;
	}
	
	
	/**
	  * @Method_Name : ajaxEventList
	  * @retuen :ModelAndView
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 이벤트 이력 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@RequestMapping("/monitoring/content/eventList")
	public ModelAndView ajaxEventList(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
				
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<EventVO> eventList = eventService.getEventList(vo);
		mav.addObject("eventList", eventList);
		
		mav.setViewName("monitoring/content/event_list");
		return mav;
	}
	
	/**
	  * @Method_Name : ajaxEventInfo
	  * @retuen :Object
	  * @Date : 2020. 8. 26.
	  * @Author : KEJ
	  * @Method_Description :
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 26. KEJ : 생성
	  */
	@RequestMapping("/monitoring/ajaxEventInfo")   
	public @ResponseBody Object ajaxEventInfo(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		

		eventService.setEventLocationUpdate(vo);
		
		//24시간 조건 걸기
		vo.setMonitoring("Y"); 
		
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		List<EventVO> eventList = eventService.getEventList(vo);
		return eventList;
	}
	
	/**
	  * @Method_Name : ajaxMapInfo
	  * @retuen :Object
	  * @Date : 2020. 8. 26.
	  * @Author : KEJ
	  * @Method_Description :
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 26. KEJ : 생성
	  */
	@RequestMapping("/monitoring/ajaxMapInfo")  
	public @ResponseBody Object ajaxMapInfo(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		//map 정보		
		List<MonitoringVO> mapInfoList = monitoringService.getMapInfoList(vo);
		return mapInfoList;
	}
	
}
