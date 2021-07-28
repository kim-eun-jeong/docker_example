package kr.co.enitt.intrusionMonitoring.util;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
	/**
	 * context path를 제거한 URI 반환
	 * @param request
	 * @return
	 */
	public static String getUri(HttpServletRequest request) {
		return Optional.ofNullable(request.getRequestURI().replace(request.getContextPath(), "")).orElse("");
	}
}