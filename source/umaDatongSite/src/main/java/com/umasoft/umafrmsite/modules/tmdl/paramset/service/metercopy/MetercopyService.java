/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.metercopy;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.metercopy.Metercopy;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.metercopy.MetercopyDao;

/**
 * 抄表本Service
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@Service
@Transactional(readOnly = true)
public class MetercopyService extends CrudService<MetercopyDao, Metercopy> {

	public Metercopy get(String id) {
		return super.get(id);
	}
	
	public List<Metercopy> findList(Metercopy metercopy) {
		return super.findList(metercopy);
	}
	
	public Page<Metercopy> findPage(Page<Metercopy> page, Metercopy metercopy) {
		return super.findPage(page, metercopy);
	}
	
	@Transactional(readOnly = false)
	public void save(Metercopy metercopy) {
		super.save(metercopy);
	}
	
	@Transactional(readOnly = false)
	public void delete(Metercopy metercopy) {
		super.delete(metercopy);
	}
	
}