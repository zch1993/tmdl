package com.umasoft.umafrmsite.modules.tmdl.synchronization.dao;

import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@MyBatisDao
public interface SynchronizationDao {

    public List<ShuntBottom> findList(@Param("date") String date);
    public Map<String,String> getmaxtime();
}
