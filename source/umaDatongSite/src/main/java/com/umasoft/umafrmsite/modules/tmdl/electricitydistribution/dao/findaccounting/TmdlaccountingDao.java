/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findaccounting;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findaccounting.Tmdlaccounting;

/**
 * 核算表查询DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@MyBatisDao
public interface TmdlaccountingDao extends CrudDao<Tmdlaccounting> {
	
}