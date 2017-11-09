package com.qingshixun.service.Impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.IUserDao;
import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;
import com.qingshixun.service.IUserService;

@Transactional
@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserDao userDao;

	@Override
	public int selectUser(String name, String password) {
		User user = userDao.selectUser(name);
		// 如果不为NULL，说明查询到用户，就可以继续验证他的密码是否正确。
		if (user != null) {
			if (user.getPassword().equals(password)) {
				// 密码正确，允许登录。
				return 1;
			} else {
				// 密码错误。
				return -1;
			}
		}
		// 没有查询到该用户，用户不存在。
		return -2;
	}

	// 查询所有账户信息
	@Override
	public List<User> getUser(User user) {
		List<User> listuser = userDao.getUser(user);
		return listuser;
	}

	// 删除一行账户
	@Override
	public boolean delete(int deleteId) {
		return userDao.delete(deleteId);
	}

	@Override
	public List<Department> queryDepartment(Department department) {
		List<Department> listdepartment = userDao.queryDepartment(department);
		return listdepartment;
	}

	@Override
	public List<Role> queryRole(Role role) {
		List<Role> listrole = userDao.queryRole(role);
		return listrole;
	}

	// 添加用户
	@Override
	public boolean from(User user) {
		return userDao.from(user);
	}

	// 编辑用户
	@Override
	public User editUser(int editId) {
		User listEditUser = userDao.edit(editId);
		return listEditUser;
	}

	// 把修改的数据更新到数据库
	@Override
	public boolean editUserSave(User user) {
		return userDao.editUser(user);
	}

	// 全选删除
	@Override
	public boolean deleteAll(List<Integer> ids) {
		return userDao.deleteAll(ids);
	}
}
