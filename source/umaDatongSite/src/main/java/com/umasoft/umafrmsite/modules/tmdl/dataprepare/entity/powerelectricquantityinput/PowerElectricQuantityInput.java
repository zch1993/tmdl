/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powerelectricquantityinput;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;

/**
 * 月度分路表底录入Entity
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
public class PowerElectricQuantityInput extends DataEntity<PowerElectricQuantityInput> {
	
	private static final long serialVersionUID = 1L;
	private TmdlPowerShunt shuntId;		// 分路ID
	private Integer wattfulgross;		// 有功总量
	private Integer peaksegment;		// 峰段总量
	private Integer grainsegment;		// 谷段总量
	private Integer flatsegment;		// 平段总量
	private Integer pluswatt;		// 正有功总量
	private Integer resewatt;		// 反有功总量
	private Integer demnum;		// 需量表底
	private Integer idleroll;		// 无功总量
	private Integer antiReactivePower;		// 反无功总量
	private Date time;		// 月度
	private Integer lastbase;		// 上月表底
	private Integer nowbase;		// 本月表底
	private Integer days;		// 天数
	private Integer lastpower;		// 上月用电量
	private Integer abversionpoe;		// 外转供电
	private Integer demand;		// 需量
	private String insertFlag;		// 插入途径:1:采集数据 2:手工录入
	
	private List<PowerElectricQuantityInput> inputList = null;
	public PowerElectricQuantityInput() {
		super();
	}

	public PowerElectricQuantityInput(String id){
		super(id);
	}


	public TmdlPowerShunt getShuntId() {
		return shuntId;
	}

	public void setShuntId(TmdlPowerShunt shuntId) {
		this.shuntId = shuntId;
	}

	@NotNull(message="有功总量不能为空")
	public Integer getWattfulgross() {
		return wattfulgross;
	}

	public void setWattfulgross(Integer wattfulgross) {
		this.wattfulgross = wattfulgross;
	}

	@NotNull(message="峰段总量不能为空")
	public Integer getPeaksegment() {
		return peaksegment;
	}

	public void setPeaksegment(Integer peaksegment) {
		this.peaksegment = peaksegment;
	}

	@NotNull(message="谷段总量不能为空")
	public Integer getGrainsegment() {
		return grainsegment;
	}

	public void setGrainsegment(Integer grainsegment) {
		this.grainsegment = grainsegment;
	}

	@NotNull(message="平段总量不能为空")
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

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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

	public List<PowerElectricQuantityInput> getInputList() {
		return inputList;
	}

	public void setInputList(List<PowerElectricQuantityInput> inputList) {
		this.inputList = inputList;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}