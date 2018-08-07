/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.dao.metercopy;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.metercopy.Metercopy;

/**
 * 抄表本DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@MyBatisDao
public interface MetercopyDao extends CrudDao<Metercopy> {
	
}