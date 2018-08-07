/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.service.openaccount;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.dao.openaccount.TmdlSystemBranchDao;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.openaccount.TmdlSystemBranch;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 开户管理Service
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class TmdlSystemBranchService extends CrudService<TmdlSystemBranchDao, TmdlSystemBranch> {

	public TmdlSystemBranch get(String id) {
		return super.get(id);
	}
	
	public List<TmdlSystemBranch> findList(TmdlSystemBranch tmdlSystemBranch) {
		return super.findList(tmdlSystemBranch);
	}
	
	public Page<TmdlSystemBranch> findPage(Page<TmdlSystemBranch> page, TmdlSystemBranch tmdlSystemBranch) {
		return super.findPage(page, tmdlSystemBranch);
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlSystemBranch tmdlSystemBranch) {
		super.save(tmdlSystemBranch);
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlSystemBranch tmdlSystemBranch) {
		super.delete(tmdlSystemBranch);
	}
	
}