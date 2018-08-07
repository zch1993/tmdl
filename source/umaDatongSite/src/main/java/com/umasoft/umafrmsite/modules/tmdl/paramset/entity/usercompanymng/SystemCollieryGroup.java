/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng;

import org.hibernate.validator.constraints.Length;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

/**
 * 矿业和集团比例分配Entity
 * @author zhangky@umasoft
 * @version 2018-07-12
 */
public class SystemCollieryGroup extends DataEntity<SystemCollieryGroup> {
	
	private static final long serialVersionUID = 1L;
	private String collieryname;		// 煤矿企业名称
	private UserCompany unitid;		// 单位ID
	private Double ydbl;		// 所占比例
	
	private List<SystemCollieryGroup> cgList;
	
	public SystemCollieryGroup() {
		super();
	}

	public SystemCollieryGroup(String id){
		super(id);
	}
	
	public SystemCollieryGroup(UserCompany unitid){
		this.unitid = unitid;
	}

	@Length(min=1, max=100, message="煤矿企业名称长度必须介于 1 和 100 之间")
	public String getCollieryname() {
		return collieryname;
	}

	public void setCollieryname(String collieryname) {
		this.collieryname = collieryname;
	}

	public UserCompany getUnitid() {
		return unitid;
	}

	public void setUnitid(UserCompany unitid) {
		this.unitid = unitid;
	}

	//@NotNull(message="所占比例不能为空")
	public Double getYdbl() {
		return ydbl;
	}

	public void setYdbl(Double ydbl) {
		this.ydbl = ydbl;
	}

	public List<SystemCollieryGroup> getCgList() {
		return cgList;
	}

	public void setCgList(List<SystemCollieryGroup> cgList) {
		this.cgList = cgList;
	}

		
		
		
		
		
		
		
		
		
		
}