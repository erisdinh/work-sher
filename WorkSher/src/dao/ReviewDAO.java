package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.Date;

import model.Review;
import model.User;
import util.DBUtil;

public class ReviewDAO {
	
	private static Connection conn = null;

	public static void addReview(Review review) {
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO reviews (for_user_id, from_user_id, posting_id, review_rating, review_text) "
					+ "VALUES (?, ?, ?, ?, ?");
			stmt.setLong(1, review.getForUserId());
			stmt.setLong(2, review.getFromUserId());
			stmt.setLong(3, review.getPostingId());
			stmt.setDouble(4, review.getReviewRating());
			stmt.setString(5, review.getReviewText());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static void editReview(Review review) {
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("UPDATE reviews SET review_rating=?, review_text=? WHERE review_id=?");
			stmt.setDouble(1, review.getReviewRating());
			stmt.setString(2, review.getReviewText());
			stmt.setLong(3, review.getReviewId());
			stmt.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static void deleteReviewById(long reviewId) {
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM reviews WHERE review_id=?");
			stmt.setLong(1, reviewId);
			stmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static Review getReviewById(long reviewId) {
		Review review = new Review();
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reviews WHERE review_id=?");
			stmt.setLong(1, reviewId);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				review.setReviewId(rs.getLong("review_id"));
				review.setForUserId(rs.getLong("for_user_id"));
				review.setFromUserId(rs.getLong("from_user_id"));
				review.setReviewDate(new Date(rs.getDate("review_date").getTime()));
				review.setOrderId(rs.getLong("order_id"));
				review.setPostingId(rs.getLong("posting_id"));
				review.setReviewRating(rs.getDouble("review_rating"));
				review.setReviewText(rs.getString("review_text"));
				
				User user = UserDAO.getUserById(review.getForUserId());
				review.setForUsername(user.getUsername());
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return review;
	}

	public static List<Review> getReviewsByForUserId(long forUserId) {
		List<Review> reviews = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT users.username, reviews.* FROM reviews "
					+ "INNER JOIN users ON users.user_id=reviews.for_user_id WHERE users.user_id=? ORDER BY review_date DESC");
			stmt.setLong(1, forUserId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setForUserId(rs.getLong("for_user_id"));
				review.setFromUserId(rs.getLong("from_user_id"));
				review.setForUsername(rs.getString("username"));
				review.setPostingId(rs.getLong("posting_id"));
				review.setReviewDate(new Date(rs.getDate("review_date").getTime()));
				review.setReviewRating(rs.getDouble("review_rating"));
				review.setReviewText(rs.getString("review_text"));
				
				User user = UserDAO.getUserById(review.getFromUserId());
				review.setFromUsername(user.getUsername());
				reviews.add(review);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return reviews;

	}
	
	public static List<Review> getReviewsByFromUserId(long fromUserId) {
		List<Review> reviews = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT users.username, reviews.* FROM reviews "
					+ "INNER JOIN users ON users.user_id=reviews.from_user_id WHERE users.user_id=? ORDER BY review_date DESC");
			stmt.setLong(1, fromUserId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setForUserId(rs.getLong("for_user_id"));
				review.setFromUserId(rs.getLong("from_user_id"));
				review.setFromUsername(rs.getString("username"));
				review.setPostingId(rs.getLong("posting_id"));
				review.setReviewDate(new Date(rs.getDate("review_date").getTime()));
				review.setReviewRating(rs.getDouble("review_rating"));
				review.setReviewText(rs.getString("review_text"));
				
				User user = UserDAO.getUserById(review.getForUserId());
				review.setForUsername(user.getUsername());
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return reviews;

	}

	public static List<Review> getReviewsByPostingId(long postingId) {
		List<Review> reviews = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reviews WHERE posting_id=?");
			stmt.setLong(1, postingId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setForUserId(rs.getLong("for_user_id"));
				review.setFromUserId(rs.getLong("from_user_id"));
				review.setPostingId(rs.getLong("posting_id"));
				review.setReviewDate(new Date(rs.getDate("review_date").getTime()));
				review.setReviewRating(rs.getDouble("review_rating"));
				review.setReviewText(rs.getString("review_text"));
				reviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return reviews;
	}

}
