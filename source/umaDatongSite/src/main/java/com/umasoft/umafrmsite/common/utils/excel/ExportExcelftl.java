package com.umasoft.umafrmsite.common.utils.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.umasoft.umafrmsite.common.config.Global;
import com.umasoft.umafrmsite.common.utils.FreeMarkerEx.getDictLabelCls;
import com.umasoft.umafrmsite.common.utils.FreeMarkerEx.getDictLabelsCls;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class ExportExcelftl {

	private static Configuration configuration =null;
    private static Map<String, Template> allTemplates =null;
    //private static String realPath = "";//ServletContextFactory.getServletContext().getRealPath("/");
    
    public static void exportExcelFtlFile(Map<String, Object> model, String title,String filePathName,HttpServletRequest request, HttpServletResponse response) throws IOException {
    	File file = null;
        InputStream inputStream = null;
        ServletOutputStream out = null;
        try {
        	   model.put("fns_getDictLabel", new getDictLabelCls());
        	   model.put("fns_getDictLabels", new getDictLabelsCls());
            request.setCharacterEncoding("UTF-8");
            file = createExcel(model, title,filePathName);//调用创建excel帮助类
            inputStream = new FileInputStream(file);
            response.setCharacterEncoding("utf-8");
            response.setContentType("application/msexcel");
            response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(title + ".xls", "UTF-8"));
            out = response.getOutputStream();
            byte[] buffer = new byte[512]; // 缓冲区
            int bytesToRead = -1;
            // 通过循环将读入的Excel文件的内容输出到浏览器中
            while ((bytesToRead = inputStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesToRead);
            }
            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null)
                inputStream.close();
            if (out != null)
                out.close();
            if (file != null)
                file.delete(); // 删除临时文件
        }
    	
    }
    /**
     * 创建excel
     * @param dataMap
     * @param type
     * @return
    
     */
    private static File createExcel(Map<?, ?> model, String title,String filePathName) {
        try {
        	 String realPath = Global.getUserfilesBaseDir();
        	 String filePath = filePathName.substring(0, filePathName.lastIndexOf("/"));
        	 if(filePathName.indexOf("\\")>0)filePath = filePathName.substring(0, filePathName.lastIndexOf("\\"));
        	   File dir = new File(realPath+"WEB-INF/views/modules" +filePath);
        	   
        	   configuration = new Configuration();
            configuration.setDefaultEncoding("UTF-8");
            configuration.setDirectoryForTemplateLoading(dir);
            configuration.setClassicCompatible(true);
            allTemplates = new HashMap<String, Template>();
            String filename = filePathName.substring(filePathName.lastIndexOf("/")+1);
            allTemplates.put(title, configuration.getTemplate(filename));
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);
        }
        String name = "temp" + (int) (Math.random() * 100000) + ".xls";
         File file = new File(name);
         Template template = allTemplates.get(title);
         try {
             Writer w = new OutputStreamWriter(new FileOutputStream(file), "utf-8");
             template.process(model, w);
             w.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
         return file;
    }
}
