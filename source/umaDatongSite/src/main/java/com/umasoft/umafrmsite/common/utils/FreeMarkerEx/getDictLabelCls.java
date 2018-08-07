package com.umasoft.umafrmsite.common.utils.FreeMarkerEx;

import java.util.List;

import com.umasoft.umafrmsite.modules.sys.utils.DictUtils;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

public class getDictLabelCls  implements TemplateMethodModelEx{
	//private final static Log log= LogFactory.getLog(GetClsNameTMM.class);  
	
	public Object exec(String value, String type, String defaultValue ) throws TemplateModelException {  
        
    	   String strLabel =  DictUtils.getDictLabel(value, type, defaultValue);;

       
        return strLabel;  
    }

	@Override
	public Object exec(List arg0) throws TemplateModelException {
		String defaultValue = "";
		if(arg0.size()>2)defaultValue = arg0.get(2).toString();  ;
		if(arg0.size()<2)return null;
		
		String strLabel =  DictUtils.getDictLabel(arg0.get(0).toString(), arg0.get(1).toString(), defaultValue);;
		return strLabel;
	}  
}
