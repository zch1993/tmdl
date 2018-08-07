/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.entity.demandmanagement;

import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

/**
 * 需量管理Entity
 * @author zhangch@umasoft
 * @version 2018-07-09
 */
public class TmdlPowerDemand extends DataEntity<TmdlPowerDemand> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitId;		// 用电单位
	private TmdlSubstation stationId;		// 变电站
	private TmdlPowerShunt shuntId;		// 分路
	private Date month;		// 年月
	private String demand;		// 需量

	public TmdlPowerDemand() {
		super();
	}

	public TmdlPowerDemand(String id){
		super(id);
	}

	public UserCompany getUnitId() {
		return unitId;
	}

	public void setUnitId(UserCompany unitId) {
		this.unitId = unitId;
	}

	public TmdlSubstation getStationId() {
		return stationId;
	}

	public void setStationId(TmdlSubstation stationId) {
		this.stationId = stationId;
	}


	public TmdlPowerShunt getShuntId() {
		return shuntId;
	}

	public void setShuntId(TmdlPowerShunt shuntId) {
		this.shuntId = shuntId;
	}

	@JsonFormat(pattern = "yyyy-MM")
	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	@Length(min=0, max=255, message="需量长度必须介于 0 和 255 之间")
	public String getDemand() {
		return demand;
	}

	public void setDemand(String demand) {
		this.demand = demand;
	}





}