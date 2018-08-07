/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.dao.branchmnagement;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 分路管理DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
@MyBatisDao
public interface TmdlPowerShuntDao extends CrudDao<TmdlPowerShunt> {

    public List<TmdlPowerShunt> dataSynchrobyFL(@Param("count")Integer count);

    public Integer count();

    public  Map findfl(@Param("id") String count);

    public TmdlPowerShunt insertbz(@Param("kgbh")String kgbh,@Param("bdzid")String bdzid);
    public List<TmdlPowerShunt> findallData (TmdlPowerShunt powerShunt);
}