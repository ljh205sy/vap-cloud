package com.vrv.vap.common.page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liujinhui
 * date 2021/4/28 22:29
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QueryCodition implements Serializable {

    private int pageNum;
    private int pageSize;
    private String orderColumn;
    private String sort = OrderByEnum.ASC.name();
}
