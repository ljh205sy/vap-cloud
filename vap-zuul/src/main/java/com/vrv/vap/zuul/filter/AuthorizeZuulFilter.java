package com.vrv.vap.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.vrv.vap.zuul.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;

/**
 * @author liujinhui
 * date 2021/4/26 9:50
 */
@Component
public class AuthorizeZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SecurityProperties securityProperties;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -2;
    }

    @Override
    public boolean shouldFilter() {
        //非登录用户发送令牌或者是登录时不进行拦截请求
        RequestContext ctx = RequestContext.getCurrentContext();
        Boolean isSuccess = (Boolean) ctx.get("should_filter");
        return isSuccess;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        logger.info("-------AuthorizeZuulFilter--------");
        return null;
    }
}
