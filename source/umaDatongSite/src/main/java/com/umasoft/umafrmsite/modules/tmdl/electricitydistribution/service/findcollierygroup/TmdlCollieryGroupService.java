/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findcollierygroup;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findcollierygroup.TmdlCollieryGroup;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findcollierygroup.TmdlCollieryGroupDao;

/**
 * 煤业和集团查询Service
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Service
@Transactional(readOnly = true)
public class TmdlCollieryGroupService extends CrudService<TmdlCollieryGroupDao, TmdlCollieryGroup> {
	@Autowired
	private  TmdlCollieryGroupDao dao;
	public TmdlCollieryGroup get(String id,String time,String collieryname) {
		return dao.get(id,time,collieryname);
	}
	
	public List<TmdlCollieryGroup> findList(TmdlCollieryGroup tmdlCollieryGroup) {
		return super.findList(tmdlCollieryGroup);
	}
	
	public Page<TmdlCollieryGroup> findPage(Page<TmdlCollieryGroup> page, TmdlCollieryGroup tmdlCollieryGroup) {
		return super.findPage(page, tmdlCollieryGroup);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlCollieryGroup tmdlCollieryGroup) {
		super.save(tmdlCollieryGroup);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlCollieryGroup tmdlCollieryGroup) {
		super.delete(tmdlCollieryGroup);
	}
	
}