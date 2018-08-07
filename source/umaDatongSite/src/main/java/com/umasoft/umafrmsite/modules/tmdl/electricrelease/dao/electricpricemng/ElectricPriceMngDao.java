/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.dao.electricpricemng;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpricemng.ElectricPriceMng;

/**
 * 电价管理DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@MyBatisDao
public interface ElectricPriceMngDao extends CrudDao<ElectricPriceMng> {
	
}