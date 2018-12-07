package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

public class UserDAO {
	
	// Method to add the user to the database
	// TESTED - This works
	public static void addUser(User user) {
		Connection conn = null;		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("insert into usertest" 
														+ "(username, user_pass, name, email, date_joined, role)"
														+ " values (?, ?, ?, ?, ?, ? )");					
			pStmt.setString(1, user.getUsername());
			pStmt.setString(2, user.getPassword());
			pStmt.setString(3, user.getName());
			pStmt.setString(4, user.getEmail());
			pStmt.setDate(5, new java.sql.Date (user.getDateJoined().getTime()));
			pStmt.setString(6, user.getRole());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static User getUserById(int userid) {
		// TODO Auto-generated method stub
		return null;
	}
}
