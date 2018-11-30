package com.winsun.item.modular.system.service.impl;

import com.winsun.item.modular.system.dao.VersionMapper;
import com.winsun.item.modular.system.model.Version;
import com.winsun.item.modular.system.service.IVersionService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/**
 * @author fulonghuang
 * @date 2018/11/26
 */
@Service
public class VersionServiceImpl implements IVersionService {

    @Autowired
    private VersionMapper versionMapper;

    @Override
    public Version updateVersion(String version, String softSystem) {

        String locationAppVersion;// 本机版本号
        String appVersion; // 网络返回版本号
        String versionType; // 版本类型  beta或release
        versionType = StringUtils.substringAfter(version, "_");
        Version ver = versionMapper.selectNewVersion(softSystem, versionType);
        locationAppVersion = StringUtils.substringBefore(version, "_");
//        System.out.println(version.substring(version.indexOf("_")));
        appVersion = StringUtils.substringBefore(ver.getVersion(), "_");
        boolean flag = false;// 默认不用更新
        if (locationAppVersion.equals(appVersion))
            flag = false;// 不用更新

        String[] arr1 = locationAppVersion.split("\\.");
        String[] arr2 = appVersion.split("\\.");
        //System.out.println(Arrays.asList(arr1));
        //System.out.println(Arrays.asList(arr2));

        int length = arr1.length < arr2.length ? arr1.length : arr2.length;
        for (int i = 0; i < length; i++) {
            int s1 = Integer.parseInt(arr1[i]);
            int s2 = Integer.parseInt(arr2[i]);
            flag = s1 < s2;
            if (flag) {
                flag = true;// 需要更新
                break;
            }
        }
        //System.out.println(flag ? "需要更新" : "不用更新");
        if (flag) {
            return ver;
        }
        return null;
    }
}