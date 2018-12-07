package model;

import java.io.Serializable;
import java.sql.Date;

public class Posting implements Serializable {

	private int postingid;
	private User user;
	private String jobCategory;
	private String description;
	private String compensation;
	private String status;
	private String portfolio;
	private Date dateCreated;
	
	public Posting() {}

	public int getPostingid() {
		return postingid;
	}

	public void setPostingid(int postingid) {
		this.postingid = postingid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCompensation() {
		return compensation;
	}

	public void setCompensation(String compensation) {
		this.compensation = compensation;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(String portfolio) {
		this.portfolio = portfolio;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
}
