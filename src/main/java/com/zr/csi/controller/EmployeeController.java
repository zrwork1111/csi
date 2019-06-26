package com.zr.csi.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zr.csi.model.Employee;
import com.zr.csi.util.Util;
import com.zr.dubbo.EmployeeDubboService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	@Reference(version="1_3",check=false)
	EmployeeDubboService employeeDubboService;
	Map<String, Object> map;
	@RequestMapping(value="/queryEmployee", method=RequestMethod.POST)
	@ApiOperation(value="员工查询", notes="员工查询")
	public Map<String, Object> queryEmployee(
		@RequestParam(value = "jobId", required=false) Integer jobId,
		@RequestParam(value = "sex", required=false) Integer sex,
		@RequestParam(value = "name", required=false) String name,
		@RequestParam(value = "cardId", required=false) String cardId,
		@RequestParam(value = "deptId", required=false) Integer deptId,
		@RequestParam(value = "phone", required=false) String phone
			) {	
		
		List<Employee> list = employeeDubboService.queryEmployee(jobId, sex, name, cardId, deptId, phone);
		return Util.setMapQuery(list, map);
	}
	
	@RequestMapping(value="/addEmployee", method=RequestMethod.POST)
	@ApiOperation(value="员工添加", notes="员工添加")
	public Map<String, Object> addEmployee(
		@RequestBody Employee employee
			) {
		Date createDate = new Date();
		employee.setCreateDate(createDate);
		  
		int result=employeeDubboService.addEmployee(employee);
		return Util.setMapInsert(result, map);
	}
	
	@RequestMapping(value="/updateEmployee",method=RequestMethod.POST)
	@ApiOperation(value = "更新员工" ,  notes="更新员工")
	public Map<String, Object> updateEmployee(
		@RequestBody Employee employee) {
		int result = employeeDubboService.updateEmployee(employee);
		return Util.setMapUpdate(result, map);
	}
	
	@RequestMapping(value="/deleteEmployees",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个员工" ,  notes="删除多个员工")
	public Map<String, Object> deleteEmployees(
			@RequestParam String id) {
		String[] ids = id.split(",");
		int count = 0, length = ids.length;
		for (String val: ids) {
			int result = employeeDubboService.deleteEmployee(Integer.valueOf(val));
			if (result > 0) {
				count++;
			}
		}
		
		return Util.setMapDeletes(count, length, map);
	}
	
	@RequestMapping(value="/deleteEmployee",method=RequestMethod.POST)
	@ApiOperation(value = "删除一个员工" ,  notes="删除一个员工")
	public Map<String, Object> deleteEmployee(
			@RequestParam Integer id) {
		int result = employeeDubboService.deleteEmployee(id);
		return Util.setMapDelete(result,map);
	}
}
