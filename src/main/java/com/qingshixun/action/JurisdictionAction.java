package com.qingshixun.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.qingshixun.model.Jurisdiction;
import com.qingshixun.service.IJurisdictionService;

@Namespace("/") // @Namespace来代替<package namespace="">
@ParentPackage("base") // @ParentPackage来代替<package extends=””>
@Controller("jurisdictionAction")
public class JurisdictionAction {
	@Autowired
	private IJurisdictionService jurisdictionService;
	private int deleteJurisdictionId;
	private int editJurisdictionId;
	private Jurisdiction jurisdiction;
	private Jurisdiction listEditJurisdiction;
	private List<Jurisdiction> listjurisdiction;
	private List<Integer> ids;
	private String message;

	/**
	 * 权限列表
	 * 
	 * @return
	 */
	@Action(value = "queryJurisdiction", results = {
			@Result(name = "success", location = "/WEB-INF/views/jurisdiction/jurisdiction.jsp") })
	public String queryJurisdiction() {
		listjurisdiction = jurisdictionService.getJurisdiction(jurisdiction);
		return "success";
	}

	/**
	 * 新增权限跳转
	 */
	@Action(value = "queryAddjurisdiction", results = {
			@Result(name = "success", location = "/WEB-INF/views/jurisdiction/addJurisdiction.jsp") })
	public String queryAddjurisdiction() {
		return "success";
	}

	/**
	 * 添加权限
	 */
	@Action(value = "form", results = {
			@Result(name = "success", type="json") })
	public String form() {
		if (jurisdictionService.from(jurisdiction)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 编辑选中的权限信息
	 * 
	 * @return
	 */
	@Action(value = "editJurisdiction", results = {
			@Result(name = "success", location = "/WEB-INF/views/jurisdiction/editJurisdiction.jsp") })
	public String editJurisdiction() {
		listEditJurisdiction = jurisdictionService.editJurisdiction(editJurisdictionId);
		return "success";
	}

	/**
	 * 更新权限信息
	 * 
	 * @return
	 */
	@Action(value = "editJurisdictionSave", results = { @Result(name = "success", type = "json") })
	public String editJurisdictionSave() {
		if (jurisdictionService.editJurisdictionSave(jurisdiction)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 权限删除
	 * 
	 * @return
	 */
	@Action(value = "deleteJurisdiction", results = { @Result(name = "success", type = "json") })
	public String delete() {
		if (jurisdictionService.delete(deleteJurisdictionId)) {
			message = "success";
		}
		return "success";
	}

	/**
	 * 选中全部删除
	 * 
	 * @return
	 */
	@Action(value = "deleteAllJurisdiction", results = { @Result(name = "success", type = "json") })
	public String deleteAll() {
		System.out.println("传进来的值：" + ids);
		if (jurisdictionService.deleteAll(ids)) {
			message = "success";
		}
		return "success";
	}

	public int getDeleteJurisdictionId() {
		return deleteJurisdictionId;
	}

	public void setDeleteJurisdictionId(int deleteJurisdictionId) {
		this.deleteJurisdictionId = deleteJurisdictionId;
	}

	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public List<Jurisdiction> getListjurisdiction() {
		return listjurisdiction;
	}

	public void setListjurisdiction(List<Jurisdiction> listjurisdiction) {
		this.listjurisdiction = listjurisdiction;
	}

	public Jurisdiction getListEditJurisdiction() {
		return listEditJurisdiction;
	}

	public void setListEditJurisdiction(Jurisdiction listEditJurisdiction) {
		this.listEditJurisdiction = listEditJurisdiction;
	}

	public int getEditJurisdictionId() {
		return editJurisdictionId;
	}

	public void setEditJurisdictionId(int editJurisdictionId) {
		this.editJurisdictionId = editJurisdictionId;
	}

	public List<Integer> getIds() {
		return ids;
	}

	public void setIds(List<Integer> ids) {
		this.ids = ids;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
