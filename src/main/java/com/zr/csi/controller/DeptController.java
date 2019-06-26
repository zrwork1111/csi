package com.zr.csi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zr.csi.model.Dept;
import com.zr.csi.util.Util;
import com.zr.dubbo.DeptDubboService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dept")
public class DeptController {
	@Reference(version="1_2", check=false)
	DeptDubboService deptDubboService;
	Map<String, Object> map;
	
	@RequestMapping(value="/queryDept",method=RequestMethod.POST)
	@ApiOperation(value = "部门查询" ,  notes="部门查询")
	public Map<String, Object> queryDept(
		@RequestParam(value="name", required=false) 
		String name 
		) {
		List<Dept> list = deptDubboService.queryDept(name);
		return Util.setMapQuery(list, map);
	}
	
	@RequestMapping(value="/addDept",method=RequestMethod.POST)
	@ApiOperation(value = "部门添加" ,  notes="部门添加")
	public Map<String, Object> addDept(
		@RequestBody Dept dept
		) {
		int result = deptDubboService.addDept(dept);
		return Util.setMapInsert(result, map);
	}
	
	@RequestMapping(value="/updateDept",method=RequestMethod.POST)
	@ApiOperation(value = "更新部门" ,  notes="更新部门")
	public Map<String, Object> updateDept(
			@RequestBody Dept dept) {
		int result = deptDubboService.updateDept(dept);
		return Util.setMapUpdate(result, map);
	}
	
	@RequestMapping(value="/deleteDepts",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个部门" ,  notes="删除多个部门")
	public Map<String, Object> deleteDepts(
			@RequestParam String id) {
		String[] ids = id.split(",");
		int count = 0, length = ids.length;
		for (String str: ids) {
			int result = deptDubboService.deleteDept(Integer.valueOf(str));
			if (result > 0) {
				count++;
			}
		}
		
		return Util.setMapDeletes(count, length, map);
	}
	
	@RequestMapping(value="/deleteDept",method=RequestMethod.POST)
	@ApiOperation(value = "删除一个部门" ,  notes="删除一个部门")
	public Map<String, Object> deleteDept(
			@RequestParam Integer id) {
		int result = deptDubboService.deleteDept(id);
		return Util.setMapDelete(result,map);
	}
}
