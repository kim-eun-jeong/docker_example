package kr.co.enitt.intrusionMonitoring.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.intrusionMonitoring.service.LoginService;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;
import kr.co.enitt.intrusionMonitoring.vo.MemberVO;

/**
* @Project : safetyControlSystem
* @FileName : LoginController.java
* @Author : KEJ
* @Date : 2020. 8. 25. 
* @Description :
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 25.       KEJ      최초작성
*/
@Controller
public class LoginController {
	
	@Resource(name = "loginService")
	private LoginService loginService;
	
	/**
	  * @Method_Name : login
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 25.
	  * @Author : KEJ
	  * @Method_Description : 로그인 화면으로 이동
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		mav.setViewName("login");
		return mav;
	}
	
	
	/**
	  * @Method_Name : ajaxLogin
	  * @retuen :Object
	  * @Date : 2020. 8. 25.
	  * @Author : KEJ
	  * @Method_Description : 로그인/ 로그인 로그 추가/ 세션 추가 
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */ 
	@RequestMapping("/actionLogin")
	public @ResponseBody Object ajaxLogin(HttpServletRequest request, ModelMap model, CommonVO vo) throws Exception {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(true);
		
		CommonVO commonVO = new CommonVO();
		
		MemberVO memberVO = loginService.ajaxLogin(vo);
		if(ObjectUtil.isNotEmpty(memberVO)) {
			session.setAttribute("loginId", memberVO.getId());
			session.setAttribute("loginName", memberVO.getName());
			session.setAttribute("loginEmail", memberVO.getEmail());
			session.setAttribute("loginAuth", memberVO.getAuth());
			session.setAttribute("loginAuthNm", memberVO.getAuthNm());
			session.setAttribute("loginPasswordChangeYn", memberVO.getPasswordChangeYn());
			session.setAttribute("loginDepartment", memberVO.getDepartment());
			session.setAttribute("loginDepartmentNm", memberVO.getDepartmentNm());
			session.setAttribute("loginUseYn", memberVO.getUseYn());
			session.setAttribute("loginIp", request.getRemoteAddr());
		}
		commonVO.setContent(memberVO.getMessage());
		commonVO.setResult(memberVO.isResult());
		return commonVO; 
	}
	
	
	/**
	  * @Method_Name : logout
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 25.
	  * @Author : KEJ
	  * @Method_Description : 로그아웃/ 세션 끊기
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@RequestMapping("/logout")
	public ModelAndView logout(HttpServletRequest request) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession(true);
		if(session != null){
			session.invalidate();
		}
		
		mav.setViewName("login");
		return mav;
	}

}
