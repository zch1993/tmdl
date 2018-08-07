/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.electricpricemng;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpricemng.ElectricPriceMng;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.dao.electricpricemng.ElectricPriceMngDao;

/**
 * 电价管理Service
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class ElectricPriceMngService extends CrudService<ElectricPriceMngDao, ElectricPriceMng> {

	public ElectricPriceMng get(String id) {
		return super.get(id);
	}
	
	public List<ElectricPriceMng> findList(ElectricPriceMng electricPriceMng) {
		return super.findList(electricPriceMng);
	}
	
	public Page<ElectricPriceMng> findPage(Page<ElectricPriceMng> page, ElectricPriceMng electricPriceMng) {
		return super.findPage(page, electricPriceMng);
	}
	
	@Transactional(readOnly = false)
	public void save(ElectricPriceMng electricPriceMng) {
		super.save(electricPriceMng);
	}
	
	@Transactional(readOnly = false)
	public void delete(ElectricPriceMng electricPriceMng) {
		super.delete(electricPriceMng);
	}
	
}