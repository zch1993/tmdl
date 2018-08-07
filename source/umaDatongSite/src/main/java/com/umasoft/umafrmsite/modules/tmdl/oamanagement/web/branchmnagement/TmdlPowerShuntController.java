/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.oamanagement.web.branchmnagement;

import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt;
import com.umasoft.umafrmsite.modules.tmdl.oamanagement.service.branchmnagement.TmdlPowerShuntService;
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
 * 分路管理Controller
 * @author zhangch@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/oamanagement/branchmnagement/tmdlPowerShunt")
public class TmdlPowerShuntController extends BaseController {

	@Autowired
	private TmdlPowerShuntService tmdlPowerShuntService;
	
	@ModelAttribute
	public TmdlPowerShunt get(@RequestParam(required=false) String id) {
		TmdlPowerShunt entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlPowerShuntService.get(id);
		}
		if (entity == null){
			entity = new TmdlPowerShunt();
		}
		return entity;
	}
	
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlPowerShunt tmdlPowerShunt, HttpServletRequest request, HttpServletResponse response, Model model) {
		EncoderUrlSearchParam(request,model);
		Page<TmdlPowerShunt> page = tmdlPowerShuntService.findPage(new Page<TmdlPowerShunt>(request, response), tmdlPowerShunt);
		model.addAttribute("page", page);
		return "tmdl/oamanagement/branchmnagement/tmdlPowerShuntList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlPowerShunt> listData(TmdlPowerShunt tmdlPowerShunt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlPowerShunt> page = tmdlPowerShuntService.findPage(new Page<TmdlPowerShunt>(request, response), tmdlPowerShunt);
		return page;
	}



	/*
	 *   return JSON data
	 */
	@ResponseBody
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = {"findallData"})
	public Page<TmdlPowerShunt> findallData(TmdlPowerShunt tmdlPowerShunt, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlPowerShunt> page = tmdlPowerShuntService.findallData(new Page<TmdlPowerShunt>(request, response), tmdlPowerShunt);
		return page;
	}

	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = "form")
	public String form(TmdlPowerShunt tmdlPowerShunt, Model model) {
		model.addAttribute("tmdlPowerShunt", tmdlPowerShunt);
		return "tmdl/oamanagement/branchmnagement/tmdlPowerShuntForm";
	}
	
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlPowerShunt tmdlPowerShunt, Model model) {
		model.addAttribute("tmdlPowerShunt", tmdlPowerShunt);
		return "tmdl/oamanagement/branchmnagement/tmdlPowerShuntFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:view")
	@RequestMapping(value = {"formData"})
	public TmdlPowerShunt formData(TmdlPowerShunt tmdlPowerShunt, Model model) {
		return tmdlPowerShunt;
	}

	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:edit")
	@RequestMapping(value = "save")
	public String save(TmdlPowerShunt tmdlPowerShunt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlPowerShunt)){
			return form(tmdlPowerShunt, model);
		}
		tmdlPowerShuntService.save(tmdlPowerShunt);
		addMessage(redirectAttributes, "保存保存分路管理成功");
		return "redirect:"+Global.getAdminPath()+"/oamanagement/branchmnagement/tmdlPowerShunt/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt tmdlPowerShunt, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlPowerShunt)){
			return "保存保存分路管理失败，数据校验失败。";
		}
		tmdlPowerShuntService.save(tmdlPowerShunt);		
		return "保存保存分路管理成功";
	}
	
	@RequiresPermissions("oamanagement:branchmnagement:tmdlPowerShunt:edit")
	@RequestMapping(value = "delete")
	public String delete(com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt tmdlPowerShunt, RedirectAttributes redirectAttributes) {
		tmdlPowerShuntService.delete(tmdlPowerShunt);
		addMessage(redirectAttributes, "删除保存分路管理成功");
		return "redirect:"+Global.getAdminPath()+"/oamanagement/branchmnagement/tmdlPowerShunt/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(com.umasoft.umafrmsite.modules.tmdl.oamanagement.entity.branchmnagement.TmdlPowerShunt tmdlPowerShunt, RedirectAttributes redirectAttributes) {
		tmdlPowerShuntService.delete(tmdlPowerShunt);
		return "删除保存分路管理成功";
	}

}