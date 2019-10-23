package com.wwmxd.generator.mapper;

import java.util.List;
import java.util.Map;


public interface GeneratorMapper {

	List<Map<String, Object>> queryList(Map<String, Object> map);

	int queryTotal(Map<String, Object> map);

	Map<String, Object> queryTable(String tableName);

	List<Map<String, String>> queryColumns(String tableName);
}
