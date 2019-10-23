package com.wwmxd.generator.rest;

import com.wwmxd.generator.entity.TemplateEntity;
import com.wwmxd.generator.service.GeneratorService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/base/generator")
public class GeneratorRest {

    @Autowired
    private GeneratorService generatorService;

    /**
     * 列表
     */
    @ResponseBody
    @RequestMapping("/page")
    public TableResultResponse<Map<String, Object>> list(@RequestParam Map<String, Object> params) {
        List<Map<String, Object>> result = generatorService.queryList(params);
        int total = generatorService.queryTotal(params);
        return new TableResultResponse<Map<String, Object>>(total, result);
    }

    @ResponseBody
    @RequestMapping("/getTable")
    public List<Map<String, String>> getTable(@RequestParam Map<String, Object> params) {
        String tableName = params.get("tableName").toString();
        return generatorService.queryColumns(tableName);

    }

    /**
     * 生成代码
     */

    @RequestMapping(value = "/create",method = RequestMethod.POST)
    public void createTemplate(@RequestBody TemplateEntity entity, HttpServletResponse response) throws IOException {
        byte[] data = generatorService.generatorCode(entity);
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");
        response.setHeader("Access-control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "DNT,X-CustomHeader,Keep-Alive,User-Agent,X-Requested-With,If-Modified-Since,Cache-Control,Content-Type");

        IOUtils.write(data, response.getOutputStream());
    }
}
