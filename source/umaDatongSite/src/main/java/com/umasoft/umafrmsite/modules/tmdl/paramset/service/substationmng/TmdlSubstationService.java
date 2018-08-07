/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationmng;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.service.CrudService;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpricemng.ElectricPriceMng;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationcompanyrelation.SubstationCompanyRelationDao;
import com.umasoft.umafrmsite.modules.tmdl.paramset.dao.substationmng.TmdlSubstationDao;

/**
 * 变电站管理Service
 * @author zhangky@umasoft
 * @version 2018-07-05
 */
@Service
@Transactional(readOnly = true)
public class TmdlSubstationService extends CrudService<TmdlSubstationDao, TmdlSubstation> {

	@Autowired
	SubstationCompanyRelationDao substationCompanyRelationDao;
	
	public TmdlSubstation get(String id) {
		TmdlSubstation tmdlSubstation = super.get(id);
		tmdlSubstation.setSubstationCompanyRelatioList(substationCompanyRelationDao.findList(new SubstationCompanyRelation(tmdlSubstation)));
		return tmdlSubstation;
	}
	
	public List<TmdlSubstation> findList(TmdlSubstation tmdlSubstation) {
		
		List<TmdlSubstation> list = super.findList(tmdlSubstation);
		int iindex = 0;
		for(TmdlSubstation ts : list) {
			ts.setSubstationCompanyRelatioList(substationCompanyRelationDao.findList(new SubstationCompanyRelation(ts)));
			list.set(iindex, ts);
			iindex ++ ;
		}
		return list;
	}
	
	public Page<TmdlSubstation> findPage(Page<TmdlSubstation> page, TmdlSubstation tmdlSubstation) {
		Page<TmdlSubstation> tspage = super.findPage(page, tmdlSubstation);
		List<TmdlSubstation> list = tspage.getList();
		int iindex = 0;
		for(TmdlSubstation ts : list) {
			ts.setSubstationCompanyRelatioList(substationCompanyRelationDao.findList(new SubstationCompanyRelation(ts)));
			list.set(iindex, ts);
			iindex ++ ;
		}
		tspage.setList(list);
		return tspage;
	}
	
	public int getSubstationCount() {
		int lcount  = dao.getCount();
		return lcount;
	}
	
	/**
	 * 和远程数据库的变电站表数据作同步，主要判断code在本地没有，才同步过来，
	 * 如果有其他判断条件，比如地址、名称等发生变化，修改mappering的sql的where语句即可
	 */
	public void dataSynchro() {
		List<TmdlSubstation> substationList  = dao.dataSynchro();
		for(TmdlSubstation substation : substationList) {			
			super.save(substation);
		}
	}
	
	@Transactional(readOnly = false)
	public void save(TmdlSubstation tmdlSubstation) {
		super.save(tmdlSubstation);
		
		for (SubstationCompanyRelation substationCompanyRelation : tmdlSubstation.getSubstationCompanyRelatioList()){
			if(substationCompanyRelation.getUnitid() == null)continue;
			
			if (substationCompanyRelation.DEL_FLAG_NORMAL.equals(substationCompanyRelation.getDelFlag())){
				if (StringUtils.isBlank(substationCompanyRelation.getId())){
					substationCompanyRelation.setBdzid(tmdlSubstation);
					substationCompanyRelation.preInsert();
					substationCompanyRelationDao.insert(substationCompanyRelation);
				}else{
					substationCompanyRelation.setBdzid(tmdlSubstation);
					substationCompanyRelation.preUpdate();
					substationCompanyRelationDao.update(substationCompanyRelation);
				}
			}else{
				substationCompanyRelationDao.delete(substationCompanyRelation);
			}
		}
	}
	
	@Transactional(readOnly = false)
	public void delete(TmdlSubstation tmdlSubstation) {
		SubstationCompanyRelation substationCompanyRelation = new SubstationCompanyRelation(tmdlSubstation);
		substationCompanyRelationDao.delete(substationCompanyRelation);
		super.delete(tmdlSubstation);
		
	}
	
}