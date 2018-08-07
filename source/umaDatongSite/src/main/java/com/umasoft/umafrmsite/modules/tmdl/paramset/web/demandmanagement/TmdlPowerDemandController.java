/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.demandmanagement;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.demandmanagement.TmdlPowerDemand;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.demandmanagement.TmdlPowerDemandService;

/**
 * 需量管理Controller
 * @author zhangch@umasoft
 * @version 2018-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/demandmanagement/tmdlPowerDemand")
public class TmdlPowerDemandController extends BaseController {

	@Autowired
	private TmdlPowerDemandService tmdlPowerDemandService;
	
	@ModelAttribute
	public TmdlPowerDemand get(@RequestParam(required=false) String id) {
		TmdlPowerDemand entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlPowerDemandService.get(id);
		}
		if (entity == null){
			entity = new TmdlPowerDemand();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlPowerDemand tmdlPowerDemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlPowerDemand> page = tmdlPowerDemandService.findPage(new Page<TmdlPowerDemand>(request, response), tmdlPowerDemand); 
		model.addAttribute("page", page);
		return "tmdl/paramset/demandmanagement/tmdlPowerDemandList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlPowerDemand> listData(TmdlPowerDemand tmdlPowerDemand, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlPowerDemand> page = tmdlPowerDemandService.findPage(new Page<TmdlPowerDemand>(request, response), tmdlPowerDemand); 
		return page;
	}
	

	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:view")
	@RequestMapping(value = "form")
	public String form(TmdlPowerDemand tmdlPowerDemand, Model model) {
		model.addAttribute("tmdlPowerDemand", tmdlPowerDemand);
		return "tmdl/paramset/demandmanagement/tmdlPowerDemandForm";
	}
	
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlPowerDemand tmdlPowerDemand, Model model) {
		model.addAttribute("tmdlPowerDemand", tmdlPowerDemand);
		return "tmdl/paramset/demandmanagement/tmdlPowerDemandFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:view")
	@RequestMapping(value = {"formData"})
	public TmdlPowerDemand formData(TmdlPowerDemand tmdlPowerDemand, Model model) {
		return tmdlPowerDemand;
	}

	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:edit")
	@RequestMapping(value = "save")
	public String save(TmdlPowerDemand tmdlPowerDemand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlPowerDemand)){
			return form(tmdlPowerDemand, model);
		}
		tmdlPowerDemandService.save(tmdlPowerDemand);
		addMessage(redirectAttributes, "保存需量管理成功");
		return "redirect:"+Global.getAdminPath()+"/paramset/demandmanagement/tmdlPowerDemand/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlPowerDemand tmdlPowerDemand, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlPowerDemand)){
			return "保存需量管理失败，数据校验失败。";
		}
		tmdlPowerDemandService.save(tmdlPowerDemand);		
		return "保存需量管理成功";
	}
	
	@RequiresPermissions("paramset:demandmanagement:tmdlPowerDemand:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlPowerDemand tmdlPowerDemand, RedirectAttributes redirectAttributes) {
		tmdlPowerDemandService.delete(tmdlPowerDemand);
		addMessage(redirectAttributes, "删除需量管理成功");
		return "redirect:"+Global.getAdminPath()+"/paramset/demandmanagement/tmdlPowerDemand/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlPowerDemand tmdlPowerDemand, RedirectAttributes redirectAttributes) {
		tmdlPowerDemandService.delete(tmdlPowerDemand);
		return "删除需量管理成功";
	}
	@ResponseBody
	@RequestMapping(value = {"findfl"})
	public Map<String,String> findfl(@RequestParam(value = "id") String id ){
		return tmdlPowerDemandService.findfl(id);
	}

}