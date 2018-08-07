/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.syssubstationmng;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.syssubstationmng.PowerShunt;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.syssubstationmng.PowerShuntService;

/**
 * 超表本管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/syssubstationmng/powerShunt")
public class PowerShuntController extends BaseController {

	@Autowired
	private PowerShuntService powerShuntService;
	
	@ModelAttribute
	public PowerShunt get(@RequestParam(required=false) String id) {
		PowerShunt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = powerShuntService.get(id);
		}
		if (entity == null){
			entity = new PowerShunt();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:view")
	@RequestMapping(value = {"list", ""})
	public String list(PowerShunt powerShunt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PowerShunt> page = powerShuntService.findPage(new Page<PowerShunt>(request, response), powerShunt); 
		model.addAttribute("page", page);
		return "tmdl/paramset/syssubstationmng/powerShuntList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:view")
	@RequestMapping(value = {"listData"})
	public Page<PowerShunt> listData(PowerShunt powerShunt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PowerShunt> page = powerShuntService.findPage(new Page<PowerShunt>(request, response), powerShunt); 
		return page;
	}
	

	@RequiresPermissions("paramset:syssubstationmng:powerShunt:view")
	@RequestMapping(value = "form")
	public String form(PowerShunt powerShunt, Model model) {
		model.addAttribute("powerShunt", powerShunt);
		return "tmdl/paramset/syssubstationmng/powerShuntForm";
	}
	
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:view")
	@RequestMapping(value = "formView")
	public String formView(PowerShunt powerShunt, Model model) {
		model.addAttribute("powerShunt", powerShunt);
		return "tmdl/paramset/syssubstationmng/powerShuntFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:view")
	@RequestMapping(value = {"formData"})
	public PowerShunt formData(PowerShunt powerShunt, Model model) {
		return powerShunt;
	}

	@RequiresPermissions("paramset:syssubstationmng:powerShunt:edit")
	@RequestMapping(value = "save")
	public String save(PowerShunt powerShunt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, powerShunt)){
			return form(powerShunt, model);
		}
		powerShuntService.save(powerShunt);
		addMessage(redirectAttributes, "保存超表本管理成功");
		return "redirect:"+Global.getAdminPath()+"/paramset/syssubstationmng/powerShunt/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(PowerShunt powerShunt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, powerShunt)){
			return "保存超表本管理失败，数据校验失败。";
		}
		powerShuntService.save(powerShunt);		
		return "保存超表本管理成功";
	}
	
	@RequiresPermissions("paramset:syssubstationmng:powerShunt:edit")
	@RequestMapping(value = "delete")
	public String delete(PowerShunt powerShunt, RedirectAttributes redirectAttributes) {
		powerShuntService.delete(powerShunt);
		addMessage(redirectAttributes, "删除超表本管理成功");
		return "redirect:"+Global.getAdminPath()+"/paramset/syssubstationmng/powerShunt/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(PowerShunt powerShunt, RedirectAttributes redirectAttributes) {
		powerShuntService.delete(powerShunt);
		return "删除超表本管理成功";
	}

}