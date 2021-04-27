package com.vrv.vap.zuul.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

/**
 * @author zlt
 * @date 2019/1/4
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "vap.security")
@Configuration
@RefreshScope
public class SecurityProperties {
    private AuthProperties auth = new AuthProperties();

    private PermissionsProperties ignore = new PermissionsProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
