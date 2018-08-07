/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.web.globalpower;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.globalpower.TmdlUsePowerGlobal;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.service.globalpower.TmdlUsePowerGlobalService;

/**
 * 全局电量录入Controller
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/electricalanalysis/globalpower/tmdlUsePowerGlobal")
public class TmdlUsePowerGlobalController extends BaseController {

	@Autowired
	private TmdlUsePowerGlobalService tmdlUsePowerGlobalService;
	
	@ModelAttribute
	public TmdlUsePowerGlobal get(@RequestParam(required=false) String id) {
		TmdlUsePowerGlobal entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlUsePowerGlobalService.get(id);
		}
		if (entity == null){
			entity = new TmdlUsePowerGlobal();
		}
		return entity;
	}
	
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlUsePowerGlobal tmdlUsePowerGlobal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlUsePowerGlobal> page = tmdlUsePowerGlobalService.findPage(new Page<TmdlUsePowerGlobal>(request, response), tmdlUsePowerGlobal); 
		model.addAttribute("page", page);
		return "tmdl/electricalanalysis/globalpower/tmdlUsePowerGlobalList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlUsePowerGlobal> listData(TmdlUsePowerGlobal tmdlUsePowerGlobal, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlUsePowerGlobal> page = tmdlUsePowerGlobalService.findPage(new Page<TmdlUsePowerGlobal>(request, response), tmdlUsePowerGlobal); 
		return page;
	}
	

	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:view")
	@RequestMapping(value = "form")
	public String form(TmdlUsePowerGlobal tmdlUsePowerGlobal, Model model) {
		model.addAttribute("tmdlUsePowerGlobal", tmdlUsePowerGlobal);
		return "tmdl/electricalanalysis/globalpower/tmdlUsePowerGlobalForm";
	}
	
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlUsePowerGlobal tmdlUsePowerGlobal, Model model) {
		model.addAttribute("tmdlUsePowerGlobal", tmdlUsePowerGlobal);
		return "tmdl/electricalanalysis/globalpower/tmdlUsePowerGlobalFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:view")
	@RequestMapping(value = {"formData"})
	public TmdlUsePowerGlobal formData(TmdlUsePowerGlobal tmdlUsePowerGlobal, Model model) {
		return tmdlUsePowerGlobal;
	}

	public static String getStringDate( Date currentTime) {

		    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		   String dateString = formatter.format(currentTime);
		   return dateString;
		}

	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:edit")
	@RequestMapping(value = "save")
	public String save(TmdlUsePowerGlobal tmdlUsePowerGlobal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlUsePowerGlobal)){
			return form(tmdlUsePowerGlobal, model);
		}
		String path="";
		String time=getStringDate(tmdlUsePowerGlobal.getTime());
		TmdlUsePowerGlobal result=tmdlUsePowerGlobalService.findbytime(time);


		if(result!=null){
			addMessage(redirectAttributes, "存在相同月份的全局电量");

			path="redirect:"+Global.getAdminPath()+"/electricalanalysis/globalpower/tmdlUsePowerGlobal/?repage";
		}
		else{
			tmdlUsePowerGlobalService.save(tmdlUsePowerGlobal);
			addMessage(redirectAttributes, "保存全局电量成功");
			path="redirect:"+Global.getAdminPath()+"/electricalanalysis/globalpower/tmdlUsePowerGlobal/?repage";
		}




		return path;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlUsePowerGlobal tmdlUsePowerGlobal, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlUsePowerGlobal)){
			return "保存全局电量失败，数据校验失败。";
		}
		String time=getStringDate(tmdlUsePowerGlobal.getTime());
		TmdlUsePowerGlobal result=tmdlUsePowerGlobalService.findbytime(time);
		if(result!=null){

			return "存在相同月份的全局电量";
		}else{
			tmdlUsePowerGlobalService.save(tmdlUsePowerGlobal);
			return "保存全局电量成功";
		}


	}
	
	@RequiresPermissions("electricalanalysis:globalpower:tmdlUsePowerGlobal:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlUsePowerGlobal tmdlUsePowerGlobal, RedirectAttributes redirectAttributes) {
		tmdlUsePowerGlobalService.delete(tmdlUsePowerGlobal);
		addMessage(redirectAttributes, "删除全局电量成功");
		return "redirect:"+Global.getAdminPath()+"/electricalanalysis/globalpower/tmdlUsePowerGlobal/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlUsePowerGlobal tmdlUsePowerGlobal, RedirectAttributes redirectAttributes) {
		tmdlUsePowerGlobalService.delete(tmdlUsePowerGlobal);
		return "删除全局电量成功";
	}

}