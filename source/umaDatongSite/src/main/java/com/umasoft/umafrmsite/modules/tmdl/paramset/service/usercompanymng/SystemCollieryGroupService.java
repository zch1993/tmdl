/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.SystemCollieryGroup;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationcompanyrelation.SubstationCompanyRelationDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.usercompanymng.SystemCollieryGroupDao;

/**
 * 矿业和集团比例分配Service
 * @author zhangky@umasoft
 * @version 2018-07-12
 */
@Service
@Transactional(readOnly = true)
public class SystemCollieryGroupService extends CrudService<SystemCollieryGroupDao, SystemCollieryGroup> {

	@Autowired
	SystemCollieryGroupDao systemCollieryGroupDao;
    protected Logger logger = LoggerFactory.getLogger(SystemCollieryGroupService.class);
	public SystemCollieryGroup get(String id) {
		SystemCollieryGroup  systemCollieryGroup = super.get(id);
		SystemCollieryGroup  systemCollieryGroupsear= new SystemCollieryGroup();
		systemCollieryGroupsear.setUnitid(systemCollieryGroup.getUnitid());
		systemCollieryGroup.setCgList(systemCollieryGroupDao.findList(systemCollieryGroupsear));
		return systemCollieryGroup;
	}
	
	public List<SystemCollieryGroup> findList(SystemCollieryGroup systemCollieryGroup) {
		/*return super.findList(systemCollieryGroup);*/


		/*
		List<SystemCollieryGroup> list = super.findList(systemCollieryGroup);
		int iindex = 0;
		SystemCollieryGroup  systemCollieryGroupsear= new SystemCollieryGroup();
		for(SystemCollieryGroup ts : list) {			
			systemCollieryGroupsear.setUnitid(ts.getUnitid());
			ts.setCgList(systemCollieryGroupDao.findList(systemCollieryGroupsear));
			list.set(iindex, ts);
			iindex ++ ;
		}
		return list;
		*/

        List<Map<String, String>> list=systemCollieryGroupDao.findcompany();
        List<SystemCollieryGroup> resultslist= new ArrayList<SystemCollieryGroup>();
        UserCompany userCompany =new UserCompany();
        SystemCollieryGroup  systemCollieryGroupsear= new SystemCollieryGroup();
        for(Map<String, String> ts : list) {
            userCompany.setId(ts.get("ID"));
            userCompany.setName(ts.get("NAME"));
            systemCollieryGroupsear.setUnitid(userCompany);
            List<SystemCollieryGroup> result=systemCollieryGroupDao.findCollieryGroup(userCompany.getId());
            systemCollieryGroupsear.setCgList(result);
            resultslist.add(systemCollieryGroupsear);
        }
        return  resultslist;
	}
	
	public Page<SystemCollieryGroup> findPage(Page<SystemCollieryGroup> page, SystemCollieryGroup systemCollieryGroup) {


        Page<SystemCollieryGroup> tspage= super.findPage(page, systemCollieryGroup);
       // List<SystemCollieryGroup> resultlist = tspage.getList();
        List<Map<String, String>> list=systemCollieryGroupDao.findcompany();

        List<SystemCollieryGroup> resultlist= new ArrayList<SystemCollieryGroup>();

        String collieryname ="";
        for(Map<String, String> ts : list) {

            UserCompany userCompany =new UserCompany();
            SystemCollieryGroup  systemCollieryGroupsear= new SystemCollieryGroup();
            userCompany.setId(ts.get("ID"));
            userCompany.setName(ts.get("NAME"));
            systemCollieryGroupsear.setUnitid(userCompany);

            List<SystemCollieryGroup> result=null;

            if(systemCollieryGroup!=null  ){

                if (systemCollieryGroup.getUnitid()==null  || systemCollieryGroup.getCollieryname()==null){
                    result=systemCollieryGroupDao.findCollieryGroup(userCompany.getId());


                }
                else if(systemCollieryGroup.getUnitid().getName().equals("") && systemCollieryGroup.getCollieryname().equals("")){
                    result=systemCollieryGroupDao.findCollieryGroup(userCompany.getId());
                }


               else if((systemCollieryGroup.getUnitid().getName()!=null && !systemCollieryGroup.getUnitid().getName().equals(""))  || (systemCollieryGroup.getCollieryname()!=null && !systemCollieryGroup.getCollieryname().equals(""))){
                    result=systemCollieryGroupDao.findColliery(systemCollieryGroup.getUnitid().getName(),systemCollieryGroup.getCollieryname());

                }

            }

            for (SystemCollieryGroup data:result) {

                systemCollieryGroupsear.setId(data.getId());
                systemCollieryGroupsear.setCollieryname(data.getCollieryname());
                systemCollieryGroupsear.setYdbl(data.getYdbl());


            }

            systemCollieryGroupsear.setCgList(result.size()>0?result:new ArrayList<SystemCollieryGroup>());

            if(collieryname!="" ||collieryname!=null){

                    if(systemCollieryGroupsear.getCollieryname().equals(collieryname)){

                    }else{
                        resultlist.add(systemCollieryGroupsear);
                    }


            }else{
                 if(systemCollieryGroupsear.getCollieryname()==null ||systemCollieryGroupsear.getCollieryname().equals("")){
                     systemCollieryGroupsear=new SystemCollieryGroup();
                 }
                    resultlist.add(systemCollieryGroupsear);



            }


            collieryname=systemCollieryGroupsear.getCollieryname();

        }
        tspage.setList(resultlist);
        logger.info("info" +tspage.getList());
		return tspage;

		/*Page<SystemCollieryGroup> tspage = super.findPage(page, systemCollieryGroup);
		List<SystemCollieryGroup> list = tspage.getList();
		int iindex = 0;
		SystemCollieryGroup  systemCollieryGroupsear= new SystemCollieryGroup();
		for(SystemCollieryGroup ts : list) {
			systemCollieryGroupsear.setUnitid(ts.getUnitid());
			ts.setCgList(systemCollieryGroupDao.findList(systemCollieryGroupsear));
			list.set(iindex, ts);
			iindex ++ ;
		}
		tspage.setList(list);
		return tspage;*/

	}
	
	@Transactional(readOnly = false)
	public void save(SystemCollieryGroup systemCollieryGroup) {
		//super.save(systemCollieryGroup);
		
		for (SystemCollieryGroup ts : systemCollieryGroup.getCgList()){
			if(ts.getUnitid() == null)continue;
			
			if (ts.DEL_FLAG_NORMAL.equals(ts.getDelFlag())){
				if (StringUtils.isBlank(ts.getId())){
					ts.setUnitid(systemCollieryGroup.getUnitid());
					ts.preInsert();
					systemCollieryGroupDao.insert(ts);
				}else{
					ts.setUnitid(systemCollieryGroup.getUnitid());
					ts.preUpdate();
					systemCollieryGroupDao.update(ts);
				}
			}else{
				systemCollieryGroupDao.delete(systemCollieryGroup);
			}
		}
		
	}
	
	@Transactional(readOnly = false)
	public void delete(SystemCollieryGroup systemCollieryGroup) {
		super.delete(systemCollieryGroup);
	}
	
}