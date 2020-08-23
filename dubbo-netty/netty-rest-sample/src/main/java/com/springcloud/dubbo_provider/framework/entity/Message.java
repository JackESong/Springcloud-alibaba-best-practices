package com.springcloud.dubbo_provider.framework.entity;

import java.util.HashMap;
import java.util.Map;

public class Message {
	/**
	 * Returns a map of name-value pairs describing the data stored in the body.
	 */
	private Map<String, String> headers = new HashMap<String ,String>();
	private String body = "";
	
	public Message(String body){
		this.body = body;
	}
	
	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * Set the event headers
	 * 
	 * @param headers
	 *            Map of headers to replace the current headers.
	 */
	public void setHeaders(Map<String, String> headers){
		headers.putAll(headers);
	}

	/**
	 * Returns the raw byte array of the data contained in this event.
	 */
	public String getBody(){
		return body;
	}

	/**
	 * Sets the raw byte array of the data contained in this event.
	 * 
	 * @param body
	 *            The data.
	 */
	public void setBody(String body){
		this.body = body;
	}
}
