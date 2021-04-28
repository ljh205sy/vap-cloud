package com.vrv.vap.common.feign.fallback;

import com.vrv.vap.common.feign.UserService;
import com.vrv.vap.common.model.SysUser;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * userService降级工场
 */
@Slf4j
public class SysUserServiceFallbackFactory implements FallbackFactory<UserService> {
    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public SysUser selectByUsername(String username) {
                log.error("通过用户名查询用户异常:{}", username, throwable);
                return new SysUser();
            }
        };
    }
}
