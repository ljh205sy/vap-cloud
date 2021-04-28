package com.vrv.vap.common.config;


import com.vrv.vap.common.feign.UserService;
import com.vrv.vap.common.resolver.ClientArgumentResolver;
import com.vrv.vap.common.resolver.LoginTokenArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * 默认SpringMVC拦截器
 */
public class DefaultWebMvcConfig  implements WebMvcConfigurer {

	@Lazy
	@Autowired
	private UserService userService;
	/**
	 * Token参数解析
	 *
	 * @param argumentResolvers 解析类
	 */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		//注入用户信息
		argumentResolvers.add(new LoginTokenArgumentResolver(userService));
		//注入应用信息
		argumentResolvers.add(new ClientArgumentResolver());
	}
}
