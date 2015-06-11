package com.ocean.ad.report.model;

import java.sql.Timestamp;
public class User {
	private Integer id;
	private String username;
	private String password;
	private String remark;
	private Timestamp createTime;
	private Timestamp updateTime;
	
	public User() {
		
	}
	public User( Integer id,String username, String password, String remark,
			Timestamp createTime,Timestamp updateTime) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
