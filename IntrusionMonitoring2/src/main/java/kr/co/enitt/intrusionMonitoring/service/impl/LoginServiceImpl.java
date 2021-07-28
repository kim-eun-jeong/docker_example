package kr.co.enitt.intrusionMonitoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.enitt.intrusionMonitoring.dao.MemberDAO;
import kr.co.enitt.intrusionMonitoring.service.LoginService;
import kr.co.enitt.intrusionMonitoring.util.MD5Hash;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	@Autowired
	private MemberDAO memberDAO;

	MD5Hash MD5 = new MD5Hash();

	/**
	  * @Method_Name : ajaxLogin
	  * @Method_Description : 로그인/ 로그인 로그 추가
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	
	@Override
	public MemberVO ajaxLogin(CommonVO vo) throws Exception {
		vo.setPassword(MD5.getMd5(vo.getPassword())); // 비밀번호 암호화
		MemberVO memberVO = memberDAO.getMemberDetail(vo);
		
		if(ObjectUtil.isNotEmpty(memberVO)){
			String useYn = memberVO.getUseYn();
			if(useYn.equals("Y")){
				// 로그인 로그 추가
				memberVO.setResult(true);
				memberDAO.createLoginLog(vo);
			}else{//미사용 회원
				memberVO.setResult(false);
				memberVO.setMessage("미사용 회원입니다.");
			}
			
		}else{
			memberVO = new MemberVO();
			if(ObjectUtil.isNotEmpty(memberVO)){
				memberVO.setResult(false);
				memberVO.setMessage("비밀번호를 다시 입력해주세요.");
			}else {
				memberVO.setResult(false);
				memberVO.setMessage("등록되지않은 사용자입나다.");
			}
		}
		
		return memberVO;
	}
	
	
}
