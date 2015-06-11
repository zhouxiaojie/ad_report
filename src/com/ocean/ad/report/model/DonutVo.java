package com.ocean.ad.report.model;

public class DonutVo {
	private String label;
	private int value;
	
	public DonutVo() {
		super();
	}
	public DonutVo(String label, int value) {
		super();
		this.label = label;
		this.value = value;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	
	
}
