package com.vrv.vap.generator.service.impl;

import com.vrv.vap.generator.mapper.SysGeneratorMapper;
import com.vrv.vap.generator.service.SysGeneratorService;
import com.vrv.vap.generator.utils.GenUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.vrv.vap.common.page.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * @author wh1107066
 */
@Slf4j
@Service
public class SysGeneratorServiceImpl implements SysGeneratorService {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SysGeneratorMapper sysGeneratorMapper;

    @Override
    public PageResult<Map<String, Object>> queryList(Map<String, Object> map) {
        // map 中存有分页对象，还存在查询条件,  // 设置分页查询参数, pageSize 单页记录数量.pageNum 记录页数
        Integer pageNum = MapUtils.getInteger(map, "pageNum");
        Integer pageSize = MapUtils.getInteger(map, "pageSize");
        PageHelper.startPage(pageNum, pageSize);
        List<Map<String, Object>> list = sysGeneratorMapper.queryList(map);
        // 封装分页查询结果到 PageInfo 对象中以获取相关分页信息
        PageInfo pageInfo = new PageInfo(list);
        logger.info("总页数: " + pageInfo.getPages());
        logger.info("总记录数: " + pageInfo.getTotal());
        logger.info("当前页数: " + pageInfo.getPageNum());
        logger.info("当前页面记录数量: " + pageInfo.getSize());

        return PageResult.<Map<String, Object>>builder().data(list).code(0).count(pageInfo.getTotal()).build();
    }

    @Override
    public Map<String, String> queryTable(String tableName) {
        return sysGeneratorMapper.queryTable(tableName);
    }

    @Override
    public List<Map<String, String>> queryColumns(String tableName) {
        return sysGeneratorMapper.queryColumns(tableName);
    }

    @Override
    public byte[] generatorCode(String[] tableNames) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (
                ZipOutputStream zip = new ZipOutputStream(outputStream)
        ) {
            for (String tableName : tableNames) {
                //查询表信息
                Map<String, String> table = queryTable(tableName);
                //查询列信息
                List<Map<String, String>> columns = queryColumns(tableName);
                //生成代码
                GenUtils.generatorCode(table, columns, zip);
            }
        } catch (IOException e) {
            log.error("generatorCode-error: ", e);
        }
        return outputStream.toByteArray();
    }
}
