package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

import model.JobCategory;
import model.Posting;
import model.User;
import util.DBUtil;

public class PostingDAO {
	
	private static Connection conn = null;
	
	public static void addPosting(Posting posting) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO posting (user_id, username, jobCategory, title, description, compensation, status, portfolio, portfoliotype, portfoliolength) VALUES (?,?,?,?,?,?,?,?,?,?)");
			pStmt.setLong(1, posting.getUserId());
			pStmt.setString(2, posting.getUsername());
			pStmt.setString(3, posting.getJobCategory());
			pStmt.setString(4, posting.getTitle());
			pStmt.setString(5, posting.getDescription());
			pStmt.setString(6, posting.getCompensation());
			pStmt.setString(7, posting.getStatus());
			pStmt.setBlob(8, posting.getPortfolio());
			pStmt.setString(9, posting.getPortfolioType());
			pStmt.setInt(10, posting.getPortfolioLength());
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static void deactivatePostingById(long postingId) {

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE posting SET status = ? WHERE posting_id = ?");
				pStmt.setString(1, "inactive");
				pStmt.setLong(2, postingId);
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static void activatePostingById(long postingId) {

		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE posting SET status = ? WHERE posting_id = ?");
				pStmt.setString(1, "active");
				pStmt.setLong(2, postingId);
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	
	public static void deletePostingById(long postingId) {

		PostingDAO.deleteAllReviewsForPostingId(postingId);
		PostingDAO.deleteAllOrdersForPostingId(postingId);
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM posting WHERE posting_id = ?");
				pStmt.setLong(1, postingId);
				
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static void updatePosting(Posting posting) {
		System.out.println("updating: " + posting.getPortfolioType());
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE posting SET jobCategory = ?, title = ?, description = ?, compensation = ?, status = ?, portfolio = ?, portfoliotype = ?, portfoliolength = ?, dateUpdated = ? WHERE posting_id = ?");
			pStmt.setString(1, posting.getJobCategory());
			pStmt.setString(2, posting.getTitle());
			pStmt.setString(3, posting.getDescription());
			pStmt.setString(4, posting.getCompensation());
			pStmt.setString(5, posting.getStatus());
			pStmt.setBlob(6, posting.getPortfolio());
		
			pStmt.setString(7, posting.getPortfolioType());
			pStmt.setInt(8, posting.getPortfolioLength());
			System.out.println(posting.getDateUpdated());
			System.out.println(posting.getDateUpdated().getTime());
			pStmt.setDate(9, new java.sql.Date(posting.getDateUpdated().getTime()));
			
			pStmt.setLong(10, posting.getPostingId());
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static List<Posting> getAllPostings() {
		System.out.println("hi");
		List<Posting> postings = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id ORDER BY dateCreated DESC");
			
			
			ResultSet rSet = pStmt.executeQuery();		

			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setUsername(rSet.getString("username"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				postings.add(posting);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return postings;
	}
	public static List<Posting> getHomePageResults() {
		List<Posting> postings = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id ORDER BY dateCreated DESC LIMIT 5");
			
			
			ResultSet rSet = pStmt.executeQuery();		

			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setUsername(rSet.getString("username"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				postings.add(posting);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return postings;
	}
	
	public static List<Posting> getSearchResults(String searchTerm) {
		List<Posting> postings = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			searchTerm = "%" + searchTerm + "%";
			
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(title) LIKE ? OR LOWER(description) LIKE ? OR LOWER(username) LIKE ? OR LOWER(jobCategory) LIKE ? ORDER BY dateCreated DESC");
			pStmt.setString(1, searchTerm);
			pStmt.setString(2, searchTerm);
			pStmt.setString(3, searchTerm);
			pStmt.setString(4, searchTerm);
			
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				if (rSet.getBlob("portfolio") != null) {
					posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());
				}
				postings.add(posting);
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		
		return postings;
	}
	
	public static List<Posting> getAdvancedSearchResults(String searchTitle, String searchCategory, String searchDesc, String searchUser, String searchStartDate, String searchEndDate) {

		if (!searchTitle.equals("")) {
			searchTitle = "%" + searchTitle + "%";
		}
		if (!searchDesc.equals("")) {
			searchDesc = "%" + searchDesc + "%";
		}
		if (!searchUser.equals("")) {
			searchUser = "%" + searchUser + "%";
		}
		
		System.out.println(searchTitle + " " + searchCategory + " " + searchDesc + " " + searchUser);
		
		List<Posting> postings = new ArrayList<>();
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting");
			String append = " AND dateCreated > '" + searchStartDate + "' AND dateCreated  < '" +searchEndDate +"'";
			
			
			if (searchTitle.equals("") || searchTitle == null) {
				if (searchCategory.equals("") || searchCategory == null) {
					if (searchDesc.equals("") || searchDesc == null) {
						if (searchUser.equals("") || searchUser == null) {
							// keep original statement
						} else {
							// only username
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(p.username) LIKE ?" + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1, searchUser);
						}
					} else {
						// description
						if (searchUser.equals("") || searchUser == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1, searchDesc);
							} 
						// description & username
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description> LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1, searchUser);
							pStmt.setString(2,  searchDesc);
						}
					}
				} else {
					// category exists, description doesnt
					if (searchDesc.equals("") || searchDesc == null) {
						// only category
						if (searchUser.equals("") || searchUser == null) {
							System.out.println(searchCategory);
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE jobCategory = ?");
							pStmt.setString(1,  searchCategory);
						}
						// category and user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND jobCategory = ?");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchCategory);		
						}
					} 
					// category & description exist
					else {
						// category & desc
						if (searchUser.equals("") || searchUser == null){
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? AND jobCategory = ?");
							pStmt.setString(1,  searchDesc);
							pStmt.setString(2,  searchCategory);		
						}
						// category & description & username
						else {pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description) LIKE ? AND jobCategory = ?");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchDesc);
							pStmt.setString(3,  searchCategory);
						}	
					}				
				}
			} 
			// title exists
			else {
				// category does not exist
				if (searchCategory.equals("") || searchCategory == null) {
					// description does not exist
					if (searchDesc.equals("") || searchDesc == null) {
						// user does not exist
						// title only
						if (searchUser.equals("") || searchUser == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchTitle);		
						} 
						// title & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchTitle);
						}
					}
					// description exists
					else {
						// user does not exist
						// title & description
						if (searchUser.equals("") || searchUser == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchDesc);
							pStmt.setString(2,  searchTitle);
							
						} 
						// title & description & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description) LIKE ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchDesc);
							pStmt.setString(3,  searchTitle);				
						}
					}
				} 
				// category exists
				else {
					// description does not exist
					if (searchDesc.equals("") || searchDesc == null) {
						// user does not exist
						// title & category
						if (searchUser.equals("") || searchUser == null) {	
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE jobCategory = ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchCategory);
							pStmt.setString(2,  searchTitle);
						} 
						// title & category & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchCategory);
							pStmt.setString(3,  searchTitle);
						}
					}
					// description exists
					else {
						// user does not exist
						// title, category, description
						if (searchUser.equals("") || searchDesc == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchDesc);
							pStmt.setString(2,  searchCategory);
							pStmt.setString(3,  searchTitle);
						}
						// EVERYTHING!!!!
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ? " + append + " ORDER BY dateCreated DESC");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchDesc);
							pStmt.setString(3,  searchCategory);
							pStmt.setString(4,  searchTitle);
						}		
					}
				}	
			}
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				if (rSet.getBlob("portfolio") != null) {
				posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());

				}
				postings.add(posting);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return postings;
	}
	public static List<Posting> getPostingsByUserId(long userId) {
		List<Posting> postings = new ArrayList<>();
		try {
			conn  = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE user_id = ? ORDER BY dateCreated DESC");
			pStmt.setLong(1, userId);
	
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				Posting posting = new Posting();
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setUsername(rSet.getString("username"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				if (rSet.getBlob("portfolio") != null) {
				posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());

				}
				postings.add(posting);
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
		return postings;
	}
	
	public static Posting getPostingById(long postingId) {
		
		Posting posting = new Posting();
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE posting_id = ?");
			pStmt.setLong(1, postingId);
			
			ResultSet rSet = pStmt.executeQuery();
			while (rSet.next()) {
				posting.setPostingId(rSet.getLong("posting_id"));
				posting.setUserId(rSet.getLong("user_id"));
				posting.setUsername(rSet.getString("username"));
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				posting.setPortfolioLength(rSet.getInt("portfolioLength"));
				posting.setPortfolioType(rSet.getString("portfolioType"));
				if (posting.getPortfolioType()!= null) {
				
					posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());
				}
				System.out.println("getting: " + posting.getPortfolioType());
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}

		return posting;
	}
	
	public static byte[] getImage(Posting posting) {
		byte[] portfolio = null;
		
		try {
			conn = DBUtil.getConnection();
		//	PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE )
			
		} catch (SQLException ex) {
			
		} finally {
			DBUtil.closeConnection(conn);
		}
		return portfolio;
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
	public static void deleteAllOrdersForPostingId(long postingId) {
		
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM orders WHERE posting_id = ?");
				pStmt.setLong(1, postingId);
				
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}

	public static void deleteAllReviewsForPostingId(long postingId) {
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("DELETE FROM reviews WHERE posting_id = ?");
				pStmt.setLong(1, postingId);
				
				pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	
	
	
}
