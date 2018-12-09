package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import model.*;

import java.util.ArrayList;

public class OrderDAO {

	
	// Tested - It works!! YEAH!!!!
	public static void addOrder(Order order) throws SQLException {
		
		Connection connection = null;
		
		try {
			connection = DBUtil.getConnection();
			
			PreparedStatement pstmt = connection.prepareStatement("insert into orders(requestOrderUser_id, postOrderUser_id, posting_id, description, dateResponsed, dateCompleted, status) values"
					+ "(?, ?, ?, ?, ?, ?, ?);");
			pstmt.setInt(1, order.getRequestUser().getUserid());
			pstmt.setInt(2, order.getPosting().getUser().getUserid());
			pstmt.setInt(3, order.getPosting().getPostingid());
			pstmt.setString(4, order.getDescription());
			pstmt.setDate(5, order.getDateResponsed());
			pstmt.setDate(6, order.getDateCompleted());
			pstmt.setString(7, order.getStatus());
			
			pstmt.executeUpdate();
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			connection.close();
		}
	}
	
	public static ArrayList<Order> getAllOrder() throws SQLException {
		
		Connection connection = null;
		ArrayList<Order> allOrder = new ArrayList<>();
		
		try {
			connection = DBUtil.getConnection();
			
			Statement stmt = connection.createStatement();
			
			ResultSet rs = stmt.executeQuery("select * from orders");
			
			while(rs.next()) {
				
				Order order = new Order();
				
				order.setOrderid(rs.getInt("order_id"));
				
				int requestUserId = rs.getInt("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);
				
				int postUserId = rs.getInt("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);
				
				int postingid = rs.getInt("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);
				
				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));
				
				
				allOrder.add(order);
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			connection.close();
		}
		
		return allOrder;
	}
	
	public static Order getOrderById(int orderid) throws SQLException {
		
		Connection connection = null;
		Order order = new Order();
		
		try {
			
			connection = DBUtil.getConnection();
			
			PreparedStatement pstmt = connection.prepareStatement("select * from orders where order_id = ?");
			
			pstmt.setInt(1, orderid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				order.setOrderid(rs.getInt("order_id"));
				
				int requestUserId = rs.getInt("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);
				
				int postUserId = rs.getInt("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);
				
				int postingid = rs.getInt("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);
				
				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			connection.close();
		}
		
		return order;
	}
}