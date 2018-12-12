package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.JobCategory;
import model.Posting;
import model.User;
import util.DBUtil;

public class PostingDAO {
	
	private static Connection conn = null;
	
	public static void addPosting(Posting posting) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO posting (user_id, jobCategory, title, description, compensation, status) VALUES (?,?,?,?,?,?)");
			pStmt.setInt(1, posting.getUserId());
			pStmt.setString(2, posting.getJobCategory());
			pStmt.setString(3, posting.getTitle());
			pStmt.setString(4, posting.getDescription());
			pStmt.setString(5, posting.getCompensation());
			pStmt.setString(6, posting.getStatus());
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static void deletePostingById(int postingId) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM posting WHERE posting_id = ?");
				pStmt.setInt(1, postingId);
				
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static void updatePosting(Posting posting) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE posting SET jobCategory = ?, title = ?, description = ?, compensation = ?, status = ? WHERE posting_id = ?");
			pStmt.setString(1, posting.getJobCategory());
			pStmt.setString(2, posting.getTitle());
			pStmt.setString(3, posting.getDescription());
			pStmt.setString(4, posting.getCompensation());
			pStmt.setString(5, posting.getStatus());
			pStmt.setInt(6, posting.getPostingId());
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static List<Posting> getAllPostings() {
		
		List<Posting> postings = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting");
			ResultSet rSet = pStmt.executeQuery();		

			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getInt("posting_id"));
				posting.setUserId(rSet.getInt("user_id"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				postings.add(posting);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return postings;
	}
	
	public static Posting getPostingById(int postingId) {
		
		Posting posting = new Posting();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE posting_id = ?");
			pStmt.setInt(1, postingId);
			
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				posting.setPostingId(rSet.getInt("posting_id"));
				posting.setUserId(rSet.getInt("user_id"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

		return posting;
	}
	public static JobCategory getCategoryById(String jobCategoryId) {
		JobCategory jobCategory = new JobCategory();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE jobCategoryId = ?");
			pStmt.setString(1, jobCategoryId);
			
			ResultSet rSet = pStmt.executeQuery();
			
			while (rSet.next()) {
				jobCategory.setJobCategoryId(rSet.getString("jobCategoryId"));
				jobCategory.setJobCategoryDesc(rSet.getString("jobCategoryDesc"));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return jobCategory;
	}
	public static List<JobCategory> getAllJobCategories() {
		
		List<JobCategory> jobCategories = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM jobCategory");
			ResultSet rSet = pStmt.executeQuery();		

			while (rSet.next()) {
				JobCategory jobCategory = new JobCategory();

				jobCategory.setJobCategoryId(rSet.getString("jobCategoryId"));
				jobCategory.setJobCategoryDesc(rSet.getString("jobCategoryDesc"));
				
				jobCategories.add(jobCategory);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return jobCategories;
	}	
}
