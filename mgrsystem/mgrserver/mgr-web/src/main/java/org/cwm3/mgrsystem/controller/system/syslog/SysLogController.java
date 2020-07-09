package org.cwm3.mgrsystem.controller.system.syslog;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.cwm3.mgrsystem.common.entity.AjaxResult;
import org.cwm3.mgrsystem.model.Department;
import org.cwm3.mgrsystem.common.entily.RespBean;
import org.cwm3.mgrsystem.model.SysLog;
import org.cwm3.mgrsystem.service.ISysLogService;
import org.cwm3.mgrsystem.utils.ExcelUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 操作日志表(TLog)表控制层
 *
 * @author makejava
 * @since 2020-07-08 10:13:14
 */
@RestController
@RequestMapping("/mgrsystem/syslog")
public class SysLogController {
    /**
     * 服务对象
     */
    @Resource
    private ISysLogService sysLogService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public SysLog selectOne(Long id) {
        return this.sysLogService.queryById(id);
    }




    /**
     * 通过主键查询单条数据
     *
     * @param
     * @return 所有数据
     */
    @GetMapping("/")
    public AjaxResult getAllSysLog(@RequestParam(defaultValue = "1") Integer pageNum, @RequestParam(defaultValue = "10") Integer pageSize ,String name){
        Department department = new Department();
        AjaxResult ajaxResult = new AjaxResult();
        try {
            Page<SysLog> iPage = sysLogService.selectPageExt(pageNum,pageSize,department ,name);
            ajaxResult.setSuccess(true);
            ajaxResult.setData(iPage);
        }catch (Exception e){
            ajaxResult.setSuccess(false);
            ajaxResult.setMessage(e.getMessage());
        }
        return  ajaxResult;
    }

    @GetMapping("/export")
    public RespBean export(HttpServletResponse response,String[] headers ,String fileName){
        List<SysLog> list = this.sysLogService.list();
        Map<String, Object> studentMap = new HashMap();
        studentMap.put("headers", headers);
        studentMap.put("dataList", list);
        studentMap.put("fileName", fileName);
        List<Map> mapList = new ArrayList();
        mapList.add(studentMap);
        try {
            ExcelUtil.exportMultisheetExcel(fileName, mapList, response);
            return RespBean.ok("导出成功");
        } catch (Exception e) {
           return RespBean.error(e.getMessage());
        }

    }


}