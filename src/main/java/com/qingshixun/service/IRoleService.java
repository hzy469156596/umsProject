package com.qingshixun.service;

import java.util.List;

import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

public interface IRoleService {
	List<Role> getRole(Role role);
	boolean delete(int deleteId);
	boolean from(Role role);
	 List<Jurisdiction> queryJurisdiction(int editId,Jurisdiction jurisdiction);
	 boolean deleteAll(List<Integer> ids);
	 Role findEditRole(int editId,Jurisdiction jurisdiction);
	 boolean editRoleSave(Role role);
	 public List<Jurisdiction> addqueryJurisdiction(Jurisdiction jurisdiction);
	 //获取权限
}
