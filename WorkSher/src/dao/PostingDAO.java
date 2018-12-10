package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Posting;
import model.User;
import util.DBUtil;

public class PostingDAO {

	// from Quynh
	// sample method for testing order loading
	// please feel free to change anything in here
	public static Posting getPostingById(int postingid) {
		Connection connection = null;
		Posting posting = new Posting();
		
		try {
			connection = DBUtil.getConnection();
			
			PreparedStatement pstmt = connection.prepareStatement("select * from posting where posting_id = ?");
			
			pstmt.setInt(1, postingid);
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				posting.setPostingid(rs.getInt("posting_id"));
				posting.setUser(UserDAO.getUserById(rs.getInt("user_id")));
				posting.setJobCategory(rs.getString("jobCategory"));
				posting.setDescription(rs.getString("description"));
				posting.setCompensation(rs.getString("compensation"));
				posting.setStatus(rs.getString("status"));
				posting.setPortfolio("portfolio");
				posting.setDateCreated(rs.getDate("dateCreated"));
			}
			
		} catch(SQLException e) {
			e.getMessage();
		} finally {
			DBUtil.closeConnection(connection);
		}
		
		return posting;
	}

}
