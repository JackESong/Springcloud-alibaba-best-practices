package com.springcloud.dubbo_provider.framework.e;


public class BasicException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String				errorCode;
	private Object[]			args;
	
	public BasicException(String msg){
		super(msg);
	}
	public BasicException(String errorCode, Object... args) {
		super(errorCode);

		this.errorCode = errorCode;
		this.args = args;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public Object[] getArgs() {
		return args;
	}
}
