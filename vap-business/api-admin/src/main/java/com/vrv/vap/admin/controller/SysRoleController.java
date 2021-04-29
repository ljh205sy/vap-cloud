package com.vrv.vap.admin.controller;

import com.github.pagehelper.PageInfo;
import com.vrv.vap.admin.model.SysMenu;
import com.vrv.vap.admin.model.SysRole;
import com.vrv.vap.admin.service.SysRoleService;
import com.vrv.vap.admin.vo.RoleVO;
import com.vrv.vap.common.controler.BaseController;
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

import java.util.List;

/**
 * @author liujinhui
 * date 2021/3/30 21:30
 */
@RestController
@RequestMapping("/role")
@Api(tags = "角色管理接口")
public class SysRoleController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysRoleService sysRoleService;

    /**
     * 直接获取参数进行查询
     *
     * @param name 界面上查询条件
     * @param pageNum 第几个页开始查找
     * @param pageSize 每次查找多少条数据
     * @return 返回分页对象
     */
    @GetMapping("/test")
    @ApiImplicitParams({@ApiImplicitParam(name = "name", value = "角色名称"),
            @ApiImplicitParam(name = "pageNum", value = "第几条开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示几条")})
    @ApiOperation(value = "角色分页对象")
    public PageResult<SysRole> queryRoleByExampleAndPagination(@RequestParam String name, @RequestParam int pageNum, @RequestParam(name = "pageSize") int pageSize) {
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(name)) {
            criteria.andLike("name", "%" + name + "%");
        }
        PageInfo<SysRole> pageExample = sysRoleService.findPageExample(pageNum, pageSize, example);
        return PageResult.succeed(pageExample);
    }

    /**
     * 通过对象来封装
     * 参数为对象， 必须继承QueryCondition的，
     * 返回分页对象
     */
    @GetMapping
    @ApiOperation(value = "查询角色分页显示", notes = "角色分页显示")
    public PageResult<SysRole> queryRoleAndPagination(RoleVO roleVO) {
        PageInfo<SysRole> pageInfo;
        Example example = new Example(SysRole.class);
        Example.Criteria criteria = example.createCriteria();
        String rname = roleVO.getName();
        if (!StringUtils.isEmpty(rname)) {
            criteria.andLike("name", "%" + rname + "%");
        }
        String sortOrderByClause="";
        if (!StringUtils.isEmpty(roleVO.getOrderColumn())) {
            /**
             * example.setOrderByClause("`index` ASC,`AFTER_CHECK_TIME` ASC");
             **/
            sortOrderByClause = roleVO.getOrderColumn() + " " + roleVO.getSort();
            example.setOrderByClause(sortOrderByClause);
        }
        pageInfo = sysRoleService.findPageExample(roleVO.getPageNum(), roleVO.getPageSize(), sortOrderByClause, example);
        PageResult<SysRole> succeed = PageResult.succeed(pageInfo);
        return succeed;
    }

    /**
     * 依赖role的id进行查询对象，该对象不会返回资源列表信息
     *
     * @param rid 角色ID
     * @return result
     */
    @GetMapping("/{rid:\\d+}")
    public Result<SysRole> queryRoleById(@PathVariable Integer rid) {
        SysRole sysRole = sysRoleService.findByPrimaryKey(rid);
        List<SysMenu> menus = sysRole.getMenus();
        System.out.println(menus);
        return Result.succeed(sysRole);
    }

    /**
     * 角色新增
     *
     * @param sysRole 对象
     * @return 返回result
     */
    @PostMapping
    public Result<SysRole> insertRole(@RequestBody SysRole sysRole) {
        sysRoleService.insertSelective(sysRole);
        return Result.succeed(sysRole);
    }


    @DeleteMapping("/{rid:\\d+}")
    public Result<Boolean> deleteRoleById(@PathVariable Integer rid) {
        sysRoleService.deleteByPrimaryKey(rid);
        return Result.succeed(true);
    }

    @GetMapping("/menu/{rid:\\d+}")
    @ApiImplicitParams({@ApiImplicitParam(name = "rid", value = "角色ID", required = true, dataType = "Integer")})
    @ApiOperation(value = "角色ID加载该角色所有资源", notes = "角色ID不能为空")
    public Result<SysRole> querySysRoleAndMenus(@PathVariable Integer rid) {
        SysRole sysRole = sysRoleService.queryMenusByRid(rid);
        List<SysMenu> menus = sysRole.getMenus();
        System.out.println(menus);
        return Result.succeed(sysRole);
    }
}
