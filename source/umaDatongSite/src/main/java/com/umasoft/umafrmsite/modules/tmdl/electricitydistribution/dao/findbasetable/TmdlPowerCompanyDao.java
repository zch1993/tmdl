/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findbasetable;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findbasetable.TmdlPowerCompany;

import java.util.List;

/**
 * 基础表查询DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@MyBatisDao
public interface TmdlPowerCompanyDao extends CrudDao<TmdlPowerCompany> {

    public List<TmdlPowerCompany> findCompany();


	
}