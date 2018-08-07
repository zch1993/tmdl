/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.usercompanyelectsequence;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.usercompanyelectsequence.UserCompanyElectSequence;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.dao.usercompanyelectsequence.UserCompanyElectSequenceDao;

/**
 * 用电序列Service
 * @author zhangky@umasoft
 * @version 2018-07-08
 */
@Service
@Transactional(readOnly = true)
public class UserCompanyElectSequenceService extends CrudService<UserCompanyElectSequenceDao, UserCompanyElectSequence> {

	public UserCompanyElectSequence get(String id) {
		return super.get(id);
	}
	
	public List<UserCompanyElectSequence> findList(UserCompanyElectSequence userCompanyElectSequence) {
		return super.findList(userCompanyElectSequence);
	}
	
	public Page<UserCompanyElectSequence> findPage(Page<UserCompanyElectSequence> page, UserCompanyElectSequence userCompanyElectSequence) {
		return super.findPage(page, userCompanyElectSequence);
	}
	
	@Transactional(readOnly = false)
	public void save(UserCompanyElectSequence userCompanyElectSequence) {
		super.save(userCompanyElectSequence);
	}
	
	@Transactional(readOnly = false)
	public void delete(UserCompanyElectSequence userCompanyElectSequence) {
		super.delete(userCompanyElectSequence);
	}
	
}