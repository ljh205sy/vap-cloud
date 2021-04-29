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
@EnableConfigurationProperties(DruidProperties.class)
@AutoConfigureBefore({DataSourceAutoConfiguration.class, DruidDataSourceAutoConfigure.class})
@AutoConfigureAfter(DruidProperties.class)
public class DynamicDataSourceConfig {

    @Autowired
    private DruidProperties druidProperties;

    /**
     * 通过全限定名进行配置的绑定，这里的ConfigurationProperties其实就类似于使用多个@Value同时绑定，
     * 绑定的对象就是DataSource类型的对象，而且是 隐式绑定 的，意味着在配置文件编写的时候需要与对应类的字段名称相同，
     * https://www.cnblogs.com/tian874540961/p/12146467.html
     * @return 主数据源
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.master")
    public DataSource masterDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DruidDataSource master = druidProperties.dataSource(druidDataSource);
        return master;
    }

    /**
     * 把spring.datasource.druid.slave 关联配置文件进行绑定到slaveDataSource的bean中，，然后将此Bean归还给容器
     * DataSource中也存在url的属性。 把前缀spring.datasource.druid.slave的下的属性值进行绑定。
     * @return 第二数据源对象
     */
    @Bean
    @ConfigurationProperties("spring.datasource.druid.slave")
    @ConditionalOnProperty(prefix = "spring.datasource.druid", name = "enableSlave", havingValue = "true")
    public DataSource slaveDataSource() {
        DruidDataSource druidDataSource = DruidDataSourceBuilder.create().build();
        DruidDataSource slave = druidProperties.dataSource(druidDataSource);
        return slave;
    }

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource masterDataSource, DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER.name(), masterDataSource);
        if (druidProperties.enableSlave) {
            targetDataSources.put(DataSourceType.SLAVE.name(), slaveDataSource);
        }
        return new DynamicDataSource(masterDataSource, targetDataSources);
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
