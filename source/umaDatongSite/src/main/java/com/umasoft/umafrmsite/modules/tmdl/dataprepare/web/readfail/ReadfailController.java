/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.readfail;

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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.readfail.Readfail;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.readfail.ReadfailService;

/**
 * 读取失败Controller
 * @author zhangch@umasoft
 * @version 2018-07-16
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/readfail/readfail")
public class ReadfailController extends BaseController {

	@Autowired
	private ReadfailService readfailService;
	
	@ModelAttribute
	public Readfail get(@RequestParam(required=false) String id) {
		Readfail entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = readfailService.get(id);
		}
		if (entity == null){
			entity = new Readfail();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:readfail:readfail:view")
	@RequestMapping(value = {"list", ""})
	public String list(Readfail readfail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Readfail> page = readfailService.findPage(new Page<Readfail>(request, response), readfail); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/dataprepare/readfail/readfailList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:readfail:readfail:view")
	@RequestMapping(value = {"listData"})
	public Page<Readfail> listData(Readfail readfail, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Readfail> page = readfailService.findPage(new Page<Readfail>(request, response), readfail); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:readfail:readfail:edit")
	@RequestMapping(value = "form")
	public String form(Readfail readfail, Model model, HttpServletRequest request) {
		model.addAttribute("readfail", readfail);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/dataprepare/readfail/readfailForm";
	}
	
	@RequiresPermissions("dataprepare:readfail:readfail:view")
	@RequestMapping(value = "formView")
	public String formView(Readfail readfail, Model model) {
		model.addAttribute("readfail", readfail);
		return "tmdl/dataprepare/readfail/readfailFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:readfail:readfail:view")
	@RequestMapping(value = {"formData"})
	public Readfail formData(Readfail readfail, Model model) {
		return readfail;
	}

	@RequiresPermissions("dataprepare:readfail:readfail:edit")
	@RequestMapping(value = "save")
	public String save(Readfail readfail, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, readfail)){
			return form(readfail, model,request);
		}
		readfailService.save(readfail);
		addMessage(redirectAttributes, "保存读取失败成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/readfail/readfail/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:readfail:readfail:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(Readfail readfail, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, readfail)){
			return "保存读取失败失败，数据校验失败。";
		}
		readfailService.save(readfail);		
		return "保存读取失败成功";
	}
	
	@RequiresPermissions("dataprepare:readfail:readfail:edit")
	@RequestMapping(value = "delete")
	public String delete(Readfail readfail, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		readfailService.delete(readfail);
		addMessage(redirectAttributes, "删除读取失败成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/readfail/readfail/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("dataprepare:readfail:readfail:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(Readfail readfail, RedirectAttributes redirectAttributes) {
		readfailService.delete(readfail);
		return "删除读取失败成功";
	}

}