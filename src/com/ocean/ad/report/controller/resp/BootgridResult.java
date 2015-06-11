package com.ocean.ad.report.controller.resp;

import java.util.List;

public class BootgridResult<T> {
	
	private int current;
	private int rowCount;
	private List<T>rows;
	private int total;
	
	
	public BootgridResult() {
		super();
	}
	public BootgridResult(int current, int rowCount, List<T> rows, int total) {
		super();
		this.current = current;
		this.rowCount = rowCount;
		this.rows = rows;
		this.total = total;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getRowCount() {
		return rowCount;
	}
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
}
