package com.springcloud.dubbo_provider.framework.entity;

import com.springcloud.dubbo_provider.framework.e.BasicConstants;
import com.springcloud.dubbo_provider.framework.e.BasicException;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;


public class Request {

	private static final Logger LOGGER = LoggerFactory.getLogger(Request.class);
	
	private String name = null;
	private String batch_id = null;
	private String path = null;
	private String max_expiration = null;
	private String time_stamp = null;
	private String verify_ok = null;
	private String verify_error = null;
	private String apply_ok = null;
	private String apply_error = null;
	
	private Map<String,List<String>> data = new HashMap<String, List<String>>();
	private Map<String,String> headers = new HashMap<String, String>();
	private String uri = "";
	private String httpPath = "";
	private HttpRequest httpRequest = null;
	private byte[] content = null;
	private String requestMethod = "GET";
	private List<String> logs = new LinkedList<String>();
	private String clientIP = null;
	
	public String getClientIP() {
		return clientIP;
	}
	public void setClientIP(String clientIP) {
		this.clientIP = clientIP; 
	}
	
	public Request(){}
	public Request(HttpRequest httpReq){
		 httpRequest = httpReq;
		 uri = httpRequest.getUri();
		 QueryStringDecoder decoderQuery = new QueryStringDecoder(uri);
		 httpPath = decoderQuery.path().replaceAll("/+", "/");
		 data = decoderQuery.parameters();
	}
	
	
	public List<String> getLogs() {
		return logs;
	}
	public void addLogs(String log) {
		this.logs.add(log);
	}
	public void putHead(String key,String value){
		headers.put(key, value);
	}
	public String getHead(String key){
		return headers.get(key);
	}
	
	public String get(String key) throws Exception{
		if(data.containsKey(key)){
			List<String> ls = data.get(key);
			if(null != ls && ls.size() > 0){
				return ls.get(0);
			}
		}else{
			List<String> ps = new ArrayList<String>();
			ps.add(key);
			throw new BasicException(BasicConstants.REQUEST_PARAMETER_NOT_FOUND_CODE, ps);
		}
		return null;
	}
	public List<String> getParameters(String key){
		if(data.containsKey(key)){
			List<String> ls = data.get(key);
			if(null != ls){
				return ls;
			}
		}else{
			List<String> ps = new ArrayList<String>();
			ps.add(key);
			throw new BasicException(BasicConstants.REQUEST_PARAMETER_NOT_FOUND_CODE, ps);
		}
		return new ArrayList<String>();
	}
	public String getRequestMethod() {
		return requestMethod;
	}
	public void setRequestMethod(String requestMethod) {
		this.requestMethod = requestMethod;
	}
	public byte[] getContent() {
		return content;
	}
	public void setContent(byte[] content) {
		this.content = content;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("URI :"+uri);
		for (String attr : headers.keySet()) {
			sb.append("\t " + attr + '=' + headers.get(attr));
		}
		sb.append("\t Content = "+content);
		sb.append("\t Method = "+requestMethod);
		return sb.toString();
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public String getUri() {
		return uri;
	}

	public String getHttpPath() {
		return httpPath;
	}

	public HttpRequest getHttpRequest() {
		return httpRequest;
	}
	public void putAll(Map<String, List<String>> data) {
		this.data.putAll(data);
	}
	public void setUri(String uri) {
		this.uri = uri;
	}
	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}
	
	public void put(String key ,String value){
		List<String> val = new ArrayList<String>();
		val.add(value);
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		map.put(key, val);
		putAll(map);
	}

	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getBatch_id()
	{
		return batch_id;
	}
	public void setBatch_id(String batch_id)
	{
		this.batch_id = batch_id;
	}
	public String getPath()
	{
		return path;
	}
	public void setPath(String path)
	{
		this.path = path;
	}
	public String getMax_expiration()
	{
		return max_expiration;
	}
	public void setMax_expiration(String max_expiration)
	{
		this.max_expiration = max_expiration;
	}
	public String getTime_stamp()
	{
		return time_stamp;
	}
	public void setTime_stamp(String time_stamp)
	{
		this.time_stamp = time_stamp;
	}
	public String getVerify_ok()
	{
		return verify_ok;
	}
	public void setVerify_ok(String verify_ok)
	{
		this.verify_ok = verify_ok;
	}
	public String getVerify_error()
	{
		return verify_error;
	}
	public void setVerify_error(String verify_error)
	{
		this.verify_error = verify_error;
	}
	public String getApply_ok()
	{
		return apply_ok;
	}
	public void setApply_ok(String apply_ok)
	{
		this.apply_ok = apply_ok;
	}
	public String getApply_error()
	{
		return apply_error;
	}
	public void setApply_error(String apply_error)
	{
		this.apply_error = apply_error;
	}

}
