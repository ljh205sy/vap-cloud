package com.vrv.vap.admin.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vrv.vap.admin.mapper.SysRoleMapper;
import com.vrv.vap.admin.model.SysRole;
import com.vrv.vap.admin.service.SysRoleService;
import com.vrv.vap.db.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:28
 */
@Service
public class SysRoleServiceImpl extends BaseServiceImpl<SysRole, Integer> implements SysRoleService {
    @Resource
    private SysRoleMapper sysRoleMapper;


    @Override
    public List<SysRole> queryRoleByExampleAndPagination(Integer page, Integer rows, String rname) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(rname)) {
            criteria.andLike("username", "%" + rname + "%");
        }
        PageHelper.startPage(page, rows);
        List<SysRole> sysRoles = sysRoleMapper.selectByExample(example);
        PageInfo pageInfo = new PageInfo(sysRoles);
        return pageInfo.getList();
    }

    @Override
    public SysRole queryMenusByRid(Integer rid) {
        return sysRoleMapper.queryMenusByRid(rid);
    }
}
