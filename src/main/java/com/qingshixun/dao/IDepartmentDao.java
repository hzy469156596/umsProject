package com.qingshixun.dao;

import java.util.List;

import com.qingshixun.model.Department;

public interface IDepartmentDao {
	List<Department> getDepartment(Department department);
	boolean form (Department department);
	
	Department  editDepartment(int editDepartmentId);
	boolean editDepartment(Department department);
	boolean delete(int deleteDepartmentId);
	boolean deleteAll(List<Integer> ids);
}
