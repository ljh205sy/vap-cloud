package com.vrv.vap.vapservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.lang.annotation.*;

/**
 * @author liujinhui
 * date 2021/4/19 17:23
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.vrv.vap"}, exclude = {DataSourceAutoConfiguration.class})
public @interface VapCloudApplication {
}
