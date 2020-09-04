package com.springcloud.dubbo_provider.framework.e;

public class BasicConstants {

	public static String JSONP_RETURN_CALL_METHOD = BasicConfig.getString("jsonp.method");
	public final static String HTTP_SERVER_URL_CHARCODE_UTF_8 = "UTF-8";
	public final static int RETURN_CODE_SUCCESS = 1;
	public final static int RETURN_CODE_ERROR = 0;
	
	//系統异常内容
	public static final String EXCEPTION_DES="访问异常，请重试。";


	public final static String UTF8="UTF-8";
	public final static String GBK="GBK";

	public static final String REQUEST_PARAMETER_NOT_FOUND_CODE="1020101";
}
