package com.qingshixun.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.IDepartmentDao;
import com.qingshixun.model.Department;
import com.qingshixun.service.IDepartmentService;

@Transactional
@Service("departmentService")
public class DepartmentService implements IDepartmentService {
	@Autowired
	private IDepartmentDao departmentDao;

	// 查询所有部门信息
	@Override
	public List<Department> getDepartment(Department department) {
		List<Department> listDepartment = departmentDao.getDepartment(department);
		return listDepartment;
	}

	// 添加部门
	@Override
	public boolean form(Department department) {
		departmentDao.form(department);
		return true;
	}

	// 编辑部门
	@Override
	public Department editDepartment(int editDepartmentId) {
		Department listEditDepartment = departmentDao.editDepartment(editDepartmentId);
		return listEditDepartment;
	}

	// 更新部门
	@Override
	public boolean editDepartmentSave(Department department) {
		return departmentDao.editDepartment(department);
	}

	// 删除部门
	@Override
	public boolean delete(int deleteDepartmentId) {
		return departmentDao.delete(deleteDepartmentId);
	}
	// 全选删除
		@Override
		public boolean deleteAll(List<Integer> ids) {
			return departmentDao.deleteAll(ids);
		}
}
