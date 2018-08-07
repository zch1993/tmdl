/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.usercompanyelectsequence;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.groupcompany.GroupCompany;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;

/**
 * 用电序列Entity
 * @author zhangky@umasoft
 * @version 2018-07-08
 */
public class UserCompanyElectSequence extends DataEntity<UserCompanyElectSequence> {
	
	private static final long serialVersionUID = 1L;
	private GroupCompany colgroupid;		// 煤矿集团
	private TmdlSubstation bdzid;		// 变电站
	private String code;		// 用电单位编号
	private String name;		// 用电单位名称
	private String companyNo;		// 企业代码
	private Long standardIllumination;		// 标准照明电量
	private Long powercode;		// 电量序号
	private Long ueleccode;		// 用电序号
	private Long costcategory;		// 电费类别
	private Double ratio;		// 比例
	private Long tollmethod;		// 银行托收
	private Date addtime;		// 添加时间
	private Date changetime;		// 修改时间
	private String isratio;		// 是否使用分配比例
	private Long unitSort;		// unit_sort
	private Double zmyd;		// 月度总共
	private Double sl;		// 税率
	private String sfcyjs;		// 是否参与计算
	
	public UserCompanyElectSequence() {
		super();
	}

	public UserCompanyElectSequence(String id){
		super(id);
	}

	public GroupCompany getColgroupid() {
		return colgroupid;
	}

	public void setColgroupid(GroupCompany colgroupid) {
		this.colgroupid = colgroupid;
	}

	public TmdlSubstation getBdzid() {
		return bdzid;
	}

	public void setBdzid(TmdlSubstation bdzid) {
		this.bdzid = bdzid;
	}

	@Length(min=0, max=65, message="用电单位编号长度必须介于 0 和 65 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Length(min=0, max=200, message="用电单位名称长度必须介于 0 和 200 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Length(min=0, max=60, message="企业代码长度必须介于 0 和 60 之间")
	public String getCompanyNo() {
		return companyNo;
	}

	public void setCompanyNo(String companyNo) {
		this.companyNo = companyNo;
	}

	public Long getStandardIllumination() {
		return standardIllumination;
	}

	public void setStandardIllumination(Long standardIllumination) {
		this.standardIllumination = standardIllumination;
	}

	public Long getPowercode() {
		return powercode;
	}

	public void setPowercode(Long powercode) {
		this.powercode = powercode;
	}

	public Long getUeleccode() {
		return ueleccode;
	}

	public void setUeleccode(Long ueleccode) {
		this.ueleccode = ueleccode;
	}

	public Long getCostcategory() {
		return costcategory;
	}

	public void setCostcategory(Long costcategory) {
		this.costcategory = costcategory;
	}

	public Double getRatio() {
		return ratio;
	}

	public void setRatio(Double ratio) {
		this.ratio = ratio;
	}

	public Long getTollmethod() {
		return tollmethod;
	}

	public void setTollmethod(Long tollmethod) {
		this.tollmethod = tollmethod;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getChangetime() {
		return changetime;
	}

	public void setChangetime(Date changetime) {
		this.changetime = changetime;
	}

	@Length(min=0, max=2, message="是否使用分配比例长度必须介于 0 和 2 之间")
	public String getIsratio() {
		return isratio;
	}

	public void setIsratio(String isratio) {
		this.isratio = isratio;
	}

	public Long getUnitSort() {
		return unitSort;
	}

	public void setUnitSort(Long unitSort) {
		this.unitSort = unitSort;
	}

	public Double getZmyd() {
		return zmyd;
	}

	public void setZmyd(Double zmyd) {
		this.zmyd = zmyd;
	}

	public Double getSl() {
		return sl;
	}

	public void setSl(Double sl) {
		this.sl = sl;
	}

	@Length(min=0, max=5, message="是否参与计算长度必须介于 0 和 5 之间")
	public String getSfcyjs() {
		return sfcyjs;
	}

	public void setSfcyjs(String sfcyjs) {
		this.sfcyjs = sfcyjs;
	}

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}