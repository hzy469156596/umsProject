package com.qingshixun.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qingshixun.dao.IJurisdictionDao;
import com.qingshixun.model.Jurisdiction;
import com.qingshixun.service.IJurisdictionService;

@Service("jurisdictionService")
@Transactional
public class JurisdictionService implements IJurisdictionService {
	@Autowired
	private IJurisdictionDao jurisdictionDao;

	// 查询所有权限信息
	@Override
	public List<Jurisdiction> getJurisdiction(Jurisdiction jurisdiction) {
		List<Jurisdiction> listJurisdiction = jurisdictionDao.getJurisdiction(jurisdiction);
		return listJurisdiction;
	}

	// 添加权限
	@Override
	public boolean from(Jurisdiction jurisdiction) {
		return jurisdictionDao.from(jurisdiction);
	}

	// 编辑权限
	@Override
	public Jurisdiction editJurisdiction(int editJurisdictionId) {
		Jurisdiction listEditJurisdiction = jurisdictionDao.editJurisdiction(editJurisdictionId);
		return listEditJurisdiction;
	}

	// 更新权限
	@Override
	public boolean editJurisdictionSave(Jurisdiction jurisdiction) {
		return jurisdictionDao.editJurisdiction(jurisdiction);
	}

	// 删除权限
	@Override
	public boolean delete(int deleteJurisdictionId) {
		return jurisdictionDao.delete(deleteJurisdictionId);
	}
	
	// 全选删除
		@Override
		public boolean deleteAll(List<Integer> ids) {
			return jurisdictionDao.deleteAll(ids);
		}
}
