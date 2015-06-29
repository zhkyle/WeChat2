package com.scie.wechat.util;

import java.sql.*;

public class DB {

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection con=null;
		try {
//			String url = "jdbc:mysql://sqld.duapp.com:4050/JfWSkekSItekiajvhVPX?user=tWG0qtMbsmV1zC204pcDMcti&password=GVKC6sAquZDicQk1hjFwEUk4Qe6Y0AAy";
			String url = "jdbc:mysql://rdsyf29un8570sh9ecdl8.mysql.rds.aliyuncs.com:3306/r8k0vo2832108sr9?user=r8k0vo2832108sr9&password=scietx";
//			String url = "jdbc:mysql://localhost/test?user=root&password=199328";
			con = DriverManager.getConnection(url);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return con;
	}
	
	public static void closeConnection(Connection con) {
		try{
			if(con!=null) {
				con.close();
				con=null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static Statement getStatement(Connection con) {
		Statement stat=null;
		try {
			stat=con.createStatement();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return stat;
	}
	
	public static PreparedStatement getPreparedStatement(Connection con,String sql) {
		PreparedStatement pstat=null;
		try {
			pstat=con.prepareStatement(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return pstat;
	}
	
	public static void closePreparedStatement(PreparedStatement pstat) {
		try {
			if(pstat!=null) {
				pstat.close();
				pstat=null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
			
	}
	
	public static void closeStatement(Statement stat) {
		try {
			if(stat!=null) {
				stat.close();
				stat=null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public static ResultSet getResultSet(Statement stat,String sql) {
		ResultSet rs=null;
		try {
			rs=stat.executeQuery(sql);
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null) {
				rs.close();
				rs=null;
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	

}
