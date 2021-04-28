package com.vrv.vap.admin.mapper;

import com.vrv.vap.admin.model.SysMenu;
import com.vrv.vap.db.basemapper.BaseBatisMapper;

import java.util.List;

/**
 * @author wh1107066
 */
public interface SysMenuMapper extends BaseBatisMapper<SysMenu, Integer> {

    /**
     * 根据角色返回菜单
     * @param rid 角色ID
     * @return 返回SysMenu集合
     */
    List<SysMenu> queryMenusByRoleId(Integer rid);

    List<SysMenu> queryChildrenByParentId(SysMenu sysMenu);

    SysMenu queryByMenuId(Integer mid);

    List<SysMenu> queryByTenantId(String tenantId);
}