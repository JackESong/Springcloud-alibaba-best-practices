/**
 * Project Name:ibus-0.0.1-SNAPSHOT
 * File Name:ResponeBody.java
 * Package Name:com.geo.dataquate.ibus.core.admin.bean
 * Date:2014年8月3日上午9:37:46
 * Copyright (c) 2014, 北京集奥聚合科技有限公司 All Rights Reserved.
 *
*/

package com.springcloud.dubbo_provider.framework.entity;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class Response 
{
	private Map<String,Object> datas = new HashMap<String,Object>();
	private byte[] content = null;
	private List<String> logs = new LinkedList<String>();
	
	
	public List<String> getLogs() {
		return logs;
	}
	
	public void setLogs(List<String> logs) {
		this.logs = logs;
	}

	public void addLogs(String log) {
		this.logs.add(log);
	}
	public void put(String key,String value){
		datas.put(key, value);
	}
	public Object get(String key){
		return datas.get(key);
	}

	@Override
	public String toString(){
		StringBuffer sb = new StringBuffer("Conten :"+content);
		for(String key:datas.keySet()){
			sb.append("; "+key+" :"+datas.get(key));
		}
		return  sb.toString();
	}
	
	public Map<String, Object> getDatas() {
		return datas;
	}
	public void setDatas(Map<String, Object> datas) {
		this.datas = datas;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}
	
}

