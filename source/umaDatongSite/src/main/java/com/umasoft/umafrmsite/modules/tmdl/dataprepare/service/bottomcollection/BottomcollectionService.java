/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.bottomcollection;


import java.util.*;


import com.umasoft.umafrmsite.datasource.DataSource;
import com.umasoft.umafrmsite.datasource.DynamicDataSourceHolder;
import com.umasoft.umafrmsite.modules.tmdl._common.DateUtils;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.ShuntBottom;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.DbnmIdEntity;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.dao.branchmnagement.TmdlPowerShuntDao;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.Bottomcollection;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.dao.bottomcollection.BottomcollectionDao;

/**
 * 表底采集Service
 * @author zhangch@umasoft
 * @version 2018-07-13
 */
@Service
@Transactional(readOnly = true)
public class BottomcollectionService extends CrudService<BottomcollectionDao, Bottomcollection> {
	public static final Logger LOGGER = LoggerFactory.getLogger(BottomcollectionService.class);

	@Autowired
	private BottomcollectionDao  bottomcollectionDao;
	@Autowired
	private TmdlPowerShuntDao shutdao;
	public Bottomcollection get(String id) {
		return super.get(id);
	}

	public List<Bottomcollection> findList(Bottomcollection bottomcollection) {
		return super.findList(bottomcollection);
	}

	public Page<Bottomcollection> findPage(Page<Bottomcollection> page, Bottomcollection bottomcollection) {
		return super.findPage(page, bottomcollection);
	}

	public Map<String,String> getmaxtime(){
		return bottomcollectionDao.getmaxtime();
	}


	@Transactional(readOnly = false)
	public void save(Bottomcollection bottomcollection) {
		super.save(bottomcollection);
	}

	@Transactional(readOnly = false)
	public void delete(Bottomcollection bottomcollection) {
		super.delete(bottomcollection);
	}



	public List<ShuntBottom> findall() {
		return bottomcollectionDao.findData();
	}

	public void insertbranch(ShuntBottom e) {
		DynamicDataSourceHolder.setDataSource("dataSource");
		bottomcollectionDao.branchinsert(e);
	}



	public void dataSynchrobyDL(){
		List<Bottomcollection> list =findmothbd();
		for (Bottomcollection data :list){
			super.save(data);
		}
	}

	/**
	 * 查找月度表底
	 * @return
	 */
	public List findmothbd(){
		List<Bottomcollection> list = new ArrayList<Bottomcollection>();
		Date date =new Date();
		String kgbh="";
		String maxtime="";
		String mintime="";
		String firsttime=DateUtils.DateparseString(date,"yyyy-MM");
		String nexttime=DateUtils.month(date,"yyyy-MM");
		String nexttime2=DateUtils.month( DateUtils.StringparseDate(nexttime,"yyyy-MM"),"yyyy-MM");
		LOGGER.info("firsttime :"+firsttime);
		LOGGER.info("nexttime :"+nexttime);

		//本月list
		List<DbnmIdEntity> nowmonthlist=bottomcollectionDao.findFistMonth(nexttime);
		//上月
		List <DbnmIdEntity> Lastlist=bottomcollectionDao.findFistMonth(nexttime2);
		for(DbnmIdEntity D :nowmonthlist){
			Double WATTFULGROSS=null;//有功总量
			Double Peaksegment=null;//峰段总量
			Double FLATSEGMENT=null;//平段总量
			Double GRAINSEGMENT=null;//谷段总量
			Double PLUSWATT=null;//正有功总量
			Double RESEWATT=null;//反有功总量
            Double IDLEROLL=null;//无功总量
            Double ANTI_REACTIVE_POWER=null;//反无功总量
            Double DEMAND=null;//需量
			String first=DateUtils.DateparseString(D.getFirst(),"yyyy-MM-dd");
			String last=DateUtils.DateparseString(D.getNext(),"yyyy-MM-dd");

            LOGGER.info("first"+first+"last"+last);
			    ShuntBottom firstData=bottomcollectionDao.findDataByMonth(first,D.getKgbh());
				ShuntBottom lastData=bottomcollectionDao.findDataByMonth(last,D.getKgbh());
				if(firstData.getSwitchnumber().equals(lastData.getSwitchnumber())){
					TmdlPowerShunt shuit=bottomcollectionDao.findshuit(D.getKgbh(),D.getStationid());
					Bottomcollection load =new Bottomcollection();

                    WATTFULGROSS=(firstData.getR0p1()+firstData.getR0p2())-(lastData.getR0p1()+lastData.getR0p2());

					//峰电量
                    Peaksegment=firstData.getR2p1()-lastData.getR2p1();
					//平
                    FLATSEGMENT=firstData.getR3p1()-lastData.getR3p1();
					//谷
                    GRAINSEGMENT=firstData.getR4p1()-lastData.getR4p1();
                    PLUSWATT=firstData.getR0p1()-lastData.getR0p1();
                    RESEWATT=firstData.getR0p2()-lastData.getR0p2();

                    IDLEROLL=(firstData.getR0p3()-lastData.getR0p3())+(firstData.getR0p4()-lastData.getR0p4());
                    ANTI_REACTIVE_POWER=(firstData.getR0p4()-lastData.getR0p4())+ (firstData.getR0p2()-lastData.getR0p2());
                    DEMAND=firstData.getZdxl();
					load.setWattfulgross(WATTFULGROSS!=null?WATTFULGROSS:0.00);
					load.setPeaksegment(Peaksegment!=null?Peaksegment:0.00);
					load.setGrainsegment(GRAINSEGMENT!=null?GRAINSEGMENT:0.00);
					load.setFlatsegment(FLATSEGMENT!=null?FLATSEGMENT:0.00);
					load.setPluswatt(PLUSWATT!=null?PLUSWATT:0.00);
					load.setResewatt(RESEWATT!=null?RESEWATT:0.00);
					load.setDemand(DEMAND!=null?DEMAND:0.00);
                    load.setIdleroll(IDLEROLL!=null?IDLEROLL:0.00);
					load.setAntiReactivePower(ANTI_REACTIVE_POWER!=null?ANTI_REACTIVE_POWER:0.00);
					Date dates=DateUtils.StringparseDate(first,"yyyy-MM-dd");
					ShuntBottom lastbase = bottomcollectionDao.findDataByMonth(DateUtils.month(dates,"yyyy-MM-dd"), D.getKgbh());
					load.setLastbase(lastbase!=null?lastbase.getR0p1():0.00);
					load.setNowbase(firstData.getR0p1()!=null?firstData.getR0p1():0.00);
					load.setInsertFlag("1");
					/*load.setTime(dates);*/
                    load.setShuntId(shuit);
					list.add(load);

				}
		}
        for(DbnmIdEntity C :Lastlist) {
            Double WATTFULGROSS=null;//有功总量
            Double Peaksegment=null;//峰段总量
            Double FLATSEGMENT=null;//平段总量
            Double GRAINSEGMENT=null;//谷段总量
            Double PLUSWATT=null;//正有功总量
            Double RESEWATT=null;//反有功总量
            Double IDLEROLL=null;//无功总量
            Double ANTI_REACTIVE_POWER=null;//反无功总量
            Double DEMAND=null;//需量
            String first=DateUtils.DateparseString(C.getFirst(),"yyyy-MM-dd");
            String last=DateUtils.DateparseString(C.getNext(),"yyyy-MM-dd");

            LOGGER.info("first"+first+"last"+last);
            ShuntBottom firstData=bottomcollectionDao.findDataByMonth(first,C.getKgbh());
            ShuntBottom lastData=bottomcollectionDao.findDataByMonth(last,C.getKgbh());
            if(firstData.getSwitchnumber().equals(lastData.getSwitchnumber())){
                TmdlPowerShunt shuit=bottomcollectionDao.findshuit(C.getKgbh(),C.getStationid());
                Bottomcollection nowload =new Bottomcollection();

                WATTFULGROSS=(firstData.getR0p1()+firstData.getR0p2())-(lastData.getR0p1()+lastData.getR0p2());

                //峰电量
                Peaksegment=firstData.getR2p1()-lastData.getR2p1();
                //平
                FLATSEGMENT=firstData.getR3p1()-lastData.getR3p1();
                //谷
                GRAINSEGMENT=firstData.getR4p1()-lastData.getR4p1();
                PLUSWATT=firstData.getR0p1()-lastData.getR0p1();
                RESEWATT=firstData.getR0p2()-lastData.getR0p2();

                IDLEROLL=(firstData.getR0p3()-lastData.getR0p3())+(firstData.getR0p4()-lastData.getR0p4());
                ANTI_REACTIVE_POWER=(firstData.getR0p4()-lastData.getR0p4())+ (firstData.getR0p2()-lastData.getR0p2());
                DEMAND=firstData.getZdxl();
                nowload.setWattfulgross(WATTFULGROSS!=null?WATTFULGROSS:0.00);
                nowload.setPeaksegment(Peaksegment!=null?Peaksegment:0.00);
                nowload.setGrainsegment(GRAINSEGMENT!=null?GRAINSEGMENT:0.00);
                nowload.setFlatsegment(FLATSEGMENT!=null?FLATSEGMENT:0.00);
                nowload.setPluswatt(PLUSWATT!=null?PLUSWATT:0.00);
                nowload.setResewatt(RESEWATT!=null?RESEWATT:0.00);
                nowload.setDemand(DEMAND!=null?DEMAND:0.00);
                nowload.setIdleroll(IDLEROLL!=null?IDLEROLL:0.00);
                nowload.setAntiReactivePower(ANTI_REACTIVE_POWER!=null?ANTI_REACTIVE_POWER:0.00);
                Date dates=DateUtils.StringparseDate(first,"yyyy-MM-dd");
                ShuntBottom lastbase = bottomcollectionDao.findDataByMonth(DateUtils.month(dates,"yyyy-MM-dd"), C.getKgbh());
                nowload.setLastbase(lastbase!=null?lastbase.getR0p1():0.00);
                nowload.setNowbase(firstData.getR0p1()!=null?firstData.getR0p1():0.00);
                nowload.setInsertFlag("1");
                /*nowload.setTime(dates);*/
                nowload.setShuntId(shuit);
                list.add(nowload);

            }
		}
		return list;

	}
	
}