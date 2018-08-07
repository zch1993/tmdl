/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.dao.findcollierygroup;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findcollierygroup.TmdlCollieryGroup;
import org.apache.ibatis.annotations.Param;

/**
 * 煤业和集团查询DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@MyBatisDao
public interface TmdlCollieryGroupDao extends CrudDao<TmdlCollieryGroup> {
    public TmdlCollieryGroup get(@Param("id") String id,@Param("collieryname") String name, @Param("time") String time);
}