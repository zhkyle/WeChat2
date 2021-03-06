package com.scie.wechat.function.impl;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.scie.wechat.bean.Student;
import com.scie.wechat.util.DB;
import com.scie.wechat.util.QEncodeUtil;
import com.sice.wechat.function.Functions;

/*
 * 报名功能
 */
public class Apply implements Functions {
	private Student student;
	private String uid;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}
	/**
	 * 已经报名查询返回学生信息
	 * @param request
	 * @return
	 */
	public String selectStuInf() {
		Connection con = DB.getConnection();
		ResultSet rs = null;
		String str = "";
		String sql = "select sname,snum,sphone from runner_apply where uid=?";
		PreparedStatement pstat = DB.getPreparedStatement(con, sql);
		try {
			pstat.setString(1, getUid());
			rs = pstat.executeQuery();
			while(rs.next()) {
				str = "学号:" + rs.getString("snum");
				str = str + " 姓名:" + rs.getString("sname");
				str = str + " 联系方式： " + rs.getString("sphone");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(pstat);
			DB.closeConnection(con);
		}
		return str;
	}
	
	@Override
	public boolean save(HttpServletRequest request) {
		if(this.isExist()) {
			this.generateImg(request);
			return false;
		}
		Connection con = DB.getConnection();
		String sql = "insert into runner_apply (sname,snum,sphone,uid) values(?,?,?,?)";
		PreparedStatement pstat = DB.getPreparedStatement(con, sql);
		try {
			pstat.setString(1, this.getStudent().getSname());
			pstat.setString(2, this.getStudent().getSnum());
			pstat.setString(3, this.getStudent().getSphone());
			pstat.setString(4, getUid());
			pstat.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closePreparedStatement(pstat);
			DB.closeConnection(con);
		}
		
		this.generateImg(request);
		return true;
	}
	
	/**生成二维码*/
	private void generateImg(HttpServletRequest request) {
		try {
			String str = uid;// 二维码内容
			
			str = "run" + str + "ferf";
			/*//对二维码进行加密
			// password 为runningMan md5 加密
			String password = "1234";
			String encryptResult = QEncodeUtil.encrypt(str, password);
			
			System.out.println("encryptResult:   " + encryptResult);
			System.out.println("str:   " + str);*/
			
			String path = request.getSession().getServletContext().getRealPath("/") + "activityImg/" + uid + ".png";
			System.out.println("imgPath:   " + path);
			BitMatrix byteMatrix;
			byteMatrix = new MultiFormatWriter().encode(new String(str.getBytes("UTF-8"),"iso-8859-1"),
					BarcodeFormat.QR_CODE, 200, 200);
			File file = new File(path);
			
			MatrixToImageWriter.writeToFile(byteMatrix, "png", file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean isExist() {
		ResultSet rs = null;
		Connection con = DB.getConnection();
		Statement stat = DB.getStatement(con);
		String sql = "select * from runner_apply where uid = '"+this.getUid()+"'";
		try {
			rs = stat.executeQuery(sql);
			if(rs.next())
				return true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stat);
			DB.closeConnection(con);
		}
		
		return false;
		
	}
}
