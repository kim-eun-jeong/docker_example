package kr.co.enitt.intrusionMonitoring.service;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

public interface LoginService {
	
	MemberVO ajaxLogin(CommonVO vo) throws Exception;
}
