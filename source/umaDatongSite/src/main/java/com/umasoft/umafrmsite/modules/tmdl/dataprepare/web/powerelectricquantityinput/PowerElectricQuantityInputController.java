/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.powerelectricquantityinput;

import java.util.ArrayList;
import java.util.Date;
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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.powerelectricquantityinput.PowerElectricQuantityInput;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.powerelectricquantityinput.PowerElectricQuantityInputService;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpriceblcpaymng.ElectricPriceBlcPayMng;

/**
 * 月度分路表底录入Controller
 * @author zhangky@umasoft
 * @version 2018-07-09
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/powerelectricquantityinput/powerElectricQuantityInput")
public class PowerElectricQuantityInputController extends BaseController {

	@Autowired
	private PowerElectricQuantityInputService powerElectricQuantityInputService;
	
	@ModelAttribute
	public PowerElectricQuantityInput get(@RequestParam(required=false) String id) {
		PowerElectricQuantityInput entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = powerElectricQuantityInputService.get(id);
		}
		if (entity == null){
			entity = new PowerElectricQuantityInput();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:view")
	@RequestMapping(value = {"list", ""})
	public String list(PowerElectricQuantityInput powerElectricQuantityInput, HttpServletRequest request, HttpServletResponse response, Model model) {
		powerElectricQuantityInput.setInsertFlag("2");
		if(powerElectricQuantityInput.getTime() == null) {
			powerElectricQuantityInput.setTime(new Date());
		}
		Page<PowerElectricQuantityInput> page = powerElectricQuantityInputService.findPage(new Page<PowerElectricQuantityInput>(request, response), powerElectricQuantityInput); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/dataprepare/powerelectricquantityinput/powerElectricQuantityInputList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:view")
	@RequestMapping(value = {"listData"})
	public Page<PowerElectricQuantityInput> listData(PowerElectricQuantityInput powerElectricQuantityInput, HttpServletRequest request, HttpServletResponse response, Model model) {
		powerElectricQuantityInput.setInsertFlag("2");
		Page<PowerElectricQuantityInput> page = powerElectricQuantityInputService.findPage(new Page<PowerElectricQuantityInput>(request, response), powerElectricQuantityInput); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "form")
	public String form(PowerElectricQuantityInput powerElectricQuantityInput, Model model, HttpServletRequest request) {
		if(powerElectricQuantityInput.getIsNewRecord()) {
			powerElectricQuantityInput.setTime(new Date());
		}
		model.addAttribute("powerElectricQuantityInput", powerElectricQuantityInput);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/dataprepare/powerelectricquantityinput/powerElectricQuantityInputForm";
	}
	
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "formView")
	public String formView(PowerElectricQuantityInput powerElectricQuantityInput, Model model) {
		model.addAttribute("powerElectricQuantityInput", powerElectricQuantityInput);
		return "tmdl/dataprepare/powerelectricquantityinput/powerElectricQuantityInputFormView";
	}
	


	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:view")
	@RequestMapping(value = {"formData"})
	public PowerElectricQuantityInput formData(PowerElectricQuantityInput powerElectricQuantityInput, Model model) {
		return powerElectricQuantityInput;
	}

	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "save")
	public String save(PowerElectricQuantityInput powerElectricQuantityInput, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, powerElectricQuantityInput)){
			return form(powerElectricQuantityInput, model,request);
		}
		powerElectricQuantityInput.setInsertFlag("2");
		powerElectricQuantityInputService.save(powerElectricQuantityInput);
		addMessage(redirectAttributes, "保存月度分路表底录入成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(PowerElectricQuantityInput powerElectricQuantityInput, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, powerElectricQuantityInput)){
			return "保存月度分路表底录入失败，数据校验失败。";
		}
		powerElectricQuantityInput.setInsertFlag("2");
		powerElectricQuantityInputService.save(powerElectricQuantityInput);		
		return "保存月度分路表底录入成功";
	}
	
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "delete")
	public String delete(PowerElectricQuantityInput powerElectricQuantityInput, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		powerElectricQuantityInputService.delete(powerElectricQuantityInput);
		addMessage(redirectAttributes, "删除月度分路表底录入成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(PowerElectricQuantityInput powerElectricQuantityInput, RedirectAttributes redirectAttributes) {
		powerElectricQuantityInputService.delete(powerElectricQuantityInput);
		return "删除月度分路表底录入成功";
	}
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "mutiformView")
	public String mutiformView(PowerElectricQuantityInput powerElectricQuantityInput, Model model) {
		powerElectricQuantityInput.setTime(new Date());
		model.addAttribute("powerElectricQuantityInput", powerElectricQuantityInput);
		return "tmdl/dataprepare/powerelectricquantityinput/powerElectricQuantityInputMutiForm";
	}
	@RequiresPermissions("dataprepare:powerelectricquantityinput:powerElectricQuantityInput:edit")
	@RequestMapping(value = "mutiSave")
	public String mutiSave(PowerElectricQuantityInput powerElectricQuantityInput, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		
		List<PowerElectricQuantityInput> inputList = powerElectricQuantityInput.getInputList();
		for(PowerElectricQuantityInput eleQuantity : inputList) {
			if(eleQuantity.getShuntId() == null || StringUtils.isBlank(eleQuantity.getShuntId().getId()))
				continue;
			eleQuantity.setTime(powerElectricQuantityInput.getTime());
			eleQuantity.setInsertFlag("2");
			powerElectricQuantityInputService.save(eleQuantity);
		}
		
		addMessage(redirectAttributes, "保存批量月度分路表底录入成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/powerelectricquantityinput/powerElectricQuantityInput/?repage" + searchUrlParam;
	}
}