package com.vrv.vap.admin.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.admin.model.SysMenu;
import com.vrv.vap.admin.service.SysMenuService;
import com.vrv.vap.admin.vo.MenuVO;
import com.vrv.vap.common.page.PageResult;
import com.vrv.vap.common.page.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.validation.Valid;
import java.util.List;

/**
 * @author liujinhui
 * date 2021/4/2 16:18
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理接口")
public class SysMenuController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysMenuService sysMenuService;

    /**
     * 通过对象来封装
     * @ menuQuery.name 界面上查询条件
     * @ page 第几个位置开始查找
     * @ rows 每次查找多少条数据
     * 参数为对象， 必须继承Query对象
     * 返回分页对象
     */
    @GetMapping
    public PageResult<SysMenu> queryMenuAndPagination(@RequestBody MenuVO menuVO) {
        Example example = new Example(SysMenu.class);
        Example.Criteria criteria = example.createCriteria();
        String rname = menuVO.getName();
        if (!StringUtils.isEmpty(rname)) {
            criteria.andLike("name", "%" + rname + "%");
        }
        PageInfo<SysMenu> pageInfo = sysMenuService.findPageExample(menuVO.getPageNum(), menuVO.getPageSize(), example);
        return PageResult.succeed(pageInfo);
    }


    /**
     * 依赖role的id进行查询对象
     *
     * @param rid 角色ID
     * @return result
     */
    @GetMapping("/{rid:\\d+}")
    public Result<SysMenu> queryMenuById(@PathVariable Integer rid) {
        SysMenu sysMenu = sysMenuService.findByPrimaryKey(rid);
        return Result.succeed(sysMenu);
    }

    /**
     * 角色新增
     *
     * @param sysMenu 对象
     * @return 返回result
     */
    @PostMapping
    public Result<SysMenu> insertMenu(@RequestBody SysMenu sysMenu) {
        sysMenuService.insertSelective(sysMenu);
        return Result.succeed(sysMenu);
    }


    @DeleteMapping("/{mid:\\d+}")
    public Result<Boolean> deleteMenuById(@PathVariable Integer mid) {
        sysMenuService.deleteByPrimaryKey(mid);
        return Result.succeed(true);
    }

    @PutMapping
    public Result<Boolean> updateMenu(@Valid @RequestBody SysMenu sysMenu) {
        if (sysMenu == null || sysMenu.getId() == null) {
            throw new RuntimeException("删除菜单异常！");
        }
        sysMenuService.updateSelective(sysMenu);
        return Result.succeed(true);
    }

    @GetMapping("/children/{tenantId}")
    @ApiImplicitParams({@ApiImplicitParam(name = "tenantId", value = "租户ID", required = true, dataType = "String")})
    @ApiOperation(value = "租户ID", notes = "租户ID不能为空")
    public Result<List<SysMenu>> menuChildren(@PathVariable(required = true) String tenantId){
        List<SysMenu> list = sysMenuService.loadChildren(tenantId);
        return Result.succeed(list);
    }
}
