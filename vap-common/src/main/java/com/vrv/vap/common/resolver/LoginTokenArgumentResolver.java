package com.vrv.vap.common.resolver;

import cn.hutool.core.util.StrUtil;
import com.vrv.vap.common.annotation.LoginUser;
import com.vrv.vap.common.constant.SecurityConstants;
import com.vrv.vap.common.feign.UserService;
import com.vrv.vap.common.model.SysUser;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * Token转化SysUser
 */
@Slf4j
@ConditionalOnBean(com.vrv.vap.common.feign.UserService.class)
public class LoginTokenArgumentResolver implements HandlerMethodArgumentResolver {
    private Logger logger = LoggerFactory.getLogger(getClass());
    private UserService sysUserService;

    public LoginTokenArgumentResolver(UserService sysUserService) {
        this.sysUserService = sysUserService;
    }

    /**
     * 入参筛选
     *
     * @param methodParameter 参数集合
     * @return 格式化后的参数
     */
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(LoginUser.class) && methodParameter.getParameterType().equals(SysUser.class);
    }

    /**
     * @param methodParameter       入参集合
     * @param modelAndViewContainer model 和 view
     * @param nativeWebRequest      web相关
     * @param webDataBinderFactory  入参解析
     * @return 包装对象
     */
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        if (logger.isDebugEnabled()) {
            logger.debug("-------拦截注解@LoginUser, 并进行参数塞入到header中，请求--------");
        }
        LoginUser loginUser = methodParameter.getParameterAnnotation(LoginUser.class);
        boolean isFull = loginUser.isFull();
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        String userId = request.getHeader(SecurityConstants.USER_ID_HEADER);
        String username = request.getHeader(SecurityConstants.USER_HEADER);
        String roles = request.getHeader(SecurityConstants.ROLE_HEADER);
        if (StrUtil.isBlank(username)) {
            log.warn("resolveArgument 错误，传递的username为空！{}", " error username is empty");
            return null;
        }

        // TODO：构建User对象及用户所拥有的角色
        SysUser user;
        if (isFull) {
            if (logger.isDebugEnabled()) {
                logger.debug("----------------拦截注解@LoginUser, 进行feign客户端调用----------");
            }
            user = sysUserService.selectByUsername(username);
        } else {
            user = new SysUser();
            user.setId(Integer.valueOf(userId));
            user.setUsername(username);
        }
        return user;
    }
}
