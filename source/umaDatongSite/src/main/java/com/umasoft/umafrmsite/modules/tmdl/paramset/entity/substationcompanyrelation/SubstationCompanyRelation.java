/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation;

import javax.validation.constraints.NotNull;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;

/**
 * 变电站和用电单位对应关系Entity
 * @author zhangky@umasoft
 * @version 2018-07-11
 */
public class SubstationCompanyRelation extends DataEntity<SubstationCompanyRelation> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitid;		// 单位ID
	private TmdlSubstation bdzid;		// 变电站ID
	
	public SubstationCompanyRelation() {
		super();
	}

	public SubstationCompanyRelation(String id){
		super(id);
	}

	public SubstationCompanyRelation(TmdlSubstation bdzid) {
		this.bdzid = bdzid;
	}

	public UserCompany getUnitid() {
		return unitid;
	}

	public void setUnitid(UserCompany unitid) {
		this.unitid = unitid;
	}


	public TmdlSubstation getBdzid() {
		return bdzid;
	}

	public void setBdzid(TmdlSubstation bdzid) {
		this.bdzid = bdzid;
	}

		
		
		
		
		
		
		
		
		
}