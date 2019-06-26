package com.zr.dubbo;

import java.util.List;

import com.zr.csi.model.User;

public interface UserDubboService {
	public User selectUserById(Integer userId);
	public List<User> queryUser(String userName, Integer status);
	public int addUser(User user);
	public int updateUser(User user);
	public int deleteUser(Integer id);
}