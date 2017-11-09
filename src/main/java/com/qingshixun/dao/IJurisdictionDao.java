package com.qingshixun.dao;

import java.util.List;

import com.qingshixun.model.Jurisdiction;

public interface IJurisdictionDao {
	List<Jurisdiction> getJurisdiction(Jurisdiction jurisdiction);
	boolean from (Jurisdiction jurisdiction);
	
	Jurisdiction  editJurisdiction(int editJurisdictionId);
	boolean editJurisdiction(Jurisdiction jurisdiction);
	boolean delete(int deleteJurisdictionId);
	
	boolean deleteAll(List<Integer> ids);
}
