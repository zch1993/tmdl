/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.web.findaccounting;

import java.util.ArrayList;
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
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findaccounting.Tmdlaccounting;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findaccounting.TmdlaccountingService;

/**
 * 核算表查询Controller
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/electricitydistribution/findaccounting/tmdlaccounting")
public class TmdlaccountingController extends BaseController {

	@Autowired
	private TmdlaccountingService tmdlaccountingService;
	
	@ModelAttribute
	public Tmdlaccounting get(@RequestParam(required=false) String id) {
		Tmdlaccounting entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlaccountingService.get(id);
		}
		if (entity == null){
			entity = new Tmdlaccounting();
		}
		return entity;
	}
	
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:view")
	@RequestMapping(value = {"list", ""})
	public String list(Tmdlaccounting tmdlaccounting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Tmdlaccounting> page = tmdlaccountingService.findPage(new Page<Tmdlaccounting>(request, response), tmdlaccounting); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findaccounting/tmdlaccountingList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:view")
	@RequestMapping(value = {"listData"})
	public Page<Tmdlaccounting> listData(Tmdlaccounting tmdlaccounting, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Tmdlaccounting> page = tmdlaccountingService.findPage(new Page<Tmdlaccounting>(request, response), tmdlaccounting); 
		return page;
	}
	

	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:edit")
	@RequestMapping(value = "form")
	public String form(Tmdlaccounting tmdlaccounting, Model model, HttpServletRequest request) {
		model.addAttribute("tmdlaccounting", tmdlaccounting);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findaccounting/tmdlaccountingForm";
	}
	
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:view")
	@RequestMapping(value = "formView")
	public String formView(Tmdlaccounting tmdlaccounting, Model model) {
		model.addAttribute("tmdlaccounting", tmdlaccounting);
		return "tmdl/electricitydistribution/findaccounting/tmdlaccountingFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:view")
	@RequestMapping(value = {"formData"})
	public Tmdlaccounting formData(Tmdlaccounting tmdlaccounting, Model model) {
		return tmdlaccounting;
	}

	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:edit")
	@RequestMapping(value = "save")
	public String save(Tmdlaccounting tmdlaccounting, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, tmdlaccounting)){
			return form(tmdlaccounting, model,request);
		}
		tmdlaccountingService.save(tmdlaccounting);
		addMessage(redirectAttributes, "保存核算表查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findaccounting/tmdlaccounting/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(Tmdlaccounting tmdlaccounting, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlaccounting)){
			return "保存核算表查询失败，数据校验失败。";
		}
		tmdlaccountingService.save(tmdlaccounting);		
		return "保存核算表查询成功";
	}
	
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:edit")
	@RequestMapping(value = "delete")
	public String delete(Tmdlaccounting tmdlaccounting, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		tmdlaccountingService.delete(tmdlaccounting);
		addMessage(redirectAttributes, "删除核算表查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findaccounting/tmdlaccounting/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findaccounting:tmdlaccounting:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(Tmdlaccounting tmdlaccounting, RedirectAttributes redirectAttributes) {
		tmdlaccountingService.delete(tmdlaccounting);
		return "删除核算表查询成功";
	}

}