/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection;

import javax.validation.constraints.NotNull;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import org.hibernate.validator.constraints.Length;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 表底采集Entity
 * @author zhangch@umasoft
 * @version 2018-07-13
 */
public class Bottomcollection extends DataEntity<Bottomcollection> {
	
	private static final long serialVersionUID = 1L;
	private TmdlPowerShunt shuntId;		// 分路ID
	private Double wattfulgross;		// 有功总量
	private Double peaksegment;		// 峰段总量
	private Double grainsegment;		// 谷段总量
	private Double flatsegment;		// 平段总量
	private Double pluswatt;		// 正有功总量
	private Double resewatt;		// 反有功总量
	private Double demnum;		// 需量表底
	private Double idleroll;		// 无功总量
	private Double antiReactivePower;		// 反无功总量
	private String time;		// 月度
	private Double lastbase;		// 上月表底
	private Double nowbase;		// 本月表底
	private Double days;		// 天数
	private Double lastpower;		// 上月用电量
	private Double abversionpoe;		// 外转供电
	private Double demand;		// 需量
	private String insertFlag;		// 插入途径:1:采集数据 2:手工录入
	
	public Bottomcollection() {
		super();
	}

	public Bottomcollection(String id){
		super(id);
	}

	@NotNull(message="分路ID不能为空")
	public TmdlPowerShunt getShuntId() {
		return shuntId;
	}

	public void setShuntId(TmdlPowerShunt shuntId) {
		this.shuntId = shuntId;
	}

	@NotNull(message="有功总量不能为空")
	public Double getWattfulgross() {
		return wattfulgross;
	}

	public void setWattfulgross(Double wattfulgross) {
		this.wattfulgross = wattfulgross;
	}

	@NotNull(message="峰段总量不能为空")
	public Double getPeaksegment() {
		return peaksegment;
	}

	public void setPeaksegment(Double peaksegment) {
		this.peaksegment = peaksegment;
	}

	@NotNull(message="谷段总量不能为空")
	public Double getGrainsegment() {
		return grainsegment;
	}

	public void setGrainsegment(Double grainsegment) {
		this.grainsegment = grainsegment;
	}

	@NotNull(message="平段总量不能为空")
	public Double getFlatsegment() {
		return flatsegment;
	}

	public void setFlatsegment(Double flatsegment) {
		this.flatsegment = flatsegment;
	}

	public Double getPluswatt() {
		return pluswatt;
	}

	public void setPluswatt(Double pluswatt) {
		this.pluswatt = pluswatt;
	}

	public Double getResewatt() {
		return resewatt;
	}

	public void setResewatt(Double resewatt) {
		this.resewatt = resewatt;
	}

	public Double getDemnum() {
		return demnum;
	}

	public void setDemnum(Double demnum) {
		this.demnum = demnum;
	}

	public Double getIdleroll() {
		return idleroll;
	}

	public void setIdleroll(Double idleroll) {
		this.idleroll = idleroll;
	}

	public Double getAntiReactivePower() {
		return antiReactivePower;
	}

	public void setAntiReactivePower(Double antiReactivePower) {
		this.antiReactivePower = antiReactivePower;
	}
   /* @DateTimeFormat(iso=DateTimeFormat.ISO.DATE,pattern = "")
	@JsonFormat(pattern = "yyyy-MM")*/
	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public Double getLastbase() {
		return lastbase;
	}

	public void setLastbase(Double lastbase) {
		this.lastbase = lastbase;
	}

	public Double getNowbase() {
		return nowbase;
	}

	public void setNowbase(Double nowbase) {
		this.nowbase = nowbase;
	}

	public Double getDays() {
		return days;
	}

	public void setDays(Double days) {
		this.days = days;
	}

	public Double getLastpower() {
		return lastpower;
	}

	public void setLastpower(Double lastpower) {
		this.lastpower = lastpower;
	}

	public Double getAbversionpoe() {
		return abversionpoe;
	}

	public void setAbversionpoe(Double abversionpoe) {
		this.abversionpoe = abversionpoe;
	}

	public Double getDemand() {
		return demand;
	}

	public void setDemand(Double demand) {
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