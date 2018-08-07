/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.web.companypsupplys;

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
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.entity.companypsupplys.TmdlUsePowerSupply;
import com.umasoft.umafrmsite.modules.tmdl.electricalanalysis.service.companypsupplys.TmdlUsePowerSupplyService;

/**
 * 录入供电情况Controller
 * @author zhangch@umasoft
 * @version 2018-07-07
 */
@Controller
@RequestMapping(value = "${adminPath}/electricalanalysis/companypsupplys/tmdlUsePowerSupply")
public class TmdlUsePowerSupplyController extends BaseController {

	@Autowired
	private TmdlUsePowerSupplyService tmdlUsePowerSupplyService;
	
	@ModelAttribute
	public TmdlUsePowerSupply get(@RequestParam(required=false) String id) {
		TmdlUsePowerSupply entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = tmdlUsePowerSupplyService.get(id);
		}
		if (entity == null){
			entity = new TmdlUsePowerSupply();
		}
		return entity;
	}
	
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:view")
	@RequestMapping(value = {"list", ""})
	public String list(TmdlUsePowerSupply tmdlUsePowerSupply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlUsePowerSupply> page = tmdlUsePowerSupplyService.findPage(new Page<TmdlUsePowerSupply>(request, response), tmdlUsePowerSupply); 
		model.addAttribute("page", page);
		return "tmdl/electricalanalysis/companypsupplys/tmdlUsePowerSupplyList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:view")
	@RequestMapping(value = {"listData"})
	public Page<TmdlUsePowerSupply> listData(TmdlUsePowerSupply tmdlUsePowerSupply, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<TmdlUsePowerSupply> page = tmdlUsePowerSupplyService.findPage(new Page<TmdlUsePowerSupply>(request, response), tmdlUsePowerSupply); 
		return page;
	}
	

	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:view")
	@RequestMapping(value = "form")
	public String form(TmdlUsePowerSupply tmdlUsePowerSupply, Model model) {
		model.addAttribute("tmdlUsePowerSupply", tmdlUsePowerSupply);
		return "tmdl/electricalanalysis/companypsupplys/tmdlUsePowerSupplyForm";
	}
	
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:view")
	@RequestMapping(value = "formView")
	public String formView(TmdlUsePowerSupply tmdlUsePowerSupply, Model model) {
		model.addAttribute("tmdlUsePowerSupply", tmdlUsePowerSupply);
		return "tmdl/electricalanalysis/companypsupplys/tmdlUsePowerSupplyFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:view")
	@RequestMapping(value = {"formData"})
	public TmdlUsePowerSupply formData(TmdlUsePowerSupply tmdlUsePowerSupply, Model model) {
		return tmdlUsePowerSupply;
	}

	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit")
	@RequestMapping(value = "save")
	public String save(TmdlUsePowerSupply tmdlUsePowerSupply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlUsePowerSupply)){
			return form(tmdlUsePowerSupply, model);
		}
		tmdlUsePowerSupplyService.save(tmdlUsePowerSupply);
		addMessage(redirectAttributes, "保存录入供电情况成功");
		return "redirect:"+Global.getAdminPath()+"/electricalanalysis/companypsupplys/tmdlUsePowerSupply/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(TmdlUsePowerSupply tmdlUsePowerSupply, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, tmdlUsePowerSupply)){
			return "保存录入供电情况失败，数据校验失败。";
		}
		tmdlUsePowerSupplyService.save(tmdlUsePowerSupply);		
		return "保存录入供电情况成功";
	}
	
	@RequiresPermissions("electricalanalysis:companypsupplys:tmdlUsePowerSupply:edit")
	@RequestMapping(value = "delete")
	public String delete(TmdlUsePowerSupply tmdlUsePowerSupply, RedirectAttributes redirectAttributes) {
		tmdlUsePowerSupplyService.delete(tmdlUsePowerSupply);
		addMessage(redirectAttributes, "删除录入供电情况成功");
		return "redirect:"+Global.getAdminPath()+"/electricalanalysis/companypsupplys/tmdlUsePowerSupply/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(TmdlUsePowerSupply tmdlUsePowerSupply, RedirectAttributes redirectAttributes) {
		tmdlUsePowerSupplyService.delete(tmdlUsePowerSupply);
		return "删除录入供电情况成功";
	}

}