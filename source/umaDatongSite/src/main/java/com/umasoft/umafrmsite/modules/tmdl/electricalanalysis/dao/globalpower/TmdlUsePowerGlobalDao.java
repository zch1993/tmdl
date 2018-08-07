/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.dao.globalpower;

import com.umasoft.umafrmsite.common.persistence.CrudDao;
import com.umasoft.umafrmsite.common.persistence.annotation.MyBatisDao;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.globalpower.TmdlUsePowerGlobal;
import org.apache.ibatis.annotations.Param;

/**
 * 全局电量录入DAO接口
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@MyBatisDao
public interface TmdlUsePowerGlobalDao extends CrudDao<TmdlUsePowerGlobal> {
    public TmdlUsePowerGlobal findbytime(@Param("time") String time);
}