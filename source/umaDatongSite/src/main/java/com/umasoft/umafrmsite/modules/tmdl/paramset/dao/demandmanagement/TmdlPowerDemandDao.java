/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.dao.demandmanagement;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.demandmanagement.TmdlPowerDemand;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

import java.util.Map;

/**
 * 需量管理DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-09
 */
@MyBatisDao
public interface TmdlPowerDemandDao extends  CrudDao<TmdlPowerDemand>  {


}