package com.umasoft.umafrmsite.modules.tmdl._common;


import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.bottomcollection.BottomcollectionService;


import java.util.List;

public class SynchronThread implements Runnable {

    private ShuntBottom   entity;

    private BottomcollectionService bottomcollectionService;

    public SynchronThread(ShuntBottom entity, BottomcollectionService bottomcollectionService) {
        this.entity = entity;
        this.bottomcollectionService = bottomcollectionService;
    }

    public ShuntBottom getEntity() {
        return entity;
    }

    public void setEntity(ShuntBottom entity) {
        this.entity = entity;
    }

    public BottomcollectionService getBottomcollectionService() {
        return bottomcollectionService;
    }

    public void setBottomcollectionService(BottomcollectionService bottomcollectionService) {
        this.bottomcollectionService = bottomcollectionService;
    }

    @Override
    public void run() {
            System.out.println("线程" + Thread.currentThread().getName()+ "正在执行。。");
            if(entity!=null ){
                    bottomcollectionService.insertbranch(entity);
        }



    }


}
