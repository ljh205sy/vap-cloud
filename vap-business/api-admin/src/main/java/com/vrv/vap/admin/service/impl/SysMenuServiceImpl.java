package com.vrv.vap.admin.service.impl;


import com.vrv.vap.admin.mapper.SysMenuMapper;
import com.vrv.vap.admin.model.SysMenu;
import com.vrv.vap.admin.service.SysMenuService;
import com.vrv.vap.db.service.impl.BaseServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 17:38
 */
@Service
public class SysMenuServiceImpl extends BaseServiceImpl<SysMenu, Integer> implements SysMenuService {

    @Resource
    private SysMenuMapper sysMenuMapper;

    @Override
    public List<SysMenu> queryOneLevelChildrenByParentId(String tenantId, Integer parentId) {
        SysMenu sysMenu = new SysMenu();
        sysMenu.setTenantId(tenantId);
        sysMenu.setParentId(parentId);
        return sysMenuMapper.queryChildrenByParentId(sysMenu);
    }

    @Override
    public List<SysMenu> queryChildrenByTenantId(String tenantId) {
        return sysMenuMapper.queryByTenantId(tenantId);
    }

    @Override
    public List<SysMenu> loadRecursionChildren(String tenantId) {
        List<SysMenu> sysMenus = sysMenuMapper.queryByTenantId(tenantId);
        List<SysMenu> list = loadAllTop(sysMenus);
        return list;
    }


    /**
     *  测试OK
     * @param tenantId 第三方clientId
     * @return  返回树节点json信息
     */
    @Override
    public List<SysMenu> loadChildren(String tenantId) {
        List<SysMenu> sysMenus = sysMenuMapper.queryByTenantId(tenantId);
        List<SysMenu> topMenu = new ArrayList<>();

        // 获取顶级节点菜单
        for (SysMenu item : sysMenus) {
            Integer parentId = item.getParentId();
            if ("-1".equals(String.valueOf(parentId))) {
                topMenu.add(item);
            }
        }

        // 为一级菜单设置子节点，getChild是递归调用的
        for (SysMenu menu : topMenu) {
            menu.setChildren(getChild(menu.getId(), sysMenus));
        }
        return topMenu;
    }


    private List<SysMenu> loadAllTop(List<SysMenu> menus) {
        final String topId = "-1";
        List<SysMenu> topMenu = new ArrayList<>();
        for (SysMenu item : menus) {
            Integer parentId = item.getParentId();
            if (topId.equals(String.valueOf(parentId))) {
                topMenu.add(item);
            }
            // 所有菜单 ， 当前菜单
            item.setChildren(recursionChildren(menus, item));
        }
        return topMenu;
    }

    private List<SysMenu> recursionChildren(List<SysMenu> menus, SysMenu currMenu) {
        List<SysMenu> children = new ArrayList<>();
        for (SysMenu item : menus) {
            if (item.getParentId().equals(currMenu.getId())) {
                children.add(item);
            }
        }
        currMenu.setChildren(children.size() > 0 ? children : null);
        // 子节点递归
        for (SysMenu child : children) {
            recursionChildren(menus, child);
        }
        // 递归退出条件, 防止最末级点为空数组的情况
        if (children.size() == 0) {
            return null;
        }

        return children;
    }


    /**
     * 递归查找子菜单
     *
     * @param id  当前菜单id， 作为父节点，查找下面的子节点
     * @param allMenus 要查找的列表
     * @return 返回树形结构
     */
    private List<SysMenu> getChild(Integer id, List<SysMenu> allMenus) {
        // 子菜单
        List<SysMenu> childList = new ArrayList<>();
        for (SysMenu sysMenu : allMenus) {
            // 遍历所有节点，将父菜单id与数据中的所有的父节点id比较。 如果upid=当前传过来的节点，那么这个就是子节点
            if (StringUtils.isNotBlank(String.valueOf(sysMenu.getParentId()))) {
                // 循环每个菜单，查找属于当前节点的子节点
                if (sysMenu.getParentId().equals(id)) {
                    childList.add(sysMenu);
                }
            }
        }

        // 把子菜单的子菜单再循环一遍
        for (SysMenu sysMenu : childList) {
            // 递归
            sysMenu.setChildren(getChild(sysMenu.getId(), allMenus));
        }
        // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }

}
