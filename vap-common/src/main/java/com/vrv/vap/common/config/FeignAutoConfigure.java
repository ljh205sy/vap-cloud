package com.vrv.vap.common.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Feign统一配置，记录打印级别，全部打印
 *
 * @author liujinhui
 * date 2021/4/26 18:09
 */
@Configuration
public class FeignAutoConfigure {
    /**
     * Feign 日志级别
     */
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}






