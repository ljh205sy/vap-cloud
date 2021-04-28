package com.vrv.vap.admin.mapper;

import com.vrv.vap.admin.model.SysRoleMenu;
import tk.mybatis.mapper.common.IdsMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface SysRoleMenuMapper extends MySqlMapper<SysRoleMenu>, Mapper<SysRoleMenu>, IdsMapper<SysRoleMenu> {
}