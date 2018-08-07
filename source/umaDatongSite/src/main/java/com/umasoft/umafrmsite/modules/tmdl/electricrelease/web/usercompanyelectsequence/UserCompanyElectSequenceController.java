/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.web.usercompanyelectsequence;

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
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.usercompanyelectsequence.UserCompanyElectSequence;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.usercompanyelectsequence.UserCompanyElectSequenceService;

/**
 * 用电序列Controller
 * @author zhangky@umasoft
 * @version 2018-07-08
 */
@Controller
@RequestMapping(value = "${adminPath}/electricrelease/usercompanyelectsequence/userCompanyElectSequence")
public class UserCompanyElectSequenceController extends BaseController {

	@Autowired
	private UserCompanyElectSequenceService userCompanyElectSequenceService;
	
	@ModelAttribute
	public UserCompanyElectSequence get(@RequestParam(required=false) String id) {
		UserCompanyElectSequence entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = userCompanyElectSequenceService.get(id);
		}
		if (entity == null){
			entity = new UserCompanyElectSequence();
		}
		return entity;
	}
	
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:view")
	@RequestMapping(value = {"list", ""})
	public String list(UserCompanyElectSequence userCompanyElectSequence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserCompanyElectSequence> page = userCompanyElectSequenceService.findPage(new Page<UserCompanyElectSequence>(request, response), userCompanyElectSequence); 
		model.addAttribute("page", page);
		return "tmdl/electricrelease/usercompanyelectsequence/userCompanyElectSequenceList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:view")
	@RequestMapping(value = {"listData"})
	public Page<UserCompanyElectSequence> listData(UserCompanyElectSequence userCompanyElectSequence, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<UserCompanyElectSequence> page = userCompanyElectSequenceService.findPage(new Page<UserCompanyElectSequence>(request, response), userCompanyElectSequence); 
		return page;
	}
	

	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:view")
	@RequestMapping(value = "form")
	public String form(UserCompanyElectSequence userCompanyElectSequence, Model model) {
		model.addAttribute("userCompanyElectSequence", userCompanyElectSequence);
		return "tmdl/electricrelease/usercompanyelectsequence/userCompanyElectSequenceForm";
	}
	
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:view")
	@RequestMapping(value = "formView")
	public String formView(UserCompanyElectSequence userCompanyElectSequence, Model model) {
		model.addAttribute("userCompanyElectSequence", userCompanyElectSequence);
		return "tmdl/electricrelease/usercompanyelectsequence/userCompanyElectSequenceFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:view")
	@RequestMapping(value = {"formData"})
	public UserCompanyElectSequence formData(UserCompanyElectSequence userCompanyElectSequence, Model model) {
		return userCompanyElectSequence;
	}

	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit")
	@RequestMapping(value = "save")
	public String save(UserCompanyElectSequence userCompanyElectSequence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userCompanyElectSequence)){
			return form(userCompanyElectSequence, model);
		}
		userCompanyElectSequenceService.save(userCompanyElectSequence);
		addMessage(redirectAttributes, "保存用电序列成功");
		return "redirect:"+Global.getAdminPath()+"/electricrelease/usercompanyelectsequence/userCompanyElectSequence/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(UserCompanyElectSequence userCompanyElectSequence, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, userCompanyElectSequence)){
			return "保存用电序列失败，数据校验失败。";
		}
		userCompanyElectSequenceService.save(userCompanyElectSequence);		
		return "保存用电序列成功";
	}
	
	@RequiresPermissions("electricrelease:usercompanyelectsequence:userCompanyElectSequence:edit")
	@RequestMapping(value = "delete")
	public String delete(UserCompanyElectSequence userCompanyElectSequence, RedirectAttributes redirectAttributes) {
		userCompanyElectSequenceService.delete(userCompanyElectSequence);
		addMessage(redirectAttributes, "删除用电序列成功");
		return "redirect:"+Global.getAdminPath()+"/electricrelease/usercompanyelectsequence/userCompanyElectSequence/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(UserCompanyElectSequence userCompanyElectSequence, RedirectAttributes redirectAttributes) {
		userCompanyElectSequenceService.delete(userCompanyElectSequence);
		return "删除用电序列成功";
	}

}