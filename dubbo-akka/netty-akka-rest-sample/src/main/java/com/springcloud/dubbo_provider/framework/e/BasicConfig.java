package com.springcloud.dubbo_provider.framework.e;

import com.springcloud.dubbo_provider.framework.Utils.CommonUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.TimerTask;


public class BasicConfig extends TimerTask {

	private static Logger LOGGER	= LoggerFactory.getLogger(BasicConfig.class);
	private static Properties properties = null;
	private static String defaultConfName = "default.properties";
	
	static{
		load();
	}
	
	private static void load(){
		InputStream inStream = null;
		BufferedReader br = null;
		try {
			inStream = CommonUtils.getConfigFileStream(defaultConfName);
			if(inStream == null){
				throw new RuntimeException("not found configer file...");
			}
			LOGGER.info("load "+defaultConfName);
			br = new BufferedReader(new InputStreamReader(inStream));
			properties = new Properties();
			properties.load(br);
			
		} catch (IOException e) {
			LOGGER.error("While load config file [" + defaultConfName + "] error occured. Perhaps the file isn't exist.", e);
		}  finally {
			try {
				inStream.close();
			} catch (Exception e) {}
		}
	}

	@Override
	public void run() {
		BasicConfig.load();
	}
	
	public static String getString(String key) {
		if (StringUtils.isBlank(key)) {
			String error = "key is empty.";
			LOGGER.warn(error);
			return null;
		}
		return properties.getProperty(key);
	}

	
	public static int getInt(String key) {
		String value = getString(key);
		try {
			return new Integer(value.trim());
		} catch (NumberFormatException e) {
			LOGGER.error("parse int exception ,parse key is["+key+"] ,val is "+value);
			throw new RuntimeException("parse int exception ,parse key is["+key+"] ,val is "+value);
		}
	}
	
	public static long getLong(String key) {
		String value = "";
		try {
			value = getString(key);
			return Long.parseLong(value.trim());
		} catch (NumberFormatException e) {
			LOGGER.error("parse long exception ,parse key is["+key+"]");
			throw new RuntimeException("parse long exception ,parse key is["+key+"]");
		}
	}

	
	public static boolean getBoolean(String key) {
		String value = getString(key);
		return Boolean.parseBoolean(value);
	}

}
