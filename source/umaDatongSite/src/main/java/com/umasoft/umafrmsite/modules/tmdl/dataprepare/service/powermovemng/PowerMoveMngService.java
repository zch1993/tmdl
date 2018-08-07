/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.powermovemng;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powermovemng.PowerMoveMng;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.powermovemng.PowerMoveMngDao;

/**
 * 转入转出电量录入Service
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
@Service
@Transactional(readOnly = true)
public class PowerMoveMngService extends CrudService<PowerMoveMngDao, PowerMoveMng> {

	public PowerMoveMng get(String id) {
		return super.get(id);
	}
	
	public List<PowerMoveMng> findList(PowerMoveMng powerMoveMng) {
		return super.findList(powerMoveMng);
	}
	
	public Page<PowerMoveMng> findPage(Page<PowerMoveMng> page, PowerMoveMng powerMoveMng) {
		return super.findPage(page, powerMoveMng);
	}
	
	@Transactional(readOnly = false)
	public void save(PowerMoveMng powerMoveMng) {
		super.save(powerMoveMng);
	}
	
	@Transactional(readOnly = false)
	public void delete(PowerMoveMng powerMoveMng) {
		super.delete(powerMoveMng);
	}
	
}