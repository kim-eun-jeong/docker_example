package kr.co.enitt.intrusionMonitoring.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventVO;

import java.util.List;

@Mapper
public interface EventDAO{
	//이벤트 목록
	public List<EventVO> getEventList(CommonVO vo) throws Exception;
	public List<EventVO> getEventPagingList(CommonVO vo) throws Exception;
	public int getEventListCnt(CommonVO vo) throws Exception;
	 

	//이벤트 갯수 목록
	public List<EventVO> getEventCntList(CommonVO vo) throws Exception;
	
	//이벤트 상세 
	public EventVO getEventDetail(CommonVO vo) throws Exception;
	
	
	public void setEventLocationUpdate(CommonVO vo);
}
