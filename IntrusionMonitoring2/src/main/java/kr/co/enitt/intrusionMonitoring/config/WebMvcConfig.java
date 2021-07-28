package kr.co.enitt.intrusionMonitoring.config;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.enitt.intrusionMonitoring.interceptor.AuthInterceptor;
import kr.co.enitt.intrusionMonitoring.util.ObjectUtil;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	/** InitPageInterceptor Values */
	@Value("${project.init.page.interceptor.patterns:}")
	private String initPageInterceptorPathPatterns;
	@Value("${project.init.page.interceptor.exclude.patterns:}")
	private String excludeInitPageInterceptorPathPatterns;
	
	@Autowired private AuthInterceptor authInterceptor;
	//@Autowired private InitPageInterceptor initPageInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		addInterceptor(registry, authInterceptor, initPageInterceptorPathPatterns, excludeInitPageInterceptorPathPatterns);
	}
	
	
	/**
	 * Interceptor 추가 메소드
	 * @param registry
	 */
	private void addInterceptor(InterceptorRegistry registry, AuthInterceptor interceptor, String pathPatterns, String excludePathPatterns) {
		InterceptorRegistration interceptorRegistration = null;
		System.out.println("interceptor pathPatterns : " + pathPatterns);
		System.out.println("interceptor excludePathPatterns : " + excludePathPatterns);
		
		if (ObjectUtil.isNotEmpty(pathPatterns)) {
			String[] patterns = pathPatterns.split(",");
			List<String> patternList = Arrays.stream(patterns).map(String::trim).collect(Collectors.toList());
			interceptorRegistration = registry.addInterceptor(interceptor).addPathPatterns(patternList);
		}
		
		if (interceptorRegistration != null && ObjectUtil.isNotEmpty(excludePathPatterns)) {
			String[] excludePatterns = excludePathPatterns.split(",");
			List<String> excludePatternList = Arrays.stream(excludePatterns).map(String::trim).collect(Collectors.toList());
			interceptorRegistration.excludePathPatterns(excludePatternList);
		}
	}
}