/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.metercopy;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.metercopy.Metercopy;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.metercopy.MetercopyService;

/**
 * 抄表本Controller
 * @author zhangch@umasoft
 * @version 2018-07-24
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/metercopy/metercopy")
public class MetercopyController extends BaseController {

	@Autowired
	private MetercopyService metercopyService;
	
	@ModelAttribute
	public Metercopy get(@RequestParam(required=false) String id) {
		Metercopy entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = metercopyService.get(id);
		}
		if (entity == null){
			entity = new Metercopy();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:metercopy:metercopy:view")
	@RequestMapping(value = {"list", ""})
	public String list(Metercopy metercopy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Metercopy> page = metercopyService.findPage(new Page<Metercopy>(request, response), metercopy); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/paramset/metercopy/metercopyList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:metercopy:metercopy:view")
	@RequestMapping(value = {"listData"})
	public Page<Metercopy> listData(Metercopy metercopy, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Metercopy> page = metercopyService.findPage(new Page<Metercopy>(request, response), metercopy); 
		return page;
	}
	

	@RequiresPermissions("paramset:metercopy:metercopy:edit")
	@RequestMapping(value = "form")
	public String form(Metercopy metercopy, Model model, HttpServletRequest request) {
		model.addAttribute("metercopy", metercopy);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/paramset/metercopy/metercopyForm";
	}
	
	@RequiresPermissions("paramset:metercopy:metercopy:view")
	@RequestMapping(value = "formView")
	public String formView(Metercopy metercopy, Model model) {
		model.addAttribute("metercopy", metercopy);
		return "tmdl/paramset/metercopy/metercopyFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:metercopy:metercopy:view")
	@RequestMapping(value = {"formData"})
	public Metercopy formData(Metercopy metercopy, Model model) {
		return metercopy;
	}

	@RequiresPermissions("paramset:metercopy:metercopy:edit")
	@RequestMapping(value = "save")
	public String save(Metercopy metercopy, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, metercopy)){
			return form(metercopy, model,request);
		}
		metercopyService.save(metercopy);
		addMessage(redirectAttributes, "保存抄表本成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/metercopy/metercopy/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:metercopy:metercopy:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(Metercopy metercopy, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, metercopy)){
			return "保存抄表本失败，数据校验失败。";
		}
		metercopyService.save(metercopy);		
		return "保存抄表本成功";
	}
	
	@RequiresPermissions("paramset:metercopy:metercopy:edit")
	@RequestMapping(value = "delete")
	public String delete(Metercopy metercopy, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		metercopyService.delete(metercopy);
		addMessage(redirectAttributes, "删除抄表本成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/metercopy/metercopy/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("paramset:metercopy:metercopy:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(Metercopy metercopy, RedirectAttributes redirectAttributes) {
		metercopyService.delete(metercopy);
		return "删除抄表本成功";
	}

}