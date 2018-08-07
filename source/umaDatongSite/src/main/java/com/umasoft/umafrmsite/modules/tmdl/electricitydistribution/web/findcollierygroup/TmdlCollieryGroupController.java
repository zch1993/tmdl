/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.web.findcollierygroup;

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
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.entity.findcollierygroup.TmdlCollieryGroup;
import com.umasoft.umafrmsite.modules.tmdl.electricitydistribution.service.findcollierygroup.TmdlCollieryGroupService;

/**
 * 煤业和集团查询Controller
 * @author zhangch@umasoft
 * @version 2018-07-18
 */
@Controller
@RequestMapping(value = "${adminPath}/electricitydistribution/findcollierygroup/tmdlCollieryGroup")
public class TmdlCollieryGroupController extends BaseController {

	@Autowired
	private TmdlCollieryGroupService tmdlCollieryGroupService;
	
	@ModelAttribute
	public TmdlCollieryGroup get(@RequestParam(required=false) String id,@RequestParam(required=false) String time,@RequestParam(required=false) String collieryname) {
		TmdlCollieryGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlCollieryGroupService.get(id,time,collieryname);
		}
		if (entity == null){
			entity = new TmdlCollieryGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlCollieryGroup tmdlCollieryGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlCollieryGroup> page = tmdlCollieryGroupService.findPage(new Page<TmdlCollieryGroup>(request, response), tmdlCollieryGroup);
		model.addAttribute("page", page);

		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findcollierygroup/tmdlCollieryGroupList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlCollieryGroup> listData(TmdlCollieryGroup tmdlCollieryGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlCollieryGroup> page = tmdlCollieryGroupService.findPage(new Page<TmdlCollieryGroup>(request, response), tmdlCollieryGroup); 
		return page;
	}
	

	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit")
	@RequestMapping(value = "form")
	public String form(TmdlCollieryGroup tmdlCollieryGroup, Model model, HttpServletRequest request) {
		model.addAttribute("tmdlCollieryGroup", tmdlCollieryGroup);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/electricitydistribution/findcollierygroup/tmdlCollieryGroupForm";
	}
	
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlCollieryGroup tmdlCollieryGroup, Model model) {
		model.addAttribute("tmdlCollieryGroup", tmdlCollieryGroup);
		return "tmdl/electricitydistribution/findcollierygroup/tmdlCollieryGroupFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:view")
	@RequestMapping(value = {"formData"})
	public TmdlCollieryGroup formData(TmdlCollieryGroup tmdlCollieryGroup, Model model) {
		return tmdlCollieryGroup;
	}

	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit")
	@RequestMapping(value = "save")
	public String save(TmdlCollieryGroup tmdlCollieryGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, tmdlCollieryGroup)){
			return form(tmdlCollieryGroup, model,request);
		}
		tmdlCollieryGroupService.save(tmdlCollieryGroup);
		addMessage(redirectAttributes, "保存煤业和集团查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findcollierygroup/tmdlCollieryGroup/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlCollieryGroup tmdlCollieryGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlCollieryGroup)){
			return "保存煤业和集团查询失败，数据校验失败。";
		}
		tmdlCollieryGroupService.save(tmdlCollieryGroup);		
		return "保存煤业和集团查询成功";
	}
	
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlCollieryGroup tmdlCollieryGroup, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		tmdlCollieryGroupService.delete(tmdlCollieryGroup);
		addMessage(redirectAttributes, "删除煤业和集团查询成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/electricitydistribution/findcollierygroup/tmdlCollieryGroup/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("electricitydistribution:findcollierygroup:tmdlCollieryGroup:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlCollieryGroup tmdlCollieryGroup, RedirectAttributes redirectAttributes) {
		tmdlCollieryGroupService.delete(tmdlCollieryGroup);
		return "删除煤业和集团查询成功";
	}

}