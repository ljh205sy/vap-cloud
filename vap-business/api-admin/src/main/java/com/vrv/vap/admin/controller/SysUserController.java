package com.vrv.vap.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.vrv.vap.admin.exception.UserNotExistException;
import com.vrv.vap.admin.model.SysRole;
import com.vrv.vap.admin.model.SysUser;
import com.vrv.vap.admin.service.SysRoleUserService;
import com.vrv.vap.admin.service.SysUserService;
import com.vrv.vap.common.annotation.LoginUser;
import com.vrv.vap.common.exception.BusinessException;
import com.vrv.vap.common.page.Result;
import com.vrv.vap.db.annotation.DataSource;
import com.vrv.vap.db.common.DataSourceType;
import io.swagger.annotations.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wh1107066
 */
@RestController
@RequestMapping("user")
@Api(tags = "用户管理")
public class SysUserController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 服务对象
     */
    @Resource
    private SysUserService sysUserService;
    @Autowired
    private SysRoleUserService sysUserRoleService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    @DataSource(DataSourceType.SLAVE)
    public SysUser selectOne(@PathVariable Integer id) {
        return sysUserService.findByPrimaryKey(id);
    }

    /**
     * 主要测试该方法： http://localhost:9900/user
     * header中携带id   username, 构建SysUser返回
     *
     * @param sysUser 通过注解方式进行获取
     * @return 返回用户对象
     */
    @ApiOperation(value = "获取当前用户，需要传入userID和username")
    @GetMapping("/test")
    public SysUser user(@LoginUser(isFull = true) SysUser sysUser) {
        logger.info("----------------------进入到方法体中----------------------");
        logger.info(sysUser == null ? "空" : "---->" + sysUser.toString());
        return sysUser;
    }


    @DataSource(DataSourceType.SLAVE)
    @GetMapping(value = "/name/{username:admin}")
    public SysUser selectByUsername(@PathVariable("username") String username) {
        logger.info("++++++++++++++feign接口调用++++++++++++++");
        SysUser sysUserModel = new SysUser();
        sysUserModel.setUsername(username);
        SysUser source = sysUserService.selectOne(sysUserModel);
        if (source == null) {
            throw new BusinessException(String.format("用户不存在,username:%s", username));
        }
        SysUser target = new SysUser();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @ApiOperation(value = "获取用户并加载角色")
    @GetMapping
    public List<SysUser> queryUsersByRoles() {
        List<SysUser> userList = sysUserService.queryUserAndRoles();
        return userList;
    }

    /**
     * 新增用户
     *
     * @return int
     */
    @ApiOperation("新增用户")
    @PostMapping
    public Result<SysUser> insertUser(@Valid @RequestBody SysUser sysUser) {
        logger.info(ReflectionToStringBuilder.toString(sysUser, ToStringStyle.MULTI_LINE_STYLE));
        sysUserService.insertSelective(sysUser);
        templateOperator(sysUser);
        return Result.succeed(sysUser);
    }

    @ResponseBody
    @PutMapping
    @ApiOperation(value = "用户修改", notes = "用户修改", hidden = false)
    public Result<Boolean> updateUser(@Valid @RequestBody SysUser sysUser) {
        if (sysUser == null || sysUser.getId() == null) {
            throw new UserNotExistException();
        }
        // findByProperty如果主键id为空，会查出所有的数据
        List<SysUser> list = sysUserService.findByProperty("id", sysUser.getId());
        if (CollectionUtil.isEmpty(list)) {
            throw new UserNotExistException(sysUser.getId());
        }
        logger.info(ReflectionToStringBuilder.toString(sysUser, ToStringStyle.MULTI_LINE_STYLE));
        sysUserService.updateSelective(sysUser);
        templateOperator(sysUser);
        return Result.succeed(true);
    }

    @DeleteMapping("/{uid:\\d+}")
    @ResponseBody
    @ApiImplicitParams({@ApiImplicitParam(name = "uid", value = "用户id")})
    @ApiOperation(value = "用户删除", notes = "删除用户")
    public Result<Boolean> deleteUserById(@PathVariable("uid") @ApiParam("用户id") Integer uid) {
        SysUser queryUser = new SysUser();
        queryUser.setId(uid);
        SysUser sysUser = sysUserService.selectOne(queryUser);
        if (sysUser == null) {
            throw new UserNotExistException(uid);
        }
        // 这个是真删除，注意这块可以写自己的业务逻辑
        // 删除用户的角色信息，一般是假删除。中间表中信息需要删除
        sysUserService.deleteByPrimaryKey(sysUser.getId());
        sysUserRoleService.deleteUserAndRoles(sysUser.getId(), null);
        return Result.succeed(true);
    }


    /**
     * 模版模式：
     * 1.先对用户角色表进行删除
     * 2.在对角色用户表进行新增
     *
     * @param sysUser
     */
    private void templateOperator(SysUser sysUser) {
        List<SysRole> roles = sysUser.getRoles();
        if (!CollectionUtil.isEmpty(roles)) {
            List<Integer> rids = roles.stream().map(p -> p.getId()).collect(Collectors.toList());
            sysUserRoleService.deleteUserAndRoles(sysUser.getId(), rids);
            sysUserRoleService.insertUserAndRoles(sysUser.getId(), rids);
        } else {
            sysUserRoleService.deleteUserAndRoles(sysUser.getId(), null);
        }
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping("/all")
    public List<SysUser> selectAll() {
        return sysUserService.findAll();
    }

}