package com.vrv.vap.admin.vo;

import com.vrv.vap.common.page.QueryCodition;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liujinhui
 * date 2021/4/28 22:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "角色")
public class RoleVO extends QueryCodition implements Serializable {

    @ApiModelProperty(value = "角色名称")
    private String name;
}
