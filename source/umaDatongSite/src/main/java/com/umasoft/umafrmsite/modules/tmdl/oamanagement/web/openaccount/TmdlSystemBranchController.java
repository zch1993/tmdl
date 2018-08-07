/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.web.openaccount;

import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.openaccount.TmdlSystemBranch;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 开户管理Controller
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/oamanagement/openaccount/tmdlSystemBranch")
public class TmdlSystemBranchController extends BaseController {

	@Autowired
	private com.umasoft.umafrmsite.modules.tmdl.oamanagement.service.openaccount.TmdlSystemBranchService tmdlSystemBranchService;
	
	@ModelAttribute
	public TmdlSystemBranch get(@RequestParam(required=false) String id) {
		TmdlSystemBranch entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlSystemBranchService.get(id);
		}
		if (entity == null){
			entity = new TmdlSystemBranch();
		}
		return entity;
	}
	
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlSystemBranch tmdlSystemBranch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlSystemBranch> page = tmdlSystemBranchService.findPage(new Page<TmdlSystemBranch>(request, response), tmdlSystemBranch);
		model.addAttribute("page", page);
		return "tmdl/oamanagement/openaccount/tmdlSystemBranchList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlSystemBranch> listData(TmdlSystemBranch tmdlSystemBranch, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlSystemBranch> page = tmdlSystemBranchService.findPage(new Page<TmdlSystemBranch>(request, response), tmdlSystemBranch);
		return page;
	}
	

	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:view")
	@RequestMapping(value = "form")
	public String form(TmdlSystemBranch tmdlSystemBranch, Model model) {
		model.addAttribute("tmdlSystemBranch", tmdlSystemBranch);
		return "tmdl/oamanagement/openaccount/tmdlSystemBranchForm";
	}
	
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlSystemBranch tmdlSystemBranch, Model model) {
		model.addAttribute("tmdlSystemBranch", tmdlSystemBranch);
		return "tmdl/oamanagement/openaccount/tmdlSystemBranchFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:view")
	@RequestMapping(value = {"formData"})
	public TmdlSystemBranch formData(TmdlSystemBranch tmdlSystemBranch, Model model) {
		return tmdlSystemBranch;
	}

	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:edit")
	@RequestMapping(value = "save")
	public String save(TmdlSystemBranch tmdlSystemBranch, Model model, RedirectAttributes redirectAttributes) {


		if (!beanValidator(model, tmdlSystemBranch)){
			return form(tmdlSystemBranch, model);
		}
		tmdlSystemBranchService.save(tmdlSystemBranch);
		addMessage(redirectAttributes, "保存开户管理成功");
		return "redirect:"+Global.getAdminPath()+"/oamanagement/openaccount/tmdlSystemBranch/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlSystemBranch tmdlSystemBranch, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlSystemBranch)){
			return "保存开户管理失败，数据校验失败。";
		}
		tmdlSystemBranchService.save(tmdlSystemBranch);		
		return "保存开户管理成功";
	}
	
	@RequiresPermissions("oamanagement:openaccount:tmdlSystemBranch:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlSystemBranch tmdlSystemBranch, RedirectAttributes redirectAttributes) {
		tmdlSystemBranchService.delete(tmdlSystemBranch);
		addMessage(redirectAttributes, "删除开户管理成功");
		return "redirect:"+Global.getAdminPath()+"/oamanagement/openaccount/tmdlSystemBranch/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlSystemBranch tmdlSystemBranch, RedirectAttributes redirectAttributes) {
		tmdlSystemBranchService.delete(tmdlSystemBranch);
		return "删除开户管理成功";
	}

}