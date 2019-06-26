package com.zr.dubbo;

import java.util.List;

import com.zr.csi.model.Employee;

public interface EmployeeDubboService {
int addEmployee(Employee employee);

int updateEmployee(Employee employee);

List<Employee> queryEmployee(Integer jobId, Integer sex, String name, String cardId, Integer deptId, String phone);

int deleteEmployee(Integer id);

}
