/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.service.globalpower;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.globalpower.TmdlUsePowerGlobal;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.dao.globalpower.TmdlUsePowerGlobalDao;

/**
 * 全局电量录入Service
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@Service
@Transactional(readOnly = true)
public class TmdlUsePowerGlobalService extends CrudService<TmdlUsePowerGlobalDao, TmdlUsePowerGlobal> {
	@Autowired
	private TmdlUsePowerGlobalDao dao;

	public TmdlUsePowerGlobal findbytime(String time){
		return dao.findbytime(time);
	}

	public TmdlUsePowerGlobal get(String id) {
		return super.get(id);
	}
	
	public List<TmdlUsePowerGlobal> findList(TmdlUsePowerGlobal tmdlUsePowerGlobal) {
		return super.findList(tmdlUsePowerGlobal);
	}
	
	public Page<TmdlUsePowerGlobal> findPage(Page<TmdlUsePowerGlobal> page, TmdlUsePowerGlobal tmdlUsePowerGlobal) {
		return super.findPage(page, tmdlUsePowerGlobal);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlUsePowerGlobal tmdlUsePowerGlobal) {
		super.save(tmdlUsePowerGlobal);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlUsePowerGlobal tmdlUsePowerGlobal) {
		super.delete(tmdlUsePowerGlobal);
	}
	
}