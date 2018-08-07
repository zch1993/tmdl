/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/umasoft/umafrmsite">umafrmsite</a> All rights reserved.
 */
package com.umasoft.umafrmsite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;


import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import com.alibaba.fastjson.JSON;
import com.umasoft.umafrmsite.common.utils.Plupload;
import com.umasoft.umafrmsite.common.utils.PluploadUtil;
import com.umasoft.umafrmsite.common.web.BaseController;
import com.umasoft.umafrmsite.modules.sys.service.SystemService;
import com.umasoft.umafrmsite.modules.sys.utils.UserUtils;



/**
 * 用户Controller
 * @author umasoft
 * @version 2013-8-29
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/plupload")
public class pluploadController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	private String savePath;// 数据库存储路径
	private String uploadPath;// 附件的实际存储的绝对路径
	public static final String FileDir = "uploadfile/";  
	
	
	 /**上传处理方法
	 * @throws IOException */  
	@ResponseBody
    @RequestMapping(value="/uploadfile", method = RequestMethod.POST)  
    public void uploadfile(Plupload plupload,HttpServletRequest request, HttpServletResponse response) throws IOException {  
          
        //System.out.println(plupload.getChunk() + "===" + plupload.getName() + "---" + plupload.getChunks());  
          
        plupload.setRequest(request);  
         
       // System.out.println(dir.getPath());  
          
        try {  
        	Calendar date = Calendar.getInstance();
    		uploadPath = request.getSession().getServletContext()
    				.getRealPath("/")+"uploadPath" + File.separator + UserUtils.getUser().getLoginName() + File.separator
    						+ request.getParameter("type") + request.getParameter("uploadPath") + File.separator
    						+ date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator
    						+ date.get(Calendar.DAY_OF_MONTH);
    		savePath = request.getContextPath()+ "/uploadPath" + File.separator + UserUtils.getUser().getLoginName() + File.separator
    				+ request.getParameter("type") + request.getParameter("uploadPath") + File.separator
    				+ date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator
    				+ date.get(Calendar.DAY_OF_MONTH);
    		savePath = savePath.replaceAll("\\\\", "/");
    		
    		//文件存储路径  
            File dir = new File(uploadPath);  
             
            
    		String tempFileName = null; /* 临时文件名 */
    		String newFileName = null; /* 最后合并后的新文件名 */
    		BufferedOutputStream outputStream = null;
    		/* System.out.println(FileUtils.getTempDirectoryPath()); */
    		/**
    		 * 还是加个null工具类判断
    		 */
    		String savename="";//UserUtils.checkNull(request.getParameter("savename"));//保存名称
    		if(!savename.equals("")){
    		savename=URLDecoder.decode( request.getParameter("savename"), "utf-8");
    		System.out.println("保存文件原名"+savename);
    		}
    		else
    		{
    			System.out.println("系统随机重命名");

    		}
    		if(!savename.equals("")){
				newFileName =savename;
				}else{
				//newFileName = UUID.randomUUID().toString().replace("-", "").concat(".").concat(FilenameUtils.getExtension(tempFileName));
				}
    		
            //上传文件  
            PluploadUtil.upload(plupload, dir);  
            //判断文件是否上传成功（被分成块的文件是否全部上传完成）  
            if (PluploadUtil.isUploadFinish(plupload)) {  
                System.out.println(plupload.getName() + "----");  
            }  
              
            Map<String, Object> m = new HashMap<String, Object>();
    		m.put("status", true);    		
    		m.put("fileUrl", savePath + "/" + plupload.getSaveFileName());
    		System.out.println(savePath);
    		response.getWriter().write(JSON.toJSONString(m));
        } catch (IllegalStateException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
            e.printStackTrace();
			Map<String, Object> m = new HashMap<String, Object>();
			m.put("status", false);
			response.getWriter().write(JSON.toJSONString(m));
        } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
      
    }  
	//@RequiresPermissions("user")
	@ResponseBody
	//@RequestMapping(value = "uploadfile")
	@RequestMapping(value="mobileupload", method = RequestMethod.POST)  
	public void mobileupload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Calendar date = Calendar.getInstance();
		response.setCharacterEncoding("UTF-8");
		Integer chunk = null; /* 分割块数 */
		Integer chunks = null; /* 总分割数 */
		String tempFileName = null; /* 临时文件名 */
		String newFileName = null; /* 最后合并后的新文件名 */
		OutputStream out = null;
		/* System.out.println(FileUtils.getTempDirectoryPath()); */
		/**
		 * 还是加个null工具类判断把 fuck
		 */
		String savename="";//UserUtils.checkNull(request.getParameter("savename"));//保存名称
		if(!savename.equals("")){
		savename=URLDecoder.decode( request.getParameter("savename"), "utf-8");
		System.out.println("保存文件原名"+savename);
		}
		else
		{
			System.out.println("系统随机重命名");

		}
		
		uploadPath = request.getSession().getServletContext()
				.getRealPath("/")+"uploadPath" + File.separator + UserUtils.getUser().getLoginName() + File.separator
						+ request.getParameter("type") + request.getParameter("uploadPath") + File.separator
						+ date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator
						+ date.get(Calendar.DAY_OF_MONTH);
		savePath = request.getContextPath()+ "/uploadPath" + File.separator + UserUtils.getUser().getLoginName() + File.separator
				+ request.getParameter("type") + request.getParameter("uploadPath") + File.separator
				+ date.get(Calendar.YEAR) + File.separator + (date.get(Calendar.MONTH) + 1) + File.separator
				+ date.get(Calendar.DAY_OF_MONTH);
		savePath = savePath.replaceAll("\\\\", "/");
		File up = new File(uploadPath);
		if (!up.exists()) {
			up.mkdirs();
		}

		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				
				//UploadUtils uutf = new UploadUtils();
				//uutf.uploadFile(request);
				// 复杂类型的request对象
				MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
				// 获取文件名集合放入迭代器
				Iterator<String> files = mRequest.getFileNames();

				while (files.hasNext()) {
				//获取上传文件的对象
				MultipartFile mFile = mRequest.getFile(files.next());
					if (mFile != null) {
						String fileName = ""+System.currentTimeMillis();
						
						String oldfile = mFile.getOriginalFilename();
						
						//创建一个新的文件名
						newFileName = fileName + oldfile;
						
						//将上传的文件写入数组
						byte[] bytes = mFile.getBytes();
						// 获取服务器的绝对路径
						
						//创建流对象
						out = new FileOutputStream(new File(uploadPath + newFileName));
						//将数组写入到服务器
						out.write(bytes);
						out.close();
					}
				}
				
				
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", true);
				// savePath
				// m.put( "fileUrl",uploadPath+ "\\"
				// + newFileName );
				m.put("fileUrl", savePath + "/" + newFileName);
				System.out.println(savePath);
				response.getWriter().write(JSON.toJSONString(m));
			} catch (Exception e) {
				e.printStackTrace();
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("status", false);
				response.getWriter().write(JSON.toJSONString(m));
			} finally {
				try {
					if (out != null)
						out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
			
		}

}