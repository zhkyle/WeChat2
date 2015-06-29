package com.scie.wechat.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity

public class Student {
	
	private int sid;
	private String snum;
	private String sclass;
	private String sname;
	private String sphone;
	private String sdep;
	
	public Student() {
		
	}

	public Student(String snum, String sclass, String sname, String sphone,
			String sdep) {
		super();
		this.snum = snum;
		this.sclass = sclass;
		this.sname = sname;
		this.sphone = sphone;
		this.sdep = sdep;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getSnum() {
		return snum;
	}

	public void setSnum(String snum) {
		this.snum = snum;
	}

	public String getSclass() {
		return sclass;
	}

	public void setSclass(String sclass) {
		this.sclass = sclass;
	}

	public String getSphone() {
		return sphone;
	}

	public void setSphone(String sphone) {
		this.sphone = sphone;
	}

	public String getSdep() {
		return sdep;
	}

	public void setSdep(String sdep) {
		this.sdep = sdep;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

}
