package com.vrv.vap.zuul.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.vap.vap.redis.template.RedisRepository;
import com.vrv.vap.core.constant.SecurityConstants;
import com.vrv.vap.core.model.SysUser;
import com.vrv.vap.zuul.properties.SecurityProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author liujinhui
 * date 2021/4/26 9:50
 */
@Component
public class PermissionsZuulFilter extends ZuulFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SecurityProperties securityProperties;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisRepository redisRepository;

    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return -3;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        String requestURI = request.getRequestURL().toString();
        // 最先执行的过滤器， filterOrder的值为-3

        //  针对登录、登出不进行拦截请求 /login ,  /logout等, 其他的filter也不进行run()方法的操作， 请求包含/login, 返回false
        boolean flag = !requestURI.contains("/login") && !requestURI.contains("/logout");
        ctx.set("should_filter", flag);

        //  针对其他请求，需要验证是否登录

        //非登录用户发送令牌或者是登录时不进行拦截请求


        logger.info("send  {} request to {} ", request.getMethod(), requestURI);
        // 请求URL内不包含login或join则需要经过该过滤器，即执行run()


//        String[] httpUrls = securityProperties.getIgnore().getHttpUrls();
//        //判断不进行url权限认证的api，所有已登录用户都能访问的url
//        for (String path : httpUrls) {
//            if (antPathMatcher.match(path, requestURI)) {
//                return false;
//            }
//        }

        return flag;
    }

    @Override
    public Object run() {
        logger.info("-------PermissionsZuulFilter--------");

        RequestContext ctx = RequestContext.getCurrentContext();
        // 跳过不需要验证的路径
        ctx.addZuulRequestHeader(SecurityConstants.USER_ID_HEADER, "1");
        String userId = ctx.getRequest().getHeader(SecurityConstants.USER_ID_HEADER);

        HttpSession session = ctx.getRequest().getSession();
        String sesid = session.getId();
        logger.info("从用户获取的sessionID:" + sesid);
        SysUser user = (SysUser) session.getAttribute(userId);

        String sessionid = (String)redisTemplate.opsForValue().get(SecurityConstants.USER_ID_HEADER + ":" + userId);

        // springsession 是以jdk的方式进行的序列化
        // 如果需要反序列化就需要jdk的方式，默认是json的方式？？


        logger.info("zuul中获取用户信息:{}", user);
        ctx.addZuulRequestHeader(SecurityConstants.USER_HEADER, "admin");
        ctx.addZuulRequestHeader("from", "zuul");
        // 不转发请求到后端服务中，例如controller中是获取不到请求的。但是其他的filter中依然可以执行
        ctx.setSendZuulResponse(false);
        return null;
    }
}
