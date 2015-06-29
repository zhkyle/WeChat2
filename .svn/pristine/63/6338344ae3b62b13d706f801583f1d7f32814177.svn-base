package com.scie.wechat.test;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.junit.Test;

import com.scie.wechat.bean.UserRecorder;
import com.scie.wechat.recruitment.RecruitmentStudent;
import com.scie.wechat.service.AdvancedClassManager;
import com.scie.wechat.service.VoterManager;
import com.scie.wechat.util.DB;

public class TestHibernate {

	@Test
	public void testRecruit() {
		RecruitmentStudent recruitmentStudent = new RecruitmentStudent();
		recruitmentStudent.recruit("2012210569", "张天乐", "0101206", "18883863755", "美宣部");
	}
	
	@Test
	public void testDanLi() {
		Map m1=UserRecorder.getStatus();
		Map m2=UserRecorder.getStatus();
		System.out.println(m1==m2);
	}
	
	@Test
	public void testUserValidate() {
		System.out.println(VoterManager.vote("dfdf",1));
	}
	
	@Test
	public void testAddUser() {
//		VoterManager.addUser("abc");
	}
	
	@Test
	public void testVoteCounter() {
		AdvancedClassManager.addVote(DB.getConnection(),1);
		System.out.println(AdvancedClassManager.getVoteCount(1));
	}
	
	@Test
	public void testTimeDifference() throws Exception{
		/*
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date d1 = df.parse("2004-03-26 13:31:40");
			Date d2 = df.parse("2004-03-27 13:31:39");
	
			
			
		} catch (ParseException e) {
			
			e.printStackTrace();
		}
		*/
		//System.out.println(VoterManager.isOverTime("avc"));
		//System.out.println(VoterManager.isOverTime(new Timestamp(System.currentTimeMillis()-24*60*60*1000)));
		
	}
	
	@Test
	public void testUpdateUser() {
		Connection con = DB.getConnection();
//		VoterManager.updateUser(con, "avc");
	}
	
	@Test
	public void testVote() {
		assertTrue(VoterManager.vote("aa", 3));
//		System.out.println(VoterManager.vote("aaa",2));
	}
}
