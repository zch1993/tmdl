/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.readfail;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.readfail.Readfail;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.readfail.ReadfailDao;

/**
 * 读取失败Service
 * @author zhangch@umasoft
 * @version 2018-07-16
 */
@Service
@Transactional(readOnly = true)
public class ReadfailService extends CrudService<ReadfailDao, Readfail> {

	public Readfail get(String id) {
		return super.get(id);
	}
	
	public List<Readfail> findList(Readfail readfail) {
		return super.findList(readfail);
	}
	
	public Page<Readfail> findPage(Page<Readfail> page, Readfail readfail) {
		return super.findPage(page, readfail);
	}
	
	@Transactional(readOnly = false)
	public void save(Readfail readfail) {
		super.save(readfail);
	}
	
	@Transactional(readOnly = false)
	public void delete(Readfail readfail) {
		super.delete(readfail);
	}
	
}