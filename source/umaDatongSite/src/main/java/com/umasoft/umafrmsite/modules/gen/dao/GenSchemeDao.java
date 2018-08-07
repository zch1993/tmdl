/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.gen.dao;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * @author umasoft
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {
	
}
