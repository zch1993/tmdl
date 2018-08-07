/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.dao.usercompanymng;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.SystemCollieryGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 矿业和集团比例分配DAO接口
 * @author zhangky@umasoft
 * @version 2018-07-12
 */
@MyBatisDao
public interface SystemCollieryGroupDao extends CrudDao<SystemCollieryGroup> {

    public List<Map<String,String>> findcompany();

    public List<SystemCollieryGroup> findCollieryGroup(@Param("unitid") String unitid);

    public List<SystemCollieryGroup> findColliery(@Param("unitname") String unitname, @Param("collieryname")String collieryname);


	
}