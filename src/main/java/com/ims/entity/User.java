package com.ims.entity;



import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	private int id;
	private String username;
	private String password;
	private String logintime;
	private int usertype;
	private int userstatus;
	private String regtime;
	private String remark;





	public int getId() {
		return id;
	}
	public void setId(int id) {
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

	public String getLogintime() { return logintime; }
	public void setLogintime(String logintime) { this.logintime = logintime; }

	public int getUsertype() {
		return usertype;
	}
	public void setUsertype(int usertype) {
		this.usertype = usertype;
	}

	public int getUserstatus() {
		return userstatus;
	}
	public void setUserstatus(int userstatus) {
		this.userstatus = userstatus;
	}

	public String getRegtime() { return regtime; }
	public void setRegtime(String regtime) { this.regtime = regtime; }

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	
	

}
