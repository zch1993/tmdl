/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.syssubstationmng;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.syssubstationmng.PowerShunt;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.syssubstationmng.PowerShuntDao;

/**
 * 超表本管理Service
 * @author zhangky@umasoft
 * @version 2018-07-03
 */
@Service
@Transactional(readOnly = true)
public class PowerShuntService extends CrudService<PowerShuntDao, PowerShunt> {

	public PowerShunt get(String id) {
		return super.get(id);
	}
	
	public List<PowerShunt> findList(PowerShunt powerShunt) {
		return super.findList(powerShunt);
	}
	
	public Page<PowerShunt> findPage(Page<PowerShunt> page, PowerShunt powerShunt) {
		return super.findPage(page, powerShunt);
	}
	
	@Transactional(readOnly = false)
	public void save(PowerShunt powerShunt) {
		super.save(powerShunt);
	}
	
	@Transactional(readOnly = false)
	public void delete(PowerShunt powerShunt) {
		super.delete(powerShunt);
	}
	
}