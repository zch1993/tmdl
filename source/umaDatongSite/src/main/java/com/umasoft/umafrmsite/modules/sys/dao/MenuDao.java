/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.sys.dao;

import java.util.List;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.sys.entity.Menu;

/**
 * 菜单DAO接口
 * @author umasoft
 * @version 2014-05-16
 */
@MyBatisDao
public interface MenuDao extends CrudDao<Menu> {

	public List<Menu> findByParentIdsLike(Menu menu);

	public List<Menu> findByUserId(Menu menu);
	
	public List<Menu> findByPemission(Menu menu);
	
	public int updateParentIds(Menu menu);
	
	public int updateSort(Menu menu);
	
}
