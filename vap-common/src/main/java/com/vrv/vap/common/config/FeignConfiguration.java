package com.vrv.vap.common.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Fegin接口调用时，主动在头部塞入用户信息，只要是feign接口调用，携带header的config的配置就会存在这个打印信息。
 *
 * @author liujinhui
 * date 2021/4/26 18:04
 */

@Configuration
public class FeignConfiguration implements RequestInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void apply(RequestTemplate template) {
        logger.info("--------> feign 传递参数 ， feign接口日志全量打印输出-------");
        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        if (headerNames != null) {
            while (headerNames.hasMoreElements()) {
                String name = headerNames.nextElement();
                String values = request.getHeader(name);
                logger.info("打印头部信息-------->" + values);
                template.header(name, values);
            }
        }
    }
}