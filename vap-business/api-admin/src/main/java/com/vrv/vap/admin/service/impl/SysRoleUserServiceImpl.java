package com.vrv.vap.admin.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.vrv.vap.admin.mapper.SysRoleUserMapper;
import com.vrv.vap.admin.model.SysRoleUser;
import com.vrv.vap.admin.service.SysRoleUserService;
import com.vrv.vap.db.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:02
 */
@Service
public class SysRoleUserServiceImpl extends BaseServiceImpl<SysRoleUser, Integer> implements SysRoleUserService {
    @Resource
    private SysRoleUserMapper sysRoleUserMapper;

    @Override
    public int deleteUserAndRoles(Integer uid, List<Integer> roles) {
        Example example = new Example(SysRoleUser.class);
        Example.Criteria criteria = example.createCriteria();
        //模糊查询
        if (!StringUtils.isEmpty(uid)) {
            criteria.andEqualTo("userId", uid);
        }

        if (!CollectionUtil.isEmpty(roles)) {
            criteria.andIn("roleId", roles);
        }
        // 删除中间表中该用户的角色信息
        return sysRoleUserMapper.deleteByExample(example);
    }

    @Override
    public int insertUserAndRoles(Integer uid, List<Integer> roles) {
        List<SysRoleUser> list = new ArrayList<>();
        for (Integer rid : roles) {
            SysRoleUser userRole = new SysRoleUser();
            userRole.setUserId(uid);
            userRole.setRoleId(rid);
            list.add(userRole);
        }
        return sysRoleUserMapper.insertList(list);
    }
}
