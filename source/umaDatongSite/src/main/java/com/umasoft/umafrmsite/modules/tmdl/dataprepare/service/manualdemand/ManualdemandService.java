/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.manualdemand;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.manualdemand.Manualdemand;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.manualdemand.ManualdemandDao;

/**
 * 手工录入需量Service
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Service
@Transactional(readOnly = true)
public class ManualdemandService extends CrudService<ManualdemandDao, Manualdemand> {

	public Manualdemand get(String id) {
		return super.get(id);
	}
	
	public List<Manualdemand> findList(Manualdemand manualdemand) {
		return super.findList(manualdemand);
	}
	
	public Page<Manualdemand> findPage(Page<Manualdemand> page, Manualdemand manualdemand) {
		return super.findPage(page, manualdemand);
	}
	
	@Transactional(readOnly = false)
	public void save(Manualdemand manualdemand) {
		super.save(manualdemand);
	}
	
	@Transactional(readOnly = false)
	public void delete(Manualdemand manualdemand) {
		super.delete(manualdemand);
	}
	
}