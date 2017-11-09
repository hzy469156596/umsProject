package com.qingshixun.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;
import com.qingshixun.service.IRoleService;

@Namespace("/") // @Namespace来代替<package namespace="">
@ParentPackage("base") // @ParentPackage来代替<package extends=””>
@Controller("roleAction")
public class RoleAction {

	@Autowired
	private IRoleService roleService;

	private int deleteId;

	private Role role;

	private Jurisdiction jurisdiction;

	private List<Role> listrole;

	private String message;

	private List<Jurisdiction> listjurisdiction;

	private List<Integer> ids;

	private int editId;

	private Role findEditRole;

	/**
	 * 角色列表
	 * 
	 * @return
	 */
	@Action(value = "queryRole", results = {
			@Result(name = "success", location = "/WEB-INF/views/role/roleManage.jsp") })
	public String list() {
		listrole = roleService.getRole(role);
		return "success";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "deleteRole", results = { @Result(name = "success", type = "json") })
	public String delete() {
		if (roleService.delete(deleteId)) {
			message = "success";
			return "success";
		}
		return "fail";
	}

	// 添加时获取权限的id
	@Action(value = "addRole", results = { @Result(name = "success", location = "/WEB-INF/views/role/addRole.jsp") })
	public String addRole() {
		listjurisdiction = roleService.addqueryJurisdiction(jurisdiction);
		return "success";
	}

	/**
	 * 新增角色
	 */
	@Action(value = "addRoleSubmit", results = { @Result(name = "success", type = "json") })
	public String from() {
		if (roleService.from(role)) {
			message = "success";
			return "success";
		}
		return "fail";
	}

	/**
	 * 选中全部删除
	 * 
	 * @return
	 */
	@Action(value = "deleteAllRole", results = { @Result(name = "success", type = "json") })
	public String deleteAll() {
		System.out.println("传进来的值：" + ids);
		if (roleService.deleteAll(ids)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 编辑选中的角色信息
	 * 
	 * @return
	 */
	@Action(value = "editRole", results = { @Result(name = "success", location = "/WEB-INF/views/role/editRole.jsp") })
	public String editUser() {
		findEditRole = roleService.findEditRole(editId, jurisdiction);
		listjurisdiction = roleService.queryJurisdiction(editId,jurisdiction);
		return "success";
	}

	/**
	 * 修改角色信息
	 * 
	 * @return
	 */
	@Action(value = "editRoleSubmit", results = { @Result(name = "success", type = "json") })
	public String editUserSave() {
		if (roleService.editRoleSave(role)) {
			message = "success";
		}
		return "success";
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Role> getListrole() {
		return listrole;
	}

	public void setListrole(List<Role> listrole) {
		this.listrole = listrole;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Jurisdiction> getListjurisdiction() {
		return listjurisdiction;
	}

	public void setListjurisdiction(List<Jurisdiction> listjurisdiction) {
		this.listjurisdiction = listjurisdiction;
	}

	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public Role getFindEditRole() {
		return findEditRole;
	}

	public void setFindEditRole(Role findEditRole) {
		this.findEditRole = findEditRole;
	}



	


	

}
