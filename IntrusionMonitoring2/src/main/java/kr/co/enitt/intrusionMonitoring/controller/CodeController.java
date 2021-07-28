package kr.co.enitt.intrusionMonitoring.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import kr.co.enitt.intrusionMonitoring.service.CodeService;
import kr.co.enitt.intrusionMonitoring.vo.CodeVO;
import kr.co.enitt.intrusionMonitoring.vo.CommonVO;

/**
* @Project : safetyControlSystem
* @FileName : CodeController.java 
* @Author : KEJ 
* @Date : 2020. 8. 25.   
* @Description : 
* ===========================================================
* DATE                   AUTHOR                     NOTE
* -----------------------------------------------------------
* 2020. 8. 25.       KEJ      최초작성
*/ 
@RequestMapping("/code") 
@Controller
public class CodeController { 
	
	@Resource(name = "codeService")
	private CodeService codeService;
	
	/**
	  * @Method_Name : main
	  * @retuen :ModelAndView
	  * @Date : 2020. 8. 25.
	  * @Author : KEJ
	  * @Method_Description :
	  * ---------------------
	  * @변경이력
	  * 2020. 8. 25. KEJ : 생성
	  */
	@RequestMapping("/list")
	public ModelAndView main(HttpServletRequest request, CommonVO vo) throws Exception {
		ModelAndView mav = new ModelAndView();
		request.setCharacterEncoding("UTF-8");
		
		List<CodeVO> result = new ArrayList<CodeVO>();
		result = codeService.getCodeList(vo);
		
		
		mav.addObject("commonVO", vo);
		mav.addObject("list", result);
		mav.setViewName("code/list");
		return mav;
	}
	
}
