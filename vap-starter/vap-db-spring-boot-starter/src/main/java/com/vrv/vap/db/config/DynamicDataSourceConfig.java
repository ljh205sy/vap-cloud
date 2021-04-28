package com.vrv.vap.db.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.alibaba.druid.support.http.WebStatFilter;
import com.vrv.vap.db.common.DataSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liujinhui
 * date 2021/4/24 17:33
 */
@Configuration
@AutoConfigureBefore({DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@AutoConfigureAfter(DruidProperties.class)
@EnableConfigurationProperties(DruidProperties.class)
public class DynamicDataSourceConfig {

    @Autowired
    private DruidProperties druidProperties;

    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DruidDataSource master = druidProperties.dataSource(druidDataSource);
        return master;
    }

    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid.slave", name = "enabled", havingValue = "true")
    public DataSource slaveDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DruidDataSource slave = druidProperties.dataSource(druidDataSource);
        return slave;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource()
    {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource());
        if (druidProperties.enableSlave)
        {
            targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource());
        }
        return new DynamicDataSource(masterDataSource(), targetDataSources);
    }

    @Bean
    @ConditionalOnMissingBean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/static/*,/druid/*");
        return filterRegistrationBean;
    }


}
