package com.scie.wechat.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.scie.wechat.util.DB;

public class AdvancedClassManager {
	
	public static boolean addVote(Connection con,int classNum) {
		
		
		PreparedStatement pstat=null;
		String sql=null;
		/*int situation;
		
		
		if(AdvancedClassManager.getVoteCount(classNum)==0) {
			situation = 1;
		} else {
			situation = 2;
		}
		
		if(situation==1) {
			sql = "insert into votecounter values(?,?)";
		} else {*/
			sql = "update votecounter set votecount=? where classnum=?";
//		}
		pstat = DB.getPreparedStatement(con, sql);
		
		try {
			/*if(situation==1) {
				pstat.setInt(1, classNum);
				pstat.setInt(2, AdvancedClassManager.getVoteCount(classNum)+1);
			}
			else {*/
				pstat.setInt(1, AdvancedClassManager.getVoteCount(classNum)+1);
				pstat.setInt(2, classNum);
//			}
			pstat.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		} finally {
			
			DB.closePreparedStatement(pstat);
			
		}
		
		return true;
		
	}
	
	public static int getVoteCount(int classNum) {
		Connection con = null;
		Statement stat = null;
		ResultSet rs = null;
		int voteCount=0;
		
		con = DB.getConnection();
		stat = DB.getStatement(con);
		String sql= "select * from votecounter where classnum="+classNum;

		try {
			rs=stat.executeQuery(sql);
			if(!rs.next())
				return 0;
			else {
				voteCount = rs.getInt("votecount");
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(stat);
			DB.closeConnection(con);
		}
		
		return voteCount;
	}
}
