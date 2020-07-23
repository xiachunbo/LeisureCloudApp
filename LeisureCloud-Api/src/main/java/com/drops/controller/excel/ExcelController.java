package com.drops.controller.excel;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.drops.controller.excel.ExcelController;
import com.drops.service.GenericService;
import com.drops.service.TableService;
import com.drops.tools.ExportExcelKit;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExcelController {
    @Autowired
    private GenericService genericService;

    @GetMapping({"/export_users"})
    public void exportWorkSchedules(HttpServletResponse response) throws ParseException, IOException {
        Map<String, String> title = new HashMap<>();
        List<LinkedHashMap<String, Object>> data = new ArrayList<>();
        Map<String, Integer> position = new HashMap<>();
        Map<String, Object> parems = new HashMap<>();
        String columnfields = "filed,remark";
        parems.put("tablename", "crm_sys_role");
        String fieldStr = "";

        List<LinkedHashMap<String, Object>> columnDatas = this.genericService.selectByParem("sys_table_mapper", columnfields, parems);
        if (columnDatas.size() > 0) {
            LinkedHashMap<String, Object> column = columnDatas.get(0);
            String fields = column.get("filed").toString();
            String remarks = column.get("remark").toString();
            fieldStr = column.get("filed").toString();
            String[] field = fields.split(",");
            String[] remark = remarks.split(",");
            for (int i = 0; i < field.length; i++) {
                position.put(field[i], Integer.valueOf(i));
                title.put(field[i], remark[i]);
            }
        } else {
            PageHelper.startPage(0, 2147483647);
            List<Map<String, Object>> list1 = this.tableService.listTableColumn("crm_sys_role");
            PageInfo<Map<String, Object>> page1 = new PageInfo(list1);
            String _columns = JSON.toJSONString(page1.getList(), new SerializerFeature[]{SerializerFeature.WriteNullStringAsEmpty});
            JSONArray columnsArr = JSONArray.parseArray(_columns);
            for (Object aColumnsArr : columnsArr) {
                JSONObject job = (JSONObject) aColumnsArr;
                fieldStr = fieldStr + job.get("COLUMN_NAME").toString() + ",";
            }
            fieldStr = fieldStr.substring(0, fieldStr.length() - 1);
        }
        Map<String, Object> paremdata = new HashMap<>();
        PageHelper.startPage(0, 2147483647);
        data = this.genericService.selectByParem("crm_sys_role", fieldStr, paremdata);
        DateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String date = df.format(new Date());
        String excelName = date + ".xlsx";
        String sheetName = "用户列表数据";
        excelName = URLEncoder.encode(excelName, "UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + excelName);
        response.setContentType("application/x-download");

        ExportExcelKit.exportDataToExcel(title, position, data, sheetName, (OutputStream) response.getOutputStream());
    }

    @Autowired
    private TableService tableService;
}