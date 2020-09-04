package com.springcloud.dubbo_provider.framework.Utils;

import com.springcloud.dubbo_provider.framework.e.BasicException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(CommonUtils.class);
	private static ExecutorService pool = Executors.newCachedThreadPool();
	private static long preUpTime = 0;
	
	public static InputStream getConfigFileStream(String fileName) {
		String path = null;
		try {
			path = getProjectPath() + File.separator
					+ "conf" + File.separator + fileName;// jar外部配置路径
			File f = new File(path);
			
			if (!f.exists()) {
				path = getFilePathInClassPath(fileName);
			}
			LOGGER.info("load "+path);
			f = new File(path);
			if(preUpTime != f.lastModified()){
				preUpTime = f.lastModified();
				return new FileInputStream(path);
			}
			return null;
		} catch (FileNotFoundException e) {
			LOGGER.error("getConfigFileStream error , maybe file <"+path+"> not found");
		}
		return null;
	}
	public static String getFilePathInClassPath(String fileName){
		return CommonUtils.class.getResource("/" + fileName).getPath();
	}
	public static String getConfigFilePath(String fileName) {
		String path = System.getProperty("user.dir") + File.separator + "conf"
				+ File.separator + fileName;
		File f = new File(path);
		if (!f.exists()) {
			path = File.separator + "conf" + File.separator + fileName;
		}
		LOGGER.info("load file "+path);
		return path;
	}
	
	public static Integer paseInt(String str ,Integer defaultInt){
		if(null == str || str.trim().length() == 0){
			return defaultInt;
		}else{
			return Integer.parseInt(str);
		}
	}
	
	public static long getFileCount(String filePath){
		long n = 0;
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(new File(filePath)));
			while(br.readLine()!=null){
				n++;
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.info(e.getMessage());
			throw new BasicException(e.getMessage());
		}finally{
			if(null != br){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return n;
	}
	/**
	 * 获取项目根目录
	 */
	public static String getProjectPath(){
		return  System.getProperty("user.dir");
	}
	
	/**
	 * 在指定目录下遍历出指定文件的路径
	 */
	public static String getFilePath(File rootPath ,String fileName){
		String name = null;
		if(rootPath.isDirectory()){
			File []files = rootPath.listFiles();
			for(int i=0;i<files.length;i++){
				File f = files[i];
				if(!f.isDirectory() && f.getName().equalsIgnoreCase(fileName)){
					name = f.getPath();
					break;
				}else{
					name = getFilePath(f, fileName);
					if(fileName.equalsIgnoreCase(name)){
						break;
					}
				}
			}
		}
		return name;
	}
	public static String getHost(String url){
		  if(url==null||url.trim().equals("")){
		   return "";
		  }
		  String host = "";
		  Pattern p =  Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
		  Matcher matcher = p.matcher(url);  
		  if(matcher.find()){
		   host = matcher.group();  
		  }
		  return host;
	}
	public static ExecutorService getThreadPool(){
		if(null != pool && pool.isShutdown()){
			pool = Executors.newCachedThreadPool();
		}
		return pool;
	}
	

}
