package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.DBUtil;
import model.*;

import java.util.ArrayList;

public class OrderDAO {

	// Tested - It works!! YEAH!!!!
	public static void addOrder(Order order) {

		Connection connection = null;

		try {
			connection = DBUtil.getConnection();

			PreparedStatement pstmt = connection.prepareStatement(
					"insert into orders(requestOrderUser_id, postOrderUser_id, posting_id, description, status) values"
							+ "(?, ?, ?, ?, ?)");
			pstmt.setLong(1, order.getRequestUser().getUserid());
			pstmt.setLong(2, order.getPosting().getUserId());
			pstmt.setLong(3, order.getPosting().getPostingId());
			pstmt.setString(4, order.getDescription());
			pstmt.setString(5, order.getStatus());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	public static ArrayList<Order> getAllOrder() {

		Connection connection = null;
		ArrayList<Order> allOrder = new ArrayList<>();

		try {
			connection = DBUtil.getConnection();

			Statement stmt = connection.createStatement();

			ResultSet rs = stmt.executeQuery("select * from orders");

			while (rs.next()) {

				Order order = new Order();

				order.setOrderid(rs.getLong("order_id"));

				long requestUserId = rs.getLong("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);

				long postUserId = rs.getLong("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);

				long postingid = rs.getLong("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);

				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));

				allOrder.add(order);
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return allOrder;
	}

	public static Order getOrderById(long orderid) {
		System.out.println("In getOrderById");
		Connection connection = null;
		Order order = new Order();

		try {

			connection = DBUtil.getConnection();

			PreparedStatement pstmt = connection.prepareStatement("select * from orders where order_id = ?");

			pstmt.setLong(1, orderid);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				order.setOrderid(rs.getLong("order_id"));

				long requestUserId = rs.getLong("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);

				long postUserId = rs.getLong("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);

				long postingid = rs.getLong("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);

				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));
			}

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return order;
	}

	public static ArrayList<Order> getRequestedOrders(long requestUserId) {

		ArrayList<Order> requestedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select * from orders where requestOrderUser_id=? order by order_id desc");

			pstmt.setLong(1, requestUserId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getLong("order_id"));

				long tempRequestUserId = rs.getLong("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);

				long postUserId = rs.getLong("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);

				long postingid = rs.getLong("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);

				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));

				requestedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return requestedOrders;
	}

	public static ArrayList<Order> getReceivedOrders(long postUseId) {

		ArrayList<Order> receivedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select * from orders where postOrderUser_id=? order by order_id desc");

			pstmt.setLong(1, postUseId);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setOrderid(rs.getInt("order_id"));

				long requestUserId = rs.getLong("requestOrderUser_id");
				User requestUser = UserDAO.getUserById(requestUserId);
				order.setRequestUser(requestUser);

				long postUserId = rs.getLong("postOrderUser_id");
				User postUser = UserDAO.getUserById(postUserId);
				order.setPostUser(postUser);

				long postingid = rs.getLong("posting_id");
				Posting posting = PostingDAO.getPostingById(postingid);
				order.setPosting(posting);

				order.setDescription(rs.getString("description"));
				order.setDateRequested(rs.getDate("dateRequested"));
				order.setDateResponsed(rs.getDate("dateResponsed"));
				order.setDateCompleted(rs.getDate("dateCompleted"));
				order.setStatus(rs.getString("status"));

				receivedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return receivedOrders;
	}

	public static void editOrder(Order order) {
		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("update orders set description = ?, status = ? where order_id = ?");

			pstmt.setString(1, order.getDescription());
			pstmt.setString(2, order.getStatus());
			pstmt.setLong(1, order.getOrderid());

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	public static void responseOrder(Order order) {
		Connection connection = null;
		ResultSet rs = null;

		try {
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"update orders set dateResponsed = ?, status = ? "
					+ "where order_id = ?");

			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			pstmt.setString(2, order.getStatus());
			pstmt.setLong(3, order.getOrderid());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
	}
	
	public static void markCompletedOrder(Order order) {
		Connection connection = null;
		ResultSet rs = null;

		try {
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"update orders set dateCompleted = ?, status = ? "
					+ "where order_id = ?");

			pstmt.setTimestamp(1, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
			pstmt.setString(2, order.getStatus());
			pstmt.setLong(3, order.getOrderid());
			
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
	}
}