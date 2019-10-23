package com.wwmxd.generator.service;


import com.wwmxd.generator.entity.TableEntity;
import com.wwmxd.generator.entity.TemplateEntity;
import com.wwmxd.generator.mapper.GeneratorMapper;
import com.wwmxd.generator.utils.GeneratorUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

/**
 * 代码生成器
 *
 * @author Mr.AG
 * @email 463540703@qq.com
 * @date 2017年08月25日
 */
@Service
public class GeneratorService {
	@Autowired
	private GeneratorMapper generatorMapper;

	public List<Map<String, Object>> queryList(Map<String, Object> map) {
		int offset = Integer.parseInt(map.get("page").toString())-1;
		int limit = Integer.parseInt(map.get("limit").toString());
		if(map.containsKey("tableName")){
			String name=map.get("tableName").toString();
			map.put("tableName",name);
		}
		map.put("offset", offset);
		map.put("limit", limit);

		return generatorMapper.queryList(map);
	}

	public int queryTotal(Map<String, Object> map) {
		return generatorMapper.queryTotal(map);
	}

	public Map<String, Object> queryTable(String tableName) {
		return generatorMapper.queryTable(tableName);
	}

	public List<Map<String, String>> queryColumns(String tableName) {
		return generatorMapper.queryColumns(tableName);
	}

	public byte[] generatorCode(TemplateEntity entity) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipOutputStream zip = new ZipOutputStream(outputStream);
		List<TableEntity> tableNames=entity.getTableData();
		for(TableEntity tableName:tableNames){
			//查询表信息
			Map<String, Object> table = queryTable(tableName.getTableName());
			//查询列信息
			List<Map<String, String>> columns = queryColumns(tableName.getTableName());
			//生成代码
			GeneratorUtils.generatorCode(table, columns, zip,entity);
		}


		IOUtils.closeQuietly(zip);
		return outputStream.toByteArray();
	}
}
