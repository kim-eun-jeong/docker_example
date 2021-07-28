package kr.co.enitt.intrusionMonitoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.MemberDAO;
import kr.co.enitt.intrusionMonitoring.service.MemberService;
import kr.co.enitt.intrusionMonitoring.util.MD5Hash;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

@Service("memberService")
public class MemberServiceImpl implements MemberService{
	@Autowired
	private MemberDAO memberDAO;
	
	/**
	  * @Method_Name : getMemberList
	  * @Method_Description : 회원 목록
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public List<MemberVO> getMemberList(CommonVO vo) throws Exception {
		List<MemberVO> list = memberDAO.getMemberList(vo);
		return list;
	}
	@Override
	public List<MemberVO> getMemberPagingList(CommonVO vo) throws Exception {
		List<MemberVO> list = memberDAO.getMemberPagingList(vo);
		return list;
	}
	public int getMemberListCnt(CommonVO vo) throws Exception {
		int cnt = memberDAO.getMemberListCnt(vo);
		return cnt;
	}
	
	/**
	  * @Method_Name : getMemberDetail
	  * @Method_Description : 회원 상세 정보
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public MemberVO getMemberDetail(CommonVO vo) throws Exception {
		MemberVO detail = memberDAO.getMemberDetail(vo);
		return detail;
	}

	@Override
	public void saveMember(MemberVO vo) throws Exception {
		memberDAO.saveMember(vo);
	}
	
	/**
	  * @Method_Name : updateMember
	  * @Method_Description : 회원정보 수정
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void updateMember(MemberVO vo) throws Exception {
		memberDAO.updateMember(vo);
	}

	
	/**
	  * @Method_Name : updatePasssword
	  * @Method_Description : 비밀번호 변경
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 9. KEJ : 생성
	  */
	@Override
	public void updatePasssword(CommonVO vo) throws Exception {
		memberDAO.updatePassword(vo);
	}
	
	
	
}
