package com.vrv.vap.admin.service.impl;

import com.vrv.vap.admin.mapper.SysUserMapper;
import com.vrv.vap.admin.model.SysUserModel;
import com.vrv.vap.admin.service.SysUserService;
import com.vrv.vap.db.service.impl.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * @author wh1107066
 */
@Service("sysUserService")
@Transactional(rollbackFor = RuntimeException.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserModel, Integer> implements SysUserService {
    @Resource
    private SysUserMapper sysUserMapper;


}