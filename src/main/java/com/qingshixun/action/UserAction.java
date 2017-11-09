package com.qingshixun.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.qingshixun.model.Department;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;
import com.qingshixun.service.IRoleService;
import com.qingshixun.service.IUserService;

@Namespace("/") // @Namespace来代替<package namespace="">
// @ParentPackage("struts-default") @ParentPackage来代替<package extends=””>
@ParentPackage("base") // 这是写json
@Controller("userAction")
public class UserAction {

	@Autowired
	private IUserService userService;

	@Autowired
	private IRoleService roleService;
	// 获取要删除的用户ID
	private int deleteId;
	// 获取要删除的用户ID
	private List<Integer> ids;

	// 获取编辑用户的ID，来查找用户信息进行编辑
	private int editId;

	private User user;
	private Department department;
	private Role role;

	// 接受用户列表
	private List<User> listuser;
	// 接受要编辑的用户的信息
	private User listEditUser;
	// 接受部门
	private List<Department> listdepartment;
	// 接受角色
	private List<Role> listrole;

	private String message;

	/**
	 * 登录跳转。
	 * 
	 * @return
	 */
	@Action(value = "login", results = { @Result(name = "success", location = "/WEB-INF/views/main.jsp"),
			@Result(name = "error", location = "/WEB-INF/views/login.jsp") })
	public String Login() {
		int selectUser = 0;
		HttpSession session = ServletActionContext.getRequest().getSession();
		session.setAttribute("user", user);
		if (user != null) {
			selectUser = userService.selectUser(user.getName(), user.getPassword());
			if (selectUser == 1) {
				return "success";
			} else if (selectUser == -1) {
				// 提示密码错误，请重新输入。
				message = "用户名或密码错误!";
				return "error";
			}
		} else if (session.getAttribute("user") != null) {
			return "success";
		} else {
			return "error";
		}

		// 您输入的用户不存在，快去注册一个吧！
		message = "您输入的用户不存在！";
		return "error";
	}

	/**
	 * 跳转登录页面
	 */
	@Action(value = "toLogin", results = { @Result(name = "success", location = "/WEB-INF/views/login.jsp") })
	public String toLogin() {
		return "success";
	}
	/**
	 * 退出
	 */
	@Action(value = "exit", results = { @Result(name = "success", location = "/WEB-INF/views/login.jsp") })
	public String exit() {
		return "success";
	}

	/**
	 * 账户列表
	 * 
	 * @return
	 */
	@Action(value = "queryUser", results = {
			@Result(name = "success", location = "/WEB-INF/views/user/accountManage.jsp") })
	public String list() {
		listuser = userService.getUser(user);
		return "success";
	}

	/**
	 * 删除
	 * 
	 * @return
	 */
	@Action(value = "delete", results = { @Result(name = "success", type = "json") })
	public String delete() {
		if (userService.delete(deleteId)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 选中全部删除
	 * 
	 * @return
	 */
	@Action(value = "deleteAll", results = { @Result(name = "success", type = "json") })
	public String deleteAll() {
		System.out.println("传进来的值：" + ids);
		if (userService.deleteAll(ids)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 部门 角色 列表
	 * 
	 * @return
	 */
	@Action(value = "queryAdduser", results = {
			@Result(name = "success", location = "/WEB-INF/views/user/addUser.jsp") })
	public String listDepartment() {
		listdepartment = userService.queryDepartment(department);
		listrole = userService.queryRole(role);
		// listrole = roleService.getRole(role);
		return "success";
	}

	/**
	 * 新增用户
	 */
	@Action(value = "from", results = { @Result(name = "success", type = "json") })
	public String from() {
		if (userService.from(user)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 编辑选中的用户信息
	 * 
	 * @return
	 */
	@Action(value = "editUser", results = { @Result(name = "success", location = "/WEB-INF/views/user/edit.jsp") })
	public String editUser() {
		listEditUser = userService.editUser(editId);
		listdepartment = userService.queryDepartment(department);
		listrole = userService.queryRole(role);
		return "success";
	}

	/**
	 * 修改用户信息
	 * 
	 * @return
	 */
	@Action(value = "editUserSave", results = { @Result(name = "success", type = "json") })
	public String editUserSave() {
		if (userService.editUserSave(user)) {
			message = "success";
		}
		return "success";
	}

	public int getEditId() {
		return editId;
	}

	public void setEditId(int editId) {
		this.editId = editId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<User> getListuser() {
		return listuser;
	}

	public void setListuser(List<User> listuser) {
		this.listuser = listuser;
	}

	public int getDeleteId() {
		return deleteId;
	}

	public void setDeleteId(int deleteId) {
		this.deleteId = deleteId;
	}

	public List<Department> getListdepartment() {
		return listdepartment;
	}

	public void setListdepartment(List<Department> listdepartment) {
		this.listdepartment = listdepartment;
	}

	public List<Role> getListrole() {
		return listrole;
	}

	public void setListrole(List<Role> listrole) {
		this.listrole = listrole;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User getListEditUser() {
		return listEditUser;
	}

	public void setListEditUser(User listEditUser) {
		this.listEditUser = listEditUser;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

}
