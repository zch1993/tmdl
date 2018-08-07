/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.groupcompany;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.groupcompany.GroupCompany;

/**
 * 集团企业管理DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@MyBatisDao
public interface GroupCompanyDao extends CrudDao<GroupCompany> {
	
}