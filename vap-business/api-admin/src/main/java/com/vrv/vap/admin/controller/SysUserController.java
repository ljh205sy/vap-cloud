package com.vrv.vap.admin.controller;


import com.vrv.vap.admin.model.SysUserModel;
import com.vrv.vap.admin.service.SysUserService;
import com.vrv.vap.common.annotation.LoginUser;
import com.vrv.vap.common.exception.BusinessException;
import com.vrv.vap.common.model.SysUser;
import com.vrv.vap.db.annotation.DataSource;
import com.vrv.vap.db.common.DataSourceType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

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

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(value = "根据ID查询")
    @GetMapping("/{id}")
    @DataSource(DataSourceType.SLAVE)
    public SysUserModel selectOne(@PathVariable Integer id) {
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
    @GetMapping
    public SysUser user(@LoginUser(isFull = true) SysUser sysUser) {
        logger.info("----------------------进入到方法体中----------------------");
        logger.info(sysUser == null ? "空" : "---->" + sysUser.toString());
        return sysUser;
    }


    @DataSource(DataSourceType.SLAVE)
    @GetMapping(value = "/name/{username:admin}")
    public SysUser selectByUsername(@PathVariable("username") String username) {
        logger.info("++++++++++++++feign接口调用++++++++++++++");
        SysUserModel sysUserModel = new SysUserModel();
        sysUserModel.setUsername(username);
        SysUserModel source = sysUserService.selectOne(sysUserModel);

        if (!Optional.of(source).isPresent()) {
            throw new BusinessException(String.format("用户不存在,username:%s", username));
        }
        SysUser target = new SysUser();
        BeanUtils.copyProperties(source, target);
        return target;
    }

    @ApiOperation(value = "查询所有数据")
    @GetMapping("/all")
    public List<SysUserModel> selectAll() {
        return sysUserService.findAll();
    }

}