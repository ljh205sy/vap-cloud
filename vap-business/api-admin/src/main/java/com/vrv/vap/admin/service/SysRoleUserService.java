package com.vrv.vap.admin.service;

import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:00
 */
public interface SysRoleUserService {
    /**
     * 删除用户角色表中的数据
     *
     * @param uid
     * @param roles
     * @return
     */
    int deleteUserAndRoles(Integer uid, List<Integer> roles);

    /**
     * 新增用户角色表中的数据
     *
     * @param uid
     * @param roles
     * @return
     */
    int insertUserAndRoles(Integer uid, List<Integer> roles);
}
