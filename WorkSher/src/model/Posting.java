package model;

import java.io.Serializable;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class Posting implements Serializable {

	private int postingId;
	private int userId;
	private String jobCategory;
	private String title;
	private String description;
	private String compensation;
	private String status;
	private String portfolio;
	private Date dateCreated;
	
	public Posting() {
		//portfolio = new ArrayList<>();
	}
	
	// constructors with arguments
	// Please feel free to change it
	public Posting(int postingid, User user, String jobCategory, String desscription, String compensation, String status, String portfolio) {
		this.postingId = postingid;
		this.userId = user.getUserid();
		this.jobCategory = jobCategory;
		this.description = desscription;
		this.compensation = compensation;
		this.status = status;
		this.portfolio = portfolio;
		this.dateCreated = dateCreated;
	}

	public int getPostingId() {
		return postingId;
	}

	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getJobCategory() {
		return jobCategory;
	}

	public void setJobCategory(String jobCategory) {
		this.jobCategory = jobCategory;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
