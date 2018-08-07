/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findaccounting;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findaccounting.Tmdlaccounting;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findaccounting.TmdlaccountingDao;

/**
 * 核算表查询Service
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class TmdlaccountingService extends CrudService<TmdlaccountingDao, Tmdlaccounting> {

	public Tmdlaccounting get(String id) {
		return super.get(id);
	}
	
	public List<Tmdlaccounting> findList(Tmdlaccounting tmdlaccounting) {
		return super.findList(tmdlaccounting);
	}
	
	public Page<Tmdlaccounting> findPage(Page<Tmdlaccounting> page, Tmdlaccounting tmdlaccounting) {
		return super.findPage(page, tmdlaccounting);
	}
	
	@Transactional(readOnly = false)
	public void save(Tmdlaccounting tmdlaccounting) {
		super.save(tmdlaccounting);
	}
	
	@Transactional(readOnly = false)
	public void delete(Tmdlaccounting tmdlaccounting) {
		super.delete(tmdlaccounting);
	}
	
}