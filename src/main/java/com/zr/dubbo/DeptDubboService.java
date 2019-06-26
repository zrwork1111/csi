package com.zr.dubbo;

import java.util.List;

import com.zr.csi.model.Dept;

public interface DeptDubboService {

	List<Dept> queryDept(String name);

	int addDept(Dept dept);

	int updateDept(Dept dept);

	int deleteDept(Integer id);
}
