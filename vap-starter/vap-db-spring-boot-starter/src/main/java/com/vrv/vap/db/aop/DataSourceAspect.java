package com.vrv.vap.db.aop;


import com.vrv.vap.db.annotation.DataSource;
import com.vrv.vap.db.common.DataSourceType;
import com.vrv.vap.db.config.DynamicDataSourceHelper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 多数据源，切面处理类
 *
 * @author wh1107066
 */
@Aspect
@Component
public class DataSourceAspect implements Ordered {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 选择切入点为DataSource注解
     */
    @Pointcut("@annotation(com.vrv.vap.db.annotation.DataSource)" +
            " || @within(com.vrv.vap.db.annotation.DataSource)")
    public void dataSourcePointCut() {
    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        DataSource dataSource = getDataSource(point);
        if (dataSource == null) {
            //如果不存在是直接走的master主库
            DynamicDataSourceHelper.setDataSourceType(DataSourceType.MASTER.name());
            logger.info("设置数据源set datasource is " + DataSourceType.MASTER);
        } else {
            // 如果不为空，从注解中获取是走主库还是从库
            DynamicDataSourceHelper.setDataSourceType(dataSource.value().name());
            logger.info("设置数据源set datasource is " + dataSource.value());
        }

        try {
            return point.proceed();
        } finally {
            DynamicDataSourceHelper.clearDataSourceType();
            logger.info("ThreadLocal清除缓存 datasource");
        }
    }

    /**
     * 获取需要切换的数据源
     */
    public DataSource getDataSource(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Class<? extends Object> targetClass = point.getTarget().getClass();
        DataSource targetDataSource = targetClass.getAnnotation(DataSource.class);
        if (targetDataSource != null) {
            return targetDataSource;
        } else {
            Method method = signature.getMethod();
            DataSource dataSource = method.getAnnotation(DataSource.class);
            return dataSource;
        }
    }

    @Override
    public int getOrder() {
        return 1;
    }
}