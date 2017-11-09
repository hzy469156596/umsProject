package com.qingshixun.service;

import java.util.List;

import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;


public interface IUserService {
	
	
	int selectUser(String name,String password);
	List<User> getUser(User user);
	boolean delete(int deleteId);
	
	List<Department> queryDepartment (Department department);
	List<Role> queryRole (Role role);
	
	boolean from(User user);
	
	User editUser(int editId);
	
	boolean editUserSave(User user);
	
	
	boolean deleteAll(List<Integer> ids);
}
