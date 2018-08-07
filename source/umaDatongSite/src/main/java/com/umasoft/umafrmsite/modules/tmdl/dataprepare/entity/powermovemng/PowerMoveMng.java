/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powermovemng;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;

/**
 * 转入转出电量录入Entity
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
public class PowerMoveMng extends DataEntity<PowerMoveMng> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitid;		// 单位ID
	private Date createtime;		// 转入转出日期
	private Double ygzr;		// 有功转入
	private Double ygzc;		// 有功转出
	private Double wgzr;		// 无功转入
	private Double wgzc;		// 无功转出
	private Double xlzj;		// 需量增减
	
	public PowerMoveMng() {
		super();
	}

	public PowerMoveMng(String id){
		super(id);
	}

	public UserCompany getUnitid() {
		return unitid;
	}

	public void setUnitid(UserCompany unitid) {
		this.unitid = unitid;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Double getYgzr() {
		return ygzr;
	}

	public void setYgzr(Double ygzr) {
		this.ygzr = ygzr;
	}

	public Double getYgzc() {
		return ygzc;
	}

	public void setYgzc(Double ygzc) {
		this.ygzc = ygzc;
	}

	public Double getWgzr() {
		return wgzr;
	}

	public void setWgzr(Double wgzr) {
		this.wgzr = wgzr;
	}

	public Double getWgzc() {
		return wgzc;
	}

	public void setWgzc(Double wgzc) {
		this.wgzc = wgzc;
	}

	public Double getXlzj() {
		return xlzj;
	}

	public void setXlzj(Double xlzj) {
		this.xlzj = xlzj;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
}