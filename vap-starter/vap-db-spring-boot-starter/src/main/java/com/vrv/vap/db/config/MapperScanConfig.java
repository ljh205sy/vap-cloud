package com.vrv.vap.db.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Configuration;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wh1107066
 */
@Configuration
@ConditionalOnBean(SqlSessionFactory.class)
@MapperScan(basePackages = "com.vrv.vap.*.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MapperScanConfig {

}
