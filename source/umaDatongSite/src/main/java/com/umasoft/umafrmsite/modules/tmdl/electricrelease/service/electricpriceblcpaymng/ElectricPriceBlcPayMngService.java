/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.electricpriceblcpaymng;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpriceblcpaymng.ElectricPriceBlcPayMng;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.dao.electricpriceblcpaymng.ElectricPriceBlcPayMngDao;

/**
 * 电价管理Service
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class ElectricPriceBlcPayMngService extends CrudService<ElectricPriceBlcPayMngDao, ElectricPriceBlcPayMng> {

	public ElectricPriceBlcPayMng get(String id) {
		return super.get(id);
	}
	
	public List<ElectricPriceBlcPayMng> findList(ElectricPriceBlcPayMng ElectricPriceBlcPayMng) {
		return super.findList(ElectricPriceBlcPayMng);
	}
	
	public Page<ElectricPriceBlcPayMng> findPage(Page<ElectricPriceBlcPayMng> page, ElectricPriceBlcPayMng ElectricPriceBlcPayMng) {
		return super.findPage(page, ElectricPriceBlcPayMng);
	}
	
	@Transactional(readOnly = false)
	public void save(ElectricPriceBlcPayMng ElectricPriceBlcPayMng) {
		super.save(ElectricPriceBlcPayMng);
	}
	
	@Transactional(readOnly = false)
	public void delete(ElectricPriceBlcPayMng ElectricPriceBlcPayMng) {
		super.delete(ElectricPriceBlcPayMng);
	}
	
}