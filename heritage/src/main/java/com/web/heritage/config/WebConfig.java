package com.web.heritage.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.web.common.interceptor.JwtInterceptor;

@ComponentScan({"com.web.common.interceptor"})
@Configuration
public class WebConfig implements WebMvcConfigurer {
	private static final String[] EXCLUDE_PATHS = {"/error/**", "/heritages/**", "/heritage/**", "/user/**",
		"/noticepage/**", "/images",
		"/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html", "/webjars/**" // Swagger 권한 처리
	};

	@Autowired
	private JwtInterceptor jwtInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// System.out.println(jwtInterceptor);
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/**")// 기본 적용 경로
			.excludePathPatterns(EXCLUDE_PATHS);// 적용 제외 경로
		//		registry.addInterceptor(jwtInterceptor).addPathPatterns("/user/**", "/article/**", "/memo/**") // 기본 적용 경로
		//        .excludePathPatterns(Arrays.asList("/user/confirm/**", "/article/list"));// 적용 제외 경로
	}

	//  Interceptor를 이용해서 처리하므로 전역의 Corss Origin 처리를 해준다.
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD");
		//		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*").allowedHeaders("*")
		//				.exposedHeaders("auth-token");
	}

}
