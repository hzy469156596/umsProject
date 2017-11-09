package com.qingshixun.dao;


import java.util.List;


import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

public interface IUserDao {

	
	List<Department> queryDepartment (Department department);
	List<Role> queryRole (Role role);
	User selectUser(String name);
	List<User> getUser(User user);
	boolean delete(int deleteId);
	boolean from (User user);
	
	User edit(int editId);
	
	boolean editUser(User user);
	//全删除
	
	boolean deleteAll(List<Integer> ids);
}
