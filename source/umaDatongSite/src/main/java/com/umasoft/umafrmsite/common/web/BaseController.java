/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.common.web;

import java.beans.PropertyEditorSupport;
import java.io.IOException;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import javax.validation.Validator;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.umasoft.umafrmsite.common.beanvalidator.BeanValidators;
import com.umasoft.umafrmsite.common.mapper.JsonMapper;
import com.umasoft.umafrmsite.common.utils.DateUtils;
import com.umasoft.umafrmsite.common.utils.Encodes;
import com.umasoft.umafrmsite.common.utils.StringUtils;

/**
 * 控制器支持类
 * @author umasoft
 * @version 2018-5-23
 */
public abstract class BaseController {

	/**
	 * 日志对象
	 */
	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 管理基础路径
	 */
	@Value("${adminPath}")
	protected String adminPath;
	
	/**
	 * 前端基础路径
	 */
	@Value("${frontPath}")
	protected String frontPath;
	
	/**
	 * 前端URL后缀
	 */
	@Value("${urlSuffix}")
	protected String urlSuffix;
	
	/**
	 * 验证Bean实例对象
	 */
	@Autowired
	protected Validator validator;

	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 message 中
	 */
	protected boolean beanValidator(Model model, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(model, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组
	 * @return 验证成功：返回true；严重失败：将错误信息添加到 flash message 中
	 */
	protected boolean beanValidator(RedirectAttributes redirectAttributes, Object object, Class<?>... groups) {
		try{
			BeanValidators.validateWithException(validator, object, groups);
		}catch(ConstraintViolationException ex){
			List<String> list = BeanValidators.extractPropertyAndMessageAsList(ex, ": ");
			list.add(0, "数据验证失败：");
			addMessage(redirectAttributes, list.toArray(new String[]{}));
			return false;
		}
		return true;
	}
	
	/**
	 * 服务端参数有效性验证
	 * @param object 验证的实体对象
	 * @param groups 验证组，不传入此参数时，同@Valid注解验证
	 * @return 验证成功：继续执行；验证失败：抛出异常跳转400页面。
	 */
	protected void beanValidator(Object object, Class<?>... groups) {
		BeanValidators.validateWithException(validator, object, groups);
	}
	
	/**
	 * 添加Model消息
	 * @param message
	 */
	protected void addMessage(Model model, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		model.addAttribute("message", sb.toString());
	}
	
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addMessage(RedirectAttributes redirectAttributes, String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute("message", sb.toString());
	}
	/**
	 * 添加Flash消息
	 * @param message
	 */
	protected void addPageAttibute(RedirectAttributes redirectAttributes, String attributeid,String... messages) {
		StringBuilder sb = new StringBuilder();
		for (String message : messages){
			sb.append(message).append(messages.length>1?"<br/>":"");
		}
		redirectAttributes.addFlashAttribute(attributeid, sb.toString());		
	}
	
	/**
	 * 客户端返回JSON字符串
	 * @param response
	 * @param object
	 * @return
	 */
	protected String renderString(HttpServletResponse response, Object object) {
		return renderString(response, JsonMapper.toJsonString(object), "application/json");
	}
	
	/**
	 * 客户端返回字符串
	 * @param response
	 * @param string
	 * @return
	 */
	protected String renderString(HttpServletResponse response, String string, String type) {
		try {
			response.reset();
	        response.setContentType(type);
	        response.setCharacterEncoding("utf-8");
			response.getWriter().print(string);
			return null;
		} catch (IOException e) {
			return null;
		}
	}

	/**
	 * 参数绑定异常
	 */
	@ExceptionHandler({BindException.class, ConstraintViolationException.class, ValidationException.class})
    public String bindException() {  
        return "error/400";
    }
	
	/**
	 * 授权登录异常
	 */
	@ExceptionHandler({AuthenticationException.class})
    public String authenticationException() {  
        return "error/403";
    }
	
	/**
	 * 初始化数据绑定
	 * 1. 将所有传递进来的String进行HTML编码，防止XSS攻击
	 * 2. 将字段中Date类型转换为String类型
	 */
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		// String类型转换，将所有传递进来的String进行HTML编码，防止XSS攻击
		binder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(text == null ? null : StringEscapeUtils.escapeHtml4(text.trim()));
			}
			@Override
			public String getAsText() {
				Object value = getValue();
				return value != null ? value.toString() : "";
			}
		});
		// Date 类型转换
		binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) {
				setValue(DateUtils.parseDate(text));
			}
//			@Override
//			public String getAsText() {
//				Object value = getValue();
//				return value != null ? DateUtils.formatDateTime((Date)value) : "";
//			}
		});
	}
	/**
	 * 页面搜索条件等参数保存下来，在编辑、查看等操作回去后可以保留在原来页面
	 * @param request
	 * @param response
	 * @param model
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	public void EncoderUrlSearchParam( HttpServletRequest request, Model model) {
		// 页面搜索条件、页面排序等参数
		String searchUrlParam = request.getParameter("searchUrlParam");
		if(StringUtils.isNotBlank(searchUrlParam)) {
			model.addAttribute("searchUrlParam", searchUrlParam);
			return;
		}
		searchUrlParam = getUrlSearchParam(request);
		
        if(!"".equals(searchUrlParam)) {
        	//searchUrlParam = Encodes.encodeBase64(searchUrlParam);
        	//searchUrlParam = Encodes.urlEncode(searchUrlParam);
        	model.addAttribute("searchUrlParam", searchUrlParam);
        }
	}
	/**
	 * 页面搜索条件等参数保存下来，在编辑、查看等操作回去后可以保留在原来页面
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	public void EncoderUrlSearchParam(HttpServletRequest request, RedirectAttributes redirectAttributes) {
		// 页面搜索条件、页面排序等参数
		String searchUrlParam = request.getParameter("searchUrlParam");
		if(StringUtils.isNotBlank(searchUrlParam)) {
			redirectAttributes.addAttribute("searchUrlParam", searchUrlParam);
			return;
		}
		
		searchUrlParam = getUrlSearchParam(request);
		
        if(!"".equals(searchUrlParam)) {
        	//searchUrlParam = Encodes.encodeBase64(searchUrlParam);
        	//searchUrlParam = Encodes.urlEncode(searchUrlParam);
        	redirectAttributes.addAttribute("searchUrlParam", searchUrlParam);
        }
	}
	/**
	 * 
	 * @param request
	 * @param model
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	public void setUrlSearchParam(HttpServletRequest request, Model model) {
		// 页面搜索条件、页面排序等参数
		String searchUrlParam = request.getParameter("searchUrlParam");
		if(StringUtils.isNotBlank(searchUrlParam)) {
			model.addAttribute("searchUrlParam", searchUrlParam);			
		}
	}
	
	/***
	 * 
	 * @param request
	 * @param redirectAttributes
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	public void setUrlSearchParam(HttpServletRequest request,  RedirectAttributes redirectAttributes) {
		// 页面搜索条件、页面排序等参数
		String searchUrlParam = request.getParameter("searchUrlParam");
		if(StringUtils.isNotBlank(searchUrlParam)) {
			redirectAttributes.addAttribute("searchUrlParam", searchUrlParam);		
		}
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	private String getUrlSearchParam(HttpServletRequest request) {
		String searchUrlParam = "";
		Enumeration<String> names = request .getParameterNames();
        while (names .hasMoreElements()) {
           String name = (String) names .nextElement();
           String[] values = request .getParameterValues(name );
           if(values != null && values.length>0 && StringUtils.isNotBlank(values[0])) {
        	   if(!"".equals(searchUrlParam)) {
        		   searchUrlParam = searchUrlParam + "&";
        	   }
        	   /*
        	   if("pageNo".equals(name)) {
        		   name = "page.pageNo";
        	   }else if("pageSize".equals(name)) {
        		   name = "page.pageSize";
        	   }else if("orderBy".equals(name)) {
        		   name = "page.orderBy";
        	   }else if(!"page.pageNo".equals(name) && !"page.pageSize".equals(name) 
        			   && !"page.orderBy".equals(name) && name.indexOf(clsName) !=0 ){
        		   name = clsName + "." + name;
        	   }
        	   */
        	   searchUrlParam = searchUrlParam + name + "=" + Encodes.urlEncode(values[0]);
           }
        }
        if(!"".equals(searchUrlParam)) {
        	searchUrlParam = Encodes.encodeBase64(searchUrlParam);
        	searchUrlParam = Encodes.urlEncode(searchUrlParam);        	
        }
        
        return searchUrlParam;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 * @author zhangky@umasoft
	 * @ 2018-07-01
	 */
	public String decodeUrlSearchParam(HttpServletRequest request) {
		String searchUrlParam = request.getParameter("searchUrlParam");
		if(StringUtils.isNotBlank(searchUrlParam)) {
			searchUrlParam = Encodes.urlDecode(searchUrlParam);
			searchUrlParam = Encodes.decodeBase64String(searchUrlParam);
			//searchUrlParam = Encodes.urlEncode(searchUrlParam);
		}
		
		return searchUrlParam;
	}
	
}
