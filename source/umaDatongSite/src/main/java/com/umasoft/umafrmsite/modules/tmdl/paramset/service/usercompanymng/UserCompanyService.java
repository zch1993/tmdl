/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.SystemCollieryGroup;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.usercompanymng.SystemCollieryGroupDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.usercompanymng.UserCompanyDao;

/**
 * 用电用户管理Service
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Service
@Transactional(readOnly = true)
public class UserCompanyService extends CrudService<UserCompanyDao, UserCompany> {

	@Autowired
	SystemCollieryGroupDao systemCollieryGroupDao;
	
	public UserCompany get(String id) {
		//return super.get(id);
		UserCompany userCompany = super.get(id);
		userCompany.setCgList(systemCollieryGroupDao.findList(new SystemCollieryGroup(userCompany)));
		return userCompany;
	}
	
	public List<UserCompany> findList(UserCompany userCompany) {
		//return super.findList(userCompany);
		List<UserCompany> list = super.findList(userCompany);
		int iindex = 0;
		userCompany.setName("");
		for(UserCompany ts : list) {
			if(ts.getIsratio() != null && "1".equals(ts.getIsratio())){
				userCompany.setId(ts.getId());
				ts.setCgList(systemCollieryGroupDao.findList(new SystemCollieryGroup(userCompany)));
				list.set(iindex, ts);
			}
			iindex ++ ;
		}
		return list;
	}
	
	public Page<UserCompany> findPage(Page<UserCompany> page, UserCompany userCompany) {
		//return super.findPage(page, userCompany);
		Page<UserCompany> tspage = super.findPage(page, userCompany);
		List<UserCompany> list = tspage.getList();
		int iindex = 0;
		userCompany.setName("");
		for(UserCompany ts : list) {
			if(ts.getIsratio() != null && "1".equals(ts.getIsratio())){
				userCompany.setId(ts.getId());
				ts.setCgList(systemCollieryGroupDao.findList(new SystemCollieryGroup(userCompany)));
				list.set(iindex, ts);
			}
			iindex ++ ;
		}
		tspage.setList(list);
		return tspage;
	}
	
	@Transactional(readOnly = false)
	public void save(UserCompany userCompany) {
		super.save(userCompany);
		
		for (SystemCollieryGroup ts : userCompany.getCgList()){
			if(ts.getUnitid() == null)continue;
			
			if (ts.DEL_FLAG_NORMAL.equals(ts.getDelFlag()) && "1".equals(userCompany.getIsratio())){
				if (StringUtils.isBlank(ts.getId())){
					ts.setUnitid(userCompany);
					ts.preInsert();
					systemCollieryGroupDao.insert(ts);
				}else{
					ts.setUnitid(userCompany);
					ts.preUpdate();
					systemCollieryGroupDao.update(ts);
				}
			}else{
				systemCollieryGroupDao.delete(ts);
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(UserCompany userCompany) {
		super.delete(userCompany);
	}
	
	public int getUserCompanyCount() {
		int lcount  = dao.getCount();
		return lcount;
	}
}