/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.powermovemng;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powermovemng.PowerMoveMng;

/**
 * 转入转出电量录入DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
@MyBatisDao
public interface PowerMoveMngDao extends CrudDao<PowerMoveMng> {
	
}