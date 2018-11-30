package com.winsun.item.modular.system.controller;

import com.winsun.item.core.util.ResponseEntity;
import com.winsun.item.modular.system.model.Version;
import com.winsun.item.modular.system.service.IVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
@RestController
@RequestMapping("/version")
public class VersionController {

    @Autowired
    private IVersionService versionService;

    @RequestMapping("/update")
    @ResponseBody
    public Object updateVersion(String version, String softSystem) {
        Version v = null;
        try {
            v = versionService.updateVersion(version, softSystem);
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
}
