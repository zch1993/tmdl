/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.readfail;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.readfail.Readfail;

/**
 * 读取失败DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-16
 */
@MyBatisDao
public interface ReadfailDao extends CrudDao<Readfail> {
	
}