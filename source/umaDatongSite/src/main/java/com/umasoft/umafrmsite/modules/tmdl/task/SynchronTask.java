package com.umasoft.umafrmsite.modules.tmdl.task;




import com.umasoft.umafrmsite.modules.tmdl._common.DateUtils;
import com.umasoft.umafrmsite.modules.tmdl.synchronization.service.SynchronizationService;
import com.umasoft.umafrmsite.modules.tmdl._common.SynchronThread;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.bottomcollection.BottomcollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

@Component
public class SynchronTask {

  public static   ExecutorService executor = Executors.newFixedThreadPool(10);
   @Autowired
    private SynchronizationService service;

    @Autowired
    private BottomcollectionService bottomcollectionService;


    //@Scheduled(cron = "0 36 15 ? * *")
    public void save(){
        String date="";
        long startTimes = System.currentTimeMillis();
        List<ShuntBottom> synchronList = bottomcollectionService.findall();
        if(synchronList.size()>0 || synchronList!=null){
            Map<String,String> maxtime=bottomcollectionService.getmaxtime();
            Map<String,String> maxtime2=service.getmaxtime();
             String time=maxtime.get("maxtime");
            String time2=maxtime2.get("maxtime");
            Date date1=DateUtils.StringparseDate(time,"yyyy-MM-dd");
            Date date2=DateUtils.StringparseDate(time2,"yyyy-MM-dd");
            if(date1.before(date2)){
                date=DateUtils.DateparseString(date2,"yyyy-MM-dd");
            }

        }
        List<ShuntBottom> base = service.findList(date);
        System.out.println("之前" +base.size());
        //base.removeAll(synchronList);
        System.out.println("之后" +base.size());
        System.out.println("线程" + Thread.currentThread().getName() + "正在执行。。");

        if(base.size()>0){

            /*for (ShuntBottom e : base) {
                SynchronThread t = new SynchronThread(e, bottomcollectionService);
                executor.execute(t);
                System.out.println("数据" + e.getSwitchnumber() + "time" + DateUtils.DateparseString(e.getDatadatetime(),"YYYY-MM-dd"));
            }*/
        }


       // for (ShuntBottom e : base) {
        //}
        long endTimes = System.currentTimeMillis();
        System.out.println("所有线程执行完毕:" + (endTimes - startTimes));
        System.out.println("========结束========");
    }
}
