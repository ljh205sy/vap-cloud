package com.vrv.vap.common.page;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.List;

/**
 * 分页实体类
 *
 * @author wh1107066
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {
    private static final long serialVersionUID = -275582248840137389L;
    private static Logger logger = LoggerFactory.getLogger(PageResult.class);
    /**
     * 总数
     */
    private Long total;
    /**
     * 是否成功：0 成功、1 失败
     */
    private int code;
    /**
     * 当前页结果集
     */
    private List<T> list;
    /**
     * 消息
     */
    private String message;

    public static <T> PageResult<T> succeed(PageInfo<T> pageInfo) {
        PageResult<T> result = new PageResult<>();
        result.setCode(CodeEnum.SUCCESS.getCode());
        if (pageInfo != null && pageInfo.getTotal() > 0) {
            result.setTotal(pageInfo.getTotal());
            result.setList(pageInfo.getList());
        }
        if (logger.isDebugEnabled()) {
            //将封装好的数据返回到前台页面， 返回ResponseBody
            logger.debug("总数量：" + pageInfo.getTotal());
            logger.debug("总页数：" + pageInfo.getPages());
            logger.debug("每页显示条数：" + pageInfo.getPageSize());
            logger.debug("当前是第几页：" + pageInfo.getPageNum());
            logger.debug("当前页数据数量：" + pageInfo.getSize());
        }
        return result;
    }
}
