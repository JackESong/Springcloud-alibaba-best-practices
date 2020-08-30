package com.springcloud.dubbo_provider.framework.entity;


import com.alibaba.fastjson.JSON;
import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class ResultMsg {

	private int returnCode;
	private String desc = "";
	
	private Map<String, Map<String,Object>> result = new HashMap<String, Map<String,Object>>();
	private Map<String,Object> head = new HashMap<String, Object>();
	private Map<String,Object> body = new HashMap<String, Object>();
	
	public ResultMsg(){}
	
	public void addReturnCode(Object code){
		head.put("returnCode", code);
	}
	public void addReturnDesc(Object desc){
		head.put("returnDesc", desc);
	}
	public void addReturnKey(Object desc){
		head.put("key", desc);
	}
	public void addReturnValue(Object desc){
		head.put("value", desc);
	}
	public void putHead(String key ,Object val){
		head.put(key, val);
	}
	public Object getByKeyFromHead(String key){
		return head.get(key);
	}
	
	public void addResultData(Object value){
		body.put("resultData", value);
	}
	public void putBody(String key ,Object value){
		body.put(key, value);
	}
	public Object getByKeyFromBody(String key){
		return body.get(key);
	}
	
	
	
	public String toJsonWithCallBack()
	{
		if(StringUtils.trim(BasicConstants.JSONP_RETURN_CALL_METHOD) == ""){
			return toJson();
		}
		return BasicConstants.JSONP_RETURN_CALL_METHOD+"("+toJson()+")";
	}
	public String toJson()
	{   
		result.put("head", head);
		result.put("body", body);
		return JSON.toJSONString(result);
	}

}
