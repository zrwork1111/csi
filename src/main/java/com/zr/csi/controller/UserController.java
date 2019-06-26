package com.zr.csi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zr.csi.model.User;
import com.zr.csi.util.Util;
import com.zr.dubbo.UserDubboService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/user")
public class UserController {
	@Reference(version ="1_1", check=false)
	UserDubboService userDubboService;
	
	Map<String, Object> map;
	
	@RequestMapping(value="/queryUser",method=RequestMethod.POST)
	@ApiOperation(value = "查询用户list" ,  notes="通过用户名和status查询")
	public Map<String, Object> queryUser(
		@RequestParam(value="userName", required=false) 
		String userName, 
		@RequestParam(value="status",required=false) 
		Integer status) {
		
		List<User> list = userDubboService.queryUser(userName, status);
		return Util.setMapQuery(list, map);
	}
	
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ApiOperation(value = "添加用户" ,  notes="添加用户")
	public Map<String, Object> addUser(
		@RequestBody User user) {
		
		int result = userDubboService.addUser(user);
		return Util.setMapInsert(result, map);
	}
	
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ApiOperation(value = "更新用户" ,  notes="更新用户")
	public Map<String, Object> updateUser(
			@RequestBody User user) {
		
		int result = userDubboService.updateUser(user);
		return Util.setMapUpdate(result, map);
	}
	
	@RequestMapping(value="/deleteUsers",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个用户" ,  notes="删除多个用户")
	public Map<String, Object> deleteUsers(
			@RequestParam String id) {
		String[] ids = id.split(",");
		int count = 0, length = ids.length;
		for (String val: ids) {
			int result = userDubboService.deleteUser(Integer.valueOf(val));
			if (result > 0) {
				count++;
			}
		}
		
		return Util.setMapDeletes(count, length, map);
	}
	
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ApiOperation(value = "删除一个用户" ,  notes="删除一个用户")
	public Map<String, Object> deleteUser(
			@RequestParam Integer id) {
		int result = userDubboService.deleteUser(id);
		return Util.setMapDelete(result,map);
	}

	 @GetMapping(value = "/index")
	public ModelAndView  hello() {
	    return new ModelAndView("views/index");
	}

}
