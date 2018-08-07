/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.web.electricpricemng;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
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
import com.umasoft.umafrmsite.common.utils.Encodes;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpricemng.ElectricPriceMng;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.electricpricemng.ElectricPriceMngService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng.UserCompanyService;

/**
 * 电价管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/electricrelease/electricpricemng/electricPriceMng")
public class ElectricPriceMngController extends BaseController {

	@Autowired
	private ElectricPriceMngService electricPriceMngService;
	@Autowired
	private UserCompanyService userCompanyService;
	
	@ModelAttribute
	public ElectricPriceMng get(@RequestParam(required=false) String id) {
		ElectricPriceMng entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = electricPriceMngService.get(id);
		}
		if (entity == null){
			entity = new ElectricPriceMng();
		}
		return entity;
	}
	
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:view")
	@RequestMapping(value = {"list", ""})
	public String list(ElectricPriceMng electricPriceMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*
		  String addtime = request.getParameter("addtime");
		 
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				java.util.Date dt=sdf.parse(addtime+"-11 11:11:11");
				electricPriceMng.setAddtime(dt);
			}catch(Exception e) {
				
			}
		}
		*/
		model.addAttribute("unitid.id", request.getParameter("unitid.id"));
		model.addAttribute("addtime", request.getParameter("addtime"));
		Page<ElectricPriceMng> page = electricPriceMngService.findPage(new Page<ElectricPriceMng>(request, response), electricPriceMng); 
		model.addAttribute("page", page);
		
		// 页面搜索条件、页面排序等参数
		EncoderUrlSearchParam(request,model);		
		return "tmdl/electricrelease/electricpricemng/electricPriceMngList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:view")
	@RequestMapping(value = {"listData"})
	public Page<ElectricPriceMng> listData(ElectricPriceMng electricPriceMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ElectricPriceMng> page = electricPriceMngService.findPage(new Page<ElectricPriceMng>(request, response), electricPriceMng); 
		return page;
	}
	

	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:view")
	@RequestMapping(value = "form")
	public String form(ElectricPriceMng electricPriceMng, Model model,HttpServletRequest request, HttpServletResponse response) {
		String companyid = request.getParameter("unitid.id");
		if (electricPriceMng.getIsNewRecord()){
			// 如果有单位ID，而没有当前记录，是根据单位新建价格记录
			
			if(StringUtils.isNotBlank(companyid)) {
				electricPriceMng.setUnitid(userCompanyService.get(companyid));
				
			}
		}
		companyid = request.getParameter("unitidid");
		model.addAttribute("unitidparam", companyid);
		
		String addtime = request.getParameter("addtimeparam");
		 // 带参数为设置某个月的电价
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}
		model.addAttribute("addtimeparam", addtime);
		String pageno = request.getParameter("pageno");
		model.addAttribute("pagenoparam", pageno);
		
		model.addAttribute("electricPriceMng", electricPriceMng);
		
		// 页面搜索条件、页面排序等参数
		setUrlSearchParam(request,model);	
		return "tmdl/electricrelease/electricpricemng/electricPriceMngForm";
	}
	
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:view")
	@RequestMapping(value = "formView")
	public String formView(ElectricPriceMng electricPriceMng, Model model) {
		model.addAttribute("electricPriceMng", electricPriceMng);
		return "tmdl/electricrelease/electricpricemng/electricPriceMngFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:view")
	@RequestMapping(value = {"formData"})
	public ElectricPriceMng formData(ElectricPriceMng electricPriceMng, Model model) {
		return electricPriceMng;
	}

	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:edit")
	@RequestMapping(value = "save")
	public String save(ElectricPriceMng electricPriceMng, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, electricPriceMng)){
			return form(electricPriceMng, model,request,response);
		}
		electricPriceMngService.save(electricPriceMng);
		addMessage(redirectAttributes, "保存电价管理成功");
		
		String companyid = request.getParameter("unitidparam");
		String addtime = request.getParameter("addtimeparam");
		String pageno = request.getParameter("pagenoparam");
		
		redirectAttributes.addFlashAttribute("pageno",pageno);
		if(StringUtils.isNotBlank(companyid)) {
			electricPriceMng.setUnitid(userCompanyService.get(companyid));			
		}else
		{
			electricPriceMng.setUnitid(null);	
		}
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}else {
			electricPriceMng.setAddtime(null);
		}
		redirectAttributes.addFlashAttribute("electricPriceMng",electricPriceMng);
		
		// 页面搜索条件、页面排序等参数
		String 	searchUrlParam = decodeUrlSearchParam(request);
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = "&" + searchUrlParam;
		}
				
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpricemng/electricPriceMng/?repage" + searchUrlParam;
	}
	
	

	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:edit")
	@RequestMapping(value = "formSetAll")
	public String formSetAll(ElectricPriceMng electricPriceMng, Model model,HttpServletRequest request, HttpServletResponse response) {
				
		String addtime = request.getParameter("addtime");
		 // 带参数为设置某个月的电价
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}
		model.addAttribute("addtimeparam", addtime);
		String pageno = request.getParameter("pageno");
		model.addAttribute("pagenoparam", pageno);
		
		model.addAttribute("electricPriceMng", electricPriceMng);
		return "tmdl/electricrelease/electricpricemng/electricPriceMngFormSetAll";
	}
	

	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:edit")
	@RequestMapping(value = "saveSetAll")
	public String saveSetAll(ElectricPriceMng electricPriceMng, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, electricPriceMng)){
			return form(electricPriceMng, model,request,response);
		}
		electricPriceMng.setGetprice(1);
		Page<ElectricPriceMng> page = electricPriceMngService.findPage(new Page<ElectricPriceMng>(request, response,-1), electricPriceMng);
		List<ElectricPriceMng> electricPriceMnglist = page.getList();
		for(ElectricPriceMng saveElectricPriceMng : electricPriceMnglist) {
			electricPriceMng.setId(saveElectricPriceMng.getId());
			electricPriceMng.setUnitid(saveElectricPriceMng.getUnitid());
			electricPriceMngService.save(electricPriceMng);
		}
				
		addMessage(redirectAttributes, "保存电价管理成功");
		
		String companyid = request.getParameter("unitidparam");
		String addtime = request.getParameter("addtimeparam");
		String pageno = request.getParameter("pagenoparam");
		
		redirectAttributes.addFlashAttribute("pageno",pageno);
		if(StringUtils.isNotBlank(companyid)) {
			electricPriceMng.setUnitid(userCompanyService.get(companyid));			
		}else
		{
			electricPriceMng.setUnitid(null);	
		}
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}else {
			electricPriceMng.setAddtime(null);
		}
		redirectAttributes.addFlashAttribute("electricPriceMng",electricPriceMng);
		
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpricemng/electricPriceMng/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(ElectricPriceMng electricPriceMng, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, electricPriceMng)){
			return "保存电价管理失败，数据校验失败。";
		}
		electricPriceMngService.save(electricPriceMng);		
		return "保存电价管理成功";
	}
	
	@RequiresPermissions("electricrelease:electricpricemng:electricPriceMng:edit")
	@RequestMapping(value = "delete")
	public String delete(ElectricPriceMng electricPriceMng, RedirectAttributes redirectAttributes) {
		electricPriceMngService.delete(electricPriceMng);
		addMessage(redirectAttributes, "删除电价管理成功");
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpricemng/electricPriceMng/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(ElectricPriceMng electricPriceMng, RedirectAttributes redirectAttributes) {
		electricPriceMngService.delete(electricPriceMng);
		return "删除电价管理成功";
	}

}