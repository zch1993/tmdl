/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.entity.metercopy;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

/**
 * 抄表本Entity
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
public class Metercopy extends DataEntity<Metercopy> {
	
	private static final long serialVersionUID = 1L;
	private String kgbh;		// 开关编号
	private String name;		// 路别名称
	private String bdzid;		// 变电站ID
	private String status;		// status
	private String gateproperty;		// gateproperty
	private String voltage;		// voltage
	private String segmentname;		// segmentname
	private Long bl;		// 倍率
	private Date addtime;		// addtime
	private String dljj;		// 电量加减
	private String xl1jj;		// 需量1加减
	private String xl2jj;		// 需量2加减
	private Long jsbs;		// 计算倍数
	private String unitType;		// 单位性质
	private Long unitId;		// 单位代码
	private String kgbh1;		// 开关编号1(线路编号)
	private Integer insertFlag;		// 1:手动抄表, 2:自动抄表
	
	public Metercopy() {
		super();
	}

	public Metercopy(String id){
		super(id);
	}

	@Length(min=0, max=255, message="开关编号长度必须介于 0 和 255 之间")
	public String getKgbh() {
		return kgbh;
	}

	public void setKgbh(String kgbh) {
		this.kgbh = kgbh;
	}

	@Length(min=0, max=255, message="路别名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=255, message="变电站ID长度必须介于 0 和 255 之间")
	public String getBdzid() {
		return bdzid;
	}

	public void setBdzid(String bdzid) {
		this.bdzid = bdzid;
	}

	@Length(min=0, max=255, message="status长度必须介于 0 和 255 之间")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Length(min=0, max=255, message="gateproperty长度必须介于 0 和 255 之间")
	public String getGateproperty() {
		return gateproperty;
	}

	public void setGateproperty(String gateproperty) {
		this.gateproperty = gateproperty;
	}

	@Length(min=0, max=255, message="voltage长度必须介于 0 和 255 之间")
	public String getVoltage() {
		return voltage;
	}

	public void setVoltage(String voltage) {
		this.voltage = voltage;
	}

	@Length(min=0, max=255, message="segmentname长度必须介于 0 和 255 之间")
	public String getSegmentname() {
		return segmentname;
	}

	public void setSegmentname(String segmentname) {
		this.segmentname = segmentname;
	}

	public Long getBl() {
		return bl;
	}

	public void setBl(Long bl) {
		this.bl = bl;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	@Length(min=0, max=255, message="电量加减长度必须介于 0 和 255 之间")
	public String getDljj() {
		return dljj;
	}

	public void setDljj(String dljj) {
		this.dljj = dljj;
	}

	@Length(min=0, max=255, message="需量1加减长度必须介于 0 和 255 之间")
	public String getXl1jj() {
		return xl1jj;
	}

	public void setXl1jj(String xl1jj) {
		this.xl1jj = xl1jj;
	}

	@Length(min=0, max=255, message="需量2加减长度必须介于 0 和 255 之间")
	public String getXl2jj() {
		return xl2jj;
	}

	public void setXl2jj(String xl2jj) {
		this.xl2jj = xl2jj;
	}

	public Long getJsbs() {
		return jsbs;
	}

	public void setJsbs(Long jsbs) {
		this.jsbs = jsbs;
	}

	@Length(min=0, max=255, message="单位性质长度必须介于 0 和 255 之间")
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public Long getUnitId() {
		return unitId;
	}

	public void setUnitId(Long unitId) {
		this.unitId = unitId;
	}

	@Length(min=0, max=255, message="开关编号1(线路编号)长度必须介于 0 和 255 之间")
	public String getKgbh1() {
		return kgbh1;
	}

	public void setKgbh1(String kgbh1) {
		this.kgbh1 = kgbh1;
	}

	public Integer getInsertFlag() {
		return insertFlag;
	}

	public void setInsertFlag(Integer insertFlag) {
		this.insertFlag = insertFlag;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}