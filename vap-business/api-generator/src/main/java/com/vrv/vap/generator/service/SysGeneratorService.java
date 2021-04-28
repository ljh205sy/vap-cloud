package com.vrv.vap.generator.service;

import com.vrv.vap.common.page.PageResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wh1107066
 */
@Service
public interface SysGeneratorService  {
     PageResult queryList(Map<String, Object> map);

     Map<String, String> queryTable(String tableName);

     List<Map<String, String>> queryColumns(String tableName);

     byte[] generatorCode(String[] tableNames);
}
