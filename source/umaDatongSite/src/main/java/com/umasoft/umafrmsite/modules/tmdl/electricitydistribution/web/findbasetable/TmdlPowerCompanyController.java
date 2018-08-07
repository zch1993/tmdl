/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.web.findbasetable;

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
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findbasetable.TmdlPowerCompany;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findbasetable.TmdlPowerCompanyService;

/**
 * 基础表查询Controller
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/electricitydistribution/findbasetable/tmdlPowerCompany")
public class TmdlPowerCompanyController extends BaseController {

	@Autowired
	private TmdlPowerCompanyService tmdlPowerCompanyService;
	
	@ModelAttribute
	public TmdlPowerCompany get(@RequestParam(required=false) String id) {
		TmdlPowerCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlPowerCompanyService.get(id);
		}
		if (entity == null){
			entity = new TmdlPowerCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlPowerCompany tmdlPowerCompany, HttpServletRequest request, HttpServletResponse response, Model model) {

		Page<TmdlPowerCompany> page = tmdlPowerCompanyService.findPage(new Page<TmdlPowerCompany>(request, response), tmdlPowerCompany);
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findbasetable/tmdlPowerCompanyList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlPowerCompany> listData(TmdlPowerCompany tmdlPowerCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlPowerCompany> page = tmdlPowerCompanyService.findPage(new Page<TmdlPowerCompany>(request, response), tmdlPowerCompany); 
		return page;
	}
	

	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:edit")
	@RequestMapping(value = "form")
	public String form(TmdlPowerCompany tmdlPowerCompany, Model model, HttpServletRequest request) {
		model.addAttribute("tmdlPowerCompany", tmdlPowerCompany);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findbasetable/tmdlPowerCompanyForm";
	}
	
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlPowerCompany tmdlPowerCompany, Model model) {
		model.addAttribute("tmdlPowerCompany", tmdlPowerCompany);
		return "tmdl/electricitydistribution/findbasetable/tmdlPowerCompanyFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:view")
	@RequestMapping(value = {"formData"})
	public TmdlPowerCompany formData(TmdlPowerCompany tmdlPowerCompany, Model model) {
		return tmdlPowerCompany;
	}

	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:edit")
	@RequestMapping(value = "save")
	public String save(TmdlPowerCompany tmdlPowerCompany, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, tmdlPowerCompany)){
			return form(tmdlPowerCompany, model,request);
		}
		tmdlPowerCompanyService.save(tmdlPowerCompany);
		addMessage(redirectAttributes, "保存基础表查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findbasetable/tmdlPowerCompany/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlPowerCompany tmdlPowerCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlPowerCompany)){
			return "保存基础表查询失败，数据校验失败。";
		}
		tmdlPowerCompanyService.save(tmdlPowerCompany);		
		return "保存基础表查询成功";
	}
	
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlPowerCompany tmdlPowerCompany, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		tmdlPowerCompanyService.delete(tmdlPowerCompany);
		addMessage(redirectAttributes, "删除基础表查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findbasetable/tmdlPowerCompany/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findbasetable:tmdlPowerCompany:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlPowerCompany tmdlPowerCompany, RedirectAttributes redirectAttributes) {
		tmdlPowerCompanyService.delete(tmdlPowerCompany);
		return "删除基础表查询成功";
	}

	@ResponseBody
	@RequestMapping(value = {"dataSynchro"})
	public String dataSynchro(){
		tmdlPowerCompanyService.dataSynchro();
		return "ok";
	}


	@InitBinder
	public void intDate(WebDataBinder dataBinder){
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		dateFormat.setLenient(false);
		dataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));

	}

}