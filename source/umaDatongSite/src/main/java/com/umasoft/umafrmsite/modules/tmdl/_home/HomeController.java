/**
 * Copyright &copy; 2009-2018 <a href="http://www.umasoft.com">umasoft</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.tmdl._home;

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
import com.umasoft.umafrmsite.common.security.shiro.session.SessionDAO;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.substationmng.TmdlSubstationService;
import com.umasoft.umafrmsite.modules.tmdl.paramset.service.usercompanymng.UserCompanyService;
import com.umasoft.umafrmsite.common.utils.StringUtils;


/**
 * 首页Controller
 * @author zhangky@umasoft
 * @version 2018-07-03
 */
@Controller
@RequestMapping(value = "${adminPath}/tmdl/_home/Home")
public class HomeController extends BaseController {

	@Autowired
	private TmdlSubstationService tmdlSubstationService;
	@Autowired
	private UserCompanyService userCompanyService;
	@Autowired
	private SessionDAO sessionDAO;
	
	@RequestMapping(value = {"list", ""})
	public String list(HttpServletRequest request, HttpServletResponse response, Model model) {
		int iSubstationCount = tmdlSubstationService.getSubstationCount();
		model.addAttribute("substationCount", iSubstationCount);
		
		int iUserCompany = userCompanyService.getUserCompanyCount();
		model.addAttribute("userCompanyCount", iUserCompany);
		
		int activeUsers = sessionDAO.getActiveSessions(false).size();
		model.addAttribute("activeUsers", activeUsers);
		
		return "tmdl/_home/index";
	}
	
	

}