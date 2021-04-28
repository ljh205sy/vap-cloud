package com.vrv.vap.admin.service;

import com.vrv.vap.admin.model.SysMenu;
import com.vrv.vap.db.service.BaseService;

import java.util.List;


/**
 * @author liujinhui
 * date 2021/3/30 17:36
 */
public interface SysMenuService  extends BaseService<SysMenu, Integer> {

    /**
     *  查询第一级的所有子节点,
     */
    List<SysMenu> queryOneLevelChildrenByParentId(String tenantId, Integer parentId);


    /**
     * 查询所有数据，
     * @param tenantId 客户端ID
     * @return 菜单集合
     */
    List<SysMenu> queryChildrenByTenantId(String tenantId);


    /**
     *  查询父节点下的所有子节点，递归
     */
    List<SysMenu> loadRecursionChildren(String tenantId);


    List<SysMenu> loadChildren(String tenantId);
}
