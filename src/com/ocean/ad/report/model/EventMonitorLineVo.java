package com.ocean.ad.report.model;

public class EventMonitorLineVo {
	
	private String time;
	private int num;
	
	
	public EventMonitorLineVo() {
		super();
	}
	public EventMonitorLineVo(String time, int num) {
		super();
		this.time = time;
		this.num = num;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	
}
