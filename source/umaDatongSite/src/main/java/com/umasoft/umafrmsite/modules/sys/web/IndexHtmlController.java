/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import javax.validation.ConstraintViolationException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.umasoft.umafrmsite.common.beanvalidator.BeanValidators;
import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.persistence.Page;
import com.umasoft.umafrmsite.common.utils.DateUtils;
import com.umasoft.umafrmsite.common.utils.StringUtils;
import com.umasoft.umafrmsite.common.utils.UploadUtils;
import com.umasoft.umafrmsite.common.utils.excel.ExportExcel;
import com.umasoft.umafrmsite.common.utils.excel.ImportExcel;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.modules.sys.entity.Office;
import com.umasoft.umafrmsite.modules.sys.entity.Role;
import com.umasoft.umafrmsite.modules.sys.entity.User;
import com.umasoft.umafrmsite.modules.sys.service.SystemService;
import com.umasoft.umafrmsite.modules.sys.utils.UserUtils;

/**
 * 用户Controller
 * 用于保存用户页面定制
 * @author umasoft
 * @version 2013-8-29
 */
//@Controller
//@RequestMapping(value = "${adminPath}/sys/userhtml")
public class IndexHtmlController extends BaseController {

	//@Autowired
	private SystemService systemService;
	@Autowired
	private SqlSessionFactory sqlSessionFactory;
			
	//@RequiresPermissions("user")
	//@ResponseBody
	//@RequestMapping(value = "updatepage")
	public List<Map<String, Object>> updatepage(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		List<Map<String, Object>> mapList = Lists.newArrayList();
		
		Map<String, Object> map = Maps.newHashMap();
		map.put("status", "1");
		
		mapList.add(map);
		
		return mapList;
	
	}

	public static void main(String[] args) {
		/*sqlSessionFactory.openSession().getMapper(User.class)；*/
	}


}

