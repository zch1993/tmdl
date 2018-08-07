package com.umasoft.umafrmsite.modules.tmdl.synchronization.service;


import com.umasoft.umafrmsite.datasource.DynamicDataSourceHolder;
import com.umasoft.umafrmsite.modules.tmdl.synchronization.dao.SynchronizationDao;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SynchronizationService {

    @Autowired
    private SynchronizationDao dao;
    public List<ShuntBottom> findList(String date){
       DynamicDataSourceHolder.setDataSource("dataSource2");



        return dao.findList(date);
    }

    public Map<String,String> getmaxtime(){
        return dao.getmaxtime();
    }
}
