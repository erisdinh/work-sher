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
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO usertest" 
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
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usertest WHERE user_id = ?");			
			pstmt.setInt(1, userid);		
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return user;
	}
	
	// Method to get user id number from username (member usernames may change)
	// Returns -1 if user does not exist
	public static int getIdOfUser(String username) {		
		int userId = -1;
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usertest WHERE name = ?");			
			pstmt.setString(1, username);		
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				userId = rs.getInt("user_id");
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}		
		return userId;
	}
	
	// Method used for authenticating users during login
	// TESTED - This works
	public static User authenticateUser(String username, String password) {
		User user = new User();
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM usertest WHERE username = ? AND user_pass = ?");			
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setRole(rs.getString("role"));
			} else {
				user = null;
			}	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return user;
	}
	
	// Method to determine whether or not the user is an admin
	public static boolean authorizeUser(User user) {		
		int user_id = user.getUserid();
		boolean isAdmin = false;
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT role FROM usertest WHERE user_id = ?");			
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();
						
			if ((rs.getString("role")).equals("admin")) {
				isAdmin = true;
			} else {
				isAdmin = false;
			}	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return isAdmin;
	}
}
