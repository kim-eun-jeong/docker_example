package kr.co.enitt.intrusionMonitoring.service;

import java.util.List;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MonitoringVO;

public interface MonitoringService {
	/**
	  * @Method_Name : getEquipmentState
	  * @retuen :List<MonitoringVO>
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : 장비상태 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	List<MonitoringVO> getEquipmentState(CommonVO vo) throws Exception;

	/**
	  * @Method_Name : getMapInfoList
	  * @retuen :List<MonitoringVO>
	  * @Date : 2020. 11. 20.
	  * @Author : ENITT_KEJ
	  * @Method_Description : map 정보
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	List<MonitoringVO> getMapInfoList(CommonVO vo) throws Exception;
}
