package com.winsun.item.modular.compare.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.winsun.item.core.base.controller.BaseController;
import com.winsun.item.core.common.constant.factory.PageFactory;
import com.winsun.item.core.feign.AddressFeignClient;
import com.winsun.item.core.support.HttpKit;
import com.winsun.item.core.util.ResponseEntity;
import com.winsun.item.core.util.ToolUtil;
import com.winsun.item.modular.compare.service.IAddressService;

/**
 * 地址模糊查询接口
 *
 * @author zero
 * @Date 2018年11月9日
 */
@RestController
@RequestMapping("/address")
public class AddressController extends BaseController {

	@Autowired
	private IAddressService addressService;
	
	@Autowired
	private AddressFeignClient addressFeignClient;
	
	@RequestMapping("/search")
	public Object searchAddress(String substName, String address){
		Page<Map<String,Object>> page = new PageFactory<Map<String,Object>>().defaultPage();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("substName", StringUtils.isBlank(substName)?"":substName);
		map.put("address", StringUtils.isBlank(address)?"":address);
		System.out.println(1);
		List<Map<String, Object>> list = addressService.searchAddress(page, map);
		return ResponseEntity
				.newJSON("code", 200, "data", list);	
	}

	
	
	
	@RequestMapping("/essearch")
	public Object esSearchAddress(@RequestParam("address")String address) {
		HttpServletRequest request = HttpKit.getRequest();
        String limitNum = request.getParameter("limit");
        String pageNum = request.getParameter("page");
        int limit = 10;     //每页多少条数据
        int pageN = 1;   //第几页
        
        if (ToolUtil.isNotEmpty(limitNum)) {
        	limit = Integer.valueOf(limitNum); 
        }
        if (ToolUtil.isNotEmpty(pageNum)) {
        	pageN = Integer.valueOf(pageNum); 
        }
		Object match = addressFeignClient.match(address, pageN, limit);
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(match));
		Integer code = json.getInteger("resultcode");
		if(code != 0) {
			return ResponseEntity
					.newJSON("code", 500, "data", json.get("results"));
		}else {
			return ResponseEntity
					.newJSON("code", 200, "data", json.get("results"));
		}
		
	}
	
	@RequestMapping("/essearchPart")
	public Object esSearchPartAddress(@RequestParam("address")String address) {
		HttpServletRequest request = HttpKit.getRequest();
        String limitNum = request.getParameter("limit");
        String pageNum = request.getParameter("page");
        int limit = 10;     //每页多少条数据
        int pageN = 1;   //第几页
        
        if (ToolUtil.isNotEmpty(limitNum)) {
        	limit = Integer.valueOf(limitNum); 
        }
        if (ToolUtil.isNotEmpty(pageNum)) {
        	pageN = Integer.valueOf(pageNum); 
        }
		Object match = addressFeignClient.matchPart(address, pageN, limit);
		JSONObject json = JSONObject.parseObject(JSON.toJSONString(match));
		Integer code = json.getInteger("resultcode");
		if(code != 0) {
			return ResponseEntity
					.newJSON("code", 500, "data", json.get("results"));
		}else {
			return ResponseEntity
					.newJSON("code", 200, "data", json.get("results"));
		}
		
	}
	
	
}
