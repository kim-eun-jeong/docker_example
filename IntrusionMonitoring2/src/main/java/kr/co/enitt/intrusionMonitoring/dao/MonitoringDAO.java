package kr.co.enitt.intrusionMonitoring.dao;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MonitoringVO;

import java.util.List;

@Mapper
public interface MonitoringDAO{
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
	public List<MonitoringVO> getMapInfoList(CommonVO vo) throws Exception;
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
	public List<MonitoringVO> getEquipmentState(CommonVO vo) throws Exception;
}
