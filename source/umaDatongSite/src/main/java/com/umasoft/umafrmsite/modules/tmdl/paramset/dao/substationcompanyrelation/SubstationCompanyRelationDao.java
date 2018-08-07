/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationcompanyrelation;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;

/**
 * 变电站和用电单位对应关系DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-11
 */
@MyBatisDao
public interface SubstationCompanyRelationDao extends CrudDao<SubstationCompanyRelation> {
	
}