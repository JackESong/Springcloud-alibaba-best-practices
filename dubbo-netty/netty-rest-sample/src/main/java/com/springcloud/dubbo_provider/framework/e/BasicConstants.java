package com.springcloud.dubbo_provider.framework.e;

public class BasicConstants {
	public final static String PROJECT_JAR_HOME = System.getProperty("user.dir");
	public final static int TOKEN_INVALID_DATE = BasicConfig.getInt("token.invalid.hour");
	public final static int TIME_OUT_SECONDS = 60;
	public final static String ADMIN_TOKEN = BasicConfig.getString("admin.token");
	public static String JSONP_RETURN_CALL_METHOD = BasicConfig.getString("jsonp.method");
	public final static String HTTP_SERVER_URL_CHARCODE_UTF_8 = "UTF-8";
	public final static int RETURN_CODE_SUCCESS = 0;
	public final static int RETURN_CODE_ERROR = 1;
	public final static int HTTP_SERVER_PORT = BasicConfig.getInt("http.server.port");
	public static String REQUEST_URI_FILTERS = BasicConfig.getString("request.uri.filters");

	public final static String JDBCURL = BasicConfig.getString("jdbc");
	
	//系統异常内容
	public static final String EXCEPTION_DES="访问异常，请重试。";


	public final static String UTF8="UTF-8";
	public final static String GBK="GBK";

	public final static String REDIS_QUEUE_HOST = BasicConfig.getString("redis.queue.host");
	public final static int REDIS_QUEUE_PORT = BasicConfig.getInt("redis.queue.port");


	public final static String RESULT_CODE_SUCCESS="0" ;
	public final static String RESULT_CODE_FAIL="-1" ;
	public static final String REQUEST_PARAMETER_NOT_FOUND_CODE="1020101";
	
	public final static String RESULT_MESSAGE="用户名错误" ;
	public final static String RESULT_MESSAGE_REGISTER="注册失败" ;
}
