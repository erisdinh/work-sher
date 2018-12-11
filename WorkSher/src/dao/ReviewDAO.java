package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Date;

import model.Review;
import util.DBUtil;

public class ReviewDAO {
	
	private static Connection conn = null;

	public static void addReview(Review review) {
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("INSERT INTO reviews (user_id, posting_id, review_rating, review_text) VALUES (?, ?, ?, ?)");
			stmt.setLong(1, review.getUserId());
			stmt.setLong(2, review.getPostingId());
			stmt.setDouble(3, review.getReviewRating());
			stmt.setString(4, review.getReviewText());
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

	public static void deleteReview(Review review) {
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("DELETE FROM reviews WHERE review_id=?");
			stmt.setLong(1, review.getReviewId());
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
				review.setUserId(rs.getLong("user_id"));
				review.setReviewDate(new Date(rs.getDate("review_date").getTime()));
				review.setPostingId(rs.getLong("posting_id"));
				review.setReviewRating(rs.getDouble("review_rating"));
				review.setReviewText(rs.getString("review_text"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return review;
	}

	public static ArrayList<Review> getReviewsByUserId(long userId) {
		ArrayList<Review> reviews = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT usertest.username, reviews.* FROM reviews "
					+ "INNER JOIN usertest ON usertest.user_id=reviews.user_id WHERE usertest.user_id=? ORDER BY review_date DESC");
			stmt.setLong(1, userId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setUserId(rs.getLong("user_id"));
				review.setUsername(rs.getString("username"));
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
	
	public static ArrayList<Review> getReviewsByUserId(long userId, int startIndex, int numPerPage) {
		ArrayList<Review> reviews = new ArrayList<>();
		
		int endIndex = startIndex + numPerPage;
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT usertest.username, reviews.* FROM reviews "
					+ "INNER JOIN usertest ON usertest.user_id=reviews.user_id WHERE usertest.user_id=? "
					+ "ORDER BY review_date DESC LIMIT ?, ?");
			stmt.setLong(1, userId);
			stmt.setInt(2, startIndex);
			stmt.setInt(3, endIndex);

			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setUserId(rs.getLong("user_id"));
				review.setUsername(rs.getString("username"));
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

	public static ArrayList<Review> getReviewsByPostingId(long postingId) {
		ArrayList<Review> reviews = new ArrayList<>();
		
		try {
			conn = DBUtil.getConnection();
			
			PreparedStatement stmt = conn.prepareStatement("SELECT * FROM reviews WHERE posting_id=?");
			stmt.setLong(1, postingId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Review review = new Review();
				review.setReviewId(rs.getLong("review_id"));
				review.setUserId(rs.getLong("user_id"));
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
