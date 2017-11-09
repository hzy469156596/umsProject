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

import com.qingshixun.dao.IDepartmentDao;
import com.qingshixun.model.Department;
import com.qingshixun.model.Jurisdiction;

@RunWith(SpringJUnit4ClassRunner.class) // Spring 整合 JUnit4
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定
																		// spring
																		// 配置文件的位置
@Repository("departmentDao")
public class DepartmentDao implements IDepartmentDao{
	@Autowired
	private SessionFactory sessionFactory;
	// 查找部门信息
	@Override
	public List<Department> getDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Department");
		List<Department> listdepartment = query.list();
		return listdepartment;
	}
//	添加部门
	@Override
	public boolean form(Department department) {
		Session session = sessionFactory.getCurrentSession();
		department.setCreateDate(new Date());
		session.save(department);
		return true;
		
	}
	/**
	 * 通过Id找到当前要编辑的部门的信息。
	 */
	@Override
	public Department editDepartment(int editDepartmentId) {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department) session.get(Department.class, editDepartmentId);
		System.out.println("找到了要编辑的权限信息" + department);
		return department;
	}
	//更新部门
	@Override
	public boolean editDepartment(Department department) {
		Session session = sessionFactory.getCurrentSession();
		department.setCreateDate(new Date());
		session.saveOrUpdate(department);
		return true;
		
	}
	//删除部门
	@Override
	public boolean delete(int deleteDepartmentId) {
		Session session = sessionFactory.getCurrentSession();
		Department department = (Department) session.get(Department.class, deleteDepartmentId);
		if (department != null) {
			session.delete(department);
		}
		return true;
	}

	// 全选
		@Override
		public boolean deleteAll(List<Integer> ids) {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery("delete from Department where id in (:id)").setParameterList("id", ids).executeUpdate();
			return true;
		}
}
