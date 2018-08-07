/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.bottomcollection;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.Bottomcollection;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.DbnmIdEntity;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 表底采集DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-13
 */
@MyBatisDao
public interface BottomcollectionDao extends CrudDao<Bottomcollection> {

    public ShuntBottom findDataByMonth(@Param("first") String first, @Param("kgbh")String kgbh);
    public List<ShuntBottom> findData();


    public void branchinsert(ShuntBottom e);
    public Map<String,String> getmaxtime();

    public List<DbnmIdEntity>findFistMonth(@Param("time") String first);

    public TmdlPowerShunt findshuit(@Param("kgbh") String kgbh,@Param("stationid")String stationid);


	
}