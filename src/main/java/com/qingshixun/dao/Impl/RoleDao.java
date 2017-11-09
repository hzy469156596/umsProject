package com.qingshixun.dao.Impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.qingshixun.dao.IRoleDao;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

@RunWith(SpringJUnit4ClassRunner.class) // Spring 整合 JUnit4
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定
																		// spring
																		// 配置文件的位置
@Repository("roleDao")
public class RoleDao implements IRoleDao {

	@Autowired
	private SessionFactory sessionFactory;

	// 查找角色信息
	@Override
	public List<Role> getRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Role");
		List<Role> listrole = query.list();
		return listrole;
	}

	// 先查询要删除行的ID，找到该Id之后删除
	@Override
	public boolean delete(int deleteId) {
		Session session = sessionFactory.getCurrentSession();

		Role role = (Role) session.get(Role.class, deleteId);
		if (role != null) {
			session.delete(role);
			return true;
		}
		return false;
	}

	/**
	 * 新增角色 查询权限
	 */
	@Override
	public List<Jurisdiction> queryJurisdiction(Jurisdiction jurisdiction) {
		Session session = sessionFactory.getCurrentSession();
		// 查询用户
		Query queryJurisdiction = session.createQuery("from Jurisdiction");
		List<Jurisdiction> listQueryJurisdiction = queryJurisdiction.list();
		return listQueryJurisdiction;
	}

	/**
	 * 新增角色
	 */
	@Override
	public boolean from(Role role) {
		Session session = sessionFactory.getCurrentSession();
		role.setCreateDate(new Date());
		session.save(role);
		return true;
	}

	// 全选
	@Override
	public boolean deleteAll(List<Integer> ids) {
		Session session = sessionFactory.getCurrentSession();
		session.createQuery("delete from Role where id in (:id)").setParameterList("id", ids).executeUpdate();
		System.out.println("删除成功了");
		return true;
	}
	
	/**
	 * 通过Id找到当前要编辑的角色信息。
	 */
	@Override
	public Role findEditRole(int editId) {
		Session session = sessionFactory.getCurrentSession();
		Role role =(Role) session.get(Role.class, editId);
		System.out.println("找到了要编辑的角色信息" + role );
		return role;
	}
	
	

	@Override
	public boolean editRole(Role role) {
		Session session = sessionFactory.getCurrentSession();
		role.setCreateDate(new Date());
		session.saveOrUpdate(role);
		return true;
	}
}
