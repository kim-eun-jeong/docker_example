package kr.co.enitt.intrusionMonitoring.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.co.enitt.intrusionMonitoring.service.MenuService;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;
import kr.co.enitt.intrusionMonitoring.util.WebUtil;
import kr.co.enitt.intrusionMonitoring.vo.MenuVO;


@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {
	@Autowired 
	MenuService menuService;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession(true);
		String loginId = ObjectUtil.toString(session.getAttribute("loginId"),"");
		String loginAuth = ObjectUtil.toString(session.getAttribute("loginAuth"),"");
		String loginPasswordChangeYn = ObjectUtil.toString(session.getAttribute("loginPasswordChangeYn"),"N");
		
		if(request.getRequestURI().equals("/")) {
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			response.sendRedirect("/login");
			return false; 
		}
		if(ObjectUtil.isEmpty(loginId)) {//로그인이 안되어 있을경우
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
			out.println("<script>alert('로그인 후 사용 가능한 메뉴 입니다.');location.href = '/login';</script>");
			out.println("</body></html>");
			out.close();
			return false; 
		}
		
		if("N".equals(loginPasswordChangeYn)) {
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
			out.println("<script>alert('비밀번호를 변경해주세요.');location.href = '/member/passwordChange';</script>");
			out.println("</body></html>");
			out.close();
			return false;
		}
		
		String[] url = WebUtil.getUri(request).split("/"); 
		MenuVO vo = new MenuVO();
		
		//menuService.getMenuList(vo);
		 
		vo.setMenuTitle(url[1]);
		MenuVO 	menuVO = menuService.getMenu(vo);
		
		if(!ObjectUtil.isEmpty(menuVO)) {
			int menuAuth = Integer.parseInt(menuVO.getMenuAuth().replace("AUTH_", ""));
			int auth = Integer.parseInt(loginAuth.replace("AUTH_", ""));
			if(auth <= menuAuth) {
				request.setAttribute("menuVO", menuVO);
			} else {
				response.setContentType("text/html; charset=UTF-8");
			    response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
				out.println("<script>alert('접근 권한이 없습니다.');history.go(-1);</script>");
				out.println("</body></html>");
				out.close();
				return false;
			}
		}else {//해당 url의 메뉴가 없을 경우
			response.setContentType("text/html; charset=UTF-8");
		    response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<!DOCTYPE html><html lang=\"ko\"><head><title></title></head><body>");
			out.println("<script>alert('해당하는 메뉴가 없습니다.');history.go(-1);</script>");
			out.println("</body></html>");
			out.close();
			return false;
		}
		

		return true;
	}
	
}