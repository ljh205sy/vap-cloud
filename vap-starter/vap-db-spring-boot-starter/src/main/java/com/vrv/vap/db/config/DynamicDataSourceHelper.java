package com.vrv.vap.db.config;

/**
 * @author liujinhui
 * date 2021/4/24 21:50
 */
public class DynamicDataSourceHelper
{

    private static final ThreadLocal<String> CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源的变量
     */
    public static void setDataSourceType(String dsType) { CONTEXT_HOLDER.set(dsType); }

    /**
     * 获得数据源的变量
     */
    public static String getDataSourceType() { return CONTEXT_HOLDER.get(); }

    /**
     * 清空数据源变量
     */
    public static void clearDataSourceType()
    {
        CONTEXT_HOLDER.remove();
    }
}
