package com.vrv.vap.admin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author wh1107066
 */
@Table(name = "sys_menu")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class SysMenu {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "parent_id")
    private Integer parentId;

    private String name;

    private String url;

    private String path;

    @Column(name = "path_method")
    private String pathMethod;

    private String css;

    private Integer sort;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private Boolean type;

    private Boolean hidden;

    /**
     * 租户字段
     */
    @Column(name = "tenant_id")
    private String tenantId;

    /**
     *  该节点下的子节点集合
     */
    private List<SysMenu> children;

    public List<SysMenu> getChildren() {
        return children;
    }

    public void setChildren(List<SysMenu> children) {
        this.children = children;
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return parent_id
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return path
     */
    public String getPath() {
        return path;
    }

    /**
     * @param path
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * @return path_method
     */
    public String getPathMethod() {
        return pathMethod;
    }

    /**
     * @param pathMethod
     */
    public void setPathMethod(String pathMethod) {
        this.pathMethod = pathMethod;
    }

    /**
     * @return css
     */
    public String getCss() {
        return css;
    }

    /**
     * @param css
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * @return create_time
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return update_time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return type
     */
    public Boolean getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(Boolean type) {
        this.type = type;
    }

    /**
     * @return hidden
     */
    public Boolean getHidden() {
        return hidden;
    }

    /**
     * @param hidden
     */
    public void setHidden(Boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * 获取租户字段
     *
     * @return tenant_id - 租户字段
     */
    public String getTenantId() {
        return tenantId;
    }

    /**
     * 设置租户字段
     *
     * @param tenantId 租户字段
     */
    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }


    @Override
    public String toString() {
        return "SysMenu{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                ", path='" + path + '\'' +
                ", pathMethod='" + pathMethod + '\'' +
                ", css='" + css + '\'' +
                ", sort=" + sort +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", type=" + type +
                ", hidden=" + hidden +
                ", tenantId='" + tenantId + '\'' +
                ", children=" + children +
                '}';
    }
}