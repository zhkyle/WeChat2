package com.scie.wechat.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Date;

import com.scie.wechat.util.DB;

public class VoterManager {

	public static boolean vote(String username, int reqNum) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;

		con = DB.getConnection();
		stat = DB.getStatement(con);
		String sql = "select * from voter where username='" + username + "'";

		try {
			rs = stat.executeQuery(sql);
			if (rs.next()) {
				Timestamp earlyTime = rs.getTimestamp("time");
				if (isOverTime(earlyTime)) {
					updateUser(con, username);
					AdvancedClassManager.addVote(con, reqNum);
					return true;
				} else
					return false;
			}

			else {
				addUser(con, username);
				AdvancedClassManager.addVote(con, reqNum);
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stat);
			DB.closeConnection(con);
		}

	}

	/**
	 * classes vote count views
	 * 
	 * @return count: classes vote count
	 */
	public String voteCount() {
		String sql = "select * from votecounter";
		String msg = "";

		Statement stmt = null;
		ResultSet rs = null;
		Connection con = DB.getConnection();
		try {
			stmt = con.createStatement();
			boolean hasResultSet = stmt.execute(sql);
			if (hasResultSet) {
				rs = stmt.getResultSet();
				while (rs.next()) {
					String className = rs.getString("classname");
					int count = rs.getInt("votecount");
					msg = msg + className + ": 票数: " + count + "\n";
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}  finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stmt);
			DB.closeConnection(con);
		}

		return msg;
	}

	private static boolean addUser(Connection con, String username) {

		PreparedStatement pstat = null;

		con = DB.getConnection();
		String sql = "insert into voter values(?,?)";

		pstat = DB.getPreparedStatement(con, sql);

		try {
			pstat.setString(1, username);
			pstat.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			pstat.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {

			DB.closePreparedStatement(pstat);

		}

		return true;
	}

	private static boolean updateUser(Connection con, String username) {

		PreparedStatement pstat = null;

		con = DB.getConnection();
		String sql = "update voter set time=? where username=?";

		pstat = DB.getPreparedStatement(con, sql);

		try {
			pstat.setTimestamp(1, new Timestamp(System.currentTimeMillis()));
			pstat.setString(2, username);
			pstat.executeUpdate();

		} catch (SQLException e) {

			e.printStackTrace();
			return false;
		} finally {

			DB.closePreparedStatement(pstat);

		}

		return true;
	}

	private static boolean isOverTime(Timestamp earlyTime) {

		Timestamp currentTime = new Timestamp(System.currentTimeMillis());
	//	long hours = getTimeDifference(earlyTime, currentTime);
		
		//截取日期
		String[] etStr = earlyTime.toString().split(" ");
		String[] ctStr = currentTime.toString().split(" ");
//System.out.println(etStr[0].equals(ctStr[0]));
		// 每天只能投一票
		if (etStr[0].equals(ctStr[0]))
			return false;
		else
			return true;

	}

	// 返回小时
	private static long getTimeDifference(Date d1, Date d2) {
		long dif = d2.getTime() - d1.getTime();
		return dif / (1000 * 60 * 60);
	}
	
	
}
