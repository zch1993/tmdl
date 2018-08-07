/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.substationmng;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationmng.TmdlSubstation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationmng.TmdlSubstationService;

/**
 * 变电站管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-05
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/substationmng/tmdlSubstation")
public class TmdlSubstationController extends BaseController {

	@Autowired
	private TmdlSubstationService tmdlSubstationService;
	
	@ModelAttribute
	public TmdlSubstation get(@RequestParam(required=false) String id) {
		TmdlSubstation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlSubstationService.get(id);
		}
		if (entity == null){
			entity = new TmdlSubstation();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlSubstation tmdlSubstation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlSubstation> page = tmdlSubstationService.findPage(new Page<TmdlSubstation>(request, response), tmdlSubstation); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
				EncoderUrlSearchParam(request,model);
		return "tmdl/paramset/substationmng/tmdlSubstationList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlSubstation> listData(TmdlSubstation tmdlSubstation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlSubstation> page = tmdlSubstationService.findPage(new Page<TmdlSubstation>(request, response), tmdlSubstation); 
		return page;
	}
	

	@RequiresPermissions("paramset:substationmng:tmdlSubstation:view")
	@RequestMapping(value = "form")
	public String form(TmdlSubstation tmdlSubstation, Model model, HttpServletRequest request) {
		model.addAttribute("tmdlSubstation", tmdlSubstation);
		// 页面搜索条件、页面排序等参数
				setUrlSearchParam(request,model);
		return "tmdl/paramset/substationmng/tmdlSubstationForm";
	}
	
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlSubstation tmdlSubstation, Model model) {
		model.addAttribute("tmdlSubstation", tmdlSubstation);
		return "tmdl/paramset/substationmng/tmdlSubstationFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:view")
	@RequestMapping(value = {"formData"})
	public TmdlSubstation formData(TmdlSubstation tmdlSubstation, Model model) {
		return tmdlSubstation;
	}

	@RequiresPermissions("paramset:substationmng:tmdlSubstation:edit")
	@RequestMapping(value = "save")
	public String save(TmdlSubstation tmdlSubstation, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, tmdlSubstation)){
			return form(tmdlSubstation, model,request);
		}
		tmdlSubstationService.save(tmdlSubstation);
		addMessage(redirectAttributes, "保存变电站管理成功");
		
		// 页面搜索条件、页面排序等参数
				String 	searchUrlParam = decodeUrlSearchParam(request);
				if(StringUtils.isNotBlank(searchUrlParam)) {
					searchUrlParam = "&" + searchUrlParam;
				}
		return "redirect:"+Global.getAdminPath()+"/paramset/substationmng/tmdlSubstation/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlSubstation tmdlSubstation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlSubstation)){
			return "保存变电站管理失败，数据校验失败。";
		}
		tmdlSubstationService.save(tmdlSubstation);		
		return "保存变电站管理成功";
	}
	
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlSubstation tmdlSubstation, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		tmdlSubstationService.delete(tmdlSubstation);
		addMessage(redirectAttributes, "删除变电站管理成功");
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/substationmng/tmdlSubstation/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlSubstation tmdlSubstation, RedirectAttributes redirectAttributes) {
		tmdlSubstationService.delete(tmdlSubstation);
		return "删除变电站管理成功";
	}

	/***
	 * 测试变电站和远程采集数据库同步，其他地方调用方式：tmdlSubstationService.dataSynchro();
	 * @param tmdlSubstation
	 * @param redirectAttributes
	 * @return
	 */
	@ResponseBody
	@RequiresPermissions("paramset:substationmng:tmdlSubstation:edit")
	@RequestMapping(value = {"dataSynchro"})
	public String dataSynchro(TmdlSubstation tmdlSubstation, RedirectAttributes redirectAttributes) {
		tmdlSubstationService.dataSynchro();
		return "变电站数据同步成功";
	}
}