/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.service.companypsupplys;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.companypsupplys.TmdlUsePowerSupply;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.dao.companypsupplys.TmdlUsePowerSupplyDao;

/**
 * 录入供电情况Service
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class TmdlUsePowerSupplyService extends CrudService<TmdlUsePowerSupplyDao, TmdlUsePowerSupply> {

	public TmdlUsePowerSupply get(String id) {
		return super.get(id);
	}
	
	public List<TmdlUsePowerSupply> findList(TmdlUsePowerSupply tmdlUsePowerSupply) {
		return super.findList(tmdlUsePowerSupply);
	}
	
	public Page<TmdlUsePowerSupply> findPage(Page<TmdlUsePowerSupply> page, TmdlUsePowerSupply tmdlUsePowerSupply) {
		return super.findPage(page, tmdlUsePowerSupply);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlUsePowerSupply tmdlUsePowerSupply) {
		super.save(tmdlUsePowerSupply);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlUsePowerSupply tmdlUsePowerSupply) {
		super.delete(tmdlUsePowerSupply);
	}
	
}