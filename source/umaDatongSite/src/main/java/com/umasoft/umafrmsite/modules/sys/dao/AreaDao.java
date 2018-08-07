/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.sys.dao;

import com.umasoft.umafrmsite.common.persistence.TreeDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.sys.entity.Area;

import java.util.List;

/**
 * 区域DAO接口
 * @author umasoft
 * @version 2014-05-16
 */
@MyBatisDao
public interface AreaDao extends TreeDao<Area> {
    public List<Area> findallcityinfo();
	
}
