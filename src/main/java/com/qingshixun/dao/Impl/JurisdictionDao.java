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

import com.qingshixun.dao.IJurisdictionDao;
import com.qingshixun.model.Jurisdiction;

@RunWith(SpringJUnit4ClassRunner.class) // Spring 整合 JUnit4
@ContextConfiguration(locations = "classpath:applicationContext.xml") // 指定
																		// spring
																		// 配置文件的位置
@Repository("jurisdictionDao")
public class JurisdictionDao implements IJurisdictionDao{
	@Autowired
	private SessionFactory sessionFactory;

	// 查找权限信息
	@Override
	public List<Jurisdiction> getJurisdiction(Jurisdiction jurisdiction) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Jurisdiction");
		List<Jurisdiction> listrole = query.list();
		return listrole;
	}
//	添加权限
	@Override
	public boolean from(Jurisdiction jurisdiction) {
		Session session = sessionFactory.getCurrentSession();
		jurisdiction.setCreateDate(new Date());
		session.save(jurisdiction);
		return true;
		
	}
	/**
	 * 通过Id找到当前要编辑的权限的信息。
	 */
	@Override
	public Jurisdiction editJurisdiction(int editJurisdictionId) {
		Session session = sessionFactory.getCurrentSession();
		Jurisdiction jurisdiction = (Jurisdiction) session.get(Jurisdiction.class, editJurisdictionId);
		System.out.println("找到了要编辑的权限信息" + jurisdiction);
		return jurisdiction;
	}
//	更改权限
	@Override
	public boolean editJurisdiction(Jurisdiction jurisdiction) {
		Session session = sessionFactory.getCurrentSession();
		jurisdiction.setCreateDate(new Date());
		session.saveOrUpdate(jurisdiction);
		return true;
	}
	// 先查询要删除行的ID，找到该Id之后删除
	@Override
	public boolean delete(int deleteJurisdictionId) {
		Session session = sessionFactory.getCurrentSession();
		Jurisdiction jurisdiction = (Jurisdiction) session.get(Jurisdiction.class, deleteJurisdictionId);
		if (jurisdiction != null) {
			session.delete(jurisdiction);
		}
		return true;
	}

	// 全选
		@Override
		public boolean deleteAll(List<Integer> ids) {
			Session session = sessionFactory.getCurrentSession();
			session.createQuery("delete from Jurisdiction where id in (:id)").setParameterList("id", ids).executeUpdate();
			return true;
		}

	
}
