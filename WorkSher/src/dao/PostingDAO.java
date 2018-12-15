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
			PreparedStatement pStmt = conn.prepareStatement("INSERT INTO posting (user_id, jobCategory, title, description, compensation, status, portfolio) VALUES (?,?,?,?,?,?,?)");
			pStmt.setLong(1, posting.getUserId());
			pStmt.setString(2, posting.getJobCategory());
			pStmt.setString(3, posting.getTitle());
			pStmt.setString(4, posting.getDescription());
			pStmt.setString(5, posting.getCompensation());
			pStmt.setString(6, posting.getStatus());
			pStmt.setBlob(7, posting.getPortfolio());
			
			pStmt.executeUpdate();
		} catch (SQLException ex) {
			ex.printStackTrace();
		} finally {
			DBUtil.closeConnection(conn);
		}
	}
	public static void deletePostingById(long postingId) {
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
		try {
			conn = DBUtil.getConnection();
			PreparedStatement pStmt = conn.prepareStatement("UPDATE posting SET jobCategory = ?, title = ?, description = ?, compensation = ?, status = ?, portfolio = ? WHERE posting_id = ?");
			pStmt.setString(1, posting.getJobCategory());
			pStmt.setString(2, posting.getTitle());
			pStmt.setString(3, posting.getDescription());
			pStmt.setString(4, posting.getCompensation());
			pStmt.setString(5, posting.getStatus());
			pStmt.setBlob(6, posting.getPortfolio());
			pStmt.setLong(7, posting.getPostingId());
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
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting");
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
				//String test = rSet.getBlob("portfoliothumb").toString();
				//System.out.println("hi!:-" + test + "-|");
				try {
					posting.setPortfolio(rSet.getBlob("portfoliothumb").getBinaryStream());
					} catch (Exception ex ) {
						ex.printStackTrace();
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
	public static List<Posting> getAdvancedSearchResults(String searchTitle, String searchCategory, String searchDesc, String searchUser) {

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
			if (searchTitle.equals("") || searchTitle == null) {
				if (searchCategory.equals("") || searchCategory == null) {
					if (searchDesc.equals("") || searchDesc == null) {
						if (searchUser.equals("") || searchUser == null) {
							// keep original statement
						} else {
							// only username
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ?");
							pStmt.setString(1, searchUser);
						}
					} else {
						// description
						if (searchUser.equals("") || searchUser == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ?");
							pStmt.setString(1, searchDesc);
							} 
						// description & username
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description> LIKE ?");
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
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(title) LIKE ?");
							pStmt.setString(1,  searchTitle);		
						} 
						// title & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(title) LIKE ?");
							pStmt.setString(1,  searchUser);
							pStmt.setString(2,  searchTitle);
						}
					}
					// description exists
					else {
						// user does not exist
						// title & description
						if (searchUser.equals("") || searchUser == null) {
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? AND LOWER(title) LIKE ?");
							pStmt.setString(1,  searchDesc);
							pStmt.setString(2,  searchTitle);
							
						} 
						// title & description & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description) LIKE ? AND LOWER(title) LIKE ?");
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
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE jobCategory = ? AND LOWER(title) LIKE ?");
							pStmt.setString(1,  searchCategory);
							pStmt.setString(2,  searchTitle);
						} 
						// title & category & user
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ?");
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
							pStmt = conn.prepareStatement("SELECT * FROM posting WHERE LOWER(description) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ?");
							pStmt.setString(1,  searchDesc);
							pStmt.setString(2,  searchCategory);
							pStmt.setString(3,  searchTitle);
						}
						// EVERYTHING!!!!
						else {
							pStmt = conn.prepareStatement("SELECT * FROM posting p JOIN users u ON p.user_id = u.user_id WHERE LOWER(username) LIKE ? AND LOWER(description) LIKE ? AND jobCategory = ? AND LOWER(title) LIKE ?");
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
				try {
				posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());
				} catch (Exception ex ) {
					ex.printStackTrace();
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
			PreparedStatement pStmt = conn.prepareStatement("SELECT * FROM posting WHERE user_id = ?");
			pStmt.setLong(1, userId);
	
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
				try {
				posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());
				} catch (Exception ex ) {
					ex.printStackTrace();
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
				posting.setJobCategory(rSet.getString("jobCategory"));
				posting.setTitle(rSet.getString("title"));
				posting.setDescription(rSet.getString("description"));
				posting.setCompensation(rSet.getString("compensation"));
				posting.setStatus(rSet.getString("status"));
				posting.setDateCreated(rSet.getDate("dateCreated"));
				posting.setDateUpdated(rSet.getDate("dateUpdated"));
				try {
				posting.setPortfolio(rSet.getBlob("portfolio").getBinaryStream());
				} catch (Exception ex) {
					ex.printStackTrace();
				}
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
}
