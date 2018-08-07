/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.demandmanagement;

import java.util.List;
import java.util.Map;

import com.umasoft.umafrmsite.modules.tmdl.oamanagement.dao.branchmnagement.TmdlPowerShuntDao;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.demandmanagement.TmdlPowerDemand;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.demandmanagement.TmdlPowerDemandDao;

/**
 * 需量管理Service
 * @author zhangch@umasoft
 * @version 2018-07-09
 */
@Service
@Transactional(readOnly = true)
public class TmdlPowerDemandService extends CrudService<TmdlPowerDemandDao, TmdlPowerDemand> {
	@Autowired
	private TmdlPowerDemandDao dao;

    @Autowired
    private TmdlPowerShuntDao fldao;
	public TmdlPowerDemand get(String id) {
		return super.get(id);
	}
	
	public List<TmdlPowerDemand> findList(TmdlPowerDemand tmdlPowerDemand) {
		return super.findList(tmdlPowerDemand);
	}
	
	public Page<TmdlPowerDemand> findPage(Page<TmdlPowerDemand> page, TmdlPowerDemand tmdlPowerDemand) {
		return super.findPage(page, tmdlPowerDemand);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlPowerDemand tmdlPowerDemand) {
		super.save(tmdlPowerDemand);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlPowerDemand tmdlPowerDemand) {
		super.delete(tmdlPowerDemand);
	}

	public Map<String,String> findfl(String id){
		return fldao.findfl(id);
	}
}