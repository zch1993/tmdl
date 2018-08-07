/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.globalpower;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotNull;

import com.umasoft.umafrmsite.common.persistence.DataEntity;

import java.util.Date;

/**
 * 全局电量录入Entity
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
public class TmdlUsePowerGlobal extends DataEntity<TmdlUsePowerGlobal> {
	
	private static final long serialVersionUID = 1L;
	private Date time;		// 用电年月
	private Integer globalElectricity;		// 全局收入电量
	private Integer maxload;		// 最大负荷
	private Integer meanload;		// 平均负荷
	private Integer loadrate;		// 负荷率
	
	public TmdlUsePowerGlobal() {
		super();
	}

	public TmdlUsePowerGlobal(String id){
		super(id);
	}

	@JsonFormat(pattern = "yyyy-MM")
	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@NotNull(message="全局收入电量不能为空")
	public Integer getGlobalElectricity() {
		return globalElectricity;
	}

	public void setGlobalElectricity(Integer globalElectricity) {
		this.globalElectricity = globalElectricity;
	}

	@NotNull(message="最大负荷不能为空")
	public Integer getMaxload() {
		return maxload;
	}

	public void setMaxload(Integer maxload) {
		this.maxload = maxload;
	}

	@NotNull(message="平均负荷不能为空")
	public Integer getMeanload() {
		return meanload;
	}

	public void setMeanload(Integer meanload) {
		this.meanload = meanload;
	}

	@NotNull(message="负荷率不能为空")
	public Integer getLoadrate() {
		return loadrate;
	}

	public void setLoadrate(Integer loadrate) {
		this.loadrate = loadrate;
	}

		
		
		
		
		
		
		
		
		
		
		
		
}