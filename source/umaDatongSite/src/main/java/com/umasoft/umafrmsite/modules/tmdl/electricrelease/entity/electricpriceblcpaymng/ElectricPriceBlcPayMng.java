/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpriceblcpaymng;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;

/**
 * 电价管理Entity
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
public class ElectricPriceBlcPayMng extends DataEntity<ElectricPriceBlcPayMng> {
	
	private static final long serialVersionUID = 1L;
	private UserCompany unitid;		// 单位表_单位ID
	private Double pingp;		// 平段用电单价
	private Double fengp;		// 峰段用电单价
	private Double gup;		// 谷段用电单价
	private Double dlp;		// 动力用电单价
	private Double zmp;		// 照明单价
	private Double xlp;		// 需量单价
	private Double wzgp;		// 外转供电价
	private Double taxrate;		// 税率
	private Date addtime;		// 添加日期
	private Date changedtime;		// 修改日期
	private String username;		// 操作员
	private Double sl;		// 税率
	private Double flatprice;		// 平价
	private int getprice = 1;  //1查询电价、2设置电价、3查询所有电价
	
	public ElectricPriceBlcPayMng() {
		super();
	}

	public ElectricPriceBlcPayMng(String id){
		super(id);
	}


	public UserCompany getUnitid() {
		return unitid;
	}

	public void setUnitid(UserCompany unitid) {
		this.unitid = unitid;
	}
	
	public Double getPingp() {
		return pingp;
	}

	public void setPingp(Double pingp) {
		this.pingp = pingp;
	}

	public Double getFengp() {
		return fengp;
	}

	public void setFengp(Double fengp) {
		this.fengp = fengp;
	}

	public Double getGup() {
		return gup;
	}

	public void setGup(Double gup) {
		this.gup = gup;
	}

	public Double getDlp() {
		return dlp;
	}

	public void setDlp(Double dlp) {
		this.dlp = dlp;
	}

	public Double getZmp() {
		return zmp;
	}

	public void setZmp(Double zmp) {
		this.zmp = zmp;
	}

	public Double getXlp() {
		return xlp;
	}

	public void setXlp(Double xlp) {
		this.xlp = xlp;
	}

	public Double getWzgp() {
		return wzgp;
	}

	public void setWzgp(Double wzgp) {
		this.wzgp = wzgp;
	}

	public Double getTaxrate() {
		return taxrate;
	}

	public void setTaxrate(Double taxrate) {
		this.taxrate = taxrate;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChangedtime() {
		return changedtime;
	}

	public void setChangedtime(Date changedtime) {
		this.changedtime = changedtime;
	}

	@Length(min=0, max=50, message="操作员长度必须介于 0 和 50 之间")
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	public Double getFlatprice() {
		return flatprice;
	}

	public void setFlatprice(Double flatprice) {
		this.flatprice = flatprice;
	}

	public int getGetprice() {
		
		return getprice;
	}

	public void setGetprice(int getprice) {
		this.getprice = getprice;
		//if("1".equals(getprice)  && addtime == null) {
		//	addtime = new Date(); 
		//}
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}