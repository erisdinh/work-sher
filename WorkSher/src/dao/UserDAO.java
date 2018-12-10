package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;
import util.DBUtil;

public class UserDAO {
	private static Connection conn = null;
	
	// Method to add the user to the database
	// TESTED - This works
	public static void addUser(User user) {	
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
			
			/* TO DO:
			 * - Make sure usernames are unique before updating
			 */
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	// Method to get user info from database by UserID
	public static User getUserById(int userid) {
		User user = new User();
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("select * from usertest where user_id = ?");			
			pstmt.setInt(1, userid);		
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return user;
	}
}
