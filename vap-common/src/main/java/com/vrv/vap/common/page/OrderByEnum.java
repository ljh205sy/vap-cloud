package com.vrv.vap.common.page;

/**
 * 排序
 * @author liujinhui
 * date 2021/4/28 22:52
 */
public enum OrderByEnum {
    /**
     * 升序
     */
    ASC("asc"),
    /**
     * 降序
     */
    DESC("desc");
    private String descriptor;

     OrderByEnum(String descriptor){
        this.descriptor = descriptor;
    }


}
