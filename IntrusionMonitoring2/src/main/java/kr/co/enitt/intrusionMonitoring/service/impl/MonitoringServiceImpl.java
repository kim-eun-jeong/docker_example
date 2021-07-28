package kr.co.enitt.intrusionMonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.MonitoringDAO;
import kr.co.enitt.intrusionMonitoring.service.MonitoringService;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MonitoringVO;

import java.util.List;

@Service("monitoringService")
public class MonitoringServiceImpl implements MonitoringService{
	@Autowired
	private MonitoringDAO monitoringDAO;

	/**
	  * @Method_Name : getMapInfoList
	  * @Method_Description :map 정보
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@Override
	public List<MonitoringVO> getMapInfoList(CommonVO vo) throws Exception {
		List<MonitoringVO> list = monitoringDAO.getMapInfoList(vo);
		return list;
	}
	
	/**
	  * @Method_Name : getEquipmentState
	  * @Method_Description : 장비상태 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 11. 20. ENITT_KEJ : 생성
	  */
	@Override
	public List<MonitoringVO> getEquipmentState(CommonVO vo) throws Exception {
		return monitoringDAO.getEquipmentState(vo);
	}

	
}
