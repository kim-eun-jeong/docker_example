package kr.co.enitt.intrusionMonitoring.controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.intrusionMonitoring.service.CodeService;
import kr.co.enitt.intrusionMonitoring.service.EventService;
import kr.co.enitt.intrusionMonitoring.util.DownloadUtil;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;

/**
* @Project : safetyControlSystem
* @FileName : EventController.java
* @Author : ENITT_KEJ
* @Date : 2020. 8. 26. 
* @Description : 부분 방전 이력
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 26.       ENITT_KEJ      최초작성
*/
@RequestMapping("/eventHistory")
@Controller
public class EventController {
	
	@Resource(name = "eventService")
	private EventService eventService;
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	DownloadUtil downloadUtil;
	
	/**
	  * @Method_Name : list
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 26.
	  * @Author : KEJ
	  * @Method_Description : 이력 목록 
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 26. KEJ : 생성
	  */ 
	@RequestMapping(value= {"","/"})
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
	
		//검색 조건
		CommonVO commonVO = new CommonVO(); 
		
		// 이벤트명
		commonVO.setParentCode("EVENT");
		List<CodeVO> eventCodeList = codeService.getCodeList(commonVO);
		mav.addObject("eventCodeList", eventCodeList);
		//이벤트 구간
		commonVO.setParentCode("ZONE_TYPE");
		eventCodeList = codeService.getCodeList(commonVO);
		mav.addObject("eventZoneList", eventCodeList);
		
		
		//이벤트 목록		
		vo.setNow(ObjectUtil.getNow("",0)); // 현재 시간
		
		int Cnt = eventService.getEventListCnt(vo);
		vo.setTotalCount(Cnt);
		List<EventVO> list = eventService.getEventPagingList(vo);
		mav.addObject("eventList", list);

		mav.addObject("commonVO", vo);
		mav.setViewName("event/list");
		return mav;
	}
	
	/**
	  * @Method_Name : excelList
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 9.
	  * @Author : KEJ
	  * @Method_Description : 이력 목록 엑셀
	  * --------------------- 
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@RequestMapping("/excel/list")  
	public void excelList(HttpServletRequest request, HttpServletResponse response, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		 
		
		List<EventVO> list = eventService.getEventList(vo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sheetName","이력");
		map.put("fileName","이력");
		map.put("title","발생시간,이벤트명,구간,위치(m)");
		map.put("cell","eventTime,eventCodeNm,eventZoneNm,eventLocation");
		
		DownloadUtil.ecxelDown(request, response, map, list,"Y");
	} 
}
