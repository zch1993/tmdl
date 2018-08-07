/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.usercompanymng;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.SystemCollieryGroup;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng.SystemCollieryGroupService;

/**
 * 矿业和集团比例分配Controller
 * @author zhangky@umasoft
 * @version 2018-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/usercompanymng/systemCollieryGroup")
public class SystemCollieryGroupController extends BaseController {

	@Autowired
	private SystemCollieryGroupService systemCollieryGroupService;
	
	@ModelAttribute
	public SystemCollieryGroup get(@RequestParam(required=false) String id) {
		SystemCollieryGroup entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = systemCollieryGroupService.get(id);
		}
		if (entity == null){
			entity = new SystemCollieryGroup();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:view")
	@RequestMapping(value = {"list", ""})
	public String list(SystemCollieryGroup systemCollieryGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SystemCollieryGroup> page = systemCollieryGroupService.findPage(new Page<SystemCollieryGroup>(request, response), systemCollieryGroup); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/paramset/usercompanymng/systemCollieryGroupList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:view")
	@RequestMapping(value = {"listData"})
	public Page<SystemCollieryGroup> listData(SystemCollieryGroup systemCollieryGroup, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SystemCollieryGroup> page = systemCollieryGroupService.findPage(new Page<SystemCollieryGroup>(request, response), systemCollieryGroup); 
		return page;
	}
	

	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:edit")
	@RequestMapping(value = "form")
	public String form(SystemCollieryGroup systemCollieryGroup, Model model, HttpServletRequest request) {
		model.addAttribute("systemCollieryGroup", systemCollieryGroup);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/paramset/usercompanymng/systemCollieryGroupForm";
	}
	
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:view")
	@RequestMapping(value = "formView")
	public String formView(SystemCollieryGroup systemCollieryGroup, Model model) {
		model.addAttribute("systemCollieryGroup", systemCollieryGroup);
		return "tmdl/paramset/usercompanymng/systemCollieryGroupFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:view")
	@RequestMapping(value = {"formData"})
	public SystemCollieryGroup formData(SystemCollieryGroup systemCollieryGroup, Model model) {
		return systemCollieryGroup;
	}

	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:edit")
	@RequestMapping(value = "save")
	public String save(SystemCollieryGroup systemCollieryGroup, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, systemCollieryGroup)){
			return form(systemCollieryGroup, model,request);
		}
		systemCollieryGroupService.save(systemCollieryGroup);
		addMessage(redirectAttributes, "保存矿业和集团比例分配成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/usercompanymng/systemCollieryGroup/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(SystemCollieryGroup systemCollieryGroup, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, systemCollieryGroup)){
			return "保存矿业和集团比例分配失败，数据校验失败。";
		}
		systemCollieryGroupService.save(systemCollieryGroup);		
		return "保存矿业和集团比例分配成功";
	}
	
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:edit")
	@RequestMapping(value = "delete")
	public String delete(SystemCollieryGroup systemCollieryGroup, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		systemCollieryGroupService.delete(systemCollieryGroup);
		addMessage(redirectAttributes, "删除矿业和集团比例分配成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/usercompanymng/systemCollieryGroup/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:systemCollieryGroup:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(SystemCollieryGroup systemCollieryGroup, RedirectAttributes redirectAttributes) {
		systemCollieryGroupService.delete(systemCollieryGroup);
		return "删除矿业和集团比例分配成功";
	}

}