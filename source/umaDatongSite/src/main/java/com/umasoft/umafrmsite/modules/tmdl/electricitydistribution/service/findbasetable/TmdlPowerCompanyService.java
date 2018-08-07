/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findbasetable;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findbasetable.TmdlPowerCompany;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findbasetable.TmdlPowerCompanyDao;

/**
 * 基础表查询Service
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Service
@Transactional(readOnly = true)
public class TmdlPowerCompanyService extends CrudService<TmdlPowerCompanyDao, TmdlPowerCompany> {
	public static final Logger LOGGER = LoggerFactory.getLogger(TmdlPowerCompanyService.class);
	@Autowired
	private TmdlPowerCompanyDao dao;
	public TmdlPowerCompany get(String id) {
		return super.get(id);
	}
	
	public List<TmdlPowerCompany> findList(TmdlPowerCompany tmdlPowerCompany) {
		return super.findList(tmdlPowerCompany);
	}
	
	public Page<TmdlPowerCompany> findPage(Page<TmdlPowerCompany> page, TmdlPowerCompany tmdlPowerCompany) {
		return super.findPage(page, tmdlPowerCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlPowerCompany tmdlPowerCompany) {
		super.save(tmdlPowerCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlPowerCompany tmdlPowerCompany) {
		super.delete(tmdlPowerCompany);
	}


	public void dataSynchro(){

	List<TmdlPowerCompany> companys=	dao.findCompany();

	for (TmdlPowerCompany list:companys){
		LOGGER.info("unitId"+list.getUnitId().getId());
		super.save(list);

	}
	}
	
}