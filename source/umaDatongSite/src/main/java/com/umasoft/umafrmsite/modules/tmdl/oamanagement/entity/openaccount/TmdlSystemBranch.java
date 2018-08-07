/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.openaccount;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;

/**
 * 开户管理Entity
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
public class TmdlSystemBranch extends DataEntity<TmdlSystemBranch> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitid;		// 单位ID
	private Long fixation;		// 核准照明电量

	private String unitcode;//单位代码

	
	public TmdlSystemBranch() {
		super();
	}

	public TmdlSystemBranch(String id){
		super(id);
	}

	
	public UserCompany getUnitid() {
		return unitid;
	}

	public void setUnitid(UserCompany unitid) {
		this.unitid = unitid;
	}

	public Long getFixation() {
		return fixation;
	}

	public void setFixation(Long fixation) {
		this.fixation = fixation;
	}

	public String getUnitcode() {
		return unitcode;
	}

	public void setUnitcode(String unitcode) {
		this.unitcode = unitcode;
	}
}