/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.groupcompany;

import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

/**
 * 集团企业管理Entity
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
public class GroupCompany extends DataEntity<GroupCompany> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// 集团名称
	private Date createtime;		// createtime
	private Long power;		// 有功总量
	
	public GroupCompany() {
		super();
	}

	public GroupCompany(String id){
		super(id);
	}

	@Length(min=0, max=255, message="集团名称长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Long getPower() {
		return power;
	}

	public void setPower(Long power) {
		this.power = power;
	}

		
		
		
		
		
		
		
		
		
		
}