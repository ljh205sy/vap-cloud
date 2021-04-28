package com.vrv.vap.common.feign;


import com.vrv.vap.common.config.FeignConfiguration;
import com.vrv.vap.common.constant.ServiceNameConstants;
import com.vrv.vap.common.feign.fallback.SysUserServiceFallbackFactory;
import com.vrv.vap.common.model.SysUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author wh1107066
 */
@FeignClient(name = ServiceNameConstants.USER_SERVICE, contextId = "vap-common",configuration = FeignConfiguration.class, fallbackFactory = SysUserServiceFallbackFactory.class, decode404 = true)
public interface UserService {
    /**
     * feign rpc访问远程/users/{username}接口， 查询用户实体对象SysUser
     *
     * @param username 用户名称
     * @return  查询用户实体对象SysUser
     */
    @GetMapping(value = "/user/name/{username}")
    SysUser selectByUsername(@PathVariable("username") String username);
}
