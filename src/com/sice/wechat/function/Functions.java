package com.sice.wechat.function;

import javax.servlet.http.HttpServletRequest;

import com.scie.wechat.bean.Student;

public interface Functions {

	public Student getStudent();

	public void setStudent(Student student);

	public String getUid();

	public void setUid(String uid);
	
	public String selectStuInf();

	public boolean save(HttpServletRequest request);
	
	public boolean isExist();
}
