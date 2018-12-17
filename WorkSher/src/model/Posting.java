package model;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import model.User;

public class Posting implements Serializable {

	private long postingId;
	private long userId;
	private String username;
	private String jobCategory;
	private String title;
	private String description;
	private String compensation;
	private String status;
	private InputStream portfolio;
	private String portfolioType;
	private int portfolioLength;
	private InputStream portfolioThumb;
	private Date dateCreated;
	private Date dateUpdated;
	
	public Posting() {

	}
	
	// constructors with arguments
	// Please feel free to change it
	public Posting(long postingid, User user, String jobCategory, String description, String compensation, String status, InputStream portfolio) {
		this.postingId = postingid;
		this.userId = user.getUserid();
		this.jobCategory = jobCategory;
		this.description = description;
		this.compensation = compensation;
		this.status = status;
		this.portfolio = portfolio;
		this.dateCreated = dateCreated;
		this.dateUpdated = dateUpdated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public long getPostingId() {
		return postingId;
	}

	public void setPostingId(long postingId) {
		this.postingId = postingId;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
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

	public InputStream getPortfolio() {
		return portfolio;
	}

	public void setPortfolio(InputStream portfolio) {
		this.portfolio = portfolio;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public InputStream getPortfolioThumb() {
		return portfolioThumb;
	}

	public void setPortfolioThumb(InputStream portfolioThumb) {
		this.portfolioThumb = portfolioThumb;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPortfolioType() {
		return portfolioType;
	}

	public void setPortfolioType(String portfolioType) {
		this.portfolioType = portfolioType;
	}

	public int getPortfolioLength() {
		return portfolioLength;
	}

	public void setPortfolioLength(int portfolioLength) {
		this.portfolioLength = portfolioLength;
	}
	
}
