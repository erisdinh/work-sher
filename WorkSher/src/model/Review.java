package model;

import java.io.Serializable;
import java.util.Date;

public class Review implements Serializable {
	private long forUserId;
	private long fromUserId;
	private String forUsername;
	private String fromUsername;
	private long postingId;
	private long reviewId;
	private Date reviewDate;
	private double reviewRating;
	private String reviewText;

	public Review() {
		forUserId = 0;
		fromUserId = 0;
		forUsername = "";
		fromUsername = "";
		postingId = 0;
		reviewId = 0;
		reviewDate = new Date();
		reviewRating = 0;
		reviewText = "";
	}

	public Review(long reviewId, long forUserId, long fromUserId, String forUsername, String fromUsername, long postingId, Date reviewDate, double reviewRating, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.forUserId = forUserId;
		this.fromUserId = fromUserId;
		this.forUsername = forUsername;
		this.fromUsername = fromUsername;
		this.postingId = postingId;
		this.reviewDate = reviewDate;
		this.reviewRating = reviewRating;
		this.reviewText = reviewText;
	}

	public long getForUserId() {
		return forUserId;
	}

	public void setForUserId(long forUserId) {
		this.forUserId = forUserId;
	}
	
	public long getFromUserId() {
		return fromUserId;
	}
	
	public void setFromUserId(long fromUserId) {
		this.fromUserId = fromUserId;
	}
	
	public String getForUsername() {
		return forUsername;
	}
	
	public void setForUsername(String forUsername) {
		this.forUsername = forUsername;
	}
	
	public String getFromUsername() {
		return fromUsername;
	}
	
	public void setFromUsername(String fromUsername) {
		this.fromUsername = fromUsername;
	}

	public long getPostingId() {
		return postingId;
	}

	public void setPostingId(long postingId) {
		this.postingId = postingId;
	}

	public long getReviewId() {
		return reviewId;
	}

	public void setReviewId(long reviewId) {
		this.reviewId = reviewId;
	}

	public Date getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(Date reviewDate) {
		this.reviewDate = reviewDate;
	}

	public double getReviewRating() {
		return reviewRating;
	}

	public void setReviewRating(double reviewRating) {
		this.reviewRating = reviewRating;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

}
