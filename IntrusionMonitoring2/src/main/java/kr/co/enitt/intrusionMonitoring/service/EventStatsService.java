package kr.co.enitt.intrusionMonitoring.service;

import java.util.HashMap;
import java.util.List;

import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.EventStatsVO;

public interface EventStatsService {
	//이벤트 통계
	HashMap<String, Object> getEventStatsTimeList(CommonVO vo, List<CodeVO> eventCodeList) throws Exception;
	HashMap<String, Object> getEventStatsDayList(CommonVO vo) throws Exception;
	HashMap<String, Object> getEventStatsMonthList(CommonVO vo) throws Exception;
	HashMap<String, Object> getEventStatsYearList(CommonVO vo) throws Exception;

}
