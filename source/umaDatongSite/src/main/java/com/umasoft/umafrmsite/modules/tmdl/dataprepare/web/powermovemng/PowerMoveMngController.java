/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.powermovemng;

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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powermovemng.PowerMoveMng;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.powermovemng.PowerMoveMngService;

/**
 * 转入转出电量录入Controller
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/powermovemng/powerMoveMng")
public class PowerMoveMngController extends BaseController {

	@Autowired
	private PowerMoveMngService powerMoveMngService;
	
	@ModelAttribute
	public PowerMoveMng get(@RequestParam(required=false) String id) {
		PowerMoveMng entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = powerMoveMngService.get(id);
		}
		if (entity == null){
			entity = new PowerMoveMng();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:view")
	@RequestMapping(value = {"list", ""})
	public String list(PowerMoveMng powerMoveMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PowerMoveMng> page = powerMoveMngService.findPage(new Page<PowerMoveMng>(request, response), powerMoveMng); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/dataprepare/powermovemng/powerMoveMngList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:view")
	@RequestMapping(value = {"listData"})
	public Page<PowerMoveMng> listData(PowerMoveMng powerMoveMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<PowerMoveMng> page = powerMoveMngService.findPage(new Page<PowerMoveMng>(request, response), powerMoveMng); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:edit")
	@RequestMapping(value = "form")
	public String form(PowerMoveMng powerMoveMng, Model model, HttpServletRequest request) {
		model.addAttribute("powerMoveMng", powerMoveMng);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/dataprepare/powermovemng/powerMoveMngForm";
	}
	
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:view")
	@RequestMapping(value = "formView")
	public String formView(PowerMoveMng powerMoveMng, Model model) {
		model.addAttribute("powerMoveMng", powerMoveMng);
		return "tmdl/dataprepare/powermovemng/powerMoveMngFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:view")
	@RequestMapping(value = {"formData"})
	public PowerMoveMng formData(PowerMoveMng powerMoveMng, Model model) {
		return powerMoveMng;
	}

	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:edit")
	@RequestMapping(value = "save")
	public String save(PowerMoveMng powerMoveMng, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, powerMoveMng)){
			return form(powerMoveMng, model,request);
		}
		powerMoveMngService.save(powerMoveMng);
		addMessage(redirectAttributes, "保存转入转出电量录入成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/powermovemng/powerMoveMng/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(PowerMoveMng powerMoveMng, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, powerMoveMng)){
			return "保存转入转出电量录入失败，数据校验失败。";
		}
		powerMoveMngService.save(powerMoveMng);		
		return "保存转入转出电量录入成功";
	}
	
	@RequiresPermissions("dataprepare:powermovemng:powerMoveMng:edit")
	@RequestMapping(value = "delete")
	public String delete(PowerMoveMng powerMoveMng, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		powerMoveMngService.delete(powerMoveMng);
		addMessage(redirectAttributes, "删除转入转出电量录入成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/powermovemng/powerMoveMng/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(PowerMoveMng powerMoveMng, RedirectAttributes redirectAttributes) {
		powerMoveMngService.delete(powerMoveMng);
		return "删除转入转出电量录入成功";
	}

}