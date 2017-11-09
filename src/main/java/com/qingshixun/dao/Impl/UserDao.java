package com.qingshixun.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingshixun.dao.IUserDao;
import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

@RunWith(SpringJUnit4ClassRunner.class) // Spring 整合 JUnit4
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定
																		// spring
																		// 配置文件的位置
@Repository("userDao")
public class UserDao implements IUserDao {

	@Autowired
	private SessionFactory sessionFactory;

	/**
	 * 登录查询用户名是否存在。
	 */
	@Override
	public User selectUser(String name) {
		Session session = sessionFactory.getCurrentSession();

		// 查询用户
		Query query = session.createQuery("from User Where name =:name");
		query.setParameter("name", name);
		List<User> list = query.list();
		if (list.size() != 0) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 遍历所有账号信息
	 */
	@Override
	@Test
	public List<User> getUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		// 查询用户
		Query query = session.createQuery("from User");
		List<User> listuser = query.list();

		return listuser;
	}

	// 先查询要删除行的ID，找到该Id之后删除
	@Override
	public boolean delete(int deleteId) {
		Session session = sessionFactory.getCurrentSession();

		User user = (User) session.get(User.class, deleteId);
		if (user != null) {
			session.delete(user);
			return true;
		}
		return false;
	}

	// 全选
	@Override
	public boolean deleteAll(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from User where id in (:id)").setParameterList("id", ids).executeUpdate();
		return true;
	}

	/**
	 * 新增用户 先要从数据库获取部门和角色信息
	 */
	@Override
	public List<Department> queryDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		// 查询用户
		Query queryDepartment = session.createQuery("from Department");
		List<Department> listQueryDepartment = queryDepartment.list();
		return listQueryDepartment;
	}

	@Override
	public List<Role> queryRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Query queryRole = session.createQuery("from Role");
		List<Role> listQueryRole = queryRole.list();
		System.out.println("执行了角色查询！");
		return listQueryRole;
	}

	/**
	 * 新增用户
	 */
	@Override
	public boolean from(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setCreateDate(new Date());
		session.save(user);
		return true;
	}

	/**
	 * 通过Id找到当前要编辑的用户的信息。
	 */
	@Override
	public User edit(int editId) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, editId);
		System.out.println("找到了要编辑的用户信息" + user);
		return user;
	}

	@Override
	public boolean editUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		user.setCreateDate(new Date());
		session.saveOrUpdate(user);
		return true;
	}

}
