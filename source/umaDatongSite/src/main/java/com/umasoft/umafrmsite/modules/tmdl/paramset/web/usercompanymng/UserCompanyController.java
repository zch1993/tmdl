/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.paramset.web.usercompanymng;



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
import com.umasoft.umafrmsite.modules.tmdl.paramset.entity.usercompanymng.UserCompany;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng.UserCompanyService;

/**
 * 用电用户管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/paramset/usercompanymng/userCompany")
public class UserCompanyController extends BaseController {

	@Autowired
	private UserCompanyService userCompanyService;
	
	@ModelAttribute
	public UserCompany get(@RequestParam(required=false) String id) {
		UserCompany entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userCompanyService.get(id);
		}
		if (entity == null){
			entity = new UserCompany();
		}
		return entity;
	}
	
	@RequiresPermissions("paramset:usercompanymng:userCompany:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserCompany userCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserCompany> page = userCompanyService.findPage(new Page<UserCompany>(request, response), userCompany);
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/paramset/usercompanymng/userCompanyList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:userCompany:view")
	@RequestMapping(value = {"listData"})
	public Page<UserCompany> listData(UserCompany userCompany, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserCompany> page = userCompanyService.findPage(new Page<UserCompany>(request, response), userCompany); 
		return page;
	}
	

	@RequiresPermissions("paramset:usercompanymng:userCompany:view")
	@RequestMapping(value = "form")
	public String form(UserCompany userCompany, Model model, HttpServletRequest request, HttpServletResponse response) {
		model.addAttribute("userCompany", userCompany);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/paramset/usercompanymng/userCompanyForm";
	}
	
	@RequiresPermissions("paramset:usercompanymng:userCompany:view")
	@RequestMapping(value = "formView")
	public String formView(UserCompany userCompany, Model model) {
		model.addAttribute("userCompany", userCompany);
		return "tmdl/paramset/usercompanymng/userCompanyFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:userCompany:view")
	@RequestMapping(value = {"formData"})
	public UserCompany formData(UserCompany userCompany, Model model) {
		return userCompany;
	}

	@RequiresPermissions("paramset:usercompanymng:userCompany:edit")
	@RequestMapping(value = "save")
	public String save(UserCompany userCompany, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, userCompany)){
			return form(userCompany, model,request,response);
		}
		userCompanyService.save(userCompany);
		addMessage(redirectAttributes, "保存用电用户管理成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/usercompanymng/userCompany/?repage"  + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("paramset:usercompanymng:userCompany:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(UserCompany userCompany, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userCompany)){
			return "保存用电用户管理失败，数据校验失败。";
		}
		userCompanyService.save(userCompany);		
		return "保存用电用户管理成功";
	}
	
	@RequiresPermissions("paramset:usercompanymng:userCompany:edit")
	@RequestMapping(value = "delete")
	public String delete(UserCompany userCompany, RedirectAttributes redirectAttributes, HttpServletRequest request, HttpServletResponse response) {
		userCompanyService.delete(userCompany);
		addMessage(redirectAttributes, "删除用电用户管理成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/paramset/usercompanymng/userCompany/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(UserCompany userCompany, RedirectAttributes redirectAttributes) {
		userCompanyService.delete(userCompany);
		return "删除用电用户管理成功";
	}


}