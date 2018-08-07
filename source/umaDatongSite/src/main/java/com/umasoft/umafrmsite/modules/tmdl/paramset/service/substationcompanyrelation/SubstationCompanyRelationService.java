/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationcompanyrelation;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationcompanyrelation.SubstationCompanyRelationDao;

/**
 * 变电站和用电单位对应关系Service
 * @author zhangky@umasoft
 * @version 2018-07-11
 */
@Service
@Transactional(readOnly = true)
public class SubstationCompanyRelationService extends CrudService<SubstationCompanyRelationDao, SubstationCompanyRelation> {

	public SubstationCompanyRelation get(String id) {
		SubstationCompanyRelation substationCompanyRelation= super.get(id);
		
		return substationCompanyRelation;
	}
	
	public List<SubstationCompanyRelation> findList(SubstationCompanyRelation substationCompanyRelation) {
		return super.findList(substationCompanyRelation);
	}
	
	public Page<SubstationCompanyRelation> findPage(Page<SubstationCompanyRelation> page, SubstationCompanyRelation substationCompanyRelation) {
		return super.findPage(page, substationCompanyRelation);
	}
	
	@Transactional(readOnly = false)
	public void save(SubstationCompanyRelation substationCompanyRelation) {
		super.save(substationCompanyRelation);
	}
	
	@Transactional(readOnly = false)
	public void delete(SubstationCompanyRelation substationCompanyRelation) {
		super.delete(substationCompanyRelation);
	}
	
}