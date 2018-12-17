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
	public static void addUser(User user) {	
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO users" 
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

	// Method to "delete" the user from the database, this deactivates their account
	public static void deleteUser(User user) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE users SET"
													      + " name = ?,"
														  + " role = ?"
														  + " WHERE user_id = ?");					
			pStmt.setString(1, (user.getName() + " (deleted user)"));
			pStmt.setString(2, "deleted");
			pStmt.setLong(3, user.getUserid());
			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	// Method to update user details in database
	public static void updateUser(User user) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE users SET" 
														+ " user_pass = ?,"
														+ " name = ?,"
														+ " email = ?"
														+ " WHERE user_id = ?");					
			pStmt.setString(1, user.getPassword());
			pStmt.setString(2, user.getName());
			pStmt.setString(3, user.getEmail());
			pStmt.setLong(4, user.getUserid());

			pStmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	// Method to get user info from database by UserID
	public static User getUserById(long userid) {
		User user = new User();
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE user_id = ?");			
			pstmt.setLong(1, userid);		
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next()) {
				user.setUserid(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setDateJoined(rs.getDate("date_joined"));
				user.setRole(rs.getString("role"));
			}			
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return user;
	}	
	
	// Method used for authenticating users during login
	// TESTED - This works
	public static User authenticateUser(String username, String password) {
		User user = new User();
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ? AND user_pass = ?");			
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
		long user_id = user.getUserid();
		boolean isAdmin = false;
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT role FROM users WHERE user_id = ?");			
			pstmt.setLong(1, user_id);
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
	
	public static boolean checkUsername(String username) {		
		boolean usernameTaken = false;
		
		try {
			conn = DBUtil.getConnection();			
			PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM users WHERE username = ?");			
			pstmt.setString(1, username);
			ResultSet rs = pstmt.executeQuery();
						
			if (rs.next()) {
				usernameTaken = true;
			} else {
				usernameTaken = false;
			}	
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return usernameTaken;
	}
}
