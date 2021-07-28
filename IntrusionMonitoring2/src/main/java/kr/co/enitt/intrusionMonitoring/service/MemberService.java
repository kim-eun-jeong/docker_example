package kr.co.enitt.intrusionMonitoring.service;

import java.util.List;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

public interface MemberService {
	// 회원 목록
	List<MemberVO> getMemberList(CommonVO vo) throws Exception;
	List<MemberVO> getMemberPagingList(CommonVO vo) throws Exception;
	int getMemberListCnt(CommonVO vo) throws Exception;
		
	// 회원 상세 정보
	MemberVO getMemberDetail(CommonVO vo) throws Exception;
	
	// 회원 등록
	void saveMember(MemberVO vo) throws Exception;
		
	// 회원 정보 수정
	void updateMember(MemberVO vo) throws Exception;
	
	//비밀번호 변경
	void updatePasssword(CommonVO vo) throws Exception;
}
