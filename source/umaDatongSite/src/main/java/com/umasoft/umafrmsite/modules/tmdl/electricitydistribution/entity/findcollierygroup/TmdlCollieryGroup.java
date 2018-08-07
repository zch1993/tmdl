/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findcollierygroup;

import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

/**
 * 煤业和集团查询Entity
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
public class TmdlCollieryGroup extends DataEntity<TmdlCollieryGroup> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitId;		// 单位ID
	private Integer wattfulgross;		// 有功总量
	private Integer peaksegment;		// 峰段总量
	private Integer grainsegment;		// 谷段总量
	private Integer flatsegment;		// 平段总量
	private Integer pluswatt;		// 正有功总量
	private Integer resewatt;		// 反有功总量
	private Integer demnum;		// 需量表底
	private String time;		// 月度
	private Integer idleroll;		// 无功总量
	private Integer antiReactivePower;		// 反无功总量
	private Integer lastbase;		// 上月表底
	private Integer nowbase;		// 本月表底
	private Integer days;		// 天数
	private Integer lastpower;		// 上月用电量
	private Integer abversionpoe;		// 外转供电
	private Integer demand;		// 需量
	private String insertFlag;		// 插入途径:1:采集数据 2:手工录入
	
	public TmdlCollieryGroup() {
		super();
	}

	public TmdlCollieryGroup(String id){
		super(id);
	}

	@Length(min=0, max=255, message="单位ID长度必须介于 0 和 255 之间")
	public UserCompany getUnitId() {
		return unitId;
	}

	public void setUnitId(UserCompany unitId) {
		this.unitId = unitId;
	}

	public Integer getWattfulgross() {
		return wattfulgross;
	}

	public void setWattfulgross(Integer wattfulgross) {
		this.wattfulgross = wattfulgross;
	}

	public Integer getPeaksegment() {
		return peaksegment;
	}

	public void setPeaksegment(Integer peaksegment) {
		this.peaksegment = peaksegment;
	}

	public Integer getGrainsegment() {
		return grainsegment;
	}

	public void setGrainsegment(Integer grainsegment) {
		this.grainsegment = grainsegment;
	}

	public Integer getFlatsegment() {
		return flatsegment;
	}

	public void setFlatsegment(Integer flatsegment) {
		this.flatsegment = flatsegment;
	}

	public Integer getPluswatt() {
		return pluswatt;
	}

	public void setPluswatt(Integer pluswatt) {
		this.pluswatt = pluswatt;
	}

	public Integer getResewatt() {
		return resewatt;
	}

	public void setResewatt(Integer resewatt) {
		this.resewatt = resewatt;
	}

	public Integer getDemnum() {
		return demnum;
	}

	public void setDemnum(Integer demnum) {
		this.demnum = demnum;
	}


	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Integer getIdleroll() {
		return idleroll;
	}

	public void setIdleroll(Integer idleroll) {
		this.idleroll = idleroll;
	}

	public Integer getAntiReactivePower() {
		return antiReactivePower;
	}

	public void setAntiReactivePower(Integer antiReactivePower) {
		this.antiReactivePower = antiReactivePower;
	}

	public Integer getLastbase() {
		return lastbase;
	}

	public void setLastbase(Integer lastbase) {
		this.lastbase = lastbase;
	}

	public Integer getNowbase() {
		return nowbase;
	}

	public void setNowbase(Integer nowbase) {
		this.nowbase = nowbase;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}

	public Integer getLastpower() {
		return lastpower;
	}

	public void setLastpower(Integer lastpower) {
		this.lastpower = lastpower;
	}

	public Integer getAbversionpoe() {
		return abversionpoe;
	}

	public void setAbversionpoe(Integer abversionpoe) {
		this.abversionpoe = abversionpoe;
	}

	public Integer getDemand() {
		return demand;
	}

	public void setDemand(Integer demand) {
		this.demand = demand;
	}

	@Length(min=0, max=1, message="插入途径:1:采集数据 2:手工录入长度必须介于 0 和 1 之间")
	public String getInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(String insertFlag) {
		this.insertFlag = insertFlag;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}