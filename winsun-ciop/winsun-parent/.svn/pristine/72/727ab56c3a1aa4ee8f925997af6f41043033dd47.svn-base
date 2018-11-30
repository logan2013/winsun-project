package com.winsun.item.modular.system.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.base.tips.Tip;
import com.winsun.item.core.common.annotion.BussinessLog;
import com.winsun.item.core.common.annotion.Permission;
import com.winsun.item.core.common.constant.Const;
import com.winsun.item.core.common.constant.dictmap.MenuDict;
import com.winsun.item.core.common.constant.dictmap.UserDict;
import com.winsun.item.core.common.constant.factory.ConstantFactory;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.common.constant.state.ManagerStatus;
import com.winsun.item.core.common.constant.state.MenuStatus;
import com.winsun.item.core.common.exception.BizExceptionEnum;
import com.winsun.item.core.exception.GunsException;
import com.winsun.item.core.log.LogObjectHolder;
import com.winsun.item.core.node.MenuNode;
import com.winsun.item.core.node.ZTreeNode;
import com.winsun.item.core.shiro.ShiroKit;
import com.winsun.item.core.support.BeanKit;
import com.winsun.item.core.util.ToolUtil;
import com.winsun.item.modular.system.model.Menu;
import com.winsun.item.modular.system.model.Role;
import com.winsun.item.modular.system.service.IMenuService;
import com.winsun.item.modular.system.warpper.MenuWarpper;
import com.winsun.item.modular.system.warpper.UserWarpper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 菜单权限控制器
 *
 * @author fengshuonan
 * @Date 2017年2月12日21:59:14
 */
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController {

    @Autowired
    private IMenuService menuService;

    /**
     * 获取菜单权限列表
     */
    @Permission(Const.ADMIN_NAME)
    @GetMapping("/list")
    public Object list(@RequestParam(required = false) String menuName, @RequestParam(required = false) String level) {
    	Page<Menu> page = new PageFactory<Menu>().defaultPage();
        List<Map<String, Object>> menus = this.menuService.selectMenus(menuName, level);
        page.setRecords((List<Menu>) new MenuWarpper(menus).warp());
        return super.packForBT(page);
    }

    /**
     * 获取菜单权限详情
     */
    @Permission(Const.ADMIN_NAME)
    @GetMapping("/menu_info/{menuId}")
    public Object menuInfo(@PathVariable Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        Menu menu = this.menuService.selectById(menuId);

        //获取父级菜单权限的id
        Menu temp = new Menu();
        temp.setCode(menu.getPcode());
        Menu pMenu = this.menuService.selectOne(new EntityWrapper<>(temp));

        //如果父级是顶级菜单权限
        if (pMenu == null) {
            menu.setPcode("0");
        } else {
            //设置父级菜单权限的code为父级菜单权限的id
            menu.setPcode(String.valueOf(pMenu.getId()));
        }

        Map<String, Object> menuMap = BeanKit.beanToMap(menu);
        menuMap.put("pcodeName", ConstantFactory.me().getMenuNameByCode(temp.getCode()));
        resultMap.put("menu", menuMap);
        LogObjectHolder.me().set(menu);
        
        return resultMap;
    }
    
    /**
     * 新增菜单权限
     */
    @Permission(Const.ADMIN_NAME)
    @PostMapping("/add")
    @BussinessLog(value = "菜单权限新增", key = "name", dict = MenuDict.class)
    public Tip add(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //判断是否存在该编号
        String existedMenuName = ConstantFactory.me().getMenuNameByCode(menu.getCode());
        if (ToolUtil.isNotEmpty(existedMenuName)) {
            throw new GunsException(BizExceptionEnum.EXISTED_THE_MENU);
        }

        //设置父级菜单权限编号
        menuSetPcode(menu);

        menu.setStatus(MenuStatus.ENABLE.getCode());
        this.menuService.insert(menu);
        return SUCCESS_TIP;
    }

    /**
     * 修改菜单权限
     */
    @Permission(Const.ADMIN_NAME)
    @PostMapping(value = "/edit")
    @BussinessLog(value = "修改菜单权限", key = "name", dict = MenuDict.class)
    public Tip edit(@Valid Menu menu, BindingResult result) {
        if (result.hasErrors()) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        //设置父级菜单权限编号
        menuSetPcode(menu);

        this.menuService.updateById(menu);
        return SUCCESS_TIP;
    }

    /**
     * 删除菜单权限
     */
    @Permission(Const.ADMIN_NAME)
    @PostMapping("/remove")
    @BussinessLog(value = "删除菜单权限", key = "menuId", dict = MenuDict.class)
    public Tip remove(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }

        //缓存菜单权限的名称
        LogObjectHolder.me().set(ConstantFactory.me().getMenuName(menuId));

        this.menuService.delMenuContainSubMenus(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 查看菜单权限
     */
    @GetMapping("/view/{menuId}")
    public Tip view(@PathVariable Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.menuService.selectById(menuId);
        return SUCCESS_TIP;
    }

    /**
     * 获取菜单权限列表(首页用)
     */
    @GetMapping("/menuTreeList")
    public List<ZTreeNode> menuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        return roleTreeList;
    }

    /**
     * 获取菜单权限列表(选择父级菜单权限用)
     */
    @GetMapping("/selectMenuTreeList")
    public List<ZTreeNode> selectMenuTreeList() {
        List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
        return roleTreeList;
    }

    /**
     * 获取角色列表
     */
    @GetMapping("/menuTreeListByRoleId/{roleId}")
    public List<ZTreeNode> menuTreeListByRoleId(@PathVariable Integer roleId) {
        List<Long> menuIds = this.menuService.getMenuIdsByRoleId(roleId);
        if (ToolUtil.isEmpty(menuIds)) {
            List<ZTreeNode> roleTreeList = this.menuService.menuTreeList();
            return roleTreeList;
        } else {
            List<ZTreeNode> roleTreeListByUserId = this.menuService.menuTreeListByMenuIds(menuIds);
            return roleTreeListByUserId;
        }
    }
    
    /**
     * 获取菜单列表
     */
    @GetMapping("/getMenus")
    public List<MenuNode> getMenus() {
        List<MenuNode> menu = this.menuService.getMenusByRoleIds(ShiroKit.getUser().getRoleList(), "0");
        return menu;
    }
    
    /**
     * 关闭菜单权限
     */
    @PostMapping("/freeze")
    @BussinessLog(value = "关闭菜单权限", key = "menuId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip freeze(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
       
        this.menuService.setStatus(menuId, ManagerStatus.CLOSE.getCode());
        return SUCCESS_TIP;
    }
    
    /**
     * 启动菜单权限
     */
    @PostMapping("/unfreeze")
    @BussinessLog(value = "启动菜单权限", key = "menuId", dict = UserDict.class)
    @Permission(Const.ADMIN_NAME)
    public Tip unfreeze(@RequestParam Long menuId) {
        if (ToolUtil.isEmpty(menuId)) {
            throw new GunsException(BizExceptionEnum.REQUEST_NULL);
        }
        this.menuService.setStatus(menuId, ManagerStatus.OK.getCode());
        return SUCCESS_TIP;
    }
    
    

    /**
     * 根据请求的父级菜单权限编号设置pcode和层级
     */
    private void menuSetPcode(@Valid Menu menu) {
        if (ToolUtil.isEmpty(menu.getPcode()) || menu.getPcode().equals("0")) {
            menu.setPcode("0");
            menu.setPcodes("[0],");
            menu.setLevels(1);
        } else {
            long code = Long.parseLong(menu.getPcode());
            Menu pMenu = menuService.selectById(code);
            Integer pLevels = pMenu.getLevels();
            menu.setPcode(pMenu.getCode());

            //如果编号和父编号一致会导致无限递归
            if (menu.getCode().equals(menu.getPcode())) {
                throw new GunsException(BizExceptionEnum.MENU_PCODE_COINCIDENCE);
            }

            menu.setLevels(pLevels + 1);
            menu.setPcodes(pMenu.getPcodes() + "[" + pMenu.getCode() + "],");
        }
    }

}
