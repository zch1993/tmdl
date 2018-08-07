/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.dataprepare.web.bottomcollection;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.umasoft.umafrmsite.modules.tmdl.oamanagement.service.branchmnagement.TmdlPowerShuntService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationmng.TmdlSubstationService;
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
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.entity.bottomcollection.Bottomcollection;
import com.umasoft.umafrmsite.modules.tmdl.dataprepare.service.bottomcollection.BottomcollectionService;

/**
 * 表底采集Controller
 * @author zhangch@umasoft
 * @version 2018-07-13
 */
@Controller
@RequestMapping(value = "${adminPath}/dataprepare/bottomcollection/bottomcollection")
public class BottomcollectionController extends BaseController {

	@Autowired
	private BottomcollectionService bottomcollectionService;

	@Autowired
	private TmdlPowerShuntService tmdlpowershuntService;

	@Autowired
	private TmdlSubstationService tmdlsubstationService;
	
	@ModelAttribute
	public Bottomcollection get(@RequestParam(required=false) String id) {
		Bottomcollection entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = bottomcollectionService.get(id);
		}
		if (entity == null){
			entity = new Bottomcollection();
		}
		return entity;
	}
	
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:view")
	@RequestMapping(value = {"list", ""})
	public String list(Bottomcollection bottomcollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Bottomcollection> page = bottomcollectionService.findPage(new Page<Bottomcollection>(request, response), bottomcollection); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);
		return "tmdl/dataprepare/bottomcollection/bottomcollectionList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:view")
	@RequestMapping(value = {"listData"})
	public Page<Bottomcollection> listData(Bottomcollection bottomcollection, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<Bottomcollection> page = bottomcollectionService.findPage(new Page<Bottomcollection>(request, response), bottomcollection); 
		return page;
	}
	

	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:edit")
	@RequestMapping(value = "form")
	public String form(Bottomcollection bottomcollection, Model model, HttpServletRequest request) {
		model.addAttribute("bottomcollection", bottomcollection);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);
		return "tmdl/dataprepare/bottomcollection/bottomcollectionForm";
	}
	
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:view")
	@RequestMapping(value = "formView")
	public String formView(Bottomcollection bottomcollection, Model model) {
		model.addAttribute("bottomcollection", bottomcollection);
		return "tmdl/dataprepare/bottomcollection/bottomcollectionFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:view")
	@RequestMapping(value = {"formData"})
	public Bottomcollection formData(Bottomcollection bottomcollection, Model model) {
		return bottomcollection;
	}

	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:edit")
	@RequestMapping(value = "save")
	public String save(Bottomcollection bottomcollection, Model model, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		if (!beanValidator(model, bottomcollection)){
			return form(bottomcollection, model,request);
		}
		bottomcollectionService.save(bottomcollection);
		addMessage(redirectAttributes, "保存表底采集保存成功成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/bottomcollection/bottomcollection/?repage" + searchUrlParam;
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(Bottomcollection bottomcollection, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, bottomcollection)){
			return "保存表底采集保存成功失败，数据校验失败。";
		}
		bottomcollectionService.save(bottomcollection);		
		return "保存表底采集保存成功成功";
	}
	
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:edit")
	@RequestMapping(value = "delete")
	public String delete(Bottomcollection bottomcollection, RedirectAttributes redirectAttributes, HttpServletRequest request) {
		bottomcollectionService.delete(bottomcollection);
		addMessage(redirectAttributes, "删除表底采集保存成功成功");
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return "redirect:"+Global.getAdminPath()+"/dataprepare/bottomcollection/bottomcollection/?repage" + searchUrlParam;
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("dataprepare:bottomcollection:bottomcollection:edit")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(Bottomcollection bottomcollection, RedirectAttributes redirectAttributes) {
		bottomcollectionService.delete(bottomcollection);
		return "删除表底采集保存成功成功";
	}

	@ResponseBody
	@RequestMapping(value = {"dataSynchro"})
	public String dataSynchro(HttpServletRequest request){
		tmdlsubstationService.dataSynchro();
		tmdlpowershuntService.dataSynchrobyFL();
		bottomcollectionService.dataSynchrobyDL();
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
		return"OK";
	}

}