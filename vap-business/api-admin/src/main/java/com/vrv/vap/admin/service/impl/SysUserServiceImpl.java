package com.vrv.vap.admin.service.impl;

import com.vrv.vap.admin.mapper.SysUserMapper;
import com.vrv.vap.admin.model.SysUser;
import com.vrv.vap.admin.service.SysUserService;
import com.vrv.vap.db.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author wh1107066
 */
@Service("sysUserService")
@Transactional(rollbackFor = RuntimeException.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUser, Integer> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;

    @Override
    public SysUser queryUserAndRolesByUid(Integer uid) {
        return sysUserMapper.queryUserAndRolesByUid(uid);
    }

    @Override
    public List<SysUser> queryUserAndRoles() {
        return sysUserMapper.queryUserAndRoles();
    }

}