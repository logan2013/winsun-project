package com.winsun.item.modular.system.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.config.properties.GunsProperties;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.base.tips.Tip;
import com.winsun.item.core.common.annotion.BussinessLog;
import com.winsun.item.core.common.annotion.Permission;
import com.winsun.item.core.common.constant.Const;
import com.winsun.item.core.common.constant.dictmap.UserDict;
import com.winsun.item.core.common.constant.factory.ConstantFactory;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.common.constant.state.ManagerStatus;
import com.winsun.item.core.common.exception.BizExceptionEnum;
import com.winsun.item.core.datascope.DataScope;
import com.winsun.item.core.db.Db;
import com.winsun.item.core.exception.GunsException;
import com.winsun.item.core.log.LogObjectHolder;
import com.winsun.item.core.shiro.ShiroKit;
import com.winsun.item.core.shiro.ShiroUser;
import com.winsun.item.core.util.ToolUtil;
import com.winsun.item.modular.system.dao.UserMapper;
import com.winsun.item.modular.system.factory.UserFactory;
import com.winsun.item.modular.system.model.OperationLog;
import com.winsun.item.modular.system.model.User;
import com.winsun.item.modular.system.service.IUserService;
import com.winsun.item.modular.system.transfer.UserDto;
import com.winsun.item.modular.system.warpper.LogWarpper;
import com.winsun.item.modular.system.warpper.UserWarpper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.naming.NoPermissionException;
import javax.validation.Valid;
import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理控制器
 *
 * @author winsun
 * @Date 2018年11月1日
 */
@RestController
@RequestMapping("/mgr")
public class UserMgrController extends BaseController {

    @Autowired
    private GunsProperties gunsProperties;

    @Autowired
    private IUserService userService;

    /**
     * 查询管理员列表
     */
    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String name, @RequestParam(required = false) String beginTime, @RequestParam(required = false) String endTime, @RequestParam(required = false) Integer deptid) {
    	Page<User> page = new PageFactory<User>().defaultPage();
    	List<Map<String, Object>> users = null;
    	if (ShiroKit.isAdmin()) {
            users = userService.selectUsers(page, null, name, beginTime, endTime, deptid);
        } else {
            DataScope dataScope = new DataScope(ShiroKit.getDeptDataScope());
            users = userService.selectUsers(page, dataScope, name, beginTime, endTime, deptid);
        }
    	
    	page.setRecords((List<User>) new UserWarpper(users).warp());
        return super.packForBT(page);
    }

    /**
     * 用户详情信息接口
     */
    @GetMapping("/user_info/{userId}")
    public Object userEdit(@PathVariable Integer userId, Model model) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //assertAuth(userId);判断修改权限
        User user = this.userService.selectById(userId);
        resultMap.put("user", user);
        resultMap.put("roleName", ConstantFactory.me().getRoleName(user.getRoleid()));
        resultMap.put("deptName", ConstantFactory.me().getDeptName(user.getDeptid()));
        LogObjectHolder.me().set(user);
        
        return resultMap;
    }
    
    /**
     * 添加用户
     */
    @PostMapping("/add")
    @BussinessLog(value = "添加用户", key = "account", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip add(@Valid UserDto user, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        // 判断账号是否重复
        User theUser = userService.getByAccount(user.getAccount());
        if (theUser != null) {
            throw new GunsException(BizExceptionEnum.USER_ALREADY_REG);
        }

        // 完善账号信息
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5("111111", user.getSalt()));
        user.setStatus(ManagerStatus.OK.getCode());
        user.setCreatetime(new Date());

        this.userService.insert(UserFactory.createUser(user));
        return SUCCESS_TIP;
    }

    /**
     * 修改用户
     *
     * @throws
     */
    @PostMapping("/edit")
    @BussinessLog(value = "修改用户", key = "account", dict = UserDict.class)
    public Tip edit(@Valid UserDto user, BindingResult result){
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        User oldUser = userService.selectById(user.getId());

        if (ShiroKit.hasRole(Const.ADMIN_NAME)) {
            this.userService.updateById(UserFactory.editUser(user, oldUser));
            return SUCCESS_TIP;
        } else {
            assertAuth(user.getId());
            ShiroUser shiroUser = ShiroKit.getUser();
            if (shiroUser.getId().equals(user.getId())) {
                this.userService.updateById(UserFactory.editUser(user, oldUser));
                return SUCCESS_TIP;
            } else {
                throw new GunsException(BizExceptionEnum.NO_PERMITION);
            }
        }
    }

    /**
     * 冻结用户
     */
    @PostMapping("/freeze")
    @BussinessLog(value = "冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip freeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能冻结超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_FREEZE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.FREEZED.getCode());
        return SUCCESS_TIP;
    }
    
    /**
     * 解除冻结用户
     */
    @PostMapping("/unfreeze")
    @BussinessLog(value = "解除冻结用户", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip unfreeze(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }
    
    /**
     * 删除用户（逻辑删除）
     */
    @PostMapping("/delete")
    @BussinessLog(value = "删除用户", key = "userId", dict = UserDict.class)
    public Tip delete(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能删除超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_DELETE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setStatus(userId, ManagerStatus.DELETED.getCode());
        return SUCCESS_TIP;
    }

    /**
     * 重置管理员的密码
     */
    @RequestMapping("/reset")
    @BussinessLog(value = "重置管理员密码", key = "userId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip reset(@RequestParam Integer userId) {
        if (ToolUtil.isEmpty(userId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        assertAuth(userId);
        User user = this.userService.selectById(userId);
        user.setSalt(ShiroKit.getRandomSalt(5));
        user.setPassword(ShiroKit.md5(Const.DEFAULT_PWD, user.getSalt()));
        this.userService.updateById(user);
        return SUCCESS_TIP;
    }

    /**
     * 修改当前用户的密码
     */
    @RequestMapping("/changePwd")
    public Object changePwd(@RequestParam String oldPwd, @RequestParam String newPwd, @RequestParam String rePwd) {
        if (!newPwd.equals(rePwd)) {
            throw new GunsException(BizExceptionEnum.TWO_PWD_NOT_MATCH);
        }
        Integer userId = ShiroKit.getUser().getId();
        User user = userService.selectById(userId);
        String oldMd5 = ShiroKit.md5(oldPwd, user.getSalt());
        if (user.getPassword().equals(oldMd5)) {
            String newMd5 = ShiroKit.md5(newPwd, user.getSalt());
            user.setPassword(newMd5);
            user.updateById();
            return SUCCESS_TIP;
        } else {
            throw new GunsException(BizExceptionEnum.OLD_PWD_NOT_RIGHT);
        }
    }

    /**
     * 分配角色
     */
    @PostMapping("/setRole")
    @BussinessLog(value = "分配角色", key = "userId,roleIds", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip setRole(@RequestParam("userId") Integer userId, @RequestParam("roleIds") String roleIds) {
        if (ToolUtil.isOneEmpty(userId, roleIds)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //不能修改超级管理员
        if (userId.equals(Const.ADMIN_ID)) {
            throw new GunsException(BizExceptionEnum.CANT_CHANGE_ADMIN);
        }
        assertAuth(userId);
        this.userService.setRoles(userId, roleIds);
        return SUCCESS_TIP;
    }

    /**
     * 上传图片
     */
    @RequestMapping(method = RequestMethod.POST, path = "/upload")
    public String upload(@RequestPart("file") MultipartFile picture) {

        String pictureName = UUID.randomUUID().toString() + "." + ToolUtil.getFileSuffix(picture.getOriginalFilename());
        try {
            String fileSavePath = gunsProperties.getFileUploadPath();
            picture.transferTo(new File(fileSavePath + pictureName));
        } catch (Exception e) {
            throw new GunsException(BizExceptionEnum.UPLOAD_ERROR);
        }
        return pictureName;
    }

    /**
     * 判断当前登录的用户是否有操作这个用户的权限
     */
    private void assertAuth(Integer userId) {
        if (ShiroKit.isAdmin()) {
            return;
        }
        List<Integer> deptDataScope = ShiroKit.getDeptDataScope();
        User user = this.userService.selectById(userId);
        Integer deptid = user.getDeptid();
        if (deptDataScope.contains(deptid)) {
            return;
        } else {
            throw new GunsException(BizExceptionEnum.NO_PERMITION);
        }

    }
}
