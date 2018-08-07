/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.companypsupplys;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;

/**
 * 录入供电情况Entity
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
public class TmdlUsePowerSupply extends DataEntity<TmdlUsePowerSupply> {
	
	private static final long serialVersionUID = 1L;

	private UserCompany sysid;		// 单位代码
	private Date time;		// 日期
	private Date begintime;		// 开始时间
	private Date endtime;		// 结束时间
	private Integer srdl;		// 收入电量
	private Integer loser;		// 局外转供电量
	private Integer council;		// 局内转供电量
	private Integer coal;		// 原煤生产
	private Integer build;		// 基本建设
	private Integer notcoal;		// 非原煤生产
	private Integer notproduce;		// 非生产部门
	private Integer llife;		// 生活用电
	private Integer allcoal;		// 原煤总产量
	private Integer allseep;		// 矿井加漏天产量
	private Integer maxload;		// 最大负荷
	private Integer meanload;		// 平均负荷
	private Integer loadrate;		// 负荷率
	private Integer pf;		// 功率因数
	private Integer lljf;		// 利率奖罚
	private Integer wg;		// 无功电量
	private Integer xbs;		// 线变损


	
	public TmdlUsePowerSupply() {
		super();
	}


	public TmdlUsePowerSupply(String id){
		super(id);
	}



	public UserCompany getSysid() {
		return sysid;
	}

	public void setSysid(UserCompany sysid) {
		this.sysid = sysid;
	}

	@JsonFormat(pattern = "yyyy-MM")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getBegintime() {
		return begintime;
	}

	public void setBegintime(Date begintime) {
		this.begintime = begintime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd")
	public Date getEndtime() {
		return endtime;
	}

	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}

	public Integer getSrdl() {
		return srdl;
	}

	public void setSrdl(Integer srdl) {
		this.srdl = srdl;
	}

	public Integer getLoser() {
		return loser;
	}

	public void setLoser(Integer loser) {
		this.loser = loser;
	}

	public Integer getCouncil() {
		return council;
	}

	public void setCouncil(Integer council) {
		this.council = council;
	}

	public Integer getCoal() {
		return coal;
	}

	public void setCoal(Integer coal) {
		this.coal = coal;
	}

	public Integer getBuild() {
		return build;
	}

	public void setBuild(Integer build) {
		this.build = build;
	}

	public Integer getNotcoal() {
		return notcoal;
	}

	public void setNotcoal(Integer notcoal) {
		this.notcoal = notcoal;
	}

	public Integer getNotproduce() {
		return notproduce;
	}

	public void setNotproduce(Integer notproduce) {
		this.notproduce = notproduce;
	}

	public Integer getLlife() {
		return llife;
	}

	public void setLlife(Integer llife) {
		this.llife = llife;
	}

	public Integer getAllcoal() {
		return allcoal;
	}

	public void setAllcoal(Integer allcoal) {
		this.allcoal = allcoal;
	}

	public Integer getAllseep() {
		return allseep;
	}

	public void setAllseep(Integer allseep) {
		this.allseep = allseep;
	}

	public Integer getMaxload() {
		return maxload;
	}

	public void setMaxload(Integer maxload) {
		this.maxload = maxload;
	}

	public Integer getMeanload() {
		return meanload;
	}

	public void setMeanload(Integer meanload) {
		this.meanload = meanload;
	}

	public Integer getLoadrate() {
		return loadrate;
	}

	public void setLoadrate(Integer loadrate) {
		this.loadrate = loadrate;
	}

	public Integer getPf() {
		return pf;
	}

	public void setPf(Integer pf) {
		this.pf = pf;
	}

	public Integer getLljf() {
		return lljf;
	}

	public void setLljf(Integer lljf) {
		this.lljf = lljf;
	}

	public Integer getWg() {
		return wg;
	}

	public void setWg(Integer wg) {
		this.wg = wg;
	}

	public Integer getXbs() {
		return xbs;
	}

	public void setXbs(Integer xbs) {
		this.xbs = xbs;
	}





}