package com.vrv.vap.admin.service;

import com.vrv.vap.admin.model.SysUser;
import com.vrv.vap.db.service.BaseService;

import java.util.List;

/**
 * @author wh1107066
 */
public interface SysUserService extends BaseService<SysUser, Integer> {
    /**
     * 获取所有的用户，并携带所有的角色信息
     *
     * @return 用户信息
     */
    SysUser queryUserAndRolesByUid(Integer uid);

    List<SysUser> queryUserAndRoles();
}