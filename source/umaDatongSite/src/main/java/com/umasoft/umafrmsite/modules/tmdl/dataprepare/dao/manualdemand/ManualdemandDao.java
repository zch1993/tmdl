/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.manualdemand;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.manualdemand.Manualdemand;

/**
 * 手工录入需量DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@MyBatisDao
public interface ManualdemandDao extends CrudDao<Manualdemand> {
	
}