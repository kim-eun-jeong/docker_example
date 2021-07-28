package kr.co.enitt.intrusionMonitoring.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

@Mapper
public interface MemberDAO{
	//회원 목록
	public List<MemberVO> getMemberList(CommonVO vo) throws Exception;
	public List<MemberVO> getMemberPagingList(CommonVO vo) throws Exception;
	public int getMemberListCnt(CommonVO vo) throws Exception;
	
	//회원 상세 정보
	public MemberVO getMemberDetail(CommonVO vo) throws Exception;
	
	//회원 정보 수정
	public void saveMember(MemberVO vo);
	
	//회원 정보 수정
	public void updateMember(MemberVO vo);
	
	// 회원 비밀번호 수정
	public void updatePassword(CommonVO vo);
	
    //로그인 로그 추가
	public void createLoginLog(CommonVO vo);
	 
}
