/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.sys.dao;

import java.util.List;

import com.umasoft.umafrmsite.common.persistence.TreeDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.sys.entity.Office;
import com.umasoft.umafrmsite.modules.sys.entity.User;

/**
 * 机构DAO接口
 * @author umasoft
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	public List<Office> findByOfficeCode(Office code);
}
