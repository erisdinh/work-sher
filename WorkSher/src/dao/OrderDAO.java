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

	public static ArrayList<Order> getAllOrders() {

		Connection connection = null;
		ResultSet rs = null;
		ArrayList<Order> allOrder = new ArrayList<>();

		try {
			connection = DBUtil.getConnection();

			Statement stmt = connection.createStatement();

			rs = stmt.executeQuery("select * from orders order by order_id desc");

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
		Connection connection = null;
		ResultSet rs = null;
		Order order = new Order();

		try {

			connection = DBUtil.getConnection();

			PreparedStatement pstmt = connection.prepareStatement("select * from orders where order_id = ?");

			pstmt.setLong(1, orderid);

			rs = pstmt.executeQuery();

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

	public static ArrayList<Order> getPlacedOrders(long tempRequestUserId) {

		ArrayList<Order> placedOrder = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select * from orders where requestOrderUser_id=? order by order_id desc");

			pstmt.setLong(1, tempRequestUserId);

			rs = pstmt.executeQuery();

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

				placedOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return placedOrder;
	}

	public static ArrayList<Order> getReceivedOrders(long tempPostUserId) {

		ArrayList<Order> receivedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("select * from orders where postOrderUser_id=? order by order_id desc");

			pstmt.setLong(1, tempPostUserId);

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

	public static void updateOrder(Order order) {
		Connection connection = null;

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

		try {
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("update orders set dateResponsed = ?, status = ? " + "where order_id = ?");

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

		try {
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection
					.prepareStatement("update orders set dateCompleted = ?, status = ? " + "where order_id = ?");

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

	public static void deleteOrder(long orderid) {
		Connection connection = null;

		try {
			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement("delete from orders where order_id = ?");

			pstmt.setLong(1, orderid);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
	}

	public static ArrayList<Order> searchReceivedOrdersByJobCategory(long tempRequestUserId, String jobCategory) {
		ArrayList<Order> receivedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where requestOrderUser_id = ? and posting_id in (select posting_id from posting where jobCategory = ?) order by order_id desc");

			pstmt.setLong(1, tempRequestUserId);
			pstmt.setString(2, jobCategory);

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

	public static ArrayList<Order> searchPlacedOrdersByJobCategory(long tempPostUserId, String jobCategory) {
		ArrayList<Order> placedOrder = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where postOrderUser_id = ? and posting_id in (select posting_id from posting where jobCategory = ?) order by order_id desc");

			pstmt.setLong(1, tempPostUserId);
			pstmt.setString(2, jobCategory);

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

				placedOrder.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return placedOrder;
	}

	public static ArrayList<Order> searchReceivedOrdersByTitle(long tempPostUserId, String title) {
		ArrayList<Order> receivedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where postOrderUser_id = ? and posting_id in (select posting_id from posting where title like '%?%') order by order_id desc");

			pstmt.setLong(1, tempPostUserId);
			pstmt.setString(2, title);

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

	public static ArrayList<Order> searchPlacedOrdersByTitle(long tempRequestUserId, String title) {
		ArrayList<Order> placedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where requestOrderUser_id = ? and posting_id in (select posting_id from posting where title like '%?%') order by order_id desc");

			pstmt.setLong(1, tempRequestUserId);
			pstmt.setString(2, title);

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

				placedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return placedOrders;
	}
	
	public static ArrayList<Order> searchReceivedOrdersByStatus(long tempPostUserId, String status) {
		ArrayList<Order> receivedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where postOrderUser_id = ? and status = ? order by order_id desc");

			pstmt.setLong(1, tempPostUserId);
			pstmt.setString(2, status);

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
	
	public static ArrayList<Order> searchPlacedOrdersByStatus(long tempRequestUserId, String status) {
		ArrayList<Order> placedOrders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where requestOrderUser_id = ? and status = ? order by order_id desc");

			pstmt.setLong(1, tempRequestUserId);
			pstmt.setString(2, status);

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

				placedOrders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return placedOrders;
	}
	
	public static ArrayList<Order> searchOrdersByJobCategory(String jobCategory) {
		ArrayList<Order> orders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where posting_id in (select posting_id from posting where jobCategory = ?) order by order_id desc");

			pstmt.setString(1, jobCategory);

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

				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}

		return orders;
	}
	
	public static ArrayList<Order> searchOrdersByTitle(String title) {
		ArrayList<Order> orders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where posting_id in (select posting_id from posting where title like '%?%') order by order_id desc");

			pstmt.setString(1, title);

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

				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return orders;
	}
	
	public static ArrayList<Order> searchOrdersByStatus(String status) {
		ArrayList<Order> orders = new ArrayList<>();

		Connection connection = null;
		ResultSet rs = null;

		try {

			connection = DBUtil.getConnection();
			PreparedStatement pstmt = connection.prepareStatement(
					"select * from orders where status = ? order by order_id desc");

			pstmt.setString(1, status);

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

				orders.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return orders;
	}
}