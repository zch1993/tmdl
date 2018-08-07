/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.dao.companypsupplys;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.companypsupplys.TmdlUsePowerSupply;

/**
 * 录入供电情况DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@MyBatisDao
public interface TmdlUsePowerSupplyDao extends CrudDao<TmdlUsePowerSupply> {
	
}