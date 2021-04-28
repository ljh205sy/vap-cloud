package com.vrv.vap.common.model;

/**
 * @author liujinhui
 * date 2021/4/28 10:15
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author wh1107066
 */
@Table(name = "sys_user")
public class SysUser implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;

    private String password;

    private String nickname;

    @Column(name = "head_img_url")
    private String headImgUrl;

    private String mobile;

    private Boolean sex;

    private Boolean enabled;

    private String type;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_time")
    private Date updateTime;

    private String company;

    @Column(name = "open_id")
    private String openId;

    @Column(name = "is_del")
    private Boolean isDel;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return head_img_url
     */
    public String getHeadImgUrl() {
        return headImgUrl;
    }

    /**
     * @param headImgUrl
     */
    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    /**
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return sex
     */
    public Boolean getSex() {
        return sex;
    }

    /**
     * @param sex
     */
    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    /**
     * @return enabled
     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * @param enabled
     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    /**
     * @return type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type
     */
    public void setType(String type) {
        this.type = type;
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
     * @return company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return open_id
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * @param openId
     */
    public void setOpenId(String openId) {
        this.openId = openId;
    }

    /**
     * @return is_del
     */
    public Boolean getIsDel() {
        return isDel;
    }

    /**
     * @param isDel
     */
    public void setIsDel(Boolean isDel) {
        this.isDel = isDel;
    }


    @Override
    public String toString() {
        return "SysUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", headImgUrl='" + headImgUrl + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex=" + sex +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", company='" + company + '\'' +
                ", openId='" + openId + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}