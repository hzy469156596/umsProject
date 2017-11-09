package com.qingshixun.dao;

import java.util.List;

import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;

public interface IRoleDao {
	List<Role> getRole(Role role);
	boolean delete(int deleteId);
	boolean from(Role role);
	
	List<Jurisdiction> queryJurisdiction(Jurisdiction jurisdiction);
	boolean deleteAll(List<Integer> ids);
	Role findEditRole(int editId);
	boolean editRole(Role role);
	
	
	
}
