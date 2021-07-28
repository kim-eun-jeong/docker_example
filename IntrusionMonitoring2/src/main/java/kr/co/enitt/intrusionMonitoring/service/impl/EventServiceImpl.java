package kr.co.enitt.intrusionMonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.EventDAO;
import kr.co.enitt.intrusionMonitoring.service.EventService;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;

import java.util.List;

@Service("eventService")
public class EventServiceImpl implements EventService{
	@Autowired
	private EventDAO eventDAO;

	/**
	  * @Method_Name : getEventList
	  * @Method_Description : 이벤트 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<EventVO> getEventList(CommonVO vo) throws Exception {
		List<EventVO> list = eventDAO.getEventList(vo);
		return list;
	}
	@Override
	public List<EventVO> getEventPagingList(CommonVO vo) throws Exception {
		List<EventVO> list = eventDAO.getEventPagingList(vo);
		return list;
	}
	@Override
	public int getEventListCnt(CommonVO vo) throws Exception {
		return eventDAO.getEventListCnt(vo);
	}
	
	/**
	  * @Method_Name : getEventCntList
	  * @Method_Description : 이벤트 갯수 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<EventVO> getEventCntList(CommonVO vo) throws Exception {
		List<EventVO> list = eventDAO.getEventCntList(vo);
		return list;
	}
	
	
	/**
	  * @Method_Name : getEventDetail
	  * @Method_Description : 이벤트 상세
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	
	@Override
	public EventVO getEventDetail(CommonVO vo) throws Exception {
		EventVO detail = eventDAO.getEventDetail(vo);
		return detail;
	}
	
	
	@Override
	public void setEventLocationUpdate(CommonVO vo) throws Exception {
		eventDAO.setEventLocationUpdate(vo);
	}
	
}
