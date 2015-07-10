package com.ocean.ad.report.controller.resp;

public class Result {
	public static final int SUCCESS=200;
	public static final int USER_EXISTS=300;
	public static final int USER_ERROR=301;
	public static final int EXCEPTION=500;
	public static final int MONITOR_EXCEPTION=400;
	private int code;
	
	public Result() {
		super();
		this.code=SUCCESS;
	}
	public Result(int code) {
		super();
		this.code = code;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}

	
	
}
