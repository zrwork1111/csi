package com.zr.csi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zr.csi.model.Job;
import com.zr.csi.util.Util;
import com.zr.dubbo.JobDubboService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/job")
public class JobController {
	@Reference(version="1_4", check=false)
	JobDubboService jobDubboService;
	Map<String, Object> map;
	
	@RequestMapping(value="/queryJob", method=RequestMethod.POST)
	@ApiOperation(value="职位查询", notes = "职位查询")
	public Map<String, Object> queryJob(
		@RequestParam(value = "name", required=false) String name
			) {
		List<Job> list = jobDubboService.queryJob(name);
		return Util.setMapQuery(list, map);
	}
	
	@RequestMapping(value="/addJob", method=RequestMethod.POST)
	@ApiOperation(value="职位添加", notes="职位添加")
	public Map<String, Object> addJob(
			@RequestBody Job job) {
		int result=jobDubboService.addJob(job);
		return Util.setMapInsert(result, map);
	}
	
	@RequestMapping(value="/updateJob",method=RequestMethod.POST)
	@ApiOperation(value = "更新职位" ,  notes="更新职位")
	public Map<String, Object> updateJob(
			@RequestBody Job job) {
		
		int result = jobDubboService.updateJob(job);
		return Util.setMapUpdate(result, map);
	}
	
	@RequestMapping(value="/deleteJobs",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个职位" ,  notes="删除多个职位")
	public Map<String, Object> deleteJobs(
			@RequestParam String id) {
		String[] ids = id.split(",");
		int count = 0, length = ids.length;
		for (String val: ids) {
			int result = jobDubboService.deleteJob(Integer.valueOf(val));
			if (result > 0) {
				count++;
			}
		}
		
		return Util.setMapDeletes(count, length, map);
	}
	
	@RequestMapping(value="/deleteJob",method=RequestMethod.POST)
	@ApiOperation(value = "删除一个职位" ,  notes="删除一个职位")
	public Map<String, Object> deleteJob(
			@RequestParam Integer id) {
		int result = jobDubboService.deleteJob(id);
		return Util.setMapDelete(result,map);
	}
}
