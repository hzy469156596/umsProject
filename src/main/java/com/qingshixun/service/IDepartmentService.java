package com.qingshixun.service;

import java.util.List;

import com.qingshixun.model.Department;

public interface IDepartmentService {
	List<Department> getDepartment(Department department);
	boolean form(Department department);
	
	Department editDepartment(int editDepartmentId);
	boolean editDepartmentSave(Department department);
	boolean delete(int deleteDepartmentId);
	boolean deleteAll(List<Integer> ids);
}
