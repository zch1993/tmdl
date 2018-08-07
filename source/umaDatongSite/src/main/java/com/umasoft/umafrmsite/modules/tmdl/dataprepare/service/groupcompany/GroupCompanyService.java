/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.groupcompany;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.groupcompany.GroupCompany;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.groupcompany.GroupCompanyDao;

/**
 * 集团企业管理Service
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class GroupCompanyService extends CrudService<GroupCompanyDao, GroupCompany> {

	public GroupCompany get(String id) {
		return super.get(id);
	}
	
	public List<GroupCompany> findList(GroupCompany groupCompany) {
		return super.findList(groupCompany);
	}
	
	public Page<GroupCompany> findPage(Page<GroupCompany> page, GroupCompany groupCompany) {
		return super.findPage(page, groupCompany);
	}
	
	@Transactional(readOnly = false)
	public void save(GroupCompany groupCompany) {
		super.save(groupCompany);
	}
	
	@Transactional(readOnly = false)
	public void delete(GroupCompany groupCompany) {
		super.delete(groupCompany);
	}
	
}