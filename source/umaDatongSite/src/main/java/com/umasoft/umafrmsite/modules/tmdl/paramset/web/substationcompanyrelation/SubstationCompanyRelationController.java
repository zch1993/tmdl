/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.substationcompanyrelation;

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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.substationcompanyrelation.SubstationCompanyRelation;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationcompanyrelation.SubstationCompanyRelationService;

/**
 * 变电站和用电单位对应关系Controller
 * @author zhangky@umasoft
 * @version 2018-07-11
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/substationcompanyrelation/substationCompanyRelation")
public class SubstationCompanyRelationController extends BaseController {

	@Autowired
	private SubstationCompanyRelationService substationCompanyRelationService;
	
	@ModelAttribute
	public SubstationCompanyRelation get(@RequestParam(required=false) String id) {
		SubstationCompanyRelation entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = substationCompanyRelationService.get(id);
		}
		if (entity == null){
			entity = new SubstationCompanyRelation();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:view")
	@RequestMapping(value = {"list", ""})
	public String list(SubstationCompanyRelation substationCompanyRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SubstationCompanyRelation> page = substationCompanyRelationService.findPage(new Page<SubstationCompanyRelation>(request, response), substationCompanyRelation); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/paramset/substationcompanyrelation/substationCompanyRelationList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:view")
	@RequestMapping(value = {"listData"})
	public Page<SubstationCompanyRelation> listData(SubstationCompanyRelation substationCompanyRelation, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<SubstationCompanyRelation> page = substationCompanyRelationService.findPage(new Page<SubstationCompanyRelation>(request, response), substationCompanyRelation); 
		return page;
	}
	

	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:edit")
	@RequestMapping(value = "form")
	public String form(SubstationCompanyRelation substationCompanyRelation, Model model, HttpServletRequest request) {
		model.addAttribute("substationCompanyRelation", substationCompanyRelation);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/paramset/substationcompanyrelation/substationCompanyRelationForm";
	}
	
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:view")
	@RequestMapping(value = "formView")
	public String formView(SubstationCompanyRelation substationCompanyRelation, Model model) {
		model.addAttribute("substationCompanyRelation", substationCompanyRelation);
		return "tmdl/paramset/substationcompanyrelation/substationCompanyRelationFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:view")
	@RequestMapping(value = {"formData"})
	public SubstationCompanyRelation formData(SubstationCompanyRelation substationCompanyRelation, Model model) {
		return substationCompanyRelation;
	}

	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:edit")
	@RequestMapping(value = "save")
	public String save(SubstationCompanyRelation substationCompanyRelation, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, substationCompanyRelation)){
			return form(substationCompanyRelation, model,request);
		}
		substationCompanyRelationService.save(substationCompanyRelation);
		addMessage(redirectAttributes, "保存变电站和用电单位对应关系成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/substationcompanyrelation/substationCompanyRelation/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(SubstationCompanyRelation substationCompanyRelation, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, substationCompanyRelation)){
			return "保存变电站和用电单位对应关系失败，数据校验失败。";
		}
		substationCompanyRelationService.save(substationCompanyRelation);		
		return "保存变电站和用电单位对应关系成功";
	}
	
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:edit")
	@RequestMapping(value = "delete")
	public String delete(SubstationCompanyRelation substationCompanyRelation, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		substationCompanyRelationService.delete(substationCompanyRelation);
		addMessage(redirectAttributes, "删除变电站和用电单位对应关系成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/substationcompanyrelation/substationCompanyRelation/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("paramset:substationcompanyrelation:substationCompanyRelation:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(SubstationCompanyRelation substationCompanyRelation, RedirectAttributes redirectAttributes) {
		substationCompanyRelationService.delete(substationCompanyRelation);
		return "删除变电站和用电单位对应关系成功";
	}

}