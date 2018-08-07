/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.service.branchmnagement;


import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.dao.branchmnagement.TmdlPowerShuntDao;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * 分路管理Service
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class TmdlPowerShuntService extends CrudService<TmdlPowerShuntDao, TmdlPowerShunt> {
	@Autowired
	private TmdlPowerShuntDao dao;

	public static final Logger LOGGER = LoggerFactory.getLogger(TmdlPowerShuntService.class);
	@Autowired
	private TmdlPowerShuntDao PowerShunt;

	public TmdlPowerShunt get(String id) {
		return super.get(id);
	}
	
	public List<TmdlPowerShunt> findList(TmdlPowerShunt tmdlPowerShunt) {
		return super.findList(tmdlPowerShunt);
	}
	
	public Page<TmdlPowerShunt> findPage(Page<TmdlPowerShunt> page, TmdlPowerShunt tmdlPowerShunt) {
		return super.findPage(page, tmdlPowerShunt);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlPowerShunt tmdlPowerShunt) {
		super.save(tmdlPowerShunt);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlPowerShunt tmdlPowerShunt) {
		super.delete(tmdlPowerShunt);
	}


	public void dataSynchrobyFL(){
		Integer count=PowerShunt.count();
		List <TmdlPowerShunt>PowerShuntList= PowerShunt.dataSynchrobyFL(count);
		for (TmdlPowerShunt TmdlPowerShunt:PowerShuntList) {
			super.save(TmdlPowerShunt);
			LOGGER.info("id="+TmdlPowerShunt.getId());

			
		}

	}


	public Page<TmdlPowerShunt> findallData(Page<TmdlPowerShunt> page, TmdlPowerShunt tmdlPowerShunt) {
		tmdlPowerShunt.setPage(page);
		List<TmdlPowerShunt> list = dao.findallData(tmdlPowerShunt);
		page.setList(list);
		return page;
	}


	
}