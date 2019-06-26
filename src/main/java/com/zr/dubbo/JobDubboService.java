package com.zr.dubbo;

import java.util.List;

import com.zr.csi.model.Job;

public interface JobDubboService {

	List<Job> queryJob(String name);

	int addJob(Job job);

	int updateJob(Job job);

	int deleteJob(Integer id);

}
