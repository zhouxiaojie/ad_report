package com.ocean.ad.report.model;

public class UserReport {
	private Integer userNum;
	private String event;
	public Integer getUserNum() {
		return userNum;
	}
	public int getDefaultUserNum() {
		if(userNum==null)
			return 0;
		return userNum;
	}
	public void setUserNum(Integer userNum) {
		this.userNum = userNum;
	}
	public String getEvent() {
		return event;
	}
	public void setEvent(String event) {
		this.event = event;
	}
	
	
}
