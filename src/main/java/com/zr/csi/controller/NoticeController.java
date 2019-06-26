package com.zr.csi.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zr.csi.model.Notice;
import com.zr.csi.util.Util;
import com.zr.dubbo.NoticeDubboService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value="/notice")
public class NoticeController {
	@Reference(version="1_5", check=false)
	NoticeDubboService noticeDubboService;
	Map<String, Object> map;
	
	@RequestMapping(value="/queryNotice", method=RequestMethod.POST)
	@ApiOperation(value="公告查询", notes="公告查询")
	public Map<String, Object> queryNotice(
		@RequestParam(value="title", required=false) String title,
		@RequestParam(value="content", required=false) String content
			) {
		List<Notice> list = noticeDubboService.queryNotice(title, content);
		return Util.setMapQuery(list, map);
	}
	
	@RequestMapping(value="/addNotice", method=RequestMethod.POST)
	@ApiOperation(value="公告添加", notes="公告添加")
	public Map<String, Object> addNotice(
			@RequestBody Notice notice
			) {
		int result=noticeDubboService.addNotice(notice);
		return Util.setMapInsert(result, map);
	}
	
	@RequestMapping(value="/updateNotice",method=RequestMethod.POST)
	@ApiOperation(value = "更新公告" ,  notes="更新公告")
	public Map<String, Object> updateNotice(
			@RequestBody Notice notice) {
		int result = noticeDubboService.updateNotice(notice);
		return Util.setMapUpdate(result, map);
	}
	
	@RequestMapping(value="/deleteNotices",method=RequestMethod.POST)
	@ApiOperation(value = "删除多个公告" ,  notes="删除多个公告")
	public Map<String, Object> deleteNotices(
			@RequestParam String id) {
		String[] ids = id.split(",");
		int count = 0, length = ids.length;
		for (String val: ids) {
			int result = noticeDubboService.deleteNotice(Integer.valueOf(val));
			if (result > 0) {
				count++;
			}
		}
		
		return Util.setMapDeletes(count, length, map);
	}
	
	@RequestMapping(value="/deleteNotice",method=RequestMethod.POST)
	@ApiOperation(value = "删除一个公告" ,  notes="删除一个公告")
	public Map<String, Object> deleteNotice(
			@RequestParam Integer id) {
		int result = noticeDubboService.deleteNotice(id);
		return Util.setMapDelete(result,map);
	}
}
