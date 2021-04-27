package com.vrv.vap.prometheus.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: liujinhui
 * @date: 2020/10/13 1:03
 */
@Configuration
public class PrometheusAutoConfiguration {
    @Value("${spring.application.name:unknown_service_name}")
    private String applicationName;

    @Bean
    MeterRegistryCustomizer meterRegistryCustomizer(MeterRegistry meterRegistry) {
        return meterRegistry1 -> {
            meterRegistry.config().commonTags("application", applicationName);
        };
    }
}
