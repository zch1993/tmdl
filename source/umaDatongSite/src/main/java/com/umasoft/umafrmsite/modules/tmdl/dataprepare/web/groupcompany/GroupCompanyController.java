/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.groupcompany;

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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.groupcompany.GroupCompany;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.groupcompany.GroupCompanyService;

/**
 * 集团企业管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/groupcompany/groupCompany")
public class GroupCompanyController extends BaseController {

	@Autowired
	private GroupCompanyService groupCompanyService;
	
	@ModelAttribute
	public GroupCompany get(@RequestParam(required=false) String id) {
		GroupCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = groupCompanyService.get(id);
		}
		if (entity == null){
			entity = new GroupCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(GroupCompany groupCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GroupCompany> page = groupCompanyService.findPage(new Page<GroupCompany>(request, response), groupCompany); 
		model.addAttribute("page", page);
		return "tmdl/dataprepare/groupcompany/groupCompanyList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:view")
	@RequestMapping(value = {"listData"})
	public Page<GroupCompany> listData(GroupCompany groupCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<GroupCompany> page = groupCompanyService.findPage(new Page<GroupCompany>(request, response), groupCompany); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:groupcompany:groupCompany:view")
	@RequestMapping(value = "form")
	public String form(GroupCompany groupCompany, Model model) {
		model.addAttribute("groupCompany", groupCompany);
		return "tmdl/dataprepare/groupcompany/groupCompanyForm";
	}
	
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:view")
	@RequestMapping(value = "formView")
	public String formView(GroupCompany groupCompany, Model model) {
		model.addAttribute("groupCompany", groupCompany);
		return "tmdl/dataprepare/groupcompany/groupCompanyFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:view")
	@RequestMapping(value = {"formData"})
	public GroupCompany formData(GroupCompany groupCompany, Model model) {
		return groupCompany;
	}

	@RequiresPermissions("dataprepare:groupcompany:groupCompany:edit")
	@RequestMapping(value = "save")
	public String save(GroupCompany groupCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, groupCompany)){
			return form(groupCompany, model);
		}
		groupCompanyService.save(groupCompany);
		addMessage(redirectAttributes, "保存集团企业管理成功");
		return "redirect:"+Global.getAdminPath()+"/dataprepare/groupcompany/groupCompany/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(GroupCompany groupCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, groupCompany)){
			return "保存集团企业管理失败，数据校验失败。";
		}
		groupCompanyService.save(groupCompany);		
		return "保存集团企业管理成功";
	}
	
	@RequiresPermissions("dataprepare:groupcompany:groupCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(GroupCompany groupCompany, RedirectAttributes redirectAttributes) {
		groupCompanyService.delete(groupCompany);
		addMessage(redirectAttributes, "删除集团企业管理成功");
		return "redirect:"+Global.getAdminPath()+"/dataprepare/groupcompany/groupCompany/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(GroupCompany groupCompany, RedirectAttributes redirectAttributes) {
		groupCompanyService.delete(groupCompany);
		return "删除集团企业管理成功";
	}

}