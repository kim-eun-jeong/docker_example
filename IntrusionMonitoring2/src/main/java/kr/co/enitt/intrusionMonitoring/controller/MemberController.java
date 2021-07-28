package kr.co.enitt.intrusionMonitoring.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.intrusionMonitoring.service.CodeService;
import kr.co.enitt.intrusionMonitoring.service.MemberService;
import kr.co.enitt.intrusionMonitoring.util.MD5Hash;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;


@RequestMapping("/member")
@Controller
public class MemberController { 
	
	@Resource(name = "memberService")
	private MemberService memberService;
	 
	@Resource(name = "codeService")
	private CodeService codeService;

	MD5Hash MD5 = new MD5Hash();
	
	@RequestMapping(value= {"","/"})
	public ModelAndView member(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		int cnt = memberService.getMemberListCnt(vo);
		vo.setTotalCount(cnt);
		List<MemberVO> list = memberService.getMemberPagingList(vo);
		mav.addObject("memberList", list);
		
		//검색 조건
		CommonVO commonVO = new CommonVO();
		
		commonVO.setParentCode("AUTH");
		List<CodeVO> authList = codeService.getCodeList(commonVO);
		mav.addObject("authList", authList);
		
		commonVO.setParentCode("DEPT");
		List<CodeVO> departmentList = codeService.getCodeList(commonVO);
		mav.addObject("departmentList", departmentList);
				
		mav.addObject("commonVO", vo);
		mav.setViewName("member/member");
		return mav;
	}
	
	@RequestMapping("/content/list")
	public ModelAndView list(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		int cnt = memberService.getMemberListCnt(vo);
		vo.setTotalCount(cnt);
		List<MemberVO> list = memberService.getMemberPagingList(vo);
		mav.addObject("memberList", list);
				
		mav.setViewName("member/content/list");
		return mav;
	}
	
	@RequestMapping("/content/detail")
	public ModelAndView detail(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		  
		MemberVO detail = memberService.getMemberDetail(vo);
		mav.addObject("detail", detail);
		
		//검색 조건
		CommonVO commonVO = new CommonVO();
		
		commonVO.setParentCode("AUTH");
		List<CodeVO> authList = codeService.getCodeList(commonVO);
		mav.addObject("authList", authList);
		
		commonVO.setParentCode("DEPT");
		List<CodeVO> departmentList = codeService.getCodeList(commonVO);
		mav.addObject("departmentList", departmentList);
				
		mav.setViewName("member/content/detail");
		return mav;
	}
	
	@RequestMapping("/ajaxIdCheck")
	public @ResponseBody Object ajaxIdCheck(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 회원 정보 수정
			MemberVO detail = memberService.getMemberDetail(vo);
			if(!ObjectUtil.isNotEmpty(detail)) {
				commonVO.setContent("사용 가능한 ID 입니다.");
				commonVO.setResult(true);
			}else {
				commonVO.setContent("이미 사용중인 ID 입니다.");
				commonVO.setResult(false);
			}
			
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO; 
	}
	
	@RequestMapping("/ajaxResetPasssword")
	public @ResponseBody Object ajaxResetPasssword(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 회원 정보 수정
			vo.setPassword(MD5.getMd5(vo.getPassword())); //비밀번호 암호화
			memberService.updatePasssword(vo);
			commonVO.setResult(true);
			commonVO.setContent("정상적으로 변경 되었습니다.");
			
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO; 
	}
	
	@RequestMapping("/ajaxSaveMember")
	public @ResponseBody Object saveMember(HttpServletRequest request, MemberVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 회원 정보 수정
			HttpSession session = request.getSession(true);
			vo.setRegisterId((String)session.getAttribute("loginId"));
			vo.setRegisterIp((String)session.getAttribute("loginIp"));
			vo.setUpdateId((String)session.getAttribute("loginId"));
			vo.setUpdateIp((String)session.getAttribute("loginIp"));
			if(!vo.getPassword().equals("")) {
				vo.setPassword(MD5.getMd5(vo.getPassword())); //비밀번호 암호화
			}else {
				vo.setPassword(" ");
			}
			memberService.saveMember(vo);
			commonVO.setResult(true);
			commonVO.setContent("정상적으로 저장 되었습니다.");
			
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO;  
	}
	
	/**
	  * @Method_Name : mypage
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 25.
	  * @Author : KEJ
	  * @Method_Description :
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@RequestMapping(value= {"","/"}, params="mode=mypage")
	public ModelAndView mypage(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		// 회원 상세 정보
		HttpSession session = request.getSession(true);
		vo.setId((String)session.getAttribute("loginId"));
		MemberVO detail = memberService.getMemberDetail(vo);
		mav.addObject("detail", detail);
		
		mav.addObject("commonVO", vo);
		mav.setViewName("member/mypage");
		return mav;
	}
	
	@RequestMapping("/ajaxUpdateMember")
	public @ResponseBody Object ajaxUpdateMember(HttpServletRequest request, MemberVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			// 회원 정보 수정
			memberService.updateMember(vo);
			commonVO.setContent("정상적으로 수정되었습니다.");
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
		}
		
		return commonVO;    
	} 
	
	@RequestMapping("/ajaxUpdatePasssword")
	public @ResponseBody Object ajaxUpdatePasssword(HttpServletRequest request, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		CommonVO commonVO = new CommonVO();
		try {
			vo.setPassword(MD5.getMd5(vo.getPassword())); // 비밀번호 암호화
			vo.setNewPassword(MD5.getMd5(vo.getNewPassword())); //새 비밀번호 암호화
			
			MemberVO detail = memberService.getMemberDetail(vo);
			if(!ObjectUtil.isNotEmpty(detail)) {
				commonVO.setResult(false);
				commonVO.setContent("현재 비밀번호가 맞지 않습니다.");
			}else {
				// 회원 정보 수정
				memberService.updatePasssword(vo);
				commonVO.setResult(true);
				commonVO.setContent("정상적으로 변경 되었습니다.");
				if("Y".equals(ObjectUtil.toString(vo.getPasswordChangeYn(),""))) {
					HttpSession session = request.getSession(true);
					session.setAttribute("loginPasswordChangeYn", "Y");
				}
				
			}
		} catch (Exception e) {
			commonVO.setContent("오류가 발생했습니다.");
			commonVO.setResult(false);
		}
		
		return commonVO; 
	}
	
	/**
	  * @Method_Name : passwordChange
	  * @retuen :ModelAndView
	  * @Date : 2020. 9. 22.
	  * @Author : KEJ
	  * @Method_Description : 비밀번호 변경 화면
	  * ---------------------
	  * @변경이력
	  * 2020. 9. 22. KEJ : 생성
	  */
	@RequestMapping("/passwordChange")
	public ModelAndView passwordChange(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		mav.addObject("id", (String)session.getAttribute("loginId"));
		
		mav.setViewName("member/passwordChange");
		return mav;
	}
}
