package com.qingshixun.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.IRoleDao;
import com.qingshixun.model.Department;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.model.Role;
import com.qingshixun.model.User;
import com.qingshixun.service.IRoleService;

@Service("roleService")
@Transactional
public class RoleService implements IRoleService {
	@Autowired
	private IRoleDao roleDao;

	// 查询所有角色信息
	@Override
	public List<Role> getRole(Role role) {
		List<Role> listrole = roleDao.getRole(role);
		return listrole;
	}

	// 删除一行账户
	@Override
	public boolean delete(int deleteId) {
		return roleDao.delete(deleteId);
	}

	// 查询权限
	@Override
	public List<Jurisdiction> addqueryJurisdiction(Jurisdiction jurisdiction){
		List<Jurisdiction> addqueryJurisdiction = roleDao.queryJurisdiction(jurisdiction);
		return addqueryJurisdiction;
	}
	
//修改权限时，获取已有权限	
	@Override
	public List<Jurisdiction> queryJurisdiction(int editId, Jurisdiction jurisdiction) {

		Role listEditRole = roleDao.findEditRole(editId);
		List<Jurisdiction> queryJurisdiction = roleDao.queryJurisdiction(jurisdiction);
		for (Jurisdiction jurisdiction1 : queryJurisdiction) {
			for (Jurisdiction myJurisdiction : listEditRole.getJurisdiction()) {
				if (jurisdiction1.getId() == myJurisdiction.getId()) {
					jurisdiction1.setFlag(true);
					break;
				}
			}
		}
		return queryJurisdiction;
	}

	// 添加角色
	@Override
	public boolean from(Role role) {
		return roleDao.from(role);
	}

	// 全选删除
	@Override
	public boolean deleteAll(List<Integer> ids) {
		return roleDao.deleteAll(ids);
	}

	// 编辑角色
	@Override
	public Role findEditRole(int editId,Jurisdiction jurisdiction) {
		Role listEditRole = roleDao.findEditRole(editId);
		return listEditRole;
	}
	

	// 把修改的数据更新到数据库
	@Override
	public boolean editRoleSave(Role role) {
		return roleDao.editRole(role);
	}

	
}
