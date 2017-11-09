package com.qingshixun.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qingshixun.model.Department;
import com.qingshixun.service.IDepartmentService;

@Namespace("/") // @Namespace来代替<package namespace="">
@ParentPackage("base") // @ParentPackage来代替<package extends=””>
@Controller("departmentAction")
public class DepartmentAction {
	@Autowired
	private IDepartmentService departmentService;

	private Department department;

	private List<Department> listdepartment;

	private Department listEditDepartment;

	private int editDepartmentId;
	private int deleteDepartmentId;
	private String message;
	private List<Integer> ids;

	/**
	 * 部门列表
	 * 
	 * @return
	 */
	@Action(value = "queryDepartment", results = {
			@Result(name = "success", location = "/WEB-INF/views/department/department.jsp") })
	public String queryDepartment() {
		listdepartment = departmentService.getDepartment(department);
		return "success";
	}

	/**
	 * 新增部门跳转
	 */
	@Action(value = "queryAdddepartment", results = {
			@Result(name = "success", location = "/WEB-INF/views/department/addDepartment.jsp") })
	public String queryAddjurisdiction() {
		return "success";
	}

	/**
	 * 新增部门
	 */
	@Action(value = "formdescription", results = {
			@Result(name = "success", type="json") })
	public String formdescription() {
		if (departmentService.form(department)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 编辑选中的部门信息
	 * 
	 * @return
	 */
	@Action(value = "editDepartment", results = {
			@Result(name = "success", location = "/WEB-INF/views/department/editDepartment.jsp") })
	public String editDepartment() {
		listEditDepartment = departmentService.editDepartment(editDepartmentId);
		return "success";
	}

	/**
	 * 更新权限信息
	 * 
	 * @return
	 */
	@Action(value = "editDepartmentSave", results = {
			@Result(name = "success",type="json") })
	public String editDepartmentSave() {
		if (departmentService.editDepartmentSave(department)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 删除部门
	 * 
	 * @return
	 */
	@Action(value = "deleteDepartment", results = {
			@Result(name = "success", type="json") })
	public String delete() {
		if(departmentService.delete(deleteDepartmentId)){
			message = "success";
		}
		return "success";
	}

	/**
	 * 选中全部删除
	 * 
	 * @return
	 */
	@Action(value = "deleteAllDepartment", results = { @Result(name = "success", type = "json") })
	public String deleteAll() {
		System.out.println("传进来的值：" + ids);
		if (departmentService.deleteAll(ids)) {
			message = "success";
		}
		return "success";
	}

	public IDepartmentService getDepartmentService() {
		return departmentService;
	}

	public void setDepartmentService(IDepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	public List<Department> getListdepartment() {
		return listdepartment;
	}

	public void setListdepartment(List<Department> listdepartment) {
		this.listdepartment = listdepartment;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Department getListEditDepartment() {
		return listEditDepartment;
	}

	public void setListEditDepartment(Department listEditDepartment) {
		this.listEditDepartment = listEditDepartment;
	}

	public int getEditDepartmentId() {
		return editDepartmentId;
	}

	public void setEditDepartmentId(int editDepartmentId) {
		this.editDepartmentId = editDepartmentId;
	}

	public int getDeleteDepartmentId() {
		return deleteDepartmentId;
	}

	public void setDeleteDepartmentId(int deleteDepartmentId) {
		this.deleteDepartmentId = deleteDepartmentId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
