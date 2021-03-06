package com.winsun.item.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.common.constant.state.AppType;
import com.winsun.item.core.util.ResponseEntity;
import com.winsun.item.modular.system.model.Version;
import com.winsun.item.modular.system.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
@RestController
@RequestMapping("/version")
public class VersionController extends BaseController{

    @Autowired
    private IVersionService versionService;


    /**
     * 版本更新接口
     * @param version 版本号
     * @param softSystem 系统类型
     * @return
     */
    @RequestMapping({"/update","/getVersion"})
    @ResponseBody
    public Object getVersion(String version, String softSystem) {
        Version v = null;
        try {
            v = versionService.updateVersion(version, AppType.valOf(softSystem));
            if (v == null) {
                return ResponseEntity.newJSON("code", 200, "isUpdate", false);
            } else {
                return ResponseEntity.newJSON("code", 200, "isUpdate", true, "updateData", v);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.newJSON("code", 200, "isUpdate", false);
        }
    }

    @RequestMapping(value = "/list")
    public Object list(@RequestParam Map<String, Object> map) {
        Page<Map<String, Object>> page = new PageFactory<Map<String, Object>>().defaultPage();
        System.out.println(page);
        List<Map<String, Object>> list = versionService.selectList(page, map);
        page.setRecords(list);
        return super.packForBT(page);
    }

    @RequestMapping(value = "/add")
    public Object add(Version version) {
        version.setUpdateTime(new Date());
        //version.setSoftSystem(AppType.valOf(version.getSoftSystem()));
        boolean flag = versionService.insert(version);
        if (flag) {
            return ResponseEntity
                    .newJSON("code", 200);
        } else {
            return ResponseEntity
                    .newJSON("code", 400, "message", "新增失败");
        }
    }

    @RequestMapping(value = "/detail/{id}")
    public Object detail(@PathVariable Integer id) {
        Version version = versionService.selectById(id);
        //System.out.println(version);
        return ResponseEntity
                .newJSON("code", 200, "data", version);
    }

    @RequestMapping(value = "/edit")
    public Object update(Version version) {
        version.setUpdateTime(new Date());
        //version.setSoftSystem(AppType.valOf(version.getSoftSystem()));
        boolean flag = versionService.updateById(version);
        if (flag) {
            return ResponseEntity
                    .newJSON("code", 200);
        } else {
            return ResponseEntity
                    .newJSON("code", 400, "message", "修改失败");
        }
    }

    @RequestMapping(value = "/delete")
    public Object delete(@RequestParam String id) {
        versionService.deleteById(id);
        return ResponseEntity
                .newJSON("code", 200);
    }

}
