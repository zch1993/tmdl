/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.powerelectricquantityinput;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powerelectricquantityinput.PowerElectricQuantityInput;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.powerelectricquantityinput.PowerElectricQuantityInputDao;

/**
 * 月度分路表底录入Service
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
@Service
@Transactional(readOnly = true)
public class PowerElectricQuantityInputService extends CrudService<PowerElectricQuantityInputDao, PowerElectricQuantityInput> {

	public PowerElectricQuantityInput get(String id) {
		return super.get(id);
	}
	
	public List<PowerElectricQuantityInput> findList(PowerElectricQuantityInput powerElectricQuantityInput) {
		return super.findList(powerElectricQuantityInput);
	}
	
	public Page<PowerElectricQuantityInput> findPage(Page<PowerElectricQuantityInput> page, PowerElectricQuantityInput powerElectricQuantityInput) {
		return super.findPage(page, powerElectricQuantityInput);
	}
	
	@Transactional(readOnly = false)
	public void save(PowerElectricQuantityInput powerElectricQuantityInput) {
		super.save(powerElectricQuantityInput);
	}
	
	@Transactional(readOnly = false)
	public void delete(PowerElectricQuantityInput powerElectricQuantityInput) {
		super.delete(powerElectricQuantityInput);
	}
	
}