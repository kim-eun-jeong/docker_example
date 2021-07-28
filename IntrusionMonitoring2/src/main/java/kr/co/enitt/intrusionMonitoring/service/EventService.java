package kr.co.enitt.intrusionMonitoring.service;

import java.util.List;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;

public interface EventService {
	//이벤트 목록
	List<EventVO> getEventList(CommonVO vo) throws Exception;
	List<EventVO> getEventPagingList(CommonVO vo) throws Exception;
	int getEventListCnt(CommonVO vo) throws Exception;
	
	//이벤트 갯수 목록
	List<EventVO> getEventCntList(CommonVO vo) throws Exception;
	
	
	//이벤트 상세
	EventVO getEventDetail(CommonVO vo) throws Exception;
	

	void setEventLocationUpdate(CommonVO vo) throws Exception;

}
