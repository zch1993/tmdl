/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl.electricrelease.web.electricpriceblcpaymng;

import java.text.SimpleDateFormat;
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
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.entity.electricpriceblcpaymng.ElectricPriceBlcPayMng;
import com.umasoft.umafrmsite.modules.tmdl.electricrelease.service.electricpriceblcpaymng.ElectricPriceBlcPayMngService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng.UserCompanyService;

/**
 * 电价管理Controller
 * @author zhangky@umasoft
 * @version 2018-07-06
 */
@Controller
@RequestMapping(value = "${adminPath}/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng")
public class ElectricPriceBlcPayMngController extends BaseController {

	@Autowired
	private ElectricPriceBlcPayMngService electricPriceBlcPayMngService;
	@Autowired
	private UserCompanyService userCompanyService;
	
	@ModelAttribute
	public ElectricPriceBlcPayMng get(@RequestParam(required=false) String id) {
		ElectricPriceBlcPayMng entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = electricPriceBlcPayMngService.get(id);
		}
		if (entity == null){
			entity = new ElectricPriceBlcPayMng();
		}
		return entity;
	}
	
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:view")
	@RequestMapping(value = {"list", ""})
	public String list(ElectricPriceBlcPayMng electricPriceBlcPayMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		/*
		  String addtime = request.getParameter("addtime");
		 
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:MM:SS");
				java.util.Date dt=sdf.parse(addtime+"-11 11:11:11");
				electricPriceBlcPayMng.setAddtime(dt);
			}catch(Exception e) {
				
			}
		}
		*/
		model.addAttribute("unitid.id", request.getParameter("unitid.id"));
		model.addAttribute("addtime", request.getParameter("addtime"));
		Page<ElectricPriceBlcPayMng> page = electricPriceBlcPayMngService.findPage(new Page<ElectricPriceBlcPayMng>(request, response), electricPriceBlcPayMng); 
		model.addAttribute("page", page);
		return "tmdl/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMngList";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:view")
	@RequestMapping(value = {"listData"})
	public Page<ElectricPriceBlcPayMng> listData(ElectricPriceBlcPayMng electricPriceBlcPayMng, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ElectricPriceBlcPayMng> page = electricPriceBlcPayMngService.findPage(new Page<ElectricPriceBlcPayMng>(request, response), electricPriceBlcPayMng); 
		return page;
	}
	

	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:view")
	@RequestMapping(value = "form")
	public String form(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model,HttpServletRequest request, HttpServletResponse response) {
		String companyid = request.getParameter("unitid.id");
		if (electricPriceBlcPayMng.getIsNewRecord()){
			// 如果有单位ID，而没有当前记录，是根据单位新建价格记录
			
			if(StringUtils.isNotBlank(companyid)) {
				electricPriceBlcPayMng.setUnitid(userCompanyService.get(companyid));
				
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
				electricPriceBlcPayMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}
		model.addAttribute("addtimeparam", addtime);
		String pageno = request.getParameter("pageno");
		model.addAttribute("pagenoparam", pageno);
		
		model.addAttribute("electricPriceBlcPayMng", electricPriceBlcPayMng);
		return "tmdl/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMngForm";
	}
	
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:view")
	@RequestMapping(value = "formView")
	public String formView(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model) {
		model.addAttribute("electricPriceBlcPayMng", electricPriceBlcPayMng);
		return "tmdl/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMngFormView";
	}
	

	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:view")
	@RequestMapping(value = {"formData"})
	public ElectricPriceBlcPayMng formData(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model) {
		return electricPriceBlcPayMng;
	}

	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit")
	@RequestMapping(value = "save")
	public String save(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, electricPriceBlcPayMng)){
			return form(electricPriceBlcPayMng, model,request,response);
		}
		electricPriceBlcPayMngService.save(electricPriceBlcPayMng);
		addMessage(redirectAttributes, "保存电价管理成功");
		
		String companyid = request.getParameter("unitidparam");
		String addtime = request.getParameter("addtimeparam");
		String pageno = request.getParameter("pagenoparam");
		
		redirectAttributes.addFlashAttribute("pageno",pageno);
		if(StringUtils.isNotBlank(companyid)) {
			electricPriceBlcPayMng.setUnitid(userCompanyService.get(companyid));			
		}else
		{
			electricPriceBlcPayMng.setUnitid(null);	
		}
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceBlcPayMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}else {
			electricPriceBlcPayMng.setAddtime(null);
		}
		redirectAttributes.addFlashAttribute("electricPriceBlcPayMng",electricPriceBlcPayMng);
		
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/?repage";
	}
	
	

	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit")
	@RequestMapping(value = "formSetAll")
	public String formSetAll(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model,HttpServletRequest request, HttpServletResponse response) {
				
		String addtime = request.getParameter("addtime");
		 // 带参数为设置某个月的电价
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceBlcPayMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}
		model.addAttribute("addtimeparam", addtime);
		String pageno = request.getParameter("pageno");
		model.addAttribute("pagenoparam", pageno);
		
		model.addAttribute("electricPriceBlcPayMng", electricPriceBlcPayMng);
		return "tmdl/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMngFormSetAll";
	}
	

	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit")
	@RequestMapping(value = "saveSetAll")
	public String saveSetAll(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model, RedirectAttributes redirectAttributes,HttpServletRequest request, HttpServletResponse response) {
		if (!beanValidator(model, electricPriceBlcPayMng)){
			return form(electricPriceBlcPayMng, model,request,response);
		}
		electricPriceBlcPayMng.setGetprice(1);
		Page<ElectricPriceBlcPayMng> page = electricPriceBlcPayMngService.findPage(new Page<ElectricPriceBlcPayMng>(request, response,-1), electricPriceBlcPayMng);
		List<ElectricPriceBlcPayMng> electricPriceBlcPayMnglist = page.getList();
		for(ElectricPriceBlcPayMng saveElectricPriceBlcPayMng : electricPriceBlcPayMnglist) {
			electricPriceBlcPayMng.setId(saveElectricPriceBlcPayMng.getId());
			electricPriceBlcPayMng.setUnitid(saveElectricPriceBlcPayMng.getUnitid());
			electricPriceBlcPayMngService.save(electricPriceBlcPayMng);
		}
				
		addMessage(redirectAttributes, "保存电价管理成功");
		
		String companyid = request.getParameter("unitidparam");
		String addtime = request.getParameter("addtimeparam");
		String pageno = request.getParameter("pagenoparam");
		
		redirectAttributes.addFlashAttribute("pageno",pageno);
		if(StringUtils.isNotBlank(companyid)) {
			electricPriceBlcPayMng.setUnitid(userCompanyService.get(companyid));			
		}else
		{
			electricPriceBlcPayMng.setUnitid(null);	
		}
		if(StringUtils.isNotBlank(addtime)) {
			try {
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date dt=sdf.parse(addtime+"-01");
				electricPriceBlcPayMng.setAddtime(dt);
				
			}catch(Exception e) {
				
			}
		}else {
			electricPriceBlcPayMng.setAddtime(null);
		}
		redirectAttributes.addFlashAttribute("electricPriceBlcPayMng",electricPriceBlcPayMng);
		
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/?repage";
	}
	
	/*
	*   return JSON data 
	*/
	@ResponseBody
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit")
	@RequestMapping(value = {"saveData"})
	public String saveData(ElectricPriceBlcPayMng electricPriceBlcPayMng, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, electricPriceBlcPayMng)){
			return "保存电价管理失败，数据校验失败。";
		}
		electricPriceBlcPayMngService.save(electricPriceBlcPayMng);		
		return "保存电价管理成功";
	}
	
	@RequiresPermissions("electricrelease:electricpriceblcpaymng:electricPriceBlcPayMng:edit")
	@RequestMapping(value = "delete")
	public String delete(ElectricPriceBlcPayMng electricPriceBlcPayMng, RedirectAttributes redirectAttributes) {
		electricPriceBlcPayMngService.delete(electricPriceBlcPayMng);
		addMessage(redirectAttributes, "删除电价管理成功");
		return "redirect:"+Global.getAdminPath()+"/electricrelease/electricpriceblcpaymng/electricPriceBlcPayMng/?repage";
	}

	/*
	*   return JSON data 
	*/	
	@ResponseBody
	@RequiresPermissions("biz:projectcontractmng:bizProjectContractmng:view")
	@RequestMapping(value = {"deleteData"})
	public String deleteData(ElectricPriceBlcPayMng electricPriceBlcPayMng, RedirectAttributes redirectAttributes) {
		electricPriceBlcPayMngService.delete(electricPriceBlcPayMng);
		return "删除电价管理成功";
	}

}