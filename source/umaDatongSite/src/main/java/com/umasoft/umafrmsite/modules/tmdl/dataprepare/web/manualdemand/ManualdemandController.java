/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.manualdemand;

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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.manualdemand.Manualdemand;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.manualdemand.ManualdemandService;

/**
 * 手工录入需量Controller
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/manualdemand/manualdemand")
public class ManualdemandController extends BaseController {

	@Autowired
	private ManualdemandService manualdemandService;
	
	@ModelAttribute
	public Manualdemand get(@RequestParam(required=false) String id) {
		Manualdemand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = manualdemandService.get(id);
		}
		if (entity == null){
			entity = new Manualdemand();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:view")
	@RequestMapping(value = {"list", ""})
	public String list(Manualdemand manualdemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Manualdemand> page = manualdemandService.findPage(new Page<Manualdemand>(request, response), manualdemand); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/dataprepare/manualdemand/manualdemandList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:view")
	@RequestMapping(value = {"listData"})
	public Page<Manualdemand> listData(Manualdemand manualdemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Manualdemand> page = manualdemandService.findPage(new Page<Manualdemand>(request, response), manualdemand); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:manualdemand:manualdemand:edit")
	@RequestMapping(value = "form")
	public String form(Manualdemand manualdemand, Model model, HttpServletRequest request) {
		model.addAttribute("manualdemand", manualdemand);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/dataprepare/manualdemand/manualdemandForm";
	}
	
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:view")
	@RequestMapping(value = "formView")
	public String formView(Manualdemand manualdemand, Model model) {
		model.addAttribute("manualdemand", manualdemand);
		return "tmdl/dataprepare/manualdemand/manualdemandFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:view")
	@RequestMapping(value = {"formData"})
	public Manualdemand formData(Manualdemand manualdemand, Model model) {
		return manualdemand;
	}

	@RequiresPermissions("dataprepare:manualdemand:manualdemand:edit")
	@RequestMapping(value = "save")
	public String save(Manualdemand manualdemand, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, manualdemand)){
			return form(manualdemand, model,request);
		}
		manualdemandService.save(manualdemand);
		addMessage(redirectAttributes, "保存手工录入需量成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/manualdemand/manualdemand/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(Manualdemand manualdemand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, manualdemand)){
			return "保存手工录入需量失败，数据校验失败。";
		}
		manualdemandService.save(manualdemand);		
		return "保存手工录入需量成功";
	}
	
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:edit")
	@RequestMapping(value = "delete")
	public String delete(Manualdemand manualdemand, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		manualdemandService.delete(manualdemand);
		addMessage(redirectAttributes, "删除手工录入需量成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/manualdemand/manualdemand/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("dataprepare:manualdemand:manualdemand:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(Manualdemand manualdemand, RedirectAttributes redirectAttributes) {
		manualdemandService.delete(manualdemand);
		return "删除手工录入需量成功";
	}

}