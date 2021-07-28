package kr.co.enitt.intrusionMonitoring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventStatsVO;

@Mapper
public interface EventStatsDAO{
	//이벤트 통계
	public List<EventStatsVO> getEventStatsTimeList(CommonVO vo) throws Exception;
	public List<EventStatsVO> getEventStatsDayList(CommonVO vo) throws Exception;
	public List<EventStatsVO> getEventStatsMonthList(CommonVO vo) throws Exception;
	public List<EventStatsVO> getEventStatsYearList(CommonVO vo) throws Exception;

}
