package com.qingshixun.service;

import java.util.List;

import com.qingshixun.model.Jurisdiction;

public interface IJurisdictionService {

	List<Jurisdiction> getJurisdiction(Jurisdiction jurisdiction);
	boolean from(Jurisdiction jurisdiction);
	
	Jurisdiction editJurisdiction(int editJurisdictionId);
	boolean editJurisdictionSave(Jurisdiction jurisdiction);
	boolean delete(int deleteJurisdictionId);
	boolean deleteAll(List<Integer> ids);
}
