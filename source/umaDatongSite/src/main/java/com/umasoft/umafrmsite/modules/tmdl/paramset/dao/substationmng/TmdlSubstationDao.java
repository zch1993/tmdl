/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationmng;

import java.util.List;
import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;

/**
 * 变电站管理DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-05
 */
@MyBatisDao
public interface TmdlSubstationDao extends CrudDao<TmdlSubstation> {
	public int getCount();
	public List<TmdlSubstation> dataSynchro();
}