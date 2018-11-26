package com.winsun.item.core.common.constant.factory;

import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.common.constant.state.Order;
import com.winsun.item.core.support.HttpKit;
import com.winsun.item.core.util.ToolUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * BootStrap Table默认的分页参数创建
 *
 * @author fengshuonan
 * @date 2017-04-05 22:25
 */
public class PageFactory<T> {

    public Page<T> defaultPage() {
        HttpServletRequest request = HttpKit.getRequest();
        String limitNum = request.getParameter("limit");
        String pageNum = request.getParameter("page");
        int limit = 10;     //每页多少条数据
        int pageN = 0;   //第几页
        
        if (ToolUtil.isNotEmpty(limitNum)) {
        	limit = Integer.valueOf(limitNum); 
        }
        if (ToolUtil.isNotEmpty(pageNum)) {
        	pageN = Integer.valueOf(pageNum); 
        }
        
        String sort = request.getParameter("sort");         //排序字段名称
        String order = request.getParameter("order");       //asc或desc(升序或降序)
        if (ToolUtil.isEmpty(sort)) {
            Page<T> page = new Page<>(pageN, limit);
            page.setOpenSort(false);
            return page;
        } else {
            Page<T> page = new Page<>(pageN, limit, sort);
            if (Order.ASC.getDes().equals(order)) {
                page.setAsc(true);
            } else {
                page.setAsc(false);
            }
            return page;
        }
    }
}
