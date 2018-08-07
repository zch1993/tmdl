/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;

/**
 * 变电站管理Entity
 * @author zhangky@umasoft
 * @version 2018-07-05
 */
public class TmdlSubstation extends DataEntity<TmdlSubstation> {
	
	private static final long serialVersionUID = 1L;
	private String code;		// 变电站编号
	private String name;		// 变电站名称
	private Date addtime;		// 添加时间
	private Date changetime;		// 修改时间
	//private String interfaceid;		//接口Id
	private String address;		//地址位置
	private String typeid;		//厂站类型
	//private String companyid;		//单位编号
	private String voltageid;		//电压等级
	private String shortening;		//简称
	private String isexaminedobject;		//是否考核  1考核 2不考核
	private String isphysicaldevices;		//物理设备标志 1 是 2 否

	
	private String searchCompany; // 公司名称，用来搜索外联
	
	private List<SubstationCompanyRelation> substationCompanyRelatioList;
	
	public TmdlSubstation() {
		super();
	}

	public TmdlSubstation(String id){
		super(id);
	}

	@Length(min=0, max=60, message="变电站编号长度必须介于 0 和 60 之间")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Length(min=0, max=100, message="变电站名称长度必须介于 0 和 100 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getVoltageid() {
		return voltageid;
	}

	public void setVoltageid(String voltageid) {
		this.voltageid = voltageid;
	}

	public String getShortening() {
		return shortening;
	}

	public void setShortening(String shortening) {
		this.shortening = shortening;
	}

	public String getIsexaminedobject() {
		return isexaminedobject;
	}

	public void setIsexaminedobject(String isexaminedobject) {
		this.isexaminedobject = isexaminedobject;
	}

	public String getIsphysicaldevices() {
		return isphysicaldevices;
	}

	public void setIsphysicaldevices(String isphysicaldevices) {
		this.isphysicaldevices = isphysicaldevices;
	}

	public List<SubstationCompanyRelation> getSubstationCompanyRelatioList() {
		return substationCompanyRelatioList;
	}

	public void setSubstationCompanyRelatioList(List<SubstationCompanyRelation> substationCompanyRelatioList) {
		this.substationCompanyRelatioList = substationCompanyRelatioList;
	}

	public String getSearchCompany() {
		return searchCompany;
	}

	public void setSearchCompany(String searchCompany) {
		this.searchCompany = searchCompany;
	}

		
		
		
		
		
		
		
		
		
		
		
}