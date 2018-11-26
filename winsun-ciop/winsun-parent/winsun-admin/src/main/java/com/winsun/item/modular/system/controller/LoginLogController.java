package com.winsun.item.modular.system.controller;

import com.baomidou.mybatisplus.mapper.SqlRunner;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.annotion.BussinessLog;
import com.winsun.item.core.common.annotion.Permission;
import com.winsun.item.core.common.constant.Const;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.modular.system.model.LoginLog;
import com.winsun.item.modular.system.service.ILoginLogService;
import com.winsun.item.modular.system.warpper.LogWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/loginLog")
public class LoginLogController extends BaseController {


    @Autowired
    private ILoginLogService loginLogService;

    /**
     * 查询登录日志列表
     */
    @GetMapping("/list")
    @Permission(Const.ADMIN_NAME)
    public Object list(@RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) String logName) {
        Page<LoginLog> page = new PageFactory<LoginLog>().defaultPage();
        List<Map<String, Object>> result = loginLogService.getLoginLogs(page, beginTime, endTime, logName, page.getOrderByField(), page.isAsc());
        page.setRecords((List<LoginLog>) new LogWarpper(result).warp());
        return super.packForBT(page);
    }

    /**
     * 清空日志
     */
    @BussinessLog("清空登录日志")
    @PostMapping("/delLoginLog")
    @Permission(Const.ADMIN_NAME)
    public Object delLog() {
        SqlRunner.db().delete("delete from sys_login_log");
        return SUCCESS_TIP;
    }
}
