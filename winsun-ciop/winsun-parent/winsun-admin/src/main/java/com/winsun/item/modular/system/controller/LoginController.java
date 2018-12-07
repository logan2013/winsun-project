package com.winsun.item.modular.system.controller;

import com.google.code.kaptcha.Constants;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.base.tips.ErrorTip;
import com.winsun.item.core.common.constant.factory.ConstantFactory;
import com.winsun.item.core.common.exception.InvalidKaptchaException;
import com.winsun.item.core.log.LogManager;
import com.winsun.item.core.log.factory.LogTaskFactory;
import com.winsun.item.core.node.MenuNode;
import com.winsun.item.core.shiro.CustomToken;
import com.winsun.item.core.shiro.ShiroKit;
import com.winsun.item.core.shiro.ShiroUser;
import com.winsun.item.core.support.HttpKit;
import com.winsun.item.core.util.AccLoginUtil;
import com.winsun.item.core.util.ApiMenuFilter;
import com.winsun.item.core.util.Convert;
import com.winsun.item.core.util.JwtTokenUtil;
import com.winsun.item.core.util.KaptchaUtil;
import com.winsun.item.core.util.ToolUtil;
import com.winsun.item.modular.system.model.User;
import com.winsun.item.modular.system.service.IMenuService;
import com.winsun.item.modular.system.service.IUserService;
import com.winsun.item.modular.system.service.impl.AccServiceImpl;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import static com.winsun.item.core.support.HttpKit.getIp;

/**
 * 登录控制器
 *
 * @author fengshuonan
 * @Date 2017年1月10日 下午8:25:24
 */
@RestController
public class LoginController extends BaseController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
    @Autowired
    private IMenuService menuService;

    @Autowired
    private IUserService userService;
    
    @RequestMapping(value = "/acclogin", method = RequestMethod.POST)
    public Object accloginVali(@RequestParam("staffId")String subStaffId,@RequestParam(name = "staffPwd",required = false)String staffPwd) throws UnknownHostException, UnsupportedEncodingException {       
    	
    	//获取数据库中的账号密码，准备比对
        User user = userService.getByAccount(subStaffId);
        if (null == user) {
        	logger.info("统一账号或子统一账号在系统中找不到或被冻结！");
        	return new ErrorTip(500, "统一账号或子统一账号在系统中找不到或被冻结！");
        }
        
        logger.info("user :{}",user);
        Boolean flag = AccLoginUtil.whetherAccLogin(user);
        
        logger.info("flag :{}",flag);
        
        if(flag) {
        	if(StringUtils.isBlank(staffPwd)) {
        		return new ErrorTip(500, "非短信验证时密码不能为空！");
        	}
        	return AccLoginUtil.normalLogin(subStaffId, staffPwd, user);
        }else {
        	
        	HttpServletRequest request = HttpKit.getRequest();
        	String smsCode = request.getParameter("smsCode");
        	
        	if(StringUtils.isBlank(smsCode)) {
        		if(StringUtils.isBlank(staffPwd)) {
            		return new ErrorTip(500, "非短信验证时密码不能为空！");
            	}
        		return AccLoginUtil.accLogin(subStaffId, staffPwd);
        	}else {
        		String uuid = request.getParameter("theUuid");
        		return AccLoginUtil.accSmsValidate(uuid,subStaffId,smsCode);
        	}
        	
        	
        }
    	
    }
    
    @RequestMapping(value = "/accSendSms", method = RequestMethod.POST)
    public Object accSendSms(@RequestParam("staffId")String subStaffId) {
    	return AccLoginUtil.accSendSms(subStaffId);
    }

    
    
    /**
     * 点击登录执行的动作
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Object loginVali() {

        String username = super.getPara("username").trim();
        String password = super.getPara("password").trim();
        
        //获取数据库中的账号密码，准备比对
        User user = userService.getByAccount(username);
        if (null == user) {
        	return new ErrorTip(500, "账号密码错误！");
        }

        //校验用户账号密码
        boolean passwordTrueFlag = false;
        if (user.getPassword().equals(ShiroKit.md5(password, user.getSalt()))) {
        	passwordTrueFlag = true;
        }
        
        if (passwordTrueFlag) {
        	 //封装请求账号密码为shiro可验证的token
        	CustomToken customPasswordToken = new CustomToken(username, password);
        	Subject currentUser = ShiroKit.getSubject();
            currentUser.login(customPasswordToken);
            Serializable sessionId = ShiroKit.getSession().getId();

            ShiroUser shiroUser = ShiroKit.getUser();
            //super.getSession().setAttribute("shiroUser", shiroUser);
            //super.getSession().setAttribute("username", shiroUser.getAccount());

            LogManager.me().executeLog(LogTaskFactory.loginLog(shiroUser.getId(), getIp()));
            ShiroKit.getSession().setAttribute("sessionFlag", true);
            
            HashMap<String, Object> result = new HashMap<>();
            result.put("sessionId", sessionId.toString());
            result.put("currentUser", shiroUser.getName());
            result.put("code", 200);
            result.put("message", "登陆成功！");
            return result; 
            
        } else {
            return new ErrorTip(500, "账号密码错误！");
        }
        
    }

    /**
     * 退出登录
     */
    @RequestMapping(value = "/logout")
    public String logout() {
    	System.err.println(ShiroKit.getUser().getId());
        LogManager.me().executeLog(LogTaskFactory.exitLog(ShiroKit.getUser().getId(), getIp()));
        ShiroKit.getSubject().logout();
        return "{\"code\":200}";
    }
}
