package com.winsun.item.modular.system.controller;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.annotion.BussinessLog;
import com.winsun.item.core.common.annotion.Permission;
import com.winsun.item.core.common.constant.Const;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.common.constant.state.BizLogType;
import com.winsun.item.core.support.BeanKit;
import com.winsun.item.modular.system.model.OperationLog;
import com.winsun.item.modular.system.service.IOperationLogService;
import com.winsun.item.modular.system.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 日志管理的控制器
 *
 * @author fengshuonan
 * @Date 2017年4月5日 19:45:36
 */
@RestController
@RequestMapping("/log")
public class LogController extends BaseController {

    @Autowired
    private IOperationLogService operationLogService;

    /**
     * 查询操作日志列表
     */
    @GetMapping("/list")
    @Permission(Const.ADMIN_NAME)
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName, @RequestParam(required = false) Integer logType) {
        Page<OperationLog> page = new PageFactory<OperationLog>().defaultPage();
        List<Map<String, Object>> result = operationLogService.getOperationLogs(page, beginTime, endTime, logName, BizLogType.valueOf(logType), page.getOrderByField(), page.isAsc());
        page.setRecords((List<OperationLog>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 查询操作日志详情
     */
    @GetMapping("/detail/{id}")
    @Permission(Const.ADMIN_NAME)
    public Object detail(@PathVariable Integer id) {
        OperationLog operationLog = operationLogService.selectById(id);
        Map<String, Object> stringObjectMap = BeanKit.beanToMap(operationLog);
        return super.warpObject(new LogWarpper(stringObjectMap));
    }

    /**
     * 清空日志
     */
    @BussinessLog(value = "清空业务日志")
    @PostMapping("/delLog")
    @Permission(Const.ADMIN_NAME)
    public Object delLog() {
        SqlRunner.db().delete("delete from sys_operation_log");
        return SUCCESS_TIP;
    }
}
