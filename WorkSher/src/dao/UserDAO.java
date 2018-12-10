package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

	// from Quynh
	// getUserByID will get user information based on the user ID
	// Sample method to test order
	// Please feel free to change anything in here
	public static User getUserById(int userid) {
		Connection connection = null;
		User user = new User();
		
		try {
			connection = DBUtil.getConnection();
			
			PreparedStatement pstmt = connection.prepareStatement("select * from usertest where user_id = ?");
			
			pstmt.setInt(1, userid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return user;
	}
}
