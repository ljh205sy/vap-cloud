package com.vrv.vap.db.common;

/**
 * 增加多数据源，在此配置 , 自定义多数据源切换注解,在这里切换数据源名称
 * @author wh1107066
 */
public enum DataSourceType {
    /**
     * 主库
     */
    MASTER,

    /**
     * 从库
     */
    SLAVE
}