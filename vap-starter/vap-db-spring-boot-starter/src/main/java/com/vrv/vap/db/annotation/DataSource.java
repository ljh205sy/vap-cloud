package com.vrv.vap.db.annotation;

import com.vrv.vap.db.common.DataSourceType;

import java.lang.annotation.*;

/**
 * 多数据源注解
 *
 * @author wh1107066
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    DataSourceType value() default DataSourceType.MASTER;
}